
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
 <script type="text/javascript" src="https://cdn.datatables.net/r/bs-3.3.5/dt-1.10.8/datatables.min.js"> </script> 
<!-- <meta charset="utf-8"> -->

<div class="clearfix"></div>
 <c:choose>
 <c:when test="${not empty questionList}">
<div class="row">
<div class="col-md-12" style="background: rgb(246, 246, 246) none repeat scroll 0% 0%; padding: 10px; border: 1px solid rgb(124, 124, 124); font-size: 13px; border-radius: 8px; margin-left: 2%; width: 96%;">
<div class="col-md-3">Overall Total Questions : ${totalCount }</div>
<div class="col-md-2">Easy : ${easyCount}</div>
<div class="col-md-5">Intermediate : ${intermediateCount}</div>
<div class="col-md-2">Difficult : ${hardCount}</div>
</div>
</div>
</c:when>
</c:choose>

<div>

</div>
<div id="filterBox col-md-6">
	 <select class="searchChapBox " id="chapterBoxId"  style="width:40%">
	 <option selected="selected" value="">Select Chapter</option>

</select> 
		
<select class="searchTopBox " id="topicBoxId"  style="width:40%">
<option selected="selected" value="">Select Topic</option>
</select>


 <select class="searchImgBox " id="ImpBoxId"  style="width:40%">
<option selected="selected" value="">Select Images</option>
<option  value="Image  hai ">Image based</option>
<option  value="Image nhi hai ">Not image based</option>
</select>   


</div>


<div class="table-responsive" style="padding: 0px; overflow: hidden;">
<!-- <table class="table table-bordered table-hover" style="margin-top:10px;">

			<tr style="background: #36bdb4 none repeat scroll 0 0; color: #fff; font-weight:bold;">
			<td>Current Total Marks : 50</td>
	<td></td>
	<td></td>
	<td></td>
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
	
</table> -->
<div class="table-responsive"><table id="datatable" class="display dataTable table-responsive" cellspacing="0" width="100%">
	<thead>
			<tr class="dataTab">
			<th>S.No.</th>
			<th>Select<!-- <input type="checkbox" onchange="selectAllQuestion(this)"/> --></th>
			<th>Questions</th>
			<th>Cognitive level</th>
			<th>Image</th>
			<th>Marks</th>
			<th>Difficulty Level</th>
			<th>Created</th>
			<th style="display: none;"></th>
			<th style="display: none;"></th>
			<th style="display: none;"></th>
		</tr>
	</thead>


	<tbody>
		<c:if test="${not empty questionList }">
		<c:set var='count' value="1" />
		<c:forEach var='questionTopic' items="${questionList}" varStatus="1"
			step="1">
			<tr  id="tr_${questionTopic.question.id}">
				<td onclick="toggle(${questionTopic.question.id})">${count}</td>
				<td <%-- onclick="toggle(${question.id})" --%>><label for="check_${questionTopic.question.id}" style="width: 100%;height: 100%"><input name="" type="checkbox" value="${questionTopic.question.id}"
					class="qSelect" onchange="enable(this)" id="check_${questionTopic.question.id}"></label></td>
					<%-- <td onclick="toggle(${question.id})">
					<label for="check_${questionTopic.question.cognitiveLevel.id}" style="width: 100%;height: 100%">
					<input name="" type="checkbox" value="${questionTopic.question.cognitiveLevel.name}"
					class="qSelect" onchange="enable(this)" id="check_${questionTopic.question.cognitiveLevel.id}"></label></td> --%>
				<td onclick="toggle(${questionTopic.question.id})"><p style="float:left; font-weight:bold;">${questionTopic.question.description}</p>
				
					<br> <c:forEach var="ans"
											items="${questionTopic.question.answers}" varStatus="stats">
												<p style="float:left;">${stats.count}. &nbsp;
											${ans.description} &nbsp; 
											<c:if test="${not empty ans.imagePath}">
											<img src="${commonPath}/questionImages/${ans.imagePath}" />
										</c:if>
										
										
										</p><br></c:forEach>
				
				</td>
				<td onclick="toggle(${questionTopic.question.id})" id="cong_${questionTopic.question.id}" class="cong_${questionTopic.question.cognitiveLevel.id}">${questionTopic.question.cognitiveLevel.name}</td>
					
				<td onclick="toggle(${questionTopic.question.id})">
				<c:if test="${not empty questionTopic.question.imagePath}">
				<img src="${commonPath}/questionImages/${questionTopic.question.imagePath}" alt="No Image" />
				</c:if><c:if test="${empty questionTopic.question.imagePath}">N/A</c:if>
				
				</td>
				<td><input type="text" maxlength="2" style="width: 30px"
				class="marks" id='marks_${questionTopic.question.id}' disabled="disabled" onkeyup="setAnsweredArray(${questionTopic.question.id})"></td>
				<td onclick="toggle(${questionTopic.question.id})" class="diff_${questionTopic.question.difficultyLevel.id}" id="diff_${questionTopic.question.id}">${questionTopic.question.difficultyLevel.name}</td>
				<c:choose>
				
				<c:when test="${not empty questionTopic.question.user}">
					<td onclick="toggle(${questionTopic.question.id})">${questionTopic.question.user.firstName}</td>
					</c:when>
					<c:otherwise>
					<td onclick="toggle(${questionTopic.question.id})">FLP</td>
					</c:otherwise>
				</c:choose>
				<td style="display: none" >${questionTopic.topic.chapter.name} </td>
				<td style="display: none" >${questionTopic.topic.name} </td>
				
				<c:if test="${not empty questionTopic.question.imagePath}">
				<td style="display: none" >Image based</td>
				</c:if><c:if test="${empty questionTopic.question.imagePath}">
				<td style="display: none" >Not image based</td></c:if>
				
				
			</tr>
			<c:set var='count' value="${count+1 }" />
		</c:forEach>
		</c:if>
					
			
		
	</tbody>
