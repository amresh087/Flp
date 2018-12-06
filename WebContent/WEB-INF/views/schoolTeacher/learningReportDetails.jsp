<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- Modal5 -->

<div id="learningReport" class="modal fade" role="dialog">
	<div class="modal-dialog">

		<!-- Modal content-->
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<div class="modal-title">
					<span>Status</span> Not Assigned
				</div>
			</div>
			<div class="modal-body"></div>

		</div>

	</div>
</div>
<!-- Modal5 end -->


<a href="javascript:void(0);" style="display: none;"
	id="getStudentReportId" data-target="#reportModelId"
	data-toggle="modal"></a>
<!-- Modal8 -->
<div id="reportModelId" class="modal fade" role="dialog">
	<div class="modal-dialog"></div>
</div>
<!-- Modal8 end -->
<a href="#" data-toggle="modal" data-target="#learningReport"
	id="commonReportsId" style="display: none;"></a>
<div class="col-md-12 col-sm-12 col-xs-12">
	<c:choose>
		<c:when test="${reportType eq 'reportsBySubject' }">
			<br />
			<h3 align="center"
				style="background: #ccc; height: 30px; padding-top: 5px">
				<b>Subject-wise Reports</b>
			</h3>
			<br />
			<br />
			<table id="example" class="display" style="display: none;">
				<thead>
					<tr class="dataTab">
						<th>S.No.</th>
						<th>Subject Name</th>
						<th>Question Count</th>
						<th>Click to view</th>
					</tr>
				</thead>

				<tbody>

					<c:forEach var="item" items="${reportListBySubject }"
						varStatus="status">
						<tr>
							<td>${status.count }</td>
							<td>${item.subject.name }</td>
							<td><span id="totalQuestionCountId${status.count }">${item.subject.count }</span></td>
							<td><c:choose>
									<c:when test="${item.subject.count gt 0 }">
										<a href="javascript:void(0);"
											onclick="getSubjectWiseReport(${item.subject.id },'subjectwise',${status.count })">Get
											Reports</a>
									</c:when>
									<c:otherwise>N/A</c:otherwise>
								</c:choose></td>
						</tr>

					</c:forEach>

				</tbody>
			</table>
		</c:when>

		<c:when test="${reportType eq 'reportsByChapter' }">
			<br />
			<h3 align="center"
				style="background: #ccc; height: 30px; padding-top: 5px">
				<b>Chapter-wise Reports</b>
			</h3>
			<br />
			<br />
			<table id="example" class="display" style="display: none;">
				<thead>
					<tr class="dataTab">
						<th>S.No.</th>
						<th>Chapter Name</th>
						<th>Question Count</th>
						<th>Action</th>
					</tr>
				</thead>

				<tbody>

					<c:forEach var="item" items="${reportListBySubject }"
						varStatus="status">
						<tr>
							<td>${status.count }</td>
							<td>${item.name }</td>
							<td><span id="totalQuestionCountId${status.count }">${item.count }</span></td>
							<td><c:choose>
									<c:when test="${item.count gt 0 }">
										<a href="javascript:void(0);"
											onclick="getSubjectWiseReport(${item.id },'chapterwise',${status.count })">Get
											Reports</a>
									</c:when>
									<c:otherwise>N/A</c:otherwise>
								</c:choose></td>
						</tr>

					</c:forEach>

				</tbody>

			</table>
		</c:when>
		<c:when test="${reportType eq 'reportsByTopic' }">
			<br />
			<h3 align="center"
				style="background: #ccc; height: 30px; padding-top: 5px;">
				<b>Topic-wise Reports</b>
			</h3>
			<br />
			<br />
			<table id="example" class="display" style="display: none;">
				<thead>
					<tr class="dataTab">
						<th>S.No.</th>
						<th>Topic Name</th>
						<th>Question Count</th>
						<th>Action</th>
					</tr>
				</thead>

				<tbody>

					<c:forEach var="item" items="${reportListBySubject }"
						varStatus="status">
						<tr>
							<td>${status.count }</td>
							<td>${item.name }</td>
							<td><span id="totalQuestionCountId${status.count }">${item.count }</span></td>
							<td><c:choose>
									<c:when test="${item.count gt 0 }">
										<a href="javascript:void(0);"
											onclick="getSubjectWiseReport(${item.id },'topicwise',${status.count })">Get
											Reports</a>
									</c:when>
									<c:otherwise>N/A</c:otherwise>
								</c:choose></td>
						</tr>

					</c:forEach>

				</tbody>

			</table>
		</c:when>
	</c:choose>

</div>



<script type="text/javascript">
$(document).ready(function(){
	$("#example").dataTable();
	$("#example").show();
});

function getSubjectWiseReport(id,reportType,count){
	var gradeId=$("#gradeId1").val();
	var sectionId=$("#sectionId").val();
	var quesCount=$("#totalQuestionCountId"+count).html();
	if(reportType=="subjectwise"){
	var url="${pageContext.request.contextPath}/schoolTeacher/getStudentwiseReport?gradeId=" + gradeId + "&sectionId=" + sectionId+ "&subjectId=" + id+ "&reportType="+reportType+"&questionCount="+quesCount;
	}
	else if(reportType=="chapterwise"){
		var subjectId=$("#subjectId").val();
		var url="${pageContext.request.contextPath}/schoolTeacher/getStudentwiseReport?gradeId=" + gradeId + "&sectionId=" + sectionId+ "&subjectId=" + subjectId+ "&reportType="+reportType+"&chapterId="+id+"&questionCount="+quesCount;
	}else{
		var subjectId=$("#subjectId").val();
		var url="${pageContext.request.contextPath}/schoolTeacher/getStudentwiseReport?gradeId=" + gradeId + "&sectionId=" + sectionId+ "&subjectId=" + subjectId+ "&reportType="+reportType+"&topicId="+id+"&questionCount="+quesCount;	
	}
	$.post(url, function(data)
		    {
		$("#reportModelId").html(data);
		$("#getStudentReportId").trigger("click");
		$(".totalQuestionId").html($("#totalQuestionCountId"+count).html());
		    }); 
}
</script>