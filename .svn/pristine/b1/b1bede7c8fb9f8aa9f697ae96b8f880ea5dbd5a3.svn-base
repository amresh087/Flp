<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="container-fluid mrt20 question-con">
<div class="container">
<div class="row">
<div class="col-md-12 col-sm-12 col-xs-12">
<div class="row">
<div class="panel-group" id="accordion">
<c:if test="${not empty chapterList}">
<c:forEach var="c" items="${chapterList}" varStatus="s">

<div class="panel panel-default panel-cont accordion-toggle" data-toggle="collapse" data-parent="#accordion" href="#collapse${s.count }">
    <div class="panel-heading <c:if test="${s.count eq 1 }">panel-head-active</c:if>">
      <h4 class="panel-title"> <b class="mrr10 pull-left"><img src="${pageContext.request.contextPath}/resources/images/arrow.png" class="arrowCls"/></b>  <div class="topic-question-name">${c.name }</div>  <span class="glyphicon glyphicon-plus pull-right mrl10"></span>  <div class="pro-bar-cont pull-right">
          <p class="percentCls">${c.resultPercent}% Completed</p>
          <div class="progress">
<div class="progress-bar" role="progressbar" aria-valuenow="70" aria-valuemin="0" aria-valuemax="100" style="width:${c.resultPercent}%">
           </div>
        </div>
          </div>
         </h4>
  </div>
      
    <div id="collapse${s.count }" class="panel-collapse collapse in">
      <div class="panel-body-new">
      
    <div class="table-responsive">
       <table  border="0" cellspacing="0" cellpadding="0" data-toggle="modal" data-target="#myModal" class="table table-bordered ">
  <tr style="background:#333333; color:#fff;">
                                                       
    <td width="38%" ><strong>Topic</strong></td>
    <td width="10%" align="center"><strong>Total Ques.</strong></td>
    <td width="10%" align="center"><strong>Attempted</strong></td>
    <td width="7%" align="center"><strong>Correct</strong></td>
    <td width="7%" align="center"><strong>Incorrect</strong></td>
     <!-- <td width="7%" align="center"><strong>Skip</strong></td> -->
    <td width="9%" align="center"><strong>Avg. Score</strong></td>
    <td width="7%" align="center"><strong>G- Score</strong></td>
    <td width="10%" align="center"><strong>Complete</strong></td>
  </tr>
 <c:if test="${not empty c.topicSet }">
 <c:forEach var="t" items="${c.topicSet }" varStatus="s1">
 
   <tr>
    <td ><b class="glyphicon glyphicon-chevron-right mrr10"></b>${t.name }${t.percent }</td>
    <td align="center">${t.count }</td>
    <td align="center">${t.rightCount+t.wrongCount }</td>
    <td align="center">${t.rightCount }</td>
    <td align="center">${t.wrongCount }</td>
     <%-- <td align="center">${t.skipCount }</td> --%>
    <td align="center" ><span class="parent-report-ang-score">${t.smartScore }</span></td>
    <td align="center" ><span class="parent-report-g-score">${t.avgSmartScore }</span></td>
    <td align="center"><div id="diagram-id-${s.count }${s1.count }" class="diagram2"></div></td>
    <c:choose>
												<c:when test="${t.percent le 30 }">
												<script type="text/javascript">
												$('#diagram-id-${s.count}${s1.count}').circleDiagram({
													   "percent": "${t.percent}%",
														"size": "50",
														"borderWidth": "4",
														"bgFill": "#e2e2e2",
														"frFill": "red",
														"textSize": "12",
														"textColor": "red"
													});

												</script>
												</c:when>
												<c:when test="${t.percent gt 30 && t.percent le 70}">
												<script type="text/javascript">
												$('#diagram-id-${s.count}${s1.count}').circleDiagram({
													   "percent": "${t.percent}%",
														"size": "50",
														"borderWidth": "4",
														"bgFill": "#e2e2e2",
														"frFill": "orange",
														"textSize": "12",
														"textColor": "orange"
													});

												</script>
												</c:when>
												<c:otherwise>
												<script type="text/javascript">
												$('#diagram-id-${s.count}${s1.count}').circleDiagram({
													   "percent": "${t.percent}%",
														"size": "50",
														"borderWidth": "4",
														"bgFill": "#e2e2e2",
														"frFill": "#289e25",
														"textSize": "12",
														"textColor": "#289e25"
													});

												</script>
												</c:otherwise>
												</c:choose>
  </tr>
 </c:forEach></c:if>

   <!--  <tr>
    <td ><b class="glyphicon glyphicon-chevron-right mrr10"></b>Activity 1- Adjectives</td>
    <td align="center">10</td>
    <td align="center">6</td>
    <td align="center">6</td>
    <td align="center">4</td>
    <td align="center" ><span class="parent-report-ang-score">60</span></td>
    <td align="center" ><span class="parent-report-g-score">02</span></td>
    <td align="center"><div id="diagram-id-1" class="diagram2"></div></td>
  </tr>
    <tr>
    <td ><b class="glyphicon glyphicon-chevron-right mrr10"></b>Activity 1- Adjectives</td>
    <td align="center">10</td>
    <td align="center">6</td>
    <td align="center">6</td>
    <td align="center">4</td>
    <td align="center" ><span class="parent-report-ang-score">60</span></td>
    <td align="center" ><span class="parent-report-g-score">02</span></td>
    <td align="center"><div id="diagram-id-1" class="diagram2"></div></td>
  </tr> -->
