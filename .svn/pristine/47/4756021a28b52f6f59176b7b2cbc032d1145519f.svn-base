<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

	
	

					<div class="panel panel-default" >
						<div class="panel-heading assign-res-bg">
							<h3 class="panel-title">
								<i class="fa fa-check-circle"></i> Assignment Report
							</h3>
						</div>
						<div class="panel-body">
							<div class="table-responsive">
	<br>
	<div id=assignmentDiv>
	<table id="example" class="display" cellspacing="0" width="100%">
	<thead>
	<tr class="dataTab" style="font-size:15px;">
								<th>Sr. No.</th>
								<th>Assignment Name</th>
								<th>Chapter Covered</th>
								<th>Topic Covered</th>
								<th >Assignment Start Date</th>
								<th >Assignment End Date</th>
								<th> Students</th>
								<th>Action</th>
							</tr>
	</thead>
	<tbody>
	<c:forEach var="assessment" items="${assignmentLst}"
									varStatus="status">
	 <tr style=" font-size:14px;">
									<td>${status.count}</td>
									<td><span id="stestnameId${status.count }"><a href="#" data-toggle="modal"  data-target="#testpreview"  onclick="testPreview('${assessment.id}')" >${assessment.testName}</a><input type="hidden" id="testname${status.count}"  value="${assessment.testName}" dir="${assessment.id}"/></span></td>
									<td><c:choose><c:when test="${assessment.chapCount gt 0}"><a href="javascript:void(0);" title="Chapter Details" data-toggle="popover" data-trigger="hover" data-content="${assessment.chapterIds }"  >${assessment.chapCount}</a> </c:when><c:otherwise>N/A</c:otherwise></c:choose></td>							
									<td>
									<c:choose><c:when test="${assessment.topicCount gt 0}"><a href="javascript:void(0);" title="Topic Details" data-toggle="popover" data-trigger="hover" data-content="${assessment.topicIds }"  >${assessment.topicCount}</a> </c:when><c:otherwise>N/A</c:otherwise></c:choose>
									</td>
									<td><span id="sstartDateId${status.count }"><fmt:formatDate pattern="dd/MM/yyyy  " value="${assessment.startDate}" /><input type="hidden" id="testOn${status.count}" value="<fmt:formatDate pattern="dd/MMM/yyyy HH:mm " value="${assessment.startDate}" />" /></span></td>
									<td><span id="sendDateId${status.count }"><fmt:formatDate pattern="dd/MM/yyyy  " value="${assessment.endDate}" /> <input type="hidden" id="testOff${status.count}" value="<fmt:formatDate pattern="dd/MMM/yyyy HH:mm" value="${assessment.endDate}" />" /></span></td>
									<td><a href="javascript:void(0);" data-toggle="modal" data-target="#studentlist" id="studentDetailsId${assessment.id}" class="studentDetailsCls">${assessment.assignedAssessment.size() }</a></td>
									<td><c:choose>
									<c:when test="${(currentDate > assessment.startDate)}">
									N/A
									</c:when>
									<c:otherwise>
									<a href="javascript:void(0);" data-toggle="modal" data-target="#myModal3" id="studentDetailId${assessment.id}" class="unassignStudentDetailsCls" >Unassigned/</a><a href="javascript:void(0);" data-toggle="modal" data-target="#upcoming-edit" id="editAssignment" onclick="editAssmnt('${assessment.id}','${status.count}')"  >Edit
									</c:otherwise>
									</c:choose>
									</td>
								 </tr>
								 </c:forEach>
								 
	</tbody></table>
</div>
</div>
							<br> <br>
						</div>
					</div>
					
					
					<div id="chaptername" class="modal fade" role="dialog">
	<div class="modal-dialog">

		<!-- Modal content-->
		<div class="modal-content">
			<form action="" method="post" class="form-horizontal" role="form">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<div class="modal-title">
						<span>Select</span> Chapters
					</div>
				</div>
				<div class="modal-body">

					<!--  <div class="col-md-2 col-md-offset-1  mrt10 mrb10"><div class="row">Select All
  <input type="checkbox">
 </div></div> -->

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
</div>	

