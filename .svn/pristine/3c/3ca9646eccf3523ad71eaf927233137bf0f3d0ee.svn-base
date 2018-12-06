<html>
	<head>
		<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
		<meta charset="UTF-8">
		<jsp:include page="../globalPage.jsp" />		<!-- All js, css, data-table api are defined in globalPage.js -->
		
		<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>	
		
	</head>
	<body>
		<h2 class="text-center">Upload Download Questions Through Grade Subject-wise</h2>
		<div style="width:40%;margin:0px auto;text-align:center">
		
		<button type="submit" class="btn btn-warning" data-toggle="modal" data-target="#uploadExcel" id="questionUpload">Import From Excel</button>
		<button style="float:right" type="button" class="btn btn-warning" data-toggle="modal" data-target="#excelFormat" id="exportExcel">Download Excel Format</button><br><br>
		
		
		
		<%-- 	<form id="questionUploadForm" method="post" action="${pageContext.request.contextPath}/admin/uploadQuestionGradeSubjectWise.htm" enctype="multipart/form-data">
						<!-- File input -->
						<input name="gradeWiseExcel" id="excelFileId" type="file" /><br />
						
						<a class="btn btn-warning" href="javascript:void(0)" onclick="uploadQuestionExcelForm()">Upload</a>
					</form>
		 --%>
			</div>
		
		
		
		<!-- excelModal -->
	<div class="modal fade" id="uploadExcel" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">Upload from Excel</h4>
				</div>
	
				<div class="clearfix"></div>
				<div class="modal-body" style="border-bottom: none !important">
					<span id="studentExcelId"></span>
					<form id="questionUploadForm" method="post" action="${pageContext.request.contextPath}/admin/uploadQuestionGradeSubjectWise.htm" enctype="multipart/form-data">
						<!-- File input -->
						<input name="gradeWiseExcel" id="excelFileId" type="file" /><br />
						
						<a class="btn btn-warning" href="javascript:void(0)" onclick="uploadQuestionExcelForm()">Upload</a>
					</form>
					<div id="result"></div>
				</div>
			</div>
		</div>
	</div>
	<!-- excelModal -->
			
			
			<!-- excelFormaModal -->
	<div class="modal fade" id="excelFormat" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">Download Excel Format</h4>
				</div>
	
				<div class="clearfix"></div>
				<div class="modal-body" style="border-bottom: none !important">
					<ul>
						<li>Please do not delete header row from sheet.</li>
					</ul>
					<div style="text-align:center"><a class="btn btn-warning" href="${pageContext.request.contextPath}/admin/downloadQuestionExcelFormatGradewise">Download</a></div>
				</div>
			</div>
		</div>
	</div>
	<!-- excelFormatModal -->	
			
			
			<script type="text/javascript">
			
			$(".close").click(function(){
				$("#excelFileId").val("");
			});
			
			function uploadQuestionExcelForm()
			{
				var selectedFile = $('#excelFileId').val();
				
				if(selectedFile == '')
				{
					alert("Please select file");
					return false;
				}
				
				var extension = selectedFile.substring(selectedFile.lastIndexOf(".")+1, selectedFile.length);
				if(extension != 'xlsx' && extension != 'xls')
				{
					alert("Please select excel format file");
					return false;
				}
			
				$("#questionUploadForm").submit(function(e)
				{
					
					$('#fullPageloader').show();
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
							$('#fullPageloader').hide();
							if(data=="success")
							{
								alert("Question uploaded successfully");
								$("#excelFileId").val("");
						    	$('.close').trigger('click');
						    	// To hide the pop-up background
							}
							else
								alert(data);
							
							
						},
						error:function()
						{
							$('#fullPageloader').hide();
						}
					}); 
					e.preventDefault();				//Stop default action
					$(this).unbind("submit");	
					// Unbind to stop multiple form submit
				});
				$("#questionUploadForm").submit();		// Submit the form.	
			}
			</script>
	</body>
	
	
		<script>
		
		</script>
</html>