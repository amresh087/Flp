<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="schoolTeacherHeader.jsp">
<jsp:param value="manageToc" name="header"/>
</jsp:include>

 <div id="page-wrapper" style="padding-top:5%;">
        
            <div class="container-fluid">
<div class="row">
<div class="col-lg-12">

<jsp:include page="teacherSubjectHeader.jsp">
		<jsp:param value="dashboard" name="header" />
		</jsp:include>
<script type="text/javascript">


function checkBoxChapterValueContentPermission(data){
	//var flag=confirm("Are you sure you want to mark chapter.");
	
	
	var flag;
	if($('#cp_'+data.value).is(':checked'))
	{
		 flag=confirm("Are you sure you want to mark chapter.");
	}else{
		flag=confirm("Are you sure you want to Unmark chapter.");
	}
    if(flag){
    	
    	var chapterID=data.value;
    	
    	var subjectId=${subjectId};
    	$(".cpTopicCls"+chapterID ).each(function() {
    		var topicId=$(this).attr('id');
    		
    		if($('#cp_'+chapterID).is(':checked')){
    			$('#'+topicId).prop('checked', true);
    			var topc=topicId.replace("cp_", "");

    			checkBoxAjaxContentPermission(topc,chapterID,subjectId,'checked');			
    			
    		}
    		else{
    			$('#'+topicId).prop('checked', false);
    			//alert("unChecked---"+topicId);
    			
    			var topc=topicId.replace("cp_", "");

    			checkBoxAjaxContentPermission(topc,chapterID,subjectId,'unChecked');
    		}

    	});

 	  // alert("updated succesffuly");
    }
    else
 	   {
 	  // alert("you pressed no");
 	   }
	
	
}

function checkBoxTopicValueContentPermission(data,chapterID){

	

	var flag;
	if($('#cp_'+data.value).is(':checked'))
	{
		 flag=confirm("Are you sure you want to mark topic.");
	}else{
		flag=confirm("Are you sure you want to UnMark topic.");
	}
	 
     if(flag){
    	 
    	 var topicId=data.value;
    		var subjectId=${subjectId};
    		if($('#cp_'+data.value).is(':checked'))
    		{

    			

    			
    			
    			
    			var topc=topicId.replace("cp_", ""); 
    			checkBoxAjaxContentPermission(topc,chapterID,subjectId,'checked');
    			
    			  
    			}
    	    else
    	    {
    	    //	alert("Unchecked");
    	    	//var chap=chapterID.replace("cp_", "");
    			var topc=topicId.replace("cp_", "");
    			checkBoxAjaxContentPermission(topc,chapterID,subjectId,'unChecked');
    	    	//checkBoxAjax(topicId,chapterID,subjectId,'unChecked')
    	    	}
    	       
     }
     else
  	   {
  	   //alert("you pressed no");
  	   }
	
	
	

	}
	
	
	/////////////////////////////////////////////////////////////////////////















function checkBoxChapterValue(data){
	//var flag=confirm("Are you sure you want to mark chapter.");
	
	
	var flag;
	if($('#'+data.value).is(':checked'))
	{
		 flag=confirm("Are you sure you want to mark chapter.");
	}else{
		flag=confirm("Are you sure you want to Unmark chapter.");
	}
    if(flag){
    	
    	var chapterID=data.value;
    	var subjectId=${subjectId};
    	$(".topicCls"+chapterID ).each(function() {
    		var topicId=$(this).attr('id');
    		if($('#'+chapterID).is(':checked')){
    			$('#'+topicId).prop('checked', true);
    			checkBoxAjax(topicId,chapterID,subjectId,'checked');			

    		}
    		else{
    			$('#'+topicId).prop('checked', false);
    			checkBoxAjax(topicId,chapterID,subjectId,'unChecked');
    		}

    	});

 	  // alert("updated succesffuly");
    }
    else
 	   {
 	  // alert("you pressed no");
 	   }
	
	
}

function checkBoxTopicValue(data,chapterID){
	var flag;
	if($('#'+data.value).is(':checked'))
	{
		 flag=confirm("Are you sure you want to mark topic.");
	}else{
		flag=confirm("Are you sure you want to UnMark topic.");
	}
	 
     if(flag){
    	 
    	 var topicId=data.value;
    		var subjectId=${subjectId};
    		if($('#'+data.value).is(':checked'))
    		{
    			
    			checkBoxAjax(topicId,chapterID,subjectId,'checked')
    			  
    			}
    	    else
    	    {
    	    	checkBoxAjax(topicId,chapterID,subjectId,'unChecked')
    	    	}
    	       
     }
     else
  	   {
  	   //alert("you pressed no");
  	   }
	
	
	

	}
	
