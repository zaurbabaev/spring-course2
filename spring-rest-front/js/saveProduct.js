function onSignup(event) {
    event.preventDefault();

    var name = document.getElementById('name').value;
    var price = document.getElementById('price').value;
    var description = document.getElementById('description').value;
    var quantity = document.getElementById('quantity').value;



    var productsObj = { name : name, price : price, description : description, quantity : quantity};

    console.log(productsObj);
    // AJAX

    var http = new XMLHttpRequest();
    http.onload = function () {
        alert('ugurlu')
    };

    http.onerror = function () {
        alert('fail')
    };

    http.open('POST', 'http://localhost:8080/products', true);
    http.setRequestHeader('Content-type', 'application/json');
    http.send(JSON.stringify(productsObj));


}