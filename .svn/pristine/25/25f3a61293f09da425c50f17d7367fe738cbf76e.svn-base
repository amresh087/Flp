<html>
	<head>
		<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
		<meta charset="UTF-8">
		<jsp:include page="../globalPageSME.jsp" />		<!-- All js, css, data-table api are defined in globalPage.js -->
		
		<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>	
		
		<script>
			function getTopicData()
			{
				$('#topicDataId').empty();
				
				if($('#chapterId').val() == '')
					return false;
				
				$('#fullPageloader').show();
				$('#topicDataId').empty().load('${pageContext.request.contextPath}/admin/getTopicDataTocPage/'+$('#chapterId').val()+'.htm',function()
				{
					$('#fullPageloader').hide();
				});
			}
			
			function getChapters(refObj)
			{
				$('#topicDataId').empty();
				setDropdownList('#chapterId', refObj.val(), '${pageContext.request.contextPath}/ajax/rest/getChapters/'+$('#sectionId option:selected').attr('gradeid'), 'Select Chapter', false);
			}
			
			function getSubjects(refObj)
			{
				$('#chapterId').empty().append("<option value=''>Select Chapter</option>");
				$('#topicDataId').empty();
				setDropdownList('#subjectId', refObj.val(), '${pageContext.request.contextPath}/ajax/rest/getSubjects', 'Select Subject', false)
			}
		</script>
	</head>
	<body>
		<h2 class="text-center">Topic Details</h2>
		<div style="width:90%;margin:0px auto;text-align:center">
			<select id="sectionId" onchange="getSubjects($(this))" style="width:250px;height:37px">
				<option value="">Select Grade</option>
				<c:forEach var="section" items="${sectionList}">
					<option value="${section.id}" gradeid="${section.grade.id}">${section.grade.name}</option>
				</c:forEach>
			</select>
			<select id="subjectId" style="width:250px;height:37px" onchange="getChapters($(this))">
				<option value="">Select Subject</option>
			</select>
			<select id="chapterId" style="width:250px;height:37px" onchange="getTopicData()">
				<option value="">Select Chapter</option>
			</select>
		</div><br>
		<div id="topicDataId"></div>
	</body>
</html>