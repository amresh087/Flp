<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<jsp:include page="schoolTeacherHeader.jsp">
<jsp:param value="createTest" name="header"/>
</jsp:include>

<script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular.min.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/dateTime/jquery.datetimepicker.css" />
<script src="${pageContext.request.contextPath}/resources/dateTime/jquery.datetimepicker.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/jquery-ui.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/jquery.popupoverlay.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/jstorage.js"></script>
<style>
#edit-test {
    -webkit-transform: scale(0.8);
       -moz-transform: scale(0.8);
        -ms-transform: scale(0.8);
            transform: scale(0.8);
}
.popup_visible #edit-test {
    -webkit-transform: scale(1);
       -moz-transform: scale(1);
        -ms-transform: scale(1);
            transform: scale(1);
}



#select-student {
    -webkit-transform: scale(0.8);
       -moz-transform: scale(0.8);
        -ms-transform: scale(0.8);
            transform: scale(0.8);
}
.popup_visible #select-student {
    -webkit-transform: scale(1);
       -moz-transform: scale(1);
        -ms-transform: scale(1);
            transform: scale(1);
}
 .well {
        box-shadow: 0 0 10px rgba(0,0,0,0.3);
        display:none;
        margin:1em;
    }
    pre.prettyprint {
        padding: 9px 14px;
    }
    .fulltable {
        max-width: 100%;
        overflow: auto;
    }
    .container {
        padding-left: 0;
        padding-right: 0;
    }
    .lineheight {
        line-height: 3em;
    }
</style>
<body ng-app="">









	<!-- Modal -->
	 <div id="show-student" class="modal fade" role="dialog">
		<div class="modal-dialog">

			Student Details
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<div class="modal-title">
						<span>Student</span>
					</div>
				</div>
				
				<div class="modal-body">
					<table width="90%" border="0" cellspacing="0" cellpadding="0"
						class="topic-per table table-bordered table-hover table-condensed" id="studentLst">
						
					</table>



				</div>
				<span id="dateErr" style="color: red;"></span>
				<div class="modal-footer">
					<div class="btn btn-primary btn-md" id=""><input type="button" id="updateAssessment" onclick="updateAssessment()">Save Changes</div>
				</div>
				
			</div> 
 </div>
		</div>
	<!-- Modal end -->








<div id="page-wrapper" style="padding-top:5%">
<div class="container-fluid">
<!-- Page Heading -->
<div class="row">
<div class="col-md-12">
<jsp:include page="teacherSubjectHeader.jsp">
		<jsp:param value="dashboard" name="header" />
		</jsp:include>
</div>
</div>
</div>
<!-- /.row -->
<div class="clearfix"></div>


</div>
<div class=" col-md-12">
<ul class="nav nav-tabs creatnav">
<li <c:if test="${empty param.tab}"> class="active" </c:if> ><a data-toggle="tab" href="#home">
<span class="fa fa-angle-double-down"> &nbsp;</span>Create Test</a></li>
<li <c:if test="${param.tab == 'mTest'}"> class="active" </c:if> ><a data-toggle="tab" href="#menu1" id="funkId">
<span class="fa fa-cog"> &nbsp;</span>Manage Test</a></li>
<input type="hidden" id="dateSession" value="${sessionDate}">
</ul>
<div class="clearfix"></div>
<!--tab-cont-->
<div class="tab-content">
<!--create-test-->
<div id="home" class="tab-pane fade <c:if test="${empty param.tab}"> in active </c:if>">
<ul class="nav nav-tabs creatnav" style="background: none!important;">
<%-- <li <c:if test="${empty param.tab}"> class="active" </c:if> ><a data-toggle="tab" href="#testmanually" style="padding: 10px 20px ! important; font-size: 12px;" >
					Create Test Manually</a></li> --%>
				<%-- <li <c:if test="${param.tab == 'mTest'}"> class="active" </c:if> ><a data-toggle="tab" href="#autoques" style="padding: 10px 20px ! important; font-size: 12px;" >
					Auto Test</a></li> --%>
	</ul>
			<div class="clearfix"></div>
			<!--tab-cont-->
						<!------------Creat Test Manually------------->
			<div class="tab-content">

			<div id="testmanually" style="background:#ffffff; padding: 10px;" class="tab-pane fade <c:if test="${empty param.tab}"> in active </c:if>">
			
			<ul class="nav nav-tabs own-nav" style="background-colo:#ffffff; !important; border-bottom:none;">
			<input type="hidden" value="${param.subId}" id="subIdH"/>
			<input type="hidden" value="${param.grdId}" id="grdIdH"/>
			<input type="hidden" value="${param.secId}" id="secIdH"/>
						<li class="active"><a href="#select-ques" id="tab1"
							style="display: none;" hidden="hidden" data-toggle="tab"></a><a
							href="#"><!-- <span class="create-circle">01</span> -->Select Question</a></li>
						<li><a data-toggle="tab" href="#set-test" id="tab2"
							style="display: none;"></a><a href="#"><!-- <span
								class="create-circle orng-circle">02</span> -->Set Test Parameters</a></li>
						<li><a data-toggle="tab" href="#preview" id="tab3"
							style="display: none;"></a><a href="#"><!-- <span
								class="create-circle blue-circle">03</span> -->Preview Test</a></li>
						<!--  <li><a data-toggle="tab" href="#publish" id="tab4"
							style="display: none;"></a><a href="#"><span
								class="create-circle green-circle">04</span>Save Test</a></li> 
					</ul> -->
					<!--tab-cont-->

					<div class="tab-content">

						<!--SELECT-QUESTIONS-->
<div id="select-ques" class="tab-pane fade in active">
<div class="row">
<div class="col-md-12"><script src="${pageContext.request.contextPath}/resources/js/jstorage.js"></script>
<input type="checkbox" id='' class="createdBy" value="2"/><label>Self Created</label>
<input type="checkbox" id='' class="createdBy"value="3"/><label>CTLS Library</label>
<input type="checkbox" id='' class="createdBy"value="4"/><label>Other Teachers</label>
</div>
<%-- <div class="in-box">
<div class="col-md-3">
<select id='selectGrade' onchange="populateSubject(this.value)">
<option value='' selected="selected">Select Grade</option>
<c:forEach var="ts" items="${teacherSection}">
<option class='clsGrade' value='${ts.section.sessionSchoolGrade.grade.id}'>${ts.section.sessionSchoolGrade.grade.name}</option>
</c:forEach>
</select>
</div>
<div class="col-md-3">
<select id='selectSubject' onChange="getChapters(this.value)">
<option>Select Subject</option>
</select>
									</div>
									<div class="col-md-3">
										<select id="createdBy">
											<option value=''>Show Questions Created By</option>
											<option value='1'>All</option>
											<option value='2'>Me</option>
										</select>
									</div>
								</div>  --%>
								<div class="col-md-6">
									<div class=" chk">

										<label  ><strong>Chapter</strong></label> <input type="checkbox"
											onchange="checkAllChapters(this)" id='selectAllChapter' /><label for="selectAllChapter">Select
											All</label>

									</div>

									<div class="row">
										<div class="col-md-12">
											<div class="selct-chap  mCustomScrollbar">
												<div class="table-responsive panel-body3">
													<table class="table table-bordered table-hover table-striped ">
														<tbody id="chapters">
												<script src="${pageContext.request.contextPath}/resources/js/jstorage.js"></script>		</tbody>
													</table>
												</div>
											</div>

										</div>
									</div>

								</div>

								<div class="col-md-6">

									<div class=" chk">
										<label><strong>Topic</strong></label> <input type="checkbox"
											id='selectAllTopicId' onChange="selectAllTopic(this)" /><label for="selectAllTopic">Select
											All</label>
									</div>

									<div class="row">
										<div class="col-md-12">

											<div class="selct-chap  mCustomScrollbar">

												<div class="table-responsive panel-body3">

													<table
														class="table table-bordered table-hover table-striped">

														<tbody id="topics">
														</tbody>

													</table>
												</div>
											</div>
										</div>
									</div>

								</div>
								<div class="clearfix"></div>
								<br>
								<div class="col-md-3" style="margin-top:15px;">
									<button type="submit" class="btn btn-primary"
										onclick="getQuestion(1)" id="btnGetQuestion">Manual Creation</button>
								</div>
								<div class="col-md-2" style="margin-top:15px;">
									<button type="submit" class="btn btn-primary" onclick="getQuestion(2)" id="detailsOfCh">Auto Creation</button>
								
								
								
								</div>
							</div>

							<br /> <br />
							<div id="dataChart table-responsive">
							  <table class="table table-bordered table-hover" style="margin-top:10px; display: none;" id="chartShow"  >

			<tr style="background: #36bdb4 none repeat scroll 0 0; color: #fff; font-weight:bold;">
			<td></td>
	<td></td>
	<td></td>
	<td></td>
	<td><!-- <button type="button" class="btn btn-danger" onclick="showTab2()">NEXT</button> --></td>
		</tr>
	<tr>
	<td><b>Type of Question</b></td>
	<td><b>Easy</b></td>
	<td><b>Intermediate</b></td>
	<td><b>Difficult</b></td>
	<td><b>Total</b></td>
	</tr>
	
	<c:forEach var="quesType" items="${quesType}">
	<tr id="cognId_${quesType.id}">
	
		<td>${quesType.name}</td>
		<td class="removeChart"><span id="${quesType.id}1">0</span></td>
		<td class="removeChart"><span id="${quesType.id}2">0</span></td>
		<td class="removeChart"><span id="${quesType.id}3">0</span></td>
		<td class="removeChart"><span id="totalId_${quesType.id}">0</span></td>
			
			
	</tr>
	</c:forEach>
	
</table> 
				</div>	
							<div class="row">
								<div class="col-md-12 table-responsive" id="questions" style="padding:0px; overflow: hidden;">
								</div>
							</div>

						</div>
						<!--SELECT-QUESTIONS-->


						<!--SET-TEST-PARAMETER-->
						<div id="set-test" class="tab-pane fade">
							<div id="select-ques" class="tab-pane fade in active">

								<div class="row">
									<div class="in-box">

										<div class="col-md-12 col-sm-12">
											<div class="row">
											<div class="table-responsive" style="padding: 0px; overflow: hidden;">
											<div id="dataChart1"></div>
 <!-- <table class="table table-bordered table-hover" style="margin-top:10px;">

			<tr style="background: #36bdb4 none repeat scroll 0 0; color: #fff; font-weight:bold;">
			<td>Current Total Marks : 50</td>
	<td></td>
	<td></td>
	<td><button type="submit" class="btn btn-danger" onclick="showTab1()">BACK</button></td>
	<td><button type="button" class="btn btn-danger" onclick="showTab2()">NEXT</button></td>
		</tr>
	<tr>
	<td><b>Type of Question</b></td>
	<td><b>Easy</b></td>
	<td><b>Intermediate</b></td>
	<td><b>Difficult</b></td>
	<td><b>Total</b></td>
	</tr>
	<tr>
	<td>Application</td>
	<td>3</td>
	<td>2</td>
	<td>3</td>
	<td>8</td>
	</tr>
	<tr>
	<td>Analysis</td>
	<td>3</td>
	<td>2</td>
	<td>3</td>
	<td>8</td>
	</tr>
	<tr>
	<td>Comprehension</td>
	<td>3</td>
	<td>2</td>
	<td>3</td>
	<td>8</td>
	</tr>
	<tr>
	<td>Evaluation</td>
	<td>3</td>
	<td>2</td>
	<td>3</td>
	<td>8</td>
	</tr>
	<tr>
	<td>Knowledge</td>
	<td>3</td>
	<td>2</td>
	<td>3</td>
	<td>8</td>
	</tr>
	<tr>
	<td>Total Question</td>
	<td>3</td>
	<td>2</td>
	<td>3</td>
	<td>8</td>
	</tr>
	
