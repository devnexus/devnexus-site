<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/includes/taglibs.jsp"%>
<% pageContext.setAttribute("lf", "\n"); %>

<title>Manage Evaluations</title>

<!-- intro -->
<section id="about" class="module parallax parallax-3">
	<div class="container header">
		<div class="row centered">
			<div class="col-md-10 col-md-offset-1">
				<div class="top-intro travel">
					<h4 class="section-white-title decorated"><span>Evaluations</span></h4>
					<h5 class="intro-white-lead">${event.title}
					<c:if test="${event.current}">
						(current)
					</c:if>
					</h5>
				</div>
			</div>
		</div>
	</div>
</section>
<!-- /intro -->

<div class="row">
	<div class="col-md-8 col-md-offset-2">

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
					<c:out escapeXml="false" value="${evaluation.commentAsHtml}"/>
				</div>
				<div class="panel-footer"><small>Added <fmt:formatDate pattern="yyyy-MM-dd'T'HH:mm:ssz" value="${evaluation.createdDate}"/></small></div>
			</div>
		</c:forEach>
		<a class="btn btn-default" href="${ctx}${baseSiteUrl}/admin/${eventKey}/index"
				role="button">Main Menu</a>
	</div>
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
