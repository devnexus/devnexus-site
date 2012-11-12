<%@include file="/WEB-INF/jsp/includes/taglibs.jsp"%>

<div data-role="page" data-theme="b" id="main-page">
    <div data-role="header">
        <h1>DevNexus 2013</h1>
    </div>
    <div data-role="content">
        <ul data-role="listview" data-inset="true" data-theme="c" data-dividertheme="b">
            <li data-role="list-divider">DevNexus 2013</li>
            <li><img class="ui-li-icon" alt="Register" src="${ctx}/img/icons/crystal/kate.png"/>Registration Opens Soon!</li>
            <li><a href="${ctx}${baseSiteUrl}/speakers"      rel="external"><img class="ui-li-icon" alt="Speakers" src="${ctx}/img/mobile/crystal/groupevent.png"/>Speakers</a></li>
            <li><a href="${ctx}${baseSiteUrl}/presentations" rel="external"><img class="ui-li-icon" alt="Speakers" src="${ctx}/img/mobile/crystal/kchart.png"/>Presentations</a></li>
            <li><a href="${ctx}${baseSiteUrl}/schedule"      rel="external"><img class="ui-li-icon" alt="Schedule" src="${ctx}/img/icons/crystal/vcalendar.png"/>Schedule</a></li>
            <li><a href="${ctx}${baseSiteUrl}/organizers"    rel="external"><img class="ui-li-icon" alt="Speakers" src="${ctx}/img/mobile/crystal/identity.png"/>Organizers</a></li>
            <li><a href="${ctx}${baseSiteUrl}/twitter"       rel="external"><img class="ui-li-icon" alt="Twitter"  src="${ctx}/img/icons/erenemre/twitter-03.png"/>Twitter</a></li>
            <li><a href="#sponsors"><img class="ui-li-icon" alt="Our Sponsors" src="${ctx}/img/icons/crystal/kwikdisk.png"/>Our Sponsors</a></li>
            <c:choose>
                <c:when test="${currentDevice.mobile}">
                    <li><a href="${ctx}/desktop/index" rel="external"><img class="ui-li-icon" alt="Full Browser Version" src="${ctx}/img/icons/crystal/globe.png"/>Full Browser Version</a></li>
                </c:when>
                <c:otherwise>
                    <li><a href="${ctx}/s/index" rel="external"><img class="ui-li-icon" alt="Full Browser Version" src="${ctx}/img/icons/crystal/globe.png"/>Full Browser Version</a></li>
                </c:otherwise>
            </c:choose>
        </ul>
    </div>
    <div data-role="footer">
        <h4>&copy; 2011-2013 AJUG</h4>
    </div>
</div>

<!-- Start of second page: #two -->
<div data-role="page" id="sponsors" data-theme="b">

    <div data-role="header">
        <a href="#main-page">Back</a>
        <h1>Our Sponsors</h1>
    </div><!-- /header -->

    <div data-role="content" style="background-color: white;">

    <h3>Gold Sponsors</h3>
    <h3>Silver Sponsors</h3>

    <h3>Cocktail Hour Sponsor</h3>
    </div>
    <!-- /content -->

   <div data-role="footer">
       <h4>&copy; 2011-2013 AJUG</h4>
   </div>
</div><!-- /page two -->