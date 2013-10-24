<%@page import="com.devnexus.ting.core.model.PresentationType" %>
<%@ include file="/WEB-INF/jsp/includes/taglibs.jsp" %>
<% pageContext.setAttribute("lf", "\n"); %>
<% pageContext.setAttribute("keynoteType", PresentationType.KEYNOTE); %>

<title>DevNexus 2013 - Speakers</title>

<div class="container">
    <div id="speaker2">
        <div id="row">
            <c:forEach items="${speakerList.speakers}" var="speaker" varStatus="status">
                <c:if test="${status.first}">
                    <div class="col-md-3">
                </c:if>
                <c:if test="${!status.first && (status.index % columnLength == 0)}">
                    </div><div class="col-md-3">
                </c:if>
                <p><a href="#${speaker.firstName}_${speaker.lastName}"><c:out
                        value="${speaker.firstName}"/> <c:out value="${speaker.lastName}"/></a></p>

                    <c:if test="${status.last}">
                        </div>
                    </c:if>
            </c:forEach>
        </div>
        <div style="clear: both"></div>
        <div id="row">
            <div id="bio">

                <c:forEach items="${speakerList.speakers}" var="speaker" varStatus="status">
                    <c:if test="${status.index%3 == 0}">
                        <br style="clear: both;"/>
                    </c:if>
                    <a style="padding-top: 100px" id="${speaker.firstName}_${speaker.lastName}" name="${speaker.firstName}_${speaker.lastName}"></a>
                    <div class="col-md-4 speakerContainer">
                        <div id="one-third">
                            <c:if test="${speaker.picture != null}">
                                <img class="speaker" src="${ctx}${baseSiteUrl}/speakers/${speaker.id}.jpg"/>
                            </c:if>

                            <h3 >
                                <c:out value="${speaker.firstName}"/>
                                <c:out value="${speaker.lastName}"/>
                            </h3>

                            <div id="social">
                                <ul>
                                    <c:if test="${!empty speaker.twitterId}">
                                        <li id="twitter"><a
                                                href="https://twitter.com/<c:out value="${speaker.twitterId}" />"
                                                ><img class="social" alt="<c:out
                                            value='${speaker.googlePlusId}' />"
                                                      src="${ctx}/img/icons/icondock/24px/twitter.png"/>@<c:out
                                                value="${speaker.twitterId}"/></a>
                                        </li>
                                    </c:if>

                                    <c:if test="${!empty speaker.googlePlusId}">
                                        <li id="google"><a
                                                href="https://plus.google.com/<c:out value="${speaker.googlePlusId}" />"><img
                                                class="social" alt="<c:out
                                            value='${speaker.googlePlusId}' />"
                                                src="${ctx}/img/icons/icondock/24px/google-plus.png"/></a></li>
                                    </c:if>
                                </ul>
                            </div>

                            <c:if test="${!empty speaker.presentations}">
                                <c:forEach var="presentation" items="${speaker.presentations}">
                                <p><strong>Presentation:</strong><br/>
                                    <c:if test="${presentation.presentationType == keynoteType}">
                                       (Keynote)
                                    </c:if>
                                    <a href="${siteUrl}/presentations#id-${presentation.id}"><c:out
                                        value="${presentation.title}"/></a><br/>--</p>
                                </c:forEach>
                            </c:if>
                            <p class="detail">
                                <c:out value="${speaker.bioAsHtml}" escapeXml="false"/>
                            </p>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </div>
</div>

	<content tag='bottom'>
		<script type="text/javascript">
			$(document).ready(function() {

				var container = document.querySelector('#bio');
				var msnry = new Masonry( container, {
					columnWidth: 1,
				  'margin-bottom': '10px',
				  itemSelector: '.speakerContainer'
				});

			});
		</script>
	</content>