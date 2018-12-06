<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%-- <jsp:include page="studentHeader.jsp" />  --%>
<jsp:include page="../student/studentHeader.jsp" />


<link href="${pageContext.request.contextPath}/resources/css/student/search.css" rel="stylesheet">


<!-- middle panel -->
<div class="container-fluid">
	<div class="row">

		<div class="stu-cont2">

			<div class="stu-board-con">
				<div class="stu-board-name">Search Any Topic</div>
				<div class="input-group stu-board-search ">
					<input type="text" class="pd15 form-control biginput" placeholder="Topic name here..." id="autocomplete"> <span class="input-group-btn">
						<button class="btn stu-board-bt" type="button">Search</button>
					</span>
				</div>
				<div class="stu-board-upgrade-bt">
					<a href="${pageContext.request.contextPath}/payment/upgradeAccount.htm">
						<img src="${pageContext.request.contextPath}/resources/images/upgrade-bt.png" alt="" />
					</a>
				</div>
			</div>
			<div class="clearfix"></div>
		</div>



	</div>
</div>

<div class="container-fluid stu-cont">
	<div class="container">
		<div class="row">


			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 ">
				<div class="table-responsive">
					<table width="100%" border="0" cellspacing="0" cellpadding="0" class="table table-bordered table-hover table-striped student-learning-head-sbj">
						<thead>
							<tr>
								<th height="50">Subjects</th>
								<th>Chapter</th>
								<th>Topic</th>
								<th>G-score</th>
								<th>Avg. G-score</th>
								<th>Activity</th>
							</tr>
						</thead>

						<tbody>
							<c:if test="${not empty userActivityList }">
								<c:forEach var="activity" items="${userActivityList }" varStatus="f">
									<%-- <c:if test="${activity.smartScore lt 100 }"> --%>
										<tr>
											<td height="40">${activity.subject.name }</td>
											<td>${activity.topic.chapter.name }</td>
											<td>${activity.topic.name }</td>
											<td>
												<c:choose>
													<c:when test="${not empty activity.smartScore}">
   ${ activity.smartScore}
    </c:when>
													<c:otherwise>0</c:otherwise>
												</c:choose>
											</td>
											<td>${activity.avgSmartScore }</td>
											<td>
												<a href="${pageContext.request.contextPath}/student/getQuestionAssessmentPage.htm?topicId=${activity.topic.id }&chapterId=${activity.topic.chapter.id }&sId=${activity.subject.id}&subjectName='${activity.subject.name }&topicName=${activity.topic.name}">
													<c:choose>
														<c:when test="${activity.smartScore eq 0 }">Start Learning</c:when>
														<c:when test="${activity.smartScore gt 0 && activity.smartScore lt 40}">Keep Learning</c:when>
														<c:when test="${activity.smartScore ge 40 }">Continue Practice</c:when>

													</c:choose>

												</a>
											</td>
										</tr>
								<%-- 	</c:if> --%>
								</c:forEach>

							</c:if>

							<c:if test="${not empty suggestedTopicLst }">
								<c:forEach var="activity" items="${suggestedTopicLst }">
									<c:if test="${activity.smartScore lt 100 }">
										<tr>
											<td height="40">${activity.chapter.subject.name }</td>
											<td>${activity.chapter.name }</td>
											<td>${activity.name }</td>
											<td>
												<c:choose>
													<c:when test="${not empty activity.smartScore}">
													   ${ activity.smartScore}
													</c:when>
													<c:otherwise>0</c:otherwise>
												</c:choose>
											</td>
											<td>${activity.avgSmartScore }</td>
											<td>
												<a href="${pageContext.request.contextPath}/student/getQuestionAssessmentPage.htm?topicId=${activity.id }&chapterId=${activity.chapter.id }&sId=${activity.chapter.subject.id}&subjectName='${activity.chapter.subject.name }&topicName=${activity.name}">
													<c:choose>
														<c:when test="${activity.smartScore eq 0 }">Start Learning</c:when>
														<c:when test="${activity.smartScore gt 0 && activity.smartScore lt 40}">Keep Learning</c:when>
														<c:when test="${activity.smartScore ge 40 && activity.smartScore lt 100}">Continue Practice</c:when>

													</c:choose>

												</a>
											</td>
										</tr>
									</c:if>
								</c:forEach>

							</c:if>
						</tbody>

						<!-- <tr>
							<td height="40">Mathemetics</td>
							<td>Basic Math: Number 1 to 10</td>
							<td>Basic Math: Number 1 to 10</td>
							<td>50</td>
							<td>20</td>
							<td>
								<a href="#">Keep learning</a>
							</td>
						</tr>
						<tr>
							<td height="40">Mathemetics</td>
							<td>Basic Math: Number 1 to 10</td>
							<td>Basic Math: Number 1 to 10</td>
							<td>50</td>
							<td>20</td>
							<td>
								<a href="#">Keep learning</a>
							</td>
						</tr>
						<tr>
							<td height="40">Mathemetics</td>
							<td>Basic Math: Number 1 to 10</td>
							<td>Basic Math: Number 1 to 10</td>
							<td>50</td>
							<td>20</td>
							<td>
								<a href="#">Challenge yourself</a>
							</td>
						</tr>
						<tr>
							<td height="40">Mathemetics</td>
							<td>Basic Math: Number 1 to 10</td>
							<td>Basic Math: Number 1 to 10</td>
							<td>50</td>
							<td>20</td>
							<td>
								<a href="#">Keep learning</a>
							</td>
						</tr>
						<tr>
							<td height="40">Mathemetics</td>
							<td>Basic Math: Number 1 to 10</td>
							<td>Basic Math: Number 1 to 10</td>
							<td>50</td>
							<td>20</td>
							<td>
								<a href="#">Challenge yourself</a>
							</td>
						</tr>
						<tr>
							<td height="40">Mathemetics</td>
							<td>Basic Math: Number 1 to 10</td>
							<td>Basic Math: Number 1 to 10</td>
							<td>50</td>
							<td>20</td>
							<td>
								<a href="#">Keep learning</a>
							</td>
						</tr> -->
					</table>


				</div>
			</div>


			<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12 mrt20 mrb20">
				<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 recent-active-cont">
					<div class="row">
						<h2>Favorites</h2>
						<div class="recent-active">
							<div class="recent-active-img">
								<img src="${pageContext.request.contextPath}/resources/images/favorite-icon.png" alt="" />
							</div>
							<div class="recent-active-content-cont Favorites-new panel-body9">
								<c:if test="${not empty favTopicList }">
									<c:forEach var="favTopic" items="${favTopicList }" varStatus="f">
										<div class="stu-recent-active-content-con">
											<div class="stu-recent-active-content">
												<p>
													<strong>${f.count }. ${favTopic.subject.name }</strong>
												</p>
												<span>${favTopic.chapter.name }</span>
												<p>${favTopic.topic.name }</p>
												<a class="btn btn-success btn-xs" href="${pageContext.request.contextPath}/student/getQuestionAssessmentPage.htm?topicId=${favTopic.topic.id }&chapterId=${favTopic.chapter.id }&sId=${favTopic.subject.id}&subjectName='${favTopic.subject.name }&topicName=${favTopic.topic.name}">
													<c:choose>
														<c:when test="${favTopic.smartScore eq 0 }">Start Learning</c:when>
														<c:when test="${favTopic.smartScore gt 0 && favTopic.smartScore lt 40}">Keep Learning</c:when>
														<c:when test="${favTopic.smartScore ge 40 && favTopic.smartScore lt 100}">Continue Practice</c:when>
														<c:otherwise>Take the Challenge</c:otherwise>
													</c:choose>

												</a>



											</div>
											<div class="stu-recent-active-content-gscore">
												<p>
													<strong>G-Score</strong>
												</p>
												<span><c:choose>
														<c:when test="${not empty favTopic.smartScore}">
   ${ favTopic.smartScore}
    </c:when>
														<c:otherwise>0</c:otherwise>
													</c:choose></span>
											</div>
										</div>
									</c:forEach>
									<!-- 	<div class="recent-active-content">
										<a href="#" class=" pull-right mrr20">More...</a>
									</div> -->
								</c:if>

								<c:if test="${ empty favTopicList }">
							Their is no favorite topic yet.For favorite topic, please go to subject details page.
								</c:if>
								<!-- <div class="stu-recent-active-content-con">
									<div class="stu-recent-active-content">
										<p>
											<strong>1. Mathemetics</strong>
										</p>
										<span>Numbers</span>
										<p>Basic Math: Number 1 to 10</p>
										<a class="btn btn-success btn-xs">Keep Practice</a>
									</div>

									<div class="stu-recent-active-content-gscore">
										<p>
											<strong>G-Score</strong>
										</p>
										<span>50</span>
									</div>
								</div>
								<div class="stu-recent-active-content-con">
									<div class="stu-recent-active-content">
										<p>
											<strong>1. Mathemetics</strong>
										</p>
										<span>Numbers</span>
										<p>Basic Math: Number 1 to 10</p>
										<a class="btn btn-success btn-xs">Keep Practice</a>
									</div>

									<div class="stu-recent-active-content-gscore">
										<p>
											<strong>G-Score</strong>
										</p>
										<span>50</span>
									</div>
								</div>

								<div class="stu-recent-active-content-con">
									<div class="stu-recent-active-content">
										<p>
											<strong>1. Mathemetics</strong>
										</p>
										<span>Numbers</span>
										<p>Basic Math: Number 1 to 10</p>
										<a class="btn btn-success btn-xs">Keep Practice</a>
									</div>

									<div class="stu-recent-active-content-gscore">
										<p>
											<strong>G-Score</strong>
										</p>
										<span>50</span>
									</div>
								</div>

								<div class="stu-recent-active-content-con">
									<div class="stu-recent-active-content">
										<p>
											<strong>1. Mathemetics</strong>
										</p>
										<span>Numbers</span>
										<p>Basic Math: Number 1 to 10</p>
										<a class="btn btn-success btn-xs">Keep Practice</a>
									</div>

									<div class="stu-recent-active-content-gscore">
										<p>
											<strong>G-Score</strong>
										</p>
										<span>50</span>
									</div>
								</div>
 -->

							</div>

						</div>
					</div>
				</div>
			</div>

			<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12 mrt20 mrb20">
				<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 recent-active-cont">
					<div class="row">

						<h2>
							<a href="${pageContext.request.contextPath}/user/getCreateTestPage.htm"> Create Your Own Test</a>
						</h2>


						<div class="recent-active create-test-new panel-body10">
							<c:if test="${not empty testDetails }">
								<c:forEach var="test" items="${testDetails }">


									<c:choose>
										<c:when test="${test.status eq 0}">
											<a href="${pageContext.request.contextPath}/user/attemptTest.htm?testId=${test.id}">
										</c:when>
										<c:otherwise>
											<a href="${pageContext.request.contextPath}/user/getTestSummery.htm?testId=${test.id}">
										</c:otherwise>
									</c:choose>

									<div class="stu-create-test-con">
										<div class="stu-create-test-part">
											<p>
												<strong>${test.name }</strong>
											</p>
											<span> <c:choose>
													<c:when test="${test.status eq 1 }">
														<img src="${pageContext.request.contextPath}/resources/images/attem-icon.png" alt="" />
													</c:when>
													<c:otherwise>
														<img src="${pageContext.request.contextPath}/resources/images/unattem-icon.png" alt="" />
													</c:otherwise>
												</c:choose>
											</span>


										</div>
										<div class="stu-create-test-part">
											<p>
												<strong>Total Question</strong>
											</p>
											<span class="totalques">${test.totalQuestion }</span>
										</div>
										<div class="stu-create-test-part2">
											<p>
												<strong>Correct / Incorrect / Unattempted</strong>
											</p>
											<span class="correct-ans">${test.totalCorrect }</span> <span class="correct-divide">/</span> <span class="correct-inc">${test.totalIncorrect }</span> <span class="correct-divide">/</span> <span class="unattempt-ans">${test.totalUnAttempted }</span>
										</div>

									</div>
									</a>
								</c:forEach>
								<!-- 	<div class="recent-active-content">
									<a href="#" class=" pull-right mrr20">More...</a>
								</div> -->

							</c:if>

							<c:if test="${ empty testDetails }">
							No Test Created !!! Please click on <a href="${pageContext.request.contextPath}/user/getCreateTestPage.htm">
									<b>Create Your Own Test</b>
								</a>link for create test.
							</c:if>






						</div>
					</div>
				</div>
			</div>

		</div>
	</div>
</div>

<!-- middle panel end -->
<script>
$(function()
{
	var topics = 
	[
		<c:forEach var="topic" items="${searchTopics}">
			{value: '${topic.name} [${topic.subject.name}]', topicId: '${topic.id}', chapterId: '${topic.chapterId}', subjectId: '${topic.subjectId}', subjectName:'${topic.subject.name}', topicName: '${topic.name}'},
		</c:forEach>
	];
	  
	// setup autocomplete function pulling from array
	$('#autocomplete').autocomplete
	({
	  	lookup: topics,
	  	onSelect: function (suggestion) 
	  	{
	     	var thehtml = '${pageContext.request.contextPath}/student/getQuestionAssessmentPage.htm?topicId='+suggestion.topicId+'&chapterId='+suggestion.chapterId+'&sId='+suggestion.subjectId+'&subjectName='+suggestion.subjectName+'&topicName='+suggestion.topicName; 
	     	window.location.href = thehtml;
	   }
	});
});
</script>


<jsp:include page="studentFooter.jsp" />
