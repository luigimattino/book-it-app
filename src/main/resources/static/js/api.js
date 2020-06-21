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
    $('#cancel-btn').click(function () {
        $('#add-event-form').find('#eventText').val("");
        $('#add-event-form').find('#eventStart').val("");
        $('#add-event-form').find('#eventEnd').val("");
        $('#add-event-form').find('#space-slot-select').val("");
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
            if ($("option").length < 2) {
                var select = $('#space-slot-select');
                data.forEach(elem => {
                    select.append('<option value="' + elem.id + '">' + elem.text + '</option>');
                });
            }
            var timetable = new Timetable();
            timetable.setScope(0, 23)
            timetable.addLocations(data.map(elem => elem.text));
            getTimes(timetable);
        }
    });
}

function getTimes(timetable) {
    var stringDate = formatDate($('#datepicker').datepicker('getDate'));
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
    //window.alert('You clicked on the ' + event.name + ' event in ' + event.location + '. This is an example of a click handler');
    $('#EditModal').find('#eventText').val(event.name);
    $('#EditModal').find('#update-event-btn').click(event, updateEvent);
    $('#EditModal').find('#delete-event-btn').click(event, deleteEvent);
    $('#EditModal').modal('show');
}

function updateEvent(thatevent) {
    var event = {};
    event['text'] = $('#EditModal').find('#eventText').val();
    event['startDateTimeMillis'] = thatevent.data.startDate.getTime();
    event['endDateTimeMillis'] = thatevent.data.endDate.getTime();
    event['nameSpace'] = thatevent.data.location;
    $.ajax({
        type: "PUT",
        contentType: "application/json",
        url: "/api/update-event",
        data: JSON.stringify(event),
        dataType: 'json',
        cache: false,
        timeout: 600000,
        success: function (data) {
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

function deleteEvent(thatevent) {
    var event = {};
    //event['text'] = $('#EditModal').find('#eventText').val();
    event['startDateTimeMillis'] = thatevent.data.startDate.getTime();
    event['endDateTimeMillis'] = thatevent.data.endDate.getTime();
    event['nameSpace'] = thatevent.data.location;
    $.ajax({
        type: "DELETE",
        contentType: "application/json",
        url: "/api/delete-event",
        data: JSON.stringify(event),
        dataType: 'json',
        cache: false,
        timeout: 600000,
        success: function (data) {
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

//UNUSED - ONLY FOR TESTING PURPOSE
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

