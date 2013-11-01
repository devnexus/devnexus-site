<%@ include file="/WEB-INF/jsp/includes/taglibs.jsp"%>
<% pageContext.setAttribute("lf", "\n"); %>
<div id="devnex" class="jumbotron">
	<div class="container">
		<div id="banner">
			<h1 id="gray">DevNexus 2014</h1>
			<h1 id="white">Social</h1>
		</div>
	</div>
</div>
<div class="container masonry">
		<c:forEach items="${tweets}" var="tweet">
		<div class="col-md-2 tweet-box">
			<div class="panel panel-default">
				<div class="panel-body">
				<img alt="${tweet.fromUser}" title="${tweet.fromUser}" src="${tweet.profileImageUrl}" class="img-thumbnail"/>
				<c:out value="${tweet.text}" />
				<p><small><c:out value="${tweet.prettyTime}" /></small></p>
				</div>
			</div>
		</div>
		</c:forEach>
</div>

	<content tag='bottom'>
		<script type="text/javascript">
			$(document).ready(function() {

				$(document).ready(function () {
				    var $container = $('.masonry');

				    $container.imagesLoaded(function () {
				        $container.masonry({
				            itemSelector: '.tweet-box',
				            columnWidth: '.tweet-box',
				            transitionDuration: 0
				        });
				    });
				});

/* 				var $container = $('.tweets');
				// initialize
				$container.masonry({
				  itemSelector: '.tweet'
				}); */

/*
                 // or with jQuery
                var $container = $('.tweets');
                var msnry;
                // initialize Masonry after all images have loaded
                $container.imagesLoaded( function() {
                    msnry = $container.masonry({
                        columnWidth: '.tweet',
                        'margin-bottom': '10px',
                        itemSelector: '.tweets',
                        isResizable: true
                } );
                });

                $( window).resize(function() {
                  window.setTimeout(function(){
                      $container.masonry({
                          columnWidth: '.tweet',
                          'margin-bottom': '10px',
                          itemSelector: '.tweets',
                          isResizable: true
                      } );
                  }, 1000)
                }); */
			});
		</script>
	</content>