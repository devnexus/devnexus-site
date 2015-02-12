<%@page contentType="text/cache-manifest" %><%
response.setHeader("Cache-Control","no-cache");
response.setHeader("Pragma","no-cache");
response.setDateHeader ("Expires", 0);
%><%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %><c:set var="ctx" value="${pageContext['request'].contextPath}"/>CACHE MANIFEST
# version ${applicationCache.uuid} (${applicationCache.updatedDate}) 8

${ctx}${baseSiteUrl}/index
${ctx}${baseSiteUrl}/speakers
${ctx}${baseSiteUrl}/presentations
${ctx}${baseSiteUrl}/organizers
${ctx}${baseSiteUrl}/travel

${ctx}/img/icons/crystal/globe.png
${ctx}/img/mobile/crystal/groupevent.png
${ctx}/img/mobile/crystal/kchart.png
${ctx}/img/mobile/crystal/identity.png
${ctx}/img/icons/erenemre/twitter-03.png

${ctx}/img/logo_devnexus_300.png
${ctx}/img/sponsors/4t.png
${ctx}/img/sponsors/ALC-Logo-250.png
${ctx}/img/sponsors/Anteo.png
${ctx}/img/sponsors/apex-systems.png
${ctx}/img/sponsors/ICE.jpg
${ctx}/img/sponsors/intersect-logo.png
${ctx}/img/sponsors/jboss.png
${ctx}/img/sponsors/matrix.png
${ctx}/img/sponsors/terracotta-logo.png

${ctx}/js/mobile-min.js

<c:forEach items="${speakerList.speakers}" var="speaker">
${ctx}${baseSiteUrl}/speakers/${speaker.id}.jpg
</c:forEach>

<c:forEach items="${organizerList.organizers}" var="organizer">
${ctx}${baseSiteUrl}/organizers/${organizer.id}.jpg</c:forEach>

NETWORK:
*
