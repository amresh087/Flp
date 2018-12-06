<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<style>
.eng-img{position:absolute; display:none; margin:5% 30%; z-index:999999; background:#fff; padding:7px; text-align:center; }
</style>
<script>
	var addEditRemove;

	$(document).ready(function()
	{
		//$('#reportTableID').dataTable().fnDestroy();			// Use it when you want to reinitialize data table
		$('#dataTableId').dataTable();
		
		
	});
	
	$(".enlarge").on("mouseover",function() {
		var val=$(this).children().attr('src');
		  $("#engImg" ).empty().html('<img src="'+val+'"/>').show();
		}); 
	
	$(".enlarge").mouseleave(function() {
		  $("#engImg" ).empty().hide();
		}); 
	
	function editQuestion(questionId)
	{
		var count=0;
		$('#questionFormId')[0].reset();
		addEditRemove = "edit";
		$(".enlarge").empty()
		$(".errorCls2").html('');
		<c:forEach var="quesTopic" items="${questionTopic}"	varStatus="status">;
		if('${quesTopic.question.id}' == questionId)
		{
			$('#editQuestionId').val("${quesTopic.question.id}");
			$('#editQuestionTopicId').val("${quesTopic.id}");
			$('#questionTypeId option[value=${quesTopic.question.questionTypeId}]').prop('selected', true);
			$('#questionDifficultyLevelId option[value=${quesTopic.question.difficultyLevelId}]').prop('selected', true);
			$('#questionCognitiveLevelId option[value=${quesTopic.question.cognitiveLevelId}]').prop('selected', true);
			$('#quesDescription').html("<c:out value='${quesTopic.question.description}' />");
			
			<c:if test="${not empty quesTopic.question.imagePath}">;
				$('#quesImageDivId').empty().append("<img width='70px' height='70px' src='${globalVO.questionResourceDirectory}${quesTopic.question.imagePath}' />").show();
			</c:if>;
			
			<c:forEach var="ans" items="${quesTopic.question.answers}" varStatus="status">
				<c:if test="${status.count == 1}">;
					$('#editOptionId1').val("${ans.id}");
					$('#optionDescription1').html("<c:out value='${ans.description}' />");
					$("input[name=isCorrectOption1][value=${ans.isCorrect}]").prop('checked', true);
					
					<c:if test="${not empty ans.imagePath}">;
						$('#ansImageDivId1').empty().append("<img width='70px' height='70px' src='${globalVO.questionResourceDirectory}${ans.imagePath}' />").show();
					</c:if>
					count++;
				</c:if>
				<c:if test="${status.count == 2}">;
					$('#editOptionId2').val("${ans.id}");
					$('#optionDescription2').html("<c:out value='${ans.description}' />");
					$("input[name=isCorrectOption2][value=${ans.isCorrect}]").prop('checked', true);
					
					<c:if test="${not empty ans.imagePath}">;
						$('#ansImageDivId2').empty().append("<img width='70px' height='70px' src='${globalVO.questionResourceDirectory}${ans.imagePath}' />").show();
					</c:if>
					count++;
				</c:if>
				<c:if test="${status.count == 3}">;
					$('#editOptionId3').val("${ans.id}");
					$('#optionDescription3').html("<c:out value='${ans.description}' />");
					$("input[name=isCorrectOption3][value=${ans.isCorrect}]").prop('checked', true);
					
					<c:if test="${not empty ans.imagePath}">;
						$('#ansImageDivId3').empty().append("<img width='70px' height='70px' src='${globalVO.questionResourceDirectory}${ans.imagePath}' />").show();
					</c:if>
					count++;
				</c:if>
				<c:if test="${status.count == 4}">;
					$('#editOptionId4').val("${ans.id}");
					$('#optionDescription4').html("<c:out value='${ans.description}' />");
					$("input[name=isCorrectOption4][value=${ans.isCorrect}]").prop('checked', true);
					
					<c:if test="${not empty ans.imagePath}">;
						$('#ansImageDivId4').empty().append("<img width='70px' height='70px' src='${globalVO.questionResourceDirectory}${ans.imagePath}' />");
					</c:if>
					count++;
				</c:if>
			</c:forEach>;
		}
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
	}
	
	function addQuestion()
	{
		addEditRemove = "add";
		
		$('#editQuestionId').val("");
		$('#editQuestionTopicId').val("");
		$('#questionTypeId option[value=""]').prop('selected', true);
		$('#questionDifficultyLevelId option[value=""]').prop('selected', true);
		$('#questionCognitiveLevelId option[value=""]').prop('selected', true);
		$('#quesDescription').empty();
		$('#editOptionId1').val("");
		$('#editOptionId2').val("");
		$('#editOptionId3').val("");
		$('#editOptionId4').val("");
		$('#optionDescription1').empty();
		$('#optionDescription2').empty();
		$('#optionDescription3').empty();
		$('#optionDescription4').empty();
		$('#questionImgId').val("");
		$('#optionImgId1').val("");
		$('#optionImgId2').val("");
		$('#optionImgId3').val("");
		$('#optionImgId4').val("");
		$('#quesImageDivId').empty().hide();
		$('#ansImageDivId1').empty().hide();
		$('#ansImageDivId2').empty().hide();
		$('#ansImageDivId3').empty().hide();
		$('#ansImageDivId4').empty().hide();
		$("input[name=isCorrectOption1][value=0]").prop('checked', true);
		$("input[name=isCorrectOption2][value=0]").prop('checked', true);
		$("input[name=isCorrectOption3][value=0]").prop('checked', true);
		$("input[name=isCorrectOption4][value=0]").prop('checked', true);
		
		$('#headerId').empty().html("Add");
	}
	
	function removeQuestion(questionTopicId)
	{
		if(confirm("Are you sure to delete this question?") == false)
			return false;
		
		addEditRemove = "remove";
		
		$('#toRemove').val("true");
		$('#editQuestionTopicId').val(questionTopicId);
		submitMessageForm();
	}
	
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
		$("#questionFormId").submit(function(e)
		{
			var formData = new FormData(this);
			var formAction = $(this).attr("action");
	
			$.ajax
			({
				url:formAction,
				type:"POST",
				data: formData,
				mimeType:"multipart/form-data",
   				contentType: false,
   				cache: false,
   				processData:false,
				success:function(data)
				{
					$("#fullPageloader").hide();
					if(data == "success")
					{
						$(".close").trigger('click');			// To hide the pop-up background
						
						if(addEditRemove == 'add')
							alert("Question uploaded successfully");
						if(addEditRemove == 'edit')
							alert("Question edited successfully");
						if(addEditRemove == 'remove')
							alert("Question removed successfully");
						
						getQuestionData();						// Reload question data table
					}
					else if(data =='used' && addEditRemove == 'edit' )
						alert("Cant modify. Question already in use");
					else if(data =='used' && addEditRemove == 'remove' )
						alert("Cant remove. Question already in use");
				},
				error:function()
				{
					
				}
			});
			e.preventDefault();				//Stop default action
			$(this).unbind("submit");		// Unbind to stop multiple form submit
		});
		$("#questionFormId").submit();		// Submit the form.				
	}

	function uploadQuestionExcelForm()
	{
		var selectedFile = $('#excelFileId').val();
		
		if(selectedFile == '')
		{
			alert("Please select file");
			return false;
		}
		
		var extension = selectedFile.substring(selectedFile.lastIndexOf(".")+1, selectedFile.length);
		if(extension != 'xlsx' && extension != 'xls')
		{
			alert("Please select excel format file");
			return false;
		}
		
		$("#questionUploadForm").submit(function(e)
		{
			var formData = new FormData(this);
			var formAction = $(this).attr("action");
	
			$.ajax
			({
				url:formAction+"?topicId=${globalVO.topicId}",
				type:"POST",
				data: formData,
				mimeType:"multipart/form-data",
   				contentType: false,
   				cache: false,
   				processData:false,
				success:function(data)
				{
					if(data=="success")
					{
						alert("Question uploaded successfully");
				    	$('.close').trigger('click');				// To hide the pop-up background
					}
					else
						alert(data);
				},
				error:function()
				{
					
				}
			});
			e.preventDefault();				//Stop default action
			$(this).unbind("submit");		// Unbind to stop multiple form submit
		});
		$("#questionUploadForm").submit();		// Submit the form.	
	}
