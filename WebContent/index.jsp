<!DOCTYPE html>
<html lang="en">
	
	<jsp:include page="WEB-INF/view/components/metahead.jsp" />

  	<body>

    	<div class="navbar navbar-inverse navbar-fixed-top">
      		<div class="navbar-inner">
        		<div class="container">
          			<span class="brand">TEDwsms</span>
          			<div class="nav-collapse collapse">
            			<ul class="nav">
							<li class="active"><a href="${pageContext.request.contextPath}">Home</a></li>
							<li><a href="${pageContext.request.contextPath}/about">About</a></li>
							<li><a href="${pageContext.request.contextPath}/contact">Contact</a></li>
           	 			</ul>
            			<form class="navbar-form pull-right" method="post" action=<%= response.encodeURL(request.getContextPath() + "/login")%>>
              				<input class="span2" type="text" placeholder="Username" name="username">
              				<input class="span2" type="password" placeholder="Password" name="password">
              				<button type="submit" class="btn">Sign in</button>
            			</form>
          			</div><!--/.nav-collapse -->
        		</div>
      		</div>
    	</div>

		<div id="wrap">
			
			<div class="space-top"></div>
			
			<div class="container">

		  		<!-- Main hero unit for a primary marketing message or call to action -->
		  		<div class="hero-unit">
					<h2>Warehouse Management Software</h2>
					<p>The Better way to monitor your business and be always on Time and on Budget. <i>For small businesses and freelancers</i></p>
					<p><a href="<%= response.encodeURL("register") %>" class="btn btn-primary btn-large">Sign up today &raquo;</a></p>
		  		</div>

		  		<!-- Example row of columns -->
		  		<div class="row">
					<div class="span4">
				 		<h3>Easy to Use</h3>
			  			<p>TEDwsms is simple and intuitive, with a user friendly environment and clear options.</p>
					</div>
					<div class="span4">
			 			<h3>Work Anywhere</h3>
			  			<p>Hello online management, goodbye desk. With TEDWsms, your business is accessible everywhere via the internet.</p>
		   			</div>
					<div class="span4">
			  			<h3>Save Time</h3>
		  				<p>TEDwsms is built for small business owners to get organized and be more efficient. You'ill be tracking time, quantities, expenses and all transactions in no time.</p>
					</div>
		 		</div>
		 	
		 	<div id="push"></div>
		
		</div> <!-- /container -->
	</div> <!-- /wrap -->
	<jsp:include page="WEB-INF/view/components/footer.jsp" />	
	<jsp:include page="WEB-INF/view/components/bootstrapjs.jsp" />	
  </body>
</html>
