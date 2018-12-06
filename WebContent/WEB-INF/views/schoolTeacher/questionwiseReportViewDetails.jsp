<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<!-- Modal content for Student Learning Report  -->
<!-- <div id="myModal" class="modal fade" role="dialog"> -->
	<div class="modal-dialog" style="width:65%;">

		<!-- Modal content-->
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<div class="modal-title">
					<span>Student Reports</span> on the Topic
				</div>
			</div>
			<div class="modal-body">
			<div class="table-responsive">
			<table border="0" cellspacing="0" cellpadding="0" class="topic-per table table-bordered table-hover table-condensed table-responsive">
				<thead>
					<tr>
						<th>S.No.</th>
						<th>Student Name</th>
						<th>Total Questions</th>
						<th>Total Attempted</th>
						<th>Total Correct</th>
						<th>Total Incorrect</th>
						<th>Total Skipped</th>
						<th>Percentage(%)</th>
					</tr>
				</thead>
				<tbody>
				<c:choose>
				<c:when test="${not empty studentReports }">
				<c:forEach var='studentReport' items="${studentReports}" varStatus="status">
								<tr>
									<td>${status.count }</td>
									<td>${studentReport.user.firstName}</td>
									<td>${studentReport.totalQuestionCount}</td>
									<td> ${studentReport.totalQuestionAttempted}</td>
									<td>${studentReport.correctCount}</td>
									<td>${studentReport.incorrectCount}</td>
									<td>${studentReport.skipCount}</td>
									<td><%-- <th> <svg id="container${count}"></svg></th>  --%>
								 <div class="progress progress-striped " style="height:20px;">
									<div id="active${count}" class="bar" style="height:20px;width:${studentReport.percentage}%;background-color:green; color:black;">${studentReport.percentage}%</div> </div>
							</td>
								</tr>
								 
<script type="text/javascript">
      var progress = $("#active${count}").Progress({
        percent:'${studentReport.percentage}'
       
      });
</script>
				</c:forEach>
				 </c:when>
				 <c:otherwise>
							<tr>
								<td colspan="7">No Record Found !!!</td>
							</tr>
				 </c:otherwise> 
 
				 </c:choose>
				 

				</tbody>

			</table>
			</div>
			</div>

		</div>

	</div>
