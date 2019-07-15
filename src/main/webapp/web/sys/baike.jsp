<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/web/header.jsp"%>
<title>百科</title>
</head>
<body>
<fieldset class="layui-elem-field " style="margin: 20px; padding: 20px">
		<legend>词条搜索</legend>
		<div class="layui-field-box">
			<form class="layui-form layui-form-pane" method="post" onsubmit="return false">
				<div class="layui-form-item">
					<label class="layui-form-label">词条：</label>
					<div class="layui-input-inline">
                		<input type="text" name="item" lay-verify="required" placeholder="请输入关键词" autocomplete="off"
                   			 class="layui-input">
            		</div>
				</div>
				<div class="layui-form-item">
            		<label class="layui-form-label"></label>
            		<div class="layui-input-inline">
                		<input type="button" class="layui-btn" lay-submit lay-filter="search" value="检索" />
                		<button type="reset" class="layui-btn">重置</button>
            		</div>
        		</div>
			</form>		
		</div>
</fieldset>
<fieldset class="layui-elem-field " style="margin: 20px; padding: 20px">
	<legend>词条解释</legend>
	<div class="layui-field-box">
		<div class="layui-form-item">
			<div class="layui-input-blank">
				<div id="text" style="font-size: 20px"></div>			
			</div>
		</div>		
	</div>
</fieldset>
<script type="text/javascript">
formSubmit('/jsoup/item','submit(search)','text',function(data) {
    var text = '';
    if(data == ''){
		text='暂无该词条';
    } else {
		text=data;
    }
    $("#text").html(text);
});
</script>
</body>
</html>