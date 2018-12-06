<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<!-- Bootstrap Core JavaScript -->
	  <script src="${pageContext.request.contextPath}/resources/js/jquery.mCustomScrollbar.concat.min.js"></script>
<!-- custom scrollbar  -->
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/student/jquery.mCustomScrollbar.css">


<c:if test="${notfound==0}"> <script> /* alert("No student attempted test !!!") */ </script></c:if>

<c:if test="${notfound!=0}">
<div class="panel-body" >

<div class="col-md-5" style="background-color:#fff; height:342px">
<div class="table-responsive">
<table id="testReport" class="table table-bordered table-hover table-striped" style="margin-top:70px;">
	
	
	
	<tr>
	<td>
	<p>Grade : ${getTestDetail.grade.name} <br>
Subject : ${getTestDetail.subject.name} <br>
Date :<fmt:formatDate pattern="dd-MM-yyyy" value="${getTestDetail.endDate}" /></p></td>
	
	<td>
	<p>Highest Score :  ${maxMarks}<br>
Average Score : ${getTestDetail.average}<br>
Lowest Score :  ${minMarks}</p></td>
	</td>
	</tr>										
		
	<tr>
	<td>
	<p>Type : <c:if test="${getTestDetail.type==2}">Assignment</c:if>
			  <c:if test="${getTestDetail.type==1}">E-Test</c:if>
	<br>
Total Q : ${fn:length(getTestDetail.assessmentQuestion)}
<br>
Max Marks : ${getTestDetail.maxMarks}</p></td>
	
	<td>
	<p>Appeared Student : ${getTestDetail.studentTest.size()}<br>
	
 <a href="javascript:void()" data-target="#absent-student" data-toggle="modal" style="text-decoration:underline;">Absent Student : ${getTestDetail.assignedAssessment.size()-getTestDetail.studentTest.size()}</a>

 

<%-- Absent Student : ${getTestDetail.assignedAssessment.size()-getTestDetail.studentTest.size()}
 
 assignedAssessmentSize  :  ${getTestDetail.assignedAssessment.size()}  
 
 studentTestSize  :${getTestDetail.studentTest.size()}  --%>
</p></td>

	</tr>
	
										
</table>
</div>
</div>

<div class="col-md-7">
<div id="container" style="min-width: 250px; height: 340px; max-width: 540px; margin: 0 auto">

</div>
<!-- <a href="#" class="pull-right" data-target="#all-report" data-toggle="modal"  type="button" style="margin-top: 10px; position: relative; top: -117px; right: 85px;"><i class="fa fa-sign-in" aria-hidden="true"></i>
 Detail Reports</a> -->
</div>

<br> <br>
</div>


<div class="col-md-12">
<div class="score-zone">
<div class="col-md-4">
<h2 class="score-zone-h "><span class="pull-left">Zone : 0-30%</span><span class="pull-right">${fn:length(zone1)} Student</span></h2>
<div class="student-score-zone mCustomScrollbar" style="overflow: scroll;">
<c:forEach var="z1" items="${zone1}">
<div style="border-bottom: 1px solid rgb(54, 189, 180); float: left; padding: 10px; ">
<div class="col-md-4">



			<c:choose>
  <c:when test="${z1.user.profilePath!=null}">
    <img src="${commonPath}/profileImage/${z1.user.profilePath}" alt="" height="72" width="72">
  </c:when>
 
  <c:otherwise>
      <img src="${pageContext.request.contextPath}/resources/images/activity-user-pic.png" alt="" height="72" width="72">
  </c:otherwise>
</c:choose>





</div>
<div class="col-md-8" style="padding-right:0px; padding-left:10px;">

<p>
<b>${z1.user.firstName}</b><br>
Marks Obtained : <b>${z1.marks}/${getTestDetail.maxMarks}</b><br>
Percentage : <b><fmt:formatNumber pattern="#" value="${(z1.marks/getTestDetail.maxMarks)*100}"/>%</b><br>
</p>

</div>
<%-- <a href="${pageContext.request.contextPath}/schoolTeacher/studenttestreport.htm"> --%><button class="btn btn-primary"  data-target="#student-test-detail" data-toggle="modal"  onclick="getstudentTestReport('${z1.user.id}','${getTestDetail.id}')"  type="button" style="margin-top:10px;">View Details</button></a>
</div>
</c:forEach>

</div>
</div>

