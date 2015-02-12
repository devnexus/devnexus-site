<%@page contentType="text/cache-manifest" %><%
response.setHeader("Cache-Control","no-cache");
response.setHeader("Pragma","no-cache");
response.setDateHeader ("Expires", 0);
%><%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %><c:set var="ctx" value="${pageContext['request'].contextPath}"/>CACHE MANIFEST
# version ${applicationCache.uuid} (${applicationCache.updatedDate}) 38

${ctx}/
${ctx}/index.jsp
${ctx}${baseSiteUrl}/index
${ctx}${baseSiteUrl}/speakers
${ctx}${baseSiteUrl}/presentations
${ctx}${baseSiteUrl}/organizers
${ctx}${baseSiteUrl}/travel
${ctx}${baseSiteUrl}/schedule

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

${ctx}/js/header.js
${ctx}/js/lib.js

<c:forEach items="${speakerList.speakers}" var="speaker">
${ctx}${baseSiteUrl}/speakers/${speaker.id}.jpg</c:forEach>

<c:forEach items="${organizerList.organizers}" var="organizer">
${ctx}${baseSiteUrl}/organizers/${organizer.id}.jpg</c:forEach>

${ctx}/img/icons/icondock/facebook.png
${ctx}/img/icons/icondock/twitter.png

NETWORK:
*
