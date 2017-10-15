<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/includes/taglibs.jsp" %>

<title>${contextEvent.title} | Conference Information </title>
<section class="container-fluid conference-information" >
	<h1 class="featured-header">
		PAYMENT SUCCESSFUL
	</h1>
	<div class="row ">
		<div class="col-xs-12 text-center">
				<img src="${ctx}/assets/img/success.png" class="center-block" title="Success"/>
				<p style="text-align: center"><strong>Thank you for your order!</strong> We look forward to seeing you at DevNexus 2018!</p>
				<p>Are you looking for a hotel or have transportation questions?
				<p>Please review our <strong>Conference Info</strong> page for details:</p>
				<a class="btn hero-btn-register" href="https://devnexus.com/conference-info">Conference Info</a>
			</div>
	</div>
</section>
