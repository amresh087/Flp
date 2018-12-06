<!-- Bootstrap core CSS -->

   <%--  <link href="${pageContext.request.contextPath}/resources/css/admin-custom.css" rel="stylesheet" type="text/css">
   <link href="${pageContext.request.contextPath}/resources/css/bootstrap.css" rel="stylesheet" type="text/css">
	<link href="${pageContext.request.contextPath}/resources/css/owl.carousel.css" rel="stylesheet" media="screen">
    <link href="${pageContext.request.contextPath}/resources/css/circle.css" rel="stylesheet" media="screen">
    <link href="${pageContext.request.contextPath}/resources/css/owl.theme.css" rel="stylesheet" media="screen">
    <link href="${pageContext.request.contextPath}/resources/css/build.css" rel="stylesheet" media="screen">
    <link href="${pageContext.request.contextPath}/resources/css/font-awesome.css" rel="stylesheet" media="screen"> --%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    <%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<jsp:include page="../globalPage.jsp" />
<style type="text/css">
.notifi-detail{width:100%;padding:5px; float:left; background:#EBEFF2; margin-top:2px;}
.user-name{color:#226A94; font-style: italic;}
.user-img{float: left; padding-left: 0; padding-right: 0; width: 6%;}
.comments{width:100%; float:left; padding:10px; background: url(${pageContext.request.contextPath}/resources/images/quo-1.png) left top no-repeat;  background-position: 0 7px;
    float: left; padding-left: 28px;
    padding-top: 17px;}
    p{text-align:left;}
</style>
<div class="container-fluid mrt20 question-con" id="instruction">
<div class="container">
<div class="row">
<div class=col-md-12>
<div class="table-responsive">
<h3>Comments on this Question</h3>
<table id="dataTableId" class="display table" style="width: 100%; font-size: 13px;">
 <thead>
 <tr class="dataTab" style="font-size:13px;">
<th>Sr. No.</th>
<th>Question-Id</th>
<th>Question</th>
<th>Level</th>
<th class="text-center">Question Image</th>
<th>Action</th>
</tr>
</thead>
<tr>
<td>1</td>
<td>${questionDetails.id }</td>
<td><p>${questionDetails.description}</p>

		
										<c:forEach var="ans"
											items="${questionDetails.answers}" varStatus="stats">
												<p style="float:left">${stats.count}. &nbsp;
											${ans.description} &nbsp; 
											<c:if test="${not empty ans.imagePath}">
											<img
												src="${commonPath}/questionImages/${ans.imagePath}" style="max-height:250px; max-width:250px"/>
										</c:if></p>
											

										</c:forEach> 


</td>
<td>${questionDetails.difficultyLevel.name}</td>
<td><c:if test="${not empty questionDetails.imagePath}"><img src="${commonPath}/questionImages/${questionDetails.imagePath}" style="max-height:250px; max-width:250px"/></c:if>
										<c:if test="${empty questionDetails.imagePath}">N/A</c:if></td>
<td><!-- <a href="javascript:void(0)" data-toggle="modal" data-target="#myModal4" id="editQuestionId"><i class="fa fa-pencil-square-o" aria-hidden="true"></i>&nbsp;Edit</a> --><br>
 
<!--  <a href="#"><i class="fa fa-times" aria-hidden="true"></i>&nbsp;Remove</a> --></td>
</tr>
</table>
<c:if test="${not empty commentsLst }">
<c:forEach var="comment" items="${commentsLst}">
<div class="comments">
<div class="notifi-detail">
   <table class="display">
 <tr>
 <td valign="top"><div class="user-img"><img src="${pageContext.request.contextPath}/resources/images/user-pic.png" style=" margin-right:10px; max-width: 60px;"><br>
   </div></td>
 <td><div class="notifi-detail">
   <div class="user-name"><b>${comment.user.firstName }</b></div>
  <p>${comment.commentDesc } </p>
  <span class="notification-date" style="color:#588FCC;"> <b><fmt:formatDate pattern="dd-MM-yyyy" 
            value="${comment.postedOn}" /></b></span>
 </div></td>
 </tr>
 </table>
 </div>

 </div>
</c:forEach>

</c:if>

 

 
</div>
</div>
</div>
</div>
</div>



<!-- Modal4 -->
	<div id="myModal4" class="modal fade" role="dialog">
	 <div class="eng-img img-responsive" id="engImg"></div>
		<div class="modal-dialog assign-name-pop" style="width:780px !important">
	
			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" value="Reset Form" onclick="questionID.reset()">&times;</button>
					<div class="modal-title">
						<span id="headerId"></span> Questions
					</div>
				</div>
	
				<div class="modal-body assign-name-pop-mid panel-body2">
	
					<div class="col-md-12">
						<div class="row">
	
							<div class="edit-quest">
	
								<form:form action="${pageContext.request.contextPath}/admin/updateQuestionComment.htm" method="POST" modelAttribute="globalVO" enctype="multipart/form-data" id="questionFormId" name="questionID">
<div class="new-question">

									<!--  <div class="col-md-3 col-sm-3">
							            <select name="">
							            <option>Question Category</option>
							            <option>Text Based</option>
							            <option>Image Based</option>
							            <option>Video Based</option>
							            </select>
							            </div> -->

									<input type="hidden" name="questionId" id="editQuestionIds"
										value="" /> <input type="hidden" name="optionId1"
										id="editOptionId1" value="" /> <input type="hidden"
										name="optionId2" id="editOptionId2" value="" /> <input
										type="hidden" name="optionId3" id="editOptionId3" value="" />
									<input type="hidden" name="optionId4" id="editOptionId4"
										value="" /> 
									<form:hidden path="loginUserId" value="${user.id}" />
									<form:hidden path="questionCategoryId" value="2" />
									<!-- Right now we are using only question with image type questions. so providing static value -->

									<div class="col-md-4 col-sm-3">
										<select id="questionTypeId" name="questionTypeId"
											required="required">
											<option value="">Question Type</option>
											<c:forEach var="questionType"
												items="${questType}">
												<option value="${questionType.id}">${questionType.name}</option>
											</c:forEach>
										</select>
									</div>

									<div class="col-md-4 col-sm-3">
										<select id="questionDifficultyLevelId"
											name="questionDifficultyLevelId" required="required">
											<option value="">Difficulty Level</option>
											<c:forEach var="questionDifficulty"
												items="${difficultType}">
												<option value="${questionDifficulty.id}">${questionDifficulty.name}</option>
											</c:forEach>
										</select>
									</div>

									<div class="col-md-4 col-sm-3">
										<select id="questionCognitiveLevelId"
											name="questionCognitiveLevelId" required="required">
											<option value="">Cognitive Level</option>
											<c:forEach var="questionCognitive"
												items="${cognitiveType}">
												<option value="${questionCognitive.id}">${questionCognitive.name}</option>
											</c:forEach>
										</select>
									</div>

								</div>
								<div class="clearfix"></div>

								<div class="col-md-6 col-sm-6 mrt20">
									<label>Question</label>
									<textarea class=" mrt10" name="question" cols="" rows=""
										id="quesDescription"></textarea>
								</div>
								<div class="col-md-4 col-sm-4 ques-file-fld">
									<input name="questionImage" type="file" id="questionImgId">
								
								</div>
								
								<div class="col-md-2 col-sm-2 col-xs-12 ques-file-fld">
								<div id="quesImageDivId" class="enlarge">
								</div>	
								    
								</div>
								<div class="clearfix"></div>

								<div class="col-md-6 col-xs-12 col-sm-6 mrt20">
									<label>Option 1</label>
									<textarea name="option1" class="mrt10" cols="" rows=""
										id="optionDescription1"></textarea>
								</div>
								<div class="col-md-4 col-sm-4 col-xs-12 ques-file-fld">
									 <input name="option1Image" type="file" id="optionImgId1">
								     <input name="isCorrectOption1" type="radio" value="1" class="correctAnsCls mr-rd"><label class="mr-rd">Correct</label>
								    <input name="isCorrectOption1" type="radio" value="0" class="inCorrect mr-rd"><label class="mr-rd">Incorrect</label>
								</div>
								
								
								<div class="col-md-2 col-sm-2 col-xs-12 ques-file-fld">
								<div id="ansImageDivId1" class="enlarge">
								</div>	
								    
								</div>
								
								
								<!-- 
								<div class="col-md-2 col-sm-2 col-xs-5 ques-file-fld">
									
								</div> -->

								<div class="clearfix"></div>


								<div class="col-md-6 col-sm-6 col-xs-12 mrt20">
									<label>Option 2</label>
									<textarea name="option2" class="mrt10" cols="" rows=""
										id="optionDescription2"></textarea>
								</div>
								<div class="col-md-4 col-sm-4 col-xs-12 ques-file-fld">
									<input name="option2Image" type="file" id="optionImgId2">
									<input name="isCorrectOption2" type="radio" value="1" class="correctAnsCls mr-rd"><label class="mr-rd">Correct</label>
									<input name="isCorrectOption2" type="radio" value="0" class="inCorrect mr-rd"><label class="mr-rd">Incorrect</label>
								</div>
								
								<div class="col-md-2 col-sm-2 col-xs-12 ques-file-fld">
							   <div id="ansImageDivId2" class="enlarge"></div>
								</div>
								 <!-- <div class="col-md-2 col-sm-2 col-xs-5 ques-file-fld">
									
								</div> -->

								<div class="clearfix"></div>

								<div class="col-md-6 col-xs-12 col-sm-6 mrt20">
									<label>Option 3</label>
									<textarea name="option3" class="mrt10" cols="" rows=""
										id="optionDescription3"></textarea>
								</div>
								<div class="col-md-4 col-sm-4 col-xs-12 ques-file-fld">
									<input name="option3Image" type="file" id="optionImgId3">
									<input name="isCorrectOption3" type="radio" value="1" class="correctAnsCls mr-rd"><label class="mr-rd">Correct</label>
									<input name="isCorrectOption3" type="radio" value="0" class="inCorrect mr-rd"><label class="mr-rd">Incorrect</label>
								</div>
								
								<div class="col-md-2 col-sm-2 col-xs-12 ques-file-fld">
								<div id="ansImageDivId3" class="enlarge" ></div>	
								</div>
								<!-- <div class="col-md-2 col-sm-2 col-xs-5 ques-file-fld">
									
								</div> -->

								<div class="clearfix"></div>

								<div class="col-md-6 col-sm-6 col-xs-12 mrt20">
									<label>Option 4</label>
									<textarea name="option4" class="mrt10" cols="" rows=""
										id="optionDescription4"></textarea>
								</div>
								
								<div class="col-md-4 col-sm-4 col-xs-12 ques-file-fld">
									<input name="option4Image" type="file" id="optionImgId4">
									<input name="isCorrectOption4" type="radio" value="1" class="correctAnsCls mr-rd"><label class="mr-rd">Correct</label>
									<input name="isCorrectOption4" type="radio" value="0" class="inCorrect mr-rd"><label class="mr-rd">Incorrect</label>
									
								</div>
								
								<div class="col-md-2 col-sm-2 col-xs-12 ques-file-fld">
									<div id="ansImageDivId4" class="enlarge"></div>
								</div>
								<!-- div class="col-md-2 col-sm-2 col-xs-5 ques-file-fld">
									
								</div> -->

								<div class="clearfix"></div>
									<span class="errorCls2" style="color: red;"></span>
									<br>
								<div class="col-md-2 mrt20">
									<div class="col-md-2 mrt20"><a href="javascript:void(0)" class="btn btn-danger" id="updateQuestionId">Update Question</a></div>
								</div>
							
								
								</form:form>
							</div>
						</div>
					</div>
	
				</div>
	
				<div class="modal-footer"></div>
			</div>
	
		</div>
	</div>
	<!-- Modal4 end -->
	
	<script type="text/javascript">
	
	$("#editQuestionId").on("click", function(){
		
		
	    var count=0;
    	addEditRemove = "edit";


		$('#editQuestionIds').val("${questionDetails.id}");
		$('#questionTypeId option[value=${questionDetails.questionTypeId}]').prop('selected', true);
		$('#questionDifficultyLevelId option[value=${questionDetails.difficultyLevelId}]').prop('selected', true);
		$('#questionCognitiveLevelId option[value=${questionDetails.cognitiveLevelId}]').prop('selected', true);
		$('#quesDescription').html("<c:out value='${questionDetails.description}' />");
		
		<c:if test="${not empty questionDetails.imagePath}">;
			$('#quesImageDivId').empty().append("<img width='70px' height='70px' src='${questionResourceDirectory}${questionDetails.imagePath}' />").show();
		</c:if>;
		
		<c:forEach var="ans" items="${questionDetails.answers}" varStatus="status">
			<c:if test="${status.count == 1}">;
				$('#editOptionId1').val("${ans.id}");
				$('#optionDescription1').html("<c:out value='${ans.description}' />");
				$("input[name=isCorrectOption1][value=${ans.isCorrect}]").prop('checked', true);
				
				<c:if test="${not empty ans.imagePath}">;
					$('#ansImageDivId1').empty().append("<img width='70px' height='70px' src='${questionResourceDirectory}${ans.imagePath}' />").show();
				</c:if>
				count++;
			</c:if>
			<c:if test="${status.count == 2}">;
				$('#editOptionId2').val("${ans.id}");
				$('#optionDescription2').html("<c:out value='${ans.description}' />");
				$("input[name=isCorrectOption2][value=${ans.isCorrect}]").prop('checked', true);
				
				<c:if test="${not empty ans.imagePath}">;
					$('#ansImageDivId2').empty().append("<img width='70px' height='70px' src='${questionResourceDirectory}${ans.imagePath}' />").show();
				</c:if>
				count++;
			</c:if>
			<c:if test="${status.count == 3}">;
				$('#editOptionId3').val("${ans.id}");
				$('#optionDescription3').html("<c:out value='${ans.description}' />");
				$("input[name=isCorrectOption3][value=${ans.isCorrect}]").prop('checked', true);
				
				<c:if test="${not empty ans.imagePath}">;
					$('#ansImageDivId3').empty().append("<img width='70px' height='70px' src='${questionResourceDirectory}${ans.imagePath}' />").show();
				</c:if>
				count++;
			</c:if>
			<c:if test="${status.count == 4}">;
				$('#editOptionId4').val("${ans.id}");
				$('#optionDescription4').html("<c:out value='${ans.description}' />");
				$("input[name=isCorrectOption4][value=${ans.isCorrect}]").prop('checked', true);
				
				<c:if test="${not empty ans.imagePath}">;
					$('#ansImageDivId4').empty().append("<img width='70px' height='70px' src='${questionResourceDirectory}${ans.imagePath}' />");
				</c:if>
				count++;
			</c:if>
		</c:forEach>;


if(count<4)
	{
	$('#editOptionId4').val("");
	$('#optionDescription4').html("");
	
	$("isCorrectOption4").prop('checked', false);;
	$('#ansImageDivId4').empty();
	if(count<3){
		$('#editOptionId3').val("");
		$('#optionDescription3').html("");
		$("isCorrectOption3").prop('checked', false);;
		$('#ansImageDivId3').empty();
		if(count<2){
			$('#editOptionId2').val("");
			$('#optionDescription2').html("");
			$("isCorrectOption2").prop('checked', false);
			$('#ansImageDivId2').empty();
		}
	}
	
	
	}



	
	$('#headerId').empty().html("Edit");
		
	});
	
	
	$("#updateQuestionId").on("click", function()
			{
				if(validateForm() != false)
					submitMessageForm();
		    });
			 
			function validateForm()
			{
				var status = false;
				var question = $("#quesDescription").val();
				
			    $(".errorCls2").empty();
			    
				if($('#questionTypeId').val() == "")
				{
					$(".errorCls2").html("Please select question type");
					return false;
				}
			    
				if($('#questionDifficultyLevelId').val() == "")
				{
					$(".errorCls2").html("Please select question difficulty level");
					return false;
				}
				
				if($('#questionCognitiveLevelId').val() == "")
				{
					$(".errorCls2").html("Please select cognitive level");
					return false;
				}
				
			    if(question==null || question=="")
			    {
			    	var questionFile=$("#questionImgId").val();
			    	if(questionFile==null || questionFile=="")
			    	{
			    		$(".errorCls2").html("Enter Question or upload question!");
					    $(".questionId").focus();
					    return false;
			    	}
			    	else
			    	{
			    		var val=questionFile.split(".");
			    		if(val[val.length-1]!="jpg" && val[val.length-1]!="jpeg" && val[val.length-1]!="png" )
			    		{
			    			$(".errorCls2").html("Please browse png or jpg file!");
						    return false;
			    		}
			    	}
			    }
			    
			    var option1 = $("#optionDescription1").val();
			    if(option1==null || option1=="")
			    {
			    	var option1File=$("#optionImgId1").val();
			    	if(option1File==null || option1File=="")
			    	{
			    		$(".errorCls2").html("Enter option1 or upload option1!");
					    return false;
			    	}
			    	else
			    	{
			    		var val=option1File.split(".");
			    		if(val[val.length-1]!="jpg" && val[val.length-1]!="jpeg" && val[val.length-1]!="png" )
			    		{
			    			$(".errorCls2").html("Please browse png or jpg file!");
						    return false;
			    		}
			    	}
			    }
			    
			    var option2 = $("#optionDescription2").val();
			    if(option2==null || option2=="")
			    {
			    	var option2File=$("#optionImgId2").val();
			    	if(option2File==null || option2File=="")
			    	{
			    		$(".errorCls2").html("Enter option2 or upload option2!");
					    return false;
			    	}
			    	else
			    	{
			    		var val=option1File.split(".");
			    		if(val[val.length-1]!="jpg" && val[val.length-1]!="jpeg" && val[val.length-1]!="png" )
			    		{
			    			$(".errorCls2").html("Please browse png or jpg file!");
						    return false;
			    		}
			    	}
			    }
			    var counter=0;
			    $(".correctAnsCls").each(function(e)
			    {
				    if($(this).prop('checked') == true)
				    {
					    status = true;
					    counter++;
				    }
			    });
			    if(status == false)
			    {
				    $(".errorCls2").html("select atleast one correct answer!");
				    $(".errorCls2").focus();
				    return false;
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
				    $(".errorCls2").html("Atleast one answer must be in-correct!");
				    $(".errorCls2").focus();
				    return false;
			    }
			    
			    
			    if ($("#questionTypeId").val()=='10' && counter>1){
		    		$(".errorCls2").html("Only one answer must be correct!");
			        $(".errorCls2").focus();
			        return false;
		    }else if($("#questionTypeId").val()=='16' && counter==1)
		    	 {
		    		$(".errorCls2").html("More than one answer should be correct!");
			        $(".errorCls2").focus();
			        return false;
		    }
			}
			function submitMessageForm()
			{
				$("#fullPageloader").show();
		
				$("#questionFormId").submit();		// Submit the form.				
			}
	</script>