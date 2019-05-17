let webSocket;
let name;
let message;
let score;


function openSocket() {
    webSocket = new WebSocket("ws://localhost:8080/echo");

    webSocket.onopen = function (event) {
    };

    webSocket.onmessage = function (event) {
        writeResponse(event.data);
    };

    webSocket.onclose = function (event) {
    };
}

function send1() {
    name = document.getElementById('nameinput1').value;
    message = document.getElementById('messageinput1').value;
    score = document.getElementById('scoreinput1').value;
    let text = "1_" + name + "_" + message + "_" + score;
    webSocket.send(text);
}

function send2() {
    name = document.getElementById('nameinput2').value;
    message = document.getElementById('messageinput2').value;
    score = document.getElementById('scoreinput2').value;
    let text = "2_" + name + "_" + message + "_" + score;
    webSocket.send(text);
}

function send3() {
    name = document.getElementById('nameinput3').value;
    message = document.getElementById('messageinput3').value;
    score = document.getElementById('scoreinput3').value;
    let text = "3_" + name + "_" + message + "_" + score;
    webSocket.send(text);
}

function send4() {
    name = document.getElementById('nameinput4').value;
    message = document.getElementById('messageinput4').value;
    score = document.getElementById('scoreinput4').value;
    let text = "4_" + name + "_" + message + "_" + score;
    webSocket.send(text);
}

function send5() {
    name = document.getElementById('nameinput5').value;
    message = document.getElementById('messageinput5').value;
    score = document.getElementById('scoreinput5').value;
    let text = "5_" + name + "_" + message + "_" + score;
    webSocket.send(text);
}

function closeSocket() {
    webSocket.close();
}

function writeResponse(text) {
    let textArray = text.split("_");
    let messages = document.getElementById('messages' + textArray[0]);
    let out = textArray[1] + ": " + textArray[2] + " SCORE: " + textArray[3];
    messages.innerHTML += "<p>" + out + "</p>";
}