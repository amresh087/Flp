<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<jsp:include page="schoolTeacherHeader.jsp" />
<div id="page-wrapper">

	<div class="container-fluid">

		<!-- Page Heading -->
		<div class="row">
			<div class="col-md-12">
				<h1>Assessment Report Details</h1>
				<ol class="breadcrumb">
					<li><a href="${pageContext.request.contextPath}/schoolTeacher/teacherHome.htm">Home</a></li>
					<li class="active">Assessment Report</li>


				</ol>
				</div>
				</div>
				<br/>
<div align="right"><a href="${pageContext.request.contextPath}/schoolTeacher/getLearningReports.htm" class="btn btn-success">Get Learning Report Details</a></div>
<br/>
	<!--assign-task-test-->
	<div id="assign-task" class="tab-pane active ">


		<div class="col-md-12 col-sm-12 col-xs-12 table-responsive">

			<table id="example" class="display">

				<thead>
					<tr class="dataTab">
						<th>S.No.</th>
						<th>Test Name</th>
						<th>Grade</th>
						<th>Assessment Type</th>
						<th>Created on</th>
						<th>Tested on</th>
						<th>Click to view</th>
					</tr>
				</thead>


				<tbody>


					<c:if test="${not empty assessmentList}">
						<c:forEach var="assessment" items="${ assessmentList}"
							varStatus="status">
							<tr>
								<td>${status.count }</td>
								<td>${assessment.testName }</td>
								<td>${assessment.grade.name }</td>
								<td><c:if test="${assessment.type eq 1 }">E-Test</c:if> <c:if
										test="${assessment.type eq 2 }">Assigned Test</c:if></td>
								<td><fmt:formatDate pattern="dd-MMM-yyyy"
										value="${assessment.createdOn}" /></td>
								<td><fmt:formatDate pattern="dd-MMM-yyyy"
										value="${assessment.startDate}" /></td>
								<td><a href="${pageContext.request.contextPath}/schoolTeacher/getAssessmentReportDetails?assessmentId=${assessment.id}&assessmentName=${assessment.testName }&mm=${ assessment.maxMarks}" class="assessmentDetailsCls" id="${assessment.id}" mm="${ assessment.maxMarks}">Get Reports</a></td>
							</tr>
						</c:forEach>

					</c:if>




				</tbody>
			</table>

		</div>
	</div>
</div>
</div>
<!-- Modal8 -->
<div id="assessmentDetailsId" class="modal fade" role="dialog">
	
</div>
<!-- Modal8 end -->
<script type="text/javascript">

/* $(".assessmentDetailsCls").on("click", function(){
	var assessmentId=$(this).attr('id');
	   $.post("${pageContext.request.contextPath}/schoolTeacher/getAssessmentReportDetails?assessmentId=" + assessmentId+"&mm="+$(this).attr('mm'), function(data)
			    {
		   $("#assessmentDetailsId").html(data);
			    });
}); */
</script>
