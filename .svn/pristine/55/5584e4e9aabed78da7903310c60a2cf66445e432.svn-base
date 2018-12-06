
<jsp:include page="../parent/parentHeader.jsp" />
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>




<!-- middle panel -->
<div class="container-fluid bred-cont">
	<div class="container">
		<div class="row">

			<div class="col-lg-3 col-md-3 col-sm-4 col-xs-12 pull-right">
				<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 sel-sub">
					<div class="row">
						<label>Select Subject</label> <select id="subjectIds">
							<c:if test="${not empty subjectList }">
								<c:forEach var="s" items="${subjectList }">
									<c:choose>
										<c:when test="${s.id ne subjectId }">
											<option value="${s.id }">${s.name }</option>
										</c:when>
										<c:otherwise>
											<option value="${s.id }" selected="selected">${s.name }</option>
										</c:otherwise>
									</c:choose>
								</c:forEach>
							</c:if>
							<!-- <option value="English" selected>English</option>
<option value="English" >Math</option>
<option value="English" >Science</option>
<option value="English" >Hindi</option> -->
						</select>
					</div>
				</div>
			</div>


			<div class="col-lg-3 col-md-3 col-sm-4 col-xs-12 pull-right">
				<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 sel-sub">
					<div class="row">
						<label>Select Student</label> <select id="userIds">

							<c:if test="${not empty childrenList }">
								<c:forEach var="stu" items="${childrenList }">
									<c:choose>
										<c:when test="${stu.user1.id ne userId }">
											<option value="${stu.user1.id }">${stu.user1.firstName }</option>
										</c:when>
										<c:otherwise>
											<option value="${stu.user1.id }" selected="selected">${stu.user1.firstName }</option>
										</c:otherwise>

									</c:choose>
								</c:forEach>

							</c:if>
							<!-- <option value="Amit" selected>Amit</option>
<option value="Ragahv" >Ragahv</option>
<option value="Ruchi" >Ruchi</option> -->
						</select>
					</div>
				</div>


			</div>



		</div>
	</div>
</div>

<div id="showPerformanceDetails"></div>

<!-- middle panel end -->




<script type="text/javascript">
	$(document).ready(function()
    {
	    var userId = '${userId}';
	    var subjectId = '${subjectId}';
	    getData(userId, subjectId);
	    
    });
    
    function getData(userId, subjectId)
    {
	    $.post("${pageContext.request.contextPath}/parent/getPerformanceReport/" + userId + "/" + subjectId, function(data)
	    {
	    	
	    	  
		    $("#showPerformanceDetails").html('');
		    $("#showPerformanceDetails").html(data);
		    
		  
		    
	    });
    }

    $("#userIds").on("change", function()
    {
	    var userId = $(this).val();
	    $("#subjectDetailId").empty();
	    $("#subjectIds").empty();
	    $.post("${pageContext.request.contextPath}/parent/getSubjectDetailByUserId/" + userId, function(data)
	    {
	    	$("a#parentDashId").attr('href',"${pageContext.request.contextPath}/parent/parentDashboard.htm?studentId="+userId);
			  
	    	
		    if(data != null && data.length>0)
		    {
		    	var subjectId=data[0].id;
			    var val = '';
			    var val1='';
			    for (var i = 0; i < data.length; i++)
			    {
				    val = val + '<option value="'+data[i].id+'">' + data[i].name + '</option>'
				    val1=val1+'<li><a href="${pageContext.request.contextPath}/parent/studentPerformance.htm?userId='+userId+'&subjectId='+data[i].id+'"><i class="fa fa-book"></i> ' + data[i].name + '</a></li>';
			    }
			    $("#subjectIds").append(val);
			    $("ul#subjectDetailId").html(val1);
			    getData(userId,subjectId);
		    }else{
		    	
		    	$("#subjectIds").append('<option value="">Select Subject</option>');
		    }
	    });
	    
    });
    
    $("#subjectIds").on("change", function()
    {
	    
	    var userId1 = $("#userIds").val();
	    var subjectId1 = $(this).val();
	    getData(userId1, subjectId1);
    });
</script>






<jsp:include page="../student/studentFooter.jsp" />