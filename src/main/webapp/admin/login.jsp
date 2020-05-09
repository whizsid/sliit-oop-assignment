<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
	<title>Admin - Login Page</title>
   <!--Made with love by Mutiullah Samim -->
   
	<!--Bootsrap 4 CDN-->
	<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/bootstrap.min.css" >
    
    <!--Fontawesome CDN-->
	<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/fontawesome.css" >

	<!--Custom styles-->
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/css/admin/login.css">
</head>
<body>
<div class="container">
	<div class="d-flex justify-content-center h-100">
		<div class="card">
			<div class="card-header">
                <h3>Sign In</h3>
			</div>
			<div class="card-body">
				<form method="POST" >
					<div class="input-group form-group">
						<div class="input-group-prepend">
							<span class="input-group-text"><i class="fas fa-user"></i></span>
						</div>
						<input type="text" class="form-control" value="${email}" name="email" placeholder="Email" >
						
					</div>
					<div class="input-group form-group">
						<div class="input-group-prepend">
							<span class="input-group-text"><i class="fas fa-key"></i></span>
						</div>
						<input placeholder="Password" class="form-control" type="password"  name="password">
                    </div>
                    <c:if test="${error!=null}">
                        <div class="alert alert-danger">
                            <span>${error}</span>
                        </div>
                    </c:if>
					<div class="form-group">
						<input type="submit" value="Login" class="btn float-right login_btn">
					</div>
				</form>
			</div>
		</div>
	</div>
</div>
</body>
</html>