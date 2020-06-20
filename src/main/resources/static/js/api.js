$(document).ready(function () {
    loadTimeTable();
    $('#datepicker').datepicker({
        clearBtn: true,
        format: 'yyyy-mm-dd',
        language: 'it-IT'
    }).on('changeDate', function (e) {
        //console.log(e);
        //var cond1 = e['date'] === undefined || e['date'] === null;
        //var cond2 = $('#add-event-btn').hasClass('d-none')
        if (e['date'] === undefined || e['date'] === null) {
            if (!$('#add-event-btn').hasClass('d-none')) {
                $('#add-event-btn').toggleClass('d-none');
            }
        } else {
            if (e['date'] instanceof Date) {
                console.log(e['date'])
            }
            if ($('#add-event-btn').hasClass('d-none')) {
                $('#add-event-btn').toggleClass('d-none');
                if (!$('#add-event-form').hasClass('d-none')) {
                    $('#add-event-form').toggleClass('d-none')
                }
            }
        }
        loadTimeTable();
    }).on('clearDate', function (e) {
        //console.log(e);
        if (!$('#add-event-btn').hasClass('d-none')) {
            $('#add-event-btn').toggleClass('d-none')
        }
        if (!$('#add-event-form').hasClass('d-none')) {
            $('#add-event-form').toggleClass('d-none')
        }
    });
    $('#datepicker').datepicker('setDate', new Date());

    $('#add-event-btn').click(function () {
        $('#add-event-btn').toggleClass('d-none');
        $('#add-event-form').toggleClass('d-none');
    })
    $('#save-event-btn').click(function () {

        var event = {};
        event['text'] = $('#add-event-form').find('#eventText').val();
        event['start'] = $('#add-event-form').find('#eventStart').val();
        event['end'] = $('#add-event-form').find('#eventEnd').val();
        event['date'] = formatDate($('#datepicker').datepicker('getDate'));
        event['idSpace'] = $('#add-event-form').find('#space-slot-select').val();
        addEvent(event);
    })
});

function addEvent(event) {
    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/api/save-new-event",
        data: JSON.stringify(event),
        dataType: 'json',
        cache: false,
        timeout: 600000,
        success: function (data) {
            $('#add-event-btn').toggleClass('d-none');
            $('#add-event-form').toggleClass('d-none');
            console.log("SUCCESS : ", data);
            location.reload();

        },
        error: function (e) {
            $('#error-feedback-alert').toggleClass('d-none');
            $('#error-feedback-alert').find('.alert').html(e.responseText)
            console.log("ERROR : ", e);
            setTimeout(toggleErrorFeedbackAlert, 10000);
        }
    });
}

function toggleErrorFeedbackAlert() {
    $('#error-feedback-alert').toggleClass('d-none');
}

function loadTimeTable() {
    $.get("/api/all-spaces", function (data, status) {
        console.log("Data: " + JSON.stringify(data) + "\nStatus: " + status);
        if ($.isEmptyObject(data)) {
            $('#no-spaces-alert').toggleClass("d-none");
            $('#timetable-body').toggleClass("d-none");
        } else {
            var select = $('#space-slot-select');
            data.forEach(elem => {
                select.append('<option value="' + elem.id + '">' + elem.text + '</option>');
            })
            var timetable = new Timetable();
            timetable.setScope(0, 23)
            timetable.addLocations(data.map(elem => elem.text));
            getTimes(timetable);
        }
    });
}

function getTimes(timetable) {
    var stringDate = formatDate($('#datepicker').datepicker('getDate'));
    /*$.get("/api/all-times", function (data, status) {
     console.log("Data: " + JSON.stringify(data) + "\nStatus: " + status);
     if ($.isEmptyObject(data)) {
     
     }
     });*/
    $.ajax({
        type: "GET",
        url: "/api/all-times",
        data: {
            date: stringDate
        },
        success: function (response) {
            console.log("SUCCESS : ", response);
            response.forEach(event => {
                timetable.addEvent(
                        event['text'],
                        event['space']['text'],
                        new Date(event['id']['startTime']),
                        new Date(event['id']['endTime']),
                        {onClick: onClickEvent});
            });
            var renderer = new Timetable.Renderer(timetable);
            renderer.draw('.timetable');
        },
        error: function (e) {
            $('#error-feedback-alert').toggleClass('d-none');
            $('#error-feedback-alert').find('.alert').html(e.responseText)
            console.log("ERROR : ", e)
        }
    });
}

