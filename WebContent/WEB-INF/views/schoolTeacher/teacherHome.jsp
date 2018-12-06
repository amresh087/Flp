 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<jsp:include page="schoolTeacherHeader.jsp">
	<jsp:param value="dashboard" name="header" />
</jsp:include>

<div id="page-wrapper" style="padding-top:5%">
 
	<%-- --%>
<!--  -->
<div class="row">
                <div class="col-lg-9">
                    <div class="panel panel-default" style="margin-top:2%;">
                        <div class="panel-heading">
                             <div class="col-lg-2"><i class="fa fa-bar-chart-o fa-fw"></i> Dashboard</div>
				<ol class="breadcrumb col-lg-3" style="padding:0px;">
					<%-- <li><a href="${pageContext.request.contextPath}/schoolTeacher/teacherHome.htm">Home</a></li> --%>
					

                           
                        </div>
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                            <div class="teacher-mid">

		<!-- Page Heading -->
		<div class="row">
			<div class="col-md-12">
				

				</ol>
			</div>
		</div>
		<!-- /.row -->


		<div class="my-classes">


							<c:forEach var='sts' items="${teacherGradeSubjectDetails}" varStatus="s">

								<div class="col-md-4">
								<a href="${pageContext.request.contextPath}/schoolTeacher/gradesubjectdetail.htm?secId=${sts.sectionId}&subId=${sts.subjectId}&grade=${sts.section.sessionSchoolGrade.grade.name}&sec=${sts.section.name}&sub=${sts.subject.name}&grdId=${sts.section.sessionSchoolGrade.grade.id}">
										<div class="subjects">
											<div class="sub-img">
											<c:choose>
											<c:when test="${not empty sts.subject.subImage && sts.subject.subImage!=null  }">
											<img
													src="${commonPath}/subjectImage/${sts.subject.subImage}"
													 />
											</c:when>
											<c:otherwise><img
													src="${pageContext.request.contextPath}/resources/images/11.png"
													alt=""></c:otherwise>
											</c:choose>
											
												
											</div>

											<h1 class="sub-heading">
												 ${sts.section.sessionSchoolGrade.grade.name}
												(${sts.section.name}) <br>${sts.subject.name}
											</h1>
											 <div class="sub-pr-bar">
												<script type="text/javascript">
													$(function() {


														$('#diagram-id-${s.count}')
																.circleDiagram(
																		{
																			"percent" : "${sts.percentage}%",
																			"size" : "50",
																			"borderWidth" : "4",
																			"bgFill" : "#fff",
																			"frFill" : "#36BDB4",
																			"textSize" : "14",
																			"textColor" : "#585858"
																		});
													});
												</script>
												<div id="diagram-id-${s.count}" class="diagram"></div>
												<b>Completed</b>
											</div> 


											<b
												style="float: left; margin-top: 6px; font-size: 12px; padding-right: 15px; padding-left: 16px;">Total
												Student : ${sts.userCount}</b>
											<button class=" btn-view pull-l" type="button">View
												More</button>
										</div>
									</a>
								</div>

							</c:forEach>
							
							

							

<%-- 



							<div class="col-md-4">
 <a href="${pageContext.request.contextPath}/schoolTeacher/gradesubjectdetail.htm">
<div class="subjects">
 <div class="sub-img">
<img src="${pageContext.request.contextPath}/resources/images/11.png" alt="">
</div> 
<h1 class="sub-heading">Grade 6 (A)<br> Mathematics </h1>
<div class="sub-pr-bar">
<script type="text/javascript">
$(function() {

	$('#diagram-id-3').circleDiagram();

	$('#diagram-id-4').circleDiagram({
		"percent": "55%",
		"size": "50",
		"borderWidth": "4",
		"bgFill": "#fff",
		"frFill": "#36BDB4",
		"textSize": "14",
		"textColor": "#585858"
	});
});
</script>
	<div id="diagram-id-4" class="diagram"></div> <b>Completed</b>
