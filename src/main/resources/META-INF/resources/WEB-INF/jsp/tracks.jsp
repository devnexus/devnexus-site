<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/includes/taglibs.jsp"%>
<% pageContext.setAttribute("lf", "\n"); %>

<title>Tracks</title>

<!-- intro -->
<section id="tracks" class="module parallax parallax-3">
	<div class="container header">
		<div class="row centered">
			<div class="col-md-10 col-md-offset-1">
				<div class="top-intro travel">
					<h4 class="section-white-title decorated"><span>Tracks</span></h4>
				</div>
			</div>
		</div>
	</div>
</section>
<!-- /intro -->

<div class="row">
	<div class="col-md-10 col-md-offset-1">
		<table class="table table-hover">
			<thead>
				<tr><th>&nbsp;</th><th>Track</th><th class="text-center">Sessions</th></tr>
			</thead>
			<tbody>
				<c:forEach items="${trackList.tracks}" var="track">
				<tr>
					<c:url value="/s/presentations" var="showPresosForTrack">
						<c:param name="trackId" value="${track.id}"/>
					</c:url>
					<td style="width: 30px; background-color: ${not empty track.color?track.color:'#FFFFFF' }">&nbsp;</td>
					<td><a href="${showPresosForTrack}"><c:out value="${track.name}" /></a></td>
					<td class="text-center"><span class="badge"><c:out value="${track.presentations.size()}" /></span></td>
				</tr>
				</c:forEach>
				<c:if test="${unassignedSessions > 0}">
					<tr>
						<td style="width: 30px;">&nbsp;</td>
						<td>Unassigned Presentations</td>
						<td class="text-center"><span class="badge"><c:out value="${unassignedSessions}" /></span></td>
					</tr>
				</c:if>
			</tbody>
		</table>
	</div>
</div>

