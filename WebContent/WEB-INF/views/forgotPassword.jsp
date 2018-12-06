<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 <script src="${pageContext.request.contextPath}/resources/js/jquery-1.11.0.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/common.js"></script>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	

	<input type="hidden" value="${forgotPwdUserId}" id="forgotPwduserId"/>
	<c:remove var="forgotPwdUserId" scope="session"/>
	<input type="text" placeholder="Password" id="firstPassword"/>
	<input type="text" placeholder="Re-Enter Password" id="confirmPassword"/>
	<a href="javascript:void(0);" id="resetPasswordId">Reset Password</a>
</body>
</html>