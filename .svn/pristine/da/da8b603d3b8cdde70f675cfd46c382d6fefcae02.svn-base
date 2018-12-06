<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<jsp:include page="../globalPage.jsp" />
	<br />
	<h2 class="text-center">SME Information</h2>
	<style>
	
		.smeName:hover
		{
			font-weight: bold;
			color:blue;
			background-color: #A9D0F5 !important;
		}
	</style>
	<div style="width: 90% !important; margin: 0px auto;">
		<button style="margin-bottom:10px;" class="btn btn-success " data-toggle="modal" onclick="addSme23.reset()"
			data-target="#smeModel" id="smeadd">Add New</button>
			<button style="margin-bottom:10px;" class="btn btn-success " data-toggle="modal" onclick="addSme.reset()"
			data-target="#smeAssignModel" id="smeAssignGradeAndSubject">Assign Grade And Subject</button>
			
		<!-- <button class="btn btn-success " data-toggle="modal"
		data-target="#smeUnassignModel" id="smeUnassignGradeAndSubject">Unassign
		Grade And Subject</button>
 -->
		
		<table id="smeDetailsId" class="display" style="display: none;">
			<thead>
				<tr class="dataTab">
					<th>S.No.</th>
					<th>Name</th>
					<!-- <th>Address</th> -->
					<th>Username</th>
					<th>Password</th>
					<th>Contact No.</th>
					<th>Email-Id</th>
					<th>Action</th>
				</tr>
			</thead>
			<tbody>
				<c:if test="${not empty schoolSmeList}">
					<c:forEach var="sme" items="${ schoolSmeList}" varStatus="status">
						<tr>
							<td>${status.count }
							<input type="hidden" id="smeId${status.count }" value="${sme.id }">
						</td>
						<td class="smeName" onclick="getSmeGradesAndSubject('${sme.id}', '${sessionScope.user.sessionId}')" style="cursor: pointer; background: #E0ECF8;text-decoration: underline;" data-toggle="modal" data-target="#smeGradesAndSubjects">
							<span id="smeNameId${status.count }">${sme.firstName }</span>
						</td>
						<%-- 	<td><c:if test="${not empty sme.address }">${sme.address }</c:if><c:if test="${ empty sme.address }">N/A</c:if></td> --%>
						<td>
							<span id="smeUserNameId${status.count }">${sme.userName }</span>
						</td>
						<td>${sme.password }</td>
						<td>
							<span id="smeMobileNoId${status.count }">${sme.mobileNo }</span>
						</td>
						<td>
							<span id="smeEmailId${status.count }">${sme.emailId }</span>
						</td>
						<td>
							<a href="javascript:void(0);" data-toggle="modal" data-target="#updateUserModel" id="updateUserId${status.count }" class="updateUserCls">Edit</a>
							&nbsp;/&nbsp;
							<a href="${pageContext.request.contextPath}/schoolAdmin/inactivateUser/${sme.id}/6" onclick="return confirm('Are you sure you want to remove?')">Remove</a>
						</td>
					</tr>
				</c:forEach>

			</c:if>

		</tbody>
	</table>
</div>

<!-- Modal -->
<div aria-labelledby="myModalLabel" role="dialog" tabindex="-1" id="smeGradesAndSubjects" class="modal fade in" aria-hidden="false">
	<div style="width: 500px !important" role="document" class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button aria-label="Close" data-dismiss="modal" class="close" type="button">
					<span aria-hidden="true">×</span>
				</button>
				<h4 id="myModalLabel" class="modal-title">SME Assigned Grades And Subjects</h4>
			</div>
			<div style="margin: 15px">
				<table cellspacing="0" cellpadding="0" border="0" class="topic-per table-striped table table-bordered table-hover table-condensed" style="overflow: scroll">
					<thead>
						<tr>
							<th style="text-align: center">Grade Name</th>
							<th style="text-align: center">Subject Name</th>
							<th style="text-align: center">Action</th>
						</tr>
					</thead>
					<tbody id="gradesAndSubjects"></tbody>
				</table>
			</div>
		</div>
	</div>
