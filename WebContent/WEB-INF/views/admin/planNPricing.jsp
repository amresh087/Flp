<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%-- <%@ include file="../superAdminheader.jsp" %> --%>
<%-- <script src="${pageContext.request.contextPath}/resources/js/jquery.js" type="text/javascript"></script> --%> 
<script src="${pageContext.request.contextPath}/resources/js/jquery-1.11.0.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/common.js" type="text/javascript"></script>


<!--table-start-here-->

	<div class="container">
  
   
    		<div class="row">
            <!--Left-panel-start-here-->
    		<div class="col-md-9">
            
         
            <div class="row">
            <!--table-form-start-here-->
            <div class="col-md-12 tab-forms">	
            <input type="hidden" value="${userId }" name="userId">
            <input type="hidden" name="payable_amount" id="payable_amount" value="">
            	<div class="row">
            	<div class="col-md-4"><p>Select Board, Class and Subject</p></div>
            	<input type="hidden" value="" name="urlId" id="urlId">
                <div class="col-md-3">
                <select name="board" id="board" required="required" onchange="getGradeList('#grade', $(this).val(), '${pageContext.request.contextPath}/getGradeByBoardId', 'Select Grade');">
                <option value="" selected="selected">Select Board</option>
                <c:forEach items="${boardList }" var="board">
				<option value="${board.id}">${board.name}</option>
				</c:forEach>
                
                
                </select></div>
                <div class="col-md-2"><select name="grade" id="grade" required="required" onchange="loadSubjectList();">
                <option>Select Grade</option>
                </select>
                
                </div>
                </div>      
            </div>
            <!--table-form-end-here-->
           </div>
   
            
            <div class="clearfix"></div>
            
             <div class="row">
            <table>
            <thead>
            <tr>
            <th>S. No.</th>
            <th>Subject</th>
            <th>Quarter</th>
            <th>Half Year</th>
            <th>Annual</th>
            <th>Save Update</th>
            </tr>
            </thead>
            <tbody  id="priceList">
            
            </tbody>
            </table>
            
             </div>
           <!-- need to add price list here -->
          	
            
            </div>
            <!--Left-panel-end-here-->
        
        
        
        
           
            
    
    </div>
    </div>
    
	     <script src="${pageContext.request.contextPath}/resources/js/bootstrap.js"></script>
	
	<script>
	function loadSubjectList()
	{
		var url='${pageContext.request.contextPath}/ajax/rest/getSubjectByBoardAndGrade';
		var boardId=$('#board').val();
		var gradeId=$('#grade').val();
		if(boardId!='')
			{
				if(gradeId!=undefined && gradeId != '')
					{
					 $.post(url+"/"+boardId+"/"+gradeId,
							 function(data, status){
					       // alert("Data: " + data + "\nStatus: " + status);
					        $('#priceList').empty();
					        var count;
					        for(s in data)
					        	{
					        	//
					        	count=(parseInt(s)+1);
					        	console.log(data[s].name);
					        	$('#priceList').append('<tr><td>'+
					        			count+'</td><td>'+
					        			data[s].name+'</td><td><input type="text" maxlength="10"  id="quarter_'+
					        			data[s].id+'"/></td><td><input type="text" maxlength="10" id="half_'+
					        			data[s].id+'"/></td><td><input type="text" maxlength="10" id="annual_'+
					        			data[s].id+'" /></td><td><button onclick="savePrice('+data[s].id+')">Save Update</button></td></tr>');
					        		}
					        if(data.length>0)
					        	{
					        	$('#priceList').append('<tr><td>'+
					        			(1+count)+'</td><td>All Subjects</td><td><input type="text" id="quarter_-1"/></td><td><input type="text" id="half_-1"/></td><td><input type="text" id="annual_-1"/></td><td><button onclick="savePrice('+-1+')">Save Update</button></td></tr>');
					        	}
					        else
					        	$('#priceList').append('<tr>No subjects found in selected board</tr>');
					        	
					       // alert(data.length);
					        
					        
					    });
					}
				else
					alert('Please select grade');
			}
		else
			alert('Please select board');
		
	}
	
	function savePrice(id)
	{
		var boardId=$('#board').val();
		var gradeId=$('#grade').val();
		var price1=$('#quarter_'+id).val();
		var price2=$('#half_'+id).val();
		var price3=$('#annual_'+id).val();
		
		console.log(price1.length);
				if(price1=='')
					{
						alert("Please enter price ");
						console.log("----------->>"+$('#quarter_'+id).text());
						$('#quarter_'+id).focus().css("border","red solid 1px");
						return false;
					}else if(price1.match(/[^0-9\.]/g))
						{
							alert("Please enter numeric value");
							console.log("----------->>"+$('#quarter_'+id).text());
							$('#quarter_'+id).focus().css("border","red solid 1px");
							return false;
						}/* else if(price1.length>5)
							{
								alert("Please enter maximum 9999 ");
								console.log("----------->>"+$('#quarter_'+id).text());
								$('#quarter_'+id).focus().css("border","red solid 1px");
								return false;
							} */
				
				
				
				if(price2=='')
				{
					alert("Please enter price ");
					$('#quarter_'+id).removeAttr( 'style' );
					$('#half_'+id).focus().css("border","red solid 1px");
					return false;
				}else if(price2.match(/[^0-9\.]/g))
					{
						alert("Please enter numeric value");
						$('#quarter_'+id).removeAttr( 'style' );
						$('#half_'+id).focus().css("border","red solid 1px");
						return false;
					}/* else if(price2.length>5)
						{
							alert("Please enter maximum 9999 amount ");
							$('#quarter_'+id).removeAttr( 'style' );
							$('#half_'+id).focus().css("border","red solid 1px");
							return false;
						} */
				
				
				if(price3=='')
				{
					alert("Please enter price ");
					$('#quarter_'+id).removeAttr( 'style' );
					$('#half_'+id).removeAttr( 'style' );
					$('#annual_'+id).focus().css("border","red solid 1px");
					return false;
				}else if(price3.match(/[^0-9\.]/g))
					{
						alert("Please enter numeric value");
						$('#quarter_'+id).removeAttr( 'style' );
						$('#half_'+id).removeAttr( 'style' );
						$('#annual_'+id).focus().css("border","red solid 1px");
						return false;
					}/* else if(price3.length>5)
						{
							alert("Please enter maximum 9999 ");
							$('#quarter_'+id).removeAttr( 'style' );
							$('#half_'+id).removeAttr( 'style' );
							$('#annual_'+id).focus().css("border","red solid 1px");
							return false;
						} */
				
				
			
		var url='${pageContext.request.contextPath}/admin/savePrice';
	
		console.log(id);
		if(boardId!=''){
			if(gradeId!=undefined && gradeId != ''){
				console.log("Board: "+ boardId+", gradeId: "+ gradeId+", price1: "+ price1+", price2: "+price2+", price3: "+ price3+", SubjectId:"+id);
				$.post(url,{
					 boardId:boardId,
					 gradeId:gradeId,
					 subjectId: id,
					 price1:price1,
					 price2:price2,
					 price3:price3
					 
				 },
						 function(data, status){
				        if(data=='success')
				        	alert('Information successfully updated');
				        else
				        	alert(data);
				    }); 
			
			
			
			
			}
			else
				alert('Please select grade');
		}
		else
			alert('Please select board');
	}
	</script>