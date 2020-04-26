

$(document).ready(function() {
 $("#fav").hide();
 $("#unfav").hide();
 //get user id and bearer token from session
 var user_id = sessionStorage.getItem("user_id");
 var tok = sessionStorage.getItem("token");
 //get movie id from url
    var sPageURL = window.location.pathname;
    var id = 0
    var sURLVariables = sPageURL.split('/');

    for (var i = 0; i < sURLVariables.length; i++)
    {
         id = sURLVariables[2];
    }

    var singleRequest = {}
    singleRequest["id"] = id;


        var sjson = JSON.stringify(singleRequest);

        //send request to get movie details
        $.ajax({

        type:'post',
       url : "/api/movies/view",
       contentType : 'application/json',
       data : sjson,
       dataType : 'json',
       cache : false,
       timeout : 600000,
       beforeSend: function(xhr){
         $("#loader").show();
         $("#page").hide();

        },

       success : function(data) {
        $("#loader").hide();
         $("#page").show();
            console.log(data.id);
            console.log(data.overview);
            console.log(data.voteAverage);
            console.log(data.releaseDate);
            console.log(data.genres);
            console.log(data.vidoes);
            $("#title").text(data.title);
            $("#description").text(data.title);
            $("#overview").text(data.overview);
             var htmlgenre = 'Genre:';
                        $.each(data.genres, function(i, genre){

                                    htmlgenre += '<span>';
                                     htmlgenre += genre.name +' </span>';
                                    $('#genre').prepend(htmlgenre);
                        });

             var htmlhome = 'Home Page : ';
             htmlhome += '                <a class="btn btn-success" href=\"'+ data.homepage+'\">';
             htmlhome += '                    Click here';
             htmlhome += '                </a>';
             $('#homepage').prepend(htmlhome);

             $(".poster").css("background-image", "url(" + data.posterPath + ")");
             var html = '<center> <div class=\"movie-card\">';
            html += '        <div style=\"background: url(' +data.backdropPath+'); background-position: 100% 80%; background-repeat: no-repeat; background-size: cover;  \">';
            html += '            <div class=\"header-icon-container\">';
            html += '                <a href=\"#\">';
            html += '                    <i class=\"material-icons header-icon\"></i>';
            html += '                </a>';
            html += '            </div>';
            html += '        </div><!--movie-header-->';
            html += '        <div class=\"movie-content\">';
            html += '            <div class=\"movie-content-header\">';
            html += '                <a href=\"#\">';
            html += '                    <h3 class=\"movie-title\">' +data.title+'</h3>';
            html += '                </a>';
            html += '                <div ></div>';
            html += '            </div>';
            html += '            <div class=\"movie-info\">';
            html += '                <div class=\"info-section\">';
            html += '                    <label>Date & Time</label>';
            html += '                    <span>'+data.releaseDate+'</span>';
            html += '                </div><!--date,time-->';
            html += '                <div class=\"info-section\">';
            html += '                    <label>Runtime</label>';
            html += '                    <span>'+ data.runtime+'</span>';
            html += '                </div><!--screen-->';
            html += '                <div class=\"info-section\">';
            html += '                    <label>Budget</label>';
            html += '                    <span>'+data.budget+'</span>';
            html += '                </div><!--row-->';
            html += '                <div class=\"info-section\">';
            html += '                    <label>Rating</label>';
            html += '                    <span>'+data.voteAverage+'</span>';
            html += '                </div><!--seat-->';
            html += '            </div>';
            html += '        </div><!--movie-content-->';
            html += '    </div><!--movie-card--> <center> ';

             $('#result').prepend(html);
               //loop through videos model
            $.each(data.vidoes, function(i, video){

            var html = '<center><iframe width=\"70%\" height=\"345\" src=\"https://www.youtube.com/embed/';
            console.log(video.site);
            console.log(video.name);
             html += video.key +'\"></iframe></center></br>';
            $('#result-video').prepend(html);
            });


                    //request to get if current movie is users's favorite
                    $.ajax({
                    type:'get',
                   contentType : 'application/json',
                   url : "/api/fav/"+ user_id + "/" + id+"/",
                   dataType : 'json',
                   cache : false,
                   timeout : 600000,
                   beforeSend: function(xhr){xhr.setRequestHeader('Authorization', tok);},
                   success : function(data) {
  //if true hide add favorite and show remove favorite else otherwise
                       if(data ==true){

                            $("#fav").hide();
                            $("#unfav").show();

                       }else if(data == false){
                            $("#fav").show();
                            $("#unfav").hide();
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
                            alert('Uncaught Error hi.\n' + jqXHR.responseText);

                        }
                        location.href = "http://localhost:8080/login";
                   }
                });



       },
       error: function(jqXHR, exception) {
            if (jqXHR.status === 0) {
                alert('Not connect.\n Verify Network.');
            } else if (jqXHR.status == 404) {
                alert('Requested page not found. [404]');
            } else if (jqXHR.status == 500) {
                alert('Not connected.\n You need internet connection.');
            } else if (exception === 'parsererror') {
                alert('Requested JSON parse failed.');
            } else if (exception === 'timeout') {
                alert('Time out error.');
            } else if (exception === 'abort') {
                alert('Ajax request aborted.');
            } else {
                alert('Uncaught Error.\n' + jqXHR.responseText);
                location.href = "http://localhost:8080/login";
            }
            location.href = "http://localhost:8080/login";
        }
      });

//add to favorite
 $("#fav").click(function(){
 $("#response").children().remove();

 var user_id = sessionStorage.getItem("user_id");
 var tok = sessionStorage.getItem("token");
      console.log(user_id);


    var favorites = {}
        favorites["movie_id"] = id;
        favorites["user_id"] = user_id;

        var sjson = JSON.stringify(favorites);

    $.ajax({
        type:'post',
       contentType : 'application/json',
       url : "/api/fav/add",
       data : JSON.stringify(favorites),
       dataType : 'json',
       cache : false,
       timeout : 600000,
       beforeSend: function(xhr){xhr.setRequestHeader('Authorization', tok);},
       success : function(data) {
       $("#response").prepend('<div class="alert alert-success alert-dismissable">You have added this movie to favorite list!</div>');
               $("#fav").hide();
               $("#unfav").show();

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
                location.href = "http://localhost:8080/login";
            }
       }
    });

  });


//remove from favorite
    $("#unfav").click(function(){
    $("#response").children().remove();
 var user_id = sessionStorage.getItem("user_id");
 var tok = sessionStorage.getItem("token");
      console.log(user_id);

    $.ajax({
        type:'get',
       contentType : 'application/json',
       url : "/api/fav/"+ user_id + "/" + id+"/delete",
       dataType : 'json',
       cache : false,
       timeout : 600000,
       beforeSend: function(xhr){xhr.setRequestHeader('Authorization', tok);},
       success : function(data) {

       $("#response").prepend('<div class="alert alert-success alert-dismissable">You have removed this movie from favorite list!</div>');
        $("#fav").show();
        $("#unfav").hide();

       },
       error: function(jqXHR, exception) {

            if (jqXHR.status === 0) {
                alert('Not connect.\n Verify Network.');
            } else if (jqXHR.status == 404) {
                alert('Requested page not found. [404]');
            } else if (jqXHR.status == 500) {

            } else if (exception === 'parsererror') {
                alert('Requested JSON parse failed.');
            } else if (exception === 'timeout') {
                alert('Time out error.');
            } else if (exception === 'abort') {
                alert('Ajax request aborted.');
            } else {
                alert('Uncaught Error.\n' + jqXHR.responseText);
                location.href = "http://localhost:8080/login";
            }
            location.href = "http://localhost:8080/login";
       }
    });

  });
});