function onClickEvent(event) {
    window.alert('You clicked on the ' + event.name + ' event in ' + event.location + '. This is an example of a click handler');
}

function fire_ajax_submit() {
    var event = {};
    event['text'] = "esempio di test";
    event['date'] = "2020-06-21";
    event['start'] = "09:00";
    event['end'] = "10:00";
    event['idSpace'] = 1;
    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/api/save-new-event",
        data: JSON.stringify(event),
        dataType: 'json',
        cache: false,
        timeout: 600000,
        success: function (data) {

            var json = "<h4>Ajax Response</h4>&lt;pre&gt;"
                    + JSON.stringify(data, null, 4) + "&lt;/pre&gt;";
            $('#feedback').html(json);

            console.log("SUCCESS : ", data);

        },
        error: function (e) {

            var json = "<h4>Ajax Response</h4>&lt;pre&gt;"
                    + e.responseText + "&lt;/pre&gt;";
            $('#feedback').html(json);

            console.log("ERROR : ", e);

        }
    });
}
function formatDate(date) {
    var d = new Date(date),
            month = '' + (d.getMonth() + 1),
            day = '' + d.getDate(),
            year = d.getFullYear();

    if (month.length < 2)
        month = '0' + month;
    if (day.length < 2)
        day = '0' + day;

    return [year, month, day].join('-');
}
/*
 var timetable = new Timetable();
 
 timetable.setScope(0, 23)
 
 timetable.addLocations(['Rotterdam', 'Madrid', 'Los Angeles', 'London', 'New York', 'Jakarta', 'Tokyo']);
 
 timetable.addEvent('Sightseeing', 'Rotterdam', new Date(2015, 7, 17, 9, 00), new Date(2015, 7, 17, 11, 30), {url: '#'});
 timetable.addEvent('Zumba', 'Madrid', new Date(2015, 7, 17, 12), new Date(2015, 7, 17, 13), {url: '#'});
 timetable.addEvent('Zumbu', 'Madrid', new Date(2015, 7, 17, 13, 30), new Date(2015, 7, 17, 15), {url: '#'});
 timetable.addEvent('Lasergaming', 'London', new Date(2015, 7, 17, 17, 45), new Date(2015, 7, 17, 19, 30), {class: 'vip-only', data: {maxPlayers: 14, gameType: 'Capture the flag'}});
 timetable.addEvent('All-you-can-eat grill', 'New York', new Date(2015, 7, 17, 21), new Date(2015, 7, 18, 1, 30), {url: '#'});
 timetable.addEvent('Hackathon', 'Tokyo', new Date(2015, 7, 17, 11, 30), new Date(2015, 7, 17, 20)); // options attribute is not used for this event
 timetable.addEvent('Tokyo Hackathon Livestream', 'Los Angeles', new Date(2015, 7, 17, 12, 30), new Date(2015, 7, 17, 16, 15)); // options attribute is not used for this event
 timetable.addEvent('Lunch', 'Jakarta', new Date(2015, 7, 17, 9, 30), new Date(2015, 7, 17, 11, 45), {onClick: function (event) {
 window.alert('You clicked on the ' + event.name + ' event in ' + event.location + '. This is an example of a click handler');
 }});
 timetable.addEvent('Cocktails', 'Rotterdam', new Date(2015, 7, 18, 00, 00), new Date(2015, 7, 18, 02, 00), {class: 'vip-only'});
 
 var renderer = new Timetable.Renderer(timetable);
 renderer.draw('.timetable');
 */