function checkBoxAjax(topicId,chapterID,subjectId,checkUncheckedVal){
	
	$.post("${pageContext.request.contextPath}/ajax/rest/markCompleted/" + topicId +  "/" + chapterID + "/" +subjectId+ "/" + checkUncheckedVal+"/${param.secId}/${param.grdId}", function(data)
		    {
		     
		    }); 
}
function checkBoxAjaxContentPermission(topicId,chapterID,subjectId,checkUncheckedVal){
	
	
	/* alert('topicId :: '+topicId);
	alert('chapterID :: '+chapterID);
	alert('subjectId :: '+subjectId); */
	
	
	$.post("${pageContext.request.contextPath}/ajax/rest/contentPermission/" + topicId +  "/" + chapterID + "/" +subjectId+ "/" + checkUncheckedVal+"/${param.secId}/${param.grdId}", function(data)
		    {
		     
		    });  
}

	
	
function assignedTopic(topicID){
	var flag=confirm("Are you sure you want assign topic.");
	if(flag){
		 var sectionId=${sectionId};
		    var topicId=topicID;
		    var gradeId=${gradeId};
		    var subjectId=${subjectId};
		    
		    
		   $.post("${pageContext.request.contextPath}/ajax/rest/assignTopicToSection/" + sectionId + "/" + topicId + "/" + gradeId + "/" + subjectId, function(data)
				    {
					    if(data != null && parseInt(data) > 0)
					    {
					    	alert('Topic Successfully Assigned.');
					    	$("#topic"+topicID).val('Assigned');
					    	 $("#topic"+topicID).removeAttr('class');
					    	 $("#topic"+topicID).addClass('btn').addClass('btn-info').addClass('disabled');
					    }else{alert('Topic already Assigned.');}
					   
					    $(".close").trigger('click');
				    });
	}else{}
	   
	
}




</script>

</div>
          
		 <div class="clearfix"></div>
            
		 </div>
    	
                
        <div class=" col-md-12">
	  <div class="clearfix"></div>
	  <h3><img src="${pageContext.request.contextPath}/resources/images/libr.png" alt=""> Content Library</h3>
        <!--tab-cont-->
		<div class="tab-content"> 
	
     
		
		
	     <c:set var="chapCount" value="1" />
        <c:forEach items="${chapterList}" var="chapter" varStatus="status">
        
       
        
         
        <div class="panel panel-default">
        
            <div class="panel-heading">
                <h4 class="panel-title col-md-4">
                    <a data-toggle="collapse" data-parent="#accordion_${chapter.id}_${status.count}" href="#collapse_${chapter.id}_${status.count}">${status.count}. ${chapter.name}</a>
                </h4>
              
                  <div class="col-md-2">Modules : ${chapter.moduleCount}</div>
                  
                 
                  
                <div class="col-md-2">Questions : ${chapter.count}</div>
                <div class="col-md-2">Mark Complete : 
                <c:choose>
    <c:when test="${chapter.isCompleted == 1}">
                <input type="checkbox" id="${chapter.id}" value="${chapter.id}" onclick="checkBoxChapterValue(this)"  checked>
    </c:when>    
    <c:otherwise>
        <input type="checkbox" id="${chapter.id}" value="${chapter.id}" onclick="checkBoxChapterValue(this)"> 
    </c:otherwise>
</c:choose>
           </div>
           
           
           <!--  Show or hide Student-->
           <div class="col-md-2">Access to student : 
                <c:choose>
    <c:when test="${chapter.markChapterContentPermission == 1}">
                <input type="checkbox" id="cp_${chapter.id}" value="${chapter.id}" onclick="checkBoxChapterValueContentPermission(this)"  checked>
    </c:when>    
    <c:otherwise>
        <input type="checkbox" id="cp_${chapter.id}" value="${chapter.id}" onclick="checkBoxChapterValueContentPermission(this)"> 
    </c:otherwise>
