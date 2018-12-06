
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div class="row">
	<div class="table-responsive">
	<%-- <div style="float:right; margin-right:10px;"><a href="#">Download <img src="${pageContext.request.contextPath}/resources/images/pdf.png"></a></div> --%>
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
				<td>${maxAssessmentMarks}</td>
				<td><fmt:formatNumber pattern="#"
						value="${(studentAssessmentSummary.marks/maxAssessmentMarks)*100}" /></td>





				<td><fmt:formatNumber pattern="#" value="${maxAssessmentMarks}" /></td>
				<td>${TotalStudentAttempt }</td>

			</tr>
		</table>
	</div>

	<div class="col-md-12 test-summ-con">
	<!-- 	<h2 class="text-center">Test Summary</h2> -->

		<div class="col-md-12 col-sm-12 col-xs-12 mrb20 mrt20">
			<div class="col-md-2 col-sm-3 col-xs-12 correct mrb10">
				<b class="fa fa-thumbs-o-up mrr10 size18"> </b>Correct<b
					class="fa fa-check mrr20 sum-ans-right"></b>
			</div>
			<div class="col-md-2 col-sm-3 col-xs-12 Incorrect mrb10">
				<b class="fa fa-thumbs-o-down pull-left mrr10 size18"></b>Incorrect<b
					class="fa fa-times mrr20 sum-wrong "></b>
			</div>
			<div class="col-md-2 col-sm-3 col-xs-12 unattempted-sub mrb10">Unattampted
			</div>
			<div class="col-md-2  col-sm-3 col-xs-12 bordersu mrb10"></div>
		</div>



		<ul class="list-unstyled test-summ">
			<c:forEach var="sts" items="${studentAssessmentSummary.testDeatail }"
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
									Incorrect <b class="fa fa-times"></b>
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
												<b class="fa fa-thumbs-o-up mrr10 size18"> </b>
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
														<b class="fa fa-times mrr20 sum-wrong "></b>
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