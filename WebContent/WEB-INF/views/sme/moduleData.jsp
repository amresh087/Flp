<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<script>
	var addEditRemove;

	$(document).ready(function()
	{
		//$('#reportTableID').dataTable().fnDestroy();			// Use it when you want to reinitialize data table
		$('#dataTableId').dataTable();
	});
	
	function editModule(moduleId)
	{
		addEditRemove = "edit";
		
		<c:forEach var="moduleTopic" items="${moduleTopicList}"	varStatus="status">;
			if('${moduleTopic.moduleId}' == moduleId)
			{
				$('#editModuleId').val(moduleId);
				$('#editModuleTopicId').val('${moduleTopic.id}');
				$('#moduleName').val("<c:out value='${moduleTopic.module.name}' />");
				$('#moduleDescription').val("<c:out value='${moduleTopic.module.description}' />");
				$('#resourceTypeId option[value=${moduleTopic.module.resourceTypeId}]').prop('selected', true);
				$('#gradeTypeId option[value=${moduleTopic.module.gradeTypeId}]').prop('selected', true);
			}
		</c:forEach>;
		
		$('#headerId').empty().html("Edit");
	}
	
	function addModule()
	{
		addEditRemove = "add";
		
		$('#editModuleId').val("");
		$('#editModuleTopicId').val("");
		$('#moduleFile').val("");
		$('#moduleName').val("");
		$('#moduleDescription').val("");
		$('#resourceTypeId option:first-child').prop('selected', true);
		$('#gradeTypeId option:first-child').prop('selected', true);
		
		$('#headerId').empty().html("Add");
	}
	
	function removeModule(moduleTopicId)
	{
		if(confirm("Are you sure to delete this module?") == false)
			return false;
		
		addEditRemove = "remove";
		
		$('#toRemove').val("true");
		$('#editModuleTopicId').val(moduleTopicId);
		
		submitMessageForm();
	}
	
	$("#addModuleId").on("click", function()
    {
	    var file = $('#moduleFile').val();
	    
	    if(file == '' && addEditRemove == "add")
	    {
	    	alert("Please select module to upload");
	    	return false;
	    }
	    
	    var exension = file.substring((file.lastIndexOf(".") + 1), file.length);
	    if(exension != "zip" && addEditRemove == "add")
	    {
		    alert("Please select zip format file");
		    return false;
	    }
	    
	    if($('#moduleName').val().trim() == '')
	    {
	    	alert("Please type module name");
	    	return false;
	    }
	    
	    if($('#moduleDescription').val().trim() == '')
	    {
	    	alert("Please type module description");
	    	return false;
	    }
	    
	    submitMessageForm();
    });

	function submitMessageForm()
	{
		$("#fullPageloader").show();
		$("#moduleFormId").submit(function(e)
		{
			var formData = new FormData(this);
			var formAction = $(this).attr("action");
	
			$.ajax
			({
				url:formAction,
				type:"POST",
				data: formData,
				mimeType:"multipart/form-data",
   				contentType: false,
   				cache: false,
   				processData:false,
				success:function(data)
				{
					$("#fullPageloader").hide();
					if(data == 'notZip')
						alert("Please select .zip format file");
					else if(data == 'moduleFolderNameError')
						alert("Error in creating module name folder");
					else if(data == "success")
					{
						$(".close").trigger('click');			// To hide the pop-up background
						
						if(addEditRemove == 'add')
							alert("Module uploaded successfully");
						if(addEditRemove == 'edit')
							alert("Module edited successfully");
						if(addEditRemove == 'remove')
							alert("Module removed successfully");
						
						getModuleData();						// Reload module data table
					}
				},
				error:function()
				{
					
				}
			});
			e.preventDefault();				//Stop default action
			$(this).unbind("submit");		// Unbind to stop multiple form submit
		});
		$("#moduleFormId").submit();		// Submit the form.				
	}
</script>

