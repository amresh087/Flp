<html>
	<head>
		<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
		<meta charset="UTF-8">
		<jsp:include page="../globalPage.jsp" />		<!-- All js, css, data-table api are defined in globalPage.js -->
		
		<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>	
		
		<script>
			function getQuestionData()
			{
				$('#fullPageloader').show();
				$('#dataDivId').empty().load('${pageContext.request.contextPath}/sme/questionDataPage/'+$('#topicId').val()+'.htm',function()
				{
					$('#fullPageloader').hide();
				});
			}
			
			function getChapters(refObj)
			{
				setDropdownList('#chapterId', refObj.val(), '${pageContext.request.contextPath}/ajax/rest/getChapters/'+$('#gradeId option:selected').val(), 'Select Chapter', false);
			}
		</script>
	</head>
	<body>
		<h2 class="text-center">Questions</h2>
		<div style="width:90%;margin:0px auto;text-align:center">
			<select id="gradeId" onchange="$('#chapterDataId').empty(), setDropdownList('#subjectId', $(this).val(), '${pageContext.request.contextPath}/ajax/rest/getSubjectsSme', 'Select Subject', false)" style="width:250px;height:37px">
				<option value="">Select Grade</option>
				<c:forEach var="grade" items="${gradeList}">
					<option value="${grade.id}">${grade.name}</option>
				</c:forEach>
			</select>
			<select id="subjectId" style="width:250px;height:37px" onchange="getChapters($(this))">
				<option value="">Select Subject</option>
			</select>
			<select id="chapterId" style="width:250px;height:37px" onchange="setDropdownList('#topicId', $(this).val(), '${pageContext.request.contextPath}/ajax/rest/getTopics', 'Select Topic', false)">
				<option value="">Select Chapter</option>
			</select>
			<select id="topicId" style="width:250px;height:37px" onchange="getQuestionData()">
				<option value="">Select Topic</option>
			</select>
		</div><br>
		<div id="dataDivId"></div>
	</body>
</html>