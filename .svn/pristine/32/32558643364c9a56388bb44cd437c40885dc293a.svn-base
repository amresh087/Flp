<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<jsp:include page="../student/studentHeader.jsp" />

<!-- Banner -->
<div class="container-fluid bred-cont">
	<div class="container">
<div class="row">
<ul class="breadcrumb">
<li>Student Assessment Reports</li>
</ul>
</div>
</div>
</div>
<div class="container-fluid">
	<div class="container">
<div class="table-responsive">
	<br>
	
	<table id="testReports" class="display " style="display: none;">
	<thead>
		<tr class="dataTab">
			<th>Test Name</th>
			<th>Maximum Marks</th>
			<th>Total Marks</th>
			<th>Total Questions</th>
			<th>Correct</th>
			<th>Incorrect</th>
			<th>Unattempted</th>
			<th>Topper's Score</th>
			<th>No. of students appeared</th>
			
		</tr>
		</thead>
		<tbody>
		<c:if test="${not empty assessment}">
			<c:forEach var="ass" items="${assessment}">
				
				
									
					



		
					
					
					
					<c:forEach var ="stuTest" items="${ass.assessment.studentTest}" varStatus="status">
					<c:if test="${stuTest.studentId eq sessionScope.user.id }">
					<tr>
					<td><a href="${pageContext.request.contextPath}/student/assessmentSummary.htm?assessmentId=${ass.assessment.id}&subjectName=${ass.assessment.subject.name}&&maxMarks=${ass.assessment.maxMarks}">${ass.assessment.testName}</a></td>
					<td>${ass.assessment.maxMarks}</td>
					<td><fmt:formatNumber pattern="#" value="${stuTest.marks}" /></td>
					<td>${stuTest.totalQuestion} </td>
					<td>${stuTest.totalCorrect} </td>
					<td>${stuTest.totalIncorrect}</td>
					<td>${stuTest.totalUnAttempted} </td>
					 <td><fmt:formatNumber pattern="#" value="${ass.maxMarks}" /></td> 
					<td>${ass.count}</td>
					</tr>
					</c:if>
					</c:forEach>
					
					
				
			</c:forEach>
		</c:if>
		</tbody>
	</table>
</div>
</div>
</div>

<script type="text/javascript">
$(document).ready(function()
    {
		 
		
	    $('#testReports').dataTable();
	    $('#testReports').show();
	    });
	    </script>
