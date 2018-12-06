<script>
	(function ($) 	// Self executing function
	{
		setInterval(function()
		{
			$.ajax
			({
				url:"${pageContext.request.contextPath}/keepSesssionAlive.htm",
				type:"POST",
				//data: formData,
				success:function(data)
				{
					console.log(data);					
				},
				error:function()
				{
					alert("Session timeout, please re-login");
				}
			});
		}, 60000);	 // After every 60 seconds, it will execute the code
	})(jQuery);
</script>

This Is a footer
