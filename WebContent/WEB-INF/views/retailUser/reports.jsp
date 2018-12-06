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
			<th>Total Questions</th>
			<th>Correct</th>
			<th>Incorrect</th>
			<th>Unattempted</th>
			
		</tr>
		</thead>
		<tbody>
		<c:if test="${not empty testDetails}">
			<c:forEach var="td" items="${testDetails}">
				<tr>
					<td>${td.name}</td>
					<td>${td.totalQuestion} </td>
					<td>${td.totalCorrect} </td>
					<td>${td.totalIncorrect}</td>
					<td>${td.totalUnAttempted} </td>
				</tr>
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
