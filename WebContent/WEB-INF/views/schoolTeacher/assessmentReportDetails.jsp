<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<jsp:include page="schoolTeacherHeader.jsp" />
<style type="text/css">
<!--
strong {
    color: #6f6f6f;
    font-size: 14px;
}
-->
</style>
<div id="page-wrapper">

	<div class="container-fluid">

		<!-- Page Heading -->
		<div class="row">
			<div class="col-md-12">
				<h1>Student Assessment Details </h1>
				<ol class="breadcrumb">
					<li><a
						href="${pageContext.request.contextPath}/schoolTeacher/teacherHome.htm">Home</a></li>
					<li class="active"><a href="${pageContext.request.contextPath}/schoolTeacher/getAssessmentReports.htm">Assessment Report</a></li>
					<li class="active" id="assNameId">${assessmentName }</li>

				</ol>
			</div>
		</div>
		<br />
		<!--assign-task-test-->
		<div id="assign-task" class="tab-pane active ">


			<div class="col-md-12 col-sm-12 col-xs-12 table-responsive">
				<table class="table table-striped table-bordered ">
					<thead>
						<tr class="dataTab">
							<td>S.No.</td>
							<td>Student Name</td>
							<!-- <td>Test Name</td> -->
							<td>Test Attempted Date</td>
							<td>Marks Obtained</td>
							<td>Maximum Marks</td>
							<td>Test Summary</td>


						</tr>
					</thead>
					<tbody>
						<c:choose>
							<c:when test="${not empty studentAssessmentDetais}">
								<c:forEach var="details" items="${studentAssessmentDetais }"
									varStatus="status">

									<tr>
										<td>${status.count }</td>
										<td id="nameCls${details.user.id }">${details.user.firstName }</td>
										<%-- <td>${assessmentName }</td> --%>
										<td id="testAttemptCls${details.user.id }"><fmt:formatDate pattern="dd-MMM-yyyy"
										value="${details.attemptDate}" /></td>
										<td><fmt:formatNumber maxIntegerDigits="3"
												value="${details.marks}" /></td>

										<td>${maxMarks }</td>
										<td><a href="javascript:void(0)" class="btn btn-success getAssessmentSummaryCls"  data-toggle="modal" data-target="#assessmentDetailsId" id="${details.user.id }">View Test Summary</a></td>

									</tr>
								</c:forEach>
							</c:when>

							<c:otherwise>
								<tr>
									<td colspan="8">No Data Found !!!</td>
								</tr>
							</c:otherwise>
						</c:choose>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</div>
<!-- Modal8 -->
<div id="assessmentDetailsId" class="modal fade" role="dialog">
	<div class="modal-dialog student_ass_con" style="width:85%!important;">
<div class="modal-content" >
		<div class="modal-header" style="height:120px;">
			<button type="button" class="close" data-dismiss="modal">&times;</button>
			<div class="modal-title">
				<span>Student</span> Test Summary
			</div>
		
				<div class="col-md-12" style="margin-top:47px">
			<div class="col-md-5"><strong>Student Name:</strong> <span id="stuNameId"></span></div>
			<div class="col-md-4"><strong>Test Name:</strong> <span id="testNameId"></span></div>
		    <div class="col-md-3"><strong>Test Attempted Date:</strong> <span id="attemptedDateId"></span></div>
		</div>
		</div>
		
	
		<div class="modal-body" id="assSummary" style="display: none;">
		
<%-- 		<div class="row">
		<div class="table-responsive">
		<div style="float:right; margin-right:10px;"><a href="#">Download <img src="${pageContext.request.contextPath}/resources/images/pdf.png"></a></div>
				<table border="0" cellspacing="0" cellpadding="0"
					class="table table-bordered text-center table-head">
					<tr style="background-color:#FF7E00; color:#ffffff;">
						<td>Total Questions</td>
						<td>Correct</td>
						<td>Incorrect</td>
						<td>Skipped</td>

						<td>Marks Obtained</td>
						<td>Average Marks</td>
						<td>Maximum Marks</td>
						<td>Percentage</td>
						<td>Topper's Score</td>
						<td>No. of Students who took the test</td>

					</tr>
					<tr>
						<td>${studentAssessmentSummary.totalQuestion }</td>
						<td>${studentAssessmentSummary.totalCorrect }</td>
						<td>${studentAssessmentSummary.totalIncorrect }</td>
						<td>${studentAssessmentSummary.totalUnAttempted }</td>
						<td><fmt:formatNumber pattern="#"
								value="${studentAssessmentSummary.marks}" /></td>

<td><fmt:formatNumber pattern="#.##"
								value="${avgMarks}" /></td>
						<td>${param.maxMarks }</td>
						<td><fmt:formatNumber pattern="#"
								value="${(studentAssessmentSummary.marks/param.maxMarks)*100}" /></td>





						<td><fmt:formatNumber pattern="#"
								value="${maxAssessmentMarks}" /></td>
						<td>${TotalStudentAttempt }</td>

					</tr>
				</table>
			</div>
			
		</div> --%>
			
		</div>

	</div>

</div>
</div>

<!-- Modal8 end -->

<script type="text/javascript">
	$(".getAssessmentSummaryCls").click( function(){
		var stuId=$(this).attr('id');
		
		$("#stuNameId").html($("#nameCls"+stuId).html());
		$("#testNameId").html($("#assNameId").html())
		$("#attemptedDateId").html($("#testAttemptCls"+stuId).html())
		  $.get("${pageContext.request.contextPath}/schoolTeacher/stuAssessmentSummary?assessmentId=${assessmentId}&stuId="+stuId, function(data)
				    {
			   $("#assSummary").html(data);
			  
				    });
		  
		  $("#assSummary").show();
		
	});
</script>