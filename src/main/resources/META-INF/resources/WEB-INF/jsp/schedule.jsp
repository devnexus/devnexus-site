<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@page import="com.devnexus.ting.model.ScheduleItemType" %>
<%@ include file="/WEB-INF/jsp/includes/taglibs.jsp" %>
<% pageContext.setAttribute("scheduleItemTypeAdminsitrative", ScheduleItemType.ADMINISTRATIVE); %>
<% pageContext.setAttribute("scheduleItemTypeEveningReception", ScheduleItemType.EVENING_RECEPTION); %>
<% pageContext.setAttribute("scheduleItemTypeBreak", ScheduleItemType.BREAK); %>
<% pageContext.setAttribute("scheduleItemTypeKeynote", ScheduleItemType.KEYNOTE); %>
<% pageContext.setAttribute("scheduleItemTypeRegistration", ScheduleItemType.REGISTRATION); %>
<% pageContext.setAttribute("scheduleItemTypeMorningReception", ScheduleItemType.MORNING_RECEPTION); %>
<% pageContext.setAttribute("scheduleItemTypeSession", ScheduleItemType.SESSION); %>

<title>${contextEvent.title} | Schedule</title>


<section class="container-fluid schedule-config" >
    <h1 class="featured-header">
        SCHEDULE COMING SOON
    </h1>

</section>

<section class="container-fluid schedule schedule-config" >
    <h1 class="featured-header">
        SCHEDULE
        <span class="visible-xs visible-sm" style="float: right; padding-right: 40px;">
            <a style="font-size: 10pt; color: rgb(220, 78, 65);vertical-align: middle" href="#">Customize</a>
        </span>
    </h1>

    <div class="row hidden-xs">
        <div class="text-center">
            <button class="btn customize"><span class="badge"><img src="/assets/img/google-plus-2.png" class="icon"/></span>custom schedule</button>
            <button class="btn download">download</button>  
        </div>
    </div>

    <div class="row hidden-xs" id="schedule-day-filter">
        <div class="text-center">
            <div class="btn-group" role="group" >
                <button type="button" class="btn btn-default "><span class="big">Day 1</span><br><span class="little">02/20/2017</span></button>
                <button type="button" class="btn btn-default unselected "><span class="big">Day 2</span><br><span class="little">02/21/2017</span></button>
                <button type="button" class="btn btn-default unselected"><span class="big">Day 3</span><br><span class="little">02/22/2017</span></button>
            </div>
        </div>
    </div>

    <div class="row visible-xs sub-filter" id="schedule-day-dropdown-filter">

        <div class="btn-group" >
            <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                Day 1 <span class="caret"></span>
            </button>
            <ul class="dropdown-menu">
                <li><a href="#">Day 1</a></li>
                <li><a href="#">Day 2</a></li>
                <li><a href="#">Day 3</a></li>
            </ul>
        </div>

    </div>

</section>