<div style="width:90%;margin:0px auto;" id="showHideId">
	<div>
		<button class="btn btn-success " data-toggle="modal" data-target="#myModal2" onclick="addModule()">Add New</button>
		<br><br>
		
		
					<table id="dataTableId" class="display" style="width:100%">
						<thead>
							<tr class="dataTab">
								<th>Sr. No.</th>
								<th>Module Name</th>
								<th>Module Description</th>
								<th>Module</th>
								<th>Action</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="moduleTopic" items="${moduleTopicList}" varStatus="status">
								<tr>
									<td>${status.count}</td>
									<td>${moduleTopic.module.name}</td>
									<td>${moduleTopic.module.description}</td>
									<td>
									<a href="${globalVO.moduleResourceDirectory}${moduleTopic.module.contentUrl}" target="_blank">
									<c:choose><c:when test="${moduleTopic.module.resourceTypeId == 7 }"><img
									src="${pageContext.request.contextPath}/resources/images/pdf.png"
									border="0" alt="" /> </c:when>
									<c:when test="${moduleTopic.module.resourceTypeId == 8 }"><img
									src="${pageContext.request.contextPath}/resources/images/word.png"
									border="0" alt="" /></c:when>
									<c:when test="${moduleTopic.module.resourceTypeId == 10}"><img
									src="${pageContext.request.contextPath}/resources/images/ppt.png"
									border="0" alt="" /> 
									</c:when>
									<c:when test="${moduleTopic.module.resourceTypeId == 9}"><img
									src="${pageContext.request.contextPath}/resources/images/excel.png"
									border="0" alt="" /> 
									</c:when>
									<c:when test="${moduleTopic.module.resourceTypeId == 1}">
									<img src="${pageContext.request.contextPath}/resources/images/swf.png" border="0" alt="Flash file" /> 
									</c:when>
									<c:when test="${moduleTopic.module.resourceTypeId == 2 || moduleTopic.module.resourceTypeId == 3}">
									<img src="${pageContext.request.contextPath}/resources/images/video.png" border="0" alt="Flash file" /> 
									<%-- <video id="${module.id}" width="320" height="176" controls>
									<source src="${globalVO.moduleResourceDirectory}${moduleTopic.module.contentUrl}" type="video/mp4">
								   </video> --%>
								   </c:when>
								   <c:otherwise>
								   <img src="${pageContext.request.contextPath}/resources/images/default.png" border="0" alt="" /> 
								   </c:otherwise>									
									</c:choose>
									</a>							
									</td>
									<td>
										<a href="javascript:void(0)" data-toggle="modal" data-target="#myModal2" onclick="editModule('${moduleTopic.moduleId}')">Edit</a> / 
										<a href="javascript:void(0)" onclick="removeModule('${moduleTopic.id}')">Remove</a>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
			
	</div>
	
	<!-- Modal2 -->
	<div id="myModal2" class="modal fade" role="dialog">
		<div class="modal-dialog">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<div class="modal-title">
						<span id="headerId"></span> Module
					</div>
				</div>

				<form:form action="${pageContext.request.contextPath}/sme/uploadModule.htm" commandName="globalVO" method="post" id="moduleFormId" class="form-horizontal" role="form" enctype="multipart/form-data">
				
					<input type="hidden" name="moduleId" id="editModuleId" value="" />
					<input type="hidden" name="moduleTopicId" id="editModuleTopicId" value="" />
					<input type="hidden" name="topicId" value="${globalVO.topicId}" />
					<form:hidden path="loginUserId" value="${user.id}" />
					<form:hidden path="loginUserType" value="${globalVO.loginUserType}" />
					<form:hidden path="toRemove" id="toRemove" value="false"/>
					
					<div class="modal-body">
						<table width="90%" border="0" cellspacing="0" cellpadding="0" class="topic-per table-striped table table-bordered table-hover table-condensed">
							<tr>
								<td>Browse File</td>
								<td>
									<input name="moduleFile" type="file" required="required" id="moduleFile">
								</td>
							</tr>
							<tr>
								<td>Module Name:</td>
								<td>
									<input name="moduleName" type="text" required="required" id="moduleName">
								</td>
							</tr>
							<tr>
								<td>Module Description</td>
								<td>
									<input name="moduleDescription" type="text" required="required" id="moduleDescription">
								</td>
							</tr>
							<!-- <tr>
							<td>Type</td>
							<td><input name="" type="text"></td>
						</tr> -->
							<tr>
								<td>Resources Tags</td>
								<td>
									<select name="resourceTypeId" required="required" id="resourceTypeId">
										<c:forEach var="resourceType" items="${globalVO.resourceTypeList}">
											<option value="${resourceType.id}">${resourceType.name}</option>
										</c:forEach>
									</select>
								</td>
							</tr>
							<tr>
								<td>Grade Type</td>
								<td>
									<select name="gradeTypeId" required="required" id="gradeTypeId">
										<c:forEach var="resourceType" items="${globalVO.gradeTypeList}">
											<option value="${resourceType.id}">${resourceType.name}</option>
										</c:forEach>
									</select>
								</td>
							</tr>
						</table>

						<input type="hidden" name="topicId" value="${globalVO.topicId}" id="topicId"/>
					</div>
					<div class="modal-footer">
						<div class="col-md-4 col-xs-6 ">
							<a href="javascript:void(0)" class="btn btn-primary btn-md" id="addModuleId">Add Module</a>
						</div>
					</div>
				</form:form>
			</div>
		</div>
	</div>
	<!-- Modal2 end -->
</div>