</table>  -->
</div>
							<div class="col-md-12" style="padding-left:0px; margin-bottom: 15px;">
			<h3 style="background:#F2F2F2; padding: 10px; position: absolute; z-index: 100; left: 15px; color:#575757; font-weight:normal; font-style:italic;">Selected Chapters & Topics</h3>
			<div class="selct-chap mCustomScrollbar" style="padding-top:25px;">
			<table class="table table-bordered table-hover table-striped ">
			
	<!-- 		<div id="detailsOfChapTp"></div> -->
<div class="getDetailsOfChapTp"></div>
<!-- <td>
<Strong><span >Knowing Our Numbers</span></Strong><br>
Comparing Numbers , Large Numbers In Practice
</td>
 -->

			</table>
			</div>
			</div>					
												<div class="col-md-6 col-sm-12 mrt15">
													<div class="row">
															<div class="col-md-12 col-sm-12 col-lg-12">
			
			</div>
														<div class="col-md-12 col-sm-12">
															<strong  class="f16bold">Test Type</strong>
														</div>
														<div class="col-md-3 col-sm-3 mrt15">
															<input type="radio" id="q128" name="testType"
																class="testType" value="eTest" data='1'
																ng-model="modelType" onclick="enableEndDate()"/> eTest
														</div>
														<div class="col-md-4  col-sm-6 mrt15">
															<input type="radio" id="q129" name="testType"
																class="testType" checked="checked" value="Assignment"
																data="2" ng-model="modelType" onclick="disableEndDate()"/> Assignment
														</div>
													</div>
												</div>
											</div>
										</div>

										<div class="col-md-6 col-sm-12 mrt15">
											<label>Test Name</label> <input name="" type="text"
												placeholder="Test Name" id="name" data-ng-model="modelTestName">
										</div>

										<div class="col-md-12 col-sm-12 mrt15">
											<div class="row">
												<div class="col-md-4 col-sm-6">
													<label>Test Start Date and Time</label>
													<div class="hero-unit">
														<input type="text" placeholder="Set Test Date"
															id="startDate" data-ng-model="modelDate">

													</div>


												</div>
												<div class="col-md-4 col-sm-6" id="deadLineDate">
													<label>Deadline Date and Time</label>
													<div class="hero-unit">
														<input type="text" placeholder="Deadline Date"
															id="endDate" data-ng-model="modelEndDate" value="">

													</div>

												</div>
											</div>
										</div>

										<div class="clearfix"></div>

										<div class="col-md-4 col-sm-6 mrt15">
										<div class="row">
											<div class="col-md-12 col-sm-12">
												<label>Test Time Limit:</label>
											</div>
											<div class="col-md-4 col-sm-6">
												<input type="text" placeholder="hh" id="hh"
													onchange="setTime()" maxlength="2" ng-init="modelhh=00" data-ng-model="modelhh"/>
											</div>
											<div class="col-md-4 col-sm-6">
												<input type="text" placeholder="mm" id="mm"
													onchange="setTime()" maxlength="2" ng-init="modelmm=00" data-ng-model="modelmm"/>
											</div>
										</div>
										</div>
									</div>

									<div class="col-md-12  col-sm-12  col-xs-12 ">

										<div class="chk" >
											<label><strong>Set Instructions</strong></label>
											
											<!-- <textarea name="" COLS=40 ROWS=6 id="instruction" >
											1. Students must not use calculators and any other unfair means while taking the test.
											2. The duration of the test is {{modelhh}} Hr {{modelmm}} min. You will not be able to submit the test after the time is over.
											3. There will be total qCount MCQ in this test.
											4. The test will consist of only objective type multiple choice questions requiring students to mouse-click their 
											correct choice of the options against the related question number.
											5. For every question there will be only one correct option out of the four given options.
											6. For moving to the next question, click on 'Next' at the bottom of the screen.
											7. Questions can be answered in any order in given time.
											8. For moving to previous question click on 'PREVIOUS' at the bottom of the screen.
											9.Students can mark the question for review too.
											10. Students can make changes in their choice of options before clicking the 'SUBMIT' button.
											11. After completing all the questions, click 'SUBMIT' button. A message will be displayed on the screen 
											'Do you really want to submit ?'. If student click on 'NO', the will be redirected to the test page again and if they press 'YES', 
											the result will be displayed on the screen with details of questions attempted.
											</textarea> -->
											
											<div contenteditable="true" id="instruction" style="border:solid 1px #ccc; padding:5px;">
											1. Students must not indulge in any unfair means while taking the test.<br/>
											2. The duration of the test is {{modelhh}} Hr {{modelmm}} min. You will not be able to submit the test after the time is over.<br/>
											3. The test contains only multiple choice questions.There are a total of <span id="qCountIds"></span> MCQ in this test having one correct option.<br/>
											4. Questions can be answered in any order.<br/>
											5. Click the 'SUBMIT' button at the right hand corner of the screen to complete the test. Consequently, your test result will be displayed on the screen.<br/>
											</div>
									</div>

									</div>

									<div class="clearfix"></div>
									<br>
									<div class="col-md-2 col-sm-2">
										<button type="submit" class="btn btn-primary"
											onclick="showTab1()">BACK</button>
									</div>
									<div class="col-md-1 col-sm-2">
										<button type="submit" class="btn btn-danger"
											onclick="showTab3()">NEXT</button>
									</div>
								</div>

								<br /> <br />

							</div>
						</div>
						<!--SET-TEST-PARAMETER-->

						<!--PREVIEW-->
						<div id="preview" class="tab-pane fade">
				<div class="row" style="margin-top:20px;">
<div class="col-md-12" style="background: rgb(246, 246, 246) none repeat scroll 0% 0%; padding: 10px; border: 1px solid rgb(124, 124, 124); font-size: 13px; border-radius: 8px; margin-left: 2%; width: 96%;">
<div class="col-md-3">Overall Total Questions :<span id="totalQuestionId">0</span></div>
<div class="col-md-2">Easy : <span id="easyPercentId">0</span>%</div>
<div class="col-md-5">Intermediate :<span id="mediumPercentId">0 </span>%</div>
<div class="col-md-2">Difficult : <span id="hardPercentage">0</span>%</div>
</div>
</div>
						
							<div id="editTest123" style="display:none;">
				
		<!-- 		<div class="col-md-12">
				<h3>Test Type</h3>
				<div class="6 mrt15 ">
				<input id="eeTestTypeId1" class="EETestType"  type="radio" data-ng-model="modelType" value="eTest" name="testTypeName" data='1'> E-Type
				 <input id="eaTestTypeId1" class="EATestType" type="radio" data-ng-model="modelType" value="Assignment" name="testTypeName" data='2'> Assignment
				</div>
				</div> -->
				
				<div class="col-md-12 mrt15 ">
				<label>Test Name</label>
<input id="testNameNew1" type="text" placeholder="Test Name" data-ng-model="modelTestName" >
				</div>
				<div class="col-md-6 mrt15 ">
				<label>Test Start Date and Time</label>
<input id="startDateNew1" type="text" placeholder="Start date" data-ng-model="modelDate" value="0">
				</div>
				<div class="col-md-6 mrt15 " id="endDateII">
				<label> Deadline Date and Time
</label>
<input id="endDateNew1"  type="text" placeholder="End date" data-ng-model="modelEndDate" value="0">
				</div>
				<div class="col-md-5 mrt15" style="padding:2px">
				<label style="padding-left:14px"> Time Limit
</label>
  <br>
  <div class="col-md-4"><input type="text"  placeholder="HH" class="" id="hhNew" data-ng-model="modelhh"></div>
  <div class="col-md-4"><input type="text"  placeholder="MM" class="" id="mmNew" data-ng-model="modelmm"></div>
				</div>
				<div class="col-md-12 mrt15">
				<!-- <label style="padding-left:14px"> Instruction
</label> -->
				<!-- <textarea rows="" class="form-control" id="gInstruction"=></textarea> -->
				<!-- <div contenteditable="true" id="newInstruction" style="border:solid 1px #ccc; padding:5px;"></div> -->
				</div>
				
				
				 <div class="col-md-12 mrt15">
  <div class="preview-test-question-con seq">
  <div class="preview-test-question"><div>
  
  <!-- <div class="pull-right btn btn-xs btn-warning">54</div>  -->
  </div><div id="questionAnswerText"></div><!-- 1.In the adjacent figure, three sacks of rice with their respective weights are given. Find the total weight of all the three sacks of rice.<br> -->
  </div>
  <!-- <input type="radio" readonly="readonly" value="1" name="ans">2590 kg<br>
  <input type="radio" readonly="readonly" value="1" name="ans">2600 kg<br>
  <input type="radio" readonly="readonly" value="1" name="ans">2610 kg<br>
  <input type="radio" readonly="readonly" value="1" name="ans">2640 kg<br> -->
  </div>
  </div>
  <div class="col-md-3  mrt15">
				<button class="btn btn-primary" onclick="againPreviewPage()">Update</button>
			<!-- 	<button class="btn btn-primary" onclick="backPreviewPage()">Cancel</button> -->
				</div>
				
					</div>
						
						
						<div id="previewScreenId">
							<br /> <br />
										 <div class="row">
							<!-- <div class="col-md-12" style="background: #f6f6f6 none repeat scroll 0% 0%; padding: 10px; border: 1px solid #7c7c7c; font-size: 13px; border-radius: 8px;">
							<div class="col-md-2"><b>percentage Of </b></div>
							<div class="col-md-3">Easy : <span id="easyPercentId"></span>%</div>
							<div class="col-md-5">Intermediate : <span id="mediumPercentId"></span>%  </div>
							<div class="col-md-2">Difficult : <span id="hardPercentage"></span>% </div>
							</div> -->
							<div class="col-md-12">
							
							<div id="dataChart2"></div>
							
							<!-- <table class="table table-bordered table-hover" style="margin-top:10px;">
			<tr style="background: #36bdb4 none repeat scroll 0 0; color: #fff; font-weight:bold;">
			<td>Current Total Marks : 50</td>
	<td></td>
	<td></td>
	<td><button type="submit" class="btn btn-danger" onclick="showTab2()">Back</button></td>
	<td><button type="submit" class="btn btn-danger" onclick="showTab4()">Assign</button></td>
		</tr>
	<tr>
	<td><b>Type of Question</b></td>
	<td><b>Easy</b></td>
	<td><b>Intermediate</b></td>
	<td><b>Difficult</b></td>
	<td><b>Total</b></td>
	</tr>
	<tr>
	<td>Application</td>
	<td>3</td>
	<td>2</td>
	<td>3</td>
	<td>8</td>
	</tr>
	<tr>
	<td>Analysis</td>
	<td>3</td>
	<td>2</td>
	<td>3</td>
	<td>8</td>
	</tr>
	<tr>
	<td>Comprehension</td>
	<td>3</td>
	<td>2</td>
	<td>3</td>
	<td>8</td>
	</tr>
	<tr>
	<td>Evaluation</td>
	<td>3</td>
	<td>2</td>
	<td>3</td>
	<td>8</td>
	</tr>
	<tr>
	<td>Knowledge</td>
	<td>3</td>
	<td>2</td>
	<td>3</td>
	<td>8</td>
	</tr>
	<tr>
	<td>Total Question</td>
	<td>3</td>
	<td>2</td>
	<td>3</td>
	<td>8</td>
	</tr>
	
