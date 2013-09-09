<%@page import="com.devnexus.ting.core.model.PresentationType"%>
<%@ include file="/WEB-INF/jsp/includes/taglibs.jsp"%>
<% pageContext.setAttribute("lf", "\n"); %>
<% pageContext.setAttribute("keynoteType", PresentationType.KEYNOTE); %>

<title>DevNexus 2013 - Speakers</title>
<link rel="stylesheet" type="text/css" href="${ctx}/css/speakers.css"/>


	<%--<h2>--%>
		<%--Speakers--%>
		<%--<c:if test="${not empty event}">for ${event.title}</c:if>--%>
	<%--</h2>--%>
    <div id="wrapper">
        <ul id="speakers">
            <c:forEach items="${speakerList.speakers}" var="speaker">
                <li><a href="#${speaker.firstName}_${speaker.lastName}"><c:out
                            value="${speaker.firstName}" /> <c:out value="${speaker.lastName}" /></a></li>
            </c:forEach>
        </ul>

    </div>
    <div style="clear: both"></div>
    <div id="wrapper">
        <div id="bio">

            <c:forEach items="${speakerList.speakers}" var="speaker" varStatus="status">
                <c:if test="${status.index%3 == 0}">
                    <br style="clear: both;" />
                </c:if>
                <div class="one-third">
                    <c:if test="${speaker.picture != null}">
                        <img class="speaker" src="${ctx}${baseSiteUrl}/speakers/${speaker.id}.jpg" />
                    </c:if>
                    <h3 id="${speaker.firstName}_${speaker.lastName}">
                        <c:out value="${speaker.firstName}" />
                        <c:out value="${speaker.lastName}" />
                    </h3>

                    <div class="social">
                        <ul>
                            <c:if test="${!empty speaker.twitterId}">
                                <li class="twitter"><a href="https://twitter.com/<c:out value="${speaker.twitterId}" />"
                                        ><img class="social" alt="<c:out
                                            value='${speaker.googlePlusId}' />" src="${ctx}/img/icons/icondock/24px/twitter.png"/>@<c:out
                                        value="${speaker.twitterId}" /></a>
                                </li>
                            </c:if>

                            <c:if test="${!empty speaker.googlePlusId}">
                                <li class="google"><a href="https://plus.google.com/<c:out value="${speaker.googlePlusId}" />"><img class="social" alt="<c:out
                                            value='${speaker.googlePlusId}' />" src="${ctx}/img/icons/icondock/24px/google-plus.png"/></a></li>
                            </c:if>
                        </ul>
                    </div>

                    <c:if test="${!empty speaker.presentations}">
                        <p class="presentation-header">Presentation:</p>
                        <ul>
                            <c:forEach items="${speaker.presentations}" var="presentation">
                                <li><a href="${ctx}/s/presentations#id-${presentation.id}"><c:out
                                        value="${presentation.title}" /></a>
                                    <c:if test="${presentation.presentationType == keynoteType}">
                                        (Keynote)
                                    </c:if>
                                </li>

                            </c:forEach>
                        </ul>
                    </c:if>


                    <p class="detail">
                        <c:out value="${speaker.bioAsHtml}" escapeXml="false" />
                    </p>
                </div>

            </c:forEach>
        </div>
    </div>