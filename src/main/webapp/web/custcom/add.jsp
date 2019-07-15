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
		<legend>商品信息录入</legend>
		<div class="layui-field-box">
		  <form class="layui-form" >
		  	  <input type="hidden" name="action" value="add">
		    <div class="layui-form-item">
		      <label class="layui-form-label">卖家编号</label>
		        <div class="layui-input-inline">
		        <input type="text" name="userCode" id="usercode"  value="" required  lay-verify="required" placeholder="必填" autocomplete="off" class="layui-input" >
		      </div>
		    </div>
		   <div class="layui-form-item">
		     <label class="layui-form-label">买家名称</label>
		   <div class="layui-input-inline">
		       <input type="text" name="custCode" id="username" value="" required  lay-verify="required" placeholder="必填" autocomplete="off" class="layui-input">
		     </div>
		   </div>
		      <div class="layui-form-item">
		     <label class="layui-form-label">时间</label>
		   <div class="layui-input-inline">
		       <input type="text" name="TIME" id="test1" value="" required  lay-verify="required" placeholder="必填" autocomplete="off" class="layui-input">
		     </div>
		   </div>
		   <div class="layui-form-item">
    <label class="layui-form-label">购买方式</label>
    <div class="layui-input-inline">
    <select name="type" lay-search required  lay-verify="required">
     <option value="">请选择</option>
    <option value="支付宝"  >支付宝</option>
    <option value="微信" >微信</option>
    <option value="银行卡">银行卡</option>
    <option value="信用卡">信用卡</option>
    <option value="分期">分期</option>
    </select>
    </div>
   </div>
   		      <div class="layui-form-item">
		     <label class="layui-form-label">评价</label>
		   <div class="layui-input-inline">
		       <input type="text" name="content" id="username" value="" required  lay-verify="required" placeholder="必填" autocomplete="off" class="layui-input">
		     </div>
		   </div>
<!--    <div class="layui-form-item"> -->
<!--    <label class="layui-form-label">评价</label> -->
<!--    <div id="test1" name="content"> -->
<!--    </div> -->
<!--    </div> -->
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
		 var laydate = layui.laydate;
  
  //执行一个laydate实例
  		laydate.render({
   		 elem: '#test1' //指定元素
 			 });
			
		form.render();
		var contextPath="${pageContext.request.contextPath}"
		layui.use('form', function(){
		  var form = layui.form;
		  
		  //监听提交
		  form.on('submit(reg)', function(data){
		    //layer.msg(JSON.stringify(data.field));
		    $.ajax({
		    	type:'POST',
				url:contextPath+'/custcom/action',
				dataType:"text",
				data:data.field,
				success:function(data){
					var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
					layer.msg(data);
					setTimeout(function(){parent.layer.close(index);},800)
// 					layer.msg(data);
// 					layer.msg("1234567");
// 						layer.msg(data);
						
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
// 		 $.ajax({
// 				type:'post',
// 				url:contextPath+'/department/action.do',
// 				dataType:'json',
// 				data:{action:'userDeName'},
// 				success:function(data){
// 					console.log(typeof data)
// 					console.log(data)
// 					//data = JSON.parse(data);
					
// 					var st="";
// 					$('select[name="departmentid"]').html(st);
// 				 	$.each(data,function(i,dom){
// 						layui.use('laytpl',function(){
// 							console.log(dom)
// 							var laytpl=layui.laytpl;
// 							st=laytpl($('#opt').html()).render(dom)
// 							console.log(st)
// 							$('select[name="departmentid"]').append(st);
// 						})
						
// 					});//each 
// 					layui.use('form', function(){
// 						 var form = layui.form;
// 					form.render();});//form渲染
// 				}//ajax.success
// 			});//ajax
// 	   ajax('/DepartmentServlet',{action:'all'},'json',function(data){
//     	var html='';
//     	$.each(data,function(i,d){
//     		html+=laytpl($('#opt').html()).render(d);
//     	})
//     	$("select[name='roleCode']").html(html);
//     	form.render();
//     })
    </script>
<!--     <script> -->
<!--     layui.use('rate', function(){   -->
<!--        var rate = layui.rate;   -->
<!--            // 渲染   -->
<!--        var ins1 = rate.render({   -->
<!--         elem: '#test1'  //绑定元素  -->
<!--      });   -->
<!--     });   -->
<!--    </script>  -->
</body>
</html>