</div>

<!-- Modal -->

<div class="modal fade" id="smeModel" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title" id="myModalLabel">SME Detail</h4>
			</div>
			<form:form name="addSme23" action="${pageContext.request.contextPath}/schoolAdmin/saveSmeRegistration.htm" commandName="user" method="post" id="smeRegistration" class="form-horizontal" role="form" >
				<div class="modal-body">
					<div class="col-md-12 col-sm-12 col-xs-12">
						<div class="row">
							<input type="text" name="firstName" id="firstname" placeholder="Enter Name" required class="registration" pattern="^(?=.*[a-zA-Z]).+" onkeydown="return alphaOnly(event)"/>
						</div>
					</div>
					<div class="col-md-12 col-sm-12 col-xs-12">
						<div class="row">
							<input type="email" name="emailId" id="emailId" placeholder="Enter email id" required class="registration">
						</div>
					</div>
					<div class="col-md-12 col-sm-12 col-xs-12">
						<div class="row">
							<input type="password" name="password" id="lastname" placeholder="Enter password" required pattern=".{6,}" title="Six or More characters" class="registration">
						</div>
					</div>
					<div class="col-md-12 col-sm-12 col-xs-12">
						<div class="row">
							<input type="tel" id="mobileNo" pattern="[0-9]{10,11}" name="mobileNo" placeholder="Enter mobile no." required maxlength="10" class="registration">
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="submit" class="btn btn-primary">Add</button>
				</div>
			</form:form>
			<c:if test="${not empty key }">
				<div id="msg" style="color: red">"${key}"</div>
				<c:remove var="key" scope="session" />
			</c:if>
		</div>
	</div>
</div>



<!-- Modal -->
<div id="updateUserModel" class="modal fade" role="dialog">
	<div class="modal-dialog">

		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<div class="modal-title">
					<span>Subject Matter Expert Details</span>
				</div>
			</div>
			<form:form action="${pageContext.request.contextPath}/schoolAdmin/updateUser.htm" commandName="user" method="post" id="updateUserForm" class="form-horizontal" role="form">
				<div class="modal-body">
					<table width="90%" border="0" cellspacing="0" cellpadding="0" class="topic-per table table-bordered table-hover table-condensed">
						<tr>
							<td>
								<strong>UserName :</strong>
							</td>
							<td>
								<span id="userNameIds"></span> <input type="hidden" id="studentIds1" name="id">
							</td>
						</tr>

						<tr>
							<td>
								<strong>Name:</strong>
							</td>
							<td>
								<input type="text" placeholder="Enter Name" id="nameIds" required="required" name="firstName" onkeydown="return alphaOnly(event)" pattern="[a-zA-Z\s]+" maxlength="100" />
							</td>
						</tr>
						<tr>
							<td>
								<strong>Email:</strong>
							</td>
							<td>
								<input type="email" placeholder="Enter Email" id="emailIds" name="emailId" required="required" />
							</td>
						</tr>
						<tr>
							<td>
								<strong>Mobile No.:</strong>
							</td>
							<td>
								<input type="tel" placeholder="Enter Mobile No" id="mobileIds" name="mobileNo" pattern="[0-9]{10,11}" required="required" maxlength="10"/>
							</td>
						</tr>

					</table>



				</div>
				<span id="dateErr" style="color: red;"></span>
				<div class="modal-footer">
					<input type="submit" class="btn btn-primary btn-md" id="updateUser" value="Update" />
				</div>
			</form:form>
		</div>

	</div>
</div>

