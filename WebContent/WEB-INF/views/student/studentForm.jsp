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
<body class="skin-blue sidebar-mini wysihtml5-supported fixed">
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
 <p style="float:left; color:#fff;"> ${sessionScope.user.schoolName }</p>
 </div>
 <div class="navbar-custom-menu">
 <ul class="nav navbar-nav">
 <li class="dropdown user user-menu">
 <a href="#" class="dropdown-toggle" data-toggle="dropdown">
 <span class="hidden-xs pull-left" style="margin-right:10px">${sessionScope.user.name} <br>
 ${sessionScope.user.gradeName }</span>

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
     
      <h1 style="text-align:center; margin-bottom:20px;">
        Registration Form
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
<!-- <div class="edit-heading col-sm-12" style="margin-bottom:15px;">
<h4 class="col-sm-10">Login Details</h4><div class="btn btn-primary cursor col-sm-2" >
<a href="javascript:void(0);" onclick="editLoginDetails()" style="color:#fff">Edit</a></div></div> -->

<form class="form-horizontal" name="form1" action="${pageContext.request.contextPath}/user/studentcompleteRegistration" method="post">
<div class="row">
<div class="profile-form">

<div class="form-group" style="display:none">
<label class="col-sm-2 control-label ng-binding" for="txtFirstName">School Id</label>
<div class="col-sm-9">
<input  id="schoolId" name="schoolId" class="form-control " type="text" readonly value="${schoolId}"></div>
</div> 

<div class="form-group">
<label class="col-sm-2 control-label ng-binding" for="txtFirstName">School Code</label>
<div class="col-sm-9">
<input  id="schoolIdCode" name="schoolIdCode" class="form-control " type="text" readonly value="${schoolIdCode}"></div>
</div>
<!-- "hiddenFieldUserName" -->
<div class="form-group">
<label class="col-sm-2 control-label ng-binding" for="txtFirstName">Admission Id</label>
<div class="col-sm-9">
<input  id="admissionNo" name="admissionNo" class="form-control " type="text" readonly value="${admissionNo}"></div>
</div>

<div class="form-group">
<label class="col-sm-2 control-label ng-binding" for="txtFirstName">UserName</label>
<div class="col-sm-9">
<input  id="userName" name="userName" class="form-control " type="text" readonly value="${userName}"></div>
</div>

<%-- <div class="form-group">
<label class="col-sm-2 control-label ng-binding" for="txtFirstName">School Id</label>
<div class="col-sm-9">
<input  id="AadmissionNo" class="form-control " type="text" readonly value="${admissionNo}"></div>
</div> --%>

<div class="form-group">
<label class="col-sm-2 control-label ng-binding" for="txtFirstName">Password</label>
<div class="col-sm-9">
<input id="password" name="password" class="form-control" type="password" onchange="validateCode()"  required ></div>
</div>

<div class="form-group">
<label class="col-sm-2 control-label ng-binding" for="txtFirstName">First Name</label>
<div class="col-sm-9">
<input id="firstName" name="firstName" class="form-control" type="text"  value="${firstName}"></div>
</div>

<div class="form-group">
<label class="col-sm-2 control-label ng-binding" for="txtFirstName">Last Name</label>
<div class="col-sm-9">
<input id="lastName" name="lastName" class="form-control" type="text"  value="${lastName}"></div>
</div>

<div class="form-group">
<label class="col-sm-2 control-label ng-binding" for="txtFirstName">Class</label>
<div class="col-sm-9">
<input id="class" name="gradeName" class="form-control" type="text" readonly value="${gradeName}"></div>
</div>

<div class="form-group">
<label class="col-sm-2 control-label ng-binding" for="txtFirstName">Section</label>
<div class="col-sm-9">
<input id="section" name="sectionName" class="form-control" type="text" readonly value="${section}"></div>
</div>


<div class="form-group">
<label class="col-sm-2 control-label  ng-binding" for="txtFirstName">Email Id</label>
<div class="col-sm-9">
<input id="emailId" name="emailId" class="form-control" type="text" onchange="validateForm()"  required>
<span id="errorEmailMessage"
				style="color: red;"></span>
</div> 
  </div>
<div class="form-group">
<label class="col-sm-2 control-label  ng-binding" for="txtFirstName">Mobile No</label>
<div class="col-sm-9">
<input id="mobileNo" name="mobileNo" class="form-control" type="text"></div> 
</div>

<div class="form-group">

<div class="col-md-2" style="margin-top: 15px; margin-left: 0px;">
<input id="updateLoginDetails" class="btn btn-primary btn-success" type="submit" value="Register" 

style="visibility:block;" onclick="forUpdateUserPassword()">

<span id="successsEmailMessage"
				style="color: red;"></span>
</div>
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

function validateCode(){
    var TCode = document.getElementById('password').value;
    if( /[^a-zA-Z0-9]/.test( TCode ) ) {
       alert('Input is not alphanumeric');
       return false;
    }
    return true;     
 }


function validateForm() {
    var x = document.forms["form1"]["email"].value;
    var atpos = x.indexOf("@");
    var dotpos = x.lastIndexOf(".");
    if (atpos<1 || dotpos<atpos+2 || dotpos+2>=x.length) {
        alert("Not a valid e-mail address");
        return false;
    }
}

</script>
    <script src="https://code.jquery.com/jquery-1.9.1.min.js"></script>
    <script>
   /*  $( document ).ready(function() {
    	alert('ready');
    }); */
 
    $( window ).on( "load", function() {
    	//alert('load');
    	$("#errorEmailMessage").html('${errMsg2}');
    	$("#successsEmailMessage").html('${errMsg2}');
    	
    });
    </script>




<script>

/* alert('sadasfdafsdfsfdsfdsf1111111111111');
reader.onload = function() {
	alert('sdfdsds1133333333333331111111111111');

$("#errorEmailMessage").html('${errMsg2}');
} */
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

		if ($.trim($('#password').val()) == "") {
			alert("Please Enter Password..");
			return false;
			if ($.trim($('#emailId').val()) == "") {
				alert("Please Enter Mail Id..");
				return false;
				<c:if test="${not empty errMsg2}">
		        $("#errorEmailMessage").html('${errMsg2}');
		        $(".wrapper").show();
		        <c:remove var="errMsg2" scope="session"/>
		        </c:if>	
			
			
			// Display error message
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