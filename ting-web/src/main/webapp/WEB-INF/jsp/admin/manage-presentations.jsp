<%@ include file="/WEB-INF/jsp/includes/taglibs.jsp"%>

<title>Manage Presentations</title>

    <h2>Manage Presentations</h2>

    <form name="events" action="presentations">

        <jmesa:tableModel
            id="presentationsTable"
            items="${presentations}"
            var="presentation"
            >
            <jmesa:htmlTable>
                <jmesa:htmlRow>
                     <jmesa:htmlColumn property="details" title="&nbsp;" filterable="false">
                      <a title="Presentation Detail" href="${ctx}/s/admin/presentation/${presentation.id}">
                          <img alt="Details" title="Details" src="${ctx}/img/icons/crystal/viewmag.png"/>
                      </a>
                    </jmesa:htmlColumn>
                    <jmesa:htmlColumn property="event.eventKey" title="Event"/>
                    <jmesa:htmlColumn property="title"          title="Title"/>
                     <jmesa:htmlColumn property="presentation.presentationFile" title="&nbsp;" filterable="false">
                        <c:if test="${id != null}">
                            X
                        </c:if>
                    </jmesa:htmlColumn>
                </jmesa:htmlRow>
            </jmesa:htmlTable>
        </jmesa:tableModel>
    </form>
    <a href="${ctx}/s/admin/presentation">Add presentation</a>



    <script type="text/javascript">
            function onInvokeAction(id) {
                $.jmesa.setExportToLimit(id, '');
                $.jmesa.createHiddenInputFieldsForLimitAndSubmit(id);
            }
            function onInvokeExportAction(id) {
                var parameterString = $.jmesa.createParameterStringForLimit(id);
                location.href = '${ctx}/s/admin/presentations?' + parameterString;
            }
    </script>