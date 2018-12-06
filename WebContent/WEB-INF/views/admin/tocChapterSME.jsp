<html>
	<head>
		<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
		<meta charset="UTF-8">
		<jsp:include page="../globalPageSME.jsp" />		<!-- All js, css, data-table api are defined in globalPage.js -->
		
		<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>	
		
		<script>
			function getChapterData()
			
			{	var gradeName=$('#sectionId option:selected').text();
				
			var	subName=$('#subjectId option:selected').text();
				
				$('#chapterDataId').empty();
				
				if($('#subjectId').val() == '')
					return false;
					
				$('#fullPageloader').show();
				$('#chapterDataId').empty().load(encodeURI('${pageContext.request.contextPath}/admin/getChapterDataTocPage/'+$('#sectionId option:selected').attr("gradeid")+'/'+$('#subjectId').val()+'.htm?gradeName='+gradeName+'&subjectName='+subName),function()
				{
					$('#fullPageloader').hide();
				});
			}
		</script>
	</head>
	<body>
		<h2 class="text-center">Chapter Details</h2>
		<div style="width:90%;margin:0px auto;text-align:center">
			<select id="sectionId" onchange="$('#chapterDataId').empty(), setDropdownList('#subjectId', $(this).val(), '${pageContext.request.contextPath}/ajax/rest/getSubjects', 'Select Subject', false)" style="width:250px;height:37px">
				<option value="">Select Grade</option>
				<c:forEach var="section" items="${sectionList}">
					<option value="${section.id}" gradeid="${section.grade.id}">${section.grade.name}</option>
				</c:forEach>
			</select>
			<select id="subjectId" style="width:250px;height:37px" onchange="getChapterData()">
				<option value="">Select Subject</option>
			</select>
		</div><br>
		<div id="chapterDataId"></div>
	</body>
</html>