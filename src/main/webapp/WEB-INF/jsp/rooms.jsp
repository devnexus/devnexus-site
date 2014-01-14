<%@ include file="/WEB-INF/jsp/includes/taglibs.jsp"%>
<% pageContext.setAttribute("lf", "\n"); %>

<title>Rooms</title>
<div style="margin-top: 20px" class="col-md-10 col-md-offset-1">
	<h2>Rooms</h2>
</div>

<div class="row">
	<div class="col-md-10 col-md-offset-1">
		<table class="table table-hover">
			<thead>
				<tr><th>Rooms</th><th>Capacity</th><th>Track</th></tr>
			</thead>
			<tbody>
				<c:forEach items="${roomList.rooms}" var="room">
				<tr>
					<c:url value="/s/presentations" var="showRooms">
						<c:param name="order" value="room"/>
					</c:url>
					<td><a href="${showRooms}"><c:out value="${room.name}" /></a></td>
					<td><c:out value="${room.capacity}" /></td>
					<td><c:out value="${room.track}" /></td>
				</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</div>

