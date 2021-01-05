<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/resource/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/resource/dist/css/AdminLTE.min.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/temp/login/logincss/myLogin.css">
<title>登录</title>
</head>
<body class="hold-transition login-page">
	<div class="login-box">
		<div class="login-logo">
			<h1>
				学生课程管理系统<b>Admin</b>student
			</h1>
		</div>
		<!-- /.login-logo -->
		<div class="login-box-body">
			<div class="form-group has-feedback">
				<input type="text" class="form-control" placeholder="username"
					id="userName"> <span
					class="glyphicon glyphicon-envelope form-control-feedback"></span>
			</div>
			<div class="form-group has-feedback">
				<input type="password" class="form-control" placeholder="Password"
					id="password"> <span
					class="glyphicon glyphicon-lock form-control-feedback"></span>
			</div>
			<div class="col-xs-12">
				<label id="error" style="color: red"></label>
			</div>
			<div class="row">
				<div class="col-xs-6">
					<button type="button" id="loginBtn"
						class="btn btn-primary btn-block btn-flat">登录</button>
				</div>
				<!-- /.col -->
				<div class="col-xs-6">
					<button type="reset" class="btn btn-primary btn-block btn-flat">重置</button>
				</div>
				<!-- /.col -->
			</div>
		</div>
		<!-- /.login-box-body -->
	</div>
	<!-- /.login-box -->
	<!-- jQuery 2.1.4 -->
	<script
		src="<%=request.getContextPath()%>/resource/jQuery/jQuery-2.1.4.min.js"></script>
	<!-- Bootstrap 3.3.5 -->
	<script
		src="<%=request.getContextPath()%>/resource/bootstrap/js/bootstrap.min.js"></script>
	<script type="text/javascript">
		$('#loginBtn').on('click',function(){
			var userName=$('#userName').val();
			var password=$('#password').val();
			var url="<%=request.getContextPath()%>/login"
			var data = {
				"userName" : userName,
				"password" : password
			}
			$.ajax({
				type : "post",
				url : url,
				dataType : "json",
				data:data,
				success : function(data) {
                    if(data.code==1){
                    	window.location ='<%=request.getContextPath()%>/temp/student/student.jsp';
					} else {
						$('#error').html('用户名或密码错误');
					}
                }
		   })
	})
	</script>
</body>
</html>