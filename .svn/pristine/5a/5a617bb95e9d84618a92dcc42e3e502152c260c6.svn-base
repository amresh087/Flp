
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%-- <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/placeholders.jquery.min.js"></script> --%>
<div style="width: 90% !important; margin: 0px auto;">
	<div id="buttonId" style="display: none; margin-bottom:10px;">
		<button class="btn btn-success " data-toggle="modal"
			onclick="studentForm.reset()" data-target="#addStudentModel"
			id="addStudentId">Add New</button>
					<button class="btn btn-success " data-toggle="modal"
			onclick="assignForm.reset()" data-target="#assignedSubject"
			id="assignedSubId">Assign Subject</button>
			
		<button class="btn btn-success " data-toggle="modal"
			onclick="unAssignForm.reset()" data-target="#unassignedSubject"
			id="unassignedSubId">Unassign Subject</button>

	</div>
	<table id="studentDetailsId" class="display" style="display: none;">
		<thead>
			<tr class="dataTab">
				<th>S.No.</th>
				<th>Admission No.</th>
				<th>Student's Name</th>
				<!-- <th>Address</th> -->
				<th>Username</th>
				<th>Password</th>
				<th>Subjects</th>
				<th>Action</th>
			</tr>
		</thead>
		<tbody>
			<c:if test="${not empty studentList}">
				<c:forEach var="student" items="${studentList }" varStatus="status">
					<tr id="first1">
						<td>${status.count }</td>
						<td id="sAddNo_${status.count }">${student.admissionNo }</td>
						<td id="sName_${status.count }"><a href="javascript:void(0)"
							id="studentId${student.id }" class="admissionNoCls"
							data-toggle="modal" data-target="#parentModel">${student.firstName }</a>

							<input type="hidden" id="hidSName_${status.count }"
							value="${student.firstName }" />
							<c:if test="${not empty student.emailId }">
							<input type="hidden" id="emailIds_${status.count }" value="${student.emailId }"/>
							</c:if>
							<c:if test="${not empty student.mobileNo }">
							<input type="hidden" id="mobileNoIds_${status.count }" value="${student.mobileNo }"/>
							</c:if>
							</td>
						<%-- <td><c:if test="${not empty student.address}">${student.address }</c:if>
								<c:if test="${empty student.address}">N/A</c:if></td> --%>
						<td id="sUserName_${status.count }">${student.userName }</td>
						<td id="sPass_${status.count }">${student.password }</td>
						<td id="sSubject_${status.count }"><c:choose>
								<c:when test="${not empty  subjectSectionLst }">
									<c:set var="subStr" value="" />
									<c:forEach var="sub" items="${subjectSectionLst }"
										varStatus="status1">
										<c:set var="flag" value="0"></c:set>
										<c:choose>
											<c:when test="${not empty student.unassignedSubjectId }">
												<c:forEach var="notsub"
													items="${student.unassignedSubjectId }">
													<c:if test="${sub.subject.id eq notsub}">
														<c:set var="flag" value="1"></c:set>
													</c:if>
												</c:forEach>
												<c:if test="${flag eq 0}">
													<c:set var="subStr" value="${subStr}, ${sub.subject.name }" />
												</c:if>


											</c:when>
											<c:otherwise>
												<c:set var="subStr" value="${subStr}, ${sub.subject.name }" />
											</c:otherwise>
										</c:choose>
									</c:forEach>
									<c:set var="subStr"
										value="${fn:substring(subStr, 1, fn:length(subStr))}" />
										${subStr}
										
									</c:when>
								<c:otherwise>N/A</c:otherwise>
							</c:choose></td>
						<td><a href="javascript:void(0);" data-toggle="modal"
							data-target="#editStudentModel" id="editStudentId${status.count }" class="editStudentCls">Edit</a>&nbsp;/&nbsp; <a
							href="${pageContext.request.contextPath}/schoolAdmin/inactivateUser/${student.id}/2.htm"
							onclick="return confirm('Are you sure you want to remove?')">Remove</a>

							<%-- /<a  href="#edit"  data-target="#addEditModel" data-toggle="modal" data-id="${status.count }" onclick="editProfile(${status.count })">Edit</a> --%></td>
					</tr>
				</c:forEach>

			</c:if>
		</tbody>
	</table>
</div>


