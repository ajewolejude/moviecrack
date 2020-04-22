$(document).ready(function() {
    //logout implementation
    $("#logout").click(function(){
     sessionStorage.clear();
    location.href ="http://localhost:8080/login";
        
    
      });

});
