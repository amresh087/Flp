<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.io.*,java.util.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>Iperformkonnect</title>
<meta
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"
	name="viewport">
<link
	href="${pageContext.request.contextPath}/resources/css/student/newcss/bootstrap.min.css"
	rel="stylesheet">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.5.0/css/font-awesome.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/student/newcss/AdminLTE.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/student/newcss/_all-skins.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/student/newcss/datepicker3.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/student/newcss/bootstrap3-wysihtml5.min.css">
<script
	src="${pageContext.request.contextPath}/resources/js/student/newjs/highcharts.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/js/student/newjs/exporting.js"></script>
</head>
<body class="skin-blue sidebar-mini wysihtml5-supported fixed">
	<div class="wrapper">
		<header class="main-header">
			<a href="#" class="logo"> <span class="logo-mini"><img
					src="${pageContext.request.contextPath}/resources/images/dist/img/mini-logo.png"
					alt="log" width="50" height="50"></span> <span class="logo-lg"><img
					src="${pageContext.request.contextPath}/resources/images/dist/img/Neurona-Logo.png"
					alt="log" width="120" height="50"></span>
			</a>
			<nav class="navbar navbar-static-top">
				<a href="#" class="sidebar-toggle" data-toggle="offcanvas"
					role="button"> <span class="sr-only">Toggle navigation</span>
				</a>
				<div class="news-update-box" style="margin-top: 13px; float: left;">
					<p style="float: left; color: #000;">
						${sessionScope.user.schoolName }</p>
				</div>
				<div class="navbar-custom-menu">
					<ul class="nav navbar-nav">
						<li class="dropdown user user-menu"><a href="#"
							class="dropdown-toggle" data-toggle="dropdown"> <span
								class="hidden-xs pull-left" style="margin-right: 10px">${sessionScope.user.name}
									(${sessionScope.user.gradeName })
							</span> <c:choose>
									<c:when test="${not empty sessionScope.user.profilePath}">
										<img
											src="${commonPath}/profileImage/${sessionScope.user.profilePath}"
											class="user-image" alt="User Image">
									</c:when>
									<c:otherwise>
										<img
											src="${pageContext.request.contextPath}/resources/images/dist/img/user2-160x160.jpg"
											class="user-image" alt="User Image">
									</c:otherwise>
								</c:choose>
						</a>
							<ul class="dropdown-menu">
								<!-- User image -->
								<li class="user-header"><c:choose>
										<c:when test="${not empty sessionScope.user.profilePath}">
											<img
												src="${commonPath}/profileImage/${sessionScope.user.profilePath}"
												class="img-circle" alt="User Image">
										</c:when>
										<c:otherwise>
											<img
												src="${pageContext.request.contextPath}/resources/images/dist/img/user2-160x160.jpg"
												class="img-circle" alt="User Image">
										</c:otherwise>
									</c:choose>
									<p>${sessionScope.user.name}</p>
									<p>${sessionScope.user.gradeName }</p></li>
								<li class="user-footer">
									<div class="pull-left">
										<a
											href="${pageContext.request.contextPath}/student/studentprofile.htm"
											class="btn btn-default btn-flat">Profile</a>
									</div>
									<div class="pull-right">
										<a href="${pageContext.request.contextPath}/logout"
											class="btn btn-default btn-flat">Sign out</a>
									</div>
								</li>
							</ul></li>
					</ul>
				</div>
			</nav>
		</header>

		<aside class="main-sidebar">
			<section class="sidebar">
				<ul class="sidebar-menu">
					<li class="active treeview"><a href="#"> <i
							class="fa fa-dashboard"></i> <span>Dashboard</span>
					</a></li>


					<c:if test="${not empty sessionScope.user}">
						<c:forEach items="${sessionScope.user.subjects}" var="subject">
							<li class="treeview"><a
								href="${pageContext.request.contextPath}/student/subjectDetail.htm?sid=${subject.id}">

									<c:set var="string1" value="${subject.name}" /> <c:if
										test="${fn:containsIgnoreCase(string1, 'Math')}">
										<i class="fa fa-sort-numeric-desc"></i>

									</c:if> <c:if test="${fn:containsIgnoreCase(string1, 'Sci')}">
										<i class="fa fa-flask"></i>
									</c:if> <span>${subject.name} </span>
							</a></li>
						</c:forEach>
					</c:if>


					<li class="treeview"><a href="#"> <i
							class="fa fa-line-chart"></i> <span>Analytics</span> <span
							class="pull-right-container"> <i
								class="fa fa-angle-left pull-right"></i>
						</span>
					</a>
						<ul class="treeview-menu">



							<c:if test="${not empty sessionScope.user}">
								<c:forEach items="${sessionScope.user.subjects}" var="subject">

									<c:set var="string1" value="${subject.name}" />
									<c:set var="subjectIdNEW" value="${subject.id}" />

									<c:if test="${fn:containsIgnoreCase(string1, 'Math')}">
										<li><a href="${pageContext.request.contextPath}/student/learningReports.htm?sid=${subject.id}"><i
												class="fa fa-sort-numeric-desc"></i> ${subject.name}</a></li>

									</c:if>

									<c:if test="${fn:containsIgnoreCase(string1, 'Sci')}">
										<li><a href="${pageContext.request.contextPath}/student/learningReports.htm?sid=${subject.id}"><i
												class="fa fa-flask"></i> ${subject.name}</a></li>
									</c:if>


								</c:forEach>
							</c:if>




						</ul></li>

				</ul>
			</section>

		</aside>


		<div class="content-wrapper">








			<div class="row">
				<div class="col-lg-12" style="margin-top: 15px;">

					<c:forEach var="allsubjectlist" items="${allsubject}">



						<div class="col-md-6 col-sm-6 col-xs-12">
							<div class="wrappers">
								<div class="small">
							 <div class="box-header ui-sortable-handle sub_headingss" style="cursor: move;">
