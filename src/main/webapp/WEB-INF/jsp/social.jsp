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
<div class="masonry">
		<c:forEach items="${tweets}" var="tweet">
		<div class="col-md-3 tweet-box">
			<div class="panel panel-default">
				<div class="panel-heading" style="padding: 1px;"
				><img style="margin-right: 4px;" alt="${tweet.fromUser}" title="${tweet.fromUser}" src="${tweet.profileImageUrl}"/><a href="http://www.twitter.com/"<c:out value="${tweet.fromUser}"/>"
				><c:out value="${tweet.fromUser}"/></a></div>
				<div class="panel-body"><c:out value="${tweet.html}" escapeXml="false"/></div>
				<div class="panel-footer"><small><c:out value="${tweet.prettyTime}" /></small></div>
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