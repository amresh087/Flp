<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:include page="../student/studentHeader.jsp" />
<!-- middle panel -->
<div class="container-fluid bred-cont">
<div class="container">
<div class="row">
<ul class="breadcrumb">
<li>Edit</li>
<li>Student</li>
<li>Profile</li>
</ul>
</div>
</div>
</div>


<div class="container-fluid mrt20 question-con">
<div class="container">
<div class="row">
<div class="col-md-12 test-summ-con">
<h2 class="text-center">Student Profile</h2>
<div class="row">

<div class="col-lg-3 col-md-3 col-sm-4" style="text-align: center" style="border">
 
<form method="post" enctype="multipart/form-data" action="singleSave">
 <div class="profile-pic"> 
<%-- <c:choose><c:when test="${empty sessionScope.user.profilePath}"></c:when></c:choose>
<img class="media-object" id="pImg" src="${pageContext.request.contextPath}/resources/images/profile-pic.jpg" alt="">
</div> --%>
<c:if test="${not empty sessionScope.user.profilePath}">
							
									
	<img class="media-object" id="pImg" src="${commonPath}/profileImage/${sessionScope.user.profilePath}" alt="" width="200" height="200">
								
								</c:if>
<input id="filePic" type="file" name="file"  onchange=" onchangeOfFile()">
<input id="imgUpload" class="btn btn-primary btn-success" type="submit" value="Update"  style="float: right; visibility:hidden;" >
 </form>
</div>
 </div>

<div class="col-lg-9 col-md-9 col-sm-8">
<div class="edit-content">
<div class="edit-heading"><h4>Login Details</h4><div class="btn btn-success btn-xs cursor mrl10 bt-mt" style="float:right; margin-top: -21px;"><a href="javascript:void(0);" onclick="editLoginDetails()">Edit</a></div></div>

<form class="form-group">
<div class="row">
<div class="profile-form">
<label class="col-sm-4 col-lg-2 col-md-2 control-label ng-binding" for="txtFirstName">User Name</label>
<div class="input-group col-sm-8 col-lg-10 col-md-10">
<input  id="hiddenFieldUserName" class="text-field " type="text" readonly value="${sessionScope.user.userName}"></div>

<label class="col-sm-4 col-lg-2 col-md-2 control-label ng-binding" for="txtFirstName">OldPassword</label>
<div class="input-group col-sm-8 col-lg-10 col-md-10">
<input id="password" class="text-field " type="password" readonly></div>

<label class="col-sm-4 col-lg-2 col-md-2 control-label ng-binding" for="txtFirstName">NewPassword</label>
<div class="input-group col-sm-8 col-lg-10 col-md-10">
<input id="newPassword" class="text-field " type="password"  readonly></div>



<label class="col-sm-4 col-lg-2 col-md-2 control-label ng-binding" for="txtFirstName">ConfirmPassword</label>
<div class="input-group col-sm-8 col-lg-10 col-md-10">
<input id="confirmPassword" class="text-field " type="password"  readonly></div> 


<div class="clearfix"></div>
<div class="col-md-2" style="margin-top: 15px; margin-left: 0px;">
<input id="updateLoginDetails" class="btn btn-primary btn-success" type="submit" value="Update" style="visibility:hidden;" onclick="forUpdateUserPassword()">
</div>
</div>
</form>
</div>
<div class="clearfix"></div>
</div>
<div class="edit-content" style="margin-top:20px;">
<div class="edit-heading"><h4>Personal Information</h4><div class="btn btn-success btn-xs cursor mrl10 bt-mt" style="float:right; margin-top: -21px;"><a href="javascript:void(0);" onclick="editPersonalInformation()">Edit</a></div></div>

<form class="form-group"  modelAttribute="updatePInformation" method="post"  action="updateStudentPersonalInformation">
<div class="row">
<div class="profile-form">
<label class="col-sm-4 col-lg-2 col-md-2 control-label ng-binding" for="txtFirstName" >Full Name</label>
<div class="input-group col-sm-8 col-lg-10 col-md-10">
<input id="pFullName" name="firstName" class="text-field " type="text" value="${sessionScope.user.name}" readonly></div>

