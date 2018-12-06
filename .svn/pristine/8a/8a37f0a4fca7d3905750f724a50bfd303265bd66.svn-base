// JavaScript Document

			jQuery(function($) {
				$('.easy-pie-chart.percentage').each(function(){
					var $box = $(this).closest('.infobox');
					var barColor = $(this).data('color') || (!$box.hasClass('infobox-dark') ? $box.css('color') : 'rgba(255,255,255,0.95)');
					var trackColor = barColor == 'rgba(255,255,255,0.95)' ? 'rgba(255,255,255,0.25)' : '#E2E2E2';
					var size = parseInt($(this).data('size')) ||70;
					$(this).easyPieChart({
						barColor: barColor,
						trackColor: trackColor,
						scaleColor: false,
						lineCap: 'butt',
						lineWidth: parseInt(size/10),
						animate: ace.vars['old_ie'] ? false : 1000,
						height:300,
						size: size
					});
				})
				
				
			$('#upcoming').ace_scroll({
					size: 325,
					mouseWheelLock: true,
					alwaysVisible : true
				});
				
				$('#member-tab').ace_scroll({
					size: 325,
					mouseWheelLock: true,
					alwaysVisible : true
				});
				
				$('#comment-tab').ace_scroll({
					size: 325,
					mouseWheelLock: true,
					alwaysVisible : true
				});
				
				
				
				
				});
		
			$('[data-rel=tooltip]').tooltip();
				$('[data-rel=popover]').popover({html:true});
				/////////////////////////////////////
				$(document).one('ajaxloadstart.page', function(e) {
					$tooltip.remove();
			
			
			})
			
<!--Grade detail-->
$(window).load(function() {
    $("#flexiselDemo1").flexisel({
	 visibleItems:8,
        itemsToScroll: 3,
        animationSpeed: 200,
	    infinite: true,
        navigationTargetSelector: null,}
	);
    
    
});
$('[data-rel=tooltip]').tooltip();
$('[data-rel=popover]').popover({html:true});
$('#score-zone-1').ace_scroll({
					size: '250',
					mouseWheelLock: true,
					alwaysVisible : true
				});
$('#score-zone-2').ace_scroll({
					size: '250',
					mouseWheelLock: true,
					alwaysVisible : true
				});
				
$('#score-zone-3').ace_scroll({
					size: '250',
					mouseWheelLock: true,
					alwaysVisible : true
				});
				
$('#score-zone-4').ace_scroll({
					size: '250',
					mouseWheelLock: true,
					alwaysVisible : true
				});
				
$('#score-zone-5').ace_scroll({
					size: '250',
					mouseWheelLock: true,
					alwaysVisible : true
				});
$('#score-zone-6').ace_scroll({
					size: '250',
					mouseWheelLock: true,
					alwaysVisible : true
				});
				
<!-------------------------------------------------------------->
jQuery(function($) {
				$('.modal.aside').ace_aside();
				
				$('#aside-inside-modal').addClass('aside').ace_aside({container: '#my-modal > .modal-dialog'});
				
				//$('#top-menu').modal('show')
				$('[data-rel=tooltip]').tooltip();
				$('[data-rel=popover]').popover({html:true});
				/////////////////////////////////////
				$(document).one('ajaxloadstart.page', function(e) {
					$tooltip.remove();
				});
				
				$(document).one('ajaxloadstart.page', function(e) {
					//in ajax mode, remove before leaving page
					$('.modal.aside').remove();
					$(window).off('.aside')
					
					
				});
				
				//make content sliders resizable using jQuery UI (you should include jquery ui files)
				//$('#right-menu > .modal-dialog').resizable({handles: "w", grid: [ 20, 0 ], minWidth: 200, maxWidth: 600});
			
						
				});

//jquery accordion
			$(document).ready(function() {
    
        var $button = $("#submit_prog").hide(),
            $cbs = $('input[name="form-field-checkbox"]').click(function() {
                $button.toggle( $cbs.is(":checked") );
            });
    
    });
	
	var global=0;
function bigImg(x) {
	
	if(global==0){
	setmaximg(x);
	
	global++;
	}else if(global>0)
	{
		setminimg(x)
		global--;
	}
}


function setmaximg(x)
{
x.style.height = "auto";
x.style.width = "400px";
}

function setminimg(x)
{
x.style.height = "auto";
x.style.width = "100px";
}																							
///////////////////////////////////////////
$(function() {
    $('#chartselector').change(function(){
        $('.chart').hide();
        $('#' + $(this).val()).show();
    });
});
