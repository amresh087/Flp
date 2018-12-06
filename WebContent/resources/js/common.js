
function getGradeList(destinatoinId, id, url, emptyMsg)
{
	// url = url+"/"+id;
	setDropdownList(destinatoinId, id, url, emptyMsg, false);
}

function getSubjectList(destinatoinId, gradeId, url, emptyMsg)
{
	var boardId = $('#board').val();
	url = url + "/" + boardId;
	setDropdownList(destinatoinId, gradeId, url, emptyMsg, true);
}

function setDropdownList(destinatoinId, id, url, emptyMsg, isAllSubject)
{
	$(destinatoinId).empty();
	$(destinatoinId).append('<option value="">' + emptyMsg + '</option>');
	if(id != null && id != "")
	{
		url = url + "/" + id;
		$.post(url, function(data)
		{
			if(data != null && data.length > 0)
			{
				for (var i = 0; i < data.length; i++)
				{
					$(destinatoinId).append('<option value="' + data[i].id + '">' + data[i].name + '</option>');
				}
			}
			if(isAllSubject == true)
			{
				$(destinatoinId).append('<option value="-1">All Subjects</option>');
			}
		}, "json");
	}
	else
	{
		$('#priceList').hide();
		$('#buyNowRight').hide();
	}
}

function setSectionDropdownList(destinationId, id, url, emptyMsg)
{
	$(destinationId).empty();
	$(destinationId).append('<option value="">' + emptyMsg + '</option>');
	if(id != null && id != "")
	{
		url = url + "/" + id;
		$.post(url, function(data)
		{
			if(data != null && data.length > 0)
			{
				for (var i = 0; i < data.length; i++)
				{
					$(destinationId).append('<option value="' + data[i].id + '" gradeid="' + data[i].grade.id + '">' + data[i].grade.name + '</option>');
				}
			}
		}, "json");
	}
};

function getSubjectPrices(url)
{
	var boardId = $('#board').val();
	var gradeId = $('#grade').val();
	var subjectId = $('#subject').val();
	
	var boardName=$("#board option:selected").text();
	var gradeName = $('#grade option:selected').text();

	$('#buyNowRight').hide();
	if(subjectId != null && subjectId != "")
	{
		$.post(url + boardId + "/" + gradeId + "/" + subjectId+"/"+boardName+"/"+gradeName, function(data)
		{
			$('#priceList').html(data);
			$('#priceList').show();
			
		});
	}
	else
	{
		$('#priceList').hide();
		$('#buyNowRight').hide();
	}
}

function updatePrice(boardName, gradeName, subjectName, price, planType)
{
	$('#byNowDetails').html(boardName + " " + gradeName + " " + subjectName + " " + planType + "<br />Plan is Selected");
	$('#planPrice').html('Rs. ' + price);
	$('#payable_amount').val(price);
}

function parentChildLinking(url)
{
	var email = $("input#searchEMailId").val();
	$.post(url + email, function(data)
	{
	}, "json");
}

