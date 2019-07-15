<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>login</title>
<link href="http://fonts.googleapis.com/css?family=Lato:100,300,400,700"
	media="all" rel="stylesheet" type="text/css" />
<link href="/test3/web/stylesheets/bootstrap.min.css" media="all" rel="stylesheet"
	type="text/css" />
<link href="/test3/web/stylesheets/font-awesome.css" media="all" rel="stylesheet"
	type="text/css" />
<link href="/test3/web/stylesheets/se7en-font.css" media="all" rel="stylesheet"
	type="text/css" />
<link href="/test3/web/stylesheets/style.css" media="all" rel="stylesheet"
	type="text/css" />
<script src="http://code.jquery.com/jquery-1.10.2.min.js"
	type="text/javascript"></script>
<script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"
	type="text/javascript"></script>
<script src="/test3/web/javascripts/bootstrap.min.js" type="text/javascript"></script>
<script src="/test3/web/javascripts/modernizr.custom.js" type="text/javascript"></script>
<script src="/test3/web/javascripts/main.js" type="text/javascript"></script>
</head>
<body class="login2">
	<!-- Login Screen -->
	<div class="login-wrapper">
		<a href="index.html"><img width="100" height="30"
			src="/test3/web/common/img/logo-login%402x.png" /></a>
		<form action="/test3/user/login1" method="post">
			<div class="form-group">
				<div class="input-group">
					<span class="input-group-addon"><i class="icon-envelope"></i></span><input
						class="form-control" placeholder="Username or Email" type="text" name="userCode">
				</div>
			</div>
			<div class="form-group">
				<div class="input-group">
					<span class="input-group-addon"><i class="icon-lock"></i></span><input
						class="form-control" placeholder="Password" type="text"
						name="pass">
				</div>
			</div>
			<a class="pull-right" href="#">Forgot password?</a>
			<div class="text-left">
				<label class="checkbox"><input type="checkbox"><span>Keep
						me logged in</span></label>
			</div>
			<input class="btn btn-lg btn-primary btn-block" type="submit"
				value="Login">
			<div class="social-login clearfix">
				<a class="btn btn-primary pull-left facebook" href="index-2.html"><i
					class="icon-facebook"></i>Login with facebook</a><a
					class="btn btn-primary pull-right twitter" href="index-2.html"><i
					class="icon-twitter"></i>Login with twitter</a>
			</div>
		</form>
		<p>Don't have an account yet?</p>
		<a class="btn btn-default-outline btn-block" href="signup2.html">Sign
			up now</a>
	</div>
	<!-- End Login Screen -->
</body>
</html>