</table> --></div>
							
							</div> <br />
							<div class="col-md-4 mrt15" id="timer">
								<strong>Test start date :</strong> <span id="Sdate">{{modelDate}} <br></span>
								<strong>Test end date :</strong> {{modelEndDate}}
								
								<br> <span  id="time"><strong>Time : </strong><span id='showHH'></span><span id="showMM"></span> <!-- {{modelhh}} --> <!-- HH:{{modelmm}} MM --></span>
							</div>

							<div class="col-md-4 txt-cen" id="testNameE">
								<h3>{{modelTestName |uppercase}}</h3>
								<span id="modelGrade"></span><br>
								<span id="modelSubject"></span>
							</div>

							<div class="col-md-4 txt-right mrt15 " id="testTypeE">
								<strong>Test Type :</strong> {{modelType}} <br> <strong>Total Question :</strong> <span
									id="totalQuestion" ng-model="totalQuestion"></span><br> <strong>Maximum Marks :</strong> <span
									id="totalMarks"></span> <br>
							</div>

							<div class="col-md-12 mrt15">
								<div class="col-md-6 pull-left"><h4 id="lblInst">General Instructions:</h4></div>

								<div class="col-md-6 pull-right" style="text-align:right;"><button class="btn btn-primary" onclick="getDetailForTest()">Edit</button>
								</div>
								
								<%-- <div id="stuSelection" style="display:none;">
				<table class="table table-bordered table-hover" >
				
					<tr>
					<td><strong>SR. No</strong></td>
					<td><strong>Select</strong></td>
					<td><strong>Student</strong></td>
					
					</tr>
						<c:if test="${not empty studentList}">
					<c:forEach var='studentList' items="${studentList}" varStatus="status" step="1">
					<tr>
					
							
						<td>${status.count }</td>
						
						<td><input type="checkbox" class="clsStudId" id="str_${studentList.id}"  value="${studentList.id}" ></input></td>
						
						<td>${studentList.firstName}</td>
					
					</tr>
					</c:forEach>
					</c:if>
					</table>
					<button type="button" class="btn btn-danger" id="" onclick="getListOfStudents()">OK</button>
					</div> --%>
																

								<p id="modelInstruction"></p><br>

								<div id="questionAnswer"></div>
								<div class="mrt15 mrb15">
									<button type="submit" class="btn btn-primary"
										onclick="showTab2()">Back</button>
										<!-- <button class="js-open btn btn-primary">Student</button> -->
										<a class="initialism select-student_open btn btn-success" href="#select-student" onclick="checkStudents()" >Student</a>
										<!-- <button type="button" class="btn btn-primary" data-target="#show-student" data-toggle="modal"
										>Student</button> -->
									 <button type="submit" class="btn btn-danger"
										onclick="showTab4()">Save Test</button> 
								</div>
								
								<!-- <div id="editTest" style="display:none; height:580px;">
				
				<div class="col-md-12">
				<h3>Test Type</h3>
				</div>
				<div class="6 mrt15 ">
				<input id="" class="EETestType" name="" type="radio"> E-Type <input id="" class="EATestType" type="radio"> Assignment
				</div>
				<div class="col-md-12 mrt15 ">
				<label>Test Name</label>
<input id="Tname" class="ng-valid ng-dirty ng-valid-parse ng-touched" type="text" placeholder="Test Name" name="" value="{{modelTestName |uppercase}}" onchange="changeValue(this.value)">
				</div>
				<div class="col-md-6 mrt15 ">
				<label>Test Start Date & Time</label>
<input id="startDateE" class="ng-valid ng-dirty ng-valid-parse ng-touched" type="text" placeholder="Test Name" name="" >
				</div>
				<div class="col-md-6 mrt15 ">
				<label> Deadline Date and Time
</label>
<input id="test-name" class="ng-valid ng-dirty ng-valid-parse ng-touched" type="text" placeholder="Test Name" name="" value="{{modelEndDate}}">
				</div>
				<div class="col-md-5 mrt15" style="padding:2px">
				<label style="padding-left:14px"> Time Limit
</label>
  <br>
  <div class="col-md-4"><input type="text" name="" placeholder="HH" class="" id="test-name" value="{{modelhh}} "></div>
  <div class="col-md-4"><input type="text" name="" placeholder="MM" class="" id="test-name" value="{{modelmm}} "></div>
				</div>
				<div class="col-md-12 mrt15">
				<label style="padding-left:14px"> Instruction
</label>
				<textarea rows="" class="form-control" id="gInstruction"=></textarea>
				</div>
				
				
				 <div class="col-md-12 mrt15">
  <div class="preview-test-question-con seq">
  <div class="preview-test-question"><div>
  <span class="pull-right" style="width:20px;margin-left:5px;"><i class="fa fa-times" aria-hidden="true"></i>
  </span>
  <div class="pull-right btn btn-xs btn-warning">54</div> 
  </div><div id="questionAnswerText"></div>1.In the adjacent figure, three sacks of rice with their respective weights are given. Find the total weight of all the three sacks of rice.<br>
  </div>
  <input type="radio" readonly="readonly" value="1" name="ans">2590 kg<br>
  <input type="radio" readonly="readonly" value="1" name="ans">2600 kg<br>
  <input type="radio" readonly="readonly" value="1" name="ans">2610 kg<br>
  <input type="radio" readonly="readonly" value="1" name="ans">2640 kg<br>
  </div>
  </div>
  <div class="col-md-3  mrt15">
				<button class="js-open btn btn-primary">Update</button>
				</div>
				
					</div>

		 -->			<!--student detail end pop up   -->
							</div>
							</div>
						</div>
						<!--PREVIEW-->

						<!--PUBLISH-->
						<div id="publish" class="tab-pane fade">
							<div class="publish-cont">
								<p>You have created <span id='gradeName1'></span>, <span id='subjectName1'></span>, {{modelType}}. Click on the below
									save Test button to make it available in assign task section.</p>
								<div class="btn btn-primary btn-md" onClick="validateNSubmit()">
									Save Test</div>
								
							</div>
							<div class="col-md-12">
								<button type="submit" class="btn btn-primary"
									onclick="showTabBack()">BACK</button>
							</div>
							<!--PUBLISH-->
							<!--tab-cont-->
						</div>
						<!--PUBLISH-->
						<!--tab-cont-->
					</div>
			</div>
			
			
			
			
			
			
			<!--  Auto preview Page-->
			
			
			<div id="AutoPreviewScreenId">
							<br /> <br />
										 <div class="row">
							
							
							</div> <br />
							<div class="col-md-4 mrt15" id="timerAuto">
								<strong>Test start date :</strong> <span id="aSdate"><br></span>
								<strong>Test end date :</strong> <span id="aEDate"></span>
								
								<br> <span  id="time"><strong>Time : </strong>HH:<span id='showHHA'></span>MM:<span id="showMMA"></span> </span>
							</div>

							<div class="col-md-4 txt-cen" id="testNameAuto">
								<h3><span id="autoTestName"></span></h3>
								<span id="autoModelGrade"></span><br>
								<span id="autoModelSubject"></span>
							</div>

							<div class="col-md-4 txt-right mrt15 " id="testTypeAuto">
								<strong>Test Type :</strong> <span id="autoTypeAuto"></span> <br> <strong>Total Question :</strong> <span
									id="totalQuestionAuto" ng-model="totalQuestion"></span><br> <strong>Maximum Marks :</strong> <span
									id="totalMarksAuto"></span> <br>
							</div>

							<div class="col-md-12 mrt15">
								<div class="col-md-6 pull-left"><h4 id="autoTestInst">General Instructions:</h4></div>

								
								
								
																

								<p id="modelInstruction1"></p><br>

								<div id="questionAnswerAuto"></div>
								<div class="mrt15 mrb15">
									<button type="submit" class="btn btn-primary"
										onclick="showTab2()">Back</button>
										<!-- <button class="js-open btn btn-primary">Student</button> -->
										<a class="initialism select-student_open btn btn-success" href="#select-student" onclick="checkStudents()" >Student</a>
										<!-- <button type="button" class="btn btn-primary" data-target="#show-student" data-toggle="modal"
										>Student</button> -->
									 <button type="submit" class="btn btn-danger"
										onclick="showTab4()">Save Test</button> 
								</div>
								
		
							</div>
							</div>
			
			
			<!-- End  -->
			
			<!------------Auto Question------------->
			
			<div ng-app="autoQuestion" id="autoques"  style="background:#f2f2f2; padding: 10px;"  class="tab-pane fade">
			<div class="row" style="margin-top:20px">
			<div class="col-md-12">
	<!-- 		<table class="table table-bordered table-hover" style="margin-top:10px;">
			<tr style="background: #36bdb4 none repeat scroll 0 0; color: #fff; font-weight:bold;">
			<td>Current Total Marks : 50</td>
	<td></td>
	<td></td>
	<td></td>
	<td></td>
		</tr>
	<tr>
	<td><b>Type of Question</b></td>
	<td><b>Easy</b></td>
	<td><b>Intermediate</b></td>
	<td><b>Difficult</b></td>
	<td><b>Total</b></td>
	</tr>
	<tr>
	<td>Application</td>
	<td>3</td>
	<td>2</td>
	<td>3</td>
	<td>8</td>
	</tr>
	<tr>
	<td>Analysis</td>
	<td>3</td>
	<td>2</td>
	<td>3</td>
	<td>8</td>
	</tr>
	<tr>
	<td>Comprehension</td>
	<td>3</td>
	<td>2</td>
	<td>3</td>
	<td>8</td>
	</tr>
	<tr>
	<td>Evaluation</td>
	<td>3</td>
	<td>2</td>
	<td>3</td>
	<td>8</td>
	</tr>
	<tr>
	<td>Knowledge</td>
	<td>3</td>
	<td>2</td>
	<td>3</td>
	<td>8</td>
	</tr>
	<tr>
	<td>Total Question</td>
	<td>3</td>
	<td>2</td>
	<td>3</td>
	<td>8</td>
	</tr>
	
