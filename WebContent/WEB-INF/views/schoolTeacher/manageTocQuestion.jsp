<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<jsp:include page="schoolTeacherHeader.jsp">
	<jsp:param value="manageToc" name="header" />
</jsp:include>
</head>

<body>
	<div id="page-wrapper">

		<div class="container-fluid" style="margin-top:5%;">
		
		
		<div class="row">
<div class="col-lg-12">

<jsp:include page="teacherSubjectHeader.jsp">
		<jsp:param value="dashboard" name="header" />
		</jsp:include>
		
		</div>
		 <div class="clearfix"></div>
		</div>
		
		

			<!-- Page Heading -->
			<div class="row">
				<div class="col-md-12">
					<h1 class="page-header">Content Library</h1>
					<ol class="breadcrumb">
						<li>
							<a href="${pageContext.request.contextPath}/schoolTeacher/getManageTocHome.htm">Content Library</a>
						</li>
						<li>
							<a href="${pageContext.request.contextPath}/schoolTeacher/getManageTocModule.htm?topicId=${param.topicId}&secId=${param.secId}&subId=${param.subId}&grdId=${param.gradeId}&subjectName=${param.subjectName}&chapName=${param.chapName}&topicName=${param.topicName}">${param.subjectName} / ${param.chapName} / ${param.topicName}</a>
						</li>
						<li class="active">New Question</li>
					</ol>
				</div>
			</div>
			<!-- /.row -->

			<div class="col-md-12">
				<div class="row">

					<form:form action="${pageContext.request.contextPath}/schoolTeacher/uploadQuestion.htm?subjectName=${param.subjectName}&chapName=${param.chapName}&topicName=${param.topicName}" modelAttribute="globalVO" enctype="multipart/form-data" id="upload" accept-charset="utf-8">

						<form:hidden path="schoolId" value="${user.schoolId}" />
						<form:hidden path="loginUserId" value="${user.id}" />
						<form:hidden path="topicId" value="${param.topicId}" />
						<form:hidden path="questionCategoryId" value="2" />
						<input type="hidden" name="loginUserType" value="${sessionScope.user.userType}" />
						
						
						
						<form:hidden path="secId" value="${sectionId }" />
						<form:hidden path="subId" value="${subjectId }" />
						<form:hidden path="grdId" value="${gradeId }" />
						<!-- Right now we are using only question with image type questions. so providing static value -->
						<div class="new-question">

							<!-- <div class="col-md-3 col-sm-3">
				            <select name="">
					            <option>Question Category</option>
					            <option>Text Based</option>
					            <option>Image Based</option>
					            <option>Video Based</option>
				            </select>
			            </div> -->

							<div class="col-md-3 col-sm-3">
								<select name="questionTypeId" required="required" id="questionTypeId">
									<option value="">Question Type</option>
									<c:forEach var="questionType" items="${globalVO.questionTypeList}">
										<option id = "questionTypeId" value="${questionType.id}">${questionType.name}</option>
									</c:forEach>
								</select>
							</div>

							<div class="col-md-3 col-sm-3">
								<select name="questionDifficultyLevelId" required="required">
									<option value="">Difficulty Level</option>
									<c:forEach var="questionDifficulty" items="${globalVO.questionDifficultyLevelList}">
										<option value="${questionDifficulty.id}">${questionDifficulty.name}</option>
									</c:forEach>
								</select>
							</div>

							<div class="col-md-3 col-sm-3">
								<select name="questionCognitiveLevelId" required="required" id="questionCognitiveLevelId">
									<option value="">Cognitive Level</option>
									<c:forEach var="questionCognitive" items="${globalVO.questionCognitiveLevelList}">
										<option value="${questionCognitive.id}">${questionCognitive.name}</option>
									</c:forEach>
								</select>
							</div>

						</div>
						<div class="clearfix"></div>

						<div class="col-md-5 col-sm-6 mrt20">
							<label>Question</label>
							<textarea name="question" class="mrt10" cols="" rows="" id="questionId"></textarea>
						</div>
						<div class="col-md-3 ques-file-fld">
							<input name="questionImage" type="file"  id="questionImgId">
						</div>

						<div class="clearfix"></div>

						<div class="col-md-5 col-sm-5 mrt20">
							<label>Option 1</label>
							<textarea name="option1" class="mrt10" cols="" rows="" id="optionId1"></textarea>
						</div>
						<div class="col-md-3 col-sm-2 col-xs-3 ques-file-fld">
							<input name="option1Image" type="file" id="optionImgId1" >
						
						
						
						</div>
						<div class="col-md-2 col-sm-2 col-xs-4 ques-file-fld">
							<label>Correct</label>
							<form:radiobutton path="isCorrectOption1" value="1" class="correctAnsCls"></form:radiobutton>
						</div>
						<div class="col-md-2 col-sm-2 col-xs-5 ques-file-fld">
							<label>Incorrect</label>
							<form:radiobutton path="isCorrectOption1" value="0" class="inCorrect"></form:radiobutton>
						</div>

						<div class="clearfix"></div>

						<div class="col-md-5 col-sm-5 mrt20">
							<label>Option 2</label>
							<textarea name="option2" class="mrt10" cols="" rows="" id="optionId2"></textarea>
						</div>
						<div class="col-md-3 col-sm-2 col-xs-3 ques-file-fld">
							<input name="option2Image" type="file" id="optionImgId2" >
						</div>
						<div class="col-md-2 col-sm-2 col-xs-4 ques-file-fld">
							<label>Correct</label>
							<form:radiobutton path="isCorrectOption2" value="1" class="correctAnsCls"></form:radiobutton>
						</div>
						<div class="col-md-2 col-sm-2 col-xs-5 ques-file-fld">
							<label>Incorrect</label>
							<form:radiobutton path="isCorrectOption2" value="0" class="inCorrect"></form:radiobutton>
						</div>

						<div class="clearfix"></div>

						<div class="col-md-5 col-sm-5 mrt20">
							<label>Option 3</label>
							<form:textarea path="option3" class="mrt10" cols="" rows="" id="optionId3"></form:textarea>
						</div>
						<div class="col-md-3 col-sm-2 col-xs-3 ques-file-fld">
							<input name="option3Image" type="file" id="optionImgId3" >
						</div>
						<div class="col-md-2 col-sm-2 col-xs-4 ques-file-fld">
							<label>Correct</label>
							<form:radiobutton path="isCorrectOption3" value="1" class="correctAnsCls"></form:radiobutton>
						</div>
						<div class="col-md-2 col-sm-2 col-xs-5 ques-file-fld">
							<label>Incorrect</label>
							<form:radiobutton path="isCorrectOption3" value="0" class="inCorrect"></form:radiobutton>
						</div>

						<div class="clearfix"></div>

						<div class="col-md-5 col-sm-5 mrt20">
							<label>Option 4</label>
							<form:textarea path="option4" class="mrt10" cols="" rows="" id="optionId4"></form:textarea>
						</div>
						<div class="col-md-3 col-sm-2 col-xs-3 ques-file-fld">
							<input name="option4Image" type="file" id="optionImgId4">
						</div>
						<div class="col-md-2 col-sm-2 col-xs-4 ques-file-fld">
							<label>Correct</label>
							<form:radiobutton path="isCorrectOption4" value="1" class="correctAnsCls"></form:radiobutton>
						</div>
						<div class="col-md-2 col-sm-2 col-xs-5 ques-file-fld">
							<label>Incorrect</label>
							<form:radiobutton path="isCorrectOption4" value="0" class="inCorrect"></form:radiobutton>
						</div>

						<div class="clearfix"></div>
                       
                       <div class="col-md-1 mrt20">
							<a href="${pageContext.request.contextPath}/schoolTeacher/getManageTocModule.htm?topicId=${param.topicId}&secId=${param.secId}&subId=${param.subId}&grdId=${param.gradeId}&subjectName=${param.subjectName}&chapName=${param.chapName}&topicName=${param.topicName}&tab=question" class="btn btn-primary" >Back</a>
						</div>
						<div class="col-md-2 mrt20">
							<input type="submit" class="btn btn-danger" value="Save Question" id="saveQuestionId">
						</div>
						
						<span class="errorCls2" style="color: red;"></span>
						<div class="clearfix"></div>
					</form:form>
				</div>
			</div>
			<!-- /#page-wrapper -->




		</div>
	</div>
	<c:if test="${param.submit == 'true'}">
		<script type="text/javascript">
			alert('Question Uploaded Successfully');
            document.getElementById("upload").reset();
            location.href = "${pageContext.request.contextPath}/schoolTeacher/getManageTocQuestion.htm?topicId=${param.topicId}&subjectName=${param.subjectName}&chapName=${param.chapName}&topicName=${param.topicName}";
		</script>
	</c:if>

	<script type="text/javascript">
		$(document).ready(function()
        {
	        $("#saveQuestionId").on("click", function()
	        {
	        	var flag=true;
		        var status = false;
		        $(".errorCls2").empty();
		        
		        
		        
		        var question = $("#questionId").val();
		        if(question == null || question == "")
		        {
			        var questionFile = $("#questionImgId").val();
			        if(questionFile == null || questionFile == "")
			        {
				        $(".errorCls2").html("Enter Question or upload question!");
				        $(".questionId").focus();
				        flag= false;
			        }
			        else
			        {
				        var val = questionFile.split(".");
				        if(val[val.length - 1] != "jpg" && val[val.length - 1] != "jpeg" && val[val.length - 1] != "png")
				        {
					        $(".errorCls2").html("Please browse png or jpg file!");
					        flag= false;
				        }
			        }
		        }
		        
		        var option1 = $("#optionId1").val();
		        if(option1 == null || option1 == "")
		        {
			        var option1File = $("#optionImgId1").val();
			        if(option1File == null || option1File == "")
			        {
				        $(".errorCls2").html("Enter option1 or upload option1!");
				        flag= false;
			        }
			        else
			        {
				        var val = option1File.split(".");
				        if(val[val.length - 1] != "jpg" && val[val.length - 1] != "jpeg" && val[val.length - 1] != "png")
				        {
					        $(".errorCls2").html("Please browse png or jpg file!");
					        flag= false;
				        }
			        }
		        }
		        
		        var option2 = $("#optionId2").val();
		        if(option2 == null || option2 == "")
		        {
			        var option2File = $("#optionImgId2").val();
			        if(option2File == null || option2File == "")
			        {
				        $(".errorCls2").html("Enter option2 or upload option2!");
				        flag= false;
			        }
			        else
			        {
				        var val = option1File.split(".");
				        if(val[val.length - 1] != "jpg" && val[val.length - 1] != "jpeg" && val[val.length - 1] != "png")
				        {
					        $(".errorCls2").html("Please browse png or jpg file!");
					        flag= false;
				        }
			        }
		        }
		        var count=0;
		        $(".correctAnsCls").each(function(e)
		        {
			        if($(this).prop('checked') == true)
			        {
				        status = true;
				        count++;
			        }
		        });
		        if(status == false)
		        {
			        $(".errorCls2").html("select atleast one correct answer!");
			        $(".errorCls2").focus();
			        flag= false;
		        }
		        
		        status = false;
		       
		        $(".inCorrect").each(function(e)
		        {
			        if($(this).prop('checked') == true)
			        {
				        status = true;
				      
				        
			        }
		        });
		        if(status == false)
		        {
			        
			        flag= false;
		        }
		         if ($("#questionTypeId").val()=='10' && count>1){
		        	
		        		flag=false;
		        		$(".errorCls2").html("Only one answer must be correct!");
				        $(".errorCls2").focus();
		        	
		        	
		        }else if($("#questionTypeId").val()=='16' && count==1)
		        	 {
		        		flag=false;
		        		$(".errorCls2").html("More than one answer should be correct!");
				        $(".errorCls2").focus();
		        	
		        }
		        
		         
		       
		        if(!flag)
		        	return flag;
		        else if($("#questionTypeId").val()=='' || $("#questionCognitiveLevelId").val()==''||$("#questionDifficultyLevelId").val()=='')
		      	{
		        	$('#upload').submit();
		      	}
		        else
		        	{
		        	$("#fullPageloader").show();
		        	$('#upload').submit();
		        	
		        	
		        
		        	}
		        
	        });
        });
	</script>