</div>
<b style="float: left; margin-top: 6px; font-size: 12px; padding-right: 15px; padding-left: 16px;">Total Student : 100</b>
<button class=" btn-view pull-l" type="button">View More</button>
</div>
</a>
</div>
<div class="col-md-4">
 <a href="${pageContext.request.contextPath}/schoolTeacher/gradesubjectdetail.htm">
<div class="subjects">
<div class="sub-img">
<img src="${pageContext.request.contextPath}/resources/images/11.png" alt="">
</div>
<h1 class="sub-heading">Grade 6 (A)<br> Mathematics </h1>
<div class="sub-pr-bar">
<script type="text/javascript">
$(function() {

	$('#diagram-id-5').circleDiagram();

	$('#diagram-id-6').circleDiagram({
		"percent": "45%",
		"size": "50",
		"borderWidth": "4",
		"bgFill": "#fff",
		"frFill": "#36BDB4",
		"textSize": "14",
		"textColor": "#585858"
	});
});
</script>
	<div id="diagram-id-6" class="diagram"></div> <b>Completed</b>
</div>
<b style="float: left; margin-top: 6px; font-size: 12px; padding-right: 15px; padding-left: 16px;">Total Student : 100</b>
<button class=" btn-view pull-l" type="button">View More</button>
</div>
</a>
</div>	 --%>	
</div>

<div class="recent-activity">
<%-- <h1 class="recent-activity-h">Recent Activity</h1>
<div class="recent-activity-cont">
<div class="recent-activity-point">
<div class="col-md-1">
<img src="${pageContext.request.contextPath}/resources/images/activity-user-pic.png" alt="">
</div>
<div class="col-md-10">
<div style="padding:10px">
<span class="subject-tag">Pritam Chugh (VI A)  </span>Started Physics test at 14:00 hr.<br>
<span class="test-date-right">6 hours ago</span></div>
</div>
</div>
</div> --%>

<%-- <div class="recent-activity-cont">
<div class="recent-activity-point">
<div class="col-md-1">
<img src="${pageContext.request.contextPath}/resources/images/activity-user-pic.png" alt="">
</div>
<div class="col-md-10">
<div style="padding:10px">
<span class="subject-tag">Pritam Chugh (VI A)  </span>Started Physics test at 14:00 hr.<br>
<span class="test-date-right">6 hours ago</span></div>
</div>
</div>
</div> --%>


<%-- <div class="recent-activity-cont">
<div class="recent-activity-point">
<div class="col-md-1">
<img src="${pageContext.request.contextPath}/resources/images/activity-user-pic.png" alt="">
</div>
<div class="col-md-10">
<div style="padding:10px">
<span class="subject-tag">Pritam Chugh (VI A)  </span>Started Physics test at 14:00 hr.<br>
<span class="test-date-right">6 hours ago</span></div>
</div>
</div>
</div> --%>


<%-- <div class="recent-activity-cont">
<div class="recent-activity-point">
<div class="col-md-1">
<img src="${pageContext.request.contextPath}/resources/images/activity-user-pic.png" alt="">
</div>
<div class="col-md-10">
<div style="padding:10px">
<span class="subject-tag">Pritam Chugh (VI A)  </span>Started Physics test at 14:00 hr.<br>
<span class="test-date-right">6 hours ago</span></div>
</div>
</div>
</div> --%>



<c:if test="${empty highestScore }">
<div class="unit-test">
<div class=col-md-6>
<div id="container" style="min-width: 310px; height: 400px; margin: 0 auto"></div>

</div>
<div class="col-md-6">
<table class="topic-per table table-bordered table-hover table-condensed" width="100%" cellspacing="0" cellpadding="0" border="0" style="font-size:13px; margin-top:115px;">
<tr>
<td><p style="color:#4006b7;">Violet Zone Performer</p></td>
 <td>NA (0%)</td>
<td>NA (0%)</td>
<td>NA (0%)</td>  
</tr>

<tr>
<td><p style="color:#8f0d0d;">Red Zone Performer</p></td>
 <td>NA (0%)</td>
<td>NA (0%)</td>
<td>NA (0%)</td>  

