<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@ include file="/web/header.jsp"%>
</head>
<body>
	<blockquote class="layui-elem-quote">查找</blockquote>
	<form class="layui-form">

		<div class="layui-form-item">
			<label class="layui-form-label">员工编号</label>
			<div class="layui-input-inline">
				<input type="text" value="" name="userCode" required
					lay-verify="required" placeholder="必填" autocomplete="off"
					class="layui-input">
			</div>
			<label class="layui-form-label">员工姓名</label>
			<div class="layui-input-inline">
				<input type="text" value="${gb.name}" name="name" required
					lay-verify="required" placeholder="必填" autocomplete="off"
					class="layui-input">
			</div>
			<!-- 			   <label class="layui-form-label">员工姓名</label> -->
			<!-- 			   <div class="layui-input-inline"> -->
			<!-- 			    <input type="button" onclick="list()" id="find" value="查找" class="layui-btn"> -->
			<!-- 			   </div> -->
		</div>

	</form>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<button class="layui-btn" onclick="list()" id="find">查找</button>
	&nbsp;&nbsp;&nbsp;&nbsp;
	<button class="layui-btn" onclick="insert()" id="insert">添加/注册</button>

	<br>
	<br>
	<blockquote class="layui-elem-quote">员工信息</blockquote>
	<table class="layui-hide" id="test" lay-filter="test"></table>

	<script id="toolbarDemo" type="text/html">
  <div class="layui-btn-container">
    <button class="layui-btn layui-btn-sm" lay-event="getCheckData">获取选中行数据</button>
    <button class="layui-btn layui-btn-sm" lay-event="getCheckLength">获取选中数目</button>
    <button class="layui-btn layui-btn-sm" lay-event="isAll">验证是否全选</button>
  </div>
</script>

	<script id="barDemo" type="text/html">
  <a class="layui-btn layui-btn-xs" lay-event="edit" onclick="javaScript:upd('{{d.code}}')">恢复</a>
  <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del" onclick="javaScript:del('{{d.code}}')">删除</a>
</script>



	<!-- 注意：如果你直接复制所有代码到本地，上述js路径需要改成你本地的 -->

	<script type="text/javascript">
		function export1() {
			window.location.href = '/test3/user/exportExcel';
		}
		function list() {
			var table = layui.table;

			table.render({
				elem : '#test',
				request : {
					pageName : 'pageIndex' //页码的参数名称，默认：page
					,
					limitName : 'pageLimit' //每页数据量的参数名，默认：limit
				},
				where : {
					name : $('input[name="name"]').val(),
					userCode : $('input[name="userCode"]').val()
				},
				limit : 3,
				limits : [ 3, 6, 9, 12, 15 ],
				url : con.app + '/user/retrieve',
				toolbar : '#toolbarDemo',
				title : '用户数据表',
				cols : [ [ {
					type : 'checkbox',
					fixed : 'left'
				}, {
					field : 'id',
					title : 'ID',
					width : 80,
					fixed : 'left',
					unresize : true,
					sort : true
				}, {
					field : 'name',
					title : '用户名',
					edit : 'text'
				}, {
					field : 'userCode',
					title : '编号',
					edit : 'text'
				}, {
					field : 'pass',
					title : '密码',
					width : 80,
					edit : 'text',
					sort : true
				}, {
					field : 'roleCode',
					title : '员工职称'
				}, {
					field : 'parentCode',
					title : '上级领导'
				}, {
					fixed : 'right',
					title : '操作',
					toolbar : '#barDemo',
					width : 150
				} ] ],
				page : true
			});

			//头工具栏事件
			table.on('toolbar(test)', function(obj) {
				var checkStatus = table.checkStatus(obj.config.id);
				switch (obj.event) {
				case 'getCheckData':
					var data = checkStatus.data;
					layer.alert(JSON.stringify(data));
					break;
				case 'getCheckLength':
					var data = checkStatus.data;
					layer.msg('选中了：' + data.length + ' 个');
					break;
				case 'isAll':
					layer.msg(checkStatus.isAll ? '全选' : '未全选');
					break;
				}
				;
			});

			//监听行工具事件
			// 	  table.on('tool(test)', function(obj){
			// 	    var data = obj.data;
			// 	    //console.log(obj)
			// 	    if(obj.event === 'del'){
			// 	      layer.confirm('真的删除行么', function(index){
			// 	        obj.del();
			// 	        layer.close(index);
			// 	      });
			// 	    } else if(obj.event === 'edit'){
			// 	      layer.prompt({
			// 	        formType: 2
			// 	        ,value: data.email
			// 	      }, function(value, index){
			// 	        obj.update({
			// 	          email: value
			// 	        });
			// 	        layer.close(index);
			// 	      });
			// 	    }
			// 	  });

		}
		var userCode="${user.code}"
		function upd(code) {
			openConfirm(function(){
				ajax('/user/retrieveData',{code:code,userCode:userCode},'text',function(data){
					layer.msg(data);
					$('#test').html('');
					list();
				})
				}, "确认要恢复么")
		}
		function del(code) {
			openConfirm(function(){
				ajax('/user/delR',{code:code},'text',function(data){
					layer.msg(data);
					$('#test').html('');
					list();
				})
				}, "确认要永久删除么")
		}
		$('#find').click();
	</script>
</body>
</html> 