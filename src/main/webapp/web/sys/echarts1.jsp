<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>销量统计</title>
<%@ include file="/web/header.jsp"%>
<script type="text/javascript" src="/test3/web/common/js/echarts.min.js"></script>
</head>
<body>
	<div id="main" style="width: 700px; height: 800px;"></div>
	<script type="text/javascript">
		ajax('/orderinfo/xiaoliang', {}, 'json', function(data) {
			var datad = [];
			$.each(data, function(i, dom) {
				var d = {
					value : dom.count,
					name : dom.proName
				}
				datad.push(d);
			})
			//console.log(datad);
			var myChart = echarts.init(document.getElementById('main'));

			var option = {
				backgroundColor : '#2c343c',
				title : {
					text : '销量比较',
					left : 'center',
					top : 20,
					textStyle : {
						color : '#ccc'
					}
				},
				tooltip : {
					trigger : 'item',
					formatter : "{a} <br/>{b} : {c} ({d}%)"
				},
				visualMap : {
					show : false,
					min : 80,
					max : 600,
					inRange : {
						colorLightness : [ 0, 1 ]
					}
				},
				series : [ {
					name : '销量',
					type : 'pie',
					radius : '55%',
					center : [ '50%', '50%' ],
					data : datad.sort(function(a, b) {
						return a.value - b.value;
					}),
					//		 	            data:[
					//		 	                {value:335, name:'直接访问'},
					//		 	                {value:310, name:'邮件营销'},
					//		 	                {value:274, name:'联盟广告'},
					//		 	                {value:235, name:'视频广告'},
					//		 	                {value:400, name:'搜索引擎'}
					//		 	            ].sort(function (a, b) { return a.value - b.value; }),
					roseType : 'radius',
					label : {
						normal : {
							textStyle : {
								color : 'rgba(255, 255, 255, 0.3)'
							}
						}
					},
					labelLine : {
						normal : {
							lineStyle : {
								color : 'rgba(255, 255, 255, 0.3)'
							},
							smooth : 0.2,
							length : 10,
							length2 : 20
						}
					},
					itemStyle : {
						normal : {
							color : '#c23531',
							shadowBlur : 200,
							shadowColor : 'rgba(0, 0, 0, 0.5)'
						}
					},
					animationType : 'scale',
					animationEasing : 'elasticOut',
					animationDelay : function(idx) {
						return Math.random() * 200;
					}
				} ]
			};
			myChart.setOption(option);
		})
	</script>
</body>
</html>