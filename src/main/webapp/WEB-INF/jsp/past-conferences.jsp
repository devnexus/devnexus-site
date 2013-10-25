<%@ include file="/WEB-INF/jsp/includes/taglibs.jsp" %>
<div id="devnex" class="jumbotron">
    <div class="container">
        <div id="banner">
            <h1 id="gray">DevNexus 2014</h1>

            <h1 id="white"><c:out default="Atlanta, GA" value="${headerTitle}"/></h1>

            <h3><c:out default="The professional developer conference." value="${tag}"/></h3>
        </div>
    </div>
</div>
<div class="container" style="margin-bottom:20px">
<!-- Example row of columns -->
<c:set var="trackName" value="nill"/>
<c:forEach items="${eventsForMenu}" var="event" varStatus="status">

        <div id="event" class="col-md-4">
            <div class="row ">
                <div class="col-md=12">
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

