<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.io.*,java.util.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<jsp:include page="../student/studentHeader.jsp" />



<!-- middle panel -->
<div class="container-fluid ">
<div class="row">

<div class="slider-cont">
<div class="student-board-con ">
<!-- <div class="input-group student-board-search "> 
<input type="text" class="pd15 form-control " placeholder="What do you want to learn today ..."> 
<span class="input-group-btn"> <button class="btn student-board-bt" type="button" >Search</button></span>
</div> -->
</div>
</div>

<div class="container">
<div class="row">
<ul id="flexiselDemo1"> 
<li>
<div class="col-md-12 col-sm-12 col-xs-12">
<div class="under-heading">Check Your Understanding</div>
<div class="student-board-bt-img-con"><img src="${pageContext.request.contextPath}/resources/images/understanding.png" alt="understanding"/></div>
<div id="scroll-container" class="panel-body2">
<c:choose>
<c:when test="${not empty etestList}">
<c:forEach items="${etestList}" var="test" varStatus="status">
<a id="etestSummary${status.count }" >
<div class="col-md-12 col-sm-12 col-xs-12 student-board-container-list" >

<h5>${test.testName} (${test.subject.name}) <span class="text-right"> :- ${test.user.firstName }</span></h5>
<c:set var="srcPath" value="/resources/images/sad-icon.jpg"></c:set>
<c:if test="${not empty test.studentTest}">
<c:set var="srcPath" value="/resources/images/right-icon.jpg"></c:set>
</c:if>

<img src="${pageContext.request.contextPath}${srcPath}" alt="understanding"/>

<h6>Start Date - <fmt:formatDate value="${test.startDate}" pattern="dd-MM-yyyy hh:mm a"/> <br /> 

End Date - <fmt:formatDate value="${test.endDate}" pattern="dd-MM-yyyy hh:mm a"/> </h6>


<c:if test="${test.dateDifference ne null &&  empty test.studentTest}">
<c:set var="datas" value="${fn:split(test.dateDifference, ',')}" />


<div class="head-title" id="timeLeftId${status.count }">Time left for test to BECOME ACTIVE : </div>
<div class="left-time" id="timeLeftIds${status.count }"> <span id='dd${status.count }' ><fmt:formatNumber  value="${datas[0] / 24}" pattern="#" /></span><c:if test="${datas[0] / 24 gt 1 }">Days,</c:if> <c:if test="${datas[0] / 24 lt 2 }">Day,</c:if>  <span id='hh${status.count }' style="margin-left: 2px;"><fmt:formatNumber  value="${datas[0] mod 24}" pattern="#" /></span> Hr, <span id='mm${status.count }' style="margin-left: 2px;"><fmt:formatNumber  value="${datas[1]}" pattern="#" /></span> Min, <span id='ss${status.count }' style="margin-left: 2px;"><fmt:formatNumber  value="${datas[2]}" pattern="#" /></span> Sec
</div>



<script type="text/javascript">

$(document).ready(function(){
	var dd='<fmt:formatNumber  value="${datas[0]/24}" pattern="#" />';
	var hh='<fmt:formatNumber  value="${datas[0] mod 24}" pattern="#" />';
	var mm='<fmt:formatNumber  value="${datas[1]}" pattern="#" />';
	var ss='<fmt:formatNumber  value="${datas[2]}" pattern="#" />';
	var sec = (1000);
    timer = setInterval(function() 
    { 
 		if(ss=='0')
 			{
 			if(mm>'0')
 			{	mm--;
 				ss=59;
 				
 				$('#mm${status.count }').html(mm >= 10 ? mm : "0"+mm.toString());
 				$('#ss${status.count }').html(ss >= 10 ? ss : "0"+ss.toString());
 			}
 			else if(hh>'0'){
 			hh--;
 			mm=59;
 			ss=59;
 			
 			$('#hh${status.count }').html(hh >= 10 ? hh : "0"+hh.toString());
 			$('#mm${status.count }').html(mm >= 10 ? mm : "0"+mm.toString());
			$('#ss${status.count }').html(ss >= 10 ? ss : "0"+ss.toString());
 			}
 			else if(hh>'0'){
 				dd--;
 	 			hh=23;
 	 			mm=59;
 	 			ss=59;
 	 			$('#dd${status.count }').html(dd >= 10 ? dd : "0"+dd.toString());
 	 			$('#hh${status.count }').html(hh >= 10 ? hh : "0"+hh.toString());
 	 			$('#mm${status.count }').html(mm >= 10 ? mm : "0"+mm.toString());
 				$('#ss${status.count }').html(ss >= 10 ? ss : "0"+ss.toString());
 	 			}
 			else
 				{
 				$('#ss${status.count }').html(ss >= 10 ? ss : "0"+ss.toString());
 				timer.cancel;
 				clearInterval(timer);
 			$("#timeLeftId${status.count }").hide();
 				 $("#startTestId${status.count }").show();
 				$("#timeLeftIds${status.count }").hide();
 				}
 			
 			}
 		else
 			{
 			$('#ss${status.count }').html(ss >= 10 ? ss : "0"+ss.toString());
 			ss--;
 			}
    	
    	
    	
    },(sec));
});