<div class="col-md-4">
<h2 class="score-zone-h" style="background:#434348; color:#ffffff"><span class="pull-left">Zone : 31%-45%</span><span class="pull-right">${fn:length(zone2)} Student</span></h2>
<div class="student-score-zone  mCustomScrollbar">
<c:forEach var="z2" items="${zone2}">
<div style="border-bottom: 1px solid rgb(54, 189, 180); float: left; padding: 10px; ">
<div class="col-md-4">
<c:choose>
  <c:when test="${z2.user.profilePath!=null}">
    <img src="${commonPath}/profileImage/${z2.user.profilePath}" alt="" height="72" width="72">
  </c:when>
 
  <c:otherwise>
      <img src="${pageContext.request.contextPath}/resources/images/activity-user-pic.png" alt="" height="72" width="72">
  </c:otherwise>
</c:choose>
</div>
<div class="col-md-8" style="padding-right:0px; padding-left:10px;">
<p>
<b>${z2.user.firstName}</b><br>
Marks Obtained : <b>${z2.marks}/${getTestDetail.maxMarks}</b><br>
Percentage : <b><fmt:formatNumber pattern="#" value="${(z2.marks/getTestDetail.maxMarks)*100}"/>%</b><br>
</p>
</div>
<button class="btn btn-primary"  data-target="#student-test-detail" data-toggle="modal"  onclick="getstudentTestReport('${z2.user.id}','${getTestDetail.id}')"  type="button" style="margin-top:10px;">View Details</button>
</div>
</c:forEach>


</div>
</div>
<div class="col-md-4">
<h2 class="score-zone-h" style="background:#90ED7D;"><span class="pull-left">Zone : 46%-60%</span><span class="pull-right">${fn:length(zone3)} Student</span></h2>
<div class="student-score-zone  mCustomScrollbar">
<c:forEach var="z3" items="${zone3}">
<div style="border-bottom: 1px solid rgb(54, 189, 180); float: left; padding: 10px; ">
<div class="col-md-4">
<c:choose>
  <c:when test="${z3.user.profilePath!=null}">
    <img src="${commonPath}/profileImage/${z3.user.profilePath}" alt="" height="72" width="72">
  </c:when>
 
  <c:otherwise>
      <img src="${pageContext.request.contextPath}/resources/images/activity-user-pic.png" alt="" height="72" width="72">
  </c:otherwise>
</c:choose>
</div>
<div class="col-md-8" style="padding-right:0px; padding-left:10px;">
<p>
<b>${z3.user.firstName}</b><br>
Marks Obtained : <b>${z3.marks}/${getTestDetail.maxMarks}</b><br>
Percentage : <b><fmt:formatNumber pattern="#" value="${(z3.marks/getTestDetail.maxMarks)*100}"/>%</b><br>
</p>
</div>
<button class="btn btn-primary"  type="button"  data-target="#student-test-detail" data-toggle="modal"  onclick="getstudentTestReport('${z3.user.id}','${getTestDetail.id}')"   style="margin-top:10px;">View Details</button>
</div>
</c:forEach>


</div>
</div>


</div>

<div class="score-zone">

<div class="col-md-4">
<h2 class="score-zone-h" style="background:#F7A35C; "><span class="pull-left">Zone : 61%-75%</span><span class="pull-right">${fn:length(zone4)} Student</span></h2>
<div class="student-score-zone  mCustomScrollbar">
<c:forEach var="z4" items="${zone4}">
<div style="border-bottom: 1px solid rgb(54, 189, 180); float: left; padding: 10px; ">
<div class="col-md-4">
<c:choose>
  <c:when test="${z4.user.profilePath!=null}">
    <img src="${commonPath}/profileImage/${z4.user.profilePath}" alt="" height="72" width="72">
  </c:when>
 
  <c:otherwise>
      <img src="${pageContext.request.contextPath}/resources/images/activity-user-pic.png" alt="" height="72" width="72">
  </c:otherwise>
</c:choose>
</div>
<div class="col-md-8" style="padding-right:0px; padding-left:10px;">
<p>
<b>${z4.user.firstName}</b><br>
Marks Obtained : <b>${z4.marks}/${getTestDetail.maxMarks}</b><br>
Percentage : <b><fmt:formatNumber pattern="#" value="${(z4.marks/getTestDetail.maxMarks)*100}"/>%</b><br>
</p>
</div>
<button class="btn btn-primary"  type="button"  data-target="#student-test-detail" data-toggle="modal"  onclick="getstudentTestReport('${z4.user.id}','${getTestDetail.id}')" style="margin-top:10px;">View Details</button>
</div>
</c:forEach>

</div>
</div>

<div class="col-md-4">
<h2 class="score-zone-h" style="background:#8085E9; color:#ffffff" ><span class="pull-left">Zone : 76%-90%</span><span class="pull-right">${fn:length(zone5)} Student</span></h2>
<div class="student-score-zone  mCustomScrollbar">
<c:forEach var="z5" items="${zone5}">
<div style="border-bottom: 1px solid rgb(54, 189, 180); float: left; padding: 10px; ">
<div class="col-md-4">

