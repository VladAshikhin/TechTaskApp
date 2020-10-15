$("#fillData").on("click", function () {
    fillData();
})

$("#performPdf").on("click", function () {

    console.log("PerformPdf Clicked.")

    let data = getJsonData()
    let json = JSON.stringify(data);

    sendJson(json);

});

function fillData() {
    console.log("test fillData")
    $("#company-name").val('Ulmart');
    $("#maket-size").val('200x400');
    $("#hor").attr('checked', true);
    $("#info-task").val("Inform clients about promo");
    $("#platform").val("Slider on company website");
    $("#deadline").val(setDeadline)
    $("#buttonrequired").attr('checked', true)
    $("#button-text").val("See more")
    $("#slogan").val("Be patient and get your reward!")
    $("#description").val("Get a car for free.")
    $("#contacts").val("+79997654321")
    $("#vector-logo").prop('checked', true)
    $("#brandbook").prop('checked', true)
    $("#haveExample").prop('checked', true)

}


function sendJson(json) {

    $.ajax({
        url: "/pdf",
        type: "POST",
        data: json,
        contentType: "application/json",
        success: function (data) {
            alert('Success');
        },
        error: function (e) {
            alert(e.message);
            console.log('Was trying to send data, but error occurred.' + json);
            alert('Error sending data to server');
        }
    });
}

function getJsonData() {

    console.log("getJsonData")

    let companyName = $("#company-name").val();
    let maketSize = $("#maket-size").val();
    let orientation = getOrientation();
    let infoTask = $("#info-task").val();
    let platform = $("#platform").val();
    let deadline = $("#deadline").val();
    let button = getButtonRequired();
    let buttonText = $("#button-text").val();
    let primaryText = $("#slogan").val();
    let secondaryText = $("#description").val();
    let contacts = $("#contacts").val();
    let example


    return {
        "company": companyName,
        "maketSize": maketSize,
        "maketOrientation": orientation,
        "info": infoTask,
        "platform": platform,
        "deadline": deadline,
        "button": button,
        "buttonText": buttonText,
        "primaryMaketText": primaryText,
        "secondaryMaketText": secondaryText,
        "contacts": contacts,
        "source": source
    }
}

function getOrientation() {
    var radios = document.getElementsByName('orientation');

    for (var i = 0, length = radios.length; i < length; i++) {
        if (radios[i].checked) {
            return radios[i].value;
        }
    }
}

function getButtonRequired() {
    let radios = document.getElementsByName('buttonrequired');

    for (var i = 0, length = radios.length; i < length; i++) {
        if (radios[i].checked) {
            return radios[i].value;
        }
    }
}

function getSource() {
    console.log("Enter getSource")
    let checks = document.getElementsByName('source');

    let values = [];

    for (var i = 0, length = checks.length; i < length; i++) {
        if (checks[i].checked) {
            values.push(checks[i].value);
        }
    }

    console.log("Values " + values)
}

function setDeadline() {

    let now = new Date()

    let day = ("0" + now.getDate()).slice(-2)
    let month = ("0" + (now.getMonth() + 1)).slice(-2)

    return now.getFullYear() + "-" + (month) + "-" + (day)
}