<span> <c:set var="string1" value="${allsubjectlist.key.name}" /> 
<c:if test="${fn:containsIgnoreCase(string1, 'Math')}">
												<i class="fa fa-sort-numeric-desc"></i>

											</c:if> <c:if test="${fn:containsIgnoreCase(string1, 'Sci')}">
												<i class="fa fa-flask"></i>
											</c:if>






										</span>

<h3 class="box-title"><a href="${pageContext.request.contextPath}/student/subjectDetail.htm?sid=${subjectIdNEW}" >${allsubjectlist.key.name}</a></h3>

</div> 
									<div class="info-box bg-aqua">


										


										<div class="info-box-content">
											


											<c:forEach var="allchapter" items="${allsubjectlist.value}">

												<c:if test="${fn:containsIgnoreCase(string1, 'Math')}">
												</c:if>

												<c:if test="${allchapter.name != 'pp'}">

													<h3 class="box-title" style="color:#181f22; font-weight:bold;">${allchapter.name}</h3>

													<ul class="todo-list ui-sortable">
														<c:forEach var="alltopic" items="${allchapter.topics}">

															<li><span class="text colo-lg-5 no-padding">${alltopic.name}</span>
																<small class="label pull-right" style="color:#1c2427; font-size:14px; font-weight:normal;"><i class="fa fa-calendar" aria-hidden="true"></i> Last Studied 
																 ${alltopic.lxDate}</small>
																<span class="pull-right" style="color:#1c2427; font-size:14px; font-weight:normal;"><i class="fa fa-trophy" aria-hidden="true"></i>
 Lx-${alltopic.lx}</span>
															</li>

														</c:forEach>

													</ul>

												</c:if>









											</c:forEach>















										</div>



										<!-- /.info-box-content -->
									</div>
									<!-- /.info-box -->
								</div>
								<a class="btn-primary pull-left" href="#"
									style="margin-top: 10px; padding: 6px; width: 74px;">View
									More</a>

							</div>


						</div>





					</c:forEach>
				</div>

			</div>

			<div class="row" style="margin-top:18px">
				<div class="col-lg-12"  style="marin-top:20px;">

					<section class="col-lg-12 connectedSortable">

						<div class="nav-tabs-custom">

							<div class="tab-content no-padding">
