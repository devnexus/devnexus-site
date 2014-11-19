<%@ include file="/WEB-INF/jsp/includes/taglibs.jsp" %>

<style>
		.jumbotron {
				margin-bottom: 0;
		}

		div#banner {
				padding-top: 20px;
		}

		#gold a img {
				margin: 5px;
		}

		#silver a img {
				margin: 5px;
		}
</style>

<style>
		.jumbotron {
				margin-bottom: 0px;
		}
</style>
<div id="devnex" class="jumbotron">
	<div class="container">
		<div id="banner">
			<h1 id="gray">DevNexus 2015</h1>
			<h1 id="white"><c:out default="Atlanta, GA" value="${headerTitle}"/></h1>
			<h3><c:out default="The professional developer conference." value="${tag}"/></h3>
		</div>
	</div>
</div>
<div id="yellow" class="jumbotron" style="margin-bottom:0">
	<div class="container">
		<div class="row">
			<div class="col-md-7 col-md-push-5">
				<h1 class="center">March 10-12, 2015</h1>
				<div class="text-center"><a href="${ctx}/s/cfp" class="btn btn-primary btn-lg">Call for Papers</a>
					<a href="${ctx}/static/2015/files/promo/devnexus-2015-sponsorship-options.pdf" class="btn btn-primary btn-lg">Sponsor us</a>
					<a href="https://devnexus2015.eventbrite.com" class="btn btn-success btn-lg">Register Now!</a></div>
			</div>
			<div id="travelphoto" class="col-md-5 col-md-pull-7">
				<div class="embed-responsive embed-responsive-16by9">
					<iframe class="embed-responsive-item" src="//player.vimeo.com/video/90063155?title=0&amp;byline=0&amp;portrait=0" webkitallowfullscreen mozallowfullscreen allowfullscreen></iframe>
				</div>
			</div>
		</div>
	</div>
</div>

<div class="container contents-container">

		<div class="row">
				<div class="col-md-7 col-md-push-5">

						<h1 class="center">What</h1>

						<p>Atlanta&rsquo;s most exciting conference for professional software developers is back in 2015! Come discover
								how the industry&rsquo;s best minds use the latest technologies to build solutions to business problems.
								Network with other Atlanta software developers, and study real life case studies in application design
								and development.</p>

						<p>Our first seven conference events were tremendous successes. Skip the travel headaches, expense, and mobs
								of people at other conferences and join us this March to experience one of the best developer
								conferences, presented by your fellow developers, right in the heart of the Southeast (and in your own
								backyard). </p>

						<p>This conference is an incredible value. Not only do we provide exceptional speakers and presentations but
								we also provide book raffles, food, a cocktail hour for networking and mingling with the speakers and
								much more.</p>

						<h1 class="center">Where</h1>

						<p>The conference will be hosted at the Cobb Galleria Center, just a few minutes north of downtown Atlanta
								and easily accessible from I-75 and I-285.</p>
						<c:url var="travelUrl" value="${baseSiteUrl}/travel"/>
						<p>The <a href="${travelUrl}">travel page</a> has more information including directions, maps, and nearby
								hotels.</p>

						<h1 class="center">Registration</h1>

						<div id="signup">
								<p style="color: #FF0000;" class="text-center">Registration opens November 10th</p>
						</div>
						<h2>Main Conference <span style="color: #617784">|</span> March 11&ndash;12</h2>
						<p>
								Registration opens <strong>November 10th</strong> and Early Bird tickets
								for the main conference (March 11&ndash;12) will be available for <strong>$250</strong>.
						</p>
						<p>Please reserve your ticket as early as possible. <strong>We have sold out
							 all prior DevNexus events!!</strong>
						</p>
						<table class="table table-striped">
								<tbody>
										<tr><td>Early Bird</td><td><strong>$250</strong></td><td>Nov 10 &ndash; Jan 11</td></tr>
										<tr><td>Standard Ticket</td><td><strong>$280</strong></td><td>Jan 12 &ndash; Feb 8</td></tr>
										<tr><td>Late Ticket</td><td><strong>$310</strong></td><td>Feb 9 &ndash; Feb 23</td></tr>
										<tr><td>Group Ticket (5 and more)</td><td><strong>$250</strong></td><td>Nov 10 &ndash; Feb 23</td></tr>
										<tr><td>Student Ticket*</td><td><strong>$150</strong></td><td>Nov 10 &ndash; Feb 23</td></tr>
								</tbody>
						</table>
						<p>* Are you are student? We have a limited amount of student tickets priced at <strong>$150</strong>.
							Please contact us at: info AT ajug DOT org for details and the discount code.
						 </p>
						<h2>Workshop Day <span style="color: #617784">|</span> March 10</h2>
						<p>
							For 2015 we offer a dedicated Workshop day on <strong>March 10</strong>.
							We will offer 6 full-day workshops for <strong>$150</strong>. Please reserve your seat
							early as space is limited.
						</p>
						<table class="table table-striped">
								<thead>
										<tr><th>Topic</th><th>Instructor</th></tr>
								</thead>
								<tbody>
									<tr><td>Functional Design By Example</td><td>Josh Backfield</td></tr>
									<tr><td>Android Workshop </td><td>Ken Kousen</td></tr>
									<tr><td>Hacking Web Components and Polymer</td><td>Kito Mann</td></tr>
									<tr><td>Down and Dirty with Java EE 7</td><td>Reza Rahman</td></tr>
									<tr><td>Spring XD: Big Data Integration</td><td>Glenn Renfro</td></tr>
									<tr><td>Scala/Play/Akka</td><td>Daniel Hinosoa</td></tr>
								</tbody>
						</table>
						<p>If you have any questions, please contact us at info AT ajug DOT org</p>
				</div>
				<div id="travelphoto" class="col-md-5 col-md-pull-7">
						<img class="img-responsive" src="${ctx}/images/keynote-2014.jpg">
						<img class="img-responsive" src="${ctx}/images/devnexus-2014.jpg">
						<img class="img-responsive" src="${ctx}/images/devnexus-audience-2014.jpg">
						<img class="img-responsive" src="${ctx}/images/cobb-galleria-2014.jpg">
				</div>
		</div>
