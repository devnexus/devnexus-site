<%@ include file="/WEB-INF/jsp/includes/taglibs.jsp"%>

<style>
	.twitterMessages {
	    list-style: none;
	    padding-left: 5px;
	}

	.twitterMessages img {
		float: left;
	    vertical-align: middle;
	    margin-right: 10px;
	    margin-top: 5px;
	    margin-bottom: 5px;
	}

	.twitterMessages li {
		clear: left;
	    padding-top: 2px;
	    padding-bottom: 2px;
	    padding-right: 5px;
	    border-top: 1px dotted #EEEEEE;
	    margin-top: 5px;
	}
</style>
<div class="content">
<p>Welcome to the 2014 developer conference brought to you by the <a href="http://www.ajug.org">Atlanta Java Users Group</a> (AJUG)</p>
            <div class="quote"><span>What the community says:</span> "First time, GREAT EXPERIENCE"</div>

            <h2 style="margin-top: 0;">Welcome to DevNexus 2014 - February 24-25</h2>
            <p>
                We are going to rock again for 2014! The call for papers will be
                open soon. So stay tuned for more updates.
            </p>

            <h2 style="">What?</h2>

            <p>Atlanta&#8217;s most exciting conference for professional
            software developers is back in 2014! Come discover how the
            industry&#8217;s best minds use the latest technologies to build
            solutions to business problems. Network with other Atlanta software
            developers, and study real life case studies in application design and
            development.</p>

            <p>Our first seven conference events were tremendous successes. Skip
            the travel headaches, expense, and mobs of people at other conferences
            and join us this February to experience one of the best developer
            conferences, presented by your fellow developers, right in the heart of
            the Southeast (and in your own backyard).</p>

            <p>This conference is an incredible value. Not only do we provide
               exceptional speakers and presentations but we also provide book
               raffles, food, a cocktail hour for networking and mingling with the
               speakers and much more.</p>

            <p><strong>Make sure to sign up early to ensure you get a seat this year!
            We sold out last year and the year before and the year before...
            </strong></p>

            <ul>
                <li><a href="${ctx}${baseSiteUrl}/speakers">List of speakers</a></li>
                <li><a href="${ctx}${baseSiteUrl}/presentations">List of presentations</a></li>
                <li><a href="${ctx}${baseSiteUrl}/schedule">Schedule</a></li>
            </ul>

    <h2>When?</h2>
    <h3>Monday, <strong>February 24</strong> 2014 and Tuesday, <strong>February 25</strong> 2014</h3>

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
       <h2 style="color: #FF0000;">NOT OPEN YET!</h2>
   </div>

    <p><strong>Please book your tickets as early as possible! We have completely sold out
    in the past 3 years.</strong>
    </p>

    <p>If you have any questions, please contact us at info AT ajug DOT org</p>

	<div style="margin: 40px 30px 20px 20px; border: 1px solid #EEEEEE;">
		<div style="background-color: #302F91; color: #FFFFFF"><span style="margin-left: 10px;">DevNexus Tweets</span></div>
		<ul class="twitterMessages">
			<c:choose>
				<c:when test="${not empty tweets}">
					<c:forEach items="${tweets}" var="twitterMessage">
						<li><img alt="${twitterMessage.fromUser}"
							title="${twitterMessage.fromUser}"
							src="${twitterMessage.profileImageUrl}" width="48" height="48">
							<div style="font-weight: bold;"><c:out value="${twitterMessage.fromUser}" /></div>
							<c:out value="${twitterMessage.text}" /><br/>
							<div style="color: #AAAAAA; text-align: right; font-size: 80%;"><c:out value="${twitterMessage.prettyTime}" /></div></li>
					</c:forEach>
				</c:when>
				<c:otherwise><li>No Twitter messages found.</li></c:otherwise>
			</c:choose>
			<li><a href="https://twitter.com/search?q=devnexus">More Tweets…</a></li>
		</ul>
	</div>
</div>

