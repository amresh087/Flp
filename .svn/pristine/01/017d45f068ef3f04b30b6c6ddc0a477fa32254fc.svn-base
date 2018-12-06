
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/ihover.css" />
<div class="panel panel-default" style="margin-top: 1%;">
	<div class="panel-body" style="padding-top:10px;">
		<div class="teacher-mid">
			<!-- Page Heading -->
			<!-- /.row -->
			<h3 style="margin-bottom:10px;">
<img alt="" src="${pageContext.request.contextPath}/resources/images/subje.png">
${grade}-${section } ${subject }
</h3>
			<div class="my-classes">
			
			<div class="row">
  <div class="col-sm-4">
 
    <!-- normal -->
    <div class="ih-item circle effect3 left_to_right"><a href="${pageContext.request.contextPath}/schoolTeacher/gradesubjectdetail.htm?secId=${sectionId}&subId=${subjectId}&grade=${grade}&sec=${section}&sub=${subject}&grdId=${gradeId}">
        <div class="img-c"><img src="${pageContext.request.contextPath}/resources/images/grade.png" alt="img"></div>
        <div class="info">
        
          <h3>${grade}-${section }<br>${subject }</h3>
           <p style="margin:0px">Progress</p>
          <script type="text/javascript">
							$(function() {

							

								$('#diagram-id-2').circleDiagram({
									"percent" : "${subPercentage}%",
									"size" : "60",
									"borderWidth" : "6",
									"bgFill" : "#FFFFFF",
									"frFill" : "#36BDB4",
									"textSize" : "16",
									"textColor" : "#ffffff",
									"lineHeight" :"4.8"
								});
							});
						</script>
						<div id="diagram-id-2" class="diagram"></div>
        </div></a></div>
    <!-- end normal -->
 
  </div>
  <div class="col-sm-4">
 
    <!-- colored -->
    <div class="ih-item circle effect3 left_to_right"><a href="${pageContext.request.contextPath}/schoolTeacher/getManageTocHome.htm?secId=${sectionId}&subId=${subjectId}&grade=${grade}&sec=${section}&sub=${subject}&grdId=${gradeId}"">
        <div class="img-c"><img src="${pageContext.request.contextPath}/resources/images/c-library.png" alt="img"></div>
        <div class="info">
          <h3 style="height:100px;">Content<br> Library</h3><br>
          <img src="${pageContext.request.contextPath}/resources/images/libr2.png" alt="img">
         <!--  <p>Description goes here</p> -->
        </div></a></div>
    <!-- end colored -->
 
  </div>
  
  <div class="col-sm-4">
 
    <!-- colored -->
    <div class="ih-item circle effect3 left_to_right"><a href="${pageContext.request.contextPath}/schoolTeacher/getCreateAssessment.htm?secId=${sectionId}&subId=${subjectId}&grade=${grade}&sec=${section}&sub=${subject}&grdId=${gradeId}">
        <div class="img-c"><img src="${pageContext.request.contextPath}/resources/images/creat-test.png" alt="img"></div>
        <div class="info">
          <h3>Create Test</h3>
          <img src="${pageContext.request.contextPath}/resources/images/crt-ico.png" alt="img">
          <!-- <p>Description goes here</p> -->
        </div></a></div>
    <!-- end colored -->
 
  </div>
  
</div>
<!-- end Left to right-->
				<%-- <div class="col-md-3">
					<a href="${pageContext.request.contextPath}/schoolTeacher/gradesubjectdetail.htm?secId=${sectionId}&subId=${subjectId}&grade=${grade}&sec=${section}&sub=${subject}&grdId=${gradeId}">
						<div class="subjects" style="height: auto;">
							<div class="sub-img">
							<c:choose>
							<c:when test="${not empty subImg}">
								<img
									src="${commonPath}/subjectImage/${subImg}"
									alt="">
							</c:when>
							<c:otherwise>
								<img
									src="${pageContext.request.contextPath}/resources/images/11.png"
									alt="">
									</c:otherwise>
									</c:choose>
							</div>
							<h1 class="sub-heading">${grade}
								${section} <br>${subject}</h1>
								
								<div class="col-md-12">
					<div style="margin-bottom: 10px; text-align:center;">
						<img
							src="${pageContext.request.contextPath}/resources/images/prgrss.png"
							alt=""> <b
							style="margin-top: 15px; font-size: 12px; padding-right: 15px; padding-left: 10px;">Progress</b>
						<script type="text/javascript">
							$(function() {

							

								$('#diagram-id-2').circleDiagram({
									"percent" : "${subPercentage}%",
									"size" : "50",
									"borderWidth" : "4",
									"bgFill" : "#FFFFFF",
									"frFill" : "#36BDB4",
									"textSize" : "15",
									"textColor" : "#585858"
								});
							});
						</script>
						<div id="diagram-id-2" class="diagram"></div>
					</div>

				</div>

						</div>
					</a>
					
				</div> --%>
				

				<%-- <div class="col-md-9">
					<div class="pull-right" style="margin-top: 40px;">

						<a
							href="${pageContext.request.contextPath}/schoolTeacher/getManageTocHome.htm?secId=${sectionId}&subId=${subjectId}&grade=${grade}&sec=${section}&sub=${subject}&grdId=${gradeId}">
							<img src="${pageContext.request.contextPath}/resources/images/libr.png"
							alt=""> <b
							style="margin-top: 15px; font-size: 12px; padding-right: 5px; padding-left: 10px;">Content
								Library </b></a> <a
							href="${pageContext.request.contextPath}/schoolTeacher/getCreateAssessment.htm?secId=${sectionId}&subId=${subjectId}&grade=${grade}&sec=${section}&sub=${subject}&grdId=${gradeId}"><img
							src="${pageContext.request.contextPath}/resources/images/cont-libry.png"
							alt=""> <b
							style="margin-top: 15px; font-size: 12px; padding-right: 5px; padding-left: 10px;">Create
								Test</b></a>



					</div>

				</div>
 --%>
			</div>

		</div>

	</div>
</div>
<!-- /.panel-body -->