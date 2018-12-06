<!-- footer panel --> 
<script src="${pageContext.request.contextPath}/resources/js/jquery.circle-diagram.js"></script>
<%-- 	<script src="${pageContext.request.contextPath}/resources/js/parent/main.js"></script> --%>
<!-- Start Footer Section -->
	<footer>

		<div class="container">
			<div class="row footer-widgets">
				<!-- Start Contact Widget -->
				<div class="col-md-4 col-xs-12 col-sm-3">
					<div class="footer-widget contact-widget">
						<h4>
							Contact Us
							<%-- <img src="${pageContext.request.contextPath}/resources/images/footer-margo.png"
 class="img-responsive" alt="Footer Logo" /> --%>
						</h4>
						<p>
							Mind Shaper Technologies<br> Plot no. 188, Udyog Vihar Phase
							-4,<br> Gurgaon, Haryana, India 122015
						</p>
					</div>
				</div>
				<!-- .col-md-3 -->
				<!-- End Contact Widget -->
				<div class="col-md-5 col-xs-12 col-sm-3">
					<div class="footer-widget flickr-widget">
						<h4></h4>
						<ul>
							<li style="margin-left:8px;">About Us</li>
							<li>Terms & Conditions</li>
							<li style="border: none;">FAQ</li>
						</ul>
					</div>
				</div>

				<div class="col-md-3 col-xs-12 col-sm-3">
					<div class="footer-widget contact-widget">
						<h4 style="margin-bottom: 0px; padding-left: 20px;">Follow Us</h4>
						<div class="footer-widget social-widget">

							<ul class="social-icons">
								<li style="border: none;"><a class="facebook" href="#"><i
										class="fa fa-facebook-square"></i></a></li>
								<li style="border: none;"><a class="twitter" href="#"><i
										class="fa fa-twitter-square"></i></a></li>
								<li style="border: none;"><a class="google" href="#"><i
										class="fa fa-google-plus-square"></i></a></li>
								<li style="border: none;"><a class="linkdin" href="#"><i
										class="fa fa-linkedin-square"></i> </a></li>

							</ul>
						</div>
						<ul>
			<li><i class="fa fa-mobile"></i> : 0124 - 6942225 <br> <i class="fa fa-envelope-o"></i> : communications@classteacher.com
			</li>
			<li><i class="fa fa-globe"></i> : http://www.iperformkonnect.com</li>
						</ul>
						

					</div>
				</div>
			</div>
			<!-- .row -->
			<!-- Start Copyright -->
			<div class="copyright-section">
				<div class="row">
					<div class="col-md-12 col-sm-6 col-xs-12"
						style="text-align: center;">
						<img
							src="${pageContext.request.contextPath}/resources/images/footer-logo.png">
						Copyright 2016, All Right Reserved.
					</div>
				</div>
				<!-- .row -->
			</div>
			<!-- End Copyright -->
		</div>

	</footer>
	<!-- End Footer Section -->


<script type="text/javascript">
$(document).ready(function() {
$('.navbar-nav > li').on('click',function(){
	$('.navbar-nav > li').removeClass('nav-active');  
	$(this).addClass('nav-active');   
	  });
	  
$('.sub-side-cont li a').on('click',function(){
	$('.sub-side-cont li a').removeClass('sub-side-cont-active');  
	$(this).addClass('sub-side-cont-active');   
});
});

$(document).ready(function() {
$("#flexiselDemo1").flexisel();
});

$('.panel-heading').on('click',function(){
	
	var status=$(this).hasClass('panel-head-active');
	$('.panel-heading').removeClass('panel-head-active');
	$(".panel-title, .percentCls").removeClass('panel-title-a-active'); 
	$(".arrowCls").removeAttr('src').attr('src','${pageContext.request.contextPath}/resources/images/arrow.png');
	if(status==false){
	$(this).addClass('panel-head-active');   
	$(this).children().addClass('panel-title-a-active');  
	$(this).children().children().children().addClass('panel-title-a-active');  
	$(this).children().children().children().removeAttr('src').attr('src','${pageContext.request.contextPath}/resources/images/arrow-active.png');
	}
	
});


$(".panel-body").on("click", function(e){
	e.preventDefault();
	e.stopPropagation();
});

$('.collapse').on('shown.bs.collapse', function(){
$(this).parent().find(".glyphicon-plus").removeClass("glyphicon-plus").addClass("glyphicon-minus");
}).on('hidden.bs.collapse', function(){
$(this).parent().find(".glyphicon-minus").removeClass("glyphicon-minus").addClass("glyphicon-plus");
});

</script>



<script type="text/javascript">
(function($){
			$(window).load(function(){
				$(".panel-body2").mCustomScrollbar({
					setHeight:440,
					theme:"dark-3"
				});
				});
})(jQuery);
</script>
   
</body>

</html>
