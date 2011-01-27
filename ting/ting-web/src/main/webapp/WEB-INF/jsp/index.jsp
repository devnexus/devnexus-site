<%@ include file="/WEB-INF/jsp/includes/taglibs.jsp"%>

<div id="content" class="span-14">
<div class="first">
        <p>Welcome to the 2011 developer conference brought to you by the <a href="http://www.ajug.org">Atlanta Java Users Group</a> (AJUG)</p>
            <div class="quote"><span>What the community says:</span> "First time, GREAT EXPERIENCE"</div>

            <h2 style="margin-top: 0;">DevNexus 2011 - Coming March 21-22</h2>

            <p>Call for papers! If you would like to present at DevNexus 2011, please
               send a session abstract and bio to info at ajug dot org.</p>

            <p>Please check back periodically for speaker and session updates.</p>
            <p style="font-weight: bold;">We hope to see you at DevNexus 2011!!</p>
            <h2 style="">What?</h2>

            <p>Atlanta&#8217;s most exciting conference for professional
            software developers is back in 2011! Come discover how the
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
                <li><a href="${ctx}/s/speakers">List of speakers</a></li>
                <li><a href="${ctx}/s/presentations">List of presentations</a></li>
                <li><a href="${ctx}/s/schedule">Schedule</a></li>
            </ul>

            <p>*Please keep in mind that the exact schedule of presentations
            and/or speakers can still change between now and March.</p>
    </div>
    <h2>When?</h2>
    <h3>Monday, <strong>March 21</strong> 2011 and Tuesday, <strong>March 22</strong> 2011</h3>

    <h2>Where?</h2>

    <p>The conference will be hosted at the <a
        href="http://www.cobbgalleria.com/">Cobb Galleria Center</a>, just a
    few minutes north of downtown Atlanta and easily accessible from I-75
    and I-285.</p>

    <p>Directions can be found at <a
        href="http://www.cobbgalleria.com/directions.aspx">http://www.cobbgalleria.com/directions.aspx</a>.
    A map of the area is provided <a
        href="http://maps.google.com/maps?f=q&#38;hl=en&#38;geocode=&#38;q=Two+Galleria+Parkway,+atlanta,+Georgia+30339&#38;sll=33.883866,-84.45699&#38;sspn=0.014358,0.033045&#38;g=cobb+galleria,+atlanta&#38;ie=UTF8&#38;ll=33.881603,-84.466753&#38;spn=0.028716,0.06609&#38;z=15">here</a>.</p>

    <h2>Registration</h2>

   <div id="signup">
   <h2><a href="http://ajug.eventwax.com/devnexus-2011/register">SIGN UP</a></h2>
   </div>

    <p>The Standard Ticket is <strong>$195.00</strong>.</p>
    <p>For a limited time (until Feb 1, 2010), we have an Early Bird Special of <strong>$175.00</strong>.</p>
    <p>Also, we have a group discount available: If you book 5 tickets
    or more each ticket is <strong>$175</strong>.</p>

    <p>Are you are student? We have a limited amount of student tickets priced at $130. Please contact us at: info AT ajug DOT org for details.</p>

    <p>Please be aware that we have to close registration on March 15 as we need to
       report our attendee numbers to the Cobb Galleria. Also 2 weeks before the event only late tickets
       will be available for $220. Therefore, please book your tickets as early as possible!</p>

    <p><strong><a href="http://ajug.eventwax.com/devnexus-2011/register">Book your place now!</a></strong> You know you want to.</p>

    <p>If you have any questions, please contact us at info AT ajug DOT org</p>

</div>
<div class="prepend-1 span-7 last">
    <h2 style="border-bottom:1px solid orange;">Gold Sponsor</h2>
    <a href="http://www.theintersectgroup.com/"><img alt="The Intersect Group" src="${ctx}/images/sponsors/intersect-logo.png"/></a>
    <h2 style="border-bottom:1px solid orange; margin-top: 20px;">Silver Sponsors</h2>

    <a href="http://www.rim.com/"><img alt="Blackberry" src="${ctx}/images/sponsors/blackberry_logo.png"/></a>
    <a href="http://www.anteogroup.com/"><img alt="Anteo" src="${ctx}/images/sponsors/Anteo.png"/></a>
    <a href="http://www.4tnetworks.com/"><img alt="4t networks" src="${ctx}/images/sponsors/4t.png"/></a>
    <a href="http://www.gca.net/"><img alt="GCA" src="${ctx}/images/sponsors/gca-logo.jpg"/></a>
    <a href="https://www.theice.com/careers.jhtml"><img alt="ICE" src="${ctx}/images/sponsors/ICE.jpg"/></a>
    <a href="http://www.jboss.com/"><img alt="JBoss" src="${ctx}/images/sponsors/jboss.png"/></a>
    <a href="https://www.mymatrixjobs.com//"><img alt="Matrix" src="${ctx}/images/sponsors/matrix.png"/></a>
    <a href="http://www.adorsys.com/"><img alt="Adorsys" src="${ctx}/images/sponsors/adorsys.jpg"/></a>

	<div style="margin-top: 3em;">
	<script src="http://widgets.twimg.com/j/2/widget.js"></script>
	<script type="text/javascript">
	new TWTR.Widget({
	  version: 2,
	  type: 'profile',
	  rpp: 3,
	  interval: 6000,
	  width: 250,
	  height: 200,
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

</div>

