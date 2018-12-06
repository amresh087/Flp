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
	 
	<script>var chatdata = new Array();</script>
	<c:forEach var="chapters" items="${chapterList}" >
	  <script>chatdata.push({name: '${chapters.name}',y: parseFloat('${chapters.resultPercent}')}); </script>   
	</c:forEach>


 
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
  
  <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/student/newcss/bootstrap.min.css">
  
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.5.0/css/font-awesome.min.css">

  
  <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/student/newcss/AdminLTE.min.css">
  
  <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/student/newcss/_all-skins.min.css">
 
 

  <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/student/newcss/daterangepicker.css">

  <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/student/newcss/datepicker3.css">

  <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/student/newcss/daterangepicker.css">

  <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/student/newcss/bootstrap3-wysihtml5.min.css">
  <script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/highcharts.js"></script>

</head>
<body class="skin-blue sidebar-mini wysihtml5-supported fixed sidebar-collapse">


   

        
<div class="wrapper">

  <header class="main-header">
 <a href="#" class="logo">
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
 <span class="hidden-xs pull-left" style="margin-right:10px">${sessionScope.user.name}(
 ${sessionScope.user.gradeName })</span>

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
        <li class="treeview">
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
		
		
		
			
			
			
			<span>${subject.name}</span>
			</a></li>
			</c:forEach>
			</c:if>

		
          <li class="active treeview">
       
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


  <div class="content-wrapper">
   
    <section class="content-header">
      <h1>
       Analytics
        <small>Report</small>
      </h1>
     
    </section>

   
    <section class="content">
   
     
   
      <div class="row">
      <ul class="timeline margin-bottom">
      <li>
      <div class="timeline-item sub_headingss">
               
              <div class="form-group"  style="float: right;margin-top: 2px;">
              <div class="input-group">
                  <div class="input-group-addon">
                    <i class="fa fa-calendar"></i>
                  </div>
                  <input class="form-control pull-right" id="config-demo" type="text">
                </div>
              
              </div>
                <h3 class="timeline-header no-border"><a href="#"><i class="fa fa-line-chart"></i>  Learning Analytics</a> <i class="fa fa-angle-double-right" aria-hidden="true"></i> <c:forEach var="sub" items="${sessionScope.user.subjects }" varStatus="status">
										<c:if test="${subjectId eq sub.id}">
										${sub.name }
										</c:if>
										</c:forEach> </h3>
              </div>
      </li>
      </ul>
      <div class="col-lg-12">
        <div class="col-md-3 col-sm-6 col-xs-12">
          <div class="info-box bg-yellow">
            <span class="info-box-icon"><i class="fa fa-book"></i></span>

            <div class="info-box-content">
              <span class="info-box-text">Chapter Mastred</span>
              <span class="info-box-number">${chapterMaster}</span>

              <div class="progress">
                <div class="progress-bar" style="width: ${chapterProgressPercent}%"></div>
              </div>
                  <span class="progress-description">
                    ${chapterProgressPercent}% Increase 
                  </span>
            </div>
            <!-- /.info-box-content -->
          </div>
          <!-- /.info-box -->
        </div>
        <!-- ./col -->
        <div class="col-md-3 col-sm-6 col-xs-12">
          <div class="info-box bg-yellow">
            <span class="info-box-icon"><i class="fa fa-list"></i></span>

            <div class="info-box-content">
              <span class="info-box-text">Topic Mastred</span>
              <span class="info-box-number">${topicMaster}</span>

              <div class="progress">
                <div class="progress-bar" style="width: ${topicProgressPerccent}%"></div>
              </div>
                  <span class="progress-description">
                    ${topicProgressPerccent}% Increase 
                  </span>
            </div>
           
          </div>
         
        </div>
     
         <div class="col-md-3 col-sm-6 col-xs-12">
          <div class="info-box bg-yellow">
            <span class="info-box-icon"><i class="fa fa-question"></i></span>

            <div class="info-box-content">
              <span class="info-box-text">Question Answered</span>
              <span class="info-box-number">${totalCorrectCount}</span>

              <div class="progress">
                <div class="progress-bar" style="width: ${totalQuestionPercent}%"></div>
              </div>
                  <span class="progress-description">
                    ${totalQuestionPercent}% Increase
                  </span>
            </div>
           
          </div>
         
        </div>
       
        <div class="col-md-3 col-sm-6 col-xs-12">
          <div class="info-box bg-yellow">
            <span class="info-box-icon"><i class="fa fa-clock-o"></i></span>

            <div class="info-box-content">
              <span class="info-box-text">Total Time Spent</span>
              <span class="info-box-number">${time} (HH:MM:SS) </span>

              
            </div>
          
          </div>
          
        </div>
      </div>
      </div>
      
      
      
      
      
      
      <div class="row">
      <div class="col-lg-12">
    <div class="box box-info sub_headingss2">
            <div class="box-header with-border">
              

              <div class="box-tools pull-right">
  
                <button type="button" class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i>
                </button>
                <button type="button" class="btn btn-box-tool" data-widget="remove"><i class="fa fa-times"></i></button>
              </div>
            </div>
            <div class="box-body">
               <div id="chapterlr" style="min-width: 310px; height: 400px; margin: 0 auto"></div>
            </div>
            <!-- /.box-body -->
          </div>
          
          
         
          
          
      </div>
      </div>
        
        
      
      <div class="row">
      <div class="col-lg-12">
      <div class="box  sub_headingss2">
      
            <div class="box-header with-border">
              <div class="box-title"> <div class="form-group col-md-6 ">
                                                        <!-- <select class="form-control">
                                                            <option>Select Chapter</option>
                                                             <option selected="Separation of Substances">Separation of Substances</option>
                                                            <option value="0">Light, Shadows and Reflections</option>
                                                            <option value="1">Electricity and Circuits</option>
                                                            <option value="2">The Living Organisms And Their Surroundings</option>
                                                        </select> -->
                                                        
                                                        
              <select id="chapterId" class="form-control" onchange="setDropdownList('#topicId', $(this).val(), '${pageContext.request.contextPath}/ajax/rest/getTopics', 'Select Topic', false)" >
				<option value="">Select Chapter</option>
				<c:forEach var="chapters" items="${chapterList}">
					<option value="${chapters.id}" >${chapters.name}</option>
				</c:forEach>
			</select>
                                                     <!-- // ----------------------------------------------------------------------------->   
                                                        
                                                        
                                                        </div>
                                                        
                           <div class="form-group col-md-6">
                           
                           
                           <select id="topicId" class="form-control" onchange="showGraph($(this))">
				<option value="">Select Topic</option>
			</select>
                                                        <!-- <select class="form-control">
                                                            <option>Select Topic</option>
                                                            <option value="0">Mixture</option>
                                                            <option value="1" selected="Separations of Solids with Solids">Separations of Solids with Solids</option>
                                                            <option value="2">Separations of Liquids with Liquids</option>
                                                            <option value="2">Separations of Solids with Liquids</option>
                                                        </select> --></div>  </div>

              <div class="box-tools pull-right">
           
                <button type="button" class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i>
                </button>
                <button type="button" class="btn btn-box-tool" data-widget="remove"><i class="fa fa-times"></i></button>
              </div>
            </div>
            <div class="box-body">
            <h3 class="text-center"> Practice By Month</h3>
            
            <div class="col-lg-12 data_headingss">
              <!-- <div class="col-lg-7">Separations of Liquids with Liquids</div> -->
           <div class="col-lg-3">
         <!--   <small class="label pull-left" style="color:#1c2427; font-size:14px; font-weight:normal;">
