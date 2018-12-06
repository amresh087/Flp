<div style="width:90% !important;  margin: 0px auto;" >

	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<button class="btn btn-success " data-toggle="modal" data-target="#myModal1" >Add New</button>
	<button type="button" class="btn btn-primary">Edit</button>
	<button type="button" class="btn btn-danger">Remove</button>
	<%-- <a href="#" style="padding-right:10px;" onclick="createSchool()"><img src="${pageContext.request.contextPath}/resources/images/add.png" width="100px" /></a>
	<a href="#" style="padding-right:10px;"><img src="${pageContext.request.contextPath}/resources/images/edit.png" width="80px" /></a>
	<a href="#" style="padding-right:10px;"><img src="${pageContext.request.contextPath}/resources/images/remove.png" width="100px" /></a> --%>
	<br /><br />
	<table id="gradeDetailsId" class="display" style="display: none;">
		<thead>
			<tr>
				<th>S.No.</th>
				<th>Board</th>
				<th>Grade</th>
			</tr>
		</thead>
		<tbody>
		 <c:choose>
		<c:when test="${not empty gradeList}">
		<c:forEach var="grade" items="${gradeList}" varStatus="status" step="1">
		<tr><td>${status.count }&nbsp;<input type="checkbox" value="${grade.id}" name="gradeId"/></td>
		<td>${boardName}</td>
		<td>${grade.name}</td>
		</tr>
		</c:forEach>
		</c:when>
		</c:choose> 
		
		</tbody>
	</table>
</div>

<!-- Modal -->
<div class="modal fade" id="myModal1" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">Add grade to board '${boardName}'</h4>
      </div>
      <form:form	action="${pageContext.request.contextPath}/admin/addSchool.htm"	commandName="school" method="post" id="addgradeDetailsId">
      <div class="modal-body">
       <div class="col-md-12 col-sm-12 col-xs-12"> <div class="row"><input type="text" name="gradeName" id="gradeName" placeholder="Grade Name" required="required" class="registration"></div></div>
      
      </div>
      <div class="modal-footer">
        <button class="btn btn-primary" onclick="createGrade()">Add Grade</button>
      </div>
      </form:form>
    </div>
  </div>
</div>
<script type="text/javascript">
$(document).ready(function() {
$('#gradeDetailsId').dataTable(
);
$('#gradeDetailsId').show();
});

function createGrade()
{
	
	if(/^[a-z0-9_\- ]+$/i.test($('#gradeName').val())){
	  $.ajax(
			    {
			        url : "${pageContext.request.contextPath}/admin/addGradeToBoard?gradeName="+$('#gradeName').val()+"&boardId="+boardId,
			        type : "post",
			        dataType:"text",
			        success : function(data) 
			        {
			        	if(data=="success"){
			        	alert('Grade created');
			        	location.reload();
			        	}
			        	else if(data=="duplicate")
			        		{
			        		alert('Duplicate grade name. Please provide different name');
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
		alert('Please provide a valide grade name');
	
}

</script>
