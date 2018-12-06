<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script>
	$(document).ready(function()
	{
		$(".boardCls").html($("#boardId option:selected").text());
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
		var boardId = $('#boardId').val();
		var gradeId = $('#sectionId option:selected').attr("gradeid");
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
				url:"${pageContext.request.contextPath}/admin/addTopicTocStandard.htm",
				type:"POST",
				data: {"boardId": boardId, "gradeId": gradeId, "subjectId":subjectId, "chapterId": chapterId, "topicName": topicName},
				success:function(data)
				{
					if(data == 'noBoard')
						alert("Please select board");
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
						$(".close").trigger('click');				// To hide the pop-up background
						alert("successfully created");
						loadDataPage();
					}
				},
				error:function()
				{
					alert("Something went wrong");
				}
			});
		}
	}
	
	function getChapters(refObj, targetId)
	{
		setDropdownList('#'+targetId, refObj.val(), '${pageContext.request.contextPath}/ajax/rest/getChapters/'+$('#sectionIdSourceMap option:selected').attr('gradeid'), 'Select Chapter To Map From', false);	
	}
	
	function getTopics(refObj, targetId)
	{
		$('#'+targetId).empty();
		
		var chapterIdMap = refObj.val();
		if(chapterIdMap != null || chapterIdMap != '')
		{
			$.post("${pageContext.request.contextPath}/ajax/rest/getTopics/"+chapterIdMap,function(data) 
			{
				if (data != null && data.length > 0) 
				{
					for (var i = 0; i < data.length; i++) 
					{
						/* $(destinatoinId).append('<option value="'+data[i].id+'" gradeid="'+data[i].grade.id+'">'+ data[i].grade.name+ '</option>'); */
						$('#'+targetId).append("<p><input type='checkbox' class= 'mappingCheckbox' name='"+data[i].name+"' value='"+data[i].id+"' alt='"+data[i].serialOrder+"'/>&nbsp;&nbsp;"+data[i].name+"&nbsp;&nbsp;</p>");
					}
				}
			}, "json");
		}
	}
	
	function mapTopics()
	{
		var boardIdTarget = $('#boardId').val();
		var boardIdSource = $('#boardIdMap').val();
		var sectionIdTarget = $('#sectionId').val();
		var sectionIdSource = $('#sectionIdSourceMap').val();
		var subjectIdTarget = $('#subjectId').val();
		var subjectIdSource = $('#subjectIdMap').val();
		var chapterIdTarget = $('#chapterId').val();
		var chapterIdSource = $('#chapterIdMap').val();
		var gradeIdTarget = $('#sectionId option:selected').attr("gradeid");
		var topicIdString = $('.mappingCheckbox:checked').map(function() 
								{
								    return this.value;
								}).get();
		var topicSerialOrderString = $('.mappingCheckbox:checked').map(function() 
									{
									    return this.alt;
									}).get();
		var topicNameString = $('.mappingCheckbox:checked').map(function() 
								{
								    return this.name;
								}).get().join("###");
		
		if(boardIdTarget == '')
		{
			alert("Please select a board to map to");
		}
		else if(sectionIdTarget == '')
		{
			alert("Please select grade to map to");
		}
		else if(subjectIdTarget == '')
		{
			alert("Please select subject to map to");
		}
		else if(chapterIdTarget == '')
		{
			alert("Please select chapter to map to");
		}
		
		else if(boardIdSource == '')
		{
			alert("Please select board to map from");
		}
		else if(sectionIdSource == '')
		{
			alert("Please select grade to map from");
		}
		else if(subjectIdSource == '')
		{
			alert("Please select subject to map from");
		}	
		else if(chapterIdSource == '')
		{
			alert("Please select chapter to map from");
		}
		
		else if(topicIdString == '')
		{
			alert("Please select topic(s)");
		}
		else if(gradeIdTarget == undefined || gradeIdTarget == '')
		{
			console.log("gradeIdTarget is missing");
		}
		else
		{
			$.ajax
			({
				url:"${pageContext.request.contextPath}/admin/mapTopicTocStandard.htm",
				type:"POST",
				data: {"boardIdTarget": boardIdTarget, "gradeIdTarget":gradeIdTarget, "subjectIdTarget":subjectIdTarget, "chapterIdTarget": chapterIdTarget, "topicIdString": topicIdString.toString(), "topicNameString": topicNameString.toString(), "topicSerialOrderString": topicSerialOrderString.toString()},
				success:function(data)
				{
					if(data == 'noBoardIdTarget')
						alert("Please select board to map to");
					else if(data == 'noGradeIdTarget')
						console.log("no grade ids");
					else if(data == "noSubjectIdTarget")
						alert("Please select subject to map to");
					else if(data == "noChapterIdString")
						alert("Please select chapter(s)");
					else if(data == "noChapterNameString")
						console.log('no chapter names');
					else if(data.indexOf('<html') == -1)			// Since error page will have <html> tag
					{
						$(".close").trigger('click');				// To hide the pop-up background
						//alert("successfully mapped : "+data);
						alert("successfully mapped");
						loadDataPage();
					}
				},
				error:function()
				{
					alert("Something went wrong");
				}
			});
		}
	}
	
	function getChaptersExisted(refObj, targetId)
	{
		setDropdownList('#'+targetId, refObj.val(), '${pageContext.request.contextPath}/ajax/rest/getChapters/'+$('#sectionIdSourceMapExisted option:selected').attr('gradeid'), 'Select Chapter To Map From', false);
	}
	
	function mapTopicsExisted()
	{
		var boardIdTarget = $('#boardId').val();
		var gradeIdTarget = $('#sectionId option:selected').attr('gradeid');
		var subjectIdTarget = $('#subjectId').val();
		var chapterIdTarget = $('#chapterId').val();
		var topicIdTargetMapExisted = $('#topicIdTargetMapExisted').val();
		var boardIdSourceMapExisted = $('#boardIdSourceMapExisted').val();
		var gradeIdSource = $('#sectionIdSourceMapExisted option:selected').attr('gradeid');
		var subjectIdSourceMapExisted = $('#subjectIdSourceMapExisted').val();
		var chapterIdSourceMapExisted = $('#chapterIdSourceMapExisted').val();
		var topicIdSourceMapExisted = $('#topicIdSourceMapExisted').val();
		
		if(boardIdTarget == '')
			alert("Please select board to map to");
		else if(gradeIdTarget == undefined || gradeIdTarget == '')
			console.log("gradeIdTarget missing");
		else if(subjectIdTarget == '')
			alert("Please select subject to map to");
		else if(chapterIdTarget == '')
			alert("Please select chapter to map to");
		else if(topicIdTargetMapExisted == '')
			alert("Please select topic to map to");
		else if(boardIdSourceMapExisted == '')
			alert("Please select board to map from");
		else if(gradeIdSource == undefined || gradeIdSource == '')
			console.log("gradeIdSource missing");
		else if(subjectIdSourceMapExisted == '')
			alert("Please select subject to map from");
		else if(chapterIdSourceMapExisted == '')
			alert("Please select chapter to map from");
		else if(topicIdSourceMapExisted == '')
			alert("Please select topic to map from");
		else
		{
			$.ajax
			({
				url:"${pageContext.request.contextPath}/admin/mapTopicTocStandardExisted.htm",
				type:"POST",
				data: {"topicIdSource":topicIdSourceMapExisted, "boardIdTarget":boardIdTarget, "gradeIdTarget":gradeIdTarget, "subjectIdTarget":subjectIdTarget, "chapterIdTarget":chapterIdTarget, "topicIdTarget":topicIdTargetMapExisted},
				success:function(data)
				{
					if(data == 'noTopicIdSource')
						alert("Please select topic to map from");
					else if(data == 'noGradeIdTarget')
						console.log("gradeIdTarget missing");
					else if(data == 'noBoardIdTarget')
						alert("Please select board to map to");
					else if(data == 'noChapterIdTarget')
						alert("Please select chapter to map to");
					else if(data == 'noSubjectIdTarget')
						alert("Please select subject to map to");
					else if(data == '')
						alert("Something went wrong");
					else if(data=='success')
						{
						$(".close").trigger('click');			
						alert("successfully mapped");
						loadDataPage();
						}
					else if(data != '' && data.indexOf('<html') == -1)			// Since error page will have <html> tag
					{
						$(".close").trigger('click');				// To hide the pop-up background
						//alert("successfully mapped : "+data);
						alert("successfully mapped");
						loadDataPage();
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
				url:"${pageContext.request.contextPath}/admin/removeTopicTocStandard.htm",
				type:"POST",
				data: {"topicIdString": checkedValues.toString()},
				success:function(data)
				{
					if(data == 'noTopic')
						alert("Please select topic");
					else if(data.indexOf('<html') == -1)			// Since error page will have <html> tag
					{
						alert("successfully deleted");
						loadDataPage();
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
			url:"${pageContext.request.contextPath}/admin/removeTopicTocStandard.htm",
			type:"POST",
			data: {"topicIdString": topicId.toString()},
			success:function(data)
			{
				if(data == 'noTopic')
					alert("Please select topic");
				else if(data.indexOf('<html') == -1)			// Since error page will have <html> tag
				{
					alert("successfully deleted");
					loadDataPage();
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
					loadDataPage();
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
			
			loadDataPage();
			
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
		<button style="float:right;margin-left:5px" class="btn btn-success" onclick="saveOrder()">Save Order</button>
		<button class="btn btn-success" data-toggle="modal" data-target="#myModal4" style="float:right">Mapping</button>
		<button class="btn btn-success" data-toggle="modal" data-target="#myModal5" style="float:right;margin-right:5px">Map Existing</button><br><br>
		
		<table style="border: 3px;background: rgb(243, 244, 248);width:100%">		<!-- Use it because it's helpful -->
			<tr style="width:100%;height:100%">
				<td>
					<table id="dataTableId" class="display" style="width:100%">
						<thead>
							<tr class="dataTab">
								<th>Sr. No.</th>
								<th>Board</th>
								<th>Grade</th>
								<th>Subject</th>
								<th>Chapter</th>
								<th>Topic</th>
								<!-- <th>Status</th> -->
								<th>Action</th>
							</tr>
						</thead>
						<tbody>
							<c:set var="count" value="1"></c:set>
							<c:forEach var="topic" items="${topicList}" varStatus="status">
								<tr id="${topic.id}">
									<td>${count}</td> <!-- <input class="tableCheckbox" type="checkbox" value="${topic.id }" name="topicId"/>  -->
									<td><span class="boardCls"></span></td>
									<td><span class="gradeCls"></span></td>
									<td><span class="subjectCls"></span></td>
									<td><span class="chapterCls"></span></td>
									<td>${topic.name}</td>
									<%-- <td><a href="javascript:void(0)" onclick="enableDisableTopic(${topic.status},${topic.id},${status.count })" id="enDisTopic${status.count}"><c:choose><c:when test="${topic.status eq 1 }">Active</c:when><c:otherwise>Inactive</c:otherwise></c:choose></a></td> --%>
									<td> <a href="javascript:void(0)" onclick="removeTopic(${topic.id})">Remove</a><a onclick="script:$('#txtOldTopic').val(topicName); showEdit(${topic.id},'${topic.name}')"  data-toggle="modal" data-target="#myModal2"  href="javascript:void(0)" >/Edit</a> </td><!-- <input style="width:30px" type="text" maxlength="2" value="${topic.serialOrder}" /> -->
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
	
	<div class="modal fade" id="myModal4" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">Map Topics</h4>
				</div>
				<div class="modal-body">
					<div class="col-md-12 col-sm-12 col-xs-12">
						<div class="row">
							<select id="boardIdMap" style="width:250px;height:37px" onchange="setSectionDropdownList('#sectionIdSourceMap', $(this).val(), '${pageContext.request.contextPath}/ajax/rest/getSectionsForBoard', 'Select Grade To Map From')">
								<option value='' selected="selected" >Select Board To Map From</option>
								<c:forEach var='board' items="${boardListMap}">
									<option value="${board.id}">${board.name}</option>
								</c:forEach>
							</select>
						</div>
						<div class="row">
							<select id="sectionIdSourceMap" style="width:250px;height:37px" onchange="setDropdownList('#subjectIdMap', $(this).val(), '${pageContext.request.contextPath}/ajax/rest/getSubjects', 'Select Subject To Map From', false)">
								<option value='' selected="selected" >Select Grade To Map From</option>
							</select>
						</div>
						<div class="row">
							<select id="subjectIdMap" style="width:250px;height:37px" onchange="getChapters($(this), 'chapterIdMap')">
								<option value='' selected="selected" >Select Subject To Map From</option>
							</select>
						</div>
						<div class="row">
							<select id="chapterIdMap" style="width:250px;height:37px" onchange="getTopics($(this), 'generateTopicId')">
								<option value='' selected="selected" >Select Chapter To Map From</option>
							</select>
						</div><br>
					</div>
				</div><br>
				<div id="generateTopicId" style="margin-top:60px;padding:15px"></div>
				<div class="modal-footer">
					<button class="btn btn-primary" onclick = "mapTopics()">Map</button>
				</div>
			</div>
		</div>
	</div>
	
	<div class="modal fade" id="myModal5" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">Map Grades</h4>
				</div>
				<div class="modal-body">
					<div class="col-md-12 col-sm-12 col-xs-12">
						<div class="row">
							<select id="topicIdTargetMapExisted" style="width:250px;height:37px">
								<option value='' selected="selected" >Select Topic To Map To</option>
								<c:forEach var='topic' items="${topicList}">
									<option value="${topic.id}">${topic.name}</option>
								</c:forEach>
							</select>
						</div>
					</div>
				</div><br>
				<div class="modal-body">
					<div class="col-md-12 col-sm-12 col-xs-12">
						<div class="row">
							<select id="boardIdSourceMapExisted" style="width:250px;height:37px" onchange="setSectionDropdownList('#sectionIdSourceMapExisted', $(this).val(), '${pageContext.request.contextPath}/ajax/rest/getSectionsForBoard', 'Select Grade To Map From')">
								<option value='' selected="selected" >Select Board To Map From</option>
								<c:forEach var='board' items="${boardListMap}">
									<option value="${board.id}">${board.name}</option>
								</c:forEach>
							</select>
						</div>
					</div>
				</div>
				
				<div class="modal-body">
					<div class="col-md-12 col-sm-12 col-xs-12">
						<div class="row">
							<select id="sectionIdSourceMapExisted" style="width:250px;height:37px" onchange="setDropdownList('#subjectIdSourceMapExisted', $(this).val(), '${pageContext.request.contextPath}/ajax/rest/getSubjects', 'Select Subject To Map From', false)">
								<option value='' selected="selected" >Select Grade To Map From</option>
							</select>
						</div>
					</div>
				</div>
				
				<div class="modal-body">
					<div class="col-md-12 col-sm-12 col-xs-12">
						<div class="row">
							<select id="subjectIdSourceMapExisted" style="width:250px;height:37px" onchange="getChaptersExisted($(this), 'chapterIdSourceMapExisted')">
								<option value='' selected="selected" >Select Subject To Map From</option>
							</select>
						</div>
					</div>
				</div>
				
				<div class="modal-body">
					<div class="col-md-12 col-sm-12 col-xs-12">
						<div class="row">
							<select id="chapterIdSourceMapExisted" style="width:250px;height:37px" onchange="setDropdownList('#topicIdSourceMapExisted', $(this).val(), '${pageContext.request.contextPath}/ajax/rest/getTopics', 'Select Topic To Map From', false)">
								<option value='' selected="selected" >Select Chapter To Map From</option>
							</select>
						</div>
					</div>
				</div>
				
				<div class="modal-body">
					<div class="col-md-12 col-sm-12 col-xs-12">
						<div class="row">
							<select id="topicIdSourceMapExisted" style="width:250px;height:37px">
								<option value='' selected="selected" >Select Topic To Map From</option>
							</select>
						</div>
					</div>
				</div>
				
				<div class="modal-footer">
					<button class="btn btn-primary" onclick = "mapTopicsExisted()">Map</button>
				</div>
			</div>
		</div>
	</div>
</div>
