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
        <c:url var="conferenceInfoUrl" value="${baseSiteUrl}/sponsors"/>
        <c:url var="registrationUrl" value="${baseSiteUrl}/register-overview"/>
        <c:url var="pastConferencesUrl" value="${baseSiteUrl}/past-conferences"/>
        <c:url var="privacyPolicyUrl" value="${baseSiteUrl}/privacy-policy"/>
        <c:url var="codeOfConductUrl" value="${baseSiteUrl}/code-of-conduct"/>
        <c:url var="facebookUrl" value="https://www.facebook.com/devnexus"/>
        <c:url var="twitterUrl" value="https://www.twitter.com/devnexus"/>
        <c:url var="instagramUrl" value="https://www.instagram.com/devnexus/"/>
        <c:url var="googlePlusUrl" value="https://plus.google.com/+devnexus-conference"/>
        
        <section class="hero">
            <div>
                <ul class="list-inline hero-social">
                    <li><a href="#"><img src="${ctx}/assets/img/facebook-logo-button.png"/></a></li>
                    <li><a href="#"><img src="${ctx}/assets/img/twitter-logo-button.png"/></a></li>
                    <li><a href="#"><img src="${ctx}/assets/img/instagram-logo.png"/></a></li>
                    <li><a href="#"><img src="${ctx}/assets/img/google-plus-1.png"/></a></li>
                </ul>

                <ul class="list-inline hero-date-location">
                    <li><img src="${ctx}/assets/img/calendar.png"/> 20-22.FEB</li>
                    <li><img src="${ctx}/assets/img/location.png"/> ATLANTA, GA</li>
                </ul>

                <h1><img src="${ctx}/assets/img/dev-nexus-logo-large.png" alt="DevNexus"/></h1>
                <h2>Join the <span>&lt;dev/&gt;</span>olution</h2>

                <a class="btn hero-btn-register" href="register.html">REGISTER NOW</a>

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
                <div class="col-md-9 preview">
                    <a class="center-block" href="#">
                        <img class="img-responsive center-block play-button" src="${ctx}/assets/img/play-button.png" alt="Play Promo Video"/>
                    </a>
                </div>
            </div>

            <div class="row">
                <div class="col-xs-12">
                    <div class="copy">
                        <p>The goal of the Dev Nexus Conference is to ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex</p>
                    </div>
                </div>
            </div>

            <div class="row stats">

                <div class="col-lg-3 col-sm-6">
                    <img src="${ctx}/assets/img/multiple-users-silhouette.png"/>
                    <p>1700</p>
                    <img src="${ctx}/assets/img/line.png"/>
                    <br>
                    <p class="trump">DEVELOPERS</p>
                </div>

                <div class="col-lg-3 col-sm-6">
                    <img src="${ctx}/assets/img/settings.png"/>
                    <p>9</p>
                    <img src="${ctx}/assets/img/line.png"/>
                    <br>
                    <p class="trump">WORKSHOPS</p>
                </div>

                <div class="col-lg-3 col-sm-6">
                    <img src="${ctx}/assets/img/folder.png"/>
                    <p>13</p>
                    <img src="${ctx}/assets/img/line.png"/>
                    <br>
                    <p class="trump">TRACKS</p>
                </div>

                <div class="col-lg-3 col-sm-6">
                    <img src="${ctx}/assets/img/microphone.png"/>
                    <p>120</p>
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
                            <img class="img-responsive" src="${ctx}/assets/img/head.jpg" alt="Franklin">
                            <div class="caption">
                                <h3>Franklin Kennedy</h3>
                                <p>Job Title</p>
                                <p>Company</p>
                            </div>
                        </div>
                    </div>


                    <div class="col-sm-6 col-md-4 col-lg-3">
                        <div class="thumbnail">
                            <img class="img-responsive" src="${ctx}/assets/img/head.jpg" alt="Franklin">
                            <div class="caption">
                                <h3>Franklin Kennedy</h3>
                                <p>Job Title</p>
                                <p>Company</p>
                            </div>
                        </div>
                    </div>

                    <div class="col-sm-6 col-md-4 col-lg-3">
                        <div class="thumbnail">
                            <img class="img-responsive" src="${ctx}/assets/img/head.jpg" alt="Franklin">
                            <div class="caption">
                                <h3>Franklin Kennedy</h3>
                                <p>Job Title</p>
                                <p>Company</p>
                            </div>
                        </div>
                    </div>


                    <div class="col-sm-6 col-md-4 col-lg-3">
                        <div class="thumbnail">
                            <img class="img-responsive" src="${ctx}/assets/img/head.jpg" alt="Franklin">
                            <div class="caption">
                                <h3>Franklin Kennedy</h3>
                                <p>Job Title</p>
                                <p>Company</p>
                            </div>
                        </div>
                    </div>


                    <div class="col-sm-6 col-md-4 col-lg-3">
                        <div class="thumbnail">
                            <img class="img-responsive" src="${ctx}/assets/img/head.jpg" alt="Franklin">
                            <div class="caption">
                                <h3>Franklin Kennedy</h3>
                                <p>Job Title</p>
                                <p>Company</p>
                            </div>
                        </div>
                    </div>

                    <div class="col-sm-6 col-md-4 col-lg-3">
                        <div class="thumbnail">
                            <img class="img-responsive" src="${ctx}/assets/img/head.jpg" alt="Franklin">
                            <div class="caption">
                                <h3>Franklin Kennedy</h3>
                                <p>Job Title</p>
                                <p>Company</p>
                            </div>
                        </div>
                    </div>


                    <div class="col-sm-6 col-md-4 col-lg-3">
                        <div class="thumbnail">
                            <img class="img-responsive" src="${ctx}/assets/img/head.jpg" alt="Franklin">
                            <div class="caption">
                                <h3>Franklin Kennedy</h3>
                                <p>Job Title</p>
                                <p>Company</p>
                            </div>
                        </div>
                    </div>

                    <div class="col-sm-6 col-md-4 col-lg-3">
                        <div class="thumbnail">
                            <img class="img-responsive" src="${ctx}/assets/img/head.jpg" alt="Franklin">
                            <div class="caption">
                                <h3>Franklin Kennedy</h3>
                                <p>Job Title</p>
                                <p>Company</p>
                            </div>
                        </div>
                    </div>


                </div><!-- speakers -->

                <button class="btn btn-square btn-square btn-speakers center-block">SEE ALL SPEAKERS</button>
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
                    FEATURED SESSIONS
                </h1>

                <div class="speakers sessions">

                    <div class="col-sm-6 col-md-4 col-lg-4">
                        <div class="thumbnail">
                            <img class="img-responsive" src="${ctx}/assets/img/head.jpg" alt="Franklin">
                            <div class="caption">
                                <h3>Presentation Name</h3>
                                <p>Speaker name</p>
                            </div>
                        </div>
                    </div>
                    <div class="col-sm-6 col-md-4 col-lg-4">
                        <div class="thumbnail">
                            <img class="img-responsive" src="${ctx}/assets/img/head.jpg" alt="Franklin">
                            <div class="caption">
                                <h3>Presentation Name</h3>
                                <p>Speaker name</p>
                            </div>
                        </div>
                    </div>
                    <div class="col-sm-6 col-md-4 col-lg-4">
                        <div class="thumbnail">
                            <img class="img-responsive" src="${ctx}/assets/img/head.jpg" alt="Franklin">
                            <div class="caption">
                                <h3>Presentation Name</h3>
                                <p>Speaker name</p>
                            </div>
                        </div>
                    </div>
                    <div class="col-sm-6 col-md-4 col-lg-4">
                        <div class="thumbnail">
                            <img class="img-responsive" src="${ctx}/assets/img/head.jpg" alt="Franklin">
                            <div class="caption">
                                <h3>Presentation Name</h3>
                                <p>Speaker name</p>
                            </div>
                        </div>
                    </div>
                    <div class="col-sm-6 col-md-4 col-lg-4">
                        <div class="thumbnail">
                            <img class="img-responsive" src="${ctx}/assets/img/head.jpg" alt="Franklin">
                            <div class="caption">
                                <h3>Presentation Name</h3>
                                <p>Speaker name</p>
                            </div>
                        </div>
                    </div>
                    <div class="col-sm-6 col-md-4 col-lg-4">
                        <div class="thumbnail">
                            <img class="img-responsive" src="${ctx}/assets/img/head.jpg" alt="Franklin">
                            <div class="caption">
                                <h3>Presentation Name</h3>
                                <p>Speaker name</p>
                            </div>
                        </div>
                    </div>


                </div><!-- sessions -->

                <button class="btn btn-square btn-square btn-speakers center-block">SEE ALL SESSIONS</button>

            </div><!-- ends session row -->

            <div class="row tickets">


                <h1 class="featured-header">TICKETS</h1>

                <div class="col-md-10 col-md-offset-1 col-lg-8 col-lg-offset-2">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <p class="pull-right">$162</p>
                            <h3 class="panel-title">Panel title</h3>
                        </div>
                        <div class="panel-body">
                            <a class="btn btn-register" href="${registrationUrl}">BUY NOW</a>
                            <p>Bacon ipsum dolor amet pork belly ham hock turkey tri-tip bresaola pastrami boudin, fatback bacon jerky jowl pig drumstick brisket Bacon ipsum dolor amet pork belly ham hock turkey tri-tip bresaola pastrami boudin, fatback bacon jerky jowl pig drumstick brisket Bacon ipsum dolor amet pork belly ham hock turkey tri-tip bresaola pastrami boudin, fatback bacon jerky jowl pig drumstick brisket</p>
                        </div>
                    </div>
                </div>

                <div class="col-md-10 col-md-offset-1 col-lg-8 col-lg-offset-2">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <p class="pull-right">$162</p>
                            <h3 class="panel-title">Panel title</h3>
                        </div>
                        <div class="panel-body">
                            <a class="btn btn-register" href="${registrationUrl}">BUY NOW</a>
                            <p>Bacon ipsum dolor amet pork belly ham hock turkey tri-tip bresaola pastrami boudin, fatback bacon jerky jowl pig drumstick brisket Bacon ipsum dolor amet pork belly ham hock turkey tri-tip bresaola pastrami boudin, fatback bacon jerky jowl pig drumstick brisket Bacon ipsum dolor amet pork belly ham hock turkey tri-tip bresaola pastrami boudin, fatback bacon jerky jowl pig drumstick brisket</p>
                        </div>
                    </div>
                </div>

                <div class="col-md-10 col-md-offset-1 col-lg-8 col-lg-offset-2">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <p class="pull-right">$162</p>
                            <h3 class="panel-title">Panel title</h3>
                        </div>
                        <div class="panel-body">
                            <a class="btn btn-register" href="${registrationUrl}">BUY NOW</a>
                            <p>Bacon ipsum dolor amet pork belly ham hock turkey tri-tip bresaola pastrami boudin, fatback bacon jerky jowl pig drumstick brisket Bacon ipsum dolor amet pork belly ham hock turkey tri-tip bresaola pastrami boudin, fatback bacon jerky jowl pig drumstick brisket Bacon ipsum dolor amet pork belly ham hock turkey tri-tip bresaola pastrami boudin, fatback bacon jerky jowl pig drumstick brisket</p>
                        </div>
                    </div>
                </div>


            </div><!-- ends tickets row -->


            <div class="row call-to-action boss">
                <div class="col-xs-12">
                    <h1>Need To Convince Your <span class="trump">Boss</span>?</h1>
                    <button class="btn hero-btn-register">HERE IS HOW</button>
                </div>
            </div>

            <div class="row">
                <div class="sponsors">
                    <div class="row featured-header">
                        <p>
                            SPONSORS
                        </p>
                    </div>
                    <div class="row sponsor-section">
                        <div class="col-lg-2">
                            Platinum
                        </div>
                        <div class="col-lg-10">
                            <a href="#">
                                <img class="img-responsive" src="${ctx}/assets/img/sponsor.png" alt="Sponsor"/>
                            </a>
                            <a href="#">
                                <img class="img-responsive" src="${ctx}/assets/img/sponsor.png" alt="Sponsor"/>
                            </a>
                            <a href="#">
                                <img class="img-responsive" src="${ctx}/assets/img/sponsor.png" alt="Sponsor"/>
                            </a>
                            <a href="#">
                                <img class="img-responsive" src="${ctx}/assets/img/sponsor.png" alt="Sponsor"/>
                            </a>
                            <a href="#">
                                <img class="img-responsive" src="${ctx}/assets/img/sponsor.png" alt="Sponsor"/>
                            </a>
                            <a href="#">
                                <img class="img-responsive" src="${ctx}/assets/img/sponsor.png" alt="Sponsor"/>
                            </a>
                            <a href="#">
                                <img class="img-responsive" src="${ctx}/assets/img/sponsor.png" alt="Sponsor"/>
                            </a>
                            <a href="#">
                                <img class="img-responsive" src="${ctx}/assets/img/sponsor.png" alt="Sponsor"/>
                            </a>
                        </div>
                    </div>
                    <div class="row sponsor-section">
                        <div class="col-lg-2">
                            Gold
                        </div>
                        <div class="col-lg-10">
                            <a href="#">
                                <img class="img-responsive" src="${ctx}/assets/img/sponsor.png" alt="Sponsor"/>
                            </a>
                            <a href="#">
                                <img class="img-responsive" src="${ctx}/assets/img/sponsor.png" alt="Sponsor"/>
                            </a>
                            <a href="#">
                                <img class="img-responsive" src="${ctx}/assets/img/sponsor.png" alt="Sponsor"/>
                            </a>
                            <a href="#">
                                <img class="img-responsive" src="${ctx}/assets/img/sponsor.png" alt="Sponsor"/>
                            </a>
                            <a href="#">
                                <img class="img-responsive" src="${ctx}/assets/img/sponsor.png" alt="Sponsor"/>
                            </a>
                            <a href="#">
                                <img class="img-responsive" src="${ctx}/assets/img/sponsor.png" alt="Sponsor"/>
                            </a>
                            <a href="#">
                                <img class="img-responsive" src="${ctx}/assets/img/sponsor.png" alt="Sponsor"/>
                            </a>
                            <a href="#">
                                <img class="img-responsive" src="${ctx}/assets/img/sponsor.png" alt="Sponsor"/>
                            </a>
                        </div>
                    </div>
                </div>
                <div class="row center-block">
                    <button class="btn btn-square btn-speakers center-block">SEE ALL SPONSORS</button>
                </div>
            </div>

            <div class="row call-to-action become-sponsor">
                <div class="col-xs-12">
                    <h1>Become a <span class="trump">Sponsor</span></h1>
                    <p>Sed ut perspiciatis unde omnis iste natus error sit  voluptatem accusantium doloremque laudantium</p>
                    <button class="btn hero-btn-register">LET US KNOW</button>
                </div>
            </div>

            <div class="row call-to-action question">
                <h1>Have a <span class="trump">Question</span></h1>
                <button class="btn hero-btn-register">CONTACT US</button>
            </div>

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
            </script>
    </body>
</html>
