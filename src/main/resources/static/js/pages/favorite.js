

$(document).ready(function() {
 //get user id and bearer token from session
 var user_id = sessionStorage.getItem("user_id");
 var tok = sessionStorage.getItem("token");
 $("#movieList").hide();
$("#emptyList").hide();



    var singleRequest = {}
    singleRequest["id"] = user_id;


        var sjson = JSON.stringify(singleRequest);

//request to get all favorites of users
        $.ajax({

        type:'post',
       url : "/api/movies/favorites",
       contentType : 'application/json',
       data : sjson,
       dataType : 'json',
       cache : false,
       timeout : 600000,
        beforeSend: function(xhr){
                            xhr.setRequestHeader('Authorization', tok);
                            $("#loader").show();

               },
               success : function(data) {
                if(data == 0){
                //list is empty, no favorite movie
                $("#loader").hide();
                  $("#emptyList").show();

                }else{
                //show list of movies
                                $("#loader").hide();
                                $("#movieList").show();
                                $("#emptyList").hide();
                                $.each(data, function(i, movie){

                                     var html = '<tr>' ;
                                     html += '<td>' + movie.id + '</td>';

                                      html += '<td>' + movie.title + '</td>';
                                      html += '<td>' + movie.overview + '</td>';
                                      html += '<td>' + movie.runtime + '</td>';
                                      html += '<td>' + movie.voteAverage + '</td>';
                                      html += '<td>' + movie.status + '</td>';
                                      html += '<td><a class=\"btn btn-primary btn-square\"  href = \"/movie/' + movie.id + '/view">View</a></td>';
                                      html += '</tr>';
                                     $('#result').prepend(html);

                                    $.each(movie.vidoes, function(i, video){
                                    console.log(video.site);
                                    console.log(video.name);
                                    });

                                });
                                $('#favTable').dataTable();
                }


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
                        location.href ="http://localhost:8080/login";
                    }
                    location.href ="http://localhost:8080/login";
                }
              });


});