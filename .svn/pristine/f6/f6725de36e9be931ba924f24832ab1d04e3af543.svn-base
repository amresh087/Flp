<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script>
	$(document).ready(function()
	{
		generateDataTable();
		selectOnlyOneCheckbox();
		$(".boardCls").html($("#boardId option:selected").text());
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
	
	
	function saveSubject1()
	{
		
		var boardId=parseInt($('#boardId').val());
		var subjectId =parseInt($('#subjectDropDownId').val());
		 var	subjectName=''; 
		 
		 if(subjectId!="" && subjectId>0){
			 subjectName= $('#subjectDropDownId option:selected').text();
			
		 }else if(subjectId==-11 || subjectId=='-11'){
			 subjectName = $('#subjectName').val().trim();	 
			
		 }
		 
		
		 var val=$('#subjectDropDownId').val()
		var checkedValues = $('.sectionCheckbox:checked').map(function() 
						{
						    return this.value;
						}).get();
		 
		 if(checkedValues == '')
			{
				alert("Please select grade(s)");
				return false;
			}
	
		 if(val=='')
			 {
			 alert("Please select subject");
			 return false;
			 
			 }
		

		$("#form1").submit(function(e)
				{
		
		var formData = new FormData(this);
		//var formAction = $(this).attr("action");
		
	 	$.ajax
		({
			url:"${pageContext.request.contextPath}/admin/updateSubjectAndImage.htm?boardId="+boardId+"&sectionIdString="+checkedValues.toString()+"&subjectName="+subjectName+"&subjectId="+subjectId,
			type:"POST",
			data: formData,
			mimeType:"multipart/form-data",
				contentType: false,
				cache: false,
				processData:false,
			success:function(data)
			{
				if(data == 'noBoard')
					console.log("No board id");
				else if(data == 'noSection')
					alert("Please select grade");
				else if(data == 'noSubject')
					alert("Please type subject");
				else if(data == 'subjectNotAvailable')
					alert("Subject already exist");
				else if(data == 'mappingAlreadyExist')
					alert("Mapping(s) already exist");
				else if(data.indexOf('<html') == -1)		// Since error page will have <html> tag
				{
					$(".close").trigger('click');			// To hide the pop-up background
					alert("subject successfully created");
					loadDataPage();					// Refreshing page
				}
				
			},
			error:function()
			{
				alert("Something went wrong");
			}
		}); 
		e.preventDefault();				//Stop default action
		$(this).unbind("submit");	
		// Unbind to stop multiple form submit
	});
		
	}
	
	
	function saveSubject()
	{
		var boardId = $('#boardId').val();
		var subjectName = '';
		var subjectId = '';
		var checkedValues = $('.sectionCheckbox:checked').map(function() 
							{
							    return this.value;
							}).get();
		
		if($('#subjectName').val().trim() != '' && $('#subjectDropDownId').val() == '')
		{
			subjectName = $('#subjectName').val().trim();
		}
		if($('#subjectDropDownId').val() != '' && $('#subjectName').val().trim() == '')
		{
			subjectId = $('#subjectDropDownId').val();
			subjectName = $('#subjectDropDownId option:selected').text();
		}
		
		
		if(checkedValues == '')
		{
			alert("Please select grade(s)");
		}
		else if($('#subjectName').val().trim() == '' && $('#subjectDropDownId').val() == '')
		{
			alert("Please type name of subject or select a subject");
		}
		else if($('#subjectDropDownId').val() != '' && $('#subjectName').val().trim() != '')
		{
			alert("Please type either name of subject or select a subject");
		}
		else
		{
			$.ajax
			({
				url:"${pageContext.request.contextPath}/admin/addSubjectTocStandard.htm",
				type:"POST",
				data: {"boardId":boardId, "sectionIdString":checkedValues.toString(), "subjectName":subjectName, "subjectId":subjectId},
				success:function(data)
				{
					if(data == 'noBoard')
						alert("Please select board");
					else if(data == 'noSection')
						alert("Please select grade");
					else if(data == 'noSubject')
						alert("Please type subject");
					else if(data == 'subjectNotAvailable')
						alert("Subject already exist");
					else if(data == 'mappingAlreadyExist')
						alert("Mapping(s) already exist");
					else if(data.indexOf('<html') == -1)			// Since error page will have <html> tag
					{
						$(".close").trigger('click');				// To hide the pop-up background
						alert("Subject successfully created");
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
	
	/* function editSubject()
	{
		var refObj = $('.tableCheckbox:checked');
		var gradeId = refObj.parent().next().attr("class");
		var subjectId = refObj.parent().next().next().attr("class");
	} */
	
	function removeSubject()
	{
		var checkedValues = $('.tableCheckbox:checked').map(function() 
							{
							    return this.value;
							}).get();
		
		if(checkedValues == '')
		{
			alert("Please select a subject");
		}
		else
		{
			if(confirm("Are you sure to delete this subject?") == false)
				return false;
			
			$.ajax
			({
				url:"${pageContext.request.contextPath}/admin/removeSubjectTocStandard.htm",
				type:"POST",
				data: {"subjectSectionIdString": checkedValues.toString()},
				success:function(data)
				{
					if(data == 'noSubjectSection')
						alert("Please select subject");
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
	}
	
	function getSubjects(refObj, targetId)
	{
		$('#'+targetId).empty();
		
		var sectionIdSourceMap = refObj.val();
		if(sectionIdSourceMap != null || sectionIdSourceMap != '')
		{
			$.post("${pageContext.request.contextPath}/ajax/rest/getSubjects/"+sectionIdSourceMap,function(data) 
			{
				if (data != null && data.length > 0) 
				{
					for (var i = 0; i < data.length; i++) 
					{
						/* $(destinatoinId).append('<option value="'+data[i].id+'" gradeid="'+data[i].grade.id+'">'+ data[i].grade.name+ '</option>'); */
						$('#'+targetId).append("<p><input type='checkbox' class= 'mappingCheckbox' name='"+data[i].name+"' value='"+data[i].id+"'/>&nbsp;&nbsp;"+data[i].name+"&nbsp;&nbsp;</p>");
					}
				}
			}, "json");
		}
	}
	
	function mapSubjects()
	{
		var boardIdTarget = $('#boardId').val();
		var boardIdSource = $('#boardIdMap').val();
		var sectionIdTarget = $('#sectionIdTargetMap').val();
		var sectionIdSource = $('#sectionIdSourceMap').val();
		var gradeIdSource = $('#sectionIdSourceMap option:selected').attr("gradeid");
		var gradeIdTarget = $('#sectionIdTargetMap option:selected').attr("gradeid");
		var subjectIdString = $('.mappingCheckbox:checked').map(function() 
									{
									    return this.value;
									}).get();
		var subjectNameString = $('.mappingCheckbox:checked').map(function() 
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
		else if(boardIdSource == '')
		{
			alert("Please select board to map from");
		}		
		else if(sectionIdSource == '')
		{
			alert("Please select grade to map from");
		}
		else if (subjectIdString == '')
		{
			alert("Please select subjects");
		}	
		else if(gradeIdSource == undefined || gradeIdSource == '')
		{
			console.log("gradeIdSource missing");
		}
		else if (gradeIdTarget == undefined || gradeIdTarget == '')
		{
			console.log("gradeIdTarget missing");
		}
		else
		{
			$.ajax
			({
				url:"${pageContext.request.contextPath}/admin/mapSubjectTocStandard.htm",
				type:"POST",
				data: {"gradeIdSource": gradeIdSource, "subjectIdString": subjectIdString.toString(), "subjectNameString": subjectNameString.toString(), "boardIdTarget": boardIdTarget, "sectionIdTarget": sectionIdTarget, "gradeIdTarget": gradeIdTarget},
				success:function(data)
				{
					if(data == 'noBoardIdTarget')
						alert("Please select board to map to");
					else if(data == 'noSectionIdTarget')
						alert("Please select grade to map to");
					else if(data == 'noGradeIdTarget')
						console.log("gradeIdTarget missing");
					else if(data == 'noGradeIdSource')
						console.log("gradeIdSource missing");
					else if(data == "noSubjectIdString")
						alert("Please select subjects");
					else if(data == "noSubjectNameString")
						console.log('no subject names');
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
	
	
 	function setDropdownList123(){
		
		$("#subjectDropDownId").empty();
		$("#subjectName").empty();
		$("#subjectImageId").empty();
		
		 $("#newCreateSubject").hide();
			$("#orId").hide(); 

		$("subjectDropDownId").append('<option value=""> Select Subject </option>');
		$.ajax
		({
			url:"${pageContext.request.contextPath}/ajax/rest/getDistinctSubjects/1/1",
			type:"POST",
			success:function(data)
			{
				$("#subjectDropDownId").empty().append('<option value=""> Select Subject </option>');
				if(data != null && data.length > 0)
				{
					for (var i = 0; i < data.length; i++)
					{
						$("#subjectDropDownId").append('<option value="' + data[i].id + '">' + data[i].name + '</option>');
					}
					$("#subjectDropDownId").append('<option value="-11">Create New Subject </option>');
				}
				
			},
			error:function()
			{
				alert("Something went wrong");
			}
		});
		
	}
	 
	function mapSubjectsExisted()
	{
		var boardIdTarget = $('#boardId').val();
		var gradeIdTarget = $('#sectionIdTargetMapExisted option:selected').attr('gradeid');
		var subjectIdTarget = $('#subjectIdTargetMapExisted option:selected').val();
		var boardIdSourceMapExisted = $('#boardIdSourceMapExisted').val();
		var gradeIdSource = $('#sectionIdSourceMapExisted option:selected').attr('gradeid');
		var subjectIdSourceMapExisted = $('#subjectIdSourceMapExisted').val();
		
		if(boardIdTarget == '')
			alert("Please select board to map to");
		else if(subjectIdTarget == '')
			alert("Please select subject to map to");
		else if(gradeIdTarget == undefined || gradeIdTarget == '')
			console.log("gradeIdTarget missing");
		else if(boardIdSourceMapExisted == null)
			alert("Please select board to map from");
		else if(gradeIdSource == undefined || gradeIdSource == '')
			console.log("gradeIdSource missing");
		else if(subjectIdSourceMapExisted == null)
			alert("Please select subject to map from");
		else
		{
			$.ajax
			({
				url:"${pageContext.request.contextPath}/admin/mapSubjectTocStandardExisted.htm",
				type:"POST",
				data: {"gradeIdSource":gradeIdSource, "subjectIdSource":subjectIdSourceMapExisted, "boardIdTarget":boardIdTarget, "gradeIdTarget":gradeIdTarget, "subjectIdTarget":subjectIdTarget},
				success:function(data)
				{
					if(data == 'noGradeIdSource')
						console.log("gradeIdSource missing");
					else if(data == 'noSubjectIdSource')
						alert("Please select subject to map from");
					else if(data == 'noBoardIdTarget')
						alert("Please select board to map to");
					else if(data == 'noGradeIdTarget')
						console.log("gradeIdTarget missing");
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
	
	function editSubject()
	{
		var oldName=$('#txtOldSubject').val();
		var newName=$('#renameSubject').val();
		var id=$('#subjectId').val();
		
		console.log("Old Name:"+oldName+"New Name:"+newName+"id"+id);
		if(newName!='' && newName!=oldName)
		{
		
	$.ajax
	({
		url:"${pageContext.request.contextPath}/admin/updateSubject.htm",
		type:"POST",
		data: {id: id, oldName: oldName, newName: newName},
		success:function(data)
		{
			
			$('#editChapterClose').trigger("click");
			alert("Subject updated");
			
			
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
		
	
	function showEdit(id, subjectName)
	{
		console.log("Show editdescription for: "+id+":"+subjectName);
		$('#txtOldSubject').val(subjectName);
		$('#subjectId').val(id);
		$("#boardIds5").val($("#boardId").val());
	}
	
	
	
	function getValueOfSubject() {
		 
		 var val=parseInt($('#subjectDropDownId').val());
		 if(val!="" && val!=-11)
			 {
			 $("#newCreateSubject").hide();
				$("#orId").hide(); 
			 $("#subjectImageId").val();
			 $("#subjectName").val('');
			 }
		 else
			 {
	 			$("#newCreateSubject").show();
				$("#orId").show();
			 }
		 
		 
			
		 
	
		}
	
	
	 function updateSubjectIds(){
		 var oldName=$('#txtOldSubject').val();
			var newName=$('#txtSubject').val();
			
			if(newName!='' && newName!=null)
				{
				$("#updateSubjectFormId").submit();
				
				}else{
					alert("Please Provide Subject Name");
					return false;
				}
	 }
</script>

<div style="width:90%;margin:0px auto;">
	<button class="btn btn-success " data-toggle="modal" data-target="#myModal1" onclick="setDropdownList123()">Add New</button>	<!-- 1 = status -->
	<!-- <button class="btn btn-primary" data-toggle="modal" data-target="#myModal2">Edit</button> -->
	<button class="btn btn-danger" data-toggle="modal" data-target="#myModal3" onclick="removeSubject()">Remove</button>
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
							<th>Subject Image</th>
							<th>Action</th>
						</tr>
					</thead>
					<tbody>
						<c:set var="count" value="1"></c:set>
						<c:forEach var="subjectSection" items="${subjectSectionList}">
							<tr>
								<td><input class="tableCheckbox" type="checkbox" value="${subjectSection.id}" name="subjectSectionId"/> ${count}</td>
								<td><span class="boardCls"></span></td>
								<td class="${subjectSection.section.id}">${subjectSection.section.grade.name}</td>
								<td class="${subjectSection.subject.id}">${subjectSection.subject.name}</td>
								
								<td><c:choose>
											<c:when test="${subjectSection.subject.subImage != null}">
												<img
													src="${commonPath}/subjectImage/${subjectSection.subject.subImage}"
													class="img-responsive" title="Futuristic Learning"
													style="width: 50px;" />

												<%-- ${pageContext.request.contextPath}/resources/images${subjectSection.subject.subImage} --%>


											</c:when>
											<c:otherwise>
											N/A
																						</c:otherwise>
										</c:choose></td>
								<td><a onclick="script:$('#txtOldSubject').val(subjectName); showEdit(${subjectSection.subject.id},'${subjectSection.subject.name}')"  data-toggle="modal" data-target="#myModal2"  href="javascript:void(0)" >Edit</a> </td>
							</tr>
							<c:set var="count" value="${count+1}"></c:set>
						</c:forEach>
					</tbody>
				</table>
			</td>
		</tr>
	</table>
	
	
	<div class="modal fade" id="myModal1" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
		<form  enctype="multipart/form-data" id="form1" method = "post" onsubmit="return false;">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">Subject Detail</h4>
				</div>
				<div class="modal-body">
					<div class="col-md-12 col-sm-12 col-xs-12">
						<div class="row">
							<c:forEach var="section" items="${sectionList}">
								<p><input class="sectionCheckbox" type="checkbox" value="${section.id}" />&nbsp;&nbsp;${section.grade.name}&nbsp;&nbsp;</p>
							</c:forEach>
						</div>
					</div>
				</div>
				<div class="modal-body">
					<div class="col-md-12 col-sm-12 col-xs-12">
						<div class="row" id="newCreateSubject" style="display: none;">
								<input type="text" id="subjectName" placeholder="Subject Name" >
								Subject Image <input type="file" name="subjectImage" placeholder="Subject Image" id="subjectImageId"/>
							</div>
					</div>
				</div>
				<div class="modal-body">
					<div class="col-md-12 col-sm-12 col-xs-12">
						<div class="row" id="orId" style="display: none;">
							OR
						</div>
					</div>
				</div>
				<div class="modal-body">
					<div class="col-md-12 col-sm-12 col-xs-12">
						<div class="row">
							<select id="subjectDropDownId" onchange="getValueOfSubject()">
								<option value="">Select Subject</option>
							</select>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button class="btn btn-primary" onclick = "saveSubject1()">Add Subject</button>
				</div>
			</div>
		</div>
		</form>
	</div>
	
	<div class="modal fade" id="myModal2" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close" id="editSubjectClose">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">Edit Subject Name</h4>
				</div>
				<form  enctype="multipart/form-data" id="updateSubjectFormId" method = "post" action="${pageContext.request.contextPath}/admin/updateSubject.htm">
				<div class="modal-body">
					<div class="col-md-12 col-sm-12 col-xs-12">
						<div class="row">
							<input type="text" id="txtOldSubject" placeholder="Subject Name" required="required" name="txtOldSubject" >
							<input type="text" id="txtSubject" placeholder="New Name" required="required" name="txtNewSubject" />
							<input type="hidden"  id="subjectId" name="txtSubjectId" />
							<input type="hidden"  id="type" name="type" value="nonCtls"/>
							<input type="hidden"   name="boardId" id="boardIds5"/>
							Subject Image <input type="file" name="subjectImageNew" placeholder="Subject Image" id="updateSubjectImageId"/>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<!-- <button class="btn btn-primary" onclick = "editSubject()">Update</button> -->
						<button type="button" class="btn btn-primary" onclick="updateSubjectIds();">Upload</button>
				</div>
				</form>
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
					<h4 class="modal-title" id="myModalLabel">Map Subjects</h4>
				</div>
				<div class="modal-body">
					<div class="col-md-12 col-sm-12 col-xs-12">
						<div class="row">
							<select id="sectionIdTargetMap" style="width:250px;height:37px">
								<option value='' selected="selected" >Select Grade To Map To</option>
								<c:forEach var="section" items="${sectionList}">
									<option value="${section.id}" gradeid="${section.grade.id}">${section.grade.name}</option>
								</c:forEach>
							</select>
						</div><br>
						<div class="row">
							<select id="boardIdMap" style="width:250px;height:37px" onchange="setSectionDropdownList('#sectionIdSourceMap', $(this).val(), '${pageContext.request.contextPath}/ajax/rest/getSectionsForBoard', 'Select Grade To Map From')">
								<option value='' selected="selected" >Select Board To Map From</option>
								<c:forEach var='board' items="${boardListMap}">
									<option value="${board.id}">${board.name}</option>
								</c:forEach>
							</select>
						</div>
						<div class="row">
							<select id="sectionIdSourceMap" style="width:250px;height:37px" onchange="getSubjects($(this), 'generateSubjectId')">
								<option value='' selected="selected" >Select Grade To Map From</option>
							</select>
						</div>
					</div>
				</div><br><br>
				<div id="generateSubjectId" style="margin-top:60px;padding:15px"></div>
				<div class="modal-footer">
					<button class="btn btn-primary" onclick = "mapSubjects()">Map</button>
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
					<h4 class="modal-title" id="myModalLabel">Map Existing Subject</h4>
				</div>
				<div class="modal-body">
					<div class="col-md-12 col-sm-12 col-xs-12">
						<div class="row">
							<select id="sectionIdTargetMapExisted" style="width:250px;height:37px" onchange="setDropdownList('#subjectIdTargetMapExisted', $(this).val(), '${pageContext.request.contextPath}/ajax/rest/getSubjects', 'Select Subject To Map To')">
								<option value='' selected="selected" >Select Grade To Map To</option>
								<c:forEach var='section' items="${sectionList}">
									<option value="${section.id}" gradeid="${section.grade.id}">${section.grade.name}</option>
								</c:forEach>
							</select>
						</div>
					</div>
				</div><br>
				<div class="modal-body">
					<div class="col-md-12 col-sm-12 col-xs-12">
						<div class="row">
							<select id="subjectIdTargetMapExisted" style="width:250px;height:37px">
								<option value='' selected="selected" >Select Subject To Map To</option>
								<%-- <c:forEach var='subjectSection' items="${subjectSectionList}">
									<option value="${subjectSection.id}" subjectid="${subjectSection.subjectId}" gradeid="${subjectSection.section.gradeId}">${subjectSection.subject.name}</option>
								</c:forEach> --%>
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
							<select id="subjectIdSourceMapExisted" style="width:250px;height:37px">
								<option value='' selected="selected" >Select Subject To Map From</option>
							</select>
						</div>
					</div>
				</div>
				
				<div class="modal-footer">
					<button class="btn btn-primary" onclick = "mapSubjectsExisted()">Map</button>
				</div>
			</div>
		</div>
	</div>
</div>