<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<jsp:include page="schoolTeacherHeader.jsp">
	<jsp:param value="dashboard" name="header" />
</jsp:include>
<script src="${pageContext.request.contextPath}/resources/dateTime/jquery.datetimepicker.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/dateTime/jquery.datetimepicker.css" />

<div id="page-wrapper" style="padding-top: 5%">

	<%-- --%>
	<!--  -->
	<div class="row">
		<div class="col-lg-12">

			<jsp:include page="teacherSubjectHeader.jsp">
				<jsp:param value="dashboard" name="header" />
			</jsp:include>
			<script type="text/javascript">

/* 	$('#upcoming1').load('${pageContext.request.contextPath}/schoolTeacher/getAssessmentonSubjectAndGrade?grdId=${gradeId}&subId=${subjectId}',function(data){
 */		
	$(document).ready(function(){
		
		$('#completedtest option:last').prop('selected', true);
		var a=$('#completedtest option:last').val();
		completedLastTest(a);
		
	//$("#student-wise-report").empty();
	$('#search').keypress(function(ev){
		if(ev.which==13){
			var value=$('#search').val();
			//alert("key pressed"+ev.which +value);
			$("#fullPageloader").show();
			$.get("${pageContext.request.contextPath}/schoolTeacher/searchStudents?search="+value+"&grdId="+'${gradeId}'+"&subId="+'${subjectId}'+"&secId="+'${sectionId}'+"&grade="+'${grade}'+"&subject="+'${subject}'+"&section="+'${section}',function(data){
				if(data !=null)
					{
					$("#student-wise-report").html(data);
					$("#fullPageloader").hide();
					}
			
			});
			
			}

 }); 
	
	
	
	
	
	
	$('#searchStudent').keyup(function(ss){
		
var val=$(this).val().trim();
if(ss.keyCode==8 && val==""){

	$("#allStudentShowId").show();
	$("#searchStudentId").empty();
	

	
}
		if(ss.keyCode==13)
		{
			$("#allStudentShowId").hide();
			$("#searchStudentId").empty();
			var searchName=$('#searchStudent').val();
			$.get("${pageContext.request.contextPath}/ajax/rest/searchStudentDetail?search="+searchName+"&grdId="+'${gradeId}'+ "&subId="+'${subjectId}'+ "&secId="+'${sectionId}', function(data){
				var val='';
				if(data!=null && data.length>0){
				for(var i=0 ; i<data.length ; i++)
				{
					
					
					val=val+'';
					
					var allStudentdata=data[i].admissionNo+'||'+data[i].firstName+'||'+data[i].grade.name+'||'+data[i].sectionName+'||'+data[i].school.schoolName+'||'+data[i].address+'||'+data[i].mobileNo;
					allStudentdata=	allStudentdata.split(' ').join(':::');
					
					val=val+" <input  id='hiddenStudentRec"+data[i].id+"' value="+allStudentdata+"  style='display:none'/><div class='col-md-2'><a href='#' data-target='#student-detail' onclick='getDetail("+data[i].id+")' data-toggle='modal' style='float: right; margin-bottom: 10px;'><div class='studen-list-pic'> <img src='${pageContext.request.contextPath}/resources/images/activity-user-pic.png' alt='' height='100' width='110'><br> <b>"+data[i].firstName+"</b></div></a></div>";
					//$('#allStudent').append();
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					$('#searchStudent').val();
				}
				}
			$("#searchStudentId").html(val)
			});

		}
		
		
		 
	});	
			
		
	
	$("#testwisereportId").on("click", function(){
		
		$("#completetestIds").show();
		$("#searchStuId").hide();
		$("#test-wise-report").empty();
		//$("#completedtest").val('');
		//$("#completedtest option").last().val();
		//$("#completedtest").val($("#completedtest option:second").val());
		$('#completedtest option:last').prop('selected', true);
		var a=$('#completedtest option:last').val();
		completedLastTest(a);
		
		});
	
	
$("#studentwiseReportId").on("click", function(){
		
		$("#completetestIds").hide();
		$("#searchStuId").show();
		$("#search").val('');
		$("#student-wise-report").empty();
		});
		
		

$('#startDate1').datetimepicker(
	    {
	        mask : '9999/19/39 29:59',
	        step : 5,
	        minDate : '-1970/01/01',
	        maxDate : '+1970/03/01',
	        format : 'd/M/Y H:i'
	    
	    });
	    
$('#endDate1').datetimepicker(
	    {
	        mask : '9999/19/39 29:59',
	        step : 5,
	        minDate : '-1970/01/01',
	        maxDate : '+1970/03/01',
	        format : 'd/M/Y H:i'
	    
	    });
	    
	
	});

 
 
	function editAssessment(id,assmntId){
			    	$("#testNameIds").val($("#assessmentIds"+id).val());
			        $("#startDate1").val($("#testOn"+id).val());
			        $("#endDate1").val($("#testOff"+id).val());
			        $("#asmntId").val(assmntId);
			        $("#hiddId").val(id);
			      
		}    
 
 
	 function submitAssessmentEditForm(count){
	
	 var strtDate=$('#startDate1').val();
	 var endDate=$('#endDate1').val();
	 var tstName= $("#testNameIds").val();
	 var assmntId=$("#asmntId").val();
	 var d1=new Date(strtDate);
	 var d2=new Date(endDate);
	 var id=$("#hiddId").val();
	 if (d1 >= d2) {
		alert("Start Date Time must be less then End Date Time!");
		return false;
	} 
	
	else
		{
		 
		$.get("${pageContext.request.contextPath}/schoolTeacher/updateAssessments?testName="+tstName+"&strtDate="+strtDate+"&endDate="+endDate+"&assmentId="+assmntId,function(data){
			
		if(data == 1){	
		alert(" Assessment Updated Successfully !! ");
		$("#stestNameId"+id).html(tstName);
		$("#sstartDateId"+id).html(strtDate);
		$("#sendDateId"+id).html(endDate);
		$(".close").trigger("click");
		//$("#upcomingDiv").trigger("click");
		

		}
		});
		
		}
	
	 }
 
 
 
function completedtestFunction(data){
	
	if(data.value==1)
	{
		alert("Please Select value");
	}
	else
		{
		var a=data.value.split('+');
		var testid=a[0];
		var gradeId=a[1];
		var subjectId=a[2];
		$("#fullPageloader").show();
		$.get("${pageContext.request.contextPath}/schoolTeacher/getTestWiseReport?grdId="+gradeId+"&subId="+subjectId+"&assesmentId="+testid,function(data){
		   
			//alert("test report"+data);
			 $('#test-wise-report').html(data);
		$("#fullPageloader").hide();
		});
		}
		
		}
	