$(document).ready(function()
{
	$("#forgotId").on("click", function()
	{
		$("#forgotPwdId").show();
		$("#loginFormId").hide();
	})

	$("#loginId, #cancelId").on("click", function()
	{
		$("#forgotPwdId").hide();
		$("#loginFormId").show();
		
	});
	
	// code for send mail for reset password
	$("#resetPwdbtn").on("click", function()
	{
		var email = $("#emailAddress").val();
		$("#forgotErrMsg").empty();
		var contextPath = $("#contextPathId").val();
		$("#errEmailMsg").hide();
		var pattern = new RegExp(/^((([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+(\.([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+)*)|((\x22)((((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(([\x01-\x08\x0b\x0c\x0e-\x1f\x7f]|\x21|[\x23-\x5b]|[\x5d-\x7e]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(\\([\x01-\x09\x0b\x0c\x0d-\x7f]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]))))*(((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(\x22)))@((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.?$/i);
		if(!pattern.test(email))
		{
			$("#errEmailMsg").show();
			return false;
		}
		$.post(contextPath + "/ajax/rest/forgotPassword/" + email, function(data)
		{
			if(data == true || data == 'true')
			{
				$("#forgotErrMsg").html('Please click on a link send to your EmailId to reset password.');
				$("#forgotErrMsg").css('color', 'green');
				$("#emailAddress").val('');
			}
			else
			{
				$("#forgotErrMsg").html("Email-Id is not exists.");
				$("#forgotErrMsg").css('color', 'red');
			}
		}, 'json');
		
	});
	// end
	
	// code for reset password
	$("#resetPasswordId").on("click", function()
	{
		$("#resetPwdId").empty();
		var userId = $("#forgotPwduserId").val();
		var password = $("#firstPassword").val();
		var confirmPassword = $("#confirmPassword").val();
		var contextPath = $("#contextPathId").val();
		if(password.trim().length < 6)
		{
			$("#resetPwdId").html('Password should be greater than 5.');
			$("#resetPwdId").css('color', 'red');
			return false;
		}
		else if(password != confirmPassword)
		{
			$("#resetPwdId").html('Password should be match.');
			$("#resetPwdId").css('color', 'red');
			return false;
		}
		$.post(contextPath + "/ajax/rest/resetPassword/" + password + "/" + confirmPassword + "/" + userId, function(data)
		{
			
			if(data == true || data == 'true')
			{
				$("#resetPwdId").html('Your password reset successfully.Please login with new password.');
				$("#resetPwdId").css('color', 'green');
				$("#firstPassword").val('');
				$("#confirmPassword").val('');
			}
			else
			{
				$("#resetPwdId").html('Password should be match.');
				$("#resetPwdId").css('color', 'red');
			}
		}, 'json');
		
	});
	// end
	
	$(".studentParentCls").on("click", function()
	{
		var userTypeId = parseFloat($(this).val());
		if(userTypeId == 3)
		{
			$("#boardGradeId").hide();
			$('#studentId').removeAttr('checked');
			$("#boardId").removeAttr('required');
			$("#gradeId").removeAttr('required');
		}
		else
		{
			$("#boardGradeId").show();
			$('#parentId').removeAttr('checked');
			$("#boardId").attr('required', 'required');
			$("#gradeId").attr('required', 'required');
		}
	});
	
	// code for user search
	$("#userSearchId").on("keyup", function()
	{
		var searchContent = $(this).val();
		$("#showAllUserId").empty();
		$("#showAllUserId").hide();
		if(searchContent != null && searchContent != "")
		{
			var userType = "-1";
			var contextPath = $("#contextPathId").val();
			$.post(contextPath + "/ajax/rest/searchUser/" + userType + "/" + searchContent, function(data)
			{
				var ele = '<ul class="list-unstyled" id="searchUserLstId">';
				if(data != null && data.length > 0)
				{
					var maxLen = parseFloat(data.length);
					var actualLen = maxLen;
					if(maxLen > 6)
					{
						maxLen = 6;
					}
					for (var i = 0; i < maxLen; i++)
					{
						ele = ele + '<li><img src="' + contextPath + '/resources/images/dash_search_img.jpg" alt=""/>' + data[i].firstName + ',';
						if(data[i].address != null)
						{
							ele = ele + data[i].address + ",";
						}
						ele = ele + data[i].emailId + '<span class="pull-right add-people"><a href="#">' + '<img src="' + contextPath + '/resources/images/dash-add-peaple.png" alt=""/></a></span><div class="clr"></div></li>';
					}
					if(actualLen > 6)
					{
						ele = ele + ' <li class=" view-all"><a href="#">View All</a><div class="clr"></div></li>';
					}
					
				}
				else if(data == null)
				{
					ele = ele + '<li>no record found.....<div class="clr"></div></li>';
				}
				ele = ele + '</ul>';
				$("#showAllUserId").append(ele);
				$("#showAllUserId").show();
			}, "json");
		}
	})

	var hiddenPopCls1 = parseFloat($(".hiddenPopCls1").val());
	if(hiddenPopCls1 == 5 || hiddenPopCls1 == '5')
	{
		$("#resetPwdIds").trigger("click");
	}
	
});

function alphaOnly(event){
	 var key = event.keyCode;
	
	  return ((key >= 65 && key <= 90) || key == 8||key==32||key==46||key==37||key==39 ||key==9);
	
}
