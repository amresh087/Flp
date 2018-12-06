<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:include page="schoolTeacherHeader.jsp">
	<jsp:param value="manageToc" name="header" />
</jsp:include>
<style type="text/css">
.notifi-detail{width:100%;padding:5px; background:#EBEFF2; margin-top:2px;}
.user-comment{color: #226a94; float: left; margin-left: 1%; width: 86%; margin-bottom:10px;}
.user-img{float: left; padding-left: 0; padding-right: 0; width: 6%;}
.comments{width:100%; padding:10px; background-position: 0 7px;
padding-left: 28px;padding-top: 17px;}
</style>

<!-- Modal3 -->
<input type="hidden" id="flagQorMVal" value="${checkFlagQorM}" />
<div style="z-index: 9999;" id="myModal3" class="modal fade" role="dialog">
<div class="modal-dialog">
<!-- Modal content-->
<div class="modal-content">
<div class="modal-header">
<button type="button" class="close" data-dismiss="modal">&times;</button>
<div class="modal-title">
<span>Add</span> Module
</div>
</div>
<form:form action="${pageContext.request.contextPath}/schoolTeacher/addModule.htm?topicId=${param.topicId}&secId=${param.secId}&subId=${param.subId}&grdId=${param.grdId}&subjectName=${subject}&chapName=${chapterName}&topicName=${topicName}"
commandName="globalVO" method="post" id="addModule" class="form-horizontal" role="form" enctype="multipart/form-data" name="moduleForm">
<div class="modal-body" >
<table width="90%" border="0" cellspacing="0" cellpadding="0" class="topic-per table-striped table table-bordered table-hover table-condensed">
<tr>
<td>Browse File</td>
<td><input name="moduleFile" type="file" required="required" id="fileType"></td>
</tr>
<tr>
<td>Module Name:</td>
<td><input name="moduleName" type="text" required="required" id="modName"> </td>
</tr>
<tr>
<td>Module Description</td>
<td><input name="moduleDescription" type="text"></td>
</tr>
<tr>
<td>Resources Tags</td>
<td><select name="resourceTypeId" >
<c:forEach var="resourceType" items="${globalVO.resourceTypeList}">
<c:if test="${resourceType.id eq 1 || resourceType.id eq 2 || resourceType.id eq 3}">
<option value="${resourceType.id}">${resourceType.name}</option>
</c:if>
</c:forEach>
</select></td>
</tr>
<tr>
<td>Grade Type</td>
<td><select name="gradeTypeId" >
<c:forEach var="resourceType" items="${globalVO.gradeTypeList}">
<option value="${resourceType.id}">${resourceType.name}</option>
</c:forEach>
</select></td>
</tr>
</table>
<input type="hidden" name="topicId" value="${globalVO.topicId}" />
<input type="hidden" name="loginUserType" value="${sessionScope.user.userType}" />
</div>
<div class="modal-footer">
<div class="col-md-4 col-xs-6 ">
<input type="submit" class="btn btn-primary btn-md" value="Submit" id="addModuleId">
</div>
</div>
</form:form>
</div>
</div>
</div>
<!-- Modal3 end -->
<!-- Modal2 -->
<div style="z-index: 9999;" id="myModal2" class="modal fadecommonPath" role="dialog">
<div class="modal-dialog">

		<!-- Modal content-->
<div class="modal-content">
<div class="modal-header">
<button type="button" class="close" data-dismiss="modal">&times;</button>
<div class="modal-title">
<span>Add</span> Resource
</div>
</div>
<form:form action="${pageContext.request.contextPath}/schoolTeacher/addDocument.htm?topicId=${param.topicId}&secId=${param.secId}&subId=${param.subId}&grdId=${param.grdId}&subjectName=${subject}&chapName=${chapterName}&topicName=${topicName}"
commandName="globalVO" method="post" class="form-horizontal" role="form" enctype="multipart/form-data" name="documentForm">
<div class="modal-body">
<input type="hidden" name="loginUserType" value="${sessionScope.user.userType}" /> <input type="hidden" name="topicId" value="${globalVO.topicId}" />
<table width="90%" border="0" cellspacing="0" cellpadding="0" class="topic-per table-striped table table-bordered table-hover table-condensed">

<tr>
<td>Browse File</td>
<td width="200px; border:1px solid red;"><input name="moduleFile" width="200px" wrap="true" type="file" required="required" id="moduleFileId1"></td>
</tr>
<tr>
<td>Module Name:</td>
<td><input name="moduleName" type="text" required="required" id="docName"></td>
</tr>
<tr>
<td>Type</td>
<td><select name="moduleType" required="required">
<!--  <option value="">Select Resource Format</option> -->
<option value="Content">Content</option>
<option value="Comprehensive">Comprehensive</option>
<option value="Simulation">Simulation</option>
<option value="Explanation">Explanation</option>
<option value="Games">Games</option>
<option value="Worksheet">Worksheet</option>
<option value="Template">Template</option>
<option value="Acitivity">Acitivity</option>
<option value="Assessment">Assessment</option>
<option value="Mindmap">Mindmap</option>
<option value="Timeline">Timeline</option>
<option value="Digital Library">Digital Library</option>
<option value="Advance Resource">Advance Resource</option>
<option value="Example">Example</option>
<option value="Revision Notes">Revision Notes</option>
<option value="Practice Paper">Practice Paper</option>
<option value="other">other</option>
</select></td>
</tr>
<tr>
<td>Resources Tags</td>
<td><select name="resourceTypeId" required="required" id="resourceTypeIds">
<c:forEach var="resourceType" items="${globalVO.resourceTypeList}">
<c:if test="${resourceType.id eq 7 || resourceType.id eq 8 || resourceType.id eq 9 || resourceType.id eq 10}">
<option value="${resourceType.id}">${resourceType.name}</option>
</c:if>
</c:forEach>
</select></td>
</tr>
<tr>
<td>Module Description</td>
<td><textarea name="moduleDescription" cols="" rows=""></textarea></td>
</tr>
</table>
</div>
<div class="modal-footer">
<div class="col-md-4"></div>
<span id="errorIds" style="color: red;"></span>
<div class="col-md-4 col-xs-6">
<input type="submit" class="btn btn-primary btn-md" value="Submit" id="addDocId"/>
</div>
<div class="col-md-4"></div>
</div>
</form:form>
</div>
</div>
</div>
<!-- Modal2 end -->
<!-- ModalwebR- -->
<div id="ModalwebR" class="modal fade" role="dialog">
<div class="modal-dialog">

		<!-- Modal content-->
<div class="modal-content">
<div class="modal-header">
<button type="button" class="close" data-dismiss="modal">&times;</button>
<div class="modal-title">
<span>Add</span> Web Resources
</div>
</div>
<form:form action="${pageContext.request.contextPath}/schoolTeacher/addWebModule.htm?topicId=${param.topicId}&secId=${param.secId}&subId=${param.subId}&grdId=${param.grdId}&subjectName=${subject}&chapName=${chapterName}&topicName=${topicName}" 
 commandName="globalVO" method="post" id="addWebModule" class="form-horizontal" role="form" name="webModuleForm">
<div class="modal-body">
<input type="hidden" name="loginUserType" value="${sessionScope.user.userType}" /> <input type="hidden" name="topicId" value="${globalVO.topicId}" />
<table width="90%" border="0" cellspacing="0" cellpadding="0" class="topic-per table-striped table table-bordered table-hover table-condensed">
<tr>
<td><input type="text" placeholder="Type URL of the web resource you want to add" name="contentUrl" required="required" id="webUrl"/></td>
</tr>
<tr>
<td><input name="moduleName" type="text" required="required" placeholder="Module Name" id="urlName">
</td>Web-URL
</tr>
<tr>
<td><input name="moduleDescription" type="text" required="required" placeholder="Module Description" id="modDescrptn">
</td>

</tr>


<tr>
<td><select name="resourceTypeId" required="required">
<c:forEach var="resourceType" items="${globalVO.resourceTypeList}">

<c:if test="${resourceType.id eq 14 || resourceType.id eq 15}">
<option value="${resourceType.id}">${resourceType.name}</option>
</c:if>

</c:forEach>
</select></td>
</tr>
</table>
</div>
<div class="modal-footer">
<div class="col-md-4 col-xs-6 ">
<input type="submit" class="btn btn-primary btn-md" value="Add" id="adWebModuleId">
</div>
</div>
</form:form>
</div>
</div>
</div>  
<!-- ModalwebR end -->
<div id="page-wrapper" style="padding-top:5%;">
<div class="container-fluid">
<div class="row"><div class="col-lg-12">

<jsp:include page="teacherSubjectHeader.jsp">
		<jsp:param value="dashboard" name="header" />
		</jsp:include>

</div>
</div>
<div class="row">
<div class="col-md-12">
<!-- <h1 class="page-header">Content Library</h1> -->
 <div class="panel-heading">
                            
				<ol class="breadcrumb col-lg-9" style="padding:0px;">
					<li><a href="${pageContext.request.contextPath}/schoolTeacher/getManageTocHome.htm?secId=${sectionId}&subId=${subjectId}&grade=${grade}&sec=${section}&sub=${subject}&grdId=${gradeId}">Content Library</a></li> <li><a href="${pageContext.request.contextPath}/schoolTeacher/getManageTocHome.htm?secId=${sectionId}&subId=${subjectId}&grade=${grade}&sec=${section}&sub=${subject}&grdId=${gradeId}">${chapterName} </a></li>
					
					 <li class="active">${topicName}</li>
					 <li class="active" id="resourceQuestion">Question</li>
				   
                        </div>
</div>
</div>
<!-- /.row -->
<div class="clearfix"></div>
</div>


<div class=" col-md-12">
<div class="clearfix"></div>
		<!--tab-cont-->
<div class="tab-content">
<div id="home" class="tab-pane fade in active">
<div id="questions" class="tab-pane fade in active">
<div class="col-md-12 col-sm-12 col-xs-12 table-responsive">
<div class="col-md-12 col-sm-12 col-xs-12 mrt20 ">
<div class="row mrb20">
<a href="${pageContext.request.contextPath}/schoolTeacher/getManageTocQuestion.htm?topicId=${param.topicId}&secId=${param.secId}&subId=${param.subId }&gradeId=${param.grdId}&subjectName=${subject}&chapName=${chapterName}&topicName=${topicName}">
<button type="button" class="btn btn-success">Add New Question</button> </a>
<button type="submit" class="btn btn-warning" data-toggle="modal" data-target="#uploadExcel" id="questionUpload" onclick="$('#result').html('')">Import
From Excel</button>

 
 <a data-toggle="tab" class="btn btn-success pull-right" href="#flp-resource" id="resourceModuleId">Resources</a>
</div>
</div>
 <table id="datatableid" class="display" cellspacing="0"width="100%" style="font-size:13px;">
<thead>
<tr class="">
<th width="5%">S.No.</th>
<th>Questions</th>
<th>Cognitive level</th>
<th>Level</th>
<th class="text-center">Question Image</th>
<th>Author Name </th>
<th class="text-center">Action</th>
</tr>
</thead>
<tbody>
 <c:if test="${not empty questionTopic }">
<c:forEach var="quesTopic" items="${ questionTopic}" varStatus="status">
<tr style="font-size:15px;">
<td>${status.count }</td>
<td><p style="font-weight:bold;">${quesTopic.question.description }</p>
<c:forEach var="ans" items="${quesTopic.question.answers }" varStatus="stats">
<c:choose >
<c:when test="${ans.isCorrect == 1}">
<p style="color:green; float:left; font-size:14px;" kyabe="${ans.isCorrect}"  >
${stats.count}. &nbsp; ${ans.description} <i class="fa fa-check-circle" aria-hidden="true"></i>
 </p>
</c:when>
<c:otherwise>
<p style="float:left; font-size:14px;" kyabe="${ans.isCorrect}">${stats.count}. &nbsp; ${ans.description} &nbsp; </p>
</c:otherwise>
</c:choose>

<c:if test="${not empty ans.imagePath}">
<img src="${commonPath}/questionImages/${ans.imagePath}" /> </c:if> </p>
</c:forEach>
<div class="col-md-12" style="border-top: 1px solid #dddddd; margin-top: 10px; padding: 10px;">
				<!-- <a href="#" class=""  data-toggle="modal" data-target="#comment-post" ><i class="fa fa-comments" aria-hidden="true"></i>Comments</a> -->
<c:if test="${sessionScope.user.id ne quesTopic.question.createdBy}">
<a href="javascript:void(0)"  data-toggle="modal" data-target="#comment-post" id="questIds${quesTopic.question.id}" class="commentsCls"><i class="fa fa-comments" aria-hidden="true"></i>Feedbacks</a>
</c:if>
<!-- <a href="#" class=""  data-toggle="modal" data-target="#comment-post" ><i class="fa fa-comments" aria-hidden="true"></i>Comments</a> -->
</div>
</td>
<td  id="cong_${quesTopic.id}" class="cong_${quesTopic.cognitiveLevel.id}">${quesTopic.cognitiveLevel.name}</td>
<td>${quesTopic.difficultyLevel.name }</td>
<td><c:if test="${not empty quesTopic.question.imagePath}"><img src="${commonPath}/questionImages/${quesTopic.question.imagePath}" height="" width=""/></c:if>
<c:if test="${empty quesTopic.question.imagePath}">N/A</c:if></td>
<td> ${quesTopic.question.user.firstName} </td>
<td><c:if test="${sessionScope.user.id ==  quesTopic.question.createdBy}">
<a href="javascript:void(0)" data-toggle="modal" data-target="#myModal4" onclick="editQuestion('${quesTopic.question.id}')">Edit</a>
</c:if></td>
</tr>
</c:forEach>
</c:if> 
</tbody>
</table> 
</div>

</div><a href="${pageContext.request.contextPath}/common/getQuestionPagination?topicId=${param.topicId}" >2</a></div>
<!--QUESTIONS-->

<!--FLP RESOURCES-->
<div id="flp-resource" class="tab-pane fade <c:if test="${empty param.tab}"> in </c:if>" >
<div class="row mrt20">
<div class="col-md-6 pull-leftt"><a data-toggle="tab" class="btn btn-success " href="#home" id="backToQustionClick" ><i class="fa fa-chevron-circle-left" aria-hidden="true"></i>
 Back To Question</a></div>
 
<div class="col-md-12" style="margin-top: 15px; background-color:#F5F5F5;  border: 1px solid rgb(204, 204, 204); border-radius: 5px; padding: 10px;"><h3>Media Resources</h3></div>
<div class="col-md-12" style="margin-top:15px;">
<div class="col-md-2 pull-right" data-toggle="modal" data-target="#myModal3">
<button type="submit" class="btn btn-danger"><i class="fa fa-plus-circle" aria-hidden="true"></i>
Add More..</button>
</div>

</div>


<div class="clearfix"></div>
<div class="col-md-12" style="background-color:#F5F5F5; margin-top:15px; border-radius:8px;">
<div class="clearfix"></div>
<div class="row  mrt20">

<c:forEach var='module' items="${moduleLst}">
<c:if test="${module.resourceTypeId==1 ||module.resourceTypeId==2||module.resourceTypeId==3}">
<div class="col-md-3" id="${module.id}">
<div class="vid-dcumnt" style="padding-left:0px; padding-right:0px; padding-top:2px;">




<c:choose>

<c:when test="${module.resourceTypeId == 1}">
<!-- swf -->
<h5 style="height:60px">${module.name }</h5>
<div style="width:253px; height:176px; padding-top: 43px;">
<a href="${commonPath}/modules/${module.contentUrl}" target="_blank">
	<img border="0" alt="Flash file" src="${pageContext.request.contextPath}/resources/images/web-res.png">
</a> 

</div>



</c:when> 

<c:when test="${module.resourceTypeId == 2}">
<!-- MP4 File -->
<h5 style="height:60px">${module.name }</h5>
<div style="width:253px; height:176px;">
<video id="${module.id}" width="253" height="176" controls>
  <source src="${commonPath}/modules/${module.contentUrl}" type="video/mp4">
</video>
</div>

</c:when> 

<c:when test="${module.resourceTypeId == 3}">
<!-- MPGEV File -->
<h5 style="height:60px">${module.name }</h5>
<div style="width:253px; height:176px;">
<video id="${module.id}" width="253" height="176" controls>
  <source src="${commonPath}/modules/${module.contentUrl}" type="video/mp4">
  <source src="${commonPath}/modules/${module.contentUrl}" type="video/ogg">
  <source src="${commonPath}/modules/${module.contentUrl}" type="video/mpeg">

</video>
</div>
</c:when> 

</c:choose>
<%-- <video id="${module.id}" width="320" height="176" controls>
  <source src="${commonPath}/modules/${module.contentUrl}" type="video/mp4">
</video> --%>



<div style="margin-top:10px;"></div>
<div class="col-md-12 pull-right" style="text-align: right; border-top: 1px solid rgb(204, 204, 204); padding: 3px; background: rgb(246, 244, 244) none repeat scroll 0px 0px;">
<a href="javascript:void(0);"><img border="0" alt="Flash file" src="${pageContext.request.contextPath}/resources/images/share-icon.png" onclick="assignModule(${module.id},${module.contentType})"></a>


<c:if test="${module.createdBy eq sessionScope.user.id}">
<a href="javascript:void(0);"><img border="0" alt="Flash file" src="${pageContext.request.contextPath}/resources/images/edit.png" 
id="${module.id}" data-toggle="modal" data-target="#myModalEdit" 
onclick="editModule(${module.id},${module.contentType})"></a>

<%--  <div id="dataDescription" >${module.description}<div>
<div id="dataName"  display: none;>${module.name}<div> --%>

<a href="javascript:void(0);"><img border="0" alt="Flash file" src="${pageContext.request.contextPath}/resources/images/delet.png" onclick="delModule(${module.id},${module.contentType},${param.topicId})"></a>

</c:if>
</div>
</div>
</div>

</c:if>
 </c:forEach>
 
 
 
 
</div>
</div>
</div>



<div class="row mrt20">

<div class="col-md-12" style="margin-top: 15px; background-color:#F5F5F5;  border: 1px solid rgb(204, 204, 204); border-radius: 5px; padding: 10px;"><h3>Web Resources</h3></div>

<div class="clearfix"></div>
<div class="col-md-2 pull-right" data-toggle="modal" data-target="#ModalwebR" style="margin-top:15px;">
<button type="submit" class="btn btn-danger" onclick="webModuleForm.reset();" id="webResourcesId"><i class="fa fa-plus-circle" aria-hidden="true"></i>
Add More..</button>
</div>
<div class="col-md-12" style="background-color:#F5F5F5; margin-top:15px; border-radius:8px;"">
<div class="clearfix"></div>
<div class="row  mrt20">






<c:forEach var='module' items="${moduleLst}">
<c:if test="${module.contentType == 2}">
<div class="col-md-3" id="${module.id}">
<div class="wrd-dcumnt" style="padding-left:0px; padding-right:0px;">
<a href="${module.contentUrl}" target="_blank">
<div class="col-md-12" style="height: 130px; padding-top: 0px;">
<c:choose>
<c:when test="${module.resourceTypeId == 2}">
<!-- VideoURL -->

<img border="0" alt="Flash file" src="${pageContext.request.contextPath}/resources/images/web-res.png">



</c:when> 
<c:when test="${module.resourceTypeId == 14}">
<!-- contentURL -->
<img border="0" alt="Flash file" src="${pageContext.request.contextPath}/resources/images/web-res.png">
</c:when> 
<c:when test="${module.resourceTypeId == 15}">
<!-- VideoURL -->
<img border="0" alt="Flash file" src="${pageContext.request.contextPath}/resources/images/web-res.png">
</c:when> 
<c:otherwise>
<img border="0" alt="Flash file" src="${pageContext.request.contextPath}/resources/images/web-res.png">
</c:otherwise>
</c:choose>
<h5>${module.name }</h5>
</div>


</a>

<div class="col-md-12 pull-right" style="text-align: right; border-top: 1px solid rgb(204, 204, 204); padding: 3px; background: rgb(246, 244, 244) none repeat scroll 0px 0px;">
<a href="javascript:void(0);"><img border="0" alt="Flash file" src="${pageContext.request.contextPath}/resources/images/share-icon.png" onclick="assignModule(${module.id},${module.contentType})"></a>
<c:if test="${module.createdBy eq sessionScope.user.id}">
<a href="javascript:void(0);"><img border="0" alt="Flash file" src="${pageContext.request.contextPath}/resources/images/edit.png" id="${module.id}" data-toggle="modal" data-target="#ModalwebREdit" onclick="editModule(${module.id},${module.contentType})"></a>

<a href="javascript:void(0);"><img border="0" alt="Flash file" src="${pageContext.request.contextPath}/resources/images/delet.png" id="${module.id}" onclick="delModule(${module.id},${module.contentType},${param.topicId})"></a>

</c:if>

</div>
</div>
</div>
</c:if>
 </c:forEach>
 
</div>
</div>
</div>



<div class="row mrt20">

<div class="col-md-12" style="margin-top: 15px; border: 1px solid rgb(204, 204, 204); background-color:#F5F5F5; border-radius: 5px; padding: 10px;"><h3>Document Library</h3></div>

<div class="clearfix"></div>
<div class="col-md-2 pull-right" data-toggle="modal" data-target="#myModal2" style="margin-top:15px;">
<button type="submit" class="btn btn-danger" onclick="webModuleForm.reset();" id="webResourcesId"><i class="fa fa-plus-circle" aria-hidden="true"></i>
Add More..</button>
</div>
<div class="col-md-12" style="background-color:#F5F5F5; margin-top:15px; border-radius:8px;"">
<div class="clearfix"></div>
<div class="row  mrt20">

<c:forEach var='module' items="${moduleLst}">
<c:if test="${module.contentType == 3}" >
<div class="col-md-3" id="${module.id}">
<div class="wrd-dcumnt" style="padding-left:0px; padding-right:0px;">

<a href="${commonPath}/modules/${module.contentUrl}" target="_blank">


<div class="col-md-12" style="height: 130px; padding-top: 24px;">
<c:choose>

<c:when test="${module.resourceTypeId == 7}">
<!-- PDF -->
<img border="0" alt="Flash file" src="${pageContext.request.contextPath}/resources/images/pdf.png">
</c:when> 
<c:when test="${module.resourceTypeId == 8}">
<!-- word -->
<img border="0" alt="Flash file" src="${pageContext.request.contextPath}/resources/images/word.png">
</c:when> 
<c:when test="${module.resourceTypeId == 9}">
<!-- Excel -->
<img border="0" alt="Flash file" src="${pageContext.request.contextPath}/resources/images/excel.png">
</c:when> 
<c:when test="${module.resourceTypeId == 10}">
<!-- PPTX -->
<img border="0" alt="Flash file" src="${pageContext.request.contextPath}/resources/images/ppt.png">
</c:when> 
<c:otherwise>
</c:otherwise>
</c:choose>
<h5>${module.name }</h5>
</div>


</a>


<div class="col-md-12 pull-right" style="text-align: right; border-top: 1px solid rgb(204, 204, 204); padding: 3px; background: rgb(246, 244, 244) none repeat scroll 0px 0px;">
<a href="javascript:void(0);"><img border="0" alt="Flash file" src="${pageContext.request.contextPath}/resources/images/share-icon.png" onclick="assignModule(${module.id},${module.contentType})"></a>

<c:if test="${module.createdBy eq sessionScope.user.id}">
<a href="javascript:void(0);"><img border="0" alt="Flash file" src="${pageContext.request.contextPath}/resources/images/edit.png" id="${module.id}" data-toggle="modal" data-target="#myModal2Edit" onclick="editModule(${module.id},${module.contentType})"></a>

<a href="javascript:void(0);"><img border="0" alt="Flash file" src="${pageContext.request.contextPath}/resources/images/delet.png" onclick="delModule(${module.id},${module.contentType},${param.topicId})"></a>

</c:if>
</div>
</div>
</div>
</c:if>
 </c:forEach>

</div>


</div>

</div>
</div>
					<!--FLP RESOURCES-->




<!--tab-cont-->
</div>
</div>
<!--create-test-->
<!--tab-cont-->
<br /> <br />
</div>
<!-- /.container-fluid -->
</div>
	<!-- /#page-wrapper -->
</div>

<!-- Modal4 -->
<div id="myModal4" class="modal fade" role="dialog">

 <div class="eng-img img-responsive" id="engImg"></div>
<div class="modal-dialog assign-name-pop">
<!-- Modal content-->
<div class="modal-content">
<div class="modal-header">
<button type="button" class="close" data-dismiss="modal">&times;</button>
<div class="modal-title"> <span>Edit</span> Questions </div>
</div>
<div class="modal-body assign-name-pop-mid panel-body2">
<div class="col-md-12">
<div class="row">
<div class="edit-quest">
<form:form action="${pageContext.request.contextPath}/schoolTeacher/editQuestion.htm?subjectName=${subject}&chapName=${chapterName}&topicName=${topicName}"
method="POST" modelAttribute="globalVO" enctype="multipart/form-data" id="editQuestionFormId">

<div class="new-question">
<input type="hidden" name="questionId" id="editQuestionId" value="" /> 
<input type="hidden" name="optionId1" id="editOptionId1" value="" /> 
<input type="hidden" name="optionId2" id="editOptionId2" value="" /> 
<input type="hidden" name="optionId3" id="editOptionId3" value="" />
<input type="hidden" name="optionId4" id="editOptionId4" value="" /> 
<input type="hidden" name="questionTopicId" id="editQuestionTopicId" value="" /> 
<input type="hidden" name="topicId" value="${globalVO.topicId}" />
<form:hidden path="loginUserId" value="${user.id}" />
<form:hidden path="questionCategoryId" value="2" />
<form:hidden path="secId" value="${sectionId }" />
						<form:hidden path="subId" value="${subjectId }" />
						<form:hidden path="grdId" value="${gradeId }" />
<!-- Right now we are using only question with image type questions. so providing static value -->
<div class="col-md-4 col-sm-3">
<select id="questionTypeId" name="questionTypeId" required="required">
<option value="">Question Type</option>
<c:forEach var="questionType" items="${globalVO.questionTypeList}">
<option value="${questionType.id}">${questionType.name}</option>
</c:forEach>
</select>
</div>
<div class="col-md-4 col-sm-3">
<select id="questionDifficultyLevelId" name="questionDifficultyLevelId" required="required">
<option value="">Difficulty Level</option>
<c:forEach var="questionDifficulty" items="${globalVO.questionDifficultyLevelList}">
<option value="${questionDifficulty.id}">${questionDifficulty.name}</option>
</c:forEach>
</select>
</div>

<div class="col-md-4 col-sm-3">
<select id="questionCognitiveLevelId" name="questionCognitiveLevelId" required="required">
<option value="">Cognitive Level</option>
<c:forEach var="questionCognitive" items="${globalVO.questionCognitiveLevelList}">
<option value="${questionCognitive.id}">${questionCognitive.name}</option>
</c:forEach>
</select>
</div>
</div>
<div class="clearfix"></div>
<div class="col-md-6 col-sm-6 mrt20">
<label>Question</label>
<input type="checkbox" name="questionImgCheck" ><!--  -->Remove Image</input>
<textarea class=" mrt10" name="question" cols="" rows="" id="quesDescription"></textarea>
</div>
<div class="col-md-4 col-sm-4 ques-file-fld">
<input name="questionImage" type="file" id="questionImgId">
</div>
<div class="col-md-2 col-sm-2 col-xs-12 ques-file-fld">
<div id="quesImageDivId" class="enlarge">
</div>	
</div>
<div class="clearfix"></div>
<div class="col-md-6 col-xs-12 col-sm-6 mrt20">
<label>Option 1</label>
<input type="checkbox" name="option1ImgCheck" ><!--  -->Remove Image</input>
<textarea name="option1" class="mrt10" cols="" rows="" id="optionDescription1"></textarea>
</div>
<div class="col-md-4 col-sm-4 col-xs-12 ques-file-fld">
<input name="option1Image" type="file" id="optionImgId1">
<input name="isCorrectOption1" type="radio" value="1" class="correctAnsCls mr-rd"><label class="mr-rd">Correct</label>
<input name="isCorrectOption1" type="radio" value="0" class="inCorrect mr-rd"><label class="mr-rd">Incorrect</label>
</div>
								
								
								<div class="col-md-2 col-sm-2 col-xs-12 ques-file-fld">
								<div id="ansImageDivId1" class="enlarge">
								</div>	
								    
								</div>
								
								
								<!-- 
								<div class="col-md-2 col-sm-2 col-xs-5 ques-file-fld">
									
								</div> -->

								<div class="clearfix"></div>


								<div class="col-md-6 col-sm-6 col-xs-12 mrt20">
									<label>Option 2</label>
									<input type="checkbox" name="option2ImgCheck"  ><!--  -->Remove Image</input>
									<textarea name="option2" class="mrt10" cols="" rows=""
										id="optionDescription2"></textarea>
								</div>
								<div class="col-md-4 col-sm-4 col-xs-12 ques-file-fld">
									<input name="option2Image" type="file" id="optionImgId2">
									<input name="isCorrectOption2" type="radio" value="1" class="correctAnsCls mr-rd"><label class="mr-rd">Correct</label>
									<input name="isCorrectOption2" type="radio" value="0" class="inCorrect mr-rd"><label class="mr-rd">Incorrect</label>
								</div>
								
								<div class="col-md-2 col-sm-2 col-xs-12 ques-file-fld">
							   <div id="ansImageDivId2" class="enlarge"></div>
								</div>
								 <!-- <div class="col-md-2 col-sm-2 col-xs-5 ques-file-fld">
									
								</div> -->

								<div class="clearfix"></div>

								<div class="col-md-6 col-xs-12 col-sm-6 mrt20">
									<label>Option 3</label>
									<input type="checkbox" name="option3ImgCheck"  ><!--  -->Remove Image</input>
									<textarea name="option3" class="mrt10" cols="" rows=""
										id="optionDescription3"></textarea>
								</div>
								<div class="col-md-4 col-sm-4 col-xs-12 ques-file-fld">
									<input name="option3Image" type="file" id="optionImgId3">
									<input name="isCorrectOption3" type="radio" value="1" class="correctAnsCls mr-rd"><label class="mr-rd">Correct</label>
									<input name="isCorrectOption3" type="radio" value="0" class="inCorrect mr-rd"><label class="mr-rd">Incorrect</label>
								</div>
								
								<div class="col-md-2 col-sm-2 col-xs-12 ques-file-fld">
								<div id="ansImageDivId3" class="enlarge" ></div>	
								</div>
								<!-- <div class="col-md-2 col-sm-2 col-xs-5 ques-file-fld">
									
								</div> -->

								<div class="clearfix"></div>

								<div class="col-md-6 col-sm-6 col-xs-12 mrt20">
									<label>Option 4</label>
									<input type="checkbox" name="option4ImgCheck"  ><!--  -->Remove Image</input>
									<textarea name="option4" class="mrt10" cols="" rows=""
										id="optionDescription4"></textarea>
								</div>
								
								<div class="col-md-4 col-sm-4 col-xs-12 ques-file-fld">
									<input name="option4Image" type="file" id="optionImgId4">
									<input name="isCorrectOption4" type="radio" value="1" class="correctAnsCls mr-rd"><label class="mr-rd">Correct</label>
									<input name="isCorrectOption4" type="radio" value="0" class="inCorrect mr-rd"><label class="mr-rd">Incorrect</label>
									"}]
								</div>
								
								<div class="col-md-2 col-sm-2 col-xs-12 ques-file-fld">
									<div id="ansImageDivId4" class="enlarge"></div>
								</div>
								<!-- div class="col-md-2 col-sm-2 col-xs-5 ques-file-fld">
									
								</div> -->

								<div class="clearfix"></div>

								<div class="col-md-2 mrt20">
									<button type="submit" class="btn btn-danger"
										id="updateQuestionId">Save Question</button>
								</div>
								<span class="errorCls2" style="color: red;"></span>
							</form:form>
						</div>
					</div>
				</div>

			</div>

			<div class="modal-footer"></div>
		</div>

	</div>
</div>
<!-- Modal4 end -->

<div class="modal fade" id="uploadExcel" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel">
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
			<p>This form will upload question in bulk from excel sheet. You can download excel template  for question adding from this
			
			<a href="${pageContext.request.contextPath}/schoolTeacher/downloadExcel" target="_blank" style="color:#0035fb; test-decoration:underline;">link (Download Template)</a>
			
			 . Add questions in given format in excel and then upload sheet through below browse section </p>
				<span id="studentExcelId"></span>
				<form id="form2" method="post"
					action="${pageContext.request.contextPath}/teacher/uploadLoQuestionExcel?topicId=${globalVO.topicId}&secId=${param.secId}&subId=${param.subId}&grdId=${param.grdId}&subjectName=${param.subjectName}&chapName=${param.chapName}&topicName=${param.topicName}"
					enctype="multipart/form-data" onsubmit="return uploadFormData()">
					<!-- File input -->
					<input name="file2" id="file2" type="file" /><br />  
					<input	class="btn btn-success " type="submit" value="Upload" />  
						
				</form>


				<div id="result">
					<c:if test="${not empty sessionScope.excelMsg}">

						<script>
				alert('${sessionScope.excelMsg}');
				$('#questionUpload').trigger('click');
				</script>
						<br>
						<%
							session.removeAttribute("excelMsg");
						%>
						<c:remove var="excelMsg" scope="session" />
					</c:if>
				</div>
			</div>

		</div>
	</div>

</div>
<!-- comment post -->

<div class="modal fade" id="comment-post" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel">
	<div class="modal-dialog" role="document" style="width:67%;">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title" id="myModalLabel">Post your comment</h4>
			</div>

			<div class="clearfix"></div>
			<div class="modal-body">
				<span id="studentExcelId"></span>
				<div class="comments"><div class="notifi-detail">
   <div class="user-img"><img width="60" src="${pageContext.request.contextPath}/resources/images/user-pic.png" style="max-width:100%; margin-right:10px;"><br>
   </div>
   <div class="user-comment"> <form id="form2" method="post"><textarea rows="3" class="form-control" placeholder="Post comment" id="commentsDescId"></textarea></form></div>
   <div style="margin-left:55px; margin-top:10px;">
    <input type="hidden" id="questCommentId">
<a href="javascript:void(0)" id="btnGetQuestion" class="btn btn-primary" >Post Comment</a>
</div>
 </div></div>
 <div id="allCommentsDetails">
<%--  <div class="comments" style="border-top: dashed 1px #5D95CF;">
 <table class="display">
 <tr>
 <td valign="top"><div class="user-img"><img width="60" src="${pageContext.request.contextPath}/resources/images/user-pic.png" style=" margin-right:10px;"><br>
   </div></td>
 <td><div class="notifi-detail">
   
   <div class="user-name"><b>Teacher Name</b></div>
  <p>This question has grammatical mistakes. Please check. </p>
  <span class="notification-date" style="color:#588FCC;"> <b>12-April-2016</b></span>
 </div></td>
 </tr>
 </table>
 </div> --%>
 </div>

			</div>

		</div>
	</div>

</div>
<!-- comment post End  -->















<!-- Start Media Resources-->
<div style="z-index: 9999;" id="myModalEdit" class="modal fade" role="dialog">
<div class="modal-dialog">
<!-- Modal content-->
<div class="modal-content">
<div class="modal-header">
<button type="button" class="close" data-dismiss="modal">&times;</button>
<div class="modal-title">
<span>Edit</span> Module
</div>
</div>
<form:form action="${pageContext.request.contextPath}/schoolTeacher/updateModule.htm?topicId=${param.topicId}&secId=${param.secId}&subId=${param.subId}&grdId=${param.grdId}&subjectName=${subject}&chapName=${chapterName}&topicName=${topicName}"
commandName="globalVO" method="post" id="addModuleEdit" class="form-horizontal" role="form" enctype="multipart/form-data" name="moduleForm">
<div class="modal-body" >
<table width="90%" border="0" cellspacing="0" cellpadding="0" class="topic-per table-striped table table-bordered table-hover table-condensed">
<!-- <tr>
<td>Browse File</td>
<td><input name="moduleFile" type="file" required="required" id="fileTypeEdit"></td>
</tr> -->
<tr>
<td>Module Name:</td>
<td><input name="moduleName" type="text" required="required" id="modNameEdit"> </td>
</tr>
<tr>
<td>Module Description</td>
<td><input name="moduleDescription" type="text" id="moduleDescriptionEdit"></td>
</tr>
<%-- <tr>
<td>Resources Tags</td>
<td><select name="resourceTypeId" >
<c:forEach var="resourceType" items="${globalVO.resourceTypeList}">
<c:if test="${resourceType.id eq 1 || resourceType.id eq 2 || resourceType.id eq 3}">
<option value="${resourceType.id}">${resourceType.name}</option>
</c:if>
</c:forEach>
</select></td>
</tr> --%>
<tr>
<td>Grade Type</td>
<td><select name="gradeTypeId" id="editGradeTypeId">
<c:forEach var="resourceType" items="${globalVO.gradeTypeList}">
<option value="${resourceType.id}">${resourceType.name}</option>
</c:forEach>
</select></td>
</tr>
</table>
<input type="hidden" name="topicId" value="${globalVO.topicId}" />
<input type="hidden" name="loginUserType" value="${sessionScope.user.userType}" />
</div>
<input type="hidden" name="moduleId" id="mediaEditmoduleId"/>
<input type="hidden" name="contentType" id="mediaEditContentType"/>

<div class="modal-footer">
<div class="col-md-4 col-xs-6 ">
<input type="submit" class="btn btn-primary btn-md" value="Update" id="addModuleIdEdit">
</div>
</div>
</form:form>
</div>
</div>
</div>
<!-- End Media Resources-->






<!--Edit DocumentPopUp Start -->

<div style="z-index: 9999;" id="myModal2Edit" class="modal fadecommonPath" role="dialog">
<div class="modal-dialog">

		<!-- Modal content-->
<div class="modal-content">
<div class="modal-header">
<button type="button" class="close" data-dismiss="modal">&times;</button>
<div class="modal-title">
<span>Edit</span> Resource
</div>
</div>
<form:form action="${pageContext.request.contextPath}/schoolTeacher/updateModule.htm?topicId=${param.topicId}&secId=${param.secId}&subId=${param.subId}&grdId=${param.grdId}&subjectName=${subject}&chapName=${chapterName}&topicName=${topicName}"
commandName="globalVO" method="post" class="form-horizontal" role="form" enctype="multipart/form-data" name="documentForm">
<div class="modal-body">
<input type="hidden" name="loginUserType" value="${sessionScope.user.userType}" /> <input type="hidden" name="topicIdEdit" value="${globalVO.topicId}" />
<table width="90%" border="0" cellspacing="0" cellpadding="0" class="topic-per table-striped table table-bordered table-hover table-condensed">

<!-- <tr>
<td>Browse File</td>
<td width="200px; border:1px solid red;"><input name="moduleFile" width="200px" wrap="true" type="file" required="required" id="moduleFileId1Edit"></td>
</tr>
<tr> -->
<td>Module Name:</td>
<td><input name="moduleName" type="text" required="required" id="docNameEdit"></td>
</tr>
<tr>
<td>Type</td>
<td><select name="moduleType" id="docType" required="required">
<!--  <option value="">Select Resource Format</option> -->
<option value="Content">Content</option>
<option value="Comprehensive">Comprehensive</option>
<option value="Simulation">Simulation</option>
<option value="Explanation">Explanation</option>
<option value="Games">Games</option>
<option value="Worksheet">Worksheet</option>
<option value="Template">Template</option>
<option value="Acitivity">Acitivity</option>
<option value="Assessment">Assessment</option>
<option value="Mindmap">Mindmap</option>
<option value="Timeline">Timeline</option>
<option value="Digital Library">Digital Library</option>
<option value="Advance Resource">Advance Resource</option>
<option value="Example">Example</option>
<option value="Revision Notes">Revision Notes</option>
<option value="Practice Paper">Practice Paper</option>
<option value="other">other</option>
</select></td>
</tr>
<%-- <tr>
<td>Resources Tags</td>
<td><select name="resourceTypeId" required="required" id="resourceTypeIdsEdit">
<c:forEach var="resourceType" items="${globalVO.resourceTypeList}">
<c:if test="${resourceType.id eq 7 || resourceType.id eq 8 || resourceType.id eq 9 || resourceType.id eq 10}">
<option value="${resourceType.id}">${resourceType.name}</option>
</c:if>
</c:forEach>
</select></td>
</tr> --%>
<tr>
<td>Module Description</td>
<td><textarea name="moduleDescription" cols="" rows="" id="docModuleDescription"></textarea></td>
</tr>
</table>
</div>
<div class="modal-footer">
<div class="col-md-4"></div>
<span id="errorIdsEdit" style="color: red;"></span>
<input type="hidden" name="moduleId" id="documentEditmoduleId"/>
<input type="hidden" name="contentType" id="documentEditContentType"/>
<div class="col-md-4 col-xs-6">
<input type="submit" class="btn btn-primary btn-md" value="Update" id="addDocIdEdit"/>
</div>
<div class="col-md-4"></div>
</div>
</form:form>
</div>
</div>
</div>

<!--Edit DocumentPopUp End -->




















<div id="ModalwebREdit" class="modal fade" role="dialog">
<div class="modal-dialog">

		<!-- Modal content-->
<div class="modal-content">
<div class="modal-header">
<button type="button" class="close" data-dismiss="modal">&times;</button>
<div class="modal-title">
<span>Edit</span> Web Resources
</div>
</div>
<form:form action="${pageContext.request.contextPath}/schoolTeacher/updateModule.htm?topicId=${param.topicId}&secId=${param.secId}&subId=${param.subId}&grdId=${param.grdId}&subjectName=${subject}&chapName=${chapterName}&topicName=${topicName}" 
 commandName="globalVO" method="post" id="addWebModuleEdit" class="form-horizontal" role="form" name="webModuleForm">
<div class="modal-body">
<input type="hidden" name="loginUserType" value="${sessionScope.user.userType}" /> <input type="hidden" name="topicId" value="${globalVO.topicId}" />
<table width="90%" border="0" cellspacing="0" cellpadding="0" class="topic-per table-striped table table-bordered table-hover table-condensed">
<tr>
<td><input type="text" placeholder="Type URL of the web resource you want to add" name="contentUrl" required="required" id="webUrlEdit"/></td>
</tr>
<tr>
<td><input name="moduleName" type="text" required="required" placeholder="Module Name" id="urlNameEdit">
</td>Web-URL
</tr>
<tr>
<td><select id="editResourceTypeId" name="resourceTypeId" >
<c:forEach var="resourceType" items="${globalVO.resourceTypeList}">

<c:if test="${resourceType.id eq 14 || resourceType.id eq 15}">
<option value="${resourceType.id}">${resourceType.name}</option>
</c:if>

</c:forEach>
</select></td>
</tr>
</table>
</div>
<input type="hidden" name="moduleId" id="webEditmoduleId"/>
<input type="hidden" name="contentType" id="webEditContentType"/>
<div class="modal-footer">
<div class="col-md-4 col-xs-6 ">
<input type="submit" class="btn btn-primary btn-md" value="Update" id="adWebModuleIdEdit">
</div>
</div>
</form:form>
</div>
</div>
</div> 

<div style="width: 38px; height: 117px; right: 0px; top: 50%; position: fixed;">
<a href="https://docs.google.com/forms/d/e/1FAIpQLScylZUgeQrLr2oGBufoyeQFKFIlBQD67qvOdFhWu6Qcd17rRw/viewform" target="blank"><img src="${pageContext.request.contextPath}/resources/images/feedback.png" alt=""></a>
</div>







<c:if test="${msg =='used'}">
<script>
alert("Cannot modify question. Already in used.");
</script>
<c:remove scope="session" var="msg"/>

</c:if>


<script type="text/javascript">
$(document).ready(function(e) {
	
	
	
	<c:if test="${saveQuestion ne null && not empty saveQuestion}">
	alert("Question Add Successfully !!!");
	<c:remove var="saveQuestion" scope="session"></c:remove>
	</c:if>
	
	
	<c:if test="${not empty flagModule}">
	$("#resourceModuleId").trigger("click");
	<c:remove var="flagModule" scope="session"></c:remove>
	</c:if>
	
	
	$(".enlarge").on("mouseover",function() {
		var val=$(this).children().attr('src');
		  $("#engImg" ).empty().html('<img src="'+val+'"/>').show();
		}); 
	
	$(".enlarge").mouseleave(function() {
		  $("#engImg" ).empty().hide();
		}); 
	
	 $("#resource_ques").dataTable();
	    $("#resource_ques").show();
	    $('table#datatableid').dataTable({
 	});
	    

});


function editQuestion(questionId)
{
	var count=0;
	$(".enlarge").empty()
	$(".errorCls2").html('');
	$('#editQuestionFormId').each (function(){
		  this.reset();
		});
	
	
	<c:forEach var="quesTopic" items="${questionTopic}"	varStatus="status">;
		if('${quesTopic.question.id}' == questionId)
		{
			$('#editQuestionId').val("${quesTopic.question.id}");
			$('#editQuestionTopicId').val("${quesTopic.id}");
			$('#questionTypeId option[value=${quesTopic.question.questionTypeId}]').prop('selected', true);
			$('#questionDifficultyLevelId option[value=${quesTopic.difficultyLevelId}]').prop('selected', true);
			$('#questionCognitiveLevelId option[value=${quesTopic.cognitiveLevelId}]').prop('selected', true);
			$('#quesDescription').html("<c:out value='${quesTopic.question.description}' />");
			
			<c:if test="${not empty quesTopic.question.imagePath}">;
				$('#quesImageDivId').empty().append("<img width='70px' height='70px' src='${globalVO.questionResourceDirectory}${quesTopic.question.imagePath}' />").show();
			</c:if>;
			
			<c:forEach var="ans" items="${quesTopic.question.answers}" varStatus="status">
				<c:if test="${status.count == 1}">;
					$('#editOptionId1').val("${ans.id}");
					$('#optionDescription1').html("<c:out value='${ans.description}' />");
					$("input[name=isCorrectOption1][value=${ans.isCorrect}]").prop('checked', true);
					
					<c:if test="${not empty ans.imagePath}">;
						$('#ansImageDivId1').empty().append("<img width='70px' height='70px' src='${globalVO.questionResourceDirectory}${ans.imagePath}' />").show();
					</c:if>
					count++;
				</c:if>
				<c:if test="${status.count == 2}">;
					$('#editOptionId2').val("${ans.id}");
					$('#optionDescription2').html("<c:out value='${ans.description}' />");
					$("input[name=isCorrectOption2][value=${ans.isCorrect}]").prop('checked', true);
					
					<c:if test="${not empty ans.imagePath}">;
						$('#ansImageDivId2').empty().append("<img width='70px' height='70px' src='${globalVO.questionResourceDirectory}${ans.imagePath}' />").show();
					</c:if>
					count++;
				</c:if>
				<c:if test="${status.count == 3}">;
					$('#editOptionId3').val("${ans.id}");
					$('#optionDescription3').html("<c:out value='${ans.description}' />");
					$("input[name=isCorrectOption3][value=${ans.isCorrect}]").prop('checked', true);
					
					<c:if test="${not empty ans.imagePath}">;
						$('#ansImageDivId3').empty().append("<img width='70px' height='70px' src='${globalVO.questionResourceDirectory}${ans.imagePath}' />").show();
					</c:if>
					count++;
				</c:if>
				<c:if test="${status.count == 4}">;
					$('#editOptionId4').val("${ans.id}");
					$('#optionDescription4').html("<c:out value='${ans.description}' />");
					$("input[name=isCorrectOption4][value=${ans.isCorrect}]").prop('checked', true);
					
					<c:if test="${not empty ans.imagePath}">;
						$('#ansImageDivId4').empty().append("<img width='70px' height='70px' src='${globalVO.questionResourceDirectory}${ans.imagePath}' />");
					</c:if>
					count++;
				</c:if>
			</c:forEach>;
		}
	</c:forEach>;
	
	if(count<4)
	{
	$('#editOptionId4').val("");
	$('#optionDescription4').html("");
	
	$("isCorrectOption4").prop('checked', false);;
	$('#ansImageDivId4').empty();
	if(count<3){
		$('#editOptionId3').val("");
		$('#optionDescription3').html("");
		$("isCorrectOption3").prop('checked', false);;
		$('#ansImageDivId3').empty();
		if(count<2){
			$('#editOptionId2').val("");
			$('#optionDescription2').html("");
			$("isCorrectOption2").prop('checked', false);
			$('#ansImageDivId2').empty();
		}
	}
	}
	
	
	
}


	
function editModule(moduleId,contentType){
	<c:forEach var='module' items="${moduleLst}">
	var oldMod=parseInt('${module.id}');
	if(oldMod == parseInt(moduleId)){
		
		if(contentType==1){
			//media resources
			$("#modNameEdit").val('${module.name}');
			$("#moduleDescriptionEdit").val('${module.description}');
			$("#editGradeTypeId").val('${module.gradeTypeId}');
			$("#mediaEditmoduleId").val(moduleId);
			$("#mediaEditContentType").val('${module.contentType}');
			
			
			
		}
		else if(contentType==2){
			//web resources
			$("#webUrlEdit").val('${module.contentUrl}');
			$("#urlNameEdit").val('${module.name}');
			$("#editResourceTypeId").val('${module.resourceTypeId}');
			$("#webEditmoduleId").val(moduleId);
			$("#webEditContentType").val('${module.contentType}');
			
		}
		else if(contentType==3){
			
			//Document Library
			$("#docNameEdit").val('${module.name}');
			$("#docModuleDescription").val('${module.description}');
			$("#docType").val('${module.moduleType}');
			$("#documentEditmoduleId").val(moduleId);
			$("#documentEditContentType").val('${module.contentType}');
			
		}
		
		
	
	}
	</c:forEach>;
		
	} 
	
	
	
	
	function delModule(moduleId,contentType,topicId){
		  var val=confirm("Are you sure you want to delete module..!");
		  if(val){
			  $.post("${pageContext.request.contextPath}/ajax/rest/deleteModule/" + topicId + "/" + moduleId + "/" + contentType, function(data)
					    {
						   if(data==true || data=="true"){
							   
							   alert("Successfully deleted !!!");
							    document.getElementById(moduleId).style.display = 'none';
							 // $("317").hide();
						   } 
					    });
		  }else{}
		
	}
	
	function assignModule(moduleId,moduleContentType){
	
		
	    var flag=confirm("Are you sure you want assign Module.");
		if(flag){
			
			var sectionId=${param.secId};
		     var topicId=${param.topicId};
		    var gradeId=${param.grdId};
		    var subjectId=${param.subId};
		 
			
			   $.post("${pageContext.request.contextPath}/ajax/rest/assignModuleToSection/" + sectionId + "/" + topicId + "/" + moduleId + "/" + gradeId + "/" + subjectId, function(data)
					    {
						    if(data != null && parseInt(data) > 0)
						    {
						    	alert('Module Successfully Assigned.');
						    }else{alert('Module already Assigned.');}
					    });
		}
		
	}


	
	
	 $(".commentsCls").on("click", function(){
		 $("#commentsDescId").val('');
	    	$("#questCommentId").val('')
	    	var questId=$(this).attr("id").substr(8);
	    	$("#questCommentId").val(questId);
	    	var val='';
	    	$("#allCommentsDetails").html('');
	   	 $.post("${pageContext.request.contextPath}/teacher/getAllTeacherCommentsByQuestId/" + questId , function(data)
				    {
					   if(data!=null && data.length>0){
						   for(var i=0;i<data.length;i++){
							   var formattedDate = new Date(data[i].postedOn);
							   var d = formattedDate.getDate();
							   var m =  formattedDate.getMonth();
							   m += 1;  // JavaScript months are 0-11
							   var y = formattedDate.getFullYear();
							   
							   val=val+' <div class="comments" style="border-top: dashed 1px #5D95CF;"> <table class="display"> <tr>'
							   +'<td valign="top"><div class="user-img"><img width="60" src="${pageContext.request.contextPath}/resources/images/user-pic.png" style=" margin-right:10px;"><br>'
							   +'</div></td><td><div class="notifi-detail"><div class="user-name"><b>'+data[i].user.firstName+'</b></div> <p>'+data[i].commentDesc+' </p>'
							   +'<span class="notification-date" style="color:#588FCC;"> <b>'+d+'-'+m+'-'+y+'</b></span></div></td></tr></table></div>';
							   
							   
						   }
						   $("#allCommentsDetails").html(val);
					   }
				    });
	    });
	 
	 $("#btnGetQuestion").on("click", function(){
		var questId= $("#questCommentId").val();
		var questComment=$("#commentsDescId").val().trim();
		if(questComment==""){
			alert("Please Enter Comment for this question !!!");
			return false;
		}
		 $.post("${pageContext.request.contextPath}/teacher/addQuestionComment/" + questId + "?questComment=" + questComment, function(data)
				    {
					   if(data==true || data=="true"){
						   alert("Comment add successfully !!!")
					   $(".close").trigger("click");
					   }
				    });
	 });
	

    
    $("#adWebModuleId").on("click", function(){
    	var urlSource=$('#webUrl').val().trim();
    	var urlName=$('#urlName').val().trim();
    	if(urlSource== null || urlSource==""){
    	alert("please provide web url for module");	
    	}else if(urlName== null || urlName==""){
    		alert("please provide url name");
    	}else{
    	 $("#fullPageloader").show();
    }
    	});
    
    $("#addModuleId").on("click", function()
    	    {
    		    var file = $('#fileType').val();
    		    var exension = file.substring((file.lastIndexOf(".") + 1), file.length);
    		    var moduleName = $('#modName').val().trim();
    		    
    		    if(exension != "zip")
    		    { alert("please select zip format file");
    			    return false;
    		    }else if(moduleName==null || moduleName==""){
    		    
    		    	alert("please give module name");
    		    
    		    }else {
    		    	
    		    $("#fullPageloader").show();
    		    }
    	    });
    	    
    	    
    	    
    	    $("#updateQuestionId").on("click", function()
    	    	    {
    	    		    var status = false;
    	    		    $(".errorCls2").empty();
    	    		    
    	    		    var question = $("#quesDescription").val();
    	    		    if(question==null || question==""){
    	    		    	var questionFile=$("#questionImgId").val();
    	    		    	if(questionFile==null || questionFile==""){
    	    		    		$(".errorCls2").html("Enter Question or upload question!");
    	    				    $(".questionId").focus();
    	    				    return false;
    	    		    	}else{
    	    		    		var val=questionFile.split(".");
    	    		    		if(val[val.length-1]!="jpg" && val[val.length-1]!="jpeg" && val[val.length-1]!="png" ){
    	    		    			$(".errorCls2").html("Please browse png or jpg file!");
    	    					    return false;
    	    		    		}
    	    		    	}
    	    		    }
    	    		    
    	    		    var option1 = $("#optionDescription1").val();
    	    		    if(option1==null || option1==""){
    	    		    	var option1File=$("#optionImgId1").val();
    	    		    	if(option1File==null || option1File==""){
    	    		    		$(".errorCls2").html("Enter option1 or upload option1!");
    	    				    return false;
    	    		    	}else{
    	    		    		var val=option1File.split(".");
    	    		    		if(val[val.length-1]!="jpg" && val[val.length-1]!="jpeg" && val[val.length-1]!="png" ){
    	    		    			$(".errorCls2").html("Please browse png or jpg file!");
    	    					    return false;
    	    		    		}
    	    		    	}
    	    		    }
    	    		    
    	    		    var option2 = $("#optionDescription2").val();
    	    		    if(option2==null || option2==""){
    	    		    	var option2File=$("#optionImgId2").val();
    	    		    	if(option2File==null || option2File==""){
    	    		    		$(".errorCls2").html("Enter option2 or upload option2!");
    	    				    return false;
    	    		    	}else{
    	    		    		var val=option1File.split(".");
    	    		    		if(val[val.length-1]!="jpg" && val[val.length-1]!="jpeg" && val[val.length-1]!="png" ){
    	    		    			$(".errorCls2").html("Please browse png or jpg file!");
    	    					    return false;
    	    		    		}
    	    		    	}
    	    		    }
    	    		    
    	    		    $(".correctAnsCls").each(function(e)
    	    		    {
    	    			    if($(this).prop('checked') == true)
    	    			    {
    	    				    status = true;
    	    			    }
    	    		    });
    	    		    if(status == false)
    	    		    {
    	    			    $(".errorCls2").html("select atleast one correct answer!");
    	    			    $(".errorCls2").focus();
    	    			    return false;
    	    		    }
    	    		    
    	    		    status = false;
    	    		    $(".inCorrect").each(function(e)
    	    		    {
    	    			    if($(this).prop('checked') == true)
    	    			    {
    	    				    status = true;
    	    			    }
    	    		    });
    	    		    
    	    		    if(status == false)
    	    		    {
    	    			    $(".errorCls2").html("Atleast one answer must be in-correct!");
    	    			    $(".errorCls2").focus();
    	    			    return false;
    	    		    }
    	    		      $("#fullPageloader").show();
    	    	    });
    	    
    	    
    	
    	    $("#addDocId").on("click", function(){
    	    	$("#errorIds").empty();
    	    	var resourceTypeIds=parseInt($("#resourceTypeIds").val());
    	    	var docName=$('#docName').val().trim();
    	    	var file=$("#moduleFileId1").val();
    	    	var ext = file.substr( (file.lastIndexOf('.') +1) );
    	    	if(resourceTypeIds == 7){
    	    	 if(ext!='pdf'){
    	    		 $("#errorIds").html("Please select pdf file to upload!");
    	    		 return false;
    	    	 }
    	    	}else if(resourceTypeIds == 8){
    		    	//var file=$("#moduleFileId1").val();
    		    	 if(ext!='doc' && ext!='docx'){
    		    		 $("#errorIds").html("Please select word file to upload!");
    		    		 return false;
    		    	 }
    		    	}else if(resourceTypeIds == 9){
    			    	//var file=$("#moduleFileId1").val();
    			    	 if(ext!='xls' && ext!='xlsx'){
    			    		 $("#errorIds").html("Please select excel file to upload!");
    			    		 return false;
    			    	 }
    			    	}else if(resourceTypeIds == 10){
        			    	//var file=$("#moduleFileId1").val();
       			    	 if(ext!='pptx' && ext!='ppt') {
       			    		 $("#errorIds").html("Please select PPT file to upload!");
       			    		 return false;
       			    	 }
    			    	}else if(docName== null || docName==""){
    			    		
    			    	alert("please give name to the document file!!");	
    			    	}else{
    	    	 $("#fullPageloader").show();
    			    	}  });
    	    
    	    setInterval(function(){ $("#errorIds").empty(); }, 10000);
    //using FormData() object
    function uploadFormData()
    {
	    $('#result').html('');
	    
	    var oMyForm = new FormData();
	    
	    var file = $('#file2').val();
	    var extension = file.substring((file.lastIndexOf(".") + 1), file.length);
	    
	    if(extension == 'xlsx' || extension =='xls')
	    { 
		    return true;
	    }
	    else
		    {
	    	alert('Please select an excel file to upload');
		    return false;
		    }
    }
    
    
    function getPaginationPage()
    {
    	console.log("pagination page");
    	 $.get("${pageContext.request.contextPath}/common/getQuestionPagination",{
    		topicId:"${param.topicId}",
    		offset:2
    		
    	 },function(data){
    		// console.log(data);
    		 if(data==""|| data==null)
    			 {
        		 console.log("Data for Qusetion"+data);    			 
    			 }
    		 else
    			 {
        		 console.log("Data for Qusetion"+data);
    			 }

    		 
    	 });
    	
    	
    	
    }
    $(document).ready(function(){
  
    	
    	
    	var flag=$('#flagQorMVal').val();;
    	if(flag=='M'){
    		$('#resourceModuleId').click();
    		
    	} 
    
    });
    $("#resourceModuleId").click(function(){  $('#resourceQuestion').html('Resources'); });
    $("#backToQustionClick").click(function(){  $('#resourceQuestion').html('Question'); });

</script>

