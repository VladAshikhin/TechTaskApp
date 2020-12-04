$("#fillData").on("click", function () {
    fillCorporateData();
})
$("#banner-fill-data").on("click", function () {
    fillBannerData();
})
$("#presentation-fill-data").on("click", function () {
    fillPresentationData();
})

$("#performPdf").on("click", function () {
    let data = getCorporateStyleData()
    let json = JSON.stringify(data);

    sendJson(json);
});
$("#banner-perform-pdf").on("click", function () {
    let data = getBannerData()
    let json = JSON.stringify(data);

    sendJson(json);
});
$("#presentation-perform-pdf").on("click", function () {
    let data = getPresentationData()
    let json = JSON.stringify(data);

    sendJson(json);
});

function fillCorporateData() {
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
    $("#exampleYes").prop('checked', true)
}

function fillBannerData() {
    $("#banner-name").val('Баннер-маннер');
    $("#banner-size").val('128x128');
    $("#banner-format").val('.ban');
}

function fillPresentationData() {
    $("#presentation-name").val('Презентация-канализация');
    $("#presentation-format").val('.ppt');
}

function sendJson(json) {

    $.ajax({
        url: "/pdf/create",
        type: "POST",
        data: json,
        contentType: "application/json",
        success: function (data) {

            var file = base64ToArrayBuffer(data)
            openFile(file)

        },
        error: function (e) {
            alert(e.message);
            console.log('Was trying to send data, but error occurred. ' + json);
            alert('Error sending data to server');
        }
    });
}

function base64ToArrayBuffer(base64) {
    var binaryString = window.atob(base64);
    var binaryLen = binaryString.length;
    var bytes = new Uint8Array(binaryLen);
    for (var i = 0; i < binaryLen; i++) {
        bytes[i] = binaryString.charCodeAt(i);
    }
    return bytes;
}

function openFile(byte) {
    var file = new Blob([byte], {type: "application/pdf"});

    var fileURL = URL.createObjectURL(file);
    window.open(fileURL);
}

function getCorporateStyleData() {
    let type = "corporateStyle"

    console.log("getJsonData!!!")

    let companyName = $("#company-name").val();
    let maketSize = $("#maket-size").val();
    let orientation = getRadioButtonValueByName('orientation');
    let infoTask = $("#info-task").val();
    let platform = $("#platform").val();
    let deadline = $("#deadline").val();
    let button = getRadioButtonValueByName('buttonrequired');
    let buttonText = $("#button-text").val();
    let primaryText = $("#slogan").val();
    let secondaryText = $("#description").val();
    let contacts = $("#contacts").val();
    let example = getRadioButtonValueByName('example')

    return {
        "type": type,
        "data":
            {
                "companyName": companyName,
                "maketSize": maketSize,
                "maketOrientation": orientation,
                "info": infoTask,
                "platform": platform,
                "deadline": deadline,
                "buttonRequired": button,
                "buttonText": buttonText,
                "primaryMaketText": primaryText,
                "secondaryMaketText": secondaryText,
                "contacts": contacts,
                "examplesReady": example
            }
    }
}

function getBannerData() {
    let type = "banner"

    let name = $("#banner-name").val();
    let size = $("#banner-size").val();
    let format = $("#banner-format").val();

    return {
        "type": type,
        "data":
            {
                "name": name,
                "size": size,
                "format": format
            }
    }
}

function getPresentationData() {
    let type = "presentation"

    let name = $("#presentation-name").val();
    let format = $("#presentation-format").val();

    return {
        "type": type,
        "data":
            {
                "name": name,
                "format": format
            }
    }
}

function getRadioButtonValueByName(elementName) {
    var radios = document.getElementsByName(elementName);

    for (var i = 0, length = radios.length; i < length; i++) {
        if (radios[i].checked) {
            return radios[i].value;
        }
    }
}

function setDeadline() {

    let now = new Date()

    let day = ("0" + now.getDate()).slice(-2)
    let month = ("0" + (now.getMonth() + 1)).slice(-2)

    return now.getFullYear() + "-" + (month) + "-" + (day)
}