</c:choose> 

           </div>
           <!-- End -Show or hide Student-->
           
           
           
           
           
            </div>
            <div id="collapse_${chapter.id}_${status.count}" class="panel-collapse collapse <c:if test="${chapCount == 1}"> in </c:if>">
                
           
                <c:forEach items="${chapter.topics}" var="topic">
                 <c:if test="${topic.status == 1}">
                <div class="panel-body">
                <div class="col-md-12">
                
                  <div class="col-md-3"><p>
                  <a href="${pageContext.request.contextPath}/schoolTeacher/getManageTocModule.htm?&topicId=${topic.id}&secId=${sectionId}&flagQorM=Q&subId=${subjectId}&grdId=${gradeId}&subjectName=${subject}&chapName=${chapter.name}&topicName=${topic.name}">${topic.name}</a></p></div>
                 

                <div class="col-md-2"><a href="${pageContext.request.contextPath}/schoolTeacher/getManageTocModule.htm?&topicId=${topic.id} &flagQorM=M&secId=${sectionId}&subId=${subjectId}&grdId=${gradeId}&subjectName=${subject}&chapName=${chapter.name}&topicName=${topic.name}" >Modules :${topic.tModulecount}</a></div>
                <div class="col-md-2"><a href="${pageContext.request.contextPath}/schoolTeacher/getManageTocModule.htm?&topicId=${topic.id}&flagQorM=Q&secId=${sectionId}&subId=${subjectId}&grdId=${gradeId}&subjectName=${subject}&chapName=${chapter.name}&topicName=${topic.name}">Questions :${topic.count}</a></div>

                <div class="col-md-2">Mark Complete : 
                
                 <c:choose>
  <c:when test="${topic.markTopic == 1}">
       <input type="checkbox" id="${topic.id}" value="${topic.id}" onclick="checkBoxTopicValue(this,${chapter.id})" class="topicCls${chapter.id }" checked>
    </c:when>    
    <c:otherwise>
     <input type="checkbox" id="${topic.id}" value="${topic.id}" onclick="checkBoxTopicValue(this,${chapter.id})" class="topicCls${chapter.id }">
    </c:otherwise>
				</c:choose>
                
 </div>
 
 
 <!--  Show or hide Student-->
   <div class="col-md-2">Access to student :  
                 
                  <c:choose>
  <c:when test="${topic.markTopicContentPermission == 1}">
       <input type="checkbox" id="cp_${topic.id}" value="${topic.id}" onclick="checkBoxTopicValueContentPermission(this,${chapter.id})" class="cpTopicCls${chapter.id }" checked>
    </c:when>    
    <c:otherwise>
     <input type="checkbox" id="cp_${topic.id}" value="${topic.id}" onclick="checkBoxTopicValueContentPermission(this,${chapter.id})" class="cpTopicCls${chapter.id }">
    </c:otherwise>
				</c:choose> 
				
				
 </div>
  <!-- End -Show or hide Student-->
 
 
 
 
 
 
                <div class="col-md-2"><c:choose><c:when test="${topic.isAssigned eq 1 }"><input type="button" class="btn btn-info disabled" value="Assigned"/></c:when><c:otherwise><input type="button" class="btn btn-primary btn-md" id="topic${topic.id}"  style="" value="Assign" Onclick="assignedTopic(${topic.id})"/></c:otherwise></c:choose></div>
                </div>
            </div><br> </c:if></c:forEach>
        </div>
        
        </div>
        <c:set var="chapCount" value="${chapCount +1}" />
        </c:forEach>   	
		
		
<!-- 		
endDiv -->
		
		
		
		
		
		
		
		

    
    <!--tab-cont-->
    
    
 <br /><br />
  
  
  
</div>

        
		<div class="clearfix"></div>
        <!--tab-cont-->
		<div class="tab-content">
	
      
      



	  

          
<!-- /.container-fluid -->
 </div>
<!-- /#page-wrapper -->
        
        
       </div> 

    </div>
    <div style="width: 38px; height: 117px; right: 0px; top: 50%; position: fixed;">
<a href="https://docs.google.com/forms/d/e/1FAIpQLScylZUgeQrLr2oGBufoyeQFKFIlBQD67qvOdFhWu6Qcd17rRw/viewform" target="blank"><img src="${pageContext.request.contextPath}/resources/images/feedback.png" alt=""></a>
</div>