</script>

<a href="${pageContext.request.contextPath}/student/getTestPage/${test.id}.htm?subjectName=${test.subject.name}" style="display: none;" id="startTestId${status.count }">
<div class="btn btn-success btn-xs">Test Activated, You can start now.</div>
</a>

</c:if>
<c:if test="${test.dateDifference eq null && empty test.studentTest}">
<a href="${pageContext.request.contextPath}/student/getTestPage/${test.id}.htm?subjectName=${test.subject.name}"  >
<div class="btn btn-success btn-xs">Test Activated, You can start now.</div>
</a>
</c:if>
<div class="clearfix"></div>
</div>
</a>
<c:if test="${not empty test.studentTest}">
<script type="text/javascript">
$("#etestSummary${status.count}").attr('href','${pageContext.request.contextPath}/student/assessmentSummary.htm?assessmentId=${test.id}&subjectName=${test.subject.name}').css('cursor','pointer');
</script>
</c:if>

</c:forEach>

</c:when>
<c:otherwise>
No e-test assigned.

</c:otherwise>

</c:choose>
<!-- <div class="col-md-12 col-sm-12 col-xs-12 student-board-container-list">
<h5>Multiplying Fraction (Math)</h5>
<h6>22-07-2015 / 9:00 - 9:30 AM</h6>
</div>
<div class="col-md-12 col-sm-12 col-xs-12 student-board-container-list">
<h5>Comprehension (Math)</h5>
<h6>Time Left: 1 Days, 17 Hr, 30 Min</h6>
</div> -->

</div>

</div>
</li>


<li>

<div class="col-md-12 col-sm-12 col-xs-12">
<div class="revise-heading">Learn, Practice and Revise</div>
<div class="student-board-bt-img-con"><img src="${pageContext.request.contextPath}/resources/images/revise.png" alt="understanding"/></div>
<div id="scroll-container" class="panel-body2">
<div class="col-md-12 col-sm-12 col-xs-12 student-board-container-list">
<h5>Assignment</h5>

<c:choose>
<c:when test="${not empty assignedTestList}">
<c:forEach items="${assignedTestList}" var="assignment" varStatus="status">
<a id="assignedTestSummary${status.count }">
<div class="student-board-container-inner">
<h6>${assignment.testName} :- ${assignment.user.firstName }</h6> 
<c:set var="srcPath" value="/resources/images/sad-icon.jpg"></c:set>
<c:if test="${not empty assignment.studentTest}">
<c:set var="srcPath" value="/resources/images/right-icon.jpg"></c:set>
</c:if>
<img src="${pageContext.request.contextPath}${srcPath}" alt="understanding"/>
<c:forEach items="${assignment.assignedAssessment}" var="testAssign">
<c:if test="${testAssign.status == 1}">
<p>Assigned on : <fmt:formatDate value="${testAssign.assignedDate}" pattern="dd MMM yyyy"/></p>
<c:if test="${empty assignment.studentTest}">
<p>Start Date : <fmt:formatDate value="${assignment.startDate}" pattern="dd MMM yyyy hh:mm a"/></p>
<p>End Date : <fmt:formatDate value="${assignment.endDate}" pattern="dd MMM yyyy hh:mm a"/></p>
</c:if>
</c:if>

</c:forEach>

<p>${assignment.subject.name}
<c:if test="${not empty assignment.studentTest}">
<c:forEach items="${assignment.studentTest}" var="stuTest">
<span>(${stuTest.totalCorrect}/${stuTest.totalQuestion})</span>
</c:forEach>
  
 </c:if> 
 </p>
 
 <c:if test="${assignment.dateDifference ne null &&  empty assignment.studentTest}">
