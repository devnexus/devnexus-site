<%@page import="com.devnexus.ting.core.model.ScheduleItemType" %>
<%@ include file="/WEB-INF/jsp/includes/taglibs.jsp" %>
<% pageContext.setAttribute("scheduleItemTypeAdminsitrative", ScheduleItemType.ADMINISTRATIVE); %>
<% pageContext.setAttribute("scheduleItemTypeBreak", ScheduleItemType.BREAK); %>
<% pageContext.setAttribute("scheduleItemTypeKeynote", ScheduleItemType.KEYNOTE); %>
<% pageContext.setAttribute("scheduleItemTypeRegistration", ScheduleItemType.REGISTRATION); %>
<% pageContext.setAttribute("scheduleItemTypeSession", ScheduleItemType.SESSION); %>
<head>
    <title>DevNexus 2013 - Schedule</title>
</head>
<div class="container">

    <c:forEach items="${scheduleItemList.days}" var="date" varStatus="dateStatus">

        <!-- Example row of columns -->
        <h1 class="center">Day ${dateStatus.index}</h1>

        <div id="h4wrap"><h4><fmt:formatDate pattern="EEEE MMMM d, yyyy" value="${date}"/></h4></div>
        <div id="sessions">
            <div class="row">
                <c:forEach items="${scheduleItemList.findHeaderItemsOnDate(date)}" var="headerItem"
                           varStatus="headerStatus">
                    <div class="col-md-2">
                        <div class="row">
                            <div class="col-md-12"><h3 id="green">${headerItem.title}</h3></div>
                        </div>
                        <div id="botttom" class="row">
                            <div class="col-md-12">
                                <h4><fmt:formatDate pattern="h:mm" value="${headerItem.fromTime}"/>-<fmt:formatDate
                                        pattern="h:mm" value="${headerItem.toTime}"/><span id="small"><fmt:formatDate
                                        pattern="a" value="${headerItem.toTime}"/></span></h4>

                                <p>${headerItem.room.name}</p>
                            </div>
                        </div>
                    </div>
                </c:forEach>
                <div class="col-md-2">
                    <div class="row">
                        <div class="col-md-12"><h3 id="green">Lunch</div>
                    </div>
                    <div id="botttom" class="row">
                        <div class="col-md-12">
                            <h4>11:45-12:30<span id="small">pm</span></h4>

                            <p>Ballroom CDF</p>
                        </div>
                    </div>
                </div>
                <div class="col-md-2">
                    <div class="row">
                        <div class="col-md-12"><h3 id="green">Desert</div>
                    </div>
                    <div id="botttom" class="row">
                        <div class="col-md-12">
                            <h4>12:30-1:00<span id="small">pm</span></h4>

                            <p>Ballroom CDF</p>
                        </div>
                    </div>
                </div>
                <div class="col-md-2">
                    <div class="row">
                        <div class="col-md-12">
                            <h3 id="green">15 <span id="small">min</span>
                                <h4>10:15 <span id="small">am</span></h4>
                                <h4>2:15 <span id="small">pm</span></h4>
                        </div>
                    </div>
                    <div id="botttom" class="row">
                        <div class="col-md-12">
                            <h4>3:45 <span id="small">pm</span></h4>
                            <h4>5:15 <span id="small">pm</span></h4>

                            <p>Galleria Atrium</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div id="h4wrap"><h4>Breakouts</h4></div>
        <div id="schedule">
            <c:forEach items="${scheduleItemList.findRooms(date)}" var="room" varStatus="roomStatus">
                <c:if test="${roomStatus.index%4 == 0}">
                    <div class="row">
                </c:if>
                <div class="col-md-3">
                    <div id="one-fourth" class="${room.cssStyleName}">
                        <h3>${room.track}<br/>${room.name}</h3>
                        <c:forEach items="${scheduleItemList.findBreakoutItemsOnDateInRoom(date, room)}" var="session"
                                   varStatus="sessionStatus">
                            <p><strong><fmt:formatDate pattern="h:mm" value="${session.fromTime}"/>-<fmt:formatDate
                                    pattern="h:mm" value="${session.toTime}"/> <fmt:formatDate pattern="a"
                                                                                               value="${session.toTime}"/></strong><br/>
                                <c:choose>
                                    <c:when test="${not empty session.presentation}">
                                        <c:url var="presentationUrl"
                                               value="${baseSiteUrl}/presentations#id-${session.presentation.id}"/>
                                        <a href="${presentationUrl}"><c:out value="${session.presentation.title}"/></a>
                                    </c:when>
                                    <c:otherwise>
                                        <c:out value="${session.title}" default="N/A"/>
                                    </c:otherwise>
                                </c:choose><br/>${session.presentation.speaker.firstName} ${session.presentation.speaker.lastName}
                            </p>
                        </c:forEach>
                    </div>
                </div>
                <c:if test="${roomStatus.index%4 == 3 or roomStatus.last}">
                    </div>
                </c:if>
            </c:forEach>

        </div>


    </c:forEach>


