<%@include file="/WEB-INF/jsp/includes/taglibs.jsp"%>

<div data-role="page" data-theme="b">
            <header data-role="header">
                <h1>DevNexus 2011</h1>
            </header>
    <div data-role="content">
        <ul data-role="listview" data-inset="true" data-theme="c" data-dividertheme="b">
            <li data-role="list-divider">DevNexus 2011</li>
            <li><img class="ui-li-icon" alt="Speakers" src="${ctx}/images/mobile/crystal/runit.png"/><a href="http://ajug.eventwax.com/devnexus-2011/register">Register</a></li>
            <li><img class="ui-li-icon" alt="Speakers" src="${ctx}/images/mobile/crystal/groupevent.png"/><a href="${ctx}/s/speakers"  rel="external">Speakers</a></li>
            <li><img class="ui-li-icon" alt="Speakers" src="${ctx}/images/mobile/crystal/kchart.png"/><a href="${ctx}/s/presentations" rel="external">Presentations</a></li>
            <li><img class="ui-li-icon" alt="Speakers" src="${ctx}/images/mobile/crystal/identity.png"/><a href="${ctx}/s/organizers"  rel="external">Organizers</a></li>
            <li><img class="ui-li-icon" alt="Twitter"  src="${ctx}/images/icons/erenemre/twitter-03.png"/><a href="${ctx}/s/twitter"  rel="external">Twitter</a></li>
            <li><img class="ui-li-icon" alt="Full Browser Version" src="${ctx}/images/icons/crystal/globe.png"/><a href="${ctx}/s/index?site_preference=normal" rel="external">Full Browser Version</a></li>
        </ul>
    </div>
            <footer data-role="footer">
                <h4>&copy; 2011 AJUG</h4>
            </footer>
</div>