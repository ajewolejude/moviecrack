$(document).ready(function() {
   //alert("hiiiii");
   $('#loginForm').submit(function (event) {
       // alert("hi");
event.preventDefault();
        var loginRequest = {}
        loginRequest["username"] = $("#username").val();
        loginRequest["password"] = $("#password").val();

        var sjson = JSON.stringify(loginRequest);
        alert(sjson);


        $.ajax({
        type:'post',
       contentType : 'application/json',
       url : "/api/users/login",
       data : JSON.stringify(loginRequest),
       dataType : 'json',
       cache : false,
       timeout : 600000,
       success : function(data) {
       alert(JSON.stringify(data));
       sessionStorage.setItem("token", data.token);
       sessionStorage.setItem("user_id", data.id);
       location.href = "http://localhost:8080/movie/all";

       },
       error: function(jqXHR, exception) {
            if (jqXHR.status === 0) {
                alert('Not connect.\n Verify Network.');
            } else if (jqXHR.status == 404) {
                alert('Requested page not found. [404]');
            } else if (jqXHR.status == 500) {
                alert('Internal Server Error [500].');
            } else if (exception === 'parsererror') {
                alert('Requested JSON parse failed.');
            } else if (exception === 'timeout') {
                alert('Time out error.');
            } else if (exception === 'abort') {
                alert('Ajax request aborted.');
            } else {
                alert('Uncaught Error.\n' + jqXHR.responseText);
                alert('check login, try again');


            }
        }
      });




   });
});