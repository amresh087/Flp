<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script>
	$(document).ready(function()
	{
		$(".gradeCls").html($("#gradeId option:selected").text());
		$(".subjectCls").html($("#subjectId option:selected").text());
		$(".chapterCls").html($("#chapterId option:selected").text());
		generateDataTable();
		
		selectOnlyOneCheckbox();
	});
	
	$(".editTopicCls").on("click", function(){
		
		$("#topicNameId").val($(this).attr('dir'));
		$("#hidetopicId").val($(this).attr('id'));
		//$("#editTopicNameIds").trigger("click");
	});

	/* function editFunction(id,name){
		
		alert("hi");
		
		
	} */
	
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
	
	function saveTopic()
	{
		var gradeId = $('#gradeId option:selected').val();
		var subjectId = $('#subjectId').val();
		var chapterId = $('#chapterId').val();		
		var topicName = $('#topicName').val();
		
		if(topicName == '')
		{
			alert("Please enter topic name");
		}
		else
		{
			$.ajax
			({
				url:"${pageContext.request.contextPath}/sme/addTopic.htm",
				type:"POST",
				data: {"gradeId": gradeId, "subjectId":subjectId, "chapterId": chapterId, "topicName": topicName},
				success:function(data)
				{
					if(data == 'noSchool')
						console.log("No school id");
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
	
	function removeTopic()
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
				url:"${pageContext.request.contextPath}/sme/removeTopic.htm",
				type:"POST",
				data: {"topicIdString": checkedValues.toString()},
				success:function(data)
				{
					if(data == 'noTopic')
						alert("Please select topic");
					else if(data.indexOf('<html') == -1)			// Since error page will have <html> tag
					{    
						alert("Successfully deleted");
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
	function clearFields(id){$(id).val('');}
	
	function editTopic(name){
		
        var topicName = $(name).val().trim();
        var topicId=$("#hidetopicId").val();
		if(topicName == '')
		{
			alert("Please enter topic name");
		}
		
		else
			{if(topicName == '')
			{
				alert("Please enter topic name");
			}
			$.post("${pageContext.request.contextPath}/editTopic?name=" + topicName +"&id=" +topicId , function(data){
				$(".close").trigger('click');	
				
				getTopicData();
				alert('updated Sucessfully ');
				
				
				});
			
			
			}
		
		
		
		
	}
	
	
	
	
	
		
	
	
</script>

<div style="width:90%;margin:0px auto;">
	<div>
		<button class="btn btn-success " data-toggle="modal" data-target="#myModal1" onclick="clearFields('#topicName')" >Add New</button>
		<!--  <button class="btn btn-primary" data-toggle="modal" data-target="#myModal2" id="editTopicNameIds" style="display: none;"></button>  -->
		<button class="btn btn-danger" data-toggle="modal" data-target="#myModal3" onclick="removeTopic('#topicDataId')">Remove</button><br><br>
		
		
					<table id="dataTableId" class="display" style="width:100%">
						<thead>
							<tr class="dataTab">
								<th>Sr. No.</th>
								<th>Grade</th>
								<th>Subject</th>
								<th>Chapter</th>
								<th>Topic</th>
								<th> Action </th>
								
							<!-- 	<th>Serial Order</th> -->
							</tr>
						</thead>
						<tbody>
							<c:set var="count" value="1"></c:set>
							<c:forEach var="topic" items="${topicList}">
								<tr>
									<td><input class="tableCheckbox" type="checkbox" value="${topic.id }" name="topicId" /> ${count}</td>
									<td><span class="gradeCls"></span></td>
									<td><span class="subjectCls"></span></td>
									<td><span class="chapterCls"></span></td>
									<td>${topic.name}</td>
									<td><a href="javascript:void(0)"  class="editTopicCls" id="${topic.id }" dir="${topic.name}" data-toggle="modal" data-target="#myModal2">Edit</a></td>
									<%-- <td><input style="width:30px" type="text" maxlength="2" value="${topic.serialOrder}" /></td> --%>
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
					<h4 class="modal-title" id="myModalLabel">Topic Detail</h4>
				</div>
				<div class="modal-body">
					<div class="col-md-12 col-sm-12 col-xs-12">
						<div class="row">
							<input type="text" id="topicName" placeholder="Topic Name" required="required" maxlength="50" >
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button class="btn btn-primary" onclick = "saveTopic()">Add Topic</button>
				</div>
			</div>
		</div>
	</div>
</div>

<div class="modal fade" id="myModal2" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">Edit Detail</h4>
				</div>
				<div class="modal-body">
					<div class="col-md-12 col-sm-12 col-xs-12">
						<div class="row">
						<input type="hidden" id="hidetopicId" name="hide"/>
							<input type="text" id="topicNameId" placeholder="Topic Name" required="required" maxlength="50"  >
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button class="btn btn-primary" onclick = "editTopic('#topicNameId')">Edit Topic</button>
				</div>
			</div>
		</div>
	</div>
</div>
 