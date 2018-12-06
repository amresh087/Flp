<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/student/newjs/lightbox-plus-jquery.min.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/student/newcss/lightbox.min.css">		
	
<script>

function getNextQuestion1() {
	 /*start loder  */
	 $("#hintId").html(" ");
	 $('#quesload').show();
	 
	 
    if (questioncountflag++, !flag || 0 == flag) {
        var e = parseFloat($("#ss").val()),
            t = parseFloat($("#mm").val());
        t > 0 && (e += 60 * t);
        var a = parseFloat($("#hh").val());
        a > 0 && (e += 60 * a * 60);
        var n = document.getElementById("diffId").value;
       
        saveQuestion(isAttempted, 0, correctCount, e, "", n);
        
        
        var o = "";
        if (0 == isAttempted) {
            var s = (document.getElementById("questionNumberCount1").value, document.getElementById("questions").innerHTML);
           if(questionselected.length<5)
            questionselected.push(s);
            
            
            o = '<span data-toggle="tooltip" title="" class="badge bg-yellows quest_cir" data-original-title=""></span>';
            var r = document.getElementById("questionNumberCount1").value,
                u = parseInt(r) + 1;
            document.getElementById("questionNumberCount1").value = u;
        }
        $("#questStatusId1").append(o)
    }
    
    window.setTimeout(function()
        	{
        	
        	//alert("Hello1"); 
        	
    
    
    $("#questionDesc").empty();
    var l = "${pageContext.request.contextPath}/student/getNextQuestion?topicId=${param.topicId}&sId=${param.sId}&subjectName=${param.subjectName}";
    $.ajax({
        type: "post",
        url: l
    }).done(function(e) {
        $("#questionDesc").html(e);
        var t = document.getElementById("dynamicresource").innerHTML;
        $("#maindynamicresource").html(t);
        var t = document.getElementById("hintIdComman").innerHTML;
        $("#hintId").html(t);
        
        $('#quesload').hide();
        
    }).fail(function() {
        alert("error")
    }), $("#ss").val("00"), $("#mm").val("00"), $("#hh").val("00"), clearInterval(timer)
    
        	},1000);
    
}

function saveQuestion(e, t, a, n, o, s) {
    var r = "${pageContext.request.contextPath}/student/saveQuestion",
        u = {
            topicId: '<c:out value="${param.topicId}"/>',
            questionId: '<c:out value="${question.id}"/>',
            isAttempted: e,
            isCorrect: t,
            correctCount: a,
            timeTaken: n,
            locount: "1",
            diffValue: s
        };
    
    $.ajax({
        type: "post",
        url: r + "?userValues=" + o,
        data: u
    }).done(function(e) {
    
    	
    	if (console.log(v++), parseInt(cGscore) != parseInt(e)) switch (a = parseInt(0), cGscore = e, 32 == parseInt(cGscore) && (a = 0), e) {
            case 40:$("#hintId").html(" ");
                localStorage.setItem("gola_green", document.getElementById("questStatusId").innerHTML), location.reload();
                break;
            case 100:
                location.reload();
                break;
            case 200:
                location.reload()
        }
        ss = parseFloat(0)
    }).fail(function() {
        alert("error")
    })
}
    </script>
<script src="${pageContext.request.contextPath}/resources/js/jquery.js"
	type="text/javascript"></script>

<!-- for Hint code----------------------------------- -->
<div id='hintIdComman' class="face back" style="display: none;">
	<c:choose>
		<c:when test="${question.hinttypeid=='2'}">
			<p>${question.hintContent}</p>
		</c:when>

		<c:when test="${question.hinttypeid=='3'}">
		<a class="example-image-link" href="${lmsimagepath}${question.hintContent}" data-lightbox="example-1">
			<img src="${lmsimagepath}${question.hintContent}"
				class="img-responsive" />
