<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<jsp:include page="../globalPage.jsp" />
	<br />
	<h2 class="text-center">Content Library</h2>
	<div style="width: 90% !important; margin: 0px auto;">
		<button class="btn btn-success " data-toggle="modal"
			data-target="#editGradeModel" >Edit Grade</button>
		
		<br />
		<br />
		<table id="gradeDetails" class="display" style="display: none;">
			<thead>
				<tr class="dataTab">
					<th>S.No.</th>
					<th>Grade Name</th>
					<th>Section</th>
					<th>Subject</th>
					<th>Action</th>
					
				</tr>
			</thead>
			<tbody>
				<c:if test="${not empty gradeList}">
					
						<tr>
							<td>1.</td>
							<td>Grade 1</td>
							<td>Section A</td>
							<td>English</td>
							<td>Remove</td>
						</tr>
					<tr>
							<td>2.</td>
							<td>Grade 1</td>
							<td>Section B</td>
							<td>Hindi</td>
							<td>Remove</td>
						</tr>
<tr>
							<td>2.</td>
							<td>Grade 2</td>
							<td>Section A</td>
							<td>Mathematics</td>
							<td>Remove</td>
						</tr>
				</c:if>

			</tbody>
		</table>
	</div>
		<!-- Modal -->
	<div class="modal fade" id="editGradeModel" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">Admin Detail</h4>
				</div>
				
			</div>
		</div>
	</div>

	<script type="text/javascript">
		$(document).ready(function() {
			$('#gradeDetails').dataTable();
			$('#gradeDetails').show();
            $("#schoolToc").addClass("active");
			
		});
	</script>