function completedLastTest(value){
	
	if(value==1)
	{
		//alert("Please Select value");
	}
	else
		{
		var a=value.split('+');
		var testid=a[0];
		var gradeId=a[1];
		var subjectId=a[2];
		$("#fullPageloader").show();
		$.get("${pageContext.request.contextPath}/schoolTeacher/getTestWiseReport?grdId="+gradeId+"&subId="+subjectId+"&assesmentId="+testid,function(data){
		   
			//alert("test report"+data);
			 $('#test-wise-report').html(data);
		$("#fullPageloader").hide();
		});
		}
		
		}
	
/* function getDetail(valAdmissionNo,valAdmissionNo,valFname,valGrd,ValSec,valName,valAddress,valContact){
	
	alert("reached" +data);
	 $("#sadmissionNo").html(data);	
	$("#fname").html(data.firstName);
	$("#grd").html(data.grade);
	$("#sec").html(data.sectionName);
	$("#sname").html(data.school.schoolName);
	$("#saddress").html(data.address);
	$("#scontact").html(data.mobileNo); 
	
} */

 function getDetail(id){
var allValues=$('#hiddenStudentRec'+id).val();
allValues=allValues.split(':::').join(' ');
var values = allValues.split("||");
$("#sadmissionNo").html(values[0]);
$("#fname").html(values[1]);
$("#grd").html(values[2]);
$("#sec").html('${param.sec}');
$("#sname").html(values[4]);
$("#saddress").html(values[5]);
$("#scontact").html(values[6]); 
var subjectId='${param.subId}';
$.get('${pageContext.request.contextPath}/ajax/rest/getStudentSubjectProgressDetails/'+subjectId+'/'+id,function(data){
$('#progressbarValue').css('width', data+'%');
	    $('#progressbarValue').html(data+'%');
	
	
 });

} 







function getStudentDetail(id,firstName,grade,section,schoolName,profileImg,contact,admissionNo,address){
 if(profileImg!=null&profileImg!=''){$("#imgForShowDetails").attr("src","${commonPath}/profileImage/"+profileImg);}
 else{$("#imgForShowDetails").attr("src","${pageContext.request.contextPath}/resources/images/activity-user-pic.png");}
 	$("#sadmissionNo").html(admissionNo);	
 	$("#fname").html(firstName);
	$("#grd").html(grade);
	$("#sec").html(section);
	$("#sname").html(schoolName);
	$("#saddress").html(address);
	$("#scontact").html(contact);
	
	
	
var subjectId='${param.subId}';
$.get('${pageContext.request.contextPath}/ajax/rest/getStudentSubjectProgressDetails/'+subjectId+'/'+id,function(data){

	 $('#progressbarValue').css('width', data+'%');
	    $('#progressbarValue').html(data+'%');
	
	
 });
	}

 function getReport(){
//&secId={}&reportTyp={}&quesCount{}
	 $.get('${pageContext.request.contextPath}/schoolTeacher/learningReport?grdId=${gradeId}&subjctId=${subjectId}&secId=${sectionId}',function(data){
	$('#learning-report').html(data);
	
	
 });
 }
 
 function viewCompletedTestDetail(testid,gradeId,subjectId)
 {
	 $("#testwisereportId").trigger("click");
	 var values=testid+"+"+gradeId+"+"+subjectId;
	 $.get("${pageContext.request.contextPath}/schoolTeacher/getTestWiseReport?grdId="+gradeId+"&subId="+subjectId+"&assesmentId="+testid,function(data){
		   
			 $('#test-wise-report').html(data);
			 
			 $('#completedtest').val(values);
			 
		});
	 
	 
 }
 
 function EtestPreview(data)
 {
	 var assessmentId = data;
	 
	    $.get("${pageContext.request.contextPath}/schoolTeacher/assessmentPreview/" + assessmentId, function(data)
	    {  
	    	$('#Etestpreview123').html(data);
		 
	    });
 }
 
 
 
 
		 /*  $.ajax
			({
				url:"${pageContext.request.contextPath}/schoolTeacher/learningReport?grdId=${gradeId} &subjctId=${subjectId}",
				type:"POST",
				success:function(data)
				{
					alert("response recieved"+data);
					var chapterlst=data;
					alert("size"+chapterlst.length);
					
						$("#chapterData").html(chapterlst);
					
					for(var i=0;i<chapterlst.length;i++)
						{
						$("#chapterData").append(chapterlst[i].name+"<br>");
						
						}
						
						
				
				},
				error:function()
				{
					alert("Something went wrong");
				}
	 
			}); */
	 

 



</script>

			<!-- /.panel-body -->
		</div>
		<!-- /.panel -->

		<!-- /.panel -->

		<!-- /.panel -->
	</div>
</div>
<div class="clearfix"></div>
<div  class="col-md-12">
<div class="recent-activity">
<div class="col-md-6"><h3>
		
<div class="input-group col-sm-8 col-lg-12 col-md-6" id="searchStuIdStudent" >
<div class="col-md-4"><img src="${pageContext.request.contextPath}/resources/images/students.png" alt="">Search Student</img></div>
<div class="col-md-5"><input id="searchStudent" class="text-field " type="text" placeholder="SearchStudent"></div>
</div>
</h3></div>

 <div class="col-md-3 pull-right">
 <!-- <input id="searchStudent" class="text-field pull-right" value="" type="text" placeholder="Search Student" style="border: solid 1px #dddddd;"> --> 
</div> 
		<div class="recent-activity-cont mCustomScrollbar">
			<div id="allStudent" class="recent-activity-point">

<span id="allStudentShowId">
				<c:forEach var='sts' items="${usl}">
					<div class="col-md-2">

						<a href="#" data-target="#student-detail"
							onclick="getStudentDetail(${sts.id},'${sts.firstName}','${grade}','${section}','${sts.school.schoolName}','${sts.profilePath}','${sts.mobileNo}','${sts.admissionNo}','${sts.address}')"
							data-toggle="modal" style="float: right; margin-bottom: 10px;">

							<div class="studen-list-pic">
								
								
								<c:choose>
  <c:when test="${sts.profilePath!=null}">
    <img src="${commonPath}/profileImage/${sts.profilePath}" alt="" height="100" width="110">
  </c:when>
 
  <c:otherwise>
      <img src="${pageContext.request.contextPath}/resources/images/activity-user-pic.png" alt="" height="100" width="110">
  </c:otherwise>
</c:choose>
								
								
								<br> <b>${sts.firstName}</b>
							</div>
						</a>

					</div>
				</c:forEach>
</span>

<span id="searchStudentId"></span>
			</div>
		</div>
	</div>

</div>

<!--Analytics Report Start  -->
<div class="clearfix"></div>

