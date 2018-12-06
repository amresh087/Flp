<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <!-- Modal content-->
   	<div class="row">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <div class="modal-title"><span></span> ${assessment.testName }</div>
      </div>
      <div class="modal-body assign-name-pop-mid panel-body2">
      <c:if test="${not empty assessment}">
 <div class="col-md-4 mrt15"><strong>Test start date :</strong> <fmt:formatDate pattern="dd MMM yyyy"
												value="${assessment.startDate}" /> <br>
												<strong>Test end date :</strong>
												<fmt:formatDate pattern="dd MMM yyyy"
												value="${assessment.endDate}" /> <br>
								<c:if test="${assessment.duration > 0}">
								<strong>Time :</strong>
								<c:choose>
								<c:when test="${(assessment.duration/60) >= 1}">
								<fmt:formatNumber  value="${assessment.duration/60}" pattern="##" /> 
								</c:when>
								<c:otherwise>
								<fmt:formatNumber  value="00" pattern="##" />
								</c:otherwise>
								</c:choose>
								
								  :<fmt:formatNumber  value="${assessment.duration mod 60}" pattern="#"  /> (H:M)
								</c:if>		
								 <br>		
          <%--  <a href="${pageContext.request.contextPath}/downloadPDF/${assessment.id}.htm" class="btn btn-xs btn-success mrt10" >Generate Pdf</a> --%>
           </div>
        
        <div class="col-md-4 txt-cen mrt15"><h3></h3>
          (<strong>Grade :</strong> ${assessment.grade.name })
          <div class="col-md-12 txt-cen mrt15">
          <strong>Subject :</strong> ${assessment.subject.name }</div>
          </div>
          
           <%-- <div class="col-md-4 txt-cen mrt15"><h3></h3>
          <strong>Subject :</strong> ${assessment.subject.name }</div>  --%>
            
         <div class="col-md-4 txt-right mrt15 "><strong>Test Type :</strong> <c:if test="${assessment.type eq 1 }">E-Test</c:if> <c:if test="${assessment.type eq 2 }">Assignment</c:if> <br>
         <strong>Total Questions :</strong> ${assessment.assessmentQuestion.size() } <br>
         <strong>Maximum Marks :</strong> ${assessment.maxMarks } <br>
        
           </div>    
        
         <div class="col-md-12 mrt15">
         <c:if test="${not empty assessment.instruction}">
        <h4> General Instructions:</h4>

 
 
${assessment.instruction}
</c:if>
 <br/>
  <br/>

 <c:if test="${not empty assessment.assessmentQuestionList}">
 <c:set var="qCount" value="1" />
 <c:forEach var="question" items="${assessment.assessmentQuestionList}">
 <div class="preview-test-question-con">
<div class="preview-test-question">
<span>Question ${qCount} <div class="pull-right btn btn-xs btn-warning">
<fmt:parseNumber var="i" integerOnly="true" 
                       type="number" value="${question.marks}" />
Marks: <c:out value="${i}" /></div></span>
${question.question.description}?<br />
<c:if test="${not empty question.question.imagePath }"><img src="${commonPath}/questionImages/${question.question.imagePath}" alt="" /></c:if>
</div>
<c:forEach var="option" items="${question.question.answers}">
<input type="radio" /> ${option.description} <c:if test="${not empty option.imagePath }"><img src="${commonPath}/questionImages/${option.imagePath}" alt="" /></c:if>

<br/>
</c:forEach>




</div>
<c:set var="qCount" value="${qCount +1}" />
 </c:forEach>
 
 </c:if>

<!-- <div class="preview-test-question-con">
<div class="preview-test-question">
<span>Question</span>
Monu spends 1.23 hours for English reading, 1.40 hours for Math and 0.39 hours for Science. How many hours does he spend studying?</div>
			
<input type="radio" id="q128" name="quality[22]" value="1" /> 3.02 hours
<input type="radio" id="q128" name="quality[22]" value="1" /> 2.02 hours	
<input type="radio" id="q128" name="quality[22]" value="1" />4.02 hours	
<input type="radio" id="q128" name="quality[22]" value="1" />3.12 hours
</div>
<div class="preview-test-question-con">
<div class="preview-test-question">
<span>Question</span>
Sohan measures a triangular plate whose sides are 12.4 inches, 9.45 inches and 10.35 inches respectively. What is the perimeter of a triangular plate?</div>
			
