<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script type="text/javascript">
<!--
var customUrl = '/';
//-->
</script>

<jsp:include page="../student/studentHeader.jsp" /> 

<!--table-start-here-->

	<div class="container">
    <form action="${pageContext.request.contextPath}/payment/upgrade.htm" method="post">
   
    		<div class="row">
            <!--Left-panel-start-here-->
    		<div class="col-md-9">
            
         
            <div class="row">
            <!--table-form-start-here-->
            <div class="col-md-12 tab-forms">	
            <input type="hidden" value="${userId }" name="userId">
            <input type="hidden" name="payable_amount" id="payable_amount" value="">
            	<div class="row">
            	<div class="col-md-4"><p>Select Board, Class and Subject</p></div>
            	<input type="hidden" value="" name="urlId" id="urlId">
                <div class="col-md-3">
                <select name="board" id="board" required="required" onchange="getGradeList('#grade', $(this).val(), '${pageContext.request.contextPath}/getGradeByBoardId', 'Select Grade');">
                <option value="" selected="selected">Select Board</option>
                <c:forEach items="${boardList }" var="board">
                <c:if test="${board.id ne 1 }">
				<option value="${board.id}">${board.name}</option>
				</c:if>
				</c:forEach>
                
                
                </select></div>
                <div class="col-md-2"><select name="grade" id="grade" required="required" onchange="getSubjectList('#subject', $(this).val(), '${pageContext.request.contextPath}/ajax/rest/getSubjectByBoardAndGrade', 'Select Subject');">
                <option>Select Grade</option>
                </select></div>
                
                <div class="col-md-3"><select name="subject" id="subject" required="required" onchange="getSubjectPrices('${pageContext.request.contextPath}/ajax/payment/priceList/')">
                <option>Select Subjects</option>
                 <option value="-1">All Subjects</option>
                </select></div>	           
                </div>      
            </div>
            <!--table-form-end-here-->
           </div>
   
            
            <div class="clearfix"></div>
            
             <div class="row" id="priceList">
            
            
             </div>
           <!-- need to add price list here -->
          	
            
            </div>
            <!--Left-panel-end-here-->
        
        
        
        	<!--Right-panel-start-here-->
			<div class="col-md-3" id="buyNowRight" style="display: none;">
            	
                
            	<div class="right-payment">
            
            		<div class="col-md-3 col-sm-12 col-xs-12"><div class="row e-com text-center"><img src="${pageContext.request.contextPath}/resources/images/e-commerce.png" title="" /></div></div>
            		<div class="col-md-9 col-sm-12 col-xs-12 e-com-text" id="byNowDetails">
                
                   
                   <!--  1 Selected Cources(s)<br />
                    CBSC IX All Subjects - Quarterly<br />
                    Plan is Selected --><br />
                   <!--  <span><a href="#">Remove</a></span> -->
                   	
                    
                    </div>
                    
                    <div class="clearfix"></div>
            	</div>
                    
	
                <table class="table mart10">
                     
                    <!--  <tr>
                     <td><strong>Price:</strong></td>
                     <td>Rs. 14,990</td>
                     </tr>
                     
                     <tr>
                     <td colspan="2"><a href="#">Have a Special Discount Coupon Code?</a></td>
                     </tr>
                     
                     <tr>
                     <td><strong>Service Tax:</strong></td>
                     <td>Rs. 20,98.8</td>
                     </tr> -->
                     
                     <tr>
                     <td><strong>Net Amount:</strong></td>
                    <td id="planPrice"></td>
                     </tr>
                     
                     
                    </table>
                
                
                <div class="row"><div class="col-md-12"><input type="submit" value="Buy Now" class="btn btn-danger custmfull"></div></div>
                
                
            </div>
            <!--Right-panel-end-here-->    
           
            
    
    </div>
    </form>
    </div>
    
<!--table-end-here-->


		<!-- Bootstrap core JavaScript
	    ================================================== -->
	    <!-- Placed at the end of the document so the pages load faster -->
	    <!-- <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script> -->
	    <%--  <script src="${pageContext.request.contextPath}/resources/js/bootstrap.js"></script> --%>
	    <!-- <script src="bootstrap-3.3.4/bootstrap-3.3.4/docs/assets/js/vendor/holder.js"></script> -->
	    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
	    <!-- <script src="bootstrap-3.3.4/bootstrap-3.3.4/docs/assets/js/ie10-viewport-bug-workaround.js"></script> -->
		
		
	
	  
	   <!-- jQuery Code executes on Date Format option ----->
	   <!-- <script src="js/script.js"></script> -->