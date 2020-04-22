$(document).ready(function() {
   alert("hiiiii");
$('#regForm').submit(function (event) {
        alert("hi");
event.preventDefault();
        var user = {}
        user["username"] = $("#username").val();
        user["firstname"] = $("#firstname").val();
        user["lastname"] = $("#lastname").val();
        user["password"] = $("#password").val();
        user["confirmPassword"] = $("#confirmPassword").val();

        var sjson = JSON.stringify(user);
        alert(sjson);

        $.ajax({
        type:'post',
       contentType : 'application/json',
       url : "/api/users/register",
       data : JSON.stringify(user),
       dataType : 'json',
       cache : false,
       timeout : 600000,
       success : function(data) {
        alert(data);
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
            }

        }
      });



   });
});
