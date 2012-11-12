
// usage: log('inside coolFunc', this, arguments);
// paulirish.com/2009/log-a-lightweight-wrapper-for-consolelog/
window.log = function(){
  log.history = log.history || [];   // store logs to an array for reference
  log.history.push(arguments);
  if(this.console) {
    arguments.callee = arguments.callee.caller;
    var newarr = [].slice.call(arguments);
    (typeof console.log === 'object' ? log.apply.call(console.log, console, newarr) : console.log.apply(console, newarr));
  }
};

// make it safe to use console.log always
(function(b){function c(){}for(var d="assert,clear,count,debug,dir,dirxml,error,exception,firebug,group,groupCollapsed,groupEnd,info,log,memoryProfile,memoryProfileEnd,profile,profileEnd,table,time,timeEnd,timeStamp,trace,warn".split(","),a;a=d.pop();){b[a]=b[a]||c}})((function(){try
{console.log();return window.console;}catch(err){return window.console={};}})());


// place any jQuery/helper plugins in here, instead of separate, slower script files.

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

  if (Modernizr.applicationcache){

    // We have offline web app support
      if (navigator.onLine) {

      } else {
        $.sticky('<b>You are offline.</b>');
      }

      window.addEventListener("offline", function(e) {
        $.sticky('<b>You are offline.</b>');
      }, false);

      window.addEventListener("online", function(e) {
        $.sticky('<b>You are back online.</b>');
      }, false);

      window.applicationCache.addEventListener('updateready', function(e) {
        if (window.applicationCache.status == window.applicationCache.UPDATEREADY) {
          window.applicationCache.swapCache();

          $( "#dialog-confirm" ).dialog({
          resizable: false,
          height:140,
          modal: true,
          buttons: {
            "Reload": function() {
              $( this ).dialog( "close" );
              window.location.reload();
            },
            Cancel: function() {
              $( this ).dialog( "close" );
            }
          }
        });

        }
      }, false);

      window.applicationCache.addEventListener("error", function(e) {
        $.sticky("<b>Error fetching manifest: a good chance we are offlinedddd</b>");
        alert(e);
      });

  }

});
