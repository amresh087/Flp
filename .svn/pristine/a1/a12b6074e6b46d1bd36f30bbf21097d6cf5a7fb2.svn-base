<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<c:choose>
	<c:when test="${not empty question}">
		<div class="test-ques col-md-12">
			<h3>${question.getDescription()}</h3>
			<c:if test="${not empty question.imagePath}">
				<div class="col-md-12">
					<img src="${commonPath}/questionImages/${question.imagePath}"
						alt="" />
				</div>
			</c:if>

			<div class="col-md-12">
				<div class="row">
					<ul class="chutiya">
						<c:forEach var="answer" items="${question.answers}"
							varStatus="status">
							<c:set var="ic" value="${answer.isCorrect}" />
							<li id="${answer.id}" ic='${ic}' class="questionCntCls">
								<%-- <input type="radio" id="${answer.id}" ic='${ic}' name="answer">  --%>
								<label for="${answer.id}"> ${answer.getDescription()}</label> <c:if
									test="${not empty answer.imagePath}">
									<img src="${commonPath}/questionImages/${answer.imagePath}"
										alt="" />
								</c:if>


							</li>


						</c:forEach>
					</ul>
				</div>
			</div>
		</div>

		<!--<div class="col-md-1 col-sm-3 col-xs-4"> <div class="btn-danger btn btn-md"><a href="#">Preview</a></div></div>-->
		<div class="col-md-1 col-sm-3 col-xs-4">
			<a href="#"><div class="btn-info btn btn-md"
					onclick="getNextQuestion()">Next</div></a>
			<c:set var="flag" value="true" />
	</c:when>
	<c:otherwise>
		<div class="test-ques col-md-12">
			<h3>No question found!!</h3>

		</div>
		<c:set var="flag" value="false" />

	</c:otherwise>
</c:choose>


<script>

	var v = 0;
	$.ajaxSetup({
		cache : false
	});
	var ss = parseInt(0), mm = parseInt(0), hh = parseInt(0);
	var sec = parseInt(1000);
	var fun = function() {
		if (ss > 0) {
			ss++;
			if (ss > 59) {
				ss = parseInt(0);
				mm++;
				if (mm > 59) {
					mm = parseInt(0);
					hh++;
					if (hh > parseInt(23)) {
						hh++;
					}
				}
			}
			$("#ss").val(ss >= 10 ? ss : parseInt(0) + ss.toString());
			$("#mm").val(mm >= 10 ? mm : "0" + mm.toString());
			$("#hh").val(hh >= 10 ? hh : "0" + hh.toString());
		} else {
			ss = parseInt(1);
			$("#ss").val("0" + ss.toString());
		}

	}
	var timer = setInterval(fun, (sec));
	$(document).ready(function() {
		if ('${flag}' != 'false')
			$('#genScore').empty().append('${score}');
		else {
			clearInterval(timer);
		}

	});

	var isSelect = parseInt(0);
	var isAttempted = parseInt(0);
	var isCorrect = parseInt(0);
	var resp = "";
	var flag = false;
	$(".questionCntCls")
			.on(
					"click",
					function() {
						if (isSelect == 0 || isSelect == '0') {
							flag = true;
							var correctOrIncorrect = $(this).attr('ic');
							var userValues = $(this).attr('id');
							if (correctOrIncorrect == 1
									|| correctOrIncorrect == "1") {
								isAttempted = 1;
								isCorrect = 1;
								correctCount++;
							} else {
								isAttempted = 1;
								isCorrect = 0;
							}
							isSelect++;
							$(this).css("background", "#f68929").css("border",
									"1px solid #cf6f1a");
							var ss1 = parseFloat($("#ss").val());
							var mm1 = parseFloat($("#mm").val());
							if (mm1 > 0) {
								ss1 = ss1 + mm1 * 60;
							}
							var hh1 = parseFloat($("#hh").val());
							if (hh1 > 0) {
								ss1 = ss1 + hh1 * 60 * 60;
							}
							saveQuestion(isAttempted, isCorrect, correctCount,
									ss1, userValues); 
							var val = '';
							if (isAttempted == 1 && isCorrect == 1) {
								val = '<li class="question-right" style="cursor: pointer;"><i class="fa fa-check" aria-hidden="true"></i> </li>';
							} else if (isAttempted == 1 && isCorrect == 0) {
								val = ' <li class="question-wrong" style="cursor: pointer;"><i class="fa fa-times" aria-hidden="true"></i></li>';
							}
							$("#questStatusId").append(val);
						} else {
							alert("You have already Select Answer");
							return false;
						}

					})

	function getNextQuestion() {

		
		if (!flag || flag == false) {
			var ss1 = parseFloat($("#ss").val());
			var mm1 = parseFloat($("#mm").val());
			if (mm1 > 0) {
				ss1 = ss1 + mm1 * 60;
			}
			var hh1 = parseFloat($("#hh").val());
			if (hh1 > 0) {
				ss1 = ss1 + hh1 * 60 * 60;
			}
			 saveQuestion(isAttempted, 0, correctCount, ss1, ""); 
			var val = '';
		if (isAttempted == 0) {
			val = '<li class="skip" style="cursor: pointer;"><i class="fa fa-question" aria-hidden="true"></i></li>';
		}
		$("#questStatusId").append(val)
		}

		$('#questionDesc').empty();
		$
				.get(
						"${pageContext.request.contextPath}/student/getNextQuestion?topicId=${param.topicId}&sId=${param.sId}&subjectName=${param.subjectName}",
						function(data) {
							$('#questionDesc').html(data);
						});
		//$('#questionDesc').empty().load("${pageContext.request.contextPath}/student/getNextQuestion?topicId=${param.topicId}&sId=${param.sId}&subjectName=${param.subjectName}");
		$("#ss").val('00');
		$("#mm").val('00');
		$("#hh").val('00');
		clearInterval(timer);
		
	}

	function saveQuestion(isAttempted, isCorrect, correctCount, ss1, userValues) {
		var url = "${pageContext.request.contextPath}/student/saveQuestion";
		var data = {
			topicId : '<c:out value="${param.topicId}"/>',
			questionId : '<c:out value="${question.id}"/>',
			isAttempted : isAttempted,
			isCorrect : isCorrect,
			correctCount : correctCount,
			timeTaken : ss1,
			locount:'1'
		}
		$
				.ajax(

				{
					type : "post",
					url : url + "?userValues=" + userValues,
					data : data

				})
				.done(
						function(res) {

							console.log(v++);
							if (parseInt(cGscore) != parseInt(res)) {
								correctCount=parseInt(0);
								cGscore = res;
								if(parseInt(cGscore)==32){
									correctCount=0;
								}
								switch (res) {
								case 20:
									location.href = "${pageContext.request.contextPath}/student/getTopicSubmissionMessage.htm?topicId=${param.topicId}&sId=${param.sId}&case=20&subjectName=${param.subjectName}";
									break;
								case 40:
									location.href = "${pageContext.request.contextPath}/student/getTopicSubmissionMessage.htm?topicId=${param.topicId}&sId=${param.sId}&case=1&subjectName=${param.subjectName}";
									break;

								case 100:
									location.href = "${pageContext.request.contextPath}/student/getTopicSubmissionMessage.htm?topicId=${param.topicId}&sId=${param.sId}&case=2&subjectName=${param.subjectName}";
									break;
									default:
										location.reload();

								$("#ss").val('00');
								$("#mm").val('00');
								$("#hh").val('00');
								clearInterval(timer);
								break;
							}

						}

						ss = parseFloat(0);
					}).fail(function() {

					alert("error");

				});
	}
</script>