<div id="topicname" class="modal fade" role="dialog">
	<div class="modal-dialog">

		<!-- Modal content-->
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
</div>	



 <div  class="modal fade" role="dialog" id="testpreview">
	<div class="modal-dialog" style="width:70%;">

		<!-- Modal content -->
		<div class="modal-content" id="testpreview123">
		<!-- 	<form action="" method="post" class="form-horizontal" role="form">
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
David’s home is 12.53 miles away from the lake and 16.73 miles away from his school. How far is David’s school from the lake?</div>
			
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
			</form> -->
		</div>

	</div>
</div> 
<!--upcoming test edit  -->
<div id="upcoming-edit" class="modal fade" role="dialog">
	<div class="modal-dialog">

		<!-- Modal content-->
		<div class="modal-content">
			<form action="#" method="post" class="form-horizontal" role="form" id="updateAssessmentForm">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<div class="modal-title">
						<span><b>Edit Assignment</span> 
					</div>
				</div>
				<div class="modal-body">

					<!--  <div class="col-md-2 col-md-offset-1  mrt10 mrb10"><div class="row">Select All
  <input type="checkbox">
 </div></div> -->
 <div class="row">
 <div class="col-md-12">
 <input type="hidden" name="assessmentId" id="hiddAssessmentId"/>
<b>Test Name<input class="text-field " type="text" id="testNameIds"  placeholder="Enter Test Name"> </div>
</div>
 <div class="row">
	<div class="col-md-12">
<b>Test Start Date<input class="text-field " type="text" id="startDate1"  placeholder="Enter Test Start Date"/>
<input class="text-field " type="hidden" id="asmntId" >
</div>
</div>
 <div class="row">	
<div class="col-md-12">
<b>Test End Date<input class="text-field " type="text" id="endDate1"  placeholder="Enter Test End Date"/>
</div>
</div>
 <div class="row">				
<div class="col-md-3">
<br>
<span id="dateErr" style="color: red;"></span>
<button class="btn btn-primary" id="updateAssignment" type="button" onclick="submitAssignmentForm()">Update</button></div>
 </div>
<input type="hidden" id="hiddId"/>

				</div>
				</form>
		
		</div>

	</div>
</div>
<div id="myModal3" class="modal fade out" role="dialog">
	<div class="modal-dialog">

		<!-- Modal content-->
		<div class="modal-content">
			<form:form action="#" method="post" class="form-horizontal" role="form">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<div class="modal-title">
						<span>Status Unassigned</span> 
					</div>
				</div>
				<div class="modal-body">

					

					<table border="0" cellspacing="0" cellpadding="0" class="topic-per table-striped table table-bordered table-hover table-condensed" style="overflow:scroll;width:90%;">
						<thead>
							<tr>
								<th><input type="checkbox" id="select-all"  />Select All</th>
								<th>Admission No</th>
								<th>Student Name</th>
								<!-- <th>Grade</th> -->
							</tr>
						</thead>
						<div class="row">
						<tbody id="UnassignStuListId">
						
						</tbody>
						</div>
					</table>


				</div>
				<div class="modal-footer">
					<div class="col-md-12 text-center ">
					   <input type="hidden"  value="${assessment.id}"/>
						<input type="button" class="btn btn-primary btn-md"
							id="unassignAllUser"  value="Unassign" />
							
					</div>


				</div>
			</form:form>
		</div>

	</div>
</div>
	<!--Student list content Model  -->
<div id="studentlist" class="modal fade" role="dialog">
	<div class="modal-dialog">

		<!-- Modal content-->
		<div class="modal-content">
			<form action="" method="post" class="form-horizontal" role="form">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<div class="modal-title">
						<span>Student</span> Details
					</div>
				</div>
				<div class="modal-body">

					<table  border="0" cellspacing="0" cellpadding="0" class="topic-per table-striped table table-bordered table-hover table-condensed" style="overflow:scroll;width:90%;">
						<thead>
							<tr>
								<!-- <th><input type="checkbox" id="assignedAllId" />Select All</th> -->
								<th>Sr. No</th>
								<th>Student Name</th>
								
							</tr>
						</thead>
						<tbody id="stuListId"></tbody>
					
					</table>


				</div>
			
			</form>
		</div>

	</div>