<label class="col-sm-4 col-lg-2 col-md-2 control-label ng-binding" for="txtFirstName">Date of Birth</label>
<div class="input-group col-sm-8 col-lg-10 col-md-10">
<input id="pDateOfBirth" name="dateOfBirth" class="text-field " type="text" value="<fmt:formatDate pattern="MM/dd/yyyy" 
            value="${sessionScope.user.studentDob}" />" readonly></div>

<!-- <label class="col-sm-4 col-lg-2 col-md-2 control-label ng-binding" for="txtFirstName">Father's Name</label>
<div class="input-group col-sm-8 col-lg-10 col-md-10">
<input id="pFatherName" class="text-field " type="text" readonly></div>
 -->
<label class="col-sm-4 col-lg-2 col-md-2 control-label ng-binding" for="txtFirstName">Grade</label>
<div class="input-group col-sm-8 col-lg-10 col-md-10">
<input id="pGrade" class="text-field " type="text" readonly="" placeholder="" value="${sessionScope.user.gradeName}"></div>

<label class="col-sm-4 col-lg-2 col-md-2 control-label ng-binding" for="txtFirstName">School Name</label>
<div class="input-group col-sm-8 col-lg-10 col-md-10">
<input id="pSchool" class="text-field " type="text" readonly="" placeholder="" value="${sessionScope.user.schoolName}"></div>

<div class="clearfix"></div>
<div class="col-md-2" style="margin-top: 15px; margin-left: 0px;">
<input id="UpdatePersonalInformation" class="btn btn-primary btn-success" type="submit" value="Update" onclick="hideSubmintBtn('2')" style="visibility:hidden;">
</div>
</div>
</form>
</div>
</div>
<div class="edit-content" style="margin-top:20px;">
<div class="edit-heading"><h4>Contact  Information</h4><div class="btn btn-success btn-xs cursor mrl10 bt-mt" style="float:right; margin-top: -21px;"><a href="javascript:void(0);" onclick="editContactInformation()">Edit</a></div></div>

<form class="form-group" onsubmit="genarate('hiddenField')" modelAttribute="updateContactInformation" method="post"  action="updateContactInformation">
<div class="row">
<div class="profile-form">
<!-- <label class="col-sm-4 col-lg-2 col-md-2 control-label ng-binding" for="txtFirstName">Father Email</label>
<div class="input-group col-sm-8 col-lg-10 col-md-10">
<input id="cFatherEmail" class="text-field " type="text" readonly ></div>

<label class="col-sm-4 col-lg-2 col-md-2 control-label ng-binding" for="txtFirstName">Father Mob No 1</label>
<div class="input-group col-sm-8 col-lg-10 col-md-10">
<input id="cFatherMob1" class="text-field " type="text"  readonly></div>

<label class="col-sm-4 col-lg-2 col-md-2 control-label ng-binding" for="txtFirstName">Father Mob No 2</label>
<div class="input-group col-sm-8 col-lg-10 col-md-10">
<input id="cFatherMob2" class="text-field " type="text" readonly></div> -->

<label class="col-sm-4 col-lg-2 col-md-2 control-label ng-binding" for="txtFirstName">Address Line 1 </label>
<div class="input-group col-sm-8 col-lg-10 col-md-10">
<input id="cAddressL1" name="address" class="text-field " type="text" value="${sessionScope.user.address}" readonly> </div>

<label class="col-sm-4 col-lg-2 col-md-2 control-label ng-binding" for="txtFirstName">Address Line 2</label>
<div class="input-group col-sm-8 col-lg-10 col-md-10">
<input id="cAddressL2" name="addressTwo" class="text-field " type="text" value="${sessionScope.user.addressTwo}" readonly></div>

