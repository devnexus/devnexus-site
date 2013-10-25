<%@ include file="/WEB-INF/jsp/includes/taglibs.jsp" %>
<style>
    .jumbotron {
        margin-bottom: 0;
    }

    div#banner {
        padding-top: 20px;
    }

    #gold a {
        margin: 5px;
    }
    #silver a img{
        margin: 5px;
    }
</style>

<style>
    .jumbotron {
        margin-bottom: 0px;
    }
</style>
<div id="yellow" class="jumbotron" style="margin-bottom:0">
    <div class="container">
        <h1 class="center">February 24-25, 2014</h1>

        <div class="row">
            <div class="col-md-6">
                <h2>Registration Information</h2>

                <div class="row">
                    <div class="col-md-2">$240</div>
                    <div class="col-md-10"><strong>Conference Pass</strong> (until February 14, 2014)</div>
                </div>
                <div class="row">
                    <div class="col-md-2">$210</div>
                    <div class="col-md-10"><strong>Early Bird Pass</strong> (until January 10, 2014)</div>
                </div>
                <div class="row">
                    <div class="col-md-2">$210</div>
                    <div class="col-md-10"><strong>Group Pass</strong> (for groups of 5 or more)</div>
                </div>
                <div class="row">
                    <div class="col-md-2">$150</div>
                    <div class="col-md-10"><strong>Student Pass</strong> (Contact us for the code)</div>
                </div>
                <div class="row">
                    <div class="col-md-8"><center><a href="http://devnexus.eventzilla.net/" class="btn btn-primary btn-lg">Register Now!</a></center></div>
                </div>

            </div>
            <div class="col-md-6">
                <h2><strong>Call for Papers</strong></h2>

                <p>Are you interested in presenting at DevNexus 2014?  Our call for papers is now open.</p>

                <p>Previous topics have included mobile technology, cloud systems, Java updates and programming, alternate JVM languages, agile best practices, web design, and project management.</p>

                <p>We are looking for managers, entrepreneurs, developers, and other technologists.  If you know someone who may be interested feel free to drop us a line at info@ajug.org or direct them to our cfp.</p>
                <c:url var="cfpUrl" value="${baseSiteUrl}/cfp"/>

                <center><a href="${cfpUrl}" class="btn btn-primary btn-lg">Send us an abstract!</a></center>
            </div>
        </div>
        <!-- Provides extra visual weight and identifies the primary action in a set of buttons -->

    </div>
</div>


<div id="travel" class="container">
    <!-- Example row of columns -->

    <div class="row">
        <div id="travelphoto" class="col-md-6">
            <img src="${ctx}/images/photo1.jpg">
            <img src="${ctx}/images/photo2.jpg">
        </div>
        <div class="col-md-6">

            <h1 class="center">What</h1>
            <p>Atlanta’s most exciting conference for professional software developers is back in 2014! Come discover how the industry’s best minds use the latest technologies to build solutions to business problems. Network with other Atlanta software developers, and study real life case studies in application design and development.</p>

            <p>Our first seven conference events were tremendous successes. Skip the travel headaches, expense, and mobs of people at other conferences and join us this February to experience one of the best developer conferences, presented by your fellow developers, right in the heart of the Southeast (and in your own backyard).        </p>

            <p>This conference is an incredible value. Not only do we provide exceptional speakers and presentations but we also provide book raffles, food, a cocktail hour for networking and mingling with the speakers and much more.</p>

            <h1 class="center">Where</h1>
            <p>The conference will be hosted at the Cobb Galleria Center, just a few minutes north of downtown Atlanta and easily accessible from I-75 and I-285.</p>
            <c:url var="travelUrl" value="${baseSiteUrl}/travel"/>
            <p>The <a href="${travelUrl}">travel page</a> has more information including directions, maps, and nearby hotels.</p>
        </div>
    </div>
</div>


<div id="devnex" class="jumbotron">
    <div>
        <div id="gold" style="max-width: 768px;margin-left:auto;margin-right:auto;">
        <h3>Gold Sponsors</h3>
            <a href="http://spring.io/"><img class="logo" alt="SpringSource"
                                                        src="${ctx}/img/sponsors/SpringByPivotal_JUG.png"/></a>
            <a href="http://terracotta.org/"><img class="logo" alt="Terracotta" src="${ctx}/img/sponsors/terracotta-logo.png"/></a>
            <a href="https://www.theice.com/careers.jhtml"><img class="logo" alt="ICE" src="${ctx}/img/sponsors/ICE.png"/></a>
            <a href="http://www.compuware.com/apm"><img class="logo" alt="Compuware Corporation"
                                                        src="${ctx}/img/sponsors/compuware.png"/></a>
        </div>
    </div>
    <div id="silver" style="max-width: 768px;margin-left:auto;margin-right:auto;">
    <h3>Silver Sponsors</h3>
    <a href="http://ehirelabs.com/"><img class="logo" alt="eHire Labs" src="${ctx}/img/sponsors/eHire.png"/></a>
    <a href="http://www.appdynamics.com/"><img class="logo" alt="AppDynamics"
                                               src="${ctx}/img/sponsors/app-dynamics.png"/></a>
    <a href="http://www.jboss.com/"><img class="logo" alt="JBoss" src="${ctx}/img/sponsors/jboss.png"/></a>
    <a href="http://www.anteogroup.com/"><img class="logo" alt="Anteo Group" src="${ctx}/img/sponsors/Anteo.png"/></a>
    <a href="http://www.apexsystemsinc.com/"><img class="logo" alt="Apex Systems"
                                                  src="${ctx}/img/sponsors/apex-systems.png"/></a>
    <a href="http://www.github.com/"><img class="logo" alt="GitHub" src="${ctx}/img/sponsors/github_logo.png"/></a>
    <a href="http://www.incomm.com/"><img class="logo" alt="InComm" src="${ctx}/img/sponsors/inComm.png"/></a>
    <a href="http://www.4tnetworks.com/"><img class="logo" alt="4t Networks" src="${ctx}/img/sponsors/4t.png"/></a>
    <a href="http://www.creative-mischief.com/"><img class="logo" alt="Creative Mischief"
                                                     src="${ctx}/img/sponsors/creative-mischief.png"/></a>
    <a href="http://www.google.com/"><img class="logo" alt="Google" src="${ctx}/img/sponsors/google.png"/></a>
    <a href="http://www.dtglobalstaffing.com/"><img class="logo" alt="Dimensional Thinking"
                                                    src="${ctx}/img/sponsors/DimensionalThinking_AJUG.png"/></a>
    <a href="http://www.bridge2solutions.com/"><img class="logo" alt="Bridge2 Solutions"
                                                    src="${ctx}/img/sponsors/B2S.png"/></a>
    <a href="http://www.actuate.com/"><img class="logo" alt="Actuate" src="${ctx}/img/sponsors/actuate.png"/></a>
    <a href="http://www.lancope.com/"><img class="logo" alt="Lancope" src="${ctx}/img/sponsors/lancope.png"/></a>
    </div>
    <div id="cocktail">
    <h3>Cocktail Hour Sponsor</h3>
    <a href="http://ehirelabs.com/"><img class="logo" alt="eHire Labs" src="${ctx}/img/sponsors/eHire.png"/></a>
    </div>
</div>
