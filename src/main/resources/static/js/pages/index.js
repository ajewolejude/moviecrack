$(document).ready(function() {
   $("#movieList").hide();


      var tok = sessionStorage.getItem("token");
      var user_id = sessionStorage.getItem("user_id");
      console.log(tok);
      console.log(user_id);
      if (tok == null){
      location.href ="http://localhost:8080/login";

      }else{


                $.ajax({
                type:'get',
               url : "/api/movies/all",
               dataType : 'json',
               cache : false,
               timeout : 600000,
               beforeSend: function(xhr){
                            xhr.setRequestHeader('Authorization', tok);
                            $("#loader").show();

               },
               success : function(data) {
                $("#loader").hide();
                $("#movieList").show();
                $.each(data, function(i, movie){

                     var html = '<tr>' ;
                     html += '<td>' + movie.id + '</td>';

                      html += '<td>' + movie.title + '</td>';
                      html += '<td>' + movie.overview + '</td>';
                      html += '<td>' + movie.runtime + '</td>';
                      html += '<td>' + movie.voteAverage + '</td>';
                      html += '<td>' + movie.status + '</td>';
                      html += '<td><a class=\"btn btn-primary btn-square\" href = \"/movie/' + movie.id + '/view">View</a></td>';
                      html += '</tr>';
                     $('#result').prepend(html);

                    $.each(movie.vidoes, function(i, video){
                    console.log(video.site);
                    console.log(video.name);
                    });






                });
                $('#favTable').dataTable();
               },
               error: function(jqXHR, exception) {
               sessionStorage.clear();
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
                    location.href ="http://localhost:8080/login";
                }
              });

      }


    //logout implementation
    $("#logout").click(function(){
     sessionStorage.clear();
    location.href ="http://localhost:8080/login";


      });

});

