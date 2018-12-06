<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.io.*,java.util.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>Iperformkonnect</title>
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
<link href="${pageContext.request.contextPath}/resources/css/student/newcss/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.5.0/css/font-awesome.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/student/newcss/AdminLTE.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/student/newcss/_all-skins.min.css">
 <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/student/newcss/datepicker3.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/student/newcss/bootstrap3-wysihtml5.min.css">
<script src="${pageContext.request.contextPath}/resources/js/student/newjs/highcharts.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/student/newjs/exporting.js"></script>
</head>
<body class="skin-blue sidebar-mini wysihtml5-supported fixed sidebar-collapse">
<div class="wrapper">
<header class="main-header">
 <a href="index.html" class="logo">
 <span class="logo-mini"><img src="${pageContext.request.contextPath}/resources/images/dist/img/mini-logo.png" alt="log" width="50" height="50"></span>
<span class="logo-lg"><img src="${pageContext.request.contextPath}/resources/images/dist/img/Neurona-Logo.png" alt="log" width="120" height="50"></span>
</a>
<nav class="navbar navbar-static-top">
 <a href="#" class="sidebar-toggle" data-toggle="offcanvas" role="button">
 <span class="sr-only">Toggle navigation</span>
 </a>
<div class="news-update-box" style="margin-top:13px;float: left;">
 <p style="float:left; color:#000;"> ${sessionScope.user.schoolName }</p>
 </div>
 <div class="navbar-custom-menu">
 <ul class="nav navbar-nav">
 <li class="dropdown user user-menu">
 <a href="#" class="dropdown-toggle" data-toggle="dropdown">
 <span class="hidden-xs pull-left" style="margin-right:10px">${sessionScope.user.name} (${sessionScope.user.gradeName })
 </span>

 <c:choose>
 <c:when test="${not empty sessionScope.user.profilePath}">
 <img src="${commonPath}/profileImage/${sessionScope.user.profilePath}" class="user-image" alt="User Image">
 </c:when>
 <c:otherwise>
 <img src="${pageContext.request.contextPath}/resources/images/dist/img/user2-160x160.jpg" class="user-image" alt="User Image">
 </c:otherwise>
 </c:choose>
 </a>
 <ul class="dropdown-menu">
 <!-- User image -->
 <li class="user-header">
 <c:choose>
 <c:when test="${not empty sessionScope.user.profilePath}">
 <img src="${commonPath}/profileImage/${sessionScope.user.profilePath}" class="img-circle" alt="User Image">
 </c:when>
 <c:otherwise>
 <img src="${pageContext.request.contextPath}/resources/images/dist/img/user2-160x160.jpg" class="img-circle" alt="User Image">
 </c:otherwise>
 </c:choose>
 <p>${sessionScope.user.name}</p>
 <p>${sessionScope.user.gradeName }</p>
 
 </li>
 <li class="user-footer">
 <div class="pull-left">
 <a href="${pageContext.request.contextPath}/student/studentprofile.htm" class="btn btn-default btn-flat">Profile</a>
 </div>
 <div class="pull-right">
 <a href="${pageContext.request.contextPath}/logout" class="btn btn-default btn-flat">Sign out</a>
 </div>
 </li> </ul>
 </li>
 </ul>
 </div>
 </nav>
 </header>
 
  <aside class="main-sidebar">
    <section class="sidebar">
      <ul class="sidebar-menu">
        <li class="active treeview">
         <a href="${pageContext.request.contextPath}/student/studentHome.htm">
 <i class="fa fa-dashboard"></i> <span>Dashboard</span>
 </a> 
          </li>


			<c:if test="${not empty sessionScope.user}">
			<c:forEach items="${sessionScope.user.subjects}" var="subject">
			<li class="treeview">
			<a href="${pageContext.request.contextPath}/student/subjectDetail.htm?sid=${subject.id}">
			
			<c:set var="string1" value="${subject.name}"/>

			<c:if test="${fn:containsIgnoreCase(string1, 'Math')}">
			<i class="fa fa-sort-numeric-desc"></i> 
		
			</c:if>
		
		<c:if test="${fn:containsIgnoreCase(string1, 'Sci')}">
		<i class="fa fa-flask"></i>
		</c:if>
		
			
			
			
			<span>${subject.name} </span>
			</a></li>
			</c:forEach>
			</c:if>

		
        <li class="treeview">
       
          <a href="#">
            <i class="fa fa-line-chart"></i> <span>Analytics</span>
          <span class="pull-right-container">
              <i class="fa fa-angle-left pull-right"></i>
            </span>
          </a>
           <ul class="treeview-menu">
           
           
           
           <c:if test="${not empty sessionScope.user}">
			<c:forEach items="${sessionScope.user.subjects}" var="subject">
			
			<c:set var="string1" value="${subject.name}"/>

			<c:if test="${fn:containsIgnoreCase(string1, 'Math')}">
			 <li><a href="${pageContext.request.contextPath}/student/learningReports.htm?sid=${subject.id}"><i class="fa fa-sort-numeric-desc"></i> ${subject.name}</a></li>
		
			</c:if>
		
		<c:if test="${fn:containsIgnoreCase(string1, 'Sci')}">
		 <li><a href="${pageContext.request.contextPath}/student/learningReports.htm?sid=${subject.id}"><i class="fa fa-flask"></i> ${subject.name}</a></li>
		</c:if>
		
			
			</c:forEach>
			</c:if>
           
           
           
         
          </ul>
        </li>
        
      </ul>
    </section>
   
  </aside> 

  
 <section class="content-wrapper" style="background-color:#fff;">
      
   <div class="row"  style="margin-top:15px;">
   <div class="col-lg-12">
     <section class="content-header">
      <h1>
        Edit
        <small>Student Profile</small>
      </h1>
      <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
        <li class="active">Dashboard</li>
      </ol>
    </section>
    <div class="col-md-3">

          <!-- Profile Image -->
          <div class="box box-primary">
          <form method="post" enctype="multipart/form-data" action="singleSave">
 <div class="profile-pic"> 
