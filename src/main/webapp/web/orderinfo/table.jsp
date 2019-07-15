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
<div class="layui-collapse">
<div class="layui-colla-item">
<h2 class="layui-colla-title">用户信息-查村条件</h2>
<div class="layui-colla-content layui-show">
<fieldset class="layui-elem-field layui-field-title" style="margin-top:50px">
<legend>用户信息-查询条件</legend>
<form class="layui-form">
<div class="layui-form-item">
<!-- <label class="layui-form-label">卖家编号</label> -->
<!--  <div class="layui-input-inline"> -->
<!--  <input type="text" name="userCode" placeholder="请输入卖家账号"autocomplete="off" class="layui-input">  -->
<!-- </div> -->
<!-- <label class="layui-form-label">买家名称</label> -->
<!-- <div class="layui-input-inline"> -->
<!--  <input type="text" name="custCode" placeholder="请输入买家账号"autocomplete="off" class="layui-input">  -->
<!-- </div> -->
<label class="layui-form-label">商品名称</label>
<div class="layui-input-inline">
 <input type="text" name="proName" placeholder="请输入商品账号"autocomplete="off" class="layui-input"> 
</div>
<label class="layui-form-label"></label>
<span>
<input type="button" class="layui-btn layui-icon"  lay-filter="formDemo" onclick="add()" value="&#xe608;添加"/>
<input type="button" class="layui-btn layui-icon"  lay-submit lay-filter="search" value="&#xe615;查找"/>
<button type="reset" class="layui-btn layui-btn-primary layui-icon">&#xe669;重置</button>
<input type="hidden" name="action" value="list">
<input type="hidden" name="pageIndex" value="1">
<input type="hidden" name="pageLimit" value="3">
</span>
</div>
</form>
</fieldset>
</div>
</div>
</div>
<table id="demo" lay-filter="test"></table>
<script>
refresh()
function refresh(){
layui.use('table', function(){
  var table = layui.table;
  //初始化--方法渲染
  table.render({
    elem: '#demo'//绑定容器
    ,url:con.app+'/orderinfo/list2'//数据接口//默认会自动传递两个参数：？page=1&limit=30
    ,height:'full-230'//容器高度
    ,request:{
    	pageName:'pageIndex'//页码的参数名称（从第几页开始）
    	,limitName:'pageLimit'//每页的数据量的参数名（一页放几个）
    }
    ,where:{proName:$("input[name='proName']").val(),userCode:$("input[name='userCode']").val(),custCode:$("input[name='custCode']").val()}
   ,page:true//分页开启
    ,cols: [[//表头
       {title:'全选',type:'checkbox',fixed:'left'}//LAY_CHECKED:true
       ,{title:'序号',type:'numbers',fixed:'left'}
      ,{field:'id', title:'主键ID',width:80,sort: true,fixed:'left',align:'right'}
      ,{field:'userCode', width:100, title: '卖家编号'}
      ,{field:'custCode', width:100, title: '买家编号'}
      ,{field:'proName', title: '商品名称', Width: 100,sort:true}
      ,{field:'count', width:100, title: '销量'}
      ,{field:'TIME', width:100, title: '时间'}
      ,{field:'status', width:120, title: '订单状态'}
      ,{toolbar:'#barDemo', width:250, title: '操作1'}
//       ,{title:'操作2',width:180,templet:'#barDemo'}
//       ,{title:'操作3'，width:80,templet:'<div><a href="javascript:;"class="layui-table-line">'}
//       ,{title:'操作4',width:80,templet:function(d){return''}}
       ]]
  });
});

}
</script>
	<script>
		function add(){
			layui.use('layer', function(){
				var layer = layui.layer;
				layer.open({
					type:2,
					content:"${pageContext.request.contextPath}/web/orderinfo/add.jsp",
					area: ['700px', '600px'],
					title: ['人员添加', 'font-size:18px;'],
					shade:0.3,
					shadeClose:true,
					closeBtn:2,
					anim: 6,
					end:function(){
						//load(1)
						refresh();
					}	
				})
					});  
		}
		function text(idd){//修改的弹出框	
			layui.use('layer', function(){
				var layer = layui.layer;	
				layer.open({
					type:2,
					content:"${pageContext.request.contextPath}/orderinfo/toupd?id="+idd,//userServlet?usercode="+idd+"&action=upd",
					area: ['700px', '600px'],
					title: ['用户修改', 'font-size:18px;'],
					shade:0.3,
					shadeClose:true,
					closeBtn:2,
					anim: 6,
					end:function(){
						//alert("????")
						//layer.mag("????")
						//load(1)
						refresh();
				}							
				})
					});  
		}
		var contextPath="${pageContext.request.contextPath}";
		function aa2(id,act){
			layui.use('layer', function(){
				  var layer = layui.layer;
				  if(confirm("确认真的要删除么？")){
						 $.ajax({
							type:'POST',
							url:'/test3/orderinfo/del',
							dataType:"text",
							data:{id:id},
							//data:{action:'list',username:valw},
							success:function(data){   
								//load(1)
								layer.msg(data)
								refresh();
							}	}); 
							//layer.msg(confirm("确认真的要删除么？"))
					}
				});      
		
		//	var h=""
		
		}
		form.on('submit(search)',function(data){
refresh();
			});
		function exportExcel(){
			window.location.href=con.app+'/orderinfo/exportExcel'
			}
		
		layui.use('upload', function(){
			console.log("aaa")
			  var upload = layui.upload;
			  //执行实例
			  var uploadInst = upload.render({
			    elem: '#test9' //绑定元素
			    ,url: '/orderinfo/uploadExcel' //上传接口
			    ,done: function(res){
			      //上传完毕回调
			    }
			    ,error: function(){
			      //请求异常回调
			    }
			  });
			});
 		</script>  
<script type="text/html" id="barDemo">
			<button onclick="text('{{d.id}}')" class="layui-btn layui-btn-primary">
			<i class="layui-icon layui-icon-edit"></i>修改</button>
			<button onclick="aa2('{{d.id}}','del')" class="layui-btn layui-btn-danger">
			<i class="layui-icon layui-icon-delete"></i>删除</button>
  <!-- 这里同样支持 laytpl 语法，如： -->
  {{#  if(d.auth > 2){ }}
    <a class="layui-btn layui-btn-xs" lay-event="check">审核</a>
  {{#  } }}
</script>
</body>
</html>