<div class="col-md-12">
	<div class="recent-activity">
		<h3>
			<img
				src="${pageContext.request.contextPath}/resources/images/analy.png"
				alt=""> Analytics
		</h3>

		<div class="recent-activity-cont" style="height: auto;">

			<div class=" col-md-12">
				<ul class="nav nav-tabs dashTab">

					<li class="active"><a data-toggle="tab" href="#test-report">Test
							Report</a></li>
					<li><a data-toggle="tab" data-target="#learning-report"
						onclick="getReport()">Learning Report</a></li>

				</ul>


				<div class="clearfix"></div>
				<!--tab-cont-->

				<div class="tab-content">

					<br />


					<!--Test Report content start here-->


					<div id="test-report" class="tab-pane fade active in">

						<ul class="nav nav-tabs dashTab">

							<li class="active"><a data-toggle="tab" href="#upcoming1" id="upcomingDiv">Upcoming
									Test</a></li>
							<li><a data-toggle="tab" href="#ongoing">Ongoing Test</a></li>
							<li><a data-toggle="tab" href="#completed">Completed
									Test</a></li>
						</ul>


						<div class="clearfix"></div>
						<!--tab-cont-->

						<div class="tab-content">

							<br />
							<!--upcoming test-->
							<div id="upcoming1" class="tab-pane fade in active">

								<div class="panel panel-default">
									<div class="panel-heading assign-topic-bg">
										<h3 class="panel-title">
											<i class="fa fa-check-circle"></i> Upcoming Test
										</h3>
									</div>
									<div class="panel-body">
										<div class="table-responsive">

											<table class="table table-bordered table-hover table-striped">
												<thead>
													<tr>

														<th>Test Name</th>
														<th>No. of Students <br> (Students
															attempted/student assigned)
														</th>
														<th>Average Score</th>
														<th>Maximum Marks</th>
														<th>Test start date</th>
														<th>Test end date</th>
														<th>Action</th>

													</tr>
												</thead>
												<tbody>
													<c:forEach var='upComing' items="${upcomingAss}" varStatus="status" > 
														<tr>
                                                            <c:if test="${upComing.type eq 1}">
															<td > <span id="stestNameId${status.count }"><a href="#" data-toggle="modal"  data-target="#Etestpreview"  onclick="EtestPreview('${upComing.id}')" >${upComing.testName}</a></span><input type="hidden" id="assessmentIds${status.count}"  value="${upComing.testName}" dir="${upComing.id}"/></td>
															<td>${upComing.studentTest.size()}/
																${upComing.assignedAssessment.size()} </td>
															<td>${upComing.average}</td>
															<td>${upComing.maxMarks} </td>
															<td> <span id="sstartDateId${status.count }"><fmt:formatDate pattern="dd-MMM-yyyy HH:mm"
																	value="${upComing.startDate}" /><input type="hidden" id="testOn${status.count }" value="<fmt:formatDate pattern="dd/MMM/yyyy HH:mm " value="${upComing.startDate}" />" /></span></td>
															<td> <span id="sendDateId${status.count }"><fmt:formatDate pattern="dd-MMM-yyyy HH:mm"
																	value="${upComing.endDate}" /><input type="hidden" id="testOff${status.count }" value="<fmt:formatDate pattern="dd/MMM/yyyy HH:mm" value="${upComing.endDate}" />" /></span></td>
																	
															<td><button class="btn btn-primary"  type="button"  data-toggle="modal" data-target="#upcoming-edit" onclick="editAssessment('${status.count}','${upComing.id}')" id="editAssessmentCls" dir="${status.count}">Edit</button></td>		
															</c:if>


														</tr>
													</c:forEach> 
													<!--  <tr>

														<td>Science:Grade 6</td>
														<td>1/1</td>
														<td>75.0 </td>
														<td>150.0 </td>
														<td>10-06-2016</td>
														 -->




												</tbody>
											</table>

										</div>
									</div>
								</div>
							</div>

							<!--ongoing test-->

							<div id="ongoing" class="tab-pane fade">
								<div class="panel panel-default">
									<div class="panel-heading assign-res-bg">
										<h3 class="panel-title">
											<i class="fa fa-check-circle"></i> Ongoing Test
										</h3>
									</div>
									<div class="panel-body">
										<div class="table-responsive">

											<table class="table table-bordered table-hover table-striped">
												<thead>
													<tr>

														<th>Test Name</th>
														<th>No. of Students <br> (Students
															attempted/student assigned)
														</th>
														<th>Average Score</th>
														<th>Maximum Marks</th>
														<th>Test start date</th>
														<th>Test end date</th>

													</tr>
												</thead>
												<tbody>
													<c:forEach var='ongng' items="${completedAss}">
													<c:if test="${ongng.type eq 1}">
														<c:if test="${ongng.flag == 1}">
												<tr>

																<td><a href="#" data-toggle="modal"  data-target="#Etestpreview"  onclick="EtestPreview('${ongng.id}')" >${ongng.testName}</a></td>
																<td>${ongng.studentTest.size()}/
																	${ongng.assignedAssessment.size()}</td>
																<td>${ongng.average}</td>
																<td>${ongng.maxMarks}</td>
																<td><fmt:formatDate pattern="dd-MMM-yyyy HH:mm"
																		value="${ongng.startDate}" /></td>
																<td> <fmt:formatDate pattern="dd-MMM-yyyy HH:mm"
																	value="${ongng.endDate}" /></td>
																

															</tr>
														</c:if>
														</c:if>
													</c:forEach>
													<!-- <tr>

														<td></td>
														<td>1/1</td>
														<td>75.0 </td>
														<td>150.0 </td>
														<td>26-05-2016</td>

													</tr>
                                              -->
												</tbody>
											</table>

										</div>
										<br> <br>
									</div>
								</div>

							</div>
							<!--completed test-->
							<div id="completed" class="tab-pane fade">
								<div class="panel panel-default">
									<div class="panel-heading assign-res-bg">
										<h3 class="panel-title">
											<i class="fa fa-check-circle"></i> Completed Test
										</h3>
									</div>
									<div class="panel-body">
										<div class="table-responsive mCustomScrollbar" style="height:320px;">
											<table class="table table-bordered table-hover table-striped">
												<thead>
													<tr>

														<th>Test Name</th>
														<th>No. of Students <br> (Students
															attempted/student assigned)
														</th>
														<th>Average Score</th>
														<th>Maximum Marks</th>
														<th>View</th>
														<th>Test start date</th>
														<th>Test end date</th>

													</tr>
												</thead>
												<tbody>
													<c:forEach var='completed' items="${completedAss}">
														<tr>
															<c:if test="${completed.type eq 1}">
															<c:if test="${completed.flag == 2}">
															<td><a href="#" data-toggle="modal"  data-target="#Etestpreview"  onclick="EtestPreview('${completed.id}')" >${completed.testName}</a></td>
															<td>${completed.studentTest.size()}/
																${completed.assignedAssessment.size()}</td>
															<td>${completed.average}</td>
															<td>${completed.maxMarks}</td>
															<td><a href="#test-wise-report"><img
																src="${pageContext.request.contextPath}/resources/images/view-icon.png"
																alt=""
																onclick="viewCompletedTestDetail('${completed.id}','${gradeId}','${subjectId}')"></a></td>
															<td><fmt:formatDate pattern="dd-MMM-yyyy HH:mm"
																	value="${completed.startDate}" /></td>
																		<td> <fmt:formatDate pattern="dd-MMM-yyyy HH:mm"
																	value="${completed.endDate}" /></td>
															</c:if>
															</c:if>
														</tr>
													</c:forEach>

													<%-- <tr>

														<td>Science:Grade 6</td>
														<td>1/1</td>
														<td>75.0 </td>
														<td>150.0 </td>
														<td><img src="${pageContext.request.contextPath}/resources/images/view-icon.png" alt=""></td>
														<td>26-05-2016</td>

													</tr> --%>

												</tbody>
											</table>

										</div>
										<br> <br>
									</div>
								</div>

							</div>

							<!--Test repot content end here-->
							<div class="col-md-12">
								<div class="recent-activity">
									<div class="recent-activity-cont" style="height: auto;">
										<div class="col-md-12">
											<ul class="nav nav-tabs dashTab">
												<li class="active"><a data-toggle="tab"
													href="#test-wise-report" id="testwisereportId">Test
														Wise Report</a></li>
												<li><a data-toggle="tab" href="#student-wise-report"
													id="studentwiseReportId">Student Wise Report</a></li>
											</ul>
											<br>
											<div class="col-md-3" id="completetestIds"
												style="display: block;">
												<b>Select Test</b>
												<select id="completedtest"
													onchange="completedtestFunction(this)">
													<option value="1">Select Test</option>
													<c:forEach var='ca' items="${completedAss}">
														<option value="${ca.id}+${gradeId}+${subjectId}">${ca.testName}</option>
													</c:forEach>
												</select>
											</div>

											<div class="input-group col-sm-8 col-lg-6 col-md-6"
												id="searchStuId" style="display: none;">
												<b>Search Student</b><input id="search" class="text-field "
													type="text" placeholder="Search">
											</div>


											<br>


											<div class="clearfix"></div>
											<!--tab-cont-->

											<div class="tab-content">

												<br />
												<!--test wise report start -->
												<div id="test-wise-report" class="tab-pane fade in active">
													<div class="panel panel-default">
														<div class="panel-heading assign-res-bg">
															<%-- <div class="col-md-3">
