<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@page import="com.devnexus.ting.model.PresentationType"%>
<%@ include file="/WEB-INF/jsp/includes/taglibs.jsp" %>
<% pageContext.setAttribute("keynoteType", PresentationType.KEYNOTE); %>

<head>
	<title>${contextEvent.title} | Speakers</title>

	<style type="text/css">
		#speaker {
			opacity: 0.0;
		}
		.centered {
			text-align: center;
		}
		.centered > div {
			float: none;
			display: inline-block;
		}

		.speaker-div-inner {
			margin: 5px 20px;
			border-radius: 8px;
			-webkit-border-radius: 8px;
			-moz-border-radius: 8px;
			-o-border-radius: 8px;

			border-color: rgba(123,173,52,0);;
			border-style: solid;
			border-width: 2px;

			transition: all 0.2s;
			-webkit-transition: all 0.2s;
			-moz-transition: all 0.2s;
			-o-transition: all 0.2s;
			-ms-transition: all 0.2s;
		}

		.speaker-div-inner:HOVER, .speaker-div-inner:FOCUS {
			border-color: rgba(123,173,52,1);

			transition: all 0.2s;
			-webkit-transition: all 0.2s;
			-moz-transition: all 0.2s;
			-o-transition: all 0.2s;
			-ms-transition: all 0.2s;
		}
	</style>
</head>

	<!-- intro -->
	<section id="about" class="module parallax parallax-3">
		<div class="container header">
				<div class="row centered">
					<div class="col-md-10 col-md-offset-1">
						<div class="top-intro travel">
							<h4 class="section-white-title decorated"><span>Speakers</span></h4>
							<h5 class="intro-white-lead">Tap into the expertise of ${fn:length(speakerList.speakers)} thought-leaders.</h5>
						</div>
					</div>
				</div>
		</div>
	</section>
	<!-- /intro -->

	<section id="speaker" class="bg-light-gray" style="margin-top: 2em; ">
		<div class="col-lg-10 col-lg-offset-1">
		<div class="row centered"
			><c:forEach items="${speakerList.speakers}" var="speaker" varStatus="status"
			><div class="col-lg-3 col-md-3 col-sm-4 col-xs-6 speaker-div masonryitem"
					style="">
				<a href="${ctx}${baseSiteUrl}/speakers/${speaker.id}">
				<div class="speaker-div-inner">
					<div id="${speaker.firstName}_${speaker.lastName}"
						class="speaker-member text-center" style="margin-bottom: 5px; margin-top: 5px">
						<img src="${speaker.pictureSerialized}" class="img-responsive img-circle" alt="">
						<h4 class="text-center"><c:out value="${speaker.firstName}"/> <c:out value="${speaker.lastName}"/></h4>
				</div>
		</div></a></div></c:forEach></div>
		</div>
	</section>

<content tag='bottom'>
	<script type="text/javascript">

		$(document).ready(function() {
			var $container = $('#speaker');

			function resizeSpeakerDivs() {
				var speakerDivMaxHeight = 0;

				$('.speaker-div').each(function () {
					$(this).outerHeight('auto');
					var height = $(this).outerHeight();
					if (height > speakerDivMaxHeight) {
						speakerDivMaxHeight = height;
					}
				}).promise().done(function (item) {
					$('.speaker-div').each(function () {
						$(this).outerHeight(speakerDivMaxHeight);
					});
				});
			}

			$container.imagesLoaded(function () {


				var hash = window.location.hash;
				console.log('Hash is: ' + hash);
				if (!(hash === '')) {
					var el = $(hash);
					console.log('Scroll: ', el);
					$('html, body').animate({scrollTop: el.offset().top - 100}, 'fast');
				}
				resizeSpeakerDivs();
				$container.masonry({
					itemSelector: '.masonryitem',
					columnWidth: '.masonryitem',
					isAnimated: true
				});
				$('#speaker').css('opacity', '1');
			});

		});
	</script>
</content>
