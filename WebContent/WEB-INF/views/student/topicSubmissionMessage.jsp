<jsp:include page="../student/studentHeader.jsp" />
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>



<!-- middle panel -->
<c:choose>
<c:when test="${ msg eq 40}">
<div class="container-fluid mrt20 question-con">
<div class="container text-center">
<div class="row">
<div class="message">
<img src="${pageContext.request.contextPath}/resources/images/smiley_face.jpg" alt="" class="mrt20 mrb20"/>
<p class="mrt20 mrb20"> Congratulations !!!, You have passed the learn level and achieved 40 Lx " . "Keep Learning" </p>

<div class="btn btn-warning mrb10"> <a href="${pageContext.request.contextPath}/student/getQuestionAssessmentPage.htm?topicId=${topic.id }&chapterId=${topic.chapter.id }&sId=${topic.chapter.subject.id}&subjectName='${topic.chapter.subject.name }&topicName=${topic.name}">Continue Practice</a></div>
<div class="btn btn-warning mrb10">
<c:choose>
<c:when test="${sessionScope.user.userType eq 2 && not empty sessionScope.user.schoolId }">
<a href="${pageContext.request.contextPath}/student/subjectDetail.htm?sid=${param.sId}">Study New Chapter</a></c:when>
<c:otherwise><a href="${pageContext.request.contextPath}/user/subjectDetail.htm?sid=${param.sId}">Study New Chapter</a></c:otherwise>

</c:choose></div>


</div>

</div>
</div>
</div>
</c:when>

<c:when test="${msg eq 100}">
<div class="container-fluid mrt20 question-con">
<div class="container text-center">
<div class="row">
<div class="message">
<img src="${pageContext.request.contextPath}/resources/images/smiley_face.jpg" alt="" class="mrt20 mrb20"/>
<p class="mrt20 mrb20">"You have passed the Practice level and now you will attempt the Mastery mode questions that will be little difficult questions to reach the 100 Lx. All the best !!!"</p>

<div class="btn btn-warning mrb10"> <a href="${pageContext.request.contextPath}/student/getQuestionAssessmentPage.htm?topicId=${topic.id }&chapterId=${topic.chapter.id }&sId=${topic.chapter.subject.id}&subjectName='${topic.chapter.subject.name }&topicName=${topic.name}">Continue Practice</a></div>
<div class="btn btn-warning mrb10">
<c:choose>
                                        <c:when test="${sessionScope.user.userType eq 2 && not empty sessionScope.user.schoolId }">
<a href="${pageContext.request.contextPath}/student/subjectDetail.htm?sid=${param.sId}">Study New Chapter</a></c:when>
<c:otherwise><a href="${pageContext.request.contextPath}/user/subjectDetail.htm?sid=${param.sId}">Study New Chapter</a></c:otherwise>

</c:choose></div>


</div>

</div>
</div>
</div>

</c:when>

<c:when test="${msg eq 150}">
<div class="container-fluid mrt20 question-con">
<div class="container text-center">
<div class="row">
<div class="message">
<img src="${pageContext.request.contextPath}/resources/images/smiley_face.jpg" alt="" class="mrt20 mrb20"/>
<p class="mrt20 mrb20">"Congratulations !!!,. "Keep Learning"  ..! you have achieved 50 Lx in Mastery and your total Lx is 150. All the best !!!"</p>

<div class="btn btn-warning mrb10"> <a href="${pageContext.request.contextPath}/student/getQuestionAssessmentPage.htm?topicId=${topic.id }&chapterId=${topic.chapter.id }&sId=${topic.chapter.subject.id}&subjectName='${topic.chapter.subject.name }&topicName=${topic.name}">Continue Practice</a></div>
<div class="btn btn-warning mrb10">
<c:choose>
                                        <c:when test="${sessionScope.user.userType eq 2 && not empty sessionScope.user.schoolId }">
<a href="${pageContext.request.contextPath}/student/subjectDetail.htm?sid=${param.sId}">Study New Chapter</a></c:when>
<c:otherwise><a href="${pageContext.request.contextPath}/user/subjectDetail.htm?sid=${param.sId}">Study New Chapter</a></c:otherwise>

</c:choose></div>


</div>

</div>
</div>
</div>

</c:when>




<c:when test="${msg eq 200}">

<div class="container-fluid mrt20 question-con">
<div class="container text-center">
<div class="row">
<div class="message">
<img src="${pageContext.request.contextPath}/resources/images/smiley_face.jpg" alt="" class="mrt20 mrb20"/>
<p class="mrt20 mrb20">"You have passed the Mastery level and now you will attempt the challenge mode questions that will be more difficult questions to reach the more than 200 Lx . All the best !!!"</p>

<%-- <div class="btn btn-warning mrb10"> <a href="${pageContext.request.contextPath}/student/getQuestionAssessmentPage.htm?topicId=${topicId}&sId=${param.sId}&subjectName=${param.subjectName}&topicName=${param.topicName}">Continue Practice</a></div> --%>
<div class="btn btn-warning mrb10"> <a href="${pageContext.request.contextPath}/student/subjectDetail.htm?sid=${param.sId}">Study New Chapter</a></div>
</div>

</div>
</div>
</div>
</c:when>
</c:choose>
<!-- middle panel end --> 