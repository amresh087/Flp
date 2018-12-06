

<jsp:include page="../student/studentHeader.jsp" />
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %> 
<head> <meta http-equiv="Expires" CONTENT="0">
  <meta http-equiv="Cache-Control" CONTENT="no-cache">
  <meta http-equiv="Pragma" CONTENT="no-cache"></head>
<!-- middle panel -->
<div class="container-fluid bred-cont">
	<div class="container">
		<div class="row">
			<ul class="breadcrumb">
				<li>${assessment.subject.name}</li>
				<li>${assessment.name}</li>
			</ul>
		</div>
	</div>
</div>



  
<%--     	
<div class="container-fluid mrt20 question-con" id="instruction">
<div class="container">
<div class="row">
<h2> Test Instructions:</h2>
<ul class="list-unstyled">
${assessment.instruction}"
</ul>
   
<div class="btn btn-danger btn-md mrt20" onclick="startTest()">Start Test</div>

</div>
</div>
</div>
 --%>
<div class="container-fluid mrt20 question-con screen-ht" id="questionDetail">
	<div class="container">
		<div class="row">

			<div class="col-md-12 top-right-test">


				<!--timerr-start-here-->	
    	
    	<c:if test="${not empty assessment.time and assessment.time gt 0}">
    	<div class="col-md-3 col-sm-4 col-xs-12">
		<div class="timerr">        
        <h4 class="red">Time Duration</h4>
        <div class="col-md-12 col-sm-12 col-xs-12 ">
        
        <ul>        
        
        
        
        
        <li><input type="text" value="<fmt:formatNumber  value="${assessment.time / 60}" pattern="#" />" readonly="readonly" id='hh'/><label>HR</label></li>
        
        <li><input type="text" value="<fmt:formatNumber  value="${assessment.time mod 60}" pattern="#"  />" readonly="readonly" id='mm'><label>MIN</label></li>
        
        <li><input type="text" readonly="readonly" id='ss'/><label>SEC</label></li>
         </ul>
        </div>
        </div>

        </div>
        </c:if>
        <!--timerr-start-here-->	

		<div class="col-md-9 col-sm-8 col-xs-12">
        <div class="question-panelhead">Question Series</div>
     
        <ul class="list-unstyled list-inline question-panel">
     	<c:forEach var="aq" items="${assessment.userTestDetailList}" varStatus="i">
        <c:choose>
        <c:when test="${i.index eq 0}">
        <li id='lbl_${i.index}' class="skip-ques border-high" onclick="showQuestion(${i.index})" style="cursor: pointer;">${1+i.index}</li>
        </c:when>
        <c:otherwise>
        <li id='lbl_${i.index}' onclick="showQuestion(${i.index})" style="cursor: pointer;">${1+i.index}</li>
        </c:otherwise>
        
        </c:choose>
        </c:forEach>
        </ul>
       
        <div class="col-md-2 col-sm-3 col-xs-12 atempt"><img src="${pageContext.request.contextPath}/resources/images/color1.jpg" class="mrr10"/>Attempted</div>
        <div class="col-md-2 col-sm-9 col-xs-12 skip"><img src="${pageContext.request.contextPath}/resources/images/color2.jpg" class="mrr10"/>Attempt Later</div>
        <div class="col-md-2 col-sm-3 col-xs-12 current"><img src="${pageContext.request.contextPath}/resources/images/color3.jpg" class="mrr10"/>Present</div>
        <div class="col-md-3 col-sm-9 col-xs-12 unatempt"><img src="${pageContext.request.contextPath}/resources/images/color4.jpg" class="mrr10"/>Not yet attempted</div>
        </div>

			</div>

			
			<c:set var="size" value="${assessment.userTestDetailList.size()}" />
			<div class="col-md-12 col-sm-12 col-xs-12 ">
			<div class="row">
			<c:forEach var="aq" items="${assessment.userTestDetailList}"
				varStatus="i">
				<c:set var="q" value="${aq.question}" />

				<c:choose>
					<c:when test="${i.index eq 0}">
						<div class="test-ques" id="q_${i.index}">
					</c:when>
					<c:otherwise>
						<div class="test-ques" id="q_${i.index}" style="display: none;">
					</c:otherwise>
				</c:choose>

				<br>
				<h3>${i.index+1}) ${q.getDescription()} <div class="pull-right question-no" id="questionCount">
				
				<c:choose>
				<c:when test="${assessment.userTestDetailList.size() eq 0}">
				0/0
				</c:when>
				<c:otherwise>
					${i.count}/${assessment.userTestDetailList.size()}		
				</c:otherwise>
				</c:choose>
				
				
				
				</div></h3>
				<c:if test="${not empty q.imagePath}">
					<div class="col-md-12">
						<img src="${commonPath}/questionImages/${q.imagePath}" alt="" />
					</div>
				</c:if>
				<div class="col-md-12">
					<div class="row">
						<ul class="chutiya">
							<c:choose>
								<c:when test="${q.questionTypeId eq 16}">
									<c:forEach var="ans" items="${q.answers}">
										<li><input type="checkbox" id="ans_${ans.id}"
											name="ans_${q.id}" class="ans_${q.id}" value="${ans.id}" onclick="changeToAnswered(${i.index})"><label
											for="ans_${ans.id}">${ans.getDescription()}</label>
										<c:if test="${not empty ans.imagePath}">
					<div class="col-md-12">
						<img src="${commonPath}/questionImages/${ans.imagePath}" alt="" />
					</div>
				</c:if></li>
									</c:forEach>
								</c:when>
								<c:otherwise>
									<c:forEach var="ans" items="${q.answers}">
										<li><input type="radio" id="ans_${ans.id}"
											name="ans_${q.id}" class="ans_${q.id}" id="${ans.id}" onclick="changeToAnswered(${i.index})"><label
											for="ans_${ans.id}">${ans.getDescription()}</label>
											<c:if test="${not empty ans.imagePath}">
					<div class="col-md-12">
						<img src="${commonPath}/questionImages/${ans.imagePath}" alt="" />
					</div>
				</c:if></li>
									</c:forEach>
								</c:otherwise>
							</c:choose>

						</ul>
					</div>
				</div>
		</div>

		</c:forEach>
		</div>