<label class="col-sm-4 col-lg-2 col-md-2 control-label ng-binding" for="txtFirstName">City</label>
<div class="input-group col-sm-8 col-lg-10 col-md-10">
<input id="cCity" name="city" class="text-field " type="text" value="${sessionScope.user.city}" readonly></div>

<label class="col-sm-4 col-lg-2 col-md-2 control-label ng-binding" for="txtFirstName">Pin Code</label>
<div class="input-group col-sm-8 col-lg-10 col-md-10">
<input id="cPin" class="text-field " name="pinCode" type="text" value="${sessionScope.user.pinCode}" readonly></div>

<label class="col-sm-4 col-lg-2 col-md-2 control-label ng-binding" for="txtFirstName">State </label>
<div class="input-group col-sm-8 col-lg-10 col-md-10">
<input id="cState" class="text-field " name="state" type="text" value="${sessionScope.user.state}" readonly></div>

<!-- <label class="col-sm-4 col-lg-2 col-md-2 control-label ng-binding" for="txtFirstName">State </label>
<div class="input-group col-sm-8 col-lg-10 col-md-10">
<input id="txtUserName" class="text-field " type="text"></div> -->

<div class="clearfix"></div>
<div class="col-md-2" style="margin-top: 15px; margin-left: 0px;">
<input id="UpdateContactInformation" class="btn btn-primary btn-success" type="submit" value="Update" onclick="hideSubmintBtn('3')" style="visibility:hidden;">
</div>
</div>
</form>
</div>



</div>
</div>

</div>



</div>
</div>
</div>
<link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/themes/smoothness/jquery-ui.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/jquery-ui.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/common.js" type="text/javascript"></script>
<script>

var readonly = true;
(function($) {
    $.fn.invisible = function() {
        return this.each(function() {
            $(this).css("visibility", "hidden");
        });
    };
    $.fn.visible = function() {
        return this.each(function() {
            $(this).css("visibility", "visible");
        });
    };
}(jQuery));
$('.example input[type="button"]').on('click', function() {
    $('.example input[type="text"]').attr('readonly', !readonly);

    readonly = !readonly;
    $('.example input[type="button"]').val( readonly ? 'Edit' : 'Save' );
    return false;
});

