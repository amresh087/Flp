<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%-- <jsp:include page="studentHeader.jsp" />  --%>
<jsp:include page="../student/studentHeader.jsp" />
<link href="${pageContext.request.contextPath}/resources/css/student/AdminLTE.min.css" rel="stylesheet">

<!-- middle panel -->
<div class="container-fluid ">
	<div class="row">
		<div class="container retail-cont">
			<h2>Intractive Zone</h2>
		
			<!--right side-->
			<div class="col-lg-9 col-md-9 col-sm-8 col-xs-12">
				<div class="retail-question-cont">
					<c:forEach var="query" items="${queries}" varStatus="status">
						<c:if test="${status.count == 1}">
							<div class="retail-question">
								<h3>Q - ${query.query} <span><b class="publish">Dated: <fmt:formatDate type="date" value="${query.insertDate}"/></b></span></h3>
								<p>${query.description}</p>
							</div>
							
							<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 retail-answer-cont">
								<c:forEach var="comment" items="${query.retailUserComments}">
									<div class="retail-answer-cont2">
										<div class="retail-answer" style="border:none !important">${comment.comment} </div>
										<div class="retail-answer-by" style="text-align:right;border-bottom:1px solid #e0e0e0;padding: 0px 0px 10px 0px">
											<span><strong>Answerd by</strong>: ${comment.answeredBy.firstName} ${comment.answeredBy.lastName}</span>  
											<span><strong>Dated: </strong><fmt:formatDate type="date" value="${query.insertDate}"/></span>
										</div>
									</div>
								</c:forEach>
								<textarea rows="5" placeholder="Write your comment here..." style="resize:none"></textarea>
								<div class="btn btn-warning btn-sm pull-right mrb15">YOUR COMMENT</div>
							</div>
						</c:if>
					</c:forEach>
					
					<div class="clearfix"></div>
				</div>
				
				<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 retail-question-post" >
					<h4>Post Your Question Here...</h4>
					<textarea rows="2" placeholder="Write question title here..." class="mrb15" style="resize:none"></textarea><br>
					<textarea rows="5" placeholder="Write question description here..." class="mrb15" style="resize:none"></textarea>
					<div class="btn btn-warning btn-sm pull-right mrb15">SUBMIT</div>
				</div>
			
			</div>
			
			
			<!--right side end-->
			
			
			
			<!--left side-->
			<div class="col-lg-3 col-md-3 col-sm-4 col-xs-12 related-question-cont" >
				<h5>Your Question</h5>
				<div class="related-question">
					<ul class="list-unstyled">
						<c:forEach var="query" items="${queries}" varStatus="status">
							<li><a href="javascript:void(0)">${query.query}</a></li>
						</c:forEach>
					</ul>
				</div>
			</div>
			<!--left side end-->
		</div>
	</div>
</div>
<!-- middle panel end -->   


<jsp:include page="studentFooter.jsp" />
