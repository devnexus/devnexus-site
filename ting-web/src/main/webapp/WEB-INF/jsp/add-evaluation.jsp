<%@ include file="/WEB-INF/jsp/includes/taglibs.jsp"%>

<div id="content" class="span-22 prepend-top last ">

	<h2>We'd love to hear what you think about DevNexus!</h2>
	<form:form id="form" method="post" modelAttribute="evaluation"
		cssClass="cleanform">

		<h3 style="text-align: center; margin-top: 50px">How likely are you to recommend DevNexus to a friend or colleague?</h3>
		<div style="text-align: center; margin: 20px auto 20px auto; height: 30px; width: 400px;">
			<input type="range" min="0" max="10" value="0" step="1" id="rating" name="rating"/>
			<div style="float: left;">Not likely at all</div>
			<div style="float: left; margin-left: 10px; margin-right: 10px" class="rateit" data-rateit-backingfld="#rating"></div>
			<div style="float: left;">Extremely likely</div>
			<form:errors path="rating" cssClass="fieldError" />
		</div>
		<h3 style="text-align: center; clear: left; margin-top: 20px">Please let us know the main reasons you provided the score above.</h3>
		<div style="text-align: center; margin: 20px auto 20px auto; width: 400px;">
			<form:textarea path="comment" rows="10"/>
			<form:errors path="comment" cssClass="fieldError" />
			<input type="submit" class="button" name="save" value="Submit" style="margin-top: 20px"/>
		</div>
	</form:form>
	<content tag='bottom'>
		<script type="text/javascript">
			$("#rateit").bind('over', function (event,value) { $(this).attr('title', value); });

		    $(function() {
		        $( "button, input:submit").button();
		    });
		</script>
	</content>

</div>
