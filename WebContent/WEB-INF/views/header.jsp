 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 <%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="images/favicon.ico">

    <title>Dashboard</title>

	<!-- Bootstrap core CSS -->

    <link href="${pageContext.request.contextPath}/resources/css/admin-custom.css" rel="stylesheet" type="text/css">
   <link href="${pageContext.request.contextPath}/resources/css/bootstrap.css" rel="stylesheet" type="text/css">
	<link href="${pageContext.request.contextPath}/resources/css/owl.carousel.css" rel="stylesheet" media="screen">
    <link href="${pageContext.request.contextPath}/resources/css/circle.css" rel="stylesheet" media="screen">
    <link href="${pageContext.request.contextPath}/resources/css/owl.theme.css" rel="stylesheet" media="screen">
    <link href="${pageContext.request.contextPath}/resources/css/build.css" rel="stylesheet" media="screen">
    <link href="${pageContext.request.contextPath}/resources/css/font-awesome.css" rel="stylesheet" media="screen">
   
   
    <!-- Jquery --> 
    <script src="${pageContext.request.contextPath}/resources/js/jquery-1.11.0.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/custom.js"></script>
   
<!--Pie Chart Start Here -->
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/canvasjs.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/piechart.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/common.js"></script>

<!--Pie Chart end Here -->    

	
	<!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
     <script src="${pageContext.request.contextPath}/resources/js/bootstrap.js"></script>
    <script src="${pageContext.request.contextPath}/resources/bootstrap-3.3.4/bootstrap-3.3.4/docs/assets/js/vendor/holder.js"></script>
    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <script src="bootstrap-3.3.4/bootstrap-3.3.4/docs/assets/js/ie10-viewport-bug-workaround.js"></script>
	
	

  
   <!-- jQuery Code executes on Date Format option ----->
   <%-- <script src="${pageContext.request.contextPath}/resources/js/script.js"></script> --%>
   
 <script src="${pageContext.request.contextPath}/resources/js/owl.carousel.js"></script> 
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/main.js"></script>
        
        
    <!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
    <!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->

    <!-- Custom styles for this template -->

  </head>
  <body>
  <c:set var="commonPath" value="${sessionScope.user.staticRootPath}" scope="session"></c:set>
		<!--top-bg-start-here-->
		<div class="container-fluid top-bg">
		<div class="container fix">
		<div class="row">
				
			<div class="col-md-12 col-xs-12">
				
				<div class="row">
				<div class="logo col-md-2 col-xs-8"  ><a href="#"><img src="${pageContext.request.contextPath}/resources/images/flp-logo.png" class="img-responsive " alt="Futuristic Learning" title="Futuristic Learning" /></a></div>
		     	<input type="hidden" id="contextPathId" value="${pageContext.request.contextPath}">
				<div class="col-md-7 col-sm-9">
				
				<div class="row">
				<nav class="navbar navbar-default  navigation">
  		<div class="row">
        <div class="container-fluid ">
    <!-- Brand and toggle get grouped for better mobile display -->
    <div class="navbar-header">
	
	
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
    </div>

    <!-- Collect the nav links, forms, and other content for toggling -->
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
		<div class="row">
	<c:if test="${not empty sessionScope.user}">
      <ul class="nav navbar-nav ">
        <li class="active"><a href="#">Dashboard <span class="sr-only">(current)</span></a></li>
        <li><a href="#">Learning Zone</a></li>
        <li>
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">Practice Zone<b class="caret"></b></a>
                    <ul class="dropdown-menu multi-level">
                    <li class="dropdown-submenu">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown">Have a Revision </a>
                            <ul class="dropdown-menu">
                                <li><a href="#">Unattempted</a></li>
                                   <li><a href="#">Wrongly Attemped </a></li>
                                    <li><a href="#">Correctly Attemped</a></li>
                            </ul>
                        </li>
                        <li><a href="#">Grade Yourself</a></li>
                       
                        
                    </ul>
                </li>
        
 		<li><a href="#">Reports</a></li>
        <li><a href="#">iPoints</a></li>
      
	    <li class="dropdown self-drop">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">Settings <span class="caret"></span></a>
          <ul class="dropdown-menu" role="menu">
            <li><a href="#">My Account</a></li>
			<li><a href="#">Edit Username</a></li>
            <li><a href="#">Change Password</a></li>
            <li><a href="#">Logout</a></li>
          </ul>
        </li>
        
	
      </ul>
      </c:if>
	</div>
    </div><!-- /.navbar-collapse -->
  </div>
  </div><!-- /.container-fluid -->
</nav>
						</div>
		</div>
		
		
		<div class="col-md-3 col-sm-3 user-top search  col-xs-12 text-right">
		
		<c:choose>
		<c:when test="${not empty sessionScope.user}">
		<p>${sessionScope.user.name} 
		<c:if test="${! empty sessionScope.user.profilePath }">
		<img src="${pageContext.request.contextPath}/resources/profileImage/${sessionScope.user.profilePath}" style="width: 35px;"/>
		</c:if>
		
		</p>  
	

		
		</c:when>
		<c:otherwise>
			<p><a href="#" onClick="regis()">Login / Register</a> <span class="glyphicon glyphicon-user"></span></p> 
		</c:otherwise>
		
		</c:choose>
		
       
	   
	   
	
	   </div>
	</div>
		
	</div>
</div>

</div>
</div>
<!--top-bg-end-here-->

  </body>
</html>
<script>
$.ajaxSetup({
    cache: false
});
</script>