<c:set var="datas" value="${fn:split(assignment.dateDifference, ',')}" />

<div class="head-title" id="timeLeftId1${status.count }">Time left for Assignment to BECOME ACTIVE :</div>
<div class="left-time" id="timeLeftIds1${status.count }"> <span id='dd1${status.count }' ><fmt:formatNumber  value="${datas[0] / 24}" pattern="#" /></span><c:if test="${datas[0] / 24 gt 1 }">Days,</c:if> <c:if test="${datas[0] / 24 lt 2 }">Day,</c:if> <span id='hh1${status.count }' style="margin-left: 2px;"><fmt:formatNumber  value="${datas[0] mod 24}" pattern="#" /></span> Hr, <span id='mm1${status.count }' style="margin-left: 2px;"><fmt:formatNumber  value="${datas[1]}" pattern="#" /></span> Min, <span id='ss1${status.count }' style="margin-left: 2px;"><fmt:formatNumber  value="${datas[2]}" pattern="#" /></span> Sec
</div>

<script type="text/javascript">

$(document).ready(function(){
	var dd='<fmt:formatNumber  value="${datas[0]/24}" pattern="#" />';
	var hh='<fmt:formatNumber  value="${datas[0] mod 24}" pattern="#" />';
	var mm='<fmt:formatNumber  value="${datas[1]}" pattern="#" />';
	var ss='<fmt:formatNumber  value="${datas[2]}" pattern="#" />';
	var sec = (1000);
    timer = setInterval(function() 
    { 
 		if(ss=='0')
 			{
 			if(mm>'0')
 			{	mm--;
 				ss=59;
 				
 				$('#mm1${status.count }').html(mm >= 10 ? mm : "0"+mm.toString());
 				$('#ss1${status.count }').html(ss >= 10 ? ss : "0"+ss.toString());
 			}
 			else if(hh>'0'){
 			hh--;
 			mm=59;
 			ss=59;
 			
 			$('#hh1${status.count }').html(hh >= 10 ? hh : "0"+hh.toString());
 			$('#mm1${status.count }').html(mm >= 10 ? mm : "0"+mm.toString());
			$('#ss1${status.count }').html(ss >= 10 ? ss : "0"+ss.toString());
 			}
 			else if(hh>'0'){
 				dd--;
 	 			hh=23;
 	 			mm=59;
 	 			ss=59;
 	 			$('#dd1${status.count }').html(dd >= 10 ? dd : "0"+dd.toString());
 	 			$('#hh1${status.count }').html(hh >= 10 ? hh : "0"+hh.toString());
 	 			$('#mm1${status.count }').html(mm >= 10 ? mm : "0"+mm.toString());
 				$('#ss1${status.count }').html(ss >= 10 ? ss : "0"+ss.toString());
 	 			}
 			else
 				{
 				$('#ss1${status.count }').html(ss >= 10 ? ss : "0"+ss.toString());
 				timer.cancel;
 				clearInterval(timer);
 			$("#timeLeftId1${status.count }").hide();
 			$("#timeLeftIds1${status.count }").hide();
 				 $("#startTestId1${status.count }").show();
 				}
 			
 			}
 		else
 			{
 			$('#ss1${status.count }').html(ss >= 10 ? ss : "0"+ss.toString());
 			ss--;
 			}
    	
    	
    	
    },(sec));
});
</script>

<a href="${pageContext.request.contextPath}/student/getTestPage/${assignment.id}.htm?subjectName=${assignment.subject.name}" style="display: none;" id="startTestId1${status.count }">
<div class="btn btn-success btn-xs">Assignment Activated, You can start now.</div>
</a>

</c:if>
<c:if test="${assignment.dateDifference eq null && empty assignment.studentTest && assignment.flag ne 1}">
<a href="${pageContext.request.contextPath}/student/getTestPage/${assignment.id}.htm?subjectName=${assignment.subject.name}"  >
<div class="btn btn-success btn-xs">Assignment Activated, You can start now.</div>
</a>
</c:if>
 
<div class="clearfix"></div>
</div>
</a>
<c:if test="${not empty assignment.studentTest}">
<script type="text/javascript">
$("#assignedTestSummary${status.count}").attr('href','${pageContext.request.contextPath}/student/assessmentSummary.htm?assessmentId=${assignment.id}&subjectName=${assignment.subject.name}').css('cursor','pointer');
</script>
</c:if>
</c:forEach>

