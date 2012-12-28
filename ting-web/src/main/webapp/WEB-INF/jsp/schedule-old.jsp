<%@ include file="/WEB-INF/jsp/includes/taglibs.jsp"%>

<title>DevNexus 2013 - Schedule</title>
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
                  <th colspan="5" class="day">Monday February 18, 2013</th>
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
                           <p class="topic">TBD</p>
                           <p class="speaker">TBD</p>
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
                           <p class="topic">TBD</p>
                           <p class="speaker">TBD</p>
               </td>
          </tr>
          <tr>
              <td class="track-2">Salon B</td>
              <td class="talk">
                           <p class="topic">TBD</p>
                           <p class="speaker">TBD</p>
              </td>
          </tr>
          <tr>
              <td class="track-3">Salon C/D</td>
              <td class="talk">
                           <p class="topic">TBD</p>
                           <p class="speaker">TBD</p>
              </td>
          </tr>
          <tr>
              <td class="track-4">Room 104</td>
              <td class="talk">
                           <p class="topic">TBD</p>
                           <p class="speaker">TBD</p>
              </td>
          </tr>
          <tr>
              <td class="track-5">Room 105</td>
              <td class="talk">
                           <p class="topic">TBD</p>
                           <p class="speaker">TBD</p>
              </td>
          </tr>
          <tr>
              <td class="track-6">Room 113/114</td>
              <td class="talk">
                           <p class="topic">TBD</p>
                           <p class="speaker">TBD</p>
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
                           <p class="topic">TBD</p>
                           <p class="speaker">TBD</p>
               </td>
          </tr>
          <tr>
              <td class="track-2">Salon B</td>
              <td class="talk">
                           <p class="topic">TBD</p>
                           <p class="speaker">TBD</p>
              </td>
          </tr>
          <tr>
              <td class="track-3">Salon C/D</td>
              <td class="talk">
                           <p class="topic">TBD</p>
                           <p class="speaker">TBD</p>
              </td>
          </tr>
          <tr>
              <td class="track-4">Room 104</td>
              <td class="talk">
                           <p class="topic">TBD</p>
                           <p class="speaker">TBD</p>
              </td>
          </tr>
          <tr>
              <td class="track-5">Room 105</td>
              <td class="talk">
                           <p class="topic">TBD</p>
                           <p class="speaker">TBD</p>
              </td>
          </tr>
          <tr>
              <td class="track-6">Room 113/114</td>
              <td class="talk">
                           <p class="topic">TBD</p>
                           <p class="speaker">TBD</p>
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
                           <p class="topic">TBD</p>
                           <p class="speaker">TBD</p>
               </td>
          </tr>
          <tr>
              <td class="track-2">Salon B</td>
              <td class="talk">
                           <p class="topic">TBD</p>
                           <p class="speaker">TBD</p>
              </td>
          </tr>
          <tr>
              <td class="track-3">Salon C/D</td>
              <td class="talk">
                           <p class="topic">TBD</p>
                           <p class="speaker">TBD</p>
              </td>
          </tr>
          <tr>
              <td class="track-4">Room 104</td>
              <td class="talk">
                           <p class="topic">TBD</p>
                           <p class="speaker">TBD</p>
              </td>
          </tr>
          <tr>
              <td class="track-5">Room 105</td>
              <td class="talk">
                           <p class="topic">TBD</p>
                           <p class="speaker">TBD</p>
              </td>
          </tr>
          <tr>
              <td class="track-6">Room 113/114</td>
              <td class="talk">
                           <p class="topic">TBD</p>
                           <p class="speaker">TBD</p>
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
                           <p class="topic">TBD</p>
                           <p class="speaker">TBD</p>
               </td>
          </tr>
          <tr>
              <td class="track-2">Salon B</td>
              <td class="talk">
                           <p class="topic">TBD</p>
                           <p class="speaker">TBD</p>
              </td>
          </tr>
          <tr>
              <td class="track-3">Salon C/D</td>
              <td class="talk">
                           <p class="topic">TBD</p>
                           <p class="speaker">TBD</p>
              </td>
          </tr>
          <tr>
              <td class="track-4">Room 104</td>
              <td class="talk">
                           <p class="topic">TBD</p>
                           <p class="speaker">TBD</p>
              </td>
          </tr>
          <tr>
              <td class="track-5">Room 105</td>
              <td class="talk">
                           <p class="topic">TBD</p>
                           <p class="speaker">TBD</p>
              </td>
          </tr>
          <tr>
              <td class="track-6">Room 113/114</td>
              <td class="talk">
                           <p class="topic">TBD</p>
                           <p class="speaker">TBD</p>
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
                           <p class="topic">TBD</p>
                           <p class="speaker">TBD</p>
                  </td>
          </tr>
          <tr>
                  <td class="time">06:45</td>
                  <td class="time">08:00</td>
                  <td colspan="6">Cocktail Reception [Jocks and Jills Sports Bar]</td>
          </tr>

          <tr>
                  <th colspan="5" class="day">Tuesday February 19, 2013</th>
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
                           <p class="topic">TBD</p>
                           <p class="speaker">TBD</p>
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
                           <p class="topic">TBD</p>
                           <p class="speaker">TBD</p>
               </td>
          </tr>
          <tr>
              <td class="track-2">Salon B</td>
              <td class="talk">
                           <p class="topic">TBD</p>
                           <p class="speaker">TBD</p>
              </td>
          </tr>
          <tr>
              <td class="track-3">Salon C/D</td>
              <td class="talk">
                           <p class="topic">TBD</p>
                           <p class="speaker">TBD</p>
              </td>
          </tr>
          <tr>
              <td class="track-4">Room 104</td>
              <td class="talk">
                           <p class="topic">TBD</p>
                           <p class="speaker">TBD</p>
              </td>
          </tr>
          <tr>
              <td class="track-5">Room 105</td>
              <td class="talk">
                           <p class="topic">TBD</p>
                           <p class="speaker">TBD</p>
              </td>
          </tr>
          <tr>
              <td class="track-6">Room 113/114</td>
              <td class="talk">
                           <p class="topic">TBD</p>
                           <p class="speaker">TBD</p>
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
                           <p class="topic">TBD</p>
                           <p class="speaker">TBD</p>
               </td>
          </tr>
          <tr>
              <td class="track-2">Salon B</td>
              <td class="talk">
                           <p class="topic">TBD</p>
                           <p class="speaker">TBD</p>
              </td>
          </tr>
          <tr>
              <td class="track-3">Salon C/D</td>
              <td class="talk">
                           <p class="topic">TBD</p>
                           <p class="speaker">TBD</p>
              </td>
          </tr>
          <tr>
              <td class="track-4">Room 104</td>
              <td class="talk">
                           <p class="topic">TBD</p>
                           <p class="speaker">TBD</p>
              </td>
          </tr>
          <tr>
              <td class="track-5">Room 105</td>
              <td class="talk">
                           <p class="topic">TBD</p>
                           <p class="speaker">TBD</p>
              </td>
          </tr>
          <tr>
              <td class="track-6">Room 113/114</td>
              <td class="talk">
                           <p class="topic">TBD</p>
                           <p class="speaker">TBD</p>
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
                           <p class="topic">TBD</p>
                           <p class="speaker">TBD</p>
               </td>
          </tr>
          <tr>
              <td class="track-2">Salon B</td>
              <td class="talk">
                           <p class="topic">TBD</p>
                           <p class="speaker">TBD</p>
              </td>
          </tr>
          <tr>
              <td class="track-3">Salon C/D</td>
              <td class="talk">
                           <p class="topic">TBD</p>
                           <p class="speaker">TBD</p>
              </td>
          </tr>
          <tr>
              <td class="track-4">Room 104</td>
              <td class="talk">
                           <p class="topic">TBD</p>
                           <p class="speaker">TBD</p>
              </td>
          </tr>
          <tr>
              <td class="track-5">Room 105</td>
              <td class="talk">
                           <p class="topic">TBD</p>
                           <p class="speaker">TBD</p>
              </td>
          </tr>
          <tr>
              <td class="track-6">Room 113/114</td>
              <td class="talk">
                           <p class="topic">TBD</p>
                           <p class="speaker">TBD</p>
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
                           <p class="topic">TBD</p>
                           <p class="speaker">TBD</p>
               </td>
          </tr>
          <tr>
              <td class="track-2">Salon B</td>
              <td class="talk">
                           <p class="topic">TBD</p>
                           <p class="speaker">TBD</p>
              </td>
          </tr>
          <tr>
              <td class="track-3">Salon C/D</td>
              <td class="talk">
                           <p class="topic">TBD</p>
                           <p class="speaker">TBD</p>
              </td>
          </tr>
          <tr>
              <td class="track-4">Room 104</td>
              <td class="talk">
                           <p class="topic">TBD</p>
                           <p class="speaker">TBD</p>
              </td>
          </tr>
          <tr>
              <td class="track-5">Room 105</td>
              <td class="talk">
                           <p class="topic">TBD</p>
                           <p class="speaker">TBD</p>
              </td>
          </tr>
          <tr>
              <td class="track-6">Room 113/114</td>
              <td class="talk">
                           <p class="topic">TBD</p>
                           <p class="speaker">TBD</p>
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
