<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/includes/taglibs.jsp"%>

<title>Submission Success - DevNexus 2015</title>

<section id="speaker" class="bg-light-gray">
	<div class="container subsuccess">
		<div class="row">
			<div class="col-md-2">
				<img src="<c:url value="/assets/img/other/check-mark.jpg"/>" border="0" style="margin-top: 50px;">
			</div>
			<div class="col-md-10">
				<h3>Your submission has been added successfully!</h3>
				<h3><strong>Thank you for your interest in DevNexus 2015!</strong><br />
					We look forward to reviewing your session proposals for the South-East's largest developer conference.</h3>
				<p>Shortly you should receive and email confirming that we received your information.</p>
				<p>We will review your submission as soon as possible and contact you once you
				are accepted or not. If you have any questions or if you need to update some of
				your information, please don't hesitate to contact us at:</p>
				<p><strong>info at ajug dot org</strong></p>

				<p><a href="${ctx}${baseSiteUrl}/index" class="btn btn-default">Continue</a></p>
			</div>
		</div>
	</div>
</section>

