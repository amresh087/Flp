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
	Lx name="viewport">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/student/newcss/bootstrap.min.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.5.0/css/font-awesome.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/student/newcss/AdminLTE.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/student/newcss/_all-skins.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/student/newcss/bootstrap3-wysihtml5.min.css">
<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
<script type="text/javascript">


$(document).ready(function(){$(".topicClsRestrict").on("click",function(t){alert("Topic Restricted")}),$(".topicCls").on("click",function(t){t.preventDefault(),t.stopPropagation();var e=$(this).attr("id"),i=$(this).attr("dir"),a=$(this).attr("dirtopic"),c=$("#hiddenSubjectName").html(),o=$(this).attr("dirchapname");localStorage.setItem("chapterName",o),window.location.href="${pageContext.request.contextPath}/student/getQuestionAssessmentPage.htm?topicId="+e+"&chapterId="+i+"&sId=${param.sid}&subjectName="+c+"&topicName="+a})});




		


</script>
</head>

<body class="skin-blue sidebar-mini wysihtml5-supported fixed">
	<div class="wrapper">
		<header class="main-header">
			<a href="index2.html" class="logo"> <span class="logo-mini"><img
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
					<li class="treeview"><a
						href="${pageContext.request.contextPath}/student/studentHome.htm">
							<i class="fa fa-dashboard"></i> <span>Dashboard</span>
					</a></li>


					<c:if test="${not empty sessionScope.user}">
						<c:forEach items="${sessionScope.user.subjects}" var="subject">
							<li class="active treeview"><a
								href="${pageContext.request.contextPath}/student/subjectDetail.htm?sid=${subject.id}">
									<c:set var="string1" value="${subject.name}" /> <c:if
										test="${fn:containsIgnoreCase(string1, 'Math')}">
										<i class="fa fa-sort-numeric-desc"></i>

									</c:if> <c:if test="${fn:containsIgnoreCase(string1, 'Sci')}">
										<i class="fa fa-flask"></i>
									</c:if> <span>${subject.name}</span>
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
									<c:if test="${fn:containsIgnoreCase(string1, 'Math')}">
										<li><a
											href="${pageContext.request.contextPath}/student/learningReports.htm?sid=${subject.id}"><i
												class="fa fa-sort-numeric-desc"></i> ${subject.name}</a></li>
									</c:if>

									<c:if test="${fn:containsIgnoreCase(string1, 'Sci')}">
										<li><a
											href="${pageContext.request.contextPath}/student/learningReports.htm?sid=${subject.id}"><i
												class="fa fa-flask"></i> ${subject.name}</a></li>
									</c:if>
								</c:forEach>
							</c:if>
						</ul></li>
				</ul>
			</section>
		</aside>
		<div class="content-wrapper">
			<section class="content-header">
				<c:forEach var="sub" items="${sessionScope.user.subjects }"
					varStatus="status">
					<c:if test="${subjectId eq sub.id}">
						<h1>
							Home <small>${sub.name }<input type="hidden"
								id="hiddenSubjectName" value="${sub.name }" /></small>
						</h1>
					</c:if>
				</c:forEach>
				<ol class="breadcrumb">
					<li><a href="#"><i class="fa fa-dashboard"></i> Learn</a></li>
					<li class="active">Chapter</li>
				</ol>
			</section>
			<section class="content">
				<div class="row">
					<ul class="timeline margin-bottom">
						<li>
							<div class="timeline-item">
								<h3 class="timeline-header no-border">
									<a href="#"><i class="fa fa-book"></i> Learn </a> <i
										class="fa fa-angle-double-right" aria-hidden="true"></i>

									<c:forEach var="sub" items="${sessionScope.user.subjects }"
										varStatus="status">
										<c:if test="${subjectId eq sub.id}">
										${sub.name }
										</c:if>
									</c:forEach>

								</h3>
							</div>
						</li>
					</ul>

				</div>
				<div class="row">
					<div class="col-lg-12">
						<div class="box-group" id="accordion">
						
						

							<c:if test="${not empty chapterList }">
								<c:set var="count" value="0" />


								<c:forEach var="chapters" items="${chapterList}" varStatus="status">
									<div class="panel box box-primary">
									<a data-toggle="collapse" data-parent="#accordion"   href="#collapse${status.count}"  class='restchap'   style="color: #000;">
										<div class="box-header gradiant-bd2">
										
										
										<c:choose>
											<c:when test="${chapters.chapterLock == 1}">
											
											<h4 class="box-title col-lg-12">
													<div class="col-lg-7 no-padding" style="color:#000; font-weight: 600; font-size: 19px; text-transform: uppercase;">${chapters.name }<span> <i class="fa fa-unlock"
																					aria-hidden="true"></i>
																				</span>
													</div>
													<div class="col-lg-2 pull-right"></div>
											</h4>
											
											
											</c:when>
											<c:otherwise>
										
											<h4 class="box-title col-lg-12">
													<div class="col-lg-7 no-padding" style="color:#000; font-weight: 600; font-size: 19px; text-transform: uppercase;">${chapters.name }<span> <i class="fa fa-lock"
																					aria-hidden="true"></i>
																				</span>
													</div>
													<div class="col-lg-2 pull-right"></div>
											</h4>
											
											</c:otherwise>
										</c:choose>
										
										
											
											
											
											
											
										</div>
									</a>

										<c:choose>
											<c:when test="${status.count ==1||status.count =='1'}">
												<div id="collapse${status.count }"
													class="panel-collapse collapse in">
													<div class="box-body acc-font">

														<c:forEach var="topic" items="${chapters.topicSet }"
															varStatus="status1">

                                                       <c:set var="string1topic" value="${topic.id }" />
															<div class="box-body acc-font gradiant-bd"
																style="padding-bottom: 0; padding-top: 0;">
																<c:choose>
																	<c:when test="${topic.markTopicContentPermission == 1}">
																	<div style="background-color:#d3ffd9; float:left; width:100%;">
																		<a href="javascript:void(0)" class="topicCls"
																			id="${topic.id }" dir="${chapters.id }"
																			dirtopic="${topic.name}"
																			dirchapname="${chapters.name}">
																			<div class="col-lg-6" style="margin-top:14px">${topic.name}
																				<span> <i class="fa fa-unlock"
																					aria-hidden="true"></i>
																				</span>
																			</div>
																		</a>
																		
	
