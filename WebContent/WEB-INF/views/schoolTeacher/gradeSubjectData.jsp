	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
	
	
	
	<script type="text/javascript">

	

	
		 /* $('#chapterId1').on("change",function(){
			 var s =$(this).val().split('+');
			 var chapid=s[0];
			 var subid=s[1];
			 var secId='${param.secId}';
			loadAssignTopicData(subid,chapid,secId);
			
			 

				
			 }); */
		 
		 function getAssignedTopicReport(grdId,subId,secId)
		 {
			 
			// alert("get assigned topic report"+grdId+" "+subId+" "+secId);
			 $.get("${pageContext.request.contextPath}/ajax/rest/getAssignedTopicReport/" + grdId  + "/" +subId+"/"+secId, function(data){
				 $("#tableData2").empty();
				// alert(data);
				  var val='';
				 val=val+'<table id="example2" class="display table-responsive" cellspacing="0" width="100%"><thead><tr><td>S.No.</td><td>Chapter Name </td><td>Assignment Topic</td><td>Assigned On</td><td>Students</td><td>Action</td></tr></thead>';
				 if(data!=null){
					
					 for(var i=0;i<data.length;i++){
						 var d=new Date(data[i].assignedOn);
			             val=val+'<tr><td>'+(i+1)+'</td><td>'+data[i].topic.chapter.name+'</td><td>'+data[i].topic.name+'</td><td>'+d.getDate()+'/'+(d.getMonth()+1)+'/'+d.getFullYear()+'</td><td><a href="javascript:void(0);" data-toggle="modal" data-target="#studentlist" >'+data[i].topic.count+'</a></td><td><a href="javascript:void(0);" onclick="unAssignTopicCls('+data[i].topic.id+','+data[i].topic.chapter.id +')">Unassign</a></td></tr>';
							 
				 }
					 
				 val=val+'</table>';
				 $("#tableData2").html(val);
				 $("#example2").dataTable({}); 
				  
				 } 
				 
			 });
		 }
		 
		 function getAssignedResourceReport(grdId,subId,secId){
			 
			// alert("get assigned resource report"+grdId+subId+secId);
			 
			 $.get("${pageContext.request.contextPath}/ajax/rest/getAssignResourceReport/" +grdId + "/" +subId + "/" + secId,function(data){
				 
				 var val='';
				 val=val+'<table id="example3" class="display table-responsive" cellspacing="0" width="100%"><thead><tr><td>S.No.</td><td>Chapter Name</td><td>Topic Name</td><td>Resource Name</td><td>Resource Type</td><td>Assigned On</td><td>Students</td><td> Action</td></tr></thead>';
	            if(data!=null)
	           	 	{
	            	
				for(var i=0; i<data.length ; i++)
				{    
					 var d=new Date(data[i].assignedOn);
					 val=val+'<tr><td>'+(i+1)+'</td><td>'+data[i].topic.chapter.name+'</td><td>'+data[i].topic.name+'</td><td>'+data[i].module.name+'</td><td>'+data[i].module.resourceType.name+'</td><td>'+d.getDate()+'/'+(d.getMonth()+1)+'/'+d.getFullYear()+'</td><td><a href="#" data-toggle="modal" data-target="#studentlist" >'+data[i].module.count+'</a></td><td><a href="javascript:void(0)" onclick="unassignModule('+data[i].module.id+',' +data[i].topic.id +')"> Unassign</a></td></tr>';
				}
	            	}	 
				 val=val+'</table>';
				 $("#tableData3").html(val);
				 $("#example3").dataTable({});
			});
			 
			 
			 
			 
		 }
			 
			 
			 
	
		/*  function loadAssignTopicData(subid,chapid,secId){
			 $("#tableData2").empty();
			 
				
			 $.get("${pageContext.request.contextPath}/ajax/rest/getAssignUnassignTopicDetails/" + subid  + "/" +chapid+"/"+secId, function(data){
				 var val='';
				 val=val+'<table id="example2" class="display table-responsive" cellspacing="0" width="100%"><thead><tr><td>S.No.</td><td>Assignment Topic</td><td>Assigned On</td><td>Students</td><td>Action</td></tr></thead>';
				 if(data!=null){
					
					 for(var i=0;i<data.length;i++){
						 var d=new Date(data[i].assignedOn);
					 val=val+'<tr><td>'+(i+1)+'</td><td>'+data[i].topic.name+'</td><td>'+d.getDate()+'/'+(d.getMonth()+1)+'/'+d.getFullYear()+'</td><td><a href="javascript:void(0);" data-toggle="modal" data-target="#studentlist" >'+data[i].topic.count+'</a></td><td><a href="javascript:void(0);" onclick="unAssignTopicCls('+data[i].topic.id+')">Unassign</a></td></tr>';
				 }
				 }
				 val=val+'</table>';
				 $("#tableData2").html(val);
				 $("#example2").dataTable({});
				 
				
				 
			 });
		 }
		  */
		
			
			function unAssignTopicCls(topicId,chapId){
				var flag=confirm("Are you sure for unassign topic from this section!!!");
				if(flag){
					
					var secId=${sectionId};
					var subId=${subjectId};
					var topId=topicId;
					var chapId=chapId;
					var grId=${idGrade};
					
				 $.post("${pageContext.request.contextPath}/ajax/rest/unAssignTopicFromSection/"  + secId  + "/" +topId ,function(data){
					getAssignedTopicReport(grId,subId,secId);
					alert("Topic unassign successfully !!!");
					
				 });
				}
			}
			
		  function unassignModule(moduleId,topicId){
				
				var flag=confirm("Are you sure for unassign module from this section!!!");
				if(flag){
					var secId=${sectionId};
					var subId=${subjectId};
					var grId=${idGrade};
					
				/* var topicId=$("#topicId1").val();
				var sectionId=$("#secIds").val();
				var subjectId=$("#subIds").val(); */
				 $.post("${pageContext.request.contextPath}/ajax/rest/unAssignModuleFromSection/"  + secId  + "/" +topicId+"/" +moduleId ,function(data){

					 //loadAllModuleData(subId,topicId,secId);
					 getAssignedResourceReport(grId,subId,secId);
					alert("Module unassign successfully !!!");
					
				 });
				}
			}
			
		  
		 
	$('#chapterId2').on("change",function(){
		
		 var s =$(this).val().split('+');
		 var chapid=s[0];
		 var subid=s[1];
		 	 $.post("${pageContext.request.contextPath}/ajax/rest/getTopics/"+chapid,function(data){
		
		    var val = '<option value="">Select Topic</option>';
		    if(data!=null && data.length>0){
		    for (var i = 0; i < data.length; i++)
		    {
			    val = val + '<option value="'+data[i].id+'">' + data[i].name + '</option>';
		    }
		    }
		    $("#topicId1").empty().append(val);
		
		
		});
				
	});
		//$('#example2').show();
		$("#topicId1").on("change", function(){
			var topicId=$(this).val();
			/* var chapterData=$('#chapterId2').val().split('+');
			var chapterId=chapterData[0]; */
			 var secId='${param.secId}';
			 var subjectId=$("#subIds").val();
			 
			 loadAllModuleData(subjectId,topicId,secId);
		
		  
		});


	/* function loadAllModuleData(subjectId,topicId,secId){
		 $.post("${pageContext.request.contextPath}/ajax/rest/getAssignUnassignModuleDetails/"  + subjectId  + "/" +topicId+"/"+secId ,function(data){
			 
			 var val='';
			 val=val+'<table id="example3" class="display table-responsive" cellspacing="0" width="100%"><thead><tr><td>S.No.</td><td>Resource Name</td><td>Resource Type</td><td>Assigned On</td><td>Students</td><td> Action</td></tr></thead>';
            if(data!=null)
           	 	{
            	
			for(var i=0; i<data.length ; i++)
			{    
				 var d=new Date(data[i].assignedOn);
				 val=val+'<tr><td>'+(i+1)+'</td><td>'+data[i].module.name+'</td><td>'+data[i].module.resourceType.name+'</td><td>'+d.getDate()+'/'+(d.getMonth()+1)+'/'+d.getFullYear()+'</td><td><a href="#" data-toggle="modal" data-target="#studentlist" >'+data[i].module.count+'</a></td><td><a href="javascript:void(0)" onclick="unAssignModuleCls('+data[i].module.id+')"> UnAssign</a></td></tr>';
			}
            	}	 
			 val=val+'</table>';
			 $("#tableData3").html(val);
			 $("#example3").dataTable({});
		});
	} */
	
	/* function unAssignModuleCls(moduleId){
		
		var flag=confirm("Are you sure for unassign module from this section!!!");
		if(flag){
		
		var topicId=$("#topicId1").val();
		var sectionId=$("#secIds").val();
		var subjectId=$("#subIds").val();
		 $.post("${pageContext.request.contextPath}/ajax/rest/unAssignModuleFromSection/"  + sectionId  + "/" +topicId+"/" +moduleId ,function(data){

			 loadAllModuleData(subjectId,topicId,sectionId);
			alert("Module unassign successfully !!!");
			
		 });
		}
	} */
	
	
	
	function changeStatus(id)
	{
		alert(" change status"+id);
		
		
	}
	
	function viewDetails(id,reportTyp,coun,gradeid,subId,secId){
		
		//alert("hiiii view report"+id+reportTyp+coun+gradeid+subId+secId);
		
		var id=id;
		var reportType=reportTyp;
		var count=coun;
		var gradeId=gradeid;
		var subjectId=subId;
		var sectionId=secId;
		
		 if(reportType=="chapterwise"){
				
				var url="${pageContext.request.contextPath}/schoolTeacher/getStudentwiseReport?gradeId=" + gradeId + "&sectionId=" + sectionId+ "&subjectId=" + subjectId+ "&reportType="+reportType+"&chapterId="+id+"&questionCount="+count;
			}
		 else{
				
				var url="${pageContext.request.contextPath}/schoolTeacher/getStudentwiseReport?gradeId=" + gradeId + "&sectionId=" + sectionId+ "&subjectId=" + subjectId+ "&reportType="+reportType+"&topicId="+id+"&questionCount="+count;	
			}
			$.post(url, function(data)
				    {
				
				$('#myModal').html(data);
				
				    }); 
		
	 }
		
		
			
		
			function getAssignmentReport(grdId,subId)
			{
				 $.get("${pageContext.request.contextPath}/ajax/rest/getAssignmentReport?gradeId=" + grdId  + "&subjectId=" + subId,function(data){
				 $('#assignment-report').html(data);
				  
				 });
			
			}
			

				    
	</script>
	
	
	<!-- learning Report content start here  -->	
				
			
			<div class="panel panel-default">
			<div class=" col-md-12" style="padding:0px">
			<ul class="nav nav-tabs dashTab">

               <li class="active"> <a data-toggle="tab"  href="#question-wise"><i class="fa fa-line-chart" aria-hidden="true"></i>Question Wise Report</a></li>
               <li onclick="getAssignmentReport('${idGrade}','${idSubject}')"><a data-toggle="tab" data-target="#assignment-report" href="#"><i class="fa fa-check-square" aria-hidden="true" ></i> Assignment Report</a></li>
			   <li onclick="getAssignedTopicReport('${idGrade}','${idSubject}','${sectionId}')"> <a data-toggle="tab" href="#" data-target="#assigned-topic"><i class="fa fa-check-square-o" aria-hidden="true"></i>Assigned Topic Report</a></li>
			   <li onclick="getAssignedResourceReport('${idGrade}','${idSubject}','${sectionId}')"> <a data-toggle="tab" href="#assigned-resource"><i class="fa fa-bar-chart" aria-hidden="true"></i>Assigned Resource Report</a></li>
			</ul>


			<div class="clearfix"></div>
			<!--tab-cont-->

			<div class="tab-content">

				<br />
				<!--question-wise-->
				<div id="question-wise" class="tab-pane fade in active">
					<div class="panel panel-default">
						<div class="panel-heading assign-res-bg">
							<h3 class="panel-title">
								<i class="fa fa-check-circle"></i> Question Wise
							</h3>
						</div>
					
		<div class="panel-body">
		<div class="panel-group" id="accordion">
		 <c:set var="chapCount" value="1" />
		<c:forEach var='chapter' items="${chapterlst}">
        <div class="panel panel-default">
            <div class="panel-heading" style="background-color:#c5c5c5;">
                <h4 class="panel-title">
   <a data-toggle="collapse" data-parent="#accordion_${chapCount}" href="#collapseOne_${chapCount}">
   <div class="col-md-5"  data-toggle="collapse" data-parent="#accordion_${chapCount}" href="#collapseOne_${chapCount}" style="padding-top:10px;">${chapCount}. ${chapter.name}</div> 
  <div class="col-md-3" style="padding-top:10px;">Question Count : ${chapter.count}</div></a>
  <div class=" col-md-3 pull-right"><a href="#" data-toggle="modal" data-target="#myModal" onclick="viewDetails('${chapter.id}','chapterwise','${chapter.count}','${chapter.gradeId}','${chapter.subjectId}','${sectionId}')" ><img src="${pageContext.request.contextPath}/resources/images/view-icon.png" alt=""> View Details</a></div>
                </h4>
            </div>
            
            <div id="collapseOne_${chapCount}" class="panel-collapse collapse <c:if test="${chapCount == 1}"> in </c:if>">
                <div class="panel-body">
                <c:forEach var='topic' items="${chapter.topics}">
                       <ul class="list-unstyled discussion">
   <li>
  <div  class="col-md-5" style="padding-top:10px;">${topic.name} </div>
  <div class="col-md-3" style="padding-top:10px;">Question Count :${topic.count}</div>
  <div class=" col-md-3 pull-right"> <a href="#" data-toggle="modal" data-target="#myModal"  onclick="viewDetails('${topic.id}','topicwise','${topic.count}','${chapter.gradeId}','${chapter.subjectId}','${sectionId}')" ><img src="${pageContext.request.contextPath}/resources/images/view-icon.png" alt=""> View Details</a></div>
  </li>
   
       
      </ul> 
       </c:forEach>
                </div>
            </div>
           
        </div>
         <c:set var="chapCount" value="${chapCount +1}" />
        </c:forEach>	
     
        </div>
    </div>
		
  
  </div>
  

				</div>
				
				<!-- assignment-report -->
				
				<div id="assignment-report" class="tab-pane fade">
				
				</div>
				
				<!-- <div id="assignment-report" class="tab-pane fade">
					<div class="panel panel-default" >
						<div class="panel-heading assign-res-bg">
							<h3 class="panel-title">
								<i class="fa fa-check-circle"></i> Assignment Report
							</h3>
						</div>
						<div class="panel-body">
							<div class="table-responsive" style="overflow: hidden;">
	<br>
	<div id=assignmentDiv>
	<table id="example" class="display" cellspacing="0" width="100%">
	<thead>
	<tr class="dataTab" style="font-size:15px;">
								<th>Sr. No.</th>
								<th>Assignment Name</th>
								<th>Chapter Covered</th>
								<th>Topic Covered</th>
								<th >Assigned On</th>
								<th> Students</th>
								<th>Action</th>
							</tr>
	</thead>
	<tbody>
	
	 <tr style=" font-size:14px;">
									<td>1</td>
									<td><a href="#" data-toggle="modal" data-target="#testpreview" >Practical Test 1</a></td>
									<td><a href="#" data-toggle="modal" data-target="#chaptername" >1 </a> </td>							
									<td><a href="#" data-toggle="modal" data-target="#topicname" >3 </a></td>
									<td>13-06-2016</td>
									<td><a href="#" data-toggle="modal" data-target="#studentlist" >34</td>
									<td><a href="#" data-toggle="modal" data-target="#myModal3" >Unassigned</a></td>
								 </tr>
								 <tr style=" font-size:14px;">
									<td>2</td>
									<td><a href="#" data-toggle="modal" data-target="#testpreview" >Practical Test 1</a></td>
									<td><a href="#" data-toggle="modal" data-target="#chaptername" >1 </a></td>							
									<td><a href="#" data-toggle="modal" data-target="#topicname" >3 </a></td>
									<td>13-06-2016</td>
									<td><a href="#" data-toggle="modal" data-target="#studentlist" >34</td>
									<td><a href="#" data-toggle="modal" data-target="#myModal3" >Unassigned</a></td>
								 </tr>
								 <tr style=" font-size:14px;">
									<td><a href="#" data-toggle="modal" data-target="#topicname" >3 </a></td>
									<td><a href="#" data-toggle="modal" data-target="#testpreview" >Practical Test 1</a></td>
									<td><a href="#" data-toggle="modal" data-target="#chaptername" >1 </a> </td>							
									<td><a href="#" data-toggle="modal" data-target="#topicname" >3 </a></td>
									<td>13-06-2016</td>
									<td><a href="#" data-toggle="modal" data-target="#studentlist" >34</td>
									<td><a href="#" data-toggle="modal" data-target="#myModal3" >Unassigned</a></td>
								 </tr>
	</tbody></table>
