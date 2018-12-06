<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<jsp:include page="../globalPage.jsp" />
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/common.js"></script>
<h2 class="text-center">Student Details</h2>

<div class="container">
<div class="col-md-12" style="margin: 0px auto; text-align: center">
	<div class="col-md-4">
	<select id="gradeId"
		onchange="setDropdownList('#sectionId', $(this).val(), '${pageContext.request.contextPath}/ajax/rest/getSectionByGradeId', 'Select Section', false)" name="grade">
		<option value="" selected="selected">Select Grade</option>
		<!-- <option value="">Select Grade</option> -->
		<c:if test="${not empty gradeList}">
			<c:forEach var="grade" items="${gradeList }">
				<option value="${grade.id}">${grade.name }</option>
			</c:forEach>
		</c:if>
	</select> 
	</div>
	
	<div class="col-md-4">
	<select id="sectionId" name="section">
		<option value="">Select Section</option>
		<c:if test="${not empty sectionList }">
		<c:forEach var="section" items="${sectionList}">
		<option value="${section.id }">${section.name}</option>
		</c:forEach>
		</c:if>
	</select>
	</div>
	
	<c:if test="${not empty section }">
	<input type="hidden" id="sectionIds1" value="${section }"/>
	<input type="hidden" id="gradeIds" value="${grade }"/>
	</c:if>
	<c:if test="${empty section }">
	<input type="hidden" id="sectionIds1" value="0"/>
	<input type="hidden" id="gradeIds" value="0"/>
	</c:if>
	<div style="float: right;">
	<a href="${pageContext.request.contextPath}/schoolAdmin/downloadExcel" target="_blank" class="btn btn-success "  >Download Template</a>
	<button class="btn btn-success " data-toggle="modal" onclick="uploadExcel.reset()"
			data-target="#uploadStudent" style=" margin-right: 10px !important;" id="excelUploadId">Upload from Excel</button>
	</div>
</div>
</div>
<br>
<div id="studentDetailsListId"></div>
<c:if test="${ empty excelMsg }"><input type="hidden" id="excelMsg" value="0"/></c:if>
<c:if test="${not empty excelMsg }"><input type="hidden" id="excelMsg" value="${excelMsg}"/><c:remove  var="excelMsg" scope="session"></c:remove></c:if>
<!-- Modal -->

	<div class="modal fade" id="uploadStudent" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" >
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">Upload from Excel</h4>
				</div>
					
							<div class="clearfix"></div>
							<div class="modal-body">
							<span id="studentExcelId" ></span>
							<form:form name ="uploadExcel" action="${pageContext.request.contextPath}/schoolAdmin/upload.htm" method="Post" enctype="multipart/form-data" commandName="user">
							<div class="col-md-12 col-sm-12 col-xs-12">
							<div class="row">
								<input type="file"  name="fileData" id="excelId"/><br>
							</div>
							<span id="errorId3" style="color: red;"></span>
						</div>
						
								
								<div class="clearfix"></div>
								
								<button type="submit" class="btn btn-primary btn-success" id="uploadId">Upload</button>
								</form:form>
								</div>
							
			</div>
			
			
			
		</div>
		
	</div>

<script type="text/javascript">
$(document).ready(function(){
	$('#gradeId option').prop('selected', function() {
        return this.defaultSelected;
    });
	
	var sectionIds=parseFloat($("#sectionIds1").val());
	var gradeIds=parseFloat($("#gradeIds").val());
	if(sectionIds>0 && gradeIds>0){ 
		 $("#gradeId").val(gradeIds);
		$("#sectionId").val(sectionIds);
		getStudentDetails(sectionIds); 
	}
	
	
	var excelMsg=$("#excelMsg").val();
	if(excelMsg!=0 || excelMsg!='0'){
		$("#studentExcelId").empty();
		$("#studentExcelId").html(excelMsg).fadeOut(10000);
		$("#excelUploadId").trigger('click');
	}
	$("#addViewStudent").addClass("active");
	$("#sectionId").on("change", function(){
		var sectionId=$(this).val();
		getStudentDetails(sectionId);
		
	});
	
	function getStudentDetails(sectionId){
		if(sectionId!=""){
			$("#fullPageloader").show();
			$.post("${pageContext.request.contextPath}/ajax/schoolAdmin/getStudentDetails/"+sectionId,function(data) {
				$("#studentDetailsListId").html(data);
				$("#studentDetailsListId").show();	
				$("#fullPageloader").hide();
				$("#buttonId").show();
				$("#sectionIds").val(sectionId);
				$("#sectionIds1").val(sectionId);
				$("#gradeIds1").val($("#gradeId").val());
				$("#hiddenSectionId").val(sectionId);
				$("#hiddenSectionId1").val(sectionId);
			});
			}else{
				$("#buttonId").hide();
				$("#sectionIds").val(0);
				$("#gradeIds1").val(0);
				$("#sectionIds1").val(0);
				$("#hiddenSectionId").val(0);
				$("#hiddenSectionId1").val(0);
				$("#fullPageloader").hide();
			}
	}
	
	$("#uploadId").on("click", function(){
		var file=$('#excelId').val();
		$("#errorId3").empty();
		if (!(/\.(xlsx|xls|xlsm)$/i).test(file)) {
		$("#errorId3").html('Please upload valid excel file .xlsx, .xlsm, .xls only.');
		return false;
		}
		
	});
	
$("#gradeId").on("change", function(){
	$("#buttonId").hide();
});
	
});
</script>