</table></div>
<span style="display: none;" id="questContentId"></span>
<div/>
</div>
<div class="clearfix"></div>
<button type="button" class="btn btn-danger" onclick="showTab2()" id="getChpAndTop" >NEXT</button>

<script type="text/javascript">
var oTable;
$(document).ready(function(){
	
	  $('#fullPageloader').hide();
	  
	  <c:if test="${sessionScope.tab123 != null && not empty sessionScope.tab123}">
		$("#funkId").trigger("click");
		<c:remove var="tab123"  scope="session"/>
		</c:if>
		
		
		
	 oTable=$('table#datatable').DataTable( {
	 } );

	  
	  $.fn.DataTable.ext.search.push(
	 		    function( settings, data, dataIndex ) {
	 		    
	 		       
	 			   		var data1=$("#chapterBoxId option:selected").text();
	 			   		var data2=$("#topicBoxId option:selected").text();
	 			   	 var data3=$("#ImpBoxId option:selected").text();
	 			   	
	 			   	 
	 			 /*   	if(data1==""||data1=="Select Chapter")
 					{
 						return true;
 					}
 				else if(data2=="" || data2=="Select Topic"){
 							if(data1==data[8])
 								{
 						//		 console.log("value of chapter " +data1); 
 									return true;
 								}
 							
 						}else
 							{
 						
 							if(data1==data[8] && data2==data[9])
 							{
 							
 						
 							return true;
 						}
 						
 			    }  */
	 			   	  
	 			   	 
	 			     
	 				  if(data1==""||data1=="Select Chapter"&&(data2=="" || data2=="Select Topic")&&(data3=="" && data3=="Select Images"))
	 					{
	 						return true;
	 					}
	 				else if(data2=="" || data2=="Select Topic"||data3=="" && data3=="Select Images"){
	 							if(data1==data[8])
	 								{
	 								return true;
	 				
	 								}
	 							
	 						}  
	 			if(data1==data[8] && data2==data[9] && (data3=="" || data3=="Select Images"))
	 							{
	 							
	 							return true;
	 						}
	 			else if(data1==data[8] && data2==data[9] && data3==data[10])
	 				{
	 				
	 				return true;
	 				}
	 			
	 			if((data1==""||data1=="Select Chapter")&& (data2=="" || data2=="Select Topic") &&( data3=="" || data3=="Select Images"))
	 			{
		  			return true;
	 			}
	 			
	 				if((data1==""||data1=="Select Chapter")&& (data2=="" || data2=="Select Topic")&&( data3!="" || data3!="Select Images"))
	 					{
	 					if(data3==data[10]){
	 						return true;	
	 					}
	 					
	 					
	 					}
	 					
	 					
	 								   		return false;
	 		         
	 		       } 
	 		        
	 		
	 		);

	

	 
});