</c:when>
<c:otherwise>
No Assignment Found
</c:otherwise>

</c:choose>



</div>

<div class="col-md-12 col-sm-12 col-xs-12 student-board-container-list">
<h5>Topic Assigned</h5>
<c:set var="isTopic" value="" />
<c:choose>
<c:when test="${not empty topicResourceList}">
<c:forEach items="${topicResourceList}" var="tp">
<c:if test="${tp.type == 1}">

<a href="${pageContext.request.contextPath}/student/getQuestionAssessmentPage.htm?topicId=${tp.topic.id}&sId=${tp.subject.id}&subjectName=${tp.subject.name}&topicName=${tp.topic.name}&chapterId=${tp.topic.chapterId}">
<div class="student-board-container-inner">
<h6>${tp.topic.name} </h6>


<p>Assigned on : <fmt:formatDate value="${tp.assignedOn}" pattern="dd MMM yy"/></p>
<p>${tp.subject.name}   <span>

 (GS : ${tp.topic.smartScore })
<%-- <c:choose>
<c:when test="${not empty tp.topic.topicSmartScore && tp.topic.topicSmartScore.userId == sessionScope.user.id}">
    (GS : ${tp.topic.topicSmartScore.smartScore })

</c:when>
<c:otherwise>
(GS : 0)
</c:otherwise> 
</c:choose>--%>
</span></p>
<div class="clearfix"></div>
</div>
</a>
<c:set var="isTopic" value="Yes" />
</c:if>
</c:forEach>
<c:if test="${empty isTopic}">
No Topic Assigned: 
</c:if>
</c:when>
<c:otherwise>
No Topic Assigned:
</c:otherwise>
</c:choose>

</div>
 <div class="col-md-12 col-sm-12 col-xs-12 student-board-container-list">
<h5>Resource Assigned</h5>

<div class="student-board-container-inner">




<ul class="list-unstyled">
 <c:set var="isResource" value="" />
<c:choose>
<c:when test="${not empty topicResourceList}">
<c:forEach items="${topicResourceList}" var="tp">

<c:if test="${tp.type == 2}">


<h6>${tp.subject.name}</h6>
Assigned on : <fmt:formatDate value="${tp.assignedOn}" pattern="dd MMM yy"/>  
 <c:set var="learningUrl" value="${commonPath}/modules/${tp.module.contentUrl}" />
      <c:if test="${tp.module.contentType eq 2}">
      <c:set var="learningUrl" value="${tp.module.contentUrl}" />
      </c:if>
     <%--  <li><a href="${learningUrl}" target="_blank"><img src="${pageContext.request.contextPath}/resources/images/word-icon.jpg" alt="understanding"/> Science: Effects of Air Pollution (Air)</a></li> --%>
     <c:set var="imgPath" value="" />


	<c:choose>
	<c:when test="${tp.module.resourceTypeId == 7 }">
	     <c:set var="imgPath" value="${pageContext.request.contextPath}/resources/images/pdf.png" />
	</c:when>
									<c:when test="${tp.module.resourceTypeId == 8 }">
									 <c:set var="imgPath" value="${pageContext.request.contextPath}/resources/images/word.png" />
									 </c:when>
									<c:when test="${tp.module.resourceTypeId == 10}">
									 <c:set var="imgPath" value="${pageContext.request.contextPath}/resources/images/ppt.png" />
									</c:when>
									<c:when test="${tp.module.resourceTypeId == 9}">
									<c:set var="imgPath" value="${pageContext.request.contextPath}/resources/images/excel.png" />
									</c:when>
									<c:when test="${tp.module.resourceTypeId == 1}">
									<c:set var="imgPath" value="${pageContext.request.contextPath}/resources/images/swf.png" />
									</c:when>
									<c:when test="${tp.module.resourceTypeId == 2 || tp.module.resourceTypeId == 3}">
									<c:set var="imgPath" value="${pageContext.request.contextPath}/resources/images/video.png" />
									</c:when>
								   <c:otherwise>
								   <c:set var="imgPath" value="${pageContext.request.contextPath}/resources/images/default.png" />
								   </c:otherwise>									
									</c:choose>
									
						<li>
		<a href="${learningUrl}" target="_blank"><img src="${imgPath}" alt="understanding"/>${tp.module.name}</a>
				</li>



