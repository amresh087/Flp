<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<jsp:include page="../student/studentHeader.jsp" />
 <link href="${pageContext.request.contextPath}/resources/css/custom-new.css" rel="stylesheet">
<!-- top header -->


<!-- middle panel -->
<div class="container">
<br /><br />



 <div class="tab-content">
 <h2>Create Your Own Test</h2>
		
        <!--SELECT-QUESTIONS-->
        <div id="select-ques" class="tab-pane fade in active" >
    	
        <div class="row">
        <div class="in-box">
        
        <div class="col-md-5">
         <select id="subject" onchange="getChapters(this.value)" required="required">
         <option value=""> Select Subject </option>
         <c:forEach var="subject" items="${subjects}">
         <option value="${subject.id}">${subject.name }</option>
         
         </c:forEach>
        </select>        
        </div>
        
        <div class="col-md-4"><input type="text" placeholder="Enter Test Name"  id="testName" required="required"/></div>
                
        <div class="col-md-1"><input type="text" placeholder="Hr:Min" id="time" pattern="([01]?[0-9]|2[0-3]):[0-5][0-9]" required="required"/></div>
        
        <div class="col-md-2"><input type="number" placeholder="Total Questions" id="total" pattern="" max= "100" min= "1" required="required" /></div>
        
        </div>

        <div class="clearfix"></div>
        	<div id="showchapTopic">
        <div class="col-md-6">
        
        <div class="chk">
        <label><strong>Select Chapters</strong></label>
        
       <input type="checkbox" id="main_checkbox" onchange="selectAll()" /><label>Select All</label>
        
        </div>
        
        <div class="row">
        <div class="col-md-12">
        
        	<div class="panel-body2 selct-chap">
            
            <div class="table-responsive">
                
                <table class="table table-bordered table-hover table-striped">
                	
                    <tbody id="chapters"></tbody>
                     
                     </table>
            </div>
            
            </div>
        
        
        </div>
        </div>
        
        </div>
        
        
        <div class="col-md-6">
        
        <div class=" chk">
        <label><strong>Select Topics</strong></label>
         <input type="checkbox" onclick="" id="main_Topic" /><label>Select All</label>
        </div>
        
        <div class="row">
        <div class="col-md-12">
        
        	<div class="panel-body2 selct-chap">
            
            <div class="table-responsive" id="creatTopic">
                
                <table class="table table-bordered table-hover table-striped">
                	
                    <tbody id="topics">
                  
                                            
                     </tbody>
                     
                     </table>
            </div>
            
            </div>
        	
            
        
        </div>
        
        
        
        </div>
        
        
        </div>
        
        </div>
        
        <div class="clearfix"></div>
        <br>
        
		</div>
        
        
 
        </div>
        <!--SELECT-QUESTIONS-->
        
        
        
        <div class="clearfix"></div>
        
        <button type="submit" onClick="createTest()" class="btn btn-danger">Create Test</button>

        
        <div class="clearfix"></div>
        
        
        <br><br>
        
        <div class="row">
        <div class="col-md-12 table-responsive" id="createtest-tab">
     
        <table id="example" class="display" cellspacing="0" width="100%">
    <thead>
        <tr class="dataTab">
            <th>Sr.</th>
            <th>Test Name</th>
            <th>Duration</th>
            <th>Total Questions</th>
            <th>Status</th>
        </tr>
    </thead>
 
   
    <tbody>
    
    <c:forEach var ="test" items="${tests}" varStatus="c">
    
        <tr>
            <td>${c.count}</td>
	        <td>${test.name }</td>
            <td><fmt:formatNumber  value="${test.time / 60}" pattern="#" />:<fmt:formatNumber  value="${test.time mod 60}" pattern="#"  /> Hrs
        </td>
            <td>${test.totalQuestion}</td>
            <c:choose>
            <c:when test="${test.status eq '0'}">
              <td><a href="${pageContext.request.contextPath}/user/attemptTest.htm?testId=${test.id}">Attempt Test</a></td>
              </c:when>
              <c:otherwise>
                <td><a href="${pageContext.request.contextPath}/user/getTestSummery.htm?testId=${test.id}">View Summery</a></td> 
                         </c:otherwise>
            </c:choose>
           
        </tr>
    
    </c:forEach>
      <!--   <tr>
            <td>1</td>
	        <td>Biology Test</td>
            <td>1:30Hrs</td>
            <td>55</td>
            <td>Attampted</td>
        </tr>
      
        <tr>
            <td>2</td>
	        <td>Physics Test</td>
            <td>1:30Hrs</td>
            <td>57</td>
            <td>Attampted</td>
        </tr>
        <tr>
            <td>3</td>
	        <td>Chemistry Test</td>
            <td>1:30Hrs</td>
            <td>75</td>
            <td>Unattampted</td>
        </tr>
        <tr>
            <td>4</td>
	        <td>English Test</td>
            <td>1:30Hrs</td>
            <td>75</td>
            <td>unattampted</td>
        </tr>
 -->        
    </tbody>
