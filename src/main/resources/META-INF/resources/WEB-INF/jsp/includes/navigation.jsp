	<c:url var="homeUrl" value="${baseSiteUrl}/index"/>
	<c:url var="speakersUrl" value="${baseSiteUrl}/speakers"/>
        <c:url var="sponsorsUrl" value="${baseSiteUrl}/sponsors"/>
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
	<c:url var="conferenceInfoUrl" value="${baseSiteUrl}/conference-info"/>
        
<nav class="navbar navbar-inverse" id="nav">
    <div class="container-fluid">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="${homeUrl}">
                <img src="${ctx}/assets/img/devnexus-logo.png" alt="DevNexus"/>
            </a>
        </div>
        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">

            <ul class="nav navbar-nav navbar-right">
                <li class="nav-link"><a href="${speakersUrl}">SPEAKERS</a></li>
                <li class="nav-link"><a href="${presentationsUrl}">PRESENTATIONS</a></li>
                <li class="nav-link"><a href="${sponsorsUrl}">SPONSORS</a></li>
                <li class="nav-link"><a href="${conferenceInfoUrl}">CONFERENCE INFO</a></li>
                <li class="nav-link"><a class="btn btn-register" href="${registrationUrl}">REGISTER</a></li>
            </ul>
        </div><!-- /.navbar-collapse -->
    </div><!-- /.container-fluid -->
</nav>