</div>
		<c:if test="${assessment.userTestDetailList.size() gt 1}">
		<div class="col-md-1 col-sm-2 col-xs-4 mrt10 mrb10" id="prev"><a href="#" onclick="prev()"><div class="btn-info btn btn-md">
				Previous
			</div></a>
		</div>

		<div class="col-md-1 col-sm-1 col-xs-3 mrt10 mrb10" id="next">
		<a href="#" onclick="next()"><div class="btn-info btn btn-md">
				Next
			</div></a>
		</div>
		</c:if>
		<div class="col-md-1 col-sm-2 col-xs-4 mrt10 mrb10" id="submit" >
			<a href="#" onclick="confirmBeforeSubmit()" id="submit"><div class=" btn-warning btn btn-md">
				Submit
			</div></a>
		</div>

</div>
 <div class="clearfix"></div>
</div>
</div>
 <div class="clearfix"></div>
<!-- middle panel end -->
<jsp:include page="studentFooter.jsp" />


<script type="text/javascript">
	var index = 0, size = '${assessment.userTestDetailList.size()}';
	var submitFlag=true; 
	var previous=0;
	var timer;
	var hh='<fmt:formatNumber  value="${assessment.time / 60}" pattern="#"/>';
	var mm='<fmt:formatNumber  value="${(assessment.time mod 60)}" pattern="#"/>';
	var ss=00;
	var IsPostBack=true;
	$(document).ready(function(){
		startTest();
	});

	function startTest(){
		$('#questionDetail').show();
		<c:if test="${not empty assessment.time and assessment.time gt 0}">
		myTimer();
		$('#hh').val(hh >= 10 ? hh : "0"+hh.toString());
			$('#mm').val(mm >= 10 ? mm : "0"+mm.toString());
			$('#ss').val(ss >= 10 ? ss : "0"+ss.toString());
		</c:if>
		$('#prev').hide();
		setInterval(function() 
		{
			callAjax();
		}, (1000*60*10));
		console.log('test started')
	}
	
	function next() {
		if((1+index)<size)
		showQuestion(++index);
	}

	function prev() {
		if(index>0)
	showQuestion(--index)
	}

	function showQuestion(num)
	{
		if(num!=previous){
		if(num > -1 && num<size){
		if($('#lbl_'+num).hasClass('attempt-ques'))
			$('#lbl_'+num).addClass("border-high");
		else
			$('#lbl_'+num).addClass("skip-ques border-high");
			
		$('#q_' + num).show();
		$('#q_' + previous).hide();
		$('#lbl_'+previous).removeClass("border-high");
		
		index=num;
		previous=num;
		
		
		}
		}
		
		if(index==0){
			$('#prev').hide();
		}
		else
		{
			$('#prev').show();
		}
		
		if((num+1)==size)
			{
			$('#next').hide();
			}
		else
			{
		$('#next').show();
		}
	}
	
	function changeToAnswered(num){
		$('#lbl_'+num).removeClass("skip-ques");
		$('#lbl_'+num).addClass("attempt-ques");
		
	}
	
	
	function submitTest() {
		var qResult=[],count=0, status=0;//status=0 skipped, 1 correct, 2 incorrect
		var marks=0,correct=0,incorrect=0,unAttempted=0;
		
		
		<c:forEach var="aq" items="${assessment.userTestDetailList}" varStatus="i">
		 status=0;
			var isTrue=true,isAttempted=true, flag=false,ansString="";
			var isSkipped=false;
			<c:forEach var="ans" items="${aq.question.answers}" >
			
					if($('#ans_'+'${ans.id}').is(':checked'))
					{
						flag=true;
						ansString+='${ans.id}@';
					}
					else if($('#ans_'+'${ans.id}').not(':checked') && '${ans.isCorrect}'=='1')
						{
						isSkipped=true;		
						if(status==1)
						status=2;
						
						
						}
					
					if(flag){
					if($('#ans_'+'${ans.id}').is(':checked') && '${ans.isCorrect}'=='1')
									{
									console.log("status: "+ status+">"+(status!='2'))
									if(isSkipped==true)
										status=2;
									else if(status!='2')
										status=1;
									}
								else if(($('#ans_'+'${ans.id}').is(':checked') && '${ans.isCorrect}'=='0')||($('#ans_'+'${ans.id}').not(':checked') && '${ans.isCorrect}'=='1'))
									{
									status=2;
							}
					}
					
			</c:forEach>

			/* if(status==1)
				{
				qResult[count++]='${aq.question.id}'+'#'+status+"#"+ansString+"#"+'${aq.id}';
				}
			else */
				qResult[count++]='${aq.question.id}'+'#'+status+"#"+ansString+"#"+'${aq.id}';

				
				switch (status) {
				case 0:
					unAttempted++;
					break;
				case 1:
					//marks+=Number('${aq.marks}');
					correct++;
					break;
				case 2:
					incorrect++;
				break;
				default:
					break;
				}
				
			status=0;
			flag=false;
			ansString="";
			
		</c:forEach>
		
		if(submitFlag){
			submitFlag=false;
			$('#submit').attr('disabled','disabled');
		$
				.post(
						"${pageContext.request.contextPath}/user/saveTest",
						{
							//marks : marks,
							correct : correct,
							incorrect : incorrect,
							unAttempted:unAttempted,
							total:count,
							questionDetailList:qResult.toString(),
							subjectId: '${assessment.subjectId}',
							assessmentId:'${assessment.id}'
							
							///student/assessmentSummary.htm?assessmentId=16
							
						}, function(data) {
							
						//	alert('Test Submitted Successfully');
							clearInterval(timer);
							IsPostBack=false;
							location.href="${pageContext.request.contextPath}/user/getTestSummery.htm?testId=${assessment.id}";
							
						});
	}

	}
	


	function myTimer() 
	{
	    var sec = (1000);
	    timer = setInterval(function() 
	    { 
	 		if(ss=='0')
	 			{
	 			if(mm>'0')
	 			{	mm--;
	 				ss=59;
	 				
	 				$('#mm').val(mm >= 10 ? mm : "0"+mm.toString());
	 				$('#ss').val(ss >= 10 ? ss : "0"+ss.toString());
	 			}
	 			else if(hh>'0'){
	 			hh--;
	 			mm=59;
	 			ss=59;
	 			
	 			$('#hh').val(hh >= 10 ? hh : "0"+hh.toString());
	 			$('#mm').val(mm >= 10 ? mm : "0"+mm.toString());
 				$('#ss').val(ss >= 10 ? ss : "0"+ss.toString());
	 			}
	 			else
	 				{
	 				$('#ss').val(ss >= 10 ? ss : "0"+ss.toString());
	 				timer.cancel;
	 				clearInterval(timer);
	 			
	 				 alert("Time over!");
	 				submitTest();
	 				}
	 			
	 			}
	 		else
	 			{
	 			$('#ss').val(ss >= 10 ? ss : "0"+ss.toString());
	 			ss--;
	 			}
	    	
	    	
	    	
	    },(sec));
	}
	
	function callAjax()
	{
	    $.ajax(
	    {
	        url : "${pageContext.request.contextPath}/student/keepSessionAlive",
	        type : "post",
	        dataType:"text",
	        success : function(data) 
	        {
	        },
	        error : function() 
	        {
	        },
	    });
	}	 

	
	function confirmBeforeSubmit(){
		
		if(confirm("Are you sure you want to submit?")){
			
			submitTest()	;		
		}
	}
	
	
	
	</script>