<div class="col-lg-4 no-padding">
<div class="progress progress-sm active">
<div class="progress-bar progress-bar-success progress-bar-striped" role="progressbar" aria-valuenow="20" aria-valuemin="0" aria-valuemax="100" style="width: ${topic.lx}%">
<span>LX ${topic.lx}</span>
</div>
</div>
</div>

<div class="col-lg-1 pull-right no-padding">
<div id="demo-pie-1_${string1topic}" class="pie-title-center" data-percent="${topic.lx}"> <span class="pie-value"> </span> </div>
<script type="text/javascript">
        $(document).ready(function () { 
        //	 alert("ghgfj"+'${string1topic}'); 
            $('#demo-pie-1_${string1topic}').pieChart({
                barColor: '#68b828',
                trackColor:'#c0c0c0',
                size: 50,
                lineCap: 'round',
                lineWidth: 5,
                onStep: function (from, to, percent) {
                    $(this.element).find('.pie-value').text('LX '+ percent);
                }
            });
        });
        
        function openclose()
        {
        	alert('kkkk');
        	}

    </script>
</div>

</div>
</c:when>
<c:otherwise>

<div style="background-color:#ffc6c6; float:left; width:100%; padding:5px;">
																		<a href="javascript:void(0)" class="topicClsRestrict"
																			id="${topic.id }" dir="${chapters.id }"
																			dirtopic="${topic.name}"
																			dirchapname="${chapters.name}">
																			<div class="col-lg-6" style="margin-top:14px">${topic.name }
																				<span> <i class="fa fa-lock"
																					aria-hidden="true"></i>
																				</span>
																			</div>
																		</a>
                                                                               </div>
																	</c:otherwise>
																</c:choose>
															</div>
														</c:forEach>
													</div>
												</div>
											</c:when>
											<c:otherwise>
												<div id="collapse${status.count }"
													class="panel-collapse collapse">
													<div class="box-body acc-font">
														<c:forEach var="topic" items="${chapters.topicSet }"
															varStatus="status1">
															<c:set var="string1topic2" value="${topic.id }" />
															<div class="box-body acc-font gradiant-bd"
																style="padding-bottom: 0; padding-top: 0;">
																<c:choose>
																	<c:when test="${topic.markTopicContentPermission == 1}">
													<div style="background-color:#d3ffd9; float:left; width:100%; padding:5px;">				
																		<a href="javascript:void(0)" class="topicCls"
																			id="${topic.id }" dir="${chapters.id }"
																			dirtopic="${topic.name}"
																			dirchapname="${chapters.name}">
																			<div class="col-lg-6" style="margin-top:14px">${topic.name}
																				<span> <i class="fa fa-unlock"
																					aria-hidden="true"></i>
																				</span>
																			</div>
																		</a>

