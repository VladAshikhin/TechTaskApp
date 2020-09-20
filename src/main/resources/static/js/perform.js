$("#fillData").on("click", function(){
fillData();
})

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

function setDeadline() {

var now = new Date();

var day = ("0" + now.getDate()).slice(-2);
var month = ("0" + (now.getMonth() + 1)).slice(-2);

var today = now.getFullYear()+"-"+(month)+"-"+(day) ;

return today;
}

$("#performPdf").on("click", function(){

var myValues = $(".data").val()

var data = getJsonData()
var json = JSON.stringify(data);

sendJson(json);

});

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

var companyName = $(".company-name").val();
var maketSize = $(".maket-size").val();
var infoTask = $(".info-task").val();
var platform = $(".platform").val();
var buttonText = $(".button-text").val();
var slogan = $(".slogan").val();
var description = $(".description").val();
var contacts = $(".contacts").val();

var stringData = {"company": companyName,
                  "maket": maketSize,
                  "info": infoTask,
                  "platform": platform,
                  "buttonText": buttonText,
                  "slogan": slogan,
                  "description": description,
                  "contacts": contacts};

return stringData;
}