<i class="fa fa-calendar" aria-hidden="true"></i>
Last Studied 2017/4/28
</small> -->
</div>
 <div class="col-lg-2">
<!-- <span class="pull-right" style="color:#1c2427; font-size:14px; font-weight:normal;">
<i class="fa fa-trophy" aria-hidden="true"></i>

Lx-12
</span> --></div>
            </div>
           <div class="col-lg-12">
           
         
            
             <div class="chart" id="questioncover" style="width: 99%; height: auto; margin: 0 auto"></div>
            
           
           
          <!--  <div class="box-body table-responsive no-padding" id="tablereport_data">
              <table class="table table-hover">
                <tr>
                  <th>Question Type</th>
                  <th>Correct</th>
                  <th>Incorrect</th>
                  <th>Correct 2nd Time</th>
                  <th>Skip</th>
                </tr>
                <tr>
                  <td>Easy</td>
                  <td>5</td>
                  <td>4</td>
                  <td>5</td>
                  <td>3</td>
                </tr>
                <tr>
                  <td>Medium</td>
                  <td>5</td>
                  <td>4</td>
                  <td>5</td>
                  <td>3</td>
                </tr>
                <tr>
                  <td>Difficult</td>
                  <td>5</td>
                  <td>4</td>
                  <td>5</td>
                  <td>3</td>
                </tr>
               
              </table>
            </div> -->
           
           
           
           
           
          
              
                
           </div>
             
                 
                
            </div>
            <!-- /.box-body -->
          </div>
      </div>
      
      
      </div>
      
      
      
       
      
      </div>
     

    </section>
    
     
  
   
  </div>
  <!-- /.content-wrapper -->
  <footer class="main-footer">
   
    <strong>Copyright &copy;2017 <a href="#">Iperformkonnect</a>.</strong> All rights
    reserved.
  </footer>

  
  
