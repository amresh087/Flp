<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>



<script>



/* $('#search').keypress(function(ev){
    
	if(ev.which==13){
		var value=$('#search').val();
		//alert("key pressed"+ev.which +value);
		$.get("${pageContext.request.contextPath}/schoolTeacher/searchStudents?search="+value+"&grdId="+'${gradeId}'+"&subId="+'${subjectId}'+"&secId="+'${sectionId}'+"&grade="+'${grade}'+"&subject="+'${subject}'+"&section="+'${section}',function(data){
			if(data !=null)
				{
				$("#student-wise-report").html(data);	
				}
				
			
		
		});
		}
	    
	
}); 
 */




		



function viewDetails(studId)
{

	//alert("student id"+studId);
	$.get("${pageContext.request.contextPath}/schoolTeacher/searchStudentTestDetail?studentId="+studId+"&grdId="+'${gradeId}'+"&subId="+'${subjectId}'+"&secId="+'${sectionId}',function(data){
		//$("#student-wise-report").html(data);
		 if(data != null && data.length > 0)
			    {
			        $("#detail"+studId).empty();
			        
			         var val = '';
			         val=val+'<thead><tr><td><b>Test Name </td><td><b>Chapter Covered</td><td><b>Topic Covered</td><td><b>Taken</td><td><b>Total Score</td><td><b>View Summary</b></td></tr></thead>';
				     for (var i = 0; i < data.length; i++)
				    {
				    	var d=new Date(data[i].attemptDate);
				    	val = val + '<tr> <td>' + data[i].assessment.testName + '</td><td>' ;
				    	 if(parseInt(data[i].assessment.chapCount)>0){
				    		val=val+'<a href="javascript:void(0)" title="Chapter Details" data-toggle="popover" data-trigger="hover" data-content="'+data[i].assessment.chapterIds+'">'+data[i].assessment.chapCount+"</a>";
				    	}else{
				    		val=val+"N/A";
				    	}
				    	val=val+'</td><td>';
				    	if(parseInt(data[i].assessment.topicCount)>0){
				    		val=val+'<a href="javascript:void(0)" title="Topic Details" data-toggle="popover" data-trigger="hover" data-content="'+data[i].assessment.topicIds+'">'+data[i].assessment.topicCount+"</a>";
				    	}else{
				    		val=val+"N/A";
				    	} 
				    	val=val+ '</td> <td>'+d.getDate()+'/'+(d.getMonth()+1)+'/'+d.getFullYear()+ '</td><td>' + data[i].marks +'/'+ data[i].assessment.maxMarks +' (' + data[i].percentage + ' %)'+'</td><td></td></tr>';
				    } 
				    $("#detail"+studId).append(val);
				    $('[data-toggle="popover"]').popover();   
			    }
		 
		
		
		//$("#detail").append(data);
	  // alert("reached");
	});
	
	
	
}



</script>



 

<div class="panel panel-default">
						<div class="panel-heading assign-res-bg">
							<div class="col-md-6"> <label class="col-sm-4 col-lg-3 col-md-2 control-label ng-binding" for="txtFirstName">Student</label>
<div class="input-group col-sm-8 col-lg-6 col-md-6">
<!-- <input id="search" class="text-field " type="text"  placeholder="Search"> -->
</div> </div>
						</div>
						<div class="panel-body">
						<h3 class="student-wise-h">All Assessment</h3>
						<div class="student-wise">
						<c:forEach var="student" items="${userDetail}" varStatus="status">
						 <div class="panel-group" id="accordion_${student.id}">
    <div class="panel panel-default">
      <div class="panel-heading">
        <h4 class="panel-title">
        <table class="table table-bordered table-hover table-striped">
										<tr style="background:#eeeeee;">
										
										<div class="student-wise-detail">
										<div class="col-md-12">
										<div class="col-md-2">
										<c:choose>
  <c:when test="${student.profilePath!=null}">
    <img src="${commonPath}/profileImage/${student.profilePath}" alt="">
  </c:when>
 
  <c:otherwise>
      <img src="${pageContext.request.contextPath}/resources/images/activity-user-pic.png" alt="">
  </c:otherwise>
</c:choose>
										
  <%-- <img src="${pageContext.request.contextPath}/resources/images/activity-user-pic.png" alt=""> --%>
 </div>
 <div class="col-md-4">
 <p>
 Student Name :${student.firstName} ${student.lastName}<br>
<!--  Session : --> <!-- 2015- 2016 -->
 </p>
 </div>

  <div class="col-md-4">
  
 <p>
 Subject : ${subjectname}<br>
Class : ${gradename} - ${sectionname}

<%-- <input type="button" value="View Details" onclick="viewDetails('${student.id}')"/> --%>

</p>
 </div>
 <div class="pull-right">
 <a data-toggle="collapse" data-parent="#accordion_${student.id}" href="#collapse1_${student.id}">
 <button style="margin-top:10px;" class="btn btn-primary pull-right" onclick="viewDetails('${student.id}')">View Details</button></a></div>

										</div>
										</div>
										
										</tr></table>
          
        </h4>
      </div>
      <div id="collapse1_${student.id}" class="panel-collapse collapse  <%--  <c:if test="${status.count==1}"> in </c:if> --%>  ">
        <div class="panel-body"><div class="table-responsive">
							
										<table id="detail${student.id}" class="table table-bordered table-hover table-striped">
										
											<thead>
												<tr>

													<th>Test Name  </th>
													<th>Chapter Covered</th>
													<th>Topic Covered</th>
													<th>Taken</th>
												    <th>Total Score</th>
													<th>View Summary</th>
												</tr>
											</thead>
											<tbody>
												<%-- <c:forEach var="sa" items="${studentAssesments}"> --%>
												
													<%-- <tr>
 														<td>aaa </td>
														<td>Life Process</td>
														<td>Fission </td>
														<td><fmt:formatDate pattern="dd-MM-yyyy" value="${sa.endDate}"/>date </td>
														<td>10/50(40%)</td>
														<td ><img src="${pageContext.request.contextPath}/resources/images/view-icon.png" alt="" onclick="viewDetails('${sa.id}')"></td>
													</tr> --%>
													<%-- </c:forEach> --%>
													
													
                                                  
											</tbody>
										</table>
									
							</div></div>
      </div>
     
    </div>
   
    
  </div> 
   </c:forEach>
						
						</div>
							
							<br> <br>
						</div>
					</div>
					