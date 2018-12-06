<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.io.*,java.util.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>Iperformkonnect</title>
<meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/student/newcss/bootstrap.min.css">
<script src="${pageContext.request.contextPath}/resources/js/jquery.js" type="text/javascript"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.5.0/css/font-awesome.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/student/newcss/AdminLTE.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/student/newcss/_all-skins.min.css">
 <script>
 var incQuestionCount=0;
 var incrimentForQuestiion=0;
 var slideIndex = 1; 
 var currentIndex = 0,items,itemAmt;
 var questionselected = [];
	var questioncountflag =0;
 var questionresponce=[];
 var question_new_old=1;
$.ajaxSetup({
    cache: false
});


var correctCount = 0;
    cGscore = '${score}';
    $(document).ready(function()
    { 
    	
    	
    	items = $('.container2 div'),
	    itemAmt = items.length;
    	//$('#genScore').empty().append('${score}');
    	//localStorage.removeItem('incQuestionCount');
    	/*  var loQ=localStorage.getItem('locountQForLocal');
    	 var countQ=localStorage.getItem('incQuestionCount');
    	alert(countQ);
    	if(countQ!=null  && countQ >= 8 && loQ=='1' ){ $('#genScore').empty().append('${score}'); localStorage.removeItem('incQuestionCount');}
    	else if(countQ!=null  && countQ >= 10 && loQ=='2' ||  loQ=='3'){ $('#genScore').empty().append('${score}'); localStorage.removeItem('incQuestionCount');}
    	
    	else if (countQ < 10 && loQ=='2' ||  loQ=='3'){$('#genScore').empty().append('0');}
    	else if (countQ < 8 && loQ=='1'){$('#genScore').empty().append('0');}
    	else {$('#genScore').empty().append('${score}');}  */
    	$('#genScore').empty().append('${score}');
    	document.getElementById("chaptername").innerHTML = localStorage.getItem("chapterName");
     var url = "${pageContext.request.contextPath}/student/getNextQuestion?topicId=${topicId}&sId=${param.sId}&subjectName=${param.subjectName}";
  	$.ajax({
             type : "post",
             url : url 
         }).done(function(res) {
              $('#questionDesc').html(res);
              var val=document.getElementById('dynamicresource').innerHTML;
              $('#maindynamicresource').html(val);
              var val1=document.getElementById('hintIdComman').innerHTML;
              $('#hintId').html(val1);
              question_new_old=1;
              
                 }).fail(function() {
                 alert("error");
             });

	   
	   
	   
    }); 
    
    
    
    
    
    
    
    
    
    
    
        
    </script>
</head>

<body class="skin-blue sidebar-mini wysihtml5-supported fixed">
 <div id="resources" class="modal fade">
 <div class="modal-dialog">
 <div class="modal-content">
 <div class="modal-header">
 <button type="button" data-dismiss="modal" aria-hidden="true" class="close">&times;</button>
 <h4 class="modal-title">Modal title</h4> </div>
 <div class="modal-body">
 <iframe id="iframeVideoURL" width="100%" height="400px" src=""> </iframe>
 </div>
 </div>
 </div>
 </div>

	<!--end hint-->
 <div class="wrapper">
 <header class="main-header">
 <a href="index2.html" class="logo">
 <span class="logo-mini"><img src="${pageContext.request.contextPath}/resources/images/dist/img/mini-logo.png" alt="log" width="50" height="50"></span>
<span class="logo-lg"><img src="${pageContext.request.contextPath}/resources/images/dist/img/Neurona-Logo.png" alt="log" width="120" height="50"></span>
</a>
<nav class="navbar navbar-static-top">
 <a href="#" class="sidebar-toggle" data-toggle="offcanvas" role="button">
 <span class="sr-only">Toggle navigation</span>
 </a>
