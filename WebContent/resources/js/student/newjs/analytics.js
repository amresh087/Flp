
Highcharts.chart('chapterlr', {
    chart: {
        type: 'column'
    },
    title: {
        text: 'Chapter Learning'
    },
    
    xAxis: {
        type: 'category'
    },
    yAxis: {
        title: {
            text: 'Total percent '
        }

    },
    legend: {
        enabled: false
    },
    plotOptions: {
        series: {
            borderWidth: 0,
            dataLabels: {
                enabled: true,
                format: '{point.y:.1f}%'
            }
        }
    },

    tooltip: {
        headerFormat: '<span style="font-size:11px">{series.name}</span><br>',
        pointFormat: '<span style="color:{point.color}">{point.name}</span>: <b>{point.y:.2f}%</b> <br/>'
    },

    series: [{
        name: 'Chapter',
        colorByPoint: true,
        data:chatdata
    }],
     
});


$(document).ready(function () {

    // Build the chart
    Highcharts.chart('lobj1', {
        chart: {
            plotBackgroundColor: null,
            plotBorderWidth: null,
            plotShadow: false,
            type: 'pie'
        },
        
        tooltip: {
            pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
        },
        plotOptions: {
            pie: {
                allowPointSelect: true,
                cursor: 'pointer',
                dataLabels: {
                    enabled: false
                },
                showInLegend: true
            }
        },
        series: [{
            name: 'Marks',
            colorByPoint: true,
            data: [{
                name: 'Assisted Question',
                y: 56
            }, {
                name: 'Aided Question',
                y: 24,
                sliced: true,
                selected: true
            }, {
                name: 'Direct Question',
                y: 20
            }]
        }]
    });
});

$(document).ready(function () {

    // Build the chart
    Highcharts.chart('lobj2', {
        chart: {
            plotBackgroundColor: null,
            plotBorderWidth: null,
            plotShadow: false,
            type: 'pie'
        },
       
        tooltip: {
            pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
        },
        plotOptions: {
            pie: {
                allowPointSelect: true,
                cursor: 'pointer',
                dataLabels: {
                    enabled: false
                },
                showInLegend: true
            }
        },
        series: [{
            name: 'Marks',
            colorByPoint: true,
            data: [{
                name: 'Easy Question',
                y: 56.33
            }, {
                name: 'Intermediate Question',
                y: 24.03,
                sliced: true,
                selected: true
            }, {
                name: 'Difficult Question',
                y: 10.38
            }]
        }]
    });
});


$(document).ready(function () {

    // Build the chart
    Highcharts.chart('lobj3', {
        chart: {
            plotBackgroundColor: null,
            plotBorderWidth: null,
            plotShadow: false,
            type: 'pie'
        },
       
        tooltip: {
            pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
        },
        plotOptions: {
            pie: {
                allowPointSelect: true,
                cursor: 'pointer',
                dataLabels: {
                    enabled: false
                },
                showInLegend: true
            }
        },
        series: [{
            name: 'Marks',
            colorByPoint: true,
            data: [{
                name: 'Knowledge',
                y: 56.33
            }, {
                name: 'Application',
                y: 24.03,
                sliced: true,
                selected: true
            }, {
                name: 'Understanding',
                y: 10.38
            }]
        }]
    });
});


$(document).ready(function () {

    // Build the chart
    Highcharts.chart('lobjw1', {
        chart: {
            plotBackgroundColor: null,
            plotBorderWidth: null,
            plotShadow: false,
            type: 'pie'
        },
       
        tooltip: {
            pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
        },
        plotOptions: {
            pie: {
                allowPointSelect: true,
                cursor: 'pointer',
                dataLabels: {
                    enabled: false
                },
                showInLegend: true
            }
        },
        series: [{
            name: 'Marks',
            colorByPoint: true,
            data: [{
                name: 'Assisted',
                y: 56
            }, {
                name: 'Direct',
                y: 24,
                sliced: true,
                selected: true
            }]
        }]
    });
});

$(document).ready(function () {

    // Build the chart
    Highcharts.chart('lobjw2', {
        chart: {
            plotBackgroundColor: null,
            plotBorderWidth: null,
            plotShadow: false,
            type: 'pie'
        },
       
        tooltip: {
            pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
        },
        plotOptions: {
            pie: {
                allowPointSelect: true,
                cursor: 'pointer',
                dataLabels: {
                    enabled: false
                },
                showInLegend: true
            }
        },
        series: [{
            name: 'Marks',
            colorByPoint: true,
            data: [{
                name: 'Easy',
                y: 15
            }, {
                name: 'Intermediate Question',
                y: 34,
                sliced: true,
                selected: true
            }, {
                name: 'Difficult',
                y: 10
            }]
        }]
    });
});

$(document).ready(function () {

    // Build the chart
    Highcharts.chart('lobjw3', {
        chart: {
            plotBackgroundColor: null,
            plotBorderWidth: null,
            plotShadow: false,
            type: 'pie'
        },
       
        tooltip: {
            pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
        },
        plotOptions: {
            pie: {
                allowPointSelect: true,
                cursor: 'pointer',
                dataLabels: {
                    enabled: false
                },
                showInLegend: true
            }
        },
        series: [{
            name: 'Marks',
            colorByPoint: true,
            data: [{
                name: 'Understanding',
                y: 50
            }, {
                name: 'Application',
                y: 20,
                sliced: true,
                selected: true
            }]
        }]
    });
});