</table> -->
			<h2>Auto Creation</h2>
			</div>
			<div class="in-box" style="float:left">
			<div class="col-md-12 col-sm-12 col-lg-12">
			<div class="col-md-12" style="padding-left: 45px; margin-bottom: 15px;">
			<h3 style="background:#F2F2F2; padding: 10px; position: absolute; z-index: 100; left: 54px; color:#575757; font-weight:normal; font-style:italic;">Selected Chapters & Topics</h3>
			<div class="selct-chap mCustomScrollbar" style="padding-top:25px;">
			<table class="table table-bordered table-hover table-striped ">
			
			<div id="detailsOfChapTp"></div>

<!-- <td id="detailsOfChapTp">
<Strong><span >Knowing Our Numbers</span></Strong><br>
Comparing Numbers , Large Numbers In Practice
</td>
 -->

			</table>
			</div>
			</div>
			</div>
			<div class="col-md-12 col-sm-12 col-lg-12">
			<div class="col-md-6">
			<div class="col-md-12">
			<div class="col-md-6">
			<input id="testAutoName" class="input" type="text" placeholder="Test Name" aria-controls="example" style="border: solid 1px #c9c9c9;">
			</div>
			<div class="col-md-6">
		
			<select id="autoTestType" class="" onclick="aDisableEndDate()">
			<option value="Test Type" selected="Test Type">Test Type</option>
			<option value="1" >E-Test</option>
			<option value="2" >Assignment</option>
			
			</select>
			</div>
			</div>
			<div class="col-md-12">
			
			<div class="col-md-6" style="padding: 10px 10px;">
			<strong>Start Date :</strong>
			<input id="startDateA" class="ng-pristine ng-valid" type="text" ng-model="modelDate1" placeholder="Set Test Date">
			</div>
			<div class="col-md-6" style="padding: 10px 10px;" id="DisableAEndDate">
			<strong>End Date :</strong>
			<input id="endDateA" class="ng-pristine ng-untouched ng-valid" type="text" ng-model="modelEndDate1" placeholder="Set End Date">
			</div>
			</div>
			<div class="col-md-12">
			<div class="col-md-9" style="padding: 10px 10px;">
			<strong>Duration Time :</strong><br>
			<input type="text" placeholder="hh" id="hh1" maxlength="2" ng-init="modelhh1=00" ng-model='modelhh1'  style="width:25%"/>
			<input type="text" placeholder="mm" id="mm1"  maxlength="2" ng-init="modelmm1=00" ng-model='modelmm1'  style="width:25%"/>
			</div>
			</div>
			<div class="col-md-12">
			<div class="col-md-12" style="padding: 10px 10px;">
			<strong>Instruction :</strong>
			<div contenteditable="true" id="autoInstruction" style="border:solid 1px #ccc; padding:5px;">
			
			1.Students must not use calculators and any other unfair means while taking the test.<br>
			2. The duration of the test is {{modelhh1}} Hr {{modelmm1}} min. You will not be able to submit the test after the time is over.<br>
			3. The test contains only multiple choice questions. There are a total of  {{quesNo}} <span id="quesNoAuto"></span> MCQ's in this test having one correct option.<br>
			4. Questions can be answered in any order.<br>
			5. Click the 'SUBMIT' button at the right hand corner of the screen to complete the test. Consequently, your test result will be displayed on the screen.
			</div>
			
			</div>
			
			</div>
			</div>
			
			<div class="col-md-6">
			<div class="col-md-12">
			<table class="table table-bordered table-hover" style="margin-top:0px;">
			<tr>
			<td></td>
			<td><b>Question Count</b></td>
			<td><b>Marks</b></td>
			</tr>
			
			<tr>
			<td><b>Easy</b></td>
			<td><input id="easyQuestId" type="text" placeholder="5" value="5"  pattern="\d*" style="width:50%" onkeyup="QusUpdate()"/></td>
			<td><input id="easyMarks" type="text" placeholder="25" value="2" pattern="\d*" style="width:70%"/></td>
			</tr>
			
			<tr>
			<td><b>Intermediate</b></td>
			<td><input type="text" id="medQuestId" placeholder="5" value="5" pattern="\d*" style="width:50%" onkeyup="QusUpdate()"/></td>
			<td><input id="interMarks"type="text" placeholder="25" value="2" pattern="\d*" style="width:70%"/></td>
			</tr>
			
			<tr>
			<td><b>Difficult</b></td>
			<td><input type="text" id="diffQuestId" placeholder="5" value="5" pattern="\d*" style="width:50%" onkeyup="QusUpdate()"/></td>
			<td><input id="diffMarks" type="text" placeholder="25" value="2" pattern="\d*" style="width:70%"/></td>
			</tr>
			</table>
			</div>
			</div>
			</div>
			
			
			
			
			
			
			</div>
			<div class="clearfix"></div>
			<br>
			<div class="col-md-2">
			<a data-toggle="tab" href="#testmanually" style="padding: 10px 20px ! important; font-size: 12px;" ><button type="submit" class="btn btn-primary" id="showAuto" onclick="removeAuto()">Back</button></a>
			</div>
			
			
		<!-- 	<div class="col-md-2">
			<button type="button" class="btn btn-primary" id="previewAutoTest" onclick="previewAutoTestScreen()" >Next</button>
			</div>  -->
			
			<div class="col-md-2">
			<a class="initialism select-student_open btn btn-success" href="#select-student" onclick="checkStudents()" >Student</a>
		<!-- <button id="select-student"  >Student</button> -->
		<a  data-toggle="tab"  class="btn btn-primary" href="#autoques" id="mannId" style="display: none;"></a>
	<!-- 	<a type="button" id="previewAutoTest" data-toggle="tab"  class="btn btn-primary" href="#autoQusetionList"  onclick="previewAutoTestScreen()" >Next</a> -->
		<a type="button" id="previewAutoTest" data-toggle="tab"  class="btn btn-primary" onclick="previewAutoTestScreen()" >Next</a>
			
			</div>
			</div>
			</div>
			</div>
				</div>
				
				<!--Auto test preview  -->
				<div id="autoQusetionList"></div>
			
			<!--Auto test preview  -->
				
				<!--create-test-->
				<div id="menu1" class="tab-pane fade <c:if test="${param.tab == 'mTest'}"> in active </c:if>">

					<div class="col-md-12 table-responsive">
						<table id="example" class="display" cellspacing="0" width="100%">
							<!-- <a href="#"><b
								class="glyphicon glyphicon-trash assign-delete pull-right"></b></a>
							<a href="#"><b
								class="glyphicon glyphicon-pencil assign-edit pull-right"
								data-toggle="modal" data-target="#myModal"></b></a> -->

							<thead>
								<tr class="dataTab">
									<th>S.No.</th>
									<!-- <th>Select</th> -->
									<th>Evaluation Name</th>
									<th>Grade</th>
									<th>Evaluation Type</th>
									<th>Created On</th>
									<th>Started On</th>
									<!-- <th>Status</th> -->
									<th>Action</th>
								</tr>
							</thead>


							<tbody>


								<c:if test="${not empty assessmentList}">
									<c:forEach var="assessment" items="${ assessmentList}"
										varStatus="status">
										<tr>
											<td>${status.count }</td>
											<%-- <td><input name="" type="checkbox"
												value="${assessment.id }"  class="editAssessmentCls" dir="${status.count }"></td> --%>
											<td><a href="javascript:void(0)" data-target="#myModal4"
												data-toggle="modal" class="testNameCls"
												id="testNameId${assessment.id}">${assessment.testName }</a>
												<input type="hidden" value="${assessment.testName }" id="assessmentIds${status.count}" dir="${assessment.id}">
												</td>
											<td><span id="gradeNameId${status.count}">${assessment.grade.name }</span>
											
											</td>
											<td><span id="assignTypeId${status.count}"><c:if test="${assessment.type eq 1 }">E-Test</c:if>
												<c:if test="${assessment.type eq 2 }">Assigned Test</c:if></span></td>
											<td><span id="createdOn${status.count }"><fmt:formatDate pattern="dd-MMM-yyyy"
													value="${assessment.createdOn}" /></span></td>
											<td><fmt:formatDate pattern="dd-MMM-yyyy"
													value="${assessment.startDate}" /> <input type="hidden"
												id="testOn${status.count }"
												value="<fmt:formatDate pattern="dd/MMM/yyyy HH:mm" value="${assessment.startDate}" />" />
												<input type="hidden"id="testOff${status.count }" value="<fmt:formatDate pattern="dd/MMM/yyyy HH:mm" value="${assessment.endDate}" />" />
												</td>

											<td>
										<%-- 	<c:choose>
											<c:when test="${empty assessment.assignedAssessment }"><a href="javascript:void(0);" class="removeAssessmentCls"
														id="removeAss${assessment.id}">Remove</a></c:when>
											<c:otherwise>N/A</c:otherwise>
											</c:choose> --%>
											<c:choose>
											<c:when test="${(currentDate > assessment.startDate)}">
											N/A
											</c:when>
											<c:otherwise>
												<c:if test="${assessment.status eq 2 }"><a href="javascript:void(0)" data-toggle="modal" data-target="#myModal" class="editAssessmentCls" dir="${status.count }">Edit</a>
												/<a href="javascript:void(0)" dir="${status.count}" id="${assessment.id}" onclick="removeAssmt('${assessment.id}')">Remove</a>
												
												</c:if>
											</c:otherwise>	</c:choose>
												</td>

										</tr>
									</c:forEach>

								</c:if>




							</tbody>
						</table>

					</div>
					<!--  <div class="col-md-2"><button type="submit" class="btn btn-danger">NEXT</button></div> -->


					<div class="clearfix"></div>




				</div>
				<!--tab-cont-->

				<br /> <br />
			</div>
			<div class="clearfix"></div>
			<!-- /.container-fluid -->
		</div>
		<!-- /#page-wrapper -->
	</div>


	<!-- Modal -->
	 <div id="myModal" class="modal fade" role="dialog">
		<div class="modal-dialog">

			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<div class="modal-title">
						<span>Edit Task</span> on the Topic
					</div>
				</div>
				<form:form action="${pageContext.request.contextPath}/schoolTeacher/updateAssessmentByTeacher.htm"
								 method="post" 
								class="form-horizontal" role="form" id="updateAssessmentForm">
				<div class="modal-body">
					<table width="90%" border="0" cellspacing="0" cellpadding="0"
						class="topic-per table table-bordered table-hover table-condensed">
						<tr>
							<td><strong>Test Name:</strong></td>
							<td><input type="text" id="testNameIds" name="assessmentName" required/>
							<input type="hidden" name="assessmentId" id="hiddAssessmentId">
							
											<input type="hidden" name="grdId" value="${param.grdId}">
											<input type="hidden" name="subId" value="${param.subId}">
											<input type="hidden" name="secId" value="${param.secId}">
											<input type="hidden" name="sec" value="${param.sec}">
											<input type="hidden" name="sub" value="${param.sub}">
											<input type="hidden" name="grd" value="${param.grade}">
											
							</td>
						</tr>
						<tr>
							<td><strong>Grade:</strong>
							
							</td>
							<td><span id="gradeNameIds"></span>
							
							</td>
						</tr>

						<tr>
							<td><strong>Test Type:</strong></td>
							<td><span id="testTypeIds"></span></td>
						</tr>
						<tr>
							<td><strong>Created On:</strong></td>
							<td><span id="createdOnIds"></span></td>
						</tr>
						<tr>
							<td><strong>Test Start Date:</strong></td>
							<td><input type="text" placeholder="" id="startDate1" name="startDate" required/></td>
						</tr>
						<tr>
							<td><strong>Test End Date:</strong></td>

							<td><input type="text" placeholder="" id="endDate1" name="endDate" required/></td>
						</tr>
					</table>



				</div>
				<span id="dateErr" style="color: red;"></span>
				<div class="modal-footer">
					<div class="btn btn-primary btn-md" id="updateAssessment" onclick="updateAssessment()">Save Changes</div>
				</div>
				</form:form>
			</div>

		</div>
	</div> 
	<!-- Modal end -->

	<!-- Modal4 -->
	<div id="myModal4" class="modal fade" role="dialog">
		<div class="modal-dialog assign-name-pop">
			<div class="modal-content" id="assessmentPreview"></div>
		</div>
	</div>
	<!-- Modal4 end -->
	<!--SHOW-STUDENTS  -->


