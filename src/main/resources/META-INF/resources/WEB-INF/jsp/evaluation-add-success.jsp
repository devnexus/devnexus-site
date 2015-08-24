<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/includes/taglibs.jsp"%>

<title>Submission Success - DevNexus 2016</title>

<section id="speaker" class="bg-light-gray">
	<div class="container subsuccess">
		<div class="row">
			<div class="
				col-md-2 col-md-offset-1
				col-sm-2 col-sm-offset-1
				col-xs-2 col-xs-offset-0">
				<img src="<c:url value="/assets/img/other/check-mark.jpg"/>" border="0" style="margin-top: 50px;">
			</div>
			<div class="col-md-8 col-sm-8 col-xs-10 ">
				<h3>Your evaluation has been added successfully!</h3>
				<h3><strong>Thank you for attending DevNexus 2016!</strong></h3>
					<p>Your feedback helps us tremendously to improve DevNexus! If you have further
					questions or concerns, please don&rsquo;t hesitate to contact us at:</p>
					<p><strong>info at ajug dot org</strong></p>
				<p><a href="${ctx}${baseSiteUrl}/index" class="btn btn-default btn-primary">Continue</a></p>
			</div>
		</div>
	</div>
</section>


