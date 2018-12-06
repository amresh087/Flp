<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  
  
    <script>
  

 function getNextQuestion1() {
  
     questioncountflag++;
        if (!flag || flag == false) {
            var ss1 = parseFloat($("#ss").val());
            var mm1 = parseFloat($("#mm").val());
            if (mm1 > 0) {
                ss1 = ss1 + mm1 * 60;
            }
            var hh1 = parseFloat($("#hh").val());
            if (hh1 > 0) {
                ss1 = ss1 + hh1 * 60 * 60;
            }
            
            var diffValue = document.getElementById("diffId").value;
           
             saveQuestion(isAttempted, 0, correctCount, ss1, "" ,diffValue);
            var val = '';
        if (isAttempted == 0) {
            var questionNumber =document.getElementById('questionNumberCount1').value;
          
             var question=document.getElementById('questions').innerHTML;
                questionselected.push(question);
              
              
          
          
            val = '<span data-toggle="tooltip" title="" class="badge bg-yellow quest_cir" data-original-title=""></span>';
          
            var inputVal =document.getElementById('questionNumberCount1').value;
            var updatedVal=parseInt(inputVal)+1;
            document.getElementById('questionNumberCount1').value=updatedVal;
          
          
        }
        $("#questStatusId1").append(val)
        }
        $('#questionDesc').empty();
      
          
         var url = "${pageContext.request.contextPath}/student/getNextQuestion?topicId=${param.topicId}&sId=${param.sId}&subjectName=${param.subjectName}";
          $.ajax({
                 type : "post",
                 url : url
             })
                 .done(function(res) {
                  $('#questionDesc').html(res);
                  var val=document.getElementById('dynamicresource').innerHTML;
                  $('#maindynamicresource').html(val);
                  var val=document.getElementById('hintIdComman').innerHTML;
                  $('#hintId').html(val);
                     }).fail(function() {
                     alert("error");
                 });

         
        $("#ss").val('00');
        $("#mm").val('00');
        $("#hh").val('00');
        clearInterval(timer);
      
    }
 
    function saveQuestion(isAttempted, isCorrect, correctCount, ss1, userValues,diffValue) {
        var url = "${pageContext.request.contextPath}/student/saveQuestion";
        var data = {
            topicId : '<c:out value="${param.topicId}"/>',
            questionId : '<c:out value="${question.id}"/>',
            isAttempted : isAttempted,
            isCorrect : isCorrect,
            correctCount : correctCount,
            timeTaken : ss1,
            locount:'1',
            diffValue : diffValue
        }
        $
                .ajax(

                {
                    type : "post",
                    url : url + "?userValues=" + userValues,
                    data : data

                })
                .done(
                        function(res) {

                            console.log(v++);
                            if (parseInt(cGscore) != parseInt(res)) {
                                correctCount=parseInt(0);
                                cGscore = res;
                                if(parseInt(cGscore)==32){
                                    correctCount=0;
                                }
                            /*     switch (res) {
                                case 20:
                                    location.href = "${pageContext.request.contextPath}/student/getTopicSubmissionMessage.htm?topicId=${param.topicId}&sId=${param.sId}&case=20&subjectName=${param.subjectName}";
                                    break;
                                case 40:
                                    location.href = "${pageContext.request.contextPath}/student/getTopicSubmissionMessage.htm?topicId=${param.topicId}&sId=${param.sId}&case=1&subjectName=${param.subjectName}";
                                    break;

                                case 100:
                                    location.href = "${pageContext.request.contextPath}/student/getTopicSubmissionMessage.htm?topicId=${param.topicId}&sId=${param.sId}&case=2&subjectName=${param.subjectName}";
                                    break;
                                    default:
                                        location.reload();

                                $("#ss").val('00');
                                $("#mm").val('00');
                                $("#hh").val('00');
                                clearInterval(timer);
                                break;
                            } */
                          
                                switch (res) {
                                case 40:
                                   
                                   
                                    localStorage.setItem("gola_green", document.getElementById('questStatusId').innerHTML);
                                    location.reload();
                                    //location.href = "${pageContext.request.contextPath}/student/getTopicSubmissionMessage.htm?topicId=${param.topicId}&sId=${param.sId}&case=40&subjectName=${param.subjectName}";
                                    break;
                                case 100:
                                    location.reload();
                                    //location.href = "${pageContext.request.contextPath}/student/getTopicSubmissionMessage.htm?topicId=${param.topicId}&sId=${param.sId}&case=100&subjectName=${param.subjectName}";
                                    break;

                                case 200:
                                    location.reload();
                                   // location.href = "${pageContext.request.contextPath}/student/getTopicSubmissionMessage.htm?topicId=${param.topicId}&sId=${param.sId}&case=200&subjectName=${param.subjectName}";
                                    break;
                                    default:
                                        //location.reload();

                               /*  $("#ss").val('00');
                                $("#mm").val('00');
                                $("#hh").val('00');
                                clearInterval(timer); */
                                break;
                            }


                        }

                        ss = parseFloat(0);
                    }).fail(function() {

                    alert("error");

                });
    }
