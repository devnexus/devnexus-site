<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/includes/taglibs.jsp" %>

<head>
    <title>${contextEvent.title} | Organizer Details for ${organizer.firstLastName}</title>
</head>
<section id="speaker" class="bg-light-gray details" style="margin-top: 2em; text-align: left; padding-bottom: 0">
    <div class="row">
        <div class="col-sm-10 col-sm-offset-1" >
            <div id="${organizer.firstName}_${organizer.lastName}" class="speaker-member row">
                <img src="${ctx}/s/organizers/${organizer.id}.jpg" class="img-responsive col-xs-3 col-xs-offset-1" alt="">
                <h4 ><c:out value="${organizer.firstName}"/> <c:out value="${organizer.lastName}"/></h4>

                <c:if test="${!empty organizer.twitterId}">
                    <div>
                        <a href="https://twitter.com/<c:out value="${organizer.twitterId}"/>" target="_blank">
                            <span class="fa fa-twitter"> @${organizer.twitterId}</span>
                        </a>
                    </div>
                </c:if>
                <c:if test="${!empty organizer.linkedInId}">
                    <div>
                        <a href="https://www.linkedin.com/in/<c:out value="${organizer.linkedInId}" />" target="_blank">
                            <span class="fa fa-linkedin"> ${organizer.linkedInId}</span>
                        </a>
                    </div>
                </c:if>
                <c:if test="${!empty organizer.githubId}">
                    <div>
                        <a href="https://github.com/<c:out value="${organizer.githubId}" />" target="_blank">
                            <span class="fa fa-github"> ${organizer.githubId}</span>
                        </a>
                    </div>
                </c:if>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-sm-10 col-sm-offset-1 ">
            <div class="biograpy-header">
                Biography
            </div>
            <div class="biograpy-body"><c:out value="${organizer.bioAsHtml}" escapeXml="false"/></div>
        </div>
    </div>
</section>


