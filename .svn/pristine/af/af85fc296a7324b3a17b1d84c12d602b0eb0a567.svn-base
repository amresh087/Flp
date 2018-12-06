
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<jsp:include page="globalPage.jsp" />

<h2 class="text-center">Subject  Details</h2>
<div style="width: 90% !important; margin: 0px auto;">
<button style="margin-bottom:10px;" class="btn btn-success " data-toggle="modal"
		data-target="#addSubject" id="createSubjcet" onclick="addSb.reset()">Add Subject
		</button>
	<button style="margin-bottom:10px;" class="btn btn-success " data-toggle="modal"
		data-target="#assignSTG" id="assignSubToGrade" onclick="assignSubjectToGrade.reset()">Assign Subjects To Section</button>
	<table id="subjectListId" class="display" style="display: none;">
		<thead>
			<tr class="dataTab">
				<th>S.No.</th>
				<th>Subject Name</th>
				<th>Subject Image</th>
				<!-- <th>Remove</th> -->
			</tr>
		</thead>
		<tbody>
			<c:if test="${not empty sList}">
				<c:forEach var="subjectList" items="${sList}" varStatus="status">
					<tr>
						<td>${status.count }</td>
						<td id="${subjectList.id}">${subjectList.name }</td>
							<td>
							
								<c:choose>
											<c:when test="${subjectList.subImage !=null}">
												<img
													src="${commonPath}/subjectImage/${subjectList.subImage}"
													class="img-responsive" title="Futuristic Learning"
													style="width: 50px;" />			
										</c:when>
										<c:otherwise>
										N/A
										</c:otherwise>
								
								</c:choose>
								
							
							</td>
						
					</tr>
				</c:forEach>
				</c:if>
				
		</tbody>
	</table>

</div>



<div class="modal fade" id="addSubject" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title" id="myModalLabel">Add Subject</h4>
			</div>
			<form:form  commandName="subject" method="post" id="addSb" action="${pageContext.request.contextPath}/schoolAdmin/addSubject.htm" enctype="multipart/form-data"
				class="form-horizontal" role="form">
				<div class="clearfix"></div>
				<div class="modal-body">
					<div class="col-md-12 col-sm-12 col-xs-12">
						<div class="row">
							<input type="text" name="sName" id="sName"
								placeholder="Enter Subject Name"   pattern="^(?=.*[a-zA-Z]).+" onkeydown="return alphaOnly(event)"/>
								Subject Image <input type="file" name="subjectImageNew" placeholder="Subject Image" id="updateSubjectImageId"/>
						</div>
					</div>

					<input type="hidden" name="userType" value="7">
					<div class="clearfix"></div>
					<input type="button" class="btn btn-primary btn-success" id="sId" value="Create">
				
				</div>
			</form:form>

			<div class="msg"></div>
			
		</div>

	</div>
</div>


<!--  -->
<div class="modal fade" id="assignSTG" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title" id="myModalLabel">Assign Subject To
					Section</h4>
			</div>

			<form:form
				action="#"
				commandName="subjectSection" method="post" id="assignSubjectToGrade"
				class="form-horizontal" role="form">
				<div class="modal-body">
					<div id="errorId" style="color: red;"></div>
					<div class="col-md-12 col-sm-12 col-xs-12">
						<div class="row">
							<select name="subId" id="subjectId" class="registration"
								required="required">
								<option value="">Select Subject</option>
								<c:if test="${not empty sList}">
									<c:forEach var="subject" items="${sList }">
										<option value="${subject.id}">${subject.getName() }</option>
									</c:forEach>
								</c:if>
							</select>
						</div>
						</div>
						<div class="col-md-12 col-sm-12 col-xs-12">
							<div class="row">
								<select name="gradeId" id="gradeId"
									onchange="getSectionByGradeId($(this).val(),'sectionChk')"
									class="registration" required="required">
									<option value="">Select Grade</option>
									<c:if test="${not empty gradeList}">
										<c:forEach var="grade" items="${gradeList }">
											<option value="${grade.id}">${grade.name }</option>
										</c:forEach>
									</c:if>
								</select>
							</div>
						</div>
						<br /> <br />
						<div class="col-md-12 col-sm-12 col-xs-12">
							<div class="row">

								<div id="sectionChk"> </div>
								
									<input type="button" class="btn btn-primary btn-success" id="assignSubAndGrade"
						value="Assign">
							</div>
						</div>
						
				
			</form:form>
			<div class="clearfix"></</div>
			<div class="assignMsg"></div>
		</div>
				
		</div>
			

</div>
.







