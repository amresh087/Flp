<jsp:include page="../parent/parentHeader.jsp" />

<link href="${pageContext.request.contextPath}/resources/css/student/search.css" rel="stylesheet">

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!-- middle panel -->
<div class="container-fluid">
	<div class="row">

		<div class="stu-cont3">

			<div class="stu-board-con3">
				<div class="stu-board-name3">Add your child</div>
				<div class="input-group stu-board-search3 ">
					<input type="text" class="pd15 form-control biginput"
						placeholder="Search by name, email id ..." id="autocomplete">
					<span class="input-group-btn">
						<button class="btn stu-board-bt3" type="button">Search</button>
					</span>
				</div>
			</div>
			<div class="clearfix"></div>
		</div>



	</div>
</div>

<div class="container-fluid stu-cont">
	<div class="container">
		<div class="row">

			<a data-toggle="modal" data-target="#myModal" id="myModelId"></a>



			<div class="col-lg-8 col-md-6 col-sm-12 col-xs-12 mrt10 mrb20">
				<div
					class="col-lg-12 col-md-12 col-sm-12 col-xs-12 recent-active-cont">
					<div class="row">
						<h2>Recent Activities</h2>
						<div class="recent-active-img">
							<img
								src="${pageContext.request.contextPath}/resources/images/recent-icon.png"
								alt="" />
						</div>
						<div class="recent-active-content-cont Favorites-new panel-body2">
							<c:if test="${not empty userActivityList }">
								<c:forEach var="ua" items="${userActivityList }" varStatus="s">

									<div class="parent-content-con">
										<div class="parent-content-content">
											<p>
												<strong>${s.count }. ${ua.subject.name }</strong>
											</p>
											<span>${ua.topic.chapter.name }</span>
											<p>${ua.topic.name }</p>
											<!-- <a class="btn btn-success btn-xs">Keep Practice</a> -->
										</div>

										<div class="parent-content-content-gscore">
											<p>
												<strong>Avg. Score </strong>
											</p>
											<span>${ua.avgSmartScore }</span>
										</div>
										<div class="parent-content-content-gscore">
											<p>
												<strong>G-Score</strong>
											</p>
											<span>${ua.smartScore }</span>
										</div>


									</div>
								</c:forEach>
							</c:if>

							<!-- <div class="parent-content-con">
<div class="parent-content-content">
<p><strong>1. Mathemetics</strong></p> 
<span>Numbers</span>
<p>Basic Math: Number 1 to 10</p>
<a class="btn btn-success btn-xs">Keep Practice</a>
</div>

<div class="parent-content-content-gscore">
<p><strong>Avg. Score </strong> </p>
<span>50</span>
</div>
<div class="parent-content-content-gscore">
<p><strong>G-Score</strong> </p>
<span>50</span>
</div>


</div>
<div class="parent-content-con">
<div class="parent-content-content">
<p><strong>1. Mathemetics</strong></p> 
<span>Numbers</span>
<p>Basic Math: Number 1 to 10</p>
<a class="btn btn-success btn-xs">Keep Practice</a>
</div>

<div class="parent-content-content-gscore">
<p><strong>Avg. Score </strong> </p>
<span>50</span>
</div>
<div class="parent-content-content-gscore">
<p><strong>G-Score</strong> </p>
<span>50</span>
</div>
<div class="parent-content-con">
<div class="parent-content-content">
<p><strong>1. Mathemetics</strong></p> 
<span>Numbers</span>
<p>Basic Math: Number 1 to 10</p>
<a class="btn btn-success btn-xs">Keep Practice</a>
</div>

<div class="parent-content-content-gscore">
<p><strong>Avg. Score </strong> </p>
<span>50</span>
</div>
<div class="parent-content-content-gscore">
<p><strong>G-Score</strong> </p>
<span>50</span>
</div>


</div>
<div class="parent-content-con">
<div class="parent-content-content">
<p><strong>1. Mathemetics</strong></p> 
<span>Numbers</span>
<p>Basic Math: Number 1 to 10</p>
<a class="btn btn-success btn-xs">Keep Practice</a>
</div>