<section class="container-fluid schedule" >


    <c:set value="" var="loopStartTime"/>
    <c:set value="" var="loopDay"/>

    <c:forEach items="${scheduleItemList.scheduleItems}" var="scheduleItem">

        <fmt:formatDate pattern="H_m_s" value="${scheduleItem.fromTime}" var="currentStartTime"/>
        <fmt:formatDate pattern="d"     value="${scheduleItem.fromTime}" var="currentDay"/>
        <!-- check is we are are a day boundary and create new table -->
        <c:if test="${empty loopDay or (currentDay != loopDay)}">

            <c:if test="${not empty loopDay}">
            </div>
        </c:if>
        <div class="row">
            <div class="row text-center row-eq-height schedule-header">
                <h2><fmt:formatDate pattern="EEEE MMMM d, yyyy" value="${scheduleItem.fromTime}"/></h2>
            </div>

        </c:if>
        <!-- end check is we are are a day boundary and create new table -->


        <c:choose>
            <c:when test="${scheduleItem.scheduleItemType == scheduleItemTypeRegistration}">
                <div class="row schedule-row">
                    <div class="col-sm-8 col-xs-12 name">
                        <c:out value="${scheduleItem.title}"/><br>
                    </div>
                    
                    <div class="col-sm-2 col-xs-12 text-center">
                        ${scheduleItem.room.name}
                    </div>
                    <div class="col-sm-2 col-xs-12 text-center">
                        <span class="time"><fmt:formatDate pattern="hh:mm" value="${scheduleItem.fromTime}" /></span> - <span class="time"><fmt:formatDate pattern="hh:mm" value="${scheduleItem.toTime}" /></span>
                    </div>
                </div>
            </c:when>
            <c:when test="${scheduleItem.scheduleItemType == scheduleItemTypeAdminsitrative}">
                <div class="row schedule-row">
                    <div class="col-sm-8 col-xs-12 name">
                        <c:out value="${scheduleItem.title}"/><br>
                    </div>
                    
                    <div class="col-sm-2 col-xs-12 text-center">
                        ${scheduleItem.room.name}
                    </div>
                    <div class="col-sm-2 col-xs-12 text-center">
                        <span class="time"><fmt:formatDate pattern="hh:mm" value="${scheduleItem.fromTime}" /></span> - <span class="time"><fmt:formatDate pattern="hh:mm" value="${scheduleItem.toTime}" /></span>
                    </div>
                </div>
            </c:when>
            <c:when test="${scheduleItem.scheduleItemType == scheduleItemTypeBreak}">
                <div class="row schedule-row">
                    <div class="col-sm-8 col-xs-12 name">
                        <c:out value="${scheduleItem.title}"/><br>
                    </div>
                    
                    <div class="col-sm-2 col-xs-12 text-center">
                        ${scheduleItem.room.name}
                    </div>
                    <div class="col-sm-2 col-xs-12 text-center">
                        <span class="time"><fmt:formatDate pattern="hh:mm" value="${scheduleItem.fromTime}" /></span> - <span class="time"><fmt:formatDate pattern="hh:mm" value="${scheduleItem.toTime}" /></span>
                    </div>
                </div>
            </c:when>
            <c:when test="${scheduleItem.scheduleItemType == scheduleItemTypeSession}">

                <c:if test="${scheduleItem.rowspan > 1}">
                    </div>
                    <div class="row">
                    <div class=" breakouts row text-center row-eq-height schedule-header">
                        <h2>Breakouts <fmt:formatDate pattern="hh:mm a" value="${scheduleItem.fromTime}"/></h2>
                    </div>
                    
                </c:if>

                <div class="row schedule-row">
                    <div class="col-sm-6 col-xs-12 name">
                        <c:choose>
                            <c:when test="${not empty scheduleItem.presentation}">
                                <c:url var="presentationUrl" value="${baseSiteUrl}/presentations#id-${scheduleItem.presentation.id}"/>
                                <a href="${presentationUrl}"><c:out value="${scheduleItem.presentation.title}"/></a>
                            </c:when>
                            <c:otherwise>
                                <c:out value="${scheduleItem.title}" default="N/A"/>
                            </c:otherwise>
                        </c:choose><br>
                        <span class="small">
                            <c:choose>
                                <c:when test="${not empty scheduleItem.presentation.speakers}">
                                    <c:forEach var="speaker" items="${scheduleItem.presentation.speakers}" varStatus="speakerStatus">
                                        <c:if test="${speakerStatus.index ne 0}">,</c:if>

                                        ${speaker.firstName} ${speaker.lastName}
                                    </c:forEach>
                                </c:when>
                                <c:otherwise>
                                    N/A
                                </c:otherwise>
                            </c:choose>
                        </span>
                    </div>
                    <div class="col-sm-2 col-xs-12 text-center">
                        <c:if test="${not empty scheduleItem.room.track}">
                            <p><c:out value="${scheduleItem.room.track}"/></p>
                        </c:if>
                    </div>
                    <div class="col-sm-2 col-xs-12 text-center">
                        ${scheduleItem.room.name}
                    </div>
                    <div class="col-sm-2 col-xs-12 text-center">
                        <span class="time"><fmt:formatDate pattern="hh:mm" value="${scheduleItem.fromTime}" /></span> - <span class="time"><fmt:formatDate pattern="hh:mm" value="${scheduleItem.toTime}" /></span>
                    </div>
                </div>

            </c:when>
            <c:when test="${scheduleItem.scheduleItemType == scheduleItemTypeKeynote}">
                
                <div class="row schedule-row keynote">
                    <div class="col-sm-6 col-xs-12 name">
                        <c:choose>
                            <c:when test="${not empty scheduleItem.presentation}">
                                <c:url var="presentationUrl" value="${baseSiteUrl}/presentations#id-${scheduleItem.presentation.id}"/>
                                <a href="${presentationUrl}"><c:out value="${scheduleItem.presentation.title}"/></a>
                            </c:when>
                            <c:otherwise>
                                <c:out value="${scheduleItem.title}" default="N/A"/>
                            </c:otherwise>
                        </c:choose><br>
                        <span class="small">
                            <c:choose>
                                <c:when test="${not empty scheduleItem.presentation.speakers}">
                                    <c:forEach var="speaker" items="${scheduleItem.presentation.speakers}" varStatus="speakerStatus">
                                        <c:if test="${speakerStatus.index ne 0}">,</c:if>

                                        ${speaker.firstName} ${speaker.lastName}
                                    </c:forEach>
                                </c:when>
                                <c:otherwise>
                                    N/A
                                </c:otherwise>
                            </c:choose>
                        </span>
                    </div>
                    <div class="col-sm-2 col-xs-12 text-center">
                        <c:if test="${not empty scheduleItem.room.track}">
                            <p><c:out value="${scheduleItem.room.track}"/></p>
                        </c:if>
                    </div>
                    <div class="col-sm-2 col-xs-12 text-center">
                        ${scheduleItem.room.name}
                    </div>
                    <div class="col-sm-2 col-xs-12 text-center">
                        <span class="time"><fmt:formatDate pattern="hh:mm" value="${scheduleItem.fromTime}" /></span> - <span class="time"><fmt:formatDate pattern="hh:mm" value="${scheduleItem.toTime}" /></span>
                    </div>
                </div>

            </c:when>
        </c:choose>


        <c:set var="loopDay" value="${currentDay}" />

    </c:forEach>

    <c:if test="${not empty loopDay}">
    </div>