</tr>
</table>

</div>

</div>
</c:if>



<c:if test="${not empty highestScore }">
<div class="unit-test">
<div class=col-md-6>
<div id="container" style="min-width: 310px; height: 400px; margin: 0 auto"></div>

</div>
<div class="col-md-6">
<table class="topic-per table table-bordered table-hover table-condensed" width="100%" cellspacing="0" cellpadding="0" border="0" style="font-size:13px; margin-top:115px;">
<tr>
<td><p style="color:#4006b7;">Violet Zone Performer</p></td>
<c:forEach items="${highestScore}" var="entry">
    <td>${entry.user.firstName} (${entry.percentage}%)</td>
</c:forEach>

<!-- <td>Rahul (96%)</td>
<td>Rahul (95%)</td>
<td>Rahul (91%)</td> -->
</tr>

<tr>
<td><p style="color:#8f0d0d;">Red Zone Performer</p></td>
<c:forEach items="${lowestScore}" var="entry1">
    <td>${entry1.user.firstName} (${entry1.percentage}%)</td>
    </c:forEach>
<!-- <td>Rahul (36%)</td>
<td>Rahul (35%)</td>
<td>Rahul (15%)</td> -->
</tr>
</table>

</div>

</div>
</c:if>
</div>

</div>
                        </div>
                        <!-- /.panel-body -->
                    </div>
                    <!-- /.panel -->
                   
                    <!-- /.panel -->
                    
                    <!-- /.panel -->
                </div>
                <!-- /.col-lg-8 -->
                <div class="col-lg-3" style="padding:0px;">
                    <div class="panel panel-default">
                     <!-- /.panel-heading -->
                        <div class="panel-body" style="padding:0px;">
                            <div class="list-group" style="margin-bottom:0px;">
                            <div class="teacher-rght">
 <div>
 <div class="teacher-right-heading"><i class="fa fa-volume-up" aria-hidden="true"></i>
Upcoming Test<div class="pull-right"><!-- <a style="color:#fff; font-size:13px;" href="#">View All</a> --></div></div>



<c:forEach var='upcomingTestList' items="${upcomingTestList}">
<div class="teacher-right-content">
<h1 class="test-name-right"><i class="fa fa-chevron-circle-right" aria-hidden="true"></i>
<a href="#" data-toggle="modal"  data-target="#testpreview"  onclick="testPreview('${upcomingTestList.id}')" >${upcomingTestList.testName}</a></h1>
<p>
<c:choose>
    <c:when test="${upcomingTestList.type==1}">
                     (E_TEST)
         </c:when>
         <c:otherwise>
                  (ASSIGNED_TEST) 
         </c:otherwise>
</c:choose>
 <span class="subject-tag">${upcomingTestList.subject.name} </span>
<br>  ${upcomingTestList.grade.name}  (A) <span class="test-date-right">On ${upcomingTestList.startDate}</span>
</p>
</div>
</c:forEach>

 </div>

   <div>
 <div class="teacher-right-heading">
 <i class="fa fa-refresh" aria-hidden="true"></i>
Ongoing Test<div class="pull-right"><!-- <a style="color:#fff; font-size:13px;" href="#">View All</a> --></div></div>
<c:forEach var='ongointTest' items="${completedTest}">
<c:if test="${ongointTest.flag ==1}">
<div class="teacher-right-content">
<h1 class="test-name-right"><i class="fa fa-chevron-circle-right" aria-hidden="true"></i>
<a href="#" data-toggle="modal"  data-target="#testpreview"  onclick="testPreview('${ongointTest.id}')" >${ongointTest.testName}</a></h1>
<p>
<c:choose>
    <c:when test="${ongointTest.type==1}">
                     (E_TEST)
         </c:when>
         <c:otherwise>
                  (ASSIGNED_TEST) 
         </c:otherwise>
</c:choose>
 <span class="subject-tag">${ongointTest.subject.name} </span>
<br> ${ongointTest.grade.name}  (A) <span class="test-date-right">On ${ongointTest.startDate}</span>
</p>
</div>
</c:if>