<div class="sidebar">

  <!-- AddThis Button BEGIN -->

  <script type="text/javascript">
    var addthis_config = {
         pubid: "ra-4ec9e6a478f688a2"
    };

     var addthis_share =  {
          templates: {
                         twitter: 'DevNexus 2013 - February 18-19 - Details: http://www.devnexus.com/ (from @devnexus)'
                     }
      };
  </script>

  <a href="http://www.addthis.com/bookmark.php?v=250"
          class="addthis_button"><img
          src="http://s7.addthis.com/static/btn/v2/lg-share-en.gif"
          width="125" height="16" border="0" alt="Share" /></a>

<script type="text/javascript" src="http://s7.addthis.com/js/250/addthis_widget.js"></script>

<!-- AddThis Button END -->

    <h3>Gold Sponsors</h3>
	<a href="http://www.springsource.com/"><img         class="logo" alt="SpringSource" src="${ctx}/img/sponsors/SpringSource_VMware.jpg"/></a>
	<a href="http://terracotta.org/"><img class="logo" alt="Terracotta" src="${ctx}/img/sponsors/terracotta-logo.png"/></a>
	<a href="https://www.theice.com/careers.jhtml"><img class="logo" alt="ICE"        src="${ctx}/img/sponsors/ICE.jpg"/></a>
	<a href="http://www.compuware.com/apm"><img class="logo" alt="Compuware Corporation"        src="${ctx}/img/sponsors/compuware.png"/></a>

	<h3>Silver Sponsors</h3>
	<a href="http://ehirelabs.com/"><img class="logo" alt="eHire Labs" src="${ctx}/img/sponsors/eHire.png"/></a>
	<a href="http://www.appdynamics.com/"><img class="logo" alt="AppDynamics" src="${ctx}/img/sponsors/app-dynamics.png"/></a>
	<a href="http://www.jboss.com/"><img class="logo" alt="JBoss" src="${ctx}/img/sponsors/jboss.png"/></a>
	<a href="http://www.anteogroup.com/"><img class="logo" alt="Anteo Group" src="${ctx}/img/sponsors/Anteo.png"/></a>
	<a href="http://www.apexsystemsinc.com/"><img class="logo" alt="Apex Systems" src="${ctx}/img/sponsors/apex-systems.png"/></a>
	<a href="http://www.github.com/"><img class="logo" alt="GitHub" src="${ctx}/img/sponsors/github_logo.png"/></a>
	<a href="http://www.incomm.com/"><img     class="logo" alt="InComm" src="${ctx}/img/sponsors/inComm.png"/></a>
	<a href="http://www.4tnetworks.com/"><img class="logo" alt="4t Networks" src="${ctx}/img/sponsors/4t.png"/></a>
	<a href="http://www.creative-mischief.com/"><img class="logo" alt="Creative Mischief" src="${ctx}/img/sponsors/creative-mischief.png"/></a>
	<a href="http://www.google.com/"><img class="logo" alt="Google" src="${ctx}/img/sponsors/google.png"/></a>
	<a href="http://www.dtglobalstaffing.com/"><img class="logo" alt="Dimensional Thinking" src="${ctx}/img/sponsors/DimensionalThinking_AJUG.png"/></a>
	<a href="http://www.bridge2solutions.com/"><img class="logo" alt="Bridge2 Solutions" src="${ctx}/img/sponsors/B2S.png"/></a>
	<a href="http://www.actuate.com/"><img class="logo" alt="Actuate" src="${ctx}/img/sponsors/actuate.jpg"/></a>
	<a href="http://www.lancope.com/"><img class="logo" alt="Lancope" src="${ctx}/img/sponsors/lancope.png"/></a>

	<h3>Cocktail Hour Sponsor</h3>
	<a href="http://ehirelabs.com/"><img class="logo" alt="eHire Labs" src="${ctx}/img/sponsors/eHire.png"/></a>

</div>

	<content tag='bottom'>
		<script type="text/javascript">
		    $(function() {
		        $( "a.button").button();
		    });
		</script>
	</content>