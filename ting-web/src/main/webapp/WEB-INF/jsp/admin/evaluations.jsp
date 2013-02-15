<%@ include file="/WEB-INF/jsp/includes/taglibs.jsp"%>
<% pageContext.setAttribute("lf", "\n"); %>

<title>DevNexus 2013 - Organizers</title>
<div id="content" class="span-22 last">

	<h2>Evaluations</h2>

	<c:forEach items="${evaluationList.evaluations}" var="evaluation">
		<div class="speaker">
			<h3>
				Rating: <c:out value="${evaluation.rating}" />, added <fmt:formatDate pattern="yyyy-MM-dd'T'HH:mm:ssz" value="${evaluation.createdDate}"/>
			</h3>
			<p>
				<c:out value="${evaluation.commentAsHtml}" escapeXml="false" />
			</p>
			<br style="clear: both;" />
		</div>
	</c:forEach>

</div>
