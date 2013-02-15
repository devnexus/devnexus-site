<%@ include file="/WEB-INF/jsp/includes/taglibs.jsp"%>

<div id="content" class="span-22 prepend-top last ">

	<h2>Thank You!</h2>
	<p>We would like to thank you for submitting your comments.</p>
	<div style="text-align: center; margin: 20px auto 20px auto; width: 400px;">
		<a href="${ctx}${baseSiteUrl}/index" class="button">Continue</a>
	</div>
	<content tag='bottom'>
		<script type="text/javascript">
		    $(function() {
		        $( "a.button").button();
		    });
		</script>
	</content>

</div>
