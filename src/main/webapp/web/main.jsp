<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/web/header.jsp"%>
<title>主页</title>
</head>
<body class="layui-layout-body">
	<div class="layui-layout layui-layout-admin">
		<div class="layui-header">
			<div class="layui-logo">后台管理系统</div>
			<ul class="layui-nav layui-layout-left"></ul>
			<ul class="layui-nav layui-layout-right">
				<li class="layui-nav-item"><a href="javascript:;"> <img
						src="<%=path%>/img/timg.jpg" class="layui-nav-img" />用户[${user.name}]
				</a>
					<dl class="layui-nav-child">
						<dd>
							<a href="javascript:openUser()">基本资料</a>
						</dd>
						<dd>
							<a href="javascript:openPass()">修改密码</a>
						</dd>
					</dl></li>
				<li class="layui-nav-item"><a href="javascript:logout()">注销</a>
				</li>
			</ul>
		</div>

		<div class="layui-side layui-bg-black">
			<div class="layui-side-scroll">
				<!-- 左侧导航区域（可配合layui已有的垂直导航） -->
				<ul class="layui-nav layui-nav-tree">
					<li class="layui-nav-item layui-nav-itemed"><a
						href="javascript:;">系统数据维护</a>
						<dl class="layui-nav-child">
							<dd>
								<a href="javascript:;"
									data-url="/web/user/table.jsp"
									class="site-demo-active">用户信息维护</a>
							</dd>
							<dd>
								<a href="javascript:;"
									data-url="/web/role/table.jsp"
									class="site-demo-active">角色信息维护</a>
							</dd>
							<dd>
								<a href="javascript:;"
									data-url="/web/menu/table.jsp"
									class="site-demo-active">菜单信息维护</a>
							</dd>
							<dd>
								<a href="javascript:;"
									data-url="/web/rel/table.jsp"
									class="site-demo-active">权限信息维护</a>
							</dd>
						</dl></li>
					<li class="layui-nav-item"><a href="javascript:;">业务数据表</a>
						<dl class="layui-nav-child">
							<dd>
								<a href="javascript:openURL('/web/custinfo/table.jsp');"
									class="site-demo-active">客户信息维护</a>
							</dd>
							<dd>
								<a href="javascript:openURL('/web/custcom/table.jsp');"
									class="site-demo-active">沟通记录维护</a>
							</dd>
							<dd>
								<a href="javascript:openURL('/web/proinfo/table.jsp');"
									class="site-demo-active">商品信息维护</a>
							</dd>
							<dd>
								<a href="javascript:openURL('/web/orderinfo/table.jsp');"
									class="site-demo-active">销售信息维护</a>
							</dd>
						</dl></li>
					<li class="layui-nav-item"><a href="javascript:;">基本功能</a>
						<dl class="layui-nav-child">
							<dd>
								<a href="javascript:openURL('/web/sys/huishouzhan.jsp');"
									class="site-demo-active">回收站</a>
							</dd>
							<dd>
								<a href="javascript:openURL('/web/sys/baike.jsp');"
									class="site-demo-active">百科</a>
							</dd>
							<dd>
								<a href="javascript:openURL('/web/sys/echarts1.jsp');"
									class="site-demo-active">销量比较</a>
							</dd>
							<dd>
								<a href="javascript:openURL('/web/sys/echarts.jsp');"
									class="site-demo-active">商品数据</a>
							</dd>
						</dl></li>
				</ul>
			</div>
		</div>
		<div class="layui-body">
			<!-- 内容主体区域 -->
			<iframe name="rightframe" width="99%" height="98%" src="#"></iframe>
		</div>
		<div class="layui-footer">© JAVA.com - 底部固定区域</div>
	</div>
	<script type="text/javascript" src="<%=path%>/layui/layui.all.js"></script>
	<script>
		$('.site-demo-active').click(function() {
			window.open(con.app + $(this).data('url'), "rightframe");
		});
		function openURL(url) {
			window.open(con.app + url, "rightframe");
		}
		$('.site-demo-active:first').click();

// 		function openUser() {
// 			openLayer('/web/page/user/userupd.jsp?usercode=${user.usercode}',
// 					function() {
// 						location.reload();
// 					})
// 		}
// 		function openPass() {
// 			openLayer('/web/page/user/userpass.jsp?usercode=${user.usercode}',
// 					function() {
// 					})
// 		}
		function logout() {
			openConfirm(function(index) {
				layer.close(index);
				toJsp("/user/logout.do");
			}, '注销当前用户?');
		}
	</script>
</body>
</html>