<div id="student-detail" class="modal fade" role="dialog">
<div class="modal-dialog">
<div class="modal-content" style="float:left; padding-bottom:10px;">
<div class="modal-header">
<button type="button" class="close" data-dismiss="modal">&times;</button>
<div class="modal-title">
</div>
</div>
<%-- <div class="modal-body">
<div class="student-detail-pop">
<div class="col-md-4"><img src="${pageContext.request.contextPath}/resources/images/activity-user-pic.png" alt=""></div>
<div class="col-md-8">
<p  class="student-mssg" id="lstStudent">

</p>
<button type="submit" class="btn btn-danger" id="getSutentsIds">OK</button>
</div>
</div>
</div> --%>
</div>
</div>
</div>

<!--Edit Create Test  -->
<div id="edit-CreateTest" class="modal fade" role="dialog">
<div class="modal-dialog">
<div class="modal-content" style="float:left; padding-bottom:10px;">
<div class="modal-header">
<button type="button" class="close" data-dismiss="modal">&times;</button>
<div class="modal-title">
</div>
</div>
<div class="modal-body">
<div class="student-detail-pop">

<div id=""><span class="showTimer"></span>



</div>


</div>
</div>
</div>
</div>
</div>

<div style="width: 38px; height: 117px; right: 0px; top: 50%; position: fixed;">
<a href="https://docs.google.com/forms/d/e/1FAIpQLScylZUgeQrLr2oGBufoyeQFKFIlBQD67qvOdFhWu6Qcd17rRw/viewform" target="blank"><img src="${pageContext.request.contextPath}/resources/images/feedback.png" alt=""></a>
</div>
</body>

</html>

<script type="text/javascript">

