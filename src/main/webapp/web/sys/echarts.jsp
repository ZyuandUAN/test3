<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>图表</title>
<%@ include file="/web/header.jsp"%>
<script type="text/javascript" src="/test3/web/common/js/echarts.min.js"></script>
</head>
<body>
<div id="main" style="width: 900px;height:400px;"></div>
   <script type="text/javascript">
   function init(){
	   var datad=[];
		datad.push(['商品名称', '数量', '单价(万)']);
	   var countt=0;
	ajax('/proinfo/list',{},'json',function(data){
	$.each(data,function(i,dom){
		countt++;
		var data="data"+i;
		window[data]=[];
		window[data].push(dom.proName);
		window[data].push(dom.sum);
		window[data].push(dom.cost);
		
		datad.push(window[data]);
		
		})
		
	
console.log(datad)
       var myChart = echarts.init(document.getElementById('main'));
		 var option = {
	        	    legend: {},
	        	    tooltip: {},
	        	    dataset: {
	        	    	source: datad
// 		        	        source: [
// 		        	            ['product', '2015', '2016', '2017'],// ['产品', '库存', '价格']
// 		        	            ['Matcha Latte', 43.3, 85.8, 93.7],
// 		        	            ['Milk Tea', 83.1, 73.4, 55.1],
// 		        	            ['Cheese Cocoa', 86.4, 65.2, 82.5],
// 		        	            ['Walnut Brownie', 72.4, 53.9, 39.1]
// 		        	           // data1
// 		        	        ]
	        	    },
	        	    xAxis: {type: 'category'},
	        	    yAxis: {},
	        	    // Declare several bar series, each will be mapped
	        	    // to a column of dataset.source by default.
	        	    series: [
	        	        {type: 'bar'},
	        	        {type: 'bar'}
	        	       // ,{type: 'bar'}
	        	    ]
	        	};
		   myChart.setOption(option);
		console.log(data1);
		})
	   }
   init();
        // 基于准备好的dom，初始化echarts实例
 
	
    </script>
</body>
</html>