<%@ include file="/WEB-INF/jsp/includes/taglibs.jsp"%>
<% pageContext.setAttribute("lf", "\n"); %>

<title>DevNexus 2013 - Organizers</title>

<div style="margin-top: 20px" class="col-md-10 col-md-offset-1">
	<h2>Evaluations</h2>

	<c:forEach items="${evaluationList.evaluations}" var="evaluation">
		<div class="panel panel-default evaluation">
			<div class="panel-heading">
				Rating: <c:out value="${evaluation.rating}" />, ${evaluation.id}
				<div class="btn-group pull-right"
				><a class="delete-evaluation" href="#" class="btn btn-link btn-xs"
					><span class="glyphicon glyphicon-remove"></span></a>
					</div>
			</div>
			<div class="panel-body">
				<c:out value="${evaluation.commentAsHtml}"/>
			</div>
			<div class="panel-footer"><small>Added <fmt:formatDate pattern="yyyy-MM-dd'T'HH:mm:ssz" value="${evaluation.createdDate}"/></small></div>
		</div>
	</c:forEach>
</div>

<content tag='bottom'>
	<script type="text/javascript">
		$(document).ready(function() {
			$("body").on('click',".delete-evaluation", function(event) {
				event.preventDefault();
				var eval = $(this).closest(".evaluation");

				eval.fadeOut(function(ele) {
					console.log(this);
					this.remove();
				});

				return false;
			});
		});
	</script>
</content>
