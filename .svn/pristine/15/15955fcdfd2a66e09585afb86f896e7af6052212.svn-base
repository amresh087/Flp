

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div id="auto-test-prie1"  style=" background :#f2f2f2; padding: 10px;"  class="tab-pane">
			<div id="timer" class="col-md-4 mrt15 ng-binding">
<strong>Test start date :</strong>
<span id="SdateAuto" class="ng-binding">

<br>
</span>
<br>
<strong>Test end date :</strong>
<span id="edateAuto123"></span>
<br>
<span id="time">
<strong>Time : </strong>
<span id="showHHAuto"></span>HH:
<span id="showMMAuto"></span>MM
</span>
</div>
<div id="testNameE" class="col-md-4 txt-cen">
<h3 class="ng-binding" class="" id="tName123"></h3>
<span id="modelGrade">${gradeName}</span>
<br>
<span id="modelSubject">${subName}</span>
</div>
<div id="testTypeE" class="col-md-4 txt-right mrt15 ng-binding">
<strong>Test Type :</strong>
<span id="tTypeA"></span>
<br>
<strong>Total Question :</strong>
<span id="totalQuestionAuto123" class="ng-pristine ng-untouched ng-valid" ></span>
<br>
<strong>Maximum Marks :</strong>
<span id="totalMarksAuto123"></span>
<br>
</div>
<div class="col-md-12 mrt15">
<div class="col-md-12 pull-left">
<h4 id="lblInst">General Instructions:</h4>
</div>

<pre id="modelInstruction123">

<%-- ${ins} --%>
<!-- 1. Students must not indulge in any unfair means while taking the test.
<br>
2. The duration of the test is 02 Hr 0 min. You will not be able to submit the test after the time is over.
<br>
3. The test contains only multiple choice questions.There are a total of
<span id="qCountIds">4</span>
MCQ in this test having one correct option.
<br>
4. Questions can be answered in any order.
<br>
5. Click the 'SUBMIT' button at the right hand corner of the screen to complete the test. Consequently, your test result will be displayed on the screen.
<br>
 --></pre>
<br>
<input type="hidden" id="subIdHidden" value="${subId}">
<input type="hidden" id="grdIdHidden" value="${gradeId}">
<input type="hidden" id="secIdHidden" value="${secId}">
<div id="autoQuestionAnswer" class="ui-sortable">

<div class="col-md-12">
<div class="col-md-3"></div>
</div>
			<div class="row" style="margin-top:20px; margin-bottom:20px;">
<div class="col-md-12" style="background: rgb(246, 246, 246) none repeat scroll 0% 0%; padding: 10px; border: 1px solid rgb(124, 124, 124); font-size: 13px; border-radius: 8px; margin-left: 1%; width: 98%;">
<div class="col-md-3">Overall Total Questions : ${totalCount}</div>
<div class="col-md-2">Easy : ${easyCount}%</div>
<div class="col-md-5">Intermediate : ${intermediateCount}%</div>
<div class="col-md-2">Difficult : <span>${hardCount}%</span></div>
</div>
</div>
<table class="table table-hover table-responsive">
	<thead>
			<tr>
			<th>S.No.</th>
			
			<th>Questions</th>
			<th>Cognitive level</th>
			
			<th>Marks</th>
			<th>Difficulty Level</th>
			
		</tr>
	</thead>


	<tbody>
		<c:if test="${not empty questionList }">
		<c:set var='count' value="1" />
		<c:forEach var='questionTopic' items="${questionList}" varStatus="1"
			step="1">
			<tr  id="tr_${questionTopic.question.id}">
				<td onclick="toggle(${questionTopic.question.id})">${count}</td>
				<td onclick="toggle(${questionTopic.question.id})"><p style="float:left; font-weight:bold;">${questionTopic.question.description}</p>
				
					<br> <c:forEach var="ans"
											items="${questionTopic.question.answers}" varStatus="stats">
												<p style="float:left;" kyabe="${ans.isCorrect}">${stats.count}. &nbsp;
											${ans.description} &nbsp; 
											
										
										</p><br></c:forEach>
				
				</td>
				<td onclick="toggle(${questionTopic.question.id})" id="cong_${questionTopic.question.id}" class="cong_${questionTopic.question.cognitiveLevel.id}">${questionTopic.question.cognitiveLevel.name}</td>
					
				<td>
				<c:choose>
					<c:when test="${questionTopic.question.difficultyLevel.id eq 1}">
					<span id="${questionTopic.question.id}#${eMarks}" class="question&Marks"></span>
					${eMarks}
					</c:when>
					<c:when test="${questionTopic.question.difficultyLevel.id eq 2}">
					<span></span>
					<span id="${questionTopic.question.id}#${iMarks}" class="question&Marks"></span>
						${iMarks}
						</c:when>
						<c:otherwise>
					<span></span>
					<span id="${questionTopic.question.id}#${dMarks}" class="question&Marks"></span>
						${dMarks}
						</c:otherwise>
				</c:choose>
				</td>
				
				<td onclick="toggle(${questionTopic.question.id})" class="diff_${questionTopic.question.difficultyLevel.id}" id="diff_${questionTopic.question.id}">${questionTopic.question.difficultyLevel.name}</td>
			
				
			</tr>
			<c:set var='count' value="${count+1 }" />
		</c:forEach>
		</c:if>
					
			
		
	</tbody>