</table>
</div>
</div>
    </div>
  </div>


</c:forEach>

</c:if>


 <%-- 
  <div class="panel panel-default panel-cont accordion-toggle"  data-toggle="collapse" data-parent="#accordion" href="#collapse2">
    <div class="panel-heading">
      <h4 class="panel-title"> <b class="mrr10 pull-left"><img src="${pageContext.request.contextPath}/resources/images/arrow.png" class="arrowCls"/></b>  <div class="topic-question-name">Opposites of Adjectives Activity</div>  <span class="glyphicon glyphicon-plus pull-right mrl10"></span>  <div class="pro-bar-cont pull-right"> <p class="percentCls">60% Completed</p>
          <div class="progress">
          <div class="progress-bar" role="progressbar" aria-valuenow="70"
          aria-valuemin="0" aria-valuemax="100" style="width:60%;">
           
          </div>
        </div>
          </div>
         </h4>
      
    </div>
    <div id="collapse2" class="panel-collapse collapse">
      <div class="panel-body-new">
      
    <div class="table-responsive">
       <table  border="0" cellspacing="0" cellpadding="0" data-toggle="modal" data-target="#myModal" class="table table-bordered ">
  <tr style="background:#333333; color:#fff;">
                                                       
    <td width="40%" ><strong>Topic</strong></td>
    <td width="10%" align="center"><strong>Total Ques.</strong></td>
    <td width="10%" align="center"><strong>Attempted</strong></td>
    <td width="7%" align="center"><strong>Correct</strong></td>
    <td width="7%" align="center"><strong>Incorrect</strong></td>
    <td width="9%" align="center"><strong>Avg. Score</strong></td>
    <td width="7%" align="center"><strong>G- Score</strong></td>
    <td width="10%" align="center"><strong>Complete</strong></td>
  </tr>
 
  <tr>
    <td ><b class="glyphicon glyphicon-chevron-right mrr10"></b>Activity 1- Adjectives</td>
    <td align="center">10</td>
    <td align="center">6</td>
    <td align="center">6</td>
    <td align="center">4</td>
    <td align="center" ><span class="parent-report-ang-score">60</span></td>
    <td align="center" ><span class="parent-report-g-score">02</span></td>
    <td align="center"><div id="diagram-id-1" class="diagram2"></div></td>
  </tr>
    <tr>
    <td ><b class="glyphicon glyphicon-chevron-right mrr10"></b>Activity 1- Adjectives</td>
    <td align="center">10</td>
    <td align="center">6</td>
    <td align="center">6</td>
    <td align="center">4</td>
    <td align="center" ><span class="parent-report-ang-score">60</span></td>
    <td align="center" ><span class="parent-report-g-score">02</span></td>
    <td align="center"><div id="diagram-id-1" class="diagram2"></div></td>
  </tr>
    <tr>
    <td ><b class="glyphicon glyphicon-chevron-right mrr10"></b>Activity 1- Adjectives</td>
    <td align="center">10</td>
    <td align="center">6</td>
    <td align="center">6</td>
    <td align="center">4</td>
    <td align="center" ><span class="parent-report-ang-score">60</span></td>
    <td align="center" ><span class="parent-report-g-score">02</span></td>
    <td align="center"><div id="diagram-id-1" class="diagram2"></div></td>
  </tr>
