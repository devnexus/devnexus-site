<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@page import="com.devnexus.ting.model.PresentationType"%>
<%@ include file="/WEB-INF/jsp/includes/taglibs.jsp" %>
<% pageContext.setAttribute("keynoteType", PresentationType.KEYNOTE); %>

<head>
    <title>${contextEvent.title} | Speakers</title>
</head>

<body>
    <section class="container-fluid speakers" >
        <h1 class="featured-header">Speakers</h1>
        
        <c:if test="${not empty speakerList.speakers}">
<!--            <div class="row track-filter" >
                <div class="btn-group">
                    <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        Track <span class="caret"></span>
                    </button>
                    <ul class="dropdown-menu">
                        <c:if test="${empty trackList}">
                            <li><a href="#">We are still preparing tracks.</a></li>
                            </c:if>
                            <c:forEach items="${trackList}" var="track" varStatus="status">

                            <li><a href="#">${track.name}</a></li>
                            </c:forEach>
                    </ul>
                </div>
            </div>-->
        </c:if>
        <div class="row">
            <c:if test="${empty speakerList.speakers}">
                <h1 class="featured-header">We are currently evaluating CFPs and will have speakers ready soon.</h1>
            </c:if>

            <c:forEach items="${speakerList.speakers}" var="speaker" varStatus="status">

                <div class="col-sm-6 col-md-4 col-lg-3">
                    <div class="thumbnail" id="${speaker.firstName}_${speaker.lastName}">
                        <img class="img-responsive" src="${speaker.pictureSerialized}" alt="${speaker.firstName} ${speaker.lastName}">
                        <div class="caption">
                            <h3>${speaker.firstName} ${speaker.lastName}</h3>
                            <p>${cfpSpeakersMap[speaker.id].company}&nbsp;</p>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </section>
</body>