</a>
		</c:when>

		<c:when test="${question.hinttypeid=='4'}">
			<video controls class="img-responsive">
				<source src="${lmsimagepath}${question.hintContent}"
					type="video/mp4">
			</video>
		</c:when>

		<c:when test="${question.hinttypeid=='5'}">
			<object id="FlashID" width="100%" height="100%">
				<param name="movie" value="${lmsimagepath}${question.hintContent}" />
				<param name="quality" value="high" />
				<param name="wmode" value="opaque" />
				<param name="swfversion" value="6.0.65.0" />
				<param name="expressinstall" value="Scripts/expressInstall.swf" />

				<object type="application/x-shockwave-flash"
					data="${lmsimagepath}${question.hintContent}" width="820"
					height="550">
					<param name="quality" value="high" />
					<param name="wmode" value="opaque" />
					<param name="swfversion" value="6.0.65.0" />
					<param name="expressinstall" value="Scripts/expressInstall.swf" />
					<div>
						<h4>Content on this page requires a newer version of Adobe
							Flash Player.</h4>
						<p>
							<a href="http://www.adobe.com/go/getflashplayer"><img
								src="http://www.adobe.com/images/shared/download_buttons/get_flash_player.gif"
								alt="Get Adobe Flash player" width="112" height="33" /></a>
						</p>
					</div>
				</object>
			</object>
		</c:when>
		<c:when test="${question.hinttypeid=='6'}">
			<iframe id="iframeVideo" width="100%" height="400px"
				src="${lmsimagepath}${question.hintContent}"> </iframe>
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
		<div class="col-lg-10">
		
		
			<div class="col-lg-12" id="fivequestionresult" style="border-bottom: solid 1px #E0E4E9;"></div>

		</div>
	</div>

</div>
</div>

<div id='dynamicresource' style="display: none;">
	<div class="box box-solid " style="height:auto;">
		<div class="box-body">

			<c:choose>
				<c:when test="${question.hinttypeid=='2'}">
					<p>${question.hintContent}</p>
				</c:when>

				<c:when test="${question.hinttypeid=='3'}">
				<a class="example-image-link" href="${lmsimagepath}${question.hintContent}" data-lightbox="example-1">
					<img src="${lmsimagepath}${question.hintContent}"
						class="img-responsive" />
