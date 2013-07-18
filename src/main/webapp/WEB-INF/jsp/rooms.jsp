<%@ include file="/WEB-INF/jsp/includes/taglibs.jsp"%>
<% pageContext.setAttribute("lf", "\n"); %>

<title>DevNexus 2013 - Rooms</title>
<div id="content" class="span-22 last">
	<div class="quote">
		<span>What the community says:</span> "Fantastic value, content vs
		expense is unmatched"
	</div>
	<h2>
		Rooms
		<c:if test="${not empty event}">for ${event.title}</c:if>
	</h2>

	<table style="width: 100%;">
		<thead>
			<tr><th>Room</th><th>Capacity</th><th>Track</th></tr>
		</thead>
		<tbody>
			<c:forEach items="${roomList.rooms}" var="room">
			<tr><td><c:out value="${room.name}" /></td><td><c:out value="${room.capacity}" /></td><td><c:out value="${room.track}" /></td></tr>
			</c:forEach>
		</tbody>
	</table>

</div>
