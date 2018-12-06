<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script>
	$(document).ready(function()
	{
		generateDataTable();
		$(".gradeCls").html($("#sectionId option:selected").text());
		$(".subjectCls").html($("#subjectId option:selected").text());
		
		selectOnlyOneCheckbox();
	});
	
	function selectOnlyOneCheckbox()
	{
		$('.tableCheckbox').on('change', function() 
		{
		    $('.tableCheckbox').not(this).prop('checked', false);  
		});
	}
	
	function generateDataTable()
	{
		//$('#reportTableID').dataTable().fnDestroy();			// Use it when you want to reinitialize data table
		$('#dataTableId').dataTable();
	}
	
	function saveChapter()
	{
		var boardId = 1;
		var gradeId = $('#sectionId option:selected').attr("gradeid");
		var sectionId = $('#sectionId').val();
		var subjectId = $('#subjectId').val();
		var chapterName = $('#chapterName').val();
		
		if(chapterName == '')
		{
			alert("Please type chapter");
		}
		else
		{  
			$.ajax
			({
				url:"${pageContext.request.contextPath}/admin/addChapterToc.htm",
				type:"POST",
				data: {"boardId": boardId, "gradeId": gradeId, "sectionId":sectionId, "subjectId":subjectId, "chapterName": chapterName},
				success:function(data)
				{
					if(data == 'noBoard')
						alert("Please select board");
					else if(data == 'noGrade')
						console.log("No grade id");
					else if(data == 'noSubject')
						alert("Please select subject");
					else if(data == 'noChapter')
						alert("Please type chapter");
					else if(data == 'chapterNotAvailable')
						alert("Chapter already exists");
					else if(data.indexOf('<html') == -1)			// Since error page will have <html> tag
					{
						$(".close").trigger('click');			// To hide the pop-up background
						alert("Chapter successfully created");
						getChapterData();
					}
				},
				error:function()
				{
					alert("Something went wrong");
				}
			});
		}
	}
	
	function removeChapter()
	{
		var checkedValues = $('.tableCheckbox:checked').map(function() 
							{
							    return this.value;
							}).get();
		
		if(checkedValues == '')
		{
			alert("Please select a chapter");
		}
		else
		{
			$.ajax
			({
				url:"${pageContext.request.contextPath}/admin/removeChapterToc.htm",
				type:"POST",
				data: {"chapterIdString": checkedValues.toString()},
				success:function(data)
				{
					if(data == 'noChapter')
						alert("Please select chapter");
					else if(data.indexOf('<html') == -1)			// Since error page will have <html> tag
					{
						alert("successfully deleted");
						getChapterData();
					}
				},
				error:function()
				{
					alert("Something went wrong");
				}
			});
		}
	}
</script>

<div style="width:90%;margin:0px auto;">
	<div>
		<button class="btn btn-success " data-toggle="modal" data-target="#myModal1" >Add New</button>
		<!-- <button class="btn btn-primary" data-toggle="modal" data-target="#myModal2">Edit</button> -->
		<button class="btn btn-danger" data-toggle="modal" data-target="#myModal3" onclick="removeChapter()">Remove</button><br><br>
		
	
					<table id="dataTableId" class="display" style="width:100%">
						<thead>
							<tr>
								<th>Sr. No.</th>
								<th>Grade</th>
								<th>Subject</th>
								<th>Chapter</th>
								<th>Serial Order</th>
							</tr>
						</thead>
						<tbody>
							<c:set var="count" value="1"></c:set>
							<c:forEach var="chapter" items="${chapterList}">
								<tr>
									<td><input class="tableCheckbox" type="checkbox" value="${chapter.id}" name="chapterId"/> ${count}</td>
									<td><span class="gradeCls"></span></td>
									<td><span class="subjectCls"></span></td>
									<td>${chapter.name} </td>
									<td><input style="width:30px" type="text" maxlength="2" value="${chapter.serialOrder}" /></td>
								</tr>
								<c:set var="count" value="${count+1}"></c:set>
							</c:forEach>
						</tbody>
					</table>
				
	</div>
	
	<div class="modal fade" id="myModal1" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">Chapter Detail</h4>
				</div>
				<div class="modal-body">
					<div class="col-md-12 col-sm-12 col-xs-12">
						<div class="row">
							<input type="text" id="chapterName" placeholder="Chapter Name" required="required" >
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button class="btn btn-primary" onclick = "saveChapter()">Add Chapter</button>
				</div>
			</div>
		</div>
	</div>
</div>
