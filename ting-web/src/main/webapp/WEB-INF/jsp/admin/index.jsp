<%@ include file="/WEB-INF/jsp/includes/taglibs.jsp"%>

<title>Main Admin Area</title>
<div id="content" class="span-22 prepend-top last ">
    <h2>Administration</h2>

    <ul>
          <li><a href="${ctx}${baseSiteUrl}/admin/events">Manage Events</a></li>
          <li><a href="${ctx}${baseSiteUrl}/admin/presentations">Manage Presentations</a></li>
          <li><a href="${ctx}${baseSiteUrl}/admin/speakers">Manage Speakers</a></li>
          <li><a href="${ctx}${baseSiteUrl}/admin/organizers">Manage Organizers</a></li>

          <li><a href="${ctx}${baseSiteUrl}/admin/update-application-cache">Update HTML5 Application Cache Manifest</a></li>
    </ul>
</div>