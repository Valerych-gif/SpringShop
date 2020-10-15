var stompClient = null;

window.onload = connect();

function connect() {
    var socket = new SockJS('/springshop/hello');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function(frame) {
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/items', function(message){
        console.log(message.body);
            showGreeting(JSON.parse(message.body).count);
        });
    });
}

function update() {
    stompClient.send("/app/hello", {}, JSON.stringify({}));
}

function showGreeting(message) {
    console.log(message);
    document.getElementById("ProductQuantity").value="Товаров в корзине: " + message;
}