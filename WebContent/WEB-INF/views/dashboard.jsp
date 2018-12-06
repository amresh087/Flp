<%@ include file="header.jsp" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
 <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!--middle-content-start-here-->
<div class="container"> 
<div class="row">
<div class="col-md-12 content">
	<div class="row">
<!--left-panel-start-here-->
<div class="col-md-9">
		<h2>Dashboard</h2>
		<div class="col-md-12">
		<div class="row"><div class="discription"><p>Suggested Learning</p></div></div>
		</div>
		<div class="clearfix"></div>
		
      <!--MAIN-SLIDER-START_HERE-->
      <div id="team" class="owl-carousel owl-theme">
	  <c:if test="${! empty subjectList}">
	  <c:forEach var="sub" items="${subjectList}" varStatus="status">
	  	<!--ITEM-START_HERE-->
        <div class="item">
          <div class="thumbnail"> 
            <div class="caption">
              <h3>${sub.name }</h3>
            <c:set var="chapterLen" value="${sub.chapters.size() }"></c:set>
			 	<!--CHAPTER-SLIDER-START_HERE-->
             	<div id="Chapter${status.count}" class="carousel slide" data-ride="carousel">
             	
                  <div class="carousel-inner" role="listbox">
                  <c:forEach var="chapter" items="${sub.chapters }" varStatus="status1">
                   <input type="hidden" class="hiddenChapCls${status.count}" value="${chapter.id }">
                  <c:if test="${status1.count eq 1 }">
                   <div class="item no-height active hiddChap${status.count}" id="chapterId${chapter.id}">
                      <p>${chapter.name}</p>
                    </div>
                   
                   <input type="hidden" value="${chapter.id }" id="firstChapter${status.count }"/>
                  </c:if>
                  <c:if test="${status1.count != 1 }">
                   <div class="item no-height hiddChap${status.count}" id="chapterId${chapter.id}">
                      <p>${chapter.name}</p>
                    </div>
                  </c:if>
                  <c:if test="${status1.count eq chapterLen}">
                  <input type="hidden" value="${chapter.id }" id="lastChapter${status.count }"/>
                  <c:set var="lastChapter" value="${chapter.id}"></c:set>
                  </c:if>
                  <c:if test="${status1.count eq 2}">
                  <c:set var="firstChapter" value="${chapter.id}"></c:set>
                  </c:if>
                  </c:forEach>
                  </div>
                <c:if test="${sub.chapters.size()>1 }">
                  <!-- Left and right controls -->
                  <a class="left leftSliderCls" href="#Chapter${status.count}" role="button" data-slide="prev" >

                    <span style="top:-28px; left:-100px;" class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>

                    <span class="sr-only" style="top:0px;">Previous</span>
                  </a>
                  <a class="right rightSliderCls" href="#Chapter${status.count}" role="button" data-slide="next">
                    <span style="top:-28px; right:-100px;" class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
                    <span class="sr-only" >Next</span>
                  </a>
                  </c:if>
                </div>
                <!--CHAPTER-SLIDER-START_HERE-->
                	<c:forEach var="chap" items="${sub.chapters}" varStatus="status1">
                	  <div id="topicx${chap.id}" class="carousel slide chapterSlider${status.count}" data-ride="carousel">
                	<c:if test="${fn:length(chap.topics) > 4 }">  
                  <!-- Indicators -->
                  <ol class="carousel-indicators indicator-custom">
                  <c:set var="topicCount" value="0"></c:set>
                  <c:forEach var="topic" items="${chap.topics }" varStatus="status2">
                  <c:if test="${(topicCount%4) eq 0 }">
                   <c:if test="${status2.count eq 1}">
                    <li data-target="#topicx${chap.id}" data-slide-to="<fmt:parseNumber integerOnly="true" 
                       type="number" value="${(status2.count-1)/4 }" />" class="active"></li>
                    </c:if>
                    <c:if test="${status2.count ne 1}">
                    <li data-target="#topicx${chap.id}" data-slide-to="<fmt:parseNumber integerOnly="true" 
                       type="number" value="${(status2.count-1)/4 }" />" ></li>
                    </c:if>
                    </c:if>
                     <c:set var="topicCount" value="${topicCount+1 }"></c:set>
                     </c:forEach>
                    
                  </ol>
                  </c:if>
                     <div class="carousel-inner" role="listbox">
                   
                       <c:set var="totalTopics" value="${fn:length(chap.topics)}"></c:set>
                      
                       
                       
                      <c:set var="tCount" value="0"></c:set>
                      <c:set var="activeClass" value="active"></c:set>
                  <c:forEach var="topic"  items="${chap.topics}" varStatus="status2">
                   <c:if test="${tCount % 4 == 0}">
                   <c:if test="${status1.count eq 1 }">
                   <div class="item ${activeClass} itemCls${status.count}" id="topicId${chap.id}">
                   </c:if>
                   <c:if test="${status1.count ne 1 }">
                   <div class="item itemCls${status.count}" id="topicId${chap.id }">
                   </c:if>
                   <div class="topic">
                        <ul>
                   
                   </c:if>
                  
                        <c:if test="${(status2.count-1)%4 eq 0 }">
                        <li><a href="#">${topic.name}</a></li>
                        </c:if>
                        <c:if test="${(status2.count-1)%4 eq 1 }">
                        <li class="margin-right topicgreen"><a href="#">${topic.name}</a></li>
                        </c:if>
                        <c:if test="${(status2.count-1)%4 eq 2 }">
                        <li class="topicred"><a href="#">${topic.name}</a></li>
                        </c:if>
                        <c:if test="${(status2.count-1)%4 eq 3 }">
                        <li class="margin-right topicornge"><a href="#">${topic.name}</a></li>
                        </c:if>
                        
                        <c:set var="tCount" value="${tCount+1}"></c:set>
                         <c:set var="activeClass" value=""></c:set>
                         <c:if test="${tCount%4 == 0 || totalTopics == tCount }">
                         </ul>
                        </div>
                        </div>
                         </c:if>
                        
                        
                         </c:forEach>  
                      	 
                    </div>
                    </div>
                     
                    
                    </c:forEach>
                 
         </div>
          </div>
        </div>
        <!--ITEM-END_HERE-->
        
	  </c:forEach>
	  
	  </c:if>
       </div>
      <!--MAIN-SLIDER-END_HERE--> 

		
    	

