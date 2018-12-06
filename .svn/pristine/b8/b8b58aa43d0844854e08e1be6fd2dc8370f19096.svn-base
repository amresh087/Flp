
<jsp:include page="../globalPage.jsp" />		<!-- All js, css, data-table api are defined in globalPage.js -->
		
		<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<h2 class="text-center">Grade Details</h2>
		
		<div style="width:90%;margin:0px auto;">
			 <button style="margin-bottom:10px;" class="btn btn-success " data-toggle="modal"  onclick="clearFields('#grade1','#sectionName')" data-target="#myModal1" >Add New Section</button> 
			 <button style="margin-bottom:10px;" class="btn btn-success " data-toggle="modal"  onclick="clearFields('#selectGrade','#selectSection','#subjectBoxes')" data-target="#myModal2" >Add Subject to Section</button>
			 <button style="margin-bottom:10px;" class="btn btn-primary" data-toggle="modal"  onclick="clearFields('#gradeSelector','#txtGradeName')" data-target="#myModal3">Edit Grade Name</button> 
			 
			<!-- <button class="btn btn-danger" data-toggle="modal" data-target="#myModal3" onclick="removeSubject()">Remove</button><br><br>? -->
			
						<table id="editGradeTable" class="display" style="width:100%">
							<thead>
								<tr class="dataTab">
									<th>S.No.</th>
									<th>Grade</th>
									<th>Section</th>
									<th>Subject</th>
									<th>Action</th>
								</tr>
							</thead>
							<tbody>
							<c:set var="count" value="1"></c:set>
								<c:forEach var="sessionSchoolGrade" items="${gradeList}">
									
									<c:forEach var="section" items="${sessionSchoolGrade.sections}">
									
										<c:choose >
										<c:when test="${empty section.subjectSectionList}">
												<tr>
										<td> &nbsp;&nbsp;${count}</td>
										<td class="${sessionSchoolGrade.grade.id}">${sessionSchoolGrade.grade.name}</td>
										<td class="${section.id}">${section.name}</td>
										<td class="">No Subject Assigned</td>
										<td>N/A</td>
										
									</tr>
									<c:set var="count" value="${count+1}"></c:set>
									
																			
										</c:when>
										<c:otherwise>
										<c:forEach var="subjectSection" items="${section.subjectSectionList}">
											
									
									
									
									<tr>
										<td> &nbsp;&nbsp;${count} &nbsp;&nbsp;</td>
										<td class="${sessionSchoolGrade.grade.id}">${sessionSchoolGrade.grade.name}</td>
										<td class="${section.id}">${section.name}</td>
										<td class="${subjectSection.subject.id}">${subjectSection.subject.name}</td>
										<td><a href="#" onclick="removeMapping(${subjectSection.id},${subjectSection.subject.id},${section.id});">Remove </a></td>
									</tr>
									<c:set var="count" value="${count+1}"></c:set>
									
										</c:forEach>
										</c:otherwise>
										</c:choose>
										
									</c:forEach>
								</c:forEach>
							</tbody>
						</table>
					
		</div>
		
		<div class="modal fade" id="myModal1" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
			<div class="modal-dialog" role="document">
				<div class="modal-content" >
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title" id="myModalLabel">Add Section</h4>
					</div>
					<div class="modal-body">
						<div class="col-md-12 col-sm-12 col-xs-12">
							<div class="row">
								<select id="grade1">
								<option value=''>Select Grade</option>
								<c:forEach var="sessionSchoolGrade" items="${gradeList}">
									<option value='${sessionSchoolGrade.grade.id}'>&nbsp;&nbsp;${sessionSchoolGrade.grade.name}&nbsp;&nbsp;</option>
								</c:forEach>
								</select>
							</div>
							<div class="row">
								<input type="text" id="sectionName" placeholder="Enter section name" >
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<button class="btn btn-success" onclick = "addSection()">Add Section</button>
					</div>
				</div>
			</div>
		</div>
		
		<div class="modal fade" id="myModal2" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-label="Close" >
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title" id="myModalLabel">Map Subjects</h4>
					</div>
					<div class="modal-body">
						<div class="col-md-12 col-sm-12 col-xs-12">
							<div class="row">
								<select id='selectGrade' onchange="populateSection()" style="margin-bottom: 10px;">
								<option value="">Select grade</option>
								<c:forEach var="sessionSchoolGrade" items="${gradeList}">
									<option value="${sessionSchoolGrade.grade.id}">${sessionSchoolGrade.grade.name}</option>
								</c:forEach>

								</select>
								<br>
								<select id='selectSection' onclick="populateSubject()" style="margin-bottom: 10px;">
								<option value="">Select Section</option>
								</select>
								<br>
								<div id="subjectBoxes">
								
								
								</div>						
							</div>
						</div>
					</div>
					<br>
					<div class="modal-footer">
						<button class="btn btn-success" onclick="addSubjectToSection()" >Map subject(s)</button>
					</div>
				</div>
			</div>
		</div>
		
			<div class="modal fade" id="myModal3" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-label="Close" ">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title" id="myModalLabel">Change grade name</h4>
					</div>
					<div class="modal-body">
						<div class="col-md-12 col-sm-12 col-xs-12">
							<div class="row">
							<select id="gradeSelector">
									<option value="">Select Grade</option>
								<c:forEach var="sessionSchoolGrade" items="${gradeList}">
									<option value="${sessionSchoolGrade.grade.id}">${sessionSchoolGrade.grade.name}</option>
								</c:forEach>
								</select>

							</div>
							<div class="row">
								<input type="text" id="txtGradeName" maxlength="50" placeholder="Enter grade name"  >
							</div>
						</div>
					</div>
					
					<div class="modal-footer">
						<button class="btn btn-primary" onclick = "editGradename()">Rename Grade</button>
					</div>
				</div>
			</div>
		</div>
