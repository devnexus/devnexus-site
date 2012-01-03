<%@ include file="/WEB-INF/jsp/includes/taglibs.jsp"%>
<% pageContext.setAttribute("lf", "\n"); %>

<title>DevNexus 2011 - Organizers</title>
<div id="content" class="span-22 last">
  <div class="quote"><span>What the community says:</span> "great networking opportunity, excellent technical knowledge transfer"</div>

      <h2>Organizers</h2>
        <ul>
            <c:forEach items="${organizerList.organizers}" var="organizer">
                <li><a href="#${organizer.firstName}_${organizer.lastName}"><c:out value="${organizer.firstName}"/> <c:out value="${organizer.lastName}"/></a></li>
            </c:forEach>
        </ul>

        <c:forEach items="${organizerList.organizers}" var="organizer">
            <div class="speaker">
                <h3 id="${organizer.firstName}_${organizer.lastName}"><c:out value="${organizer.firstName}"/> <c:out value="${organizer.lastName}"/></h3>
                <img src="${ctx}/s/organizers/${organizer.id}.jpg"/>
                <p>
                  <c:set var="organizerBio"><c:out value="${organizer.bio}" escapeXml="true"/></c:set>
                  <c:out value="${fn:replace(organizerBio, lf, '<br/>')}" escapeXml="false"/>
                </p>
                <c:if test="${!empty organizer.twitterId}">
                   <p>Twitter: <a href="https://twitter.com/<c:out value="${organizer.twitterId}" />">@<c:out value="${organizer.twitterId}" /></a></p>
                </c:if>
                <br style="clear: both;"/>
            </div>
        </c:forEach>

</div>
