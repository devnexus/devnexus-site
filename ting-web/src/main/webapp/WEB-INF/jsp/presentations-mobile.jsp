<%@ include file="/WEB-INF/jsp/includes/taglibs.jsp"%>
<% pageContext.setAttribute("lf", "\n"); %>

        <!-- Start of first page -->
        <div data-role="page" id="presentations" data-theme="b">

            <div data-role="header">
                <a rel="external" href="${ctx}/s/index">Back</a>
                <h1>DevNexus 2011</h1>
            </div><!-- /header -->

            <div data-role="content">
                <ul data-role="listview" data-inset="true" data-theme="c" data-dividertheme="b">
                    <li data-role="list-divider">Presentations</li>
                        <c:forEach items="${presentationList.presentations}" var="presentation">
                            <li>
                                <a href="#${presentation.id}"><c:out value="${presentation.title}"/></a>
                            </li>
                        </c:forEach>
                </ul>
            </div><!-- /content -->

            <div data-role="footer">
                <h4>&copy; 2011 AJUG</h4>
            </div><!-- /header -->
        </div><!-- /page -->

         <c:forEach items="${presentationList.presentations}" var="presentation">
            <!-- Start of page -->
            <div data-role="page" id="${presentation.id}" data-theme="b">

                <div data-role="header">
                    <h1><c:out value="${presentation.title}"/></h1>
                </div><!-- /header -->

                <div data-role="content">
		            <c:choose>
		                <c:when test="${not empty presentation.speaker}">
		                    <p class="speaker">
		                        <a rel="external" href="${ctx}/s/speakers#${presentation.speaker.firstName}_${presentation.speaker.lastName}">
		                            <c:out value="${presentation.speaker.firstName}"/>
		                            <c:out value="${presentation.speaker.lastName}"/></a>
		                    </p>
		                </c:when>
		                <c:otherwise>
		                    <p class="speaker">TBD</p>
		                </c:otherwise>
		            </c:choose>
                    <p>
		                <c:set var="presentationDescription"><c:out value="${presentation.description}" escapeXml="true"/></c:set>
		                <c:out value="${fn:replace(presentationDescription, lf, '<br/>')}" escapeXml="false"/>
                    </p>
                </div><!-- /content -->

                <div data-role="footer">
                    <h4>&copy; 2011 AJUG</h4>
                </div><!-- /header -->
            </div><!-- /page -->
        </c:forEach>