<select id="completedtest" onchange="completedtestFunction(this)">
<option value="1">Select Test</option>
<c:forEach var='ca' items="${completedAss}">
<option value="${ca.id}+${gradeId}+${subjectId}">${ca.testName}</option>
</c:forEach>
</select></div> --%>
														</div>
														<!-- <div class="panel-body">
<div class="col-md-5">
<div class="table-responsive">
<table id="testReport" class="table table-bordered table-hover table-striped" style="margin-top:70px;">
	<tr>
	<td>
	<p>Grade : 6<br>
Subject : Science <br>
Date :29/April/2016</p></td>
	
	<td>
	<p>Highest Score :  79<br>
Average Score : 59 <br>
Lowest Score :  35</p></td>
	</td>
	</tr>										
		
	<tr>
	<td>
	<p>Type : E-Test<br>
Total Q : 100<br>
Max Marks : 50</p></td>
	
	<td>
	<p>Appeared Student : 42<br>
Absent Student : 8</p></td>
	</td>
	</tr>											
</table>
</div>
</div>

<div class="col-md-6">
<div id="container" style="min-width: 250px; height: 340px; max-width: 540px; margin: 0 auto"></div>
</div>

<br> <br>
</div> -->
													</div>
													<%-- <div class="col-md-12">
<div class="score-zone">

<div class="col-md-4">
<h2 class="score-zone-h"><span class="pull-left">Zone : 0-30%</span><span class="pull-right">8 Student</span></h2>
<div class="student-score-zone mCustomScrollbar">

<div style="border-bottom: 1px solid rgb(54, 189, 180); float: left; padding: 10px; ">
<div class="col-md-4"><img src="${pageContext.request.contextPath}/resources/images/activity-user-pic.png" alt=""></div>
<div class="col-md-8" style="padding-right:0px; padding-left:10px;">
<p>
Pritam Chugh<br>
Marks Obtained : <b>15/50</b><br>
Percentage : <b>30%</b><br>
</p>
</div>
<a href="${pageContext.request.contextPath}/schoolTeacher/studenttestreport.htm"><button class="btn btn-primary"  type="button" style="margin-top:10px;">View Details</button></a>
</div>
<div style="border-bottom: 1px solid rgb(54, 189, 180); float: left; padding: 10px; ">
<div class="col-md-4"><img src="${pageContext.request.contextPath}/resources/images/activity-user-pic.png" alt=""></div>
<div class="col-md-8" style="padding-right:0px; padding-left:10px;">
<p>
Pritam Chugh<br>
Marks Obtained : <b>15/50</b><br>
Percentage : <b>30%</b><br>
</p>
</div>
<button class="btn btn-primary"  type="button" style="margin-top:10px;">View Details</button>
</div>
<div style="border-bottom: 1px solid rgb(54, 189, 180); float: left; padding: 10px; ">
<div class="col-md-4"><img src="${pageContext.request.contextPath}/resources/images/activity-user-pic.png" alt=""></div>
<div class="col-md-8" style="padding-right:0px; padding-left:10px;">
<p>
Pritam Chugh<br>
Marks Obtained : <b>15/50</b><br>
Percentage : <b>30%</b><br>
</p>
</div>
<button class="btn btn-primary"  type="button" style="margin-top:10px;">View Details</button>
</div>

</div>
</div>

<div class="col-md-4">
<h2 class="score-zone-h" style="background:#434348; color:#ffffff"><span class="pull-left">Zone : 31%-45%</span><span class="pull-right">9 Student</span></h2>
<div class="student-score-zone  mCustomScrollbar">
<div style="border-bottom: 1px solid rgb(54, 189, 180); float: left; padding: 10px; ">
<div class="col-md-4"><img src="${pageContext.request.contextPath}/resources/images/activity-user-pic.png" alt=""></div>
<div class="col-md-8" style="padding-right:0px; padding-left:10px;">
<p>
Pritam Chugh<br>
Marks Obtained : <b>15/50</b><br>
Percentage : <b>30%</b><br>
</p>
</div>
<button class="btn btn-primary"  type="button" style="margin-top:10px;">View Details</button>
</div>
<div style="border-bottom: 1px solid rgb(54, 189, 180); float: left; padding: 10px; ">
<div class="col-md-4"><img src="${pageContext.request.contextPath}/resources/images/activity-user-pic.png" alt=""></div>
<div class="col-md-8" style="padding-right:0px; padding-left:10px;">
<p>
Pritam Chugh<br>
Marks Obtained : <b>15/50</b><br>
Percentage : <b>30%</b><br>
</p>
</div>
<button class="btn btn-primary"  type="button" style="margin-top:10px;">View Details</button>
</div>
<div style="border-bottom: 1px solid rgb(54, 189, 180); float: left; padding: 10px; ">
<div class="col-md-4"><img src="${pageContext.request.contextPath}/resources/images/activity-user-pic.png" alt=""></div>
<div class="col-md-8" style="padding-right:0px; padding-left:10px;">
<p>
Pritam Chugh<br>
Marks Obtained : <b>15/50</b><br>
Percentage : <b>30%</b><br>
</p>
</div>
<button class="btn btn-primary"  type="button" style="margin-top:10px;">View Details</button>
</div>

