<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/includes/taglibs.jsp" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width" />

        <title>DevNexus 2015</title>

        <meta property="og:title" content="DevNexus 2015">
        <meta property="og:type" content="company">
        <meta property="og:site_name" content="DevNexus">
        <meta property="og:url" content="http://devnexus.com/s/index">
        <meta property="og:image" content="">
        <meta property="og:image" content="">
        <meta content='devnexus, conference, tech conference, southeast, 2015, atlanta conference' name='keywords' />
        <meta property="og:description" content="">

        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="">
        <meta name="author" content="">
        <link rel="shortcut icon" href="${ctx}/favicon.png">

        <!-- vendor CSS -->
        <link rel="stylesheet" type="text/css" href="${ctx}/wro/all.css" />

        <!-- fonts: external links -->
        <link href='http://fonts.googleapis.com/css?family=Quicksand' rel='stylesheet' type='text/css'>

        <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
        <![endif]-->
        <style type="text/css">
            #chart-tooltip {
                position: absolute;
                width: 400px;
                height: auto;
                padding: 5px;
                z-index: 9999999;
                background-color: #ffffff;
                -webkit-border-radius: 4px;
                -moz-border-radius: 4px;
                border-radius: 4px;
                -webkit-box-shadow: 4px 4px 10px rgba(0, 0, 0, 0.2);
                -moz-box-shadow: 4px 4px 10px rgba(0, 0, 0, 0.2);
                box-shadow: 4px 4px 10px rgba(0, 0, 0, 0.2);
                pointer-events: none;
            }

            #chart-tooltip.hidden {
                display: none;
            }

            #chart-tooltip p {
                margin: 0;
                font-family: sans-serif;
                font-size: 16px;
                line-height: 20px;
            }
        </style>
    </head>
    <body>
        <c:url var="homeUrl" value="${baseSiteUrl}/index"/>
        <c:url var="speakersUrl" value="${baseSiteUrl}/speakers"/>
        <c:url var="presentationsUrl" value="${baseSiteUrl}/presentations"/>
        <c:url var="scheduleUrl" value="${baseSiteUrl}/schedule"/>
        <c:url var="organizersUrl" value="${baseSiteUrl}/organizers"/>
        <c:url var="registrationUrl" value="https://devnexus2015.eventbrite.com"/>
        <c:url var="pastConferencesUrl" value="${baseSiteUrl}/past-conferences"/>
        <c:url var="privacyPolicyUrl" value="${baseSiteUrl}/privacy-policy"/>
        <c:url var="codeOfConductUrl" value="${baseSiteUrl}/code-of-conduct"/>

        <!-- Navigation -->
        <nav class="navbar navbar-custom navbar-fixed-top">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="${homeUrl}"><img src="${assetsUrl}/img/DevNexus_logo_small.png"></a>
            </div>

            <div class="collapse navbar-collapse">
                <ul class="nav navbar-nav">
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">Presentations <b class="caret"></b></a>
                        <ul class="dropdown-menu">
                            <li><a href="${presentationsUrl}?order=track">Presentations by Track</a></li>
                            <li><a href="${presentationsUrl}?order=room">Presentations by Room</a></li>
                            <li><a href="${presentationsUrl}?order=name">Presentations by Name</a></li>
                            <li><a href="${ctx}/s/tracks">Show Tracks</a></li>
                            <li><a href="${ctx}/s/tags">Show Presentation Tags</a></li>
                            <li><a href="${ctx}/s/rooms">Show Rooms</a></li>
                        </ul>
                    </li>
                    <li><a class="" href="${speakersUrl}">Speakers</a></li>
                    <li><a class="" href="${scheduleUrl}">Schedule</a></li>
                    <li><a class="page-scroll" href="${homeUrl}#travel">Travel</a></li>
                </ul>
            </div>
        </nav>
        <div id="chart-tooltip" class="hidden">
            <p><strong><span id="value">123</span> Attendees</strong></p>
            <p><span id="session-title">title</span></p>
            <p><span id="session-speaker">speaker</span></p>
        </div>
        <!-- Intro Header -->
        <section id="index--intro" class="module parallax index--parallax-1">
            <div class="container header">
                <div class="row intro-body">
                    <div class="col-md-8 col-md-offset-2">
                        <img class="logo" src="${assetsUrl}/img/DevNexus_logo_large.png" alt="DevNexus Logo">
                        <p class="intro-text">THE PROFESSIONAL DEVELOPERS CONFERENCE<br>ATLANTA, GA - FEBRUARY 15-17, 2016</p>
                        <div class="icon">
                            <i class="fa fa fa-twitter"></i>
                        </div><!--//icon-->
                        <p class="intro-text"><a href="https://twitter.com/devnexus">#devnexus</a></p>
                    </div>
                </div>
            </div>
        </section>

        <!-- intro -->
        <section id="about" class="white">
            <div class="container">
                <div class="row centered">
                    <div class="col-md-10 col-md-offset-1">
                        <div id="tagline">Join the <span>&lt;dev/&gt;</span>olution.</div>
                    </div>
                    <div class="col-md-8 col-md-offset-2">

                        <h1>Convince your manager</h1>
                        <p>
                            You're eager to attend DEVNEXUS, and why not? It is the most awesome professional developer
                            conference in the South East. You will meet and mingle with peers, world renowned speakers,
                            technology experts, published authors and OEM/Open Source core committers. DEVNEXUS  has
                            the potential to help you work smarter, and support your business and career goals.
                        </p>

                        <p>Before the conference, you'll receive a notification that the full schedule has been published so
                            you can start to plan your participation. Post about the sessions you attend on social mediaâyour
                            colleagues will see what you're learning, and you can review your posts after the conference to
                            recap. The official Twitter conference hashtag is <a href="https://twitter.com/hashtag/devnexus">#DEVNEXUS</a>.   Check out the 2015 event; Grab
                            the <a href="https://github.com/devnexus/devnexus2015">presentation slides from GitHub</a> or check out the download links under <a href="https://www.devnexus.com/s/presentations">presentations</a>. The
                            videos of the sessions are also  posted on <a href="http://www.dzone.com/">DZone</a> .</p>

                        <p>After the conference: create a report and share what you learned. You will receive an email with

                            links to all the conference session slides and you will receive notifications when all of the videos

                            have been published. Use this information and the conference Trip Report Template to create a

                            report detailing what you learned. Supplement your own materials with speaker slides and

                            keynote videos, posted during and after the conference. Search for news articles, blog posts, and

                            #DEVNEXUS tweets for additional reference materials to include.</p>

                        <h2>The Top 5 Reasons to Attend DEVNEXUS 2016</h2>

                        <ol>
                            <li><p>Stack exposure. At DEVNEXUS we don't just look at the full stack, but all the

                                    possibilities. You'll find out which technologies work best together and which have the

                                    capabilities you need. Learn from experts--see examples, learn from their mistakes, ask

                                    questions in person, and benefit from their wisdom. You'll be exposed to different stacks,

                                    get to test drive features, learn other programming paradigms, and walk away with a list

                                    of new strategies and tools to try back in the workplace.</p></li>

                            <li><p>Tracks. With DEVNEXUS's track based format, you can tackle your most pressing

                                    problems. Are you focusing on performance? Developing a game plan for mobile?

                                    Needing to scale your web application for the next 10,000 to 1,000,000 customers? We

                                    address some of the most common and complex problems software engineers face and

                                    give you ideas and solutions you can take back with you to fix it.</p></li>

                            <li><p>Uninterrupted focus. It's a huge advantage to be away from work and constant

                                    interruptions, acquiring the knowledge you need to move projects forward. At

                                    DEVNEXUS, you can ask questions, compare notes with fellow attendees, and immerse

                                    yourself in the subject matter you've set out to learn. And with a one day

                                    workshop/training day, you can delve as deeply into a topic as you need to.</p></li>

                            <li><p>Stay current. Technology changes fast. Keep pace with new developments, best

                                    practices, products, services, and trends at DEVNEXUS. From start-ups to some of the

                                    world's most successful companies, see how others are incorporating enterprise and

                                    open source technologies to achieve their business goals. Get your hands on some of

                                    those tools and technologies you've been hearing about, but haven't had the chance to

                                    try (Rust anyone?).</p></li>

                            <li><p>10 years of DEVNEXUS excellence. Through our long history of providing knowledge to

                                    software engineers, developers, IT practitioners, and others, we've learned what's

                                    important, which subjects go together, and how to teach them. Every year the number of

                                    quality proposals we receive for DEVNEXUS far exceeds the number of speaking slots

                                    available, making it easy for us to select the best and most relevant topics for

                                    DEVNEXUS attendees.</p></li></ol>

                    </div>
                </div>
            </div>
        </section>

        <!-- questions -->
        <section class="white">
            <div class="top-intro questions">
                <h4>Questions?</h4>
                <h3>Contact us at info@ajug.org</h3>
            </div>
        </section>

        <!-- footer -->
        <footer id="colophon" class="site-footer" role="contentinfo">
            <div class="footer-wrapper container">
                <div class="row">
                    <div class="sidebar footer-sidebar clearfix">
                        <div class="col-lg-3 col-sm-3 col-md-3 widget-1 footer-widget first footer-widget">
                            <div id="meta" class="footer-widget">
                                <img src="${assetsUrl}/img/DevNexus_logo_small.png" alt="DevNexus logo small">
                                <ul class="footer-social">
                                    <li class=""><a href="https://twitter.com/devnexus" target="_blank"><i class="fa fa-twitter"></i>#devnexus</a></li>
                                </ul>
                            </div>
                        </div>
                        <div class="col-lg-3 col-sm-3 col-md-3 widget-1 footer-widget footer-widget">
                            <div id="tweet" class="footer-widget">
                                <h3 class="footer-title">Learn More</h3>
                                <ul class="footer-social">
                                    <li class=""><a href="${organizersUrl}" target="_blank">Organizers</a></li>
                                    <li class=""><a href="${pastConferencesUrl}">Past Conferences</a></li>
                                    <li class=""><a href="${ctx}/static/2015/files/promo/devnexus-2015-sponsorship-options.pdf" target="_blank">Sponsorship (PDF)</a></li>
                                </ul>
                            </div>
                        </div>
                        <div class="col-lg-3 col-sm-3 col-md-3 widget-1 footer-widget footer-widget">
                            <div id="tweet2" class="footer-widget">
                                <h3 class="footer-title">DEVNEXUS 2015</h3>
                                <ul class="footer-social">
                                    <li class=""><a href="${scheduleUrl}">Schedule</a></li>
                                    <li class=""><a href="${speakersUrl}">Speakers</a></li>
                                    <li class=""><a href="${presentationsUrl}">Presentations</a></li>
                                </ul>
                            </div>
                        </div>
                        <div class="col-lg-3 col-sm-3 col-md-3 widget-1 footer-widget last footer-widget">
                            <div id="get_touch" class="widget widget_get_touch">
                                <button disabled="disabled" class="btn btn-primary registerButton">Sold Out</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="container">
                <div class="row">
                    <div class="col-md-10-offset-1 legal">
                        <p>&copy; 2004-2015 <a href="http://www.ajug.org/">Atlanta Java Users Group</a> (AJUG)
                            <a href="${privacyPolicyUrl}"><span class="label">Privacy Policy</span></a>
                            <a href="${codeOfConductUrl}"><span class="label">Code of Conduct</span></a>
                        </p>
                    </div>
                </div>
            </div>
        </footer>
        <!-- /footer -->

        <!-- javascipt -->
        <script src="${ctx}/wro/all.js"></script>
        <%-- 	<script src="${assetsUrl}/js/jquery1.11.1.min.js"></script>
                <script src="${assetsUrl}/js/jquery.modernizr.js"></script>
                <script src="${assetsUrl}/js/jquery.easing.min.js"></script>
                <script src="${assetsUrl}/js/bootstrap.min.js"></script> --%>
        <script>
            "use strict";
            (function (i, s, o, g, r, a, m) {
                i['GoogleAnalyticsObject'] = r;
                i[r] = i[r] || function () {
                    (i[r].q = i[r].q || []).push(arguments)
                }, i[r].l = 1 * new Date();
                a = s.createElement(o),
                        m = s.getElementsByTagName(o)[0];
                a.async = 1;
                a.src = g;
                m.parentNode.insertBefore(a, m)
            })(window, document, 'script', '//www.google-analytics.com/analytics.js', 'ga');

            ga('create', 'UA-44984422-1', 'devnexus.com');
            ga('send', 'pageview');

            $(function () {
                $(".video-container").each(function () {
                    var videoId = this.id;
                    $(document).delegate('#video-play-button', 'click', function () {
                        var iframe_url = 'https://www.youtube.com/embed/' + videoId + '?autoplay=1&autohide=1';
                        var iframe = $('<iframe/>', {'frameborder': '0', 'src': iframe_url})
                        $('#' + videoId).html(iframe).addClass('loaded');
                        $('#video-play-button').hide();
                    });
                });

                $("#tagline").fitText().fitText(1.05);

                var container = d3.select("#d3chart")

                var width = $("#d3chart").width();
                var height = 120

                var svg = container.append("svg");
                svg.attr("height", height);
                svg.attr("width", width);
                svg.attr("viewBox", "0 0 " + width + " " + height);
                svg.attr("perserveAspectRatio", "xMinYMid");

                $(window).on("resize", function () {
                    svg.attr("width", $("#d3chart").width());
                    svg.attr("height", 120);
                });

                var barWidth = 4;

                function processData(data) {

                    var maxValue = d3.max(data, function (el) {
                        return parseInt(el.attendees)
                    });
                    var minValue = d3.min(data, function (el) {
                        return parseInt(el.attendees)
                    });
                    var meanValue = d3.mean(data, function (el) {
                        return parseInt(el.attendees)
                    });

                    var xScale = d3.scale.linear().domain([0, data.length]).range([0, width]);
                    var yScale = d3.scale.linear().domain([0, maxValue]).range([0, height]);

                    svg.selectAll('rect').data(data).enter()
                            .append('rect')
                            .attr('width', barWidth)
                            .attr('height', function (d) {
                                return yScale(d.attendees);
                            })
                            .attr("x", function (d, i) {
                                return xScale(i)
                            })
                            .attr("y", function (d, i) {
                                return height - yScale(d.attendees)
                            })
                            .style("fill",Â function (d, i) {
                                return d.color
                            })

                            .on("mouseover", function (d) {
                                d3.select(this).style("fill", "orange");
                                d3.select("#chart-tooltip")
                                        .style("left", $('#d3chart').offset().left + ($('#d3chart').width() / 2) - ($('#chart-tooltip').width() / 2) + "px")
                                        .style("top", $('#d3chart').offset().top + "px")

                                d3.select("#value").text(d.attendees);
                                d3.select("#session-title").text(d.session_title);
                                d3.select("#session-speaker").text(d.speaker);
                                d3.select("#chart-tooltip")
                                        .style("opacity", 0)
                                        .classed("hidden", false)
                                        .transition()
                                        .duration(250)
                                        .style("opacity", 1);
                            })
                            .on("mouseout", function (d) {
                                d3.select("#chart-tooltip")
                                        .transition()
                                        .duration(250)
                                        .style("opacity", 0)
                                d3.select(this)
                                        .transition()
                                        .duration(250)
                                        .style("fill", function (d, i) {
                                            return d.color
                                        });
                            })
                            .style("opacity",Â 1)
                            .append("title").text(function (d) {
                        return d.session_title + ': ' + d.attendees + ' attendees.';
                    });

                }

                d3.csv('${ctx}/static/2015/devnexus-stats.csv', function (data) {
                    processData(data);
                });

            });

        </script>
    </body>
</html>
