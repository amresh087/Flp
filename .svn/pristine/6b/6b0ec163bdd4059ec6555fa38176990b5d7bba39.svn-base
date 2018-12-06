<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<jsp:include page="../globalPage.jsp" />

<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/common.js"></script>
<h2 class="text-center">Subject Teacher Section Details</h2>
<div style="width: 90% !important; margin: 0px auto;">
	<button class="btn btn-success " data-toggle="modal"
		onclick="teacherForm.reset()" data-target="#addTeacher"
		id="addTeacherId">Add Teacher</button>
		<button class="btn btn-success " data-toggle="modal"
		 data-target="#updateTeacher"
		id="updateTeacherModelId">Edit Teacher</button>
	<button class="btn btn-success " data-toggle="modal"
		onclick="assignGrade.reset()" data-target="#assignTeacherModel"
		id="assignTeacherModelId">Assign Grade & Subjects</button>
	<c:if test="${not empty error }">
		<input type="hidden" id="errorData" value="${error }" />
		<c:remove var="error" scope="session" />
	</c:if>
	<br /> <br />
	<table id="subjectteacherassignId" class="display"
		style="display: none;">
		<thead>
			<tr class="dataTab">
				<th>S.No.</th>
				<th>Teacher's Name</th>
				<th>Login-Id</th>
				<th>Password</th>
				<th>Email-Id</th>
				<th>Grade Name</th>
				<th>Section Name</th>
				<th>Subject's Name</th>
				<th>Action</th>
			</tr>
		</thead>
		<tbody>
			<c:if test="${not empty assignTeacherSectionSubjectList}">
				<c:forEach var="assignTeacher"
					items="${ assignTeacherSectionSubjectList}" varStatus="status">
					<tr>
						<td>${status.count }</td>
						<td>${assignTeacher.user.firstName }${assignTeacher.user.lastName }</td>
						<td>${assignTeacher.user.userName }</td>
						<td>${assignTeacher.user.password }</td>
						<td>${assignTeacher.user.emailId }</td>
						<td>${assignTeacher.section.sessionSchoolGrade.grade.name }</td>
						<td>${assignTeacher.section.name }</td>
						<td>${assignTeacher.subject.name }</td>
						<td><a
							href="${pageContext.request.contextPath}/schoolAdmin/removeSubjectFromTeacher/${assignTeacher.id}.htm"
							onclick="return confirm('Are you sure you want to remove?')">Remove</a></td>
					</tr>
				</c:forEach>

			</c:if>

		</tbody>
	</table>
</div>


<div class="modal fade" id="assignTeacherModel" tabindex="-1"
	role="dialog" aria-labelledby="myModalLabel">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title" id="myModalLabel">School Detail</h4>
			</div>
			<form:form name="assignGrade"
				action="${pageContext.request.contextPath}/schoolAdmin/assignSubjectToTeacher.htm"
				commandName="studentTeacherSession" method="post"
				id="assignSubjectToTeacher" class="form-horizontal" role="form">
				<div class="modal-body">
					<div id="errorId" style="color: red;"></div>
					<div class="col-md-12 col-sm-12 col-xs-12">
						<div class="row">
							<select name="userId" id="teacherId" class="registration"
								required="required">
								<option value="">Select Teacher</option>
								<c:if test="${not empty teacherList}">
									<c:forEach var="teacher" items="${teacherList }">
										<option value="${teacher.id}">${teacher.firstName}&nbsp;${teacher.lastName }</option>
									</c:forEach>
								</c:if>
							</select>
						</div>
					</div>
					<br /> <br />
					<div class="col-md-12 col-sm-12 col-xs-12">
						<div class="row">
							<select name="gradeId" id="gradeId"
								onchange="setDropdownList('#sectionId', $(this).val(), '${pageContext.request.contextPath}/ajax/rest/getSectionByGradeId', 'Select Section', false)"
								class="registration" required="required">
								<option value="">Select Grade</option>
								<c:if test="${not empty gradeList}">
									<c:forEach var="grade" items="${gradeList }">
										<option value="${grade.id}">${grade.name }</option>
									</c:forEach>
								</c:if>
							</select>
						</div>
					</div>
					<br /> <br />
					<div class="col-md-12 col-sm-12 col-xs-12">
						<div class="row">
							<select name="sectionId" id="sectionId" class="registration"
								onchange="setDropdownList('#subjectId', $(this).val(), '${pageContext.request.contextPath}/ajax/rest/getSubjectBySectionId', 'Select Subject', false)"
								required="required">
								<option value="">Select Section</option>
							</select>
						</div>
					</div>
					<br /> <br />
					<div class="col-md-12 col-sm-12 col-xs-12">
						<div class="row">
							<select name="subjectId" id="subjectId" class="registration"
								required="required">
								<option value="">Select Subject</option>
							</select>
						</div>
					</div>
					<br />

				</div>
				<div class="modal-footer">
					<button type="submit" class="btn btn-primary">Assign</button>
				</div>
			</form:form>
		</div>
	</div>
</div>