</script>

<div style="width:90%;margin:0px auto;">
	<div>
		<button class="btn btn-success " data-toggle="modal" data-target="#myModal4" onclick="addQuestion()">Add New</button>
		<!-- <button class="btn btn-primary" data-toggle="modal" data-target="#myModal2">Edit</button> -->
		<!-- <button class="btn btn-danger" data-toggle="modal" data-target="#myModal3" onclick="removeTopic()">Remove</button> -->
		<button type="submit" class="btn btn-warning" data-toggle="modal" data-target="#uploadExcel" id="questionUpload">Import From Excel</button><br><br>
		
	
					<table id="dataTableId" class="display" style="width:100%">
						<thead>
							<tr class="dataTab">
								<th>Sr. No.</th>
								<th>Question</th>
								<th>Level</th>
								<th class="text-center">Question Image</th>
								<th> Author Name </th>
								<th>Action</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="quesTopic" items="${questionTopic}" varStatus="status">
								<tr>
									<td>${status.count}</td>
									<td>${quesTopic.question.description }
									<%-- Questions along with Answers	${quesTopic.question.answers} --%>

										<br> <br>
										<c:forEach var="ans"
											items="${quesTopic.question.answers}" varStatus="stats">
												<p style="float:left">${stats.count}. &nbsp;
											${ans.description} &nbsp; 
											<c:if test="${not empty ans.imagePath}">
											<img
												src="${commonPath}/questionImages/${ans.imagePath}" />
										</c:if></p>
											<br><br>

										</c:forEach> <!-- Answers are under working -->
									
									
									
									</td>
									<td>${quesTopic.question.difficultyLevel.name }</td>
									<td>
									   <c:if test="${not empty quesTopic.question.imagePath}"><img src="${commonPath}/questionImages/${quesTopic.question.imagePath}"/></c:if>
										<c:if test="${empty quesTopic.question.imagePath}">N/A</c:if>
									 </td>
									 <td>
								       ${quesTopic.question.user.firstName}
									 </td>
									<td>
									<c:if test="${not empty sessionScope.user && sessionScope.user.id == quesTopic.question.createdBy}">
										<a href="javascript:void(0)" data-toggle="modal" data-target="#myModal4" onclick="editQuestion('${quesTopic.question.id}')">Edit</a> / 
										<a href="javascript:void(0)" onclick="removeQuestion('${quesTopic.id}')">Remove</a>
									</c:if>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				
	</div>
		
	<!-- excelModal -->
	<div class="modal fade" id="uploadExcel" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">Upload from Excel</h4>
				</div>
	
				<div class="clearfix"></div>
				<div class="modal-body">
					<span id="studentExcelId"></span>
					<form id="questionUploadForm" method="post" action="${pageContext.request.contextPath}/sme/uploadQuestionExcel.html" enctype="multipart/form-data">
						<!-- File input -->
						<input name="file2" id="excelFileId" type="file" /><br />
						
						<a href="javascript:void(0)" onclick="uploadQuestionExcelForm()">Upload</a>
					</form>
					<div id="result"></div>
				</div>
			</div>
		</div>
	</div>
	<!-- excelModal -->
	
	<!-- Modal4 -->
	<div id="myModal4" class="modal fade" role="dialog">
	 <div class="eng-img img-responsive" id="engImg"></div>
		<div class="modal-dialog assign-name-pop" style="width:780px !important">
	
			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<div class="modal-title">
						<span id="headerId"></span> Questions
					</div>
				</div>
	
				<div class="modal-body assign-name-pop-mid panel-body2">
	
					<div class="col-md-12">
						<div class="row">
	
							<div class="edit-quest">
	
								<form:form action="${pageContext.request.contextPath}/sme/uploadQuestion.htm" method="POST" modelAttribute="globalVO" enctype="multipart/form-data" id="questionFormId">

								<div class="new-question">

									<!--  <div class="col-md-3 col-sm-3">
							            <select name="">
							            <option>Question Category</option>
							            <option>Text Based</option>
							            <option>Image Based</option>
							            <option>Video Based</option>
							            </select>
							            </div> -->

									<input type="hidden" name="questionId" id="editQuestionId"
										value="" /> <input type="hidden" name="optionId1"
										id="editOptionId1" value="" /> <input type="hidden"
										name="optionId2" id="editOptionId2" value="" /> <input
										type="hidden" name="optionId3" id="editOptionId3" value="" />
									<input type="hidden" name="optionId4" id="editOptionId4"
										value="" /> <input type="hidden" name="questionTopicId"
										id="editQuestionTopicId" value="" /> <input type="hidden"
										name="topicId" value="${globalVO.topicId}" />
									<form:hidden path="loginUserId" value="${user.id}" />
									<form:hidden path="questionCategoryId" value="2" />
									<!-- Right now we are using only question with image type questions. so providing static value -->

									<div class="col-md-4 col-sm-3">
										<select id="questionTypeId" name="questionTypeId"
											required="required">
											<option value="">Question Type</option>
											<c:forEach var="questionType"
												items="${globalVO.questionTypeList}">
												<option value="${questionType.id}">${questionType.name}</option>
											</c:forEach>
										</select>
									</div>

									<div class="col-md-4 col-sm-3">
										<select id="questionDifficultyLevelId"
											name="questionDifficultyLevelId" required="required">
											<option value="">Difficulty Level</option>
											<c:forEach var="questionDifficulty"
												items="${globalVO.questionDifficultyLevelList}">
												<option value="${questionDifficulty.id}">${questionDifficulty.name}</option>
											</c:forEach>
										</select>
									</div>

									<div class="col-md-4 col-sm-3">
										<select id="questionCognitiveLevelId"
											name="questionCognitiveLevelId" required="required">
											<option value="">Cognitive Level</option>
											<c:forEach var="questionCognitive"
												items="${globalVO.questionCognitiveLevelList}">
												<option value="${questionCognitive.id}">${questionCognitive.name}</option>
											</c:forEach>
										</select>
									</div>

								</div>
								<div class="clearfix"></div>

								<div class="col-md-6 col-sm-6 mrt20">
									<label>Question</label>
									<input type="checkbox" name="questionImgCheck" ><!--  -->Remove Image</input>
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
									<input type="checkbox" name="option1ImgCheck" ><!--  -->Remove Image</input>
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
									<input type="checkbox" name="option2ImgCheck" ><!--  -->Remove Image</input>
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
									<input type="checkbox" name="option3ImgCheck" ><!--  -->Remove Image</input>
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
									<input type="checkbox" name="option4ImgCheck" ><!--  -->Remove Image</input>
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
</div>