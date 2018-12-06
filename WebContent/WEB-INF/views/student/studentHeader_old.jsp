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
</style>
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
					<c:if test="${not empty parentReqList }">
                    <li class="dropdown nav-br-top">
                 <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-globe"></i> <b class="caret"></b>
                    <span class="badge badge-notify" id="notifyCount">${parentReqList.size() }</span>
                    </a>
                   
                    <ul class="dropdown-menu panel-body2">
                    <c:forEach var="parentReq" items="${parentReqList }" varStatus="s">
                        <li class="message-preview" id="msgPreview${s.count }">
                            <a href="#">
                                <div class="media">
                                    <span class="pull-left">
                                         <img class="media-object" src="${pageContext.request.contextPath}/resources/images/parent.jpg" alt="" style="width: 50px;">
                                    </span>
                                    <div class="media-body">
                                         <h5 class="media-heading"><strong>${parentReq.user.firstName }</strong></h5>
                                         <p> Do we need to bring the Lab tests tomorrow?</p>
                                        <div class="btn btn-success btn-xs acceptParentCls" id="acceptId${s.count }" dir="${parentReq.id }">Accept</div>  <div class="btn btn-danger btn-xs rejectParentCls" id="rejectId${s.count }" dir="${parentReq.id }">Reject</div>
                                     </div>
                                </div>
                            </a>
                        </li>
                        </c:forEach>
                  <!--       <li class="message-preview">
                            <a href="#">
                                <div class="media">
                                    <span class="pull-left">
                                         <img class="media-object" src="images/parent-icon.jpg" alt="">
                                    </span>
                                    <div class="media-body">
                                        <h5 class="media-heading"><strong>Rajshree Mam</strong>
                                        </h5>
                                        <p> Do we need to bring the Lab tests tomorrow?</p>
                                        <div class="btn btn-success btn-xs">Accept</div>  <div class="btn btn-danger btn-xs">Reject</div>
                                    </div>
                                </div>
                            </a>
                        </li>
                        <li class="message-preview ">
                            <a href="#">
                                <div class="media">
                                    <span class="pull-left">
                                         <img class="media-object" src="images/parent-icon.jpg" alt="">
                                    </span>
                                    <div class="media-body">
                                        <h5 class="media-heading"><strong>Honey Bajaj</strong>
                                        </h5>
                                         <p> Do we need to bring the Lab tests tomorrow?</p>
                                        <div class="btn btn-success btn-xs">Accept</div>  <div class="btn btn-danger btn-xs">Reject</div>
                                    </div>
                                </div>
                            </a>
                        </li>
                        <li class="message-preview">
                            <a href="#">
                                <div class="media">
                                    <span class="pull-left">
                                         <img class="media-object" src="images/parent-icon.jpg" alt="">
                                    </span>
                                    <div class="media-body">
                                        <h5 class="media-heading"><strong>Kiran Guha</strong>
                                        </h5>
                                         <p> Do we need to bring the Lab tests tomorrow?</p>
                                        <div class="btn btn-success btn-xs">Accept</div>  <div class="btn btn-danger btn-xs">Reject</div>
                                    </div>
                                </div>
                            </a>
                        </li>
                        <li class="message-preview">
                            <a href="#">
                                <div class="media">
                                    <span class="pull-left">
                                         <img class="media-object" src="images/parent-icon.jpg" alt="">
                                    </span>
                                    <div class="media-body">
                                        <h5 class="media-heading"><strong>Manohj Singh</strong>
                                        </h5>
                                         <p> Do we need to bring the Lab tests tomorrow?</p>
                                        <div class="btn btn-success btn-xs">Accept</div>  <div class="btn btn-danger btn-xs">Reject</div>
                                    </div>
                                </div>
                            </a>
                        </li> -->
                         
                       <!--   <li class="message-footer">
                            <a href="#">Read All New Messages</a>
                        </li> -->
                    </ul>
                 
                </li>
                </c:if>
						<li>
							<a href="javascript:void(0);">${sessionScope.user.gradeName }</a>
						</li>


						<li class="dropdown">
							<a href="#" class="dropdown-toggle" data-toggle="dropdown">
						<c:choose>
								<c:when test="${not empty sessionScope.user.profilePath}">
								
									<img src="http://flp.localhost.com/profileImage/${sessionScope.user.profilePath}" alt="#" width="42" height="35" id="profileImg">
								
								</c:when>
								<c:otherwise>
								
								<i class="fa fa-user mrr10"></i>
								
								</c:otherwise>
							
							
							</c:choose>
											
								
							<%-- 	<img src="${pageContext.request.contextPath}/resources/profileImage/${sessionScope.user.profilePath}" alt="#" width="42" height="42"> --%>
								<c:if test="${not empty sessionScope.user}">
                    ${sessionScope.user.name}
                    </c:if>
								<b class="caret"></b>
							</a>
							<ul class="dropdown-menu">

								<li>
									<a href="#" data-toggle="modal" data-target="#editProfile">
										<i class="fa fa-fw fa-power-off"></i> Edit Profile
									</a>
								</li>
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
									<span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span>
								</button>
								<a class="navbar-brand" href="#">
									<img src="${pageContext.request.contextPath}/resources/images/logo-home.png" alt="logo" class="img-responsive" />
								</a>
							</div>
							<div id="navbar" class="navbar-collapse collapse pull-right">
								<ul class="nav navbar-nav">
									<c:choose>
										<c:when test="${sessionScope.user.userType eq 2 && not empty sessionScope.user.schoolId }">
											<li id="dashboardId">
												<a href="${pageContext.request.contextPath}/student/studentHome.htm">
													<span><img src="${pageContext.request.contextPath}/resources/images/dashboard.png" alt="logo" /></span>DashBoard
												</a>
											</li>
											<li class="dropdown" id="subjectId">
												<a href="#" class="dropdown-toggle" data-toggle="dropdown">
													<span><img src="${pageContext.request.contextPath}/resources/images/subjects.png" alt="" /></span>Subject
												</a>
												<c:if test="${not empty sessionScope.user}">
													<ul class="dropdown-menu">

														<c:forEach items="${sessionScope.user.subjects}" var="subject">
															<li>
																<a href="${pageContext.request.contextPath}/student/subjectDetail.htm?sid=${subject.id}">
																	<i class="fa fa-book"></i> ${subject.name}
																</a>
															</li>
														</c:forEach>


													</ul>
												</c:if>
											</li>
											<li id="reportsId" class="dropdown">
												<a href="javascript:void(0);" class="dropdown-toggle" data-toggle="dropdown">
													<span><img src="${pageContext.request.contextPath}/resources/images/reports-new.png" alt="logo" /></span>Reports
												</a>
												<ul class="dropdown-menu">
													<li>
														<a href="${pageContext.request.contextPath}/student/testReports.htm">Assessment Reports</a>
													</li>
													<li>
														<a href="${pageContext.request.contextPath}/student/learningReports.htm">Learning Reports</a>
													</li>
												</ul>
											</li>
										</c:when>
										<c:otherwise>
											<li id="dashboardId">
												<a href="${pageContext.request.contextPath}/user/retailUserHome.htm">
													<span><img src="${pageContext.request.contextPath}/resources/images/dashboard.png" alt="logo" /></span>DashBoard
												</a>
											</li>
											<li class="dropdown" id="subjectId">
												<a href="#" class="dropdown-toggle" data-toggle="dropdown">
													<span><img src="${pageContext.request.contextPath}/resources/images/subjects.png" alt="" /></span>Subject
												</a>
												<c:if test="${not empty sessionScope.user}">
													<ul class="dropdown-menu">

														<c:forEach items="${sessionScope.user.subjects}" var="subject">
															<li>
																<a href="${pageContext.request.contextPath}/user/subjectDetail.htm?sid=${subject.id}">
																	<i class="fa fa-book"></i> ${subject.name}
																</a>
															</li>
														</c:forEach>


													</ul>
												</c:if>
											</li>
											<li id="reportsId" class="dropdown">
												<a href="javascript:void(0);" class="dropdown-toggle" data-toggle="dropdown">
													<span><img src="${pageContext.request.contextPath}/resources/images/reports-new.png" alt="logo" /></span>Reports
												</a>
												<ul class="dropdown-menu">
													<li>
														<a href="${pageContext.request.contextPath}/user/testReports.htm">Assessment Reports</a>
													</li>
													<li>
														<a href="${pageContext.request.contextPath}/student/learningReports.htm">Learning Reports</a>
													</li>
												</ul>
											</li>
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



