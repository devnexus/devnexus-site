<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@page import="com.devnexus.ting.model.PresentationType"%>
<%@ include file="/WEB-INF/jsp/includes/taglibs.jsp" %>
<% pageContext.setAttribute("keynoteType", PresentationType.KEYNOTE); %>

<head>
    <title>${contextEvent.title} | Speaker Details for ${speaker.firstLastName}</title>
</head>
<section id="speaker" class="bg-light-gray details" style="margin-top: 2em; text-align: left; padding-bottom: 0">
    <div class="row">
        <div class="col-sm-10 col-sm-offset-1" >
            <div id="${speaker.firstName}_${speaker.lastName}" class="speaker-member row">
                <img src="${speaker.pictureSerialized}" class="img-responsive col-xs-3 col-xs-offset-1" alt="">
                <h4 ><c:out value="${speaker.firstName}"/> <c:out value="${speaker.lastName}"/></h4>

                <c:if test="${!empty speaker.twitterId}">
                    <div>
                        <a href="https://twitter.com/<c:out value="${speaker.twitterId}"/>" target="_blank">
                            <span class="fa fa-twitter"> @${speaker.twitterId}</span> 
                        </a>
                    </div>
                </c:if>
                <c:if test="${!empty speaker.linkedInId}">
                    <div>
                        <a href="https://www.linkedin.com/in/<c:out value="${speaker.linkedInId}" />" target="_blank">
                            <span class="fa fa-linkedin"> ${speaker.linkedInId}</span>
                        </a>
                    </div>
                </c:if>
                <c:if test="${!empty speaker.githubId}">
                    <div>
                        <a href="https://github.com/<c:out value="${speaker.githubId}" />" target="_blank">
                            <span class="fa fa-github"> ${speaker.githubId}</span>
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
            <div class="biograpy-body"><c:out value="${speaker.bioAsHtml}" escapeXml="false"/></div>
        </div>
    </div>
    <c:forEach var="presentation" items="${speaker.presentations}" varStatus="loop">
        <div class="row">
            <div class="col-sm-10 col-sm-offset-1 ">
                <div class="presentation-header">
                    ${presentation.title}
                        <c:if test="${presentation.presentationType == keynoteType}">
                        (Keynote)
                    </c:if>
                </div>
                <div class="presentation-body"><c:out value="${presentation.descriptionAsHtml}" escapeXml="false"/></div>
            </div>
        </div>
    </c:forEach>

</section>


