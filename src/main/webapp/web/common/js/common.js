var con = {
	app : "/test3",
	jsp_login : "/web/login.jsp",
	jsp_reg : "/web/reg.jsp",
};
var form = layui.form;
var $ = layui.jquery;
var layer = layui.layer;
var element = layui.element;
var laypage = layui.laypage;
var laytpl = layui.laytpl;

// layui.use(['form','layer','jquery'], function () {
// var form = layui.form;// 获取操作对象
// var $ = layui.jquery;
// ...
// });

// 强烈不推荐下面的做法
// var form,$;
// layui.use(['form','layer','jquery'], function () {
// form = layui.form;// 获取操作对象
// $ = layui.jquery;
// ...
// });

function toJsp(url) {
	location.href = con.app + url;
}
function goJspLogin() {
	toJsp(con.jsp_login);
}
function goJspReg() {
	toJsp(con.jsp_reg)
}
function goLogout() {
	openConfirm(function(index) {
		layer.close(index);
		toJsp("/user/logout");
		// location.href = con.app + "/EmployeeServlet?action=logout";
	}, '确定注销？');
}
function openConfirm(func, title) {
	layer.confirm(title ? title : "确定进行该操作？", {
		icon : 3,
		title : '提示'
	}, func);
}

function formSubmit(url, submit, dataType, func) {
	form.on(submit, function(data) {
		ajax(url, data.field, dataType, func);
	});
}
function ajax(url, field, dataType, func) {
	$.ajax({
		url : con.app + url,
		data : field,
		dataType : dataType,
		type : 'post',
		success : func
	});
}
function openLayer(url, end) {
	layer.open({
		type : 2,
		area : [ '800px', '450px' ],
		fixed : false, // 不固定
		maxmin : true,
		end : end,
		content : con.app + url
	});
}
function closeThis(time) {
	setTimeout(function() {// 先得到当前iframe层的索引
		var index = parent.layer.getFrameIndex(window.name);
		parent.layer.close(index);
	}, time ? time : 200)
}
function setPageInfo(elem, count, curr, limit, jump) {
	laypage
			.render({
				elem : elem,// id
				count : count,// 数据总数
				curr : curr,// 当前页
				limit : limit,// 每页显示的条数
				limits : [ 10, 20, 30, 40, 50 ],// 每页条数的选择项
				layout : [ 'count', 'prev', 'page', 'next', 'limit', 'refresh',
						'skip' ],
				jump : jump
			});
}
function getlaytpl(sel, data) {
	var temp = $(sel).html();
	return laytpl(temp).render(data);
}
