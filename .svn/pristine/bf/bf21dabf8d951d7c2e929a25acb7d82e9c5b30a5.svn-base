<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="../student/studentHeader.jsp" />
<!-- middle panel -->
<div class="container-fluid bred-cont">
	<div class="container">
		<div class="row">
			<ul class="breadcrumb">
				<c:forEach var="sub" items="${sessionScope.user.subjects }"
					varStatus="status">

					<c:if test="${subjectId eq sub.id}">
						<li>Browse ${sub.name }<input type="hidden"
							id="hiddenSubjectName" value="${sub.name }" /></li>
					</c:if>

				</c:forEach>
			</ul>
		</div>
	</div>
</div>


<div class="container-fluid mrt20 question-con">
	<div class="container">
		<div class="row">
			<div class="col-md-9 col-sm-8 col-xs-12">
				<div class="row">
					<div class="panel-group" id="accordion">

						<c:if test="${not empty chapterList }">
							<a href="#" data-toggle="modal" data-target="#myModal"
								style="display: none;" id="hiddenTopicId"></a>
							<c:set var="count" value="0" />
							<c:forEach var="chapters" items="${chapterList }"
								varStatus="status">

								<div class="panel panel-default panel-cont accordion-toggle"
									data-toggle="collapse" data-parent="#accordion"
									href="#collapse${status.count }">

									<c:if test="${status.count eq 1}">
										<div class="panel-heading panel-head-active">
									</c:if>
									<c:if test="${status.count ne 1 }">
										<div class="panel-heading">
									</c:if>


									<h4 class="panel-title">
										<b class="mrr10 pull-left"><img
											src="${pageContext.request.contextPath}/resources/images/arrow.png"
											class="arrowCls" /></b>
										<div class="topic-question-name">${chapters.name }</div>
										<span class="glyphicon glyphicon-plus pull-right mrl10"></span>
										<div class="pro-bar-cont pull-right">
											<p class="percentCls">${chapters.resultPercent}%
												Completed</p>
											<div class="progress">
												<div class="progress-bar" role="progressbar"
													aria-valuenow="70" aria-valuemin="0" aria-valuemax="100"
													style="width: ${chapters.resultPercent}%"></div>
											</div>
										</div>
									</h4>
								</div>
								<c:if test="${status.count eq 1}">
									<c:set var="count" value="${count+1 }" />
									<div id="collapse${status.count }"
										class="panel-collapse collapse in">
								</c:if>
								<c:if test="${status.count ne 1}">
									<div id="collapse${status.count }"
										class="panel-collapse collapse">
								</c:if>
								<div class="panel-body">

									<ul class="list-unstyled discussion">
										<c:forEach var="topic" items="${chapters.topicSet }"
											varStatus="status1">
											<li><b class="fa fa-chevron-right mrr10"></b><a
												href="javascript:void(0)" id="${topic.id }" class="topicCls"
												dir="${status.count }${status1.count }">${topic.name }</a> <input
												type="hidden"
												id="queCountId${status.count }${status1.count }"
												value="${topic.count }" dir="${topic.percent}">
												<div id="diagram-id${status.count }${status1.count }"
													class="diagram pull-right"></div></li>
											<c:if test="${topic.percent le 30 }">
												<script type="text/javascript">
													$('#diagram-id${status.count}${status1.count}').circleDiagram(
                                                    {
                                                        "percent" : "${topic.percent}%",
                                                        "size" : "50",
                                                        "borderWidth" : "4",
                                                        "bgFill" : "#e2e2e2",
                                                        "frFill" : "red",
                                                        "textSize" : "12",
                                                        "textColor" : "red"
                                                    });
												</script>
											</c:if>
											<c:if test="${topic.percent gt 30 && topic.percent le 70}">
												<script type="text/javascript">
													$('#diagram-id${status.count}${status1.count}').circleDiagram(
                                                    {
                                                        "percent" : "${topic.percent}%",
                                                        "size" : "50",
                                                        "borderWidth" : "4",
                                                        "bgFill" : "#e2e2e2",
                                                        "frFill" : "orange",
                                                        "textSize" : "12",
                                                        "textColor" : "orange"
                                                    });
												</script>
											</c:if>
											<c:if test="${topic.percent gt 70}">
												<script type="text/javascript">
													$('#diagram-id${status.count}${status1.count}').circleDiagram(
                                                    {
                                                        "percent" : "${topic.percent}%",
                                                        "size" : "50",
                                                        "borderWidth" : "4",
                                                        "bgFill" : "#e2e2e2",
                                                        "frFill" : "#289e25",
                                                        "textSize" : "12",
                                                        "textColor" : "#289e25"
                                                    });
												</script>
											</c:if>
										</c:forEach>
										<!--  <li data-toggle="modal" data-target="#myModal"><b class="glyphicon glyphicon-chevron-right mrr10"></b>Class discussion<div id="diagram-id-2" class="diagram pull-right"></div></li>
        
        <li data-toggle="modal" data-target="#myModal"><b class="glyphicon glyphicon-chevron-right mrr10"></b>Class discussion <div id="diagram-id-3" class="diagram pull-right" ></div></li> -->
									</ul>



								</div>
					</div>
				</div>

				</c:forEach>

				</c:if>
				<c:if test="${empty chapterList }">
					<div class="alert alert-info">No Record Found!!!</div>
				</c:if>
			</div>
		</div>
	</div>


	<div class="col-md-3 col-xs-12 col-sm-4 ">
		<div class="col-md-12 hidden-xs sub-side-cont">
			<div class="row">
				<div class="side-head">
					<img
						src="${pageContext.request.contextPath}/resources/images/sub-icon.png"
						alt="" /> Choose your Subject
				</div>
				<ul class="list-unstyled">

					<c:if test="${not empty sessionScope.user.subjects }">
						<c:forEach var="sub" items="${sessionScope.user.subjects }"
							varStatus="status">

							<c:if test="${subjectId eq sub.id}">
								<li><a
									href="${pageContext.request.contextPath}/student/learningReports.htm?sid=${sub.id}"
									class="sub-side-cont-active"><b
										class="fa fa-book mrr10"></b>${sub.name }</a></li>
							</c:if>
							<c:if test="${subjectId ne sub.id}">
								<li><a
									href="${pageContext.request.contextPath}/student/learningReports.htm?sid=${sub.id}"><b
										class="fa fa-book mrr10"></b>${sub.name }</a></li>
							</c:if>
						</c:forEach>

					</c:if>

				</ul>
			</div>
		</div>
	</div>