</table>
</div>
</div>
    </div>
  </div>


  <div class="panel panel-default panel-cont accordion-toggle"  data-toggle="collapse" data-parent="#accordion" href="#collapse3">
    <div class="panel-heading">
      <h4 class="panel-title">
      <b class="mrr10 pull-left"><img src="${pageContext.request.contextPath}/resources/images/arrow.png" class="arrowCls"/></b> 
      <div class="topic-question-name"> 
Adjective - Use and Activities
</div>  <span class="glyphicon glyphicon-plus pull-right mrl10"></span>  <div class="pro-bar-cont pull-right"> <p class="percentCls">30% Completed</p>
          <div class="progress">
          <div class="progress-bar" role="progressbar" aria-valuenow="70"
          aria-valuemin="0" aria-valuemax="100" style="width:30%">
           
          </div>
        </div>
          </div>
          
         </h4>
      
    </div>
    <div id="collapse3" class="panel-collapse collapse">
      <div class="panel-body-new">
      
    <div class="table-responsive">
       <table  border="0" cellspacing="0" cellpadding="0" data-toggle="modal" data-target="#myModal" class="table table-bordered ">
  <tr style="background:#333333; color:#fff;">
                                                       
    <td width="40%" ><strong>Topic</strong></td>
    <td width="10%" align="center"><strong>Total Ques.</strong></td>
    <td width="10%" align="center"><strong>Attempted</strong></td>
    <td width="7%" align="center"><strong>Correct</strong></td>
    <td width="7%" align="center"><strong>Incorrect</strong></td>
    <td width="9%" align="center"><strong>Avg. Score</strong></td>
    <td width="7%" align="center"><strong>G- Score</strong></td>
    <td width="10%" align="center"><strong>Complete</strong></td>
  </tr>
 
  <tr>
    <td ><b class="glyphicon glyphicon-chevron-right mrr10"></b>Activity 1- Adjectives</td>
    <td align="center">10</td>
    <td align="center">6</td>
    <td align="center">6</td>
    <td align="center">4</td>
    <td align="center" ><span class="parent-report-ang-score">60</span></td>
    <td align="center" ><span class="parent-report-g-score">02</span></td>
    <td align="center"><div id="diagram-id-1" class="diagram2"></div></td>
  </tr>
    <tr>
    <td ><b class="glyphicon glyphicon-chevron-right mrr10"></b>Activity 1- Adjectives</td>
    <td align="center">10</td>
    <td align="center">6</td>
    <td align="center">6</td>
    <td align="center">4</td>
    <td align="center" ><span class="parent-report-ang-score">60</span></td>
    <td align="center" ><span class="parent-report-g-score">02</span></td>
    <td align="center"><div id="diagram-id-1" class="diagram2"></div></td>
  </tr>
    <tr>
    <td ><b class="glyphicon glyphicon-chevron-right mrr10"></b>Activity 1- Adjectives</td>
    <td align="center">10</td>
    <td align="center">6</td>
    <td align="center">6</td>
    <td align="center">4</td>
    <td align="center" ><span class="parent-report-ang-score">60</span></td>
    <td align="center" ><span class="parent-report-g-score">02</span></td>
    <td align="center"><div id="diagram-id-1" class="diagram2"></div></td>
  </tr>
