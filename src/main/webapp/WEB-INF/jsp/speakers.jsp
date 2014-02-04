<%@page import="com.devnexus.ting.core.model.PresentationType" %>
<%@ include file="/WEB-INF/jsp/includes/taglibs.jsp" %>
<% pageContext.setAttribute("lf", "\n"); %>
<% pageContext.setAttribute("keynoteType", PresentationType.KEYNOTE); %>

<head>
	<title><c:out value="${event.title}"/> - Speakers</title>
</head>

<div id="devnex" class="jumbotron">
    <div class="container">
        <div id="banner">
            <h1 id="gray"><c:out value="${event.title}"/></h1>
            <h1 id="white">Speakers</h1>
            <h3>Discover how the industry's best minds use the latest technologies to build solutions.</h3>
        </div>
    </div>
</div>

<c:if test="${speakerList.speakers.size() eq 0}">
    <style>
        .jumbotron {
            margin-bottom: 0px;
        }
    </style>
    <div class="red jumbotron" style="margin-bottom:0">
        <div class="container">
            <h1>Speakers are still coming in.</h1>

            <div class="row">
                <div class="col-md-12">
                    <p>
                        We are currently accepting topics from leaders, builders, thinkers and doers in our field and will be updating our speakers list as soon as we have our initial lineup.
                    </p>
                    <p>
                        Perhaps you have a technology you are passionate about or some bit of wisdom to share?  If so submit an abstract and our organizers will review it and let you know what we think.
                    </p>
                    <c:url var="cfpUrl" value="${baseSiteUrl}/cfp"/>

                    <center><a href="${cfpUrl}" class="btn btn-primary btn-lg">Send us an abstract!</a></center>
                </div>

            </div>
        </div>
    </div>
</c:if>
<c:if test="${speakerList.speakers.isEmpty() eq false}">
<div class="container">
    <div id="speaker2">
        <div id="row">
            <c:forEach items="${speakerList.speakers}" var="speaker" varStatus="status">
                <c:if test="${status.first}">
                    <div class="col-md-3">
                </c:if>
                <c:if test="${!status.first && (status.index % columnLength == 0)}">
                    </div><div class="col-md-3">
                </c:if>
                <p><a href="#${speaker.firstName}_${speaker.lastName}"><c:out
                        value="${speaker.firstName}"/> <c:out value="${speaker.lastName}"/></a></p>

                    <c:if test="${status.last}">
                        </div>
                    </c:if>
            </c:forEach>
        </div>
        <div style="clear: both"></div>
        <div id="row">
            <div id="bio">

                <c:forEach items="${speakerList.speakers}" var="speaker" varStatus="status">
                    <c:if test="${status.index%3 == 0}">
                        <br style="clear: both;"/>
                    </c:if>
                    <div class="col-md-4 speakerContainer">
                        <a style="padding-top: 100px; margin-top:-100px" id="${speaker.firstName}_${speaker.lastName}" name="${speaker.firstName}_${speaker.lastName}"></a>
                        <div id="one-third">
                            <c:if test="${speaker.picture != null}">
                                <img class="speaker" src="${ctx}${baseSiteUrl}/speakers/${speaker.id}.jpg"/>
                            </c:if>

                            <h3 >
                                <c:out value="${speaker.firstName}"/>
                                <c:out value="${speaker.lastName}"/>
                            </h3>

                            <div id="social">
                                <ul>
                                    <c:if test="${!empty speaker.twitterId}">
                                        <li id="twitter"><a
                                                href="https://twitter.com/<c:out value="${speaker.twitterId}" />"
                                                ><img class="social" alt="<c:out
                                            value='${speaker.googlePlusId}' />"
                                                      src="${ctx}/img/icons/icondock/24px/twitter.png"/>@<c:out
                                                value="${speaker.twitterId}"/></a>
                                        </li>
                                    </c:if>

                                    <c:if test="${!empty speaker.googlePlusId}">
                                        <li id="google"><a
                                                href="https://plus.google.com/<c:out value="${speaker.googlePlusId}" />"><img
                                                class="social" alt="<c:out
                                            value='${speaker.googlePlusId}' />"
                                                src="${ctx}/img/icons/icondock/24px/google-plus.png"/></a></li>
									</c:if>
									<c:if test="${!empty speaker.linkedInId}">
										<li id="linkedin"><a
											href="http://www.linkedin.com/in/<c:out value="${speaker.linkedInId}" />">LinkedIn</a></li>
									</c:if>
								</ul>
							</div>

							<c:if test="${!empty speaker.presentations}">
								<c:choose>
									<c:when test="${fn:length(speaker.presentations) gt 1}">
										<p><strong>Presentations:</strong><br/>
									</c:when>
									<c:otherwise>
										<p><strong>Presentation:</strong></p>
									</c:otherwise>
								</c:choose>
								<c:forEach var="presentation" items="${speaker.presentations}" varStatus="loop">
									<p>
										<a href="${siteUrl}/presentations#id-${presentation.id}"><c:out
										value="${presentation.title}"/></a>
										<c:if test="${presentation.presentationType == keynoteType}">
											(Keynote)
										</c:if>
										<c:if test="${loop.last}"><br/>--</c:if>
									</p>
								</c:forEach>
							</c:if>
							<p class="detail">
								<c:out value="${speaker.bioAsHtml}" escapeXml="false"/>
							</p>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </div>
</div>

	<content tag='bottom'>
		<script type="text/javascript">
			$(document).ready(function() {

                // or with jQuery
                var $container = $('#bio');
                var msnry;
                // initialize Masonry after all images have loaded
                $container.imagesLoaded( function() {
                    msnry = $container.masonry({
                        columnWidth: 'div.speakerContainer',
                        'margin-bottom': '10px',
                        itemSelector: '.speakerContainer',
                        isResizable: true
                    });
					var hash = window.location.hash;
					console.log('Hash is: ' + hash);
					if (!(hash === '')) {
						console.log('Scroll: ' + hash);
						$('html, body').animate({scrollTop: $(hash).offset().top - 100}, 'slow');
					}
                });

                $( window).resize(function() {
                  window.setTimeout(function(){
                      $container.masonry({
                          columnWidth: 'div.speakerContainer',
                          'margin-bottom': '10px',
                          itemSelector: '.speakerContainer',
                          isResizable: true
                      } );
                  }, 1000)
                });
			});
		</script>
	</content>
</c:if>