<script>
var schoolId=0;
$(document).ready(function()
		{
			generateDataTable();
			selectOnlyOneCheckbox();
			$("#schoolToc").addClass("active");
		});
		
		function selectOnlyOneCheckbox()
		{
			$('.tableCheckbox').on('change', function() 
			{
				$('.tableCheckbox').not(this).prop('checked', false);
				console.log($('.tableCheckbox [type="checkbox"]').val())
			});
		}
		
		function generateDataTable()
		{
			//$('#reportTableID').dataTable().fnDestroy();			// Use it when you want to reinitialize data table
			 $('#editGradeTable').dataTable( {
				   "order":[]
			 
				
				 			
			 } );
			//$('#editGradeTable').dataTable({ "iDisplayLength": -1 "aaSorting": [[ 0, "desc" ]] });
		 	/* var oTable=$('#editGradeTable').dataTable();
			oTable.fnSort( [ [ 1, "desc" ][ 6, "desc" ] ] ); */ 
		}
		
		function addSection()
		{
			
			
			var gradeId=$("#grade1").val();
			var sectionName=$('#sectionName').val();
			
			console.log(gradeId);
			
			if(gradeId != ''){
			if(sectionName.trim()!='')
				{
				if(/^[a-z0-9 ]+$/i.test(sectionName))
				{
				console.log("valid name, going to check & submit");
				  $.ajax(
						    {
						        url : "${pageContext.request.contextPath}/ajax/schoolAdmin/createSection?sectionName="+sectionName+"&gradeId="+gradeId,
						        type : "post",
						        dataType:"text",
						        success : function(data) 
						        {
						        	if(data=="success"){
						        	alert('Section created');
						        	location.reload();
						        	}
						        	else if(data=="duplicate")
						        		{
						        		alert('Duplicate Section name. Please provide different name');
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
						        	console.log('Something went wrong...');
						        },
						    });
				}
				else
						alert('Provide a valid section name');
				
			
			}
			else
				alert('Please provide section name');
		}
		
		else
			alert('Please select grade from above');
		}
		
		function populateSection(){
			var gradeId=$('#selectGrade').val();
			
			if(gradeId!='')
				{
				$("#selectSection").empty().append("<option value=''>Select Section</option>");
				<c:forEach var="sessionSchoolGrade" items="${gradeList}">
				if('${sessionSchoolGrade.grade.id}'==gradeId){
					<c:forEach var="section" items="${sessionSchoolGrade.sections}">
					$("#selectSection").append('<option value="${section.id}">  ${section.name}  </option>&nbsp;&nbsp');
					</c:forEach>
				}
				</c:forEach>
				
				}
			else
				{
				console.log('clear select');
				$("#selectSection").empty().append("<option value=''>Select Section</option>");
				}
		}
		
		function populateSubject()
		{
			var count = 0;
			var sectionId=$('#selectSection').val();
			console.log("Section Id--->"+sectionId);
			if(sectionId!='')
				{
				$('#subjectBoxes').empty();
				<c:forEach var="sessionSchoolGrade" items="${gradeList}">
				if('${sessionSchoolGrade.grade.id}'==$('#selectGrade').val())
					{
					<c:forEach var="section" items="${sessionSchoolGrade.sections}">
						<c:forEach var="subjectSection" items="${section.subjectSectionList}">
							$('#subjectBoxes').append('<span visible="visible" class="subjectCheckBox" alt="${subjectSection.subject.id}"><input type="checkbox" value="${subjectSection.subject.id}" name="subjectLists">&nbsp;${subjectSection.subject.name}&nbsp;&nbsp;&nbsp;</input></span>');
							count = count+1;
						</c:forEach>
					</c:forEach>
					}
				</c:forEach>
				
				$('.subjectCheckBox').each(function(){
				     if ($(this).is(':visible'))  {
				    	$('.subjectCheckBox[alt="'+$(this).attr("alt")+'"]').not(this).remove();
				    	count = count-1;
				    }
				});
				
				
				<c:forEach var="sessionSchoolGrade" items="${gradeList}">
				if('${sessionSchoolGrade.grade.id}'==$('#selectGrade').val())
					{
					<c:forEach var="section" items="${sessionSchoolGrade.sections}">
					if('${section.id}'==sectionId){
					<c:forEach var="subjectSection" items="${section.subjectSectionList}">
						$('.subjectCheckBox').each(function(){
							console.log('Here>>>>>>>>>>>>>');
							if('${subjectSection.subject.id}'==$(this).attr('alt') && '${subjectSection.subject.status}'=='1')    	
							{
								console.log('Duplicate subject: '+"${subjectSection.subject.id}"+" :removing");
								$(this).remove();
								count = count-1;
							}
						});

						</c:forEach>
					}	
					</c:forEach>
					}
				</c:forEach>

				
				if(count < 0){
					$('#subjectBoxes').html('Subjects Already Mapped');
				}
				
				
				
				
				}
		 
				
		 }
		
		
		function addSubjectToSection()
		{
			var gradeId=$('#selectGrade').val();
			var sectionId=$('#selectSection').val();
			
			if(sectionId!='')
				{
				var values=[]; 
				 $("input:checkbox[name=subjectLists]:checked").each(function(){
				    values.push($(this).val());
					});
				 if(values.length==0)
				alert('No subject selected')
				else{
					 console.log(values.length+"<>Selected subjects are: "+ values.toString());
					  $.ajax(
							    {
							        url : "${pageContext.request.contextPath}/ajax/schoolAdmin/mapSubjecttoSection?subjectIds="+values.toString()+"&sectionId="+sectionId,
							        type : "post",
							        dataType:"text",
							        success : function(data) 
							        {
							        	if(data=="success"){
							        	alert('Subject(s) mapped successfully');
							        	location.reload();
							        	}
							        	else
							        		alert("Please try again later");
							        },
							        error : function() 
							        {
							        	console.log('Something went wrong...');
							        },
							    });
				
				}
				}
			else
				alert('Please select grade and section to assign subject');
			
			
		}
		
		function editGradename()
		{
		var gradeId=$('#gradeSelector').val();
		var gradeName=$('#txtGradeName').val().trim();
		if(gradeId=='')
			alert('Please select grade to rename');
		else if(gradeName.length==0)
			alert('Please provide grade name');
		else if(gradeName!="")
			{
			console.log("Change grade name with id "+ gradeId+", with name: "+gradeName);
			  $.ajax(
					    {
					        url : "${pageContext.request.contextPath}/ajax/schoolAdmin/editGradeName?gradeId="+gradeId+"&gradeName="+gradeName,
					        type : "post",
					        dataType:"text",
					        success : function(data) 
					        {
					        	if(data=="success"){
					        	alert('Grade renamed');
					        	location.reload();
					        	}
					        	else if(data=="duplicate")
					        		{
					        		alert('Duplicate Grade name. Please provide different name');
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
					        	console.log('Something went wrong...');
					        },
					    });
			
			}
		else
			alert('Please provide valid grade name');
		}
		
		function clearFields(id,id1,id2){
			
		
			$(id).val('');
			$(id1).val('');
			$(id2).html('');
			
		//	document.getElementById("subjectName").value="";
			
		}
		function removeMapping(ssId,subjectId,sectionId){
		 	if(confirm('Are you sure?')){
			$.ajax(
					    {
					        url : "${pageContext.request.contextPath}/ajax/schoolAdmin/removeSectionSubjectMapping?ssId="+ssId+"&sectionId="+sectionId+"&subjectId="+subjectId,
					        type : "post",
					        dataType:"text",
					        success : function(data) 
					        {
					        	if(data=="success"){
					        	console.log('Mapping removed');
					        	location.reload();
					        	}else if(data=="assigned"){
					        		alert("Cant not delete ! This subject already assigned to teacher!");
					        	}
					        	else
					        		alert("Please try again later");
					        },
					        error : function() 
					        {
					        	console.log('Something went wrong...');
					        }
					    });
			} 
		}
</script>