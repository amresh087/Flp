<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script>
	$(document).ready(function()
	{
		$(".gradeCls").html($("#sectionId option:selected").text());
		$(".subjectCls").html($("#subjectId option:selected").text());
		$(".chapterCls").html($("#chapterId option:selected").text());
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
	
	function saveTopic()
	{
		var boardId = 1;
		var gradeId = $('#sectionId option:selected').attr("gradeid");
		var sectionId = $('#sectionId').val();
		var subjectId = $('#subjectId').val();
		var chapterId = $('#chapterId').val();		
		var topicName = $('#topicName').val();
		
		if(topicName == '')
		{
			alert("Please type topic");
		}
		else
		{
			$.ajax
			({
				url:"${pageContext.request.contextPath}/admin/addTopicToc.htm",
				type:"POST",
				data: {"boardId": boardId, "gradeId": gradeId, "sectionId":sectionId, "subjectId":subjectId, "chapterId": chapterId, "topicName": topicName},
				success:function(data)
				{
					if(data == 'noBoard')
						console.log("No board id");
					else if(data == 'noGrade')
						console.log("No grade id");
					else if(data == 'noSubject')
						alert("Please select subject");
					else if(data == 'noChapter')
						alert("Please select chapter");
					else if(data == 'noTopic')
						alert("Please type topic");
					else if(data == 'topicNotAvailable')
						alert("Topic already exists");
					else if(data.indexOf('<html') == -1)			// Since error page will have <html> tag
					{
						$(".close").trigger('click');			// To hide the pop-up background
						alert("Topic successfully created");
						getTopicData();
					}
				},
				error:function()
				{
					alert("Something went wrong");
				}
			});
		}
	}
	
	/* function removeTopic()
	{
		var checkedValues = $('.tableCheckbox:checked').map(function() 
							{
							    return this.value;
							}).get();

		if(checkedValues == '')
		{
			alert("Please select a topic");
		}
		else
		{
			if(confirm("Are you sure to delete this topic?") == false)
				return false;
			
			$.ajax
			({
				url:"${pageContext.request.contextPath}/admin/removeTopicToc.htm",
				type:"POST",
				data: {"topicIdString": checkedValues.toString()},
				success:function(data)
				{
					if(data == 'noTopic')
						alert("Please select topic");
					else if(data.indexOf('<html') == -1)			// Since error page will have <html> tag
					{
						alert("successfully deleted");
						getTopicData();
					}
				},
				error:function()
				{
					alert("Something went wrong");
				}
			});
		}
	} */
	
	function removeTopic(topicId)
	{
		if(confirm("Are you sure to delete this topic?") == false)
			return false;
		
		$.ajax
		({
			url:"${pageContext.request.contextPath}/admin/removeTopicToc.htm",
			type:"POST",
			data: {"topicIdString": topicId.toString()},
			success:function(data)
			{
				if(data == 'noTopic')
					alert("Please select topic");
				else if(data.indexOf('<html') == -1)			// Since error page will have <html> tag
				{
					alert("successfully deleted");
					getTopicData();
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
			data: {"idArray": idArray, "serialOrderArray": serialOrderArray, "forWhat": 'topic'},
			success:function(data)
			{
				if(data.indexOf('<html') == -1)			// Since error page will have <html> tag
				{
					alert("Successfully updated");
					getTopicData();
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
	
	function editTopic()
	{
		var oldName=$('#txtOldTopic').val();
		var newName=$('#renameTopic').val();
		var id=$('#topicId').val();
		
		console.log("Old Name:"+oldName+"New Name:"+newName+"id"+id);
		if(newName!='' && newName!=oldName)
		{
		
	$.ajax
	({
		url:"${pageContext.request.contextPath}/admin/updateTopic.htm",
		type:"POST",
		data: {id: id, oldName: oldName, newName: newName},
		success:function(data)
		{
			
			$('#editTopicClose').trigger("click");
			alert("Topic updated");
			
			location.reload();
			
		},
		error:function()
		{
			alert("Something went wrong");
		}
	});
		}
	else
	alert("Please provide topic name");
}
		
		
	
	
	function showEdit(id, topicName)
	{
		console.log("Show editdescription for: "+id+":"+topicName);
		$('#txtOldTopic').val(topicName);
		$('#topicId').val(id);
	}
	
</script>

<div style="width:90%;margin:0px auto;">
	<div>
		<button class="btn btn-success " data-toggle="modal" data-target="#myModal1" >Add New</button>
		<!-- <button class="btn btn-primary" data-toggle="modal" data-target="#myModal2">Edit</button> -->
		<!-- <button class="btn btn-danger" data-toggle="modal" data-target="#myModal3" onclick="removeTopic()">Remove</button> -->
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
								<th>Topic</th>
								<th>Action</th>
							</tr>
						</thead>
						<tbody>
							<c:set var="count" value="1"></c:set>
							<c:forEach var="topic" items="${topicList}">
								<tr id="${topic.id}">
									<td>${count}</td> <!-- <input class="tableCheckbox" type="checkbox" value="${topic.id }" name="topicId"/> -->
									<td><span class="gradeCls"></span></td>
									<td><span class="subjectCls"></span></td>
									<td><span class="chapterCls"></span></td>
									<td>${topic.name}</td>
									<td><a href="javascript:void(0)" onclick="removeTopic(${topic.id})">Remove</a>-<a onclick="script:$('#txtOldTopic').val(topicName); showEdit(${topic.id},'${topic.name}')"  data-toggle="modal" data-target="#myModal2"  href="javascript:void(0)" >/Edit</a></td><!-- <input style="width:30px" type="text" maxlength="2" value="${topic.serialOrder}" /> -->
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
					<h4 class="modal-title" id="myModalLabel">Topic Detail</h4>
				</div>
				<div class="modal-body">
					<div class="col-md-12 col-sm-12 col-xs-12">
						<div class="row">
							<input type="text" id="topicName" placeholder="Topic Name" required="required" >
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button class="btn btn-primary" onclick = "saveTopic()">Add Topic</button>
				</div>
			</div>
		</div>
	</div>
	<div class="modal fade" id="myModal2" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close" id="editTopicClose">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">Edit Topic Name</h4>
				</div>
				<div class="modal-body">
					<div class="col-md-12 col-sm-12 col-xs-12">
						<div class="row">
							<input type="text" id="txtOldTopic" placeholder="Topic Name" required="required" >
							<input type="text" id="renameTopic" placeholder="New Name" required="required" >
							<input type="hidden" name="topicId" id="topicId" />
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button class="btn btn-primary" onclick = "editTopic()">Update</button>
				</div>
			</div>
		</div>
	</div>
</div>
