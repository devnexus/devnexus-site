	<!-- Navigation -->
	<nav class="navbar navbar-custom navbar-fixed-top">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
				<span class="sr-only">Toggle navigation</span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="${homeUrl}"><img src="${assetsUrl}/img/DevNexus_logo_small.png"></a>
		</div>

		<div class="collapse navbar-collapse">
			<ul class="nav navbar-nav">
				<li class="dropdown">
					<a href="#" class="dropdown-toggle" data-toggle="dropdown">Presentations <b class="caret"></b></a>
					<ul class="dropdown-menu">
						<li><a href="${presentationsUrl}?order=track">Presentations by Track</a></li>
						<li><a href="${presentationsUrl}?order=room">Presentations by Room</a></li>
						<li><a href="${presentationsUrl}?order=name">Presentations by Name</a></li>
						<li><a href="${ctx}/s/tracks">Show Tracks</a></li>
						<li><a href="${ctx}/s/tags">Show Presentation Tags</a></li>
						<li><a href="${ctx}/s/rooms">Show Rooms</a></li>
					</ul>
				</li>
				<li><a class="" href="${speakersUrl}">Speakers</a></li>
				<li><a class="" href="${scheduleUrl}">Schedule</a></li>
				<li><a class="" href="${registrationUrl}">Register Now!</a></li>
				<li><a class="page-scroll" href="${homeUrl}#travel">Travel</a></li>
				<li class="dropdown">
					<a href="#" class="dropdown-toggle" data-toggle="dropdown">More <b class="caret"></b></a>
					<ul class="dropdown-menu">
						<li><a href="${aboutUrl}">About</a></li>
						<li><a href="${organizersUrl}">Organizers</a></li>
						<li><a href="${sponsorsUrl}">Sponsors</a></li>
						<li><a href="${socialUrl}">Social</a></li>
						<li><a href="${pastConferencesUrl}">Past Conferences</a></li>
						<li><a href="${pastConferencesUrl}"><i class="fa fa-facebook fa-fw"></i> Facebook</a></li>
						<li><a href="${pastConferencesUrl}"><i class="fa fa-youtube fa-fw"></i> Youtube</a></li>
						<li><a href="${pastConferencesUrl}"><i class="fa fa-flickr fa-fw"></i> Flickr</a></li>
					</ul>
				</li>
			</ul>
		</div>
	</nav>