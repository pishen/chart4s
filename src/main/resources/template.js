var socket = null

function updateSocket() {
    socket = new WebSocket("ws://" + window.location.host + "/socket")
    socket.onopen = function() {
        console.log("websocket connected.")
    }
    socket.onmessage = function(event) {
        var json = JSON.parse(event.data)
        
        $("<div></div>").addClass("chart").prependTo("#charts")
        
        if (json.msgType == "draw") {
            c3.generate({
                bindto: ".chart:first-child",
                data: json.data
            })
        } else if (json.msgType == "") {
            //TODO
        }
    }
    socket.onclose = function() {
        console.log("websocket closed.")
    }
}

updateSocket()