<c:if test="${not empty sessionScope.user.profilePath}">
<img class="profile-user-img img-responsive img-circle" id="pImg" src="${commonPath}/profileImage/${sessionScope.user.profilePath}" alt="" width="200" height="200">
 </c:if>
 </div>
<input id="filePic" type="file" name="file"  onchange=" onchangeOfFile()">
<input id="imgUpload" class="btn btn-primary btn-success" type="submit" value="Update"  style="float: right; visibility:hidden;" >
 </form>
            
            <!-- /.box-body -->
          </div>
        
        </div>
        <div class="col-md-9" style="border: solid 1px #ccc">
          <div class="nav-tabs-custom">
          <div class="tab-content">
         <div class=" active tab-pane" id="settings">
                <div class="edit-content">
<div class="edit-heading col-sm-12" style="margin-bottom:15px;">
<h4 class="col-sm-10">Login Details</h4><div class="btn btn-primary cursor col-sm-2" >
<a href="javascript:void(0);" onclick="editLoginDetails()" style="color:#fff">Edit</a></div></div>

<form class="form-horizontal">
<div class="row">
<div class="profile-form">
<div class="form-group">
<label class="col-sm-2 control-label ng-binding" for="txtFirstName">User Name</label>
<div class="col-sm-9">
<input  id="hiddenFieldUserName" class="form-control " type="text" readonly value="${sessionScope.user.userName}"></div>
</div>

<div class="form-group">
<label class="col-sm-2 control-label ng-binding" for="txtFirstName">Old Password</label>
<div class="col-sm-9">
<input id="password" class="form-control" type="password" readonly></div>
</div>

<div class="form-group">
<label class="col-sm-2 control-label ng-binding" for="txtFirstName">New Password</label>
<div class="col-sm-9">
<input id="newPassword" class="form-control" type="password"  readonly></div>
</div>

<div class="form-group">
<label class="col-sm-2 control-label  ng-binding" for="txtFirstName">Confirm Password</label>
<div class="col-sm-9">
<input id="confirmPassword" class="form-control" type="password"  readonly></div> 
</div>

<div class="form-group">

<div class="col-md-2" style="margin-top: 15px; margin-left: 0px;">
<input id="updateLoginDetails" class="btn btn-primary btn-success" type="submit" value="Update" style="visibility:hidden;" onclick="forUpdateUserPassword()">
</div>
</div>


</div>
</form>
</div>

<div class="edit-content" style="margin-top:20px;">
<div class="edit-heading col-sm-12"  style="margin-bottom:15px;">
<h4 class="col-sm-10">Personal Information</h4>
<div class="btn btn-primary cursor col-sm-2" ><a href="javascript:void(0);" onclick="editPersonalInformation()" style="color:#fff;">Edit</a></div></div>

<form class="form-horizontal"  modelAttribute="updatePInformation" method="post"  action="updateStudentPersonalInformation">
<div class="row">
<div class="profile-form">
<div class="form-group">
<label class="col-sm-2 control-label ng-binding" for="txtFirstName" >Full Name</label>
<div class="col-sm-9">
<input id="pFullName" name="firstName" class="form-control" type="text" value="${sessionScope.user.name}" readonly></div>
</div>

<div class="form-group">
<label class="col-sm-2 control-label ng-binding" for="txtFirstName">Date of Birth</label>
 <div class="col-sm-9">
<input id="datepicker" name="dateOfBirth" class="form-control" type="text" value="<fmt:formatDate pattern="MM/dd/yyyy" value="${sessionScope.user.studentDob}"/>">
</div>
</div>

