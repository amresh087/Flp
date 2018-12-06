
<html>
<head>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<script
	src="https://ajax.googleapis.com/ajax/libs/angularjs/1.3.2/angular.min.js"></script>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/dateTime/jquery.datetimepicker.css" />
<script
	src="${pageContext.request.contextPath}/resources/dateTime/jquery.datetimepicker.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Preview and Edit Assessment</title>
</head>
<body>
	<div id="preview" class="tab-pane fade">
							<br />
							<br />
							<div class="col-md-4 mrt15">
								Test Date : ${assessment.endDate.getTime()} <br> Time : ${assessment.duration}
							</div>

							<div class="col-md-4 txt-cen">
								<h3>${assessment.testName}</h3>
								<span id="modelGrade"></span>
							</div>

							<div class="col-md-4 txt-right mrt15 ">
									Test Type : ${assessment.type} <br> Total Question : <span
									id="totalQuestion"></span><br> Maximum Marks : <span
									id="totalMarks"></span> <br>
							</div>

							<div class="col-md-12 mrt15">
								<h4>General Instructions:</h4>
								${assessment.instruction} <br>
								<div id="questionAnswer"></div>
	</div>
	</div>
</body>
</html>