</div>

<script>
  $.widget.bridge('uibutton', $.ui.button);
</script>

<script src="js/bootstrap.min.js"></script>


<script src="js/jquery.sparkline.min.js"></script>



 <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/student/newjs/jquery-2.1.3.min.js"></script>
  <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/student/newjs/bootstrap.min.js"></script>
  <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/student/newjs/moment.min.js"></script>
  <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/student/newjs/daterangepicker.js"></script>

<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.11.2/moment.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/student/newjs/daterangepicker.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/common.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/student/newjs/bootstrap-datepicker.js"></script>

<script src="${pageContext.request.contextPath}/resources/js/student/newjs/bootstrap3-wysihtml5.all.min.js"></script>

<script src="${pageContext.request.contextPath}/resources/js/student/newjs/jquery.slimscroll.min.js"></script>


<script src="${pageContext.request.contextPath}/resources/js/student/newjs/app.min.js"></script>




<script src="${pageContext.request.contextPath}/resources/js/student/newjs/analytics.js"></script>
<script type="text/javascript">




function showGraph(refObj)
{
	
	
	
	
		var urlforGraph='${pageContext.request.contextPath}/ajax/rest/getTopicGraph/'+refObj.val()+'.htm';
		       $.ajax({
		       	type: 'POST',
		       	url:  urlforGraph,
		       success: function(result) {
		       	
		       	//alert(result);
		         
		         var dataj = JSON.parse(result);
		         
		        
		         
		        
		         var jan_ans=dataj[0].coorect;
		         var feb_ans=dataj[1].coorect;
		         var mar_ans=dataj[2].coorect;
		         var apr_ans=dataj[3].coorect;
		         var may_ans=dataj[4].coorect;
		         var jun_ans=dataj[5].coorect;
		         var jul_ans=dataj[6].coorect;
		         var agu_ans=dataj[7].coorect;
		         var sept_ans=dataj[8].coorect;
		         var oct_ans=dataj[9].coorect;
		         var nov_ans=dataj[10].coorect;
		         var dec_ans=dataj[11].coorect;
		         
		         
		         
		         
		         
		       
		         
		         
		         
		         
		         
		         

		         $('#config-demo').daterangepicker({
		             "ranges": {
		             "Today": [
		                 moment("2015-10-02T11:06:15.319Z"),
		                 moment("2015-10-02T11:06:15.319Z")
		             ],
		             "Yesterday": [
		                 moment("2015-10-01T11:06:15.319Z"),
		                 moment("2015-10-01T11:06:15.319Z")
		             ],
		             "Last 7 Days": [
		                 moment("2015-09-26T11:06:15.319Z"),
		                 moment("2015-10-02T11:06:15.319Z")
		             ],
		             "Last 30 Days": [
		                 moment("2015-09-03T11:06:15.319Z"),
		                 moment("2015-10-02T11:06:15.319Z")
		             ],
		             "This Month": [
		                 moment("2015-09-30T19:00:00.000Z"),
		                 moment("2015-10-31T18:59:59.999Z")
		             ],
		             "Last Month": [
		                 moment("2015-08-31T19:00:00.000Z"),
		                 moment("2015-09-30T18:59:59.999Z")
		             ]
		         },
		         "locale": {
		             "format": "MM/DD/YYYY",
		             "separator": " - ",
		             "applyLabel": "Apply",
		             "cancelLabel": "Cancel",
		             "fromLabel": "From",
		             "toLabel": "To",
		             "customRangeLabel": "Custom",
		             "daysOfWeek": [
		                 "Su",
		                 "Mo",
		                 "Tu",
		                 "We",
		                 "Th",
		                 "Fr",
		                 "Sa"
		             ],
		             "monthNames": [
		                 "January",
		                 "February",
		                 "March",
		                 "April",
		                 "May",
		                 "June",
		                 "July",
		                 "August",
		                 "September",
		                 "October",
		                 "November",
		                 "December"
		             ],
		             "firstDay": 1
		         },
		         "linkedCalendars": false,
		         "startDate": "09/26/2015",
		         "endDate": "10/02/2015",
		         "opens": "center"
		         }, function(start, end, label) {
		             console.log("New date range selected: " + start.format('MM-DD-YYYY') + " to " + end.format('MM-DD-YYYY') + " (predefined range: " + label + ")");
		         });



		         Highcharts.chart('questioncover', {
		             chart: {
		                 type: 'column'
		             },
		             title: {
		               text: ' '
		             },
		            
		             xAxis: {
		                 type: 'category',
		                 labels: {
		                     rotation: -45,
		                     style: {
		                         fontSize: '13px',
		                         fontFamily: 'Verdana, sans-serif'
		                     }
		                 }
		             },
		             yAxis: {
		                 min: 0,
		                 title: {
		                     text: 'Questions'
		                 }
		             },
		             legend: {
		                 enabled: false
		             },
		             tooltip: {
		                 pointFormat: 'Question Answered'
		             },
		             series: [{
		                 name: 'Population',
		                 data: [
		                     ['January', jan_ans],
		                     ['February', feb_ans],
		                     ['March', mar_ans],
		                     ['April', apr_ans],
		                     ['May', may_ans],
		                     ['June', jun_ans],
		                     ['July', jul_ans],
		                     ['August', agu_ans],
		                     ['September', sept_ans],
		                     ['October', oct_ans],
		                     ['November', nov_ans],
		                     ['December', dec_ans]
		                 ],
		                 dataLabels: {
		                     enabled: true,
		                     rotation: -90,
		                     color: '#FFFFFF',
		                     align: 'right',
		                     format: '{point.y:.1f}', // one decimal
		                     y: 10, // 10 pixels down from the top
		                     style: {
		                         fontSize: '13px',
		                         fontFamily: 'Verdana, sans-serif'
		                     }
		                 }
		             }]
		         });

		         
		         
		         
		          
		     
		           
		           
		           
		           
		
		       },
		
		       error: function(jqXHR, textStatus, errorThrown) {
		
		           //alert(jqXHR.status + ' ' + jqXHR.responseText);
		
		       }
		
		   });

}
	
	
	
	
	
	
	
	
</script>


</body>
</html>