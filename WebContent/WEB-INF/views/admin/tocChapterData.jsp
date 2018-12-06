<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script>
	$(document).ready(function()
	{
		$(".gradeCls").html($("#sectionId option:selected").text());
		$(".subjectCls").html($("#subjectId option:selected").text());
		generateDataTable();
		
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
		$('#dataTableId').dataTable
		({
			"bPaginate": false,			// To off pagination
		}).rowReordering();
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
						alert("No grade id");
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
	
	/* function removeChapter()
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
			if(confirm("Are you sure to delete this chapter?") == false)
				return false;
			
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
						alert("Successfully deleted");
						getChapterData();
					}
				},
				error:function()
				{
					alert("Something went wrong");
				}
			});
		}
	} */
	
	function removeChapter(chapterId)
	{
		if(confirm("Are you sure to delete this chapter?") == false)
			return false;
		
		$.ajax
		({
			url:"${pageContext.request.contextPath}/admin/removeChapterToc.htm",
			type:"POST",
			data: {"chapterIdString": chapterId.toString()},
			success:function(data)
			{
				if(data == 'noChapter')
					alert("Please select chapter");
				else if(data.indexOf('<html') == -1)			// Since error page will have <html> tag
				{
					alert("Successfully deleted");
					getChapterData();
				}
			},
			error:function()
			{
				alert("Something went wrong");
			}
		});
	}
	
	function saveOrder()
	{
		if(confirm("Are you sure to save this serial order?") == false)
			return false;
		
		var idArray = [];
		var serialOrderArray = [];
		
		$("#dataTableId tbody tr td:nth-child(1)").each(function()
		{
			idArray.push($(this).parent().attr("id").trim());
			serialOrderArray.push($(this).text().trim());
		});
		
		$.ajax
		({
			url:"${pageContext.request.contextPath}/admin/saveSerialOrder.htm",
			type:"POST",
			data: {"idArray": idArray, "serialOrderArray": serialOrderArray, "forWhat": 'chapter'},
			success:function(data)
			{
				if(data.indexOf('<html') == -1)			// Since error page will have <html> tag
				{
					alert("Successfully updated");
					getChapterData();
				}
				else
				{
					alert("Something went wrong, please try again.");
				}
			},
			error:function()
			{
				alert("Something went wrong");
			}
		});
	}
	
	
	function editChapter()
	{
		var oldName=$('#txtOldChapter').val();
		var newName=$('#renamechapter').val();
		var id=$('#chapterId').val();
		
		console.log("Old Name:"+oldName+"New Name:"+newName+"id"+id);
		if(newName!='' && newName!=oldName)
		{
		
	$.ajax
	({
		url:"${pageContext.request.contextPath}/admin/updateChapter.htm",
		type:"POST",
		data: {id: id, oldName: oldName, newName: newName},
		success:function(data)
		{
			
			$('#editChapterClose').trigger("click");
			alert("Chapter updated");
			
			location.reload();
			
		},
		error:function()
		{
			alert("Something went wrong");
		}
	});
		}
	else
	alert("Please provide chapter name");
}
		
		
	
	
	function showEdit(id, chapterName)
	{
		console.log("Show editdescription for: "+id+":"+chapterName);
		$('#txtOldChapter').val(chapterName);
		$('#chapterId').val(id);
	}
</script>

<div style="width:90%;margin:0px auto;">
	<div>
		<button class="btn btn-success " data-toggle="modal" data-target="#myModal1" >Add New</button>
		<a class="btn btn-success " href="${pageContext.request.contextPath}/admin/getSubjectQuestions/1/${gradeId}/${subjectId}.htm?boardName=CTLS&gradeName=${gradeName}&subjectName=${subName}">Download Questions</a>
		<!-- <button class="btn btn-primary" data-toggle="modal" data-target="#myModal2">Edit</button> -->
		<!-- <button class="btn btn-danger" data-toggle="modal" data-target="#myModal3" onclick="removeChapter()">Remove</button> -->
		<button style="float:right" class="btn btn-success" onclick="saveOrder()">Save Order</button><br><br>
			
		<table style="border: 3px;background: rgb(243, 244, 248);width:100%">		<!-- Use it because it's helpful -->
			<tr style="width:100%;height:100%">
				<td>
					<table id="dataTableId" class="display" style="width:100%">
						<thead>
							<tr class="dataTab">
								<th>Sr. No.</th>
								<th>Grade</th>
								<th>Subject</th>
								<th>Chapter</th>
								<th>Action</th>
							</tr>
						</thead>
						<tbody>
							<c:set var="count" value="1"></c:set>
							<c:forEach var="chapter" items="${chapterList}">
								<tr id="${chapter.id}">
									<td>${count}</td><!--<input class="tableCheckbox" type="checkbox" value="${chapter.id}" name="chapterId"/>  -->
									<td><span class="gradeCls"></span></td>
									<td><span class="subjectCls"></span></td>
									<td>${chapter.name} </td>
									<td><a href="javascript:void(0)" onclick="removeChapter(${chapter.id})">Remove</a><a onclick="script:$('#txtOldChapter').val(chapterName); showEdit(${chapter.id},'${chapter.name}')"  data-toggle="modal" data-target="#myModal2"  href="javascript:void(0)" >/Edit</a></td> <!--<input style="width:30px" type="text" maxlength="2" value="${chapter.serialOrder}" />  -->
								</tr>
								<c:set var="count" value="${count+1}"></c:set>
							</c:forEach>
						</tbody>
					</table>
				</td>
			</tr>
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
	<div class="modal fade" id="myModal2" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close" id="editChapterClose">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">Edit Chapter Name</h4>
				</div>
				<div class="modal-body">
					<div class="col-md-12 col-sm-12 col-xs-12">
						<div class="row">
							<input type="text" id="txtOldChapter" placeholder="Topic Name" required="required" >
							<input type="text" id="renamechapter" placeholder="New Name" required="required" >
							<input type="hidden" name="chapterId" id="chapterId" />
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button class="btn btn-primary" onclick = "editChapter()">Update</button>
				</div>
			</div>
		</div>
	</div>
</div>
