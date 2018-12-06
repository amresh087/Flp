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
		$("#boardIdValue").val($('#boardId').val());
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
					if(data == 'notZip')
						alert("Please select .zip format file");
					else if(data == 'moduleFolderNameError')
						alert("Error in creating module name folder");
					else if(data == "success")
					{
						$("#fullPageloader").hide();
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
	
	$("#resourceFormId").submit(function(e)
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
						if(data == 'notZip')
							alert("Please select .zip format file");
						else if(data == 'moduleFolderNameError')
							alert("Error in creating module name folder");
						else if(data == "success")
						{
							$("#fullPageloader").hide();
							$(".close").trigger('click');			// To hide the pop-up background
							alert("Module uploaded successfully");
							/* if(addEditRemove == 'add')
								alert("Module uploaded successfully");
							if(addEditRemove == 'edit')
								alert("Module edited successfully");
							if(addEditRemove == 'remove')
								alert("Module removed successfully"); */
							
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
	
	function editResources(moduleId)
	{
		 <c:forEach var="moduleTopic" items="${moduleTopicList}" varStatus="status">;
		if('${moduleTopic.moduleId}' == moduleId)
		{
			$('#editModuleId1').val(moduleId);
			$('#editModuleTopicId1').val('${moduleTopic.id}');
			$('#webUrl1').val('${moduleTopic.module.contentUrl}')
			$('#moduleName1').val("<c:out value='${moduleTopic.module.name}' />");
			$('#moduleDescription1').val("<c:out value='${moduleTopic.module.description}' />");
			$('#resourceTypeId1 option[value=${moduleTopic.module.resourceTypeId}]').prop('selected', true);
			
		}
	</c:forEach>;	
	
		
	}
	
$("#editRes").on("click",function(){
		
		if($('#moduleName1').val().trim() == '')
	    {
	    	alert("Please type module name");
	    	return false;
	    }
	    
	    if($('#moduleDescription1').val().trim() == '')
	    {
	    	alert("Please type module description");
	    	return false;
	    }
	    
	    if($('#webUrl1').val().trim() == '')
	    	{
	    	alert("Please type weburl");
	    	
	    	}
	    
	    $("#moduleFormId1").submit(function(e)
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
	    					alert("Resource update successfully");
	    					
	    							$(".close").trigger('click');
	    						getModuleData();						// Reload module data table
	    					
	    				},
	    				error:function()
	    				{
	    					
	    				}
	    			});
	    			e.preventDefault();				//Stop default action
	    			$(this).unbind("submit");	
		
	});
	
		
	});
	
	
	
	
	
	
	
</script>