</div>
</div>

<div class="col-md-4">
<h2 class="score-zone-h" style="background:#90ED7D;"><span class="pull-left">Zone : 46%-60%</span><span class="pull-right">10 Student</span></h2>
<div class="student-score-zone  mCustomScrollbar">
<div style="border-bottom: 1px solid rgb(54, 189, 180); float: left; padding: 10px; ">
<div class="col-md-4"><img src="${pageContext.request.contextPath}/resources/images/activity-user-pic.png" alt=""></div>
<div class="col-md-8" style="padding-right:0px; padding-left:10px;">
<p>
Pritam Chugh<br>
Marks Obtained : <b>15/50</b><br>
Percentage : <b>30%</b><br>
</p>
</div>
<button class="btn btn-primary"  type="button" style="margin-top:10px;">View Details</button>
</div>
<div style="border-bottom: 1px solid rgb(54, 189, 180); float: left; padding: 10px; ">
<div class="col-md-4"><img src="${pageContext.request.contextPath}/resources/images/activity-user-pic.png" alt=""></div>
<div class="col-md-8" style="padding-right:0px; padding-left:10px;">
<p>
Pritam Chugh<br>
Marks Obtained : <b>15/50</b><br>
Percentage : <b>30%</b><br>
</p>
</div>
<button class="btn btn-primary"  type="button" style="margin-top:10px;">View Details</button>
</div>
<div style="border-bottom: 1px solid rgb(54, 189, 180); float: left; padding: 10px; ">
<div class="col-md-4"><img src="${pageContext.request.contextPath}/resources/images/activity-user-pic.png" alt=""></div>
<div class="col-md-8" style="padding-right:0px; padding-left:10px;">
<p>
Pritam Chugh<br>
Marks Obtained : <b>15/50</b><br>
Percentage : <b>30%</b><br>
</p>
</div>
<button class="btn btn-primary"  type="button" style="margin-top:10px;">View Details</button>
</div>

</div>
</div>


</div>

<div class="score-zone">

<div class="col-md-4">
<h2 class="score-zone-h" style="background:#F7A35C;"><span class="pull-left">Zone : 61%-75%</span><span class="pull-right">12 Student</span></h2>
<div class="student-score-zone  mCustomScrollbar">

<div style="border-bottom: 1px solid rgb(54, 189, 180); float: left; padding: 10px; ">
<div class="col-md-4"><img src="${pageContext.request.contextPath}/resources/images/activity-user-pic.png" alt=""></div>
<div class="col-md-8" style="padding-right:0px; padding-left:10px;">
<p>
Pritam Chugh<br>
Marks Obtained : <b>15/50</b><br>
Percentage : <b>30%</b><br>
</p>
</div>
<button class="btn btn-primary"  type="button" style="margin-top:10px;">View Details</button>
</div>
<div style="border-bottom: 1px solid rgb(54, 189, 180); float: left; padding: 10px; ">
<div class="col-md-4"><img src="${pageContext.request.contextPath}/resources/images/activity-user-pic.png" alt=""></div>
<div class="col-md-8" style="padding-right:0px; padding-left:10px;">
<p>
Pritam Chugh<br>
Marks Obtained : <b>15/50</b><br>
Percentage : <b>30%</b><br>
</p>
</div>
<button class="btn btn-primary"  type="button" style="margin-top:10px;">View Details</button>
</div>
<div style="border-bottom: 1px solid rgb(54, 189, 180); float: left; padding: 10px; ">
<div class="col-md-4"><img src="${pageContext.request.contextPath}/resources/images/activity-user-pic.png" alt=""></div>
<div class="col-md-8" style="padding-right:0px; padding-left:10px;">
<p>
Pritam Chugh<br>
Marks Obtained : <b>15/50</b><br>
Percentage : <b>30%</b><br>
</p>
</div>
<button class="btn btn-primary"  type="button" style="margin-top:10px;">View Details</button>
</div>

</div>
</div>

<div class="col-md-4">
<h2 class="score-zone-h" style="background:#8085E9; color:#ffffff"><span class="pull-left">Zone : 76%-90%</span><span class="pull-right">9 Student</span></h2>
<div class="student-score-zone  mCustomScrollbar">
<div style="border-bottom: 1px solid rgb(54, 189, 180); float: left; padding: 10px; ">
<div class="col-md-4"><img src="${pageContext.request.contextPath}/resources/images/activity-user-pic.png" alt=""></div>
<div class="col-md-8" style="padding-right:0px; padding-left:10px;">
<p>
Pritam Chugh<br>
Marks Obtained : <b>15/50</b><br>
Percentage : <b>30%</b><br>
</p>
</div>
<button class="btn btn-primary"  type="button" style="margin-top:10px;">View Details</button>
</div>
<div style="border-bottom: 1px solid rgb(54, 189, 180); float: left; padding: 10px; ">
<div class="col-md-4"><img src="${pageContext.request.contextPath}/resources/images/activity-user-pic.png" alt=""></div>
<div class="col-md-8" style="padding-right:0px; padding-left:10px;">
<p>
Pritam Chugh<br>
Marks Obtained : <b>15/50</b><br>
Percentage : <b>30%</b><br>
</p>
</div>
<button class="btn btn-primary"  type="button" style="margin-top:10px;">View Details</button>
</div>
<div style="border-bottom: 1px solid rgb(54, 189, 180); float: left; padding: 10px; ">
<div class="col-md-4"><img src="${pageContext.request.contextPath}/resources/images/activity-user-pic.png" alt=""></div>
<div class="col-md-8" style="padding-right:0px; padding-left:10px;">
<p>
Pritam Chugh<br>
Marks Obtained : <b>15/50</b><br>
Percentage : <b>30%</b><br>
</p>
</div>
<button class="btn btn-primary"  type="button" style="margin-top:10px;">View Details</button>
</div>

</div>
</div>

