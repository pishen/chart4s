var socket = null
var secondsToWait = 1

function updateSocket() {
    socket = new WebSocket("ws://" + window.location.host + "/socket")
    socket.onopen = function() {
        console.log("websocket connected.")
        secondsToWait = 1
    }
    socket.onmessage = function(event) {
        var json = JSON.parse(event.data)
        
        var ctx = document.getElementById("canvas").getContext("2d");
        
        if (json.chartType == "line") {
            new Chart(ctx).Line(json.data, json.options)
        } else if (json.msgType == "") {
            //TODO
        }
    }
    socket.onclose = function() {
        console.log("websocket closed, retry in " + secondsToWait + " seconds.")
        setTimeout(updateSocket, secondsToWait * 1000)
        secondsToWait *= 2
    }
}

updateSocket()
