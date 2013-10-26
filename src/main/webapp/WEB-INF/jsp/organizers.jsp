<%@ include file="/WEB-INF/jsp/includes/taglibs.jsp"%>
<% pageContext.setAttribute("lf", "\n"); %>
<div id="devnex" class="jumbotron">
    <div class="container">
        <div id="banner">
            <h1 id="gray">DevNexus 2014</h1>
            <h1 id="white">Organizers</h1>
        </div>
    </div>
</div>
<div class="container">
    <div id="speaker2">
        <div id="row">
            <c:forEach items="${organizerList.organizers}" var="organizer" varStatus="status">
                <c:if test="${status.first}">
                    <div class="col-md-3">
                </c:if>
                <c:if test="${!status.first && (status.index % columnLength == 0)}">
                    </div><div class="col-md-3">
                </c:if>
                <p><a href="#${organizer.firstName}_${organizer.lastName}"><c:out
                        value="${organizer.firstName}"/> <c:out value="${organizer.lastName}"/></a></p>

                <c:if test="${status.last}">
                    </div>
                </c:if>
            </c:forEach>
        </div>
        <div style="clear: both"></div>
        <div id="row">
            <div id="bio">

                <c:forEach items="${organizerList.organizers}" var="organizer" varStatus="status">
                    <c:if test="${status.index%3 == 0}">
                        <br style="clear: both;"/>
                    </c:if>
                    <a style="padding-top: 100px" id="${organizer.firstName}_${organizer.lastName}" name="${organizer.firstName}_${organizer.lastName}"></a>
                    <div class="col-md-4">
                        <div id="one-third">
                            <c:if test="${organizer.picture != null}">
                                <img class="speaker" src="${ctx}${baseSiteUrl}/organizers/${organizer.id}.jpg" />
                            </c:if>

                            <h3 >
                                <c:out value="${organizer.firstName}"/>
                                <c:out value="${organizer.lastName}"/>
                            </h3>

                            <div id="social">
                                <ul>
                                    <c:if test="${!empty organizer.twitterId}">
                                        <li id="twitter"><a
                                                href="https://twitter.com/<c:out value="${organizer.twitterId}" />"
                                                ><img class="social" alt="<c:out
                                            value='${organizer.googlePlusId}' />"
                                                      src="${ctx}/img/icons/icondock/24px/twitter.png"/>@<c:out
                                                value="${organizer.twitterId}"/></a>
                                        </li>
                                    </c:if>

                                    <c:if test="${!empty organizer.googlePlusId}">
                                        <li id="google"><a
                                                href="https://plus.google.com/<c:out value="${organizer.googlePlusId}" />"><img
                                                class="social" alt="<c:out
                                            value='${organizer.googlePlusId}' />"
                                                src="${ctx}/img/icons/icondock/24px/google-plus.png"/></a></li>
                                    </c:if>

                                    <c:if test="${!empty organizer.linkedInId}">
                                        <li id="linkedin"><a
                                                href="http://www.linkedin.com/in/<c:out value="${organizer.linkedInId}" />">LinkedIn</a></li>
                                    </c:if>
                                </ul>
                            </div>


                            <p class="detail">
                                <c:out value="${organizer.bioAsHtml}" escapeXml="false"/>
                            </p>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </div>
</div>
