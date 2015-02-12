<%@ include file="/WEB-INF/jsp/includes/taglibs.jsp" %>
<!-- intro -->
<section id="about" class="module parallax parallax-3">
	<div class="container header">
		<div class="row centered">
			<div class="col-md-10 col-md-offset-1">
				<div class="top-intro travel">
					<h4 class="section-white-title decorated"><span>Past Conferences</span></h4>
					<h5 class="intro-white-lead">See our track record.</h5>
					<br>
					<ul class="list-inline">
						<c:forEach items="${organizerList.organizers}" var="organizer" varStatus="status">
							<li><a class="speaker-link" href="#${organizer.firstName}_${organizer.lastName}"><c:out
								value="${organizer.firstName}"/> <c:out value="${organizer.lastName}"/></a></li>
						</c:forEach>
					</ul>
				</div>
			</div>
		</div>
	</div>
</section>
<!-- /intro -->
<div class="container" style="margin-bottom:20px">
<!-- Example row of columns -->
<c:set var="trackName" value="nill"/>
<c:forEach items="${eventsForMenu}" var="event" varStatus="status">

        <div id="event" class="col-md-4">
            <div class="row ">
                <div class="col-md-12">
                    <h3 class="title">
                        <c:out value="${event.title}"/>
                    </h3>

                    <p><a href="<c:url value='${baseSiteUrl}/${event.eventKey}/speakers'/>"><c:out
                            value="${event.title}"/> Speakers</a></p>
                    <p><a href="<c:url value='${baseSiteUrl}/${event.eventKey}/presentations'/>"><c:out
                            value="${event.title}"/> Presentations</a></p>
                    <c:if test="${event.eventKey eq 'devnexus2012'}">
                        <p><a href="http://devnexus.com/static/2012/audio/">DevNexus 2012 All Audio Recordings</a></p>
                    </c:if>
                    <c:if test="${event.eventKey eq 'devnexus2013'}">
                        <p><a href="http://www.infoq.com/devnexus/">DevNexus 2013 Videos on InfoQ</a></p>
                    </c:if>

                </div>
            </div>
        </div>
</c:forEach>
</div>
</div>

</div>

