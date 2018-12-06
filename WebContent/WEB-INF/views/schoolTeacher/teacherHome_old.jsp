<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:include page="schoolTeacherHeader.jsp">
	<jsp:param value="dashboard" name="header" />
</jsp:include>

<div id="page-wrapper">

	<div class="container-fluid">

		<!-- Page Heading -->
		<div class="row">
			<div class="col-md-12">
				<h1 class="page-header">Dashboard</h1>
				<ol class="breadcrumb">
					<li><a href="#">Home</a></li>
					<li class="active">Dashboard</li>


				</ol>
			</div>
		</div>
		<!-- /.row -->


		<div id="team" class="owl-carousel owl-theme">


			<div class="item">

				<div class="thumbnail">
					<div class="caption">
						<h3>Content Library</h3>

						<div class="ih-item circle effect1">

							<a
								href="${pageContext.request.contextPath}/schoolTeacher/getManageTocHome.htm">
								<div class="spinner"></div>
								<div class="img">
									<img
										src="${pageContext.request.contextPath}/resources/images/1.jpg">
								</div>
								<div class="info">
									<div class="info-back">

										   <p> View School TOC, Add chpater, topic and document or video resources.</p> 
									</div>
								</div>

							</a>
						</div>
					</div>
				</div>
			</div>
			<div class="item">

				<div class="thumbnail">
					<div class="caption">
						<h3>Create Test</h3>



						<div class="ih-item circle effect1">

							<a
								href="${pageContext.request.contextPath}/schoolTeacher/getCreateAssessment.htm">
								<div class="spinner"></div>
								<div class="img">
									<img
										src="${pageContext.request.contextPath}/resources/images/3.jpg">
								</div>
								<div class="info">
									<div class="info-back">
									<p>Choose Questions, set test parameters and publish test to students.</p>
									</div>
								</div>

							</a>
						</div>
					</div>
				</div>
			</div>
			<div class="item">

				<div class="thumbnail">
					<div class="caption">
						<h3>Assign Task</h3>

						<div class="ih-item circle effect1">

							<a
								href="${pageContext.request.contextPath}/schoolTeacher/getAssignedTaskDetails.htm">
								<div class="spinner"></div>
								<div class="img">
									<img
										src="${pageContext.request.contextPath}/resources/images/4.jpg">
								</div>
								<div class="info">
									<div class="info-back">

										  <p>Assign test, Topic and resources to the students</p> 
									</div>
								</div>

							</a>
						</div>
					</div>
				</div>
			</div>
			<div class="item">

				<div class="thumbnail">
					<div class="caption">
						<h3>Reports</h3>

						<div class="ih-item circle effect1">

							<a href="${pageContext.request.contextPath}/schoolTeacher/getAssessmentReports.htm">
								<div class="spinner"></div>
								<div class="img">
									<img
										src="${pageContext.request.contextPath}/resources/images/2.jpg">
								</div>
								<div class="info">
									<div class="info-back">
									<p>Upcoming test, Completed test , Task assigned and Student performance report</p>
									</div>
								</div>

							</a>
						</div>
					</div>
				</div>
			</div>

		</div>
		<div class="clearfix"></div>
		<div class="col-md-12"></div>
	</div>
	<!-- /.container-fluid -->
	<div class="row">

		<div class=" col-md-12">
			<ul class="nav nav-tabs dashTab">

                <li class="active"><a data-toggle="tab" href="#menu1">Upcoming Test</a></li>
               <li><a data-toggle="tab" href="#ongoing">Ongoing Test</a></li>
				<li><a data-toggle="tab" href="#home">Completed	Test</a></li>
			</ul>


			<div class="clearfix"></div>
			<!--tab-cont-->

			<div class="tab-content">

				<br />
				<!--create-test-->
				<div id="home" class="tab-pane fade">
					<div class="panel panel-default"
						style="overflow: scroll; overflow-x: hidden; height: 300px;">
						<div class="panel-heading assign-res-bg">
							<h3 class="panel-title">
								<i class="fa fa-check-circle"></i> Completed Test
							</h3>
						</div>
						<div class="panel-body">
							<div class="table-responsive">
								<c:choose>
									<c:when test="${not empty completedTest && isCompleted}">
									
										<table class="table table-bordered table-hover table-striped">
											<thead>
												<tr>

													<th>Test Name</th>
													<th>Student's Attampted</th>
													<th>Avg. Score</th>
													<th>Test Date</th>

												</tr>
											</thead>
											<tbody>
												<c:forEach items="${completedTest}" var="cTest">
												<c:if test="${cTest.flag == 2}">
													<tr>

														<td>${cTest.subject.name}:${cTest.grade.name},
															${cTest.testName}</td>
														<td>${fn:length(cTest.studentTest)}/${fn:length(cTest.assignedAssessment)}</td>
														<td>${cTest.average}</td>
														<td><fmt:formatDate value="${cTest.startDate}"
																pattern="dd-MM-yyyy" /></td>

													</tr>
                                                </c:if>
												</c:forEach>

											</tbody>
										</table>
									</c:when>

									<c:otherwise>
                                 No Record Found.
                                </c:otherwise>
								</c:choose>
							</div>
							<br> <br>
						</div>
					</div>

				</div>
				
				
				
				<div id="ongoing" class="tab-pane fade">
					<div class="panel panel-default"
						style="overflow: scroll; overflow-x: hidden; height: 300px;">
						<div class="panel-heading assign-res-bg">
							<h3 class="panel-title">
								<i class="fa fa-check-circle"></i> Ongoing Test
							</h3>
						</div>
						<div class="panel-body">
							<div class="table-responsive">
								<c:choose>
									<c:when test="${not empty completedTest && isOngoing}">
										<table class="table table-bordered table-hover table-striped">
											<thead>
												<tr>

													<th>Test Name</th>
													<th>Student's Attampted</th>
													<th>Avg. Score</th>
													<th>Test Date</th>

												</tr>
											</thead>
											<tbody>
												<c:forEach items="${completedTest}" var="cTest">
												<c:if test="${cTest.flag == 1}">
													<tr>

														<td>${cTest.subject.name}:${cTest.grade.name},
															${cTest.testName}</td>
														<td>${fn:length(cTest.studentTest)}/${fn:length(cTest.assignedAssessment)}</td>
														<td>${cTest.average}</td>
														<td><fmt:formatDate value="${cTest.startDate}"
																pattern="dd-MM-yyyy" /></td>

													</tr>
                                                   </c:if>
												</c:forEach>

											</tbody>
										</table>
									</c:when>

									<c:otherwise>
                                 No Record Found.
                                </c:otherwise>
								</c:choose>
							</div>
							<br> <br>
						</div>
					</div>

				</div>
				
				<!--create-test-->

				<div id="menu1" class="tab-pane fade in active"
					style="overflow-x: scroll; overflow-x: hidden; height: 300px;">

					<div class="panel panel-default">
						<div class="panel-heading assign-topic-bg">
							<h3 class="panel-title">
								<i class="fa fa-check-circle"></i> Upcoming Test
							</h3>
						</div>
						<div class="panel-body">

							<c:choose>
								<c:when test="${not empty upcomingTestList}">
									<div class="list-group">
										<c:forEach items="${upcomingTestList}" var="assessmentUp">
											<a href="#" class="list-group-item"> <c:choose>
													<c:when test="${assessmentUp.type == 1}">
														<span class="badge eTest"><abbr> e-Test </abbr></span>
													</c:when>
													<c:otherwise>
														<span class="badge assignment">Assignment</span>
													</c:otherwise>
												</c:choose> <i class="fa fa-book"></i> ${assessmentUp.subject.name}:
												${assessmentUp.grade.name}, ${assessmentUp.testName}, &nbsp;<fmt:formatDate
													value="${assessmentUp.startDate}" pattern="dd-MM-yyyy" />
											</a>

										</c:forEach>

									</div>
								</c:when>
								<c:otherwise>
                            No Record Found
                            </c:otherwise>
							</c:choose>
						</div>
					</div>
				</div>

			</div>
		</div>
	</div>
</div>
<!-- /#page-wrapper -->

<!-- /#wrapper -->

