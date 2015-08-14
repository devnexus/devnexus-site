<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@page import="com.devnexus.ting.model.PresentationType" %>
<%@ include file="/WEB-INF/jsp/includes/taglibs.jsp" %>

<head>
	<title><c:out value="${event.title}"/> - Presentations</title>
</head>

<div id="devnex" class="jumbotron">
	<div class="container">
		<div id="banner">
			<h1 id="gray"><c:out value="${event.title}"/></h1>

			<h1 id="white">Presentations</h1>
			<h3>Data + Integration, Java/JavaEE/Spring, HTML5 + JavaScript, Alternative Languages, Cloud, Agile + Tools, Mobile</h3>
		</div>
	</div>
</div>

<style>
	.jumbotron {
			margin-bottom: 0px;
	}
</style>
<div id="yellow" class="jumbotron" style="margin-bottom:0">
	<div class="container">
		<h1>Presentations are still coming in.</h1>

		<div class="row">
			<div class="col-md-12">
				<p>
					We will begin announcing topics in January; in the meanwhile, why don't you check out last year's presentations.
				</p>
				<c:url var="oldUrl" value="${baseSiteUrl}/devnexus2013/presentations"/>
				<center><a href="${oldUrl}" class="btn btn-primary btn-lg">DevNexus 2013 Presentations</a></center>
			</div>
		</div>
	</div>
</div>
