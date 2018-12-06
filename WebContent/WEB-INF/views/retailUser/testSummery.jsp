<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<jsp:include page="../student/studentHeader.jsp" />
<!-- middle panel -->
<div class="container-fluid bred-cont">
	<div class="container">
		<div class="row">
			<ul class="breadcrumb">
				<li>${test.subject.name}</li>
			</ul>
		</div>
	</div>
</div>


<div class="container-fluid mrt20 question-con">
	<div class="container">
		<div class="row">
			<div class="table-responsive">
				<table border="0" cellspacing="0" cellpadding="0"
					class="table table-bordered text-center table-head">
					<tr>
						<td><strong>Total Question</strong></td>
						<td><strong>Correct</strong></td>
						<td><strong>Incorrect</strong></td>
						<td><strong>Skip</strong></td>
						<td><strong>Score</strong></td>
						<td><strong>Percentage</strong></td>
						<!-- <td><strong>Topper's Score</strong></td> -->
					<!-- 	<td><strong>No. of Students who tooks the test</strong></td> -->
					</tr>
					<tr>
						<td>${test.totalQuestion }</td>
						<td>${test.totalCorrect }</td>
						<td>${test.totalIncorrect }</td>
						<td>${test.totalUnAttempted }</td>
						<td>${test.totalCorrect }</td>
						<td>${test.percentage }</td>
						<!-- <td>Topper score</td> -->
						<!-- <td>student attempt</td> -->
					</tr>
				</table>
			</div>

			<div class="col-md-12 test-summ-con">
				<h2 class="text-center">Test Summary</h2>

				<div class="col-md-12 col-sm-12 col-xs-12 mrb20 mrt20">
