<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="schoolTeacherHeader.jsp">
<jsp:param value="assignTask" name="header"/>
</jsp:include>

<div id="page-wrapper">

	<div class="container-fluid">

		<!-- Page Heading -->
		<div class="row">
			<div class="col-md-12">
				<h1>Assign Task</h1>
				<ol class="breadcrumb">
					<li><a href="${pageContext.request.contextPath}/schoolTeacher/teacherHome.htm">Home</a></li>
					<li class="active">Assign Task</li>


				</ol>
			</div>
		</div>
		<!-- /.row -->

		`
		<div class="clearfix"></div>

	</div>


	<div class="col-md-12">


		<ul class="nav nav-tabs assign-tab">
			<li class="active"><a data-toggle="tab" href="#assign-task"><span
					class="fa fa-copy"> &nbsp;</span> Evaluation Name</a></li>
			<li><a data-toggle="tab" href="#assign-topic"><span
					class="fa fa-bookmark"> &nbsp;</span>Assign Topic</a></li>
			<li><a data-toggle="tab" href="#assign-resource"><span
					class="fa fa-book"> &nbsp;</span>Assign Resources</a></li>
		</ul>

		<div class="clearfix"></div>

		<!--tab-cont-->



		<div class="tab-content tab-content2">


			<!--assign-task-test-->
			<div id="assign-task" class="tab-pane active ">
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
								<th> Assigned On</th>
								<th>Status</th>
								<th>Action</th>
							</tr>
						</thead>


						<tbody>
						
							<c:if test="${not empty assessmentList}">
								<c:forEach var="assessment" items="${ assessmentList}"
									varStatus="status">
									<fmt:formatDate pattern="dd-MMM-yyyy"
												value="${currentDate}" var="date" /> 
									
									<tr>
										<td>${status.count }</td>
										<!-- <td><input name="" type="checkbox" value=""
											data-toggle="modal" data-target="#myModal"></td> -->
										<td><a href="javascript:void(0)" data-target="#myModal4"
												data-toggle="modal" class="testNameCls"
												id="testNameId${assessment.id}">${assessment.testName }</a></td>
										<td>${assessment.grade.name }</td>
										<td><c:if test="${assessment.type eq 1 }">E-Test</c:if> <c:if
												test="${assessment.type eq 2 }">Assignment</c:if></td>
										<td><fmt:formatDate pattern="dd-MMM-yyyy"
												value="${assessment.createdOn}" />
												
												</td>
										<td><fmt:formatDate pattern="dd-MMM-yyyy"
												value="${assessment.startDate}" /></td>
										<td><c:if
												test="${not empty assessment.assignedAssessment }">
												
												<c:choose>
												<c:when test="${assessment.status eq 2 && currentDate < assessment.startDate && assessment.type eq 1}">
													<a href="javascript:void(0);" data-toggle="modal"
														data-target="#myModal2" class="assignedAssessmentCls"
														dir="${assessment.grade.id}@${assessment.id}@${assessment.grade.name}" id="${status.count}${assessment.testName}">Assigned </a>
												
												</c:when>
												
												<c:when test="${assessment.status eq 2 && (currentDate < assessment.startDate || currentDate > assessment.startDate) && assessment.type eq 2}">
													<a href="javascript:void(0);" data-toggle="modal"
														data-target="#myModal2" class="assignedAssessmentCls"
														dir="${assessment.grade.id}@${assessment.id}@${assessment.grade.name}" id="${status.count}${assessment.testName}">Assigned </a>
												
												</c:when>
												
												
												
											
												<c:otherwise>
												
												 <a href="javascript:void(0);" data-toggle="modal"
														data-target="#myModal10" class="assignedAssessmentClass"
														dir="${assessment.grade.id}@${assessment.id}@${assessment.grade.name}" id="${status.count}${assessment.testName}">Assigned</a> 
												
												</c:otherwise>
												</c:choose>
												
												
												<%-- <c:if test="${assessment.status eq 2 && assessment.createdOn > assessment.startDate}">
													<a href="javascript:void(0);" data-toggle="modal"
														data-target="#myModal2" class="assignedAssessmentCls"
														dir="${assessment.grade.id}@${assessment.id}@${assessment.grade.name}" id="${status.count}${assessment.testName}">Assigned</a>
												</c:if>
												 <c:if test="${assessment.status eq 2 }">
													<a href="javascript:void(0);" data-toggle="modal"
														data-target="#myModal10" class="assignedAssessmentClass"
														dir="${assessment.grade.id}@${assessment.id}@${assessment.grade.name}" id="${status.count}${assessment.testName}">Assigned</a>
												</c:if> --%>
											</c:if> <c:if
												test="${empty assessment.assignedAssessment && assessment.status eq 1  }">
												<c:if test="${assessment.status eq 1 }">
													<a href="javascript:void(0);" data-toggle="modal"
													  
														data-target="#myModal3" class="unassignedCls"
														dir="${assessment.grade.id}@${assessment.id}@${assessment.grade.name}" id="${status.count}${assessment.testName}">Unassigned</a>
												</c:if>
												<c:if test="${assessment.status eq 2 }">
													<a href="javascript:void(0);" >Unassigned</a>
												</c:if>
											</c:if></td>
										<td><%-- <c:if
												test="${not empty assessment.assignedAssessment && assessment.status eq 1}">
												<a
													href="${pageContext.request.contextPath}/schoolTeacher/publishedAssessment/${assessment.id}.htm"
													class="publishAssessmentCls">Publish It</a>
											</c:if> --%> <c:if
												test="${not empty assessment.assignedAssessment && assessment.status eq 2}">
												Published
											</c:if> <c:if
												test="${empty assessment.assignedAssessment && assessment.status eq 1}">
												N/A
											</c:if></td>
									</tr>
								</c:forEach>

							</c:if>




						</tbody>
					</table>

				</div>
				<!--  <div class="col-md-2"><button type="submit" class="btn btn-danger">NEXT</button></div> -->


				<div class="clearfix"></div>




			</div>
			<!--assign-task-test-->


			<!--assign-topic-->
			<div id="assign-topic" class="tab-pane fade">
				<div class="col-md-4 col-sm-4">
					Select Grade <select name="grade" id="gradeId1"
						onchange="populateSubject1(this.value)">
						<option value="">Select Grade</option>
							<c:forEach var="ts" items="${teacherSection}">
												<option class='clsGrade1'
													value='${ts.section.sessionSchoolGrade.grade.id}'>${ts.section.sessionSchoolGrade.grade.name}</option>
											</c:forEach>
					
					
										</select>
				</div>

				<div class="col-md-4 col-sm-4">
					Select Subject <select id="subjectId1" class="subjectCls">
						<option value="">Select Subject</option>

					</select>
				</div>

				<div class="col-md-4 col-sm-4">
					Select Chapter <select id="chapterId1">
						<option value="">Select Chapter</option>

					</select>
				</div>

				<div class="col-md-12 col-sm-12">
					<table width="100%" border="0" cellspacing="0" cellpadding="0"
						class="table table-bordered table-hover table-responsive table-striped"
						id="topicAssignedId">
						<tr>
							<td><strong>Topic Name</strong></td>
							<td><strong>Students Assigned</strong></td>
							<td><strong>Action</strong></td>
						</tr>
					</table>
				</div>

				<div class="clearfix"></div>
			</div>
			<!--assign-topic end-->

			<!--assign-resource-->
			<div id="assign-resource" class="tab-pane fade">
				<div class="col-md-3 col-sm-6">
					Select Grade<select name="grade" id="gradeId3"
						onchange="populateSubject(this.value)" >
						
						<option value="">Select Grade</option>
									<c:forEach var="ts" items="${teacherSection}">
									
										<option value='${ts.section.sessionSchoolGrade.grade.id}' class="clsGrade">${ts.section.sessionSchoolGrade.grade.name}</option>
									
										</c:forEach>
		
					</select>
				</div>

				<div class="col-md-3 col-sm-6">
					Select Subject <select id="subjectId3" class="subjectCls">
						<option value="">Select Subject</option>

					</select>
				</div>

				<div class="col-md-3 col-sm-6">
					Select Chapter <select id="chapterId3">
						<option value="">Select Chapter</option>

					</select>
				</div>

				<div class="col-md-3 col-sm-6">
					Select Topic <select id="topicId3">
						<option value="">Select Topic</option>

					</select>
				</div>

				<div class="col-md-12 col-sm-12">
					<table width="100%" border="0" cellspacing="0" cellpadding="0"
						class="table table-bordered table-hover table-responsive table-striped"
						id="assignUnassignModuleId">
						<tr>
							<td><strong>Resources Name</strong></td>
							<td><strong>Students Assigned</strong></td>
							<td><strong>Action</strong></td>
						</tr>

					</table>
				</div>

				<div class="clearfix"></div>
			</div>
			<!--assign-resource end-->

		</div>
		<!--tab-cont end-->

		<div class="clearfix"></div>




		<!-- /.container-fluid -->




	</div>
	<!-- /#page-wrapper -->




</div>
<!-- /#wrapper -->




<!-- Modal3 -->
<div id="myModal3" class="modal fade" role="dialog">
	<div class="modal-dialog">

		<!-- Modal content-->
		<div class="modal-content">
			<form:form
				action="${pageContext.request.contextPath}/schoolTeacher/assignAssessment.htm"
				method="post" id="assignAssessmentForm" class="form-horizontal"
				role="form">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<div class="modal-title">
						<span>Status</span> Unassigned
					</div>
				</div>
				<div class="modal-body">

					<p>Please Select the grade and section for which you want to
						assign the test</p>
					<input type="hidden" name="assessmentId" id="assessmentId" />
					<div class="col-md-4 mrt10 mrb10">
						<div class="row">
							Select Grade <select disabled="disabled" id="gradeId">
							</select>

						</div>
					</div>
					<div class="col-md-4  col-md-offset-1 mrt10 mrb10">
						<div class="row">
							Select Section <select id="sectionId" name="sectionId"
								required="required">
								<option value="">Select Section</option>

							</select>
						</div>
					</div>

					<!--  <div class="col-md-2 col-md-offset-1  mrt10 mrb10"><div class="row">Select All
  <input type="checkbox">
 </div></div> -->

					<table style="display: none;" width="90%" style="overflow: scroll"
						border="0" cellspacing="0" cellpadding="0"
						class="topic-per table-striped table table-bordered table-hover table-condensed"
						id="unassignedStudentIds">
						<thead>
							<tr>
								<th><input type="checkbox" id="assignedAllId" />Select All</th>
								<th>Admission No</th>
								<th>Student Name</th>
								<th>Grade</th>
							</tr>
						</thead>
						<div class="row">
						<tbody>
						
						</tbody>
						</div>
					</table>


				</div>
				<div class="modal-footer">
					<div class="col-md-12 text-center ">
						<input type="submit" class="btn btn-primary btn-md"
							id="assignedAssesssmentId"  onclick="publishTest($('#assessmentId').val())" style="display: none;" value="Assign" />
					</div>


				</div>
			</form:form>
		</div>

	</div>
</div>
<!-- Modal3 end -->




<!-- Modal2 -->
<div id="myModal2" class="modal fade" role="dialog">
	<div class="modal-dialog">

		<!-- Modal content-->
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<div class="modal-title">
					<span>Status</span> Assigned
				</div>
			</div>
			<div class="modal-body">

				<ul class="nav nav-tabs assign-tab">

					<li class="active"><a data-toggle="tab" href="#viewAssigned">View
							Assigned</a></li>
					<li><a data-toggle="tab" href="#AssignNew">Assign New</a></li>
				</ul>

				<div class="tab-content tab-pane3">
					<div id="viewAssigned" class="tab-pane fade in active">
						<p>Test '<span id="myTestId2"></span>' is assigned to below students, you
							can remove students to unassign the test from them</p>
						<form:form
							action="${pageContext.request.contextPath}/schoolTeacher/unAssignAssessment.htm"
							method="post" id="assignAssessmentForm" class="form-horizontal"
							role="form">
							<input type="hidden" name="assessmentId" id="hiddenAssessmentId" />
							<table width="90%" border="0" cellspacing="0" cellpadding="0"
								class="table-striped table table-bordered table-hover table-condensed">

								<thead>
									<tr>
										<th width="9%"><input type="checkbox" id="selectAllId" /></th>
										<th width="35%"><strong>AdmissionNo</strong></th>
										<th width="40%"><strong>Student Name</strong></th>
										<th width="60%"><strong>Grade</strong></th>
									</tr>
								</thead>
							</table>
							
							<div class="row">
							<div class="col-xs-12 col-sm-12  mrb10 panel-body3">
								<table width="90%" border="0" cellspacing="0" cellpadding="0"
									class="topic-per table-striped table table-bordered table-hover "
									id="assignedAssessmentStuCls">

								</table>
							</div>
							</div>
							
							
							<div class="col-md-4 col-xs-5 ">
								<input type="submit" class="btn btn-info btn-md"
									id="unAssignAssessmentId" value="Save Changes">
							</div>
							<!--  <div class="col-md-1 "> <a href="#" ><b class="glyphicon glyphicon-trash assign-delete pull-right" ></b></a></div>  -->
						</form:form>
					</div>



					<div id="AssignNew" class="tab-pane fade ">

						<p>Select the Grade and section to which you want to assign
							this test</p>
						<form:form
							action="${pageContext.request.contextPath}/schoolTeacher/assignAssessment.htm"
							method="post" id="assignAssessmentForm" class="form-horizontal"
							role="form">
							<div class="col-md-4 col-xs-4 mrt10 ">
								<div class="row">
									Select Grade <select id="gradeId5" disabled="disabled">
									</select> <input type="hidden" name="assessmentId"
										id="hiddenAssessmentId1" />
								</div>
							</div>
							<div
								class="col-md-4 col-xs-4  col-xs-offset-1 col-md-offset-1 mrt10 ">
								<div class="row">
									Select Section <select id="sectionId5" name="sectionId" required="required">
										<option value="">Select Section</option>
									</select>
								</div>
							</div>

							<div class="col-xs-12 col-sm-12">
								<div class="row">
									<table width="90%" border="0" cellspacing="0" cellpadding="0"
										class="table-striped table table-bordered table-hover table-condensed">

										<thead>
											<tr>
												<th width="9%"><input type="checkbox"
													id="selectAllId1" /></th>
														<th width="35%"><strong>AdmissionNO</strong></th>
												<th width="40%"><strong>Student Name</strong></th>
													<th width="60%"><strong>Grade</strong></th>
											</tr>
										</thead>
									</table>
								</div>
							</div>
							
							<div class="row">
							<div class="col-xs-12 col-sm-12  mrb10 panel-body3">

								<table width="90%" border="0" cellspacing="0" cellpadding="0"
									class="topic-per table-striped table table-bordered table-hover "
									style="display: none;" id="newAssignAssessment">

								</table>

							</div>
							</div>

							<div class="col-md-4 col-xs-6 ">
								<input type="submit" class="btn btn-primary btn-md"
									value="Assign" id="againAssignId">
							</div>
						</form:form>
					</div>

				</div>

				<div class="clearfix"></div>
			</div>



		</div>

	</div>
</div>
<!-- Modal2 end -->


<!-- Modal10 -->
<div id="myModal10" class="modal fade" role="dialog">
	<div class="modal-dialog">

		<!-- Modal content-->
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<div class="modal-title">
					<span>Assignment</span>
				</div>
			</div>
			<div class="modal-body">

			

				<div class="tab-content tab-pane3">
					<div id="viewAssigned" class="tab-pane fade in active">
						<p>Test '<span id="testNameId10"></span>' is assigned to given below students of this Grade/Section.</p>
					
							<table width="90%" border="0" cellspacing="0" cellpadding="0"
								class="table-striped table table-bordered table-hover table-condensed">

								<thead>
									<tr>
										<th width="9%">S.No.</th>
										<th width="35%"><strong>Admission No</strong></th>
										<th width="40%"><strong>Student's Name</strong></th>
										<th width="60%"><strong>Grade</strong></th>
									</tr>
								</thead>
							</table>
							
							<div class="row">
							<div class="col-xs-12 col-sm-12  mrb10 panel-body3">
								<table width="90%" border="0" cellspacing="0" cellpadding="0"
									class="topic-per table-striped table table-bordered table-hover "
									id="assignedAssessmentStuClass">

								</table>
							</div>
							</div>
							
							
							<!--  <div class="col-md-1 "> <a href="#" ><b class="glyphicon glyphicon-trash assign-delete pull-right" ></b></a></div>  -->
					</div>




				</div>

				<div class="clearfix"></div>
			</div>



		</div>

	</div>
</div>
<!-- Modal10 end -->



<!-- Modal7 -->
<div id="myModal7" class="modal fade" role="dialog">
	<div class="modal-dialog">

		<!-- Modal content-->
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<div class="modal-title">
					<span>Assign Resources</span> 
				</div>
			</div>
			<div class="modal-body">
				<input type="hidden" id="hiddenTopicId" /> <input type="hidden"
					id="hiddenRowId" /> <input type="hidden" id="hiddenStudentCount" />
				<input type="hidden" id="hiddenModuleId" /> <input type="hidden"
					id="hiddenGradeIdId" /> <input type="hidden" id="hiddenSubjectId" />
				<p>Select the section of this Grade to which you want to assign this topic for Practice or learning. It
					will be assigned to all students of selected section.</p>
				<div class="col-md-5 mrt10 mrb10">
					<div class="row">
						Select Grade <select id="gradeId2" disabled="disabled">
						</select>

					</div>
				</div>
				<div class="col-md-5  col-md-offset-1 mrt10 mrb10">
					<div class="row">
						Select Section <select id="sectionId2">
							<option value="">Select Section</option>

						</select>
					</div>
				</div>




				<div class="clearfix"></div>
			</div>
			<div class="modal-footer">
				<div class="col-md-12 text-center ">
					<a href="javascript:void(0);"
						class="btn btn-primary btn-md assignTopicModuleCls"
						id="topicAssignId" style="display: none;">Assign</a> <a
						href="javascript:void(0);"
						class="btn btn-primary btn-md assignTopicModuleCls"
						id="moduleAssignId" style="display: none;">Assign Resources</a>
				</div>

			</div>
		</div>
	</div>
</div>
<!-- Modal7 end -->

<!-- Modal4 -->
	<div id="myModal4" class="modal fade" role="dialog">
		<div class="modal-dialog assign-name-pop">
			<div class="modal-content" id="assessmentPreview"></div>
		</div>
	</div>
	<!-- Modal4 end -->

<!-- Modal5 -->
<div id="myModal5" class="modal fade" role="dialog">
	<div class="modal-dialog">

		<!-- Modal content-->
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<div class="modal-title">
					<span>Un-Assigned</span> 
				</div>
			</div>
			<div class="modal-body">
				<input type="hidden" id="hiddenTopicId1" /> <input type="hidden"
					id="hiddenRowId1" /> <input type="hidden" id="hiddenStudentCount1" />
				<input type="hidden" id="hiddenModuleId1" /> <input type="hidden"
					id="hiddenGradeIdId1" /> <input type="hidden"
					id="hiddenSubjectId1" />
				<table width="90%" border="0" cellspacing="0" cellpadding="0"
					class="topic-per table-striped table table-bordered table-hover table-condensed">
					<tr>

						<td><span id="topicOrResourceNameId"></span> :</td>
						<td><span id="topicOrResourceName"></span></td>
					</tr>
					<tr>
						<td>Assigned Grade :</td>
						<td><div id="gradeName1"></div></td>
					</tr>
					<tr>
						<td>Assigned Section :</td>
						<td><select id="sectionId3" name="sectionId">
						</select></td>
					</tr>
					<!--   <tr>
    <td><input type="checkbox"/></td>
     <td>Select to unassign the topic from section</td>

  </tr>
    -->
				</table>


			</div>
			<div class="modal-footer">

				<div class="col-md-4 col-xs-4 ">
					<div class="btn btn-info btn-md unassignTopicModuleCls"
						id="unAssignTopicId" style="display: none;">Un-Assign Resources</div>
					<div class="btn btn-info btn-md unassignTopicModuleCls"
						id="unAssignModuleId" style="display: none;">unassign Module</div>
				</div>
			</div>
		</div>

	</div>
</div>
<!-- Modal5 end -->

<!-- Modal6 -->
<!-- <div id="myModal6" class="modal fade" role="dialog">
	<div class="modal-dialog">

		Modal content
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<div class="modal-title">
					<span>Status</span> Assigned
				</div>
			</div>
			<div class="modal-body">
				<p>Test 'July Math Test' is assigned to below students, you can
					remove students to unassign the test from them</p>
				<table width="90%" border="0" cellspacing="0" cellpadding="0"
					class="table-striped table table-bordered table-hover table-condensed">

					<thead>
						<tr>
							<th width="20%"><input type="checkbox" /></th>
							<th width="50%"><strong>Student Name</strong></th>
							<th><strong>Grade</strong></th>
						</tr>
					</thead>
				</table>
				<div class="col-xs-12 col-sm-12  mrb10 panel-body3">
					<table width="90%" border="0" cellspacing="0" cellpadding="0"
						class="topic-per table-striped table table-bordered table-hover ">
						<tr>
							<td width="20%"><input type="checkbox" /></td>
							<td width="50%">Ashish</td>
							<td>5th</td>
						</tr>
						<tr>
							<td><input type="checkbox" /></td>
							<td>Adi</td>
							<td>5th</td>
						</tr>
						<tr>
							<td><input type="checkbox" /></td>
							<td>Ajay</td>
							<td>5th</td>
						</tr>
						<tr>
							<td><input type="checkbox" /></td>
							<td>Rohit</td>
							<td>5th</td>
						</tr>
						<td><input type="checkbox" /></td>
						<td>Adi</td>
						<td>5th</td>
						</tr>
						<tr>
							<td><input type="checkbox" /></td>
							<td>Ajay</td>
							<td>5th</td>
						</tr>
						<tr>
							<td><input type="checkbox" /></td>
							<td>Rohit</td>
							<td>5th</td>
						</tr>

					</table>
				</div>
				<div class="col-md-4 col-xs-5 ">
					<div class="btn btn-info btn-md">Save Changes</div>
				</div>
				<div class="col-md-1 ">
					<a href="#"><b
						class="glyphicon glyphicon-trash assign-delete pull-right"></b></a>
				</div>
				<div class="clearfix"></div>
			</div>

		</div>

	</div>
</div> -->
<!-- Modal6 end -->

<!-- Modal8 -->
<div id="myModal8" class="modal fade" role="dialog">
	<div class="modal-dialog">

		<!-- Modal content-->
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<div class="modal-title">
					<span>Status</span> Publish
				</div>
			</div>
			<div class="modal-body">

				<div class="col-md-5 mrt10 mrb10">
					<div class="row">
						Select Grade <select>
							<option selected="" value="1">9</option>

						</select>

					</div>
				</div>
				<div class="col-md-5  col-md-offset-1 mrt10 mrb10">
					<div class="row">
						Select Section <select>
							<option selected="" value="1">1</option>
							<option value="2">2</option>
							<option value="3">3</option>
							<option value="4">4</option>
						</select>
					</div>
				</div>




				<div class="clearfix"></div>
			</div>
			<div class="modal-footer">
				<div class="col-md-12 text-center ">
					<div class="btn btn-primary btn-md">Publish</div>
				</div>


			</div>
		</div>

	</div>
</div>
<!-- Modal8 end -->


<script type="text/javascript">
	var chapterList;

	$(document).ready(function()
		    {
		
		var d = new Date();
	    var n = d.getDate()+"-"+d.getMonth()+"-"+d.getFullYear();
	    console.log(n);
	    console.log("date"+
	    		Date.now());
		console.log("todays date"+d.getFullYear()+"-"+d.getMonth()+"-"+d.getDate()+ " " +d.getTime());
			  
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
		    	  
		   $('.clsGrade1').each(function()
				    {
					    var val = $(this);
					    var flag = false;
					    $('.clsGrade1').each(function()
					    {
						    if($(this).val() == val.val())
							    if(!flag)
								    flag = true;
							    else
								    $(this).remove();
					    });
				    });
		    });
	
    //code for get assessment preview
    $(".testNameCls").on("click", function()
    {
	    var assessmentId = $(this).attr('id').substr(10);
	    $.post("${pageContext.request.contextPath}/schoolTeacher/assessmentPreview/" + assessmentId, function(data)
	    {
		    $("div#assessmentPreview").html(data);
	    });
    });
    
	
    //get all chapters by subject
    $(".subjectCls").on("change", function()
    {
    	var subId=$(this).attr('id');
    	if(subId=='subjectId1'){
	    $("#chapterId1").empty().append("<option value=''>Select Chapter</option>");
	    $("#topicAssignedId").empty();
	    $("#topicAssignedId").append('<tr><td><strong>Topic Name</strong></td><td><strong>Student Assigned</strong></td><td>Action</td></tr>')
	  
    	}else if(subId=='subjectId3'){
    	    $("#chapterId3").empty().append("<option value=''>Select Chapter</option>");
    	    $("#topicId3").empty().append("<option value=''>Select Topic</option>");
    	    $("#assignUnassignModuleId").empty();
    	    $("#assignUnassignModuleId").append('<tr><td><strong>Resources Name</strong></td><td><strong>Student Assigned</strong></td><td>Action</td></tr>')
    	}
    	
    	var subjectId = $(this).val();
	    var id = $(this).attr('id').substr(9);
	    var gradeId = $("#gradeId" + id).val();
	    $("#chapterId" + id).empty();
	    $("#chapterId" + id).append('<option value="">Select Chapter</option>');
	    if(subjectId!=null && subjectId!=""){
	    $.post("${pageContext.request.contextPath}/ajax/schoolTeacher/getChapters?gradeId=" + gradeId + "&subjectId=" + subjectId, function(data)
	    {
		    if(data != null && data.length > 0)
		    {
			    chapterList = data;
			    var val = '';
			    for (var i = 0; i < data.length; i++)
			    {
				    val = val + '<option value="'+data[i].id+'">' + data[i].name + '</option>';
			    }
			    $("#chapterId" + id).append(val);
		    }
	    }, 'json');
	    }
    });
    //end
    
    $("#sectionId").on("change", function()
    {
	    $("table#unassignedStudentIds tbody").empty();
	    $("#assignedAllId").removeAttr('checked');
	    var sectionId = $(this).val();
	    if(sectionId != null && sectionId != "")
	    {
		    getStudentDetails(sectionId);
	    }
	    else
	    {
		    $("table#unassignedStudentIds").hide();
		    $("#assignedAssesssmentId").hide();
	    }
    });
    
    $("#sectionId5").on("change", function()
    {
	    $("table#newAssignAssessment").empty();
	    $("#selectAllId1").removeAttr('checked');
	    var sectionId = $(this).val();
	    if(sectionId != null && sectionId != "")
	    {
		    getStudentDetailsForAssessment(sectionId);
	    }
	    else
	    {
		    $("table#newAssignAssessment").hide();
		    
	    }
    });
    
    //code for get topic assign details of change chapter
    $("#chapterId1").on("change", function()
    {
	    var chapterId = $(this).val();
	    var subjectId = $("#subjectId1").val();
	    $("#topicAssignedId").empty();
	    $("#topicAssignedId").append('<tr><td><strong>Topic Name</strong></td><td><strong>Student Assigned</strong></td><td>Action</td></tr>')
	    if(chapterId != null && chapterId != "")
	    {
		    $.post("${pageContext.request.contextPath}/ajax/rest/getAssignUnassignTopicDetails/" + subjectId + "/" + chapterId, function(data)
		    {
			    if(data != null && data.length > 0)
			    {
				    var val = '';
				    for (var i = 0; i < data.length; i++)
				    {
					    val = val + '<tr><input type="hidden" id="topics'+i+'" value="'+data[i].id+'"/><td><span id="topicNameId'+i+'">' + data[i].name + '</span></td>';
					    if(parseFloat(data[i].studentAssignCount) > 0)
					    {
						    val = val + '<td><a  id="studentCount' + i + '" data-toggle="modal" data-target="#myModal6">' + data[i].studentAssignCount + '</a></td>' + '<td><a href="javascript:void(0);" data-toggle="modal" data-target="#myModal5" id="assignTopic' + i + '" onclick="unAssignTopic(' + i + ');">Unassign</a></td>';
					    }
					    else
					    {
						    val = val + '<td><a  id="studentCount' + i + '" ">' + data[i].studentAssignCount + '</a></td><td><a href="javascript:void(0);" data-toggle="modal" data-target="#myModal7" onclick="assignTopic(' + i + ');" id="assignTopic' + i + '">assign</a></td>';
					    }
					    val = val + '</tr>';
				    }
				    $("#topicAssignedId").append(val);
			    }
		    }, "json");
	    }
    });
    //end 
    
    //code for get module assign details of change topic
    $("#topicId3").on("change", function()
    {
	    var topicId = $(this).val();
	    
	    $("#assignUnassignModuleId").empty();
	    $("#assignUnassignModuleId").append('<tr><td><strong>Resources Name</strong></td><td><strong>Student Assigned</strong></td><td>Action</td></tr>')
	    if(topicId != null && topicId != "")
	    {
		    $.post("${pageContext.request.contextPath}/ajax/rest/getAssignUnassignModuleDetails/" + topicId, function(data)
		    {
			    if(data != null && data.length > 0)
			    {
				    var val = '';
				    for (var i = 0; i < data.length; i++)
				    {
					    val = val + '<tr><input type="hidden" id="module'+i+'" value="'+data[i].id+'"/><td><span id="moduleNameId'+i+'">' + data[i].name + '</span></td>';
					    if(parseFloat(data[i].count) > 0)
					    {
						    val = val + '<td><a  id="studentCounts' + i + '" data-toggle="modal" data-target="#myModal6">' + data[i].count + '</a></td>' + '<td><a href="javascript:void(0);" data-toggle="modal" data-target="#myModal5" id="assignModule' + i + '" onclick="unAssignModule(' + i + ');">Unassign</a></td>';
					    }
					    else
					    {
						    val = val + '<td><a  id="studentCounts' + i + '">' + data[i].count + '</a></td><td><a href="javascript:void(0);" data-toggle="modal" data-target="#myModal7" onclick="assignModule(' + i + ');" id="assignModule' + i + '">assign</a></td>';
					    }
					    val = val + '</tr>';
				    }
				    $("#assignUnassignModuleId").append(val);
			    }
		    }, "json");
	    }
    });
    //end 
    
    $(".unassignedCls").click("click", function()
    {
	    var data = $(this).attr('dir').split('@');
	    $("#assignedAllId").removeAttr('checked');
	    var gradeId = data[0];
	   // var publishid=$("#assessmentId").val();
	    $("#gradeId").empty();
	    $("#gradeId").append('<option>' + data[2] + '</option>');
	    $("#assessmentId").val(data[1]);
	    $("#sectionId").empty();
	    $("table#unassignedStudentIds tbody").empty();
	    $("table#unassignedStudentIds").hide();
	    $("#assignedAssesssmentId").hide();
	    setDropdownList('#sectionId', gradeId, '${pageContext.request.contextPath}/ajax/rest/getSectionByGradeId', 'Select Section', false)
    });
    
    $(".assignedAssessmentCls").on("click", function()
    {
	    var data1 = $(this).attr('dir').split('@');
	    $("#selectAllId").removeAttr('checked');
	    $("#selectAllId1").removeAttr('checked');
	    $("#newAssignAssessment").empty();
	    var testName=$(this).attr('id').substr(1);
	    $("#myTestId2").html(testName);
	    var gradeId = data1[0];
	    $("#gradeId5").empty();
	    $("#gradeId5").append('<option>' + data1[2] + '</option>');
	    $("#hiddenAssessmentId").val(data1[1]);
	    $("#hiddenAssessmentId1").val(data1[1]);
	    $("#sectionId5").empty();
	    $("table#assignedAssessmentStuCls").empty();
	    $("table#assignedAssessmentStuCls").hide();
	    $("#assignedAssesssmentId").hide();
	    setDropdownList('#sectionId5', gradeId, '${pageContext.request.contextPath}/ajax/rest/getSectionByGradeId', 'Select Section', false)
	    $.post("${pageContext.request.contextPath}/ajax/rest/getAllAssignedAssessmentStudentList?asssessmentId=" + data1[1], function(data)
	    {
		    if(data != null && data.length > 0)
		    {
			    var val = '';
			    for (var i = 0; i < data.length; i++)
			    {
				    val = val + '<tr><td width="8%"><input type="checkbox" class="studentIdsCls" name="studentIds" value="'+data[i].id+'"/></td><td  width="35%">' + data[i].admissionNo + '</td><td  width="40%">' + data[i].firstName + '</td><td>' + data1[2] + '</td></tr>';
			    }
			    $("#assignedAssessmentStuCls").append(val);
			    $("#assignedAssessmentStuCls").show();
		    }
		    
	    }, 'json');
    });
    
    
    $(".assignedAssessmentClass").on("click", function()
    	    {
    		    var data1 = $(this).attr('dir').split('@');
    		    var testName=$(this).attr('id').substr(1);
    		    $("#testNameId10").html(testName);
    		    $.post("${pageContext.request.contextPath}/ajax/rest/getAllAssignedAssessmentStudentList?asssessmentId=" + data1[1], function(data)
    		    {
    			    if(data != null && data.length > 0)
    			    {
    				    var val = '';
    				    for (var i = 0; i < data.length; i++)
    				    {
    					    val = val + '<tr><td width="8%">'+(i+1)+'.</td><td  width="35%">' + data[i].admissionNo + '</td><td  width="40%">' + data[i].firstName + '</td><td>' + data1[2] + '</td></tr>';
    				    }
    				    $("#assignedAssessmentStuClass").html(val);
    				    $("#assignedAssessmentStuClass").show();
    			    }
    			    
    		    }, 'json');
    	    });
    
    $("#assignedAssesssmentId").on("click", function()
    {
	    var flag = parseFloat(0);
	    $(".userIdsCls").each(function()
	    {
		    if($(this).prop('checked') == true)
		    {
			    flag++;
		    }
	    });
	    if(flag == 0)
	    {
		    alert("Please Select atleast one student for assigned test");
		    return false;
	    }
    });
    
    
    
    
    function publishTest(id){
    	var id1=id;
    	//alert("my value"+id1);
    	$.post("${pageContext.request.contextPath}/schoolTeacher/publishedAssessment/"+id1,function(data){
    		
    		alert("data"+data);
    	})
    	
    }
    
    $("#unAssignAssessmentId").on("click", function()
    {
	    var flag = parseFloat(0);
	    $(".studentIdsCls").each(function()
	    {
		    if($(this).prop('checked') == true)
		    {
			    flag++;
		    }
	    });
	    if(flag == 0)
	    {
		    alert("Please Select atleast one student for un-assigned test");
		    return false;
	    }
    });
    
    $("#againAssignId").on("click", function()
    	    {
    		    var flag = parseFloat(0);
    		    $(".userIdsCls1").each(function()
    		    {
    			    if($(this).prop('checked') == true)
    			    {
    				    flag++;
    			    }
    		    });
    		    if(flag == 0)
    		    {
    			    alert("Please Select atleast one student for assigned test");
    			    return false;
    		    }
    	    });
    
    $("#assignedAllId").on("click", function()
    {
	    if($(this).prop('checked') == true)
	    {
		    $(".userIdsCls").prop('checked', 'checked');
	    }
	    else
	    {
		    $(".userIdsCls").removeAttr('checked');
	    }
    });
    
    $("#selectAllId").on("click", function()
    {
	    if($(this).prop('checked') == true)
	    {
		    $(".studentIdsCls").prop('checked', 'checked');
	    }
	    else
	    {
		    $(".studentIdsCls").removeAttr('checked');
	    }
    });
    
    $("#selectAllId1").on("click", function()
    {
	    if($(this).prop('checked') == true)
	    {
		    $(".userIdsCls1").prop('checked', 'checked');
	    }
	    else
	    {
		    $(".userIdsCls1").removeAttr('checked');
	    }
    });
    
    $(".publishAssessmentCls").on("click", function()
    {
	    var txt;
	    var r = confirm("You want to publish this assessment!");
	    if(r == false)
	    {
		    return false;
	    }
	    
    });
    
    $("#topicAssignId").on("click", function()
    {
	    var sectionId = $("#sectionId2").val();
	    var topicId = $("#hiddenTopicId").val();
	    var rowId = $("#hiddenRowId").val();
	    var studentCount = parseFloat($("#studentCount" + rowId).text());
	    $.post("${pageContext.request.contextPath}/ajax/rest/assignTopicToSection/" + sectionId + "/" + topicId + "/" + $("#hiddenGradeIdId").val() + "/" + $("#hiddenSubjectId").val(), function(data)
	    {
		    if(data != null && parseFloat(data) > 0)
		    {
			    $("#studentCount" + rowId).html(parseFloat(data));
			    $("#studentCount" + rowId).attr('data-target', '#myModal6');
			    $("#studentCount" + rowId).attr('data-toggle', 'model');
			    $("#assignTopic" + rowId).html("Unassign");
			    $("#assignTopic" + rowId).attr("data-target", "#myModal5");
			    
			    $("#assignTopic" + rowId).attr('onclick', 'unAssignTopic(' + rowId + ')');
		    }
		    $(".close").trigger('click');
	    });
    });
    
    $("#moduleAssignId").on("click", function()
    {
	    var sectionId = $("#sectionId2").val();
	    var topicId = $("#hiddenTopicId").val();
	    var rowId = $("#hiddenRowId").val();
	    var studentCount = parseFloat($("#studentCount" + rowId).text());
	    var moduleId = $("#hiddenModuleId").val();
	    $.post("${pageContext.request.contextPath}/ajax/rest/assignModuleToSection/" + sectionId + "/" + topicId + "/" + moduleId + "/" + $("#hiddenGradeIdId").val() + "/" + $("#hiddenSubjectId").val(), function(data)
	    {
		    if(data != null && parseFloat(data) > 0)
		    {
			    $("#studentCounts" + rowId).html(parseFloat(data));
			    $("#studentCounts" + rowId).attr('data-target', '#myModal6');
			    $("#studentCounts" + rowId).attr('data-toggle', 'model');
			    $("#assignModule" + rowId).html("Unassign");
			    $("#assignModule" + rowId).attr("data-target", "#myModal5");
			    
			    $("#assignModule" + rowId).attr('onclick', 'unAssignModule(' + rowId + ')');
		    }
		    $(".close").trigger('click');
	    });
    });
    
    $("#unAssignTopicId").on("click", function()
    {
	    var sectionId = $("#sectionId3").val();
	    var topicId = $("#hiddenTopicId1").val();
	    var rowId = $("#hiddenRowId1").val();
	    $.post("${pageContext.request.contextPath}/ajax/rest/unAssignTopicFromSection/" + sectionId + "/" + topicId, function(data)
	    {
		    if(parseFloat(data) == 0 || parseFloat(data) == '0')
		    {
			    $("#assignTopic" + rowId).html("assign");
			    $("#assignTopic" + rowId).attr("data-target", "#myModal7");
			    $("#studentCount" + rowId).removeAttr('data-target');
			    $("#studentCount" + rowId).removeAttr('data-toggle');
			    $("#assignTopic" + rowId).attr('onclick', 'assignTopic(' + rowId + ')');
		    }
		    $("#studentCount" + rowId).html(data);
		    $(".close").trigger('click');
		    
	    });
    });
    
    $("#unAssignModuleId").on("click", function()
    {
	    var sectionId = $("#sectionId3").val();
	    var topicId = $("#hiddenTopicId1").val();
	    var moduleId = $("#hiddenModuleId1").val();
	    var rowId = $("#hiddenRowId1").val();
	    $.post("${pageContext.request.contextPath}/ajax/rest/unAssignModuleFromSection/" + sectionId + "/" + topicId + "/" + moduleId, function(data)
	    {
		    if(parseFloat(data) == 0 || parseFloat(data) == '0')
		    {
			    $("#assignModule" + rowId).html("assign");
			    $("#assignModule" + rowId).attr("data-target", "#myModal7");
			    $("#studentCounts" + rowId).removeAttr('data-target');
			    $("#studentCounts" + rowId).removeAttr('data-toggle');
			    $("#assignModule" + rowId).attr('onclick', 'assignModule(' + rowId + ')');
		    }
		    $("#studentCounts" + rowId).html(data);
		    $(".close").trigger('click');
		    
	    });
    });
    
    function assignModule(id)
    {
	    $(".assignTopicModuleCls").hide();
	    $("#moduleAssignId").show();
	    var gradeName = $("#gradeId3 option:selected").text();
	    var assignCountId = $("#studentCounts" + id).html();
	    var moduleId = $("#module" + id).val();
	    $("#hiddenModuleId").val(moduleId);
	    $("#hiddenTopicId").val($("#topicId3").val());
	    $("#hiddenRowId").val(id);
	    $("#hiddenStudentCount").val(assignCountId);
	    $("#hiddenGradeIdId").val($("#gradeId3").val());
	    $("#hiddenSubjectId").val($("#subjectId3").val());
	    $("#gradeId2").append('<option>' + gradeName + '</option>');
	    setDropdownList('#sectionId2', $("#gradeId3").val(), '${pageContext.request.contextPath}/ajax/rest/getSectionByGradeId', 'Select Section', false)
    }

    function assignTopic(id)
    {
	    
	    $(".assignTopicModuleCls").hide();
	    $("#topicAssignId").show();
	    var gradeName = $("#gradeId1 option:selected").text();
	    var assignCountId = $("#studentCount" + id).html();
	    var topicId = $("#topics" + id).val();
	    $("#hiddenTopicId").val(topicId);
	    $("#hiddenRowId").val(id);
	    $("#hiddenStudentCount").val(assignCountId);
	    $("#hiddenGradeIdId").val($("#gradeId1").val());
	    $("#hiddenSubjectId").val($("#subjectId1").val());
	    $("#gradeId2").append('<option>' + gradeName + '</option>');
	    setDropdownList('#sectionId2', $("#gradeId1").val(), '${pageContext.request.contextPath}/ajax/rest/getSectionByGradeId', 'Select Section', false)
    }

    function unAssignTopic(rowId)
    {
	    $(".unassignTopicModuleCls").hide();
	    $("#unAssignTopicId").show();
	    var gradeName = $("#gradeId1 option:selected").text();
	    var assignCountId = $("#studentCount" + rowId).html();
	    var topicId = $("#topics" + rowId).val();
	    var moduleName=$("#topicNameId"+rowId).html();
	    $("#topicOrResourceNameId").html("Topic Assigned");
	    $("#topicOrResourceName").html(moduleName);
	    $("#hiddenTopicId1").val(topicId);
	    $("#hiddenRowId1").val(rowId);
	    $("#hiddenStudentCount1").val(assignCountId);
	    $("#sectionId3").empty();
	    $("#gradeName1").html(gradeName);
	    $("#hiddenGradeIdId1").val($("#gradeId1").val());
	    $("#hiddenSubjectId1").val($("#subjectId1").val());
	    $.post("${pageContext.request.contextPath}/ajax/rest/getSectionsByTopicId/" + topicId, function(data)
	    {
		    if(data != null && data.length > 0)
		    {
			    var ele = '';
			    for (var i = 0; i < data.length; i++)
			    {
				    ele = ele + '<option value="'+data[i].id+'">' + data[i].name + '</option>';
			    }
			    $("#sectionId3").append(ele);
		    }
	    });
    }

    function unAssignModule(rowId)
    {
	    $(".unassignTopicModuleCls").hide();
	    $("#unAssignModuleId").show();
	    var gradeName = $("#gradeId3 option:selected").text();
	    var assignCountId = $("#studentCounts" + rowId).html();
	    var moduleId = $("#module" + rowId).val();
	    var topicId = $("#topicId3").val();
	    var moduleName=$("#moduleNameId"+rowId).html();
	    $("#topicOrResourceNameId").html("Resources Assigned");
	    $("#topicOrResourceName").html(moduleName);
	    $("#hiddenModuleId1").val(moduleId);
	    $("#hiddenTopicId1").val(topicId);
	    $("#hiddenRowId1").val(rowId);
	    $("#hiddenStudentCount1").val(assignCountId);
	    $("#sectionId3").empty();
	    $("#gradeName1").html(gradeName);
	    $("#hiddenGradeIdId1").val($("#gradeId3").val());
	    $("#hiddenSubjectId1").val($("#subjectId3").val());
	    $.post("${pageContext.request.contextPath}/ajax/rest/getSectionsByModuleId/" + topicId + "/" + moduleId, function(data)
	    {
		    if(data != null && data.length > 0)
		    {
			    var ele = '';
			    for (var i = 0; i < data.length; i++)
			    {
				    ele = ele + '<option value="'+data[i].id+'">' + data[i].name + '</option>';
			    }
			    $("#sectionId3").append(ele);
		    }
	    });
    }

    function getStudentDetailsForAssessment(sectionId)
    {
	    $.post("${pageContext.request.contextPath}/ajax/rest/getStudentsBySectionId/" + sectionId, function(data)
	    {
		    if(data != null && data.length > 0)
		    {
			    var val = '';
			    for (var i = 0; i < data.length; i++)
			    {
			    	var status=true;
			    	$( ".studentIdsCls" ).each(function( index ) {
			    		  if(parseInt($(this).val())==parseInt(data[i].userId)){
			    			  status=false;
			    		  }
			    		});
			    	if(status==true || status=='true'){
				    val = val + '<div class="row"><tr><td width="8%"><input type="checkbox" class="userIdsCls1" value="'+data[i].user.id+'" name="studentIds"/></td><td width="35%">' + data[i].user.admissionNo + '</td><td width="40%">' + data[i].user.firstName + '</td><td>' + $("#gradeId5 option:selected").text() + '</td></tr></div>';
			    	}
			    	}
			    $("table#newAssignAssessment").html(val);
			    $("table#newAssignAssessment").show();
		    }
		    else
		    {
			    $("table#newAssignAssessment").hide();
		    }
		    
	    });
    }

    function getStudentDetails(sectionId)
    {
	    $("#studentIds").empty();
	    $.post("${pageContext.request.contextPath}/ajax/rest/getStudentsBySectionId/" + sectionId, function(data)
	    {
		    if(data != null && data.length > 0)
		    {
			    var val = '';
			    for (var i = 0; i < data.length; i++)
			    {
				    val = val + '<tr><td><input type="checkbox" class="userIdsCls" value="'+data[i].user.id+'" name="studentIds"/></td><td>' + data[i].user.admissionNo + '</td><td>' + data[i].user.firstName + '</td><td>' + $("#gradeId option").text() + '</td></tr>';
			    }
			    $("table#unassignedStudentIds tbody").html(val);
			    $("table#unassignedStudentIds").show();
			    $("#assignedAssesssmentId").show();
		    }
		    else
		    {
			    $("table#unassignedStudentIds").hide();
			    $("#assignedAssesssmentId").hide();
		    }
		    
	    });
    }

    $("#chapterId3").on("change", function()
    {
  	    $("#topicId3").empty().append("<option value=''>Select Topic</option>");
  	    $("#assignUnassignModuleId").empty();
  	    $("#assignUnassignModuleId").append('<tr><td><strong>Resources Name</strong></td><td><strong>Student Assigned</strong></td><td>Action</td></tr>')
	    $('#topicId3').empty();
	    $('#topicId3').append('<option value="">Select Topic</option>');
	    if(chapterList != undefined && chapterList.length > 0)
	    {
		    for (var i = 0; i < chapterList.length; i++)
		    {
			    var topicList = chapterList[i].topics;
			    if(this.value == chapterList[i].id)
			    {
				    
				    var val = '';
				    for (var j = 0; j < topicList.length; j++)
				    {
					    val = val + '<option value="'+topicList[j].id+'">' + topicList[j].name + '</option>';
					    
				    }
				    $('#topicId3').append(val);
			    }
		    }
	    }
	    
    });
    
    //subjectId3
    
    
    function populateSubject(gradeId)
    {
    	
    	$('#subjectId3').empty().append("<option value=''>Select Subject</option>");
	    $("#chapterId3").empty().append("<option value=''>Select Chapter</option>");
	    $("#topicId3").empty().append("<option value=''>Select Topic</option>");
	    $("#assignUnassignModuleId").empty();
	    $("#assignUnassignModuleId").append('<tr><td><strong>Resources Name</strong></td><td><strong>Student Assigned</strong></td><td>Action</td></tr>')
    	if(gradeId != '' && !isNaN(gradeId))
	    {
		    $('#subjectId3').empty();
		    
		    $('#subjectId3').append("<option value=''>Select Subject</option>");
		    <c:forEach var="ts" items="${teacherSection}">
		    if('${ts.section.sessionSchoolGrade.grade.id}' == gradeId)
		    {
			    if(0 == $('#subjectId3 option[value="${ts.subject.id}"]').length)
				    $('#subjectId3').append("<option value='${ts.subject.id}' class='clsSubject'>${ts.subject.name}</option>");
		    }
		    </c:forEach>
	    }
	    else
	    {
		    $('#subjectId3').empty().append("<option value=''>Select Subject</option>");
		
	    }
    }
    //populateSubject1(this.value)
    
    function populateSubject1(gradeId)
    {
    	$('#subjectId1').empty().append("<option value=''>Select Subject</option>");
	    $("#chapterId1").empty().append("<option value=''>Select Chapter</option>");
	    $("#topicAssignedId").empty();
	    $("#topicAssignedId").append('<tr><td><strong>Topic Name</strong></td><td><strong>Student Assigned</strong></td><td>Action</td></tr>')
    	if(gradeId != '' && !isNaN(gradeId))
	    {
		    $('#subjectId1').empty();
		    
		    $('#subjectId1').append("<option value=''>Select Subject</option>");
		    <c:forEach var="ts" items="${teacherSection}">
		    if('${ts.section.sessionSchoolGrade.grade.id}' == gradeId)
		    {
			    if(0 == $('#subjectId1 option[value="${ts.subject.id}"]').length)
				    $('#subjectId1').append("<option value='${ts.subject.id}' class='clsSubject'>${ts.subject.name}</option>");
		    }
		    </c:forEach>
	    }
	    
    }
    
    
</script>
</html>