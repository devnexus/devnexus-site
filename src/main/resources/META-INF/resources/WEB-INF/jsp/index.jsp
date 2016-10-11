<%@ include file="/WEB-INF/jsp/includes/taglibs.jsp" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width" />

        <title>${contextEvent.title} | Main</title>

        <meta property="og:title" content="DevNexus 2017">
        <meta property="og:type" content="company">
        <meta property="og:site_name" content="DevNexus">
        <meta property="og:url" content="http://devnexus.com/s/index">
        <meta property="og:image" content="">
        <meta property="og:image" content="">
        <meta content='devnexus, conference, tech conference, southeast, 2017, atlanta conference' name='keywords' />
        <meta property="og:description" content="">

        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="">
        <meta name="author" content="">
        <link rel="shortcut icon" href="${ctx}/favicon.png">

        <!-- vendor CSS -->
        <link rel="stylesheet" type="text/css" href="${ctx}/wro/all.css" />

        <!-- fonts: external links -->
        <link href='//fonts.googleapis.com/css?family=Quicksand' rel='stylesheet' type='text/css'>

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
        <c:url var="aboutUrl" value="${baseSiteUrl}/about"/>
        <c:url var="socialUrl" value="${baseSiteUrl}/social"/>
        <c:url var="sponsorsUrl" value="${baseSiteUrl}/sponsors"/>
        <c:url var="conferenceInfoUrl" value="${baseSiteUrl}/conference-info"/>
        <c:url var="registrationUrl" value="${baseSiteUrl}/register"/>
        <c:url var="pastConferencesUrl" value="${baseSiteUrl}/past-conferences"/>
        <c:url var="privacyPolicyUrl" value="${baseSiteUrl}/privacy-policy"/>
        <c:url var="codeOfConductUrl" value="${baseSiteUrl}/code-of-conduct"/>
        <c:url var="cfpUrl" value="${baseSiteUrl}/cfp"/>
        <c:url var="facebookUrl" value="https://www.facebook.com/devnexus"/>
        <c:url var="twitterUrl" value="https://www.twitter.com/devnexus"/>
        <c:url var="instagramUrl" value="https://www.instagram.com/devnexus/"/>
        <c:url var="googlePlusUrl" value="https://plus.google.com/+devnexus-conference"/>

        <section class="hero">
            <div>
                <ul class="list-inline hero-social">
                    <li><a href="https://www.facebook.com/atlantajug/" target="_blank"><img src="${ctx}/assets/img/facebook-logo-button.png"/></a></li>
                    <li><a href="https://twitter.com/devnexus" target="_blank"><img src="${ctx}/assets/img/twitter-logo-button.png"/></a></li>
                    <li><a href="https://www.flickr.com/search/?sort=date-taken-desc&safe_search=1&text=devnexus&view_all=1" target="_blank"><img src="${ctx}/assets/img/instagram-logo.png"/></a></li>
                    <li><a href="https://plus.google.com/+devnexus-conference" target="_blank"><img src="${ctx}/assets/img/google-plus-1.png"/></a></li>
                </ul>

                <ul class="list-inline hero-date-location">
                    <li><img src="${ctx}/assets/img/calendar.png"/> 22-24 FEB</li>
                    <li><img src="${ctx}/assets/img/location.png"/> ATLANTA, GA</li>
                </ul>

                <h1><img src="${ctx}/assets/img/dev-nexus-logo-large.png" alt="DevNexus"/></h1>
                <h2>Join the <span>&lt;dev/&gt;</span>olution</h2>

                <a class="btn hero-btn-register" style="background-color: #6fa133" href="${cfpUrl}">CALL FOR PAPERS</a><br/><br/>
                <a class="btn hero-btn-register" href="${registrationUrl}">REGISTER NOW</a>

            </div>
        </section>


        <%@ include file="/WEB-INF/jsp/includes/navigation.jsp" %>

        <div class="container-fluid" >


            <div class="row marketing-panel">
                <div class="col-md-3 what-is">
                    <div>
                        <h1>WHAT IS DEVNEXUS?</h1>
                        <ul class="list-unstyled">
                            <li>PROFESSIONAL</li>
                            <li>DEVELOPERS</li>
                            <li>CONFERENCE</li>
                        </ul>
                        <p>Find out what makes DEVNEXUS special from attendees at the 2016 event.</p>
                    </div>
                </div>
                <div  id="J7XpqF8rHS8" class="col-md-9 video-container preview">
                    <a class="center-block" href="javascript:return false;">
                        <img id="video-play-button" class="play img-responsive center-block play-button" src="${ctx}/assets/img/play-button.png" alt="Play Promo Video"/>
                    </a>
                </div>
            </div>

            <div class="row">
                <div class="col-xs-12">
                    <div class="copy">
                        <p>The goal of the Devnexus Conference is to connect developers from all over the world, provide affordable education, and promote Open Source values.</p>
                    </div>
                </div>
            </div>

            <div class="row stats">

                <div class="col-lg-3 col-sm-6">
                    <img src="${ctx}/assets/img/multiple-users-silhouette.png"/>
                    <p>2000</p>
                    <img src="${ctx}/assets/img/line.png"/>
                    <br>
                    <p class="trump">DEVELOPERS</p>
                </div>

                <div class="col-lg-3 col-sm-6">
                    <img src="${ctx}/assets/img/settings.png"/>
                    <p>10</p>
                    <img src="${ctx}/assets/img/line.png"/>
                    <br>
                    <p class="trump">WORKSHOPS</p>
                </div>

                <div class="col-lg-3 col-sm-6">
                    <img src="${ctx}/assets/img/folder.png"/>
                    <p>14</p>
                    <img src="${ctx}/assets/img/line.png"/>
                    <br>
                    <p class="trump">TRACKS</p>
                </div>

                <div class="col-lg-3 col-sm-6">
                    <img src="${ctx}/assets/img/microphone.png"/>
                    <p>120+</p>
                    <img src="${ctx}/assets/img/line.png"/>
                    <br>
                    <p class="trump">PRESENTATIONS</p>
                </div>

            </div><!-- stats -->

            <div class="row">

                <h1 class="featured-header">
                    FEATURED SPEAKERS
                </h1>

                <div class="speakers">

                    <div class="col-sm-6 col-md-4 col-lg-3">
                        <div class="thumbnail">
                            <img class="img-responsive" src="${ctx}/assets/img/Gupta_Arun.png" alt="Arun Gupta">
                            <div class="caption">
                                <h3>Arun Gupta</h3>
                                <p>VP of developer advocacy</p>
                                <p>Couchbase</p>
                            </div>
                        </div>
                    </div>


                    <div class="col-sm-6 col-md-4 col-lg-3">
                        <div class="thumbnail">
                            <img class="img-responsive" src="${ctx}/assets/img/Markus_Eisele.jpg" alt="Markus Eisele">
                            <div class="caption">
                                <h3>Markus Eisele</h3>
                                <p>Java Champion</p>
                                <p>Lightbend, Inc.</p>
                            </div>
                        </div>
                    </div>

                    <div class="col-sm-6 col-md-4 col-lg-3">
                        <div class="thumbnail">
                            <img class="img-responsive" src="${ctx}/assets/img/Deane_Jeremy.jpg" alt="Jeremy Deane">
                            <div class="caption">
                                <h3>Jeremy Deane</h3>
                                <p>Software Engineering Aficionado</p>
                                <p>&nbsp;</p>
                            </div>
                        </div>
                    </div>


                    <div class="col-sm-6 col-md-4 col-lg-3">
                        <div class="thumbnail">
                            <img class="img-responsive" src="${ctx}/assets/img/VanCura_Heather.png" alt="Heather VanCura">
                            <div class="caption">
                                <h3>Heather VanCura</h3>
                                <p>Community Builder, Java Connoisseur</p>
                                <p>Women & Girls in Tech, Open Source, Fitness</p>
                            </div>
                        </div>
                    </div>


                    <div class="col-sm-6 col-md-4 col-lg-3">
                        <div class="thumbnail">
                            <img class="img-responsive" src="${ctx}/assets/img/Jaeckel_Marlene.png" alt="Marlene Jaeckel">
                            <div class="caption">
                                <h3>Marlene Jaeckel</h3>
                                <p>Founder</p>
                                <p>Polyglot Programming</p>
                            </div>
                        </div>
                    </div>

                    <div class="col-sm-6 col-md-4 col-lg-3">
                        <div class="thumbnail">
                            <img class="img-responsive" src="${ctx}/assets/img/ray_tsang.jpg" alt="Ray Tsang">
                            <div class="caption">
                                <h3>Ray Tsang</h3>
                                <p>Developer Advocate</p>
                                <p>Google Cloud Platform</p>
                            </div>
                        </div>
                    </div>


                    <div class="col-sm-6 col-md-4 col-lg-3">
                        <div class="thumbnail">
                            <img class="img-responsive" src="${ctx}/assets/img/matt_rasible.jpg" alt="Matt Raible">
                            <div class="caption">
                                <h3>Matt Raible</h3>
                                <p>Web Architecture Consultant</p>
                                <p>Raible Designs</p>
                            </div>
                        </div>
                    </div>

                    <div class="col-sm-6 col-md-4 col-lg-3">
                        <div class="thumbnail">
                            <img class="img-responsive" src="${ctx}/assets/img/burr_sutter.jpg" alt="Burr Sutter">
                            <div class="caption">
                                <h3>Burr Sutter</h3>
                                <p>Sr Product Manager</p>
                                <p>Red Hat</p>
                            </div>
                        </div>
                    </div>


                </div><!-- speakers -->

                <a href="${speakersUrl}" class="btn btn-square btn-square btn-speakers center-block">SEE ALL SPEAKERS</a>
            </div>


            <div class="row call-to-action space">
                <div class="col-xs-12">
                    <h1>Space Is <span class="trump">Limited</span></h1>
                    <p>Register for Dev Nexus today to reserve your spot at Dev Nexus 2017.</p>
                    <a class="btn btn-register" href="${registrationUrl}">REGISTER</a>
                </div>
            </div>


            <div class="row">

                <h1 class="featured-header">
                    WORKSHOPS
                </h1>

                <div class="speakers sessions">

                    <div class="col-sm-6 col-md-4 col-lg-4">
                        <div class="thumbnail">
                            <img class="img-responsive" src="${ctx}/assets/img/Deane_Jeremy.jpg" alt="Enterprise Messaging Foundations">
                            <div class="caption">
                                <h3>Enterprise Messaging Foundations</h3>
                                <p>Jeremy Deane</p>
                            </div>
                        </div>
                    </div>
                    <div class="col-sm-6 col-md-4 col-lg-4">
                        <div class="thumbnail">
                            <img class="img-responsive" src="${ctx}/assets/img/Anandan_Sabby.jpg" alt="Microservices based Streaming and Batch Data Processing">
                            <div class="caption">
                                <h3>Microservices based Streaming and Batch Data Processing</h3>
                                <p>Sabby Anandan, Glenn Renfro</p>

                            </div>
                        </div>
                    </div>
                    <div class="col-sm-6 col-md-4 col-lg-4">
                        <div class="thumbnail">
                            <img class="img-responsive" src="${ctx}/assets/img/Benevides.png" alt="Hands-on with Docker, Kubernetes and OpenShift">
                            <div class="caption">
                                <h3>Hands-on with Docker, Kubernetes and OpenShift</h3>
                                <p>Rafael Benevides, Burr Sutter, Ray Tsang</p>
                            </div>
                        </div>
                    </div>
                    <div class="col-sm-6 col-md-4 col-lg-4">
                        <div class="thumbnail">
                            <img class="img-responsive" src="${ctx}/assets/img/Jaeckel_Marlene.png" alt="Learn about Wearables">
                            <div class="caption">
                                <h3>Learn about Wearables</h3>
                                <p>Lance Gleason, Marlene Jaeckel</p>
                            </div>
                        </div>
                    </div>
                    <div class="col-sm-6 col-md-4 col-lg-4">
                        <div class="thumbnail">
                            <img class="img-responsive" src="${ctx}/assets/img/nick_raboy.jpg" alt="Full Stack Development with Java and NoSQL">
                            <div class="caption">
                                <h3>Full Stack Development with Java and NoSQL</h3>
                                <p>Nic Raboy</p>
                            </div>
                        </div>
                    </div>
                    <div class="col-sm-6 col-md-4 col-lg-4">
                        <div class="thumbnail">
                            <img class="img-responsive" src="${ctx}/assets/img/VenkatSubramaniam.jpg" alt="Building Reactive Applications">
                            <div class="caption">
                                <h3>Building Reactive Applications</h3>
                                <p>Venkat Subramaniam</p>
                            </div>
                        </div>
                    </div>


                </div><!-- sessions -->

                <a href="${presentationsUrl}"class="btn btn-square btn-square btn-speakers center-block">SEE ALL SESSIONS</a>

            </div><!-- ends session row -->

            <div class="row tickets">


                <h1 class="featured-header">TICKET TYPES</h1>

                <div class="col-md-10 col-md-offset-1 col-lg-8 col-lg-offset-2">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <p class="pull-right"></p>
                            <h3 class="panel-title">MAIN CONFERENCE PASS</h3>
                        </div>
                        <div class="panel-body">
                            <a class="btn btn-register" href="${registrationUrl}">LEARN MORE</a>
                            <p>The main conference pass allows a single person to attend DevNexus 2017.  This includes both days, lunch, access to all breakout sessions and keynotes, and the DevNexus Happy Hour.  This does NOT include a workshop day pass.</p>
                        </div>
                    </div>
                </div>

                <div class="col-md-10 col-md-offset-1 col-lg-8 col-lg-offset-2">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <p class="pull-right"></p>
                            <h3 class="panel-title">WORKSHOP PASS</h3>
                        </div>
                        <div class="panel-body">
                            <a class="btn btn-register" href="${registrationUrl}">LEARN MORE</a>
                            <p>If you wish to attend a workshop, you must purchase a workshop pass.  This pass allows you to attend a day of hands on training at one of our workshops.  The workshop pass also includes a main conference pass for a single person.</p>
                        </div>
                    </div>
                </div>

                <div class="col-md-10 col-md-offset-1 col-lg-8 col-lg-offset-2">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <p class="pull-right"></p>
                            <h3 class="panel-title">GROUP RATES</h3>
                        </div>
                        <div class="panel-body">
                            <a class="btn btn-register" href="${registrationUrl}">LEARN MORE</a>
                            <p>Group rates are available for groups of five or more.  Group rates are available for both the workshop pass and the main conference pass.</p>
                        </div>
                    </div>
                </div>


            </div><!-- ends tickets row -->


            <div class="row call-to-action boss">
                <div class="col-xs-12">
                    <h1>Need To Convince Your <span class="trump">Boss</span>?</h1>
                    <a href="manager" class="btn hero-btn-register">HERE IS HOW</a>
                </div>
            </div>

            <div class="row">
                <div class="sponsors">
                    <div class="row featured-header">
                        <p>
                            SPONSORS
                        </p>
                    </div>

                    <c:forEach items="${sponsorList.sponsors}" var="sponsor" varStatus="status">
                        <c:choose>
                            <c:when test="${sponsor.sponsorLevel.name ne sponsorLevel}">
                                <c:if test="${not status.first}"></div></div></c:if>
                                <c:set value="${sponsor.sponsorLevel.name}" var="sponsorLevel"/>
                            <div class="row sponsor-section">
                                <div class="col-lg-2">
                                    <c:choose>
                                        <c:when test="${sponsorList.sponsorLevelCount.get(sponsor.sponsorLevel) > 1}">
                                            ${sponsorLevel}s
                                        </c:when>
                                        <c:otherwise>
                                            ${sponsorLevel}
                                        </c:otherwise>
                                    </c:choose>
                                </div>
                                <div class="col-lg-10">
                                <a href="${sponsor.link}">
                                    <img title="${sponsor.name}" class="img-responsive" src="${sponsorList.logos[sponsor.id]}" alt="${sponsor.name}"/>
                                </a>


                                    <c:if test="${status.last}"></div></div></c:if>
                            </c:when>
                            <c:otherwise>
                            <a href="${sponsor.link}">
                                <img title="${sponsor.name}" class="img-responsive" src="${sponsorList.logos[sponsor.id]}" alt="${sponsor.name}"/>
                            </a>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>

            </div>
            <div class="row center-block" style="width: 100%">
                <a href="${sponsorsUrl}" class="btn btn-square btn-speakers" style="float:none;width: 100%">SEE ALL SPONSORS</a>
            </div>
        </div>

        <div class="row call-to-action become-sponsor">
            <div class="col-xs-12">
                <h1>Become a <span class="trump">Sponsor</span></h1>

                <a href="https://devnexus.com/static/2017/files/promo/devnexus-2017-sponsorship-options.pdf" class="btn hero-btn-register">LEARN MORE</a>
            </div>
        </div>

        <div class="row call-to-action question">
            <h1>Have a <span class="trump">Question</span></h1>
            <button class="btn hero-btn-register">CONTACT US</button>
        </div>

        <style>
            .footer {
                margin: 1px -15px;
            }
        </style>

        <%@ include file="/WEB-INF/jsp/includes/footer.jsp" %>

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
            $('#nav').affix({
                offset: {top: $('#nav').offset().top}
            });
            $('#nav').on('affix.bs.affix', function () {
                var navHeight = $('#nav').outerHeight(true);
                $('.marketing-panel').css('margin-top', navHeight);
            });

            $('#nav').on('affix-top.bs.affix', function () {
                $('.marketing-panel').css('margin-top', 0);
            });

            $(function() {
			$(".video-container").each(function() {
				var videoId = this.id;
				$(document).delegate('#video-play-button', 'click', function() {
					var iframe_url = 'https://www.youtube.com/embed/' + videoId + '?autoplay=1&autohide=1';
					var iframe = $('<iframe/>', {'frameborder': '0', 'src': iframe_url, style:"height:100%;width:100%"});

					$('#' + videoId).html(iframe).addClass('loaded');
					$('#video-play-button').hide();
				});
			});
                    });

        </script>
    </body>
</html>
