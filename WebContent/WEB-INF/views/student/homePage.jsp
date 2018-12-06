<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%-- <jsp:include page="studentHeader.jsp" />  --%>
<jsp:include page="../student/studentHeader.jsp" />
<link
	href="${pageContext.request.contextPath}/resources/css/student/AdminLTE.min.css"
	rel="stylesheet">














<!-- middle panel -->
<div class="container-fluid">
	<div class="row">

		<div class="stu-cont2">

			<div class="stu-board-con">
				<div class="stu-board-name">Search Any Topic</div>
				<div class="input-group stu-board-search ">
					<input type="text" class="pd15 form-control "
						placeholder="Topic name here..."> <span
						class="input-group-btn">
						<button class="btn stu-board-bt" type="button">Search</button>
					</span>
				</div>
				<div class="stu-board-upgrade-bt">
					<a href="#"><img src="${pageContext.request.contextPath}/resources/images/upgrade-bt.png" alt="" /></a>
				</div>
			</div>
			<div class="clearfix"></div>
		</div>



	</div>
</div>

<div class="container-fluid stu-cont">
	<div class="container">
		<div class="row">

			<div class="col-lg-3 col-md-3 col-sm-3 col-xs-6 ">
				<a href="#">
					<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 stu-zone">
						<div class="row">
							<div class="img-cont">
								<img src="${pageContext.request.contextPath}/resources/images/learn-zone.png" alt="" />
							</div>
							<p>Learning Zone</p>
						</div>
					</div>
				</a>
			</div>

			<div class="col-lg-3 col-md-3 col-sm-3 col-xs-6 ">
				<a href="#">
					<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 stu-zone">
						<div class="row">
							<div class="img-cont2">
								<img src="${pageContext.request.contextPath}/resources/images/test-zone.png" alt="" />
							</div>
							<p>Test Zone</p>
						</div>
					</div>
				</a>
			</div>

			<div class="col-lg-3 col-md-3 col-sm-3 col-xs-6 ">
				<a href="#">
					<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 stu-zone">
						<div class="row">
							<div class="img-cont3">
								<img src="${pageContext.request.contextPath}/resources/images/int-zone.png" alt="" />
							</div>
							<p>Interactive Zone</p>
						</div>
					</div>
				</a>
			</div>

			<div class="col-lg-3 col-md-3 col-sm-3 col-xs-6 ">
				<a href="#">
					<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 stu-zone">
						<div class="row">
							<div class="img-cont4">
								<img src="${pageContext.request.contextPath}/resources/images/study-zone.png" alt="" />
							</div>
							<p>Study Planner</p>
						</div>
					</div>
				</a>
			</div>



			<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12 mrt20 mrb20">
				<div
					class="col-lg-12 col-md-12 col-sm-12 col-xs-12 recent-active-cont">
					<div class="row">
						<h2>Recent Activity</h2>
						<div class="recent-active">
							<div class="recent-active-img">
								<img src="${pageContext.request.contextPath}/resources/images/recent-icon.png" alt="" />
							</div>
							<div class="recent-active-content-cont">
								<div class="recent-active-content">
									<p>1. You are currenty working Math Chapter</p>
									<span>Topic LCM Question</span> <a href="#"><div
											class="btn btn-success btn-xs pull-right">Learn More</div></a>
								</div>
								<div class="recent-active-content">
									<p>2. You are currenty working Math Chapter</p>
									<span>Topic LCM Question</span> <a href="#"><div
											class="btn btn-success btn-xs pull-right">Learn More</div></a>
								</div>

								<div class="recent-active-content">
									<p>3. You are currenty working Math Chapter</p>
									<span>Topic LCM Question</span> <a href="#"><div
											class="btn btn-success btn-xs pull-right">Learn More</div></a>
								</div>

								<div class="recent-active-content">

									<a href="#"><div class=" pull-right">More...</div></a>
								</div>

							</div>

						</div>
					</div>
				</div>
			</div>

			<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12 mrt20 mrb20">
				<div
					class="col-lg-12 col-md-12 col-sm-12 col-xs-12 recent-active-cont">
					<div class="row">
						<h2>Progress Bar</h2>
						<div class="recent-active">
							<div class="recent-active-img">
								<img src="${pageContext.request.contextPath}/resources/images/progress-icon.png" alt="" />
							</div>
							<div class="recent-active-content-cont">
								<div class="recent-active-content">
									<div id="bar-chart" style="height: 192px;"></div>

								</div>

							</div>

						</div>
					</div>
				</div>
			</div>


			<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12 mrt20 mrb20">

				<div
					class="col-lg-12 col-md-12 col-sm-12 col-xs-12 recent-active-cont mrb20">
					<div class="row">
						<h2>Ranking- Points</h2>
						<div class="recent-active">
							<div class="recent-active-img">
								<img src="${pageContext.request.contextPath}/resources/images/ranking-icon.png" alt="" />
							</div>
							<div class="recent-active-content-cont">
								<div class="recent-active-content">
									<p>
										<strong>farhan singh</strong> <span class="pull-right"><strong>Your
												Ranking: 25924</strong></span>
									</p>

								</div>

								<div class="recent-active-content">
									<div class="ranking-point">Topper Ranking : 1 - ( Amit
										Nain)</div>
								</div>
							</div>
						</div>
					</div>
				</div>

				<div
					class="col-lg-12 col-md-12 col-sm-12 col-xs-12 recent-active-cont">
					<div class="row">
						<h2>Favorites</h2>
						<div class="recent-active">
							<div class="recent-active-img">
								<img src="${pageContext.request.contextPath}/resources/images/favorite-icon.png" alt="" />
							</div>
							<div class="recent-active-content-cont">
								<div class="recent-active-content">
									<p>
										1. You are currenty working Math Chapter <a href="#"
											class="btn btn-success btn-xs pull-right">Learn More</a>
									</p>
									<p>
										2. You are currenty working Math Chapter <a href="#"
											class="btn btn-success btn-xs pull-right">Learn More</a>
									</p>

								</div>




								<div class="recent-active-content">

									<a href="#"><div class=" pull-right">More...</div></a>
								</div>

							</div>

						</div>
					</div>
				</div>

			</div>



			<div class="col-lg-3 col-md-3 col-sm-6 col-xs-12 mrt20 mrb20">
				<div
					class="col-lg-12 col-md-12 col-sm-12 col-xs-12 recent-active-cont mrb20">
					<div class="row">
						<h2>Suggested Learning</h2>
						<ul class="list-unstyled suggest-learning">
							<li><b
								class="glyphicon glyphicon-pencil suggest-learning-icon"></b><a
								href="#">English <span>Adjectives Activities and
										worksheets</span></a></li>
							<li><b
								class="glyphicon glyphicon-pencil suggest-learning-icon"></b><a
								href="#">Mathematics<span>Additions Practice paper</span>
							</a></li>
							<li><b
								class="glyphicon glyphicon-pencil suggest-learning-icon"></b><a
								href="#">Science <span>Plants Getting to know plants</span></a>
							</li>
							<li><b
								class="glyphicon glyphicon-pencil suggest-learning-icon"></b><a
								href="#">EVS <span>Neighbourhood People at work</span>
							</a></li>

						</ul>


					</div>
				</div>



			</div>

			<div class="col-lg-3 col-md-3 col-sm-6 col-xs-12 mrt20 mrb20">
				<div
					class="col-lg-12 col-md-12 col-sm-12 col-xs-12 recent-active-cont">
					<div class="row">
						<h2>Unattampted Test</h2>
						<div class="recent-active">
							<div class="recent-active-content-cont2">
								<div class="recent-active-content">
									<p>1. You are currenty working Math Chapter</p>

									<a href="#"><div class="btn btn-success btn-xs ">Know
											More</div></a>
								</div>
								<div class="recent-active-content">
									<p>2. You are currenty working Math Chapter</p>

									<a href="#"><div class="btn btn-success btn-xs ">Know
											More</div></a>
								</div>

								<div class="recent-active-content">
									<p>3. You are currenty working Math Chapter</p>
									<a href="#"><div class="btn btn-success btn-xs t">Know
											More</div></a>
								</div>

								<div class="recent-active-content">

									<a href="#"><div class=" pull-right">More...</div></a>
								</div>

							</div>

						</div>
					</div>
				</div>
			</div>

		</div>
	</div>
</div>

<!-- middle panel end -->


<jsp:include page="studentFooter.jsp" />
