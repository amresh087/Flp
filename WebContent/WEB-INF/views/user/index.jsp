<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" href="../../favicon.ico">

<title>iPerform Konnect</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/font-awesome.css"
	type="text/css" media="screen">
<link rel="stylesheet" type="text/css"
	href="//fonts.googleapis.com/css?family=Abel" />
<link href='http://fonts.googleapis.com/css?family=Roboto'
	rel='stylesheet' type='text/css'>

<!-- Bootstrap core CSS -->
<link
	href="${pageContext.request.contextPath}/resources/css/bootstrap.css"
	rel="stylesheet">
<link
	href="${pageContext.request.contextPath}/resources/css/custom-home.css"
	rel="stylesheet">

<!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
<!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->
<script
	src="${pageContext.request.contextPath}/resources/js/ie-emulation-modes-warning.js"></script>

<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
<script
	src="${pageContext.request.contextPath}/resources/js/jquery-1.11.0.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/common.js"></script>

<!-- Custom styles for this template -->
<link
	href="${pageContext.request.contextPath}/resources/css/carousel.css"
	rel="stylesheet">




</head>


<body>

	<!-- code for forgot password -->
	<div class="wrapper3">

		<%--   <%@ include file="userRegistration.jsp" %> --%>
		<form class="form-signin" action="" method="post">

			<div class="close">
				<i class="fa fa-times" onClick="closeLogin()"></i>
			</div>


			<h2 class="form-signin-heading">Forgot Password</h2>
			<input type="text" name="username" placeholder="Enter Email-Id"
				required class="loginCls form-control" id="forgotEmailId" /> <span
				id="errorMsg3" style="color: red;"></span> <span id="successMsg3"
				style="color: green;"></span> <a href="javascript:void(0);"
				class="btn btn-lg btn-primary btn-block" onclick="forgotPassword()">Forgot
				Password</a> <br />

		</form>

	</div>
	<!-- end -->

	<!-- code for reset password -->
	<!-- code for forgot password -->
	<div class="wrapper4">

		<%--   <%@ include file="userRegistration.jsp" %> --%>
		<form class="form-signin" action="" method="post">

			<div class="close">
				<i class="fa fa-times" onClick="closeLogin()"></i>
			</div>


			<h2 class="form-signin-heading">Reset Password</h2>
			<input type="hidden" id="huserId" /> <input type="password"
				name="password" placeholder="Enter password" required
				class="loginCls form-control" id="passwordId1" /> <input
				type="password" name="password1" placeholder="confirm Password"
				required class="loginCls form-control" id="passwordId2" /> <span
				id="errorMsg4" style="color: red;"></span> <span id="successMsg4"
				style="color: green;"></span> <a href="javascript:void(0);"
				class="btn btn-lg btn-primary btn-block" onclick="reserPassword()">Reset
				Password</a> <br />

		</form>

	</div>
	<!-- end -->

	<div class="wrapper">

		<%--   <%@ include file="userRegistration.jsp" %> --%>
		<form class="form-signin"
			action="${pageContext.request.contextPath}/userlogin.htm"
			method="post">

			<div class="close">
				<i class="fa fa-times" onClick="closeLogin()"></i>
			</div>


			<h2 class="form-signin-heading">Please login</h2>
			<input type="text" name="username" placeholder="username" required
				class="loginCls form-control" /> <input type="password"
				name="password" placeholder="Password" required
				class="loginCls form-control" /> <span id="errorMsg1"
				style="color: red;"></span> <span id="successMsg1"
				style="color: green;"></span>
			<button class="btn btn-lg btn-primary btn-block" type="submit">Login</button>
			<br />
			<div align="right">
				<a href="javascript:void(0)" onclick="forgotPwd()">forgot
					password?</a>
			</div>
		</form>

	</div>

	<div class="wrapper1">


		<form
			action="${pageContext.request.contextPath}/user/retailUserOrParentSignup.htm"
			method="post" id="userSignUpId" class="form-signin" role="form">
			<div class="close">
				<i class="fa fa-times" onClick="closeLogin()"></i>
			</div>


			<h2 class="form-signin-heading">User Sign up</h2>

			<select name="userType" required="required"
				class="loginCls form-control userTypeCls" id="userTypeId">
				<option value="">Select UserType</option>
				<option value="4">Student</option>
				<option value="3">Parent</option>
				
			</select> <input type="text" name="firstName" placeholder="Enter Your Name"
				required class="loginCls form-control" /> <input type="email"
				name="emailId" placeholder="Enter your EmailId" required
				class="loginCls form-control" /> <input type="password"
				name="password" placeholder="Password" required
				class="loginCls form-control" /> <input type="tel" name="mobileNo"
				placeholder="Mobile Number" class="loginCls form-control"
				pattern="[0-9]{10,11}" maxlength="10" />
				<span id="showHideBoardGrade" style="display: none;">
				 <select name="boardId" id="boardId"
			 class="loginCls form-control"
				onchange="getGradeList('#gradeId', $(this).val(), '${pageContext.request.contextPath}/getGradeByBoardId', 'Please Select Grade');">
				<option value="">Select Board</option>
				<c:if test="${not empty boardList }">
					<c:forEach var="board" items="${boardList }">
						<c:if test="${board.id ne 1 }">
							<option value="${board.id }">${board.name }</option>
						</c:if>
					</c:forEach>

				</c:if>
			</select> <select name="gradeId" 
				class="loginCls form-control" id="gradeId">
				<option value="">Select Grade</option>
			</select> 
			</span>
			<span id="errorMsg2" style="color: red;">
			</span> <span
				id="successMsg" style="color: green;"></span>
			<button class="btn btn-lg btn-primary btn-block" type="submit">SignUp</button>
		</form>

	</div>

	<!--top-black-->
	<div class="row-fluid">

		<div class="col-md-3 col-xs-12 logo pull-left">
			<a href="index.html"><img
				src="${pageContext.request.contextPath}/resources/images/logo-home.png"
				alt="iPerform Konnect" title="iPerform Konnect" /></a>
		</div>


		<div class="top-blk">

			<div class="container">

				<div class="row">

					<ul class="pull-right">
						<li><img
							src="${pageContext.request.contextPath}/resources/images/mail.png"
							alt="" /><span><a href="javascript:void(0);">communications@classteacher.com</a></span></li>
						<li><img
							src="${pageContext.request.contextPath}/resources/images/call.png"
							alt="" /><span>Toll Free: 1800-103-6768</span></li>
					</ul>

				</div>

			</div>


		</div>


	</div>
	<!--top-black-->

	<div class="clearfix"></div>


	<!--social-media-->


	<div class="container" style="position: relative;">

		<div class="row">



			<%-- <div class="social-media pull-right text-right">

				<ul>
					<li><a href="#"><img
							src="${pageContext.request.contextPath}/resources/images/fb.png"
							alt="" /></a></li>
					<li><a href="#"><img
							src="${pageContext.request.contextPath}/resources/images/gplus.png"
							alt="" /></a></li>
					<li><a href="#"><img
							src="${pageContext.request.contextPath}/resources/images/gal.png"
							alt="" /></a></li>
					<li><a href="#"><img
							src="${pageContext.request.contextPath}/resources/images/printrest.png"
							alt="" /></a></li>
					<li><a href="#"><img
							src="${pageContext.request.contextPath}/resources/images/twitter.png"
							alt="" /></a></li>
					<li><a href="#"><img
							src="${pageContext.request.contextPath}/resources/images/res-btn.png"
							alt="" /></a></li>
				</ul>

			</div> --%>


		</div>

	</div>
	<!--social-media-->



	<!-- Carousel
    ================================================== -->
	<div id="myCarousel" class="carousel slide" data-ride="carousel">


		<!-- Indicators -->
		<ol class="carousel-indicators">
			<li data-target="#myCarousel" data-slide-to="0" class="active"></li>
			<li data-target="#myCarousel" data-slide-to="1"></li>

		</ol>

		<div class="carousel-inner" role="listbox">

			<!--login-res-->
			<div class="container login-main">

				<div class="freetrial col-md-6">

					<div class="row">

					<!-- 	<div class="btn-group" role="group" aria-label="...">


							<div class="btn-group" role="group">
								<button type="button"
									class="btn btn-default dropbtn dropdown-toggle"
									data-toggle="dropdown" aria-haspopup="true"
									aria-expanded="false">
									Select Board <span class="caret"></span>
								</button>
								<ul class="dropdown-menu trial-list">
									<li><a href="#">CBSE</a></li>
									<li><a href="#">ICSE</a></li>
								</ul>
							</div>
							<div class="btn-group " role="group">
								<button type="button"
									class="btn btn-default dropbtn dropdown-toggle"
									data-toggle="dropdown" aria-haspopup="true"
									aria-expanded="false">
									Select Grade <span class="caret"></span>
								</button>
								<ul class="dropdown-menu trial-list">
									<li><a href="#">Grade-01</a></li>
									<li><a href="#">Grade-02</a></li>
									<li><a href="#">Grade-03</a></li>
									<li><a href="#">Grade-04</a></li>
									<li><a href="#">Grade-05</a></li>
									<li><a href="#">Grade-06</a></li>
									<li><a href="#">Grade-07</a></li>
									<li><a href="#">Grade-08</a></li>
									<li><a href="#">Grade-09</a></li>
									<li><a href="#">Grade-10</a></li>

								</ul>
							</div>
							<button type="button" class="btn btn-default freebtn">Free
								Trial</button>


						</div> -->
					</div>


				</div>

				<div class="login-res">

					<button type="button" onClick="loginDiv()"
						class="btn btn-primary login ownbt pull-left">Log In</button>
					<button type="button" onClick="signUpDiv()"
						class="btn btn-danger registration ownbt pull-right">Sign
						Up</button>


				</div>

			</div>
			<!--login-res-->


			<div class="item active">
				<img class="first-slide"
					src="${pageContext.request.contextPath}/resources/images/slide1.jpg"
					alt="First slide">

				<div class="container">

					<div class="carousel-caption option-bg col-md-5 col-sm-6 col-xs-12">

						<h1>
							<span>Smart Technology</span> to initiate
						</h1>
						<p>
							self-learning and curiosity<br> among the students.
						</p>

					</div>


				</div>
			</div>

			<div class="item">
				<img class="second-slide"
					src="${pageContext.request.contextPath}/resources/images/slide2.jpg"
					alt="First slide">

				<div class="container">

					<div
						class="carousel-caption  option-bg col-md-5 col-sm-6 col-xs-12">

						<h1>
							<span>Smart Technology</span> to initiate
						</h1>
						<p>
							self-learning and curiosity<br> among the students.
						</p>

					</div>


				</div>
			</div>


			<a class="carousel-control" href="#myCarousel" role="button"
				data-slide="prev"> <span
				class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
				<span class="sr-only">Previous</span>
			</a> <a class="right carousel-control" href="#myCarousel" role="button"
				data-slide="next"> <span
				class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
				<span class="sr-only">Next</span>
			</a>
		</div>
		<!-- /.carousel -->
	</div>
	<div style="border-bottom: 12px solid #39393d; width: 100%;"></div>


	<div></div>


	<div class="clearfix"></div>

	<!-- MAIN TEXT -->
	<div class="main-text">

		<p>
			<span class="red-txt">iPerformKonnect</span> is a <span
				class="gry-txt">Cloud based</span>, Multi platform, Device
			independent learning zone creating customized learning based on the
			ability of an <span class="blu-txt">individual student.</span>
		</p>


	</div>
	<!-- MAIN TEXT -->

	<div class="col-md-4 pull-left"></div>

	<div class="col-md-4 col-sm-12 col-xs-12 how-add pull-left">
		<h2>How Do You Learn?</h2>
	</div>


	<div class="col-md-4  pull-left"></div>

	<div class="clearfix"></div>

	<!-- HOW DO WE LEARN -->

	<div class="container marketing">

		<div class="row">


			<div
				class="col-md-3 col-sm-6 col-xs-6 text-center select-topic ">

				<img class="img-circle"
					src="${pageContext.request.contextPath}/resources/images/1.png"
					alt="">

				<h3>
					Select Your<br>Topic
				</h3>
				<p>
					Every subject has a huge variety<br> of topics
				</p>

			</div>


			<div class="col-md-3 col-sm-6 col-xs-6 text-center select-topic">
				<img class="img-circle"
					src="${pageContext.request.contextPath}/resources/images/2.png"
					alt="">
				<h3>
					Start Your<br>Assessment
				</h3>
				<p>
					Access to our system generated<br> and teacher created
					assessments
				</p>

			</div>



			<div class="col-md-3 col-sm-6 col-xs-6 text-center select-topic">
				<img class="img-circle"
					src="${pageContext.request.contextPath}/resources/images/4.png"
					alt="">
				<h3>
					Go Through Learning <br> Content
				</h3>
				<p>
					Choose from our exclusive online<br> library with over 50,000
					modules
				</p>

			</div>



			<div
				class="col-md-3 col-sm-6 col-xs-6 text-center select-topic select-border">
				<img class="img-circle"
					src="${pageContext.request.contextPath}/resources/images/3.png"
					alt="">
				<h3>
					Get Instant<br>Report
				</h3>
				<p>
					Instant analytical report can be<br> generated by everyone
				</p>

			</div>


		</div>
	</div>
	<!-- HOW DO WE LEARN -->



	<!-- BLUE BG START -->
	<div class="blue-bg">


		<div class="container">


			<div class="col-md-4 pull-left"></div>

			<div class="col-md-4 col-sm-12 col-xs-12 getstart pull-left">
				<h2>
					User Account - <span>Get Started</span>
				</h2>
			</div>

			<div class="col-md-4  pull-left"></div>

			<div class="clearfix"></div>



			<div class="row">


				<div class="col-md-4 col-sm-4">

					<div class="user-account">
						<img
							src="${pageContext.request.contextPath}/resources/images/admin-icon.png"
							class="pull-left" alt="" title="" />
						<div class="user-text">
							<h2>School Admin</h2>
							<p>
								School need not worry about<br> reaching out to everyone..<br>
								<span><a href="#" data-toggle="tooltip"
									class="media-tooltip" data-placement="right"
									title="The school admin is the main body who is responsible for the operation of a school. Execution of various effective learning and teaching methods, handling the student, teacher and parent account is made easier with instant messaging system. Integrate the people you wish to with our interface and compare the performances. You will can make a difference to many people.">Read
										More</a></span>
							</p>
						</div>
					</div>


					<div class="user-account">
						<img
							src="${pageContext.request.contextPath}/resources/images/student-icon.png"
							class="pull-left" alt="" title="" />
						<div class="user-text">
							<h2>Student</h2>
							<p>
								Focusing on your grades has<br> never been easy...<br>
								<span><a href="#" data-toggle="tooltip"
									class="media-tooltip" data-placement="top"
									title="The students have an access to our whole online digital library anywhere and anytime with an internet connection. Apart from our digital library, we have a huge range of fun, short and up-to the point videos  to capture the interest of our learners. Keeping in mind the inquisitive nature of a human being, we  have used smart technology to initiate self-learning and curiosity among the students. Bring out the best in the learners with our fun assessment driven learning.">Read
										More</a></span>
							</p>
						</div>
					</div>

				</div>

				<div class="col-md-4 col-sm-4 classpad">
					<img
						src="${pageContext.request.contextPath}/resources/images/classpad.png"
						class="img-responsive" title="" />
				</div>

				<div class="col-md-4 col-sm-4">

					<div class="user-account">
						<img
							src="${pageContext.request.contextPath}/resources/images/teacher-icon.png"
							class="pull-left" alt="" title="" />
						<div class="user-text">
							<h2>Teacher / Tutor</h2>
							<p>
								No more spending time on<br> lengthy reports..<br> <span><a
									href="#" data-toggle="tooltip" class="media-tooltip"
									data-placement="left"
									title="The only focus of a teacher should be on the learning and teaching of their students, but nowadays they also have to look into other responsibilities which take a lot of their time and energy. Here in iPerformKonnect, we want the teacher to pay attention only to the educational progress of a student and we prepare all the reports for the students, easy one touch method to send study materials and also create test online. With teachers at ease in their job, the performance of the students will automatically reach its zenith.">Read
										More</a></span>
							</p>
						</div>
					</div>


					<div class="user-account">
						<img
							src="${pageContext.request.contextPath}/resources/images/parent-icon.png"
							class="pull-left" alt="" title="" />
						<div class="user-text">
							<h2>Parent</h2>
							<p>
								Why bother teachers for any<br> child report or progress?..<br>
								<span><a href="#" data-toggle="tooltip"
									class="media-tooltip" data-placement="top"
									title="The parents will have their exclusive portal where they can login using their login id and password. In this zone they will have full access to their child’s individual report as well as the whole class performance. We also have provided the option to send and receive about any important message from the school. Parents can help out the child in the best possible manner now.">Read
										More</a></span>
							</p>
						</div>
					</div>

				</div>

			</div>



		</div>


	</div>
	<!-- BLUE BG END -->


	<div class="clearfix"></div>


	<!--Features-start-here-->
	<div class="container">

		<div class="col-md-8 features">
			`
			<h2>Features</h2>

			<div class="col-md-6 feature-inner">

				<img
					src="${pageContext.request.contextPath}/resources/images/cloud-icon.png"
					class="pull-left" title="" alt="" />

				<div class="inner-text">
					<h3>Cloud-based product</h3>
					<p>
						Anybody with our assigned username<br> and password can use
						it.<br> <span><a href="javascript:void(0);">&nbsp;&nbsp;</a></span>
					</p>
				</div>

			</div>


			<div class="col-md-6 feature-inner">

				<img
					src="${pageContext.request.contextPath}/resources/images/assessment.png"
					class="pull-left" title="" alt="" />

				<div class="inner-text">
					<h3>Multi User Platform</h3>
					<p>
						Multiple Users (Student,Parent,Teacher)<br/> can used the same platform <br/>in a customized way. 
					</p>
				</div>

			</div>


			<div class="col-md-6 feature-inner">

				<img
					src="${pageContext.request.contextPath}/resources/images/driven.png"
					class="pull-left" title="" alt="" />

				<div class="inner-text">
					<h3>Assessment driven learning</h3>
					<p>
						Assessment driven learning: Learning<br> 
						through practice.<br> <!-- <span><a href="#">Read More</a></span> -->
					</p>
				</div>

			</div>


			<div class="col-md-6 feature-inner">

				<img
					src="${pageContext.request.contextPath}/resources/images/analytic.png"
					class="pull-left" title="" alt="" />

				<div class="inner-text">
					<h3>Built in analytics</h3>
					<p>
						We are offering guided learning based<br> on Spaced which is
						a technique<br> <!-- <span><a href="#">Read More</a></span> -->
					</p>
				</div>


			</div>


			<div class="col-md-6 feature-inner">

				<img
					src="${pageContext.request.contextPath}/resources/images/reports.png"
					class="pull-left" title="" alt="" />

				<div class="inner-text">
				<h3>Analytics based reports</h3>
					<p>
						reports: Teacher and Parents can get<br> the reports to
						monitor the<br> <!-- <span><a href="#">Read More</a></span> -->
					</p>
				</div>

			</div>


			<div class="col-md-6 feature-inner">

				<img
					src="${pageContext.request.contextPath}/resources/images/system-icon.png"
					class="pull-left" title="" alt="" />

				<div class="inner-text">
					<h3>Device independent</h3>
					<p>
						Cloud based hosting of product is what<br> makes its immense
						presence<br> <!-- <span><a href="#">Read More</a></span> -->
					</p>
				</div>


			</div>


		</div>

		<div class="clearboth"></div>

		<div class="col-md-1"></div>

		<div class="clearboth"></div>

		<!--Testimonial-start-here-->
		<div class="col-md-3 testimonial">

			<h2>Testimonial</h2>
			<div id="NewsSlider" class="carousel slide" data-ride="carousel">


				<!-- Indicators -->
				<ol class="news-indicators carousel-indicators">
					<li data-target="#NewsSlider" data-slide-to="0" class="active"></li>
					<!-- <li data-target="#NewsSlider" data-slide-to="1"></li> -->

				</ol>

				<div class="carousel-inner" role="listbox">


					<!--Slider-first-->
					<div class="item active">
						<br>
						<div class="news">
							<p>
								Dear <span>Classkonnect,</span>
							</p>
							<img
								src="${pageContext.request.contextPath}/resources/images/testi.png"
								class="pull-right">"I would not have asked for better
							experience ,I think using technology on a daily basis is giving
							my child and extra advantage educationally...<br> <br>
							<strong>-----Gunjal Yadav(New delhi)</strong>

						</div>
						<br>
						<div class="clearfix"></div>

						<div class="news">
							<p>
								Dear <span>Classkonnect,</span>
							</p>
							<img
								src="${pageContext.request.contextPath}/resources/images/testi1.png"
								class="pull-left"> "I have been working with Class Teacher Learning Systems since the past 2 years and it has always been a pleasure working with them.Their creative modules and ...<br> <br>
							<strong>-----Bharti Prashad(Kanpur)</strong>

						</div>

					</div>
					<!--Slider-first-->



					<!--Slider-second-->
					<%-- <div class="item">

						<br>
						<div class="news">
							<p>
								Dear <span>Classkonnect,</span>
							</p>
							<img
								src="${pageContext.request.contextPath}/resources/images/testi.png"
								class="pull-right">" I would not have asked for better
							experience ,I think using technology on a daily basis is giving
							my child and extra advantage educationally...<br> <br>
							<strong>-----Gunjal Yadav(New delhi)</strong>

						</div>
						<br>
						<div class="clearfix"></div>

						<div class="news">
							<p>
								Dear <span>Classkonnect,</span>
							</p>
							<img
								src="${pageContext.request.contextPath}/resources/images/testi1.png"
								class="pull-left"> " I would not have asked for better
							experience ,I think using technology on a daily basis is giving
							my child and extra advantage educationally...<br> <br>
							<strong>-----Gunjal Yadav(New delhi)</strong>

						</div>

					</div> --%>
					<!--Slider-second-->


				</div>
				<!-- /.carousel -->
			</div>


		</div>
		<!--Testimonial-end-here-->


	</div>
	<!--Features-end-here-->

	<hr class="featurette-divider">

	<!-- /END THE FEATURETTES -->




	<!-- Start Footer Section -->
	<footer>

		<footer>

			<div class="container">
				<div class="row footer-widgets">

					<!-- Start Subscribe & Social Links Widget -->
					<div class="col-md-3 col-xs-12 col-sm-3">
						<div class="footer-widget mail-subscribe-widget">
							<h4>
								Get in touch<span class="head-line"></span>
							</h4>
							<p>Join our mailing list to stay up to date and get notices
								about our new releases!</p>
							<!-- <form class="subscribe"> -->
								<input type="text" placeholder="mail@example.com"> <input
									type="button" class="btn-system" value="Send">
							<!-- </form> -->
						</div>

						<div class="footer-widget social-widget">
							<h4>
								Follow Us<span class="head-line"></span>
							</h4>
							<ul class="social-icons">
								<li><a class="facebook" href="#"><i
										class="fa fa-facebook"></i></a></li>
								<li><a class="twitter" href="#"><i
										class="fa fa-twitter"></i></a></li>
								<li><a class="google" href="#"><i
										class="fa fa-google-plus"></i></a></li>
								<li><a class="dribbble" href="#"><i
										class="fa fa-dribbble"></i></a></li>
								<li><a class="linkdin" href="#"><i
										class="fa fa-linkedin"></i></a></li>
								<li><a class="flickr" href="#"><i class="fa fa-flickr"></i></a></li>
								<li><a class="tumblr" href="#"><i class="fa fa-tumblr"></i></a></li>
								<li><a class="instgram" href="#"><i
										class="fa fa-instagram"></i></a></li>
								<li><a class="vimeo" href="#"><i
										class="fa fa-vimeo-square"></i></a></li>
								<li><a class="skype" href="#"><i class="fa fa-skype"></i></a></li>
							</ul>
						</div>
					</div>
					<!-- .col-md-3 -->
					<!-- End Subscribe & Social Links Widget -->


					<!-- Start Twitter Widget -->
					<div class="col-md-3 col-xs-12 col-sm-3">

						<div class="footer-widget twitter-widget">

							<h4>
								Twitter Feed<span class="head-line"></span>
							</h4>

							<ul>
								<li>
									<p>
										<a href="javascript:void(0);">@Aruna basu </a> Learning has never been so easy.
									</p> <span>28 September 2015</span>
								</li>
							<!-- 	<li>
									<p>
										<a href="#">@GrayGrids </a> Lorem ipsum dolor et, consectetur
										adipiscing eli.An Fusce eleifend aliquet nis application.
									</p> <span>12 August 2015</span>
								</li>
								<li>
									<p>
										<a href="#">@GrayGrids </a> Lorem ipsum dolor et, consectetur
										adipiscing eli.
									</p> <span>16 July 2015</span>
								</li> -->
							</ul>
						</div>
					</div>
					<!-- .col-md-3 -->
					<!-- End Twitter Widget -->


					<!-- Start Flickr Widget -->
					<div class="col-md-3 col-xs-12 col-sm-3">
						<div class="footer-widget flickr-widget">
							<h4>
								Flicker Feed<span class="head-line"></span>
							</h4>
							<ul class="flickr-list">
								<li><a
									href="${pageContext.request.contextPath}/resources/images/flickr-01.jpg"
									class="lightbox"> <img alt=""
										src="${pageContext.request.contextPath}/resources/images/flickr-01.jpg">
								</a></li>
								<li><a
									href="${pageContext.request.contextPath}/resources/images/flickr-02.jpg"
									class="lightbox"> <img alt=""
										src="${pageContext.request.contextPath}/resources/images/flickr-02.jpg">
								</a></li>
								<li><a
									href="${pageContext.request.contextPath}/resources/images/flickr-03.jpg"
									class="lightbox"> <img alt=""
										src="${pageContext.request.contextPath}/resources/images/flickr-03.jpg">
								</a></li>
								<li><a
									href="${pageContext.request.contextPath}/resources/images/flickr-04.jpg"
									class="lightbox"> <img alt=""
										src="${pageContext.request.contextPath}/resources/images/flickr-04.jpg">
								</a></li>
								<li><a
									href="${pageContext.request.contextPath}/resources/images/flickr-05.jpg"
									class="lightbox"> <img alt=""
										src="${pageContext.request.contextPath}/resources/images/flickr-05.jpg">
								</a></li>
								<li><a
									href="${pageContext.request.contextPath}/resources/images/flickr-06.jpg"
									class="lightbox"> <img alt=""
										src="${pageContext.request.contextPath}/resources/images/flickr-06.jpg">
								</a></li>
								<li><a
									href="${pageContext.request.contextPath}/resources/images/flickr-07.jpg"
									class="lightbox"> <img alt=""
										src="${pageContext.request.contextPath}/resources/images/flickr-07.jpg">
								</a></li>
								<li><a
									href="${pageContext.request.contextPath}/resources/images/flickr-08.jpg"
									class="lightbox"> <img alt=""
										src="${pageContext.request.contextPath}/resources/images/flickr-08.jpg">
								</a></li>
								<li><a
									href="${pageContext.request.contextPath}/resources/images/flickr-09.jpg"
									class="lightbox"> <img alt=""
										src="${pageContext.request.contextPath}/resources/images/flickr-09.jpg">
								</a></li>
							</ul>
						</div>
					</div>
					<!-- .col-md-3 -->
					<!-- End Flickr Widget -->


					<!-- Start Contact Widget -->
					<div class="col-md-3 col-xs-12 col-sm-3">
						<div class="footer-widget contact-widget">
							<h4>
								<img
									src="${pageContext.request.contextPath}/resources/images/footer-margo.png"
									class="img-responsive" alt="Footer Logo" />
							</h4>
							<p>
								Mind Shaper Technologies<br>Plot no. 188, Udyog Vihar<br> Phase -4,<br>
								Gurgaon, Haryana<br> Phone: 1800-103-6768<br> Email:
								communications@classteacher.com
							</p>

							<ul>
								<li><span>Phone Number:</span> 1800-103-6768</li>
								<li><span>Email:</span> communications@classteacher.com</li>
								<li><span>Website:</span> http://www.iperformkonnect.com/</li>
							</ul>
						</div>
					</div>
					<!-- .col-md-3 -->
					<!-- End Contact Widget -->


				</div>
				<!-- .row -->

				<!-- Start Copyright -->
				<div class="copyright-section">
					<div class="row">
						<div class="col-md-6">
							<p>
								&copy; 2015 Classkonnect - All Rights Reserved <a href="#">GrayGrids</a>
							</p>
						</div>
						<!-- .col-md-6 -->
						<div class="col-md-6">
							<ul class="footer-nav">
								<li><a href="#">Sitemap</a></li>
								<li><a href="#">Privacy Policy</a></li>
								<li><a href="#">Contact</a></li>
							</ul>
						</div>
						<!-- .col-md-6 -->
					</div>
					<!-- .row -->
				</div>
				<!-- End Copyright -->

			</div>

		</footer>

	</footer>
	<!-- End Footer Section -->




	<script>
	
	$(".userTypeCls").change(function(){
		if($(this).val() == 4 || $(this).val() == '4'){
			$("#showHideBoardGrade").show();
			$("#boardId").attr('required','required');
			$("#gradeId").attr('required','required');
		}else {
			$("#showHideBoardGrade").hide();
			$("#boardId").removeAttr('required');
			$("#gradeId").removeAttr('required');
		}
	});
	
	
		$(document).ready(function()
        {
	        
	        $('[data-toggle="tooltip"]').tooltip();
	        
	        <c:if test="${not empty errMsg1}">
	        $("#errorMsg1").html('${errMsg1}');
	        $(".wrapper").show();
	        <c:remove var="errMsg1" scope="session"/>
	        </c:if>

	        <c:if test="${not empty successMsg1}">
	        $("#successMsg1").html('${successMsg1}');
	        $(".wrapper").show();
	        <c:remove var="successMsg1" scope="session"/>
	        </c:if>

	        <c:if test="${not empty errMsg2}">
	        $("#errorMsg2").html('${errMsg2}');
	        $(".wrapper1").show();
	        <c:remove var="errMsg2" scope="session"/>
	        </c:if>

	        <c:if test="${not empty successMsg}">
	        $("#successMsg").html('${successMsg}');
	        $(".wrapper1").show();
	        <c:remove var="successMsg" scope="session"/>
	        </c:if>

	        <c:if test="${not empty forgotPwdUserId}">
	        $(".wrapper4").show();
	        $("#huserId").val("${forgotPwdUserId}");
	        <c:remove var="forgotPwdUserId" scope="session"/>

	        </c:if>
        });
		
	
		$('[data-toggle="tooltip"]').tooltip(
        {
	        'placement' : function(tt, trigger)
	        {
		        var $trigger = $(trigger);
		        var windowWidth = $(window).width();
		        
		        var xs = $trigger.attr('data-placement-xs');
		        var sm = $trigger.attr('data-placement-sm');
		        var md = $trigger.attr('data-placement-md');
		        var lg = $trigger.attr('data-placement-lg');
		        var general = $trigger.attr('data-placement');
		        
		        return (windowWidth >= 1200 ? lg : undefined) || (windowWidth >= 992 ? md : undefined) || (windowWidth >= 768 ? sm : undefined) || (windowWidth >= 768 ? xs : "top") || general || "top";
	        }
        });
        
        function closeLogin()
        {
	        $(".wrapper").hide();
	        $(".wrapper1").hide();
	        $(".wrapper3").hide();
	        $(".wrapper4").hide();
        }

        function loginDiv()
        {
	        $(".wrapper").show();
	        
        }
        function signUpDiv()
        {
	        document.getElementById("userSignUpId").reset();
	        $(".wrapper1").show();
	        
        }

        function forgotPwd()
        {
	        closeLogin();
	        $("#errorMsg3").html("");
	        $("#successMsg3").html("");
	        $(".wrapper3").show();
        }

        function forgotPassword()
        {
	        var emailId = $("#forgotEmailId").val();
	        $("#errorMsg3").html("");
	        $("#successMsg3").html("");
	        if(emailId.trim() != "")
	        {
		        $.post("${pageContext.request.contextPath}/ajax/rest/forgotPassword/" + emailId, function(data)
		        {
			        if(data == 1 || data == "1")
			        {
				        $("#forgotEmailId").val("");
				        $("#successMsg3").html("For Reset Password click on a link send to your Email-id !!!");
			        }
			        else if(data == 2 || data == "2")
			        {
				        $("#errorMsg3").html("Please check your mail-id,This is not exist in our database !!!");
			        }
			        
		        });
	        }
	        else
	        {
		        $("#errorMsg3").html("Please Enter emilId !!!");
		        return false;
	        }
	        
        }

        function reserPassword()
        {
	        $("#errorMsg4").empty();
	        var pwd = $("#passwordId1").val().trim();
	        var confirmPwd = $("#passwordId2").val().trim();
	        var userId = $("#huserId").val();
	        if(pwd == null || pwd == "")
	        {
		        $("#errorMsg4").html("Please enter password !!!");
	        }
	        else if(pwd != confirmPwd)
	        {
		        $("#errorMsg4").html("Password should be match !!!");
	        }
	        else
	        {
		        $.post("${pageContext.request.contextPath}/ajax/rest/resetPassword/" + pwd + "/" + confirmPwd + "/" + userId, function(data)
		        {
			        if(data == true || data === "true")
			        {
				        $("#successMsg4").html("You have successfully reset your password !!!");
			        }
			        else
			        {
				        $("#errorMsg4").html("Password does not match !!!");
			        }
			        
		        });
	        }
        }
	</script>








	<!-- Bootstrap core JavaScript
    ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
	<!-- Just to make our placeholder images work. Don't actually copy the next line! -->
	<script
		src="${pageContext.request.contextPath}/resources/js/holder.min.js"></script>
	<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
	<script
		src="${pageContext.request.contextPath}/resources/js/ie10-viewport-bug-workaround.js"></script>
</body>
</html>
















</body>

</html>
