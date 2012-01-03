<%@ include file="/WEB-INF/jsp/includes/taglibs.jsp"%>
<% pageContext.setAttribute("lf", "\n"); %>

<title>DevNexus 2011 - Presentations</title>
<div id="content" class="span-22 last">
  <div class="quote"><span>What the community says:</span> "Great conference to share best practices"</div>
    <h2>Presentations
        <c:if test="${not empty event}">for ${event.title}</c:if></h2>
    <img  src="${ctx}/img/devnexus_2009/devnexus_2009_1.jpg" class="page-image"/>
    <ul>
        <c:forEach items="${presentationList.presentations}" var="presentation">
            <li><a href="#${presentation.id}"><c:out value="${presentation.title}"/></a></li>
        </c:forEach>
    </ul>

  <c:forEach items="${presentationList.presentations}" var="presentation">
      <div class="presentation">
          <h3 id="${presentation.id}" class="title"><c:out value="${presentation.title}"/></h3>
          <c:choose>
            <c:when test="${not empty presentation.speaker}">
                <p class="speaker">
                    <a href="${ctx}/s/speakers#${presentation.speaker.firstName}_${presentation.speaker.lastName}">
                        <c:out value="${presentation.speaker.firstName}"/>
                            <c:out value="${presentation.speaker.lastName}"/></a>
                </p>
            </c:when>
            <c:otherwise>
                <p class="speaker">TBD</p>
            </c:otherwise>
          </c:choose>
          <div class="abstract">
              <c:out value=""/>
                <c:out value="${presentation.descriptionAsHtml}" escapeXml="false"/>
          </div>
          <c:choose>
              <c:when test="${not empty presentation.presentationLink}">
                  <p class="download"><a href="${presentation.presentationLink}">Download Presentation</a> (External Link)</p>
              </c:when>
          </c:choose>
          <c:choose>
              <c:when test="${presentation.presentationFile != null}">
                  <p class="download"><a href="${ctx}/s/presentations/${presentation.id}/slides">Download Presentation</a></p>
              </c:when>
          </c:choose>
          <c:if test="${not empty presentation.audioLink}">
              <p class="download"><a href="${presentation.audioLink}">Audio Recording</a> (MP3)</p>
          </c:if>
      </div>
  </c:forEach>
</div>