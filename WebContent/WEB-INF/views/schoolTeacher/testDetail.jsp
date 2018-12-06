<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<!-- <div id="student-test-detail" class="modal fade" role="dialog"> -->
<div class="modal-dialog" style="width:75%;" id="#">

<div class="table-responsive">
	





		<!-- Modal content-->
		<div class="modal-content">
		<div class="modal-header">
<button type="button" class="close" data-dismiss="modal">&times;</button>
<div class="modal-title">
<h2 class="text-center">Student Test Summary</h2>
</div>




</div>
<div class="modal-body">
<%-- <div style="float:right; margin-right:10px;"><a href="#">Download <img src="${pageContext.request.contextPath}/resources/images/pdf.png"></a></div> --%>
		<div class="row" style="margin-top:30px;"><div class="col-md-4 pull-left">Student Name:<b>${studentAssessmentSummary.user.firstName}</b></div>  <div class="col-md-4">Test Name:<b>${studentAssessmentSummary.assessment.testName} </b></div><div class="col-md-3 pull-right">Test Attempted Date:<b><fmt:formatDate pattern="dd-MM-yyyy" value="${studentAssessmentSummary.attemptDate}" /></b>
		<br></div></div>
		<div class="row" style="margin-top:30px;">
		<table border="0" cellspacing="0" cellpadding="0"
			class="table table-bordered text-center table-head">
			<tr style="background-color:#FF7E00; color:#ffffff;">
				<td>Total Questions</td>
				<td>Correct</td>
				<td>Incorrect</td>
				<td>Skipped</td>

				<td>Marks Obtained</td>
				<td>Average Marks</td>
				<td>Maximum Marks</td>
				<td>Percentage</td>
				<td>Topper's Score</td>
				<td>No. of Students who took the test</td>

			</tr>
			<tr>
				<td>${studentAssessmentSummary.totalQuestion }</td>
				<td>${studentAssessmentSummary.totalCorrect }</td>
				<td>${studentAssessmentSummary.totalIncorrect }</td>
				<td>${studentAssessmentSummary.totalUnAttempted }</td>
				<td><fmt:formatNumber pattern="#"
						value="${studentAssessmentSummary.marks}" /></td>

				<td><fmt:formatNumber pattern="#.##" value="${avgMarks}" /></td>
				<td>${studentAssessmentSummary.assessment.maxMarks}</td>
				<td><fmt:formatNumber pattern="#.##"
						value="${(studentAssessmentSummary.marks/studentAssessmentSummary.assessment.maxMarks)*100}" />%</td>





				<td><fmt:formatNumber pattern="#" value="${TopperAssessmentMarks}" /></td>
				<td>${TotalStudentAttempt }</td>

			</tr>
		</table>
		</div>
		
	</div>

		
			
				<div class="col-md-12">
<div id="container1" class="col-md-5" style="min-width: 410px; height: 400px; margin: 0 auto"></div>
</div>

				<div class="col-md-12 col-sm-12 col-xs-12 mrb20 mrt20">
					<div class="col-md-2 col-sm-3 col-xs-12 correct mrb10">
						<b class="fa fa-thumbs-o-up mrr10 size18"> </b>Correct ( ${studentAssessmentSummary.totalCorrect } )<b
							class="fa fa-check mrr20 sum-ans-right"></b>
					</div>
					<div class="col-md-2 col-sm-3 col-xs-12 Incorrect mrb10">
						<b class="fa fa-thumbs-o-down pull-left mrr10 size18"></b>Incorrect ( ${studentAssessmentSummary.totalIncorrect } )<b
							class="fa fa-remove mrr20 sum-wrong "></b>
					</div>
					<div class="col-md-2 col-sm-3 col-xs-12 unattempted-sub mrb10">Unattampted ( ${studentAssessmentSummary.totalUnAttempted } )
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

						



				</ul>


			
			</div>
		</div>

	</div>
	<!-- </div> -->
	
	
<script type="text/javascript">
$(function () {
    // Create the chart
    $('#container1').highcharts({
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
                    format: '{point.y:.2f}%'
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
                y:${maxPer1},
                drilldown: 'Highest Score'
            }, {
                name: 'Average Score',
                y:${avgPer1},
                drilldown: 'Average Score'
            }, {
                name: 'Lowest Score',
                y: ${minPer1},
                drilldown: 'Lowest Score'
            },{
                name: 'Your Score',
                y: ${(studentAssessmentSummary.marks/studentAssessmentSummary.assessment.maxMarks)*100},
                drilldown: 'Your Score'
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
</script>


	
	
	
	