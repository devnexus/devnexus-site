<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@page import="com.devnexus.ting.model.PresentationType"%>
<%@ include file="/WEB-INF/jsp/includes/taglibs.jsp" %>
<% pageContext.setAttribute("keynoteType", PresentationType.KEYNOTE); %>

<head>
    <title>${contextEvent.title} | Speaker Details for ${speaker.firstLastName}</title>
</head>
<section id="speaker" class="bg-light-gray" style="margin-top: 2em; text-align: left; padding-bottom: 0">
    <h1 class="featured-header">
        ${presentation.title}
    </h1>
    <div class="row">
        <h2 class="featured-header">
            abstract
        </h2>
        <div class="col-sm-10 col-sm-offset-1">
            <p class="text-center">
                ${presentation.descriptionAsHtml}
            </p>
        </div>
    </div>
    <div class="row">
        <h2 class="featured-header">
            Speakers
        </h2>

        <div class="row">
            <c:forEach items="${presentation.speakers}" var="speaker" varStatus="speakerStatus">
                <div class="col-sm-6 col-lg-3 col-sm-offset-3">        
                    <A href="${ctx}/s/speakers/${speaker.id}">
                        <c:if test="${speaker.picture != null}">
                            <img style="margin: 20px;float:left;width:200px;height:200px;display: inline" src="${ctx}${baseSiteUrl}/speakers/${speaker.id}.jpg" title="${speaker.firstLastName}"/>
                        </c:if>
                        <c:if test="${speaker.picture == null}">
                            <img style="margin: 20px;float:left;width:200px;height:200px;display: inline" src="${ctx}${baseSiteUrl}/speakers/${speaker.id}.jpg" title="${speaker.firstLastName}"/>
                        </c:if>
                        <h3 style="float:left;color:black"><c:out value="${speaker.firstName}"/> <c:out value="${speaker.lastName}"/></h3>
                    </A>

                </div>
            </c:forEach>
        </div>
    </div>
</section>

<jsp:include page="includes/questions.jsp"/>

