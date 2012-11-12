<%@ include file="/WEB-INF/jsp/includes/taglibs.jsp"%>
<% pageContext.setAttribute("lf", "\n"); %>

<!-- Start of first page -->
<div data-role="page" id="schedule-index" data-theme="b">

  <div data-role="header">
    <a rel="external" href="${ctx}${baseSiteUrl}/index">Back</a>
    <h1>Schedule</h1>
  </div>
  <!-- /header -->

  <div data-role="content">
    <ul data-role="listview" data-inset="true" data-theme="c" data-dividertheme="b">
        <li><a href="#schedule-day1"><img class="ui-li-icon" alt="Day 1" src="${ctx}/img/icons/crystal/vcalendar.png"/>Day 1, Wed, March 21, 2012</a></li>
        <li><a href="#schedule-day2"><img class="ui-li-icon" alt="Day 2" src="${ctx}/img/icons/crystal/vcalendar.png"/>Day 2, Thu, March 22, 2012</a></li>
    </ul>
  </div>
  <!-- /content -->

  <div data-role="footer">
    <h4>&copy; 2012 AJUG</h4>
  </div>
  <!-- /header -->
</div>

<!-- Start of Schedule Day 1 -->
<div data-role="page" id="schedule-day1" data-theme="b">

  <div data-role="header">
    <a href="#schedule-index">Back</a>
    <h1>Day 1, Wed, March 21, 2012</h1>
  </div>
  <!-- /header -->

  <div data-role="content" style="background-color: white;">
        <ul data-role="listview" data-inset="true" data-theme="c"
            data-dividertheme="b" data-filter="true">
            <li data-role="list-divider">08:00-09:00am</li>
            <li>
                <h3>Registration</h3>
            </li>
            <li data-role="list-divider">09:00-09:15am</li>
            <li>
                    <h3>Welcome</h3>
                    <p class="ui-li-aside">
                        Ballroom <strong>C-D</strong>
                    </p>
            </li>
            <li data-role="list-divider">09:15-10:15am</li>
            <li><a rel="external" href="${ctx}${baseSiteUrl}/presentations#id-1328">
                    <h3>Patrick Curran</h3>
                    <h3>Java User Groups and the Java Community Process: a winning
                        combination</h3>
                    <p class="ui-li-aside">
                        Ballroom <strong>C-D</strong>
                    </p>
            </a></li>
            <li data-role="list-divider">10:15-10:30am</li>
            <li data-theme="e">Break</li>
            <li data-role="list-divider">10:30-11:45am</li>
            <li><a rel="external" href="${ctx}${baseSiteUrl}/presentations#id-1320">
                    <h3>Rossen Stoyanchev</h3>
                    <h3>Spring MVC 3.1 Walkthrough</h3>
                    <p class="ui-li-aside">
                        Salon <strong>A</strong>
                    </p>
            </a></li>
            <li><a rel="external" href="${ctx}${baseSiteUrl}/presentations#id-1340">
                    <h3>Pratik Patel</h3>
                    <h3>Advanced JavaScript for Java Devs</h3>
                    <p class="ui-li-aside">
                        Salon <strong>B</strong>
                    </p>
            </a></li>
            <li><a rel="external" href="${ctx}${baseSiteUrl}/presentations#id-1295">
                    <h3>Peter Bell</h3>
                    <h3>Neo4J - High Performance NoSQL Graph Database</h3>
                    <p class="ui-li-aside">
                        Salon <strong>C/D</strong>
                    </p>
            </a></li>
            <li><a rel="external" href="${ctx}${baseSiteUrl}/presentations#id-1288">
                    <h3>David Chandler</h3>
                    <h3>Launching scalable apps with Google App Engine</h3>
                    <p class="ui-li-aside">
                        Room <strong>104</strong>
                    </p>
            </a></li>
            <li><a rel="external" href="${ctx}${baseSiteUrl}/presentations#id-1361">
                    <h3>Mark Fisher</h3>
                    <h3>What's new in Spring Integration 2.1</h3>
                    <p class="ui-li-aside">
                        Room <strong>105</strong>
                    </p>
            </a></li>
            <li><a rel="external" href="${ctx}${baseSiteUrl}/presentations#id-1307">
                    <h3>Wesley Hales</h3>
                    <h3>The Fundamentals of Enterprise HTML5 Development</h3>
                    <p class="ui-li-aside">
                        Room <strong>113/114</strong>
                    </p>
            </a></li>
            <li data-role="list-divider">11:45-12:30am</li>
            <li data-theme="e">Lunch</li>
            <li data-role="list-divider">12:30-01:00pm</li>
            <li data-theme="e">Dessert</li>
            <li data-role="list-divider">01:00pm-02:15pm</li>
                <li><a rel="external" href="${ctx}${baseSiteUrl}/presentations#id-1326">
                        <h3>Ken Kousen</h3>
                        <h3>The Groovy Web: groovlets, Micro-frameworks, and Grails</h3>
                        <p class="ui-li-aside">
                            Salon <strong>A</strong>
                        </p>
                </a></li>
                <li><a rel="external" href="${ctx}${baseSiteUrl}/presentations#id-1341">
                        <h3>Peter Bell</h3>
                        <h3>Getting started with node.js</h3>
                        <p class="ui-li-aside">
                            Salon <strong>B</strong>
                        </p>
                </a></li>
                <li><a rel="external" href="${ctx}${baseSiteUrl}/presentations#id-1308">
                        <h3>Greg Luck</h3>
                        <h3>All about Caching</h3>
                        <p class="ui-li-aside">
                            Salon <strong>C/D</strong>
                        </p>
                </a></li>
                <li><a rel="external" href="${ctx}${baseSiteUrl}/presentations#id-1313">
                        <h3>James Ward</h3>
                        <h3>Introduction to Heroku</h3>
                        <p class="ui-li-aside">
                            Room <strong>104</strong>
                        </p>
                </a></li>
                <li><a rel="external" href="${ctx}${baseSiteUrl}/presentations#id-1316">
                        <h3>Neil Green</h3>
                        <h3>Nothing matters more than coding fast</h3>
                        <p class="ui-li-aside">
                            Room <strong>105</strong>
                        </p>
                </a></li>
                <li><a rel="external" href="${ctx}${baseSiteUrl}/presentations#id-1298">
                        <h3>Raymond Camden</h3>
                        <h3>Creating Mobile Friendly Sites with jQuery Mobile</h3>
                        <p class="ui-li-aside">
                            Room <strong>113/114</strong>
                        </p>
                </a></li>
            <li data-role="list-divider">02:15-02:30pm</li>
            <li data-theme="e">Break</li>
            <li data-role="list-divider">02:30pm-03:45pm</li>
              <li><a rel="external" href="${ctx}${baseSiteUrl}/presentations#id-1347">
                        <h3>Joel Webber</h3>
                        <h3>Introduction to the PlayN Cross-Platform Game Library</h3>
                        <p class="ui-li-aside">
                            Salon <strong>A</strong>
                        </p>
                </a></li>
                <li><a rel="external" href="${ctx}${baseSiteUrl}/presentations#id-1354">
                        <h3>Mark Ethan Trostler</h3>
                        <h3>Event-Driven Architectures</h3>
                        <p class="ui-li-aside">
                            Salon <strong>B</strong>
                        </p>
                </a></li>
                <li><a rel="external" href="${ctx}${baseSiteUrl}/presentations#id-1333">
                        <h3>Daniel Hinojosa</h3>
                        <h3>Joda Time and a Brief History of the World</h3>
                        <p class="ui-li-aside">
                            Salon <strong>C/D</strong>
                        </p>
                </a></li>
                <li><a rel="external" href="${ctx}${baseSiteUrl}/presentations#id-1374">
                        <h3>Mark Atwood</h3>
                        <h3>Mobilize JBoss AS 7.1 - How to Develop Mobile Apps for the Cloud w/ OpenShift</h3>
                        <p class="ui-li-aside">
                            Room <strong>104</strong>
                        </p>
                </a></li>
                <li><a rel="external" href="${ctx}${baseSiteUrl}/presentations#id-1324">
                        <h3>Kenneth Kousen</h3>
                        <h3>Testing Groovy and Java Systems</h3>
                        <p class="ui-li-aside">
                            Room <strong>105</strong>
                        </p>
                </a></li>
                <li><a rel="external" href="${ctx}${baseSiteUrl}/presentations#id-1299">
                        <h3>Raymond Camden</h3>
                        <h3>Introduction to PhoneGap Mobile Development</h3>
                        <p class="ui-li-aside">
                            Room <strong>113/114</strong>
                        </p>
                </a></li>
            <li data-role="list-divider">03:45-04:00pm</li>
            <li data-theme="e">Break</li>
            <li data-role="list-divider">04:00-05:15pm</li>
              <li><a rel="external" href="${ctx}${baseSiteUrl}/presentations#id-1303">
                        <h3>Jason Porter</h3>
                        <h3>Building CDI extensions using DeltaSpike</h3>
                        <p class="ui-li-aside">
                            Salon <strong>A</strong>
                        </p>
                </a></li>
                <li><a rel="external" href="${ctx}${baseSiteUrl}/presentations#id-1287">
                        <h3>David Chandler</h3>
                        <h3>Building Maintainable Web Apps with HTML5 and Dart</h3>
                        <p class="ui-li-aside">
                            Salon <strong>B</strong>
                        </p>
                </a></li>
                <li><a rel="external" href="${ctx}${baseSiteUrl}/presentations#id-1362">
                        <h3>Josh Suereth</h3>
                        <h3>Why you should learn Scala</h3>
                        <p class="ui-li-aside">
                            Salon <strong>C/D</strong>
                        </p>
                </a></li>
                <li><a rel="external" href="${ctx}${baseSiteUrl}/presentations#id-1364">
                        <h3>Brad Anderson</h3>
                        <h3>Large Scale Data Analysis Tools</h3>
                        <p class="ui-li-aside">
                            Room <strong>104</strong>
                        </p>
                </a></li>
                <li><a rel="external" href="${ctx}${baseSiteUrl}/presentations#id-1343">
                        <h3>Ram Singaram</h3>
                        <h3>Architecture Anti-patterns</h3>
                        <p class="ui-li-aside">
                            Room <strong>105</strong>
                        </p>
                </a></li>
                <li><a rel="external" href="${ctx}${baseSiteUrl}/presentations#id-1305">
                        <h3>Andrew Trice</h3>
                        <h3>Java-Powered Cross-Platform Mobile Applications with PhoneGap</h3>
                        <p class="ui-li-aside">
                            Room <strong>113/114</strong>
                        </p>
                </a></li>
            <li data-role="list-divider">05:15-05:30pm</li>
            <li data-theme="e">Break</li>
            <li data-role="list-divider">05:30-06:30pm</li>
                    <li><a rel="external" href="${ctx}${baseSiteUrl}/presentations#id-1329">
                            <h3>Barry Hawkins</h3>
                            <h3>How We Got Here, And What To Do About It</h3>
                            <p class="ui-li-aside">
                                Salon <strong>C/D</strong>
                            </p>
                    </a></li>
            <li data-role="list-divider">06:30-12:00am</li>
            <li data-theme="e">Cocktail Hour</li>
        </ul>
  </div>
  <!-- /content -->

  <div data-role="footer">
    <h4>&copy; 2012 AJUG</h4>
  </div>