<c:set var="isResource" value="Yes" />
<br>
</c:if>



</c:forEach>
<c:if test="${empty isResource}">
No resource found.
</c:if>
</c:when>
<c:otherwise>
No resource found.
</c:otherwise>
</c:choose>

</ul>
</div>
</div>




 <%-- <div class="col-md-12 col-sm-12 col-xs-12 student-board-container-list">
<h5>Resource Assigned</h5>

<div class="student-board-container-inner">

<ul class="list-unstyled">
<li><a href="${learningUrl}" target="_blank"><img src="${pageContext.request.contextPath}/resources/images/word-icon.jpg" alt="understanding"/> Science: Effects of Air Pollution (Air)</a></li>
<li><img src="${pageContext.request.contextPath}/resources/images/pdf-icon.jpg" alt="understanding"/> Hindi -</li>
<li><img src="${pageContext.request.contextPath}/resources/images/exel-icon.jpg" alt="understanding"/> Science: Social Studies -  Indian States
( Our Country)</li> 
</ul>





<div class="clearfix"></div>
</div>
</div>  --%>

</li>

<li>
<div class="col-md-12 col-sm-12 col-xs-12">
<div class="learning-heading">Suggested<br>
Learning</div>
<div class="student-board-bt-img-con"><img src="${pageContext.request.contextPath}/resources/images/learning.png" alt="understanding"/></div>
<div id="scroll-container" class="panel-body2">

  <c:choose>
<c:when test="${not empty subjectList}">
<c:forEach items="${subjectList}" var="subject" >
<div class="col-md-12 col-sm-12 col-xs-12 student-board-container-list">
<a id="topic_${subject.id}" >
<div class="student-board-container-inner2">
<h5>${subject.name}</h5>
<c:forEach items="${subject.chapters}" var="chapter" varStatus="chapterc">
<c:if test="${chapterc.count eq 1 }">
<h4>${chapter.name}</h4>
</c:if>
<c:forEach items="${chapter.topics }" var="topic" varStatus="topicc">
<c:if test="${topicc.count eq 1 }">
<p><span>${topic.name}</span></p>
</c:if>
</c:forEach>

</c:forEach>
 
 
</div>
</a>
<c:forEach items="${subject.chapters}" var="chapter" varStatus="chapterc">
<c:forEach items="${chapter.topics }" var="topic" varStatus="topicC" >
<c:if test="${chapterc.count eq 1 and topicC.count eq 1 }">
<div class="student-board-container-inner2-gs" id="inner2-gs-hindi">
GS ${topic.smartScore }
<%-- <c:choose>
<c:when test="${topic.topicSmartScore.userId == sessionScope.user.id }">
GS ${topic.smartScore }
</c:when>
<c:otherwise>
GS 0
</c:otherwise>
</c:choose> --%>
</div>
<script>
$("#topic_${subject.id}").attr("href", "${pageContext.request.contextPath}/student/getQuestionAssessmentPage.htm?topicId=${topic.id}&sId=${subject.id}&subjectName=${subject.name}&topicName=${topic.name}&chapterId=${chapter.id}")
</script>
</c:if>
</c:forEach>
</c:forEach>
</div>


</c:forEach>

</c:when>

</c:choose> 


<!-- <div class="col-md-12 col-sm-12 col-xs-12 student-board-container-list">
<div class="student-board-container-inner2">
<h5>English</h5>
<h4>Adjectives </h4>
<p><span>Activities and worksheets</span></p>
</div>
<div class="student-board-container-inner2-gs"  id="inner2-gs-english">GS45</div>
</div>
<div class="col-md-12 col-sm-12 col-xs-12 student-board-container-list">
<div class="student-board-container-inner2">
<h5>Science</h5>
<h4>Addition</h4>
<p><span>6 Digit Addition</span></p>
</div>
<div class="student-board-container-inner2-gs"  id="inner2-gs-sci">GS45</div>
</div>
<div class="col-md-12 col-sm-12 col-xs-12 student-board-container-list">
<div class="student-board-container-inner2">
<h5>Mathematics</h5>
<h4>Addition</h4>
<p><span>6 Digit Addition</span></p>
</div>
<div class="student-board-container-inner2-gs">GS45</div>
</div>  -->
</div>
</div>
</li>
</ul>
</div>
</div>


</div>
</div>


<!-- middle panel end -->   
 <jsp:include page="studentFooter.jsp" /> 