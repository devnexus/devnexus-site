<%@ include file="/WEB-INF/jsp/includes/taglibs.jsp"%>

<title>Manage Registration</title>

<!-- intro -->
<section id="about" class="module parallax parallax-3">
    <div class="container header">
        <div class="row centered">
            <div class="col-md-10 col-md-offset-1">
                <div class="top-intro travel">
                    <h4 class="section-white-title decorated"><span>Manage ${event.eventKey} Invoices</span></h4>

                </div>
            </div>
        </div>
    </div>
</section>
<!-- /intro -->

<div class="row">
    <div class="col-md-10 col-md-offset-1">
        
        There are no sales yet.
        <div>
        <a class="btn btn-default" href="${ctx}${baseSiteUrl}/admin/${event.eventKey}/index" role="button">Main Menu</a>
        </div>
    </div>
</div>