<div class="box-header ui-sortable-handle sub_headingss2" style="cursor: move;">

<!-- <i class="fa fa-line-chart" aria-hidden="true"></i> Learning Report -->

</div>
<!-- <div id="day" style="min-width: 310px; height: 260px; margin: 0 auto"></div> -->
<div class="col-lg-6">
<div id="myCarousel" class="carousel slide" data-ride="carousel">
  <!-- Indicators -->
 <!--  <ol class="carousel-indicators">
    <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
    <li data-target="#myCarousel" data-slide-to="1"></li>
    <li data-target="#myCarousel" data-slide-to="2"></li>
  </ol> -->

  <!-- Wrapper for slides -->
  <div class="carousel-inner">
    <div class="item active">
     <img src="${pageContext.request.contextPath}/resources/images/slider1.jpg" alt="log" class="img-responsive">
    </div>

    <div class="item">
      <img src="${pageContext.request.contextPath}/resources/images/slider2.jpg" alt="log" class="img-responsive">
    </div>

    <%-- <div class="item">
      <img src="${pageContext.request.contextPath}/resources/images/slider3.jpg" alt="log" class="img-responsive">
    </div> --%>
  </div>

  <!-- Left and right controls -->
  <a class="left carousel-control" href="#myCarousel" data-slide="prev">
    <span class="fa fa-left"></span>
    <span class="sr-only">Previous</span>
  </a>
  <a class="right carousel-control" href="#myCarousel" data-slide="next">
    <span class="fa fa-right"></span>
    <span class="sr-only">Next</span>
  </a>
</div> 
</div>
<div class="col-lg-5" style="float:right">

 <a href="#"><img src="${pageContext.request.contextPath}/resources/images/scienze.png" alt="log" class="img-responsive"></a>
</div>


							</div>
						</div>



					</section>

					
				</div>

			</div>


			</section>

		</div>

		<footer class="main-footer">
		
			<strong>Copyright &copy;2017 <a href="#">IPerformKonnect
			</a>.
			</strong> All rights reserved.
		</footer>



	</div>

	<script
		src="${pageContext.request.contextPath}/resources/js/student/newjs/jquery-2.2.3.min.js"></script>

	<script src="https://code.jquery.com/ui/1.11.4/jquery-ui.min.js"></script>

	<script>
  $.widget.bridge('uibutton', $.ui.button);
</script>

	<script
		src="${pageContext.request.contextPath}/resources/js/student/newjs/bootstrap.min.js"></script>


	<script
		src="${pageContext.request.contextPath}/resources/js/student/newjs/jquery.sparkline.min.js"></script>

	<script
		src="${pageContext.request.contextPath}/resources/js/student/newjs/jquery-jvectormap-1.2.2.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/js/student/newjs/jquery-jvectormap-world-mill-en.js"></script>

	<script
		src="${pageContext.request.contextPath}/resources/js/student/newjs/jquery.knob.js"></script>

	<script
		src="${pageContext.request.contextPath}/resources/js/student/newjs/jquery-jvectormap-world-mill-en.js"></script>


	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.11.2/moment.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/js/student/newjs/daterangepicker.js"></script>

	<script
		src="${pageContext.request.contextPath}/resources/js/student/newjs/bootstrap-datepicker.js"></script>

	<script
		src="${pageContext.request.contextPath}/resources/js/student/newjs/bootstrap3-wysihtml5.all.min.js"></script>

	<script
		src="${pageContext.request.contextPath}/resources/js/student/newjs/jquery.slimscroll.min.js"></script>


	<script
		src="${pageContext.request.contextPath}/resources/js/student/newjs/app.min.js"></script>

	<script
		src="${pageContext.request.contextPath}/resources/js/student/newjs/dashboard.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/js/student/newjs/charts.js"></script>

</body>
</html>