</table>
</div>
</div>
    </div>
  </div>


  <div class="panel panel-default panel-cont accordion-toggle" data-toggle="collapse" data-parent="#accordion" href="#collapse4">
    <div class="panel-heading" >
      <h4 class="panel-title"> <b class="mrr10 pull-left"><img src="${pageContext.request.contextPath}/resources/images/arrow.png" class="arrowCls"/></b>  <div class="topic-question-name">Comparatives</div>  <span class="glyphicon glyphicon-plus pull-right mrl10"></span>  <div class="pro-bar-cont pull-right"> <p class="percentCls">70% Completed</p>
          <div class="progress">
          <div class="progress-bar" role="progressbar" aria-valuenow="70"
          aria-valuemin="0" aria-valuemax="100" style="width:70%">
           
          </div>
        </div>
          </div>
         </h4>
      
    </div>
    <div id="collapse4" class="panel-collapse collapse">
      <div class="panel-body-new">
      
    <div class="table-responsive">
       <table  border="0" cellspacing="0" cellpadding="0" data-toggle="modal" data-target="#myModal" class="table table-bordered ">
  <tr style="background:#333333; color:#fff;">
                                                       
    <td width="40%" ><strong>Topic</strong></td>
    <td width="10%" align="center"><strong>Total Ques.</strong></td>
    <td width="10%" align="center"><strong>Attempted</strong></td>
    <td width="7%" align="center"><strong>Correct</strong></td>
    <td width="7%" align="center"><strong>Incorrect</strong></td>
    <td width="9%" align="center"><strong>Avg. Score</strong></td>
    <td width="7%" align="center"><strong>G- Score</strong></td>
    <td width="10%" align="center"><strong>Complete</strong></td>
  </tr>
 
  <tr>
    <td ><b class="glyphicon glyphicon-chevron-right mrr10"></b>Activity 1- Adjectives</td>
    <td align="center">10</td>
    <td align="center">6</td>
    <td align="center">6</td>
    <td align="center">4</td>
    <td align="center" ><span class="parent-report-ang-score">60</span></td>
    <td align="center" ><span class="parent-report-g-score">02</span></td>
    <td align="center"><div id="diagram-id-1" class="diagram2"></div></td>
  </tr>
    <tr>
    <td ><b class="glyphicon glyphicon-chevron-right mrr10"></b>Activity 1- Adjectives</td>
    <td align="center">10</td>
    <td align="center">6</td>
    <td align="center">6</td>
    <td align="center">4</td>
    <td align="center" ><span class="parent-report-ang-score">60</span></td>
    <td align="center" ><span class="parent-report-g-score">02</span></td>
    <td align="center"><div id="diagram-id-1" class="diagram2"></div></td>
  </tr>
    <tr>
    <td ><b class="glyphicon glyphicon-chevron-right mrr10"></b>Activity 1- Adjectives</td>
    <td align="center">10</td>
    <td align="center">6</td>
    <td align="center">6</td>
    <td align="center">4</td>
    <td align="center" ><span class="parent-report-ang-score">60</span></td>
    <td align="center" ><span class="parent-report-g-score">02</span></td>
    <td align="center"><div id="diagram-id-1" class="diagram2"></div></td>
  </tr>
</table>
</div>
</div>
    </div>
  </div>
  
  <div class="panel panel-default panel-cont accordion-toggle" data-toggle="collapse" data-parent="#accordion" href="#collapse5">
    <div class="panel-heading">
      <h4 class="panel-title"> <b class="mrr10 pull-left"><img src="${pageContext.request.contextPath}/resources/images/arrow.png" class="arrowCls"/></b>  <div class="topic-question-name">Describing Words</div>  <span class="glyphicon glyphicon-plus pull-right mrl10"></span>  <div class="pro-bar-cont pull-right"> <p class="percentCls">50% Completed</p>
          <div class="progress">
          <div class="progress-bar" role="progressbar" aria-valuenow="70"
          aria-valuemin="0" aria-valuemax="100" style="width:50%">
           
          </div>
        </div>
          </div>
         </h4>
      
    </div>
    <div id="collapse5" class="panel-collapse collapse">
      <div class="panel-body-new">
      
    <div class="table-responsive">
       <table  border="0" cellspacing="0" cellpadding="0" data-toggle="modal" data-target="#myModal" class="table table-bordered ">
  <tr style="background:#333333; color:#fff;">
                                                       
    <td width="40%" ><strong>Topic</strong></td>
    <td width="10%" align="center"><strong>Total Ques.</strong></td>
    <td width="10%" align="center"><strong>Attempted</strong></td>
    <td width="7%" align="center"><strong>Correct</strong></td>
    <td width="7%" align="center"><strong>Incorrect</strong></td>
    <td width="9%" align="center"><strong>Avg. Score</strong></td>
    <td width="7%" align="center"><strong>G- Score</strong></td>
    <td width="10%" align="center"><strong>Complete</strong></td>
  </tr>
 
  <tr>
    <td ><b class="glyphicon glyphicon-chevron-right mrr10"></b>Activity 1- Adjectives</td>
    <td align="center">10</td>
    <td align="center">6</td>
    <td align="center">6</td>
    <td align="center">4</td>
    <td align="center" ><span class="parent-report-ang-score">60</span></td>
    <td align="center" ><span class="parent-report-g-score">02</span></td>
    <td align="center"><div id="diagram-id-1" class="diagram2"></div></td>
  </tr>
    <tr>
    <td ><b class="glyphicon glyphicon-chevron-right mrr10"></b>Activity 1- Adjectives</td>
    <td align="center">10</td>
    <td align="center">6</td>
    <td align="center">6</td>
    <td align="center">4</td>
    <td align="center" ><span class="parent-report-ang-score">60</span></td>
    <td align="center" ><span class="parent-report-g-score">02</span></td>
    <td align="center"><div id="diagram-id-1" class="diagram2"></div></td>
  </tr>
    <tr>
    <td ><b class="glyphicon glyphicon-chevron-right mrr10"></b>Activity 1- Adjectives</td>
    <td align="center">10</td>
    <td align="center">6</td>
    <td align="center">6</td>
    <td align="center">4</td>
    <td align="center" ><span class="parent-report-ang-score">60</span></td>
    <td align="center" ><span class="parent-report-g-score">02</span></td>
    <td align="center"><div id="diagram-id-1" class="diagram2"></div></td>
  </tr>
