$(document).ready(function() {

$('#regForm').submit(function (event) {
$("#reg-submit").attr("disabled", true);
$("#response").children().remove();
event.preventDefault();
        var user = {}
        user["username"] = $("#username").val();
        user["firstname"] = $("#firstname").val();
        user["lastname"] = $("#lastname").val();
        user["password"] = $("#password").val();
        user["confirmPassword"] = $("#confirmPassword").val();

        var sjson = JSON.stringify(user);


        //register request

        $.ajax({
        type:'post',
       contentType : 'application/json',
       url : "/api/users/register",
       data : JSON.stringify(user),
       dataType : 'json',
       cache : false,
       timeout : 600000,
       success : function(data) {
        $("#reg-submit").attr("disabled", false);
        $("#response").prepend('<div class="alert alert-info">You have successfully registered to our awesome app!</div>');
        $("#regForm").reset();

       },
       error: function(jqXHR, exception) {
       $("#reg-submit").attr("disabled", false);
            if (jqXHR.status === 0) {
                alert('Not connect.\n Verify Network.');
            } else if (jqXHR.status == 404) {
                alert('Requested page not found. [404]');
            } else if (jqXHR.status == 500) {
                $("#response").prepend('<div class="alert alert-danger">Username Already Taken!</div>');
            } else if (exception === 'parsererror') {
                alert('Requested JSON parse failed.');
            } else if (exception === 'timeout') {
                alert('Time out error.');
            } else if (exception === 'abort') {
                alert('Ajax request aborted.');
            } else {
            var pattern = /Passwords must match/;


             if(pattern.test(jqXHR.responseText)){
                $("#response").prepend('<div class="alert alert-danger">Passwords must match</div>');
                }else{

              $("#response").prepend('<div class="alert alert-danger">' + jqXHR.responseText +'</div>');
               }

            }

        }
      });



   });
});