</div>
</div>
</div>
<!-- Modal -->
<div id="myModal" class="modal fade" role="dialog">
	<div class="modal-dialog">

		<!-- Modal content-->
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<div class="modal-title">
					<span>Student Reports</span> on the Topic
				</div>
			</div>
			<div class="modal-body">
				<table width="90%" border="0" cellspacing="0" cellpadding="0"
					class="topic-per table table-bordered table-hover table-condensed">
					<tr>
						<td style="width: 50%;"><strong>Topic Name:</strong></td>
						<td style="width: 50%;"><span id="topicNameId"></span></td>
					</tr>
					<tr>
						<td><strong>Total Question:</strong></td>
						<td><span id="totalQusId"></span></td>
					</tr>
					<tr>
						<td><strong>Total Attempt:</strong></td>
						<td><span id="attemptCount"></span></td>
					</tr>
					<tr>
						<td><strong>Total Correct:</strong></td>
						<td><span id="correctCount"></span></td>
					</tr>
					<tr>
						<td><strong>Total Incorrect:</strong></td>
						<td><span id="incorrectCount"></span></td>
					</tr>
					<tr>
						<td><strong>Total Skip:</strong></td>
						<td><span id="skipCount"></span></td>
					</tr>
							<tr>
						<td><strong>Gen Score:</strong></td>
						<td><span id="genScoreId"></span></td>
					</tr>
					<tr>
						<td><strong>Overall Percentage (%):</strong></td>
						<td>
							<!-- <span id="percentageId"></span> -->

							<div class="progress" style="height: 20px;">
								<div
									class="progress-bar progress-bar-success progress-bar-striped"
									aria-valuemin="0" aria-valuemax="100" id="percentageId"
									></div>
							</div>
						</td>
					</tr>
				</table>


			</div>

		</div>

	</div>
</div>

<script type="text/javascript">
	$(".topicCls").on("click", function(e)
    {
	    e.preventDefault();
	    e.stopPropagation();
	    var id = $(this).attr("id");
	    var count = $(this).attr('dir');
	    var topicName = $(this).html();
	    var totalQus = $("#queCountId" + count).val();
	    $("#topicNameId").html(topicName);
	    $("#totalQusId").html(totalQus);
	    $.post("${pageContext.request.contextPath}/ajax/rest/getStudentLearningReports/" + id + "/topicwise", function(data)
	    {
		    var val = data.split("-");
		    $("#attemptCount").html(val[0]);
		    $("#correctCount").html(val[1]);
		    $("#incorrectCount").html(val[2]);
		    $("#skipCount").html(val[3]);
		    $("#genScoreId").html(val[4]);
		    var percentage = $("#queCountId" + count).attr('dir');
		    $("#percentageId").html(percentage + "%");
		    $("#percentageId").css('width', percentage + "%");
		    $("#percentageId").attr('aria-valuenow', percentage);
	    });
	    $("#hiddenTopicId").trigger("click");
    });
</script>

<!-- jQuery -->
<!-- middle panel end -->
<jsp:include page="studentFooter.jsp" />