</div>
<!-- /container -->


<%--<c:forEach items="${scheduleItemList.scheduleItems}" var="scheduleItem">--%>

<%--<fmt:formatDate pattern="H_m_s" value="${scheduleItem.fromTime}" var="currentStartTime"/>--%>
<%--<fmt:formatDate pattern="d"     value="${scheduleItem.fromTime}" var="currentDay"/>--%>

<%--<c:if test="${empty loopDay or (currentDay != loopDay)}">--%>

<%--<c:if test="${not empty loopDay}">--%>
<%--</table>--%>
<%--</c:if>--%>
<%--<table style="border-collapse: collapse; margin-bottom: 1em;" class="schedule ">--%>
<%--<tr>--%>
<%--<th colspan="5" class="day"><fmt:formatDate pattern="EEEE MMMM d, yyyy" value="${scheduleItem.fromTime}"/></th>--%>
<%--</tr>--%>
<%--<tr>--%>
<%--<th>Start</th>--%>
<%--<th>End</th>--%>
<%--<th>Session</th>--%>
<%--<th>Room/Track</th>--%>
<%--<th>Session</th>--%>
<%--</tr>--%>
<%--</c:if>--%>

<%--<tr>--%>
<%--<c:choose>--%>
<%--<c:when test="${currentStartTime ne loopStartTime}">--%>
<%--<c:set value="${currentStartTime}" var="loopStartTime"/>--%>
<%--<c:choose>--%>
<%--<c:when test="${scheduleItem.rowspan > 1}">--%>
<%--<td class="time" rowspan="${scheduleItem.rowspan}"><fmt:formatDate pattern="hh:mm" value="${scheduleItem.fromTime}" /></td>--%>
<%--<td class="time" rowspan="${scheduleItem.rowspan}"><fmt:formatDate pattern="hh:mm" value="${scheduleItem.toTime}" /></td>--%>
<%--</c:when>--%>
<%--<c:otherwise>--%>
<%--<td class="time"><fmt:formatDate pattern="hh:mm" value="${scheduleItem.fromTime}" /></td>--%>
<%--<td class="time"><fmt:formatDate pattern="hh:mm" value="${scheduleItem.toTime}" /></td>--%>
<%--</c:otherwise>--%>
<%--</c:choose>--%>
<%--</c:when>--%>
<%--</c:choose>--%>
<%--<c:choose>--%>
<%--<c:when test="${scheduleItem.scheduleItemType == scheduleItemTypeRegistration}">--%>
<%--<td colspan="3" class="registration"><c:out value="${scheduleItem.title} [${scheduleItem.room.name}]"/></td>--%>
<%--</c:when>--%>
<%--<c:when test="${scheduleItem.scheduleItemType == scheduleItemTypeAdminsitrative}">--%>
<%--<td colspan="3" class="keynote"><c:out value="${scheduleItem.title} [${scheduleItem.room.name}]"/></td>--%>
<%--</c:when>--%>
<%--<c:when test="${scheduleItem.scheduleItemType == scheduleItemTypeBreak}">--%>
<%--<td colspan="3" class="break"><c:out value="${scheduleItem.title}" default="Break"/> [${scheduleItem.room.name}]</td>--%>
<%--</c:when>--%>
<%--<c:when test="${scheduleItem.scheduleItemType == scheduleItemTypeSession}">--%>
<%--<c:if test="${scheduleItem.rowspan > 1}">--%>
<%--<td class="breakouts" rowspan="${scheduleItem.rowspan}">Breakouts</td>--%>
<%--</c:if>--%>

