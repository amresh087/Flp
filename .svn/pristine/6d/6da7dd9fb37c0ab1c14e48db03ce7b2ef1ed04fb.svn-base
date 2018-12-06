<html>
	<head>
		<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
		<meta charset="UTF-8">
		<jsp:include page="../globalPageSME.jsp" />		<!-- All js, css, data-table api are defined in globalPage.js -->
		
		<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>	
		<script src="http://malsup.github.com/jquery.form.js"></script>
		
		<script>
			function getQuestionData()
			{
				
				
				$('#fullPageloader').show();
				$('#dataDivId').empty();
				$('#dataDivId').load('${pageContext.request.contextPath}/admin/questionDataPageSME/'+$('#topicId').val()+'/'+$('#loId').val()+'.htm',function()
				{
				
					
					$('#addquestionscreen1234').hide();
					
					
					$('#fullPageloader').hide();
					
				
					
					
					
					
					
				});
			}
			
			function getChapters(refObj)
			{
				setDropdownList('#chapterId', refObj.val(), '${pageContext.request.contextPath}/ajax/rest/getChapters/'+$('#sectionId option:selected').attr('gradeid'), 'Select Chapter', false);
			}
			
			
			
			function getloId(refObj)
			{
			
				$('#loId').empty().append("<option value=''>Select lol</option>");
				$('#dataId').empty();
				setDropdownList('#loId', refObj.val(), '${pageContext.request.contextPath}/ajax/rest/getLo', 'Select lol', false);
			
				
				//getQuestionData();
			}
			
		</script>
	</head>
	<body>
		<h2 class="text-center">Questions</h2>
		
		<div style="width:90%;margin:0px auto;text-align:center">
			<select id="sectionId" onchange="setDropdownList('#subjectId', $(this).val(), '${pageContext.request.contextPath}/ajax/rest/getSubjects', 'Select Subject', false)" style="width:250px;height:37px">
				<option value="">Select Grade</option>
				<c:forEach var="section" items="${sectionList}">
					<option value="${section.id}" gradeid="${section.grade.id}">${section.grade.name}</option>
				</c:forEach>
			</select>
			<select id="subjectId" style="width:250px;height:37px" onchange="getChapters($(this))">
				<option value="">Select Subject</option>
			</select>
			<select id="chapterId" style="width:250px;height:37px" onchange="setDropdownList('#topicId', $(this).val(), '${pageContext.request.contextPath}/ajax/rest/getTopics', 'Select Topic', false)">
				<option value="">Select Chapter</option>
			</select>
			<select id="topicId" style="width:250px;height:37px" onchange="getloId($(this))">
				<option value="">Select Topic</option>
			</select>
			
			<select id="loId" style="width:250px;height:37px" onchange="getQuestionData()">
				<option value="">Select Learn Objective</option>
			</select>
		</div><br>
		

		
		<div></div>
		
		
		
		
		
		
		
		
		
		
		
		
		
		<div id="dataDivId"></div>
	</body>
</html>