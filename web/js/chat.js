let getStateRequest = new XMLHttpRequest();
let setStateRequest = new XMLHttpRequest();
let getFriendsRequest = new XMLHttpRequest();
let addFriendRequest = new XMLHttpRequest();

let chatSessions = [];

function ready() {
    document.getElementById("statebutton").onclick = setState;
    document.getElementById("friendbutton").onclick = addFriend;

    getState();
    getFriends();
}

function addFriend() {
    let newFriend = document.getElementById("friendText").value;
    addFriendRequest.open("POST", "Controller", true);
    addFriendRequest.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
    let information = "action=addFriend&friend=" + encodeURI(newFriend);
    addFriendRequest.send(information);
    getFriends();
}

function getFriends() {
    getFriendsRequest.open("GET", "Controller?action=getFriends", true);
    getFriendsRequest.onreadystatechange = getFriendsData;
    getFriendsRequest.send(null);
}

function getFriendsData() {
    if (getFriendsRequest.readyState == 4) {
        if (getFriendsRequest.status == 200) {
            let json = JSON.parse(getFriendsRequest.responseText);

            let friendlist = document.getElementById("friendlist");

            if (friendlist.childNodes.length > 0) {
                while (friendlist.childNodes[0] != null) {
                    friendlist.removeChild(friendlist.childNodes[0]);
                }
            }

            for (let i = 0; i < json.friends.length; i++) {
                let tablerow = document.createElement('tr');
                let name = json.friends[i].name;
                let nametext = document.createTextNode(name);
                let tdname = document.createElement('td');
                tdname.id = "friend_" + i;
                tdname.appendChild(nametext);
                tdname.addEventListener("click", function () {
                    openBox(name);
                });
                let statustext = document.createTextNode(json.friends[i].state);
                let tdstatus = document.createElement('td');
                tdstatus.appendChild(statustext);
                tablerow.appendChild(tdname);
                tablerow.appendChild(tdstatus);
                friendlist.appendChild(tablerow);
            }
        }
        setTimeout("getFriends()", 10000);
    }
}


function setState() {
    let newState = document.getElementById("stateText").value;
    console.log(newState);
    setStateRequest.open("POST", "Controller", true);
    setStateRequest.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
    let information = "action=setState&state=" + encodeURI(newState);
    setStateRequest.send(information);
    getState();
}

function getState() {
    getStateRequest.open("GET", "Controller?action=getState", true);
    getStateRequest.onreadystatechange = getStateData;
    getStateRequest.send(null);
}

function getStateData() {
    if (getStateRequest.readyState == 4) {
        if (getStateRequest.status == 200) {
            let serverResponse = JSON.parse(getStateRequest.responseText);
            let stateXML = serverResponse.state;

            let stateDiv = document.getElementById("stateDiv");
            let stateParagraph = stateDiv.childNodes[0];

            if (stateParagraph == null) {
                stateParagraph = document.createElement('p');
                stateParagraph.id = "currentState";
                let stateText = document.createTextNode(stateXML);
                stateParagraph.appendChild(stateText);
                stateDiv.appendChild(stateParagraph);
            } else {
                let stateText = document.createTextNode(stateXML);
                stateParagraph.removeChild(stateParagraph.childNodes[0]);
                stateParagraph.appendChild(stateText);
            }
        }
    }
}

function openBox(friendName) {
    if (!chatSessions.includes(friendName)) {
        chatSessions.push(friendName);
        startConversation(friendName);
        let messages = document.getElementById("messages");
        let messageBox = document.createElement("div");
        messageBox.className = "messageBox";
        messageBox.id = friendName + "messageBox";
        let name = document.createElement("p");
        name.className = "name";
        name.id = friendName + "name";
        name.textContent = friendName;
        name.addEventListener("click", function () {
            console.log('hahahaha');
            closeBox(friendName);
        });
        let content = document.createElement("div");
        content.className = "content";
        content.id = friendName + "content";
        let message = document.createElement("div");
        message.className = "message";
        message.id = friendName + "message";
        let messageText = document.createElement("input");
        messageText.className = "messageText";
        messageText.id = friendName + "messageText";
        messageText.type = 'text';
        messageText.placeholder = 'Type a message';
        messageText.addEventListener('keyup', function (e) {
            if (e.keyCode === 13) {
                addToConversation(friendName);
            }
        });
        let sendButton = document.createElement("button");
        sendButton.id = friendName + "button";
        sendButton.className = "sendButton";
        sendButton.textContent = "send";
        sendButton.addEventListener("click", function () {
            addToConversation(friendName);
        });
        message.appendChild(messageText);
        message.appendChild(sendButton);
        messageBox.append(name);
        messageBox.append(content);
        messageBox.append(message);
        messages.append(messageBox);
    }else{
        let id = friendName + "messageBox";
        $('#' + id).toggle("fold");
    }
}


function startConversation(friendName) {
    $.ajax({
        type: "GET",
        url: "Controller",
        data: {
            'action': 'startConv',
            'friend': friendName
        },
        success: function (json) {
            let id = friendName + "content";
            let contentBox = $('#' + id);
            contentBox.empty();
            for (let i = 0; i < json.content.length; i++) {
                let user = json.content[i].split(":")[0];
                let message = json.content[i].split(":")[1].replace(/%20/g, " ");
                let classs = "\"self\"";
                if (user === friendName) {
                    classs = "\"friend\"";
                }
                contentBox.append("<p class=" + classs + ">" + message + "</p>");
            }
        },
        error: function () {
            alert("Something went wrong...");
        }
    });
    setTimeout(startConversation, 50000, friendName);
}

function addToConversation(friendName) {
    let id = friendName + "messageText";
    let textBox = $('#' + id + ':text');
    let message = textBox.val();
    if (message !== "") {
        textBox.val('');
        $.ajax({
            type: "POST",
            url: "Controller",
            data: {
                'action': 'addToConv',
                'friend': friendName,
                'message': encodeURI(message)
            }
        });
        startConversation(friendName);
    }
}

function closeBox(friendName) {
    let id = friendName + "messageBox";
    console.log('toggled ' + id);
    $('#' + id).toggle("blind");
}