<!-- Modal -->
<div id="editStudentModel" class="modal fade" role="dialog">
	<div class="modal-dialog">

		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<div class="modal-title">
					<span>Student Details</span>
				</div>
			</div>
			<form:form
				action="${pageContext.request.contextPath}/schoolAdmin/updateUser.htm"
				commandName="user" method="post" id="updateUserForm"
				class="form-horizontal" role="form">
				<div class="modal-body">
					<table width="90%" border="0" cellspacing="0" cellpadding="0"
						class="topic-per table table-bordered table-hover table-condensed">
						<tr>
							<td><strong>UserName :</strong></td>
							<td><span id="userNameIds"></span>
							<input type="hidden" id="studentIds1" name="id">
							</td>
						</tr>
						<tr>
							<td><strong>Admission No. :</strong></td>
							<td><span id="admissionNoIds"></span></td>
						</tr>

						<tr>
							<td><strong>Name:</strong></td>
							<td><input type="text" placeholder="Enter Name" id="nameIds"
								required="required" name="firstName" onkeydown="return alphaOnly(event)" pattern="^(?=.*[a-zA-Z]).+" maxlength="100"/></td>
						</tr>
						<tr>
							<td><strong>Email:</strong></td>
							<td><input type="email" placeholder="Enter Email" id="emailIds"
								name="emailId" /></td>
						</tr>
						<tr>
							<td><strong>Mobile No.:</strong></td>
							<td><input type="tel" placeholder="Enter Mobile No" id="mobileIds" name="mobileNo"
							 pattern="[0-9]{10,11}" maxlength="10"/></td>
						</tr>
						
					</table>



				</div>
				<span id="dateErr" style="color: red;"></span>
				<div class="modal-footer">
					<input type="submit" class="btn btn-primary btn-md" id="updateUser" value="Update"/>
				</div>
			</form:form>
		</div>

	</div>
</div>

<!-- Modal -->
<div class="modal fade" id="parentModel" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title" id="myModalLabel">Parent Details</h4>

			</div>
			<div class="modal-body pd10">
				<button class="btn btn-success " data-toggle="modal"
					data-target="#addParent" id="addParents">Create New Parent</button>

				<form:form id="hiddenfield">
				</br>
					<select name="parent" id="getParentId">
					</select>
					<div class="col-md-6" style="margin: 10px 0;">
						<div class="row">
							<button type="button" class="btn btn-primary btn-success mrt10"
								id="saveParent">Submit</button>
						</div>
					</div>
				</form:form>

				<table id="parentDetailsId" border="1" class="table mr10">
					<%-- <form:form id="hiddenfield"><select name="parent" id="getParentId" onchange="getId(this.value)"> --%>

				</table>
			</div>
			<div id="msg"
				style="color: orange; text-aligh: center; width: 90%; padding: 10px;"></div>
		</div>
		<input type="hidden" id="hiddenCountId" value="0"> <input
			type="hidden" id="hiddenSchoolId" value="0">
	</div>

</div>




<!-- Modal -->
<div class="modal fade" id="addStudentModel" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close" value="Reset Form" onclick="studentForm.reset()">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title" id="myModalLabel">Add New Student</h4>
			</div>
			<form:form name="studentForm"
				action="${pageContext.request.contextPath}/schoolAdmin/studentTeacherRegistration.htm"
				commandName="user" method="post" id="userRegistration"
				class="form-horizontal" role="form">
				<div class="clearfix"></div>
				<div class="modal-body">
					<div class="col-md-12 col-sm-12 col-xs-12">
						<div class="row">
							<input type="text" name="firstName" id="firstname"
								onkeydown="return alphaOnly(event)" pattern="^(?=.*[a-zA-Z]).+"
								title="Only enter letters" placeholder="Enter Student  name"
								required class="registration" maxlength="100" />
						</div>
					</div>
					<div class="col-md-12 col-sm-12 col-xs-12">
						<div class="row">
							<input type="text" name="admissionNo" id="admissionNo"
								placeholder="Enter Student  Admission No" required pattern="^(?=.*[a-zA-Z0-9]).+"
								class="registration" />
						</div>
					</div>
					<div class="col-md-12 col-sm-12 col-xs-12">
						<div class="row">
							<input type="text" name="userName" id="userName"
								placeholder="Enter Student UserName" required pattern="^(?=.*[a-zA-Z]).+"
								class="registration">
						</div>
					</div>
					<div class="col-md-12 col-sm-12 col-xs-12">
						<div class="row">
							<input type="password" name="password" id="lastname"
								placeholder="Enter your password" required pattern=".{6,}"
								title="Six or More characters" class="registration">
						</div>
					</div>
					<div class="col-md-12 col-sm-12 col-xs-12">
						<div class="row">
							<input type="email" name="emailId" id="emailId"
								placeholder="Enter Student EmailId" class="registration">
						</div>
					</div>
					<div class="col-md-12 col-sm-12 col-xs-12">
						<div class="row">
							<input type="tel" pattern="[0-9]{10,11}" name="mobileNo"
								placeholder="Please enter your mobile no." maxlength="10"
								class="registration">
						</div>
					</div>

					<input type="hidden" name="userType" value="2"> <input
						type="hidden" name="sectionId" id="sectionIds" value="0">
					<input type="hidden" name="gradeId" id="gradeIds1" value="0">
					<div class="clearfix"></div>
					<button type="submit" class="btn btn-primary btn-success">Submit
						</button>
				</div>
			</form:form>
			<c:if test="${not empty key}">
				<c:if test="${key == 1 }">
					<div class="studentMsg" style="color: green">Student has been
						added successfully</div>

				</c:if>

				<c:if test="${key == 3 }">
					<div class="studentMsg" style="color: green">User Already
						Exist</div>

				</c:if>

				<c:remove var="key" scope="session" />

			</c:if>
		</div>

	</div>