</script>
  
  
    <script src="${pageContext.request.contextPath}/resources/js/jquery.js" type="text/javascript"></script>


<!-- for Hint code----------------------------------- -->
<div id='hintIdComman' class="face back" style="display: none;">
  

            <c:choose>
                <c:when test="${question.hinttypeid=='2'}">
                    <p>${question.hintContent}</p>
                </c:when>
              
                <c:when test="${question.hinttypeid=='3'}">
                    <img src="${lmsimagepath}${question.hintContent}"  class="img-responsive" />

                </c:when>
              
            <c:when test="${question.hinttypeid=='4'}">
                     <video  controls class="img-responsive">
                          <source src="${lmsimagepath}${question.hintContent}" type="video/mp4">
                    </video>
                </c:when>
              
                <c:when test="${question.hinttypeid=='5'}">
                     <object id="FlashID"  width="100%" height="100%">
                     <param name="movie" value="${lmsimagepath}${question.hintContent}" />
                     <param name="quality" value="high" />
                     <param name="wmode" value="opaque" />
                     <param name="swfversion" value="6.0.65.0" />
                     <param name="expressinstall" value="Scripts/expressInstall.swf" />
                  
                     <object type="application/x-shockwave-flash" data="${lmsimagepath}${question.hintContent}" width="820" height="550">
                       <param name="quality" value="high" />
                       <param name="wmode" value="opaque" />
                       <param name="swfversion" value="6.0.65.0" />
                       <param name="expressinstall" value="Scripts/expressInstall.swf" />
                       <div>
                         <h4>Content on this page requires a newer version of Adobe Flash Player.</h4>
                         <p><a href="http://www.adobe.com/go/getflashplayer"><img src="http://www.adobe.com/images/shared/download_buttons/get_flash_player.gif" alt="Get Adobe Flash player" width="112" height="33" /></a></p>
                       </div>
                     </object></object>
                  
                </c:when>
              
              
                <c:when test="${question.hinttypeid=='6'}">
                    <iframe id="iframeVideo" width="100%" height="400px" src="${lmsimagepath}${question.hintContent}" >
                    </iframe>
                  
                </c:when>
              
              
                <c:otherwise>
                    <p>No Item Related To This Question..</p>
                </c:otherwise>


            </c:choose>

      
</div>




<!-- Assessment Summary Page -->

 <div id="myNav" class="overlay">
  <a href="javascript:void(0)" class="closebtn" onclick="closeNav()">&times;</a>
 
  <div class="overlay-content slimScrollDiv">
  <div class="col-lg-10 pull-right">
   <div class="col-lg-7" id="fivequestionresult" style="border-bottom:solid 1px #E0E4E9;">
  
 
  
       
                                  
     </div>
   
     </div>
  </div>
 
 
   
   
  </div>
</div> 




<!-- Assessment Summary Page -->

<!-- for Resource code----------------------------------- -->

