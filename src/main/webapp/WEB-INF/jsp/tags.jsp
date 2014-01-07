<%@ include file="/WEB-INF/jsp/includes/taglibs.jsp"%>
<% pageContext.setAttribute("lf", "\n"); %>

<title>Tags</title>

<div style="margin-top: 20px" class="col-md-10 col-md-offset-1">
	<h2>Tags</h2>
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
					<td><c:out value="${entry.key.name}" /></td>
					<td><c:out value="${entry.value}" /></td>
				</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</div>