<div class="col-md-4">
<h2 class="score-zone-h" style="background:#F15C80;"><span class="pull-left">Zone : 91%-100%</span><span class="pull-right">10 Student</span></h2>
<div class="student-score-zone  mCustomScrollbar">
<div style="border-bottom: 1px solid rgb(54, 189, 180); float: left; padding: 10px; ">
<div class="col-md-4"><img src="${pageContext.request.contextPath}/resources/images/activity-user-pic.png" alt=""></div>
<div class="col-md-8" style="padding-right:0px; padding-left:10px;">
<p>
Pritam Chugh<br>
Marks Obtained : <b>15/50</b><br>
Percentage : <b>30%</b><br>
</p>
</div>
<button class="btn btn-primary"  type="button" style="margin-top:10px;">View Details</button>
</div>
<div style="border-bottom: 1px solid rgb(54, 189, 180); float: left; padding: 10px; ">
<div class="col-md-4"><img src="${pageContext.request.contextPath}/resources/images/activity-user-pic.png" alt=""></div>
<div class="col-md-8" style="padding-right:0px; padding-left:10px;">
<p>
Pritam Chugh<br>
Marks Obtained : <b>15/50</b><br>
Percentage : <b>30%</b><br>
</p>
</div>
<button class="btn btn-primary"  type="button" style="margin-top:10px;">View Details</button>
</div>
<div style="border-bottom: 1px solid rgb(54, 189, 180); float: left; padding: 10px; ">
<div class="col-md-4"><img src="${pageContext.request.contextPath}/resources/images/activity-user-pic.png" alt=""></div>
<div class="col-md-8" style="padding-right:0px; padding-left:10px;">
<p>
Pritam Chugh<br>
Marks Obtained : <b>15/50</b><br>
Percentage : <b>30%</b><br>
</p>
</div>
<button class="btn btn-primary"  type="button" style="margin-top:10px;">View Details</button>
</div>

</div>
</div>


</div>
</div>	 --%>
												</div>

												<!--Student wise report start -->

												<div id="student-wise-report" class="tab-pane fade">


													<%-- <div class="panel panel-default">
						<div class="panel-heading assign-res-bg">
							<div class="col-md-6"> <label class="col-sm-4 col-lg-3 col-md-2 control-label ng-binding" for="txtFirstName">Student</label>
<div class="input-group col-sm-8 col-lg-6 col-md-6">
<input id="search" class="text-field " type="text"  placeholder="Search">
</div> </div>
						</div>
						<div class="panel-body">
						<h3 class="student-wise-h">All Assessment</h3>
						<div class="student-wise">
						<div class="table-responsive">
							
										<table class="table table-bordered table-hover table-striped">
										<tr style="background:#eeeeee;">
										<div class="student-wise-detail">
										<div class="col-md-12">
										<div class="col-md-2">
  <img src="${pageContext.request.contextPath}/resources/images/activity-user-pic.png" alt="">
 </div>
 <div class="col-md-4">
 <p>
 Student Name : Ram Kumar<br>
 Session : 2015- 2016
 </p>
 </div>
 
  <div class="col-md-4">
 <p>
 Subject : Science<br>
Class : VI-A
 </p>
 </div>
										</div>
										</div>
										</tr>
											<thead>
												<tr>

													<th>Test Name  </th>
													<th>Chapter </th>
													<th>Topic </th>
													<th>Taken</th>
												    <th>Total Score</th>
													<th>View</th>
												</tr>
											</thead>
											<tbody>
												
													<tr>
 														<td>Science </td>
														<td>Life Process</td>
														<td>Fission </td>
														<td>13-05-2016 </td>
														<td>8/20 (40%)</td>
														<td><img src="${pageContext.request.contextPath}/resources/images/view-icon.png" alt=""></td>
													</tr>
													<tr>
 														<td>Science </td>
														<td>Life Process</td>
														<td>Fission </td>
														<td>13-05-2016 </td>
														<td>8/20 (40%)</td>
														<td><img src="${pageContext.request.contextPath}/resources/images/view-icon.png" alt=""></td>
													</tr>
													<tr>
 														<td>Science </td>
														<td>Life Process</td>
														<td>Fission </td>
														<td>13-05-2016 </td>
														<td>8/20 (40%)</td>
														<td><img src="${pageContext.request.contextPath}/resources/images/view-icon.png" alt=""></td>
													</tr>
													<tr>
 														<td>Science </td>
														<td>Life Process</td>
														<td>Fission </td>
														<td>13-05-2016 </td>
														<td>8/20 (40%)</td>
														<td><img src="${pageContext.request.contextPath}/resources/images/view-icon.png" alt=""></td>
													</tr>
                                                  
											</tbody>
										</table>
									
							</div>
						</div>
							
							<br> <br>
						</div>
					</div> --%>

												</div>

												<!--create-test-->


											</div>
										</div>
									</div>
								</div>
							</div>
						</div>


					</div>
					
					
					<!-- learning Report content start here  -->
					<div id="learning-report" class="tab-pane fade"></div>
					<!-- learning Report content end here  -->
				</div>
			</div>
		</div>
	</div>
</div>




</div>

<!-- /.container-fluid -->


<!-- CHART -->
<!-- <script type="text/javascript">
$(function () {

    $(document).ready(function () {

        // Build the chart
        $('#container').highcharts({
            chart: {
                plotBackgroundColor: null,
                plotBorderWidth: null,
                plotShadow: false,
                type: 'pie'
            },
            tooltip: {
                format: '<b>{point.name}</b>: {point.y:.1f} Rs.',
            },
            plotOptions: {
                pie: {
                    allowPointSelect: true,
                    cursor: 'pointer',
                    dataLabels: {
                        enabled: false
                    },
                    showInLegend: true
                    
                }
            },
            series: [{
                name: 'Students',
                colorByPoint: true,
                data: [{
                    name: '0% - 30%',
                    y: 8
                }, {
                    name: '31% - 45%',
                    y: 9,
                    sliced: true,
                    selected: true
                }, {
                    name: '46% - 60%',
                    y: 10
                }, {
                    name: '61% - 75%',
                    y: 12
                }, {
                    name: '76% - 90%',
                    y: 15
                }, {
                    name: '91% - 100%',
                    y: 10
                }]
            }]
        });
    });
});
</script> -->
<!-- /#page-wrapper -->
<script type="text/javascript">
/* function updateProgress(percentage){
    if(percentage > 100) percentage = 100;
    $('#progressBar').css('width', percentage+'%');
    $('#progressBar').html(percentage+'%');
} */

/* var hosts = 23;
var hostsDone = 0;
for(host = 0; host <= hosts; host++){
    ipToCheck = network_addr+'130.'+host;
    $.ajax({
        type: 'GET',
        url: 'js/scanhelper.php',
        async:true,
        data: {
            ip: ipToCheck
    }
    }).done(function(msg) {
        hostsDone++;
        updateProgress((hostsDone/hosts)*100);
        if(msg!=0){
            logSuccess(ipToCheck);
        }
    });
} */
</script>
<!-- /#wrapper -->

<!-- Modal content for student detail -->
<div id="student-detail" class="modal fade" role="dialog">
	<div class="modal-dialog">
		<div class="modal-content" style="float: left; padding-bottom: 10px; padding-top:0px; width:100%;">
			<div class="modal-header" style="padding:25px;">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<div class="modal-title"> <h3>Student Progress</h3></div>
			</div>
			<div class="modal-body">
				<div class="student-detail-pop">
					<div class="col-md-4">
		
      <img id="imgForShowDetails" src="${pageContext.request.contextPath}/resources/images/activity-user-pic.png" alt="" height="100" width="110">

						
					</div>
					<div class="col-md-8">
								<p class="student-mssg" style="font-size:15px;"> Admission No - <span id="sadmissionNo"></span></p>
							    <p class="student-mssg">Name - <span id="fname"></span></p>
							    <p class="student-mssg">Grade - <span id="grd"></span>(<span id="sec"></span>)</p>
								<p class="student-mssg">School Name - <span id="sname"></span></p>
								<p class="student-mssg">Address - <span id="saddress"></span></p>
								<p class="student-mssg">Contact - <span id="scontact"></span>
								</p>
							<!-- Grade  - 6(A)<br>
