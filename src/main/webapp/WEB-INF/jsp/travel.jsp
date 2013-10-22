<%@ include file="/WEB-INF/jsp/includes/taglibs.jsp"%>
<% pageContext.setAttribute("lf", "\n"); %>

<title>DevNexus 2013 - Travel</title>

<div id="map" class="container">
    <img id="map" src="${ctx}/images/map.jpg">
</div>

<div id="travel" class="container">
    <!-- Example row of columns -->

    <div class="row">
        <div id="travelphoto" class="col-md-6">
            <img src="${ctx}/images/photo1.jpg">
            <img src="${ctx}/images/photo2.jpg">
        </div>
        <div class="col-md-6">
            <h2>Travel Information</h2>

            <p>The Cobb Galleria website includes directions to the conference center from several directions as well as
                the airport.</p>

            <p>MARTA may be used to reach the center, but the nearest bus stop is a few blocks away.</p>

            <p>The Cobb Galleria is accessible from several nearby hotels:</p>

            <p>Marriott Waverly is connected to the Center.</p>

            <p>We have reserved a block of rooms, providing a special rate of $149 to our attendees. Please use the
                following link in order to make your hotel reservation:</p>

            <h3>Make a Reservation</h3>

            <p>The group code is devdeva</p>
            <blockquote>Directions: http://bit.ly/gDzY9N<br/>
                Site: http://bit.ly/hlU1OO
            </blockquote>
            <p>Sheraton Suites is a short walk to the center.</p>
            <blockquote>Directions: http://bit.ly/fNl2Ol<br/>
                Site: http://bit.ly/fWkNVC
            </blockquote>
            <p>Embassy Suites is also a short walk to the center.</p>
            <blockquote>Directions: http://bit.ly/fKUADx
                Site: http://www.atlantagalleria.embassysuites.com
            </blockquote>
        </div>
    </div>
</div>
