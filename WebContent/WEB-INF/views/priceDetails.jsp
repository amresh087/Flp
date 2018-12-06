 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
  <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
 <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
            
                      
            <div class="table-responsive">
       
   				 <table class="table">
			       
      				<thead>
						<tr>
                        <th>Subject</th>
						<th>Base Plan</th>
						<th>Quarterly</th>
						<th>Half Yearly</th>
                        <th>Annually</th>
                    
						</tr>
					 </thead>

       				<tbody>
			<c:forEach items="${subjectList}" var="subject">
			 <tr>
						<td><strong>${subject.name}</strong></td>
						<td><label><img src="${pageContext.request.contextPath}/resources/images/tick.png" title="" /></label>Only first chapter access</td>
						<td><label><img src="${pageContext.request.contextPath}/resources/images/tick.png" title="" /></label>All Chapters</td>
						<td><label><img src="${pageContext.request.contextPath}/resources/images/tick.png" title="" /></label>All Chapters</td>
                        <td><label><img src="${pageContext.request.contextPath}/resources/images/tick.png" title="" /></label>All Chapters</td>
						</tr>
			
			</c:forEach>
                        
                      <%--   <tr>
						<td><strong>Physics</strong></td>
						<td><label><img src="${pageContext.request.contextPath}/resources/images/tick.png" title="" /></label>Only first chapter access</td>
						<td><label><img src="${pageContext.request.contextPath}/resources/images/tick.png" title="" /></label>All Chapters</td>
						<td><label><img src="${pageContext.request.contextPath}/resources/images/tick.png" title="" /></label>All Chapters</td>
                        <td><label><img src="${pageContext.request.contextPath}/resources/images/tick.png" title="" /></label>All Chapters</td>
						</tr>
                        
                        <tr>
						<td><strong>Chemistry</strong></td>
						<td><label><img src="${pageContext.request.contextPath}/resources/images/tick.png" title="" /></label>Only first chapter access</td>
						<td><label><img src="${pageContext.request.contextPath}/resources/images/tick.png" title="" /></label>All Chapters</td>
						<td><label><img src="${pageContext.request.contextPath}/resources/images/tick.png" title="" /></label>All Chapters</td>
                        <td><label><img src="${pageContext.request.contextPath}/resources/images/tick.png" title="" /></label>All Chapters</td>
						</tr>
                        
                        <tr>
						<td><strong>Biology</strong></td>
						<td><label><img src="${pageContext.request.contextPath}/resources/images/tick.png" title="" /></label>Only first chapter access</td>
						<td><label><img src="${pageContext.request.contextPath}/resources/images/tick.png" title="" /></label>All Chapters</td>
						<td><label><img src="${pageContext.request.contextPath}/resources/images/tick.png" title="" /></label>All Chapters</td>
                        <td><label><img src="${pageContext.request.contextPath}/resources/images/tick.png" title="" /></label>All Chapters</td>
						</tr>
                        
                        <tr>
						<td><strong>Math</strong></td>
						<td><label><img src="${pageContext.request.contextPath}/resources/images/tick.png" title="" /></label>Only first chapter access</td>
						<td><label><img src="${pageContext.request.contextPath}/resources/images/tick.png" title="" /></label>All Chapters</td>
						<td><label><img src="${pageContext.request.contextPath}/resources/images/tick.png" title="" /></label>All Chapters</td>
                        <td><label><img src="${pageContext.request.contextPath}/resources/images/tick.png" title="" /></label>All Chapters</td>
						</tr>
                        
                        <tr>
						<td><strong>Hindi</strong></td>
						<td><label><img src="${pageContext.request.contextPath}/resources/images/tick.png" title="" /></label>Only first chapter access</td>
						<td><label><img src="${pageContext.request.contextPath}/resources/images/tick.png" title="" /></label>All Chapters</td>
						<td><label><img src="${pageContext.request.contextPath}/resources/images/tick.png" title="" /></label>All Chapters</td>
                        <td><label><img src="${pageContext.request.contextPath}/resources/images/tick.png" title="" /></label>All Chapters</td>
						</tr>
                        
                        <tr>
						<td><strong>English</strong></td>
						<td><label><img src="${pageContext.request.contextPath}/resources/images/tick.png" title="" /></label>Only first chapter access</td>
						<td><label><img src="${pageContext.request.contextPath}/resources/images/tick.png" title="" /></label>All Chapters</td>
						<td><label><img src="${pageContext.request.contextPath}/resources/images/tick.png" title="" /></label>All Chapters</td>
                        <td><label><img src="${pageContext.request.contextPath}/resources/images/tick.png" title="" /></label>All Chapters</td>
						</tr> --%>
                        
                        
                        <tr class="table-footer">
						<td></td>
						<td><label>FREE</label></td>
						
						<td>
						
						<c:if test="${not empty quaterlyPrice}">
						<fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${quaterlyPrice}"  var="qPrice"/>
						<input type="radio" value="3" name="planType" checked="checked" onclick="updatePrice('${boardName}','${gradeName}', '${subjectName}', '${quaterlyPrice}', 'Quaterly')"><label>Rs. ${qPrice}</label>
						<script type="text/javascript">
                      updatePrice('${boardName}','${gradeName}', '${subjectName}', '${quaterlyPrice}', 'Quaterly');
                    $('#buyNowRight').show();
                     </script>
						</c:if>
						</td>
						<td>
						
						<c:if test="${not empty halfYearlyPrice}">
						<fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${halfYearlyPrice}"  var="hPrice"/>
						<input type="radio" value="2" name="planType" checked="checked" onclick="updatePrice('${boardName}','${gradeName}', '${subjectName}', '${halfYearlyPrice}', 'Half Yearly')"><label>Rs. ${hPrice}</label>
						<script type="text/javascript">
updatePrice('${boardName}','${gradeName}', '${subjectName}', '${halfYearlyPrice}', 'Half Yearly');
$('#buyNowRight').show();
</script>
						</c:if></td>
                        <td>
                        <c:if test="${not empty annualPrice}">
                        <fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${annualPrice}"  var="aPrice"/>
                        <input type="radio" checked="checked" name="planType" value="1" onclick="updatePrice('${boardName}','${gradeName}', '${subjectName}', '${annualPrice}', 'Annualy')"><label>Rs. ${aPrice}</label>
                        <script type="text/javascript">
updatePrice('${boardName}','${gradeName}', '${subjectName}', '${annualPrice}', 'Annualy');
$('#buyNowRight').show();
</script>
                        </c:if>
                        </td>
						</tr>
                        
                        
                    </tbody>

   				</table>


				</div>

         