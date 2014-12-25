<%@page import="com.devnexus.ting.core.model.PresentationType" %>
<%@ include file="/WEB-INF/jsp/includes/taglibs.jsp" %>

	<div id="id-${presentation.id}" class="speaker-member">
		<c:choose>
			<c:when test="${not empty presentation.speakers}">
				<c:forEach items="${presentation.speakers}" var="speaker">
					<c:if test="${speaker.picture != null}">
						<img class="img-responsive img-circle" src="${ctx}${baseSiteUrl}/speakers/${speaker.id}.jpg" alt="${speaker.firstLastName}"/>
					</c:if>
					<h4>
						<a href="${siteUrl}/speakers#${speaker.firstName}_${speaker.lastName}">
							<c:out value="${speaker.firstName}"/> <c:out value="${speaker.lastName}"/>
						</a>
					</h4>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<h4>
					TBD
				</h4>
			</c:otherwise>
		</c:choose>
		<p class="text-muted"><c:out value="${presentation.title}"/></p>
		<p><c:out value="${presentation.descriptionAsHtml}" escapeXml="false"/></p>
		<hr style="border-style: dotted;"/>
		<c:if test="${not empty presentation.track}">
			<p>
			<strong>Track: </strong><c:out value="${presentation.track.name}"/>
			</p>
		</c:if>
		<c:if test="${not empty presentation.skillLevel}">
			<p>
			<strong>Skill Level: </strong><c:out value="${presentation.skillLevel.name}"/>
			</p>
		</c:if>
		<c:if test="${not empty presentation.scheduleItem}">
			<p>
			<strong>Slot:</strong>
			<fmt:formatDate pattern="M/d, h:mm a" value="${presentation.scheduleItem.fromTime}"/><br/>
			<strong>Room:</strong>
			<c:choose>
				<c:when test="${not empty presentation.scheduleItem.room}">
					<c:out value="${presentation.scheduleItem.room.name}"/>
				</c:when>
				<c:otherwise>TBD</c:otherwise>
			</c:choose>
			</p>
		</c:if>
		<c:if test="${not empty presentation.presentationTags}">
			<p>
			<strong>Tags: </strong>
			<c:forEach items="${presentation.presentationTags}" var="tag" varStatus="status"><c:out value="${tag.name}"/><c:if test="${not status.last}">,
			</c:if></c:forEach>
			</p>
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
