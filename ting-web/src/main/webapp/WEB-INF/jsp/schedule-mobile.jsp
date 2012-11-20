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
        <li><a href="#schedule-day1"><img class="ui-li-icon" alt="Day 1" src="${ctx}/img/icons/crystal/vcalendar.png"/>Day 1, Mo, Feb 18, 2013</a></li>
        <li><a href="#schedule-day2"><img class="ui-li-icon" alt="Day 2" src="${ctx}/img/icons/crystal/vcalendar.png"/>Day 2, Tue, Feb 19, 2013</a></li>
    </ul>
  </div>
  <!-- /content -->

  <div data-role="footer">
    <h4>&copy; 2013 AJUG</h4>
  </div>
  <!-- /header -->
</div>

<!-- Start of Schedule Day 1 -->
<div data-role="page" id="schedule-day1" data-theme="b">

  <div data-role="header">
    <a href="#schedule-index">Back</a>
    <h1>Day 1, Mon, Feb 18, 2013</h1>
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
            <li>
                    <h3>TBD</h3>
                    <h3>TBD</h3>
                        combination</h3>
                    <p class="ui-li-aside">
                        Ballroom <strong>C-D</strong>
                    </p>
            </li>
            <li data-role="list-divider">10:15-10:30am</li>
            <li data-theme="e">Break</li>
            <li data-role="list-divider">10:30-11:45am</li>
            <li>
                    <h3>TBD</h3>
                    <h3>TBD</h3>
                    <p class="ui-li-aside">
                        Salon <strong>A</strong>
                    </p>
            </li>
            <li>
                    <h3>TBD</h3>
                    <h3>TBD</h3>
                    <p class="ui-li-aside">
                        Salon <strong>B</strong>
                    </p>
            </li>
            <li>
                    <h3>TBD</h3>
                    <h3>TBD</h3>
                    <p class="ui-li-aside">
                        Salon <strong>C/D</strong>
                    </p>
            </li>
            <li>
                    <h3>TBD</h3>
                    <h3>TBD</h3>
                    <p class="ui-li-aside">
                        Room <strong>104</strong>
                    </p>
            </li>
            <li>
                    <h3>TBD</h3>
                    <h3>TBD</h3>
                    <p class="ui-li-aside">
                        Room <strong>105</strong>
                    </p>
            </li>
            <li>
                    <h3>TBD</h3>
                    <h3>TBD</h3>
                    <p class="ui-li-aside">
                        Room <strong>113/114</strong>
                    </p>
            </li>
            <li data-role="list-divider">11:45-12:30am</li>
            <li data-theme="e">Lunch</li>
            <li data-role="list-divider">12:30-01:00pm</li>
            <li data-theme="e">Dessert</li>
            <li data-role="list-divider">01:00pm-02:15pm</li>
            <li>
                    <h3>TBD</h3>
                    <h3>TBD</h3>
                        <p class="ui-li-aside">
                            Salon <strong>A</strong>
                        </p>
            </li>
            <li>
                    <h3>TBD</h3>
                    <h3>TBD</h3>
                        <p class="ui-li-aside">
                            Salon <strong>B</strong>
                        </p>
                </li>
            <li>
                    <h3>TBD</h3>
                    <h3>TBD</h3>
                        <p class="ui-li-aside">
                            Salon <strong>C/D</strong>
                        </p>
                </li>
            <li>
                    <h3>TBD</h3>
                    <h3>TBD</h3>
                        <p class="ui-li-aside">
                            Room <strong>104</strong>
                        </p>
                </li>
            <li>
                    <h3>TBD</h3>
                    <h3>TBD</h3>
                        <p class="ui-li-aside">
                            Room <strong>105</strong>
                        </p>
                </li>
            <li>
                    <h3>TBD</h3>
                    <h3>TBD</h3>
                        <p class="ui-li-aside">
                            Room <strong>113/114</strong>
                        </p>
               </li>
            <li data-role="list-divider">02:15-02:30pm</li>
            <li data-theme="e">Break</li>
            <li data-role="list-divider">02:30pm-03:45pm</li>
            <li>
                    <h3>TBD</h3>
                    <h3>TBD</h3>
                        <p class="ui-li-aside">
                            Salon <strong>A</strong>
                        </p>
                </li>
            <li>
                    <h3>TBD</h3>
                    <h3>TBD</h3>
                        <p class="ui-li-aside">
                            Salon <strong>B</strong>
                        </p>
                </li>
            <li>
                    <h3>TBD</h3>
                    <h3>TBD</h3>
                        <p class="ui-li-aside">
                            Salon <strong>C/D</strong>
                        </p>
                </li>
            <li>
                    <h3>TBD</h3>
                    <h3>TBD</h3>
                        <p class="ui-li-aside">
                            Room <strong>104</strong>
                        </p>
                </li>
            <li>
                    <h3>TBD</h3>
                    <h3>TBD</h3>
                        <p class="ui-li-aside">
                            Room <strong>105</strong>
                        </p>
                </li>
            <li>
                    <h3>TBD</h3>
                    <h3>TBD</h3>
                        <p class="ui-li-aside">
                            Room <strong>113/114</strong>
                        </p>
                </li>
            <li data-role="list-divider">03:45-04:00pm</li>
            <li data-theme="e">Break</li>
            <li data-role="list-divider">04:00-05:15pm</li>
            <li>
                    <h3>TBD</h3>
                    <h3>TBD</h3>
                        <p class="ui-li-aside">
                            Salon <strong>A</strong>
                        </p>
                </li>
            <li>
                    <h3>TBD</h3>
                    <h3>TBD</h3>
                        <p class="ui-li-aside">
                            Salon <strong>B</strong>
                        </p>
                </li>
            <li>
                    <h3>TBD</h3>
                    <h3>TBD</h3>
                        <p class="ui-li-aside">
                            Salon <strong>C/D</strong>
                        </p>
                </li>
            <li>
                    <h3>TBD</h3>
                    <h3>TBD</h3>
                        <p class="ui-li-aside">
                            Room <strong>104</strong>
                        </p>
                </li>
            <li>
                    <h3>TBD</h3>
                    <h3>TBD</h3>
                        <p class="ui-li-aside">
                            Room <strong>105</strong>
                        </p>
                </li>
            <li>
                    <h3>TBD</h3>
                    <h3>TBD</h3>
                        <p class="ui-li-aside">
                            Room <strong>113/114</strong>
                        </p>
            </li>
            <li data-role="list-divider">05:15-05:30pm</li>
            <li data-theme="e">Break</li>
            <li data-role="list-divider">05:30-06:30pm</li>
            <li>
                    <h3>TBD</h3>
                    <h3>TBD</h3>
                            <p class="ui-li-aside">
                                Salon <strong>C/D</strong>
                            </p>
            </li>
            <li data-role="list-divider">06:30-12:00am</li>
            <li data-theme="e">Cocktail Hour</li>
        </ul>
  </div>
  <!-- /content -->

  <div data-role="footer">
    <h4>&copy; 2013 AJUG</h4>
  </div>
