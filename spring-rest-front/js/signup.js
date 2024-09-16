function onSignup(event){
    event.preventDefault();

    var username= document.getElementById('username').value;
    var password= document.getElementById('password').value;

   

    var userObj={username:username, password:password};

    console.log(userObj);

    // AJAX

    var http =new XMLHttpRequest();
    http.onload=function(){
        alert('ugurlu')
    };

    http.onerror= function(){
        alert('fail')
    };
    
    http.open('POST','http://localhost:8080/users',true);
    http.setRequestHeader('Content-type','application/json');
    http.send(JSON.stringify(userObj));


}