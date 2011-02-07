<%@include file="/WEB-INF/jsp/includes/taglibs.jsp"%>

		<!-- Start of first page -->
		<div data-role="page" id="organizers" data-theme="b">

		    <div data-role="header">
		        <a href="${ctx}/s/index">Back</a>
		        <h1>DevNexus 2011</h1>
		    </div><!-- /header -->

		    <div data-role="content">
		        <ul data-role="listview" data-inset="true" data-theme="c" data-dividertheme="b">
		            <li data-role="list-divider">Organizers</li>
		              <li>
		                  <img alt="Burr Sutter" src="${ctx}/images/organizers/burr_sutter.jpg"/>
		                  <a href="#burr_sutter">Burr Sutter</a>
		              </li>
		              <li>
		                  <img alt="Gunnar Hillert" src="${ctx}/images/organizers/gunnar_hillert.jpg"/>
		                  <a href="#gunnar_hillert">Gunnar Hillert</a>
		              </li>
		              <li>
		                  <img alt="Vincent Mayers" src="${ctx}/images/organizers/vincent_mayers.jpg"/>
		                  <a href="#vincent_mayers">Vincent Mayers</a>
		              </li>
		              <li>
		                  <img alt="Sudhir Kamatkar" src="${ctx}/images/organizers/sudhir_kamatkar.jpg"/>
		                  <a href="#sudhir_kamatkar">Sudhir Kamatkar</a>
		              </li>
		              <li>
		                  <img alt="Bruce Petro" src="${ctx}/images/organizers/bruce_petro.jpg"/>
		                  <a href="#bruce_petro">Bruce Petro</a>
		               </li>
		              <li>
		                  <img alt="Summers Pittman" src="${ctx}/images/organizers/summers_pittman.jpg"/>
		                  <a href="#summers_pittman">Summers Pittman</a>
		              </li>
		              <li>
		                  <img alt="Vincent Stoessel" src="${ctx}/images/organizers/vincent_stoessel.jpg"/>
		                  <a href="#vincent_stoessel">Vincent Stoessel</a>
		              </li>
		        </ul>
		    </div><!-- /content -->

		    <div data-role="footer">
		        <h4>&copy; 2011 AJUG</h4>
		    </div><!-- /header -->
		</div><!-- /page -->


        <c:forEach items="${organizerList.organizers}" var="organizer">
            <div class="speaker">
                <h3 id="${organizer.firstName}_${organizer.lastName}"><c:out value="${organizer.firstName}"/> <c:out value="${organizer.lastName}"/></h3>
                <c:if test="${organizer.picture != null}">
                   <img src="${ctx}/s/organizers/${organizer.id}.jpg"/>
                </c:if>
                <p>
                  <c:set var="organizerBio"><c:out value="${organizer.bio}" escapeXml="true"/></c:set>
                  <c:out value="${fn:replace(organizerBio, lf, '<br/>')}" escapeXml="false"/>
                </p>
                <br style="clear: both;"/>
            </div>
        </c:forEach>

		<!-- Start of second page -->
		<div data-role="page" id="burr_sutter" data-theme="b">

		    <div data-role="header">
		        <h1>Burr Sutter</h1>
		    </div><!-- /header -->

		    <div data-role="content">
		            <img alt="Burr Sutter" src="${ctx}/images/organizers/burr_sutter.jpg"/>
		            <p>
		              Burr Sutter is a current Sun Java Champion with over 15 years of
		              software design and development experience along with numerous published
		              articles, book chapters and developer conference speaking engagements.
		              He is presently employed at JBoss, a division of Red Hat.
		            </p>
		    </div><!-- /content -->

		    <div data-role="footer">
		        <h4>&copy; 2011 AJUG</h4>
		    </div><!-- /header -->
		</div><!-- /page -->
