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
<script src="../assets/js/jquery2.1.1.js" type="text/javascript"></script>
<script src="../assets/js/jquery.easyui.min.js" type="text/javascript"></script>
<!-- 	<script src="../assets/js/custom.js" type="text/javascript"></script> -->
<script src="../assets/js/layer.js" type="text/javascript"></script>
<script src="../assets/json/combobox_data.js" type="text/javascript"></script>
<script type="text/javascript">
	var carDataGrid;
	$(function() {
		//新增数据
		$('#editRows').on('click', function() {
			editRowsFun();
		});
		$('#deleteRows').on('click', function() {
			deleteRowsFun();
		});
		$("#search_btn").click(function() {
			$('#carDataGrid').datagrid({
				queryParams : {
					carOwner : $('#carOwnerText').val(),
					equitment : $('#eqText').val(),
					carVin : $('#carVinText').val(),
					carNumber : $('#carNumberText').val()
				}
			});

		});
		loadDataGrid();
	});
	//查询数据列表
	function loadDataGrid() {
		carDataGrid = $('#carDataGrid').datagrid({
			method : 'post',
			url : '../vehicle/list',
			loadMsg : '数据加载中....',
			// 				fit : true,
			nowrap : true, // false:折行
			rownumbers : true, // 行号
			striped : true, // 隔行变色
			singleSelect : true,// 是否单选
			// 				height : 480,
			pagination : true,
			pageSize : 20,
			pageList : [ 10, 20, 30, 50 ],
			columns : [ [ {
				field : '_opt',
				title : '查看',
				align : 'center',
				formatter : function(value, row, index) {
					return '<a href="javascript:;" title="查看" class="green" onclick="lockInfo(\'' + row.id + '\')"><i class="fa fa-eye"></i></a>';
				}
			}, {
				field : 'carNumber',
				title : '车牌号',
				width : '100',
				formatter : function(value, row, index) {
					//0：蓝色  1：黄色  2：黑色 3：白色
					if (row.plateColor == 0) {
						return "<div  style='background-color:blue;text-align:center;color:#FFFFFF;border:1px solid;border-radius:5px;'>" + row.carNumber + "</div>";
					}
					if (row.plateColor == 1) {
						return "<div  style='background-color:yellow;text-align:center;border:1px solid;border-radius:5px;'>" + row.carNumber + "</div>";
					}
					if (row.plateColor == 2) {
						return "<div  style='background-color:black;text-align:center;color:#FFFFFF;border:1px solid;border-radius:5px;'>" + row.carNumber + "</div>";
					}
					if (row.plateColor == 2) {
						return "<div  style='background-color:white;text-align:center;border:1px solid;border-radius:5px;'>" + row.carNumber + "</div>";
					}
				}
			}, {
				field : 'carType',
				title : '车辆类型',
				width : '10%',
				align : 'center'
			}, {
				field : 'carVin',
				title : '车架号'
			}, {
				field : 'companyName',
				title : '所属公司名称',
				width : '20%'
			}, {
				field : 'carOwner',
				title : '车主',
				width : '20%'
			}, {
				field : 'contacts',
				title : '联系人',
				width : '25%'
			}, {
				field : 'contactsTel',
				title : '车主电话'
			} ] ]
		});
		carDataGrid.datagrid('getPager').pagination({
			beforePageText : '第',// 页数文本框前显示的汉字
			afterPageText : '页    共 {pages} 页',
			displayMsg : '当前显示 {from} - {to} 条记录   共 {total} 条记录'
		});
	}
	function editRowsFun() {
		var data = carDataGrid.datagrid('getSelected');
		if (data == null) {
			layer.msg('请选择一条数据！', {
				time : 5000, //20s后自动关闭
			});
			return;
		}
		layer.open({
			type : 2,
			skin : 'layui-layer-rim', //加上边框
			area : [ '990px', '505px' ], //宽高
			content : '../vehicle/vehicleEdit?id=' + data.id,
			zIndex : 1000
		});
	}
	function deleteRowsFun() {
		layer.confirm('删除后车辆信息无法恢复！', {
			btn : [ '确定', '关闭' ]
		//按钮
		}, function(index) {
			var data = carDataGrid.datagrid('getSelected');
			$.ajax({
				url : "../vehicle/delete?id=" + data.id,
				success : function(data) {
					if (data) {
						carDataGrid.datagrid('load');
						layer.close(index);
					}
				}
			});
		});
	}
	//查看明细
	function lockInfo(id) {
		layer.open({
			type : 2,
			skin : 'layui-layer-rim', //加上边框
			area : [ '990px', '505px' ], //宽高
			content : '../vehicle/vehicleInfo?id=' + id + '&type=info',
			zIndex : 1000
		});
	}
</script>
</head>
<body scroll="no" class="body-pd10">
	<div class="dataView-container">
		<div class="table-container">
			<div class="tabs-wrapper">
				<div class="comp-search-box">
					<div class="screen-top">
						<div class="colRow">
							<input id="carNumberText" type="text" class="easyui-textbox" data-options="label:'车牌号：'" />
						</div>
						<div class="colRow">
							<input id="carVinText" type="text" class="easyui-textbox" data-options="label:'车架号：'" />
						</div>
						<div class="colRow">
							<input id="eqText" type="text" class="easyui-textbox" data-options="label:'设备编号：'" />
						</div>
						<div class="colRow">
							<input id="carOwnerText" type="text" class="easyui-textbox" label="车主姓名：" />
						</div>
						<div class="colRow">
							<button class="easyui-linkbutton btnDefault" id="search_btn">
								<i class="fa fa-search"></i>
								查询
							</button>
						</div>
					</div>
				</div>
				<div class="btnbar-tools">
					<a href="javascript:;" class="edit" id="editRows">
						<i class="fa fa-pencil-square info"></i>
						编辑
					</a>
					<a href="javascript:;" class="del" id="deleteRows">
						<i class="fa fa-times-rectangle danger"></i>
						删除
					</a>
				</div>
				<table id="carDataGrid" class="table table-fixed table-dotted table-hover "></table>
				<table class="layui-hide" id="test"></table>
			</div>
		</div>
	</div>

</body>
</html>