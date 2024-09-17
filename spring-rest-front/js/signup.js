function onSignup(event){
    event.preventDefault();

    var username= document.getElementById('username').value;
    var password= document.getElementById('password').value;

   

    var userObj={username:username, password:password};

    console.log(userObj);

    // AJAX

    var http =new XMLHttpRequest();
  
        http.onload = function () {
            var code = this.status;
            if (code == 400) {
                alert('məlumatların tamlığı pozulur');
            } else if (code == 201) {
                alert('user yaradıldı');
            }else if(code==409){
                alert(this.responseText);  
            }
    };

    http.onerror= function(){
        alert('fail');
    };
    
    http.open('POST','http://localhost:8080/users',true);
    http.setRequestHeader('Content-type','application/json');
    http.send(JSON.stringify(userObj));


}