<div class="parent-content-content-gscore">
<p><strong>Avg. Score </strong> </p>
<span>50</span>
</div>
<div class="parent-content-content-gscore">
<p><strong>G-Score</strong> </p>
<span>50</span>
</div>


</div>

</div>




<div class="recent-active-content">
<a href="#"><div class=" pull-right">More...</div></a>
</div>
 -->
						</div>
					</div>
				</div>
			</div>


			<div class="col-lg-4 col-md-6 col-sm-6 col-xs-12 mrt10 mrb20">
				<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 sel-ch-cont">
					<div class="row">
						<h2>Children Details</h2>
						<div class="sel-ch-active">
							<select class="selectChildCls">
								<c:if test="${not empty childrenList}">
									<c:forEach var="user" items="${childrenList }">
										<option value="${user.user1.id }"
											<c:if test="${not empty stuId && stuId eq user.user1.id }">selected="selected" </c:if>>${user.user1.firstName }</option>

									</c:forEach>
								</c:if>
							</select>
							<div class="clearfix"></div>
						</div>
					</div>
				</div>
			</div>

			<div class="col-lg-4 col-md-6 col-sm-6 col-xs-12 mrt10 mrb20">
				<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 stu-pro-cont ">
					<div class="row">
						<h2>Children Details</h2>
						<div class="stu-pro-active panel-body5">
							<c:if test="${not empty childrenList}">
								<c:forEach var="user" items="${childrenList }">
									<div class="stu-profile-cont">
										<a href="javascript:void(0);" id="stuId${user.user1.id }"
											class="stuCls"><img
											src="${pageContext.request.contextPath}/resources/images/child1.jpg"
											alt="" />
											<p>${user.user1.firstName }</p></a>
									</div>
								</c:forEach>
							</c:if>
							<%-- <div class="stu-profile-cont">
<img src="${pageContext.request.contextPath}/resources/images/child1.jpg" alt=""/>
<p>Amit</p>
</div>

<div class="stu-profile-cont">
<img src="${pageContext.request.contextPath}/resources/images/child2.jpg" alt=""/>
<p>Raghav</p>
</div>

