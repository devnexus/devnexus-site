<%@ include file="/WEB-INF/jsp/includes/taglibs.jsp"%>
<% pageContext.setAttribute("lf", "\n"); %>

<title>DevNexus 2011 - Speakers</title>
<div id="content" class="span-22 last">
  <div class="quote"><span>What the community says:</span> "Fantastic value, content vs expense is unmatched"</div>
  <h2>Speakers
  <c:if test="${not empty event}">for ${event.title}</c:if>
  </h2>
      <img class="page-image" src="${ctx}/img/devnexus_2009/devnexus_2009_2.jpg"/>
      <ul>
          <c:forEach items="${speakerList.speakers}" var="speaker">
              <li><a href="#${speaker.firstName}_${speaker.lastName}"><c:out value="${speaker.firstName}"/> <c:out value="${speaker.lastName}"/></a></li>
          </c:forEach>
      </ul>

      <h2 style="clear:both;">Speaker Biographies</h2>
      <c:forEach items="${speakerList.speakers}" var="speaker">
        <div class="speaker">
            <h3 id="${speaker.firstName}_${speaker.lastName}"><c:out value="${speaker.firstName}"/> <c:out value="${speaker.lastName}"/></h3>
            <c:if test="${speaker.picture != null}">
               <img src="${ctx}/s/speakers/${speaker.id}.jpg"/>
            </c:if>
            <p>
              <c:out value="${speaker.bioAsHtml}" escapeXml="false"/>
            </p>
            <c:if test="${!empty speaker.presentations}">
               <p class="presentation-header">Presentation:</p>
               <ul>
                 <c:forEach items="${speaker.presentations}" var="presentation">
                     <li><a href="${ctx}/s/presentations#${presentation.id}"><c:out value="${presentation.title}"/></a></li>
                 </c:forEach>
               </ul>
            </c:if>
                <c:if test="${!empty speaker.twitterId}">
                   <p>Twitter: <a href="https://twitter.com/<c:out value="${speaker.twitterId}" />">@<c:out value="${speaker.twitterId}" /></a></p>
                </c:if>
            <br style="clear: both;"/>
        </div>
      </c:forEach>
</div>