</table>




<%-- <c:forEach var="aQuestionsList" items="${questionList}" varStatus="status" step="1">


<div class="preview-test-question-con seq" chapterid="893" topicid="2644" seq="7694">
<div class="preview-test-question">
<div>
<div class="col-md-12" style="padding: 10px; background: #e6e6e6; margin-bottom: 10px;">
<div class="col-md-4">
<strong >Difficulty Level</strong>
: ${aQuestionsList.difficultyLevel.name}
</div>
<div class="col-md-3 pull-right" style="text-align:right">
<strong >Cognitive level</strong>
: ${aQuestionsList.cognitiveLevel.name}
</div>
</div>
<div class="pull-right btn btn-xs btn-warning">5</div>
</div>
${aQuestionsList.question.description}
<br>
</div>

<c:forEach var="answer" items="${aQuestionsList.question.answers}">
<input type="radio" readonly="readonly" value="1" name="ans">${answer.description}
<br>
</c:forEach> 




<br><!-- 
<input type="radio" readonly="readonly" value="1" name="ans">
2.0
<br>
<input type="radio" readonly="readonly" value="1" name="ans">
3.0
<br>
<input type="radio" readonly="readonly" value="1" name="ans">
4.0
<br>
</div> --><!-- 
<div class="preview-test-question-con seq" chapterid="893" topicid="2644" seq="7695">
<div class="preview-test-question">
<div>
<div class="col-md-12" style="padding: 10px; background: #e6e6e6; margin-bottom: 10px;">
<div class="col-md-4">
<strong>Difficulty Level</strong>
: Easy
</div>
<div class="col-md-3 pull-right" style="text-align:right">
<strong>Cognitive level</strong>
: Knowledge
</div>
</div>
<div class="pull-right btn btn-xs btn-warning">5</div>
</div>
2.What is class ()..?
<br>
</div>
<input type="radio" readonly="readonly" value="1" name="ans">
1.0
<br>
<input type="radio" readonly="readonly" value="1" name="ans">
2.0
<br>
<input type="radio" readonly="readonly" value="1" name="ans">
3.0
<br>
<input type="radio" readonly="readonly" value="1" name="ans">
4.0
<br>
</div>
<div class="preview-test-question-con seq" chapterid="893" topicid="2644" seq="7696">
<div class="preview-test-question">
<div>
<div class="col-md-12" style="padding: 10px; background: #e6e6e6; margin-bottom: 10px;">
<div class="col-md-4">
<strong>Difficulty Level</strong>
: Easy
</div>
<div class="col-md-3 pull-right" style="text-align:right">
<strong>Cognitive level</strong>
: Knowledge
</div>
</div>
<div class="pull-right btn btn-xs btn-warning">5</div>
</div>
3.What is class ()..?
<br>
</div>
<input type="radio" readonly="readonly" value="1" name="ans">
1.0
<br>
<input type="radio" readonly="readonly" value="1" name="ans">
2.0
<br>
<input type="radio" readonly="readonly" value="1" name="ans">
3.0
<br>
<input type="radio" readonly="readonly" value="1" name="ans">
4.0
<br>
</div>
<div class="preview-test-question-con seq" chapterid="893" topicid="2644" seq="7701">
<div class="preview-test-question">
<div>
<div class="col-md-12" style="padding: 10px; background: #e6e6e6; margin-bottom: 10px;">
<div class="col-md-4">
<strong>Difficulty Level</strong>
: Easy
</div>
<div class="col-md-3 pull-right" style="text-align:right">
<strong>Cognitive level</strong>
: Knowledge
</div>
</div>
<div class="pull-right btn btn-xs btn-warning">5</div>
</div>
4.What is class ()..?
<br>
</div>
<input type="radio" readonly="readonly" value="1" name="ans">
1.033
<br>
<input type="radio" readonly="readonly" value="1" name="ans">
2.0
<br>
<input type="radio" readonly="readonly" value="1" name="ans">
3.0
<br>
<input type="radio" readonly="readonly" value="1" name="ans">
4.0
<br>
</div> -->
</c:forEach> --%>
</div>
</div>
<div class="mrt15 mrb15">
<div class="col-md-2">
<button type="button" class="btn btn-primary" onclick="showAuto()">Back</button>
</div>

