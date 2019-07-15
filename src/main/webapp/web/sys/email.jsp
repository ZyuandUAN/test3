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
		<legend>邮件发送</legend>
		<div class="layui-field-box">
		  <form class="layui-form" >
		    <div class="layui-form-item">
		      <label class="layui-form-label">收件人</label>
		      <div class="layui-input-block">
		        <input type="text" name="email" value="" required  lay-verify="required" placeholder="必填" autocomplete="off" class="layui-input" >
		      </div>
		    </div>
		   <div class="layui-form-item">
		     <label class="layui-form-label">主题</label>
		     <div class="layui-input-block">
		       <input type="text" name="zhuti" value="" required  lay-verify="required" placeholder="必填" autocomplete="off" class="layui-input">
		     </div>
		   </div>
		    <div class="layui-form-item layui-form-text">
    <label class="layui-form-label">正文</label>
    <div class="layui-input-block">
      <textarea name="zhengwen" placeholder="请输入内容" class="layui-textarea"></textarea>
    </div>
  </div>
			<div class="layui-form-item">
		      <div class="layui-input-block">
		      <input type="button" class="layui-btn" value="发送" lay-submit lay-filter="add" >
		        <input type="reset" class="layui-btn" value="重置">
		      </div>
		    </div>
		  </form>
		</div>
		</fieldset>
		<script type="text/javascript">
		init();
		form.render();
		var contextPath="${pageContext.request.contextPath}"
// 		layui.use('form', function(){
// 		  var form = layui.form;
		function send(){
			form.on('submit(add)',function(data){
				 ajax('/custInfo/send',data.field,'text',function(data){
						layer.msg(data);
					 })
				})
			}
		send();

// 		var contextPath="${pageContext.request.contextPath}"
		var contextPath='<%=request.getContextPath()%>'
			function init(){
				var custCode='<%=request.getParameter("custCode")%>';
				console.log(custCode)
				 $.ajax({
						type:'post',
						url:'/test3/custInfo/list',
						dataType:'json',
						data:{custCode:custCode},
						success:function(data){
							//console.log(typeof data)
							console.log(data)
							$.each(data,function(i,dom){
								$("input[name='email']").val(dom.email)
								})
							layui.use('form', function(){
								 var form = layui.form;
								 form.val('formuse',{email:data.email})//form模块回写数据
								 form.render();
							});
							form.render();
						
						}//ajax.success
					});//ajax
				}
		</script>
</body>
</html>