</div>				
					
<script type="text/javascript">
$(document).ready(function(){
    $('[data-toggle="popover"]').popover();   
    $('table#example').dataTable();
    
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
	
	

	
	$(".studentDetailsCls").on("click", function(){
		$("#stuListId").empty();
		var assId=parseInt($(this).attr('id').substr(16));
		<c:if test="${not empty assignmentLst}">
		var data='';
		<c:forEach var="ass11" items="${assignmentLst}">
			if("${ass11.id}"==assId)
				{
					<c:forEach var="assignAss" items="${ass11.assignedAssessment}" varStatus="s"> 
					data=data+'<tr><td>${s.count}</td><td>${assignAss.user.firstName}</td></tr/>';
					</c:forEach>
				}
		</c:forEach>
		
		$("#stuListId").html(data);
		
		</c:if>
		
	});
	
	$(".unassignStudentDetailsCls").on("click",function(){
		//alert("current date"+${currentDate});
		var assId=parseInt($(this).attr('id').substr(15));
		<c:if test="${not empty assignmentLst}">
		var data='';
		<c:forEach var="ass11" items="${assignmentLst}">
		/* <c:if test="${ass.id eq assId}"> */
		if("${ass11.id}" == assId)
				{
		<c:forEach var="assignAss" items="${ass11.assignedAssessment}" varStatus="s"> 
		data=data+'<tr><td><input type="checkbox" id="checkedUser" name="unassignUser" value="${assignAss.user.id}"></td><td>${assignAss.user.admissionNo}</td><td>${assignAss.user.firstName}</td><input type="hidden" id="assId" value="${assignAss.assessmentId}"</tr/>';
		</c:forEach>
				}
	 	/* </c:if>  */
		</c:forEach>
		
		$("#UnassignStuListId").html(data);
		</c:if>
		
	});
	
	$("#select-all").on("click", function() {
		  var all = $(this);
		  $('input:checkbox').each(function() {
		       $(this).prop("checked", all.prop("checked"));
		      
		    
		  });
		});
	
	// for unassigning students in assignment (action) 
	        $("#unassignAllUser").click(function(){
	        	
	        	 var assmentId	=$("#assId").val();
                 var UnassignUserLst = [];

          $.each($("input[name='unassignUser']:checked"), function(){            
            	UnassignUserLst.push($(this).val());
            });
          
          if(UnassignUserLst != '')
	        	{
        	 
        	    var flag=confirm("Are you sure for unassign students !!!");
	    		if(flag){	
	    			 $(".close").trigger("click");
         $.get("${pageContext.request.contextPath}/ajax/rest/unassignStudentfromAssignment/" +UnassignUserLst +"/"+'${gId}'+"/"+'${sId}'+"/"+ assmentId , function(data){
        	 
        	 if(data==true)
        		 {
                	 getAssignmentReport('${gId}',+'${sId}');
               		 alert("Students Unassigned Successfully !!!");
               		 
               		
        		 }
        	
         });
         
	    		}
	        	}
          
          else
        	  alert("Please select student to unassign !!!");
	    		});
	

	 function testPreview(data)
	    {
		 var assessmentId = data;
		    $.get("${pageContext.request.contextPath}/schoolTeacher/assessmentPreview/" + assessmentId, function(data)
		    {  
		    	$('#testpreview123').html(data);
			   /*  $("div#assessmentPreview").html(data); */
		    });
	    }
	
	 
	 function editAssmnt(assmntId,count)
	 {
		// alert("assessment id is "+assmntId);
		 $("#testNameIds").val($("#testname"+count).val());
	        $("#startDate1").val($("#testOn"+count).val());
	        $("#endDate1").val($("#testOff"+count).val());
	        $("#asmntId").val(assmntId);
	       $("#hiddId").val(count);
	
	 }
	 
	
	 function submitAssignmentForm(){
			
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
			$("#stestnameId"+id).html(tstName);
			$("#sstartDateId"+id).html(strtDate);
			$("#sendDateId"+id).html(endDate);
			$(".close").trigger("click");
			
			

			}
			});
			
			}
		
		 }
	 
	 
	 
	 

</script>