<div class="stu-profile-cont">
<img src="${pageContext.request.contextPath}/resources/images/child3.jpg" alt=""/>
<p>Richa</p>
</div> --%>

							<div class="clearfix"></div>
						</div>
					</div>
				</div>
			</div>



			<!-- report panel -->
				<div class="col-lg-12 col-md-6 col-sm-12 col-xs-12 mrt20 mrb20">
					<div
						class="col-lg-12 col-md-12 col-sm-12 col-xs-12 recent-active-cont">
						<div class="row">

							<ul class="nav nav-tabs report">
								<li class="active"><a data-toggle="tab" href="#month4">April</a></li>
								<li><a data-toggle="tab" href="#month5">May</a></li>
								<li><a data-toggle="tab" href="#month6">June</a></li>
								<li><a data-toggle="tab" href="#month7">July</a></li>
								<li><a data-toggle="tab" href="#month8">August</a></li>
								<li><a data-toggle="tab" href="#month9">September</a></li>
								<li><a data-toggle="tab" href="#month10">October</a></li>
								<li><a data-toggle="tab" href="#month11">November</a></li>
								<li><a data-toggle="tab" href="#month12">December</a></li>
								<li><a data-toggle="tab" href="#month1">January</a></li>
								<li><a data-toggle="tab" href="#month2">February</a></li>
								<li><a data-toggle="tab" href="#month3">March</a></li>
							</ul>
							<div class="clearfix"></div>


							<!--tab-cont-->
							<div class="tab-content report-month ">
								<c:if test="${not empty assessmentMap}">
									<c:forEach var="assessment" items="${assessmentMap }"
										varStatus="s">
										<c:set var="ass" value="${assessment.value }" />
										<div id="month${assessment.key }"
											class="tab-pane fade <c:if test="${s.count eq 1 }">in active</c:if>">

											<ul class="nav nav-tabs report-box">

												<li class="active"><a data-toggle="tab"
													href="#E-test${s.count }">E-test Report</a></li>
												<li><a data-toggle="tab" href="#Assignment${s.count }">Assignment</a></li>
												<%--  <li><a data-toggle="tab" href="#Topic${s.count }">Topic</a></li>
            <li><a data-toggle="tab" href="#Resources${s.count }">Resources</a></li> --%>

											</ul>
											<!--tab-cont-->

											<div class="tab-content">

												<!--E-test-QUESTIONS-->
												<div id="E-test${s.count }" class="tab-pane fade in active">
													<c:choose>
														<c:when test="${not empty ass }">
															<c:forEach var="a" items="${ass }" varStatus="s1">
																<c:if test="${a.type eq 1 }">
																	<h2>
																		${a.testName } -
																		<c:forEach var="date" items="${a.assignedAssessment}"
																			end="0">
																			<span> <fmt:formatDate pattern="dd MMM yyyy"
																					value="${date.assignedDate }" />
																			</span>
																		</c:forEach>
																	</h2>
																	<div class="table-responsive pdt20">
																		<table border="0" cellspacing="0" cellpadding="0"
																			class="table table-bordered text-center table-head2">

																			<tr>
																				<td><strong>Total Question</strong></td>
																				<td><strong>Correct</strong></td>
																				<td><strong>Incorrect</strong></td>
																				<td><strong>Score</strong></td>
																				<td><strong>Percentage</strong></td>
																				<td><strong>Topper's Score</strong></td>
																				<td><strong>No. of Students who tooks
																						the test</strong></td>
																				<!--  <td><strong>Result</strong></td> -->
																			</tr>

																			<tr>
																				<c:choose>
																					<c:when test="${not empty a.studentTest }">

																						<c:forEach var="st" items="${a.studentTest}"
																							end="0">
																							<td>${st.totalQuestion }</td>
																							<td>${st.totalCorrect }</td>
																							<td>${st.totalIncorrect }</td>
																							<td>${st.marks }</td>
																							<td>${st.percentage }%</td>
																							<td>${a.maxMarks }</td>
																							<td>${a.count }</td>
																						</c:forEach>
																					</c:when>
																					<c:otherwise>
																						<td colspan="7">No record found</td>
																					</c:otherwise>
																				</c:choose>
																				<!-- <td><a href="#" class="btn btn-danger btn-xs">CHECK</a></td> -->
																			</tr>
																		</table>
																	</div>
																</c:if>

															</c:forEach>
														</c:when>
														<c:otherwise>N/A</c:otherwise>
													</c:choose>



													<!--          <h2> Mathemetics Test  - <span> (25 Nov 2015)</span>	 </h2>
         <div class="table-responsive">
<table  border="0" cellspacing="0" cellpadding="0" class="table table-bordered text-center table-head2">
  <tr>
    <td><strong>Total Question</strong></td>
    <td><strong>Correct</strong></td>
    <td><strong>Incorrect</strong></td>
    <td><strong>Score</strong></td>
    <td><strong>Percentage</strong></td>
    <td><strong>Topper's Score</strong></td>
    <td><strong>No. of Students who tooks the test</strong></td>
    <td><strong>Result</strong></td>
  </tr>
  <tr>
     <td>10</td>
    <td>6</td>
    <td>4</td>
    <td>6.00</td>
    <td>60%</td>
    <td>10.00</td>
    <td>45</td>
    <td><a href="#" class="btn btn-danger btn-xs">CHECK</a></td>
  </tr>
