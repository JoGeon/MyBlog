<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/page/commons/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link type="text/css" rel="stylesheet" href="${ctx}/css/common.css">
<link type="text/css" rel="stylesheet" href="${ctx}/css/background.css" />
<title>登录</title>
</head>
<body class="loginPage">
	<div class="container">
		<div class="login">
			<h1>Login</h1>
			<form method="post" action="${ctx}/background/admin">
                <!--p class="errMessage">
                    <s:if test="#error != null">
                        <s:property value="#error"/>
                    </s:if>
                </p-->
				<p>
					<input type="text" name="author.authorname" value="" placeholder="Username or Email"/>
				</p>
				<p>
					<input type="password" name="author.password" value="" placeholder="Password">
				</p>
				<p class="remember_me">
					<label> 
						<label> 
						<input type="checkbox" name="remember_me" id="remember_me" /> Remember me on this computer
						</label>
					</label>
				</p>
				<p class="submit">
					<input type="submit" value="Login"/>
				</p>
			</form>
		</div>
		<div class="login-help">
			<p>
				Forgot your password? <a href="#">Click here to reset it</a>.
			</p>
		</div>
	</div>
</body>
<s:debug/>
</html>