<%@ include file="header.jsp"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div style="color: green;" id="succMsgId"><c:if test="${! empty successMsg }">${ successMsg}
<c:remove var="successMsg" scope="session"/>
</c:if></div>
<form:form
	action="${pageContext.request.contextPath}/user/editProfile.htm"
	commandName="user" method="post" id="editProfileId">

User Name<input type="text" name="firstName" value="${user.firstName }" placeholder="User Name" required/>
<br/>

Address<input type="text" name="address" value="${user.address }" placeholder="Address"/>
<br/>

city<input type="text" name="city" value="${user.city }" placeholder="City"/>
<br/>

Contact No<input type="text" name="mobileNo" value="${user.mobileNo }" placeholder="Mobile number" required/>
<br/>

State<input type="text" name="state" value="${user.state }" placeholder="State"/>
<br/>
<input type="submit" value="Update"/>

</form:form>

<form:form action="${pageContext.request.contextPath}/user/uploadImage.htm" enctype="multipart/form-data" method="post">
      <input type="file" name="file">
      <input type="submit" class="btn btn-primary" />Upload
      
    
      </form:form>
<script type="text/javascript">
$(document).ready(function(){
	var id=$("#succMsgId").html();
	if(id !=null && id!=""){
		setTimeout(function(){
$("#succMsgId").hide();

			}, 5000);
	}
	
});
</script>