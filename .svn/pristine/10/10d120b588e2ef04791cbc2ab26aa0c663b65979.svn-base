<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<jsp:include page="../globalPage.jsp" />
	<br />
	<h2 class="text-center">Upcoming Assessment Details</h2>
	<div style="width: 90% !important; margin: 0px auto;">
		
		
		<br />
		<br />
		<table id="testDetails" class="display" style="display: none;">
			<thead>
				<tr>
					<th>S.No.</th>
					<th>Subject Name</th>
					<th>Grade Name</th>
					<!-- <th>Section</th> -->
					<th>Test Name</th>
					<th>Start Date</th>
					<th>Test Type</th>
				</tr>
			</thead>
			<tbody>
				<c:if test="${not empty upcomingTestList}">
					<c:forEach var="test" items="${upcomingTestList}" varStatus="status">
						<tr>
							<td>${status.count }</td>
							<td>${test.subject.name }</td>
							<td>${test.grade.name }</td>
							<%-- <td>${test.section.name }</td> --%>
							<td>${test.testName }</td>
							<td><fmt:formatDate pattern="dd-MMM-yyyy"
									value="${test.endDate}" /></td>
									<td><c:if test="${test.type eq 1 }">E-Test</c:if>
									<c:if test="${test.type eq 2 }">Assigned</c:if>
									</td>
						</tr>
					</c:forEach>

				</c:if>

			</tbody>
		</table>
	</div>
	
	<script type="text/javascript">
	$.ajaxSetup({
	    cache: false
	});
		$(document).ready(function() {
			$('#testDetails').dataTable();
			$('#testDetails').show();
			
		});
	</script>
