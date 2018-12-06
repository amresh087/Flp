<!-- footer panel --> 

<div class="footer-own">
   <div class="container">
   <div class="row">
   <div class="col-md-9 col-sm-6 col-xs-12"><img src="${pageContext.request.contextPath}/resources/images/fpro-icon.jpg"/>Copyright 2016,  All Right Reserved.</div>

   </div>
   </div>
    </div>
  



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

/* BAR CHART*/

/* var bar_data = {
  data: [["Hindi", 100], ["English", 50], ["Math", 4], ["Art & Fun", 13], ["Civics", 17], ["G.K", 9]],
  color: "#e68524",
  opacity:0.9
};
$.plot("#bar-chart", [bar_data], {
  grid: {
    borderWidth: 0,
    borderColor: "#f3f3f3",
    tickColor: "#f3f3f3"
  },
  series: {
    bars: {
      show: true,
      barWidth: 0.8,
      align: "center"
    }
  },
  xaxis: {
    mode: "categories",
    tickLength: 0
  }
}); */
/* END BAR CHART */
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
			
			$(window).load(function(){
				$(".panel-body9").mCustomScrollbar({
					setHeight:270,
					theme:"dark-3"
				});
				});
			
			$(window).load(function(){
				$(".panel-body10").mCustomScrollbar({
					setHeight:285,
					theme:"dark-3"
				});
				});
})(jQuery);
</script> 

<!-- jQuery 2.1.4 -->
<%--     <script src="${pageContext.request.contextPath}/resources/js/student/jQuery-2.1.4.min.js"></script> --%>
    <%-- <script src="${pageContext.request.contextPath}/resources/js/student/jquery.flot.min.js"></script> --%>
    <%-- <script src="${pageContext.request.contextPath}/resources/js/student/jquery.flot.resize.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/student/jquery.flot.categories.min.js"></script> --%>

</body>

</html>
