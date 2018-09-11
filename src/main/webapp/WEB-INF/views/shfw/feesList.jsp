<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>缴费管理</title>
<link href="../assets/css/reset.css" rel="stylesheet" type="text/css" />
<link href="../assets/js/themes/default/easyui.css" rel="stylesheet" type="text/css" />
<link href="../assets/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
<link href="../assets/css/layout.css" rel="stylesheet" type="text/css" />
<script src="../assets/js/jquery2.1.1.js" type="text/javascript"></script>
<script src="../assets/js/jquery.easyui.min.js" type="text/javascript"></script>
<!-- 	<script src="../assets/js/custom.js" type="text/javascript"></script> -->
<script src="../assets/js/layer.js" type="text/javascript"></script>
<script src="../assets/json/combobox_data.js" type="text/javascript"></script>
<!-- <script src="../js/DateUtil.js" type="text/javascript"></script> -->
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">
	var grid;
	$(function() {
		// 加载列表数据
		loadDataGrid();
		// 点击查询，根据条件检索
		$("#search_btn").click(function() {
			$('#listGrid').datagrid({
				queryParams : {
					carNumber : $('#carNumberText').val(),
					number : $('#numberTop').val(),
					endTime : $('#endTime').val(),
					endTime1 : $('#endTime1').val()
// 					department : $("#h_department").combotree("getValue"),
// 					purpose : $('#purpose').combobox('getValue'),
// 					fees : $('#fees').combobox('getValue')
				}
			});
			$('#gridForm').form('clear');
		});
		$('#newData').on('click', function() {
			layer.open({
				type : 2,
				skin : 'layui-layer-rim', //加上边框
				area: ['490px', '450px'], //宽高
				content : '../fees/toFeesAddPages',
				zIndex : 1000
			});
		});
	});
	function clearForm() {//重置表单
		$('#vui_sample').form('clear');
	}

	/*加载数据*/
	function loadDataGrid() {
		grid = $('#listGrid').datagrid({
			url : '../fees/list',
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
				field : 'fees',
				title : '入账状态',
				align : 'center',
				formatter : function(value, row, index) {
					if (row.fees == 0) {
						return "<div  style='background-color:gray;text-align:center;margin:0px;padding:0px;color:#FFFFFF;'>待确认</div>"
					} else if (row.fees == 1) {
						return "<div  style='background-color:green;text-align:center;margin:0px;padding:0px;color:#FFFFFF;'>已入网</div>";
					}
				}
			}, {
				field : 'carNumber',
				title : '车牌号'
			}, {
				field : 'purpose',
				title : '项目',
				align : 'center',
				width : 50,
				formatter : function(value, row, index) {
					if (row.purpose == 0) {
						return "<div  style='background-color:green;text-align:center;margin:0px;padding:0px;color:#FFFFFF;'>新装</div>"
					} else if (row.purpose == 1) {
						return "<div  style='background-color:blue;text-align:center;margin:0px;padding:0px;color:#FFFFFF;'>续费</div>"
					} else if (row.purpose == 2) {
						return "<div  style='background-color:gray;text-align:center;margin:0px;padding:0px;color:#FFFFFF;'>续费</div>"
					}
				}
			}, {
				field : 'number',
				title : '证明编号'
			}, {
				field : 'owner',
				title : '业户名称'
			}, {
				field : 'colAmounts',
				title : '代收金额',
				align : 'right',
				styler : function(value, row, index) {
					return 'color:red;';
				},
				formatter : function(val, row, index) {
					return row.colAmounts == 0 ? "" : '&yen; ' + row.colAmounts;

				}
			}, {
				field : '_yxq',
				title : '服务期限',
				formatter : function(value, row, index) {
					return row.startTime + " 至 " + row.endTime;
				}
			}, {
				field : 'agent',
				title : '收款人'
			}, {
				field : 'riqi',
				title : '缴费时间'
			}, {
				field : 'remark',
				title : '备注'
			} ] ],
			onLoadSuccess : function() {
				grid.datagrid('clearSelections');
			}
		});
		// 设置分页控件
		grid.datagrid('getPager').pagination({
			beforePageText : '第',// 页数文本框前显示的汉字
			afterPageText : '页    共 {pages} 页',
			displayMsg : '当前显示 {from} - {to} 条记录   共 {total} 条记录'
		});
	}
</script>
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
							<input id="numberTop" type="text" class="easyui-textbox" data-options="label:'证明编号：'" />
						</div>
						<div class="colRow">
<!-- 							<input class="easyui-textbox" id="endTime" name="subject" style="width:100%" data-options="label:'存储期限:'"> -->
<!-- 							 -  -->
<!-- 							<input class="easyui-textbox" id="endTime1" name="subject" style="width:100%"> -->
							到期时间：
							<input id="endTime" class="Wdate" type="text" onFocus="WdatePicker({isShowClear:false,readOnly:true})" /> 
							- 
							<input id="endTime1" class="Wdate" type="text" onFocus="WdatePicker({isShowClear:false,readOnly:true})" />
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
					<a href="javascript:;" class="edit" id="newData">
						<i class="fa fa-plus-square success"></i>
						添加
					</a>
					<a href="javascript:;" class="edit" id="editRows">
						<i class="fa fa-pencil-square info"></i>
						编辑
					</a>
				</div>
				<table id="listGrid" class="table table-fixed table-dotted table-hover "></table>
			</div>
		</div>
	</div>
</body>
</html>