</table>
</div>
 -->
													<!-- <div class="pull-right"><a href="#">More..</a></div> -->
												</div>
												<!--E-test-QUESTIONS-->


												<!--Assignment-PARAMETER-->
												<div id="Assignment${s.count }" class="tab-pane fade">
													<c:choose>
														<c:when test="${not empty ass }">
															<c:forEach var="a" items="${ass }" varStatus="s1">
																<c:if test="${a.type eq 2 }">
																	<h2>
																		${a.testName } -
																		<c:forEach var="date" items="${a.assignedAssessment}"
																			end="0">
																			<span> <fmt:formatDate pattern="dd MMM yyyy"
																					value="${date.assignedDate }" />
																			</span>
																		</c:forEach>
																	</h2>
																	<div class="table-responsive pdt20">
																		<table border="0" cellspacing="0" cellpadding="0"
																			class="table table-bordered text-center table-head2">

																			<tr>
																				<td><strong>Total Question</strong></td>
																				<td><strong>Correct</strong></td>
																				<td><strong>Incorrect</strong></td>
																				<td><strong>Score</strong></td>
																				<td><strong>Percentage</strong></td>
																				<td><strong>Topper's Score</strong></td>
																				<td><strong>No. of Students who tooks
																						the test</strong></td>
																				<!--  <td><strong>Result</strong></td> -->
																			</tr>

																			<tr>
																				<c:choose>
																					<c:when test="${not empty a.studentTest }">

																						<c:forEach var="st" items="${a.studentTest}"
																							end="0">
																							<td>${st.totalQuestion }</td>
																							<td>${st.totalCorrect }</td>
																							<td>${st.totalIncorrect }</td>
																							<td>${st.marks }</td>
																							<td>${st.percentage }%</td>
																							<td>${a.maxMarks }</td>
																							<td>${a.count }</td>
																						</c:forEach>
																					</c:when>
																					<c:otherwise>
																						<td colspan="7">No record found</td>
																					</c:otherwise>
																				</c:choose>
																				<!-- <td><a href="#" class="btn btn-danger btn-xs">CHECK</a></td> -->
																			</tr>
																		</table>
																	</div>
																</c:if>

															</c:forEach>
														</c:when>
														<c:otherwise>N/A</c:otherwise>
													</c:choose>

													<%--  <div class="table-responsive">
     
        <table  cellspacing="0" width="100%" class="table table-bordered ">
         <thead>
      <tr>
            <th>Sr.</th>
            <!-- <th>Select</th> -->
            <th>Test Name</th>
            <th>Grade</th>
            <th>Test Type</th>
            <th>Assignd On</th>
            <th>Tested On</th>
            <th>Status</th>
             <th>Publish Status</th>
        </tr>
         </thead>
        <c:choose>
    	<c:when test="${not empty ass }">
    	<c:forEach var="a" items="${ass }" varStatus="s2">
    	<c:if test="${a.type eq 2 }">
    	<tr>
            <td>${s2.count }</td>
           <!--  <td><input name="" type="checkbox" value="" data-toggle="modal" data-target="#myModal"></td> -->
            <td data-toggle="modal" data-target="#myModal4">${a.testName }</td>
            <td>2</td>

            <td>eTest</td>
            <td>3 July 2015</td>
            <td>16 July 2015</td>
            <td data-toggle="modal" data-target="#myModal2">Assigned</td>
             <td>published</td>
        </tr>
    	</c:if>
    	</c:forEach>
    	</c:when>
    	</c:choose> --%>


													<!--   <tr>
            <td>02</td>
             <td><input name="" type="checkbox" value="" data-toggle="modal" data-target="#myModal"></td>
            <td>Half Yearly Hindi</td>
              <td>2</td>
     
            <td>eTest</td>
            <td>3 July 2015</td>
            <td>16 July 2015</td>
            <td data-toggle="modal" data-target="#myModal2">Assigned</td>
               <td>published</td>
        </tr>
      
       <tr>
            <td>03</td>
             <td><input name="" type="checkbox" value="" data-toggle="modal" data-target="#myModal"></td>
            <td>Pre board Math</td>
              <td>2</td>
    
            <td>eTest</td>
            <td>3 July 2015</td>
            <td>16 July 2015</td>
            <td data-toggle="modal" data-target="#myModal3">Not Assigned</td>
               <td data-toggle="modal" data-target="#myModal8">publishe it</td>
        </tr>
      
       <tr>
            <td>04</td>
             <td><input name="" type="checkbox" value="" data-toggle="modal" data-target="#myModal"></td>
            <td>Pre board Eng</td>
              <td>2</td>
        
            <td>eTest</td>
            <td>3 July 2015</td>
            <td>16 July 2015</td>
            <td data-toggle="modal" data-target="#myModal3">Not Assigned</td>
               <td data-toggle="modal" data-target="#myModal8">publishe it</td>
        </tr>
        
         
       -->


													<!--     </tbody>
