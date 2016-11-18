<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@page import="com.devnexus.ting.model.PresentationType"%>
<%@ include file="/WEB-INF/jsp/includes/taglibs.jsp" %>
<% pageContext.setAttribute("keynoteType", PresentationType.KEYNOTE); %>

<title>${contextEvent.title} | Presentations</title>


<section class="container-fluid speakers sessions" >
    <h1 class="featured-header">
        <c:choose>
            <c:when test="${not empty track}">
                <span>Presentations for the ${track.name} track</span>
            </c:when>
            <c:otherwise>
                <span>Presentations</span>
            </c:otherwise>
        </c:choose>
    </h1>
    <div class="row">
        <c:set var="trackNameTracker" value=""/>
        <c:forEach items="${presentationList.presentations}" var="presentation" varStatus="status">
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
            <c:set var="trackName" value="${presentation.track.name}"/>
            <c:if test="${trackName ne trackNameTracker}">
                <c:set var="trackNameTracker" value="${trackName}"/>
                <c:choose>
                    <c:when test="${not empty track}">
                    </c:when>
                    <c:otherwise>
                    </div>
                        <h1 class="featured-header"><span>— ${trackName} —</span></h1>
                    <div class="row">
                    </c:otherwise>
                </c:choose>
            </c:if>
            <div id="id-${presentation.id}" class="col-sm-6 col-md-6 col-lg-4">
                <div class="thumbnail" style="border: 1px gray solid; box-shadow: 1px 1px 5px black; border-radius: 0; height: 285px;overflow: hidden" >
                    <a href="${ctx}/s/${contextEvent.eventKey}/presentations/${presentation.id}" style="color: white" title="${presentation.title}">
                        <div class="caption" style="position: initial; height: 80px;padding: 5px 15px 5px 15px; background-color: ${trackColor}">
                            <h3 style="font-size: 18px">${presentation.title}</h3>
                        </div>
                    </a>
                    <div style="margin-left: 15px;margin-right: 15px; margin-top: 5px;">
                        <c:forEach items="${presentation.speakers}" var="speaker" varStatus="speakerStatus">
                            <c:if test="${speakerStatus.index < 2}">

                                <A href="${ctx}/s/speakers/${speaker.id}">
                                    <c:if test="${speaker.picture != null}">
                                        <img  style="width:72px;height:72px;display: inline; margin-bottom: 5px" src="${ctx}${baseSiteUrl}/speakers/${speaker.id}.jpg" title="${speaker.firstLastName}"/>
                                    </c:if>
                                    <c:if test="${speaker.picture == null}">
                                        <img class="img-circle"style="width:72px;height:72px; margin-bottom: 5px;display: inline" src="${ctx}${baseSiteUrl}/speakers/${speaker.id}.jpg" title="${speaker.firstLastName}"/>
                                    </c:if>
                                        <h3 style="display: inline">${speaker.firstName} ${speaker.lastName}</h3>
                                </A>
                                <br/>
                            </c:if>
                        </c:forEach>
                            <c:if test="${fn:length(presentation.speakers) gt 2}">
                                <a href="${ctx}/s/${contextEvent.eventKey}/presentations/${presentation.id}" >
                                    <h3 style="margin-top: 10px">More Speakers…</h3>
                                </a>            
                            </c:if>
                    </div>
                </div>
            </div>
            <c:if test="${status.last}">

            </c:if>
        </c:forEach>
    </div>
</section>

<jsp:include page="includes/questions.jsp"/>