D A V School, Pitam Pura -->
						
					</div>
					<div class="col-md-12">
						<div class="progress progress-striped active"
							style="height: 20px; margin-top:10px;">
							<div id="progressbarValue" class="bar"
								style="height: 20px; background-color: green; color: #000;">0%</div>
						</div>
					</div>
					<div class="col-md-12" style="margin-top: 10px;">
						<!-- <textarea name="comment"  class="textarea ">Write your Message to Student</textarea>
<button class="btn btn-primary"  type="button" style="margin-top:10px;">Send</button> -->
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<!--Modal content for student detail Enn  -->


<div id="myModal" class="modal fade" role="dialog"></div>




							
							<tr>
								<td colspan="7">No Record Found !!!</td>
							</tr>
						

				</tbody>

			</table>
				


			</div>

		</div>

	</div>
</div>  -->
<!--Unassigned content Model  -->
<<%-- div id="myModal3" class="modal fade" role="dialog"> <div
class="modal-dialog"> <!-- Modal content--> <div class="modal-content">
<form:form
action="${pageContext.request.contextPath}/schoolTeacher/assignAssessment.htm"
method="post" id="assignAssessmentForm" class="form-horizontal"
role="form"> <div class="modal-header"> <button type="button"
class="close" data-dismiss="modal">&times;</button> <div
class="modal-title"> <span>Status</span> Unassigned </div> </div> <div
class="modal-body"> <!-- <div class="col-md-2 col-md-offset-1 mrt10
mrb10"><div class="row">Select All <input type="checkbox"> </div></div>
--> <table border="0" cellspacing="0" cellpadding="0" class="topic-per
table-striped table table-bordered table-hover table-condensed"
style="overflow:scroll;width:90%;"> <thead> <tr> <th><input
type="checkbox" id="assignedAllId" />Select All</th> <th>Admission
No</th> <th>Student Name</th> <th>Grade</th> </tr> </thead> <div
class="row"> <tbody> <td> <input type="checkbox"></td> <td>123456</td>
<td>Rahul</td> <td>6</td> </tbody> </div> </table> </div> <div
class="modal-footer"> <div class="col-md-12 text-center "> <input
type="submit" class="btn btn-primary btn-md" id="assignedAssesssmentId"
onclick="publishTest($('#assessmentId').val())" style="" value="Assign"
/> </div> </div> </form:form> </div> </div> </div> --%>




<!--testpreview window  -->
<!-- <div id="testpreview" class="modal fade" role="dialog">
	<div class="modal-dialog" style="width:70%;">

		Modal content
		<div class="modal-content">
			<form action="" method="post" class="form-horizontal" role="form">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<div class="modal-title">
						<span>Test</span> Name
					</div>
				</div>
				<div class="modal-body">

					<div class="preview-test-question-con">
<div class="preview-test-question">
<span>Question</span>
Monu spends 1.23 hours for English reading, 1.40 hours for Math and 0.39 hours for Science. How many hours does he spend studying?</div>
			
<input type="radio" id="q128" name="quality[22]" value="1" /> 3.02 hours
<input type="radio" id="q128" name="quality[22]" value="1" /> 2.02 hours	
<input type="radio" id="q128" name="quality[22]" value="1" />4.02 hours	
<input type="radio" id="q128" name="quality[22]" value="1" />3.12 hours
</div>
<div class="preview-test-question-con">
<div class="preview-test-question">
<span>Question</span>
Sohan measures a triangular plate whose sides are 12.4 inches, 9.45 inches and 10.35 inches respectively. What is the perimeter of a triangular plate?</div>
			
<input type="radio" id="q128" name="quality[22]" value="1" /> 32.2 inches
<input type="radio" id="q128" name="quality[22]" value="1" /> 42.2 inches
<input type="radio" id="q128" name="quality[22]" value="1" /> 33.2 inches
<input type="radio" id="q128" name="quality[22]" value="1" /> 31.2 inches
</div>
<div class="preview-test-question-con">
<div class="preview-test-question">
<span>Question</span>
Suman scored 56.73 points and Karan scored 74.92 points on a University exam. How many points less did Suman score than Karan?</div>
			
<input type="radio" id="q128" name="quality[22]" value="1" /> 18.19 points
<input type="radio" id="q128" name="quality[22]" value="1" /> 19.19 points
<input type="radio" id="q128" name="quality[22]" value="1" /> 17.19 points
<input type="radio" id="q128" name="quality[22]" value="1" /> 18.29 points
</div>
<div class="preview-test-question-con">
<div class="preview-test-question">
<span>Question</span>
A mixture is obtained by mixing two products A and B respectively. Product A weighs 234.56 grams and the mixture weighs 988.76 grams. How much does Product B weigh?</div>
			
<input type="radio" id="q128" name="quality[22]" value="1" /> 754.2 grams
<input type="radio" id="q128" name="quality[22]" value="1" /> 755.2 grams
<input type="radio" id="q128" name="quality[22]" value="1" /> 764.2 grams
<input type="radio" id="q128" name="quality[22]" value="1" /> 744.2 grams
</div>
<div class="preview-test-question-con">
<div class="preview-test-question">
<span>Question</span>
Davidâs home is 12.53 miles away from the lake and 16.73 miles away from his school. How far is Davidâs school from the lake?</div>
			
<input type="radio" id="q128" name="quality[22]" value="1" /> 4.2 miles
<input type="radio" id="q128" name="quality[22]" value="1" /> 4.3 miles
<input type="radio" id="q128" name="quality[22]" value="1" /> 3.2 miles
<input type="radio" id="q128" name="quality[22]" value="1" /> 5.2 miles
</div>
<div class="preview-test-question-con">
<div class="preview-test-question">
<span>Question</span>
____________________ is the measure used to check body temperature.</div>
			
<input type="radio" id="q128" name="quality[22]" value="1" />Thermometer
<input type="radio" id="q128" name="quality[22]" value="1" />Stethoscopes
<input type="radio" id="q128" name="quality[22]" value="1" />Vaporizer	None of these

</div>
<div class="preview-test-question-con">
<div class="preview-test-question">
<span>Question</span>
The _____________________ scale is named after a German scientist.</div>
			
<input type="radio" id="q128" name="quality[22]" value="1" />Fahrenheit
<input type="radio" id="q128" name="quality[22]" value="1" />Celsius	
<input type="radio" id="q128" name="quality[22]" value="1" />Degree 
</div>
<div class="preview-test-question-con">
<div class="preview-test-question">
<span>Question</span>
The _____________________ scale is named after a Swedish scientist. </div>
			