</c:if>


<div class="row">
    <div class="row text-center row-eq-height schedule-header">
        <h2>BREAKOUT #1</h2>
    </div>

    <div class="row schedule-row">
        <div class="col-sm-6 col-xs-12 name">
            FirstName LastName<br>
            <span class="small">Name of Presentaion</span>
        </div>
        <div class="col-sm-2 col-xs-12 text-center">
            Track
        </div>
        <div class="col-sm-2 col-xs-12 text-center">
            Room Location
        </div>
        <div class="col-sm-2 col-xs-12 text-center">
            00:00AM - 00:00AM
        </div>
    </div>
    <div class="row schedule-row">
        <div class="col-sm-6 col-xs-12 name">
            FirstName LastName<br>
            <span class="small">Name of Presentaion</span>
        </div>
        <div class="col-sm-2 col-xs-12 text-center">
            Track
        </div>
        <div class="col-sm-2 col-xs-12 text-center">
            Room Location
        </div>
        <div class="col-sm-2 col-xs-12 text-center">
            00:00AM - 00:00AM
        </div>
    </div>
    <div class="row schedule-row">
        <div class="col-sm-6 col-xs-12 name">
            FirstName LastName<br>
            <span class="small">Name of Presentaion</span>
        </div>
        <div class="col-sm-2 col-xs-12 text-center">
            Track
        </div>
        <div class="col-sm-2 col-xs-12 text-center">
            Room Location
        </div>
        <div class="col-sm-2 col-xs-12 text-center">
            00:00AM - 00:00AM
        </div>
    </div>
</div>

</section>
