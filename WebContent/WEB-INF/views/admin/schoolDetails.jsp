<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<jsp:include page="../globalPage.jsp" />

<!-- All js, css, data-table api are defined in globalPage.js -->
	<br />
	<h2 class="text-center">School Information</h2>
	<div style="width: 90% !important; margin: 0px auto;">
		<button class="btn btn-success " data-toggle="modal" data-target="#myModal1" id="addSchoolId">Add New</button>
		<button type="button" class="btn btn-primary" id="editSchoolId" data-toggle="modal"	data-target="#myModal2">Edit</button>
		<!-- <button type="button" class="btn btn-danger" id="removeSchoolId">Remove</button> -->
		<%-- <a href="#" style="padding-right:10px;" onclick="createSchool()"><img src="${pageContext.request.contextPath}/resources/images/add.png" width="100px" /></a>
		<a href="#" style="padding-right:10px;"><img src="${pageContext.request.contextPath}/resources/images/edit.png" width="80px" /></a>
		<a href="#" style="padding-right:10px;"><img src="${pageContext.request.contextPath}/resources/images/remove.png" width="100px" /></a> --%>
		<br />
		<br />
		<table id="schoolDetailsId" class="display" style="display: none;">
			<thead>
				<tr>
					<th>S.No.</th>
					<th>School Name</th>
					<th>Address</th>
					<th>Contact No.</th>
					<th>Email-Id</th>
					<th>Expiry Date</th>
					<th>Board</th>
					<th>Status</th>
				</tr>
			</thead>
			<tbody>
				<c:if test="${not empty schoolList}">
					<c:forEach var="school" items="${ schoolList}" varStatus="status">
						<tr>
							<td><input type="checkbox" value="${school.id }" name="schoolId" class="schoolCls" id="schoolId${status.count}"/> ${status.count }</td>
							<td>${school.schoolName }</td>
							<td>${school.address },${school.city }</td>
							<td>${school.contactNo }</td>
							<td>${school.contactEmail }</td>
							<td>
								<fmt:formatDate pattern="dd-MMM-yyyy" value="${school.expiryDate}" />
								<input type="hidden" id="schoolNameId${status.count}" value="${school.schoolName }"/>
								<input type="hidden" id="schoolAddressId${status.count}" value="${school.address }"/>
								<input type="hidden" id="schoolCityId${status.count}" value="${school.city }"/>
								<input type="hidden" id="schoolStateId${status.count}" value="${school.state }"/>
								<input type="hidden" id="schoolCountryId${status.count}" value="${school.country }"/>
								<input type="hidden" id="schoolContactId${status.count}" value="${school.contactNo }"/>
								<input type="hidden" id="schoolEmailId${status.count}" value="${school.contactEmail }"/>
								<input type="hidden" id="schoolExpiryId${status.count}" value="<fmt:formatDate pattern="dd-MM-yyyy"	value="${school.expiryDate}" />"/>
							</td>
							<td>
								<select <c:if test="${not empty school.boardId}">disabled='true'</c:if> schoolid="${school.id}" sessionid="${school.currentSessionId}" onchange="assignBoard($(this))">
									<option value="" <c:if test="${empty school.boardId}">selected='selected'</c:if>>Select Board</option>
									<c:forEach var="board" items="${boardList}">
										<option value="${board.id}" <c:if test="${school.boardId == board.id}">selected='selected'</c:if>>${board.name}</option>
									</c:forEach>
								</select>
							</td>
							<td>
								<c:if test="${school.status eq 1 }"><a href="javascript:void(0)" class="activeDeactiveSchoolCls" id="removeId${school.id}" dir="0" title="Active">Active</a></c:if>
								<c:if test="${school.status eq 0 }"><a href="javascript:void(0)" class="activeDeactiveSchoolCls" id="removeId${school.id}" dir="1" title="Inactive">Inactive</a></c:if>
							</td>
									
						</tr>
					</c:forEach>

				</c:if>

			</tbody>
		</table>
	</div>
	
	<!-- Modal -->
	<div class="modal fade" id="myModal1" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">School Detail</h4>
				</div>
				<form:form action="${pageContext.request.contextPath}/admin/addOrEditSchool.htm" commandName="school" method="post" id="addSchoolDetailsId">
					<div class="modal-body">
						<div class="col-md-12 col-sm-12 col-xs-12">
							<div class="row">
								<input type="text" name="schoolName" placeholder="School Name" required="required" class="registration" >
								<input type="hidden" name="id" value="0">
							</div>
						</div>
						<div class="col-md-12 col-sm-12 col-xs-12">
							<div class="row">
								<input type="text" name="address" placeholder="School Address" required="required" class="registration" >
							</div>
						</div>
						<div class="col-md-12 col-sm-12 col-xs-12">
							<div class="row">
								<input type="text" name="city" placeholder="City" required="required" class="registration" >
							</div>
						</div>
						<div class="col-md-12 col-sm-12 col-xs-12">
							<div class="row">
								<input type="text" name="state" placeholder="State" required="required" class="registration" >
							</div>
						</div>
						<div class="col-md-12 col-sm-12 col-xs-12">
							<div class="row">
								<select name="country" required="required" class="registration" >
									<option value="">Select Country</option>
									<option value="india" selected="selected">India</option>
								</select>
							</div>
						</div>
						<div class="col-md-12 col-sm-12 col-xs-12">
							<div class="row">
								<input type="tel" name="contactNo" placeholder="Contact No" pattern="[0-9]{10,11}" required="required" class="registration" >
							</div>
						</div>
						<div class="col-md-12 col-sm-12 col-xs-12">
							<div class="row">
								<input type="email" name="contactEmail" placeholder="Contact Email Id" required="required" class="registration" >
							</div>
						</div>
						<div class="col-md-12 col-sm-12 col-xs-12">
							<div class="row">
								<input type="text" name="expDate" placeholder="Expiry Date(dd-MM-yyyy)" class="registration" required="required" >
							</div>
						</div>
						<div class="col-md-12 col-sm-12 col-xs-12">
							<div class="row">
								<select name="sessionId" required="required" class="registration" >
									<option value="">Select Session</option>
									<c:if test="${not empty sessionList }">
										<c:forEach var="session" items="${sessionList }">
											<option value="${session.id}">${session.sessionName }</option>
										</c:forEach>
									</c:if>
								</select>
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<button type="submit" class="btn btn-primary" >Add School</button>
					</div>
				</form:form>
			</div>
		</div>
	</div>

	<!-- Modal -->
	<div class="modal fade" id="myModal2" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">School Detail</h4>
				</div>
				<form:form action="${pageContext.request.contextPath}/admin/addOrEditSchool.htm" commandName="school" method="post" id="addSchoolDetailsId">
					<div class="modal-body">
						<div class="col-md-12 col-sm-12 col-xs-12">
							<div class="row">
								<input type="text" name="schoolName" placeholder="School Name" required="required" class="registration" id="schName">
								<input type="hidden" id="schId" name="id" value="0">
							</div>
						</div>
						<div class="col-md-12 col-sm-12 col-xs-12">
							<div class="row">
								<input type="text" name="address" placeholder="School Address" required="required" class="registration" id="schAddress">
							</div>
						</div>
						<div class="col-md-12 col-sm-12 col-xs-12">
							<div class="row">
								<input type="text" name="city" placeholder="City" required="required" class="registration" id="schCity">
							</div>
						</div>
						<div class="col-md-12 col-sm-12 col-xs-12">
							<div class="row">
								<input type="text" name="state" placeholder="State" required="required" class="registration" id="schState">
							</div>
						</div>
						<div class="col-md-12 col-sm-12 col-xs-12">
							<div class="row">
								<select name="country" required="required" class="registration" id="schCountry">
									<option value="">Select Country</option>
									<option value="india" selected="selected">India</option>
								</select>
							</div>
						</div>
						<div class="col-md-12 col-sm-12 col-xs-12">
							<div class="row">
								<input type="tel" name="contactNo" placeholder="Contact No"
									pattern="[0-9]{10,11}" required="required" class="registration" id="schPhoneNo">
							</div>
						</div>
						<div class="col-md-12 col-sm-12 col-xs-12">
							<div class="row">
								<input type="email" name="contactEmail"
									placeholder="Contact Email Id" required="required"
									class="registration" id="schEmail">
							</div>
						</div>
						<div class="col-md-12 col-sm-12 col-xs-12">
							<div class="row">
								<input type="text" name="expDate"
									placeholder="Expiry Date(dd-MM-yyyy)" class="registration"
									required="required" id="schExpiryDate">
							</div>
						</div>
						
					</div>
					<div class="modal-footer">
						<button type="submit" class="btn btn-primary" id="addUpdateId">Update School</button>
					</div>
				</form:form>
			</div>
			<input type="hidden" id="hiddenCountId" value="0" >
			<input type="hidden" id="hiddenSchoolId" value="0" >
		</div>
	</div>
	<form:form
		action="${pageContext.request.contextPath}/admin/activeDeactiveSchool.htm"
		method="post" id="deactivateSchoolId">
		<input type="hidden" id="hiddenSchId"  name="schoolId">
	<input type="hidden" id="hiddenStatus" name="activeDeactive"/> 
	</form:form>

	<script type="text/javascript">
		$(document).ready(function() 
		{
			$('#schoolDetailsId').dataTable();
			$('#schoolDetailsId').show();
			
			$("#schoolLst").addClass("active");
			$(".schoolCls").on("click", function() 
			{
				if ($(this).prop('checked')) 
				{
					$(".schoolCls").removeAttr('checked');
					$(this).prop('checked', true);
					$("#hiddenSchoolId").val($(this).val());
					$("#hiddenCountId").val($(this).attr('id').substr(8));
				} 
				else 
				{
					$(this).prop('checked', false);
					$("#hiddenSchoolId").val(0);
					$("#hiddenCountId").val(0);
				} 
			});
			
            //code for activate or deactivate school
			$(".activeDeactiveSchoolCls").on("click", function() 
			{
				$("#hiddenSchId").val($(this).attr('id').substr(8));
				$("#hiddenStatus").val($(this).attr('dir'));
				$("#deactivateSchoolId").submit();
			})
			
			//code for open pop up for edit school details
			$("#editSchoolId").on("click", function()
			{
				var schoolId = parseFloat($("#hiddenSchoolId").val());
				if(schoolId>0)
				{
					var countId=parseFloat($("#hiddenCountId").val());
					$("#schId").val($("#schoolId"+countId).val());
					$("#schName").val($("#schoolNameId"+countId).val());
					$("#schAddress").val($("#schoolAddressId"+countId).val());
					$("#schCity").val($("#schoolCityId"+countId).val());
					$("#schState").val($("#schoolStateId"+countId).val());
					$("#schCountry").val($("#schoolCountryId"+countId).val());
					$("#schPhoneNo").val($("#schoolContactId"+countId).val());
					$("#schEmail").val($("#schoolEmailId"+countId).val());
					$("#schExpiryDate").val($("#schoolExpiryId"+countId).val());
				}
				else
				{
					alert("please select any one checkbox");
					return false;
				}
			});
		});
		
		function assignBoard(refObj)
		{
			var boardId = refObj.val();
			var schoolId = refObj.attr('schoolid');
			var currentSessionId = refObj.attr('sessionid');
			
			if(confirm("Do you want to assign board to school?") == true)
			{
				$.ajax
				({
					url:"${pageContext.request.contextPath}/admin/addSchoolAndBoardMapping.htm",
					type:"POST",
					data: {"boardId": boardId, "schoolId": schoolId, "currentSessionId":currentSessionId},
					success:function(data)
					{
						/* if(data == '')
							alert("Something went wrong");
						else if(data != '' && data.indexOf('<html') == -1)			// Since error page will have <html> tag
						{
							alert("successfully mapped : "+data);
						} */
					},
					error:function()
					{
						alert("Something went wrong");
					}
				});
			}
		}
	</script>