</div><!-- /Schedule Day 1 -->

<!-- Start of Schedule Day 2 -->
<div data-role="page" id="schedule-day2" data-theme="b">

    <div data-role="header">
        <a href="#schedule-index">Back</a>
        <h1>Day 2, Tue, Feb 19, 2013</h1>
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
            <li>
                    <h3>TBD</h3>
                    <h3>TBD</h3>
                    <p class="ui-li-aside">
                        Ballroom <strong>C-D</strong>
                    </p>
            </li>
            <li data-role="list-divider">10:15-10:30am</li>
            <li data-theme="e">Break</li>
            <li data-role="list-divider">10:30-11:45am</li>
            <li>
                    <h3>TBD</h3>
                    <h3>TBD</h3>
                    <p class="ui-li-aside">
                        Salon <strong>A</strong>
                    </p>
            </li>
            <li>
                    <h3>TBD</h3>
                    <h3>TBD</h3>
                    <p class="ui-li-aside">
                        Salon <strong>B</strong>
                    </p>
            </li>
            <li>
                    <h3>TBD</h3>
                    <h3>TBD</h3>
                    <p class="ui-li-aside">
                        Salon <strong>C/D</strong>
                    </p>
            </li>
            <li>
                    <h3>TBD</h3>
                    <h3>TBD</h3>
                    <p class="ui-li-aside">
                        Room <strong>104</strong>
                    </p>
            </li>
            <li>
                    <h3>TBD</h3>
                    <h3>TBD</h3>
                    <p class="ui-li-aside">
                        Room <strong>105</strong>
                    </p>
            </li>
            <li>
                    <h3>TBD</h3>
                    <h3>TBD</h3>
                    <p class="ui-li-aside">
                        Room <strong>113/114</strong>
                    </p>
            </li>
            <li data-role="list-divider">11:45-12:30am</li>
            <li data-theme="e">Lunch</li>
            <li data-role="list-divider">12:30-01:00pm</li>
            <li data-theme="e">Dessert</li>
            <li data-role="list-divider">01:00pm-02:15pm</li>
            <li>
                    <h3>TBD</h3>
                    <h3>TBD</h3>
                        <p class="ui-li-aside">
                            Salon <strong>A</strong>
                        </p>
                </li>
            <li>
                    <h3>TBD</h3>
                    <h3>TBD</h3>
                        <p class="ui-li-aside">
                            Salon <strong>B</strong>
                        </p>
                </li>
            <li>
                    <h3>TBD</h3>
                    <h3>TBD</h3>
                        <p class="ui-li-aside">
                            Salon <strong>C/D</strong>
                        </p>
                </li>
            <li>
                    <h3>TBD</h3>
                    <h3>TBD</h3>
                        <p class="ui-li-aside">
                            Room <strong>104</strong>
                        </p>
                </li>
            <li>
                    <h3>TBD</h3>
                    <h3>TBD</h3>
                        <p class="ui-li-aside">
                            Salon <strong>105</strong>
                        </p>
                </li>
            <li>
                    <h3>TBD</h3>
                    <h3>TBD</h3>
                        <p class="ui-li-aside">
                            Room <strong>113/114</strong>
                        </p>
                </li>
            <li data-role="list-divider">02:15-02:30pm</li>
            <li data-theme="e">Break</li>
            <li data-role="list-divider">02:30pm-03:45pm</li>
            <li>
                    <h3>TBD</h3>
                    <h3>TBD</h3>
                        <p class="ui-li-aside">
                            Salon <strong>A</strong>
                        </p>
             </li>
            <li>
                    <h3>TBD</h3>
                    <h3>TBD</h3>
                        <p class="ui-li-aside">
                            Salon <strong>B</strong>
                        </p>
                </li>
            <li>
                    <h3>TBD</h3>
                    <h3>TBD</h3>
                        <p class="ui-li-aside">
                            Salon <strong>C/D</strong>
                        </p>
                </li>
            <li>
                    <h3>TBD</h3>
                    <h3>TBD</h3>
                        <p class="ui-li-aside">
                            Room <strong>104</strong>
                        </p>
                </li>
            <li>
                    <h3>TBD</h3>
                    <h3>TBD</h3>
                        <p class="ui-li-aside">
                            Room <strong>105</strong>
                        </p>
                </li>
            <li>
                    <h3>TBD</h3>
                    <h3>TBD</h3>
                        <p class="ui-li-aside">
                            Room <strong>113/114</strong>
                        </p>
            </li>
            <li data-role="list-divider">03:45-04:00pm</li>
            <li data-theme="e">Break</li>
            <li data-role="list-divider">04:00-05:15pm</li>
            <li>
                    <h3>TBD</h3>
                    <h3>TBD</h3>
                        <p class="ui-li-aside">
                            Salon <strong>A</strong>
                        </p>
            </li>
            <li>
                    <h3>TBD</h3>
                    <h3>TBD</h3>
                        <p class="ui-li-aside">
                            Salon <strong>B</strong>
                        </p>
            </li>
            <li>
                    <h3>TBD</h3>
                    <h3>TBD</h3>
                        <p class="ui-li-aside">
                            Salon <strong>C/D</strong>
                        </p>
             </li>
            <li>
                    <h3>TBD</h3>
                    <h3>TBD</h3>
                        <p class="ui-li-aside">
                            Room <strong>104</strong>
                        </p>
            </li>
            <li>
                    <h3>TBD</h3>
                    <h3>TBD</h3>
                        <p class="ui-li-aside">
                            Room <strong>105</strong>
                        </p>
            </li>
            <li>
                    <h3>TBD</h3>
                    <h3>TBD</h3>
                        <p class="ui-li-aside">
                            Room <strong>113/114</strong>
                        </p>
            </li>
            <li data-role="list-divider">05:15-05:45pm</li>
            <li data-theme="e">Closing Ceremonies</li>
        </ul>
  </div>
    <div data-role="footer">
        <h4>&copy; 2013 AJUG</h4>
    </div>
</div><!-- /Schedule Day 1 -->

