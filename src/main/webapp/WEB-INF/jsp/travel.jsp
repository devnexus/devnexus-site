<%@ include file="/WEB-INF/jsp/includes/taglibs.jsp"%>
<% pageContext.setAttribute("lf", "\n"); %>

<title>DevNexus 2014 - Travel</title>

<div id="map" class="container">
	<img id="map" src="${ctx}/images/map.jpg">
</div>

<div id="travel" class="container">
	<div class="row">
		<div id="travelphoto" class="col-md-6">
			<img src="${ctx}/images/photo1.jpg">
			<img src="${ctx}/images/photo2.jpg">
		</div>
		<div class="col-md-6">
			<h2>Travel Information</h2>
			<p>The Cobb Galleria website includes <a href="http://cobbgalleria.com/destination/destinationDirections.aspx">directions to the conference center</a> from several directions as well as
				the airport.</p>

			<p><a href="http://itsmarta.com/">MARTA</a>, Atlanta's public transportation system,
				may be used to reach the center, but the nearest bus stop is a few blocks away.</p>
			<p>The Cobb Galleria is accessible from several nearby hotels:</p>

			<p><strong>Marriott Waverly</strong> is connected to the Center.</p>

			<blockquote>Directions: <a href="http://bit.ly/gDzY9N">http://bit.ly/gDzY9N</a><br/>
				Site: <a href="http://bit.ly/hlU1OO">http://bit.ly/hlU1OO</a>
			</blockquote>
			<p><strong>Sheraton Suites</strong> is a short walk to the center.</p>
			<blockquote>Directions: <a href="http://bit.ly/fNl2Ol">http://bit.ly/fNl2Ol</a><br/>
				Site: <a href="http://bit.ly/fWkNVC">http://bit.ly/fWkNVC</a>
			</blockquote>
			<p><strong>Embassy Suites</strong> is also a short walk to the center.</p>
			<blockquote>Directions: <a href="http://bit.ly/fKUADx">http://bit.ly/fKUADx</a><br/>
				Site: <a href="http://www.atlantagalleria.embassysuites.com/">http://www.atlantagalleria.embassysuites.com/</a>
			</blockquote>
		</div>
	</div>
</div>
