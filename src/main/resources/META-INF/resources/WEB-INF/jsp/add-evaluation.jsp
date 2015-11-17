<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/includes/taglibs.jsp" %>

<title>${contextEvent.title} | Add Evaluation</title>

<!-- intro -->
<section id="about" class="module parallax parallax-3">
	<div class="container header">
		<div class="row centered">
			<div class="col-md-10 col-md-offset-1">
				<div class="top-intro travel">
					<h4 class="section-white-title decorated"><span>Evaluations</span></h4>
					<h5 class="intro-white-lead">We'd love to hear what you think about DevNexus!</h5>
					<br>
					<ul class="list-inline">
						<c:forEach items="${organizerList.organizers}" var="organizer" varStatus="status">
							<li><a class="speaker-link" href="#${organizer.firstName}_${organizer.lastName}"><c:out
								value="${organizer.firstName}"/> <c:out value="${organizer.lastName}"/></a></li>
						</c:forEach>
					</ul>
				</div>
			</div>
		</div>
	</div>
</section>
<!-- /intro -->

<section id="speaker" class="bg-light-gray">
	<div class="container">

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
				<div class="stars text-center">
					<input type="hidden" id="rating" name="rating"/>
					<div id="raty" style="font-size: 1.0rem; margin-left: auto; margin-right: auto;"></div>
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

			<div class="form-group">
				<div class="col-lg-offset-2 col-lg-10 text-center">
					<button type="submit" class="btn btn-default" name="cancel" tabindex="20">Cancel</button>
					<button type="submit" class="btn btn-primary" lang="save" tabindex="19">Submit</button>
				</div>
			</div>

		</form:form>
	</div>

	</div>
</section>

<content tag='bottom'>
	<script src="${ctx}/assets/js/other/bootstrap-maxlength.min.js"></script>
	<script src="${ctx}/assets/js/jquery-plugins/jquery.raty.js"></script>
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
				hints: ['lousy', 'pretty bad', 'poor', 'meh' , 'average', 'ok', 'good', 'very good', 'awesome', 'it rocks'],
				targetType: 'score',
				starOff : '${ctx}/assets/img/evaluations/staroff.png',
				starOn  : '${ctx}/assets/img/evaluations/staron.png'
				});

		});
	</script>
</content>

