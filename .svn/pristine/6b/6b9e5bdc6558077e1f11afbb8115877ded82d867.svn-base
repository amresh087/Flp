<html>
	<head>
		<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
		<meta charset="UTF-8">
		<jsp:include page="../globalPageSME.jsp" />		<!-- All js, css, data-table api are defined in globalPage.js -->
		
		<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>	
		 <script src="http://malsup.github.com/jquery.form.js"></script>
		<script>
		var allOldQId = [];
			function getTopics(refObj)
			{
				$('#topicId').empty().append("<option value=''>Select Topic</option>");
				$('#dataId').empty();
				setDropdownList('#topicId', refObj.val(), '${pageContext.request.contextPath}/ajax/rest/getTopics', 'Select Topic', false);
			}
			
			function getChapterss(refObj)
			{
				$('#chapterId').empty().append("<option value=''>Select Chapter</option>");
				$('#topicId').empty().append("<option value=''>Select Topic</option>");
				$('#dataId').empty();
				
				setDropdownList('#chapterId', refObj.val(), '${pageContext.request.contextPath}/ajax/rest/getChapters/'+$('#sectionId option:selected').attr('gradeid'), 'Select Chapter', false);
			}
			
			function getSubjects(refObj)
			{
				$('#subjectId').empty().append("<option value=''>Select Subject</option>");
				$('#chapterId').empty().append("<option value=''>Select Chapter</option>");
				$('#topicId').empty().append("<option value=''>Select Topic</option>");
				$('#dataId').empty();
				setDropdownList('#subjectId', refObj.val(), '${pageContext.request.contextPath}/ajax/rest/getSubjects', 'Select Subject', false);
			}
			
			function getSections(refObj)
			{
				$('#sectionId').empty().append("<option value=''>Select Grade</option>");
				$('#subjectId').empty().append("<option value=''>Select Subject</option>");
				$('#chapterId').empty().append("<option value=''>Select Chapter</option>");
				$('#topicId').empty().append("<option value=''>Select Topic</option>");
				$('#dataId').empty();
				setSectionDropdownList('#sectionId', refObj.val(), '${pageContext.request.contextPath}/ajax/rest/getSectionsForBoard', 'Select Grade');
			}
			
			function getQuestionData()
			{
				
				 
				$('#fullPageloader').show();
				$('#dataId').empty().load('${pageContext.request.contextPath}/admin/questionDataPageStandard/'+$('#topicId').val()+'/'+$('#loId').val()+'.htm',function()
				{
					$('#fullPageloader').hide();
				});
				//$('#fullPageloader').hide();
			}
			
			
			
			
			
			
			function getSectionsIdMap(refObj)
			{
			//	alert('hi');
				$('#sectionIdSourceMap').empty().append("<option value=''>Select Grade To Map From</option>");
				$('#subjectIdMap').empty().append("<option value=''>Select Subject To Map From</option>");
				$('#chapterIdMap').empty().append("<option value=''>Select Chapter To Map From</option>");
				/* $('#topicId').empty().append("<option value=''>Select Topic</option>");
				$('#dataId').empty(); */
				setSectionDropdownList('#sectionIdSourceMap', refObj.val(), '${pageContext.request.contextPath}/ajax/rest/getSectionsForBoard', 'Select Grade To Map From');
			}
			function getSubjectsIdMap(refObj)
			{
				$('#subjectIdMap').empty().append("<option value=''>Select Subject To Map From</option>");
				$('#chapterIdMap').empty().append("<option value=''>Select Chapter To Map From</option>");
				/* $('#topicId').empty().append("<option value=''>Select Topic</option>");
				$('#dataId').empty(); */
				setDropdownList('#subjectIdMap', refObj.val(), '${pageContext.request.contextPath}/ajax/rest/getSubjects', 'Select Subject To Map From', false);
			}
			function getChapterssIdMap(refObj)
			{
				$('#chapterIdMap').empty().append("<option value=''>Select Chapter To Map From</option>");
			/* $('#topicId').empty().append("<option value=''>Select Topic</option>");
			$('#dataId').empty(); */
			
			setDropdownList('#chapterIdMap', refObj.val(), '${pageContext.request.contextPath}/ajax/rest/getChapters/'+$('#sectionIdSourceMap option:selected').attr('gradeid'), 'Select Chapter To Map From', false);
		
			}
			function getTopicsIdMap(refObj)
			{
				var QmapWithTopic=$("#topicId").val();
				
				$('#topicIdMap').empty().append("<option value=''>Select Topic To Map From</option>");
				//$('#dataId').empty();
				setDropdownList('#topicIdMap', refObj.val(), '${pageContext.request.contextPath}/ajax/rest/getTopics', 'Select Topic To Map From', false);
			
				
				getOldQId(QmapWithTopic, '${pageContext.request.contextPath}/ajax/rest/getQuestions');
				
			}
			
			
			
			
			
			
			
			
			
			
			////////////////////////////////
			
			function getloId(refObj)
			{
			
				$('#loId').empty().append("<option value=''>Select lol</option>");
				$('#dataId').empty();
				setDropdownList('#loId', refObj.val(), '${pageContext.request.contextPath}/ajax/rest/getLo', 'Select lol', false);
			
				
				//getQuestionData();
			}
			
			
			
			
			//////////////////////////////////////////////////////////
			
			
			
			function getQuestionDataIdMap(refObj)
			{
				
				
				
				
				
				getQuestions('#questions123', refObj.val(), '${pageContext.request.contextPath}/ajax/rest/getQuestions', 'Select Question To Map From : ', false);
				
			}
			
			
			
			var arrForallQCheckUncheked = [];
			var allQuestions = [];
			function getQuestions(destinatoinId, id, url, emptyMsg, isAllSubject){
				//arrForallQCheckUncheked.;
				
				//alert("hello");
				$(destinatoinId).empty();
				$(destinatoinId).append('<p><option value="">' + emptyMsg + '</option></p>'+'<br>');
				$(destinatoinId).append('<p><input type="checkbox" id="checkAll" value="" checked="checked" onclick = "checkUncheckAll()">' + ' SelectAll Question' + '</input></p>'+'<br>');
				
				
				if(id != null && id != "")
				{
					url = url + "/" + id;
					$.post(url, function(data)
					{
						if(data != null && data.length > 0)
						{
							allQuestions=[];
							
							if(allOldQId != null && allOldQId.length > 0)
							{
							for (var i = 0; i < allOldQId.length; i++)
							{
								console.log( allOldQId[i] );
								//alert("hi"+allOldQId[i]);
							}} 
							
							var isAvilable='false';
							for (var i = 0; i < data.length; i++)
							{
								  var QIDNew=data[i].question.id
								 var QIDOld='';
								 for (var k = 0; k < allOldQId.length; k++)
									{
									  QIDOld=allOldQId[k];
									
									 if(parseInt(QIDOld)==parseInt(QIDNew)){isAvilable='true';break;}else{isAvilable='false'}
										
									}
								
								 allQuestions.push(data[i].question.id);
								 if(isAvilable=='true'){
									 $(destinatoinId).append('<p><input type="checkbox" id="quest'+data[i].question.id+'" value="' + data[i].question.id + '"disabled="true" ></input>' + data[i].question.description +'&nbsp;<font color="red">(mapped)</font><br/> <strong>(Cognitive level : '+data[i].cognitiveLevel.name +'&nbsp;, Difficulty Level : '+data[i].difficultyLevel.name+ ')</strong></p>'+'<br>');
									
								 }else if(isAvilable=='false'){

									 arrForallQCheckUncheked.push(data[i].question.id);
$(destinatoinId).append('<p><input type="checkbox" id="quest'+data[i].question.id+'" value="'+data[i].question.id + '@'+data[i].cognitiveLevelId+'@'+data[i].
			difficultyLevelId+'" checked="checked"></input>'
																			+ data[i].question.description+'<br/>&nbsp; <strong>(Cognitive level : '+data[i].cognitiveLevel.name +'&nbsp;, Difficulty Level : '+data[i].difficultyLevel.name
																			+ ')</strong></p>'
																			+ '<br>');

												}

											}

										}

									}, "json");
				}
			}
			function checkUncheckAll() {

				var checkUncheckAll = document.getElementById('checkAll').checked;
				for (var i = 0; i < arrForallQCheckUncheked.length; i++) {

					$("#quest" + arrForallQCheckUncheked[i]).prop("checked",
							checkUncheckAll);

				}
			}

			function getOldQId(id, url) {

				//alert("hello");
				if (id != null && id != "") {
					url = url + "/" + id;
					$.post(url, function(data) {
						if (data != null && data.length > 0) {
							allOldQId = [];
							for (var i = 0; i < data.length; i++) {
								allOldQId.push(data[i].question.id);
							}

						}

					}, "json");
				}
			}

			var allQuestionsChecked = [];
			function mapQuestionWithTopicId() {
				arrForallQCheckUncheked = [];
				allQuestionsChecked = [];
				//alert("hi");
				var QmapWithTopic = $("#topicId").val();
				for (var i = 0; i < allQuestions.length; i++) {
					var checkVal = document.getElementById("quest"
							+ allQuestions[i]).checked;
					if (checkVal == true) {
						var val = $("#quest" + allQuestions[i]).val();
						allQuestionsChecked.push(val);
					}

				}

				if (allQuestionsChecked != '') {
					sendmapQuestionWithTopicId(
							'${pageContext.request.contextPath}/ajax/rest/mapQuestionWithTopic',
							QmapWithTopic, allQuestionsChecked);
				} else {
					alert('Please select atleast one question for mapping ..!!');
				}

			};
			function sendmapQuestionWithTopicId(url, QmapWithTopic,
					allQuestionsChecked) {

				if (QmapWithTopic != null && QmapWithTopic != "") {
					console.log('Topic ID : ' + QmapWithTopic);
					console.log('Questions : ' + allQuestionsChecked);
					url = url + "/" + QmapWithTopic + "/" + allQuestionsChecked;
					$.post(url, function(data) {

						if (data != null && data == 'success') {
							alert("Question Map successfully !!!");
							$("#questions123").html('');
							$("#topicIdMap").val("");
							$("#chapterIdMap").val("");
							$(".close").trigger("click");
							getQuestionData();
						}

					}, "json");
				}

			};
		</script>
	</head>
	<body>
		<h2 class="text-center">Learn Objective</h2>
		<div style="width:90%;margin:0px auto;text-align:center">
			<select id="boardId" style="width:250px;height:37px" onchange="getSections($(this))">
				<option value='' selected="selected" >Select Board</option>
				<option value="1">CTLS</option>
				<%-- <c:forEach var='board' items="${boardList}">
					<option value="1">CTLS</option>
				</c:forEach> --%>
			</select>
			<select id="sectionId" style="width:250px;height:37px" onchange="getSubjects($(this))">
				<option value="">Select Grade</option>
			</select>
			<select id="subjectId" style="width:250px;height:37px" onchange="getChapterss($(this))">
				<option value="">Select Subject</option>
			</select>
			<select id="chapterId" style="width:250px;height:37px" onchange="getTopics($(this))">
				<option value="">Select Chapter</option>
			</select>
			<select id="topicId" style="width:250px;height:37px" onchange="getloId($(this))">
				<option value="">Select Topic</option>
			</select>
			
			
			<select id="loId" style="width:250px;height:37px" onchange="getQuestionData()">
				<option value="">Select Learn Objective</option>
			</select>
			
			
			
			
		</div><br>
		<div id="dataId"></div>
			<!-- Modal5 Start -->
	<div class="modal fade" id="myModal5" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document" style="width: 60% !important;">
			<div class="modal-content">
		<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<div class="modal-title">
						<span id="headerId"></span> Map Questions
					</div>
				</div>

				<div class="modal-body">
					<div class="col-md-12 col-sm-12 col-xs-12">
					<div class="container">
					
					<div class="col-md-12">
						<select id="boardIdMap" style="width: 60%; height:37px" onchange="getSectionsIdMap($(this))">
						<option value='' selected="selected" >Select Board To Map From</option>
								<c:forEach var='board' items="${boardList}">
									<option value="${board.id}">${board.name}</option>
									</c:forEach>
							</select>
						</div>
					 <%-- <div class="col-md-12">
							<select id="boardIdMap" style="width: 60%; height:37px" onchange="getSectionsIdMap($(this))">
								<option value='' selected="selected" >Select Board To Map From...</option>
								<c:forEach var='board' items="${boardList}">
									<option value="${board.id}">${board.name}</option>
								</c:forEach>
							</select>
							
							
						</div>
					 --%>	
						
						
						<div class="col-md-12">
							<select id="sectionIdSourceMap" style="width: 60%; height:37px" onchange="getSubjectsIdMap($(this))">
								<option value='' selected="selected" >Select Grade To Map From</option>
							</select>
						</div>
						<div class="col-md-12">
							<select id="subjectIdMap" style="width: 60%; height:37px"  onchange="getChapterssIdMap($(this))" >
								<option value='' selected="selected" >Select Subject To Map From</option>
							</select>
						</div>
						<div class="col-md-12">
							<select id="chapterIdMap" style="width: 60%; height:37px" onchange="getTopicsIdMap($(this))" >
								<option value='' selected="selected" >Select Chapter To Map From</option>
							</select>
						</div>
						<div class="col-md-12">
							<select id="topicIdMap" style="width: 60%; height: 37px" onchange="getQuestionDataIdMap($(this))">
								<option value="">Select Topic To Map From</option>
							</select>
						</div>
					</div>
						
						
						<div class="col-md-12">
						<div id="questions123"></div>
						
						</div>
						
					</div>
				</div><br>
				
				<div id="generateTopicId" style="margin-top:60px;padding:15px"></div><br><br>
				<div class="modal-footer">
					<button style="margin-left:45px;" class="btn btn-primary" onclick = "mapQuestionWithTopicId()">Map</button>
				</div>
			</div>
		</div>
	</div>
	<!-- Modal5 End -->
	</body>
</html>