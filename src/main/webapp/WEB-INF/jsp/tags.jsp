<%@ include file="/WEB-INF/jsp/includes/taglibs.jsp"%>
<% pageContext.setAttribute("lf", "\n"); %>

<title>Tags</title>

<div style="margin-top: 20px" class="col-md-10 col-md-offset-1">
	<h2>Tags</h2>
</div>

<div class="row">
	<div class="col-md-10 col-md-offset-1">
		<div id="tagCloud" style="margin-bottom: 20px; text-align: center;">
			<c:forEach items="${tagCloud}" var="entry">
				<c:url value="/s/presentations" var="showPresosForTags">
					<c:param name="tags" value="${entry.key.name}"/>
				</c:url>
				<a href="${showPresosForTags}" rel="<c:out value="${entry.value}" />"><c:out value="${entry.key.name}" /></a>
			</c:forEach>
		</div>
	</div>
</div>
<div class="row">
	<div class="col-md-10 col-md-offset-1">
		<table class="table table-hover">
			<thead>
				<tr><th>Tag</th><th>Count</th></tr>
			</thead>
			<tbody>
				<c:forEach items="${tagCloud}" var="entry">
				<tr>
					<c:url value="/s/presentations" var="showPresosForTags">
						<c:param name="tags" value="${entry.key.name}"/>
					</c:url>
					<td><a href="${showPresosForTags}"><c:out value="${entry.key.name}" /></a></td>
					<td><c:out value="${entry.value}" /></td>
				</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</div>
<content tag='bottom'>

		<script src="${ctx}/js/jquery.tagcloud.js"></script>
		<script type="text/javascript">

			$(document).ready(function() {
				$.fn.tagcloud.defaults = {
						  size: {start: 14, end: 24, unit: 'pt'},
						  color: {start: '#cde', end: '#f52'}
						};

						$(function () {
						  $('#tagCloud a').tagcloud();
						});
			});
		</script>
</content>
