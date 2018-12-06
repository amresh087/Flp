<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    
<!-- Modal content-->
<div class="modal-content get-subhject" >
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal">&times;</button>
		<div class="modal-title">Student Learning Report</div>
	</div>
	<div class="modal-body">
		 <div class="col-md-12 col-sm-12 col-xs-12">
		 <div class="table-responsive">
			<table id="reportsId" class="example table table-responsive">
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
							<c:forEach var="report" items="${studentReports }"
								varStatus="status">
								<tr>
									<td>${status.count }</td>
									<td>${report.user.firstName }</td>
									<td><span class="totalQuestionId"></span></td>
									<td>${report.correctCount+report.incorrectCount+report.skipCount }</td>
									<td>${report.correctCount }</td>
									<td>${report.incorrectCount}</td>
									<td>${report.skipCount }</td>
									<th> <svg id="container${status.count }"></svg></th>
								</tr>
 <script type="text/javascript">
      var progress = $("#container${status.count}").Progress({
        percent: '${report.percentage}'
       
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
		<div class="clearfix"></div>
	</div>

</div>