<div style="width:90%;margin:0px auto;" id="showHideId">
	<div>
		<button class="btn btn-success " data-toggle="modal" data-target="#myModal2" onclick="addModule()">Add Modules</button>
		<button class="btn btn-success " data-toggle="modal" data-target="#standard-modules" onclick="addModule()">Add Resources</button>
		<button class="btn btn-success " data-toggle="modal" data-target="#myModal2" onclick="addModule()">Map Modules</button>
		
		<br><br>
		
		<table style="border: 3px;background: rgb(243, 244, 248);width:100%">		<!-- Use it because it's helpful -->
			<tr style="width:100%;height:100%">
				<td>
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
										<a href="<%-- ${globalVO.moduleResourceDirectory} --%>${commonPath}/modules/${moduleTopic.module.contentUrl}" target="_blank">
											<c:choose>
											<c:when test="${moduleTopic.module.contentType eq 1 || moduleTopic.module.contentType eq 3  }">
											<c:choose>
												<c:when test="${moduleTopic.module.resourceTypeId == 7 }">
													<img src="${pageContext.request.contextPath}/resources/images/pdf.png" border="0" alt="" />
												</c:when>
												<c:when test="${moduleTopic.module.resourceTypeId == 8 }">
													<img src="${pageContext.request.contextPath}/resources/images/word.png" border="0" alt="" />
												</c:when>
												<c:when test="${moduleTopic.module.resourceTypeId == 10}">
													<img src="${pageContext.request.contextPath}/resources/images/ppt.png" border="0" alt="" />
												</c:when>
												<c:when test="${moduleTopic.module.resourceTypeId == 9}">
													<img src="${pageContext.request.contextPath}/resources/images/excel.png" border="0" alt="" />
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
												</c:when>
												
												<c:otherwise>
									
												<a href="${moduleTopic.module.contentUrl}" target="_blank">
												<img border="0" alt="Flash file" src="${pageContext.request.contextPath}/resources/images/web-res.png">
												</a>
									
												</c:otherwise>
												
											</c:choose>
										</a>
										
									</td>
									<td><c:if test="${moduleTopic.module.contentType eq 2}">
										<a href="javascript:void(0)" data-toggle="modal" data-target="#editResources" onclick="editResources('${moduleTopic.moduleId}')">Edit</a></c:if> 
										<c:if test="${(moduleTopic.module.contentType eq 1 || moduleTopic.module.contentType eq 3)}">
										<a href="javascript:void(0)" data-toggle="modal" data-target="#myModal2" onclick="editModule('${moduleTopic.moduleId}')">Edit</a></c:if>/ 
										<a href="javascript:void(0)" onclick="removeModule('${moduleTopic.id}')">Remove</a>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</td>
			</tr>
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

				<form:form action="${pageContext.request.contextPath}/admin/uploadModule.htm" commandName="globalVO" method="post" id="moduleFormId" class="form-horizontal" role="form" enctype="multipart/form-data">
				
					<input type="hidden" name="moduleId" id="editModuleId" value="" />
					<input type="hidden" name="moduleTopicId" id="editModuleTopicId" value="" />
					<input type="hidden" name="topicId" value="${globalVO.topicId}" />
					<form:hidden path="loginUserId" value="${user.id}" />
					<form:hidden path="loginUserType" value="${globalVO.loginUserType}" />
					<form:hidden path="toRemove" id="toRemove" value="false"/>
					<form:hidden path="boardId" id="boardIdValue" value=""/> 
					
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
	
	<!-- Modal2 -->
	<div id="editResources" class="modal fade" role="dialog">
		<div class="modal-dialog">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<div class="modal-title">
						<span id="headerId">  Edit Resources</span>
					</div>
				</div>

				<form:form action="${pageContext.request.contextPath}/admin/updateResources.htm" commandName="globalVO" method="post" id="moduleFormId1" class="form-horizontal" role="form" enctype="multipart/form-data">
			
					<input type="hidden" name="moduleId" id="editModuleId1" value="" />
					<input type="hidden" name="moduleTopicId" id="editModuleTopicId1" value="" />
					<input type="hidden" name="topicId" value="${globalVO.topicId}" />
					<form:hidden path="loginUserId" value="${user.id}" />
					<form:hidden path="loginUserType" value="${globalVO.loginUserType}" />
					<form:hidden path="toRemove" id="toRemove" value="false"/>
					<form:hidden path="boardId" value="1"/> <!-- For CTLS board -->
					
					<div class="modal-body">
						<table width="90%" border="0" cellspacing="0" cellpadding="0" class="topic-per table-striped table table-bordered table-hover table-condensed">
							<tr>
								
								<td>Type Url of Web Resources:</td>
								<td>
									<input name="contentUrl" type="text" required="required" id="webUrl1">
								</td>
								
								
							</tr>
							<tr>
								<td>Module Name:</td>
								<td>
									<input name="moduleName" type="text" required="required" id="moduleName1">
								</td>
							</tr>
							<tr>
								<td>Module Description</td>
								<td>
									<input name="moduleDescription" type="text" required="required" id="moduleDescription1">
								</td>
							</tr>
							<!-- <tr>
							<td>Type</td>
							<td><input name="" type="text"></td>
						</tr> -->
							<tr>
								<td>Resources Tags</td>
								<td>
									<select name="resourceTypeId" required="required" id="resourceTypeId1">
										<c:forEach var="resourceType" items="${globalVO.resourceTypeList}">
										<c:if test="${resourceType.id eq 14 || resourceType.id eq 15}">
											<option value="${resourceType.id}">${resourceType.name}</option>
											</c:if>
										</c:forEach>
									</select>
									
									
									
								</td>
							</tr>
							
						</table>
						

							<input type="hidden" name="topicId" value="${globalVO.topicId}" id="topicId"/>
					</div>
					<div class="modal-footer">
						<div class="col-md-4 col-xs-6 ">
							<input type="submit" class="btn btn-primary btn-md" id="editRes" value="Add Module"/>
						</div>
					</div>
				</form:form>
			</div>
		</div>
	</div>
	
	<!-- Module resources -->
	<div id="standard-modules" class="modal fade" role="dialog">
		<div class="modal-dialog">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<div class="modal-title">
						<span id="headerId"></span> Module
					</div>
				</div>

				<form:form action="${pageContext.request.contextPath}/admin/uploadResources.htm"  commandName="globalVO" method="post" id="resourceFormId" class="form-horizontal" role="form" enctype="multipart/form-data">

				<!-- For CTLS board -->
				 <input type="hidden" name="moduleId" id="editModuleId" value="" />
					<input type="hidden" name="moduleTopicId" id="editModuleTopicId" value="" />
					<input type="hidden" name="topicId" value="${globalVO.topicId}" />
					<form:hidden path="loginUserId" value="${user.id}" />
					<form:hidden path="loginUserType" value="${globalVO.loginUserType}" />
					<form:hidden path="toRemove" id="toRemove" value="false"/>
					<form:hidden path="boardId" value=""/> <!-- For CTLS board -->
				
					
					<div class="modal-body">
						<table width="90%" border="0" cellspacing="0" cellpadding="0" class="topic-per table-striped table table-bordered table-hover table-condensed">
							
							<tr>
								<td>Type Url of Web Resources:</td>
								<td>
									<input name="contentUrl" type="text" required="required" id="webUrl">
								</td>
							</tr>
							<tr>
								<td>Module Name</td>
								<td>
									<input name="moduleName" type="text" required="required" id="urlName">
								</td>
							</tr>
							
							<tr>
								<td>Module Description</td>
								<td>
									<input name="moduleDescription" type="text" required="required" id="modDescrptn">
								</td>
							</tr>
							
							<tr>
								<td>Resources Tags</td>
								<td>
								<select name="resourceTypeId" required="required" id="resourceTypeId">
										<c:forEach var="resourceType" items="${globalVO.resourceTypeList}">
											<c:if test="${resourceType.id eq 14 || resourceType.id eq 15}">
											<option value="${resourceType.id}">${resourceType.name}</option>
											</c:if>
										</c:forEach>
									</select>
								
								
								
									<!-- <select name="resourceTypeId" required="required" id="resourceTypeId">
										
											<option value="">Web</option>
											<option value="">Video</option>
										
									</select> -->
								</td>
							</tr>
						</table>
</div>
					<div class="modal-footer">
						<div class="col-md-4 col-xs-6 ">
							<input type="submit" class="btn btn-primary btn-md" value="Add Module" id="adWebModuleId">
						</div>
					</div>
				</form:form>
			</div>
		</div>
	</div>
	<!-- Module resources end -->
</div>