<%--<td class="${scheduleItem.room.cssStyleName}">--%>
<%--<p><strong>${scheduleItem.room.name}</strong></p>--%>
<%--<c:if test="${not empty scheduleItem.room.track}">--%>
<%--<p><c:out value="${scheduleItem.room.track}"/></p>--%>
<%--</c:if>--%>
<%--</td>--%>
<%--<td class="talk">--%>
<%--<p class="topic">--%>
<%--<c:choose>--%>
<%--<c:when test="${not empty scheduleItem.presentation}">--%>
<%--<c:url var="presentationUrl" value="${baseSiteUrl}/presentations#id-${scheduleItem.presentation.id}"/>--%>
<%--<a href="${presentationUrl}"><c:out value="${scheduleItem.presentation.title}"/></a>--%>
<%--</c:when>--%>
<%--<c:otherwise>--%>
<%--<c:out value="${scheduleItem.title}" default="N/A"/>--%>
<%--</c:otherwise>--%>
<%--</c:choose>--%>
<%--</p>--%>
<%--<p class="speaker">--%>
<%--<c:choose>--%>
<%--<c:when test="${not empty scheduleItem.presentation}">--%>
<%--<c:url var="speakerUrl" value="${baseSiteUrl}/speakers"/>--%>
<%--<a href="${speakerUrl}#${scheduleItem.presentation.speaker.firstName}_${scheduleItem.presentation.speaker.lastName}"><c:out value="${scheduleItem.presentation.speaker.firstLastName}"/></a>--%>
<%--</c:when>--%>
<%--<c:otherwise>--%>
<%--N/A--%>
<%--</c:otherwise>--%>
<%--</c:choose>--%>
<%--</p>--%>
<%--</td>--%>
<%--</c:when>--%>
<%--<c:when test="${scheduleItem.scheduleItemType == scheduleItemTypeKeynote}">--%>
<%--<td class="keynote">Keynote</td>--%>
<%--<td class="keynote">${scheduleItem.room.name}</td>--%>
<%--<td class="talk">--%>
<%--<p class="topic">--%>
<%--<c:choose>--%>
<%--<c:when test="${not empty scheduleItem.presentation}">--%>
<%--<c:url var="presentationUrl" value="${baseSiteUrl}/presentations#id-${scheduleItem.presentation.id}"/>--%>
<%--<a href="${presentationUrl}"><c:out value="${scheduleItem.presentation.title}"/></a>--%>
<%--</c:when>--%>
<%--<c:otherwise>--%>
<%--<c:out value="${scheduleItem.title}" default="N/A"/>--%>
<%--</c:otherwise>--%>
<%--</c:choose>--%>
<%--</p>--%>
<%--<p class="speaker">--%>
<%--<c:choose>--%>
<%--<c:when test="${not empty scheduleItem.presentation}">--%>
<%--<c:url var="speakerUrl" value="${baseSiteUrl}/speakers#${scheduleItem.presentation.speaker.firstName}_${scheduleItem.presentation.speaker.lastName}"/>--%>
<%--<a href="${speakerUrl}"><c:out value="${scheduleItem.presentation.speaker.firstLastName}"/></a>--%>
<%--</c:when>--%>
<%--<c:otherwise>--%>
<%--N/A--%>
<%--</c:otherwise>--%>
<%--</c:choose>--%>
<%--</p>--%>
<%--</td>--%>
<%--</c:when>--%>
<%--</c:choose>--%>
<%--</tr>--%>

<%--<c:set var="loopDay" value="${currentDay}" />--%>

<%--</c:forEach>--%>
