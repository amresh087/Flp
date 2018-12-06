<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script>
	$(document).ready(function()
	{
		$(".boardCls").html($("#boardId option:selected").text());
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
		var boardId = $('#boardId').val();
		var gradeId = $('#sectionId option:selected').attr("gradeid");
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
				url:"${pageContext.request.contextPath}/admin/addChapterTocStandard.htm",
				type:"POST",
				data: {"boardId": boardId, "gradeId": gradeId, "subjectId":subjectId, "chapterName": chapterName},
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
						$(".close").trigger('click');				// To hide the pop-up background
						alert("Chapter successfully created");
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
		$('#'+targetId).empty();
		
		var subjectIdMap = refObj.val();
		if(subjectIdMap != null || subjectIdMap != '')
		{
			$.post("${pageContext.request.contextPath}/ajax/rest/getChapters/"+$('#sectionIdSourceMap option:selected').attr('gradeid')+'/'+subjectIdMap,function(data) 
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
	
	function mapChapters()
	{
		var boardIdTarget = $('#boardId').val();
		var boardIdSource = $('#boardIdMap').val();
		var sectionIdTarget = $('#sectionId').val();
		var sectionIdSource = $('#sectionIdSourceMap').val();
		var subjectIdTarget = $('#subjectId').val();
		var subjectIdSource = $('#subjectIdMap').val();
		var gradeIdSource = $('#sectionIdSourceMap option:selected').attr("gradeid");
		var gradeIdTarget = $('#sectionId option:selected').attr("gradeid");
		var chapterIdString = $('.mappingCheckbox:checked').map(function() 
								{
								    return this.value;
								}).get();
		var chapterSerialOrderString = $('.mappingCheckbox:checked').map(function() 
										{
										    return this.alt;
										}).get().join(",");
		var chapterNameString = $('.mappingCheckbox:checked').map(function() 
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
		
		else if(chapterIdString == '')
		{
			alert("Please select chapter(s)");
		}
		else
		{
			$.ajax
			({
				url:"${pageContext.request.contextPath}/admin/mapChapterTocStandard.htm",
				type:"POST",
				data: {"boardIdTarget": boardIdTarget, "gradeIdTarget":gradeIdTarget, "subjectIdTarget":subjectIdTarget, "chapterIdString": chapterIdString.toString(), "chapterNameString": chapterNameString.toString(), "chapterSerialOrderString": chapterSerialOrderString},
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
		setDropdownList('#'+targetId, refObj.val(),'${pageContext.request.contextPath}/ajax/rest/getChapters/'+$('#sectionIdSourceMapExisted option:selected').attr('gradeid'), 'Select Chapter To Map From', false);
	}
	
	function mapChaptersExisted()
	{
		var boardIdTarget = $('#boardId').val();
		var gradeIdTarget = $('#sectionId option:selected').attr('gradeid');
		var subjectIdTarget = $('#subjectId').val();
		var chapterIdTargetMapExisted = $('#chapterIdTargetMapExisted').val();
		var boardIdSourceMapExisted = $('#boardIdSourceMapExisted').val();
		var gradeIdSource = $('#sectionIdSourceMapExisted option:selected').attr('gradeid');
		var subjectIdSourceMapExisted = $('#subjectIdSourceMapExisted').val();
		var chapterIdSourceMapExisted = $('#chapterIdSourceMapExisted').val();
		
		if(boardIdTarget == '')
			alert("Please select board to map to");
		else if(gradeIdTarget == undefined || gradeIdTarget == '')
			console.log("gradeIdTarget missing");
		else if(subjectIdTarget == '')
			alert("Please select subject to map to");
		else if(chapterIdTargetMapExisted == '')
			alert("Please select chapter to map to");
		else if(boardIdSourceMapExisted == '')
			alert("Please select board to map from");
		else if(gradeIdSource == undefined || gradeIdSource == '')
			console.log("gradeIdSource missing");
		else if(subjectIdSourceMapExisted == '')
			alert("Please select subject to map from");
		else if(chapterIdSourceMapExisted == '')
			alert("Please select chapter to map from");
		else
		{
			$.ajax
			({
				url:"${pageContext.request.contextPath}/admin/mapChapterTocStandardExisted.htm",
				type:"POST",
				data: {"chapterIdSource":chapterIdSourceMapExisted, "boardIdTarget":boardIdTarget, "gradeIdTarget":gradeIdTarget, "subjectIdTarget":subjectIdTarget, "chapterIdTarget":chapterIdTargetMapExisted},
				success:function(data)
				{
					if(data == 'noGradeIdTarget')
						console.log("gradeIdTarget missing");
					else if(data == 'noChapterIdSource')
						alert("Please select chapter to map from");
					else if(data == 'noBoardIdTarget')
						alert("Please select board to map to");
					else if(data == 'noChapterIdTarget')
						alert("Please select chapter to map to");
					else if(data == 'noSubjectIdTarget')
						alert("Please select subject to map to");
					else if(data == '')
						alert("Something went wrong");
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
				url:"${pageContext.request.contextPath}/admin/removeChapterTocStandard.htm",
				type:"POST",
				data: {"chapterIdString": checkedValues.toString()},
				success:function(data)
				{
					if(data == 'noChapter')
						alert("Please select chapter");
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
	
	function removeChapter(chapterId)
	{
		if(confirm("Are you sure to delete this chapter?") == false)
			return false;
		
		$.ajax
		({
			url:"${pageContext.request.contextPath}/admin/removeChapterTocStandard.htm",
			type:"POST",
			data: {"chapterIdString": chapterId.toString()},
			success:function(data)
			{
				if(data == 'noChapter')
					alert("Please select chapter");
				else if(data.indexOf('<html') == -1)			// Since error page will have <html> tag
				{
					alert("Successfully deleted");
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
			data: {"idArray": idArray, "serialOrderArray": serialOrderArray, "forWhat": 'chapter'},
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
	
	function editChapter()
	{
		var oldName=$('#txtOldChapter').val();
		var newName=$('#renameChapter').val();
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
			
			
			loadDataPage();
			
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
		
		
	
	
	function showEdit(id, topicName)
	{
		console.log("Show editdescription for: "+id+":"+topicName);
		$('#txtOldChapter').val(topicName);
		$('#chapterId').val(id);
	}
	
	
	
	 function enableDisableChapter(chapStatus, chapId, statusId){
		 chapStatus=parseInt(chapStatus); 
	    	if(chapStatus==1){
	    		if(confirm("Are you sure to Inactive this Chapter?") == false)
	    		    return false;
	    		
	    	 	
	    		$.post("${pageContext.request.contextPath}/ajax/rest/enableDisableChapter/"+chapId+"/"+2, function(data){
	    			if(data==true || data=='true'){
	    				alert("Chapter Disable successfully !!!");
	    			$("#enDischap"+statusId).html("Inactive");
	        		$("#enDischap"+statusId).attr("onclick","enableDisableChapter("+2+","+chapId+","+statusId+")");
	    			}
	    		}); 
	    		
	    	}else{
	    		if(confirm("Are you sure to Active this Chapter?") == false)
	    		    return false;
	    	
	 $.post("${pageContext.request.contextPath}/ajax/rest/enableDisableChapter/"+chapId+"/"+1, function(data){
		 alert("Chapter Enable successfully !!!");
			$("#enDischap"+statusId).html("Active");
			$("#enDischap"+statusId).attr("onclick","enableDisableChapter("+1+","+chapId+","+statusId+")");
	    		}); 
	    	}
	    } 
</script>

<div style="width:90%;margin:0px auto;">
	<div>
		<button class="btn btn-success " data-toggle="modal" data-target="#myModal1" >Add New</button>
		<a class="btn btn-primary" href="${pageContext.request.contextPath}/admin/getSubjectQuestions/${boardId}/${gradeId}/${subjectId}.htm?boardName=${boardName}&gradeName=${gradeName}&subjectName=${subjectName}">Download Questions</a>
		<!-- <button class="btn btn-primary" data-toggle="modal" data-target="#myModal2">Edit</button> -->
		<!-- <button class="btn btn-danger" data-toggle="modal" data-target="#myModal3" onclick="removeChapter()">Remove</button> -->
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
							<!-- 	<th>Status</th> -->
								<th>Action</th>
							</tr>
						</thead>
						<tbody>
							<c:set var="count" value="1"></c:set>
							<c:forEach var="chapter" items="${chapterList}" varStatus="status">
								<tr id="${chapter.id}">
									<td>${count}</td><!-- <input class="tableCheckbox" type="checkbox" value="${chapter.id}" name="chapterId"/>  -->
									<td><span class="boardCls"></span></td>
									<td><span class="gradeCls"></span></td>
									<td><span class="subjectCls"></span></td>
									<td>${chapter.name} </td>
								<%-- 	<td><a href="javascript:void(0)" onclick="enableDisableChapter(${chapter.status},${chapter.id},${status.count })" id="enDischap${status.count}"><c:choose><c:when test="${chapter.status eq 1 }">Active</c:when><c:otherwise>Inactive</c:otherwise></c:choose></a></td> --%>
									<td><a href="javascript:void(0)" onclick="removeChapter(${chapter.id})">Remove</a><a onclick="script:$('#txtOldChapter').val(chapterName); showEdit(${chapter.id},'${chapter.name}')"  data-toggle="modal" data-target="#myModal2"  href="javascript:void(0)" >/Edit</a> </td> <!--<input style="width:30px" type="text" maxlength="2" value="${chapter.serialOrder}" />  -->
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
							<input type="text" id="txtOldChapter" placeholder="Chapter Name" required="required" >
							<input type="text" id="renameChapter" placeholder="New Name" required="required" >
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
	
	
	<div class="modal fade" id="myModal4" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">Map Chapters</h4>
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
							<select id="subjectIdMap" style="width:250px;height:37px" onchange="getChapters($(this), 'generateChapterId')">
								<option value='' selected="selected" >Select Subject To Map From</option>
							</select>
						</div><br>
					</div>
				</div><br>
				<div id="generateChapterId" style="margin-top:60px;padding:15px"></div>
				<div class="modal-footer">
					<button class="btn btn-primary" onclick = "mapChapters()">Map</button>
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
							<select id="chapterIdTargetMapExisted" style="width:250px;height:37px">
								<option value='' selected="selected" >Select Chapter To Map To</option>
								<c:forEach var='chapter' items="${chapterList}">
									<option value="${chapter.id}">${chapter.name}</option>
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
							<select id="chapterIdSourceMapExisted" style="width:250px;height:37px">
								<option value='' selected="selected" >Select Chapter To Map From</option>
							</select>
						</div>
					</div>
				</div>
				
				<div class="modal-footer">
					<button class="btn btn-primary" onclick = "mapChaptersExisted()">Map</button>
				</div>
			</div>
		</div>
	</div>
</div>
