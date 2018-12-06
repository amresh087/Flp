<meta charset="UTF-8">
<html>
	<head>
		<meta charset="UTF-8">
		<jsp:include page="../globalPage.jsp" />		<!-- All js, css, data-table api are defined in globalPage.js -->
		
		<script>
			function createSubject()
			{
				//openPopupBoxBackgroundReportPopup();
		       	$('#contentDivId').empty().load("${pageContext.request.contextPath}/admin/getSubjectTocPage.html").show();
			}
			function createChapter()
			{
				//openPopupBoxBackgroundReportPopup();
		       	$('#contentDivId').empty().load("${pageContext.request.contextPath}/admin/getChapterTocPage.html").show();
			}
			function createTopic()
			{
				//openPopupBoxBackgroundReportPopup();
		       	$('#contentDivId').empty().load("${pageContext.request.contextPath}/admin/getTopicTocPage.html").show();
			}
		</script>
	</head>
	<body>
		<button onclick="createSubject()">Create Subject</button>
		<button onclick="createChapter()">Create Chapter</button>
		<button onclick="createTopic()">Create Topic</button>
		
		<br><br><br>
		<div id="contentDivId" style="width:100%;height:600px"></div>
		
	</body>
</html>