<%@ include file="/WEB-INF/jsp/includes/taglibs.jsp"%>
<div class="jumbotron eval" style="margin-bottom:50px">
	<div class="container">
		<div id="banner">
			<h1><strong>We'd love to hear what you think about DevNexus!</strong></h1>
		</div> <!-- end banner -->
	</div>
</div>

<div class="row">
	<div class="col-md-6 col-md-offset-3">


		<spring:bind path="evaluation.*">
			<c:if test="${not empty status.errorMessages}">
				<div class="alert alert-danger fade in"
					><a href="#" data-dismiss="alert" class="close">&times;</a>
					<c:forEach var="error" items="${status.errorMessages}"
						><c:out value="${error}" escapeXml="false"/><br/>
					</c:forEach>
				</div>
			</c:if>
		</spring:bind>

		<form:form id="form" method="post" modelAttribute="evaluation">
			<form:hidden path="event.id"/>
			<div class="form-group text-center">
				<h3>How likely are you to recommend DevNexus to a friend or colleague?</h3>
				<div class="stars">
					<input type="hidden" id="rating" name="rating"/>
					<div id="raty"></div>
					<form:errors path="rating" cssClass="fieldError"/>
				</div>
			</div>
			<div class="form-group text-center">
				<h3>Please let us know the main reasons you provided the score above.</h3>
				<form:textarea cssClass="form-control" path="comment" id="slotPreference" tabindex="2" rows="5" maxLength="1000"/>
				<form:errors path="comment" cssClass="fieldError" />
			</div>

			<c:if test="${reCaptchaEnabled}">
				<div class="form-group text-center">
					<h3>Are you human?</h3>
					<div class="col-lg-12" style="margin-bottom: 1em; margin-left: auto;">
						<c:out value="${reCaptchaHtml}" escapeXml="false"/>
					</div>
				</div>
			</c:if>

			<div class="form-group text-center">
				<div class="col-lg-12">
				<button type="submit" class="btn btn-default" name="cancel" tabindex="20">Cancel</button>
				<input type="submit" class="btn btn-primary form" name="save" value="Submit"/>
				</div>
			</div>

		</form:form>
	</div>
</div>

<content tag='bottom'>
	<script src="${ctx}/js/bootstrap-maxlength.min.js"></script>
	<script src="${ctx}/js/jquery.raty.js"></script>
	<script type="text/javascript">
		$(document).ready(function() {

			$("textarea", document.forms['cfpForm']).focus();

			$('textarea').maxlength({
				alwaysShow: true
			});

			$('#raty').raty({
				number: 10,
				size: 27,
				target : '#rating',
				targetKeep  : true,
				hints: ['lousy', 'pretty bad', 'poor', 'meh' , 'average', 'ok', 'good', 'very good', 'awesome'],
				targetType: 'score',
				starOff : '${ctx}/images/staroff.png',
				starOn  : '${ctx}/images/staron.png'
				});

		});
	</script>
</content>

