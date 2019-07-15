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
<label class="layui-form-label">账号</label>
 <div class="layui-input-inline">
 <input type="text" name="userCode" placeholder="请输入账号"autocomplete="off" class="layui-input"> 
</div>
<label class="layui-form-label">姓名</label>
<div class="layui-input-inline">
 <input type="text" name="name" placeholder="请输入姓名"autocomplete="off" class="layui-input"> 
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
<div class="layui-form-item">
<label class="layui-form-label"></label>
<input type="button" class="layui-btn" value="导出Excel" onclick="exportExcel()"/>
<input type="button" class="layui-btn layui-btn-normal" value="下载模板" onclick="moban()"/>
<input type="button" class="layui-btn"id="upload" value="上传文件"/>
</div>
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
    ,url:con.app+'/user/list2'//数据接口//默认会自动传递两个参数：？page=1&limit=30
    ,height:'full-230'//容器高度
    ,request:{
    	pageName:'pageIndex'//页码的参数名称（从第几页开始）
    	,limitName:'pageLimit'//每页的数据量的参数名（一页放几个）
    }
  ,limit:3
  ,limits:[3,6,9,12,15]
    ,where:{userCode:$("input[name='userCode']").val(),name:$("input[name='name']").val()}
   ,page:true//分页开启
    ,cols: [[//表头
       {title:'全选',type:'checkbox',fixed:'left'}//LAY_CHECKED:true
       ,{title:'序号',type:'numbers',fixed:'left'}
      ,{field:'id', title:'主键ID',width:80,sort: true,fixed:'left',align:'right'}
      ,{field:'userCode', width:100, title: '账号'}
      ,{field:'name', title: '姓名', Width: 180,sort:true}
      ,{field:'pass', width:180, title: '密码'}
      ,{field:'roleName', width:180, title: '职位'}
      ,{field:'parentCode', width:180, title: '上级领导'}
      ,{field:'admin', width:100, title: '管理员'}
      ,{toolbar:'#barDemo', width:300, title: '操作1'}
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
					content:"${pageContext.request.contextPath}/web/user/add.jsp",
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
					content:"${pageContext.request.contextPath}/web/user/upd.jsp?userCode="+idd,//userServlet?usercode="+idd+"&action=upd",
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
		function aa2(userCode,act){
			layui.use('layer', function(){
				  var layer = layui.layer;
				  if(confirm("确认真的要删除么？")){
						 $.ajax({
							type:'POST',
							url:contextPath+'/user/action',
							dataType:"text",
							data:{action:'del',userCode:userCode},
							//data:{action:'list',username:valw},
							success:function(data){   
								//load(1)
								//$("#demo").html('');
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
			window.location.href=con.app+'/user/exportExcel'
			}
		function moban(){
			window.location.href=con.app+'/user/moban'
			}
		
		function uploadExcel(){
			var upload = layui.upload;
			   
			  //执行实例
			  var uploadInst = upload.render({
			    elem: '#upload' //绑定元素
			    ,url: '/test3/user/upload' //上传接口
				 ,accept:'file'
				,exts:'xls|xlsx'
			    ,done: function(res){
			      //上传完毕回调
				      layer.msg("成功")
				  	$('#test').html('');
						list();
			    }
			    ,error: function(){
			      //请求异常回调
			    }
			  });
			}
		uploadExcel();
 		</script>  
<script type="text/html" id="barDemo">
			<button onclick="text('{{d.userCode}}')" class="layui-btn layui-btn-primary">
			<i class="layui-icon layui-icon-edit"></i>修改</button>
			<button onclick="aa2('{{d.userCode}}','del')" class="layui-btn layui-btn-danger">
			<i class="layui-icon layui-icon-delete"></i>删除</button>
  <!-- 这里同样支持 laytpl 语法，如： -->
  {{#  if(d.auth > 2){ }}
    <a class="layui-btn layui-btn-xs" lay-event="check">审核</a>
  {{#  } }}
</script>
</body>
</html>