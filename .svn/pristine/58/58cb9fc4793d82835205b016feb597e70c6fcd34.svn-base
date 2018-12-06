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
				
				if($('#chapterId').val() == '')
					return false;
				
				$('#fullPageloader').show();
				$('#dataId').empty().load('${pageContext.request.contextPath}/admin/getTopicTocDataStandardPage/'+$('#boardId').val()+'/'+$('#chapterId').val()+'.htm',function()
				{
					$('#fullPageloader').hide();
				});
			}
			
			function getChapterss(refObj)
			{
				$('#chapterId').empty().append("<option value=''>Select Chapter</option>");
				$('#dataId').empty();
				
				setDropdownList('#chapterId', refObj.val(), '${pageContext.request.contextPath}/ajax/rest/getChapters/'+$('#sectionId option:selected').attr('gradeid'), 'Select Chapter', false);
			}
			
			function getSubjects(refObj)
			{
				$('#subjectId').empty().append("<option value=''>Select Subject</option>");
				$('#chapterId').empty().append("<option value=''>Select Chapter</option>");
				$('#dataId').empty();
				setDropdownList('#subjectId', refObj.val(), '${pageContext.request.contextPath}/ajax/rest/getSubjects', 'Select Subject', false);
			}
			
			function getSections(refObj)
			{
				$('#sectionId').empty().append("<option value=''>Select Grade</option>");
				$('#subjectId').empty().append("<option value=''>Select Subject</option>");
				$('#chapterId').empty().append("<option value=''>Select Chapter</option>");
				$('#dataId').empty();
				setSectionDropdownList('#sectionId', refObj.val(), '${pageContext.request.contextPath}/ajax/rest/getSectionsForBoard', 'Select Grade');
			}
		</script>
	
	</head>
	<body>
		<h2 class="text-center">Topic Details</h2>
		<div style="width:90%;margin:0px auto;text-align:center">
			<select id="boardId" style="width:250px;height:37px" onchange="getSections($(this))">
				<option value='' selected="selected" >Select Board</option>
				<c:forEach var='board' items="${boardList}">
					<option value="${board.id}">${board.name}</option>
				</c:forEach>
			</select>
			<select id="sectionId" style="width:250px;height:37px" onchange="getSubjects($(this))">
				<option value="">Select Grade</option>
			</select>
			<select id="subjectId" style="width:250px;height:37px" onchange="getChapterss($(this))">
				<option value="">Select Subject</option>
			</select>
			<select id="chapterId" style="width:250px;height:37px" onchange="loadDataPage()">
				<option value="">Select Chapter</option>
			</select>
		</div><br>
		
		<div id="dataId"></div>
	</body>
</html>