<%@ include file="/WEB-INF/jsp/includes/taglibs.jsp"%>
<% pageContext.setAttribute("lf", "\n"); %>

        <!-- Start of first page -->
        <div data-role="page" id="speakers" data-theme="b">

            <div data-role="header">
                <a rel="external" href="${ctx}${baseSiteUrl}/index">Back</a>
                <h1>DevNexus 2013</h1>
            </div><!-- /header -->

            <div data-role="content">
                <ul data-role="listview" data-inset="true" data-theme="c" data-dividertheme="b">
                    <li data-role="list-divider">Speakers</li>
                        <c:forEach items="${speakerList.speakers}" var="speaker">
                            <li><a href="#${speaker.firstName}_${speaker.lastName}">
                                <img src="${ctx}${baseSiteUrl}/speakers/${speaker.id}.jpg"/>
                                <c:out value="${speaker.firstName}"/> <c:out value="${speaker.lastName}"/></a>
                            </li>
                        </c:forEach>
                </ul>
            </div><!-- /content -->

            <div data-role="footer">
                <h4>&copy; 2013 AJUG</h4>
            </div><!-- /header -->
        </div><!-- /page -->

        <c:forEach items="${speakerList.speakers}" var="speaker">
            <!-- Start of page -->
            <div data-role="page" id="${speaker.firstName}_${speaker.lastName}" data-theme="b">

                <div data-role="header">
                    <a href="#speakers">Back</a>
                    <h1><c:out value="${speaker.firstName}"/> <c:out value="${speaker.lastName}"/></h1>
                </div><!-- /header -->

                <div data-role="content">
                    <img src="${ctx}${baseSiteUrl}/speakers/${speaker.id}.jpg"/>
                    <p>
                        <c:out value="${speaker.bioAsHtml}" escapeXml="false"/>
                    </p>

                    <c:if test="${!empty speaker.presentations}">
                      <ul data-role="listview" data-inset="true" data-theme="c" data-dividertheme="b">
                      <li data-role="list-divider">Presentation(s)</li>
                          <c:forEach items="${speaker.presentations}" var="presentation">
                                <li><a rel="external" href="${ctx}${baseSiteUrl}/presentations#id-${presentation.id}"><c:out value="${presentation.title}"/></a></li>
                          </c:forEach>
                      </ul>
                  </c:if>
                </div><!-- /content -->

                <div data-role="footer">
                    <h4>&copy; 2013 AJUG</h4>
                </div><!-- /header -->
            </div><!-- /page -->
        </c:forEach>