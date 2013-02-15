<%@ include file="/WEB-INF/jsp/includes/taglibs.jsp"%>
<%
	pageContext.setAttribute("lf", "\n");
%>

<!-- Start of first page -->
<div data-role="page" id="presentations" data-theme="b">

	<div data-role="header">
		<h1>Thank You!</h1>
	</div>
	<!-- /header -->

	<div data-role="content">
			<h4>
				We would like to thank you for submitting your comments.
			</h4>
			<a href="${ctx}${baseSiteUrl}/index" data-role="button">Continue</a>
	</div>

	<!-- /content -->

	<div data-role="footer">
		<h4>&copy; 2013 AJUG</h4>
	</div>

	<!-- /header -->
</div>
<!-- /page -->