<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Error in getting assessment</title>
</head>
<body>


<c:choose>
<c:when test="${error eq 1}">
<h3>Error in Requested Page</h3>
Error: Invalid Assessment!!
</c:when>
<c:when test="${error eq 2}">


<script>
location.href="${pageContext.request.contextPath}/student/assessmentSummary.htm?assessmentId=${testId}&subjectName=${subjectName}";
</script>

</c:when>
<c:when test="${error eq 3}">
Error: Assessment has not started yet!!

</c:when>
<c:when test="${error eq 4}">
Error: Assessment time has over!!
</c:when>
</c:choose>
</body>
</html>