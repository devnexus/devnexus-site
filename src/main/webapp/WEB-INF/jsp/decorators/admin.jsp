<%@include file="/WEB-INF/jsp/includes/taglibs-decorators.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<title><sitemesh:write property='title'/></title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="shortcut icon" href="${ctx}/favicon.ico">

    <!-- Bootstrap core CSS -->
    <link href="${ctx}/css/bootstrap.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="${ctx}/css/devnexus.css" rel="stylesheet">
    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="${ctx}/js/html5shiv.js"></script>
    <script src="${ctx}/js/respond.min.js"></script>
    <![endif]-->
    <sitemesh:write property='head'/>
    <script src="${ctx}/js/jquery.js"></script>
</head>
<style>
    div#banner {
        padding-top: 20px;
    }
</style>
<body>

<c:url var="homeUrl" value="${baseSiteUrl}/index"/>
<c:url var="speakersUrl" value="${baseSiteUrl}/speakers"/>
<c:url var="presentationsUrl" value="${baseSiteUrl}/presentations"/>
<c:url var="scheduleUrl" value="${baseSiteUrl}/schedule"/>
<c:url var="organizersUrl" value="${baseSiteUrl}/organizers"/>
<c:url var="travelUrl" value="${baseSiteUrl}/travel"/>

<div class="navbar navbar-inverse navbar-fixed-top" style="border:none">
	<div class="container">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
				<span class="icon-bar"></span>
 				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="${homeUrl}"><img id="logo" src="${ctx}/images/devnexus-logo.jpg" border="0"></a>
		</div>
		<div class="navbar-collapse collapse">
			<ul class="nav navbar-nav">
				<li class="dropdown">
					<a href="#" class="dropdown-toggle" data-toggle="dropdown">Web-site&hellip;<b class="caret"></b></a>
					<ul class="dropdown-menu">
						<li><a href="${speakersUrl}">Speakers</a></li>
						<li><a href="${presentationsUrl}">Presentations</a></li>
						<li><a href="${scheduleUrl}">Schedule</a></li>
					</ul>
				</li>
				<li class="dropdown">
					<a href="#" class="dropdown-toggle" data-toggle="dropdown">Past Conferences&hellip;<b class="caret"></b></a>
					<ul class="dropdown-menu">
						<c:forEach items="${eventsForMenu}" var="event">
							<li><a href="<c:url value='${baseSiteUrl}/${event.eventKey}/speakers'/>"><c:out value="${event.title}"/> Speakers</a></li>
							<li><a href="<c:url value='${baseSiteUrl}/${event.eventKey}/presentations'/>"><c:out value="${event.title}"/> Presentations</a></li>
						</c:forEach>
						<li>
							<a href="http://devnexus.com/static/2012/audio/">DevNexus 2012 All Audio Recordings</a>
						</li>
					</ul>
				</li><security:authorize access="hasRole('CFP_REVIEWER') or hasRole('ADMIN')"><li class="dropdown">
						<a href="#" class="dropdown-toggle" data-toggle="dropdown">Admin&hellip;<b class="caret"></b></a>
						<ul class="dropdown-menu">
							<li><a href="<c:url value='${baseSiteUrl}/logout'/>" >Logout</a></li>
							<li><a href="<c:url value='${baseSiteUrl}/admin/index'/>">Admin Area</a></li>
						</ul>
					</li></security:authorize>
			</ul>
		</div>
		<!-- end top nav -->
	</div>
	<!-- end headerwrapper -->
</div>
<div style="clear: both;"></div>

<sitemesh:write property='body'/>

<div style="clear:both;"></div>

<div id="footer" class="footer-inverse" style="margin-top: 20px">
    <div class="container">
        <div class="row">
            <div class="col-md-9">
                <div class="row">
                    <div class="col-md-3">
                        <h3>Learn More</h3>
                        <ul id="col">
                            <li><a href="${ctx}${baseSiteUrl}/index">About DN</a></li>
                            <li><a href="${organizersUrl}">Organizers</a></li>
                            <li><a href="${pastConferencesUrl}">Past Conferences</a></li>
                            <li><a href="${ctx}/static/2014/files/promo/devnexus-2014-sponsorship-options.pdf">Sponsorship
                                (Pdf)</a></li>
                        </ul>
                    </div>
                    <div class="col-md-3">
                        <h3>DevNexus 2014</h3>
                        <ul id="col">
                            <li><a href="${scheduleUrl}">Schedule</a></li>
                            <li><a href="${speakersUrl}">Speakers</a></li>
                            <li><a href="${presentationsUrl}">Presentations</a></li>
                        </ul>
                    </div>
                    <div class="col-md-3">
                        <h3>Attend</h3>
                        <ul id="col">
                            <li><a href="${registrationUrl}">Registration Info</a></li>
                            <li><a href="${travelUrl}">Travel</a></li>
                        </ul>
                    </div>
                    <div class="col-md-3">
                        <h3>Stay Connected</h3>
                        <ul id="col">
                            <li><a href="<c:url value="${baseSiteUrl}/social"/>">Social</a></li>
                            <ul id="col">
                                <li><a href="https://facebook.com/devnexus">Facebook</a></li>
                                <li><a href="https://twitter.com/devnexus">Twitter</a></li>
                            </ul>
                        </ul>
                    </div>
                </div>
            </div>
            <div class="col-md-3">
                <img id="logo" src="${devnexusLogoUrl}" border="0">

                <p id="copy">&copy; 2008-2014 <a href="http://ajug.org">Atlanta Java Users Group (AJUG)</a></p>
            </div>
        </div>

    </div>
</div>

<!-- Bootstrap core JavaScript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->

<script src="${ctx}/js/bootstrap.min.js"></script>

<sitemesh:write property='page.bottom'/>
</body>
</html>
