  
<!DOCTYPE>
<html>
<head>
<title>HelloWorld page</title>
<script src="${pageContext.request.contextPath}/resources/js/jquery-1.11.0.min.js"></script>
<script type="text/javascript">

$(document).ready(function(){
	$("#userSearchId").on("keyup", function(){
		var searchContent=$(this).val();
		var userType="3";
		
		/* $.ajax({
			type : "GET",
			url : "${pageContext.request.contextPath}/searchUser/"+userType+"/"+searchContent+".htm",
			data :"text", 
			contentType : 'application/json',
			success : function(data) {
				console.log(datea);
			}
		}); */
		
		
		
		 $.post("${pageContext.request.contextPath}/searchUser/"+userType+"/"+searchContent,function(data) {alert(data.length);
		},"json"); 
	})
});
</script>
</head>
<body>
<%@ include file="header.jsp" %>
<br/>

	Greeting : ${greeting}
	
	<br/>
	<a href="${pageContext.request.contextPath}/userSignup.htm">Sign Up</a>

	<br>
		<a href="${pageContext.request.contextPath}/userLoginPage.htm">Login</a>

	
	Search Parent<input type="text" id="userSearchId"/> 
	<br/>
	<%@ include file="footer.jsp" %>
	
	

</body>
</html>


