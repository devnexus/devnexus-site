<%@ include file="/WEB-INF/jsp/includes/taglibs.jsp"%>
<% pageContext.setAttribute("lf", "\n"); %>

		<!-- Start of first page -->
		<div data-role="page" id="organizers" data-theme="b">

		    <div data-role="header">
		        <a rel="external" href="${ctx}/s/index">Back</a>
		        <h1>DevNexus 2011</h1>
		    </div><!-- /header -->

		    <div data-role="content">
		        <ul ul data-role="listview" data-inset="true">
		            <li>Tweets</li>
			            <c:forEach items="${tweets}" var="tweet">
			                <li>
                                <img alt="${tweet.fromUser}" title="${tweet.fromUser}" src="${tweet.profileImageUrl}" style="margin: 5px 5px 5px 0px; position: relative;"/>
			                    <div style="margin-left: 80px;"><c:out value="${tweet.createdAt}"/>
			                    <c:out value="${tweet.text}"/></div>
			                </li>
			            </c:forEach>
		        </ul>
		    </div><!-- /content -->

		    <div data-role="footer">
		        <h4>&copy; 2011 AJUG</h4>
		    </div><!-- /header -->
		</div><!-- /page -->