<input type="radio" id="q128" name="quality[22]" value="1" />Fahrenheit 
<input type="radio" id="q128" name="quality[22]" value="1" />Celsius
<input type="radio" id="q128" name="quality[22]" value="1" />Degree 
</div>
<div class="preview-test-question-con">
<div class="preview-test-question">
<span>Question</span>
The unit is used to measure the temperature is called ________________.</div>
			
<input type="radio" id="q128" name="quality[22]" value="1" />Degree 
<input type="radio" id="q128" name="quality[22]" value="1" />Radian
<input type="radio" id="q128" name="quality[22]" value="1" />Decimal
</div>


				</div>
				<div class="modal-footer">
					<div class="col-md-12 text-center ">
						<input type="submit" class="btn btn-primary btn-md"
							id="assignedAssesssmentId"  onclick="publishTest($('#assessmentId').val())" style="" value="Assign" />
					</div>


				</div>
			</form>
		</div>

	</div>
</div>
 -->

<!-- testpreview window -->

<!--chapter name content Model  -->
<!-- <div id="chaptername" class="modal fade" role="dialog">
	<div class="modal-dialog">

		Modal content
		<div class="modal-content">
			<form action="" method="post" class="form-horizontal" role="form">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<div class="modal-title">
						<span>Select</span> Chapters
					</div>
				</div>
				<div class="modal-body">

					 <div class="col-md-2 col-md-offset-1  mrt10 mrb10"><div class="row">Select All
  <input type="checkbox">
 </div></div>

					<table border="0" cellspacing="0" cellpadding="0" class="topic-per table-striped table table-bordered table-hover table-condensed" style="overflow:scroll;width:90%;">
						
						<div class="row">
						<tbody>
						<tr>
						<select id="chapterId1">
<option value="">Select Topics</option>
<option value="">Knowing Our Numbers (Natural Numbers)</option>
<option value="">Whole Numbers</option>

</select>
</tbody>
						</div>
					</table>


				</div>
				<div class="modal-footer">
					<div class="col-md-12 text-center ">
						
					</div>


				</div>
			</form>
		</div>

	</div>
</div>	 -->

<div  class="modal fade" role="dialog" id="Etestpreview">
	<div class="modal-dialog" style="width:70%;">

		<!-- Modal content -->
		<div class="modal-content" id="Etestpreview123">
	
		</div>

	</div>
</div> 

<!--topic name content Model  -->
<!-- <div id="topicname" class="modal fade" role="dialog">
	<div class="modal-dialog">

		Modal content
		<div class="modal-content">
			<form action="" method="post" class="form-horizontal" role="form">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<div class="modal-title">
						<span>Select</span> Topic
					</div>
				</div>
				<div class="modal-body">
<table border="0" cellspacing="0" cellpadding="0" class="topic-per table-striped table table-bordered table-hover table-condensed" style="overflow:scroll;width:90%;">
						
						<div class="row">
						<tbody>
						<tr>
						<select id="chapterId1">
<option value="">Select Chapter</option>
<option value="">Comparing Numbers</option>
<option value="">Properties of Materials</option>
</select>
</tbody>
						</div>
					</table>


				</div>
				<div class="modal-footer">
					<div class="col-md-12 text-center ">
						
					</div>


				</div>
			</form>
		</div>

	</div>
</div>	 -->
<!--Student list content Model  -->
<div id="studentlist" class="modal fade" role="dialog">
	<div class="modal-dialog">

		<!-- Modal content-->
		<div class="modal-content">
			<form action="" method="post" class="form-horizontal" role="form">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<div class="modal-title">
						<span>Status</span> Unassigned
					</div>
				</div>
				<div class="modal-body">

					<!--  <div class="col-md-2 col-md-offset-1  mrt10 mrb10"><div class="row">Select All
  <input type="checkbox">
 </div></div> -->

					<table border="0" cellspacing="0" cellpadding="0"
						class="topic-per table-striped table table-bordered table-hover table-condensed"
						style="overflow: scroll; width: 90%;">
						<thead>
							<tr>
								<!-- 	<th><input type="checkbox" id="assignedAllId" />Select All</th> -->
								<th>Sr. No</th>
								<th>Student Name</th>

							</tr>
						</thead>
						<tbody>
							<c:forEach var='sts' items="${usl}" varStatus="s">
								<tr>
									<td>${s.count}</td>
									<td>${sts.firstName }</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>


				</div>
				<!-- 	<div class="modal-footer">
					<div class="col-md-12 text-center ">
						<input type="submit" class="btn btn-primary btn-md"
							id="assignedAssesssmentId"  onclick="publishTest($('#assessmentId').val())" style="" value="Assign" />
					</div>


				</div> -->
			</form>
		</div>

	</div>
</div>

<!--upcoming test edit  -->
<div id="upcoming-edit" class="modal fade" role="dialog">
	<div class="modal-dialog">

		<!-- Modal content-->
		<div class="modal-content">
			<form action="${pageContext.request.contextPath}/schoolTeacher/updateAssessment.htm" method="post" class="form-horizontal" role="form" id="updateAssessmentForm">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<div class="modal-title">
						<span><b>Edit Upcoming Test</span> 
					</div>
				</div>
				<div class="modal-body">

					<!--  <div class="col-md-2 col-md-offset-1  mrt10 mrb10"><div class="row">Select All
  <input type="checkbox">
 </div></div> -->
 <div class="row">
 <div class="col-md-12">
 <input type="hidden" name="assessmentId" id="hiddAssessmentId"/>
<b>Test Name</b><br>
<input class="text-field " type="text" id="testNameIds"  placeholder="Enter Test Name"> </div>
</div>
<div class="row">
<div class="col-md-12">
<b>Test Start Date</b><br>

<input class="text-field " type="text" id="startDate1"  placeholder="Enter Test Start Date"/>
<input class="text-field " type="hidden" id="asmntId" >
</div>
</div>
<div class="row">
<div class="col-md-12">
<b>Test End Date</b><br>

<input class="text-field " type="text" id="endDate1"  placeholder="Enter Test End Date"/>
</div>
</div>	
<div class="row">
<div class="col-md-3">
<br>
<span id="dateErr" style="color: red;"></span>
<button class="btn btn-primary" id="updateAssessment" type="button" onclick="submitAssessmentEditForm()">Update</button></div>
</div>				

 </div>
<input type="hidden" id="hiddId"/>

				</div>
				</form>
		
		</div>

	</div>
</div>
<div style="width: 38px; height: 117px; right: 0px; top: 50%; position: fixed;">
<a href="https://docs.google.com/forms/d/e/1FAIpQLScylZUgeQrLr2oGBufoyeQFKFIlBQD67qvOdFhWu6Qcd17rRw/viewform" target="blank"><img src="${pageContext.request.contextPath}/resources/images/feedback.png" alt=""></a>
</div>
 