</div>




<!-- Modal -->
<div class="modal fade" id="unassignedSubject" tabindex="-1"
	role="dialog" aria-labelledby="myModalLabel">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title" id="myModalLabel">Unassign Subject</h4>
				<br />
				<form:form name="unAssignForm"
					action="${pageContext.request.contextPath}/schoolAdmin/unassignedSubject.htm"
					method="post" id="unAssignedSubjectId">
					<div class="col-md-12 col-sm-12 col-xs-12">
						<div class="row">
							<select class="registration" name="subjectId" required="required">
								<option value="">Select Subject</option>
								<c:if test="${not empty  subjectSectionLst }">
									<c:forEach var="sub" items="${subjectSectionLst }">
										<option value="${sub.subject.id }">${sub.subject.name }</option>
									</c:forEach>
								</c:if>
							</select>
						</div>
					</div>
					<br />
					<br />
					
					<table style="width: 100%">
						<thead>
							<tr>
								<th><input type="checkbox" id="select1" onchange="selectAll()"/></th>
								<th>Select All</th>
							</tr>
						</thead>
					<table style="width: 100%">
						<thead>
							<tr>
								<th>S.No.</th>
								<th>Student Name</th>
							</tr>
						</thead>
						<tbody>
							<c:if test="${not empty studentList}">
								<c:forEach var="student" items="${studentList }">
									<tr>
										<td><input type="checkbox" name="studentIds"
											value="${student.id }" class="studentIdsCls selectStudent" /></td>
										<td>${student.firstName }&nbsp;${student.lastName }</td>
									</tr>
								</c:forEach>
							</c:if>
						</tbody>
					</table>
					<span class="errorCls1" style="color: red;"></span>
					<br />
					<input type="hidden" name="sectionId" value="0"
						id="hiddenSectionId" />
					<button type="submit" class="btn btn-primary btn-success"
						id="unassignedId">Unassign</button>
				</form:form>
			</div>

		</div>

	</div>
</div>

<!-- Modal for asssigned subject who already unsigned -->
<div class="modal fade" id="assignedSubject" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title" id="myModalLabel">Assign Subject</h4>
				<br />
				<form:form name="assignForm"
					action="${pageContext.request.contextPath}/schoolAdmin/assignedSubject.htm"
					method="post" id="assignedSubjectId">
					<div class="col-md-12 col-sm-12 col-xs-12">
						<div class="row">
							<select class="registration" name="studentId"
								id="assignSubjectIdId" required="required">
								<option value="">Select Student</option>
								<c:if test="${not empty studentList}">
									<c:forEach var="student" items="${studentList }">
										<option value="${student.id }">${student.firstName}&nbsp;${student.lastName }</option>
									</c:forEach>
								</c:if>
							</select>
						</div>
					</div>

					<div class="col-md-12 col-sm-12 col-xs-12">
						<div class="row">
							<span id="assignedSubjectIds"></span>
							<span id="noAssignedSubjectIds"></span>
						</div>
					</div>
					<span class="errorCls2" style="color: red;"></span>
					<input type="hidden" name="sectionId" value="0"
						id="hiddenSectionId1" />
					<br />
					<br />
					<button type="submit" class="btn btn-primary btn-success"
						id="assignedId">Assign</button>
				</form:form>
			</div>

		</div>

	</div>
</div>