var chapterList=new Array();;
var i=0;
<c:forEach var="cha" items="${chapterList}">
chapterList[i]='${cha}';
i++;
</c:forEach>
	//var chapterList='${chapterList}';
	var subjectId, gradeId, duration, sequence=[];


    var marks = 0;
    var count = 0;

    
  
    
    
    $(document).ready(function()
    {

  	  
    	<c:if test="${sessionScope.tab123 != null && not empty sessionScope.tab123}">
		$("#funkId").trigger("click");
		<c:remove var="tab123"  scope="session"/>

	</c:if>

    	  
	<c:if test="${sessionScope.tab420 != null && not empty sessionScope.tab420}">
	$("#funkId").trigger("click");
	<c:remove var="tab420"  scope="session"/>

</c:if>
    	
  	
  	
  	 <c:if test="${sessionScope.tab21 != null && not empty sessionScope.tab21}">
		$("#funkId").trigger("click");
		<c:remove var="tab21"  scope="session"/>

	</c:if>
    
	
	
	 <c:if test="${sessionScope.removeAsst != null && not empty sessionScope.removeAsst}">
	$("#funkId").trigger("click");
	<c:remove var="removeAsst"  scope="session"/>

</c:if>
    	    
    	
    	
    	 var oTable=$('table#datatable').DataTable( {
    	 } );
    	
    	  $('#manageTable').DataTable();
  	    $( "#questionAnswer" ).sortable();
          $( "#questionAnswer" ).disableSelection();
    	
	    $('#selectGrade').prop('selectedIndex', 0);
	    $('input:checkbox').removeAttr('checked');
	    
	    $('#startDate, #startDate1,#startDateA, #startDateNew1, #endDateNew1').datetimepicker(
	    {
	        mask : '9999/19/39 29:59',
	        step : 5,
	        minDate : '-1970/01/01',
	        maxDate : '+1970/03/01',
	        format : 'd/M/Y H:i'
	    
	    });
	    
	    $('#endDate,  #endDate1, #endDateA').datetimepicker(
	    {
	        mask : '9999/19/39 29:59',
	        step : 5,
	        minDate : '-1970/01/01',
	        maxDate : '+1970/03/01',
	        format : 'd/M/Y H:i'
	        
	        
	        
	        
	    });
	    
	   
	    
	    $('.clsGrade').each(function()
	    {
		    var val = $(this);
		    var flag = false;
		    $('.clsGrade').each(function()
		    {
			    if($(this).val() == val.val())
				    if(!flag)
					    flag = true;
				    else
					    $(this).remove();
		    });
	    });
	    
	    
	    
	   

	    
	    // get the capter
	    	  
	    	    
	    	    $.get("${pageContext.request.contextPath}/ajax/schoolTeacher/getChapters?subjectId=${param.subId}&gradeId=${param.grdId}", function(data)
	    			    {
	    				    chapterList = data;
	    				    if(data.length > 0)
	    				    {
	    					    $('#chapters').empty();
	    					    for (var i = 0; i < data.length; i++)
	    					    {
	    						    chapterList = data;
	    						    $('#chapters').append('<tr><td width="20"><input type="checkbox" class="clsChapter" onchange="getTopics()" id="chapter_' + data[i].id + '" value="' + data[i].id + '"/></td><td><label>' + data[i].name + '</label></td></tr>');
	    					    }
	    				    }
	    				    else
	    				    {
	    					    $('#chapters').empty().append(' No Chapter Found for selected grade subject');
	    				    }
	    			    });
	    	    
	    	    $('.createdBy').prop('checked','checked');
	    	    
	        });
	    
    
    // get all students
   
    
    
    
    $(".removeAssessmentCls").on("click", function()
    	    {
    		    /* alert("hi");autoQusetionList
    		     */
    		    var removeDate = $(this).parent().parent();
    		    var assessmentId = $(this).attr("id").substr(9);
    		    var r = confirm("You want to remove this assessment!");
    		    if(r == true)
    		    {
    			    $.post("${pageContext.request.contextPath}/teacherController/removeAssessment/" + assessmentId, function(data)
    			    {
    				    
    				    if(data == true || data == 'true')
    				    {
    					    removeDate.remove();
    				    }
    			    }, "json");
    			    /* ${pageContext.request.contextPath}/teacherController/removeAssessment/${assessment.id}.htm */
    			    return false;
    		    }
    		    else
    		    {
    			    return false;
    		    }
    	    });
    
    
    $(".testNameCls").on("click", function()
    	    {
    		    var assessmentId = $(this).attr('id').substr(10);
    		    $.post("${pageContext.request.contextPath}/schoolTeacher/assessmentPreview/" + assessmentId, function(data)
    		    {
    			    $("div#assessmentPreview").html(data);
    		    });
    	    });
    	    
    	   
    	    
    	    $(".editAssessmentCls").on("click", function()
    	    {
    	    	var id=$(this).attr('dir');
    	    	$("#testNameIds").val($("#assessmentIds"+id).val());
    	    	$("#gradeNameIds").html($("#gradeNameId"+id).html());
    	    	$("#testTypeIds").html($("#assignTypeId"+id).html());
    	    	$("#createdOnIds").html($("#createdOn"+id).html());
    	    	 $("#startDate1").val($("#testOn"+id).val());
    		    $("#endDate1").val($("#testOff"+id).val());
    		    
    		    $("#hiddAssessmentId").val($("#assessmentIds"+id).attr('dir'));
    	    })

    	    
    	    function updateAssessment()
    	    {
    	    	

    	    	console.log("hello");
    	    	var d1=new Date($('#startDate1').val());
    			var d2=new Date($('#endDate1').val())
    			$("#dateErr").html('');
    		if (d1 >= d2) {
    			$("#dateErr").html('Start Date Time must be less then End Date Time!');
    			return false;
    		}
    	    	$("#updateAssessmentForm").submit();
    	    }
    	    
    	    $("#updateAssessment").on("click", function(){
    	    	
    	    	console.log("hello");
    	    	var d1=new Date($('#startDate1').val());
    			var d2=new Date($('#endDate1').val())
    			$("#dateErr").html('');
    		if (d1 >= d2) {
    			$("#dateErr").html('Start Date Time must be less then End Date Time!');
    			return false;
    		}
    	    	$("#updateAssessmentForm").submit();
    	    });
    	    
    function populateSubject(gradeId)
    {
	    
	    if(gradeId != '' && !isNaN(gradeId))
	    {
		    $('#selectSubject').empty();
		    $('#chapters').empty();
		    $('#topics').empty();
		    $('#questions').empty();
		    
		    $('#selectSubject').append("<option value=''>Select Subject</option>");
		    <c:forEach var="ts" items="${teacherSection}">
		    if('${ts.section.sessionSchoolGrade.grade.id}' == gradeId)
		    {
			    if(0 == $('#selectSubject option[value="${ts.subject.id}"]').length)
				    $('#selectSubject').append("<option value='${ts.subject.id}' class='clsSubject'>${ts.subject.name}</option>");
		    }
		    </c:forEach>
	    }
	    else
	    {
		    $('#selectSubject').empty().append("<option value=''>Select Subject</option>");
		    $('#chapters').empty();
		    $('#topics').empty();
		    $('#questions').empty();
	    }
    }

    function getChapters(subId)
    {
	    subjectId = subId;
	    $('#selectAllChapter').removeAttr('checked');
	    if(subjectId != '' && !isNaN(subId))
	    {
		    gradeId = $('#selectGrade').val();
		    $('#topics').empty();
		    $('#questions').empty();
		    $.get("${pageContext.request.contextPath}/ajax/schoolTeacher/getChapters?subjectId=" + subjectId + "&gradeId=" + gradeId, function(data)
		    {
			    chapterList = data;
			    if(data.length > 0)
			    {
				    $('#chapters').empty();
				    for (var i = 0; i < data.length; i++)
				    {
					    chapterList = data;
					    $('#chapters').append('<tr><td width="20"><input type="checkbox" class="clsChapter" onchange="getTopics()" id="chapter_' + data[i].id + '" value="' + data[i].id + '"/></td><td><label>' + data[i].name + '</label></td></tr>');
				    }
			    }
			    else
			    {
				    $('#chapters').empty().append(' No Chapter Found for selected grade subject');
			    }
		    });
	    }
	    else
	    {
		    $('#chapters').empty();
		    $('#topics').empty();
		    $('#questions').empty();
	    }
    }

    
    // for chapter and topics 
    var ListChptAndTopics = [];
    function getTopics()
    {
    	 ListChptAndTopics = [];
    	
    	$("#dataChart").hide();
    	$(".removeChart span").empty().text("0");
    	console.log(chapterList[1].toString());
    	console.log(chapterList.length);
    	  console.log("length"+chapterList.length);
	    $('#topics').empty();
	    $("#selectAllTopicId").prop('checked','checked');
	
	    	
	    	  var flag1=true;
	 	    $('.clsChapter:checkbox:checked').each(function(){
	 	    	
	 	    	flag1=false;
	 	    });
	 	    
	 	    if(flag1)
	 	    	{
	 	    	$('#selectAllTopicId').removeAttr('checked');
	 	    	}
	    	
	    	var chpFlag=false;
	    	  $('.clsChapter').not(':checked').each(function()
	    			  {
	    		  			chpFlag=true;
	    		  			return false;
	    		  			    		  
	    			  });
	    	  
	    	  if(!chpFlag)
	    		  {
	    		  
	    
	    		  $("#selectAllChapter").prop('checked','checked');
	    		  }else
	    			  {
	    			  $("#selectAllChapter").removeAttr('checked');
	    			  } 
	  /*   	  
	    $('#topics').empty();
	    $('#selectAl
	    		lTopic').removeAttr('checked'); */
	    		
	    		
	    		ListChptAndTopics.push({ChpName:"", topicList:""});
	    if(chapterList != undefined && chapterList.length > 0)
	    {
		    $('#questions').empty();
		    $('.clsChapter:checkbox:checked').each(function()
		    {
			    for (var i = 0; i < chapterList.length; i++)
			    {
			    
				    var topicList = chapterList[i].topics;
				    var chName="";
				    if(this.value == chapterList[i].id)
				    {
				    	chName=chapterList[i].name;
				    	console.log(chName);
				    	var str="";
					    for (var j = 0; j < topicList.length; j++)
					    {
					    	str+=","+topicList[j].name;
					    	
					    	
					    	console.log(str);
					    	    
						    '<tr><td width="20"><input type="checkbox" class="clsTopic" id="topic_'+topicList[j].id+'" value="'+topicList[j].id+'"/></td><td><label>' + topicList[j].name + '</label></td></tr>'

						    $('#topics').append('<tr><td width="20"><input type="checkbox" class="clsTopic" checked id="topic_'+topicList[j].id+'" value="'+topicList[j].id+'" onchange="checkTopic()"/></td><td><label>' + topicList[j].name + '</label></td></tr>');
						  //  console.log("chapter topic list"+ListChptAndTopics[1].ChpName+"tosgp"+ListChptAndTopics[1].topicList);	
					    }
					   // console.log(chName);
					    ListChptAndTopics.push({ChpName:chName,topicList:str});
					    chName="";
					    str="";
					    		    
				    }
			    }
			    for(var j=0;j<ListChptAndTopics.length;j++)
		    	{
		    	console.log(ListChptAndTopics[j].ChpName+"_____________"+ListChptAndTopics[j].topicList);
		    	}
		
		
		    });
		    
	    }
	    
	    else
	    {
		    $('#topics').empty();
		    $('#questions').empty();
	    }
    }

    function getQuestion(a)
    {
    	var eQus=parseInt($("#easyQuestId").val());
    	var mQus=parseInt($("#medQuestId").val());
    	var dQus=parseInt($("#diffQuestId").val());
    	var totalQus=eQus+mQus+dQus;
    	
    	$("#quesNoAuto").text(totalQus);
    	
    	
    /* 	$('#testAutoName').val("");
    	$('#autoTestType').val("Test Type");
    	$('#startDateA').val("");    	
    	$('#endDateA').val("");
    	$('#hh1').val("");
    	$('#mm1').val("");
    	$('#easyQuestId').val("");
    	$('#medQuestId').val("");
    	$('#diffQuestId').val("");
    	$('#easyMarks').val("");
    	$('#interMarks').val("");
    	$('#diffMarks').val("");  */
    	
	    var values = [];
	    var createdById=[];
	    
	    if($('#selectGrade').val() == ''){
	    	 alert('Please Select Grade !!');
     return false;
	    }
	   
	    if($('#selectSubject').val()=='')
	    	{
	    	alert('Please Select Subject!!');
	    	return false;
	    	}
	    var flag=true;
	    $('.clsChapter:checkbox:checked').each(function(){
	    	flag=false;
	    });
	   	if(flag==true){
	   		alert("Please Select Any Chapters");
	   		return false;
	   	}
	   	
	    var flag1=true;
	    $('.clsTopic:checkbox:checked').each(function(){
	    	flag1=false;
	    });
	   	if(flag1==true){
	   		alert("Please Select Any Topic !!!");
	   		return false;
	   	}
	  
	   	$(".createdBy:checkbox:checked").each(function()
			    {
	   		
	   		createdById.push($(this).val());
				
			    });
	   	
	   
		    $(".clsTopic:checkbox:checked").each(function()
		    {
			    values.push($(this).val());
		    });
		    
		   
		    
		    
		    if(createdById.length==0)
	    	{
	    	alert("Please Select Created By !!!");
	   		return false;
	    	}
		   
		    if(values.length > 0 )
		    	if(a==1){
		    		 $('#fullPageloader').show();
		    		 	$("#chartShow").show();
		    		 $("#dataChart").show();
		    		console.log(a);
			    $('#questions').empty().load("${pageContext.request.contextPath}/schoolTeacher/getQuestions?topicIds=" + values.toString() + "&createdById="+ createdById.toString());
			    
		    	}else {
		    		console.log(a);
		    	$("#mannId").trigger("click");
		    	}
			    else
	  $('#questions').empty();
	   
    }
    
  
