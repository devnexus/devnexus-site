<%@ include file="/WEB-INF/jsp/includes/taglibs.jsp"%>
<%
	pageContext.setAttribute("lf", "\n");
%>



<!-- Start of first page -->
<div data-role="page" id="evaluations" data-theme="b">

	<div data-role="header">
		<a rel="external" href="${ctx}${baseSiteUrl}/index">Back</a>
		<h1>Feedback</h1>
	</div>
	<!-- /header -->

	<div data-role="content">
		<form:form id="form" method="post" modelAttribute="evaluation">
			<h4>
				How likely are you to recommend DevNexus to a friend or colleague?
			</h4>
			<div data-role="fieldcontain">
				<fieldset data-role="controlgroup">
					<input data-role="none" type="range" min="0" max="10" name="rating" value="0" step="1" id="rating">
					<div class="rateit bigstars"
					data-rateit-resetable="false"
					data-rateit-starwidth="24"
					data-rateit-starheight="24"
					data-rateit-backingfld="#rating"></div>
				</fieldset>
			</div>
			<h4>
				Please let us know the main reasons you provided the score above.
			</h4>
			<div data-role="fieldcontain">
				<textarea name="comment" id="textarea1" placeholder="" rows="10"></textarea>
			</div>
			<input type="submit" data-icon="plus" data-iconpos="right" value="Submit" />
		</form:form>
	</div>
	<!-- /content -->

	<div data-role="footer">
		<h4>&copy; 2013 AJUG</h4>
	</div>

	<content tag='bottom'>
		<script src="${ctx}/js/lib/jquery/jquery.rateit.min.js"></script>
	</content>

	<!-- /header -->
</div>
<!-- /page -->