<!-- Modal -->
<div id="updateTeacher" class="modal fade" role="dialog">
	<div class="modal-dialog">

		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<div class="modal-title">
					<span>Teacher Details</span>
				</div>
			</div>
			
			<form:form
				action="${pageContext.request.contextPath}/schoolAdmin/updateUser.htm"
				commandName="user" method="post" id="updateUserForm"
				class="form-horizontal" role="form">
				<div class="modal-body">
				<div class="col-md-12 col-sm-12 col-xs-12">
						<div class="row">
							<select name="id" id="teacherIds1" class="registration"
								required="required" style="margin-bottom: 10px;">
								<option value="">Select Teacher</option>
								<c:if test="${not empty teacherList}">
									<c:forEach var="teacher" items="${teacherList }">
										<option value="${teacher.id}">${teacher.firstName}&nbsp;${teacher.lastName }</option>
									</c:forEach>
								</c:if>
							</select>
						</div>
					</div>
					<table width="90%" border="0" cellspacing="0" cellpadding="0"
						class="topic-per table table-bordered table-hover table-condensed" style="display: none;" id="showHideUpdateUser">
						<tr>
							<td><strong>UserName :</strong></td>
							<td><span id="userNameIds"></span>
							</td>
						</tr>
					
						<tr>
							<td><strong>Name:</strong></td>
							<td><input type="text" placeholder="Enter Name" id="nameIds"
								required="required" name="firstName" onkeydown="return alphaOnly(event)" pattern="[a-zA-Z\s]+" maxlength="100"/></td>
						</tr>
						<tr>
							<td><strong>Email:</strong></td>
							<td><input type="email" placeholder="Enter Email" id="emailIds"
								name="emailId" required="required"/></td>
						</tr>
						<tr>
							<td><strong>Mobile No.:</strong></td>
							<td><input type="tel" placeholder="Enter Mobile No" id="mobileIds" name="mobileNo"
							 pattern="[0-9]{10,11}" required="required" maxlength="10" /></td>
						</tr>
						
					</table>



				</div>
				<div class="modal-footer">
					<input type="submit" class="btn btn-primary btn-md" id="updateUser" value="Update"/>
				</div>
			</form:form>
		</div>

	</div>
</div>
	

<!-- Modal -->
<div class="modal fade" id="addTeacher" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title" id="myModalLabel">Teacher Registration</h4>
			</div>
			<form:form name="teacherForm"
				action="${pageContext.request.contextPath}/schoolAdmin/studentTeacherRegistration.htm"
				commandName="user" method="post" id="userRegistration"
				class="form-horizontal" role="form">
				<div class="clearfix"></div>
				<div class="modal-body">
					<div class="col-md-12 col-sm-12 col-xs-12">
						<div class="row">
							<input type="text" name="firstName" id="firstname"
								onkeydown="return alphaOnly(event)" placeholder="Enter Name" pattern="^(?=.*[a-zA-Z]).+"
								required class="registration" maxlength="100" />
						</div>
					</div>

					<div class="col-md-12 col-sm-12 col-xs-12">
						<div class="row">
							<input type="text" name="userName" id="userName" pattern="^(?=.*[a-zA-Z]).+"
								placeholder="Enter UserName" required class="registration">
						</div>
					</div>
					<div class="col-md-12 col-sm-12 col-xs-12">
						<div class="row">
							<input type="password" name="password" id="lastname"
								placeholder="Enter password" required pattern=".{6,}"
								title="Six or More characters" class="registration">
						</div>
					</div>
					<div class="col-md-12 col-sm-12 col-xs-12">
						<div class="row">
							<input type="email" name="emailId" id="emailId"
								placeholder="Enter EmailId" class="registration">
						</div>
					</div>
					<div class="col-md-12 col-sm-12 col-xs-12">
						<div class="row">
							<input type="tel" pattern="[0-9]{10,11}" name="mobileNo"
								placeholder="Enter Mobile No." maxlength="11"
								class="registration">
						</div>
					</div>
					<input type="hidden" name="userType" value="7">
					<div class="clearfix"></div>
					<button type="submit" class="btn btn-primary btn-success">SUBMIT</button>
				</div>
			</form:form>
			<c:if test="${not empty key }">
				<c:if test="${key eq 1 }">
					<div class="msg" style="color: green">Student has been added
						successfully</div>

				</c:if>

				<c:if test="${key eq 2 }">
					<div class="msg" style="color: green">Teacher User has been
						added successfully ,Please assign Grade,section and subjects</div>

				</c:if>
				<c:if test="${key eq 3 }">
					<div class="msg" style="color: red">User Already Exist</div>

				</c:if>

				<c:remove var="key" scope="session" />
			</c:if>
		</div>

	</div>
</div>

<div id="studentDetailsListId"></div>

<script type="text/javascript">
	$(document)
			.ready(
					function() {
						setTimeout(function() {
							$(".msg").empty();

						}, 5000);

						var error = $(".msg").html();
						if (error != undefined) {
							$("#addTeacherId").trigger('click');
						}

						$("#addViewTeacher").addClass("active");
						$("#subjectteacherassignId").dataTable();
						$("#subjectteacherassignId").show();

						var isError = $("#errorData").val();
						if (isError == 'assignError') {
							$("#assignTeacherModelId").trigger("click");
							$("#errorId")
									.html(
											"This subject is already assign to this teacher in this section");
							setTimeout(function() {
								$("#errorId").html('');

							}, 5000);
						}
						
						$("#teacherIds1").on("change", function(){
							var teacherId=parseInt($(this).val());
							$("#showHideUpdateUser").hide();
							if($(this).val()!=""){
							<c:forEach var="teacher" items="${teacherList }">
							var id=parseInt("${teacher.id}");
							if(teacherId==id){
								
								$("#userNameIds").html("${teacher.userName}")
								$("#nameIds").val("${teacher.firstName}");
								$("#emailIds").val("${teacher.emailId}");
								$("#mobileIds").val("${teacher.mobileNo}");
							}
							$("#showHideUpdateUser").show();
						</c:forEach>
							}
						});
						
						
						$("#updateTeacherModelId").on("click", function(){
							$("#teacherIds1").val("");
							$("#showHideUpdateUser").hide();
						});
					});
</script>