function hideSubmintBtn(val){
	
	
	if (val == '1') {

		} else if (val == '2') {
			var r = confirm("Are you sure you want to change personal information..!");
			if (r == true) {
			$("#UpdatePersonalInformation").invisible();
			$("#pFullName").prop("readonly", true);
			$("#pDateOfBirth").prop("readonly", true);
			//$("#pFatherName").prop("readonly", true);
			} else {
				   
			}
		
		} else if (val == '3') {
			var r = confirm("Are you sure you want to change contact information..!");
			if (r == true) {
			$("#UpdateContactInformation").invisible();
			$("#cFatherEmail").prop("readonly", true);
			$("#cFatherMob1").prop("readonly", true);
			$("#cFatherMob2").prop("readonly", true);
			$("#cAddressL1").prop("readonly", true);
			$("#cAddressL2").prop("readonly", true);
			$("#cCity").prop("readonly", true);
			$("#cPin").prop("readonly", true);
			$("#cState").prop("readonly", true);
		} else {
			   
		}
		}

	}
	function editLoginDetails() {
		$("#newPassword").prop("readonly", false);
		$("#confirmPassword").prop("readonly", false);
		$("#password").prop("readonly", false);

		$("#updateLoginDetails").visible();

	}
	function editPersonalInformation() {
		$("#pFullName").prop("readonly", false);
		$("#pDateOfBirth").prop("readonly", false);
		//$("#pFatherName").prop("readonly", false);
		

		$("#UpdatePersonalInformation").visible();
	}
	function editContactInformation() {
		$("#cFatherEmail").prop("readonly", false);
		$("#cFatherMob1").prop("readonly", false);
		$("#cFatherMob2").prop("readonly", false);
		$("#cAddressL1").prop("readonly", false);
		$("#cAddressL2").prop("readonly", false);
		$("#cCity").prop("readonly", false);
		$("#cPin").prop("readonly", false);
		$("#cState").prop("readonly", false);

		$("#UpdateContactInformation").visible();

	}

	/* $("#filePic").change(function() {

	    var val = $(this).val();

	    switch(val.substring(val.lastIndexOf('.') + 1).toLowerCase()){
	        case 'gif': case 'jpg': case 'png':
	        	$("#imgUpload").visible();
	            alert("an image");
	            break;
	        default:
	            $(this).val('');
	            // error message here
	            alert("not an image");
	            break;
	    }
	}); */
	/* var file = $('#filePic');
	file.on('change', function(e) {
		var reader = new FileReader();
		$("#imgUpload").visible();
		reader.onload = function() {
			var data = reader.result;
			if (data.match(/^data:image\//)) {

				$('#pImg').attr('src', data);
				$("#imgUpload").visible();
			} else {
				console.error('Not an image');
				alert("not an image");
			}
		};

		reader.readAsDataURL(file.prop('files')[0]);
	}); */
	
	
	function onchangeOfFile(){
		//alert("llllll");
		var reader = new FileReader();
		$("#imgUpload").visible();
		reader.onload = function() {
			var data = reader.result;
			if (data.match(/^data:image\//)) {

				$('#pImg').attr('src', data);
				$("#imgUpload").visible();
			} else {
				console.error('Not an image');
				alert("not an image");
			}
		};

		reader.readAsDataURL(file.prop('files')[0]);
	}
	/* var file = $('#filePic');
	file.on('change', function(e) {
		var reader = new FileReader();

		reader.onload = function() {
			var data = reader.result;
			if (data.match(/^data:image\//)) {

				$('#pImg').attr('src', data);
				$("#imgUpload").visible();
			} else {
				console.error('Not an image');
				alert("not an image");
			}
		};

		reader.readAsDataURL(file.prop('files')[0]);
	}); */
	
	function forUpdateUserPassword(){

		if ($.trim($('#newPassword').val()) == ""
				|| $.trim($('#confirmPassword').val()) == "") {
			alert("Please Enter Password..");
			return false; // Display error message
		} else if ($.trim($('#newPassword').val()) != ""
				&& $.trim($('#confirmPassword').val()) != "") {
			if ($.trim($('#newPassword').val()) == $.trim($(
					'#confirmPassword').val())) {
				var password = $('#password').val();
				var confirmPassword = $('#confirmPassword').val();
				//alert(password);
					var r = confirm("Are you sure you want to change Login details..!");
		if (r == true) {
			updateUserPassword(password,confirmPassword);
		} else {
		    x = "You pressed Cancel!";
		}
				
				
				//Show error message that password doesn't match
			} else {
				$('#newPassword').val("");
				$('#confirmPassword').val("");
				alert("Password missmach..");
				
			}
		}
		
	}
	
	
	
	
	
	function updateUserPassword(password,confirmPassword)
	{
		$.post("${pageContext.request.contextPath}/ajax/rest/resetUserPassword/" + password + "/" + confirmPassword, function(data)
				{
			
					if(data == true || data == 'true')
					{
						
						alert("Your password has been changed successfully..");
						$('#password').val("");
						 $('#newPassword').val("");
						$('#confirmPassword').val(""); 
						$("#updateLoginDetails").invisible();
						$("#newPassword").prop("readonly", true);
						$("#confirmPassword").prop("readonly", true);
						$("#password").prop("readonly", true);
						
					}
					else
					{
						
						/* $('#newPassword').val("");
						$('#confirmPassword').val(""); */
						alert("Old password is incorrect..");
						$('#password').val("");
					} 
				});
	};
	$(function() {
	    $( "#pDateOfBirth" ).datepicker();
	  });
</script>
<!-- middle panel end -->  
 <jsp:include page="studentFooter.jsp" /> 