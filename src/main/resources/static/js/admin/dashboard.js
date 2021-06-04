$(document).ready(function() {
	google.charts.load('current', {
		'packages': ['corechart']
	});
	google.charts.setOnLoadCallback(drawChartDoanhThu);
	google.charts.setOnLoadCallback(drawChartTuongTac);
	google.charts.setOnLoadCallback(drawChartTinTuyenDung);
	google.charts.setOnLoadCallback(drawChartThongKeCV);

	/*<![CDATA[*/

	var dataChartDoanhThu = /*[[${dataDoanhThu}]]*/'default';
	var dataChartTuongTac = /*[[${dataTuongTac}]]*/'default';
	var dataChartTinTuyenDung = /*[[${dataTinTuyenDung}]]*/'default';
	var dataChartThongKe = /*[[${dataThongKe}]]*/'default';

	/*]]>*/

	function drawChartDoanhThu() {
		var data = new google.visualization.DataTable();
		data.addColumn('string', 'X');
		data.addColumn('number', 'Cats');

		console.log(dataChartDoanhThu)
		var ds = [];
		for (var i = 0; i < dataChartDoanhThu.length; i++) {
			ds.push([dataChartDoanhThu[i].label, dataChartDoanhThu[i].value])
		}

		data.addRows(ds);

		var options = {
			colors: ['#85CA39'],
			backgroundColor: 'transparent',
			legend: {
				position: 'none'
			},
		};

		var chart = new google.visualization.LineChart(document
			.getElementById('chartDoanhThu'));

		chart.draw(data, options);
	}

	function drawChartTuongTac() {
		var data = new google.visualization.DataTable();
		data.addColumn('string', 'X');
		data.addColumn('number', 'Cats');

		var ds = [];
		for (var i = 0; i < dataChartTuongTac.length; i++) {
			ds.push([dataChartTuongTac[i].label, dataChartTuongTac[i].value])
		}

		data.addRows(ds);

		var options = {
			colors: ['#85CA39'],
			backgroundColor: 'transparent',
			legend: {
				position: 'none'
			}
		};

		var chart = new google.visualization.ColumnChart(document
			.getElementById('chartTuongTac'));

		chart.draw(data, options);
	}

	function drawChartTinTuyenDung() {
		var data = new google.visualization.DataTable();
		data.addColumn('string', 'X');
		data.addColumn('number', 'Cats');

		var ds = [];
		for (var i = 0; i < dataChartTinTuyenDung.length; i++) {
			ds.push([dataChartTinTuyenDung[i].label,
			dataChartTinTuyenDung[i].value])
		}

		data.addRows(ds);

		var options = {
			colors: ['#85CA39'],
			backgroundColor: 'transparent',
			legend: {
				position: 'none'
			}
		};

		var chart = new google.visualization.LineChart(document
			.getElementById('chartTinTuyenDung'));

		chart.draw(data, options);
	}

	function drawChartThongKeCV() {
		var data = google.visualization.arrayToDataTable([
			['Task', 'Hours per Day'], ['Work', 11], ['Eat', 2],

			['Commute', 2], ['Watch TV', 2], ['Sleep', 7]]);

		var ds = [];
		for (var i = 0; i < dataChartTinTuyenDung.length; i++) {
			ds.push([dataChartThongKe[i].label,
			dataChartThongKe[i].value])
		}

		data.addRows(ds);

		var options = {
			backgroundColor: 'transparent',
			legend: {
				position: 'right'
			},
		};

		var chart = new google.visualization.PieChart(document
			.getElementById('chartThongKeCV'));

		chart.draw(data, options);
	}
});
