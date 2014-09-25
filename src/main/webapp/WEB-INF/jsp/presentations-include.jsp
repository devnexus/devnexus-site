<%@page import="com.devnexus.ting.core.model.PresentationType" %>
<%@ include file="/WEB-INF/jsp/includes/taglibs.jsp" %>

<div class="row ${trackStyle}" style="${trackColor}">
	<div class="col-xs-5">
		<c:forEach items="${presentation.speakers}" var="speaker">
			<c:if test="${speaker.picture != null}">
				<img class="speaker" src="${ctx}${baseSiteUrl}/speakers/${speaker.id}.jpg" style="margin-right: 10px" alt="${speaker.firstLastName}"/>
			</c:if>
		</c:forEach>
	</div>
	<div class="col-xs-7">
		<c:choose>
			<c:when test="${not empty presentation.speakers}">
				<c:forEach items="${presentation.speakers}" var="speaker">
					<h4>
						<a href="${siteUrl}/speakers#${speaker.firstName}_${speaker.lastName}">
							<c:out value="${speaker.firstName}"/> <c:out value="${speaker.lastName}"/>
						</a>
					</h4>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<p class="speaker">TBD</p>
			</c:otherwise>
		</c:choose>
		<c:if test="${not empty presentation.track}">
			<br/>
			<strong>Track: </strong><c:out value="${presentation.track.name}"/>
		</c:if>
		<c:if test="${not empty presentation.skillLevel}">
			<br/>
			<strong>Skill Level: </strong><c:out value="${presentation.skillLevel.name}"/>
		</c:if>
		<c:if test="${not empty presentation.scheduleItem}">
			<br/>
			<strong>Slot:</strong>
			<fmt:formatDate pattern="M/d, h:mm a" value="${presentation.scheduleItem.fromTime}"/><br/>
			<strong>Room:</strong>
			<c:choose>
				<c:when test="${not empty presentation.scheduleItem.room}">
					<c:out value="${presentation.scheduleItem.room.name}"/>
				</c:when>
				<c:otherwise>TBD</c:otherwise>
			</c:choose>

		</c:if>
	</div>
	<div class="clearfix"></div>
	<div class="col-md-12" style="padding-left: 10px; padding-right: 10px; padding-bottom: 10px">
		<h3 class="title"><c:out value="${presentation.title}"/></h3>
		<c:out value=""/>
		<c:out value="${presentation.descriptionAsHtml}" escapeXml="false"/>
		<c:if test="${not empty presentation.presentationTags}">
			<hr style="border-style: dotted;"/>
			<strong>Tags: </strong>
			<c:forEach items="${presentation.presentationTags}" var="tag" varStatus="status"><c:out value="${tag.name}"/><c:if test="${not status.last}">,
			</c:if></c:forEach>
		</c:if>
		<c:if test="${not empty presentation.presentationLink}">
			<hr/>
			<p class="download">
				<a href="${presentation.presentationLink}">Download Presentation</a> (External Link)
			</p>
		</c:if>
		<c:if test="${presentation.presentationFile != null}">
			<hr/>
			<p class="download">
				<a href="${ctx}${baseSiteUrl}/presentations/${presentation.id}/slides">Download Presentation</a>
			</p>
		</c:if>
		<c:if test="${not empty presentation.audioLink}">
			<br/>
			<p class="download">
				<a href="${presentation.audioLink}">Audio Recording</a> (MP3)
			</p>
		</c:if>
	</div>
</div>
