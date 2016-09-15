<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/includes/taglibs.jsp" %>

<title>${contextEvent.title} | Sponsors</title>

<!-- sponsors -->
<section class="container-fluid conference-information" >


    <h1 class="featured-header">
        SPONSORS
    </h1>
    <div class="row">
        <div class="col-md-12">
            <c:forEach items="${sponsorList.sponsors}" var="sponsor" varStatus="status">
                <c:choose>
                    <c:when test="${sponsor.sponsorLevel.name ne sponsorLevel}">
                        <c:if test="${not status.first}"></div></div></c:if>
                        <c:set value="${sponsor.sponsorLevel.name}" var="sponsorLevel"/>
                    <div class=" row top-intro sponsors ${sponsor.sponsorLevel.cssStyleClass}">
                        <div class="col-sm-12 col-md-3"><h4 class="decorated"><span>
                                <c:choose>
                                    <c:when test="${sponsorList.sponsorLevelCount.get(sponsor.sponsorLevel) > 1}">
                                        ${sponsorLevel}s
                                    </c:when>
                                    <c:otherwise>
                                        ${sponsorLevel}
                                    </c:otherwise>
                                </c:choose>
                                </span></h4></div><div class="col-sm-12 col-md-9">
                        <a href="${sponsor.link}"><img src="${sponsorList.logos[sponsor.id]}" alt="${sponsor.name}" title="${sponsor.name}"></a>
                        <c:if test="${status.last}"></div></div></c:if>
                    </c:when>
                    <c:otherwise>
                    <a href="${sponsor.link}"><img src="${sponsorList.logos[sponsor.id]}" alt="${sponsor.name}" title="${sponsor.name}"></a>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
    </div>
</div>
</section>

<jsp:include page="includes/questions.jsp"/>

<content tag='bottom'>
    <script type="text/javascript">
        $(document).ready(function () {

            var $container = $('#speaker');

            $container.imagesLoaded(function () {
                $container.masonry({
                    itemSelector: '.masonryitem',
                    columnWidth: '.masonryitem',
                    isAnimated: true
                });
            });

            $container.imagesLoaded(function () {
                var hash = window.location.hash;
                console.log('Hash is: ' + hash);
                if (!(hash === '')) {
                    console.log('Scroll: ' + hash);
                    $('html, body').animate({scrollTop: $(hash).offset().top - 100}, 'slow');
                }
            });

        });
    </script>
</content>

