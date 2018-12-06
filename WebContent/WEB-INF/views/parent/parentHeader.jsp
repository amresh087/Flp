<!DOCTYPE html>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="en">
<head>

    <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

    <title>Parent Home</title>
    <!-- Bootstrap Core CSS -->
     <link href="${pageContext.request.contextPath}/resources/css/bootstrap.css" rel="stylesheet">
     <link href="${pageContext.request.contextPath}/resources/css/sb-admin.css" rel="stylesheet">
     <link href="${pageContext.request.contextPath}/resources/css/parent/custom.css" rel="stylesheet">
     <link href="${pageContext.request.contextPath}/resources/css/parent/media.css" rel="stylesheet">
    
    <!-- Custom Fonts -->
    <link href="${pageContext.request.contextPath}/resources/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    
<script src="${pageContext.request.contextPath}/resources/js/jquery.js" type="text/javascript"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/student/jquery.autocomplete.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/common.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/resources/js/bootstrap.js" type="text/javascript"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.flexisel.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/jquery.mCustomScrollbar.concat.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.dataTables.js"></script>

</head>

<body>
<!-- top header -->
<div class="container-fluid header-top-cont">
<div class="container">
<div class="row">
        <nav class="navbar navbar-inverse navbar-default" role="navigation">
           
            <div class="navbar-header hidden-xs">
                <p>Toll free: 1800 103 6768</p>
            </div>
            <!-- Top Menu Items -->
        
            <ul class="nav navbar-right top-nav">
              <%--   <li class="dropdown nav-br-top">
                 <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-envelope"></i> <b class="caret"></b>
                    <span class="badge badge-notify">5</span>
                    </a>
                   
                    <ul class="dropdown-menu panel-body2">
                        <li class="message-preview">
                            <a href="#">
                                <div class="media">
                                    <span class="pull-left">
                                         <img class="media-object" src="${pageContext.request.contextPath}/resources/images/client.jpg" alt="">
                                    </span>
                                    <div class="media-body">
                                         <h5 class="media-heading"><strong>Gauri Mittal</strong>
                                        </h5>
                                        <p class="small text-muted"><i class="fa fa-clock-o"></i> Today at 7:24 PM</p>
                                        <p> Do we need to bring the Lab tests tomorrow?</p>
                                    </div>
                                </div>
                            </a>
                        </li>
                        <li class="message-preview">
                            <a href="#">
                                <div class="media">
                                    <span class="pull-left">
                                         <img class="media-object" src="${pageContext.request.contextPath}/resources/images/client.jpg" alt="">
                                    </span>
                                    <div class="media-body">
                                        <h5 class="media-heading"><strong>Rajshree Mam</strong>
                                        </h5>
                                        <p class="small text-muted"><i class="fa fa-clock-o"></i> Yesterday at 10:00 PM</p>
                                        <p>I have sent the exercises for Circles. Please check...</p>
                                    </div>
                                </div>
                            </a>
                        </li>
                        <li class="message-preview ">
                            <a href="#">
                                <div class="media">
                                    <span class="pull-left">
                                         <img class="media-object" src="${pageContext.request.contextPath}/resources/images/client.jpg" alt="">
                                    </span>
                                    <div class="media-body">
                                        <h5 class="media-heading"><strong>Honey Bajaj</strong>
                                        </h5>
                                        <p class="small text-muted"><i class="fa fa-clock-o"></i> Yesterday at 3:00 PM</p>
                                        <p>When is the practice paper due?...</p>
                                    </div>
                                </div>
                            </a>
                        </li>
                        <li class="message-preview">
                            <a href="#">
                                <div class="media">
                                    <span class="pull-left">
                                         <img class="media-object" src="${pageContext.request.contextPath}/resources/images/client.jpg" alt="">
                                    </span>
                                    <div class="media-body">
                                        <h5 class="media-heading"><strong>Kiran Guha</strong>
                                        </h5>
                                        <p class="small text-muted"><i class="fa fa-clock-o"></i> 1 Day ago at 10:00 AM</p>
                                        <p>Ok. It will be done...</p>
                                    </div>
                                </div>
                            </a>
                        </li>
                        <li class="message-preview">
                            <a href="#">
                                <div class="media">
                                    <span class="pull-left">
                                         <img class="media-object" src="${pageContext.request.contextPath}/resources/images/client.jpg" alt="">
                                    </span>
                                    <div class="media-body">
                                        <h5 class="media-heading"><strong>Manohj Singh</strong>
                                        </h5>
                                        <p class="small text-muted"><i class="fa fa-clock-o"></i> 2 days ago at 2:32 PM</p>
                                        <p>Give me the link for my marks...</p>
                                    </div>
                                </div>
                            </a>
                        </li>
                         
                         <li class="message-footer">
                            <a href="#">Read All New Messages</a>
                        </li>
                    </ul>
                 
                </li>
                <li class="dropdown nav-br-top">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-bell"></i> <b class="caret"></b>
                    <span class="badge badge-msg">4</span>
                    </a>
                    <!--<ul class="dropdown-menu alert-dropdown drop-custom">
                        <li>
                            <a href="#">Alert Name <span class="label label-default">Alert Badge</span></a>
                        </li>
                        <li>
                            <a href="#">Alert Name <span class="label label-primary">Alert Badge</span></a>
                        </li>
                        <li>
                            <a href="#">Alert Name <span class="label label-success">Alert Badge</span></a>
                        </li>
                        <li>
                            <a href="#">Alert Name <span class="label label-info">Alert Badge</span></a>
                        </li>
                        <li>
                            <a href="#">Alert Name <span class="label label-warning">Alert Badge</span></a>
                        </li>
                        <li>
                            <a href="#">Alert Name <span class="label label-danger">Alert Badge</span></a>
                        </li>
                        <li class="divider"></li>
                        <li>
                            <a href="#">View All</a>
                        </li>
                    </ul>-->
                    <ul class="dropdown-menu panel-body2">
                        <li class="message-preview">
                            <a href="#">
                                <div class="media">
                                    <span class="pull-left">
                                        <img class="media-object" src="${pageContext.request.contextPath}/resources/images/client.jpg" alt="">
                                    </span>
                                    <div class="media-body">
                                        <h5 class="media-heading"><strong>School Admin</strong>
                                        </h5>
                                        <p class="small text-muted"><i class="fa fa-clock-o"></i> Today at 9:24 PM</p>
                                        <p> The school will remain closed on the 5th of September for the teacher's day celebration</p>
                                    </div>
                                </div>
                            </a>
                        </li>
                        <li class="message-preview">
                            <a href="#">
                                <div class="media">
                                    <span class="pull-left">
                                         <img class="media-object" src="${pageContext.request.contextPath}/resources/images/client.jpg" alt="">
                                    </span>
                                    <div class="media-body">
                                        <h5 class="media-heading"><strong>Poonam Mam</strong>
                                        </h5>
                                        <p class="small text-muted"><i class="fa fa-clock-o"></i> Yesterday at 7:32 PM</p>
                                        <p>New assignments added under topic name " Subject and Predicate"...</p>
                                    </div>
                                </div>
                            </a>
                        </li>
                        <li class="message-preview ">
                            <a href="#">
                                <div class="media">
                                    <span class="pull-left">
                                         <img class="media-object" src="${pageContext.request.contextPath}/resources/images/client.jpg" alt="">
                                    </span>
                                    <div class="media-body">
                                        <h5 class="media-heading"><strong>Deepak Rathi</strong>
                                        </h5>
                                        <p class="small text-muted"><i class="fa fa-clock-o"></i> 2 days ago at 12:00 PM</p>
                                        <p>Wish Deepak a very Happy Birthday...</p>
                                    </div>
                                </div>
                            </a>
                        </li>
                        <li class="message-preview">
                            <a href="#">
                                <div class="media">
                                    <span class="pull-left">
                                         <img class="media-object" src="${pageContext.request.contextPath}/resources/images/client.jpg" alt="">
                                    </span>
                                    <div class="media-body">
                                        <h5 class="media-heading"><strong>School Admin</strong>
                                        </h5>
                                        <p class="small text-muted"><i class="fa fa-clock-o"></i> 3 days ago at 1:00 PM</p>
                                        <p>Geetika is going to participate in the Green Olympiad for Maths in National Level from class 8...</p>
                                    </div>
                                </div>
                            </a>
                        </li>
                        
                         <li class="message-footer">
                            <a href="#">Read All New Messages</a>
                        </li>
                    </ul>
                </li> --%>
            <!--     <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-user mrr10"></i> John Smith <b class="caret"></b></a>
                    <ul class="dropdown-menu">
                        <li>
                            <a href="#"><i class="fa fa-fw fa-user"></i> Profile</a>
                        </li>
                        <li>
                            <a href="#"><i class="fa fa-fw fa-envelope"></i> Inbox</a>
                        </li>
                        <li>
                            <a href="#"><i class="fa fa-fw fa-gear"></i> Settings</a>
                        </li>
                        <li class="divider"></li>
                        <li>
                            <a href="#"><i class="fa fa-fw fa-power-off"></i> Log Out</a>
                        </li>
                    </ul>
                </li> -->
                		<li class="dropdown">
							<a href="#" class="dropdown-toggle" data-toggle="dropdown">
								<i class="fa fa-user mrr10"></i>
								<c:if test="${not empty sessionScope.user}">
                    ${sessionScope.user.name}
                    </c:if>
								<b class="caret"></b>
							</a>
							<ul class="dropdown-menu">

								<li>
									<a href="${pageContext.request.contextPath}/logout">
										<i class="fa fa-fw fa-power-off"></i> Log Out
									</a>
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
              <a class="navbar-brand" href="student-dashboard.html"><img src="${pageContext.request.contextPath}/resources/images/logo-home.png" alt="logo" class="img-responsive"/></a>
          </div>
          <div id="navbar" class="navbar-collapse collapse pull-right">
            <ul class="nav navbar-nav" >         <li class="nav-active" ><a href="${pageContext.request.contextPath}/parent/parentDashboard.htm?studentId=${userObj.id}" id="parentDashId"><span><img src="${pageContext.request.contextPath}/resources/images/dashboard.png" alt=""/></span>Home </a></li>
              <li class="dropdown" >
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown"><span><img src="${pageContext.request.contextPath}/resources/images/subjects.png" alt=""/></span>Subject</a>         <ul class="dropdown-menu" id="subjectDetailId">
                      <c:if test="${not empty subjectDetails }">
                      <c:forEach var="sub" items="${subjectDetails }">
                      <li>
                            <a href="${pageContext.request.contextPath}/parent/studentPerformance.htm?userId=${userObj.id}&subjectId=${sub.id}"><i class="fa fa-book"></i> ${sub.name }</a>
                        </li>
                      </c:forEach>
                      
                      </c:if>
                      
                       <!--  <li>
                            <a href="english.html"><i class="fa fa-book"></i> English</a>
                        </li>
                        <li>
                            <a href="evs.html"><i class="fa fa-book"></i> Evs</a>
                        </li>
                        <li>
                            <a href="math.html"><i class="fa fa-book"></i> Math</a>
                        </li>
                       
                        <li>
                            <a href="science.html"><i class="fa fa-book"></i> Science</a>
                        </li> -->
                        
                         
                    </ul>
                </li>
              <li ><a href="#"><span><img src="${pageContext.request.contextPath}/resources/images/reports-new.png" alt="logo"/></span>Reports</a></li>
              
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