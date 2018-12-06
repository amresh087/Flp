<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">
<head>
 <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %> 
 
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

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
    
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/dataTable.css">

    
      <!-- jQuery -->
<script src="${pageContext.request.contextPath}/resources/js/jquery.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/resources/js/jquery.circle-diagram.js"></script>
<%-- <script src="${pageContext.request.contextPath}/resources/js/student/main.js"></script> --%>

<script src="${pageContext.request.contextPath}/resources/js/common.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/resources/js/bootstrap.js" type="text/javascript"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.flexisel.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/jquery.mCustomScrollbar.concat.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.dataTables.js"></script>

<style type="text/css">

.dataTab 
		{
	    	background: #226a94 none repeat scroll 0 0;
	    	color: #fff;
		}</style>
</head>

<body>
<c:set var="commonPath" value="${sessionScope.user.staticRootPath}" scope="session"></c:set>
<!-- top header -->
<div class="container-fluid header-top-cont">
<div class="container">
<div class="row">
        <nav class="navbar navbar-inverse navbar-default" role="navigation">
           
            <div class="navbar-header hidden-xs">
              <!--   <p>Toll free: 1800 103 6768</p> -->
            </div>
            <!-- Top Menu Items -->
        
            <ul class="nav navbar-right top-nav">
            <li ><a href="#">${sessionScope.user.gradeName }</a></li>
          
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-user mrr10"></i> <c:if test="${not empty sessionScope.user}">
                    ${sessionScope.user.name}
                    </c:if> <b class="caret"></b></a>
                    <ul class="dropdown-menu">
                 
                        <li>
                            <a href="${pageContext.request.contextPath}/logout"><i class="fa fa-fw fa-power-off"></i> Log Out</a>
                        </li>
                    </ul>
                </li>
            </ul>
         
        </nav>
        </div>
        </div>
</div>
<!-- top header end-->

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
              <span class="sr-only">Toggle navigation</span>
              <span class="icon-bar"></span>
              <span class="icon-bar"></span>
              <span class="icon-bar"></span>
            </button>
              <a class="navbar-brand" href="#"><img src="${pageContext.request.contextPath}/resources/images/logo-home.png" alt="logo" class="img-responsive"/></a>
          </div>
          <div id="navbar" class="navbar-collapse collapse pull-right">
            <ul class="nav navbar-nav">
              <li  id="dashboardId"><a href="${pageContext.request.contextPath}/student/studentHome.htm"><span><img src="${pageContext.request.contextPath}/resources/images/dashboard.png" alt="logo"/></span>DashBoard </a></li>
              <li class="dropdown" id="subjectId">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown"><span><img src="${pageContext.request.contextPath}/resources/images/subjects.png" alt=""/></span>Subject</a>
                   <c:if test="${not empty sessionScope.user}">
                    <ul class="dropdown-menu">
                    
                    <c:forEach items="${sessionScope.user.subjects}" var="subject">
                    <li>
                            <a href="${pageContext.request.contextPath}/retailUser/subjectDetail.htm?sid=${subject.id}"><i class="fa fa-book"></i> ${subject.name}</a>
                        </li>
                    </c:forEach>
                    
                                     
                    </ul>
                     </c:if>      
                </li>
              <li id="reportsId" class="dropdown"><a href="javascript:void(0);" class="dropdown-toggle" data-toggle="dropdown"><span><img src="${pageContext.request.contextPath}/resources/images/reports.png" alt="logo"/></span>Reports</a>
             <ul class="dropdown-menu">
              <%-- <li><a href="${pageContext.request.contextPath}/student/testReports.htm">Assessment Reports</a></li> --%>
              <li><a href="${pageContext.request.contextPath}/student/learningReports.htm">Learning Reports</a></li>
              </ul> 
              
              </li>
              
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
<script>$.ajaxSetup({
    cache: false
});

$(document).ready(function(){
	<c:choose>
	<c:when test="${pageType eq '0' }">
	$("#dashboardId").addClass("nav-active");
	</c:when>
	<c:when test="${pageType eq '2' }">
	$("#reportsId").addClass("nav-active");</c:when>
	<c:otherwise>
	$("#subjectId").addClass("nav-active");</c:otherwise>

	</c:choose>
});

</script>