<div id='dynamicresource' style="display: none;">
    <div class="box box-solid ">
        <div class="box-body">
                
            <c:choose>
                <c:when test="${question.hinttypeid=='2'}">
                    <p>${question.hintContent}</p>
                </c:when>
              
                <c:when test="${question.hinttypeid=='3'}">
                    <img src="${lmsimagepath}${question.hintContent}"  class="img-responsive" />

                </c:when>
              
            <c:when test="${question.hinttypeid=='4'}">
                     <video  controls class="img-responsive">
                          <source src="${lmsimagepath}${question.hintContent}" type="video/mp4">
                    </video>
                </c:when>
              
                <c:when test="${question.hinttypeid=='5'}">
                     <object id="FlashID"  width="100%" height="100%">
                     <param name="movie" value="${lmsimagepath}${question.hintContent}" />
                     <param name="quality" value="high" />
                     <param name="wmode" value="opaque" />
                     <param name="swfversion" value="6.0.65.0" />
                     <param name="expressinstall" value="Scripts/expressInstall.swf" />
                  
                     <object type="application/x-shockwave-flash" data="${lmsimagepath}${question.hintContent}" width="820" height="550">
                       <param name="quality" value="high" />
                       <param name="wmode" value="opaque" />
                       <param name="swfversion" value="6.0.65.0" />
                       <param name="expressinstall" value="Scripts/expressInstall.swf" />
                       <div>
                         <h4>Content on this page requires a newer version of Adobe Flash Player.</h4>
                         <p><a href="http://www.adobe.com/go/getflashplayer"><img src="http://www.adobe.com/images/shared/download_buttons/get_flash_player.gif" alt="Get Adobe Flash player" width="112" height="33" /></a></p>
                       </div>
                     </object></object>
                  
                </c:when>
              
              
                <c:when test="${question.hinttypeid=='6'}">
                    <iframe id="iframeVideo" width="100%" height="400px" src="${lmsimagepath}${question.hintContent}" >
                    </iframe>
                  
                </c:when>
              
              
                <c:otherwise>
                    <p>No Item Related To This Question..</p>
                </c:otherwise>


            </c:choose>

              
              
              
        </div>
    </div>

</div>



<c:choose>
    <c:when test="${not empty question}">
    <div class="questions" id="questions">
  <!--   <input type="hidden" name="country" value="Norway"> -->
    <input  type="hidden"  id="diffId" value='${question.difficultyLevelId}'> 
            <p class="question_description">${question.getDescription()}
            <c:if test="${not empty question.imagePath}">
                <div class="col-md-12">
                    <img src="${lmsimagepath}${question.imagePath}"
                        alt="" />
                </div>
            </c:if>
          
          

      
                 <ul class="list-group"   id="ulcontainer">
                        <c:forEach var="answer" items="${question.answers}"
                            varStatus="status">
                            <c:set var="ic" value="${answer.isCorrect}" />
                            <li class="list-group-item" id="${answer.id}" ic='${ic}'  radioid='radio${answer.id}' > 
                              
                                <input type="radio" id="radio${answer.id}"  ic='${ic}' liid="${answer.id}"  name="answer" style='display:none'>
                                <label for="${answer.id}"> ${answer.getDescription()}</label> <c:if
                                    test="${not empty answer.imagePath}">
                                    <img src="${lmsimagepath}${answer.imagePath}"
                                        alt="" />
                                </c:if>




                          

                            </li>




                        </c:forEach>
                      
                    </ul>
                    
            </div>  
              
              
                    <!-- view explanation -->
                     <div class="box box-success box-solid"  id="explanationid" style="display: none;" >
            <div class="box-header with-border">
              <h3 class="box-title" onclick="show();"><i class="fa fa-eye" aria-hidden="true"></i>View Explanation</h3>

              <div class="box-tools pull-right">
                <button type="button" class="btn btn-box-tool" data-widget="remove"><i class="fa fa-times"></i></button>
              </div>
           
            </div>
         
            <div class="box-body" id="benefits" style="display:none;">
            
            
              <c:choose>
                <c:when test="${question.explanationId=='21'}">
                    <p>${question.explanationContent}</p>
                </c:when>
              <c:when test="${question.explanationId=='22'}">
                    <img src="${lmsimagepath}${question.explanationContent}"  class="img-responsive" />

                </c:when>
              
            <c:when test="${question.explanationId=='23'}">
                     <video  controls class="img-responsive">
                          <source src="${lmsimagepath}${question.explanationContent}" type="video/mp4">
                    </video>
                </c:when>
              
                <c:when test="${question.explanationId=='24'}">
                     <object id="FlashID"  width="100%" height="100%">
                     <param name="movie" value="${lmsimagepath}${question.explanationContent}" />
                     <param name="quality" value="high" />
                     <param name="wmode" value="opaque" />
                     <param name="swfversion" value="6.0.65.0" />
                     <param name="expressinstall" value="Scripts/expressInstall.swf" />
                  
                     <object type="application/x-shockwave-flash" data="${lmsimagepath}${question.explanationContent}" width="820" height="550">
                       <param name="quality" value="high" />
                       <param name="wmode" value="opaque" />
                       <param name="swfversion" value="6.0.65.0" />
                       <param name="expressinstall" value="Scripts/expressInstall.swf" />
                       <div>
                         <h4>Content on this page requires a newer version of Adobe Flash Player.</h4>
                         <p><a href="http://www.adobe.com/go/getflashplayer"><img src="http://www.adobe.com/images/shared/download_buttons/get_flash_player.gif" alt="Get Adobe Flash player" width="112" height="33" /></a></p>
                       </div>
                     </object></object>
                  
                </c:when>
              
              
                <c:when test="${question.explanationId=='25'}">
                    <iframe id="iframeVideo" width="100%" height="400px" src="${lmsimagepath}${question.explanationContent}" >
                    </iframe>
                  
                </c:when>
              
              
                <c:otherwise>
                    <p>No Explanation Related To This Question..</p>
                </c:otherwise>
            
             </c:choose>
            
            
            
            
            
              <div id="upbutton label ">