</a>
				</c:when>

				<c:when test="${question.hinttypeid=='4'}">
					<video controls class="img-responsive">
						<source src="${lmsimagepath}${question.hintContent}"
							type="video/mp4">
					</video>
				</c:when>

				<c:when test="${question.hinttypeid=='5'}">
					<object id="FlashID" width="100%" height="100%">
						<param name="movie" value="${lmsimagepath}${question.hintContent}" />
						<param name="quality" value="high" />
						<param name="wmode" value="opaque" />
						<param name="swfversion" value="6.0.65.0" />
						<param name="expressinstall" value="Scripts/expressInstall.swf" />

						<object type="application/x-shockwave-flash"
							data="${lmsimagepath}${question.hintContent}" width="820"
							height="550">
							<param name="quality" value="high" />
							<param name="wmode" value="opaque" />
							<param name="swfversion" value="6.0.65.0" />
							<param name="expressinstall" value="Scripts/expressInstall.swf" />
							<div>
								<h4>Content on this page requires a newer version of Adobe
									Flash Player.</h4>
								<p>
									<a href="http://www.adobe.com/go/getflashplayer"><img
										src="http://www.adobe.com/images/shared/download_buttons/get_flash_player.gif"
										alt="Get Adobe Flash player" width="112" height="33" /></a>
								</p>
							</div>
						</object>
					</object>

				</c:when>


				<c:when test="${question.hinttypeid=='6'}">
					<iframe id="iframeVideo" width="100%" height="400px"
						src="${lmsimagepath}${question.hintContent}"> </iframe>
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
			<div id="question${question.id}" style="display:none;">0</div>
			<input type="hidden" id="diffId"
				value='${question.difficultyLevelId}'>
			<p class="question_description">${question.getDescription()}
				<c:if test="${not empty question.imagePath}">
					<div class="col-md-12">
					<a class="example-image-link" href="${lmsimagepath}${question.imagePath}" data-lightbox="example-1">
						<img src="${lmsimagepath}${question.imagePath}" alt="" />
						</a>
					</div>
				</c:if>
				</p>
			<ul class="list-group" id="ulcontainer">
				<c:forEach var="answer" items="${question.answers}"
					varStatus="status">
					<c:set var="ic" value="${answer.isCorrect}" />
					<li class="list-group-item" id="${answer.id}" userres="0"  divid="question${question.id}"  ic='${ic}'
						radioid='radio${answer.id}'><input type="radio"
						id="radio${answer.id}" ic='${ic}' liid="${answer.id}"
						name="answer" style='display: none'> <label
						for="${answer.id}"> ${answer.getDescription()}</label> <c:if
							test="${not empty answer.imagePath}">
							
							<a class="example-image-link" href="${lmsimagepath}${answer.imagePath}" data-lightbox="example-1"><img src="${lmsimagepath}${answer.imagePath}" alt="" /></a>
						</c:if></li>
				</c:forEach>

			</ul>

		</div>
		<!-- view explanation -->
		<div class="box box-success box-solid" id="explanationid"
			style="display: none;">
			<div class="box-header with-border">
				<h3 class="box-title"  onclick="show('benefits'+${question.id}${val})">
					<i class="fa fa-eye" aria-hidden="true"></i>View Explanation
				</h3>

				<div class="box-tools pull-right">
					<button type="button" class="btn btn-box-tool" data-widget="remove">
						<i class="fa fa-times"></i>
					</button>
				</div>

			</div>

			<div class="box-body" id="benefits${question.id}${val}" style="display: none;">


				<c:choose>
					<c:when test="${question.explanationId=='21'}">
						<p>${question.explanationContent}</p>
					</c:when>
					<c:when test="${question.explanationId=='22'}">
					<a class="example-image-link" href="${lmsimagepath}${question.explanationContent}" data-lightbox="example-1">
						<img src="${lmsimagepath}${question.explanationContent}"
							class="img-responsive" />
							</a>

					</c:when>

					<c:when test="${question.explanationId=='23'}">
						<video controls class="img-responsive">
							<source src="${lmsimagepath}${question.explanationContent}"
								type="video/mp4">
						</video>
					</c:when>

					<c:when test="${question.explanationId=='24'}">
						<object id="FlashID" width="100%" height="100%">
							<param name="movie"
								value="${lmsimagepath}${question.explanationContent}" />
							<param name="quality" value="high" />
							<param name="wmode" value="opaque" />
							<param name="swfversion" value="6.0.65.0" />
							<param name="expressinstall" value="Scripts/expressInstall.swf" />

							<object type="application/x-shockwave-flash"
								data="${lmsimagepath}${question.explanationContent}" width="820"
								height="550">
								<param name="quality" value="high" />
								<param name="wmode" value="opaque" />
								<param name="swfversion" value="6.0.65.0" />
								<param name="expressinstall" value="Scripts/expressInstall.swf" />
								<div>
									<h4>Content on this page requires a newer version of Adobe
										Flash Player.</h4>
									<p>
										<a href="http://www.adobe.com/go/getflashplayer"><img
											src="http://www.adobe.com/images/shared/download_buttons/get_flash_player.gif"
											alt="Get Adobe Flash player" width="112" height="33" /></a>
									</p>
								</div>
							</object>
						</object>

					</c:when>


					<c:when test="${question.explanationId=='25'}">
						<iframe id="iframeVideo" width="100%" height="400px"
							src="${lmsimagepath}${question.explanationContent}"> </iframe>

					</c:when>


					<c:otherwise>
						<p>No Explanation Related To This Question..</p>
					</c:otherwise>

				</c:choose>





				<div id="upbutton label ">
					<a class="btn btn-block btn-warning" onclick="conceal('benefits'+${question.id}${val})"
						style="font-size: 16px"><i class="fa fa-thumbs-up"
						aria-hidden="true"></i> Ok Got It</a>
				</div>
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
function getNextQuestion() {
	
	$("#maindynamicresource").html(" ");
	 $('#quesload').show();
	
	
    incQuestionCount++, incrimentForQuestiion++;
    
    
    var e = parseFloat($("#ss").val()),
        t = parseFloat($("#mm").val());
    t > 0 && (e += 60 * t);
    var s = parseFloat($("#hh").val());
    s > 0 && (e += 60 * s * 60);
    var n = document.getElementById("diffId").value;
    saveQuestion(isAttempted, isCorrect, correctCount, e, userValues, n);
   
    
   
    	
    	
    	 var a = "";
    	    if (0 == isAttempted) {
    	        document.getElementById("questionNumberCount").value;
    	        a = '<span data-toggle="tooltip" title="" class="badge bg-yellows quest_cir" data-original-title=""> </span>';
    	        var o = document.getElementById("questionNumberCount").value,
    	            r = parseInt(o) + 1;
    	        document.getElementById("questionNumberCount").value = r
    	    }
    	    $("#questStatusId").append(a), $("#questionDesc").empty();
    	   
    	    
    	    window.setTimeout(function()
    	        	{ 
    	    
    	    var i = "${pageContext.request.contextPath}/student/getNextQuestion?topicId=${param.topicId}&sId=${param.sId}&subjectName=${param.subjectName}";
    	    $.ajax({
    	        type: "post",
    	        url: i
    	    }).done(function(e) {
    	        $("#questionDesc").html(e);
    	        var t = document.getElementById("dynamicresource").innerHTML;
    	       
    	        $("#maindynamicresource").html(t), question_new_old = 1;
    	        $('#quesload').hide();
    	    }).fail(function() {
    	        alert("error")
    	    }), $("#ss").val("00"), $("#mm").val("00"), $("#hh").val("00"), clearInterval(timer)
    	    
    	
    	
    		}, 1000);
    
    
    
   
    
    
    
}

