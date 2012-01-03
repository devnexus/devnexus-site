    $(document).ready(function() {

      function addMega(){
        $(this).addClass("hovering");
        }

      function removeMega(){
        $(this).removeClass("hovering");
        }

    var megaConfig = {
         interval: 300,
         sensitivity: 4,
         over: addMega,
         timeout: 300,
         out: removeMega
    };

    $("li.mega").hoverIntent(megaConfig)


    });
