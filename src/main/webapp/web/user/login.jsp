<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/web/header.jsp"%>
<title>登录</title>
<style>
.window {
	width: 400px;
	position: absolute;
	margin-left: -200px;
	margin-top: -80px;
	top: 50%;
	left: 50%;
	display: block;
	z-index: 2000;
	background: #fff;
	padding: 20 0;
    background-color: rgba(0,0,0,0.5);

}
#b1{
				background-image: url(${pageContext.request.contextPath}/web/common/img/nike.jpg);
				background-size:1380px 700px;
			}

.text{text-align:center;
		top:30%;
		}
</style>
</head>
<body id="b1">
<div class="text"><h1>后台管理系统</h1></div>
	<div class="window">
		<fieldset class="layui-elem-field" style="margin: 10px;">
			<legend>登录</legend>
			<div class="layui-field-box">
				<form class="layui-form layui-form-pane" method="post">
					<div class="layui-form-item">
						<label class="layui-form-label">账号：</label>
						<div class="layui-input-inline">
							<input type="text" name="userCode" required 
							    lay-verify="required" placeholder="请输入账号"
								autocomplete="off" class="layui-input">
						</div>
					</div>
					<div class="layui-form-item">
						<label class="layui-form-label">密码：</label>
						<div class="layui-input-inline">
							<input type="password" name="pass" required 
							    lay-verify="required" placeholder="请输入密码"
								autocomplete="off" class="layui-input">
						</div>
					</div>
					<div class="layui-form-item">
						<label class="layui-form-label"> 
						<img src="<%=request.getContextPath()%>/imageServletController/imageServlet" 
						onclick="this.src='<%=request.getContextPath()%>/imageServletController/imageServlet?'+Math.random();"/>
						</label>
						<div class="layui-input-inline">
							<input type="text" name="authcode" required lay-verify="required"
								placeholder="请输入验证码" autocomplete="off" class="layui-input"/><br>
						</div>
					</div>
					<div class="layui-form-item">
						<div class="layui-form-item">
							<!--<label class="layui-form-label"></label> -->
							<div class="layui-input-inline">
								<input type="button" class="layui-btn layui-btn-normal " value="登录" 
								lay-submit lay-filter="login"/> 
								<input type="reset" class="layui-btn layui-btn-primary" value="重置"/>
							</div>
							<input type="button" class="layui-btn layui-btn-normal" value="注册" onclick="goJspReg()"/>
						</div>
					</div>  
					<input type="hidden" name="action" value="login"/>
				</form>
			</div>
		</fieldset>
	</div>
</body>
<script type="text/javascript">
// 		form.on('submit(login)', function(data) {
// 			// console.log(data.field);
// 			$.ajax({
// 				url : con.app + '/AdminServlet',
// 				data : data.field,
// 				dataType : 'text',
// 				type : 'post',
// 				success : function(data) {
	
// 					if (data == 0) {
// 						location.href = con.web + "/web/page/main.jsp";
// 					} else if (data == 1) {
// 						layer.msg('账号不存在');
// 					} else {
// 						layer.msg('密码错误');
// 					}
// 				}
// 			})
// 		})
	formSubmit('/user/login','submit(login)','json'
		,function(data){
		console.log(data)
		if(data.a == 0){
		location.href = con.app + "/web/main.jsp";
		}else if(data.a == 1){
		layer.msg('账号不存在或者密码错误');
		} else if(data.a == 2){
		layer.msg('密码错误');
		}
		else if(data.a == 3){
		layer.msg('非管理员用户禁止登陆！');
		}else{
		layer.msg('验证码错误');
		}
	});
</script>
</html>