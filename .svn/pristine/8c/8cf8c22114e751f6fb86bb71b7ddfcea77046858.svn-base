<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="../student/studentHeaderTest.jsp" />

 <style type="text/css">
 
 .w3-btn-floating:hover,.w3-btn-floating-large:hover{box-shadow:0 8px 16px 0 rgba(0,0,0,0.2),0 6px 20px 0 rgba(0,0,0,0.19)} .w3-btn,.w3-btn-floating,.w3-btn-floating-large,.w3-closenav,.w3-opennav{-webkit-touch-callout:none;-webkit-user-select:none;-khtml-user-select:none;-moz-user-select:none;-ms-user-select:none;user-select:none} .w3-btn-floating,.w3-btn-floating-large{display:inline-block;text-align:center;color:#fff;background-color:#000;position:relative;overflow:hidden;z-index:1;padding:0;border-radius:50%;cursor:pointer;font-size:24px} .w3-btn-floating{width:40px;height:40px;line-height:40px}.w3-btn-floating-large{width:56px;height:56px;line-height:56px} .w3-disabled,.w3-btn:disabled,.w3-btn-floating:disabled,.w3-btn-floating-large:disabled{cursor:not-allowed;opacity:0.3}
 
 .question-right{  background: #e2e2e2 none repeat scroll 0 0; border: 3px solid #9BC232!important; border-radius: 50%; font-size: 17px !important; height: 30px !important; margin-bottom: 5px; margin-right: 7px;
    padding: 3px 3px !important; text-align: center; width: 30px !important; color:#9BC232 !important;}
    .question-wrong{ border: 3px solid #fd817e!important; border-radius: 50%; font-size: 17px !important; height: 30px !important; margin-bottom: 5px; margin-right: 7px;
    padding: 3px 3px !important; text-align: center; width: 30px !important; color:#fd817e;}
     .skip{ border: 3px solid #646464!important; border-radius: 50%; font-size: 17px !important; height: 30px !important; margin-bottom: 5px; margin-right: 7px;
    padding: 3px 3px !important; text-align: center; width: 30px !important; color:#646464;}
    .panel-body1 { height: 80px !important; padding: 10px; margin-bottom: 20px;}
    
    </style>
<!-- middle panel -->
 <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %> 
<div class="container-fluid bred-cont"style="height:70px;">
	<div class="container">
		<div class="row" style="margin-top:10px;">
			<ul class="breadcrumb col-md-9">
				<li style="color:#336699;">${subjectName }</li>
				 <li style="color:#336699;">${topicName}</li>
				<!-- <li>Practice Zone</li> -->
			
		<div id="DateCountdown" data-date=" 00:00:00" style="width: 225px; height: 76px; padding: 0px; box-sizing: border-box; margin-right: 53px; float:right;"></div>
		</ul>
	
<a id="getModuleId" class="btn-info pull-right" data-target="#myModal2" data-toggle="modal" href="javascript:void(0)" style="padding:10px"><i class="fa fa-play" aria-hidden="true"></i>
  Learning Resources </a>
 

		</div>
	</div>
</div>
<div class="container-fluid mrt20 question-con">
	<div class="container">
	
      <div class="col-md-9">
     
      <div id="questionDesc">
		
			</>

			<!--<div class="col-md-3 col-sm-3 col-xs-12"> <div class=" btn-primary btn btn-md"><a href="summary.html">Next</a></div></div>-->
		</div>
		
	
     
		</div>
		<div class="col-md-3">
		<div class="col-md-12 col-sm-3 col-xs-12" style="float:right">
		<!-- <div id="DateCountdown" data-date=" 00:00:00" style="width: 225px; height: 76px; padding: 0px; box-sizing: border-box; background-color: #f8f8f8;"></div> -->
					 <div class="timerr2" style="border-radius:15px; background-color:#E5E5E5; height:64px; margin-bottom:15px;">
						
							<div class="col-md-12 col-sm-12 col-xs-12">
							<ul>
							<li><b>Time Spent</b></li>
								<li><input type="text" placeholder="00" readonly="readonly"
									id="hh" value="00" /><label>HR</label></li>
								<li><input type="text" placeholder="00" readonly="readonly"
									id="mm" value="00" /><label>MIN</label></li>
								<li><input type="text" placeholder="00" readonly="readonly"
									id="ss" value="00" /><label>SEC</label></li>
							</ul>
							</div>
						
							
					</div> 

				</div>
		<div class="col-md-12 col-sm-3 col-xs-12">
			<h4 class="score" style="padding:8px; background-color:#336699 !important;"><b>Question Attempt</b> </h4>
		<div id="scroll-container"  class="panel-body1" style="height: 100px !important; margin-top:15px;">
     <ul class="list-unstyled list-inline question-panel" style="margin-left:10px;"  id="questStatusId">
  <!-- <li class="question-right" style="cursor: pointer;"><i class="fa fa-check" aria-hidden="true"></i> </li>
  <li class="question-wrong" style="cursor: pointer;"><i class="fa fa-times" aria-hidden="true"></i></li>
  <li class="skip" style="cursor: pointer;"><i class="fa fa-question" aria-hidden="true"></i></li>
  <li class="question-right" style="cursor: pointer;"><i class="fa fa-check" aria-hidden="true"></i> </li>
  <li class="question-wrong" style="cursor: pointer;"><i class="fa fa-times" aria-hidden="true"></i></li>
  <li class="skip" style="cursor: pointer;"><i class="fa fa-question" aria-hidden="true"></i></li> -->
  </ul>
     </div>
		</div>
	
		<!--timerr-start-here-->
			<!-- 	<div class="col-md-12 col-sm-3 col-xs-12">
					<div class="timerr2">
						<h4><b>Time Duration</b></h4>
							<div class="col-md-12 col-sm-12 col-xs-12">
							<ul>
							
								<li><input type="text" placeholder="00" readonly="readonly"
									id="hh" value="00" /><label>HR</label></li>
								<li><input type="text" placeholder="00" readonly="readonly"
									id="mm" value="00" /><label>MIN</label></li>
								<li><input type="text" placeholder="00" readonly="readonly"
									id="ss" value="00" /><label>SEC</label></li>
							</ul>
							</div>
						
							
					</div>

				</div> -->

				<!--timerr-start-here-->
				<div class="col-md-12 col-sm-3 col-xs-12 ">

					<div class="timerr2">
<h4 class="score">
<b>Gen Score</b>
<br>
<span>
<br>
</span>
</h4>
<p id="genScore">0</p>
</div>
				</div>
				<!--timerr-start-here-->
				
<div class="col-md-12 col-sm-3 col-xs-12 ">

<a href="${pageContext.request.contextPath}/student/subjectDetail.htm?sid=${param.sId}" class="btn-info btn btn-md"  style="float:left; margin-bottom:10px">Quit Learning</a>
		</div>
		
				</div>
				<!--timerr-start-here-->
		
		</div>

	
	
	
	</div>

<div id="myModal2" class="modal fade" role="dialog">

<div class="modal-dialog"  style="width:90%; margin-top:20px; padding-left:17%;">
<div class="modal-content" style="width:80%">
		<div class="modal-header" style="padding-top:5px; padding-bottom:5px;">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<div class="modal-title">
					<span></span>Start learning
				</div>
			</div> 
			








<!-- 
<h2 class="w3-center">Start learning</h2> -->
<div class="modal-body">

 <c:set var="flag" value="false"/>
    <c:set var="flag1" value="false" />
    <c:forEach var="module" items="${moduleTopicList}" varStatus="i">
    <c:set var="learningUrl" value="${commonPath}/modules/${module.module.contentUrl}" />
     <c:set var="contentName" value="${module.module.name}" />
      <c:if test="${module.module.contentType eq 2}">
      <c:set var="learningUrl" value="${module.module.contentUrl}" />
      <c:set var="contentName" value="${module.module.name}" />
      </c:if> 
         
   
    <c:if test="${empty moduleTopicList  }">
    	 <div class="mySlides">No modules available</div>
    </c:if> 
    
    
     <c:if test="${not empty module.module.contentUrl && (module.module.resourceTypeId == 2 || module.module.resourceTypeId == 3) }">
    	
    	  	<c:choose>
    	  	<c:when test="${flag1 eq false}">
    	  	 
			 <div class="mySlides" style="display: inline-block; width:100%;" >
			  <div class="col-md-12"><h3>${contentName}</h3></div>    	  	
     		<video width="100%" height="auto" controls  style="width:100%" id="video">
  			
  			<source src="${learningUrl}" type="video/mp4">
  			<source src="${learningUrl}" type="video/ogg">
		</video>
	
    		</div>
    		<c:set var="flag1" value="true"></c:set>
    	  	</c:when>
    	  	<c:otherwise>
    	  	<c:set var="flag" value="true"/>
    	  	<div class="mySlides" style="width:100%;"> 

            <div class="col-md-12"><h3>${contentName}</h3></div>  
     		<video width="100%" height="auto" controls  style="width:100%" id="video">
     	 
  			<source src="${learningUrl}" type="video/mp4">
  			<source src="${learningUrl}" type="video/ogg">
			</video> 
		
    		</div>
    	  	</c:otherwise>
    	  	</c:choose>
      </c:if>
      <c:if test="${not empty module.module.contentUrl && (module.module.resourceTypeId == 1) }">
    	
  
    
    
    
    
    <c:choose>
    	  	<c:when test="${flag1 eq false}">
			    	  	<div class="mySlides" style="display: inline-block; width:100%;" >
     		<div class="col-md-12"><h3>${contentName}</h3></div>  
    	<object id="FlashID"  width="100%" height="100%">
    	
 <param name="movie" value="${learningUrl}" />
 <param name="quality" value="high" />
 <param name="wmode" value="opaque" />
 <param name="swfversion" value="6.0.65.0" />
 <param name="expressinstall" value="Scripts/expressInstall.swf" />

 <object type="application/x-shockwave-flash" data="${learningUrl}" width="820" height="550">
   <param name="quality" value="high" />
   <param name="wmode" value="opaque" />
   <param name="swfversion" value="6.0.65.0" />
   <param name="expressinstall" value="Scripts/expressInstall.swf" />
   <div>
     <h4>Content on this page requires a newer version of Adobe Flash Player.</h4>
     <p><a href="http://www.adobe.com/go/getflashplayer"><img src="http://www.adobe.com/images/shared/download_buttons/get_flash_player.gif" alt="Get Adobe Flash player" width="112" height="33" /></a></p>
   </div>
 </object></object>
 
 
    		</div>
    		<c:set var="flag1" value="true"></c:set>
    	  	</c:when>
    	  	<c:otherwise>
    	  	<c:set var="flag" value="true"/>
    	  	<div class="mySlides" style="width:100%;">
     		
     			<div class="col-md-12"><h3>${contentName}</h3></div>  
     	<object id="FlashID"  width="100%" height="100%">
    	
 <param name="movie" value="${learningUrl}" />
 <param name="quality" value="high" />
 <param name="wmode" value="opaque" />
 <param name="swfversion" value="6.0.65.0" />
 <param name="expressinstall" value="Scripts/expressInstall.swf" />

 <object type="application/x-shockwave-flash" data="${learningUrl}" width="820" height="550">
   <param name="quality" value="high" />
   <param name="wmode" value="opaque" />
   <param name="swfversion" value="6.0.65.0" />
   <param name="expressinstall" value="Scripts/expressInstall.swf" />
   <div>
     <h4>Content on this page requires a newer version of Adobe Flash Player.</h4>
     <p><a href="http://www.adobe.com/go/getflashplayer"><img src="http://www.adobe.com/images/shared/download_buttons/get_flash_player.gif" alt="Get Adobe Flash player" width="112" height="33" /></a></p>
   </div>
 </object></object>
    	
    
    		</div>
    	  	</c:otherwise>
    	  	</c:choose>
    
    
    	
    	
       </c:if>
      
      <c:if test="${not empty module.module.contentUrl && (module.module.resourceTypeId == 15) }">
    	<div class="mySlides"  >
    	<div class="col-md-12"><h3>${contentName}</h3></div>  
    	<div >
    	
    	<iframe id="iframeVideo" width="100%" height="400px" src="${learningUrl}" >
    	
    	
    	
</iframe></div></div>
       </c:if>
      <c:if test="${not empty module.module.contentUrl && ( module.module.resourceTypeId == 7 || module.module.resourceTypeId == 8 || module.module.resourceTypeId == 9 || module.module.resourceTypeId == 10 ||  module.module.resourceTypeId == 11 || module.module.resourceTypeId == 12 || module.module.resourceTypeId == 13 ||module.module.resourceTypeId == 14) }">
    	<div class="mySlides">
    	<div class="col-md-12"><h3>${contentName}</h3></div>  
    	
    	
    	<iframe width="100%" height="400px" src="${learningUrl}" >
    	
    	
    	
</iframe></div>
       </c:if>
      
      
      
      </c:forEach>
 
 
 
 
 
 
 

  <a class="w3-btn-floating" style="position:absolute;top:45%;left:0" onclick="plusDivs(-1)">❮</a>
  <a class="w3-btn-floating" style="position:absolute;top:45%;right:0" onclick="plusDivs(1)" id='1'>❯</a>
<!-- </div> -->
</div>




<div class="modal-footer"></div>
</div>

</div>
</div>




<%-- <jsp:include page="studentFooter.jsp" /> --%>
<script>
var slideIndex = 1;
//

function plusDivs(n) {
	
	var vid = document.getElementById("video");if(vid!=null){vid.pause();}
	
  
  
  showDivs(slideIndex += n);
}

function showDivs(n) {
  var i;
  var x = document.getElementsByClassName("mySlides");
  if (n > x.length) {slideIndex = 1}
  if (n < 1) {slideIndex = x.length}
  for (i = 0; i < x.length; i++) {
     x[i].style.display = "none";
  }
  x[slideIndex-1].style.display = "block";
}








var currentIndex = 0,
items,
itemAmt;
$.ajaxSetup({
    cache: false
});
var correctCount = 0;
    cGscore = '${score}';
    $(document).ready(function()
    {
    	items = $('.container2 div'),
	    itemAmt = items.length;
    	
    
	    $('#questionDesc').load("${pageContext.request.contextPath}/student/getNextQuestion?topicId=${topicId}&sId=${param.sId}&subjectName=${param.subjectName}");
	   
    	/* $('#link_student_container').hide();
    	$('#subjectId').hide();
    	$('#reportsId').hide();
    	$('#student_grade').hide();
    	//$('#dashboardId').hide();
    	$('#student_profileLogout').hide(); */
    	
    	
	    
	    if('${flag}'=='false')
    	{
	    	 $('#viddiv').hide();
    	
    	}

    });
    
    showDivs(slideIndex);

    </script>

 <script>
            $("#DateCountdown").TimeCircles();
            $("#CountDownTimer").TimeCircles({ time: { Days: { show: false }, Hours: { show: false } }});
            $("#PageOpenTimer").TimeCircles();
            
            var updateTime = function(){
                var date = $("#date").val();
                var time = $("#time").val();
                var datetime = date + ' ' + time + ':00';
                $("#DateCountdown").data('date', datetime).TimeCircles().start();
            }
            $("#date").change(updateTime).keyup(updateTime);
            $("#time").change(updateTime).keyup(updateTime);
            
                       // Fade in and fade out are examples of how chaining can be done with TimeCircles
            $(".fadeIn").click(function() {
                $("#PageOpenTimer").fadeIn();
            });
            $(".fadeOut").click(function() {
                $("#PageOpenTimer").fadeOut();
            });
           
            
            
            
            
            
            
            
            
            
           
        </script> 
