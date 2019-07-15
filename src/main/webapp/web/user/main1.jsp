<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@ include file="/web/header.jsp"%>
  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
</head>
<body>
      
		<div class="layui-layout layui-layout-admin">
		<div class="layui-header">
			<div class="layui-logo">后台管理系统</div>
			<ul class="layui-nav layui-layout-left"></ul>
			<ul class="layui-nav layui-layout-right">
				<li class="layui-nav-item">
				    <a href="javascript:;"> 
				        <img src="<%=path%>/img/timg.jpg"
				        class="layui-nav-img"/>用户[${user.name}]
				    </a>
					<dl class="layui-nav-child">
						<dd>
							<a href="javascript:openUser()">基本资料</a>
						</dd>
						<dd>
							<a href="javascript:openPass()">修改密码</a>
						</dd>
					</dl>
				</li>
				<li class="layui-nav-item">
				    <a href="javascript:logout()">注销</a>
				</li>
			</ul>
		</div>

		<div class="layui-side layui-bg-black">
			<div class="layui-side-scroll">
				<!-- 左侧导航区域（可配合layui已有的垂直导航） -->
				<ul class="layui-nav layui-nav-tree" >
				<c:forEach items="${menus}" var="menu">

						<li class="layui-nav-item layui-nav-itemed"><a
							href="javascript:;">${menu.menuName}</a>
							<dl class="layui-nav-child">
							<c:forEach items="${menu.child}" var="men">
								<dd>
									<a href="javascript:;" data-url="${men.menuUrl}"
										class="site-demo-active">${men.menuName}</a>
								</dd>
							</c:forEach>							
							</dl>
							</li>
					</c:forEach>
				</ul>
			</div>
		</div>
		<div class="layui-body"><!-- 内容主体区域 -->
			<iframe name="rightframe" width="99%" height="98%" src="#"></iframe>
		</div>
		<div class="layui-footer">© JAVA.com - 底部固定区域</div>
	</div>
<script type="text/javascript" src="<%=path%>/layui/layui.all.js"></script>
<script>

	$('.site-demo-active').click(function() {
		window.open(con.app + $(this).data('url'), "rightframe");
	});
	function openURL(url){
		window.open(con.app + url, "rightframe");
	}
	$('.site-demo-active:first').click();
	
	function openUser(){
		openLayer('/web/user/upd.jsp?usercode=${user.userCode}'
			,function(){location.reload();})
	}
	function openPass(){
        openLayer('/web/user/userpass.jsp?userCode=${user.userCode}'
       		,function(){})
    }
	function logout(){
		openConfirm(function(index) {
			layer.close(index);
			toJsp("/user/logout");
		}, '注销当前用户?');
	}
</script>
</body>
</html>