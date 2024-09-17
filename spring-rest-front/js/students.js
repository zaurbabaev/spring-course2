function loadStudents() {


    var http = new XMLHttpRequest();

    http.onload = function () {
        var code = this.status;
        if (code == 200) {
            var table = document.getElementById('students');

            let responeJSON = this.responseText;

            let array = JSON.parse(responeJSON);

            var html = '';
            array.forEach(student => {
                html += '<tr>';

                html += '<td>' + student.id + '</td>';
                html += '<td>' + student.name + '</td>';
                html += '<td>' + student.surname + '</td>';
                html += '<td>' + student.phoneNumber + '</td>';
                html += '<td>' + student.email + '</td>';
                html += '<td>' + student.birthday + '</td>';

                html+="<td> <button onclick='onDelete("+student.id+")'>Sil</button></td>";
                html+="<td> <button onclick='onUpdate("+student+")'>Redaktə</button></td>";
                html += '</tr>';

            });
            table.innerHTML = html;

        } else if (code == 401) {
            alert('Login olun!');
        }
    };

    http.onerror = function () {
        alert('Serever is failed');
    };

    http.open('GET', 'http://localhost:8080/students', true);
    http.setRequestHeader('Authorization', localStorage.getItem('token'));
    http.send();

}

loadStudents();
function onDelete(id){
    var http = new XMLHttpRequest();

    http.onload = function () {
        var code = this.status;
        if (code == 200) {
            loadStudents();

        } else if (code == 401) {
            alert('Login olun!');
        }
    };

    http.onerror = function () {
        alert('Serever is failed');
    };

    http.open('DELETE', 'http://localhost:8080/students/'+id, true);
    http.setRequestHeader('Authorization', localStorage.getItem('token'));
    http.send();
}