<div class="clearfix"></div>
		
	<!--bar-graph-start-here-->
	<div class="col-md-3 graph piechart">
							<div id="chartContainer" style="height:300px; width:100%;" ></div>
						</div>
				        <div class="col-md-9 graph"><div class="piechart text-center"><img src="${pageContext.request.contextPath}/resources/images/graoh.gif"></div></div>
        
        
        
	<!--<div class="graph">
		
		<div class="col-md-4 piechart"><div id="chartContainer" class="" style="height:300px; width:100%;" ></div></div>
		<div class="col-md-8 graph"><div class="piechart"><div class="row"><img src="${pageContext.request.contextPath}/resources/images/graoh.gif"></div></div></div>
		
	</div>-->
	
	

	<!--bar-graph-end-here-->



	
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
</div><br /><br /><br />

<%@ include file="footer.jsp" %>
<!--Pie Chart Start Here -->
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/canvasjs.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/piechart.js"></script>

<!--Pie Chart end Here -->    

	
	<!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
     <script src="${pageContext.request.contextPath}/resources/js/bootstrap.js"></script>
    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
	
	

  
   <!-- jQuery Code executes on Date Format option ----->
   <%-- <script src="${pageContext.request.contextPath}/resources/js/script.js"></script> --%>
   
 <script src="${pageContext.request.contextPath}/resources/js/owl.carousel.js"></script> 
<%-- <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/main.js"></script> --%>
<script>

		$(document).ready(function()
		{
			<c:set var="correct" value="${dashboardVO.pieChartReport[0]}"></c:set>
			<c:set var="incorrect" value="${dashboardVO.pieChartReport[1]}"></c:set>
			<c:set var="unattempted" value="${dashboardVO.pieChartReport[2]}"></c:set>
			//alert("${correct}");
			
			//drawPieChat('${correct}', '${incorrect}', '${unattempted}');
			//drawPieChat(49.51551, 40.2514231, 10.461313);
			
			
			
			$(".leftSliderCls").on("click",function(){
				var countId=$(this).attr("href").substr(8);
				var currentChapterId=parseFloat(0);
				$(".hiddChap"+countId).each(function() {
					  var chapterId=$(this).hasClass('active');
					  if(chapterId){
						  currentChapterId=parseFloat($(this).attr("id").substr(9));
					  }
					});
				$(".itemCls"+countId).removeClass('active');
				var firstChap=$("#firstChapter"+countId).val();
				var i=parseFloat(1);
				var newChapterId=parseFloat(0);
				if(firstChap==currentChapterId){
					newChapterId=$("#lastChapter"+countId).val();
				}else{
				$(".hiddenChapCls"+countId).each(function() {
					  var chapterId=parseFloat($(this).val());
					  if(chapterId==currentChapterId){
						 i=2;
					  }
					  if(i=='1' || i==1){
						  newChapterId=chapterId;
						  }
					});
				}
				$("#currentChapterId"+countId).val(newChapterId);
				$(".chapterSlider"+countId).hide();
				$("#topicx"+newChapterId).show();
				$("#topicId"+newChapterId).addClass('active');
				
			});
			$(".rightSliderCls").on("click",function(){
				var countId=$(this).attr("href").substr(8);
				var currentChapterId=parseFloat(0);
				$(".hiddChap"+countId).each(function() {
					  var chapterId=$(this).hasClass('active');
					  if(chapterId){
						  currentChapterId=parseFloat($(this).attr("id").substr(9));
					  }
					  
					});
				$(".itemCls"+countId).removeClass('active');
				var lastChap=$("#lastChapter"+countId).val();
				var i=parseFloat(1);
				
				if(lastChap==currentChapterId){
					newChapterId=$("#firstChapter"+countId).val();
				}else{
				$(".hiddenChapCls"+countId).each(function() {
					  var chapterId=parseFloat($(this).val());
					  if(i=='0' || i==0){
						  i=1;
						  newChapterId=chapterId;
					  }
					  if(chapterId==currentChapterId){
						i=0;
					  }
					});
				}
				$("#currentChapterId"+countId).val(newChapterId);
				$(".chapterSlider"+countId).hide();
				$("#topicx"+newChapterId).show();
				$("#topicId"+newChapterId).addClass('active');
			});
		});
	</script>
 