<div class="modal fade" id="smeAssignModel" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title" id="myModalLabel">Assign Grade And Subject</h4>
			</div>
			<form:form name="addSme" action="#" commandName="user" method="post" id="smeRegistration" class="form-horizontal" role="form">
				<div class="modal-body">
					<div class="col-md-12 col-sm-12 col-xs-12">
						<div class="row">
							<select name="sName" id="smeId" required="required">
								<option value="">Select SME</option>
								<c:if test="${not empty schoolSmeList}">
									<c:forEach var="sme" items="${schoolSmeList }">
										<option value="${sme.id}">${sme.firstName }</option>
									</c:forEach>
								</c:if>
							</select>
						</div>
					</div>
					<div class="col-md-12 col-sm-12 col-xs-12">
						<div class="row">
							<select name="gName" id="gradeId" onchange="getSubjectByGradeId($(this).val(),'subjectChk')" required="required">
								<option value="">Select Grade</option>
								<c:if test="${not empty gradeList}">
									<c:forEach var="grade" items="${gradeList }">
										<option value="${grade.id}">${grade.name }</option>
									</c:forEach>
								</c:if>
							</select>
						</div>
					</div>
					<div class="col-md-12 col-sm-12 col-xs-12">
					
						<div class="row">
							<div id="subjectChk"></div>
						</div>
					</div>

				</div>
				<span class="errorId1" style="color: red;"></span>
				<div class="modal-footer">
					<input type="button" class="btn btn-primary btn-md" id="assignGradeToSme" value="Assign" />
				</div>

			</form:form>
			<c:if test="${not empty key }">
				<div id="msg" style="color: red">"${key}"</div>
				<c:remove var="key" scope="session" />
			</c:if>
		</div>
	</div>
</div>