</table><br>
		
        </div>
        </div>
        
        
</div>


</div>
<!-- middle panel end -->    

  
<!-- footer panel --> 
<!-- footer panel end-->  

<script type="text/javascript">
var chapterList;
(function($){
			$(window).load(function(){
				
				
			
				$("#main_Topic").click(function (){
					
					$(".Topic_chk").prop('checked',$(this).prop("checked"));
				});
			 
				$(".panel-body2").mCustomScrollbar({
					setHeight:250,
					theme:"dark-3"
					
					
					
				});
				});
		})(jQuery);
		
function selectAll(){
	 
	 if($("#main_checkbox").prop('checked'))
		 {
   		 $(".clsChapter").prop("checked", 0);
   		$(".clsChapter").click();
			} else {
				$(".clsChapter").prop("checked", 0);
				$('#topics').empty();
		 }
	 
}

/* function selectAllT(){
	
}); 

	
} */
function getChapters(subId){
	
	//console.log('Selected Subject id is: '+ subId+", and grade id is: "+ '${sessionScope.user.gradeId}');
	
	
	
	$("#main_checkbox").removeAttr('checked');
	
	$('#topics').empty();
	if(subId!=''){
		gradeId='${sessionScope.user.gradeId}';
	//	console.log('get Chapters for subject: '+ subjectId+', grade: '+ gradeId);
		
		$.get("${pageContext.request.contextPath}/ajax/schoolTeacher/getChapters?subjectId="+subId+"&gradeId="+gradeId, function(data){
			chapterList=data;
			if(data.length > 0)
			{
				$('#chapters').empty();
				for(var i=0;i<data.length;i++)
				{
					chapterList=data;
					//$('#chapters').append('<input type="checkbox" class="clsChapter" onchange="getTopics()" id="chapter_'+data[i].id+'" value="'+data[i].id+'"><label for="chapter_'+data[i].id+'" >'+data[i].name+'</label>');
					$('#chapters').append('<tr> <td width="20"><input class="chd_chk clsChapter" onchange="getTopics()" type="checkbox" id="chapter_'+data[i].id+'" value="'+data[i].id+'"/><label for="chapter_'+data[i].id+'" >'+data[i].name+'</label></td></tr>');
				}
			}
			else{
				$('#chapters').empty().append(' No Chapter Found for selected grade subject');}
		});
	}
	
}
	

