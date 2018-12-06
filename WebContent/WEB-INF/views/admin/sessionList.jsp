<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<jsp:include page="../globalPage.jsp" />
<h2 class="text-center">Session Information</h2>
<div style="width: 90% !important; margin: 0px auto;">
	<button class="btn btn-success " data-toggle="modal"
		data-target="#newSession" id="addSchoolId">Add New</button>
	<table id="sessionListId" class='display' style="display: none;">
		<thead>
			<tr>
				<th>S.No.</th>
				<th>Session Name</th>
				<th>Start Date</th>
				<th>End Date</th>
				<th>Status</th>
			</tr>
		</thead>
		<tbody>
			<c:if test="${not empty sessionList }">
				<c:forEach var="session" items="${sessionList }" varStatus="status">
					<tr>
						<td>${status.count }</td>
						<td>${session.sessionName }</td>
						<td><fmt:formatDate pattern="dd-MMM-yyyy"
								value="${session.startDate}" /></td>
						<td><fmt:formatDate pattern="dd-MMM-yyyy"
								value="${session.endDate}" /></td>
						<td><c:if test="${session.status eq 1 }">Active</c:if> <c:if
								test="${session.status eq 0 }">Inactive</c:if></td>
					</tr>
				</c:forEach>
			</c:if>
		</tbody>
	</table>
</div>
<!-- Modal -->
<div class="modal fade" id="newSession" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title" id="myModalLabel">Session Detail</h4>
			</div>
			<form:form
				action="${pageContext.request.contextPath}/admin/addSession.htm"
				commandName="session" method="post" id="addSessionId"
				class="form-horizontal" role="form">
				<div class="modal-body">
					<div class="col-md-12 col-sm-12 col-xs-12">
						<div class="row">
							<input type="text" name="sessionName" id="sessionName"
								placeholder="Enter Session Name" required class="registration" />
						</div>
					</div>
					<div class="col-md-12 col-sm-12 col-xs-12">
						<div class="row">
							<input type="text" name="sessionStartDate" id="startDate"
								placeholder="Session Start Date(dd-MM-yyyy)" required
								class="registration" />
						</div>
					</div>
					<div class="col-md-12 col-sm-12 col-xs-12">
						<div class="row">
							<input type="text" name="sessionEndDate" id="endDate"
								placeholder="Session End Date(dd-MM-yyyy)" required
								class="registration" />
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="submit" class="btn btn-primary">Add</button>
				</div>
			</form:form>
		</div>
	</div>
</div>
<script type="text/javascript">
	$(document).ready(function() {
		$("#sessionListId").dataTable();
		$("#sessionListId").show();
		$("#sessionLst").addClass("active");
	});
</script>