<a class="btn btn-block btn-warning" onclick="conceal();" style="font-size:16px"><i class="fa fa-thumbs-up" aria-hidden="true"></i> Ok Got It</a></div>
            </div>
         
          </div>       
                  
  
      

      
          
          
    </c:when>
    <c:otherwise>
        <div class="test-ques col-md-12">
            <h3>No question found!!</h3>

        </div>
        <c:set var="flag" value="false" />

    </c:otherwise>
</c:choose>


<script>



var gscore='${score}';
if(gscore<40)
    {
    var v = 0;
    $.ajaxSetup({
        cache : false
    });
    var ss = parseInt(0), mm = parseInt(0), hh = parseInt(0);
    var sec = parseInt(1000);
    var fun = function() {
        if (ss > 0) {
            ss++;
            if (ss > 59) {
                ss = parseInt(0);
                mm++;
                if (mm > 59) {
                    mm = parseInt(0);
                    hh++;
                    if (hh > parseInt(23)) {
                        hh++;
                    }
                }
            }
            $("#ss").val(ss >= 10 ? ss : parseInt(0) + ss.toString());
            $("#mm").val(mm >= 10 ? mm : "0" + mm.toString());
            $("#hh").val(hh >= 10 ? hh : "0" + hh.toString());
        } else {
            ss = parseInt(1);
            $("#ss").val("0" + ss.toString());
        }

    }
    var timer = setInterval(fun, (sec));
    $(document).ready(function() {
      
      
      
      
      /*    if ('${flag}' != 'false')
            $('#genScore').empty().append('${score}'); */
           
            if ('${flag}' != 'false' && incQuestionCount >= 8 && '${locountQ}'==1)
            {  
            $('#genScore').empty().append('${score}');}
            else if ('${flag}' != 'false' && incQuestionCount >=10 && '${locountQ}'==2 || '${locountQ}'==3)
            {  

            $('#genScore').empty().append('${score}');}
        else {
           // clearInterval(timer);
        }

    });

    var isSelect = parseInt(0);
    var isAttempted = parseInt(0);
    var isCorrect = parseInt(0);
    var resp = "";
    /* var flag = false; */
  
    var userValues="";
    var trueclickCount=1;
    var falseclickCount=1;
    var countdemo=1;
    var questioid=null;
    $(".list-group-item").on(
                    "click",
                    function() {
                        if (isSelect == 0 || isSelect == '0') {
                        /*     flag = true; */
                          
                            var radio = $(this).attr('radioid');
                            document.getElementById(radio).checked = true;
                            document.getElementById(radio).attr
                            var val = $("#"+radio).attr('ic');
                          
                          
                          
                          
                          
                            var ulChildren = document.getElementById('ulcontainer').children;              
                            var childrenLength = ulChildren.length;
                        
                            for(var i = 0; i < childrenLength; i++){
                                if(ulChildren[i].nodeName.toLowerCase() == 'li'){
                                    $("#"+ulChildren[i].id).css("background", "#ffffff").css("border", "1px solid #ffffff");
                                  
                                }
                            }
                          
                          
                          
                             if(val == 1|| val == "1")
                            {
                                $(this).css("border", "1px solid #00A65A");
                              
                            }else if(val == 0|| val == "0")
                                {
                                    $(this).css("border", "1px solid #c93e3e");
                                }
                             userValues = $(this).attr('id');
                            var correctOrIncorrect = $(this).attr('ic');
                          
                            if (correctOrIncorrect == 1|| correctOrIncorrect == "1") {
                                isAttempted = 1;
                                isCorrect = 1;
                                correctCount++;
                                trueclickCount++;
                                countdemo++;
                                document.getElementById('explanationid').style.display = "none";
                            } else {
                                isAttempted = 1;
                                isCorrect = 0;
                                falseclickCount++;
                                countdemo++;
                                document.getElementById('explanationid').style.display = "none";
                            }
                          
                            if(question_new_old==1 || question_new_old=="1")
                                {
                              
                                questioid='count_'+incrimentForQuestiion+'_QID_'+'${question.id}';
                                    $("#questStatusId").append('<span data-toggle="tooltip" title="" id="'+questioid+'"  class=""  data-original-title=""></span>');
                                    question_new_old=0 ;
                                }
                          
                            if (isAttempted == 1 && isCorrect == 1)
                            {
                              
                                if( trueclickCount==2 && countdemo==2)
                                {
                                    document.getElementById(questioid).className="badge bg-green quest_cir";
                                    }
                              
                                else
                                {
                                    document.getElementById(questioid).className="badge bg-blue quest_cir";
                                    isCorrect = 2;
                                    }
                              
                                
                              
                              
                            }
                            if (isAttempted == 1 && isCorrect == 0) {
                                document.getElementById(questioid).className="badge bg-red quest_cir";
                              
                              
                            }
                        } else {
                            alert("You have already Select Answer");
                            return false;
                        }

                    })
                  
    }