</table>
         
		</div> -->


												</div>
												<!--Assignment-PARAMETER-->

												<!--Topic-->
												<%--        <div id="Topic" class="tab-pane fade">
        
<div class="parent-content-con2">
<div class="parent-content-content2">
<p><strong>1. Mathemetics</strong></p> 
<span>Numbers</span>
<p>Basic Math: Number 1 to 10</p>
<a class="btn btn-success btn-xs">Keep Practice</a>
</div>

<div class="parent-content-content-gscore2">
<p><strong>Avg. Score </strong> </p>
<span>50</span>
</div>
<div class="parent-content-content-gscore2">
<p><strong>G-Score</strong> </p>
<span>50</span>
</div>
</div>
<div class="parent-content-con2">
<div class="parent-content-content2">
<p><strong>2. Mathemetics</strong></p> 
<span>Numbers</span>
<p>Basic Math: Number 1 to 10</p>
<a class="btn btn-success btn-xs">Keep Practice</a>
</div>

<div class="parent-content-content-gscore2">
<p><strong>Avg. Score </strong> </p>
<span>50</span>
</div>
<div class="parent-content-content-gscore2">
<p><strong>G-Score</strong> </p>
<span>50</span>
</div>
</div>
<div class="parent-content-con2">
<div class="parent-content-content2">
<p><strong>3. Mathemetics</strong></p> 
<span>Numbers</span>
<p>Basic Math: Number 1 to 10</p>
<a class="btn btn-success btn-xs">Keep Practice</a>
</div>

<div class="parent-content-content-gscore2">
<p><strong>Avg. Score </strong> </p>
<span>50</span>
</div>
<div class="parent-content-content-gscore2">
<p><strong>G-Score</strong> </p>
<span>50</span>
</div>
</div>
<div class="parent-content-con2">
<div class="parent-content-content2">
<p><strong>4. Mathemetics</strong></p> 
<span>Numbers</span>
<p>Basic Math: Number 1 to 10</p>
<a class="btn btn-success btn-xs">Keep Practice</a>
</div>

<div class="parent-content-content-gscore2">
<p><strong>Avg. Score </strong> </p>
<span>50</span>
</div>
<div class="parent-content-content-gscore2">
<p><strong>G-Score</strong> </p>
<span>50</span>
</div>
</div>
<div class="parent-content-con2">
<div class="parent-content-content2">
<p><strong>5. Mathemetics</strong></p> 
<span>Numbers</span>
<p>Basic Math: Number 1 to 10</p>
<a class="btn btn-success btn-xs">Keep Practice</a>
</div>

<div class="parent-content-content-gscore2">
<p><strong>Avg. Score </strong> </p>
<span>50</span>
</div>
<div class="parent-content-content-gscore2">
<p><strong>G-Score</strong> </p>
<span>50</span>
</div>
</div>