function closeNav() {
    document.getElementById("myNav").style.width = "0%", document.body.style.overflowY = "auto", document.body.style.overflowx = "auto", questionselected = [], questioncountflag = 0;
    quest_count=0;
}
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
           
            if ('${flag}' != 'false' && incQuestionCount >= 8 && '${locountQ}'==1)
            { 
            	 
            $('#genScore').empty().append('${score}');}
            
            else if ('${flag}' != 'false' && incQuestionCount >=10 && '${locountQ}'==2 )
            {  
            	 
            $('#genScore').empty().append('${score}');}
            
            
            else if ('${flag}' != 'false' && incQuestionCount >=10 && '${locountQ}'==3 )
            {  
            	 
            $('#genScore').empty().append('${score}');
            }
            
        else {
            
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
    $(".list-group-item").on("click", function() {
        if (0 != isSelect && "0" != isSelect) return alert("You have already Select Answer"), !1;
        var e = $(this).attr("radioid");
        document.getElementById(e).checked = !0, document.getElementById(e).attr;
        for (var t = $("#" + e).attr("ic"), s = document.getElementById("ulcontainer").children, n = s.length, a = 0; n > a; a++) "li" == s[a].nodeName.toLowerCase() && $("#" + s[a].id).css("background", "#ffffff").css("border", "1px solid #ffffff");
        1 == t || "1" == t ? $(this).css("border", "1px solid #00A65A") : (0 == t || "0" == t) && $(this).css("border", "1px solid #c93e3e"), userValues = $(this).attr("id");
        var o = $(this).attr("ic");
        1 == o || "1" == o ? (isAttempted = 1, isCorrect = 1, correctCount++, trueclickCount++, countdemo++, document.getElementById("explanationid").style.display = "none") : (isAttempted = 1, isCorrect = 0, falseclickCount++, countdemo++, document.getElementById("explanationid").style.display = "block"), (1 == question_new_old || "1" == question_new_old) && (questioid = "count_" + incrimentForQuestiion + "_QID_${question.id}", $("#questStatusId").append('<span data-toggle="tooltip" title="" id="' + questioid + '"  class=""  data-original-title=""></span>'), question_new_old = 0), 1 == isAttempted && 1 == isCorrect && (2 == trueclickCount && 2 == countdemo ? document.getElementById(questioid).className = "badge bg-green quest_cir" : (document.getElementById(questioid).className = "badge bg-blue quest_cir", isCorrect = 2)), 1 == isAttempted && 0 == isCorrect && (document.getElementById(questioid).className = "badge bg-red quest_cir")
    })
} else if (gscore >= 40 && 100 > gscore) {
    40 == gscore && (document.getElementById("questStatusId1").innerHTML = localStorage.getItem("gola_green")), document.getElementById("hintId").style.display = "none", document.getElementById("face_front").style.display = "block";
    var v = 0;
    $.ajaxSetup({
        cache: !1
    });
    var ss = parseInt(0),
        mm = parseInt(0),
        hh = parseInt(0),
        sec = parseInt(1e3),
        fun = function() {
            ss > 0 ? (ss++, ss > 59 && (ss = parseInt(0), mm++, mm > 59 && (mm = parseInt(0), hh++, hh > parseInt(23) && hh++)), $("#ss").val(ss >= 10 ? ss : parseInt(0) + ss.toString()), $("#mm").val(mm >= 10 ? mm : "0" + mm.toString()), $("#hh").val(hh >= 10 ? hh : "0" + hh.toString())) : (ss = parseInt(1), $("#ss").val("0" + ss.toString()))
        },
        timer = setInterval(fun, sec);
    $(document).ready(function() {
        $("#genScore").empty().append("${score}")
    });
    var isSelect = parseInt(0),
        isAttempted = parseInt(0),
        isCorrect = parseInt(0),
        resp = "",
        flag = !1;
    $(".list-group-item").on("click", function() {
        if (0 != isSelect && "0" != isSelect) return alert("You have already Select Answer"), !1;
        flag = !0;
        var e = $(this).attr("ic"),
            t = $(this).attr("id");
        1 == e || "1" == e ? (isAttempted = 1, isCorrect = 1, correctCount++, document.getElementById("explanationid").style.display = "none") : (isAttempted = 1, isCorrect = 0, document.getElementById("explanationid").style.display = "block"), isSelect++, $(this).css("background", "#f68929").css("border", "2px solid #cf6f1a");
        var s = parseFloat($("#ss").val()),
            n = parseFloat($("#mm").val());
        n > 0 && (s += 60 * n);
        var a = parseFloat($("#hh").val());
        a > 0 && (s += 60 * a * 60);
        var o = document.getElementById("diffId").value;
        saveQuestion(isAttempted, isCorrect, correctCount, s, t, o);
        var r = "";
        if (1 == isAttempted && 1 == isCorrect) {
            document.getElementById("questionNumberCount1").value;
            r = '<span data-toggle="tooltip" title="" class="badge bg-green quest_cir" data-original-title=""></span>';
            var i = document.getElementById("questionNumberCount1").value,
                l = parseInt(i) + 1;
            document.getElementById("questionNumberCount1").value = l
        } else if (1 == isAttempted && 0 == isCorrect) {
            document.getElementById("questionNumberCount1").value;
            r = ' <span data-toggle="tooltip" title="" class="badge bg-red quest_cir" data-original-title=""></span>';
            var i = document.getElementById("questionNumberCount1").value,
                l = parseInt(i) + 1;
            document.getElementById("questionNumberCount1").value = l
        }
        $("#questStatusId1").append(r)
    })
} else {
    var v = 0;
    $.ajaxSetup({
        cache: !1
    });
    var ss = parseInt(0),
        mm = parseInt(0),
        hh = parseInt(0),
        sec = parseInt(1e3),
        fun = function() {
            ss > 0 ? (ss++, ss > 59 && (ss = parseInt(0), mm++, mm > 59 && (mm = parseInt(0), hh++, hh > parseInt(23) && hh++)), $("#ss").val(ss >= 10 ? ss : parseInt(0) + ss.toString()), $("#mm").val(mm >= 10 ? mm : "0" + mm.toString()), $("#hh").val(hh >= 10 ? hh : "0" + hh.toString())) : (ss = parseInt(1), $("#ss").val("0" + ss.toString()))
        },
        timer = setInterval(fun, sec);
    $(document).ready(function() {
        $("#genScore").empty().append("${score}")
    });
    var isSelect = parseInt(0),
        isAttempted = parseInt(0),
        isCorrect = parseInt(0),
        resp = "",
        flag = !1;
   
    
    $(".list-group-item").on("click", function() {
        if (0 != isSelect && "0" != isSelect) return alert("You have already Select Answer"), !1;
        flag = !0;
        var e = $(this).attr("ic"),
            t = $(this).attr("id");
        $(this).attr("userres","1");
        
        
        document.getElementById($(this).attr("divid")).innerHTML="1";
      
       
        
        
        
        
        if (questioncountflag > 4) {
            var s = "";
            for(var n in questionselected){ s += questionselected[n];}
            document.getElementById("fivequestionresult").innerHTML = s;
            
            
            openNav();
        } else {
            
        	
        	var a = document.getElementById("questions").innerHTML;
        	
        	 document.getElementById("tepmquestion").innerHTML=a;
        	
        	var pobj= document.getElementById("tepmquestion");
          for (var i = 0; i < pobj.childNodes.length; i++) {
        	  if (pobj.childNodes[i].nodeName.toLowerCase()== "ul") {
     	      var notes = pobj.childNodes[i];
     	      
     	      
     	      
     	     for (var j = 0; j < notes.childNodes.length; j++) {
     	    	if (notes.childNodes[j].nodeName.toLowerCase()== "li") {
     	    		
     	    		var notli = notes.childNodes[j];
     	    		var correct=$(notli).attr("ic");
     	    		var user_res=$(notli).attr("userres");
     	    		var skip=document.getElementById($(this).attr("divid")).innerHTML;
     	    	    
     	    		
     	    
     	    		
     	    		 if(user_res=="1" && correct=="1"){
      	    			$(notli).attr("style","border: 1px solid green");
      	    		}else if(user_res=="1"&& correct=="0"){
  	    			$(notli).attr("style","border: 1px solid red");
  	    			}
      	    		else if(user_res=="0" && correct=="1"  ){
  	    			 $(notli).attr("style","border: 1px solid green");
  	    			}
      	    		 if(skip!="1" ){
     	    			 $(notli).attr("style","border: 1px solid yellow");
     	    			}
     	    		 
     	    		
     	    		
     	    		 
     	    		
     	    	} 
     	    	 
     	     }
     	      
     	    
     	      
     	      
     	      
     	      
     	     // break; 
     	    }
          }
        	 
          var bb=document.getElementById("tepmquestion").innerHTML+document.getElementById("explanationid").innerHTML;
        	
        
        	if(questionselected.length<5)
        		{ 
        		  
                   questionselected.push(bb);
        		}
        document.getElementById("tepmquestion").innerHTML="";
        }
        1 == e || "1" == e ? (isAttempted = 1, isCorrect = 1, correctCount++) : (isAttempted = 1, isCorrect = 0), isSelect++, $(this).css("background", "#f68929").css("border", "1px solid #cf6f1a");
        var o = parseFloat($("#ss").val()),
            r = parseFloat($("#mm").val());
        r > 0 && (o += 60 * r);
        var i = parseFloat($("#hh").val());
        i > 0 && (o += 60 * i * 60);
        var l = document.getElementById("diffId").value;
        saveQuestion(isAttempted, isCorrect, correctCount, o, t, l);
        var u = "";
        if (1 == isAttempted && 1 == isCorrect) {
            document.getElementById("questionNumberCount1").value;
            u = '<span data-toggle="tooltip" title="" class="badge bg-green quest_cir" data-original-title=""> </span>';
            var c = document.getElementById("questionNumberCount1").value,
                d = parseInt(c) + 1;
            document.getElementById("questionNumberCount1").value = d
        } else if (1 == isAttempted && 0 == isCorrect) {
            document.getElementById("questionNumberCount1").value;
            u = ' <span data-toggle="tooltip" title="" class="badge bg-red quest_cir" data-original-title=""> </span>';
            var c = document.getElementById("questionNumberCount1").value,
                d = parseInt(c) + 1;
            document.getElementById("questionNumberCount1").value = d
        }
        $("#questStatusId1").append(u)
    })
}  


//-----------------explaination----------------------

function conceal(m) {      
        if(document.getElementById(m).style.display=='block') {
          document.getElementById(m).style.display='none';
        }
    }  

function show(n) {
	       
   if(document.getElementById(n).style.display=='none') {
      document.getElementById(n).style.display='block';
    } 
}
</script>
