<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<jsp:include page="../globalPage.jsp" />
<h2 class="text-center">School Admin Information</h2>
	<div style="width: 90% !important; margin: 0px auto;">
		<button class="btn btn-success " data-toggle="modal"
			data-target="#newAdmin" id="addSchoolId">Add New</button>
			
			<table id="adminListId" class="display" style="display: none;">
			<thead>
				<tr>
					<th>S.No.</th>
					<th>Admin Name</th>
					<th>Address</th>
					<th>Username</th>
					<th>Password</th>
					<th>Contact No.</th>
					
					<th>School</th>
					<th>School Address</th>
					
				</tr>
			</thead>
			<tbody>
			<c:if test="${not empty adminList}">
			<c:forEach var="admin" items="${adminList}" varStatus="status">
			<tr>
			<td>${status.count}.</td>
			<td>${admin.firstName }</td>
			<td><c:if test="${not empty admin.address }">${admin.address }</c:if>
			<c:if test="${empty admin.address }">N/A</c:if>
			</td>
			<td>${admin.userName }</td>
			<td>${admin.password }</td>
			<td>${admin.mobileNo }</td>
			<td>${admin.school.schoolName }</td>
			<td>${admin.school.address },${admin.school.city }</td>
			</tr>
			</c:forEach>
			</c:if>
			
			</tbody>
			</table>
			
			
			</div>
			
			
			
			<!-- Modal -->
	<div class="modal fade" id="newAdmin" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">School Admin Detail</h4>
				</div>
				<form:form action="${pageContext.request.contextPath}/admin/saveAdminRegistration.htm"
								commandName="user" method="post" id="adminRegistration"
								class="form-horizontal" role="form">
					<div class="modal-body">
						<div class="col-md-12 col-sm-12 col-xs-12">
							<div class="row">
								<input type="text" name="firstName" id="firstname"
									placeholder="Enter your  name" required class="registration"/>
							</div>
						</div>
						<div class="col-md-12 col-sm-12 col-xs-12">
							<div class="row">
								<input type="email" name="emailId" id="lastname"
									placeholder="Please enter your email id" required class="registration">
							</div>
						</div>
						<div class="col-md-12 col-sm-12 col-xs-12">
							<div class="row">
								<input type="password" name="password" id="lastname"
									placeholder="Enter your password" required  pattern=".{6,}" title="Six or More characters" class="registration">
							</div>
						</div>
						<div class="col-md-12 col-sm-12 col-xs-12">
							<div class="row">
								<input type="tel" id="lastname" pattern="[0-9]{10,11}"
									name="mobileNo" placeholder="Please enter your mobile no."
									required maxlength="11" class="registration">
							</div>
						</div>
						<div class="col-md-12 col-sm-12 col-xs-12">
							<div class="row">
								<select name="schoolId" required="required" class="registration">
								<option value="">Select School</option>
								<c:if test="${not empty schoolList }">
								<c:forEach var="school" items="${schoolList }">
								<option value="${school.id }">${school.schoolName } (${school.contactEmail})</option>
								</c:forEach>
								</c:if>
								</select>
							</div>
						</div>
						
					</div>
					<div class="modal-footer">
						<button type="submit" class="btn btn-primary" >Add</button>
					</div>
				</form:form>
			</div>
		</div>
	</div>
	<script type="text/javascript">
	$(document).ready(function() {
		
		<c:if test="${not empty alreadyExits}">
		$("#addSchoolId").trigger("click");
		alert("Email-Id Already Exists !!!");
		
		<c:remove var="alreadyExits" scope="session"/>
		</c:if>
		
			$('#adminListId').dataTable();
			$("#adminListId").show();
			$("#schoolAdminLst").addClass("active");
	});
			</script>