<div class="clearfix"></div>
         </div>
        <!--Topic-->
        
        <!--Resources-->
        <div id="Resources" class="tab-pane fade">
        <h2>Resources</h2>
            <ul class="parent-resoure list-unstyled">
            <li> <img src="${pageContext.request.contextPath}/resources/images/word.png" alt="" class="mrr10"/><a href="#">Living and Non- Living Things Worksheet</a></li>
            <li> <img src="${pageContext.request.contextPath}/resources/images/video.png" alt="" class="mrr10"/><a href="#">understanding Math: Adition-adition of decimal</a></li>
            <li> <img src="${pageContext.request.contextPath}/resources/images/ppt.png" alt="" class="mrr10"/><a href="#">understandingMaths: Counting Fingers</a></li>
            <li> <img src="${pageContext.request.contextPath}/resources/images/pdf.png" alt="" class="mrr10"/><a href="#">understandingMaths: Counting Fingers</a></li>
           </ul>
       
         </div> --%>
												<!--Resources-->



												<!--tab-cont-->
											</div>

										</div>
										<!--tab-cont-->


									</c:forEach>

								</c:if>

								<c:if test="${not empty testDetails}">
									<c:forEach var="assessment" items="${testDetails }"
										varStatus="s">
										<c:set var="ass" value="${assessment.value }" />
										<div id="month${assessment.key }"
											class="tab-pane fade <c:if test="${s.count eq 1 }">in active</c:if>">

											<ul class="nav nav-tabs report-box">

												<li class="active"><a data-toggle="tab"
													href="#E-test${s.count }">Test Report</a></li>


											</ul>
											<!--tab-cont-->

											<div class="tab-content">

												<!--E-test-QUESTIONS-->
												<div id="E-test${s.count }" class="tab-pane fade in active">
													<c:choose>
														<c:when test="${not empty ass }">
															<c:forEach var="a" items="${ass }" varStatus="s1">
																<h2>
																	${a.name } -  <span> <fmt:formatDate
																			pattern="dd MMM yyyy" value="${a.createdDate }" />
																	</span>
																</h2>
																<div class="table-responsive pdt20">
																	<table border="0" cellspacing="0" cellpadding="0"
																		class="table table-bordered text-center table-head2">

																		<tr>
																			<td><strong>Total Question</strong></td>
																			<td><strong>Correct</strong></td>
																			<td><strong>Incorrect</strong></td>
																			<td><strong>Unattempted</strong></td>
																			<td><strong>Score</strong></td>
																			<td><strong>Percentage</strong></td>
																		
																		</tr>

																		<tr>
																				
																						<td>${a.totalQuestion }</td>
																						<td>${a.totalCorrect }</td>
																						<td>${a.totalIncorrect }</td>
																						<td>${a.totalUnAttempted }</td>
																						<td>${a.totalCorrect }</td>
																						<td>${a.percentage }%</td>
																						
																			<!-- <td><a href="#" class="btn btn-danger btn-xs">CHECK</a></td> -->
																		</tr>
																	</table>
																</div>

															</c:forEach>
														</c:when>
														<c:otherwise>N/A</c:otherwise>
													</c:choose>



												</div>
												<!--E-test-QUESTIONS-->



											</div>

										</div>


									</c:forEach>

								</c:if>

								<!--create-test-->

								<!--tab-cont-->
							</div>
							<!--tab-cont-->


							<br />
							<br />



						</div>
						<div class="clearfix"></div>
						<!-- /.container-fluid -->


					</div>
				</div>
		</div>

		<!-- report panel end -->






	</div>
</div>

<!-- Modal -->
<div id="myModal" class="modal fade" role="dialog">
	<div class="modal-dialog">

		<!-- Modal content-->
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<div class="modal-title">
					<span>Select Child</span>
				</div>
			</div>
			<div class="modal-body">
				<select id="selectChild" class="selectChildCls">
					<option value="">Select Child</option>
					<c:forEach var="user" items="${childrenList }">
						<option value="${user.user1.id }">${user.user1.firstName }</option>

					</c:forEach>
				</select>

			</div>

		</div>

	</div>
</div>
<form
	action="${pageContext.request.contextPath}/parent/parentDashboard.htm"
	id="parentDashboardForm">
	<input type="hidden" id="stuId" name="studentId" />
</form>
<!-- custom scrollbar  -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/jquery.mCustomScrollbar.css">
<script
	src="${pageContext.request.contextPath}/resources/js/jquery.mCustomScrollbar.concat.min.js"></script>
