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
<div class="col-lg-12">
<div class="panel panel-default">
<!-- /.panel-heading -->
 <div class="panel-body">
<div class="teacher-mid">
<div class="my-classes">
<div class="col-md-3">
<a href="#">
<div class="subjects" style="height:auto;">
<div class="sub-img">
<img src="${pageContext.request.contextPath}/resources/images/11.png" alt="">
</div>
<h1 class="sub-heading">Grade 6 (A) <br>Mathematics </h1>


</div>
</a>
</div>
		
<div class="col-md-9">
<div class="pull-right" style="margin-top:40px;">
<a href="${pageContext.request.contextPath}/schoolTeacher/getManageTocHome.htm"><img src="${pageContext.request.contextPath}/resources/images/libr.png" alt="">
<b style="margin-top: 15px; font-size: 12px; padding-right: 5px; padding-left: 10px;">Content Library </b></a>
<a href="${pageContext.request.contextPath}/schoolTeacher/getCreateAssessment.htm"><img src="${pageContext.request.contextPath}/resources/images/cont-libry.png" alt="">
<b style="margin-top: 15px; font-size: 12px; padding-right: 5px; padding-left: 10px;">Create Test</b></a>
</div>

</div>	
		
</div>
<!-- /.row -->
<div class="recent-activity">
<div class="unit-test">
<div class=col-md-6>
<div id="container" style="min-width: 310px; height: 400px; margin: 0 auto"></div>
</div>
</div>
<div class="row">
			<%-- <div class="table-responsive">
				<table border="0" cellspacing="0" cellpadding="0"
					class="table table-bordered text-center table-head">
					<tr>
						<td><strong>Total Questions</strong></td>
						<td><strong>Correct</strong></td>
						<td><strong>Incorrect</strong></td>
						<td><strong>Skipped</strong></td>

						<td><strong>Marks Obtained</strong></td>
						<td><strong>Average Marks</strong></td>
						<td><strong>Maximum Marks</strong></td>
						<td><strong>Percentage</strong></td>
						<td><strong>Topper's Score</strong></td>
						<td><strong>No. of Students who took the test</strong></td>

					</tr>
					<tr>
						<td>${studentAssessmentSummary.totalQuestion }</td>
						<td>${studentAssessmentSummary.totalCorrect }</td>
						<td>${studentAssessmentSummary.totalIncorrect }</td>
						<td>${studentAssessmentSummary.totalUnAttempted }</td>
						<td><fmt:formatNumber pattern="#"
								value="${studentAssessmentSummary.marks}" /></td>