/* $(".setTimeCls").on("change", function(){
	 var hh = $('#hh1').val();
	    var mm = $('#mm1').val();
	console.log("hh"+hh);
	console.log("mm"+mm);
	    duration = 0;
	    
	    if(hh != undefined && !isNaN(hh))
	    {
		    duration += Number(hh * 60);
	    }
	
	    if(mm != undefined && !isNaN(mm))
	    {
		    duration += Number(mm);
	    }
	    
	    console.log("duration"+duration)
}); */
    function setTime()
    {
	    var hh = $('#hh').val();
	    var mm = $('#mm').val();
	/*  	var h1=$('#hh1').val();
	 	var h2=$('#mm1').val(); */
	    duration = 0;
	    
	    if(hh != undefined && !isNaN(hh))
	    {
		    duration += Number(hh * 60);
	    }
	
	    if(mm != undefined && !isNaN(mm))
	    {
		    duration += Number(mm);
	    }
	 
    }

    function checkAllChapters(check)
    {
    	$("#chartShow").hide();
    	$(".removeChart span").empty().text("0");
    	$('#questions').empty();
	    //$('#topics').empty();	    
	    if(check.checked == true)
	    {
		    $('.clsChapter').not(':checked').each(function()
		    {
			    $(this).trigger('click');
		    });
	    }
	    else if(!check.checked)
	    {
		    $('.clsChapter').each(function()
		    {
			    $(this).removeAttr('checked');
		    });
		    $("#selectAllTopicId").removeAttr('checked');
		    $('#topics').empty();
	    }

    }

    function selectAllTopic(check)
    {
	    if(check.checked == true)
	    {
		    $('.clsTopic').not(':checked').each(function()
		    {
				    $(this).trigger('click');
		    });
	    }
	    else if(!check.checked)
	    {
		    $('.clsTopic').each(function()
		    {
			    $(this).removeAttr('checked');
		    });
	    }
    }

    function showTab1()
    {
    	
    	$("#chartShow").show();
    	
	    $("#tab1").trigger("click");
    }
   
     function showTab2()
    {
    	 $("#dataChart").html();
    	 
    var t= $("#dataChart1").html($("#dataChart").html());

    	 var qCount=0;
    	  for(item in array) {
    		 //console.log(">>>"+ item+", value: "+ array[item]);
    		qCount++;
    		 //console.log(qCount  + count+", value: "+ array[item]);
    	    	
    	 }  
	    try
	    {
		    if(validateQuestion()){
		    	$("#chartShow").show();
			    $("#tab2").trigger("click");
			     $("#qCountIds").html(qCount)
			  /*   $("#instruction").val(val);  */
		    }
		   
		    	    }
	    catch (e)
	    {
		    console.log('Error in method showTab2: ' + e);
	    }
    }

    function showTab3()
    {
    	//$("#dataChart").html();
   	 $("#dataChart2").html($("#dataChart").html());
   	 
	    var flag = validateAll();
	    try
	    {
		    if(flag){
		    addQuestion();
		    changePreview();
			    $("#tab3").trigger("click");
			   
		    }
	    }
	    catch (e)
	    {
		    console.log('Error in method showTab3: ' + e);
	    }
    }
    
    function showTabBack()
    {
	    var flag = validateAll();
	    try
	    {
		    if(flag){
		  /*   addQuestion();
		    changePreview(); */
			    $("#tab3").trigger("click");
		    }
	    }
	    catch (e)
	    {
		    console.log('Error in method showTab3: ' + e);
	    }
    }
    

    function showTab4()
    {
    	//alert("tabe 4");
    	
    	genrateSeq(1);
    	
    	validateNSubmit();
    	/* $("#tab4").trigger("click"); */
    }

    function changePreview()
    {
    	
    	$.jStorage.set("testNamePre", $('#name').val());
    	$.jStorage.set("testStartDatePre", $('#startDate').val());
    	$.jStorage.set("testEndDatePre",$('#endDate').val());
    	$.jStorage.set("testInstructionsPre", $('#instruction').html());
    	$.jStorage.set("testTypePre", $("input[name=testType]:checked")
    			.attr('data'));
    	$.jStorage.set("testHHPre", $('#hh').val());
    	$.jStorage.set("testMMPre", $('#mm').val());


    	$('#time').show();
	    $('#totalQuestion').empty().append(count);
	    $('#totalMarks').empty().append(marks);
	    var gradeName="${param.grade}";
	    var subName="${param.sub}";
	    
	    $('#modelGrade').empty().append(gradeName);
	    $('#modelSubject').empty().append(subName);
	     var hh=$('#hh').val().trim();
	     var mm=$('#mm').val().trim();
	     
	    //alert(hh);
	     if(hh=='' && mm =='' || (hh==00 && mm==00))
	    	{
	   // alert("timer Hide");
	    	$('#time').hide();
	    	}
	    	else  {
	    		if(hh!=''&& hh<10 )
	    		  	{
	    		if( hh.length==1){
	    			//alert(hh.length);
	    	$('#showHH').empty().append("0"+hh+"Hours:");
	    		}else{
	    			$('#showHH').empty().append(hh+"Hours:");
	    		}
	    	}else if(hh!='' && hh>9)
	    		{
	    		$('#showHH').empty().append(hh+"Hours:");
	    		}
	    	else{
	    		hh="00";
	    		$('#showHH').empty().append(hh+"Hours:");
	    	}
	    
	    if(mm!='' && mm<10 )
	    	{if( mm.length==1){
    			//alert(mm.length);
		    	$('#showMM').empty().append("0"+mm+"Minutes:");
		    		}else{
		    			$('#showMM').empty().append(mm+"Minutes:");
		    		}
	    	}else if(mm!='' && mm>9)
	    		{
	    		$('#showMM').empty().append(mm+"Minutes");
	    		}
	    	else{
	    		mm="00";
	    		$('#showMM').empty().append(mm+"Minutes");
	    	}
	    $('#subjectName1').empty().append($('#selectSubject option:selected').text());
	    $('#gradeName1').empty().append($('#selectGrade option:selected').text());
	    //lblInst
	    if($('#instruction').html().trim()!='')
	    {
	    	$('#lblInst').show();
	    	$('#modelInstruction').empty().append($('#instruction').html());
	    }
	    else
	    	$('#lblInst').hide();
    }

    
    }
    
    var studentIdsLst=[];
    
    function createAutoTest(){
    	
    	var values=[];
    	createdById=[];
    	chapterIds=[];
    	var startDate=$('#startDateA').val();
    	var endDate=$('#endDateA').val();
    	var d1=new Date(startDate);
		var d2=new Date(endDate);
		
		var instruction = $("#autoInstruction").html();
		// set marks 
		
		$("#easyQuestId").val();
    	var eMarks=$("#easyMarks").val();
    	$("#medQuestId").val();
    	
    	var iMarks=$("#interMarks").val();
    	$("#diffQuestId").val();
    	var dMarks=$("#diffMarks").val();
    	
    	var check="${easyCount}";
    	
    	
    	var totalQues=parseInt($("#easyQuestId").val())+parseInt($("#medQuestId").val())+parseInt($("#diffQuestId").val());
    	var totalMarks=(parseInt($("#easyQuestId").val())*parseInt($("#easyMarks").val()))+(parseInt($("#medQuestId").val())*parseInt($("#interMarks").val())+(parseInt($("#diffQuestId").val())*parseInt($("#diffMarks").val())));
    
		
    
		$(".clsTopic:checkbox:checked").each(function()
    			    { values.push($(this).val());
    			    });
    	
		 $('.clsChapter:checkbox:checked').each(function(){
		    	
		    	chapterIds.push($(this).val());
		    	
		    	
		    });
		
    	 
		var hh=parseInt($('#hh1').val());
    	var mm=parseInt($('#mm1').val());

 	    duration = 0;
 	    
 	    if(hh != undefined && !isNaN(hh))
 	    {
 		    duration += Number(hh * 60);
 	    }
 	
 	    if(mm != undefined && !isNaN(mm))
 	    {
 		    duration += Number(mm);
 	    }
 	    
 		$(".createdBy:checkbox:checked").each(function()
			    {
	   		
	   		createdById.push($(this).val());
				
			    });
    	
    	if($('#testAutoName').val().trim()==''){
    	alert("Please Enter Test Name");
    	$('#testAutoName').focus();
    	 return false;
    	} else if($('#autoTestType option:selected').val()=='Test Type'){
    		alert("Please select Test Type");
    		$('#autoTestType').focus();
    		 return false;
    	} else if (d1=='Invalid Date') {
			alert('Please provide test start date');
			 return false;
		} else if($('#autoTestType option:selected').val()=='E-Test'||$('#autoTestType option:selected').val()=='1')
			{	
			if (d2=='Invalid Date') {
			alert('Please provide test end date');
			 return false;
		} 
			} if (d1 >= d2) {
			alert('Start Date Time must be less then End Date Time');
			 return false;
		} if(hh==''&& mm=='')
    	{
    		alert("Please select test duration !!");
    		 return false;
    	} 
    	else if(hh>24)
		{
    		alert('Hours should be less then 24');
    		 return false;
    	}
    	else if(mm>59)
    			{
    			alert('Minute should be less then 60');
    			 return false;
    			 }
    	else if(studentIdsLst=="")
		{
		alert("Please Select Student");
		 return false;
		}
		    	
    	else if (confirm("Are you sure? You want to create Assessment!!!")){
    		instruction = instruction.replace('<span id="quesNoAuto">','<span>');
    		 $('#fullPageloader').show();
    		 /* console.log(studentIdsLst.toString());
    		 console.log("${param.grdId}"+"${param.subId}"+values.toString()+""+createdById.toString()+""+$('#testAutoName').val().trim()+""+"startDate"+endDate+""+totalQues+""+duration+
    				 ""+$("#easyQuestId").val()+""+$("#medQuestId").val()+""+$("#diffQuestId").val()+""+totalMarks); */
    		$.post("${pageContext.request.contextPath}/schoolTeacher/getAutoQuestions",
    		
    		{gradeId : "${param.grdId}",
			subjectId :" ${param.subId}",
    		topicIds: values.toString(),
    		createdBy:createdById.toString(),
    		chapterIds:chapterIds.toString(),
    		testName:$('#testAutoName').val().trim(),
    		testType:$('#autoTestType option:selected').val(),
    		startDate:startDate,
    		endDate:endDate,
    		totalQues:totalQues,
    		duration:duration,
    		instruction:instruction , //$('#autoInstruction').val(),
    		eQuest:$("#easyQuestId").val(),
    		mQuest:$("#medQuestId").val(),
    		dQuest:$("#diffQuestId").val(),
    		totalMarks:totalMarks,
    		assStudentlst:studentIdsLst.toString(),
    		secId:"${param.secId}",
    		eMarks:eMarks,
    			iMarks:iMarks,
    				dMarks:dMarks
    		},
   
    		function(data){
    			$('#fullPageloader').hide();
    			if(data=="success"){
    				
    				
    			alert("Test created successfully");
    			//location.reload();
    			window.location.reload(true);
    			}else{
    				alert(data);
    			}
    		});  
    	} 
    	}
    function checkTopic()
    {
    	$("#dataChart").hide();
    	$(".removeChart span").empty().text("0");
    	$('#questions').empty();
    	  var topFlag=false;
    	  $('.clsTopic').not(':checked').each(function()
    			  {
    		  		topFlag=true;
    		  		console.log("checked");
    		  		return false;
    		  		
    			  });
    	  if(!topFlag)
    		  {
    		  
    		  $("#selectAllTopicId").prop('checked','checked');
    		  }
    		  else
    			  {
    			  $("#selectAllTopicId").removeAttr('checked');
    			  
    			  
    			  }
				
    	  
    	
    }
    
    
    
    
    // atuo create Question
     /* $("#noOfQuestions").change(function(){
    	$("#easyQuestId").empty();
    	$("#medQuestId").empty();
    	$("#diffQuestId").empty();
    	
    	/* console.log($("#noOfQuestions").val());
    	
    	var easy=Math.round($("#noOfQuestions").val()*50/100);
    	var med=Math.round($("#noOfQuestions").val()*30/100);
    	var diff=$("#noOfQuestions").val()-(easy+med);
    	
    	$("#easyQuestId").val();
    	$("#easyMarks").val();
    	$("#medQuestId").val();
    	$("#interMarks").val();
    	$("#diffQuestId").val();
    	$("#diffMarks").val();
    	
    	//console.log(""+$("#easyQuestId").val()+$("#easyMarks").val()+$("#medQuestId").val()+$("#interMarks").val()+$("#diffQuestId").val()+$("#diffMarks").val());
    	
    
     });

     */
    // get students Ids
   
     function getListOfStudents()
     {
    	 
    	 studentIdsLst=[];
     	
     		  $(".clsStudId:checkbox:checked").each(function()
     				    {
     		   		
     		   		studentIdsLst.push($(this).val());
     					
     				    });
     		  console.log(studentIdsLst);
     		  
     		  $(".select-student_close").trigger("click");
     		  		
     }
    
     function checkStudents()
     {
    	 $(".clsStudId").each(function()
				    {
 		  $(".clsStudId").prop('checked','checked');
				    });
 	 
     }
    
    $(".editCreateTest").on("click",function(e){
    	
    	
    	var timerText=$("#timer").html();
    	$(".showTimer").html(timerText);
    	
    	
    	
    	
    });
    
     
     // to Disable EndDate
     
     function disableEndDate()
     {
    	 $("#deadLineDate").hide();
    	 $("#endDate").empty().append("00/default/0000 00:00");
    	
    	 
    	 
     }
     
     function enableEndDate()
     {
    	 $("#deadLineDate").show();
    	 
    	/*  if(("#autoTestType").val()=="2"){
    		 $(".DisableEndDate").show();
    		 
    		    		 
    	 } */
     }
    	 function aDisableEndDate()
    	 {
    		 $("#endDateA").empty();
    		 
    		 if($("#autoTestType").val()=="2"){
        		 $("#DisableAEndDate").hide();
        		 $("#endDateA").empty().append("00/default/0000 00:00");
        		    		 
        	 }else
        		 {
        		 $("#DisableAEndDate").show();
        		 }
    	 }
    
    	 function backPreviewPage(){
    		 $("#previewScreenId").show();
    		 $("#editTest123").hide();
    	 }
    	 
    	 function getDetailForTest()
    	 {
    		$(".testTypeName").removeAttr('checked');
    		 var testType=parseInt($.jStorage.get("testTypePre"));
    		 if(testType==1 || testType=="1"){
    			 $("#eeTestTypeId1").prop('checked','checked');
    			 $("#endDateNew1").val($.jStorage.get("testEndDatePre"));
    		 }else{
    			 $("#eaTestTypeId1").prop('checked','checked');
    			 endDateII
    			 $("#endDateII").hide();
    		 }
    		 
    		 $("#startDateNew1").val($.jStorage.get("testStartDatePre"));
    		
    		 $("#hhNew").val($.jStorage.get("testHHPre"));
    		  $("#mmNew").val($.jStorage.get("testMMPre"));
    		 
    		 var ins=$("#instruction").html();
    		// ins.replace("qCountIds","qCountIds123");
    		// alert(ins);
    		 var questionA=$("#questionAnswer").html();
    		
    		 //$("#questionAnswerText").html(questionA);
    		 
    		 //$("#newInstruction").html(ins);
    		 
    		 $("#previewScreenId").hide();
    		 $("#editTest123").show();
    		 
    		 showPreQuestion();
    		/*  var testN=$(".testType").val();
    		 if(testN=="eTest")
    			 {
    			 $(".EETestType").prop('checked','checked');
    			 
    			 }
    		 else
    			 {
    			 $(".EATestType").prop('checked','checked');
    			 } */
    		 
    	 }
    	 
    	 
    	
    function changeValue(val)
    {
    	
    	$("#testNameE").html(val);
    	
    	
    	
    }
    
    /* auto preview Screen  */
    
    
    
    function previewAutoTestScreen()
    {
    	values=[];
    	
    	createdById=[];
    	
    	
    	var startDate=$('#startDateA').val();
    	var endDate=$('#endDateA').val();
    	var d1=new Date(startDate);
		var d2=new Date(endDate);
		
		
		 
		
		
    	var eMarks=$("#easyMarks").val();
  	
    	var iMarks=$("#interMarks").val();
    	
    	var dMarks=$("#diffMarks").val();
    	
    	var check="${easyCount}";
    	
    	
    	var totalQues=parseInt($("#easyQuestId").val())+parseInt($("#medQuestId").val())+parseInt($("#diffQuestId").val());
    	
    
		
    
		$(".clsTopic:checkbox:checked").each(function()
    			    { values.push($(this).val());
    			    });
    	
		var instruction = $("#autoInstruction").html();
    	 
		var hh=parseInt($('#hh1').val());
    	var mm=parseInt($('#mm1').val());

 	    duration = 0;
 	    
 	    if(hh != undefined && !isNaN(hh))
 	    {
 		    duration += Number(hh * 60);
 	    }
 	
 	    if(mm != undefined && !isNaN(mm))
 	    {
 		    duration += Number(mm);
 	    }
 	    
 		$(".createdBy:checkbox:checked").each(function()
			    {
	   		
	   		createdById.push($(this).val());
				
			    });
    	
    	if($('#testAutoName').val().trim()==''){
    	alert("Please Enter Test Name");
    	return false;
    	} else if($('#autoTestType option:selected').val()=='Test Type'){
    		alert("Please select Test Type");
    	
    		 return false;
    	} else if (d1=='Invalid Date') {
			alert('Please provide test start date');
			 return false;
		} else if($('#autoTestType option:selected').val()=='E-Test'||$('#autoTestType option:selected').val()=='1')
			{	
			if (d2=='Invalid Date') {
			alert('Please provide test end date');
			 return false;
		} 
			} if (d1 >= d2) {
			alert('Start Date Time must be less then End Date Time');
			 return false;
		} if(hh==''&& mm=='')
    	{
    		alert("Please select test duration !!");
    		 return false;
    	} 
    	else if(hh>24)
		{
    		alert('Hours should be less then 24');
    		 return false;
    	}
    	else if(mm>59)
    			{
    			alert('Minute should be less then 60');
    			 return false;
    			 }
    	 if(studentIdsLst=="")
		{
		alert("Please Select Student");
		 return false;
		} 
		
    	
    		
    		 $('#fullPageloader').show();
    		 instruction = instruction.replace('<span id="quesNoAuto">','<span>');
    		$.post("${pageContext.request.contextPath}/schoolTeacher/autoPreviewAssessment.htm",
    		{
    		topicIds:values.toString(),
    		createdBy:createdById.toString(),
    		totalQues:totalQues,
    		eQuest:$("#easyQuestId").val(),
    		mQuest:$("#medQuestId").val(),
    		dQuest:$("#diffQuestId").val(),
    		instruction:instruction,
    		eMarks:eMarks,
    		iMarks:iMarks,
    		dMarks:dMarks,
    		grade:"${param.grade}",
    		sub:"${param.sub}",
    		gradeId:"${param.grdId}",
    		subId:"${param.subId}",
    		secId:"${param.secId}"
    		},
   
    		function(data){
    			$('#fullPageloader').hide();
    			if(data!="false"){
    	
    			$('#autoques').hide();
    				$("#autoQusetionList").html(data).show();
    				
    				
    			}
    		}
    		);  
    	
    	
    	
    	
    	
    	 
    }
    
 
    
    function QusUpdate()
    {
    	var eQus=parseInt($("#easyQuestId").val());
    	var mQus=parseInt($("#medQuestId").val());
    	var dQus=parseInt($("#diffQuestId").val());
    	var totalQus=eQus+mQus+dQus;
    	$("#quesNoAuto").text(totalQus);
    }
    
    
    $("#detailsOfCh").on("click",function(){
    	$("#detailsOfChapTp").empty();
    	 var txt="";
    	   for(var j=1;j<ListChptAndTopics.length;j++)
	    	{
    		   txt+="<table class='table table-bordered table-hover table-striped'><tbody><tr><td><Strong><span >"+ListChptAndTopics[j].ChpName+"</span></Strong><br>"+ListChptAndTopics[j].topicList.substr(1)+"</td></tr></tbody></table>";
    		   
	    	} 
    	   
    	   $("#detailsOfChapTp").append(txt);
    	   
	
    })
    
    
    function removeAssmt(val)
    {
    	var assmtId=val;
    	console.log(assmtId);
    	
    	if(confirm("Are you sure? You want to remove test!!!"))
    		{
    		$.post("${pageContext.request.contextPath}/schoolTeacher/removeAssmt",
    	    		{
    			assmtId:assmtId,
    			subId:"${param.subId}",
    			grdId:"${param.grdId}"
    	    		},
    	   
    	    		function(data){
    	    			
    	    			if(data=="success"){
    	    				$('#fullPageloader').show();
    	    				alert("Remove test");
    	    				$('#fullPageloader').hide();
    	    				window.location.reload();
    	    			}
    	    		}
    	    		);  
    		
    		
    		}
    	
    }
   
    
    

    function removeAuto()
    {
    	
    }


