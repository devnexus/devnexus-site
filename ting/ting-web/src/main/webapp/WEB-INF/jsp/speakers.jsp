<%@ include file="/WEB-INF/jsp/includes/taglibs.jsp"%>

<title>DevNexus 2011 - Speakers</title>
<div id="content" class="span-22 last">
	<div class="quote"><span>What the community says:</span> "Fantastic value, content vs expense is unmatched"</div>
	<h2>Speakers
	<c:if test="${not empty event}">for ${event.title}</c:if>
	</h2>
	    <img class="page-image" src="${ctx}/images/devnexus_2009/devnexus_2009_2.jpg"/>
	    <ul>
	        <c:forEach items="${speakers}" var="speaker">
	            <li><a href="#${speaker.firstName}_${speaker.lastName}"><c:out value="${speaker.firstName}"/> <c:out value="${speaker.lastName}"/></a></li>
	        </c:forEach>
	    </ul>

	    <h2 style="clear:both;">Speaker Biographies</h2>
	    <c:forEach items="${speakers}" var="speaker">
		    <div class="speaker">
		        <h3 id="${speaker.firstName}_${speaker.lastName}"><c:out value="${speaker.firstName}"/> <c:out value="${speaker.lastName}"/></h3>
		        <c:if test="${speaker.picture != null}">
		           <img src="${ctx}/s/speaker/${speaker.id}.jpg"/>
		        </c:if>
		        <p>
		          <c:out value="${speaker.bio}"/>
		        </p>
		        <c:if test="${!empty speaker.presentations}">
		           <p class="presentation-header">Presentation:</p>
		           <ul>
			           <c:forEach items="${speaker.presentations}" var="presentation">
			               <li><c:out value="${presentation.title}"/></li>
			           </c:forEach>
		           </ul>
		        </c:if>

		        <br style="clear: both;"/>
		    </div>
	    </c:forEach>
</div>