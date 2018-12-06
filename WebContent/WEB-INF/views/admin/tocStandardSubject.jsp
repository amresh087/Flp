<html>
	<head>
		<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
		<meta charset="UTF-8">
		<jsp:include page="../globalPage.jsp" />		<!-- All js, css, data-table api are defined in globalPage.js -->
		
		<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
		
		<script>
		<c:if test="${not empty sessionScope.boardIds5}">
		var boardId=parseInt('${sessionScope.boardIds5}');
		
		<c:remove var="boardIds5" scope="session"/>
		$.post('${pageContext.request.contextPath}/admin/getSubjectDataTocStandardPage/'+boardId+'.htm',function(data)
				{
			$("#dataId").html(data);
			$("select#boardId").val(boardId);
					$('#fullPageloader').hide();
				});
		</c:if>
		
			function loadDataPage()
			{
				$('#dataId').empty();
				
				if($('#boardId').val() == '')
					return false;
				
				loadPage1($('#boardId').val());
				
			}
			
			function loadPage1(boardId){
				$('#fullPageloader').show();
				$('#dataId').empty().load('${pageContext.request.contextPath}/admin/getSubjectDataTocStandardPage/'+boardId+'.htm',function()
				{
					$('#fullPageloader').hide();
				});
			}
		</script>
	
	</head>
	<body>
		<h2 class="text-center">Subject Details</h2>
		<div style="width:90%;margin:0px auto;text-align:center">
			<select id="boardId" style="width:250px;height:37px" onchange="loadDataPage()" >
				<option value='' selected="selected" >Select Board</option>
				<c:forEach var='board' items="${boardList}">
					<option value="${board.id}">${board.name}</option>
				</c:forEach>
			</select>
		</div><br>
		
		<div id="dataId"></div>
	</body>
</html>