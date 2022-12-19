window.addEventListener("DOMContentLoaded", function() {

document.querySelector("#wordCount").innerHTML = "0";

document.querySelector("#text").addEventListener("keyup", function(e) {

document.querySelector("#wordCount").innerHTML = e.target.value.length;
})


})