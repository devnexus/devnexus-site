<%@ include file="/WEB-INF/jsp/includes/taglibs.jsp" %>

<style>
    .jumbotron {
        margin-bottom: 0;
    }

    div#banner {
        padding-top: 20px;
    }

    #gold a img {
        margin: 5px;
    }

    #silver a img {
        margin: 5px;
    }
</style>

<style>
    .jumbotron {
        margin-bottom: 0px;
    }
</style>
<div id="devnex" class="jumbotron">
    <div class="container">
        <div id="banner">
                    <h1 id="gray">DevNexus 2015</h1>
                    <h1 id="white"><c:out default="Atlanta, GA" value="${headerTitle}"/></h1>
                    <h3><c:out default="The professional developer conference." value="${tag}"/></h3>
        </div>
    </div>
</div>
<div id="yellow" class="jumbotron" style="margin-bottom:0">
    <div class="container">
        <div class="row">
            <div id="travelphoto" class="col-md-4">
                <iframe src="//player.vimeo.com/video/90063155?title=0&amp;byline=0&amp;portrait=0" width="382" height="215" frameborder="0" webkitallowfullscreen mozallowfullscreen allowfullscreen></iframe>
            </div>
            <div class="col-md-8">
                <h1 class="center">March 10-12, 2015</h1>
                <h2 class="text-center">Would you like to present?</h2>
                <div class="text-center"><a href="${ctx}/s/cfp" class="btn btn-primary btn-lg">Call for Papers is Open!</a></div>
                <h2 class="text-center"><a href="${ctx}/static/2015/files/promo/devnexus-2015-sponsorship-options.pdf">Would you like to sponsor us?</a></h2>
            </div>
        </div>
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

            <p>Atlanta’s most exciting conference for professional software developers is back in 2015! Come discover
                how the industry’s best minds use the latest technologies to build solutions to business problems.
                Network with other Atlanta software developers, and study real life case studies in application design
                and development.</p>

            <p>Our first seven conference events were tremendous successes. Skip the travel headaches, expense, and mobs
                of people at other conferences and join us this February to experience one of the best developer
                conferences, presented by your fellow developers, right in the heart of the Southeast (and in your own
                backyard). </p>

            <p>This conference is an incredible value. Not only do we provide exceptional speakers and presentations but
                we also provide book raffles, food, a cocktail hour for networking and mingling with the speakers and
                much more.</p>

            <h1 class="center">Where</h1>

            <p>The conference will be hosted at the Cobb Galleria Center, just a few minutes north of downtown Atlanta
                and easily accessible from I-75 and I-285.</p>
            <c:url var="travelUrl" value="${baseSiteUrl}/travel"/>
            <p>The <a href="${travelUrl}">travel page</a> has more information including directions, maps, and nearby
                hotels.</p>
        </div>
    </div>
</div>

<div id="devnex" class="jumbotron">

    <div id="platinum" style="max-width: 768px;margin-left:auto;margin-right:auto;">
        <h3>Platinum Sponsor</h3>
            <a href="${ctx}/static/2015/files/promo/devnexus-2015-sponsorship-options.pdf"><img class="logo" alt="See Sponsorship Options"
               src="${ctx}/images/sponsorship-available.png"/></a>
    </div>

    <div>
        <div id="gold" style="max-width: 768px;margin-left:auto;margin-right:auto;">
            <h3>Gold Sponsors</h3>
            <a href="http://www.pivotal.io/"><img class="logo" alt="Pivotal"
               src="${ctx}/img/sponsors/pivotal.png"/></a>
            <a href="${ctx}/static/2015/files/promo/devnexus-2015-sponsorship-options.pdf"><img class="logo" alt="See Sponsorship Options"
               src="${ctx}/images/sponsorship-available.png"/></a>
        </div>
    </div>
    <div id="silver" style="max-width: 768px;margin-left:auto;margin-right:auto;">
        <h3>Silver Sponsors</h3>
            <a href="http://www.coverity.com/"><img class="logo" alt="Coverity"
               src="${ctx}/img/sponsors/coverity.png"/></a>
            <a href="http://www.anteogroup.com/"><img class="logo" alt="Anteo Group"
               src="${ctx}/img/sponsors/Anteo.png"/></a>
            <a href="http://www.vaadin.com/"><img class="logo" alt="Vaadin"
               src="${ctx}/img/sponsors/vaadin.png"/></a>
            <a href="http://www.sonatype.com/"><img class="logo" alt="Sonatype"
               src="${ctx}/img/sponsors/sonatype_key.png"/></a>
            <a href="${ctx}/static/2015/files/promo/devnexus-2015-sponsorship-options.pdf"><img class="logo" alt="See Sponsorship Options"
               src="${ctx}/images/sponsorship-available.png"/></a>
            <a href="http://www.altisourcelabs.com/">
                <img class="logo" alt="Altisource Labs" src="${ctx}/img/sponsors/altisource.png"/>
            </a>
            <a href="http://www.aspose.com/">
                <img class="logo" alt="Aspose" src="${ctx}/img/sponsors/aspose-key.png"/>
            </a>
    </div>
    <div id="cocktail">
        <h3>Cocktail Hour Sponsor</h3>
        <a href="http://mandrill.com/"><img class="logo" alt="Mandrill by MailChimp"
               src="${ctx}/img/sponsors/mandrill.png"/></a>
    </div>
</div>