<div class="col-lg-4 no-padding">
<div class="progress progress-sm active">
<div class="progress-bar progress-bar-success progress-bar-striped" role="progressbar" aria-valuenow="20" aria-valuemin="0" aria-valuemax="100" style="width: ${topic.lx}%">
<span>LX ${topic.lx}</span>
</div>
</div>
</div>

<div class="col-lg-1 pull-right no-padding">
<div id="demo-pie-1_${string1topic2}" class="pie-title-center" data-percent= "${topic.lx}"> <span class="pie-value"> </span> </div>
	<script type="text/javascript">

        $(document).ready(function () { 
        //	 alert("ghgfj"+'${string1topic}'); 
            $('#demo-pie-1_${string1topic2}').pieChart({
                barColor: '#68b828',
                trackColor:'#c0c0c0',
                size: 50,
                lineCap: 'round',
                lineWidth: 5,
                onStep: function (from, to, percent) {
                    $(this.element).find('.pie-value').text('LX '+ percent);
                }
            });
        });
    </script>
</div>																
</div>
</c:when>
<c:otherwise>
<div style="background-color:#ffc6c6; float:left; width:100%;padding:5px;">
																		<a href="javascript:void(0)" class="topicClsRestrict"
																			id="${topic.id }" dir="${chapters.id }"
																			dirtopic="${topic.name}"
																			dirchapname="${chapters.name}">
																			<div class="col-lg-6" style="margin-top:14px">${topic.name }
																				<span> <i class="fa fa-lock"
																					aria-hidden="true"></i>
																				</span>
																			</div>
																		</a>
																	</div>
																	</c:otherwise> 
																</c:choose>  
															</div>
														</c:forEach> 
													</div>
												</div> 
											</c:otherwise> 
										</c:choose> 
									</div>
								</c:forEach> 
							</c:if> 
						</div>
					</div>
				</div>
		</div>
		</section>
	</div>
	<footer class="main-footer">
		<strong>Copyright &copy; 2017 <a href="#">IPerformKonnect</a>.
		</strong> All rights reserved.
	</footer>
	</div>
	<script>
  $.widget.bridge('uibutton', $.ui.button);
</script>
	<script src="${pageContext.request.contextPath}/resources/js/student/newjs/bootstrap.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/student/newjs/jquery.sparkline.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/student/newjs/bootstrap3-wysihtml5.all.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/student/newjs/jquery.slimscroll.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/student/newjs/app.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/student/newjs/pie-chart.js"></script>
</body>
</html>