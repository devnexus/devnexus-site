<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/includes/taglibs.jsp"%>

<title>${contextEvent.title} | Submit CFP</title>

<!-- intro -->
<section id="about" class="module parallax parallax-3">
	<div class="container header">
		<div class="row centered">
			<div class="col-md-10 col-md-offset-1">
				 <div class="top-intro travel">
					<h4 class="section-white-title decorated"><span>Call for Papers 2017!</span></h4>
					<h5 class="intro-white-lead">
						Thank you for your interest in DevNexus 2017!
					</h5>
					<p>
						We would love to review your session proposals for the South-East's
						largest developer conference. We are planning to cover a wide variety of topics around:
					</p>
					<ul class="list-inline">
						<li>Java/JavaEE/Spring</li>
						<li>HTML5 + JavaScript</li>
						<li>Data + Integration</li>
						<li>Alternative JVM Languages</li>
						<li>User Experience</li>
						<li>Cloud</li>
						<li>Agile + Tools</li>
						<li>Mobile</li>
						<li>Security</li>
					</ul>
				</div>
			</div>
		</div>
	</div>
</section>
<!-- /intro -->

<section id="speaker" class="bg-light-gray">
	<div class="container">
		<div class="row">
			<div class="col-md-8 col-md-offset-2">

				<p>
					In order to submit session proposals, you must login using
					your Google credentials. Once logged in, you will be able
					to create speakers (e.g. yourself) and one or more session
					abstracts.
				</p>

<%-- 				<form class="form-horizontal" role="form" action="<c:url value="/auth/google" />" method="POST">
					<div class="form-group">
						<div class="col-md-12">
							<button type="submit" class="btn  btn-primary  btn-block">Sign in with Google</button>
							<input type="hidden" name="scope"
								value="https://www.googleapis.com/auth/plus.login https://www.googleapis.com/auth/userinfo.email https://www.googleapis.com/auth/userinfo.profile"/>
						</div>
					</div>
				</form> --%>

				<div class="row centered">
					<div class="col-md-8 col-md-offset-2 col-xs-12" style="margin-bottom: 1em;">
						<a class="btn btn-primary btn-block" href="${ctx}/s/cfp/index">Login using Google</a>
					</div>
				</div>

				<h2 style="margin-top: 2em;" class="text-center">FAQ</h2>
				<h4>When will the CFP close?</h4>
				<p>The CFP will close on <strong>November 1, 2016</strong>.</p>
				<h4>When will I get notified whether I got accepted or not?</h4>
				<p>We will notify you by <strong>November 15, 2016</strong>.</p>
				<h4>When will the schedule be ready?</h4>
				<p>A draft schedule will be ready on <strong>November 15, 2016</strong>.</p>
				<h4>When is the Workshop day?</h4>
				<p>The workshop day is on <strong>February 20, 2017</strong>. We will have 5 rooms available,
				each accommodating 25-35 attendees.</p>
				<h4>How long are the sessions for the main conference?</h4>
				<p>
					The main conference days are <strong>February 21 &amp; 22, 2017</strong>. Breakout sessions
					(normal sessions) are <strong>75 minutes</strong> each. Keynotes
					are <strong>60 minutes</strong>.
				</p>
				<h4>Do we cover travel expenses?</h4>
				<p>
					We make that determination on a case-by-case basis. For speakers
					that are not affiliated with large organizations, we often do.
					However, if you represent a larger organization we typically don't.
					The reason is that we need to carefully watch our budget as we
					operate as non-profit and keep the ticket prices as low as possible.
					As we grow, we started to loosen the restrictions a bit but
					we rather still do the determination on a case by case basis.
				﻿</p>
				<h4>I have more questions...</h4>
				<p>
					Please don't hesitate and contact us with any other questions
					you may have at:</p>
				<p class="text-center"><strong>info at ajug.org</strong></p>
			</div>
		</div>
	</div>
</section>

