<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<jsp:include page="../globalPage.jsp" />

<!-- All js, css, data-table api are defined in globalPage.js -->

</head>
<body><br />
	<h2 class="text-center">Board Details</h2>

<div style="width:50%;  margin: 0px auto;" align="left">

<select onchange="getSubject()" id="selectboard">
<option value='' selected="selected" >Select Board</option>
<c:forEach var='board' items="${boardList}">
<option value="${board.id}">${board.name}</option>
</c:forEach>
</select>

</div>


<div style="width:50%;  margin: 0px auto;" align="right">
<input type="text" maxlength="50" id="newboard"/>
<button class='button' onclick="createBoard()">Create Board</button>
</div>

<div style="width:90% !important;  margin: 0px auto;" id='main' ></div>
<script type="text/javascript">
var boardId, boardName;
function getSubject(){
	if($('#selectboard').val()!='')
	{
		boardName=$('#selectboard option:selected').text();
		boardId=$('#selectboard').val();;
		$('#main').load("${pageContext.request.contextPath}/admin/getBoardSubjects?boardId="+$('#selectboard').val()+"&boardName="+$('#selectboard option:selected').text());
		
	}
	
}

function createBoard()
{
var board=document.getElementById('newboard');
///admin/createBoard
if(/^[a-z0-9 ]+$/i.test(board.value))
	{
	  $.ajax(
			    {
			        url : "${pageContext.request.contextPath}/admin/createBoard?boardName="+board.value,
			        type : "post",
			        dataType:"text",
			        success : function(data) 
			        {
			        	if(data=="success"){
			        	alert('Board created');
			        	location.reload();
			        	}
			        	else if(data=="duplicate")
			        		{
			        		alert('Duplicate board name. Please provide different name');
			        		}
			        	else if(data=="error")
			        		{
			        		alert("Error in saving data");
			        		}
			        	else
			        		alert("Please try again later");
			        },
			        error : function() 
			        {
			        },
			    });
	}
else
	alert('Insert a valid name');
}

</script>