</c:forEach>
</div> 
 
 <div>
 <div class="teacher-right-heading">
 <i class="fa fa-thumbs-up" aria-hidden="true"></i>
Completed Test<div class="pull-right"><!-- <a style="color:#fff; font-size:13px;" href="#">View All</a> --></div></div>
	<c:forEach var='cmpltt' items="${completedTest}">
	
<c:if test="${cmpltt.flag ==2}">
  <div class="teacher-right-content">
<h1 class="test-name-right"><i class="fa fa-chevron-circle-right" aria-hidden="true"></i>
<a href="#" data-toggle="modal"  data-target="#testpreview"  onclick="testPreview('${cmpltt.id}')" >${cmpltt.testName}</a></h1>
<p>
<c:choose>
    <c:when test="${cmpltt.type==1}">
                     (E_TEST)
         </c:when>
         <c:otherwise>
                  (ASSIGNED_TEST) 
         </c:otherwise>
</c:choose>
<span class="subject-tag">${cmpltt.subject.name} </span>
<br>${cmpltt.grade.name}  (A) <span class="test-date-right">On ${cmpltt.startDate}</span>
</p>
</div>
</c:if>

</c:forEach>

 </div> 
  
</div>
                            </div>
                            <!-- /.list-group -->
                           
                        </div>
                        <!-- /.panel-body -->
                    </div>
                    <!-- /.panel -->
                   <!-- /.panel -->
                   
                </div>
                <!-- /.col-lg-4 -->
            </div>
<div class="clearfix"></div>
<div class="col-md-12"></div>

 </div>
 
 
 							
<div  class="modal fade" role="dialog" id="testpreview">
	<div class="modal-dialog" style="width:70%;">

		<!-- Modal content -->
		<div class="modal-content" id="testpreview123">
	
		</div>

	</div>
</div> 
<div style="width: 38px; height: 117px; right: 0px; top: 50%; position: fixed;">
<a href="https://docs.google.com/forms/d/e/1FAIpQLScylZUgeQrLr2oGBufoyeQFKFIlBQD67qvOdFhWu6Qcd17rRw/viewform" target="blank"><img src="${pageContext.request.contextPath}/resources/images/feedback.png" alt=""></a>
</div>
<!-- /.container-fluid -->

 
<!-- CHART -->
<script type="text/javascript">
$(function () {
	
    $('#container').highcharts({
        chart: {
            type: 'column'
        },
       
     
        xAxis: {
            type: 'category'
        },
        yAxis: {
            title: {
                text: 'Total percent marks'
            }

        },
        legend: {
            enabled: false
        },
        plotOptions: {
            series: {
                borderWidth: 0,
                dataLabels: {
                    enabled: true,
                    format: '{point.y:.1f}%'
                }
            }
        },

        tooltip: {
            headerFormat: '<span style="font-size:11px">{series.name}</span><br>',
            pointFormat: '<span style="color:{point.color}">{point.name}</span>: <b>{point.y:.2f}%</b> of total<br/>'
        },

        series: [{
            name: 'Marks',
            colorByPoint: true,
            data: [{
                name: 'Highest Score',
                y: parseFloat('${highestScoreGraph}'),
                drilldown: 'Highest Score'
            }, {
                name: 'Average Score',
                y: parseFloat('${middelPercentageScoreGraph}'),
                drilldown: 'Average Score'
            }, {
                name: 'Lowest Score',
                y: parseFloat('${lowScoreGraph}'),
                drilldown: 'Lowest Score'
            }]
        }],
        xAxis: {
            categories: [
                'Highest Score',
                'Average Score',
                'Lowest Score'
            ]
        },
    });
});

function testPreview(data)
{
	 var assessmentId = data;
	 
	    $.get("${pageContext.request.contextPath}/schoolTeacher/assessmentPreview/" + assessmentId, function(data)
	    {  
	    	$('#testpreview123').html(data);
		 
	    });
}



</script>

<!-- /#page-wrapper -->

<!-- /#wrapper -->

