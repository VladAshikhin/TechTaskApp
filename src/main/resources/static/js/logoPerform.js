$("#logo-fill-data").on("click", function () {
    fillData();
})

$("#logo-perform-pdf").on("click", function () {
    let data = getLogoData()
    let json = JSON.stringify(data);

    sendJson(json);
});

function fillData() {
    $("#company-name").val('Компания');
    $("#web-site").val('company.com');
    $("#business-details").val('Печать широкоформатных баннеров');
    $("#strong-points").val('Большая клиентская база по всему СНГ');
    $("#opponents").val('company-analogue.com; small-company.com');
    $("#logo_rus").val('Компания-лого');
    $("#logo_eng").val('Company-logo');
    $("#logo_exists").val('Есть прототип, прошу использовать при создании логотипа');
    $("#minimalism").attr('checked', true)
    $("#font-writing").attr('checked', true)
    $("#goth").attr('checked', true);
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

function getLogoData() {
    let type = "logo"

    let name = $("#company-name").val();
    let web = $("#web-site").val();
    let details = $("#business-details").val();
    let strongPoints = $("#strong-points").val();
    let opponents = $("#opponents").val();
    let logoRus = $("#logo_rus").val();
    let logoEng = $("#logo_eng").val();
    let logoExists = $("#logo_exists").val();

    let styleMinimalism = getCheckBoxState('minimalism')
    let styleFont = getCheckBoxState('font-writing')
    let styleElegant = getCheckBoxState('elegant')
    let styleEmblem = getCheckBoxState('emblem')
    let styleCubism = getCheckBoxState('cubism')
    let styleRetro = getCheckBoxState('retro')
    let styleOther = getCheckBoxState('style_other')

    let fontPreference = parseInt(getRadioButtonValueByName('font-pref'));
    let fontPreferenceOther = $("#font_other").val()

    return {
        "type": type,
        "data":
            {
                "name": name,
                "web": web,
                "details": details,
                "strongPoints": strongPoints,
                "opponents": opponents,
                "logoRus": logoRus,
                "logoEng": logoEng,
                "logoExists": logoExists,
                "styleMinimalism": styleMinimalism,
                "styleFont": styleFont,
                "styleElegant": styleElegant,
                "styleEmblem": styleEmblem,
                "styleCubism": styleCubism,
                "styleRetro": styleRetro,
                "styleOther": styleOther,
                "fontPreference": fontPreference,
                "fontPreferenceOther": fontPreferenceOther
            }
    }
}

function getCheckBoxState(id) {
    return ($('#' + id + ':checkbox:checked').length > 0) ? 1 : 0
}

function getRadioButtonValueByName(elementName) {
    var radios = document.getElementsByName(elementName);

    for (var i = 0, length = radios.length; i < length; i++) {
        if (radios[i].checked) {
            return radios[i].value;
        }
    }
}