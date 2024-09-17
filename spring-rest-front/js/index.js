function onLogin(event) {
    event.preventDefault();

    var username = document.getElementById('username').value;
    var password = document.getElementById('password').value;
    let token='Basic ' + window.btoa(username + ":" + password);
    var http = new XMLHttpRequest();

    http.onload = function () {
        var code = this.status;
        if (code == 200) {
           localStorage.setItem('token',token);
           window.location.href='students.html';
        } else if (code == 401) {
            alert('Username və ya parol səhvdir');
        }
    };

    http.onerror = function () {
        alert('fail');
    };

    http.open('GET', 'http://localhost:8080/users/login', true);
    http.setRequestHeader('Authorization', token);
    http.send();


}

