<%@ include file="/WEB-INF/jsp/includes/taglibs.jsp"%>

<title>Manage Events</title>
    <h2>Manage Events</h2>

	<form name="events" action="events">

	    <jmesa:tableModel
	        id="eventsTable"
	        items="${events}"
	        var="event"
	        >
	        <jmesa:htmlTable>
	            <jmesa:htmlRow>
	                 <jmesa:htmlColumn property="details" title="&nbsp;" filterable="false">
                        <a title="Event Detail" href="${ctx}/s/admin/event/${event.id}">
                            <img alt="Details" title="Details" src="${ctx}/images/icons/crystal/viewmag.png"/>
                        </a>
	                </jmesa:htmlColumn>
	                <jmesa:htmlColumn property="current"  title="Current Event"/>
	                <jmesa:htmlColumn property="eventKey" title="Key"/>
	                <jmesa:htmlColumn property="title"    title="Title"/>
	            </jmesa:htmlRow>
	        </jmesa:htmlTable>
	    </jmesa:tableModel>
	</form>
    <a href="${ctx}/s/admin/event">Add Event</a>

    <script type="text/javascript">
            function onInvokeAction(id) {
                $.jmesa.setExportToLimit(id, '');
                $.jmesa.createHiddenInputFieldsForLimitAndSubmit(id);
            }
            function onInvokeExportAction(id) {
                var parameterString = $.jmesa.createParameterStringForLimit(id);
                location.href = '${ctx}/s/admin/events?' + parameterString;
            }
   </script>