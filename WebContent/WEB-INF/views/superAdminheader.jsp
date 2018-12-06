 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="${pageContext.request.contextPath}/resources/images/favicon.png">

    <title>Dashboard</title>

	
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
   
   
	
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/common.js"></script>
 	
 	
 	
	
     <script src="${pageContext.request.contextPath}/resources/js/bootstrap.js"></script>
   
	<style type="text/css">
.dataTab {background: #226a94 none repeat scroll 0 0;color: #fff;}
.see-all{width:100%; float:left; padding:5px; background:#DCEAF9; text-align:center; font-size:14px;}
.notification-icon{ background: #f56c7e none repeat scroll 0 0; border-radius: 50%;float: left; font-size: 13px !important;
    height: 22px;left: 178px;padding: 2px !important;position: absolute;text-align: center;width: 21px;}
    .notifi-detail{width:100%;padding:5px; background:#EBEFF2; margin-top:2px; float:none!important;}
.user-comment{color: #226a94; float: left; margin-left: 1%; width: 86%; margin-bottom:10px;}
.user-img{float: left; padding-left: 0; padding-right: 0; width: 6%;}
.comments{width:100%; padding:10px; background-position: 0 7px; padding-left: 28px;padding-top: 17px; float:none!important;}
.notification-date{font-size:13px !important; color:#666666 !important; font-weight:bold;}
	</style>

	
	<script type="text/javascript">
	
		var urlString = window.location.href;
		$(document).ready(function()
		{
			$('li').removeClass("active");
				
			if(urlString.indexOf("/admin/getSubjectTocPage.htm") >= 0 || urlString.indexOf("/admin/getChapterTocPage.htm") >= 0 || urlString.indexOf("/admin/getTopicTocPage.htm") >= 0 || urlString.indexOf("/admin/questionPage.htm") >= 0 || urlString.indexOf("/admin/modulePage.htm") >= 0)
				$("#masterToc").addClass("active");
			if(urlString.indexOf("/admin/getBoardTocStandardPage.htm") >= 0 || urlString.indexOf("/admin/getGradeTocStandardPage.htm") >= 0 || urlString.indexOf("/admin/getSubjectTocStandardPage.htm") >= 0 || urlString.indexOf("/admin/getChapterTocStandardPage.htm") >= 0 || urlString.indexOf("/admin/getTopicTocStandardPage.htm") >= 0)
				$("#standardToc").addClass("active");
			if(urlString.indexOf("/admin/schoolDetails.htm") >= 0)
				$("#schoolLst").addClass("active");
			if(urlString.indexOf("/admin/schoolAdminList.htm") >= 0)
				$("#schoolAdminLst").addClass("active");
			if(urlString.indexOf("/admin/sessionList.htm") >= 0)
				$("#sessionLst").addClass("active");
			
			if(urlString.indexOf("/sme/getChapterPage.htm") >= 0 || urlString.indexOf("/sme/getTopicPage.htm") >= 0 || urlString.indexOf("/sme/questionPage.htm") >= 0 || urlString.indexOf("/sme/modulePage.htm") >= 0)
				$("#smeToc").addClass("active");
		});
	
	</script>
  	</head>
  	<body>
  	<c:set var="commonPath" value="${sessionScope.user.staticRootPath}" scope="session"></c:set>
		<!--top-bg-start-here-->
		<div class="container-fluid top-bg">
			<div class="container fix">
				<div class="row">
				
					<div class="col-md-12 col-xs-12">
				
						<div class="row">
							<div class="logo col-md-2 col-xs-8"><a href="#"><img src="${pageContext.request.contextPath}/resources/images/logo-home.png" class="img-responsive" alt="Futuristic Learning" title="Futuristic Learning" /></a></div>
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
													<input type="hidden" id="userType" value="${sessionScope.user.userType}">
			      									<ul class="nav navbar-nav">
													     <c:choose>
														     <c:when test="${sessionScope.user.userType==7 or sessionScope.user.userType=='7' }">			<!-- For Teacher -->
															     <li ><a href="#">Manage Test</a></li>
															     <li ><a href="${pageContext.request.contextPath}/schoolTeacher/getAssignedTaskDetails.htm">Assign Assessment</a></li>
															     <li ><a href="${pageContext.request.contextPath}/schoolTeacher/getCreateAssessment.htm">Create Test</a></li>
														     </c:when>
													      
														     <c:when test="${sessionScope.user.userType==5 or sessionScope.user.userType=='5' }">			<!-- For School Coordinator -->
															      <li id="addViewStudent"><a href="${pageContext.request.contextPath}/schoolAdmin/studentDetails.htm">Manage Students</a></li>
															       <li id="addStudent"><a href="${pageContext.request.contextPath}/schoolAdmin/getAllSubjects.htm">Manage Subjects</a></li>
															      <li id="addViewTeacher"><a href="${pageContext.request.contextPath}/schoolAdmin/SubjectTeacherSectionDetails.htm">Manage Teachers</a></li>
															      <li id="addViewSme"><a href="${pageContext.request.contextPath}/schoolAdmin/smeDetails.htm">Manage SME</a></li>
															      <li id="schoolToc" ><a href="${pageContext.request.contextPath}/schoolAdmin/editGradeName.htm">Content Library</a></li>
														     </c:when>
													      
													      	<c:when test="${sessionScope.user.userType==6 or sessionScope.user.userType=='6' }">			<!-- For School SME -->
															    <li id="smeToc">
														      		<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">Master TOC <span class="sr-only">(current)</span></a>
															         <ul class="dropdown-menu" role="menu">
																		<li><a href="${pageContext.request.contextPath}/sme/getChapterPage.htm">Chapter Details</a></li>
															            <li><a href="${pageContext.request.contextPath}/sme/getTopicPage.htm">Topic Details</a></li>
															            <li><a href="${pageContext.request.contextPath}/sme/questionPage.htm">Questions</a></li>
															            <li><a href="${pageContext.request.contextPath}/sme/modulePage.htm">Modules</a></li>
															          
															         </ul>
														        </li>
														     </c:when>
														     
														     <c:otherwise>			<!-- For rest of user types -->
														      	<li id="masterToc">
														      		<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">Master TOC <span class="sr-only">(current)</span></a>
															         <ul class="dropdown-menu" role="menu">
															          	<li><a href="${pageContext.request.contextPath}/admin/getSubjectTocPage.htm">Subject Details</a></li>
																		<li><a href="${pageContext.request.contextPath}/admin/getChapterTocPage.htm">Chapter Details</a></li>
															            <li><a href="${pageContext.request.contextPath}/admin/getTopicTocPage.htm">Topic Details</a></li>
															            <li><a href="${pageContext.request.contextPath}/admin/questionPage.htm">Questions</a></li>
															              <li><a href="${pageContext.request.contextPath}/admin/getTopicTocStandardLOPage.htm">LO</a></li>
															            <li><a href="${pageContext.request.contextPath}/admin/modulePage.htm">Modules</a></li>
															            <li><a href="${pageContext.request.contextPath}/admin/updatePage.htm">Update Questions via Excel</a></li>
															         </ul>
														        </li>
														        
												 				<li class="active" id="standardToc">
												 					<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">Standard TOC <span class="sr-only">(current)</span></a>
															         <ul class="dropdown-menu" role="menu">
															          	<li><a href="${pageContext.request.contextPath}/admin/getBoardTocStandardPage.htm">Board Details</a></li>
															          	<li><a href="${pageContext.request.contextPath}/admin/getGradeTocStandardPage.htm">Grade Details</a></li>
																		<li><a href="${pageContext.request.contextPath}/admin/getSubjectTocStandardPage.htm">Subject Details</a></li>
															            <li><a href="${pageContext.request.contextPath}/admin/getChapterTocStandardPage.htm">Chapter Details</a></li>
															            <li><a href="${pageContext.request.contextPath}/admin/getTopicTocStandardPage.htm">Topic Details</a></li>
															            
															            <%--  <li><a href="${pageContext.request.contextPath}/admin/getTopicTocStandardLOPage.htm">LO</a></li> --%>

															            <li><a href="${pageContext.request.contextPath}/admin/planNPricing">Plan & Pricing</a></li>

															            <li><a href="${pageContext.request.contextPath}/admin/questionPageStandard.htm">Questions</a></li>
															            <li><a href="${pageContext.request.contextPath}/admin/modulePageStandard.htm">Modules</a></li>

															          </ul>
														        </li>
														        
														        <li id="schoolLst"><a href="${pageContext.request.contextPath}/admin/schoolDetails.htm">Schools</a></li>
														        <li id="schoolAdminLst"><a href="${pageContext.request.contextPath}/admin/schoolAdminList.htm">School Admin</a></li>
														     
														        <li id="sessionLst"><a href="${pageContext.request.contextPath}/admin/sessionList.htm">Session</a></li>
														        
														     </c:otherwise>
													     </c:choose>
													      
													    <li class="dropdown self-drop">
												     		<a href="${pageContext.request.contextPath}/logout" >Logout </a>
													        												        </li>
			      									</ul>
												</div>
			    							</div><!-- /.navbar-collapse -->
			  							</div>
			  						</div><!-- /.container-fluid -->
								</nav>
							</div>
						</div>
		
		
						<div class="col-md-3 col-sm-3 user-top search  col-xs-12 text-right">
						<c:if test="${sessionScope.user.userType eq 1 }">
		<div class="notifications" style="float: left; padding-top: 15px; padding-bottom: 27px; margin-left: 58%;">
		<a href="#" class=" dropdown-toggle"  data-toggle="dropdown" style="cursor: pointer; color:#ffffff; font-size: 24px;" id="notificationId"><i class="fa fa-comment"></i><c:if test="${not empty sessionScope.commentCnt && sessionScope.commentCnt >0 }"><span title="Notifications" class="notification-icon" display: inline;" id="notificationCnt">${sessionScope.commentCnt} </span></c:if></a>


 
 <div class="dropdown-menu" style="width: 360px;" >
 <div id="notificationDetailsId"></div>

     <div class="see-all"><a href="#" class=""  data-toggle="modal" data-target="#notification-window" id="allNotificationId">See All</a></div>
  </div>
 
</div>
</c:if>
							<c:choose>
								<c:when test="${not empty sessionScope.user}">
									<p>${sessionScope.user.name} 
									<c:if test="${not empty sessionScope.user.schoolName }">(${sessionScope.user.schoolName })</c:if>
									<span class="fa fa-user mrr10"></span></p>  
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

<div class="modal fade" id="notification-window" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document" style="width:70%!important;">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">All Notification</h4>
				</div>
	
				<div class="clearfix"></div>
				<div class="modal-body" style="border-bottom: none !important">
			 <div class="comments"  id="allCommentsId">

 </div>
    
    
					
				</div>
			</div>
		</div>
	</div>

<script type="text/javascript">
$.ajaxSetup({
    cache: false
});

$("#allNotificationId").on("click", function(){
	 $.get("${pageContext.request.contextPath}/admin/getCommentsnotificationsWithLimit/-1", function(data)
			    {
		 var val='';
		 var formattedDate='';
		 if(data!=null && data.length>0){
			 for(var i=0;i<data.length;i++){
				    formattedDate = new Date(data[i].postedOn);
				   var d = formattedDate.getDate();
				   var m =  formattedDate.getMonth();
				   m += 1;  // JavaScript months are 0-11
				   var y = formattedDate.getFullYear();
				
				
				   val=val+'<div class="notifi-detail" >'
				    +'<a href="${pageContext.request.contextPath}/admin/getQuestionDetailsById/'+data[i].questionId+'.htm" class="message">'
				    +'<div style="float:left"><img width="40" src="${pageContext.request.contextPath}/resources/images/user-pic.png" style="max-width:100%; margin-right:10px;"></div>'
				    +'<p>'+data[i].user.firstName+' commented On Question-ID-'+data[i].questionId+' <span class="notification-date">on '+d+'-'+m+'-'+y+'</span></p></a></div><br/>';
			 }
		 }
		 
		 $("#allCommentsId").html(val);
		
			    });
});


$("#notificationId").on("click", function(){
	var status=$(this).parent().hasClass("open");
	if(!status){
		 $.get("${pageContext.request.contextPath}/admin/getCommentsnotificationsWithLimit/5", function(data)
				    {
			 var val='';
			 var formattedDate='';
			 if(data!=null && data.length>0){
				 for(var i=0;i<data.length;i++){
					    formattedDate = new Date(data[i].postedOn);
					   var d = formattedDate.getDate();
					   var m =  formattedDate.getMonth();
					   m += 1;  // JavaScript months are 0-11
					   var y = formattedDate.getFullYear();
					   
					 val=val+'<div class="notifi-detail" >'
					    +'<a href="${pageContext.request.contextPath}/admin/getQuestionDetailsById/'+data[i].questionId+'.htm" class="message">'
					    +'<div style="float:left"><img width="40" src="${pageContext.request.contextPath}/resources/images/user-pic.png" style="max-width:100%; margin-right:10px;"></div>'
					    +'<p>'+data[i].user.firstName+' commented On Question-ID-'+data[i].questionId+' <span class="notification-date">on '+d+'-'+m+'-'+y+'</span></p></a></div>';
				 }
			 }
			 $("#notificationDetailsId").html(val);
			 $("#notificationCnt").hide();   
				    });
	}
});
</script>
