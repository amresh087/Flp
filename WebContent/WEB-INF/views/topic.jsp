<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!--middle-content-start-here-->
<%@ include file="header.jsp" %>
<!--middle-content-start-here-->
<div class="container-fluid sub-main-con ">
<div class="row">

<div class="col-md-3 col-md-offset-1  col-sm-offset-1 col-sm-4 sub-con">
<div class="row">
<div class="col-md-10">
<div class="row">
<h5>${subjectName}</h5>
<h3>${chapterName}</h3>
<div class="money_control">
<img src="${pageContext.request.contextPath}/resources/images/money_control.png" alt=""/>
</div>
</div>
</div>
</div>
</div>

<div class="col-md-8">
<div class="row">
<div class="col-md-12 col-sm-12 col-xs-12 sub-main-con-white pull-right">
<div class="row">

<div class="col-md-11">
<div class="sub-basic">
<ul class="list-unstyled ">
	<c:forEach items="${topicList}" var="topic">
		<c:set var="topicType" value="allTopic"></c:set>
	    <c:if test="${not empty demoTopicIds}">
	     	<c:set var="topicType" value="nonDemoTopic"></c:set>
			<c:forEach items="${demoTopicIds}" var="demoTopicId">
				<c:if test="${demoTopicId == topic.id}">
					<c:set var="topicType" value="demoTopic"></c:set>
				</c:if>
			</c:forEach>
		</c:if>
	
		<li class="first">
			<div class="topic_img"><img src="${pageContext.request.contextPath}/resources/images/icon-topic.png" alt=""/></div>
			<div class="topic">
			<a href="${pageContext.request.contextPath}/getNextQuestionBySS?topicId=${topic.id}&ss=-1" >${topic.name}</a>
			
			</div>
			<c:if test="${topicType == 'nonDemoTopic'}">
				<div class="lock"><a href="#"><img src="${pageContext.request.contextPath}/resources/images/lock.png" alt=""/></a></div>
			</c:if>
		</li>
	</c:forEach>
</ul>

</div>
</div>

</div>
</div>
</div>
</div>



</div>
</div>
<!--middle-content-end-here-->