</div>
</div>
							<br> <br>
						</div>
					</div>

				</div> -->
				
				<!--assigned-topic-->

				<div id="assigned-topic" class="tab-pane fade" >

					<div class="panel panel-default">
						<div class="panel-heading assign-topic-bg">
							<h3 class="panel-title">
								<i class="fa fa-check-circle"></i> Assigned Topic Report
							</h3>
							<br>
							<div class="col-md-4 col-sm-4">
					<%-- Select Chapter <select id="chapterId1">
					<option value="">Select Chapter</option>
					<c:forEach var='chap' items="${chapterlst}">
						<option value="${chap.id}+${subjectId}">${chap.name}</option>
						</c:forEach>

					</select> --%>
				</div>
						</div>
						<div class="panel-body">

							<div class="table-responsive">
	<br>
	<div id="tableData2">
	<table id="example2" class="display table-responsive" cellspacing="0" width="100%">
	<thead>
	 <tr class="dataTab" style="font-size:15px;">
								 <th>Sr. No.</th>
								<th>Chapter Name </th>
								<th>Assignment Topic</th>
								<th >Assigned On</th>
								<th> Students</th>
								<th>Action</th> 
							</tr> 
	</thead>
	<tbody>
	
	</tbody>
	
	
	</table> </div>

</div>
						</div>
					</div>
				</div>
				
				<!-- assigned-resource -->
				
				<div id="assigned-resource" class="tab-pane" >

					<div class="panel panel-default">
						<div class="panel-heading assign-topic-bg">
							<h3 class="panel-title">
								<i class="fa fa-check-circle"></i> Assigned Resource Report
							</h3>
							<<%-- div class="col-md-4 col-sm-4">
					<br><b>Select Chapter</b> <select id="chapterId2">
					<option value="">Select Chapter</option>
					<c:forEach var='chap' items="${chapterlst}">
						<option value="${chap.id}+${subjectId}">${chap.name} </option>
						</c:forEach>
					
					
					

					</select></div> --%>
					<!-- <div class="col-md-4 col-sm-4">
					<br><b>Select Topic</b><select id="topicId1">
					<option value="">Select Topic</option>
					
 					 
					</select>
					
					
					
				</div> -->
							
							
							
							
						</div>
						<div class="panel-body">

							<div class="table-responsive">
	<br>
	<div id="tableData3">
	<table id="example3" class="display table-responsive" cellspacing="0" width="100%">
	<thead>
	<tr class="dataTab" style="font-size:15px;">
								<th>Sr. No.</th>
								<th>Chapter Name</th>
								<th>Topic Name </th>
								<th>Resource Name</th>
								<th>Resource Type</th>
								<th >Assigned On</th>
								<th> Students</th>
								<th>Action</th>
							</tr>
	</thead>
	<tbody>
	<!--  <tr style=" font-size:14px;">
									<td>1</td>
									<td>Method of Separation</td>
									<td>Video</td>
									<td>13-06-2016</td>
									<td><a href="#" data-toggle="modal" data-target="#studentlist" >34</td>
									<td><a href="#" data-toggle="modal" data-target="#myModal3" >Unassigned</a></td>
								 </tr>
								 <tr style=" font-size:14px;">
									<td>2</td>
									<td>Method of Separation</td>
									<td>Video</td>
									<td>13-06-2016</td>
									<td><a href="#" data-toggle="modal" data-target="#studentlist" >34</td>
									<td><a href="#" data-toggle="modal" data-target="#myModal3" >Unassigned</a></td>
								 </tr>
								 <tr style=" font-size:14px;">
									<td>3</td>
									<td>Method of Separation</td>
									<td>Video</td>
									<td>13-06-2016</td>
									<td><a href="#" data-toggle="modal" data-target="#studentlist" >34</td>
									<td><a href="#" data-toggle="modal" data-target="#myModal3" >Unassigned</a></td>
								 </tr> -->
	</tbody></table></div>

