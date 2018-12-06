<html>
	<head>
		<jsp:include page="../globalPage.jsp" />		<!-- All js, css, data-table api are defined in globalPage.js -->
		<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
		
		<script>
			$(document).ready(function()
			{
				generateDataTable();
				$(".boardCls").html($("#boardId option:selected").text());
				
				selectOnlyOneCheckbox();
			});
			
			function selectOnlyOneCheckbox()
			{
				$('.tableCheckbox').on('change', function() 
				{
				    $('.tableCheckbox').not(this).prop('checked', false);  
				});
			}
			
			function generateDataTable()
			{
				//$('#reportTableID').dataTable().fnDestroy();			// Use it when you want to reinitialize data table
				$('#dataTableId').dataTable();
			}
			
			function saveBoard()
			{
				var boardName = $('#boardName').val();
				
				if(boardName == '')
				{
					alert("Please type board");
				}
				else
				{
					$.ajax
					({
						url:"${pageContext.request.contextPath}/admin/addBoardTocStandard.htm",
						type:"POST",
						data: {"boardName": boardName},
						success:function(data)
						{
							if(data == 'noBoard')
								alert("Please type board");
							else if(data == 'boardNotAvailable')
								alert("Board already exists");
							else if(data.indexOf('<html') == -1)		// Since error page will have <html> tag
							{
								$(".close").trigger('click');			// To hide the pop-up background
								alert("Board successfully created");
								location.reload();
							}
						},
						error:function()
						{
							alert("Something went wrong");
						}
					});
				}
			}
			
			function removeBoard()
			{
				var checkedValues = $('.tableCheckbox:checked').map(function() 
									{
									    return this.value;
									}).get();
		
				if(checkedValues == '')
				{
					alert("Please select a board");
				}
				else
				{
					if(confirm("Are you sure to delete this board?") == false)
						return false;
					
					$.ajax
					({
						url:"${pageContext.request.contextPath}/admin/removeBoardTocStandard.htm",
						type:"POST",
						data: {"boardIdString": checkedValues.toString()},
						success:function(data)
						{
							if(data == 'noBoard')
								alert("Please select board");
							else if(data.indexOf('<html') == -1)			// Since error page will have <html> tag
							{
								alert("successfully deleted");
								location.reload();
							}
						},
						error:function()
						{
							alert("Something went wrong");
						}
					});
				}
			}
			
			function editBoard()
			{
				var oldName=$('#txtOldBoard').val();
				var newName=$('#renameBoard').val();
				var id=$('#boardId').val();
				
				console.log("Old Name:"+oldName+"New Name:"+newName+"id"+id);
				if(newName!='' && newName!=oldName)
				{
				
			 $.ajax
			({
				url:"${pageContext.request.contextPath}/admin/updateBoard.htm",
				type:"POST",
				data: {id: id, oldName: oldName, newName: newName},
				success:function(data)
				{
					
					$('#editBoardClose').trigger("click");
					alert("Board updated");
					
					location.reload();
					
				},
				error:function()
				{
					alert("Something went wrong");
				}
			}); 
				}
			else
			alert("Please provide board name");
		}
			function showEdit(id, boardName)
			{
				console.log("Show editdescription for: "+id+":"+boardName);
				$('#txtOldBoard').val(boardName);
				$('#renameBoard').val('');
				$('#boardId').val(id);
			}
			
			
			
		</script>
	</head>
	<body>
		<h2 class="text-center">Board Details</h2>
		<div style="width:90%;margin:0px auto;">
			<div>
				<button class="btn btn-success " data-toggle="modal" data-target="#myModal1" >Add New</button>
				<!-- <button class="btn btn-primary" data-toggle="modal" data-target="#myModal2">Edit</button> -->
				<button class="btn btn-danger" data-toggle="modal" data-target="#myModal3" onclick="removeBoard()">Remove</button><br><br>
				
				<table style="border: 3px;background: rgb(243, 244, 248);width:100%">		<!-- Use it because it's helpful -->
					<tr style="width:100%;height:100%">
						<td>
							<table id="dataTableId" class="display" style="width:100%">
								<thead>
									<tr class="dataTab">
										<th>Sr. No.</th>
										<th>Board</th>
										<th>Action</th>
										
									</tr>
								</thead>
								<tbody>
									<c:set var="count" value="1"></c:set>
									<c:forEach var="board" items="${boardList}">
										<tr>
											<td><input class="tableCheckbox" type="checkbox" value="${board.id}" name="boardId"/> ${count}</td>
											<td>${board.name}</td>
											<td> <a onclick="script:$('#txtOldBoard').val(boardName); showEdit(${board.id},'${board.name}')"  data-toggle="modal" data-target="#myModal2"  href="javascript:void(0)" >Edit</a> </td>
											<%-- <td> <a onclick="contentSyncing(${board.id})"  href="javascript:void(0)" >Syncing</a> </td> --%>
										</tr>
										
										<c:set var="count" value="${count+1}"></c:set>
									</c:forEach>
								</tbody>
							</table>
						</td>
					</tr>
				</table>
			</div>
			
			<div class="modal fade" id="myModal1" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
				<div class="modal-dialog" role="document">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal" aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
							<h4 class="modal-title" id="myModalLabel">Board Detail</h4>
						</div>
						<div class="modal-body">
							<div class="col-md-12 col-sm-12 col-xs-12">
								<div class="row">
									<input type="text" id="boardName" placeholder="Board Name" required="required" >
								</div>
							</div>
						</div>
						<div class="modal-footer">
							<button class="btn btn-primary" onclick = "saveBoard()">Add Board</button>
						</div>
					</div>
				</div>
			</div>
			
			
			<div class="modal fade" id="myModal2" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close" id="editBoardClose">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">Edit Board Name</h4>
				</div>
				<div class="modal-body">
					<div class="col-md-12 col-sm-12 col-xs-12">
						<div class="row">
							<input type="text" id="txtOldBoard" placeholder="Board Name" required="required" >
							<input type="text" id="renameBoard" placeholder="New Name" required="required" >
							<input type="hidden" name="boardId" id="boardId" />
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button class="btn btn-primary" onclick = "editBoard()">Update</button>
				</div>
			</div>
		</div>
	</div>
		</div>
	</body>
</html>