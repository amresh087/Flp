<html>
<head>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:include page="../globalPage.jsp" />
<!-- All js, css, data-table api are defined in globalPage.js -->

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script>
			$(document).ready(function()
			{
				generateDataTable();
				selectOnlyOneCheckbox();
				$("#newCreateSubject").hide();
				$("#orId").hide();
				
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
			
			function saveSubject()
			{
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
				/* 	$.ajax
					({
						url:"${pageContext.request.contextPath}/admin/addSubjectToc.htm",
						type:"POST",
						//mimeType:"multipart/form-data",
						dataType:"JSON",
						data: {"boardId": 1, "sectionIdString":checkedValues.toString(), "subjectName":subjectName, "subjectId":subjectId},
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
								location.reload();						// Refreshing page
							}
						},
						error:function()
						{
							alert("Something went wrong");
						}
					}); */
					
					
				}
			}
			
			/* function editSubject()
			{
				var refObj = $('.tableCheckbox:checked');
				var gradeId = refObj.parent().next().attr("class");
				var subjectId = refObj.parent().next().next().attr("class");
			} */
			
			function removeSubject(subjectId)
			{
				/* var checkedValues = $('.tableCheckbox:checked').map(function() 
									{
									    return this.value;
									}).get();
				
				if(checkedValues == '')
				{
					alert("Please select a subject");
				}
				else
				{ */
					if(confirm("Are you sure to delete this subject?") == false)
						return false;
					
					$.ajax
					({
						url:"${pageContext.request.contextPath}/admin/removeSubjectToc.htm",
						type:"POST",
						data: {"subjectSectionIdString": subjectId},
						success:function(data)
						{
							if(data == 'noSubjectSection')
								alert("Please select subject");
							else if(data.indexOf('<html') == -1)			// Since error page will have <html> tag
							{
								alert("successfully deleted");
								location.reload();						// Refreshing page
							}
						},
						error:function()
						{
							alert("Something went wrong");
						}
					});
				//}
			}
			
			function resetForm()
			{
				$('#excelFile').val('');
				$('#excelResult').empty();
			}
			
			function uploadExcel()
			{
				$('#excelResult').empty();
				var messageArray; 
				
				if($('#excelFile').val() == null)
				{
					alert("Please select excel file to upload");
					return false;
				}
					
				$("#formId").submit(function(e)
				{
					$('#fullPageloader').show();
					var formData = new FormData(this);
					var formAction = $(this).attr("action");
			
					$.ajax
					({
						url:formAction,
						type:"POST",
						data: formData,
						mimeType:"multipart/form-data",
		   				contentType: false,
		   				cache: false,
		   				processData:false,
						success:function(data)
						{
							$('#fullPageloader').hide();
							if(data == "noFile")
							{
								$('#excelResult').html("Please select file");
							}	
							else if(data == "noData")
							{
								$('#excelResult').html("No data found in file");
							}
							else if(data == "wrongExtension")
							{
								$('#excelResult').html("Please select excel file only");
							}
							else if(data == "wrongFormat")
							{
								$('#excelResult').html("Please select correct format excel only");
							}
							else if(data.indexOf(",") > 0 && data.indexOf('<html') == -1)	// Since error page will have <html> tag
							{
								messageArray = data.split(",");
								$('#excelResult').html("Blank cell found in sheet "+messageArray[0]+" at row no. "+messageArray[1]);
							}
							else if(data == "success")
							{
								alert("Successfully uploaded");
								$(".close").trigger("click");
								/* getChapterData(); */
								location.reload();	
							}
						},
						error:function()
						{
							
						}
					});
					e.preventDefault();				//Stop default action
					$(this).unbind("submit");		// Unbind to stop multiple form submit
				});
				$("#formId").submit();		// Submit the form.				
			}
			
			function showEdit(id, subjectName)
			{
				console.log("Show editdescription for: "+id+":"+subjectName);
				$('#txtOldSubject').val(subjectName);
				$('#txtSubjectId').val(id);
			}
			
			function updateSubject()
			{
				var oldName=$('#txtOldSubject').val();
				var newName=$('#txtSubject').val();
				var id=$('#txtSubjectId').val();
				
				alert("ji");
				if(newName!='' && newName!=oldName)
					{
					alert("po");
					$("#updateSubjectFormId").submit(function(e)
							{
					
					//var formData = new FormData(this);	
					alert("hi");
				$.ajax
				({
					url:"${pageContext.request.contextPath}/admin/updateSubject.htm",
					type:"POST",
					data: {id: id, oldName: oldName, newName: newName,formData:formData},
					mimeType:"multipart/form-data",
	   				contentType: false,
	   				cache: false,
	   				processData:false,
					success:function(data)
					{
						
						$('#edtSubjectClose').trigger("click");
						alert("Subject updated");
						
						location.reload();
						
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
				else
				alert("Please provide subject name");
			}
			
			$("#form1").submit(function(){
				alert("dfdfds");
				console.log("joiofdofd");
				
			});
			
			
			$("#addNewSubjectId").on("click", function()
					{       alert("hiii");
						    validateForm();
							
				    });
			
			function validateForm()
			{
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
				
				var subjctImage=$("#subjectImageId").val();
		    	if(subjctImage==null || subjctImage=="")
		    	{
		    		alert("Please Select Subject Image !!");
		    	}
		    	else
		    	{
		    		var val=subjctImage.split(".");
		    		if(val[val.length-1]!="jpg" && val[val.length-1]!="jpeg" && val[val.length-1]!="png" )
		    		{
		    			alert("Please browse png or jpg file!");
		    		}
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
					var formData = new FormData(this);
					var formAction = $(this).attr("action");
					alert("formdata and aciton"+formData+formAction);
					
					$.ajax
					({
						url:"formAction",
						type:"POST",
						mimeType:"multipart/form-data",
						data:"formData",
						//data: {"boardId": 1, "sectionIdString":checkedValues.toString(), "subjectName":subjectName, "subjectId":subjectId},
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
								location.reload();						// Refreshing page
							}
						},
						error:function()
						{
							alert("Something went wrong");
						}
					});
				}
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
							location.reload();						// Refreshing page
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
			
			function setDropdownList(){
				$("#subjectDropDownId").empty();
				$("#subjectName").empty();
				$("#subjectImageId").empty();
				
				 $("#newCreateSubject").hide();
					$("#orId").hide(); 

				
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

</head>
<body>
	<h2 class="text-center">Subject Details</h2>

	<div style="width: 90%; margin: 0px auto;">
		<button class="btn btn-success " data-toggle="modal"
			data-target="#myModal1" onclick="setDropdownList()">Add New</button>
		<!-- 1 = boardId (CTLS), 1 = status -->
		<!-- <button class="btn btn-primary" data-toggle="modal" data-target="#myModal2">Edit</button> -->
		<!-- <button class="btn btn-danger" data-toggle="modal" data-target="#myModal3" onclick="removeSubject()">Remove</button> -->
		<button type="button" class="btn btn-warning" data-toggle="modal"
			data-target="#uploadExcel" id="importExcel" onclick="resetForm()">Import
			From Excel</button>
		<button style="float: right" type="button" class="btn btn-warning"
			data-toggle="modal" data-target="#excelFormat" id="exportExcel">Download
			Excel Format</button>
		<br>
		<br>

		<table
			style="border: 3px; background: rgb(243, 244, 248); width: 100%">
			<!-- Use it because it's helpful -->
			<tr style="width: 100%; height: 100%">
				<td>
					<table id="dataTableId" class="display" style="width: 100%">
						<thead>
							<tr class="dataTab">
								<th>Sr. No.</th>
								<th>Grade</th>
								<th>Subject</th>
								<th>Subject Image</th>
								<th>Edit Subject</th>
								<th>Remove Subject</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="subjectSection" items="${subjectSectionList}"
								varStatus="status">
								<tr>
									<%-- <td><input class="tableCheckbox" type="checkbox" value="${subjectSection.id}" name="subjectSectionId"/> ${status.count}</td> --%>
									<td align="right">${status.count}</td>
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
									<td><button
											onclick="script:$('#txtOldSubject').val(subjectName); showEdit(${subjectSection.subject.id},'${subjectSection.subject.name}')"
											data-target="#editSubject" data-toggle="modal">Edit</button></td>


									<td><button class="btn btn-danger"
											onclick="removeSubject('${subjectSection.id}')">Remove</button></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</td>
			</tr>
		</table>
	</div>

	<!-- excelModal -->
	<div class="modal fade" id="uploadExcel" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">Upload from Excel</h4>
				</div>

				<div class="clearfix"></div>
				<div class="modal-body" style="border-bottom: none !important">
					<form
						action="${pageContext.request.contextPath}/admin/createChapterAndTopics.htm"
						enctype="multipart/form-data" id="formId">
						<input type="file" name="excelFile" placeholder="Browse File"
							id="excelFile" /> <input type="hidden" name="boardId" value="1" />
						<!-- For CTLS board -->
					</form>
					<button type="button" class="btn btn-warning"
						onclick="uploadExcel()">Upload</button>
					<div id="excelResult" style="text-align: center; color: red"></div>
				</div>
			</div>
		</div>
	</div>
	<!-- excelModal -->


	<!--  Start edit subject-->
	<div class="modal fade" id="editSubject" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">



		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close" id="edtSubjectClose">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">Edit Subject Name</h4>
				</div>

				<div class="clearfix"></div>
				<div class="modal-body" style="border-bottom: none !important">
					<!-- 	<form action="" enctype="multipart/form-data" id="formId"> -->
					<form enctype="multipart/form-data" id="updateSubjectFormId"
						method="post"
						action="${pageContext.request.contextPath}/admin/updateSubject.htm">
						<input type="text" name="txtOldSubject" id="txtOldSubject"
							readonly="readonly" /> <input type="text" id="txtSubject"
							maxlength="50" name="txtNewSubject" /> <input type="hidden"
							name="txtSubjectId" id="txtSubjectId" /> Subject Image <input
							type="file" name="subjectImageNew" placeholder="Subject Image"
							id="updateSubjectImageId" /> <input type="hidden" id="type"
							name="type" value="ctls" />

						<button type="button" class="btn btn-warning"
							onclick="updateSubjectIds();">Upload</button>
						<div id="excelResult" style="text-align: center; color: red"></div>
					</form>
				</div>
			</div>
		</div>
	</div>
	<!-- End Edit subject -->

	<!-- excelFormaModal -->
	<div class="modal fade" id="excelFormat" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">Download Excel
						Format</h4>
				</div>

				<div class="clearfix"></div>
				<div class="modal-body" style="border-bottom: none !important">
					<ul>
						<li>Please do not delete any sheet.</li>
						<li>Please do not rename any sheet.</li>
						<li>Please do not delete header row from any sheet.</li>
					</ul>
					<div style="text-align: center">
						<a class="btn btn-warning"
							href="${pageContext.request.contextPath}/admin/downloadExcelFormat">Download</a>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- excelFormatModal -->

	<div class="modal fade" id="myModal1" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<form enctype="multipart/form-data" id="form1" method="post"
			onsubmit="return false;">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title" id="myModalLabel">Subject Detail</h4>
					</div>
					<div class="modal-body">
						<div class="col-md-12 col-sm-12 col-xs-12">
							<div class="row">
								<input type="hidden" id="boardId" name="boardId" value="1" />
								<c:forEach var="section" items="${sectionList}">
									<p>
										<input class="sectionCheckbox" type="checkbox"
											value="${section.id}" />&nbsp;&nbsp;${section.grade.name}&nbsp;&nbsp;
									</p>
								</c:forEach>
							</div>
						</div>
					</div>
					<div class="modal-body">
						<div class="col-md-12 col-sm-12 col-xs-12">
							<div class="row" id="newCreateSubject">
								<input type="text" id="subjectName" placeholder="Subject Name">
								Subject Image <input type="file" name="subjectImage"
									placeholder="Subject Image" id="subjectImageId" />
							</div>



						</div>
					</div>
					<div class="modal-body">
						<div class="col-md-12 col-sm-12 col-xs-12">
							<div class="row" id="orId">OR</div>
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
						<button class="btn btn-primary" onclick="saveSubject1()">Add
							Subject</button>


					</div>
				</div>
			</div>
		</form>
	</div>

	<%-- <div class="modal fade" id="myModal2" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
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
								<select id="gradeIdEdit">
									<option value="">Select Grade</option>
									<c:forEach var="grade" items="${gradeList}">
										<option value="${grade.id}">${grade.name}</option>
									</c:forEach>
								</select>
							</div>
						</div>
					</div>
					<div class="modal-body">
						<div class="col-md-12 col-sm-12 col-xs-12">
							<div class="row">
								<input type="text" id="subjectName2" placeholder="Subject Name" required="required" >
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<button class="btn btn-primary" onclick = "editSubject()">Edit Subject</button>
					</div>
				</div>
			</div>
		</div> --%>
</body>
</html>