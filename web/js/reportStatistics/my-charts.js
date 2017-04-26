function getLineBasicChart(id, url, jsonData) {
	$.get(url, function (data) {
		jsonData.series = data;
		jsonData.xAxis = data;
		jsonData.title = data;
		Highcharts.chart(id, jsonData);
	}, "json");
}

function getColumnarChart(id, url, jsonData) {
	getLineBasicChart(id, url, jsonData);
}

function getPieChart(id, url, jsonData) {
	$.get(url, function (data) {
		jsonData.series[0].data = data.pies;
		Highcharts.chart(id, jsonData);
	}, "json");
}