<input type="radio" id="q128" name="quality[22]" value="1" /> 32.2 inches
<input type="radio" id="q128" name="quality[22]" value="1" /> 42.2 inches
<input type="radio" id="q128" name="quality[22]" value="1" /> 33.2 inches
<input type="radio" id="q128" name="quality[22]" value="1" /> 31.2 inches
</div>
<div class="preview-test-question-con">
<div class="preview-test-question">
<span>Question</span>
Suman scored 56.73 points and Karan scored 74.92 points on a University exam. How many points less did Suman score than Karan?</div>
			
<input type="radio" id="q128" name="quality[22]" value="1" /> 18.19 points
<input type="radio" id="q128" name="quality[22]" value="1" /> 19.19 points
<input type="radio" id="q128" name="quality[22]" value="1" /> 17.19 points
<input type="radio" id="q128" name="quality[22]" value="1" /> 18.29 points
</div>
<div class="preview-test-question-con">
<div class="preview-test-question">
<span>Question</span>
A mixture is obtained by mixing two products A and B respectively. Product A weighs 234.56 grams and the mixture weighs 988.76 grams. How much does Product B weigh?</div>
			
<input type="radio" id="q128" name="quality[22]" value="1" /> 754.2 grams
<input type="radio" id="q128" name="quality[22]" value="1" /> 755.2 grams
<input type="radio" id="q128" name="quality[22]" value="1" /> 764.2 grams
<input type="radio" id="q128" name="quality[22]" value="1" /> 744.2 grams
</div>
<div class="preview-test-question-con">
<div class="preview-test-question">
<span>Question</span>
David’s home is 12.53 miles away from the lake and 16.73 miles away from his school. How far is David’s school from the lake?</div>
			
<input type="radio" id="q128" name="quality[22]" value="1" /> 4.2 miles
<input type="radio" id="q128" name="quality[22]" value="1" /> 4.3 miles
<input type="radio" id="q128" name="quality[22]" value="1" /> 3.2 miles
<input type="radio" id="q128" name="quality[22]" value="1" /> 5.2 miles
</div>
<div class="preview-test-question-con">
<div class="preview-test-question">
<span>Question</span>
____________________ is the measure used to check body temperature.</div>
			
<input type="radio" id="q128" name="quality[22]" value="1" />Thermometer
<input type="radio" id="q128" name="quality[22]" value="1" />Stethoscopes
<input type="radio" id="q128" name="quality[22]" value="1" />Vaporizer	None of these

</div>
<div class="preview-test-question-con">
<div class="preview-test-question">
<span>Question</span>
The _____________________ scale is named after a German scientist.</div>
			
<input type="radio" id="q128" name="quality[22]" value="1" />Fahrenheit
<input type="radio" id="q128" name="quality[22]" value="1" />Celsius	
<input type="radio" id="q128" name="quality[22]" value="1" />Degree 
</div>
<div class="preview-test-question-con">
<div class="preview-test-question">
<span>Question</span>
The _____________________ scale is named after a Swedish scientist. </div>
			
<input type="radio" id="q128" name="quality[22]" value="1" />Fahrenheit 
<input type="radio" id="q128" name="quality[22]" value="1" />Celsius
<input type="radio" id="q128" name="quality[22]" value="1" />Degree 
</div>
<div class="preview-test-question-con">
<div class="preview-test-question">
<span>Question</span>
The unit is used to measure the temperature is called ________________.</div>
			
<input type="radio" id="q128" name="quality[22]" value="1" />Degree 
<input type="radio" id="q128" name="quality[22]" value="1" />Radian
<input type="radio" id="q128" name="quality[22]" value="1" />Decimal
</div>
 -->


 </div>
         
  <div class="clearfix"></div>
  </c:if>
      </div>
      <div class="modal-footer">
      </div>
      
     
      </div>

