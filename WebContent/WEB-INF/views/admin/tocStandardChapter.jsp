<html>
	<head>
		<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
		<meta charset="UTF-8">
		<jsp:include page="../globalPage.jsp" />		<!-- All js, css, data-table api are defined in globalPage.js -->
		
		<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
		
		<script>
			function loadDataPage()
			{
				$('#dataId').empty();
				
				if($('#subjectId').val() == '')
					return false;
				
				$('#fullPageloader').show();
				$('#dataId').empty().load(encodeURI('${pageContext.request.contextPath}/admin/getChapterTocDataStandardPage/'+$('#boardId').val()+'/'+$('#sectionId option:selected').attr("gradeid")+'/'+$('#subjectId').val()+'.htm?boardName='+$('#boardId option:selected').text()+'&gradeName='+$('#sectionId option:selected').text()+'&subjectName='+$('#subjectId option:selected').text()),function()
				{
					$('#fullPageloader').hide();
				});
			}
			
			function getSections(refObj)
			{
				$('#subjectId').empty().append("<option value=''>Select Subject</option>");
				$('#dataId').empty();
				
				setSectionDropdownList('#sectionId', refObj.val(), '${pageContext.request.contextPath}/ajax/rest/getSectionsForBoard', 'Select Grade');
			}
		</script>
	
	</head>
	<body>
		<h2 class="text-center">Chapter Details</h2>
		<div style="width:90%;margin:0px auto;text-align:center">
			<select id="boardId" style="width:250px;height:37px" onchange="getSections($(this))">
				<option value='' selected="selected" >Select Board</option>
				<c:forEach var='board' items="${boardList}">
					<option value="${board.id}">${board.name}</option>
				</c:forEach>
			</select>
			<select id="sectionId" style="width:250px;height:37px" onchange="setDropdownList('#subjectId', $(this).val(), '${pageContext.request.contextPath}/ajax/rest/getSubjects', 'Select Subject', false)">
				<option value="">Select Grade</option>
			</select>
			<select id="subjectId" style="width:250px;height:37px" onchange="loadDataPage()">
				<option value="">Select Subject</option>
			</select>
		</div><br>
		
		<div id="dataId"></div>
	</body>
</html>