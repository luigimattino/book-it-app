function myFunction() {
    document.getElementById("demo").innerHTML = "This content has been change by javascript";
}
;

function settingHours() {
    var arr = [];
    for (i = 0; i < 24; i++) {
        arr.push("" + i + ":" + "00");
    }
    if ($(".tl-chrono").length) {
        console.log(".tl-chrono is present")
        for (i = arr.length - 1; i >= 0; i--) {
            var cell = $('<span/>', {
                "class": 'tl-chrono-cell'
            }).html(arr[i]);
            $('.tl-chrono').prepend($('<th/>').append(cell));
        }
    } else {
        console.log(".tl-chrono is NOT present")
    }
}

settingHours();
myFunction();