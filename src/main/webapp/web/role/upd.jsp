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
		        <input type="text" name="roleCode" id="roleCode"  value="" required  lay-verify="required" placeholder="必填" autocomplete="off" class="layui-input">
		      </div>
		    </div>
		   <div class="layui-form-item">
		     <label class="layui-form-label">职位</label>
		     <div class="layui-input-block">
		       <input type="text" name="roleName" id="roleName" value="" required  lay-verify="required" placeholder="必填" autocomplete="off" class="layui-input">
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
			function init(){
				var roleCode='<%=request.getParameter("roleCode")%>';
				console.log(roleCode)
				 $.ajax({
						type:'post',
						url:contextPath+'/role/action',
						dataType:'json',
						data:{action:'upd',roleCode:roleCode},
						success:function(data){
							//console.log(typeof data)
							console.log(data)
							layui.use('form', function(){
								 var form = layui.form;
								 form.val('formuse',{id333:data.id,roleCode:data.roleCode,roleName:data.roleName})//form模块回写数据
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
				url:contextPath+'/role/action',
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
	<script type="text/html" id="opt">
		<option  value="{{d.code}}">{{d.name}}</option>
</script>
</body>
</html>