<script type="text/javascript">
	$(document).ready(function()
    {
		 
		
	    var error = $("#msg").html();
	    if(error != undefined)
	    {
		    $("#smeadd").trigger('click');
		    $("#msg").fadeOut(5000);
		    
	    }
	    $('#smeDetailsId').dataTable();
	    $('#smeDetailsId').show();
	    $("#addViewSme").addClass("active");
	    
	    $(".updateUserCls").on("click", function()
	    {
		    var id = $(this).attr('id').substr(12);
		    $("#studentIds1").val($("#smeId" + id).val());
		    $("#userNameIds").html($("#smeUserNameId" + id).html());
		    $("#nameIds").val($("#smeNameId" + id).html());
		    $("#emailIds").val($("#smeEmailId" + id).html());
		    $("#mobileIds").val($("#smeMobileNoId" + id).html());
	    });
	    
	    $("#smeAssignGradeAndSubject").on("click", function()
	    {
		    $("#subjectChk").empty();
		    $("#gradeId").val("");
		    $("#smeId").val("");
	    });
	    
    });
    function getSubjectByGradeId(gradeId, targetId)
    {
	    
	    $('#' + targetId).empty();
	    var subjectId = $('#subjectId').val();
	    
	    var gradeId = gradeId;
	    var smeId = $("#smeId").val();
	    
	    $.post("${pageContext.request.contextPath}/schoolAdmin/getUniqueSubjectsByGradeId/" + gradeId + "/" + smeId, function(data)
	    {
		    var subjectAll = data["subjectAll"];
		    var subjectIn = data["subjectIn"];
		   
		    
		    if(subjectAll.length==0 || subjectAll==null)
			{
				$("#subjectChk").append("Subject already assigned");
			}
		    
		    if(subjectAll.length != null && subjectAll.length > 0)
		    {
			    for (var i = 0; i < subjectAll.length; i++)
			    {
				    var flag = false;
				    if(subjectIn.length != null && subjectIn.length > 0)
				    {
					    for (var j = 0; j < subjectIn.length; j++)
					    {
						    console.log("subjectId of in" + subjectIn[j].id);
						    if(parseInt(subjectAll[i].id) == parseInt(subjectIn[j]))
						    {
							    flag = true;
						    }
					    }
				    }
				    if(!flag)
				    {
					    $('#' + targetId).append("<p><input type='checkbox' class= 'mappingCheckbox' name='"+subjectAll[i].name+"' value='"+subjectAll[i].id+"'  />&nbsp;&nbsp;" + subjectAll[i].name + "&nbsp;&nbsp;</p>");
				    }
			    }
			    
		    }
	    });
    }
    $("#assignGradeToSme").on("click", function()
    {
	    
	    var smeId = $("#smeId").val();
	    //var subjectId=$("#subjectId").val();
	    
	    var gradeId = $("#gradeId").val();
	    var status = false;
	    var g = parseFloat(0);
	    if(smeId == "")
	    {
		    alert('Please Select Sme');
		    return false;
	    }
	    else if(gradeId == "")
	    {
		    alert('Please Select Grade')
		    return false;
	    }
	    
	    $(".mappingCheckbox").each(function(e)
	    {
		    if($(this).prop('checked') == true)
		    {
			    status = true;
		    }
		    g++;
	    });
	    if(status == false && g > 0)
	    {
		    alert("select atleast one subject");
		    return false;
	    }
	    
	    else if(g == 0)
	    {
		    alert("Subject already assigned");
		    return false;
	    }
	    
	    var subjectIdString = $('.mappingCheckbox:checked').map(function()
	    {
		    return this.value;
	    }).get();/*
	    					var gradeNameString = $('.mappingCheckbox:checked').map(function() 
	    											{
	    											    return this.name;
	    											}).get().join("##"); */
	    
	    //console.log("subjectId"+subjectId+"gardeId"+gradeId+"sectionId"+sectionIdString);
	    
	    $.ajax(
	    {
	        url : "${pageContext.request.contextPath}/schoolAdmin/assignGradeToSme.htm ",
	        type : "POST",
	        data :
	        {
	            "subjectIdString" : subjectIdString.toString(),
	            "gradeId" : gradeId,
	            "smeId" : smeId
	        },
	        success : function(data)
	        {
		        if(data == 'success')
		        {
			        alert("successfully mapped");
			        location.href = "${pageContext.request.contextPath}/schoolAdmin/smeDetails.htm";
		        }
		        else
		        {
			        alert(data);
		        }
	        },
	        error : function()
	        {
		        alert("Something went wrong");
	        }
	    });
	    
    });
    
    function getSmeGradesAndSubject(userId, sessionId)
    {
	    $.ajax(
	    {
	        url : "${pageContext.request.contextPath}/schoolAdmin/getSmeAssignedGradesAndSubjects",
	        type : "POST",
	        data :
	        {
	            "userId" : userId,
	            "sessionId" : sessionId,
	        },
	        success : function(data)
	        {
		        $('#gradesAndSubjects').empty();
		        $.each(data, function(key, value)
		        {
			        $('#gradesAndSubjects').append("<tr><td>" + value.grade.name + "</td><td>" + value.subject.name + "</td><td><a href='javascript:void(0)' onclick='removeSmeAssignedSubject(" + value.id + ","+userId+","+sessionId+")'>Remove</a></td></tr>");
		        });
	        },
	        error : function()
	        {
		        alert("Something went wrong");
	        }
	    });
    }

    function removeSmeAssignedSubject(smeGradeSubjectId, userId, sessionId)
    {
	   // alert(smeGradeSubjectId+"-"+userId+"-"+sessionId);
	    $.ajax(
	    {
	        url : "${pageContext.request.contextPath}/ajax/rest/unassignSubjectFromSme/"+smeGradeSubjectId,
	        type : "POST",
	        data :
	        {
	        	
	        },
	        success : function(data)
	        {
	        	if(data == true)
	        	{
	        		alert("Subject unassigned successfully");
	        		getSmeGradesAndSubject(userId, sessionId);
	        	}
	        },
	        error : function()
	        {
	            alert("Something went wrong");
	        }
	    }); 
    }
</script>