<!-- Modal for asssigned subject who already unsigned -->
<div class="modal fade" id="addParent" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title" id="myModalLabel">Add Parent</h4>
				<br />
				<form:form method="get"
					action="${pageContext.request.contextPath}/schoolAdmin/parentRegistration.htm"
					commandName="user" id="getId">
	 
			First Name:<input type="text" name="firstName" id="firstname" pattern="^(?=.*[a-zA-Z]).+"
						placeholder="Enter your first name" required maxlength="100" />
					<br>
					<br>
							Email ID:<input type="email" name="emailId" id="lastname"
						placeholder="Please enter your email id" required>
					<br>
					<br>
									Phone No:<input type="tel" id="phono" pattern="[0-9]{10,11}"
						name="mobileNo" placeholder="Please enter your mobile no."
						required maxlength="11">
					<br>
					<br>

					<input type="hidden" name="hName" id="userId5" value="${userId5}" />
					<button type="submit" class="btn btn-primary btn-success">SUBMIT</button>
				</form:form>
				<c:if test="${not empty Pkey}">
					<c:if test="${Pkey == 1 }">
						<div class="parentMsg" style="color: green">Parent has been
							added successfully</div>

					</c:if>

					<c:if test="${Pkey == 3 }">
						<div class="parentMsg" style="color: green">User Already
							Exist</div>

					</c:if>

					<c:remove var="Pkey" scope="session" />

				</c:if>
			</div>

		</div>

	</div>
</div>