</div><!-- /Schedule Day 1 -->

<!-- Start of Schedule Day 2 -->
<div data-role="page" id="schedule-day2" data-theme="b">

    <div data-role="header">
        <a href="#schedule-index">Back</a>
        <h1>Day 2, Thu, March 22, 2012</h1>
    </div>
    <!-- /header -->

  <div data-role="content" style="background-color: white;">
        <ul data-role="listview" data-inset="true" data-theme="c"
            data-dividertheme="b" data-filter="true">
            <li data-role="list-divider">08:00-09:00am</li>
            <li>
                <h3>Continental Breakfast / Coffee</h3>
            </li>
            <li data-role="list-divider">09:00-10:15am</li>
            <li><a rel="external" href="${ctx}${baseSiteUrl}/presentations#id-1380">
                    <h3>Ben Galbraith</h3>
                    <h3>Web vs. Apps</h3>
                    <p class="ui-li-aside">
                        Ballroom <strong>C-D</strong>
                    </p>
            </a></li>
            <li data-role="list-divider">10:15-10:30am</li>
            <li data-theme="e">Break</li>
            <li data-role="list-divider">10:30-11:45am</li>
            <li><a rel="external" href="${ctx}${baseSiteUrl}/presentations#id-1377">
                    <h3>Marius Bogoevici</h3>
                    <h3>What makes JBoss AS 7 tick?</h3>
                    <p class="ui-li-aside">
                        Salon <strong>A</strong>
                    </p>
            </a></li>
            <li><a rel="external" href="${ctx}${baseSiteUrl}/presentations#id-1355">
                    <h3>Ken Kousen</h3>
                    <h3>The Gradle Will Rock</h3>
                    <p class="ui-li-aside">
                        Salon <strong>B</strong>
                    </p>
            </a></li>
            <li><a rel="external" href="${ctx}${baseSiteUrl}/presentations#id-1311">
                    <h3>Donald Smith</h3>
                    <h3>The Java SE Platform: Rebuilding Momentum</h3>
                    <p class="ui-li-aside">
                        Salon <strong>C/D</strong>
                    </p>
            </a></li>
            <li><a rel="external" href="${ctx}${baseSiteUrl}/presentations#id-1317">
                    <h3>Tim Berglund</h3>
                    <h3>NoSQL Smackdown 2012</h3>
                    <p class="ui-li-aside">
                        Room <strong>104</strong>
                    </p>
            </a></li>
            <li><a rel="external" href="${ctx}${baseSiteUrl}/presentations#id-1356">
                    <h3>Barry Hawkins</h3>
                    <h3>Agile, Lean, and the Space Between</h3>
                    <p class="ui-li-aside">
                        Room <strong>105</strong>
                    </p>
            </a></li>
            <li><a rel="external" href="${ctx}${baseSiteUrl}/presentations#id-1358">
                    <h3>Pratik Patel</h3>
                    <h3>Easy Mobile Development: Appcelerator Titanium Introduction</h3>
                    <p class="ui-li-aside">
                        Room <strong>113/114</strong>
                    </p>
            </a></li>
            <li data-role="list-divider">11:45-12:30am</li>
            <li data-theme="e">Lunch</li>
            <li data-role="list-divider">12:30-01:00pm</li>
            <li data-theme="e">Dessert</li>
            <li data-role="list-divider">01:00pm-02:15pm</li>
                <li><a rel="external" href="${ctx}${baseSiteUrl}/presentations#id-1290">
                        <h3>Jeremy Deane</h3>
                        <h3>RESTful Imaginarium</h3>
                        <p class="ui-li-aside">
                            Salon <strong>A</strong>
                        </p>
                </a></li>
                <li><a rel="external" href="${ctx}${baseSiteUrl}/presentations#id-1301">
                        <h3>Fred Simon</h3>
                        <h3>Build Trust in Your Build to Deployment Flow!</h3>
                        <p class="ui-li-aside">
                            Salon <strong>B</strong>
                        </p>
                </a></li>
                <li><a rel="external" href="${ctx}${baseSiteUrl}/presentations#id-1332">
                        <h3>Daniel Hinojosa</h3>
                        <h3>Making Java Bearable with Guava</h3>
                        <p class="ui-li-aside">
                            Salon <strong>C/D</strong>
                        </p>
                </a></li>
                <li><a rel="external" href="${ctx}${baseSiteUrl}/presentations#id-1318">
                        <h3>Tim Berglund</h3>
                        <h3>Radical NoSQL Scalability with Cassandra</h3>
                        <p class="ui-li-aside">
                            Room <strong>104</strong>
                        </p>
                </a></li>
                <li><a rel="external" href="${ctx}${baseSiteUrl}/presentations#id-1338">
                        <h3>Josh Long</h3>
                        <h3>A walking tour of Spring 3.1</h3>
                        <p class="ui-li-aside">
                            Salon <strong>105</strong>
                        </p>
                </a></li>
                <li><a rel="external" href="${ctx}${baseSiteUrl}/presentations#id-1372">
                        <h3>Robert Cooper</h3>
                        <h3>What's new in Android</h3>
                        <p class="ui-li-aside">
                            Room <strong>113/114</strong>
                        </p>
                </a></li>
            <li data-role="list-divider">02:15-02:30pm</li>
            <li data-theme="e">Break</li>
            <li data-role="list-divider">02:30pm-03:45pm</li>
                <li><a rel="external" href="${ctx}${baseSiteUrl}/presentations#id-1314">
                        <h3>James Ward</h3>
                        <h3>Intro to Play Framework - Making Java & Scala Web App Development Fun</h3>
                        <p class="ui-li-aside">
                            Salon <strong>A</strong>
                        </p>
                </a></li>
                <li><a rel="external" href="${ctx}${baseSiteUrl}/presentations#id-1348">
                        <h3>Joel Webber</h3>
                        <h3>Porting Your Game to the Web using NaCl</h3>
                        <p class="ui-li-aside">
                            Salon <strong>B</strong>
                        </p>
                </a></li>
                <li><a rel="external" href="${ctx}${baseSiteUrl}/presentations#id-1291">
                        <h3>Greg Luck</h3>
                        <h3>JSR107: The new Java Caching Standard</h3>
                        <p class="ui-li-aside">
                            Salon <strong>C/D</strong>
                        </p>
                </a></li>
                <li><a rel="external" href="${ctx}${baseSiteUrl}/presentations#id-1337">
                        <h3>Josh Long</h3>
                        <h3>Spring and Cloud Foundry, a Marriage Made in Heaven</h3>
                        <p class="ui-li-aside">
                            Room <strong>104</strong>
                        </p>
                </a></li>
                <li><a rel="external" href="${ctx}${baseSiteUrl}/presentations#id-1350">
                        <h3>Burr Sutter</h3>
                        <h3>The Hitchhiker's Guide to the JBoss Galaxy</h3>
                        <p class="ui-li-aside">
                            Room <strong>105</strong>
                        </p>
                </a></li>
                <li><a rel="external" href="${ctx}${baseSiteUrl}/presentations#id-1330">
                        <h3>Roy Clarkson</h3>
                        <h3>Native Android Development with Spring Android</h3>
                        <p class="ui-li-aside">
                            Room <strong>113/114</strong>
                        </p>
                </a></li>
            <li data-role="list-divider">03:45-04:00pm</li>
            <li data-theme="e">Break</li>
            <li data-role="list-divider">04:00-05:15pm</li>
                <li><a rel="external" href="${ctx}${baseSiteUrl}/presentations#id-1321">
                        <h3>Rossen Stoyanchev</h3>
                        <h3>An In-depth Look At Spring MVC 3.1</h3>
                        <p class="ui-li-aside">
                            Salon <strong>A</strong>
                        </p>
                </a></li>
                <li><a rel="external" href="${ctx}${baseSiteUrl}/presentations#id-1369">
                        <h3>David Geary</h3>
                        <h3>Mind-blowing apps with HTML5 Canvas</h3>
                        <p class="ui-li-aside">
                            Salon <strong>B</strong>
                        </p>
                </a></li>
                <li><a rel="external" href="${ctx}${baseSiteUrl}/presentations#id-1363">
                        <h3>Josh Suereth</h3>
                        <h3>Effective Scala</h3>
                        <p class="ui-li-aside">
                            Salon <strong>C/D</strong>
                        </p>
                </a></li>
                <li><a rel="external" href="${ctx}${baseSiteUrl}/presentations#id-1378">
                        <h3>Mark Fisher</h3>
                        <h3>Migrating Enterprise Apps to the Cloud: from monolith to modular</h3>
                        <p class="ui-li-aside">
                            Room <strong>104</strong>
                        </p>
                </a></li>
                <li><a rel="external" href="${ctx}${baseSiteUrl}/presentations#id-1360">
                        <h3>Tim Berglund</h3>
                        <h3>Git Going with Distributed Version Control</h3>
                        <p class="ui-li-aside">
                            Room <strong>105</strong>
                        </p>
                </a></li>
                <li><a rel="external" href="${ctx}${baseSiteUrl}/presentations#id-1357">
                        <h3>Charlie Collins</h3>
                        <h3>Google TV Basics and Boundaries</h3>
                        <p class="ui-li-aside">
                            Room <strong>113/114</strong>
                        </p>
                </a></li>
            <li data-role="list-divider">05:15-05:45pm</li>
            <li data-theme="e">Closing Ceremonies</li>
        </ul>
  </div>
    <div data-role="footer">
        <h4>&copy; 2012 AJUG</h4>
    </div>
</div><!-- /Schedule Day 1 -->

