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
<h2 class="layui-colla-title">菜单信息</h2>
<div class="layui-colla-content layui-show">
<fieldset class="layui-elem-field layui-field-title" style="margin-top:50px">
<form class="layui-form">
<div class="layui-form-item">
<label class="layui-form-label"></label>
<span>
<input type="button" class="layui-btn layui-icon"  lay-filter="formDemo" onclick="add()" value="&#xe608;添加"/>
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
    ,url:con.app+'/Menu/list2'//数据接口//默认会自动传递两个参数：？page=1&limit=30
    ,height:'full-230'//容器高度
    ,request:{
    	pageName:'pageIndex'//页码的参数名称（从第几页开始）
    	,limitName:'pageLimit'//每页的数据量的参数名（一页放几个）
    }
   ,page:true//分页开启
    ,cols: [[//表头
       {title:'全选',type:'checkbox',fixed:'left'}//LAY_CHECKED:true
       ,{title:'序号',type:'numbers',fixed:'left'}
      ,{field:'id', title:'主键ID',width:80,sort: true,fixed:'left',align:'right'}
      ,{field:'menuCode', width:100, title: '菜单编号'}
      ,{field:'menuName', width:100, title: '菜单名称'}
      ,{field:'menuUrl', title: '菜单路径', Width: 100,sort:true}
      ,{field:'parentCode', width:100, title: '上一级'}
      ,{field:'level', width:100, title: '菜单等级'}
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
					content:"${pageContext.request.contextPath}/web/menu/add.jsp",
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
		var contextPath="${pageContext.request.contextPath}";
		function aa2(menuCode,act){
			layui.use('layer', function(){
				  var layer = layui.layer;
				  if(confirm("确认真的要删除么？")){
						 $.ajax({
							type:'POST',
							url:'/test3/Menu/del',
							dataType:"text",
							data:{menuCode:menuCode},
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
 		</script>  
<script type="text/html" id="barDemo">
			<button onclick="aa2('{{d.menuCode}}','del')" class="layui-btn layui-btn-danger">
			<i class="layui-icon layui-icon-delete"></i>删除</button>
  <!-- 这里同样支持 laytpl 语法，如： -->
  {{#  if(d.auth > 2){ }}
    <a class="layui-btn layui-btn-xs" lay-event="check">审核</a>
  {{#  } }}
</script>
</body>
</html>