<script type="text/javascript">
	/* function editProfile(id){
    	 var sAddNo=$("#sAddNo_"+id).html();
    	var sName=$("#hidSName_"+id).val();
    	var sUserName=$("#sUserName_"+id).html();
    	var sPass=$("#sPass_"+id).html();
    
    	
    	
    	$('#editUserName').val(sUserName);
    	$('#editFName').val(sName);
    	$('#editPass').val(sPass);
    	$('#editAdmissionNo').val(sAddNo);
    
    	
     }
     */

    $(document).ready(function()
    {
    	
	    setTimeout(function()
	    {
		    $(".studentMsg, .parentMsg ").empty();
		    //$('#addParent').hide();
		    
	    }, 5000);
	    
	    var error = $(".parentMsg").html();
	    if(error != undefined)
	    {
		    $("#addParents").trigger('click');
	    }
	    var error = $(".studentMsg").html();
	    if(error != undefined)
	    {
		    $("#addStudentId").trigger('click');
	    }
	    
	    $('#studentDetailsId').dataTable();
	    $('#studentDetailsId').show();
	    $('#selectAll').show();
	    $("#assignedSubjectIds").empty();
	   
	    $(".admissionNoCls").on("click", function()
	    {
		    var userId = $(this).attr('id').substr(9);
		    
		    $("#parentDetailsId").empty();
		    $('#msg').empty();
		    $.post("${pageContext.request.contextPath}/ajax/rest/getParentsDetailsByUserId/" + userId, function(data)
		    {
			    var ele = '<tr><th>S.No.</th><th>Parent Name</th><th>EmailId</th><th>Contact No</th></tr>';
			    if(data != null && data.length > 0)
			    {
				    for (var i = 0; i < data.length; i++)
				    {
					    ele = ele + '<tr><td>' + (i + 1) + '</td><td>' + data[i].firstName + '</td><td>' + data[i].emailId + '</td><td>' + data[i].mobileNo + '</td></tr>';
				    }
			    }
			    var option = "<option value=''>-Select From Existing Parent List-</option>";
			    var fromId = '<input type=hidden name=hName id=userId value='+userId+'></input>';
			    //ele="Parent Detail is not available....";
			    $.post("${pageContext.request.contextPath}/ajax/rest/getAllParents", function(data)
			    {
				    
				    for (var i = 0; i < data.length; i++)
				    {
					    //alert("<option value="+data[i].id+">"+data[i].emailId+"</option>");
					    option = option + '<option value='+data[i].id+'>' + data[i].firstName + '(' + data[i].emailId + ')' + '</option>';
					    /* alert(option);
					    //alert(data[i].emailId); */
				    }
				    $("#getParentId").empty().append(option);
				    $("#hiddenfield").append(fromId);
				    $("#userId5").val(userId);
				    
				    //alert(fromId);
				    
			    }, "json");
			    
			    console.log("hey___>>" + option);
			    
			    $("#parentDetailsId").html(ele);
			    
		    });
	    });
	    
	    $("#assignSubjectIdId").on("change", function()
	    {
		    var userId = $(this).val();
		  //  console.log(userId);
		    $("#assignedSubjectIds").empty();
		    $("#noAssignedSubjectIds").empty();
		    if(userId != "")
		    {
			    $.post("${pageContext.request.contextPath}/ajax/rest/getUnassignSubjectListByUserId/" + userId, function(data)
			    {
				    //console.log(data);
				    if(data != null && data.length > 0)
				    {
					    var subjectIds = '';
					    for (var i = 0; i < data.length; i++)
					    {
						    subjectIds = subjectIds + '<br/><input type="checkbox" name="subjectIds" value="'+data[i].id+'" class="subjectIdsCls"/>&nbsp;&nbsp;' + data[i].name + '<br/>';
						  //  console.log("subject Id:->"+data[i].id);
					    }
					  //  alert(subjectIds);
					    //console.log("subject id:-"+subjectIds);
					    $("#assignedSubjectIds").append(subjectIds);
				    }else
				    	{
				    		console.log("There is no subject to assign");
				    		$("#noAssignedSubjectIds").html("Subject already assigned");
				    	}
				    
			    }, 'json');
		    }
	    });
	    
	    $("#unassignedId").on("click", function()
	    {
		    $(".errorCls2").empty();
		    var status = false;
		    var g = parseFloat(0);
		    $(".studentIdsCls").each(function(e)
		    {
			    if($(this).prop('checked') == true)
			    {
				    status = true;
			    }
			    g++;
		    });
		    if(status == false && g > 0)
		    {
			    $(".errorCls2").show().html("select atleast one checkbox").fadeOut(5000);
			    return false;
		    }
		    else if(g == 0)
		    {
			    $(".errorCls2").show().html("There is no subject to assign").fadeOut(5000);
			    return false;
		    }
		    
	    });
	    
	   
	    
	    $("#assignedId").on("click", function()
	    {
		    $(".errorCls2").empty();
		    var status = false;
		    var g = parseFloat(0);
		    
		    
		    
		    $(".subjectIdsCls").each(function(e)
		    {
			    if($(this).prop('checked') == true)
			    {
				    status = true;
			    }
			    g++;
		    });
		    if(status == false && g > 0)
		    {
			    $(".errorCls2").show().html("select atleast one checkbox").fadeOut(5000);
			    return false;
		    }
		    else if(g == 0)
		    {
			 //   $(".errorCls2").show().html("There is no subject to assign").fadeOut(5000);
			    return false;
		    }
		    
	    });
	    
	    $("#saveParent").on("click", function()
	    {
		    var id = $("#getParentId").val();
		    var userId = $("#userId").val();
		    
		    console.log(id + " " + userId);
		    if(id != "")
		    {
			    
			    $.post("${pageContext.request.contextPath}/ajax/rest/parentChildMapping/" + id + "/" + userId, function(data)
			    {
				    console.log(data);
				    var msg = $("#msg").val();
				    //	alert(msg);
				    if(msg == '')
				    {
					    $("#msg").empty().append(data).show().fadeOut(5000);
				    }
				    else
				    {
					    $("#msg").empty()
				    }
			    });
			    
		    }
		    else
		    {
			    alert("some thing went wrong...");
		    }
		    
	    });
	    
	    $("#addParents").on("click", function()
	    {
		    $(".close").trigger('click');
	    });
	    
	    
	    $(".editStudentCls").on("click", function(){
	    	var id=$(this).attr('id').substr(13);
	    	$("#studentIds1").val($("#sName_"+id).children().attr('id').substr(9)); 
	    	$("#userNameIds").html($("#sUserName_"+id).html());
	    	$("#admissionNoIds").html($("#sAddNo_"+id).html());
	    	$("#nameIds").val($("#sName_"+id).children().html());
	    	var email=$("#emailIds_"+id).val()
	    	if(email!=undefined){
	    		$("#emailIds").val(email);
	    	}else{
	    		$("#emailIds").val('');
	    	}
	    	var mobile=$("#mobileNoIds_"+id).val()
	    	if(mobile!=undefined){
	    		$("#mobileIds").val(mobile);
	    	}else{
	    		$("#mobileIds").val('');
	    	}
	    });
	    
	    $("#assignedSubId").on("click", function(){
	    	$("#noAssignedSubjectIds").empty();
	    	$("#assignedSubjectIds").empty();
	    	$(".errorCls2").empty();
	    })
	    
	    /*  
	     var errorId=$("#errorIdp").val();
	     if(errorId!=undefined){
	    	 $("#addParents").trigger('click');
	     }
	    
	     
	    
	     */
	    /*  
	     var errorId=$("#errorIds").val();
	     if(errorId!=undefined){
	    	 $("#addStudentId").trigger('click');
	     }
	    
	     
	     $("#addStudentId").on("click", function(){
	    	$("#errorIds").empty(); 
	    	$(".close").trigger('click');
	     });
	     */
	   
    });
     function selectAll(){
    	 
    	 if($("#select1").prop('checked'))
    		 {
	    		 $(".selectStudent").prop("checked", 1);
    			} else {
    				$(".selectStudent").prop("checked", 0);
    		 }
     }
</script>
