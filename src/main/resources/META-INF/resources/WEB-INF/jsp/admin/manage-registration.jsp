<%@ include file="/WEB-INF/jsp/includes/taglibs.jsp"%>

<title>Manage Registration</title>

<!-- intro -->
<section id="about" class="module parallax parallax-3">
	<div class="container header">
		<div class="row centered">
			<div class="col-md-10 col-md-offset-1">
				<div class="top-intro travel">
					<h4 class="section-white-title decorated"><span>Manage ${event.eventKey} Registration</span></h4>
					
				</div>
			</div>
		</div>
	</div>
</section>
<!-- /intro -->

<div class="row">
	<div class="col-md-10 col-md-offset-1">
		<table class="table table-hover">
			<thead>
				<tr>
					<th>Name</th><th>Start Date</th><th>End Date</th><th>Price</th>
				</tr>
			</thead>

			
				<tr>
					<td>Early Bird</td>
					<td>Sept. 1 2015</td>
					<td>
						Jan. 1 2016
					</td>
					<td>$300</td>
					
				</tr>
                                <tr>
					<td>Normal Price</td>
					<td>Jan. 1 2016</td>
					<td>
						Feb. 15 2016
					</td>
					<td>$400</td>
					
				</tr>
                                <tr>
					<td>Late Price</td>
					<td>Feb. 15 2016</td>
					<td>
						March 16 2016
					</td>
					<td>$900</td>
					
				</tr>
                                <tr>
					<td>T-shirt size</td>
					<td>Sept. 1 2015</td>
					<td>
						Jan. 1 2016
					</td>
					<td>$30</td>
					
				</tr>
			
		</table>
		<a class="btn btn-default" href="${ctx}${baseSiteUrl}/admin/${event.eventKey}/registration" role="button">Add Registration</a>
		<a class="btn btn-default" href="${ctx}${baseSiteUrl}/admin/${event.eventKey}/index" role="button">Main Menu</a>
	</div>
</div>

