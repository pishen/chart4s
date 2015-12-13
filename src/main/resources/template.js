var socket = null

function updateSocket() {
    socket = new WebSocket("ws://" + window.location.host + "/socket")
    socket.onopen = function(){
        console.log("websocket connected.")
    }
    socket.onmessage = function(event){
        var json = JSON.parse(event.data)
        console.log(json)
        if (json.msgType == ""){
            //TODO
        }
    }
    /* socket.onclose = function(){
       console.log("websocket closed.")
       setTimeout(updateSocket, 3000)
       } */
}

updateSocket()
