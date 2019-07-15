<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改</title>
<%@include file="/web/header.jsp"%>
</head>
<body>

		<fieldset class="layui-elem-field layui-field-title">
		<legend>人员信息修改</legend>
		<div class="layui-field-box">
		  <form class="layui-form" lay-filter="formuse">
		  	  <input type="hidden" name="action" value="upds">
		    <div class="layui-form-item">
		      <label class="layui-form-label">账号</label>
		      <div class="layui-input-block">
		        <input type="text" name="userCode" id="code"  value="" required  lay-verify="required" placeholder="必填" autocomplete="off" class="layui-input" readonly >
		      </div>
		    </div>
		   <div class="layui-form-item">
		     <label class="layui-form-label">姓名</label>
		     <div class="layui-input-block">
		       <input type="text" name="name" id="name" value="" required  lay-verify="required" placeholder="必填" autocomplete="off" class="layui-input">
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
		var contextPath="${pageContext.request.contextPath}"
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
			function init(){
				var userCode='<%=request.getParameter("userCode")%>';
				console.log(userCode)
				 $.ajax({
						type:'post',
						url:contextPath+'/user/action',
						dataType:'json',
						data:{action:'upd',userCode:userCode},
						success:function(data){
							//console.log(typeof data)
							console.log(data)
							layui.use('form', function(){
								 var form = layui.form;
								 var admin=data.admin?"1":"0";
								 form.val('formuse',{id:data.id,name:data.name,pass:data.pass,userCode:data.userCode,roleName:data.roleName,roleCode:data.roleCode,parentCode:data.parentCode,admin:admin})//form模块回写数据
								form.render();
							});
							
							layui.use('form', function(){
								 var form = layui.form;
							form.render();});//form渲染
						}//ajax.success
					});//ajax
			}	
		</script>	
		<script>
		//Demo
			init();
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
				datatype:"text",
				data:data.field,
				success:function(data){
					//layer.msg(data);
					//layer.msg("1234567");
						layer.msg(data);
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
		 
	
				
		</script>
</body>
</html>