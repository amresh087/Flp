<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="verticle-align">
	<div class="container">
		<div class="col-md-4 col-sm-12 col-xs-12"></div>
		<div class="col-md-4 col-sm-12 col-xs-12">
			<div class="row">
				<div class="registration">
					<p>
						<a href="#" onClick="closeDiv()" class="close-btn">x</a>
					</p>
					<ul id="myTab" class="nav nav-tabs">
						<!-- <li  id="registrationId"><a href="#home" data-toggle="tab">Registration</a></li> -->
						<li class="active" id="loginId"><a href="#login" data-toggle="tab" >Login</a></li>
					</ul>
					<input type="hidden" id="contextPathId" value="${pageContext.request.contextPath}">
					<div id="myTabContent" class="tab-content">
						<div class="tab-pane fade " id="home">
							<h2>Join Now for FREE</h2>
							<c:if test="${! empty successMsg }"><input type="hidden" value="2" class="hiddenPopCls"><div class="successMsg">${successMsg }</div><c:remove var="successMsg" scope="session"></c:remove></c:if>
							<c:if test="${! empty errMsg }"><input type="hidden" value="2" class="hiddenPopCls"><div class="errMsg">${errMsg }</div><c:remove var="errMsg" scope="session"></c:remove></c:if>
							
							
							<form:form
								action="${pageContext.request.contextPath}/saveUserRegistration.htm"
								commandName="user" method="post" id="userUegistration"
								class="form-horizontal" role="form">
                           <label><form:radiobutton path="userType" id="studentId"
								class="studentParentCls" value="4" checked='checked'/>
							Student</label> <label><form:radiobutton path="userType" id="parentId"
								class="studentParentCls" value="3" />Parent</label> <br>
								
								<c:if test="${! empty parentLinkingWithStudentId }">
								<input type="hidden" value="3" class="hiddenPopCls">
								<input type="hidden" name="parentLinkingWithStudentId" value="${parentLinkingWithStudentId}">
								<c:remove var="parentLinkingWithStudentId" scope="session"/></c:if>
							<div class="clearfix"></div>
								<input type="text" name="firstName" id="firstname"
									placeholder="Enter your  name" required />
								<input type="email" name="emailId" id="lastname"
									placeholder="Please enter your email id" required>
								<input type="password" name="password" id="lastname"
									placeholder="Enter your password" required  pattern=".{6,}" title="Six or More characters">
								<input type="tel" id="lastname" pattern="[0-9]{10,11}"
									name="mobileNo" placeholder="Please enter your mobile no."
									required maxlength="11">
								<div id="boardGradeId">
									<select name="board" id="boardId" required="required" onchange="getGradeList('#gradeId', $(this).val(), '${pageContext.request.contextPath}/getGradeByBoardId', 'Please Select Grade');">
										<option value="">Please select Board</option>
										<c:forEach items="${boardList }" var="board">
											<option value="${board.id }">${board.name }</option>
										</c:forEach>
									</select> <select name="grade" id="gradeId" required="required">
										<option value="">Please select  grade</option>

									</select>
								</div>
								<div class="clearfix"></div>
								<button type="submit" class="btn btn-primary btn-success">SUBMIT</button>
							</form:form>
							<p>By clicking on 'JOIN NOW' You agree to our T&amp;C</p>
						</div>

						<div class="tab-pane fade in active login" id="login">
							<div id="loginFormId"><br />
<c:if test="${! empty errMsg1 }"><input type="hidden" value="4" class="hiddenPopCls"><div class="errMsg">${errMsg1 }</div><c:remove var="errMsg1" scope="session"></c:remove></c:if>
							<form action="${pageContext.request.contextPath}/userlogin.htm"
								method="post">
								<input type="text" name="username" placeholder="username" required class="loginCls"/>
								<input type="password" name="password" placeholder="Password"
									required class="loginCls"/>
								<div class="clearfix"></div>
								<br />
								<button type="submit" class="btn btn-primary btn-success">Login</button>
								<!-- <a href="javascript:void(0)" id="forgotId">forgot password?</a> -->
							</form>
							
							</div>
							
							<div id="forgotPwdId" style="display: none;">
							Reset Password
							<input type="text" placeholder="Email" id="emailAddress"/>
							<div id="forgotErrMsg"></div>
							<span style="display: none;" id="errEmailMsg" class="errMsg">Please enter valid emailid</span>
							<div class="clear"></div>
							<a href="javascript:void();" class="btn btn-primary btn-success" id="resetPwdbtn">Reset Password</a>
							<a href="javascript:void(0)" id="cancelId" class="btn btn-primary btn-success">Cancel</a>
							</div>
							
						</div>

					</div>
				</div>
			</div>

		</div>
		<div class="col-md-4 col-sm-12 col-xs-12"></div>

	</div>
</div>

<script type="text/javascript">
	$(document)
			.ready(
					function() {
						var hiddenPopCls=parseFloat($(".hiddenPopCls").val());
						if(hiddenPopCls==2 || hiddenPopCls=='2'){
							$(".pop-login").show();
						}else if(hiddenPopCls==3 || hiddenPopCls=='3'){
						  $(".pop-login").show();
						  $("#boardGradeId").hide();
							$('#studentId').removeAttr('checked');
							$('#parentId').attr('checked','checked');
							$("#boardId").removeAttr('required');
							$("#gradeId").removeAttr('required');
						}else if(hiddenPopCls==4 || hiddenPopCls=='4'){
							 $(".pop-login").show();
							 $("#loginId").addClass("active");
							 $("#registrationId").removeClass("active");
							 $("#home").removeClass('active').removeClass('in');
							 $("#login").addClass('active').addClass('in');
							 
						}

						
					});
</script>
