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
        <c:forEach items="${presentationList.presentations}" var="presentation" varStatus="status">
            <c:choose>
                <c:when test="${empty presentation.track}">
                    <c:set var="trackStyle" value="defaultTrackStyle"/>
                    <c:set var="trackColor" value=""/>
                    <c:set var="trackColorFont" value=""/>
                </c:when>
                <c:otherwise>
                    <c:set var="trackStyle" value="${presentation.track.cssStyleName}"/>
                    <c:set var="trackColor" value="border-color: ${presentation.track.color};"/>
                    <c:set var="trackFontColor" value="color: ${presentation.track.color};"/>
                </c:otherwise>
            </c:choose>
            <div id="id-${presentation.id}" class="col-sm-6 col-md-6 col-lg-4">
                <div class="thumbnail" >
                    <div style="margin-left: auto;margin-right: auto; text-align: center;margin-bottom: 15px">
                        <c:forEach items="${presentation.speakers}" var="speaker" varStatus="speakerStatus">
                            <c:if test="${speakerStatus.index < 3}">
                                <A href="${ctx}/s/speakers/${speaker.id}">
                                    <c:if test="${speaker.picture != null}">
                                        <img style="width:128px;height:128px;display: inline" src="${ctx}${baseSiteUrl}/speakers/${speaker.id}.jpg" title="${speaker.firstLastName}"/>
                                    </c:if>
                                    <c:if test="${speaker.picture == null}">
                                        <img style="width:128px;height:128px;display: inline" src="${ctx}${baseSiteUrl}/speakers/${speaker.id}.jpg" title="${speaker.firstLastName}"/>
                                    </c:if>
                                </A>
                            </c:if>
                        </c:forEach>
                    </div>
                    <div class="caption" style="position: initial; height: 120px;padding: 15px;padding: 15px">
                        <a href="${ctx}/s/${contextEvent.eventKey}presentations/${presentation.id}" style="color: white" title="${presentation.title}">
                            <h3 style="font-size: 18px">${presentation.title}</h3>
                        </a>
                    </div>
                </div>
            </div>
            <c:if test="${status.last}">

            </c:if>
        </c:forEach>
    </div>
</section>

<jsp:include page="includes/questions.jsp"/>