else if(gscore>=40 && gscore<100)
    {
  
   
if(gscore==40 ){document.getElementById('questStatusId1').innerHTML=localStorage.getItem("gola_green");}

document.getElementById('hintId').style.display = "none";
document.getElementById('face_front').style.display = "block";
   
   
   
   
    var v = 0;
    $.ajaxSetup({
        cache : false
    });
    var ss = parseInt(0), mm = parseInt(0), hh = parseInt(0);
    var sec = parseInt(1000);
    var fun = function() {
        if (ss > 0) {
            ss++;
            if (ss > 59) {
                ss = parseInt(0);
                mm++;
                if (mm > 59) {
                    mm = parseInt(0);
                    hh++;
                    if (hh > parseInt(23)) {
                        hh++;
                    }
                }
            }
            $("#ss").val(ss >= 10 ? ss : parseInt(0) + ss.toString());
            $("#mm").val(mm >= 10 ? mm : "0" + mm.toString());
            $("#hh").val(hh >= 10 ? hh : "0" + hh.toString());
        } else {
            ss = parseInt(1);
            $("#ss").val("0" + ss.toString());
        }

    }
    var timer = setInterval(fun, (sec));
    $(document).ready(function() {
      
      
       
       
       
       
      
      
         if ('${flag}' != 'false')
            $('#genScore').empty().append('${score}');
        else {
           // clearInterval(timer);
        }

    });

    var isSelect = parseInt(0);
    var isAttempted = parseInt(0);
    var isCorrect = parseInt(0);
    var resp = "";
    var flag = false;
    $(".list-group-item")
            .on(
                    "click",
                    function() {
                        if (isSelect == 0 || isSelect == '0') {
                            flag = true;
                            var correctOrIncorrect = $(this).attr('ic');
                            var userValues = $(this).attr('id');
                            if (correctOrIncorrect == 1|| correctOrIncorrect == "1") {
                                isAttempted = 1;
                                isCorrect = 1;
                                correctCount++;
                                document.getElementById('explanationid').style.display = "none";
                            } else {
                                isAttempted = 1;
                                isCorrect = 0;
                                document.getElementById('explanationid').style.display = "block";
                            }
                            isSelect++;
                            $(this).css("background", "#f68929").css("border",
                                    "1px solid #cf6f1a");
                            var ss1 = parseFloat($("#ss").val());
                            var mm1 = parseFloat($("#mm").val());
                            if (mm1 > 0) {
                                ss1 = ss1 + mm1 * 60;
                            }
                            var hh1 = parseFloat($("#hh").val());
                            if (hh1 > 0) {
                                ss1 = ss1 + hh1 * 60 * 60;
                            }
                            
                            
                            
                           var diffValue = document.getElementById("diffId").value;
                            saveQuestion(isAttempted, isCorrect, correctCount,
                                    ss1, userValues , diffValue);
                            var val = '';
                            if (isAttempted == 1 && isCorrect == 1) {
                              
                                var questionNumber =document.getElementById('questionNumberCount1').value;
                              
                                val = '<span data-toggle="tooltip" title="" class="badge bg-green quest_cir" data-original-title=""></span>';
                              
                              
                                var inputVal =document.getElementById('questionNumberCount1').value;
                                var updatedVal=parseInt(inputVal)+1;
                                document.getElementById('questionNumberCount1').value=updatedVal;
                              
                                //val = '<li class="question-right" style="cursor: pointer;"><i class="fa fa-check" aria-hidden="true"></i> </li>';
                            } else if (isAttempted == 1 && isCorrect == 0) {
                                var questionNumber =document.getElementById('questionNumberCount1').value;
                                val = ' <span data-toggle="tooltip" title="" class="badge bg-red quest_cir" data-original-title=""></span>';
                              
                                var inputVal =document.getElementById('questionNumberCount1').value;
                                var updatedVal=parseInt(inputVal)+1;
                                document.getElementById('questionNumberCount1').value=updatedVal;
                              
                                //val = ' <li class="question-wrong" style="cursor: pointer;"><i class="fa fa-times" aria-hidden="true"></i></li>';
                            }
                            $("#questStatusId1").append(val);
                        } else {
                            alert("You have already Select Answer");
                            return false;
                        }

                    })
  
  
  
  
}