<c:choose>
  <c:when test="${z5.user.profilePath!=null}">
    <img src="${commonPath}/profileImage/${z5.user.profilePath}" alt="" height="72" width="72">
  </c:when>
 
  <c:otherwise>
      <img src="${pageContext.request.contextPath}/resources/images/activity-user-pic.png" alt="" height="72" width="72">
  </c:otherwise>
</c:choose>
</div>
<div class="col-md-8" style="padding-right:0px; padding-left:10px;">
<p>
<b>${z5.user.firstName}</b><br>
Marks Obtained : <b>${z5.marks}/${getTestDetail.maxMarks}</b><br>
Percentage : <b><fmt:formatNumber pattern="#" value="${(z5.marks/getTestDetail.maxMarks)*100}"/>%</b><br>
</p>
</div>
<button class="btn btn-primary"  type="button" data-target="#student-test-detail" data-toggle="modal"  onclick="getstudentTestReport('${z5.user.id}','${getTestDetail.id}')"  style="margin-top:10px;">View Details</button>
</div>
</c:forEach>


</div>
</div>

<div class="col-md-4">
<h2 class="score-zone-h" style="background:#F15C80; "><span class="pull-left">Zone : 91%-100%</span><span class="pull-right">${fn:length(zone6)} Student</span></h2>
<div class="student-score-zone  mCustomScrollbar">
<c:forEach var="z6" items="${zone6}">
<div style="border-bottom: 1px solid rgb(54, 189, 180); float: left; padding: 10px; ">
<div class="col-md-4">

<c:choose>
  <c:when test="${z6.user.profilePath!=null}">
    <img src="${commonPath}/profileImage/${z6.user.profilePath}" alt="" height="72" width="72">
  </c:when>
 
  <c:otherwise>
      <img src="${pageContext.request.contextPath}/resources/images/activity-user-pic.png" alt="" height="72" width="72">
  </c:otherwise>
</c:choose>
</div>
<div class="col-md-8" style="padding-right:0px; padding-left:10px;">
<p>
<b>${z6.user.firstName}</b><br>
Marks Obtained : <b>${z6.marks}/${getTestDetail.maxMarks}</b><br>
Percentage : <b><fmt:formatNumber pattern="#" value="${(z6.marks/getTestDetail.maxMarks)*100}"/>%</b><br>
</p>
</div>
<button class="btn btn-primary"  type="button" data-target="#student-test-detail" data-toggle="modal"  onclick="getstudentTestReport('${z6.user.id}','${getTestDetail.id}')" style="margin-top:10px;">View Details</button>
</div>
</c:forEach>


</div>
</div>


</div>
</div>	
</c:if>
<div id="viewDetails">


</div>


		<div id="student-test-detail" class="modal fade" role="dialog">
	

		<!-- Modal content-->
		

	</div>
	<!--all test report  -->
	<div id="all-report" class="modal fade" role="dialog">
	<div class="modal-dialog" style="width:80%">

		<!-- Modal content-->
		<div class="modal-content">
			<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<div class="modal-title">
						<span><b>All Test Details </span> 
					</div>
				</div>
				<div class="modal-body">
				<div class="pull-right">
				<i class="fa fa-print" aria-hidden="true"></i>
				
				<i class="fa fa-download" aria-hidden="true"></i> 
				</div>
				<div class="Col-md-12" style="margin-top:30px;">
				<div class="col-md-12">
<div class="col-md-5">
Test Name:
<b>Test on Large Numbers </b>
</div>

<div class="col-md-4">
Subject Name:
<b>Mathematics </b>
</div>

<div class="col-md-3 pull-right" style="text-align:right;">
TestDate:
<b>30-03-2016</b>
<br>
</div>
</div>

<div class="col-md-12" style="margin-top:10px;">
<div class="col-md-5">
Total Marks:
<b>120 </b>
</div>

<div class="col-md-4">
Topper Score:
<b>95 </b>
</div>