<div class="form-group">
<label class="col-sm-2 control-label ng-binding" for="txtFirstName">Grade</label>
<div class="col-sm-9">
<input id="pGrade" class="form-control" type="text" readonly placeholder="" value="${sessionScope.user.gradeName}"></div>
</div>

<div class="form-group">
<label class="col-sm-2 control-label ng-binding" for="txtFirstName">School Name</label>
<div class="col-sm-9">
<input id="pSchool" class="form-control" type="text" readonly placeholder="" value="${sessionScope.user.schoolName}"></div>
</div>


<div class="form-group">
<div class="col-md-2" style="margin-top: 15px; margin-left: 0px;">
<input id="UpdatePersonalInformation" class="btn btn-primary btn-success" type="submit" value="Update" onclick="hideSubmintBtn('2')" style="visibility:hidden;">
</div>
</div>

</div>
</form>
</div>

<div class="edit-content" style="margin-top:20px;">
<div class="edit-heading col-sm-12" style="margin-bottom:15px;">
<h4 class="col-sm-10">Contact  Information</h4>
<div class="btn btn-primary cursor col-sm-2" ><a href="javascript:void(0);" onclick="editContactInformation()" style="color:#fff;">Edit</a></div>
</div>

<form class="form-horizontal" onsubmit="genarate('hiddenField')" modelAttribute="updateContactInformation" method="post"  action="updateContactInformation">
<div class="row">
<div class="profile-form">

<div class="form-group">
 <label class="col-sm-2 control-label ng-binding" for="txtFirstName">Address Line 1 </label>
<div class="col-sm-9">
<input id="cAddressL1" name="address" class="form-control" type="text" value="${sessionScope.user.address}" readonly> </div>
</div>

<div class="form-group">
<label class="col-sm-2 control-label ng-binding" for="txtFirstName">Address Line 2</label>
<div class="col-sm-9">
<input id="cAddressL2" name="addressTwo" class="form-control" type="text" value="${sessionScope.user.addressTwo}" readonly></div>
</div>

<div class="form-group">
<label class="col-sm-2 control-label ng-binding" for="txtFirstName">City</label>
<div class="col-sm-9">
<input id="cCity" name="city" class="form-control" type="text" value="${sessionScope.user.city}" readonly></div>
</div>

<div class="form-group">
<label class="col-sm-2 control-label ng-binding" for="txtFirstName">Pin Code</label>
<div class="col-sm-9">
<input id="cPin" class="form-control" name="pinCode" type="text" value="${sessionScope.user.pinCode}" readonly></div>
</div>

<div class="form-group">
<label class="col-sm-2 control-label ng-binding" for="txtFirstName">State </label>
<div class="col-sm-9">
<input id="cState" class="form-control" name="state" type="text" value="${sessionScope.user.state}" readonly></div>
</div>


<div class="clearfix"></div>
<div class="col-md-2" style="margin-top: 15px;">
<input id="UpdateContactInformation" class="btn btn-primary btn-success" type="submit" value="Update" onclick="hideSubmintBtn('3')" style="visibility:hidden;">
</div>
</div>
</form>
</div>



              </div>
              <!-- /.tab-pane -->
            </div>
            <!-- /.tab-content -->
          </div>
          <!-- /.nav-tabs-custom -->
        </div>
   
   </div>
  
 </div>
       
 
        
       
       
  
      

    </section>
  </div>
 
  <footer class="main-footer">
    <div class="pull-right hidden-xs">
      <b>Version</b> 2.3.12
    </div>
    <strong>Copyright &copy; 2016-2017 <a href="#">iperformkonnect </a>.</strong> All rights
    reserved.
  </footer>

  
  
</div>

<script src="${pageContext.request.contextPath}/resources/js/student/newjs/jquery-2.2.3.min.js"></script>
 <script src="https://code.jquery.com/ui/1.11.4/jquery-ui.min.js"></script>
<script> $.widget.bridge('uibutton', $.ui.button); </script>
<script src="${pageContext.request.contextPath}/resources/js/student/newjs/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/student/newjs/jquery.sparkline.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/student/newjs/jquery-jvectormap-1.2.2.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/student/newjs/jquery-jvectormap-world-mill-en.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/student/newjs/jquery.knob.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/student/newjs/jquery-jvectormap-world-mill-en.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.11.2/moment.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/student/newjs/bootstrap-datepicker.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/student/newjs/bootstrap3-wysihtml5.all.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/student/newjs/jquery.slimscroll.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/student/newjs/daterangepicker.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/student/newjs/app.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/student/newjs/dashboard.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/student/newjs/charts.js"></script>
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
 
	
	function onchangeOfFile(){
	 
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
	/* $(function() {
	    $( "#pDateOfBirth" ).datepicker();
	  }); */
	  
	  $('#datepicker').datepicker({
	      autoclose: true
	    });
</script>
</body>
</html>