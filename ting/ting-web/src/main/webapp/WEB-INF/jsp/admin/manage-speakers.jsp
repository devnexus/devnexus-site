<%@ include file="/WEB-INF/jsp/includes/taglibs.jsp"%>


<title>Manage Speakers</title>
    <h2>Manage Speakers</h2>

    <form name="speakers" action="speakers">

        <jmesa:tableModel
            id="speakersTable"
            items="${speakers}"
            var="speaker"
            >
            <jmesa:htmlTable>
                <jmesa:htmlRow>
                     <jmesa:htmlColumn property="details" title="&nbsp;" filterable="false">
                        <a title="Speaker Detail" href="${ctx}/s/admin/speaker/${speaker.id}">
                            <img alt="Details" title="Details" src="${ctx}/images/icons/crystal/viewmag.png"/>
                        </a>
                    </jmesa:htmlColumn>
                    <jmesa:htmlColumn property="firstName" title="First Name"/>
                    <jmesa:htmlColumn property="lastName" title="Last Name"/>
                </jmesa:htmlRow>
            </jmesa:htmlTable>
        </jmesa:tableModel>
    </form>
    <a href="${ctx}/s/admin/speaker">Add speaker</a>

    <script type="text/javascript">
            function onInvokeAction(id) {
                $.jmesa.setExportToLimit(id, '');
                $.jmesa.createHiddenInputFieldsForLimitAndSubmit(id);
            }
            function onInvokeExportAction(id) {
                var parameterString = $.jmesa.createParameterStringForLimit(id);
                location.href = '${ctx}/s/admin/speakers?' + parameterString;
            }
    </script>