// JavaScript Document


window.onload = function () {
	var chart = new CanvasJS.Chart("chartContainer",
	{
		title:{
			text: "Your Score",
			fontFamily: "Tahoma"
	
		},
                animationEnabled: true,
		legend: {
			verticalAlign: "bottom",
			horizontalAlign: "center"
		},
		theme: "theme1",
		
		data: [
		{        
			type: "pie",
			indexLabelFontFamily: "Tahoma",       
			indexLabelFontSize:14,

			indexLabelFontWeight: "bold",
			startAngle:0,
			indexLabelFontColor: "#000000",       
			indexLabelLineColor: "darkgrey", 
			indexLabelPlacement: "inside", 
			
			toolTipContent: "{name}: {y} %",
			showInLegend: true,
			indexLabel: "#percent%", 
			dataPoints: [
				{  y: 40, name: "Correct", legendMarkerType: "square"},
				{  y: 35, name: "Unattampted", legendMarkerType: "triangle"},
				{  y: 25, name: "Incorrect", legendMarkerType: "circle"}
				
				
	
				
			]
		}
		]
	});
	chart.render();
	
	
	$("dataPoints").css({fontSize: 20});
}