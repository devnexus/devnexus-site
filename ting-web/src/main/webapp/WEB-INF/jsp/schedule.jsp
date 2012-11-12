<%@ include file="/WEB-INF/jsp/includes/taglibs.jsp"%>

<title>DevNexus 2012 - Schedule</title>
<div id="content" class="span-22 last">
  <div class="quote"><span>What the community says:</span> "Best Dev Education bargain in Atlanta"</div>
  <h2>Schedule</h2>
  <ul>
      <c:forEach items="${organizerList.organizers}" var="organizer">
          <li><a href="#${organizer.firstName}_${organizer.lastName}"><c:out value="${organizer.firstName}"/> <c:out value="${organizer.lastName}"/></a></li>
      </c:forEach>
  </ul>


  <table style="border-collapse: collapse;" class="schedule ">
          <tr>
                  <th colspan="5" class="day">Wednesday March 21, 2012</th>
          </tr>
          <tr>
                  <th>Start</th>
                  <th>End</th>
                  <th>Session</th>
                  <th>Room</th>
                  <th>Session</th>
          </tr>

          <tr>
                  <td class="time">08:00</td>
                  <td class="time">09:00</td>
                  <td colspan="3" class="registration">Breakfast &amp; Registration [Galleria Attrium]</td>
          </tr>
          <tr>
                  <td class="time">09:00</td>
                  <td class="time">09:15</td>
                  <td colspan="3" class="keynote">Welcome [Salon C/D]</td>
          </tr>
          <tr>
                  <td class="time">09:15</td>
                  <td class="time">10:15</td>
                  <td class="keynote">Keynote</td>
                  <td class="keynote">Salon C/D</td>
                  <td class="talk">
                           <p class="topic">Java User Groups and the Java Community Process: a winning combination</p>
                           <p class="speaker">Patrick Curran</p>
                  </td>
          </tr>
          <tr>
                  <td class="time">10:15</td>
                  <td class="time">10:30</td>
                  <td colspan="3" class="break">Break [Galleria Attrium]</td>
          </tr>
          <tr>
               <td class="time"      rowspan="6">10:30</td>
               <td class="time"      rowspan="6">11:45</td>
               <td class="breakouts" rowspan="6">Breakouts</td>
               <td class="track-1">Salon A</td>
               <td class="talk">
                   <p class="topic">Rossen Stoyanchev</p>
                   <p class="speaker">Spring MVC 3.1 Walkthrough</p>
               </td>
          </tr>
          <tr>
              <td class="track-2">Salon B</td>
              <td class="talk">
                  <p class="topic">Pratik Patel</p>
                  <p class="speaker">Advanced JavaScript for Java Devs</p>
              </td>
          </tr>
          <tr>
              <td class="track-3">Salon C/D</td>
              <td class="talk">
                  <p class="topic">Peter Bell</p>
                  <p class="speaker">Neo4J - High Performance NoSQL Graph Database</p>
              </td>
          </tr>
          <tr>
              <td class="track-4">Room 104</td>
              <td class="talk">
                  <p class="topic">David Chandler</p>
                  <p class="speaker">Launching scalable apps with Google App Engine</p>
              </td>
          </tr>
          <tr>
              <td class="track-5">Room 105</td>
              <td class="talk">
                  <p class="topic">What's new in Spring Integration 2.1</p>
                  <p class="speaker">Mark Fisher</p>
              </td>
          </tr>
          <tr>
              <td class="track-6">Room 113/114</td>
              <td class="talk">
                  <p class="topic">Wesley Hales</p>
                  <p class="speaker">The Fundamentals of Enterprise HTML5 Development</p>
              </td>
          </tr>
          <tr>
                  <td class="time">11:45</td>
                  <td class="time">12:30</td>
                  <td colspan="3" class="break">Lunch [Salon C/D]</td>
          </tr>
          <tr>
                  <td class="time">12:30</td>
                  <td class="time">01:00</td>
                  <td colspan="3" class="break">Dessert [Galleria Attrium]</td>
          </tr>
          <tr>
               <td class="time"      rowspan="6">01:00</td>
               <td class="time"      rowspan="6">02:15</td>
               <td class="breakouts" rowspan="6">Breakouts</td>
               <td class="track-1">Salon A</td>
               <td class="talk">
                   <p class="topic">The Groovy Web: groovlets, Micro-frameworks, and Grails</p>
                   <p class="speaker">Ken Kousen</p>
               </td>
          </tr>
          <tr>
              <td class="track-2">Salon B</td>
              <td class="talk">
                  <p class="topic">Peter Bell</p>
                  <p class="speaker">Getting started with node.js</p>
              </td>
          </tr>
          <tr>
              <td class="track-3">Salon C/D</td>
              <td class="talk">
                  <p class="topic">Greg Luck</p>
                  <p class="speaker">All about Caching</p>
              </td>
          </tr>
          <tr>
              <td class="track-4">Room 104</td>
              <td class="talk">
                  <p class="topic">James Ward</p>
                  <p class="speaker">Introduction to Heroku</p>
              </td>
          </tr>
          <tr>
              <td class="track-5">Room 105</td>
              <td class="talk">
                  <p class="topic">Neil Green</p>
                  <p class="speaker">Nothing matters more than coding fast</p>
              </td>
          </tr>
          <tr>
              <td class="track-6">Room 113/114</td>
              <td class="talk">
                  <p class="topic">Raymond Camden</p>
                  <p class="speaker">Creating Mobile Friendly Sites with jQuery Mobile</p>
              </td>
          </tr>
          <tr>
                  <td class="time">02:15</td>
                  <td class="time">02:30</td>
                  <td colspan="3" class="break">Break [Galleria Attrium]</td>
          </tr>
          <tr>
               <td class="time"      rowspan="6">02:30</td>
               <td class="time"      rowspan="6">03:45</td>
               <td class="breakouts" rowspan="6">Breakouts</td>
               <td class="track-1">Salon A</td>
               <td class="talk">
                   <p class="topic">Introduction to the PlayN Cross-Platform Game Library</p>
                   <p class="speaker">Joel Webber</p>
               </td>
          </tr>
          <tr>
              <td class="track-2">Salon B</td>
              <td class="talk">
                  <p class="topic">Event-Driven Architectures</p>
                  <p class="speaker">Mark Ethan Trostler</p>
              </td>
          </tr>
          <tr>
              <td class="track-3">Salon C/D</td>
              <td class="talk">
                  <p class="topic">Joda Time and a Brief History of the World</p>
                  <p class="speaker">Daniel Hinojosa</p>
              </td>
          </tr>
          <tr>
              <td class="track-4">Room 104</td>
              <td class="talk">
                  <p class="topic">Mobilize JBoss AS 7.1 - How to Develop Mobile Apps for the Cloud w/ OpenShift</p>
                  <p class="speaker">Mark Atwood</p>
              </td>
          </tr>
          <tr>
              <td class="track-5">Room 105</td>
              <td class="talk">
                  <p class="topic">Testing Groovy and Java Systems</p>
                  <p class="speaker">Kenneth Kousen</p>
              </td>
          </tr>
          <tr>
              <td class="track-6">Room 113/114</td>
              <td class="talk">
                  <p class="topic">Raymond Camden</p>
                  <p class="speaker">Introduction to PhoneGap Mobile Development</p>
              </td>
          </tr>
          <tr>
                  <td class="time">03:45</td>
                  <td class="time">04:00</td>
                  <td colspan="3" class="break">Break [Galleria Attrium]</td>
          </tr>
          <tr>
               <td class="time"      rowspan="6">04:00</td>
               <td class="time"      rowspan="6">05:15</td>
               <td class="breakouts" rowspan="6">Breakouts</td>
               <td class="track-1">Salon A</td>
               <td class="talk">
                   <p class="topic">Building CDI extensions using DeltaSpike</p>
                   <p class="speaker">Jason Porter</p>
               </td>
          </tr>
          <tr>
              <td class="track-2">Salon B</td>
              <td class="talk">
                  <p class="topic">Building Maintainable Web Apps with HTML5 and Dart</p>
                  <p class="speaker">David Chandler</p>
              </td>
          </tr>
          <tr>
              <td class="track-3">Salon C/D</td>
              <td class="talk">
                  <p class="topic">Why you should learn Scala</p>
                  <p class="speaker">Josh Suereth</p>
              </td>
          </tr>
          <tr>
              <td class="track-4">Room 104</td>
              <td class="talk">
                  <p class="topic">Large Scale Data Analysis Tools</p>
                  <p class="speaker">Brad Anderson</p>
              </td>
          </tr>
          <tr>
              <td class="track-5">Room 105</td>
              <td class="talk">
                  <p class="topic">Architecture Anti-patterns</p>
                  <p class="speaker">Ram Singaram</p>
              </td>
          </tr>
          <tr>
              <td class="track-6">Room 113/114</td>
              <td class="talk">
                  <p class="topic">Java-Powered Cross-Platform Mobile Applications with PhoneGap</p>
                  <p class="speaker">Andrew Trice</p>
              </td>
          </tr>
            <tr>
                    <td class="time">05:15</td>
                    <td class="time">05:30</td>
                    <td colspan="3" class="break">Break [Galleria Attrium]</td>
            </tr>
          <tr>
                  <td class="time">05:30</td>
                  <td class="time">06:45</td>
                  <td class="keynote">Keynote</td>
                  <td class="keynote">Salon C/D</td>
                  <td class="talk">
                             <p class="topic">How We Got Here, And What To Do About It</p>
                             <p class="speaker">Barry Hawkins</p>
                  </td>
          </tr>
          <tr>
                  <td class="time">06:45</td>
                  <td class="time">08:00</td>
                  <td colspan="6">Cocktail Reception [Jocks and Jills Sports Bar]</td>
          </tr>

          <tr>
                  <th colspan="5" class="day">Thursday March 22, 2012</th>
          </tr>
          <tr>
                  <td class="time">08:00</td>
                  <td class="time">09:00</td>
                  <td colspan="3" class="registration">Breakfast [Galleria Attrium]</td>
          </tr>
          <tr>
                  <td class="time">09:00</td>
                  <td class="time">10:15</td>
                  <td class="keynote">Keynote</td>
                  <td class="keynote">Salon C/D</td>
                  <td class="talk">
                             <p class="topic">Web vs. Apps</p>
                             <p class="speaker">Ben Galbraith</p>
                  </td>
          </tr>
          <tr>
                  <td class="time">10:15</td>
                  <td class="time">10:30</td>
                  <td colspan="3" class="break">Break [Galleria Attrium]</td>
          </tr>
          <tr>
               <td class="time"      rowspan="6">10:30</td>
               <td class="time"      rowspan="6">11:45</td>
               <td class="breakouts" rowspan="6">Breakouts</td>
               <td class="track-1">Salon A</td>
               <td class="talk">
                   <p class="topic">What makes JBoss AS 7 tick?</p>
                   <p class="speaker">Marius Bogoevici</p>
               </td>
          </tr>
          <tr>
              <td class="track-2">Salon B</td>
              <td class="talk">
                  <p class="topic">The Gradle Will Rock</p>
                  <p class="speaker">Ken Kousen</p>
              </td>
          </tr>
          <tr>
              <td class="track-3">Salon C/D</td>
              <td class="talk">
                  <p class="topic">The Java SE Platform: Rebuilding Momentum</p>
                  <p class="speaker">Donald Smith</p>
              </td>
          </tr>
          <tr>
              <td class="track-4">Room 104</td>
              <td class="talk">
                  <p class="topic">NoSQL Smackdown 2012</p>
                  <p class="speaker">Tim Berglund</p>
              </td>
          </tr>
          <tr>
              <td class="track-5">Room 105</td>
              <td class="talk">
                  <p class="topic">Agile, Lean, and the Space Between</p>
                  <p class="speaker">Barry Hawkins</p>
              </td>
          </tr>
          <tr>
              <td class="track-6">Room 113/114</td>
              <td class="talk">
                  <p class="topic">Easy Mobile Development: Appcelerator Titanium Introduction</p>
                  <p class="speaker">Pratik Patel</p>
              </td>
          </tr>
          <tr>
                  <td class="time">11:45</td>
                  <td class="time">12:30</td>
                  <td colspan="3" class="break">Lunch [Salon C/D]</td>
          </tr>
          <tr>
                  <td class="time">12:30</td>
                  <td class="time">01:00</td>
                  <td colspan="3" class="break">Dessert [Galleria Attrium]</td>
          </tr>
          <tr>
               <td class="time"      rowspan="6">01:00</td>
               <td class="time"      rowspan="6">02:15</td>
               <td class="breakouts" rowspan="6">Breakouts</td>
               <td class="track-1">Salon A</td>
               <td class="talk">
                   <p class="topic">RESTful Imaginarium</p>
                   <p class="speaker">Jeremy Deane</p>
               </td>
          </tr>
          <tr>
              <td class="track-2">Salon B</td>
              <td class="talk">
                  <p class="topic">Build Trust in Your Build to Deployment Flow!</p>
                  <p class="speaker">Fred Simon</p>
              </td>
          </tr>
          <tr>
              <td class="track-3">Salon C/D</td>
              <td class="talk">
                  <p class="topic">Making Java Bearable with Guava</p>
                  <p class="speaker">Daniel Hinojosa</p>
              </td>
          </tr>
          <tr>
              <td class="track-4">Room 104</td>
              <td class="talk">
                  <p class="topic">Radical NoSQL Scalability with Cassandra</p>
                  <p class="speaker">Tim Berglund</p>
              </td>
          </tr>
          <tr>
              <td class="track-5">Room 105</td>
              <td class="talk">
                  <p class="topic">A walking tour of Spring 3.1</p>
                  <p class="speaker">Josh Long</p>
              </td>
          </tr>
          <tr>
              <td class="track-6">Room 113/114</td>
              <td class="talk">
                  <p class="topic">What's new in Android</p>
                  <p class="speaker">Robert Cooper</p>
              </td>
          </tr>
          <tr>
                  <td class="time">02:15</td>
                  <td class="time">02:30</td>
                  <td colspan="3" class="break">Break [Galleria Attrium]</td>
          </tr>
          <tr>
               <td class="time"      rowspan="6">02:30</td>
               <td class="time"      rowspan="6">03:45</td>
               <td class="breakouts" rowspan="6">Breakouts</td>
               <td class="track-1">Salon A</td>
               <td class="talk">
                   <p class="topic">Intro to Play Framework - Making Java & Scala Web App Development Fun</p>
                   <p class="speaker">James Ward</p>
               </td>
          </tr>
          <tr>
              <td class="track-2">Salon B</td>
              <td class="talk">
                  <p class="topic">Porting Your Game to the Web using NaCl</p>
                  <p class="speaker">Joel Webber</p>
              </td>
          </tr>
          <tr>
              <td class="track-3">Salon C/D</td>
              <td class="talk">
                  <p class="topic">JSR107: The new Java Caching Standard</p>
                  <p class="speaker">Greg Luck</p>
              </td>
          </tr>
          <tr>
              <td class="track-4">Room 104</td>
              <td class="talk">
                  <p class="topic">Spring and Cloud Foundry, a Marriage Made in Heaven</p>
                  <p class="speaker">Josh Long</p>
              </td>
          </tr>
          <tr>
              <td class="track-5">Room 105</td>
              <td class="talk">
                  <p class="topic">The Hitchhiker's Guide to the JBoss Galaxy</p>
                  <p class="speaker">Burr Sutter</p>
              </td>
          </tr>
          <tr>
              <td class="track-6">Room 113/114</td>
              <td class="talk">
                  <p class="topic">Native Android Development with Spring Android</p>
                  <p class="speaker">Roy Clarkson</p>
              </td>
          </tr>
          <tr>
                  <td class="time">03:45</td>
                  <td class="time">04:00</td>
                  <td colspan="3" class="break">Break [Galleria Attrium]</td>
          </tr>
          <tr>
               <td class="time"      rowspan="6">04:00</td>
               <td class="time"      rowspan="6">05:15</td>
               <td class="breakouts" rowspan="6">Breakouts</td>
               <td class="track-1">Salon A</td>
               <td class="talk">
                   <p class="topic">Rossen Stoyanchev</p>
                   <p class="speaker">An In-depth Look At Spring MVC 3.1</p>
               </td>
          </tr>
          <tr>
              <td class="track-2">Salon B</td>
              <td class="talk">
                  <p class="topic">Mind-blowing apps with HTML5 Canvas</p>
                  <p class="speaker">David Geary</p>
              </td>
          </tr>
          <tr>
              <td class="track-3">Salon C/D</td>
              <td class="talk">
                  <p class="topic">Effective Scala</p>
                  <p class="speaker">Josh Suereth</p>
              </td>
          </tr>
          <tr>
              <td class="track-4">Room 104</td>
              <td class="talk">
                  <p class="topic">Migrating Enterprise Apps to the Cloud: from monolith to modular</p>
                  <p class="speaker">Mark Fisher</p>
              </td>
          </tr>
          <tr>
              <td class="track-5">Room 105</td>
              <td class="talk">
                  <p class="topic">Git Going with Distributed Version Control</p>
                  <p class="speaker">Tim Berglund</p>
              </td>
          </tr>
          <tr>
              <td class="track-6">Room 113/114</td>
              <td class="talk">
                  <p class="topic">Google TV Basics and Boundaries</p>
                  <p class="speaker">Charlie Collins</p>
              </td>
          </tr>
          <tr>
                  <td class="time">05:15</td>
                  <td class="time">05:45</td>
                  <td colspan=3 class="keynote">Closing Ceremonies [Salon C/D]</td>
          </tr>

  </table>
</div>

<content tag='foo'>
  <script type="text/javascript">
  $(document).ready(function()
      {
        $("tr:even td.talk").css("background-color", "#D1D8DF");
      });
  </script>
</content>
