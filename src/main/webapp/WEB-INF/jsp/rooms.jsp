<%@ include file="/WEB-INF/jsp/includes/taglibs.jsp"%>
<% pageContext.setAttribute("lf", "\n"); %>

<title>Rooms</title>

<!-- intro -->
<section id="rooms" class="module parallax parallax-3">
	<div class="container header">
		<div class="row centered">
			<div class="col-md-10 col-md-offset-1">
				<div class="top-intro travel">
					<h4 class="section-white-title decorated"><span>Rooms</span></h4>
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
				<tr><th></th><th class="text-center"></th><th></th><th class="text-center" colspan="2"># of Session Slots</th></tr>
				<tr><th>&nbsp;</th><th>Rooms</th><th class="text-center">Capacity</th><th>Track</th><th class="text-center">Assigned</th><th class="text-center">Total</th></tr>
			</thead>
			<tbody>
				<c:forEach items="${roomList.rooms}" var="room">
				<tr>
					<c:url value="/s/presentations" var="showRooms">
						<c:param name="order" value="room"/>
					</c:url>
					<td style="width: 30px; background-color: ${not empty room.color?room.color:'#FFFFFF' }">&nbsp;</td>
					<td><a href="${showRooms}"><c:out value="${room.name}" /></a></td>
					<td class="text-center"><c:out value="${room.capacity}" /></td>
					<td><c:out value="${room.track}" /></td>
					<td class="text-center"><span class="badge"><c:out value="${fn:length(room.scheduleItemsWithAssignedSessions)}" /></span></td>
					<td class="text-center"><span class="badge"><c:out value="${fn:length(room.scheduleItems)}" /></span></td>
				</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</div>

