<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@page import="com.devnexus.ting.model.PresentationType"%>
<%@ include file="/WEB-INF/jsp/includes/taglibs.jsp" %>
<% pageContext.setAttribute("keynoteType", PresentationType.KEYNOTE); %>


<c:choose>
    <c:when test="${empty presentation.track}">
        <c:set var="trackStyle" value="defaultTrackStyle"/>
        <c:set var="trackColor" value="#94b769"/>
        <c:set var="trackColorFont" value=""/>
    </c:when>
    <c:otherwise>
        <c:set var="trackStyle" value="${presentation.track.cssStyleName}"/>
        <c:set var="trackColor" value="${presentation.track.color};"/>
        <c:set var="trackFontColor" value="color: ${presentation.track.color};"/>
    </c:otherwise>
</c:choose>

<head>
    <title>${contextEvent.title} | Presentation Details for ${presentation.title}</title>
</head>
<section id="speaker" class="bg-light-gray details" style="margin-top: 2em; text-align: left; padding-bottom: 0">
    <div class="row">
        <div class="col-sm-10 col-sm-offset-1" >
            <div id="${presentation.id}" class="speaker-member row " style="padding:25px; background-color: ${trackColor}">

                <h4 >${presentation.title}
                    <c:if test="${presentation.presentationType == keynoteType}">
                        (Keynote)
                    </c:if></h4>

                <c:if test="${!empty presentation.track}">
                    <div>
                        <a href="${ctx}/s/presentations?trackId=${presentation.track.id}" target="_blank">
                            <strong>Track : </strong>${presentation.track.name}
                        </a>
                    </div>
                </c:if>
                <div style="color:white">

                    <strong>Skill Level : </strong>${presentation.skillLevel}
                </div>
                <c:if test="${!empty presentation.room}">
                    <div>
                        <a href="${ctx}/presentations?roomId=${presentation.room.id}" target="_blank">
                            <strong>Room : </strong>${presentation.room.name}
                        </a>
                    </div>
                </c:if>

                <c:if test="${!empty presentation.presentationTags}">
                    <div style="color: white">

                        <strong style="display: inline">Tags : </strong>
                        <c:forEach var="tag" items="${presentation.presentationTags}" varStatus="tagStatus">
                            <a style="display: inline" href="${ctx}/s/presentations?tags=${tag.name}" target="_blank">
                                ${tag.name}
                            </a>
                            <c:if test="${!tagStatus.last}">
                                ,
                            </c:if>      
                        </c:forEach>
                    </div>
                </c:if>

            </div>

        </div>
    </div>
    <div class="row">
        <div class="col-sm-10 col-sm-offset-1 ">
            <div class="presentation-header">
                Abstract
            </div>
            <div class="biograpy-body"><c:out value="${presentation.descriptionAsHtml}" escapeXml="false"/></div>
        </div>
    </div>
    <c:forEach var="speaker" items="${presentation.speakers}" varStatus="loop">
        <div class="row">
            <div class="col-sm-10 col-sm-offset-1 ">
                <div class="biograpy-header">
                    ${speaker.firstName} ${speaker.lastName}

                </div>
                <div class="presentation-body"><c:out value="${speaker.bioAsHtml}" escapeXml="false"/></div>
            </div>
        </div>
    </c:forEach>

</section>

<jsp:include page="includes/questions.jsp"/>

