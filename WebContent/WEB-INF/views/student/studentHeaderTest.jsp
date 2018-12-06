<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html lang="en">
<head>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="icon" href="${pageContext.request.contextPath}/resources/images/favicon.png">
<title>IperformKonnect</title>
<!-- Bootstrap Core CSS -->
<link href="${pageContext.request.contextPath}/resources/css/student/bootstrap.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/resources/css/student/sb-admin.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/resources/css/student/custom.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/resources/css/student/media.css" rel="stylesheet">
<!-- custom scrollbar  -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/student/jquery.mCustomScrollbar.css">
<!-- Custom Fonts -->
<link href="${pageContext.request.contextPath}/resources/font-awesome/css/font-awesome.css" rel="stylesheet" type="text/css">




<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/dataTable.css">


<!-- jQuery -->
<script src="${pageContext.request.contextPath}/resources/js/jquery.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/resources/js/jquery.circle-diagram.js"></script>
<%-- <script src="${pageContext.request.contextPath}/resources/js/student/main.js"></script> --%>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/student/jquery.autocomplete.min.js"></script>


<script src="${pageContext.request.contextPath}/resources/js/common.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/resources/js/bootstrap.js" type="text/javascript"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.flexisel.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/jquery.mCustomScrollbar.concat.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.dataTables.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/student/aim.js"></script>

<style type="text/css">
.dataTab {
	background: #226a94 none repeat scroll 0 0;
	color: #fff;
}
.nav-active{ color: #fff !important;}
</style>

</head>

<body>

	<c:set var="commonPath" value="${sessionScope.user.staticRootPath}" scope="session"></c:set>
	<!-- header panel -->
		<div class="container-fluid nav-cont">
		<div class="container">
		
			<div class="col-md-12 col-sm-12 col-xs-12 ">
				<div class="row">

					<!-- Navigation  -->
					<div class="row">
						<nav class="navbar navbar-default">

							<div class="navbar-header">
								<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar ">
									<span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span>
								</button>
								
								<a class="navbar-brand" href="${pageContext.request.contextPath}/student/studentHome.htm" style="padding-top:5px; padding-bottom:1px;">
									<img src="${pageContext.request.contextPath}/resources/images/logo-home.png" alt="logo" class="img-responsive" />
								</a>
							</div>
							<div id="navbar" class="navbar-collapse collapse pull-right" style="margin-top:25px">
							
								<ul class="nav navbar-nav">
									<c:choose>
										<c:when test="${sessionScope.user.userType eq 2 && not empty sessionScope.user.schoolId }">
											<li id="dashboardId">
												<a href="${pageContext.request.contextPath}/student/studentHome.htm">
													<%-- <span> <img src="${pageContext.request.contextPath}/resources/images/dashboard.png" alt="logo"  style="max-width:100%;"/> 
													</span> --%>DashBoard
												</a>
											</li>
											
											
										</c:when>
										<c:otherwise>
											
											
											
										</c:otherwise>
									</c:choose>
								</ul>

							</div>

						</nav>
					</div>
					<!-- Navigation end -->
				</div>
			</div>
		</div>
	</div>





	<!-- header panel end -->
	<script>
		$.ajaxSetup(
        {
	        cache : false
        });
        
        $(document).ready(function()
        {
	        <c:choose>
	        <c:when test="${pageType eq '0' }">
	        $("#dashboardId").addClass("nav-active");
	        </c:when>
	        
	        <c:otherwise>
	     
	        </c:otherwise>

	        </c:choose>
        });
        
   
	</script>