<td><fmt:formatNumber pattern="#.##"
								value="${avgMarks}" /></td>
						<td>${param.maxMarks }</td>
						<td><fmt:formatNumber pattern="#"
								value="${(studentAssessmentSummary.marks/param.maxMarks)*100}" /></td>





						<td><fmt:formatNumber pattern="#"
								value="${maxAssessmentMarks}" /></td>
						<td>${TotalStudentAttempt }</td>

					</tr>
				</table>
			</div> --%>

			<div class="col-md-12 test-summ-con">
				<h2 class="text-center">Test Summary</h2>

				<div class="col-md-12 col-sm-12 col-xs-12 mrb20 mrt20">
					<div class="col-md-2 col-sm-3 col-xs-12 correct mrb10">
						<b class="fa fa-thumbs-o-up mrr10 size18"> </b>Correct<b
							class="fa fa-check mrr20 sum-ans-right"></b>
					</div>
					<div class="col-md-2 col-sm-3 col-xs-12 Incorrect mrb10">
						<b class="fa fa-thumbs-o-down pull-left mrr10 size18"></b>Incorrect<b
							class="fa fa-remove mrr20 sum-wrong "></b>
					</div>
					<div class="col-md-2 col-sm-3 col-xs-12 unattempted-sub mrb10">Unattampted
					</div>
					<div class="col-md-2  col-sm-3 col-xs-12 bordersu mrb10"></div>
				</div>



				<ul class="list-unstyled test-summ">
					<c:forEach var="sts"
						items="${studentAssessmentSummary.testDeatail }"
						varStatus="status1">
						<li><c:choose>
								<c:when test="${sts.result eq 1}">
									<div class="sum-ques">${status1.count}.
										${sts.question.description }
										<div class="btn btn-success btn-xs cursor mrl10 bt-mt">
											Correct <b class="fa fa-check "></b>
										</div>
										<div class="pull-right btn btn-xs btn-warning">Marks:
											${sts.questmarks }</div>
										<c:if test="${not empty sts.question.imagePath}">
											<img
												src="${commonPath}/questionImages/${sts.question.imagePath }" />
										</c:if>
									</div>
								</c:when>
								<c:when test="${sts.result eq 0}">
									<div class="sum-ques">${status1.count}.
										${sts.question.description }
										<div class="btn btn-warning btn-xs cursor mrl10 bt-mt">
											Unattempted</b>
										</div>
										<div class="pull-right btn btn-xs btn-warning">Marks:
											${sts.questmarks }</div>
										<c:if test="${not empty sts.question.imagePath}">
											<img
												src="${commonPath}/questionImages/${sts.question.imagePath }" />
										</c:if>
									</div>
								</c:when>
								<c:when test="${sts.result eq 2}">
									<div class="sum-ques">${status1.count}.
										${sts.question.description }
										<div class="btn btn-danger btn-xs cursor mrl10 bt-mt">
											Incorrect <b class="fa fa-remove"></b>
										</div>
										<div class="pull-right btn btn-xs btn-warning">Marks:
											${sts.questmarks }</div>
										<c:if test="${not empty sts.question.imagePath}">
											<img
												src="${commonPath}/questionImages/${sts.question.imagePath }" />
										</c:if>
									</div>
								</c:when>

							</c:choose> <c:forEach var="option" items="${sts.question.answers}"
								varStatus="status">

								<c:choose>
									<c:when test="${sts.result eq 1}">
										<c:choose>

											<c:when test="${option.isCorrect eq 1}">
												<div class="sum-ans">
													<div class="sum-ques-left">
														<b class="fa fa-thumbs-o-up mrr10 size18">
														</b>
													</div>
													<div class="sum-ques-middle">${status.count }.
														${option.description }
														<c:if test="${not empty option.imagePath }">
															<br>
															<img
																src="${commonPath}/questionImages/${option.imagePath }"
																class="img-responsive" />
														</c:if>
													</div>
													<div class="sum-ques-right">
														<b class="fa fa-check mrr20 sum-ans-right"></b>
													</div>
													<div class="clearfix"></div>
												</div>
											</c:when>
											<c:otherwise>
												<div class="sum-q">
													<div class="sum-ques-left">&nbsp;</div>
													<div class="sum-ques-middle">${status.count }.
														${option.description }
														<c:if test="${not empty option.imagePath }">
															<br>
															<img
																src="${commonPath}/questionImages/${option.imagePath }"
																class="img-responsive" />
														</c:if>
													</div>
													<div class="sum-ques-right"></div>
													<div class="clearfix"></div>
												</div>
											</c:otherwise>

										</c:choose>
									</c:when>
									<c:when test="${sts.result eq 0}">
										<c:choose>
											<c:when test="${option.isCorrect eq 1}">
												<div class="unattempted-sub">
													<div class="sum-ques-left">&nbsp;</div>
													<div class="sum-ques-middle">${status.count }.
														${option.description }
														<c:if test="${not empty option.imagePath }">
															<br>
															<img
																src="${commonPath}/questionImages/${option.imagePath }"
																class="img-responsive" />
														</c:if>
													</div>
													<div class="sum-ques-right">&nbsp;</div>
													<div class="clearfix"></div>
												</div>
											</c:when>
											<c:otherwise>
												<div class="sum-q">
													<div class="sum-ques-left">&nbsp;</div>
													<div class="sum-ques-middle">${status.count }.
														${option.description }
														<c:if test="${not empty option.imagePath }">
															<br>
															<img
																src="${commonPath}/questionImages/${option.imagePath }"
																class="img-responsive" />
														</c:if>
													</div>
													<div class="sum-ques-right"></div>
													<div class="clearfix"></div>
												</div>
											</c:otherwise>
										</c:choose>

									</c:when>
									<c:when test="${sts.result eq 2}">
										<c:choose>
											<c:when test="${option.isCorrect eq 1}">
												<c:set var="ids" value="${sts.answerIds }" />
												<c:set var="id" value="${fn:split(ids,',')}" />
												<c:set var="flag" value="false" />

												<c:forEach var="i" begin="0" end="${fn:length(id)-1}">
													<c:choose>
														<c:when test="${option.id eq id[i] }">

															<c:set var="flag" value="true" />
														</c:when>
													</c:choose>
												</c:forEach>

												<c:choose>
													<c:when test="${flag eq true}">

														<div class="sum-ans">
															<div class="sum-ques-left">
																<b class="glyphicon glyphicon-thumbs-up mrr10 size18">
																</b>
															</div>
															<div class="sum-ques-middle">${status.count }.
																${option.description }
																<c:if test="${not empty option.imagePath }">
																	<br>
																	<img
																		src="${commonPath}/questionImages/${option.imagePath }"
																		class="img-responsive" />
																</c:if>
															</div>
															<div class="sum-ques-right">
																<b class="glyphicon glyphicon-ok mrr20 sum-ans-right"></b>
															</div>
															<div class="clearfix"></div>
														</div>

													</c:when>
													<c:otherwise>


														<div class="unattempted-sub">
															<div class="sum-ques-left">&nbsp;</div>


															<div class="sum-ques-middle">${status.count }.
																${option.description }
																<c:if test="${not empty option.imagePath }">
																	<br>
																	<img
																		src="${commonPath}/questionImages/${option.imagePath }"
																		class="img-responsive" />
																</c:if>
															</div>
															<div class="sum-ques-right">&nbsp;</div>
															<div class="clearfix"></div>
														</div>
													</c:otherwise>
												</c:choose>


											</c:when>
											<c:when test="${option.isCorrect eq 0}">
												<!--  when is correct 0 but present in user answer-->
												<c:set var="ids" value="${sts.answerIds }" />
												<c:set var="id" value="${fn:split(ids,',')}" />
												<c:set var="flag" value="false" />

												<c:forEach var="i" begin="0" end="${fn:length(id)-1}">
													<c:choose>
														<c:when test="${option.id eq id[i] }">

															<c:set var="flag" value="true" />
														</c:when>

													</c:choose>
												</c:forEach>
												<c:choose>
													<c:when test="${flag eq true}">
														<div class="sum-ans-wrong">
															<div class="sum-ques-left">
																<b
																	class="fa fa-thumbs-o-down pull-left mrr10 size18"></b>
															</div>
															<div class="sum-ques-middle">${status.count }.
																${option.description }
																<c:if test="${not empty option.imagePath }">
																	<br>
																	<img
																		src="${commonPath}/questionImages/${option.imagePath }"
																		class="img-responsive" />
																</c:if>
															</div>
															<div class="sum-ques-right">
																<b class="fa fa-remove mrr20 sum-wrong "></b>
															</div>
															<div class="clearfix"></div>
														</div>

													</c:when>
													<c:otherwise>
														<div class="sum-q">
															<div class="sum-ques-left">&nbsp;</div>
															<div class="sum-ques-middle">${status.count }.
																${option.description }
																<c:if test="${not empty option.imagePath }">
																	<br>
																	<img
																		src="${commonPath}/questionImages/${option.imagePath }"
																		class="img-responsive" />
																</c:if>
															</div>
															<div class="sum-ques-right">&nbsp;</div>
															<div class="clearfix"></div>
														</div>
													</c:otherwise>
												</c:choose>
											</c:when>
										</c:choose>


									</c:when>

								</c:choose>

							</c:forEach></li>

					</c:forEach>

						<li>
						<div class="sum-ques">1. Identify the correct number: Six
							million, four hundred and eleven thousand, two hundred and sixty.</div>
						<div class="sum-ans">
							a. 64110260 <b class="fa fa-check mrr20 sum-ans-right"></b>
							<div class="clearfix"></div>
						</div>
						<div class="sum-q">b. 64011260</div>
						<div class="sum-q">c. 64110260</div>
					</li>

					<li>
						<div class="sum-ques">2. Place commas as per Indian system:
							78690502.</div>

						<div class="sum-ans-wrong">
							<b class="fa fa-thumbs-o-down pull-left mrr10 size18"></b>a.
							7,869,0502 <b class="fa fa-remove mrr20 sum-wrong "></b>
							<div class="clearfix"></div>
						</div>

						<div class="sum-ans">
							<b class="fa fa-thumbs-o-up mrr10 size18"> </b>b.
							7,86,90,502 <b class="fa fa-check mrr20 sum-ans-right"></b>
							<div class="clearfix"></div>
						</div>
						<div class="sum-ans">c. 78,69,05,02</div>
						<div class="sum-q">d. 786,90,502</div>

					</li>


				</ul>


			</div>




		</div>
</div>
</div>
</div>
<!-- /.panel-body -->
</div>
<!-- /.panel -->
<!-- /.panel -->
<!-- /.panel -->
</div>
</div>
<div class="clearfix"></div>
<div class="col-md-12"></div>

 </div>

<!-- /.container-fluid -->

 
<!-- CHART -->
<script type="text/javascript">
$(function () {
    // Create the chart
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
                y: 85.33,
                drilldown: 'Highest Score'
            }, {
                name: 'Average Score',
                y: 55.03,
                drilldown: 'Average Score'
            }, {
                name: 'Lowest Score',
                y: 35.38,
                drilldown: 'Lowest Score'
            }
            
            
            ]
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
</sc
ript>

<!-- /#page-wrapper -->

<!-- /#wrapper -->