<div class="col-md-3 pull-right" style="text-align:right;">
Total Question:
<b>200</b>
<br>
</div>
</div>
				
				</div>


				<table border="0" cellspacing="0" cellpadding="0"
			class="table table-bordered text-center table-head">
			<tr>
			<div class="col-md-12">
			<td></td>
			<td style="border:none;"></td>
			<td style="border:none;"><div class="col-md-6" style="text-align:center; border:1px solid #E9EDF3"><strong>Marks</strong></div></td>
			<td style="border:none;"></td>
		
			<td style="border:none;"></td>
			<td style="border:none;"><div class="col-md-6" style="text-align:center; border:1px solid #E9EDF3""><strong>Question</strong></div></td>
			<td></td>
			
			</div>
			</tr>
			<tr style="background-color:#efefef; color:#020202;">
				<td>Admission No</td>
				<td>Student Name</td>
				<td>Marks Obtained</td>
				<td>Percentage</td>
				<td>Average Marks</td>
				<td>correct</td>
				<td>Incorrect</td>
				<td>Skipped</td>
				<td>Accuracy %
				(Correct / Attempted)
				</td>
				

			</tr>
			<tr>
				<td>0987</td>
				<td>Rahul</td>
				<td>90</td>
				<td>90%</td>
				<td>80</td>
				<td>10</td>

				<td>5</td>
				<td>4</td>
				<td><div class="progress progress-striped active"
							style="height: 20px; margin-top:10px;">
							<div id="progressBar" class="bar"
								style="height: 20px; background-color: green; color: #000;">5%</div>
						</div></td>





			
			</tr>
		</table>
				</div>
		
		
		</div>

	</div>
</div>
	<!--  -->
	
	<!--absent student  -->
	<div id="absent-student" class="modal fade" role="dialog">
	<div class="modal-dialog" style="width:60%">

		<!-- Modal content-->
		<div class="modal-content">
			<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<div class="modal-title">
						<span><b>Absent Student : (${fn:length(actuallistabsent)} )</span> 
					</div>
				</div>
				<div class="modal-body">
				<table border="0" cellspacing="0" cellpadding="0"
			class="table table-bordered text-center table-head">
			
			 
			<tr style="background-color:#efefef; color:#020202;">
				<td>Admission NO</td>
				
				<td>Student Name </td>
			</tr>
			 
			<c:forEach var="actuallistabsent" items="${actuallistabsent}">
			
			<tr>
				<td>${actuallistabsent.user.admissionNo} </td>
				<td>  <div class="pull-left"> <img src="${pageContext.request.contextPath}/resources/images/activity-user-pic.png" alt="" height="72" width="72">
				 ${actuallistabsent.user.firstName} </div></td>
			</tr>
			
			</c:forEach>
		</table>
				</div>
		
		
		</div>

	</div>
</div>
	
	<!--  -->

<script type="text/javascript">
$(function () {

    $(document).ready(function () {

        // Build the chart
        $('#container').highcharts({
            chart: {
                plotBackgroundColor: null,
                plotBorderWidth: null,
                plotShadow: false,
                type: 'pie'
            },
            tooltip: {
                format: '<b>{point.name}</b>: {point.y:.1f} Rs.',
            },
            plotOptions: {
                pie: {
                    allowPointSelect: true,
                    cursor: 'pointer',
                    dataLabels: {
                        enabled: false
                    },
                    showInLegend: true
                    
                }
            },
            series: [{
                name: 'Students',
                colorByPoint: true,
                data: [{
                    name: '0% - 30%',
                    y: ${fn:length(zone1)}
                }, {
                    name: '31% - 45%',
                    y: ${fn:length(zone2)},
                    sliced: true,
                    selected: true
                }, {
                    name: '46% - 60%',
                    y: ${fn:length(zone3)}
                }, {
                    name: '61% - 75%',
                    y: ${fn:length(zone4)}
                }, {
                    name: '76% - 90%',
                    y: ${fn:length(zone5)}
                }, {
                    name: '91% - 100%',
                    y: ${fn:length(zone6)}
                }]
            }]
        });
    });
    
    
});


function getstudentTestReport(stuId,assessmentId)
{
	console.log("avg mrks"+'${avgPer}');
	$.get("${pageContext.request.contextPath}/schoolTeacher/stuAssessmentSummary?stuId="+stuId+"&assessmentId="+assessmentId+"&maxPer="+'${maxPer}'+"&minPer="+'${minPer}'+"&avgPer="+'${avgPer}'/* +"&yourPercentage="+'${yourPercentage}' */,function(data){
	
		$('#student-test-detail').html(data);
		
		
	});
}


function getpopUp(stuId,assessmentId)
{
	alert('aaaaaaaaa');
	console.log("avg mrks"+'${avgPer}');
	$.get("${pageContext.request.contextPath}/schoolTeacher/stuAssessmentSummary?stuId="+stuId+"&assessmentId="+assessmentId+"&maxPer="+'${maxPer}'+"&minPer="+'${minPer}'+"&avgPer="+'${avgPer}'/* +"&yourPercentage="+'${yourPercentage}' */,function(data){
	
		$('#absent-student').html(data);
		
		
	});
}
 

</script>

