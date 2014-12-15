<%@page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8"
        trimDirectiveWhitespaces="true" deferredSyntaxAllowedAsLiteral="true"%>

<%
response.setHeader("Cache-Control","no-cache");
response.setHeader("Pragma","no-cache");
response.setHeader("Expires","0");
%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>

<c:set var="ctx" value="${pageContext['request'].contextPath}"/>
<c:set var="baseSiteUrl" value="/s"/>
<c:set var="siteUrl" value="${ctx}${baseSiteUrl}"/>
<c:set var="assetsUrl" value="${ctx}/assets"/>