<div class="news-update-box" style="margin-top:13px">
 <p style="float:left; color:#fff;"> ${sessionScope.user.schoolName }</p>
 </div>
 <div class="navbar-custom-menu">
 <ul class="nav navbar-nav">
 <li class="dropdown user user-menu">
 <a href="#" class="dropdown-toggle" data-toggle="dropdown">
 <span class="hidden-xs pull-left" style="margin-right:10px">${sessionScope.user.name} <br>
 ${sessionScope.user.gradeName }</span>

 <c:choose>
 <c:when test="${not empty sessionScope.user.profilePath}">
 <img src="${commonPath}/profileImage/${sessionScope.user.profilePath}" class="user-image" alt="User Image">
 </c:when>
 <c:otherwise>
 <img src="${pageContext.request.contextPath}/resources/images/dist/img/user2-160x160.jpg" class="user-image" alt="User Image">
 </c:otherwise>
 </c:choose>
 </a>
 <ul class="dropdown-menu">
 <!-- User image -->
 <li class="user-header">
 <c:choose>
 <c:when test="${not empty sessionScope.user.profilePath}">
 <img src="${commonPath}/profileImage/${sessionScope.user.profilePath}" class="img-circle" alt="User Image">
 </c:when>
 <c:otherwise>
 <img src="${pageContext.request.contextPath}/resources/images/dist/img/user2-160x160.jpg" class="img-circle" alt="User Image">
 </c:otherwise>
 </c:choose>
 <p>${sessionScope.user.name}</p>
 <p>${sessionScope.user.gradeName }</p>
 
 </li>
 <li class="user-footer">
 <div class="pull-left">
 <a href="${pageContext.request.contextPath}/student/studentprofile.htm" class="btn btn-default btn-flat">Profile</a>
 </div>
 <div class="pull-right">
 <a href="${pageContext.request.contextPath}/logout" class="btn btn-default btn-flat">Sign out</a>
 </div>
 </li> </ul>
 </li>
 </ul>
 </div>
 </nav>
 </header>
 <aside class="main-sidebar">
 <section class="sidebar">
 <ul class="sidebar-menu">
 <li class="active treeview">
 <a href="${pageContext.request.contextPath}/student/studentHome.htm">
 <i class="fa fa-dashboard"></i> <span>Dashboard</span>
 </a> </li>
 <c:if test="${not empty sessionScope.user}">
 <c:forEach items="${sessionScope.user.subjects}" var="subject">
 <li class="treeview">
 <a href="${pageContext.request.contextPath}/student/subjectDetail.htm?sid=${subject.id}">
 <c:set var="string1" value="${subject.name}"/>
 <c:if test="${fn:containsIgnoreCase(string1, 'Math')}">
 <i class="fa fa-sort-numeric-desc"></i> 
 </c:if>
 <c:if test="${fn:containsIgnoreCase(string1, 'Sci')}">
 <i class="fa fa-flask"></i>
 </c:if>
 <span>${subject.name}</span> </a></li>
 </c:forEach>
 </c:if>
 <li class="treeview">
 <a href="#"> <i class="fa fa-line-chart"></i> <span>Analytics</span> <span class="pull-right-container">
 <i class="fa fa-angle-left pull-right"></i> </span> </a>
 <ul class="treeview-menu">
 <c:if test="${not empty sessionScope.user}">
 <c:forEach items="${sessionScope.user.subjects}" var="subject">
 <c:set var="string1" value="${subject.name}"/>
 <c:if test="${fn:containsIgnoreCase(string1, 'Math')}">
 <li><a href="${pageContext.request.contextPath}/student/learningReports.htm?sid=${subject.id}"><i class="fa fa-sort-numeric-desc"></i> ${subject.name}</a></li>
 </c:if>
 <c:if test="${fn:containsIgnoreCase(string1, 'Sci')}">
 <li><a href="${pageContext.request.contextPath}/student/learningReports.htm?sid=${subject.id}"><i class="fa fa-flask"></i> ${subject.name}</a></li>
 </c:if>
 </c:forEach>
 </c:if>
 </ul>
 </li>
 </ul>
 </section>
 </aside>
 <div class="content-wrapper">

 <section class="content">
 <div class="row">
 <ul class="timeline" style="margin-top:15px;">
 <li>
 <div class="timeline-item">
 <div class="direct-chat-text btn-danger pull-right quit_learn">
 <a href="${pageContext.request.contextPath}/student/subjectDetail.htm?sid=${param.sId}" style="color: #fff;"><i class="fa fa-power-off" aria-hidden="true"></i> Quit Learning</a>
 </div>
 
 
 <div class="direct-chat-text pull-right lx"> <i class="icon fa fa-paper-plane"></i> Lx - 
 
 <span id="genScore"></span>
 </div>
