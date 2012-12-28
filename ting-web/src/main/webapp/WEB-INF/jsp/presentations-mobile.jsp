<%@page import="com.devnexus.ting.core.model.PresentationType"%>
<%@ include file="/WEB-INF/jsp/includes/taglibs.jsp"%>
<% pageContext.setAttribute("lf", "\n"); %>
<% pageContext.setAttribute("keynoteType", PresentationType.KEYNOTE); %>

        <!-- Start of first page -->
        <div data-role="page" id="presentations" data-theme="b">

            <div data-role="header">
                <a rel="external" href="${ctx}${baseSiteUrl}/index">Back</a>
                <h1>DevNexus 2013</h1>
            </div><!-- /header -->

            <div data-role="content">
                <ul data-role="listview" data-inset="true" data-theme="c" data-dividertheme="b">
                    <li data-role="list-divider">Presentations</li>
                        <c:forEach items="${presentationList.presentations}" var="presentation">
                            <li>
                                <a href="#id-${presentation.id}"><c:out value="${presentation.title}"/>
                            <c:if test="${presentation.presentationType == keynoteType}">
							(Keynote)
							</c:if></a></li>
                        </c:forEach>
                </ul>
            </div><!-- /content -->

            <div data-role="footer">
                <h4>&copy; 2012 AJUG</h4>
            </div><!-- /header -->
        </div><!-- /page -->

         <c:forEach items="${presentationList.presentations}" var="presentation">
            <!-- Start of page -->
            <div data-role="page" id="id-${presentation.id}" data-theme="b">

                <div data-role="header">
                    <a href="#presentations">Back</a>
                    <h1><c:out value="${presentation.title}"/></h1>
                </div><!-- /header -->

                <div data-role="content">
                <c:choose>
                    <c:when test="${not empty presentation.speaker}">
                        <p class="speaker">
                            <a rel="external" href="${ctx}${baseSiteUrl}/speakers#${presentation.speaker.firstName}_${presentation.speaker.lastName}">
                                <c:out value="${presentation.speaker.firstName}"/>
                                <c:out value="${presentation.speaker.lastName}"/></a>
                        </p>
                    </c:when>
                    <c:otherwise>
                        <p class="speaker">TBD</p>
                    </c:otherwise>
                </c:choose>
                    <c:out value="${presentation.descriptionAsHtml}" escapeXml="false"/>
                    <c:if test="${not empty presentation.skillLevel}">
					Skill Level: <c:out value="${presentation.skillLevel.name}" />
					</c:if>
                </div><!-- /content -->

                <div data-role="footer">
                    <h4>&copy; 2013 AJUG</h4>
                </div><!-- /header -->
            </div><!-- /page -->
        </c:forEach>
