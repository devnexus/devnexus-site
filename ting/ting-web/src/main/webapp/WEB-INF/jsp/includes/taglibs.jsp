<%@ include file="/WEB-INF/jsp/includes/taglibs-decorators.jsp"%>
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