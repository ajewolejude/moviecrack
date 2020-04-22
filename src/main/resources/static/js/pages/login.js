$(document).ready(function() {

   $('#loginForm').submit(function (event) {
   //disable button
   $("#login-submit").attr("disabled", true);

event.preventDefault();
        var loginRequest = {}
        loginRequest["username"] = $("#username").val();
        loginRequest["password"] = $("#password").val();

        var sjson = JSON.stringify(loginRequest);


        //login request
        $.ajax({
        type:'post',
       contentType : 'application/json',
       url : "/api/users/login",
       data : JSON.stringify(loginRequest),
       dataType : 'json',
       cache : false,
       timeout : 600000,
       success : function(data) {
       $("#login-submit").attr("disabled", false);
       sessionStorage.setItem("token", data.token);
       sessionStorage.setItem("user_id", data.id);
       location.href = "http://localhost:8080/movie/all";

       },
       error: function(jqXHR, exception) {
       $("#login-submit").attr("disabled", false);
            if (jqXHR.status === 0) {
                alert('Not connect.\n Verify Network.');
            } else if (jqXHR.status == 404) {
                alert('Requested page not found. [404]');
            } else if (jqXHR.status == 500) {
            $("#response").prepend('<div class="alert alert-danger">Incorrect Login details, try again</div>');
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