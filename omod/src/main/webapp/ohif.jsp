<%@ include file="/WEB-INF/template/include.jsp"%>
<%@ include file="/WEB-INF/template/header.jsp"%>

<style>iframe{width: 1px;min-width: 100%; min-height: 1000px}</style>

<openmrs:hasPrivilege privilege="View Orders">
    <iframe id="ohifViewer" src="http://localhost:3000" frameborder="0"></iframe>
</openmrs:hasPrivilege>

<%@ include file="/WEB-INF/template/footer.jsp"%>
