<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="com.flp.util.Utility" %>
<%@ include file="header.jsp" %>

<div class="container"> 
<div class="row">
	
<div class="col-md-12">
		
<div class="row">
        
<!--left-panel-start-here-->
<div class="col-md-9">
		
	
		<h2>Learning Zone</h2>
		<div class="learning"><p>Select Your Subject</p></div>
		<div class="row no-gutter">
		<!--custom-tab-start-here-->
		 <div class="col-md-3">
         
         	<div class="row no-gutter">
                <c:forEach var="subject" items="${subjectList}">
                	<div class="col-md-12 subject" onMouseOver="showSubjectDiv('${subject.id}_subId')"><a href="javascript:void(0)" class="border-blue">${subject.name} <label class="glyphicon glyphicon-menu-right pull-right"></label></a></div>
                </c:forEach>
      		 </div>         
           
        </div>
         
         
        <div class="custom-tab-right col-md-9">
                <c:set var="count" value="1"></c:set>
                
   				<c:forEach var="subject" items="${subjectList}">
   					<c:set var="display" value="none;"></c:set>
   					<c:if test="${count == 1}">
   				 		<c:set var="display" value="block;"></c:set>
   					</c:if>
                	<div id="${subject.id}_subId" class="bluebg chapterDiv" style="display:${display}">
		                <h2>${subject.name}</h2>
		                
		                
		                <div class="row">
		              		<c:forEach var="chapter" items="${subject.chapters}">
		              			<div class="col-md-4">
		              			<c:set var="chapterDetails" value="${subject.name}#${chapter.name}#${chapter.id}"></c:set>
		              			<%-- -------	${chapterDetails}------------ --%>
		              			
		              			<%
		              				String str = Utility.encrypt((String)pageContext.getAttribute("chapterDetails"));
		              				/* out.println(str); */
		              			%>
		              			<%-- --------------<%=str %>--------------------- --%>
		              			
					                <p><a id="${chapter.id}" href="${pageContext.request.contextPath}/learning/topic.htm?chapter=<%=str %>">${chapter.name}</a></p>
					            </div>
		              		</c:forEach>
		                </div>
		            </div>
		             <c:set var="count" value="${count +1}"></c:set>
                </c:forEach>
     	</div>
		
        
  	</div>
        <!--custom-tab-start-here-->
        
</div>
<!--left-panel-end-here-->

<!--right-panel-end-here-->
<div class="col-md-3">

<div class="right-panel">
	

<div class="clearfix"></div>






<!--friendlist-start-here-->
<div class="friendlist">

<h4>Friend List</h4>

<ul>
<li><a href="#"><img src="${pageContext.request.contextPath}/resources/images/sulochna.jpg" alt="" /></a><p>Sulochna</p></li>
<li><a href="#"><img src="${pageContext.request.contextPath}/resources/images/pawan.jpg" alt="" /></a><p>Pawan</p></li>
<li><a href="#"><img src="${pageContext.request.contextPath}/resources/images/gaurav.jpg" alt="" /></a><p>Gaurav</p></li>
<li><a href="#"><img src="${pageContext.request.contextPath}/resources/images/manish.jpg" alt="" /></a><p>Manish</p></li>
<li><a href="#"><img src="${pageContext.request.contextPath}/resources/images/arpita.jpg" alt="" /></a><p>Arpita</p></li>
<li><a href="#"><img src="${pageContext.request.contextPath}/resources/images/ruma.jpg" alt="" /></a><p>Ruma</p></li>
<li><a href="#"><img src="${pageContext.request.contextPath}/resources/images/astrik.jpg" alt="" /></a><p>Astrik</p></li>
<li><a href="#"><img src="${pageContext.request.contextPath}/resources/images/sandeep.jpg" alt="" /></a><p>Sandeep</p></li>
<li><a href="#"><img src="${pageContext.request.contextPath}/resources/images/rohan.jpg" alt="" /></a><p>Rohan</p></li>	
</ul>

</div>
<!--friendlist-end-here-->

<div class="clearfix"></div>
<!--Parentlist-start-here-->
<div class="parent">

<h4>Parent Profile</h4>

<a href="#"><img src="${pageContext.request.contextPath}/resources/images/parent.jpg" title="" alt="" /></a>

</div>
<!--Parentlist-end-here-->


<div class="clearfix"></div>

<!--friendlist-start-here-->
<div class="friendlist">

<h4>Friend List</h4>

<ul>
<li><a href="#"><img src="${pageContext.request.contextPath}/resources/images/sulochna.jpg" alt="" /></a><p>Sulochna</p></li>
<li><a href="#"><img src="${pageContext.request.contextPath}/resources/images/pawan.jpg" alt="" /></a><p>Pawan</p></li>
<li><a href="#"><img src="${pageContext.request.contextPath}/resources/images/gaurav.jpg" alt="" /></a><p>Gaurav</p></li>
<li><a href="#"><img src="${pageContext.request.contextPath}/resources/images/manish.jpg" alt="" /></a><p>Manish</p></li>
<li><a href="#"><img src="${pageContext.request.contextPath}/resources/images/arpita.jpg" alt="" /></a><p>Arpita</p></li>
<li><a href="#"><img src="${pageContext.request.contextPath}/resources/images/ruma.jpg" alt="" /></a><p>Ruma</p></li>
<li><a href="#"><img src="${pageContext.request.contextPath}/resources/images/astrik.jpg" alt="" /></a><p>Astrik</p></li>
<li><a href="#"><img src="${pageContext.request.contextPath}/resources/images/sandeep.jpg" alt="" /></a><p>Sandeep</p></li>
<li><a href="#"><img src="${pageContext.request.contextPath}/resources/images/rohan.jpg" alt="" /></a><p>Rohan</p></li>	
</ul>

</div>
<!--friendlist-end-here-->


	
</div>
<!--right-panel-end-here-->

</div>
<!--middle-content-end-here-->

</div>

</div>


</div>

</div>