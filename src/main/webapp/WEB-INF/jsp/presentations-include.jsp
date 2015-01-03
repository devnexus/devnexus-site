<%@page import="com.devnexus.ting.core.model.PresentationType" %>
<%@ include file="/WEB-INF/jsp/includes/taglibs.jsp" %>

	<div class="speaker-member" style="${trackColor}">
		<c:choose>
			<c:when test="${not empty presentation.speakers}">
				<div class="row text-center">
						<c:forEach items="${presentation.speakers}" var="speaker">
							<div style="width: 150px; display: inline-block;">
								<c:if test="${speaker.picture != null}">
									<img class="img-responsive img-circle" src="${ctx}${baseSiteUrl}/speakers/${speaker.id}.jpg" alt="${speaker.firstLastName}"/>
								</c:if>
								<h4>
									<a href="${siteUrl}/speakers#${speaker.firstName}_${speaker.lastName}">
										<c:out value="${speaker.firstName}"/> <c:out value="${speaker.lastName}"/>
									</a>
								</h4>
							</div>
						</c:forEach>
				</div>
			</c:when>
			<c:otherwise>
				<h4>
					TBD
				</h4>
			</c:otherwise>
		</c:choose>
		<div class="row text-muted">
			<div class="col-xs-12 text-center">
				<c:out value="${presentation.title}"/>
			</div>
		</div>
		<div class="row">
			<div class="col-xs-12 text-center">
				<c:out value="${presentation.descriptionAsHtml}" escapeXml="false"/>
			</div>
		</div>

		<c:if test="${not empty presentation.track}">
			<div class="row">
				<div class="col-xs-3">
					<strong>Track:</strong>
				</div>
				<div class="col-xs-9" style="${trackFontColor}">
					<c:out value="${presentation.track.name}"/>
				</div>
			</div>
		</c:if>
		<c:if test="${not empty presentation.skillLevel}">
			<div class="row">
				<div class="col-xs-3" style="padding-right: 0;">
					<strong>Skill Level:</strong>
				</div>
				<div class="col-xs-9">
					<c:out value="${presentation.skillLevel.name}"/>
				</div>
			</div>
		</c:if>
		<c:if test="${not empty presentation.scheduleItem}">
			<div class="row">
				<div class="col-xs-3">
					<strong>Slot:</strong>
				</div>
				<div class="col-xs-9">
					<fmt:formatDate pattern="M/d, h:mm a" value="${presentation.scheduleItem.fromTime}"/>
					<c:choose>
						<c:when test="${not empty presentation.scheduleItem.room}">
							| <c:out value="${presentation.scheduleItem.room.name}"/>
						</c:when>
						<c:otherwise>Room TBD</c:otherwise>
					</c:choose>
				</div>
			</div>
		</c:if>
		<c:if test="${not empty presentation.presentationTags}">
			<div class="row small">
				<div class="col-xs-3">
					<strong>Tags: </strong>
				</div>
				<div class="col-xs-9">
					<c:forEach items="${presentation.presentationTags}" var="tag" varStatus="status">
						<c:url value="/s/presentations" var="showPresosForTags">
							<c:param name="tags" value="${tag.name}"/>
						</c:url>
						<a href="${showPresosForTags}"><c:out value="${tag.name}"
						/></a><c:if test="${not status.last}">,
						</c:if>
					</c:forEach>
				</div>
			</div>
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