else{
  
    var v = 0;
    $.ajaxSetup({
        cache : false
    });
    var ss = parseInt(0), mm = parseInt(0), hh = parseInt(0);
    var sec = parseInt(1000);
    var fun = function() {
        if (ss > 0) {
            ss++;
            if (ss > 59) {
                ss = parseInt(0);
                mm++;
                if (mm > 59) {
                    mm = parseInt(0);
                    hh++;
                    if (hh > parseInt(23)) {
                        hh++;
                    }
                }
            }
            $("#ss").val(ss >= 10 ? ss : parseInt(0) + ss.toString());
            $("#mm").val(mm >= 10 ? mm : "0" + mm.toString());
            $("#hh").val(hh >= 10 ? hh : "0" + hh.toString());
        } else {
            ss = parseInt(1);
            $("#ss").val("0" + ss.toString());
        }

    }
    var timer = setInterval(fun, (sec));
    $(document).ready(function() {
      
      
      
      
         if ('${flag}' != 'false')
            $('#genScore').empty().append('${score}');
        else {
           // clearInterval(timer);
        }

    });
  
    var isSelect = parseInt(0);
    var isAttempted = parseInt(0);
    var isCorrect = parseInt(0);
    var resp = "";
    var flag = false;
    $(".list-group-item")
            .on(
                    "click",
                    function() {
                        if (isSelect == 0 || isSelect == '0') {
                            flag = true;
                            var correctOrIncorrect = $(this).attr('ic');
                            var userValues = $(this).attr('id');
                          
                          
                          
                          
                          
                             if(questioncountflag>4)
                                {
                                
                                
                                 var alldata="";
                                
                                 for(var i in questionselected)
                                     {
                                      //alert();
                                    
                                     alldata=alldata+questionselected[i];
                                    
                                    
                                     }
                                
                                 document.getElementById('fivequestionresult').innerHTML=alldata;
                                
                                
                                openNav();
                              
                              
                                }
                            else{
                                 var question=document.getElementById('questions').innerHTML;
                                questionselected.push(question);
                          
                          
                            }
                          
                              
                            if (correctOrIncorrect == 1
                                    || correctOrIncorrect == "1") {
                                isAttempted = 1;
                                isCorrect = 1;
                                correctCount++;
                            } else {
                                isAttempted = 1;
                                isCorrect = 0;
                            }
                            isSelect++;
                            $(this).css("background", "#f68929").css("border",
                                    "1px solid #cf6f1a");
                            var ss1 = parseFloat($("#ss").val());
                            var mm1 = parseFloat($("#mm").val());
                            if (mm1 > 0) {
                                ss1 = ss1 + mm1 * 60;
                            }
                            var hh1 = parseFloat($("#hh").val());
                            if (hh1 > 0) {
                                ss1 = ss1 + hh1 * 60 * 60;
                            }
                            
                            var diffValue = document.getElementById("diffId").value;
                            saveQuestion(isAttempted, isCorrect, correctCount,
                                    ss1, userValues , diffValue);
                           /*  saveQuestion(isAttempted, isCorrect, correctCount,
                                    ss1, userValues); */
                            var val = '';
                            if (isAttempted == 1 && isCorrect == 1) {
                              
                                var questionNumber =document.getElementById('questionNumberCount1').value;
                              
                                val = '<span data-toggle="tooltip" title="" class="badge bg-green quest_cir" data-original-title=""> </span>';
                              
                              
                                var inputVal =document.getElementById('questionNumberCount1').value;
                                var updatedVal=parseInt(inputVal)+1;
                                document.getElementById('questionNumberCount1').value=updatedVal;
                              
                                //val = '<li class="question-right" style="cursor: pointer;"><i class="fa fa-check" aria-hidden="true"></i> </li>';
                            } else if (isAttempted == 1 && isCorrect == 0) {
                                var questionNumber =document.getElementById('questionNumberCount1').value;
                                val = ' <span data-toggle="tooltip" title="" class="badge bg-red quest_cir" data-original-title=""> </span>';
                              
                                var inputVal =document.getElementById('questionNumberCount1').value;
                                var updatedVal=parseInt(inputVal)+1;
                                document.getElementById('questionNumberCount1').value=updatedVal;
                              
                                //val = ' <li class="question-wrong" style="cursor: pointer;"><i class="fa fa-times" aria-hidden="true"></i></li>';
                            }
                            $("#questStatusId1").append(val);
                        } else {
                            alert("You have already Select Answer");
                            return false;
                        }

                    })
  
  
  
  
}

    function getNextQuestion() {
    	
    	
        incQuestionCount++;
        /* localStorage.setItem('incQuestionCount',incQuestionCount);
        localStorage.setItem('locountQForLocal','${locountQ}'); */
        
         incrimentForQuestiion++;
       
            var ss1 = parseFloat($("#ss").val());
            var mm1 = parseFloat($("#mm").val());
            if (mm1 > 0) {
                ss1 = ss1 + mm1 * 60;
            }
            var hh1 = parseFloat($("#hh").val());
            if (hh1 > 0) {
                ss1 = ss1 + hh1 * 60 * 60;
            }
          
            var diffValue = document.getElementById("diffId").value;
            saveQuestion(isAttempted, isCorrect, correctCount,
                    ss1, userValues , diffValue);
            
           // saveQuestion(isAttempted, isCorrect, correctCount,ss1, userValues);
          
            // saveQuestion(isAttempted, 0, correctCount, ss1, "");
            var val = '';
        if (isAttempted == 0) {
            var questionNumber =document.getElementById('questionNumberCount').value;
            val = '<span data-toggle="tooltip" title="" class="badge bg-yellow quest_cir" data-original-title=""> </span>';
          
            var inputVal =document.getElementById('questionNumberCount').value;
            var updatedVal=parseInt(inputVal)+1;
            document.getElementById('questionNumberCount').value=updatedVal;
          
          
        }
       $("#questStatusId").append(val)
      
        $('#questionDesc').empty();
      
          
         var url = "${pageContext.request.contextPath}/student/getNextQuestion?topicId=${param.topicId}&sId=${param.sId}&subjectName=${param.subjectName}";
          $.ajax({
                 type : "post",
                 url : url
             })
                 .done(function(res) {
                  $('#questionDesc').html(res);
                  var val=document.getElementById('dynamicresource').innerHTML;
                  $('#maindynamicresource').html(val);
                  question_new_old=1;
                     }).fail(function() {
                     alert("error");
                 });

         
      
        $("#ss").val('00');
        $("#mm").val('00');
        $("#hh").val('00');
        clearInterval(timer); 
      
    }

  
  
  
    function closeNav() {
      
        document.getElementById("myNav").style.width = "0%";
        document.body.style.overflowY = "auto";
        document.body.style.overflowx = "auto";
         questionselected = [];
         questioncountflag =0;
    }
    

    
    
</script>