function newMarksCls(questId, obj){
	 var marks=obj.value;
	if (marks!='' && !isNaN(marks)) {
		array[questId]=obj.value;
		}
	
	
}

function closeQuestCls(questionId, countId, qId, dId){
	var r = confirm("Are you sure to delete this question from assessment!");
	if (r == true) {
		delete array[questionId];
		$("#questNewId"+countId).remove();
		 var count=parseInt($("#qCountIds").html())-1;
		 $("#qCountIds").html(count);
		 var counttaa=$("#"+qId+""+dId).html();
		 var totalCounta=$("#totalId_"+qId).html();
		 $("#"+qId+""+dId).html(counttaa-1);
		 $("#totalId_"+qId).html(totalCounta-1);
		 
	} 
}

function escapeHtml(unsafe) 
{
    return unsafe
          .replace(/&/g, "&amp;")
         .replace(/</g, "&lt;")
         .replace(/>/g, "&gt;") 
         .replace(/"/g, "&quot;")
         .replace(/'/g, "&#039;");
    //&#13;&#10;
 }


	var array={};
	var noArray=["1","2","3"];
	
	
	function enable(check) {
		 var tr=$('#tr_'+check.value);
		 var tr1= $('#tr_'+check.value).find('td').attr('class');
		 $('#marks_' + check.value).prop('disabled', false);
		 // adding value in chart
			var conId=$("#cong_"+check.value).attr('class').substr(5);
			 var diffId=$("#diff_"+check.value).attr('class').substr(5);
			 var countId=conId+diffId;
		
			//console.log(tr1);
			if (check.checked) {
				tr.addClass("selectedQuestionbg");
				//console.log(">>Selected question for value: "+check.value)
			
				// dynamic value add in chart	
				console.log($("#"+countId).text());
				var num=parseInt($("#"+countId).text())+1;
				$("#"+countId).html(num);
				//total count
				var total=parseInt($("#"+conId+1).text())+parseInt($("#"+conId+2).text())+parseInt($("#"+conId+3).text());
				$("#totalId_"+conId).html(total);
				
				
				
				array[check.value]=0;
			} else {
				tr.removeClass("selectedQuestionbg");
				for(id in array){
				}
		
				$('#marks_' + check.value).prop('disabled', true);
				$('#marks_' + check.value).val('');
				//delete un chart
				console.log($("#"+countId).text());
				var num=parseInt($("#"+countId).text())-1;
				$("#"+countId).html(num);
				// delete total
				var total=parseInt($("#"+conId+1).text())+parseInt($("#"+conId+2).text())+parseInt($("#"+conId+3).text());
				$("#totalId_"+conId).html(total);
				
				
				delete array[check.value];
				
			}
		}
	/* function enable(check) {
	 var tr=$('#tr_'+check.value);
		
		if (check.checked) {
			tr.addClass("selectedQuestionbg");
			//console.log(">>Selected question for value: "+check.value)
			
			$('#marks_' + check.value).prop('disabled', false);
			array[check.value]=0;
		} else {
			tr.removeClass("selectedQuestionbg");
			for(id in array){
			}
	
			$('#marks_' + check.value).prop('disabled', true);
			$('#marks_' + check.value).val('');
			delete array[check.value];
			
		}

	} */
 
	function validateNSubmit() {
		
		
		/* $.jStorage.set("testNamePre", $('#name').val());
    	$.jStorage.set("testStartDatePre", $('#startDate').val());
    	$.jStorage.set("testEndDatePre",$('#endDate').val());
    	$.jStorage.set("testInstructionsPre", $('#instruction').html());
    	$.jStorage.set("testTypePre", $("input[name=testType]:checked")
    			.attr('data'));
     */
		var name = $.jStorage.get("testNamePre");
		var startDate = $.jStorage.get("testStartDatePre");
		var endDate = $.jStorage.get("testEndDatePre");
		 var hh = $.jStorage.get("testHHPre");
		    var mm = $.jStorage.get("testMMPre");
			var instruction = $("#instruction").html();
		    duration = 0;
		    
		    if(hh != undefined && !isNaN(hh))
		    {
			    duration += Number(hh * 60);
		    }
		
		    if(mm != undefined && !isNaN(mm))
		    {
			    duration += Number(mm);
		    }
		
		var values = [];
		
		  var topicId = [];
			var chapterIds=[];
	
		var flag = true;
		
		
		$(".clsTopic:checkbox:checked").each(function()
			    { 
			topicId.push($(this).val());
			
			    });
	
	 $('.clsChapter:checkbox:checked').each(function(){
	    	
	    	chapterIds.push($(this).val());
	    	
	    	
	    });
		
	 
	// console.log("topics"+chapterIds.toString()+"chapter"+topicId);
			for(id in array){
			
			
			var marks = array[id];
			if (marks != '') {
				if (isNaN(marks)) {
					alert('Marks must be numaric');
					try{$('#marks_' + id).empty().focus();}
					catch(e){}
					flag = false;
					return false;
				}
			} else {
				alert('Please enter marks');
				try{$('#marks_' + id).empty().focus();}
				catch(e){}
				flag = false;
				return false;
			}

			values.push(id + "#" + marks);
		
			}if(studentIdsLst=="")
				{
				alert("Please Select Student");
				 return false;
				}
	
		
		if (flag) {
			if (confirm("Are you sure? You want to create Assessment!!!")){
			instruction = instruction.replace('<span id="qCountIds">','<span>');
			var obj = values.toString();
			 $('#fullPageloader').show();
			$
					.post(
							"${pageContext.request.contextPath}/ajax/schoolTeacher/createAssessment",
							{
								mappingList : obj,
								name : name,
								duration : duration,
								startDate : startDate,
								endDate : endDate,
								gradeId :$("#grdIdH").val(),
								subjectId :$("#subIdH").val(),
								instruction : instruction,
								sequence:sequence.toString(),
								testType : $("input[name=testType]:checked")
										.attr('data'),
										topicId:topicId.toString(),
										chapterIds:chapterIds.toString(),
										assStudentlst:studentIdsLst.toString(),
							    		secId:$("#secIdH").val()
										
							}, function(data) {
								 $('#fullPageloader').hide();
								alert('Test Created Successfully');
								location.reload();
							});
		}
		}
	}



	function validateAll() {
		var hh=parseInt($('#hh').val());
    	var mm=parseInt($('#mm').val());
			var d1=new Date($('#startDate').val());
			var d2=new Date($('#endDate').val())
		var flag = true;
		if ($('#selectGrade').val() == '') {
			alert('Please select grade');
			flag = false;
		} else if ($('#selectSubject').val() == '') {
			alert('Please select subject')
			flag = false;
		} else if ($("input[name=testType]:checked").attr('data') == undefined) {
			alert("Please select test type");
			flag = false;
		} else if ($('#name').val().trim() == '') {
			alert('Please enter test name');
			flag = false;
		} else if (!/^[a-z0-9_\- ]+$/i.test($('#name').val())) {
			alert('Please provide valid test name');
			flag = false;
		} else if (d1=='Invalid Date') {
			alert('Please provide test start date');
			flag = false;
		} else if($('#autoTestType option:selected').val()=='eTest'||$('#autoTestType option:selected').val()=='1'){
			
			
			if (d2=='Invalid Date') {
			alert('Please provide test End Date');
			flag = false;
		} else if (d1 >= d2) {
			alert('Start Date Time must be less then End Date Time');
			flag = false;
		}
		}
		else if(hh==''&& mm=='')
    	{
    		alert("Please select test duration !!");
    		flag = false; 
    	} 
		else if($('#hh').val()>24)
		{
		alert('Hours should be less then 24');
		flag = false;
		}
		
		else if($('#mm').val()>59)
			{
			alert('Minute should be less then 60');
			flag = false;
			}
			
		return flag;
	}

	function validateQuestion() {
		var flag = true;
		var values = [];
		
			for(id in array){
			//var marks = $('#marks_' + id).val().trim();
			var marks=array[id]
			if (marks != '') {
				if (isNaN(marks)) {
					alert('Marks must be numaric');
					try{$('#marks_' + id).empty().focus();}
					catch(e){}
					flag = false;
					return false;
				}else if(marks==0){
					alert("You can't assign 0 marks to any question");
					flag = false;
					return false;
					
				}
			}
			else {
				alert('Please enter marks for selected question(s)');
				try{
				$('#marks_' + id).empty().focus();}
				catch(e){}
				flag = false;
				return false;
			}
			
              //console.log("value pushed---"+id + "#" + marks);
			values.push(id + "#" + marks);

		}
			//console.log("length of value --"+values.length)
		if (flag && values.length == 0) {
			alert('Please Select atleast one question from list')
			flag = false;
		}
		return flag;

	}

	 function showPreQuestion(){
			$('#questionAnswerText').empty();
			var text = '';
		var qCount1=1;
			for(id in array) {
								try {
									<c:forEach var='questionTopic' items="${questionList}" varStatus="status" step="1">
									if (id == "${questionTopic.question.id}") {
										var des= htmlDecode('<c:out value="${questionTopic.question.description}" />');
										text+= '<div id="questNewId'+qCount1+'" class="preview-test-question-con seqnew" seqe1="${questionTopic.question.id}" topicId="${questionTopic.topic.id}" chapterId="${questionTopic.topic.chapter.id}" ><div class="preview-test-question"><div class="col-md-1 pull-right"><input type="text" value="'+Number(array[id])+'" onkeyup="newMarksCls(${questionTopic.question.id}, this);" style="width:40px;" maxlength="2"/><span class="pull-right" style="width:20px;margin-left:5px;"><a href="javascript:void(0)" onclick="closeQuestCls(${questionTopic.question.id},'+qCount1+',${questionTopic.cognitiveLevelId},${questionTopic.difficultyLevelId});" ><img src="${pageContext.request.contextPath}/resources/images/close.png" style="width:20px;"/></a>'+
										  '</span></div>';
										text+= +qCount1+'. '+des;
										if('${questionTopic.question.imagePath}'!= "null" && '${questionTopic.question.imagePath}'.length>0)
										text += '<br><img src="${commonPath}/questionImages/${questionTopic.question.imagePath}"	alt="" max-width="320px" max-height="320px" />';
										else
											text+='<br>';
										text+='</div>';
									<c:forEach var="answer" items="${questionTopic.question.answers}">
									var ans=htmlDecode('<c:out value="${answer.description}" />');
										text+= '<input type="radio" name="ans" value="1" readonly="readonly" />';
										text+=ans;
										if('${answer.imagePath}'!= "null" && '${answer.imagePath}'.length>0)
										text+='<br><img src="${commonPath}/questionImages/${answer.imagePath}"	alt="" /><br>';
										else
											text+='<br>';	
										</c:forEach>
										
										qCount1 = qCount1 +1;
									
									
									}
									
									</c:forEach>
									text += '</div>';
									$('#questionAnswerText').empty().append(text);
									

								} catch (e) {
									//console.log("an Error has occured" + e);
								}
								
						
			}
	 }
	
	 
	

		function htmlDecode(value){
		  return $('<div/>').html(value).text();
		}
	 
	function addQuestion() {
	     var qCount = 1;
			$('#questionAnswer').empty();
			var text = "";
			var easyCount=0,intermediateCnt=0,hardCnt=0;
			var ecp=0,icp=0,hcp=0;
			count=0;
			marks=0;
			for(id in array) {
								try {
									<c:forEach var='questionTopic' items="${questionList}" varStatus="status" step="1">
									//$("#questContentId");
									//var con= htmlEncode('<c:out value="${questionTopic.question.description}" />');
									var des= htmlDecode('<c:out value="${questionTopic.question.description}" />');
									//$("#questContentId").empty();
									//console.log(con);
									if (id == "${questionTopic.question.id}") {
										text+= '<div class="preview-test-question-con seq" seq="${questionTopic.question.id}" topicId="${questionTopic.topic.id}" chapterId="${questionTopic.topic.chapter.id}" ><div class="preview-test-question"><div><div class="col-md-12" style="padding: 10px; background: #e6e6e6; margin-bottom: 10px;"><div class="col-md-4"><strong>Difficulty Level</strong> : ${questionTopic.difficultyLevel.name}</div><div class="col-md-3 pull-right" style="text-align:right"><strong>Cognitive level</strong> : ${questionTopic.cognitiveLevel.name}</div></div><div class="pull-right btn btn-xs btn-warning">'+Number(array[id])+'</div> </div>';
										text+= +qCount+'.'+des;
										if('${questionTopic.question.imagePath}'!= "null" && '${questionTopic.question.imagePath}'.length>0)
										text += '<br><img src="${commonPath}/questionImages/${questionTopic.question.imagePath}"	alt="" max-width="320px" max-height="320px" />';
										else
											text+='<br>';
										text+='</div>';
									<c:forEach var="answer" items="${questionTopic.question.answers}">
									var ans=htmlDecode('<c:out value="${answer.description}" />');
										text+= '<input type="radio" name="ans" value="1" readonly="readonly" />';
										text+=ans;
										if('${answer.imagePath}'!= "null" && '${answer.imagePath}'.length>0)
										text+='<br><img src="${commonPath}/questionImages/${answer.imagePath}"	alt="" /><br>';
										else
											text+='<br>';	
										</c:forEach>
										qCount = qCount +1;
										count++;
										marks+=Number(array[id]);
										if(noArray[0] == "${questionTopic.difficultyLevelId}"){
											easyCount++;
											console.log("easy");
										}else if(noArray[1] == "${questionTopic.difficultyLevelId}"){
											console.log("inter");
											intermediateCnt++;
										}else if(noArray[2] == "${questionTopic.difficultyLevelId}"){
											hardCnt++;
										}
									}
									</c:forEach>
									text += '</div>';
									$('#questionAnswer').empty().append(text);
									

								} catch (e) {
									//console.log("an Error has occured" + e);
								}
								if(parseInt(count)>0){
									console.log("sdafsdafasdf"+intermediateCnt);
									console.log(easyCount);
									console.log(intermediateCnt);
									console.log(hardCnt);
									ecp=(easyCount*100)/count;
									icp=(intermediateCnt*100)/count;
									hcp=(hardCnt*100)/count;
									
									
								}
								$("#totalQuestionId").empty().html(count);
								$("#easyPercentId").empty().html(Math.round(ecp,2));
								$("#mediumPercentId").empty().html(Math.round(icp,2));
								$("#hardPercentage").empty().html(Math.round(hcp,2));	
						
			}
			
			//console.log('Marks: '+ marks+", count: "+ count);
		}


function setAnsweredArray(questionId)
{
	var marks1=$('#marks_' + questionId).val()
	if (!isNaN(marks1)) {
		
		array[questionId]=marks1;
		}
	
}
function genrateSeq(num){
	//console.log("called from "+ num);
	sequence=[];
	var index=0;
	$('.seq').each(function (){
		
		//console.log(">>>>"+$(this).attr('seq'));
		sequence[index++]=$(this).attr('seq');
	});
	
	
}


function againPreviewPage(){
	
	
	var testName=$("#testNameNew1").val();
	var startDate=$("#startDateNew1").val();
	var endDate=$("#endDateNew1").val(); 
	var hh1=$("#hhNew").val();
	var mm1=$("#mmNew").val();
	var d1=new Date(startDate);
	var d2=new Date(endDate);
  if (testName.trim() == '') {
		alert('Please enter test name');
		return false;
	} else if (!/^[a-z0-9_\- ]+$/i.test(testName)) {
		alert('Please provide valid test name');
		return false;
	} else if (d1=='Invalid Date') {
		alert('Please provide test start date');
		return false;
	} else if(parseInt($.jStorage.get("testTypePre"))==1){
		
		
		if (d2=='Invalid Date') {
		alert('Please provide test End Date');
		return false;
	} else if (d1 >= d2) {
		alert('Start Date Time must be less then End Date Time');
		return false;
	}
	}
	else if(hh1==''&& mm1=='')
	{
		alert("Please select test duration !!");
		return false;
	} 
	else if(parseInt(hh1)>24)
	{
	alert('Hours should be less then 24');
	return false;
	}
	
	else if(parseInt(mm1)>59)
		{
		alert('Minute should be less then 60');
		return false;
		}
	
	
	
	
	
	
	count=0;
	marks=0;
	for(item in array) {
		count++;
		marks+=Number(array[item]);
	    	
	 }  
	
	
	
	$("#showHH").html(hh1+" Hours ");
	$("#showMM").html(mm1+" Minutes");
	$("#totalQuestion").html(count);
	$("#totalMarks").html(marks);
	$("#modelInstruction").html($("#instruction").html());
	
	$.jStorage.set("testNamePre", testName);
	$.jStorage.set("testStartDatePre", startDate);
	$.jStorage.set("testEndDatePre",endDate);
	$.jStorage.set("testHHPre", hh1);
	$.jStorage.set("testMMPre", mm1);

	  addQuestion();
	  
	  sequence=[];
		var index=0;
		$('.seqnew').each(function (){
			
			//console.log(">>>>"+$(this).attr('seq'));
			sequence[index]=$(this).attr('seqe1');
			index++;
		});
		
		$("#dataChart2").html($("#dataChart").html());
	//var testType=("input[name=testTypeName]:checked").attr('data');
	 $("#previewScreenId").show();
	 $("#editTest123").hide();
}


function toggle(questionId)
{
	$('#check_'+questionId).trigger("click");
}

function selectAllQuestion(check){
	 if(check.checked == true)
	    {
		    $('.qSelect').not(':checked').each(function()
		    {
			    $(this).prop('checked','checked');
			    var id=$(this).val();
			    $("#tr_"+id).addClass("selectedQuestionbg");
			    $("#marks_"+id).removeAttr('disabled');
		    });
	    }
	    else if(!check.checked)
	    {
		    $('.qSelect').each(function()
		    {
			    $(this).removeAttr('checked');
			    var id=$(this).val();
			    $("#tr_"+id).removeClass("selectedQuestionbg");
			    $("#marks_"+id).prop('disabled','disabled');
			    $("#marks_"+id).val('');
		    });
	    }
}

// for searching 

 	    
 	   var topValues=[];
 	   var chpList=[];
 	   $(".clsTopic:checkbox:checked").each(function()
			    {
 		  topValues.push($(this).val())
				    
			    });


 $('.clsChapter:checkbox:checked').each(function()
		    {
 for (var i = 0; i < chapterList.length; i++)
    {
	    var topicList = chapterList[i].topics;
	    if(this.value == chapterList[i].id)
	    {
			console.log(chapterList[i].id);
			$(".searchChapBox").append(" <option value="+chapterList[i].id+">"+chapterList[i].name +"</option>");
			
			 
	    }
    }
		    });
 
 
 $('#chapterBoxId').on('change', function() {
	 oTable.draw();
	  $(".searchTopBox").empty().append("<option value=''>Select Topic</option>");
	  $(".searchImgBox").empty().append("<option value=''>Select Images</option> <option  value='Image  hai '>Image based</option>"+"<option  value='Image nhi hai '>Not image based</option>");
			  

	  
	
	  console.log("chapter id:-"+this.value ); 
	// or $(this).val()
		 //searchTopList=[];
	   
	  var id=this.value;
	  //var chapLeng=chapterList.length;
	  //var topLen=topValues.length;
	  for (var i = 0; i < chapterList.length;  i++)
	    { 
		    if(id==chapterList[i].id)
		    	{
		    	console.log("chapter id:-"+chapterList[i].id);
				
		    	 var topicList = chapterList[i].topics;
		    	 console.log("chapter id:-"+chapterList[i].topics);
				  for(k=0;k<topValues.length;k++)
					{
						for (var j = 0; j < topicList.length; j++)
				    	{
						if(topValues[k]==topicList[j].id)
							{
					//	searchTopList.push(topicList[j].id);
						  
						$(".searchTopBox").append(
								"<option value="+topicList[j].id+">"+topicList[j].name +"</option>");
						break;
							}
					    }					
						
					}
		    	}
	 
	    }
	  
	  //console.log(searchTopList);
	  oTable.draw();
	  
	});
	
 
 
 $('#topicBoxId').on('change', function() {
	 oTable.draw();
	 $(".searchImgBox").empty().append("<option value=''>Select Images</option> <option  value='Image  hai '>Image based</option>"+"<option  value='Image nhi hai '>Not image based</option>");
 });
 
 
 $("#getChpAndTop").on("click",function(){
 	
 	console.log("hekidisdhsdjasdlfjsdiofasdfnasdklsdfjioasdfjasodj");
	$(".getDetailsOfChapTp").empty();
 	var txt="";
	   for(var j=1;j<ListChptAndTopics.length;j++)
 	{
		   txt+="<table class='table table-bordered table-hover table-striped'><tbody><tr><td><Strong><span >"+ListChptAndTopics[j].ChpName+"</span></Strong><br>"+ListChptAndTopics[j].topicList.substr(1)+"</td></tr></tbody></table>";
		   
 	} 
 	$(".getDetailsOfChapTp").append(txt);
 	
 });
	 

     $('#ImpBoxId').on('change', function() {
	 oTable.draw();
 });  
	

	
	</script>
