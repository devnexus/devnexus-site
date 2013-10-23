<%@page import="com.devnexus.ting.core.model.PresentationType" %>
<%@ include file="/WEB-INF/jsp/includes/taglibs.jsp" %>

<div class="container">
<!-- Example row of columns -->
<c:set var="trackName" value="nill"/>
<c:forEach items="${presentationList.presentations}" var="presentation" varStatus="status">
    <c:if test="${presentation.room ne null}">
        <c:if test="${trackName ne presentation.room.name}">
            <c:set var="trackName" value="${presentation.room.name}"/>
            <c:if test="${!status.first}">
                </div>
                </div>
            </c:if>
            <h1 class="${presentation.room.cssStyleName}"><strong>${presentation.room.name}</strong><br/>Workshop</h1>

            <div id="h4wrap"><h4>Presentations</h4></div>
            <div id="speakers">
            <div class="row ${presentation.room.name}">
        </c:if>
        <a style="padding-top: 100px" name ="id-${presentation.id}" id="id-${presentation.id}"></a>
        <div id="presentation" class="col-md-4">
            <div class="row ${presentation.room.cssStyleName}">
                <div class="col-md-5  ">
                    <c:if test="${presentation.speaker.picture != null}">
                        <img class="speaker" src="${ctx}${baseSiteUrl}/speakers/${presentation.speaker.id}.jpg"/>
                    </c:if>
                </div>
                <div class="col-md=7">
                    <c:choose>
                        <c:when test="${not empty presentation.speaker}">
                            <h4>
                                <a
                                        href="${siteUrl}/speakers#${presentation.speaker.firstName}_${presentation.speaker.lastName}">
                                    <c:out value="${presentation.speaker.firstName}"/> <c:out
                                        value="${presentation.speaker.lastName}"/>
                                </a>
                            </h4>
                        </c:when>
                        <c:otherwise>
                            <p class="speaker">TBD</p>
                        </c:otherwise>
                    </c:choose>
                    <h3 class="title">

                        <c:out value="${presentation.title}"/>
                    </h3>

                    <c:out value=""/>
                    <c:out value="${presentation.descriptionAsHtml}" escapeXml="false"/>
                    <c:if test="${not empty presentation.skillLevel}">
                        <br/>
                        <strong>Skill Level: </strong><c:out value="${presentation.skillLevel.name}"/>
                    </c:if>
                    <c:choose>
                        <c:when test="${not empty presentation.presentationLink}">
                            <br/>

                            <p class="download">
                                <a href="${presentation.presentationLink}">Download
                                    Presentation</a> (External Link)
                            </p>
                        </c:when>
                    </c:choose>
                    <c:choose>
                        <c:when test="${presentation.presentationFile != null}">
                            <br/>

                            <p class="download">
                                <a
                                        href="${ctx}${baseSiteUrl}/presentations/${presentation.id}/slides">Download
                                    Presentation</a>
                            </p>
                        </c:when>
                    </c:choose>
                    <c:if test="${not empty presentation.audioLink}">
                        <br/>

                        <p class="download">
                            <a href="${presentation.audioLink}">Audio Recording</a> (MP3)
                        </p>
                    </c:if>
                </div>
            </div>
        </div>
    </c:if>
</c:forEach>
</div>
</div>

</div>

	<content tag='bottom'>
		<script type="text/javascript">
			$(document).ready(function() {

				var container = document.querySelector('#bio');
				var msnry = new Masonry( container, {
					columnWidth: 1,
				  'margin-bottom': '10px',
				  itemSelector: '.speakerContainer'
				});

			});
		</script>
	</content>