<script type="text/javascript">
	(function($)
    {
		$(window).load(function()
			    {
				    $(".panel-body5").mCustomScrollbar(
				    {
				        setHeight :162,
				        theme : "dark-3"
				    });
			    });
		
		$(window).load(function()
	    {
		    $(".panel-body2").mCustomScrollbar(
		    {
		        setHeight : 340,
		        theme : "dark-3"
		    });
	    });
	    
	    $(window).load(function()
	    {
		    $(".panel-body3").mCustomScrollbar(
		    {
		        setHeight : 400,
		        theme : "dark-3"
		    });
	    });
    })(jQuery);
    $(document).ready(function()
    {
	    <c:if test="${not empty flag}">
	    $("#myModelId").trigger("click");
	    </c:if>
    });
    $(".selectChildCls").on("change", function()
    {
	    var studentId = $(this).val();
	    if(studentId != null && studentId != "")
	    {
		    $("#stuId").val(studentId);
		    $("#parentDashboardForm").submit();
	    }
    });
    
    $(".stuCls").on("click", function()
    {
	    var studentId = $(this).attr('id').substr(5);
	    if(studentId != null && studentId != "")
	    {
		    $("#stuId").val(studentId);
		    $("#parentDashboardForm").submit();
	    }
    })
    
    // setup autocomplete function pulling from array
    $(document).ready(function()
	{
		$('#autocomplete').autocomplete
		({
			//autoSelectFirst: true,	// To autoselect first element
			minChars: 3,		// Number of characters required to trigger autocomplete
			serviceUrl: '${pageContext.request.contextPath}/parent/getSearchedStudent',
			//paramName: "paramName";	// Default: query
			transformResult: function(response, originalQuery) 
			{
				//alert(originalQuery);
				//alert(JSON.parse(response));
				var suggest = [];
				$.each(JSON.parse(response), function( key, value ) 
				{
				    suggest.push( { value: originalQuery, info: value, data: value.id,  } );
				});
				
				//alert(suggest);
				
				return {suggestions: suggest};
		    },
		    formatResult: function (suggestion, currentValue) 
		    {
		    	var html = '<ul style="list-style-type: none;">'+
		    					'<li>'+
		    						 '<div>'+
		    						 	'<div style="width:80px;height:80px;float:left;text-align:center">'+
			    						 	'<table style="width:100%;height:100%">'+
		    						 			'<tr>'+
		    						 				'<td>';
		    	if(suggestion.info.profilePath != null)
		    							  html = html + '<img src="${pageContext.request.contextPath}/profileImage/'+suggestion.info.profilePath+'" style="width:70px;height:80px" />';
		    	else
		    							  html = html + '<img  style="width:70px;height:80px" src="data:image/jpeg;base64,/9j/4AAQSkZJRgABAQEASABIAAD/7RPoUGhvdG9zaG9wIDMuMAA4QklNBCUAAAAAABAAAAAAAAAAAAAAAAAAAAAAOEJJTQPtAAAAAAAQAEgAAAABAAIASAAAAAEAAjhCSU0EJgAAAAAADgAAAAAAAAAAAAA/gAAAOEJJTQQNAAAAAAAEAAAAHjhCSU0EGQAAAAAABAAAAB44QklNA/MAAAAAAAkAAAAAAAAAAAEAOEJJTQQKAAAAAAABAAA4QklNJxAAAAAAAAoAAQAAAAAAAAACOEJJTQP1AAAAAABIAC9mZgABAGxmZgAGAAAAAAABAC9mZgABAKGZmgAGAAAAAAABADIAAAABAFoAAAAGAAAAAAABADUAAAABAC0AAAAGAAAAAAABOEJJTQP4AAAAAABwAAD/////////////////////////////A+gAAAAA/////////////////////////////wPoAAAAAP////////////////////////////8D6AAAAAD/////////////////////////////A+gAADhCSU0ECAAAAAAAEAAAAAEAAAJAAAACQAAAAAA4QklNBB4AAAAAAAQAAAAAOEJJTQQaAAAAAANXAAAABgAAAAAAAAAAAAAEsAAAB4AAAAARAHcAYQBsAGwAcABhAHAAZQByAC0AMgA0ADEAOAA4ADAAOAAAAAEAAAAAAAAAAAAAAAAAAAAAAAAAAQAAAAAAAAAAAAAHgAAABLAAAAAAAAAAAAAAAAAAAAAAAQAAAAAAAAAAAAAAAAAAAAAAAAAQAAAAAQAAAAAAAG51bGwAAAACAAAABmJvdW5kc09iamMAAAABAAAAAAAAUmN0MQAAAAQAAAAAVG9wIGxvbmcAAAAAAAAAAExlZnRsb25nAAAAAAAAAABCdG9tbG9uZwAABLAAAAAAUmdodGxvbmcAAAeAAAAABnNsaWNlc1ZsTHMAAAABT2JqYwAAAAEAAAAAAAVzbGljZQAAABIAAAAHc2xpY2VJRGxvbmcAAAAAAAAAB2dyb3VwSURsb25nAAAAAAAAAAZvcmlnaW5lbnVtAAAADEVTbGljZU9yaWdpbgAAAA1hdXRvR2VuZXJhdGVkAAAAAFR5cGVlbnVtAAAACkVTbGljZVR5cGUAAAAASW1nIAAAAAZib3VuZHNPYmpjAAAA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" />';
		    		
		    	   					  html = html + '</td>'+
		    						 			'</tr>'+
		    						 		'</table>'+
		    						 	'</div>'+
		    						 	'<div style="float:left">'+
		    						 		'<table>'+
		    						 			'<tr>'+
		    						 				'<td style="width:80px;font-weight: normal; color: #3399FF;">Name:</td>'+
													'<td>'+suggestion.info.firstName+'</td>'+
		    						 			'</tr>'+
			    						 		'<tr>'+
		    						 				'<td style="width:80px;font-weight: normal; color: #3399FF;">Username:</td>'+
													'<td>'+suggestion.info.userName+'</td>'+
		    						 			'</tr>'+
		    						 			'<tr>'+
		    						 				'<td style="width:80px;font-weight: normal; color: #3399FF;">Grade:</td>'+
													'<td>'+suggestion.info.grade.name+'</td>'+
		    						 			'</tr>';
		    	if(suggestion.info.userType == 2)	// Student
		    	{
		    		html = html + 				'<tr>'+
									 				'<td style="width:80px;font-weight: normal; color: #3399FF;">School:</td>'+
													'<td>'+suggestion.info.school.schoolName+'</td>'+
									 			'</tr>';
		    	}
		    		html = html + 			'</table>'+
									 	'</div>'+
									 '</div>'+
								'</li>'+
							'</ul>';
		    	
		    	return html;
		    	//alert(suggestion);
		    },
		    onSelect: function (suggestion) 
		  	{
		    	//$('#autocomplete').val('');
		    	
		     	if(confirm("Do you want to send request to this student?") == true)
		     	{
		     		var theURL = '${pageContext.request.contextPath}/parent/addSearchedStudent'; 
			     	//var thehtml = 'http://google.com/search?q='+suggestion.data;			     	
		     		//window.location.href = thehtml;
		     		
		     		$.ajax
		        	({
		                type: 'POST',
		                url: theURL,
		                data: {"studentId": suggestion.data },
		                success: function(data)
		                {
		                	if(data == "success")
		                		alert("Request has been sent successfully");
		                	else if(data == "fail")
		                		alert("Something went wrong, please try again");
		                	else if(data == "alreadyMapped")
		                		alert("Student already added");
		                	else if(data == "alreadySent")
		                		alert("Request already sent to student");
		                }
		            });
		     	}
		    }
		});
	});
</script>

<!-- middle panel end -->
<jsp:include page="../student/studentFooter.jsp" />