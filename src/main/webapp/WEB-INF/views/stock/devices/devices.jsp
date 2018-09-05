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
								<i class="fa fa-search"></i>
								查询
							</button>
						</div>
					</div>
				</div>
				<div class="btnbar-tools">
					<a href="javascript:;" class="edit">
						<i class="fa fa-pencil-square info"></i>
						出库
					</a>
					<a href="javascript:;" class="del">
						<i class="fa fa-times-rectangle danger"></i>
						入库
					</a>
					<a href="javascript:;" class="count">
						<i class="fa fa-pie-chart purple"></i>
						导入
					</a>
					<a href="javascript:;" class="check">
						<i class="fa fa-check-circle yellow"></i>
						导出
					</a>
				</div>
				<table id="carDataGrid" class="table table-fixed table-dotted table-hover "></table>
			</div>
		</div>
	</div>
	<script src="../assets/js/jquery2.1.1.js" type="text/javascript"></script>
	<script src="../assets/js/jquery.easyui.min.js" type="text/javascript"></script>
	<!-- 	<script src="../assets/js/custom.js" type="text/javascript"></script> -->
	<script src="../assets/js/layer.js" type="text/javascript"></script>
	<!-- 	<script src="../assets/json/combobox_data.js" type="text/javascript"></script> -->
	<script type="text/javascript">
		var dataGrid;
		$(document).ready(function() {
			dataGrid = $('#carDataGrid').datagrid({
				method : 'post',
				url : '../devices/list',
				loadMsg : '数据加载中....',
				nowrap : true, // false:折行
				rownumbers : true, // 行号
				striped : true, // 隔行变色
				singleSelect : true,// 是否单选
				// 				height : 480,
				sortName : 'cstime',
				sortOrder : 'desc',
				pagination : true,
				pageSize : 20,
				pageList : [ 10, 20, 30, 50 ],
				columns : [ [ {
					field : '_opt',
					title : '明细',
					align : 'center',
					formatter : function(value, row, index) {
						return "<a href=\"javascript:void(0)\"><span  onclick=\"javaScript:isRecdFun('" + row.id + "');\">明细 </span></a>";
					}
				}, {
					field : 'packBm',
					title : '箱号'
				}, {
					field : 'listnum',
					title : '设备编号',
					width : '10%'
				}, {
					field : 'simNumber',
					title : '卡号',
					width : '10%'
				}, {
					field : 'departmentName',
					title : '库存网点',
					width : '15%'
				}, {
					field : 'state',
					title : '出库状态',
					width : '5%',
					align : 'center',
					formatter : function(value, row) {
						if (value == "1") {
							return "<div  style='background-color:#999999;text-align:center;margin:0px;padding:0px;color:#FFFFFF;'>未出库</div>";
						} else if (value == "2") {
							return "<div  style='background-color:green;text-align:center;margin:0px;padding:0px;color:#FFFFFF;'>已出库</div>";
						} else if (value == "3") {
							return "<div  style='background-color:#FF0000;text-align:center;margin:0px;padding:0px;color:#FFFFFF;'>退回</div>";
						}
					}
				}, {
					field : 'ustate',
					title : '使用状态',
					width : '5%',
					align : 'center',
					formatter : function(value, row) {
						if (value == "0") {
							return "<div  style='background-color:#999999;text-align:center;margin:0px;padding:0px;color:#FFFFFF;'>未使用</div>";
						} else if (value == "1") {
							return "<div  style='background-color:green;text-align:center;margin:0px;padding:0px;color:#FFFFFF;'>使用</div>";
						}
					}
				}, {
					field : 'ustype',
					title : '用途',
					width : '8%',
					align : 'center'
				}, {
					field : 'lyr',
					title : '领用人',
					width : '8%'
				}, {
					field : 'lytime',
					title : '领用时间',
					width : '8%'
				}, {
					field : 'remark',
					title : '备注'
				} ] ]
			});
			dataGrid.datagrid('getPager').pagination({
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