function getTopics()
{
	
	$("#main_Topic").removeAttr('checked');
	$('#topics').empty();
	
	
	/* 
	 $("#main_checkbox").each(function(e){
	if($(".clsChapter").prop('checked'))
	 {
		console.log("Hey");
		$("#main_checkbox").prop("checked", 1);
		} else {
			$("#main_checkbox").prop("checked", 0);
	 }
	 }); */
	
	 var flag=true;
	 $('.clsChapter').each(function(){
		 if(!$(this).prop('checked'))
			 {
			 flag=false;
			 return;
			 }
		});
	 if(flag)
		 $("#main_checkbox").prop("checked", 1);
	 else
		 $("#main_checkbox").prop("checked", 0);
	if(chapterList!=undefined && chapterList.length>0)
		{
		//$('#btnGetQuestion').show();
	$('.clsChapter:checkbox:checked').each(function(){
	console.log(this.value);
	
	for(var i=0;i<chapterList.length;i++)
	{
		console.log('found chapter ');
		var topicList=chapterList[i].topics;
		if(this.value==chapterList[i].id){
		for(var j=0;j<topicList.length;j++){
			$('#topics').append('<tr><td width="20"><input class="Topic_chk" onchange="checkValidate()" type="checkbox" class="clsTopic" '+
					topicList[j].id+'" value="'+topicList[j].id+'"/></td> <td><label for="topic_'+topicList[j].id+'">'+topicList[j].name+'</label></td></tr>');
		}
		}
	}
});
}
	else{
		console.log('Nothing found');	
	}
}

function checkValidate()
{

	 var flag=true;
	 $('.Topic_chk').each(function(){
		 if(!$(this).prop('checked'))
			 {
			 flag=false;
			 return;
			 }
		});
	 if(flag)
		 $("#main_Topic").prop("checked", 1);
	 else
		 $("#main_Topic").prop("checked", 0);
	
	}

function createTest(){
	var name=$('#testName').val();
	var subId=$('#subject').val();
	var time=$('#time').val();
	var total=$('#total').val();
	var topicIds=[];

	name=name.trim();
	
	

	
	
	if(subId=="")
		{
			alert("Please Select Subject");
			return false;
		}
	else if(name=="")
		{
		 	alert("Please Enter Test Name");
		 	return false;
		}
	else if(time=="")
		{
		 	alert("please Enter Time");
		 	return false;
		}

    var regex = /^([0-9]|0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]$/;
    console.log(time);
    console.log(regex.test(time));
    	if(!regex.test(time))
    		{
    			alert("Please enter valid time HH:MM");
    			return false;
    		}
	else if(total=="")
		{
			alert("Please Enter Question ");
			return false;
		}
	
	
	var m=parseInt(total);
	console.log(m);
		    if(m>101 || m<=0){
		    	alert("Please enter the value between 0 to 100");
		    }
		    

		    
		    
	 var status = false;
	  
	    $(".clsChapter").each(function(e)
	    {
		    if($(this).prop('checked') == true)
		    {
			    status = true;
			    
		    }
		
	    });
	    if(status == false)
	    {
		 alert("Please select atleast one chapter");
		    return false;
	    }

	    
	
	

		 var status1 = false;
		   
		    $(".Topic_chk").each(function(e)
		    {
			    if($(this).prop('checked') == true)
			    {
				    status1 = true;
				    
			    }
			
		    });
		    if(status1 == false )
		    {
			 alert("Please select atleast one topic");
			    return false;
		    }
	

		    
		/*     var re = /^\s*([01]?\d|2[0-3]):?([0-5]\d)\s*$/;
		    console.log(time.match(re));
		    if(time.match(re))
		    	{
		    		alert("true");
		    	
		    	
		    	} */
		    
		    
		    
	 $(".Topic_chk:checkbox:checked").each(function(){
		 topicIds.push($(this).val());
			});
	 
	 console.log("testName: "+ name+", subId: "+ subId+", time: "+ time+", no of question: "+total+", topic Ids: "+ topicIds);
   $.post("${pageContext.request.contextPath}/user/createTest",{
		 name:name,
		 subId:subId,
		 time:time,
		 total:total,
		 topicIds: topicIds.toString()
		 
	 }, function(data, status){
	       // alert("Data: " + data + "\nStatus: " + status);
	        
	        if(data=="success")
	        	{
	        	 alert("Test successfully created");
	        		location.href="${pageContext.request.contextPath}/user/getCreateTestPage.htm";
	        	}
	        	
	        
	    });
 
}

</script>


<jsp:include page="studentFooter.jsp" />