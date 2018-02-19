<%@ include file="/WEB-INF/template/include.jsp" %>
<%@ include file="/WEB-INF/template/header.jsp" %>
<style>iframe {
    width: 1px;
    min-width: 100%;
    min-height: 1000px
}</style>

<script type="text/javascript">
    function getCharPosition(str, subString, index) {
        return str.split(subString, index).join(subString).length;
    }

    function findPatient(baseUrl, param) {
        jQuery.getJSON(baseUrl + "/ws/rest/v1/patient?q=" + param, (function() {
            return function (data) {
                if (data.results.length == 0) {
                    document.getElementById('ohifViewer').contentWindow.postMessage('0|' + param, '*');
                } else {
                    document.getElementById('ohifViewer').contentWindow.postMessage('foundPatient|' + data.results[0].uuid, '*');
                }
            };
        })(param));
    }

    function createPatient(baseUrl, param) {
        jQuery.ajax({
            type: "POST",
            url: baseUrl + "/ws/rest/v1/patient",
            data: param,
            contentType: 'application/json'
        }).done(function (data) {
            window.open(baseUrl + '/module/radiology/radiologyReport.form?patient=' + data.uuid,
                '_blank', 'toolbar=0,location=0,menubar=0, height=800, width=800');
        });
    }

    function receiveMessage(event) {
        url = window.location.href;
        var baseUrl = url.substring(0, getCharPosition(url, '/', 4));
        var msg = event.data;
        var functionName = msg.split('|')[0];
        var param = msg.split('|')[1];

        if (param != undefined) {
            if (functionName == 'findPatient') {
                findPatient(baseUrl, param);
            } else if (functionName == 'newReport') {
                window.open(baseUrl + '/module/radiology/radiologyReport.form?patient=' + param,
                    '_blank', 'toolbar=0,location=0,menubar=0, height=800, width=800');
            } else {
                createPatient(baseUrl, param);
            }
        }
    }

    jQuery(document).ready(function () {
        window.addEventListener("message", receiveMessage);
    });

</script>

<openmrs:hasPrivilege privilege="View Orders">
    <openmrs:globalProperty key="radiology.ohifViewerUrl" var="ohifViewerUrl"/>
    <iframe id="ohifViewer" src="${ohifViewerUrl}" frameborder="0"></iframe>
</openmrs:hasPrivilege>
<%@ include file="/WEB-INF/template/footer.jsp" %>