</div>
						</div>
					</div>
				</div>

			</div>
		</div>	
					</div>
					
					
<%-- 					
<div id="myModal3" class="modal fade" role="dialog">
	<div class="modal-dialog">

		<!-- Modal content-->
		<div class="modal-content">
			<form:form
				action="${pageContext.request.contextPath}/schoolTeacher/assignAssessment.htm"
				method="post" id="assignAssessmentForm" class="form-horizontal"
				role="form">
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

					<table border="0" cellspacing="0" cellpadding="0" class="topic-per table-striped table table-bordered table-hover table-condensed" style="overflow:scroll;width:90%;">
						<thead>
							<tr>
								<th><input type="checkbox" id="assignedAllId" />Select All</th>
								<th>Admission No</th>
								<th>Student Name</th>
								<th>Grade</th>
							</tr>
						</thead>
						<div class="row">
						<tbody>
						<td> <input type="checkbox"></td>
						<td>123456</td>
						<td>Rahul</td>
						<td>6</td>
						
						</tbody>
						</div>
					</table>


				</div>
				<div class="modal-footer">
					<div class="col-md-12 text-center ">
						<input type="submit" class="btn btn-primary btn-md"
							id="assignedAssesssmentId"  onclick="publishTest($('#assessmentId').val())" style="" value="Assign" />
					</div>


				</div>
			</form:form>
		</div>

	</div>
</div> --%>


<input type="hidden" id="secIds" value="${sectionId }">
<input type="hidden" id="subIds" value="${subjectId }">

<script type="text/javascript">
	$('table#example').dataTable({});
	$('table#example3').dataTable({});
	$("#example2").dataTable({});
	
	
	
</script>