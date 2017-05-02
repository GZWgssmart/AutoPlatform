function getLineBasicChart(id, url, jsonData,time) {
	$.get(url, function (date) {
		jsonData.series = date;
		jsonData.xAxis = date;
		if(time == 'default'){
			jsonData.title.text = '收入与支出本月统计';
		}else if(time == 'year'){
			jsonData.title.text = '收入与支出年统计';
		}else if(time == 'quarter'){
			jsonData.title.text = '收入与支出季度统计';
		}else if(time == 'month'){
			jsonData.title.text = '收入与支出月统计';
		}else if(time == 'week'){
			jsonData.title.text = '收入与支出周统计';
		}else if(time == 'day'){
			jsonData.title.text = '收入与支出日统计';
		}

		Highcharts.chart(id, jsonData);
	}, "json");
}

function getColumnarChart(id, url, jsonData,time) {
	getLineBasicChart(id, url, jsonData,time);
}

function getPieChart(id, url, jsonData) {
	$.get(url, function (data) {
		jsonData.series[0].data = data.pies;
		Highcharts.chart(id, jsonData);
	}, "json");
}