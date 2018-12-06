<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<script type="text/javascript"><!--
        function photoStartCallback1() {
            var message = $('#messagePhotoPic1');
            message.html('');
            var file = $('#filePic').val();
            if(file == '') {
                message.html('Please select an image to upload');
                return false;
            }
            var dots = file.split('.');
            var fileExt = '.' + dots[dots.length - 1].toLowerCase();
            if(('.' + ['gif', 'jpg', 'png', 'jpeg'].join('.')).indexOf(fileExt) == -1) {
                message.html(fileExt + ' file extension is not supported');
                return false;
            }
            return true;
        }
       
        function photoCompleteCallback1(data) {
            var message = $('#messagePhotoPic1');
            message.attr('class', 'textgreen');
            message.html('Successfully Uploaded');
            $('#profileImg').attr('src', data);
        }
    // --></script>


<form action="${pageContext.request.contextPath}/user/uploadImage.htm"  method="post" id="picUpload"
				class="form-horizontal" role="form" enctype="multipart/form-data" onsubmit="return AIM.submit(this, {'onStart': photoStartCallback1, 'onComplete': photoCompleteCallback1})">
				<div class="clearfix"></div>
				<div class="modal-body">
					<div class="col-md-12 col-sm-12 col-xs-12">
						<div class="row">
							Your image: <input type="file" name="file" id="filePic">
						</div>
					</div>

				<!-- 	<input type="hidden" name="userType" value="7"> -->
					<div class="clearfix"></div>
					<input type="submit" class="btn btn-primary btn-success" id="upload" value="Upload"/>
				
				</div>
				
			</form>
			<div class="messagePhotoPic1" id="messagePhotoPic1"></div>