<div class="modal fade" id="editProfile" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title" id="myModalLabel">Edit Profile</h4>
			</div>
			 
<jsp:include page="picUpload.jsp" />
			
			
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
	        <c:when test="${pageType eq '2' }">
	        $("#reportsId").addClass("nav-active");
	        </c:when>
	        <c:otherwise>
	        $("#subjectId").addClass("nav-active");
	        </c:otherwise>

	        </c:choose>
        });
        
        $(".acceptParentCls").on("click", function(){
        	 var r = confirm("You click accept button !!!");
        	    if (r == true) {
        	       var rowId=$(this).attr("id").substr(8);
        	       var parentLinkingId=$(this).attr("dir");
        	       var notifyCount=parseInt($("#notifyCount").html());
        	       $.post("${pageContext.request.contextPath}/ajax/rest/acceptRejectParentLinking/" + parentLinkingId + "?acceptOrNot=1", function(data)
        	    		    {
        	    	   if(data==true || data=='true'){
        	    		   $("#msgPreview"+rowId).remove();
        	    		   if(notifyCount>1){
              	    			notifyCount=notifyCount-1;
              	    			$("#notifyCount").html(notifyCount)  ; 
              	    		   }else{
              	    			$("#notifyCount").html('')  ;  
              	    		   }
        	    		   alert("You have successfully add your parent !!!");
        	    	   }
        	    		    });
        	    } 
        });
        $(".rejectParentCls").on("click", function(){
       	 var r = confirm("You click reject button !!!");
       	    if (r == true) {
       	       var rowId=$(this).attr("id").substr(8);
       	       var parentLinkingId=$(this).attr("dir");
       	       var notifyCount=parseInt($("#notifyCount").html());
       	       $.post("${pageContext.request.contextPath}/ajax/rest/acceptRejectParentLinking/" + parentLinkingId + "?acceptOrNot=0", function(data)
       	    		    {
       	    	   if(data==true || data=='true'){
       	    		   $("#msgPreview"+rowId).remove();
       	    		   if(notifyCount>1){
       	    			notifyCount=notifyCount-1;
       	    			$("#notifyCount").html(notifyCount)  ; 
       	    		   }else{
       	    			$("#notifyCount").html('')  ;  
       	    		   }
       	    		   alert("You have successfully reject this request !!!");
       	    	   }
       	    		    });
       	    } 
       });
	</script>