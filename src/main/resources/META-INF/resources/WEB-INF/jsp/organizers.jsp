<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/includes/taglibs.jsp" %>

<title>${contextEvent.title} | Organizers</title>

<section id="speaker" class="bg-light-gray" style="margin-top: 0">
    <div class="row">
        <div class="speakers">
            <c:forEach items="${organizerList.organizers}" var="organizer" varStatus="status">
                <div class="col-sm-6 col-md-4 col-lg-3" style="height: 1000px; overflow-y: auto">
                    <div class="thumbnail">
                        <div id="${organizer.firstName}_${organizer.lastName}" class="speaker-member text-center">
                            <c:if test="${organizer.picture != null}">
                                <img class="img-responsive" src="${organizerPictures[organizer.id]}" alt="${organizer.firstName} ${organizer.lastName}" title="${organizer.firstName} ${organizer.lastName}"/>
                            </c:if>
                            <h4 class="text-center"><c:out value="${organizer.firstName}"/> <c:out value="${organizer.lastName}"/></h4>
                            <p class="text-center" style="margin-top: 1em;">
                                <c:if test="${!empty organizer.googlePlusId}">
                                    <a class="btn btn-default btn" data-toggle="tooltip" data-placement="bottom" href="https://plus.google.com/<c:out value="${organizer.googlePlusId}" />" target="_blank">
                                        <span class="fa fa-google"></span>
                                    </a>
                                </c:if>
                                <c:if test="${!empty organizer.twitterId}">
                                    <a class="btn btn-default btn" data-toggle="tooltip" data-placement="bottom" href="https://twitter.com/<c:out value="${organizer.twitterId}"/>" target="_blank">
                                        <span class="fa fa-twitter"></span>
                                    </a>
                                </c:if>
                                <c:if test="${!empty organizer.linkedInId}">
                                    <a class="btn btn-default btn" data-toggle="tooltip" data-placement="bottom" href="http://www.linkedin.com/in/<c:out value="${organizer.linkedInId}" />" target="_blank">
                                        <span class="fa fa-linkedin"></span>
                                    </a>
                                </c:if>
                                <c:if test="${!empty organizer.githubId}">
                                    <a class="btn btn-default btn" data-toggle="tooltip" data-placement="bottom" href="http://github.com/<c:out value="${organizer.githubId}" />" target="_blank">
                                        <span class="fa fa-github"></span>
                                    </a>
                                </c:if>
                                <c:if test="${!empty organizer.lanyrdId}">
                                    <a class="btn btn-default btn" data-toggle="tooltip" data-placement="bottom" href="http://lanyrd.com/profile/<c:out value="${organizer.lanyrdId}" />" target="_blank">
                                        <span>L</span>
                                    </a>
                                </c:if>
                            </p>
                            <div><c:out value="${organizer.bioAsHtml}" escapeXml="false"/></div>
                        </div>
                    </div>
                </div>

            </c:forEach>
        </div>
    </div>
</section>

<jsp:include page="includes/questions.jsp"/>
</content>

