<%@ include file="/WEB-INF/jsp/includes/taglibs.jsp"%>
<div style="float: right; margin-top: 3em; margin-left: 1em;">
<script src="http://widgets.twimg.com/j/2/widget.js"></script>

<script>
new TWTR.Widget({
  version: 2,
  type: 'profile',
  rpp: 3,
  interval: 6000,
  width: 200,
  height: 250,
  theme: {
    shell: {
      background: '#312f91',
      color: '#ffffff'
    },
    tweets: {
      background: '#000000',
      color: '#ffffff',
      links: '#f7951e'
    }
  },
  features: {
    scrollbar: false,
    loop: false,
    live: true,
    hashtags: true,
    timestamp: true,
    avatars: false,
    behavior: 'all'
  }
}).render().setUser('devnexus').start();
</script></div>
<div class="first">
	    <p>Welcome to the 2010 developer conference brought to you by the <a href="http://www.ajug.org">Atlanta Java Users Group</a> (AJUG)</p>
            <div class="quote"><span>What the community says:</span> "First time, GREAT EXPERIENCE"</div>

            <h2 style="margin-top: 0;">Thank you very much for attending DevNexus!!</h2>

            <p>Thank you very much for attending DevNexus 2010. Over the next couple of days we will be adding the slides of the
            presentations to the site. Please check <a href="#">List of presentations</a>
            periodically in order to see whether the desired slides have been already added, yet.
            </p><p>Within the next 2 weeks, we also hope to add the first audio recording as well. Stay tuned.</p>
            <p style="font-weight: bold;">We hope to see you at DevNexus 2011!!</p>
            <h2 style="">What?</h2>

            <p>Atlanta&#8217;s most exciting conference for professional
            software developers is back in 2010! Come discover how the
            industry&#8217;s best minds use the latest technologies to build
            solutions to business problems. Network with other Atlanta software
            developers, and study real life case studies in application design and
            development.</p>

            <p>Our first four conference events were tremendous successes. Skip
            the travel headaches, expense, and mobs of people at other conferences
            and join us this March to experience one of the best developer
            conferences, presented by your fellow developers, right in the heart of
            the Southeast (and in your own backyard).</p>

            <p>This conference is an incredible value. Not only do we provide
               exceptional speakers and presentations but we also provide book
               raffles, food, a cocktail hour for networking and mingling with the
               speakers and much more.</p>

            <p><strong>Make sure to sign up early to ensure you get a seat this year!</strong></p>

            <ul>
                <li><a href="#">List of speakers</a></li>
                <li><a href="#">List of presentations</a></li>
                <li><a href="#">Schedule</a></li>
            </ul>

            <p>*Please keep in mind that the exact schedule of presentations
            and/or speakers can still change between now and March.</p>
    </div>

<div class="flyers">
  <div class="header">Help us to promote DevNexus!</div>
  <div class="body">
    <p class="item"><img alt="Adobe Reader Icon" src="${ctx}/images/adobe-reader.png"/><a href="/static/2010/files/promo/devnexus conference 2010 - press release.pdf">Initial Press Release</a></p>
    <p class="item"><img alt="Adobe Reader Icon" src="${ctx}/images/adobe-reader.png"/><a href="/static/2010/files/promo/DevNexus_2010_Flyer_06.pdf">Flyer</a></p>
  </div>
</div>
    <h2>When?</h2>
    <h3>Monday, <strong>March 8</strong> 2010 and Tuesday, <strong>March 9</strong> 2010</h3>

    <h2>Where?</h2>

	<p>The conference will be hosted at the <a
		href="http://www.cobbgalleria.com/">Cobb Galleria Center</a>, just a
	few minutes north of downtown Atlanta and easily accessible from I-75
	and I-285.</p>

    <p>Directions can be found at <a
		href="http://www.cobbgalleria.com/maps.html">http://www.cobbgalleria.com/maps.html</a>.
	A map of the area is provided <a
		href="http://maps.google.com/maps?f=q&#38;hl=en&#38;geocode=&#38;q=Two+Galleria+Parkway,+atlanta,+Georgia+30339&#38;sll=33.883866,-84.45699&#38;sspn=0.014358,0.033045&#38;g=cobb+galleria,+atlanta&#38;ie=UTF8&#38;ll=33.881603,-84.466753&#38;spn=0.028716,0.06609&#38;z=15">here</a>.</p>

    <h2>Registration</h2>

	<p>The Standard Ticket is <strong>$185.00</strong>.</p>
        <p>For a limited time (until Feb 16, 2010), we have an Early Bird Special of <strong>$150.00</strong>.</p>
	<p>Also, we have a group discount available: If you book 5 tickets
	or more each ticket is <strong>$150</strong>.</p>

        <p>Are you are student? We have a limited amount of student tickets. Please contact us at: info AT ajug DOT org for details.</p>

	<p><strong>Book your place now!</strong> You know you want to.</p>

	<p>If you have any questions, please contact us at info AT ajug DOT org</p>


                <div id="signup">
                  <h2>Registration Closed.</h2>
                </div>

                <div id="sponsors">
                        <h2>We also would like to thank all our sponsors for their support!</h2>
                        <table class="sponsors">
                            <tr>
                                <td><a href="http://www.sun.com/"><img alt="Sun" src="${ctx}/images/sponsors/Sun.png"/></a></td>
                                <td><a href="http://www.anteogroup.com/"><img alt="Anteo" src="${ctx}/images/sponsors/Anteo.png"/></a></td>
                                <td style="text-align: center;"><a href="http://www.4tnetworks.com/"><img alt="4t networks" src="${ctx}/images/sponsors/4t.png"/></a></td>
                                <td colspan="1" rowspan="2"><a href="http://www.gca.net/"><img alt="GCA" src="${ctx}/images/sponsors/gca-logo.jpg"/></a></td>

                            </tr>
                            <tr>
                            <td style="text-align: center;" rowspan="2"><a href="https://www.theice.com/careers.jhtml"><img alt="ICE" src="${ctx}/images/sponsors/ICE.jpg"/></a></td>
                            <td colspan="1"><a href="http://www.jboss.com/"><img alt="JBoss" src="${ctx}/images/sponsors/jboss.png"/></a></td>
                            <td            ><a href="https://www.mymatrixjobs.com//"><img alt="Matrix" src="${ctx}/images/sponsors/MATRIX_150px.jpg"/></a></td>
                            </tr>

                            <tr>
                                <td><a href="http://www.theintersectgroup.com/"><img alt="Intersect" src="${ctx}/images/sponsors/intersect-logo.png"/></a></td>
                                <td style="text-align: center;" colspan="2"><a href="http://adorsysllc.com/"><img alt="Adorsys" src="${ctx}/images/sponsors/adorsys.jpg"/></a></td>
                            </tr>
                        </table>
                </div>

