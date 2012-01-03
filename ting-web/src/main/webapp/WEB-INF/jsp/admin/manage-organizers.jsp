<%@ include file="/WEB-INF/jsp/includes/taglibs.jsp"%>

<title>Manage Organizers</title>
    <h2>Manage Organizers</h2>

    <form name="organizers" action="organizers">

        <jmesa:tableModel
            id="organizersTable"
            items="${organizers}"
            var="organizer"
            >
            <jmesa:htmlTable>
                <jmesa:htmlRow>
                     <jmesa:htmlColumn property="details" title="&nbsp;" filterable="false">
                        <a title="Organizer Detail" href="${ctx}/s/admin/organizer/${organizer.id}">
                            <img alt="Details" title="Details" src="${ctx}/img/icons/crystal/viewmag.png"/>
                        </a>
                    </jmesa:htmlColumn>
                    <jmesa:htmlColumn property="firstName" title="First Name"/>
                    <jmesa:htmlColumn property="lastName" title="Last Name"/>
                </jmesa:htmlRow>
            </jmesa:htmlTable>
        </jmesa:tableModel>
    </form>
    <a href="${ctx}/s/admin/organizer">Add organizer</a>

    <script type="text/javascript">
            function onInvokeAction(id) {
                $.jmesa.setExportToLimit(id, '');
                $.jmesa.createHiddenInputFieldsForLimitAndSubmit(id);
            }
            function onInvokeExportAction(id) {
                var parameterString = $.jmesa.createParameterStringForLimit(id);
                location.href = '${ctx}/s/admin/organizers?' + parameterString;
            }
    </script>