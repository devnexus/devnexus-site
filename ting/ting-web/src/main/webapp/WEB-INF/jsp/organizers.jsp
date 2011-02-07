<%@ include file="/WEB-INF/jsp/includes/taglibs.jsp"%>

<title>DevNexus 2011 - Organizers</title>
<div id="content" class="span-22 last">
	<div class="quote"><span>What the community says:</span> "great networking opportunity, excellent technical knowledge transfer"</div>

	    <h2>Organizers</h2>
	    <ul>
	          <li><a href="#burr_sutter">Burr Sutter</a></li>
	          <li><a href="#gunnar_hillert">Gunnar Hillert</a></li>
	          <li><a href="#vincent_mayers">Vincent Mayers</a></li>
	          <li><a href="#sudhir_kamatkar">Sudhir Kamatkar</a></li>
	          <li><a href="#bruce_petro">Bruce Petro</a></li>
	          <li><a href="#summers_pittman">Summers Pittman</a></li>
	          <li><a href="#vincent_stoessel">Vincent Stoessel</a></li>
	    </ul>

        <h2 style="clear:both;">Organizers</h2>
        <c:forEach items="${organizerList.organizers}" var="organizer">
            <div class="speaker">
                <h3 id="${organizer.firstName}_${organizer.lastName}"><c:out value="${organizer.firstName}"/> <c:out value="${organizer.lastName}"/></h3>
                <img src="${ctx}/s/organizers/${organizer.id}.jpg"/>
                <p>
                  <c:set var="organizerBio"><c:out value="${organizer.bio}" escapeXml="true"/></c:set>
                  <c:out value="${fn:replace(organizerBio, lf, '<br/>')}" escapeXml="false"/>
                </p>
                <br style="clear: both;"/>
            </div>
        </c:forEach>

</div>