<script type="text/javascript">
	$(document).ready(function()
    {
	    
		<c:if test="${not empty flag}">
		<c:choose>
		<c:when test="${flag eq 2}">
		alert("Subejct Created Successfully !!!");
		</c:when>
		<c:otherwise>
		alert("Subject Already Exists !!!");
		</c:otherwise>
		</c:choose>
		<c:remove var="flag" scope="session"/>
		</c:if>
		
	    $("#subjectListId").dataTable();
	    $("#subjectListId").show();
	    
	    $("#assignSubToGrade").on("click", function(){
			$("#sectionChk").empty();
			
		 });
	    $("#createSubjcet").on("click",function()
	    {
	    	 $('.msg').empty();
	    });
	    
    });
    
    $("#sId").on("click", function()
    {
	    $('.msg').empty();
	    $('.msg').show();
	    var sName = $('#sName').val().trim();
	    
	    if(sName == '' || sName==null  ){
	    	alert("Please enter subject name");
	    	return false;	
	    } 
	    else
	    	{
	    	$("#addSb").submit();
	    	}
	    
	   /*  $.ajax(
	    {
	        url : "${pageContext.request.contextPath}/schoolAdmin/addSubject.htm",
	        type : "POST",
	        data :
	        {
		        "sName" : sName
	        },
	        success : function(data)
	        {
		        if(data == '')
			        alert("Something went wrong");
		        else
		        {
			        if(data == "1")
			        {
				        $('.msg').append("<p style='color:red'>Subject Already Exist</p>").fadeOut(9000);
				        //	location.href="${pageContext.request.contextPath}/schoolAdmin/getSubjects.htm";
			        }
			        else
			        {
				        //alert(data);
				        $('.msg').append("<p style='color:green'>Subject Created Successfully</p>").fadeOut(9000);
				        location.href = "${pageContext.request.contextPath}/schoolAdmin/getAllSubjects.htm";
			        }
		        }
	        },
	        error : function()
	        {
		        alert("Something went wrong");
	        }
	    
	    }); */
	    
    });
   
    
    
    function getSectionByGradeId(gradeId,targetId)
	{
    	
			
	$('#'+targetId).empty();
var subjectId=$('#subjectId').val();

var gradeId = gradeId;


	$.post("${pageContext.request.contextPath}/ajax/rest/getSectionByGradeId1/"+gradeId+"/"+subjectId,function(data) 
	{
	console.log("in:-"+data["sectionIn"]);
	console.log("c"+data["sectionC"]);
	var sectionIn=data["sectionIn"];// comman section
	var sectionC=data["sectionC"];// all section
	if(sectionC.length==0 || sectionC==null)
		{
			$("#sectionChk").append("Subject already assigned");
		}
	for (var i = 0; i < sectionC.length; i++)
		
			{
		var flag=false;
		if(sectionIn!=null && sectionIn.length>0){
				for (var j = 0; j < sectionIn.length; j++) 
				{ 
					if(sectionC[i].id==sectionIn[j].id){
						
						flag=true;
						
					}
				} 
		}
				if(!flag){
				$('#'+targetId).append("<p><input type='checkbox' class= 'mappingCheckbox' name='"+sectionC[i].name+"' value='"+sectionC[i].id+"' />&nbsp;&nbsp;"+sectionC[i].name+"&nbsp;&nbsp;</p>");
			
				}
				}  
		


				
	}, "json");
}
    
    
    
    $("#assignSubAndGrade").on("click", function()
	{
		var subjectId=$("#subjectId").val();
		
		var gradeId=$("#gradeId").val();
		var status = false;
	    var g = parseFloat(0);
		
		console.log("subjectId" +subjectId+ "gardeId" +gradeId+ "sectionId" +sectionIdString);
		if(subjectId=='')
		{
			alert('Please Select Subject');
			 return false;
		}else if(gradeId==''){
			alert('Please Select Grade');
			 return false;
		}
    	
    	 $(".mappingCheckbox").each(function(e)
    			    {
    				    if($(this).prop('checked') == true)
    				    {
    					    status = true;
    				    }
    				    g++;
    			    });
    	 if(status == false && g > 0)
		    {
		    	alert("Select atleast one section");
			    return false;
		    }
		    		
		    else if(g == 0)
		    {
		    	alert("Subject already assigned");
			    return false;
		    }
    	
    	var sectionIdString = $('.mappingCheckbox:checked').map(function() 
								{
								    return this.value;
								}).get();
			
		
			 $.ajax
			({
				url:"${pageContext.request.contextPath}/schoolAdmin/assignSubjectToGrade.htm ",
				type:"POST",
				data: {"sectionIdString": sectionIdString.toString(),"subjectId": subjectId},
				success:function(data)
				{
					if(data =='success')
					{
						  $('.assignMsg').append("<p style='color:green'>Successfully Assign....</p>").fadeOut(9000);
					location.href="${pageContext.request.contextPath}/schoolAdmin/getAllSubjects.htm";
					}else{
						
					alert('please select any one checkbox');
				}
				},
				error:function()
				{
					alert("Something went wrong");
				}
			}); 
		
	});

    
</script>