</script>
<!-- Fade & scale -->

<div id="edit-test" class="well">
    <h4>Fade &amp; scale example</h4>
<div class="col-md-12">
				<h3>Test Type</h3>
				</div>
				<div class="6 mrt15 ">
				<input id="" class="EETestType" name="" type="radio"> E-Type <input id="" class="EATestType" type="radio"> Assignment
				</div>
				<div class="col-md-12 mrt15 ">
				<label>Test Name</label>
<input id="Tname" class="ng-valid ng-dirty ng-valid-parse ng-touched" type="text" placeholder="Test Name" name="" value="{{modelTestName |uppercase}}" onchange="changeValue(this.value)">
				</div>
				<div class="col-md-6 mrt15 ">
				<label>Test Start Date & Time</label>
<input id="startDateE" class="ng-valid ng-dirty ng-valid-parse ng-touched" type="text" placeholder="Test Name" name="" >
				</div>
				<div class="col-md-6 mrt15 ">
				<label> Deadline Date and Time
</label>
<input id="test-name" class="ng-valid ng-dirty ng-valid-parse ng-touched" type="text" placeholder="Test Name" name="" value="{{modelEndDate}}">
				</div>
				<div class="col-md-5 mrt15" style="padding:2px">
				<label style="padding-left:14px"> Time Limit
</label>
  <br>
  <div class="col-md-4"><input type="text" name="" placeholder="HH" class="" id="test-name" value="{{modelhh}} "></div>
  <div class="col-md-4"><input type="text" name="" placeholder="MM" class="" id="test-name" value="{{modelmm}} "></div>
				</div>
				<div class="col-md-12 mrt15">
				<label style="padding-left:14px"> Instruction
</label>
				<textarea rows="" class="form-control" id="gInstruction"=></textarea>
				</div>
				
				
				 <div class="col-md-12 mrt15">
  <div class="preview-test-question-con seq">
  <div class="preview-test-question"><div>
  <span class="pull-right" style="width:20px;margin-left:5px;"><i class="fa fa-times" aria-hidden="true"></i>
  </span>
  <div class="pull-right btn btn-xs btn-warning">54</div> 
  </div><div id="questionAnswerText"></div>1.In the adjacent figure, three sacks of rice with their respective weights are given. Find the total weight of all the three sacks of rice.<br>
  </div>
  <input type="radio" readonly="readonly" value="1" name="ans">2590 kg<br>
  <input type="radio" readonly="readonly" value="1" name="ans">2600 kg<br>
  <input type="radio" readonly="readonly" value="1" name="ans">2610 kg<br>
  <input type="radio" readonly="readonly" value="1" name="ans">2640 kg<br>
  </div>
  </div>
  <div class="col-md-3  mrt15">
				<button class="btn btn-primary">Update</button>  <button class="edit-test_close btn btn-default">Close</button>
				</div>
   
   
</div>

<!--select-student  -->
<div id="select-student" class="well">
    <h4>Select Student</h4>
<table class="table table-bordered table-hover" >
				
					<tr>
					<td><strong>SR. No</strong></td>
					<td><strong>Select</strong></td>
					<td><strong>Student</strong></td>
					
					</tr>
						<c:if test="${not empty studentList}">
					<c:forEach var='studentList' items="${studentList}" varStatus="status" step="1">
					<tr>
					
							
						<td>${status.count }</td>
						
						<td><input type="checkbox" class="clsStudId" id="str_${studentList.id}"  value="${studentList.id}" checked="checked" ></input></td>
						
						<td>${studentList.firstName}</td>
					
					</tr>
					</c:forEach>
					</c:if>
					</table>
					
  <div class="col-md-3  mrt15">
  <button type="button" class="btn btn-danger" id="" onclick="getListOfStudents()">OK</button>
				  <button class="select-student_close btn btn-default" style="display: none">Close</button>
				</div>
   
   
</div>
<!--------  -->

<script>
$(document).ready(function () {

    $('#edit-test').popup({
        pagecontainer: '.container',
        transition: 'all 0.3s'
    });
    
    $('#select-student').popup({
        pagecontainer: '.container',
        transition: 'all 0.3s'
    });

    $('#AutoPreviewScreenId').hide();
});
</script>