<div class="col-md-2">
<button type="submit" class="btn btn-primary" id="btnGetQuestion" onclick="createAutoTest1()" >Create Test</button>
			</div>
<!-- <a id="open_98934957" class="initialism select-student_open btn btn-success" onclick="checkStudents()" href="#select-student" data-popup-ordinal="0">Student</a>
<button class="btn btn-danger" onclick="showTab4()" type="submit">Save Test</button> -->
</div>
</div>
<script type="text/javascript">


$(document).ready(function(){
	
	
	console.log($("#dateSession").val());
	
	$("#modelInstruction123").html($("#autoInstruction").html());
	console.log($("#autoInstruction").html());
	console.log(ListChptAndTopics);
	
	var nameA=$("#testAutoName").val();
	
	$("#tName123").html(nameA);
console.log(nameA);

if(parseInt($("#autoTestType").val())==1)
{
$("#tTypeA").html("E-Test");    	
$("span#edateAuto123").empty().append($("#endDateA").val());
}
else
{
$("#tTypeA").html("Assignment");
$("span#edateAuto123").empty().append($("#dateSession").val());

}
	
	
	var eQus=parseInt($("#easyQuestId").val());
	var mQus=parseInt($("#medQuestId").val());
	var dQus=parseInt($("#diffQuestId").val());
	var totalQus=eQus+mQus+dQus;
	$("#totalQuestionAuto123").html(totalQus);
	
	var marksE=parseInt($("#easyMarks").val());
	var marksInt=parseInt($("#interMarks").val());
	var marksDiff=parseInt($("#diffMarks").val());
	
	var marks=eQus*marksE+mQus*marksInt+dQus*marksDiff;
	
	$("#totalMarksAuto123").html(marks);
	
	$("#SdateAuto").html($("#startDateA").val());
	$("#edateAuto").html($("#endDateA").val());
	
	
	
	
	$("#showHHAuto").html($("#hh1").val());
	$("#showMMAuto").html($("#mm1").val());
		
		

});

var  questionAndMarks=[];
function createAutoTest1()
{
	$('#fullPageloader').show();
	questionAndMarks=[];
	values=[];
	chapterIds=[];
	
	var eQus=parseInt($("#easyQuestId").val());
	var mQus=parseInt($("#medQuestId").val());
	var dQus=parseInt($("#diffQuestId").val());
	var marksE=parseInt($("#easyMarks").val());
	var marksInt=parseInt($("#interMarks").val());
	var marksDiff=parseInt($("#diffMarks").val());
	
	var marks=eQus*marksE+mQus*marksInt+dQus*marksDiff;
		$("span[class='question&Marks']").each(function()
		    {
			
			questionAndMarks.push($(this).attr('id'))
			
		    }); 
		
		$(".clsTopic:checkbox:checked").each(function()
			    { values.push($(this).val());
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
 	    
 	   $('.clsChapter:checkbox:checked').each(function(){
	    	
	    	chapterIds.push($(this).val());
	    	
	    	
	    });
 	  
 	  console.log($("#modelInstruction123").text());
 	  var instruction=$("#modelInstruction123").text();
 	/*    
 	  var instruction=${ins}.toString(); */
 	 if (confirm("Are you sure? You want to create Assessment!!!")){
 		instruction = instruction.replace('<span id="quesNoAuto">','<span>');
 		
		$.post("${pageContext.request.contextPath}/schoolTeacher/createAutoTest",
	    		
	    		{
			result:questionAndMarks.toString(),
			testName:$("#testAutoName").val(),
			testType:$("#autoTestType").val(),
			startDate:$("#startDateA").val(),
			endDate:$("#endDateA").val(),
			totalMarks:marks,
			totalQus:$("#totalQuestionAuto123").text(),
			instruction:instruction,
				topicIds:values.toString(),
				duration:duration,
				chapterIds:chapterIds.toString(),
				assStudentlst:studentIdsLst.toString(),
				
				gradeId:$("#grdIdHidden").val(), 
				subjectId:$("#subIdHidden ").val(),
				secId:$("#secIdHidden").val(),
	    		},
	   
	    		function(data){
	    			
	    			if(data=="success"){
	    				
	    				alert("Test create successfully");
	    				$('#fullPageloader').hide();
	    				
	    		
	    			//location.reload();
	    			window.location.reload(true);
	    		//	alert("Test created successfully");
	    			
	    			}else{
	    				alert(data);
	    			}
	    		});  
 	 }
 	
 	 
	
}

function showAuto()
 {

    	console.log("adsfsdafasd");
    	$("#autoques").show();  
    	$("#autoQusetionList").empty().hide();
 }
	
</script>

			