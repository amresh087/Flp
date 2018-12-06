<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<jsp:include page="../globalPage.jsp" />

<h1>Create Assessment</h1>

<div style="width: 100%">
<select id='selectGrade' style="width: 25%;"  onchange="populateSubject(this.value)">
<option value='' selected="selected">Select Grade</option>
<c:forEach var="ts" items="${teacherSection}">
<option class='clsGrade' value='${ts.section.sessionSchoolGrade.grade.id}'>${ts.section.sessionSchoolGrade.grade.name}</option>
</c:forEach>
</select>

<select id='selectSubject' style="width: 25%;" onclick="getChapters(this.value)">
<option value=''>Select Subject</option>
</select>
<br>
<span>Select Chapters</span>
<div id="chapters"></div>

<span>Select Topics</span>
<div id="topics"></div>
<br>
<button onclick="getQuestion()" id="btnGetQuestion" hidden="hidden">Get Question</button>

</div>
<div id='questions'></div>

<script>
var chapterList, subjectId, gradeId;
$(document).ready(function () {

	$('.clsGrade').each(function(){
			var val=$(this);
			var flag=false;
			$('.clsGrade').each(function(){
	    	    if($(this).val()==val.val())
	    	    	if(!flag)
	    	    		flag=true;
	    	    	else
	    	    		$(this).remove();
	    	});
	});
});

function populateSubject(gradeId)
{
	console.log('Selected grade Id: '+ gradeId);
	if(gradeId!='')
		{
		$('#selectSubject').empty();
		$('#selectSubject').append("<option value=''>Select Subject</option>");
		<c:forEach var="ts" items="${teacherSection}">
			if('${ts.section.sessionSchoolGrade.grade.id}'==gradeId)
				{
				if(0==$('#selectSubject option[value="${ts.subject.id}"]').length)
				$('#selectSubject').append("<option value='${ts.subject.id}' class='clsSubject'>${ts.subject.name}</option>");
				}
		</c:forEach>
		}
}

function getChapters(subId){
	subjectId=subId;
	if(subjectId!=''){
		gradeId=$('#selectGrade').val();
		//console.log('get Chapters for subject: '+ subjectId+', grade: '+ gradeId);
		
		$.get("${pageContext.request.contextPath}/ajax/schoolTeacher/getChapters?subjectId="+subjectId+"&gradeId="+gradeId, function(data){
			chapterList=data;
			if(data.length > 0)
			{
				$('#chapters').empty();
				for(var i=0;i<data.length;i++)
				{
					chapterList=data;
					$('#chapters').append('<input type="checkbox" class="clsChapter" onchange="getTopics()" id="chapter_'+data[i].id+'" value="'+data[i].id+'"><label for="chapter_'+data[i].id+'" >'+data[i].name+'</label>');
				}
			}
			else{
				$('#chapters').empty().append(' No Chapter Found for selected grade subject');}
		});
	}
}

function getTopics()
{
	$('#topics').empty();
	if(chapterList!=undefined && chapterList.length>0)
		{
		$('#btnGetQuestion').show();
	$('.clsChapter:checkbox:checked').each(function(){
	console.log(this.value);
	for(var i=0;i<chapterList.length;i++)
	{
		var topicList=chapterList[i].topics;
		if(this.value==chapterList[i].id){
		for(var j=0;j<topicList.length;j++){
			
			$('#topics').append('<input type="checkbox" class="clsTopic" id="topic_'+
					topicList[j].id+'" value="'+topicList[j].id+'"><label for="topic_'+topicList[j].id+'" >'+topicList[j].name+'</label>');
		}
		}
	}
});
}
	else{
		$('#btnGetQuestion').hide();		
	}
}

function getQuestion(){
	var values=[]; 
	 $(".clsTopic:checkbox:checked").each(function(){
	    values.push($(this).val());
			});
	 
	 console.log(values+">>"+values.length);	
	 if(values.length>0)
		 $('#questions').empty().load("${pageContext.request.contextPath}/schoolTeacher/getQuestions?topicIds="+values.toString());
	 ///schoolTeacher/getQuestions
}
</script>