</div>

<div id="devnex" class="jumbotron">

		<div id="platinum" style="max-width: 768px;margin-left:auto;margin-right:auto;">
				<h3>Platinum Sponsor</h3>
						<a href="${ctx}/static/2015/files/promo/devnexus-2015-sponsorship-options.pdf"><img class="logo" alt="See Sponsorship Options"
							 src="${ctx}/images/sponsorship-available.png"/></a>
		</div>

		<div>
				<div id="gold" style="max-width: 768px;margin-left:auto;margin-right:auto;">
						<h3>Gold Sponsors</h3>
						<a href="http://www.pivotal.io/"><img class="logo" alt="Pivotal"
							 src="${ctx}/img/sponsors/pivotal.png"/></a>
						<a href="http://www.thoughtworks.com/"><img class="logo" alt="Thoughtworks"
							 src="${ctx}/img/sponsors/thoughtworks.png"/></a>
						<a href="${ctx}/static/2015/files/promo/devnexus-2015-sponsorship-options.pdf"><img class="logo" alt="See Sponsorship Options"
							 src="${ctx}/images/sponsorship-available.png"/></a>
				</div>
		</div>
		<div id="silver" style="max-width: 768px;margin-left:auto;margin-right:auto;">
				<h3>Silver Sponsors</h3>
						<a href="http://www.coverity.com/"><img class="logo" alt="Coverity"
							 src="${ctx}/img/sponsors/coverity.png"/></a>
						<a href="http://www.anteogroup.com/"><img class="logo" alt="Anteo Group"
							 src="${ctx}/img/sponsors/Anteo.png"/></a>
						<a href="http://www.statefarm.com/"><img class="logo" alt="State Farm"
                                                         src="${ctx}/img/sponsors/state_farm.png"/></a>
						<a href="http://www.vaadin.com/"><img class="logo" alt="Vaadin"
							 src="${ctx}/img/sponsors/vaadin.png"/></a>
						<a href="http://www.sonatype.com/"><img class="logo" alt="Sonatype"
							 src="${ctx}/img/sponsors/sonatype_key.png"/></a>
						<a href="${ctx}/static/2015/files/promo/devnexus-2015-sponsorship-options.pdf"><img class="logo" alt="See Sponsorship Options"
							 src="${ctx}/images/sponsorship-available.png"/></a>
						<a href="http://www.altisourcelabs.com/">
								<img class="logo" alt="Altisource Labs" src="${ctx}/img/sponsors/altisource.png"/>
						</a>
						 <a href="http://www.thompsontechnologies.com/"><img class="logo" alt="Thompson Technologies"
                                                         src="${ctx}/img/sponsors/thompson.png"/></a>
						<a href="http://www.aspose.com/">
								<img class="logo" alt="Aspose" src="${ctx}/img/sponsors/aspose-key.png"/>
						</a>
		</div>
		<div id="cocktail">
				<h3>Cocktail Hour Sponsor</h3>
				<a href="http://mandrill.com/"><img class="logo" alt="Mandrill by MailChimp"
							 src="${ctx}/img/sponsors/mandrill.png"/></a>
		</div>
</div>
