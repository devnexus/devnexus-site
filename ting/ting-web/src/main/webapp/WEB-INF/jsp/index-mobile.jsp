<%@include file="/WEB-INF/jsp/includes/taglibs.jsp"%>

<head>
    <c:choose>
        <c:when test="${currentSitePreference.mobile}">

            <meta name="decorator" content="mobile"/>
        </c:when>
        <c:otherwise>
            <meta name="decorator" content="default"/>
        </c:otherwise>
    </c:choose>
</head>


        <ul data-role="listview" data-inset="true" data-theme="c" data-dividertheme="b">
            <li data-role="list-divider">DevNexus 2011</li>
            <li><img class="ui-li-icon" alt="Speakers" src="${ctx}/images/mobile/crystal/runit.png"/><a href="http://ajug.eventwax.com/devnexus-2011/register">Register</a></li>
            <li><img class="ui-li-icon" alt="Speakers" src="${ctx}/images/mobile/crystal/groupevent.png"/><a href="${ctx}/s/speakers">Speakers</a></li>
            <li><img class="ui-li-icon" alt="Speakers" src="${ctx}/images/mobile/crystal/kchart.png"/><a href="${ctx}/s/presentations">Presentations</a></li>
            <li><img class="ui-li-icon" alt="Speakers" src="${ctx}/images/mobile/crystal/identity.png"/><a href="${ctx}/s/organizers">Organizers</a></li>
            <li><img class="ui-li-icon" alt="Full Browser Version" src="${ctx}/images/icons/crystal/globe.png"/><a href="${ctx}/s/index?site_preference=normal" rel="external">Full Browser Version</a></li>
        </ul>