<%@ include file="/WEB-INF/jsp/includes/taglibs.jsp"%>

<title>Manage Registration</title>

<!-- intro -->
<section id="about" class="module parallax parallax-3 index--parallax">
	<div class="container header">
		<div class="row centered">
			<div class="col-md-10 col-md-offset-1">
				<div class="top-intro travel">
					<h4 class="section-white-title decorated"><span>Registration Overview</span></h4>
					<h5 class="intro-white-lead">${event.title}
					<c:if test="${event.current}">
						(current)
					</c:if>
					</h5>
				</div>
			</div>
		</div>
	</div>
</section>
<!-- /intro -->

<div class="row">
	<div class="col-md-10 col-md-offset-1">
		<div class="row">
			<h2>Sales By Ticket Type: </h2>
			<c:forEach items="${dashboard.salesByType}" var="type">
				<div class="row">
					<div class="col-md-6"><c:out value="${type.key.label}" /></div>
					<div class="col-md-2"><c:out value="${type.value}" /></div>
				</div>
			</c:forEach>
		</div>

		<div class="row">
			<h2>Totals: </h2>
			<div class="row">
				<div class="col-md-6">Tickets Sold</div>
				<div class="col-md-2"><c:out value="${dashboard.totalTicketsSold}" /></div>
			</div>
			<div class="row">
				<div class="col-md-6">Dollar Total</div>
				<div class="col-md-2"><c:out value="${dashboard.totalDollars}" /></div>
			</div>
		</div>

		<div class="row">
			<h2>Problems: </h2>
			<div class="row">
				<div class="col-md-6">Orders which did not complete PayPal</div>
				<div class="col-md-2"><c:out value="${fn:length(dashboard.inCompletePaypalOrders)}" /></div>
			</div>
			<div class="row">
				<div class="col-md-6">Orders requesting invoice</div>
				<div class="col-md-2"><c:out value="${fn:length(dashboard.ordersRequestingInvoice)}" /></div>
			</div>
		</div>

		<div class="row" style="margin-top: 1em; margin-bottom: 1em;">
			<a class="btn btn-default" href="${ctx}${baseSiteUrl}/admin/${event.eventKey}/index" role="button">Main Menu</a>
		</div>
	</div>
</div>
