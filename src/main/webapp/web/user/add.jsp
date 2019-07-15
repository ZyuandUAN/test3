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

		<fieldset class="layui-elem-field layui-field-title">
		<legend>人员信息录入</legend>
		<div class="layui-field-box">
		  <form class="layui-form" >
		  	  <input type="hidden" name="action" value="add">
		    <div class="layui-form-item">
		      <label class="layui-form-label">账号</label>
		      <div class="layui-input-block">
		        <input type="text" name="userCode" id="usercode"  value="" required  lay-verify="required" placeholder="必填" autocomplete="off" class="layui-input" >
		      </div>
		    </div>
		   <div class="layui-form-item">
		     <label class="layui-form-label">姓名</label>
		     <div class="layui-input-block">
		       <input type="text" name="name" id="username" value="" required  lay-verify="required" placeholder="必填" autocomplete="off" class="layui-input">
		     </div>
		   </div>
			  <div class="layui-form-item">
			   <label class="layui-form-label">密码</label>
			   <div class="layui-input-block">
			     <input type="password" value="" name="pass"  required  lay-verify="required" placeholder="必填" autocomplete="off" class="layui-input" >
			   </div>
			 </div>
        <div class="layui-form-item">
        <label class="layui-form-label">职位</label>
        <div class="layui-input-inline">
        <select name="roleCode" ></select>
        </div>
        </div>
            <div class="layui-form-item">
        <label class="layui-form-label">上级领导</label>
        <div class="layui-input-inline">
        <select name="parentCode" ></select>
        </div>
        </div>
		    <div class="layui-form-item">
		      <label class="layui-form-label">管理员权限</label>
		      <div class="layui-input-block">
		        <input type="radio" name="admin"  value="1" title="是" >
		        <input type="radio" name="admin" value="0" title="否" >
		      </div>
		    </div>
			<div class="layui-form-item">
		      <div class="layui-input-block">
		      <input type="button" class="layui-btn" value="保存" lay-submit lay-filter="reg">
		        <input type="reset" class="layui-btn" value="重置">
		      </div>
		    </div>
		  </form>
		</div>
		</fieldset>
		<script>
		//Demo
		form.render();
		var contextPath="${pageContext.request.contextPath}"
		layui.use('form', function(){
		  var form = layui.form;
		  
		  //监听提交
		  form.on('submit(reg)', function(data){
		    //layer.msg(JSON.stringify(data.field));
		    $.ajax({
		    	type:'POST',
		    	url:contextPath+'/user/action',
				dataType:"text",
				data:data.field,
				success:function(data){
					var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
					layer.msg(data);
					setTimeout(function(){parent.layer.close(index);},800)
				}
		    })
		    
		  });
		});
		//layui.use('form', function(){
		// var form = layui.form;
		/* form.on('select(select)', function(data){
			  //console.log(data.elem); //得到select原始DOM对象
			  //console.log(data.value); //得到被选中的值
			 // console.log(data.othis); //得到美化后的DOM对象
			 $.ajax({
			type:'post',
			url:contextPath+'/departmentServlet',
			dataType:'text',
			data:{action:'all'},
			success:function(data){
				//console.log(data)
				//data=JSON.parse(data)
				
				var st="";
				$('select[name="address"]').html(st);
				$.each(data,function(i,dom){
					var laytpl=layui.laytpl;
					st=laytpl($('#opt').html()).render(dom)
				});//each
				$('select[name="address"]').append(st);
				form.render();
			}//ajax.success
		});//ajax
			});  //select监听  
		});//form模块 */
		 $.ajax({
				type:'post',
				url:contextPath+'/role/list',
				dataType:'json',
				data:{},
				success:function(data){
					console.log(data)
					var st="";
				 	$.each(data,function(i,dom){
					 	console.log(dom)
					 	st+='<option  value="'+dom.roleCode+'">'+dom.roleName+'</option>'
							$('select[name="roleCode"]').html(st);				
					});//each 
				 	form.render();
				}//ajax.success
			});//ajax
			 $.ajax({
					type:'post',
					url:contextPath+'/role/list',
					dataType:'json',
					data:{},
					success:function(data){
						console.log(data)
						var st="";
					 	$.each(data,function(i,dom){
						 	console.log(dom)
						 	st+='<option  value="'+dom.roleCode+'">'+dom.roleName+'</option>'
								$('select[name="roleCode"]').html(st);				
						});//each 
					 	form.render();
					}//ajax.success
				});//ajax
				 $.ajax({
						type:'post',
						url:contextPath+'/role/list',
						dataType:'json',
						data:{},
						success:function(data){
							console.log(data)
							var st="";
						 	$.each(data,function(i,dom){
							 	console.log(dom)
							 	st+='<option  value="'+dom.roleName+'">'+dom.roleName+'</option>'
									$('select[name="parentCode"]').html(st);				
							});//each 
						 	form.render();
						}//ajax.success
					});//ajax
    </script>
<!-- 	<script type="text/html" id="opt"> -->
<!-- 		<option  value="{{d.roleCode}}">{{d.roleName}}</option> -->
<!-- </script> -->
</body>
</html>