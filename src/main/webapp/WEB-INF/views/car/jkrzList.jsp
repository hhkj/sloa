<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="../assets/css/reset.css" rel="stylesheet" type="text/css" />
<link href="../assets/js/themes/default/easyui.css" rel="stylesheet" type="text/css" />
<link href="../assets/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
<link href="../assets/css/layout.css" rel="stylesheet" type="text/css" />
</head>
<body scroll="no" class="body-pd10">
	<div class="dataView-container">
		<div class="table-container">
			<div class="tabs-wrapper">
				<div class="comp-search-box">
					<div class="screen-top">
						<div class="colRow">
							<input id="txtNewPass" type="text" class="easyui-textbox" name="name" data-options="label:'设备编号'" />
						</div>
						<div class="colRow">
							<input id="numbers" type="text" class="easyui-textbox" name="name" data-options="label:'SIM卡号'" />
						</div>
						<div class="colRow">
							<input type="text" id="selest-item" name="" class="easyui-combobox" name="language" label="箱号" />
						</div>
						<div class="colRow">
							<button class="easyui-linkbutton btnDefault">
								<i class="fa fa-search"></i>查询
							</button>
						</div>
					</div>
				</div>
				<div class="btnbar-tools">
					<a href="javascript:;" class="edit"><i class="fa fa-pencil-square info"></i>编辑</a> <a href="javascript:;" class="del"><i class="fa fa-times-rectangle danger"></i>删除</a> <a href="javascript:;" class="count"><i class="fa fa-pie-chart purple"></i>统计</a> <a href="javascript:;" class="check"><i class="fa fa-check-circle yellow"></i>审核</a>
				</div>
				<table id="carDataGrid" class="table table-fixed table-dotted table-hover "></table>
			</div>
		</div>
	</div>
	<script src="../assets/js/jquery2.1.1.js" type="text/javascript"></script>
	<script src="../assets/js/jquery.easyui.min.js" type="text/javascript"></script>
	<!-- 	<script src="../assets/js/custom.js" type="text/javascript"></script> -->
	<script src="../assets/js/layer.js" type="text/javascript"></script>
	<script src="../assets/json/combobox_data.js" type="text/javascript"></script>
	<script type="text/javascript">
		var carDataGrid;
		$(document).ready(function() {
			carDataGrid = $('#carDataGrid').datagrid({
				method : 'post',
				// 				url : '../vehicle/list',
				loadMsg : '数据加载中....',
				nowrap : true, // false:折行
				rownumbers : true, // 行号
				striped : true, // 隔行变色
				singleSelect : true,// 是否单选
				height : 480,
				pagination : true,
				pageSize : 10,
				pageList : [ 10, 20, 30, 50 ],
				columns : [ [ {
					field : '_opt',
					title : '明细',
					align : 'center',
				}, {
					field : 'carNumber',
					title : '日期',
					width : '10%'
				}, {
					field : 'carNumber',
					title : '类型',
					width : '10%'
				}, {
					field : 'carNumber1',
					title : '车牌号',
					width : '15%'
				}, {
					field : 'carType',
					title : '卡号',
					width : '15%'
				},  {
					field : 'contacts',
					title : '所属地区',
					width : '8%'
				}, {
					field : 'contactsTel',
					title : '所属公司',
					width : '8%'
				}, {
					field : 'contactsTel',
					title : '车主姓名',
					width : '8%'
				}, {
					field : 'contactsTel',
					title : '车主电话',
					width : '8%'
				} ] ]
			});
			carDataGrid.datagrid('getPager').pagination({
				beforePageText : '第',// 页数文本框前显示的汉字
				afterPageText : '页    共 {pages} 页',
				displayMsg : '当前显示 {from} - {to} 条记录   共 {total} 条记录'
			});
		});

		function lockInfo() {

			layer.open({
				type : 2,
				skin : 'layui-layer-rim', //加上边框
				area : [ '890px', '505px' ], //宽高
				content : '../vehicle/vehicleInfo',
				zIndex : 1000
			});
		}
	</script>
</body>
</html>