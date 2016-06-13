<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/jsp/includes/taglibs-decorators.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width" />

	<meta property="og:title" content="DevNexus 2017">
	<meta property="og:type" content="company">
	<meta property="og:site_name" content="DevNexus">
	<meta property="og:url" content="http://devnexus.com/s/index">
	<meta property="og:image" content="">
	<meta property="og:image" content="">
	<meta content='devnexus, conference, tech conference, southeast, 2015, atlanta conference' name='keywords' />
	<meta property="og:description" content="">

	<title><sitemesh:write property='title'/></title>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta name="description" content="">
	<meta name="author" content="">
	<link rel="shortcut icon" href="${ctx}/favicon.png">

	<link rel="stylesheet" type="text/css" href="${ctx}/wro/all.css" />

<%-- 	<!-- vendor CSS -->
	<link href="${assetsUrl}/css/vendor/bootstrap.min.css" rel="stylesheet">
	<link href="${assetsUrl}/css/vendor/animate.css" rel="stylesheet">
	<link href="${assetsUrl}/css/vendor/font-awesome.css" rel="stylesheet">
 --%>

	<!-- fonts: external links -->
	<link href='//fonts.googleapis.com/css?family=Quicksand' rel='stylesheet' type='text/css'>

	<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
	<!--[if lt IE 9]>
	<script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
	<script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
	<![endif]-->

	<sitemesh:write property='head'/>
	<sitemesh:write property='page.top'/>
</head>

<body>
	<c:url var="homeUrl" value="${baseSiteUrl}/index"/>
	<c:url var="speakersUrl" value="${baseSiteUrl}/speakers"/>
	<c:url var="presentationsUrl" value="${baseSiteUrl}/presentations"/>
	<c:url var="scheduleUrl" value="${baseSiteUrl}/schedule"/>
	<c:url var="organizersUrl" value="${baseSiteUrl}/organizers"/>
	<c:url var="aboutUrl" value="${baseSiteUrl}/about"/>
	<c:url var="socialUrl" value="${baseSiteUrl}/social"/>
	<c:url var="travelUrl" value="${baseSiteUrl}/travel"/>
	<c:url var="registrationUrl" value="${baseSiteUrl}/register-overview"/>
	<c:url var="pastConferencesUrl" value="${baseSiteUrl}/past-conferences"/>
	<c:url var="privacyPolicyUrl" value="${baseSiteUrl}/privacy-policy"/>
	<c:url var="codeOfConductUrl" value="${baseSiteUrl}/code-of-conduct"/>

	<%@ include file="/WEB-INF/jsp/includes/navigation.jsp" %>

	<sitemesh:write property='body'/>

	<%@ include file="/WEB-INF/jsp/includes/footer.jsp" %>

	<!-- javascipt -->
	<script src="${ctx}/wro/all.js"></script>
<%-- 	<script src="${assetsUrl}/js/jquery1.11.1.min.js"></script>
	<script src="${assetsUrl}/js/jquery.modernizr.js"></script>
	<script src="${assetsUrl}/js/jquery.scrollTo.js"></script>
	<script src="${assetsUrl}/js/jquery.easing.min.js"></script>
	<script src="${assetsUrl}/js/bootstrap.min.js"></script>

	<script src="${assetsUrl}/js/other/masonry.pkgd.js"></script>
	<script src="${assetsUrl}/js/other/imagesloaded.pkgd.min.js"></script> --%>

	<sitemesh:write property='page.bottom'/>
	<script>
			(function (i, s, o, g, r, a, m) {
					i['GoogleAnalyticsObject'] = r;
					i[r] = i[r] || function () {
							(i[r].q = i[r].q || []).push(arguments)
					}, i[r].l = 1 * new Date();
					a = s.createElement(o),
									m = s.getElementsByTagName(o)[0];
					a.async = 1;
					a.src = g;
					m.parentNode.insertBefore(a, m)
			})(window, document, 'script', '//www.google-analytics.com/analytics.js', 'ga');

			ga('create', 'UA-44984422-1', 'devnexus.com');
			ga('send', 'pageview');

	</script>
</body>
</html>
