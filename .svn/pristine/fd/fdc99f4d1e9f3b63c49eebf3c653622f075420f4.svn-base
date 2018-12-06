<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%@ page import="java.util.*" %>  
     <%@ page import="java.text.SimpleDateFormat" %> 
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<%
String currentDate = null;
Date currentDate1 = null;
 TimeZone istTimeZone = TimeZone.getTimeZone("Asia/Calcutta");

SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd h:mm:ss a Z"); 
sdf.setTimeZone(istTimeZone); 
currentDate = sdf.format(new Date()); 
System.out.println("date only.."+new Date());
out.println("date only.."+new Date());

System.out.println("date string..."+currentDate);
out.println("date string..."+currentDate);




SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd h:mm:ss a Z"); 
sdf1.setTimeZone(istTimeZone); 
currentDate1 = sdf1.parse(currentDate);

System.out.println("get in Date formate..."+currentDate1);
out.println("get in Date formate..."+currentDate1);



%>



</body>
</html>