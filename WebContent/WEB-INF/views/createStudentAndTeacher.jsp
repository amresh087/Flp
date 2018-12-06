<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script
	src="${pageContext.request.contextPath}/resources/js/jquery-1.11.0.min.js"></script>

</head>
<body>
	<%-- 
<form:form method="get" action="${pageContext.request.contextPath}/createStudentAndTeacher.htm" commandName="user">
	First Name:<input type="text" name="fname"/><br>
	Last Name:<input type="text" name="lname"/><br>
	Address: <input type="text" name="address"/></br>
	City: <input type="text" name="city"/></br>
	Phone No:<input type="text" name="pno"/></br>
	Email Id:<input type="text" name="name" value=""/><br>
	Password:<input type="password" name="pass" value=""/></br>
	Type:<select name="select">
			<option value="2">Student</option>
			<option value="7">Teacher</option>
	
	
	</select>
	
	<input type="submit" value="Submit">



</form:form>
 --%>
	<form:form method="get"
		action="${pageContext.request.contextPath}/createStudentAndTeacher.htm"
		commandName="user">
 
First Name:<input type="text" name="firstName" id="firstname"
			placeholder="Enter your first name" required />
		<br>
		<br>
			Last Name:<input type="text" name="lastName" id="lasttname"
			placeholder="Enter your last name" required />
		<br>
		<br>
			Address:<input type="text" name="address" id="address"
			placeholder="Enter your  address" />
		<br>
		<br>
						Email ID:<input type="email" name="emailId" id="lastname"
			placeholder="Please enter your email id" required>
		<br>
		<br>
								Password:<input type="password" name="password" id="lastname"
			placeholder="Enter your password" required pattern=".{6,}"
			title="Six or More characters">
		<br>
		<br>
								Phone No:<input type="tel" id="phono" pattern="[0-9]{10,11}"
			name="mobileNo" placeholder="Please enter your mobile no." required
			maxlength="11">
		<br>
		<br>
							Type:<select name="userType">
			<option value="2">Student</option>
			<option value="7">Teacher</option>


		</select>
		<br>
		<br>


		<button type="submit">SUBMIT</button>
	</form:form>

	<c:if test="${not empty listUser }">
		<table border="2" class="display">
			<tr>
				<td>S.No</td>
				<td>User Name</td>
				<td>Role</td>
				<td>Adress</td>
				<td>Phone NO</td>
				
				
			</tr>

			<c:forEach items="${listUser}" var="user" varStatus="status">
				<tr>
					<td>${status.count }</td>
					<td>${user.emailId }</td>
					<td><c:if test="${user.userType eq 2 }">Student</c:if>
					<c:if test="${user.userType eq 7 }">Teacher</c:if>
					<c:if test="${user.userType eq 5 }">School Admin</c:if>
					</td>
					
								<td>${user.address} </td>
								<td>${user.mobileNo}</td>
				</tr>			</c:forEach>
			
		</table>
	</c:if>

	<h1>
		<c:if test="${not empty key }">

<div id="remove">
${key}</div>
<c:remove var="key" scope="session" />
		</c:if>
	</h1>
</body>


<script type="text/javascript">


$(document).ready(function () {

	setTimeout(function() {
	    $('#remove').hide();
	}, 5000);
});




<!--

//-->
</script>

</html>

