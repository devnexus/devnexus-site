<%@ include file="/WEB-INF/jsp/includes/taglibs.jsp"%>

<title>DevNexus 2011 - Presentations</title>
<div id="content" class="span-22 last">
	<div class="quote"><span>What the community says:</span> "Great conference to share best practices"</div>
	<h2>Presentations
	<c:if test="${not empty event}">for ${event.title}</c:if></h2>
	<img  src="${ctx}/devnexus_2009/devnexus_2009_1.jpg" class= "page-image"/>

	<c:forEach items="${presentations}" var="presentation">
	    <div class="presentation">
	        <h3 class="title"><c:out value="${presentation.title}"/></h3>
	        <c:choose>
		        <c:when test="${not empty presentation.speaker}">
		            <p class="speaker"><c:out value="${presentation.speaker.firstName}"/>
		                               <c:out value="${presentation.speaker.lastName}"/>
		        </c:when>
		        <c:otherwise>
		            <p class="speaker">TBD</p>
		        </c:otherwise>
	        </c:choose>
	        <div class="abstract">
	            <c:out value="${presentation.description}"/>
	        </div>
	        <c:choose>
	            <c:when test="${not empty presentation.presentationLink}">
	                <p class="download"><a href="${ctx}/s/presentation/${presentation.id}/slides">Download Presentation</a></p>
	            </c:when>
	            <c:otherwise>
	                <p class="download">Slides not available, yet.</p>
	            </c:otherwise>
	        </c:choose>
	    </div>
	</c:forEach>
</div>