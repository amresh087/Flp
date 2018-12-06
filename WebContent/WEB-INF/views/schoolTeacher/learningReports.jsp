<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<jsp:include page="schoolTeacherHeader.jsp" />
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.progress.js"></script>
<div id="page-wrapper">
	<div class="container-fluid">
		<div class="row">
			<div class="col-md-12">
				<h1>Learning Report Details</h1>
				<ol class="breadcrumb">
					<li><a
						href="${pageContext.request.contextPath}/schoolTeacher/teacherHome.htm">Home</a></li>
					<li class="active">Learning Report</li>
				</ol>
			</div>
		</div>

		<!--assign-task-test-->
		<div id="assign-task" class="tab-pane active ">
			<div class="col-md-3 col-sm-4" style=" padding-top: 6px;">>
				Grade <select name="grade" id="gradeId1"
					onchange="populateSection(this.value)">
					<option value="">Select Grade</option>
					<c:if test="${not empty teacherSection }">
						<c:forEach var="ts" items="${teacherSection}">
							<option class="clsGrade"
								value='${ts.section.sessionSchoolGrade.grade.id}'>${ts.section.sessionSchoolGrade.grade.name}</option>
						</c:forEach>
					</c:if>

				</select>
			</div>
			<div class="col-md-3 col-sm-4" style=" padding-top: 6px;">
				Section <select id="sectionId"  class="getLearningReportsCls"
					onchange="setDropdownList('#subjectId', $(this).val(), '${pageContext.request.contextPath}/ajax/rest/getSubjectBySectionId', 'Select Subject', false)" dir="reportsBySubject" >
					<option value="">Select Section</option>

				</select>
			</div>

			<div class="col-md-3 col-sm-4" style="background-color:#DDEBF9; padding-top: 6px;">
				Subject (Chapter Based Report) <select id="subjectId" dir="reportsByChapter" class="getLearningReportsCls">
					<option value="">Select Subject</option>

				</select>
			</div>

			<div class="col-md-3 col-sm-4" style="background-color:#DDEBF9; padding-top: 6px;">
				Chapter (Topic Based Report) <select id="chapterId" dir="reportsByTopic" class="getLearningReportsCls">
					<option value="">Select Chapter</option>

				</select>
			</div>
			<div style="clear: both; margin-bottom: 10px;"></div>
			<div style="color: red; margin-bottom: 10px;" id="errorId"></div>
		<!-- 	<div class="col-md-2 col-sm-4">

				<a href="javascript:void(0)"
					class="btn btn-warning getLearningReportsCls" >Get
					SubjectWise Report</a>
			</div>
			<div class="col-md-2 col-sm-4">

				<a href="javascript:void(0)" class="btn btn-warning getLearningReportsCls"
					>Get ChapterWise Report </a>
			</div>
			<div class="col-md-2 col-sm-4">

				<a href="javascript:void(0)" class="btn btn-warning getLearningReportsCls"
					>Get TopicWise Report</a>
			</div> -->

		</div>
	</div>
</div>
<div id="reportsId"></div>
<script type="text/javascript">
	$(document).ready(function()
    {
	    
	    $('.clsGrade').each(function()
	    {
		    var val = $(this);
		    var flag = false;
		    $('.clsGrade').each(function()
		    {
			    if($(this).val() == val.val())
				    if(!flag)
					    flag = true;
				    else
					    $(this).remove();
		    });
	    });
	    
    });
    
    $(".getLearningReportsCls").on("change", function()
    {
    	
	    $("#errorId").empty();
	    var id = $(this).val();
	    var type = $(this).attr('dir');
	 if(id!=""){
	    $("#fullPageloader").show();
	    $.post("${pageContext.request.contextPath}/schoolTeacher/getLearningReportBySubjctOrChapterOrTopic/" + id + "/" + type, function(data)
	    {
		    $("#reportsId").html(data);
		    $("#fullPageloader").hide();
	    });
	 }
	    
    });
    
    $("#sectionId").on("change", function()
    {
	    $("#reportsId").empty();
	    $("#subjectId").empty().append('<option value="">Select Subject</option>');
	    $("#chapterId").empty().append('<option value="">Select Chapter</option>');
    });
    

    $("#chapterId").on("change", function()
    {
	    $("#reportsId").empty();
	  
    });
    
    
    $("#subjectId").on("change", function()
    {
	    $("#chapterId").empty();
	    $("#reportsId").empty();
	    $("#chapterId").empty().append('<option value="">Select Chapter</option>');
	    var subjectId = $("#subjectId").val();
	    if(subjectId != null && subjectId != "")
	    {
		    $.post("${pageContext.request.contextPath}/ajax/rest/getChapters/" + $("#gradeId1").val() + "/" + subjectId, function(data)
		    {
			    if(data != null && data.length > 0)
			    {
				    var val = '';
				    for (var i = 0; i < data.length; i++)
				    {
					    val = val + '<option value="'+data[i].id+'">' + data[i].name + '</option>';
				    }
				    $("#chapterId").append(val);
			    }
		    });
	    }
    });
    
    
    
    function populateSection(gradeId)
    {
	    $("#subjectId").empty().append('<option value="">Select Subject</option>');
	    $("#chapterId").empty().append('<option value="">Select Chapter</option>');
	    $('#sectionId').empty().append("<option value=''>Select Section</option>");
	    $("#reportsId").empty();
	    if(gradeId != '' && !isNaN(gradeId))
	    {
		    <c:forEach var="ts" items="${teacherSection}">
		    if('${ts.section.sessionSchoolGrade.grade.id}' == gradeId)
		    {
			    $('#sectionId').append("<option value='${ts.section.id}' class='clsSection'>${ts.section.name}</option>");
		    }
		    </c:forEach>
		    $('.clsSection').each(function()
		    {
			    var val = $(this);
			    var flag = false;
			    $('.clsSection').each(function()
			    {
				    if($(this).val() == val.val())
					    if(!flag)
						    flag = true;
					    else
						    $(this).remove();
			    });
		    });
	    }
	    
    }
</script>