</table>
</div>
</div>
    </div>
  </div>
  
  <div class="panel panel-default panel-cont accordion-toggle" data-toggle="collapse" data-parent="#accordion" href="#collapse6">
    <div class="panel-heading">
      <h4 class="panel-title">
       <b class="mrr10 pull-left"><img src="${pageContext.request.contextPath}/resources/images/arrow.png" class="arrowCls"/></b>  
       <div class="topic-question-name">Worksheet</div>  <span class="glyphicon glyphicon-plus pull-right mrl10"></span>  <div class="pro-bar-cont pull-right"> <p class="percentCls">60% Completed</p>
          <div class="progress">
          <div class="progress-bar" role="progressbar" aria-valuenow="70"
          aria-valuemin="0" aria-valuemax="100" style="width:60%">
           
          </div>
        </div>
          </div>
         </h4>
      
    </div>
    <div id="collapse6" class="panel-collapse collapse">
      <div class="panel-body-new">
      
    <div class="table-responsive">
       <table  border="0" cellspacing="0" cellpadding="0" data-toggle="modal" data-target="#myModal" class="table table-bordered ">
  <tr style="background:#333333; color:#fff;">
                                                       
    <td width="38%" ><strong>Topic</strong></td>
    <td width="10%" align="center"><strong>Total Ques.</strong></td>
    <td width="10%" align="center"><strong>Attempted</strong></td>
    <td width="7%" align="center"><strong>Correct</strong></td>
    <td width="7%" align="center"><strong>Incorrect</strong></td>
    <td width="9%" align="center"><strong>Avg. Score</strong></td>
    <td width="7%" align="center"><strong>G- Score</strong></td>
    <td width="10%" align="center"><strong>Complete</strong></td>
  </tr>
 
  <tr>
    <td ><b class="glyphicon glyphicon-chevron-right mrr10"></b>Activity 1- Adjectives</td>
    <td align="center">10</td>
    <td align="center">6</td>
    <td align="center">6</td>
    <td align="center">4</td>
    <td align="center" ><span class="parent-report-ang-score">60</span></td>
    <td align="center" ><span class="parent-report-g-score">02</span></td>
    <td align="center"><div id="diagram-id-1" class="diagram2"></div></td>
  </tr>
    <tr>
    <td ><b class="glyphicon glyphicon-chevron-right mrr10"></b>Activity 1- Adjectives</td>
    <td align="center">10</td>
    <td align="center">6</td>
    <td align="center">6</td>
    <td align="center">4</td>
    <td align="center" ><span class="parent-report-ang-score">60</span></td>
    <td align="center" ><span class="parent-report-g-score">02</span></td>
    <td align="center"><div id="diagram-id-1" class="diagram2"></div></td>
  </tr>
    <tr>
    <td ><b class="glyphicon glyphicon-chevron-right mrr10"></b>Activity 1- Adjectives</td>
    <td align="center">10</td>
    <td align="center">6</td>
    <td align="center">6</td>
    <td align="center">4</td>
    <td align="center" ><span class="parent-report-ang-score">60</span></td>
    <td align="center" ><span class="parent-report-g-score">02</span></td>
    <td align="center"><div id="diagram-id-1" class="diagram2"></div></td>
  </tr>
</table>
</div>
</div>
    </div>
  </div> --%>
</div>
</div>
</div>



</div>
</div>
</div>