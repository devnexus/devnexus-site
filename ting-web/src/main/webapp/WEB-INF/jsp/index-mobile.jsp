<%@include file="/WEB-INF/jsp/includes/taglibs.jsp"%>

<div data-role="page" data-theme="b" id="main-page">
            <header data-role="header">
                <h1>DevNexus 2012</h1>
            </header>
    <div data-role="content">
        <ul data-role="listview" data-inset="true" data-theme="c" data-dividertheme="b">
            <li data-role="list-divider">DevNexus 2012</li>
            <li><img class="ui-li-icon" alt="Register" src="${ctx}/img/icons/crystal/globe.png"/><a href="https://ajug.eventwax.com/devnexus-2012/register" rel="external">Register</a></li>
            <li><img class="ui-li-icon" alt="Speakers" src="${ctx}/img/mobile/crystal/runit.png"/><a href="#">Registration Opens Dec 1, 2011</a></li>
            <li><img class="ui-li-icon" alt="Call For Papers 2012" src="${ctx}/img/mobile/crystal/groupevent.png"/><a href="#call-for-papers">Call For Papers 2012</a></li>
            <li><img class="ui-li-icon" alt="Speakers" src="${ctx}/img/mobile/crystal/groupevent.png"/><a href="${ctx}/s/speakers"  rel="external">Speakers</a></li>
            <li><img class="ui-li-icon" alt="Speakers" src="${ctx}/img/mobile/crystal/kchart.png"/><a href="${ctx}/s/presentations" rel="external">Presentations</a></li>
            <li><img class="ui-li-icon" alt="Speakers" src="${ctx}/img/mobile/crystal/identity.png"/><a href="${ctx}/s/organizers"  rel="external">Organizers</a></li>
            <li><img class="ui-li-icon" alt="Twitter"  src="${ctx}/img/icons/erenemre/twitter-03.png"/><a href="${ctx}/s/twitter"  rel="external">Twitter</a></li>
            <li><img class="ui-li-icon" alt="Full Browser Version" src="${ctx}/img/icons/crystal/globe.png"/><a href="${ctx}/s/index?site_preference=normal" rel="external">Full Browser Version</a></li>
        </ul>
    </div>
            <footer data-role="footer">
                <h4>&copy; 2012 AJUG</h4>
            </footer>
</div>

<!-- Start of second page: #two -->
<div data-role="page" id="call-for-papers" data-theme="b">

    <div data-role="header">
        <h1>Call for Papers - 2012</h1>
    </div><!-- /header -->

    <div data-role="content">

                <p>
                    The <strong>DevNexus™ 2012</strong> developer conference is taking place
                    <strong>March 21-22</strong>. We are delighted to announce the Call for Papers
                    is now open. We are looking forward to receiving your amazing
                    proposals covering one of the following topics:
                </p>
                <ul>
                    <li>Java and JVM Languages</li>
                    <li>Cloud</li>
                    <li>NoSQL</li>
                    <li>Web (incl. Mobile Development, HTML5, JavaScript)</li>
                    <li>Methodologies and Tools</li>
                </ul>
                <p>
                    We do not encourage overt marketing pitches. Sessions are 75
                    minutes long and we encourage breakout sessions, work-shops
                    and case studies. Please include the following information:
                </p>
                <ul>
                  <li>Name</li>
          <li>Job Title</li>
          <li>Email</li>
          <li>Twiter id</li>
          <li>Company</li>
          <li>Presentation Title</li>
          <li>Audience Level (General, Beginner, Intermediate, Advanced)</li>
          <li>Presentation Abstract</li>
          <li>Your Bio</li>
                </ul>
                <p>
                    Please submit your proposals as soon as possible to
                    <a href="mailto:info@ajug.org">info@ajug.org</a>. The Call for Papers closes January 15, 2012.
                </p>
                <p>
                  The planning committee will carefully review your proposals,
                  and you will get a confirmation whether your talks are selected
                  or not for the DevNexus™ conference.
                </p>
    </div><!-- /content -->

            <footer data-role="footer">
                <h4>&copy; 2012 AJUG</h4>
            </footer>
</div><!-- /page two -->