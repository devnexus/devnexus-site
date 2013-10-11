<%@ include file="/WEB-INF/jsp/includes/taglibs.jsp"%>
<% pageContext.setAttribute("lf", "\n"); %>

<title>DevNexus 2013 - Organizers</title>
<div id="content" class="span-22 last">

	<h2>Evaluations</h2>

	<c:forEach items="${cfps}" var="cfp">
		<div class="speaker">
			<p>
				<c:out value="${cfp.firstName}"/>
			</p>
			<br style="clear: both;" />
		</div>
	</c:forEach>

</div>
