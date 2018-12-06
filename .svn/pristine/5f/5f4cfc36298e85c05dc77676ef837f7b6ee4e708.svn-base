<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%-- <%@ include file="../header.jsp" %> --%>
<jsp:include page="../student/studentHeader.jsp" />
<div class="container">



	<!--invoce-start-here-->
	<div class="invoice">

		<h2>Invoice</h2>

		<div class="col-md-12">
			<div class="row">

			<!-- 	<div class="col-md-6 col-sm-5 col-xs-5 row">
					<p>
						
					</p>
				</div> -->


				<div class="col-md-6 text-right">
					<a href="#"><img
						src="${pageContext.request.contextPath}/resources/images/flp-logo1.jpg"
						title="" /></a>
				</div>

				<div class="clearfix"></div>

				<div class="row">
					<div class="col-md-8 col-xs-12">
						<h3>
							${userObj.firstName }<br /> ${userObj.address },<br /> ${userObj.state }<br />
						Phone: ${userObj.mobileNo }
						</h3>
					</div>
					<div class="col-md-4 col-xs-12">

						<div class="table-responsive">

							<table class="table">


								<tbody>

									<tr>
										<td><strong>Invoice #</strong></td>
										<td>${transaction.orderNo}</td>
									</tr>

									<tr>
										<td><strong>Date</strong></td>
										<td>${invoiceDate}</td>
									</tr>

									<tr>
										<td><strong>Amount</strong></td>
										<td><fmt:formatNumber type="number" minFractionDigits="2"
												maxFractionDigits="2" value="${transaction.payableAmount}" />
										</td>
									</tr>


								</tbody>

							</table>

						</div>

					</div>
				</div>
				<div class="clearfix"></div>

				<div class="col-md-12">

					<div class="row">
						<div class="table-responsive">

							<table class="table">

								<thead>
									<tr>
										<th>Item</th>
										<th>Description</th>
										<th>Expiry Date</th>
										<th>Amount Paid</th>
										<th>Payment Status</th>

									</tr>
								</thead>

								<tbody>


									<tr>
										<td><strong>${subjectName}</strong></td>
										<td>${boardName}- ${gradeName}- ${planType}</td>
										<td>${expirydate}</td>
										<td><fmt:formatNumber type="number" minFractionDigits="2"
												maxFractionDigits="2" value="${transaction.payableAmount}" /></td>
										<td><c:if test="${transaction.status == 'Paid'}">
                        Payment Received
                        </c:if></td>
									</tr>




								</tbody>

							</table>

						</div>
					</div>

				</div>
				<div class="clearfix"></div>


				<div class="row">
					<div class="col-md-8"></div>
					<div class="col-md-4">

						<div class="table-responsive">

							<!-- 	 <table class="table">
			       
      				
       				<tbody>
			   
                        <tr>
						<td><strong>SubTotal:</strong></td>
						<td>Rs.32,00.00</td>
						</tr>
                        
                       <tr>
						<td><strong>Total:</strong></td>
						<td>Rs.32,00.00</td>
						</tr>
                        
                        <tr>
						<td><strong>Amount Paid:</strong></td>
						<td>Rs.32,00.00</td>
						</tr>
                        
                   
                    </tbody>

   				</table> -->

						</div>

					</div>
				</div>
			</div>

		</div>

		<div class="row">
			<div class="col-md-12 text-right">
				<button class="btn btn-success" type="button"
					onclick="window.print()">PRINT</button>
			</div>
		</div>
		<br />
		<br />


	</div>
	<!--invoce-start-here-->

<c:remove var="user" />

</div>

<jsp:include page="../student/studentFooter.jsp" />
