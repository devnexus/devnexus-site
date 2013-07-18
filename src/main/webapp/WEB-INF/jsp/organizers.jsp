<%@ include file="/WEB-INF/jsp/includes/taglibs.jsp"%>
<% pageContext.setAttribute("lf", "\n"); %>

<title>DevNexus 2013 - Organizers</title>
<div id="content" class="span-22 last">
	<div class="quote">
		<span>What the community says:</span> "great networking opportunity,
		excellent technical knowledge transfer"
	</div>

	<h2>Organizers</h2>
	<ul>
		<c:forEach items="${organizerList.organizers}" var="organizer">
			<li><a href="#${organizer.firstName}_${organizer.lastName}"><c:out
						value="${organizer.firstName}" /> <c:out
						value="${organizer.lastName}" /></a></li>
		</c:forEach>
	</ul>

	<c:forEach items="${organizerList.organizers}" var="organizer">
		<div class="speaker">
			<h3 id="${organizer.firstName}_${organizer.lastName}">
				<c:out value="${organizer.firstName}" />
				<c:out value="${organizer.lastName}" />
			</h3>
			<c:if test="${organizer.picture != null}">
				<img class="speaker" src="${ctx}${baseSiteUrl}/organizers/${organizer.id}.jpg" />
			</c:if>
			<p>
				<c:out value="${organizer.bioAsHtml}" escapeXml="false" />
			</p>
			<c:if test="${!empty organizer.twitterId}">
				<p><a href="https://twitter.com/<c:out value="${organizer.twitterId}" />"
						><img class="social" alt="<c:out
							value='${organizer.googlePlusId}' />" src="${ctx}/img/icons/icondock/24px/twitter.png"/>@<c:out
							value="${organizer.twitterId}" /></a>
				</p>
			</c:if>
			<c:if test="${!empty organizer.googlePlusId}">
				<p><a href="https://plus.google.com/<c:out value="${organizer.googlePlusId}" />"><img class="social" alt="<c:out
							value='${organizer.googlePlusId}' />" src="${ctx}/img/icons/icondock/24px/google-plus.png"/></a></p>
			</c:if>
			<br style="clear: both;" />
		</div>
	</c:forEach>

</div>