<span class="time"><i class="fa fa-clock-o"></i> Time Spent <input type="text" placeholder="00" readonly
id="hh" value="00" style="width: 25px; border: 0px; margin-left: 10px;" /> : <input type="text" placeholder="00" readonly id="mm"
 value="00" style="width: 25px; border: 0px; margin-left: 5px;" /> : <input type="text" placeholder="00" readonly
 id="ss" value="00" style="width: 25px; border: 0px; margin-left: 5px;" />  </span>
 <h3 class="timeline-header no-border">
 <div class="chaptername" id="chaptername" style="display:inline">
 <i class="fa fa-flask"></i></div>
  <i class="fa fa-angle-double-right" aria-hidden="true">
 </i> ${topicName}
 </h3>
 </div>
 </li>
 </ul>
 </div>
 <!-- /.row -->
 <!-- Main row -->
 
  <c:choose>

      <c:when test="${score<40}">
 
 <div class="row">
 <div class="col-lg-12 box-header with-border">
 <div class="col-lg-10" id="questStatusId">
 </div>
 </div>

<div id='maindynamicresource' class="col-md-5  margin-top">
          
          
</div>

<div class="col-lg-7 col-lg-12 ">
<div class="col-lg-12 margin-bottom">
 <a class="btn btn-block btn-social btn-tumblr pull-right" style="width: 90px;" onclick="getNextQuestion()"> <i
 class="fa fa-arrow-right"></i> Next</a> </div>
 <input id='questionNumberCount' value="1" type="hidden" />
 
 
 <div id="questionDesc">
 
 
 </div>

 
 </div>
 </div>
 
  </c:when>   
  
  
                        
		      <c:when test="${score>=40 && score<100}">  
		         
		          <div class="col-lg-10" id="questStatusId1">
                     </div>
                     <div class="col-lg-12">
                     <div class="col-lg-12 margin-bottom">
                       <a class="btn btn-block btn-social btn-tumblr pull-right" style="width: 90px;" onclick="getNextQuestion1()"> <i
                                 class="fa fa-arrow-right"></i> Next</a> </div>
		            <div class="col-lg-7 ">
                    
                         <input id='questionNumberCount1' value="1" type="hidden" />
 
                        
                       <div id="questionDesc">
                           
 
					 </div>
					
					 </div>
		              <div class="col-md-5 margin-top no-padding">
       
          <div class="flip"> 
        <div class="card"> 
            <div class="face front" id="face_front"> 
              <button class="btn bg-red btn-flat margin" type="button" style="font-size: 19px; margin: 30% auto ! important;" onclick="showHint()">Need Help</button>
            </div> 
            <div class="face back" id="hintId" style="display:none; font-size: 19px;"> 
                
             
            </div> 
        </div> 
    </div>
    
    </div>
		    </div>  
		        </c:when>
		          
		 <c:when test="${score>=100 &&  score<200}"> 
		        <!-- <div class="col-lg-10" id="questStatusId1">
                     </div> -->
		        <div class="col-md-12  margin-top">
		        <div class="col-lg-12 margin-bottom">
                       <a class="btn btn-block btn-social btn-tumblr pull-right" style="width: 90px;" onclick="getNextQuestion1()"> <i
                                 class="fa fa-arrow-right"></i> Next</a> </div>
                         <input id='questionNumberCount1' value="1" type="hidden" />
 
                        
                       <div id="questionDesc">
                           
 
					 </div>
		        </div>
		        
		        
		        </c:when>
		         <c:otherwise>
		         
		         
		         
		         </c:otherwise>

 </c:choose>
 
 
 
 
 
 
 
 
 
 </section>
 </div>
 <footer class="main-footer">
 <strong>Copyright &copy; 2016-2017 <a href="#">Iperformkonnect </a>. </strong> All rights reserved.
 </footer>
 </div>
 <script src="${pageContext.request.contextPath}/resources/js/student/newjs/jquery-2.2.3.min.js"></script>
 <script>
  $.widget.bridge('uibutton', $.ui.button);
</script>
<script>
function showHint() {
   document.getElementById('hintId').style.display = "block";
   document.getElementById('face_front').style.display = "none";
   
   
}

</script>
 <script src="${pageContext.request.contextPath}/resources/js/student/newjs/bootstrap.min.js"></script>
 <script src="${pageContext.request.contextPath}/resources/js/student/newjs/jquery-jvectormap-world-mill-en.js"></script>
 <script src="${pageContext.request.contextPath}/resources/js/student/newjs/bootstrap3-wysihtml5.all.min.js"></script>
 <script src="${pageContext.request.contextPath}/resources/js/student/newjs/jquery.slimscroll.min.js"></script>
 <script src="${pageContext.request.contextPath}/resources/js/student/newjs/app.min.js"></script>
 <script src="${pageContext.request.contextPath}/resources/js/student/newjs/dashboard.js"></script>
 </body>
 
 

 </html>