<div class="col-md-2 col-sm-3 col-xs-12 correct mrb10"><b class="glyphicon glyphicon-thumbs-up mrr10 size18"> </b>Correct<b class="glyphicon glyphicon-ok mrr20 sum-ans-right"></b> </div>
<div class="col-md-2 col-sm-3 col-xs-12 Incorrect mrb10"><b class="glyphicon glyphicon-thumbs-down pull-left mrr10 size18"></b>Incorrect<b class="glyphicon glyphicon-remove mrr20 sum-wrong "></b> </div>
<div class="col-md-2 col-sm-3 col-xs-12 unattempted-sub mrb10">Unattampted  </div>
<div class="col-md-2  col-sm-3 col-xs-12 bordersu mrb10"> </div>
</div>



				<ul class="list-unstyled test-summ">
					<c:forEach var="sts"
						items="${test.userTestDetailList }"
						varStatus="status1">
						<li>
						
						<c:choose>
								<c:when test="${sts.result eq 1}">
								<div class="sum-ques">${status1.count}.
								${sts.question.description }<div class="btn btn-success btn-xs cursor mrl10 bt-mt">Correct <b class="glyphicon glyphicon-ok "></b></div>
								<c:if test="${not empty sts.question.imagePath}">
									<img
										src="${commonPath}/questionImages/${sts.question.imagePath }" />
								</c:if>
							</div>
								</c:when>
								<c:when test="${sts.result eq 0}">
								<div class="sum-ques">${status1.count}. ${sts.question.description }<div class="btn btn-warning btn-xs cursor mrl10 bt-mt">Unattempted</b></div>
								<c:if test="${not empty sts.question.imagePath}">
									<img
										src="${commonPath}/questionImages/${sts.question.imagePath }" />
								</c:if>
							</div>
								</c:when>
								<c:when test="${sts.result eq 2}">
								<div class="sum-ques">${status1.count}.
								${sts.question.description }<div class="btn btn-danger btn-xs cursor mrl10 bt-mt">Incorrect <b class="glyphicon glyphicon-remove"></b></div>
								<c:if test="${not empty sts.question.imagePath}">
									<img
										src="${commonPath}/questionImages/${sts.question.imagePath }" />
								</c:if>
							</div>
								</c:when>
						
						</c:choose>
							
							
							
							 <c:forEach var="option" items="${sts.question.answers}"
								varStatus="status">

								<c:choose>
									<c:when test="${sts.result eq 1}">
										<c:choose>

											<c:when test="${option.isCorrect eq 1}">
												<div class="sum-ans">
												<div class="sum-ques-left"><b class="glyphicon glyphicon-thumbs-up mrr10 size18"> </b></div>
												<div class="sum-ques-middle">${status.count }. ${option.description }
												<c:if test="${not empty option.imagePath }">
														<br>	
														<img
															src="${commonPath}/questionImages/${option.imagePath }" class="img-responsive"/>
													</c:if></div>
												<div class="sum-ques-right"><b class="glyphicon glyphicon-ok mrr20 sum-ans-right"></b> </div>
												<div class="clearfix"></div>
												</div>
											</c:when>
											<c:otherwise>
												<div class="sum-q"><div class="sum-ques-left">&nbsp;</div>
												<div class="sum-ques-middle">${status.count }. ${option.description }
												<c:if test="${not empty option.imagePath }">
														<br>	
														<img
															src="${commonPath}/questionImages/${option.imagePath }" class="img-responsive"/>
													</c:if></div>
												<div class="sum-ques-right"> </div>
												<div class="clearfix"></div>
												</div>
											</c:otherwise>

										</c:choose>
									</c:when>
									<c:when test="${sts.result eq 0}">
										<c:choose>
											<c:when test="${option.isCorrect eq 1}">
												<div class="unattempted-sub"><div class="sum-ques-left">&nbsp;</div>
												<div class="sum-ques-middle">${status.count }. ${option.description }
												<c:if test="${not empty option.imagePath }">
														<br>	
														<img
															src="${commonPath}/questionImages/${option.imagePath }" class="img-responsive"/>
													</c:if></div>
												<div class="sum-ques-right">&nbsp;</div>
												<div class="clearfix"></div>
												</div>
											</c:when>
											<c:otherwise>
												<div class="sum-q"><div class="sum-ques-left">&nbsp;</div>
												<div class="sum-ques-middle">${status.count }. ${option.description }
												<c:if test="${not empty option.imagePath }">
														<br>	
														<img
															src="${commonPath}/questionImages/${option.imagePath }" class="img-responsive"/>
													</c:if></div>
												<div class="sum-ques-right"> </div>
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
													
													<div class="sum-ans"><div class="sum-ques-left"><b class="glyphicon glyphicon-thumbs-up mrr10 size18"> </b></div>
												<div class="sum-ques-middle">${status.count }. ${option.description }
												<c:if test="${not empty option.imagePath }">
														<br>	
														<img
															src="${commonPath}/questionImages/${option.imagePath }" class="img-responsive"/>
													</c:if></div>
												<div class="sum-ques-right"><b class="glyphicon glyphicon-ok mrr20 sum-ans-right"></b> </div>
												<div class="clearfix"></div>
													</div>

													</c:when>
													<c:otherwise>
													
													
													<div class="unattempted-wrong"><div class="sum-ques-left">&nbsp;</div>
													
													
												<div class="sum-ques-middle">${status.count }. ${option.description }
												<c:if test="${not empty option.imagePath }">
														<br>	
														<img
															src="${commonPath}/questionImages/${option.imagePath }" class="img-responsive"/>
													</c:if></div>
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
													<div class="sum-ans-wrong"><div class="sum-ques-left"><b class="glyphicon glyphicon-thumbs-down pull-left mrr10 size18"></b></div>
												<div class="sum-ques-middle">${status.count }. ${option.description }
												<c:if test="${not empty option.imagePath }">
														<br>	
														<img
															src="${commonPath}/questionImages/${option.imagePath }" class="img-responsive"/>
													</c:if></div>
												<div class="sum-ques-right"><b class="glyphicon glyphicon-remove mrr20 sum-wrong "></b></div>
												<div class="clearfix"></div>
													</div>

													</c:when>
													<c:otherwise>
													<div class="sum-q"><div class="sum-ques-left">&nbsp;</div>
												<div class="sum-ques-middle">${status.count }. ${option.description }
												<c:if test="${not empty option.imagePath }">
														<br>	
														<img
															src="${commonPath}/questionImages/${option.imagePath }" class="img-responsive"/>
													</c:if></div>
												<div class="sum-ques-right">&nbsp;</div>
												<div class="clearfix"></div>
													</div>
													</c:otherwise>
												</c:choose>
											</c:when>
										</c:choose>


									</c:when>
									
								</c:choose>

							</c:forEach>
						</li>

					</c:forEach>

			<!-- 		<li>
						<div class="sum-ques">1. Identify the correct number: Six
							million, four hundred and eleven thousand, two hundred and sixty.</div>
						<div class="sum-ans">
							a. 64110260 <b class="glyphicon glyphicon-ok mrr20 sum-ans-right"></b>
							<div class="clearfix"></div>
						</div>
						<div class="sum-q">b. 64011260</div>
						<div class="sum-q">c. 64110260</div>
					</li>

					<li>
						<div class="sum-ques">2. Place commas as per Indian system:
							78690502.</div>

						<div class="sum-ans-wrong">
							<b class="glyphicon glyphicon-thumbs-down pull-left mrr10 size18"></b>a.
							7,869,0502 <b class="glyphicon glyphicon-remove mrr20 sum-wrong "></b>
							<div class="clearfix"></div>
						</div>

						<div class="sum-ans">
							<b class="glyphicon glyphicon-thumbs-up mrr10 size18"> </b>b.
							7,86,90,502 <b class="glyphicon glyphicon-ok mrr20 sum-ans-right"></b>
							<div class="clearfix"></div>
						</div>
						<div class="sum-ans">c. 78,69,05,02</div>
						<div class="sum-q">d. 786,90,502</div>

					</li>
 -->

				</ul>


			</div>

			<!-- <div class="col-md-12 test-summ-con mrt20">
<h2>Recommended</h2>
<div class="col-md-4 col-sm-4">
<iframe width="100%" height="auto"  src="https://www.youtube.com/embed/pR9N1200Bhg" frameborder="0" allowfullscreen></iframe>
</div>
<div class="col-md-4 col-sm-4">
<iframe width="100%" height="auto" src="https://www.youtube.com/embed/7pxYEsgRUnI" frameborder="0" allowfullscreen></iframe>
</div>
<div class="col-md-4 col-sm-4">
<iframe width="100%" height="auto" src="https://www.youtube.com/embed/lNxLnzD1yKE" frameborder="0" allowfullscreen></iframe>
</div>
<div class="clearfix"></div>
</div> -->


		</div>
	</div>
</div>
<!-- middle panel end -->
<jsp:include page="studentFooter.jsp" />
