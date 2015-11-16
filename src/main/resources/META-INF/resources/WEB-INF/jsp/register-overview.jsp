<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/includes/taglibs.jsp" %>

<head>

	<style>
		.price-box {
			margin-bottom: 1em;
		}

		.price-box-inner {
			padding: 0;

			border-radius:4px;
			-moz-border-radius:4px;
			-webkit-border-radius:4px;
			-webkit-box-shadow: 1px 1px 2px 0px rgba(50,50,50,0.25);
			-moz-box-shadow:    1px 1px 2px 0px rgba(50,50,50,0.25);
			box-shadow:         1px 1px 2px 0px rgba(50,50,50,0.25);
			overflow: hidden;
		}

		.price-box-disabled {
			opacity: .7;
		}

		.price-box-disabled .price-box-aux {
			background-color: #cccccc;
		}

		.price-box-main {
			background-color: #EEEEEE;
			padding-top: 2em;
			padding-bottom: 2em;
		}

		.price-box-main .btn {
			width: 300px;
			margin-top: 20px;
		}
		.price-box-aux {
			background-color: #f79331;
			padding-top: 0.5em;
			padding-bottom: 0.5em;
		}

		.package-details-box {
			padding: 0;

			border-radius:4px;
			-moz-border-radius:4px;
			-webkit-border-radius:4px;
			-webkit-box-shadow: 1px 1px 2px 0px rgba(50,50,50,0.25);
			-moz-box-shadow:    1px 1px 2px 0px rgba(50,50,50,0.25);
			box-shadow:         1px 1px 2px 0px rgba(50,50,50,0.25);
			overflow: hidden;
		}

		.package-details-header {
			background-color: #f79331;
			padding-top: 1em;
			padding-bottom: 1em;
		}

		.package-details-header h2 {
			margin-top: 0;
			margin-bottom: 0;
		}

		.package-details {
			padding-top: 0.5em;
			padding-bottom: 0.5em;
		}
		.package-offset {
			background-color: #eeeeee;
		}
	</style>

</head>

<!-- intro -->
<section id="about" class="module parallax parallax-3">
    <div class="container header">
        <div class="row centered">
            <div class="col-md-10 col-md-offset-1">
                <div class="top-intro travel">
                    <h4 class="section-white-title decorated"><span>Register for ${event.title}</span></h4>
                    <h5 class="intro-white-lead">1600+ attendees, 90+ Speakers, 12+ tracks, 2 Conference Days + 1 Workshop Day.</h5>
                </div>
            </div>
        </div>
    </div>
</section>
<!-- /intro -->

<section id="speaker" class="bg-light-gray">
	<div class="container">
		<div class="row price-box text-center price-box">
			<div class="col-md-8 col-md-offset-2 price-box-inner">
				<div class="price-box-main">
					<h2 >STANDARD PRICE: <strong>$340</strong></h2>
					<a href="${baseSiteUrl}/register" class="btn btn-primary">Buy</a>
				</div>
				<div class="price-box-aux">
					<div>Valid from 2015/11/16 to 2016/1/10</div>
				</div>
			</div>
		</div>
		<div class="row price-box text-center price-box">
			<div class="col-md-8 col-md-offset-2 price-box-inner">
				<div class="price-box-main">
					<h2 >GROUP PRICE (5+ Attendees): <strong>$300</strong></h2>
					<a href="${baseSiteUrl}/register" class="btn btn-primary">Buy</a>
				</div>
				<div class="price-box-aux">
					<div>Valid from 2015/12/5 to 2016/1/10</div>
				</div>
			</div>
		</div>
		<div class="row price-box text-center">
			<div class="col-md-8 col-md-offset-2 price-box-inner">
				<div class="price-box-main">
					<h2 >WORKSHOPS + MAIN CONF TICKET: <strong>$535 - $635</strong></h2>
					<a href="${baseSiteUrl}/register" class="btn btn-primary">Buy</a>
				</div>
				<div class="price-box-aux">
					<div>Workshops can only be purchased in combination <strong>with main conference ticket</strong>.</div>
				</div>
			</div>
		</div>
		<div class="row price-box text-center">
			<div class="col-md-8 col-md-offset-2 price-box-inner">
				<div class="price-box-main">
					<h2 >GROUP (5+) WORKSHOP + <br/> MAIN CONF COMBO: <strong>$495 - $595</strong></h2>
					<div>Email us at info@ajug.org to purchase</div>
				</div>
				<div class="price-box-aux">
					<div>Workshops can only be purchased in combination <strong>with main conference ticket</strong>.</div>
				</div>
			</div>
		</div>
		<div class="row price-box text-center">
			<div class="col-md-8 col-md-offset-2 price-box-inner">
				<div class="price-box-main">
					<h2 >STUDENT PRICE: <strong>$200</strong></h2>
					<a href="${baseSiteUrl}/register" class="btn btn-primary">Buy</a>
				</div>
				<div class="price-box-aux">
					<div>Email us to get the code at info@ajug.org</div>
				</div>
			</div>
		</div>
		<div class="row price-box text-center price-box-disabled">
			<div class="col-md-8 col-md-offset-2 price-box-inner">
				<div class="price-box-main">
					<h2 >LATE PRICE: <strong>$440</strong></h2>
				</div>
				<div class="price-box-aux">
					<div>Valid from 2016/1/11 to 2016/1/29</div>
				</div>
			</div>
		</div>
		<div class="row price-box text-center price-box-disabled">
			<div class="col-md-8 col-md-offset-2 price-box-inner">
				<div class="price-box-main">
					<h2 >LATE GROUP (5+ Attendees): <strong>$400</strong></h2>
				</div>
				<div class="price-box-aux">
					<div>Valid from 2016/1/11 to 2016/1/29</div>
				</div>
			</div>
		</div>
		<div class="row text-center">
			<div class="col-md-8 col-md-offset-2 package-details-box">
				<div class="package-details-header">
					<h2>The Main Conference Package Includes:</h2>
				</div>
				<div class="package-details">Awesome content in <strong>12+ Tracks</strong> on Feb 16th &amp; 17th</div>
				<div class="package-details package-offset"><strong>Keynotes</strong></div>
				<div class="package-details"><strong>100+ sessions</strong></div>
				<div class="package-details package-offset">Super yummy <strong>food and snacks</strong> all day long</div>
				<div class="package-details">Awesome <strong>swag</strong> that you will use</div>
				<div class="package-details package-offset">Chance to win sweet <strong>raffle prizes</strong></div>
				<div class="package-details">Globally recognized speakers</div>
				<div class="package-details package-offset">Mix and mingle <strong>Happy Hour</strong> on Feb 16th</div>
			</div>
		</div>

	</div>
</section>
