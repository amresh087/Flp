<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import = "java.io.*" %>
<%@include file="libFunctions.jsp"%>

<c:set var="pOrderPrice" value="${orderPrice}"/>
<c:set var="pOrderNo" value="${orderNo}"/>
<c:set var="pReturnURL" value="${returnURL}"/>
<c:set var="userId" value="${userId}"/>
<c:set var="promocode" value="${promocode}"/>
<%
	String Merchant_Id = "M_iPerform_5526"; //This id(also User_Id)  available at "Generate Working Key" of "Settings & Options"
	String Amount = (String) pageContext.getAttribute("pOrderPrice"); //your script should substitute the amount here in the quotes provided here
	String Order_Id = (String) pageContext.getAttribute("pOrderNo"); //your script should substitute the order description here in the quotes provided here
	String Redirect_Url = (String) pageContext.getAttribute("pReturnURL");
	Long userId = (Long)pageContext.getAttribute("userId");
	String promocode = (String) pageContext.getAttribute("promocode");
	String WorkingKey = "wfjf3lagtp8ynlqupkns4h4eitasjkcg";   //put in the 32 bit alphanumeric key in the quotes provided here.Please note that get this key login to your CCAvenue merchant account and visit the "Generate Working Key" section at the "Settings & Options" page. 
	String Checksum = getChecksum(Merchant_Id, Order_Id, Amount, Redirect_Url, WorkingKey);
	String discDtls = getDiscountDetails(userId,promocode);
	System.out.println("discDTLS:" + discDtls);
%>


Please wait while we are redirecting on payment gateway ................................. 
	<div class="btn-box">
	  <div class="container">
		<form name="paymentForm" action="https://www.ccavenue.com/shopzone/cc_details.jsp" method="post" target="_parent">	
		
			<input type="hidden" name="Order_Id" value="${orderNo}">
 		    <input type="hidden" name="Amount" value="${pOrderPrice}">  
		    <input type="hidden" name="Merchant_Id" value="M_iPerform_5526">
		    <input type="hidden" name="Redirect_Url" value="${returnURL}">
		    <input type="hidden" name="Checksum" value="<%=Checksum%>">
		    <input type="hidden" name="billing_cust_name" value="${firstName} ${lastName}">
			<input type="hidden" name="billing_cust_address" value="xyz"> 
			<input type="hidden" name="billing_cust_country" value="India">
			<input type="hidden" name="billing_cust_tel" value="${mobile}">     
			<input type="hidden" name="billing_cust_email" value="${email}">     
			<input type="hidden" name="billing_cust_notes" value="<%=discDtls%>">      
			<input type="hidden" name="delivery_cust_name" value="${firstName} ${lastName}">    
			<input type="hidden" name="delivery_cust_address" value="xyz">    
			<input type="hidden" name="delivery_cust_tel" value="${mobile}">
			<input type="hidden" name="TxnType" value="A">
			<input type="hidden" name="actionID" value="TXN">  
			<input type="hidden" name="Merchant_Param" value="<%=discDtls%>">  		 
	    	<input class="greybtn btn btnM" type="submit" value="Make Payment"/>
	    </form>
	    <script type="text/javascript">
			document.paymentForm.submit();
		</script>
	  </div>
	</div>