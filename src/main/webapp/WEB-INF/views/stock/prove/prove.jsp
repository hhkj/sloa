<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>证明管理</title>
<link href="../assets/css/reset.css" rel="stylesheet" type="text/css" />
<link href="../assets/js/themes/default/easyui.css" rel="stylesheet" type="text/css" />
<link href="../assets/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
<link href="../assets/css/layout.css" rel="stylesheet" type="text/css" />
<script src="../assets/js/jquery2.1.1.js" type="text/javascript"></script>
<script src="../assets/js/jquery.easyui.min.js" type="text/javascript"></script>
<!-- 	<script src="../assets/js/custom.js" type="text/javascript"></script> -->
<script src="../assets/js/layer.js" type="text/javascript"></script>
<script type="text/javascript">
	var grid;
	$(function() {
		loadDataGrid();
	});
	function loadDataGrid() {
		grid = $('#proveDataGrid').datagrid({
			method : 'post',
			url : '../prove/list',
			// 			fit : true,
// 			height : 480,
			fitColumns : true,
			nowrap : true, // false:折行
			rownumbers : true, // 行号
			striped : true, // 隔行变色
			pagination : true,
			// singleSelect : true,
			checkOnSelect : true,
			autoRowHeight : false,// 设置行的高度，根据该行的内容。设置为false可以提高负载性能。
			pageSize : 20,
			pageList : [ 10, 20, 30, 50, 100 ],
			loadMsg : '数据加载中,请稍后……',
			columns : [ [ {
				field : 'ck',
				checkbox : true
			}, {
				field : '_img',
				title : '照片',
				align : 'center',
				formatter : function(value, row, index) {
					if (row.imgPath != null) {
						return "<a  href=\"javascript:void(0)\"  onclick=\"javaScript:showImg('" + index + "');\"><img  src=\"../images/img_inc.png\" /></a>";
					}
					return "";
				}
			},  {
				field : 'state',
				title : '出库状态',
				align : 'center',
				formatter : function(value, row) {
					var s = "";
					if (value == "0") {
						s = "<span  style='color:blue;'>未出库</span>";
					} else if (value == "1") {
						s = "<span  style='color:green;'>已出库</span>";
					} else if (value == "2") {
						s = "<span  style='color:read;'>已回收</span>";
					}
					return s;
				}
			},{
				field : 'ustate',
				title : '使用状态',
				align : 'center',
				formatter : function(value, row) {
					var s = "";
					if (value == "0") {
						s = "<div  style='background-color:#CD3333;text-align:center;margin:0px;padding:0px;color:#FFFFFF;'>未使用</div>";
					} else if (value == "1") {
						s = "<div  style='background-color:green;text-align:center;margin:0px;padding:0px;color:#FFFFFF;'>已使用</div>";
					} else if (value == "3") {
						s = "<div  style='background-color:#aaaaaa;text-align:center;margin:0px;padding:0px;color:#FFFFFF;'>已作废</div>";
					}
					return s;
				}
			}, {
				field : 'ustype',
				title : '类型'
			}, {
				field : 'number',
				title : '编号'
			}, {
				field : 'usriqi',
				title : '出库日期'
			}, {
				field : 'departmentName',
				title : '领用部门'
			}, {
				field : 'usman',
				title : '领用人'
			}, {
				field : 'purpose',
				title : '用途',
				formatter : function(value, row, index) {
					var s = "";
					if (row.purpose == 1) {
						s = "续费";
					}
					if (row.purpose == 0) {
						s = "新装";
					}
					return s;
				}
			}, {
				field : 'carNumber',
				title : '车牌号'
			}, {
				field : 'remark',
				title : '备注'
			}, {
				field : 'fees',
				title : '是否收款',
				align : 'center',
				formatter : function(value, row, index) {
					var s = "";
					if (row.fees == 1) {
						s = "<div  style='background-color:green;text-align:center;margin:0px;padding:0px;color:#FFFFFF;'>已收款</div>";
					}
					return s;
				}
			}, {
				field : 'payee',
				title : '收款人'
			}, {
				field : '_pp',
				title : '出入库明细',
				// width : 100,
				align : 'center',
				formatter : function(value, row, index) {
					var str = "";
					str += "<a href=\"javascript:void(0)\"><span  onclick=\"javaScript:isRecdFun('" + row.id + "');\">明细 </span></a>";
					return str;

				}
			} ] ],
			toolbar : '#td_toolbar',
			onLoadSuccess : function(data) {
				grid.datagrid('clearSelections');
				if (data.total == 0) {
					var body = $(this).data().datagrid.dc.body2;
					body.find('table tbody').append('<tr><td width="' + body.width() + '" style="height: 35px; text-align: center;"><h1>暂无数据</h1></td></tr>');
				}
			}
		});
		// 设置分页控件
		grid.datagrid('getPager').pagination({
			beforePageText : '第',// 页数文本框前显示的汉字
			afterPageText : '页    共 {pages} 页',
			displayMsg : '当前显示 {from} - {to} 条记录   共 {total} 条记录'
		});
	}
	/*入库批量、单加控制显示*/
	function closedDiv() {
		// 		$('#listnum').show();
		$('#listdiv').hide();
	}
	function showDiv() {
		$('#listdiv').show();
		// 		$('#listnum').hide();
	}
	// 弹出添加页面
	function rukuFun() {
		SL.showWindow({
			title : '添加',
			iconCls : 'icon-add',
			width : 450,
			height : 240,
			url : '../prove/toAddPage',

			onLoad : function() {
				$('#ustypeWin').combobox({
					url : '../data/ustypeJson.json',
					valueField : 'id',
					textField : 'text',
					panelHeight : 300,
					editable : false
				});
			},
			buttons : [ {
				text : '保存',
				iconCls : 'icon-save',
				handler : function() {
					if ($("#zmPostForm").form('enableValidation').form('validate')) {
						var data = $("#zmPostForm").serialize();
						$.ajax({
							cache : false,
							type : "POST",
							url : '../prove/save',
							data : data,
							async : false,
							dataType : 'json',
							success : function(data) {
								if (data.success) {
									SL.closeWindow();
									grid.datagrid('load');
								} else {
									$.messager.alert('提示', '保存失败，请检查信息是否正确，或者证明编号已经存在。');
								}
							}
						});
					}
				}
			}, {
				text : '关闭',
				handler : function() {
					SL.closeWindow();
				}
			} ]
		});
	};
	/*出库方法*/
	function chukuFun() {
		var selRow = grid.datagrid("getSelections");// 返回选中多行
		if (selRow.length == 0) {
			$.messager.alert({
				title : '提示',
				msg : '未选中设备，请至少选择一行数据。',
				showType : 'slide',
				top : 200,
				left : 400
			});
			return;
		}
		var ids = [];
		for (var i = 0; i < selRow.length; i++) {
			if (selRow[i].state != "1") {
				var id = selRow[i].id;
				ids.push(id);
			}
		}
		if (ids.length == 0) {
			$.messager.alert({
				title : '提示',
				msg : '该设备已出库或已使用或未检测，请选择正确数据。',
				showType : 'slide',
				top : 200,
				left : 400
			});
			return;
		} else if (ids.length != selRow.length) {
			$.messager.alert({
				title : '提示',
				msg : '部分设备已使用或已出库或未检测，将无法出库，请选择正确数据。',
				showType : 'slide',
				top : 200,
				left : 400
			});
			return;
		} else {
			openChukuWin(ids);
		}
	}
	/*弹出出库窗口*/
	function openChukuWin(ids) {
		SL.showWindow({
			title : '设备出库信息',
			iconCls : 'icon-add',
			width : 420,
			height : 280,
			url : '../prove/toChukuPage',
			onLoad : function() {
				$("#ck_department").combotree({
					url : '../dep/getDepTree',
					required : true,
					lines : true
				});
			},
			buttons : [ {
				text : '确定',
				iconCls : 'icon-add',
				handler : function() {
					if ($("#ckform").form('enableValidation').form('validate')) {
						var param = {
							ids : ids,
							state : '1',
							department : $('#ck_department').combotree('getValue'),
							usman : $('#lyr').val(),
							usriqi : $('#ck_lytime').val(),
							remark : $('#remark').val(),
							site : $('#site').val()
						};
						$.ajax({
							cache : false,
							type : "POST",
							url : "../prove/useSave",
							data : param,
							dataType : "json",
							cache : false,
							success : function(data) {
								if (data) {
									SL.closeWindow();
									grid.datagrid('reload');
								}
							}
						});
					} else {
						alert("请填写完整信息");
					}
				}
			}, {
				text : '关闭',
				handler : function() {
					SL.closeWindow();
				}
			} ]
		});
	}
	// 撤回
	function revoke() {
		// 得到选中的行
		var selRow = grid.datagrid("getSelections");// 返回选中多行
		if (selRow.length == 0) {
			alert("请至少选择一行数据!");
			return false;
		}
		var ids = [];
		for (var i = 0; i < selRow.length; i++) {
			var id = selRow[i].id;
			ids.push(id);
		}
		var param = {
			ids : ids
		};
		if (confirm("确定要恢复数据吗？")) {
			$.ajax({
				cache : false,
				type : "POST",
				url : "../prove/revoke",
				data : param,
				dataType : "json",
				cache : false,
				success : function(data) {
					if (data) {
						grid.datagrid('reload');
					}
				}
			});
		}
	}
	//作废
	function canceled() {
		var selRow = grid.datagrid("getSelections");// 返回选中多行
		if (selRow.length == 0) {
			alert("请至少选择一行数据!");
			return false;
		}
		var ids = [];
		for (var i = 0; i < selRow.length; i++) {
			var id = selRow[i].id;
			ids.push(id);
		}
		var param = {
			ids : ids,
			state : '3'
		};
		if (confirm("确定要将证明作废吗？")) {
			$.ajax({
				cache : false,
				type : "POST",
				url : "../prove/canceled",
				data : param,
				dataType : "json",
				cache : false,
				success : function(data) {
					if (data) {
						grid.datagrid('reload');
					}
				}
			});
		}
	}
	/*弹出出入库记录*/
	function isRecdFun(id) {
		SL.showWindow({
			title : '出入库明细',
			width : 720,
			height : 380,
			url : '../prove/toDetailsPage',
			onLoad : function() {
				loadDetails(id);
			},
			buttons : [ {
				text : '关闭',
				handler : function() {
					SL.closeWindow();
				}
			} ]
		});
	}
	function loadDetails(id) {
		$('#detailsGrid').datagrid({
			url : '../prove/getListRecd',
			method : 'POST',
			queryParams : {
				'listnum' : id
			},
			fit : true,
			striped : true,
			fitColumns : true,
			singleSelect : false,
			rownumbers : true,
			pagination : false,
			nowrap : false,
			pageSize : 10,
			pageList : [ 10, 20, 50, 100, 150, 200 ],
			showFooter : true,
			columns : [ [ {
				field : 'site',
				title : '库存网点'
			}, {
				field : 'ustate',
				title : '使用状态',
				align : 'center',
				formatter : function(value, row) {
					var s = "";
					if (value == "1") {
						s = "<span  style='color:green;'>出库</span>";
					} else if (value == "2") {
						s = "<span  style='color:blue;'>退回</span>";
					}
					return s;
				}
			}, {
				field : 'cktime',
				title : '出库时间',
				sortable : true,
				order : 'desc',
			}, {
				field : 'departmentname',
				title : '领用部门'
			}, {
				field : 'lyr',
				title : '领用人'
			}, {
				field : 'qsstate',
				title : '签收',
				align : 'center',
				formatter : function(value, row) {
					var s = "";
					if (value == "1") {
						s = "<span  style='color:green;'>已签收</span>";
					} else if (value == "0") {
						s = "<span  style='color:blue;'>待收货</span>";
					}
					return s;
				}
			}, {
				field : 'qstime',
				title : '签收时间'
			}, {
				field : 'qsr',
				title : '签收人'
			} ] ]
		});
	}
	function canceled() {
		var selRow = grid.datagrid("getSelections");// 返回选中多行
		if (selRow.length == 0) {
			alert("请至少选择一行数据!");
			return false;
		}
		var ids = [];
		for (var i = 0; i < selRow.length; i++) {
			var id = selRow[i].id;
			ids.push(id);
		}
		var param = {
			ids : ids,
			state : '3'
		};
		if (confirm("确定要将证明作废吗？")) {
			$.ajax({
				cache : false,
				type : "POST",
				url : "../prove/canceled",
				data : param,
				dataType : "json",
				cache : false,
				success : function(data) {
					if (data) {
						grid.datagrid('reload');
					}
				}
			});
		}
	}
	/*弹出退回窗口*/
	function openBackWin(ids) {
		SL.showWindow({
			title : '设备退回信息',
			iconCls : 'icon-add',
			width : 420,
			height : 280,
			url : 'ChuKu.jsp',
			onLoad : function() {
				$("#ck_department").combotree({
					url : '../dep/getDepTree',
					required : true,
					lines : true
				});
			},
			buttons : [ {
				text : '确定',
				iconCls : 'icon-add',
				handler : function() {
					if ($("#ckform").form('enableValidation').form('validate')) {
						var param = {
							ids : ids,
							state : '2',
							department : $('#site').val(),
							remark : $('#remark').val(),
							usman : $('#lyr').val(),
							usriqi : $('#ck_lytime').val(),
							site : $('#ck_department').combotree('getValue')

						};
						$.ajax({
							cache : false,
							type : "POST",
							url : "../prove/useSave",
							data : param,
							dataType : "json",
							cache : false,
							success : function(data) {
								if (data) {
									SL.closeWindow();
									grid.datagrid('reload');
								}
							}
						});
					}
				}
			}, {
				text : '关闭',
				handler : function() {
					SL.closeWindow();
				}
			} ]
		});
	}
	// 导入excel
	function openExcel() {
		$("#picForm").form('clear');
		$('#uploadPicWindow').window('open');
	}
	// 导出excel
	function outExcel() {
		// 获取条件
		var param = $("#gridForm").serialize();// form序列化
		window.location.href = encodeURI("../excel/exporzm?" + param);
	}
	// 保存ecxel
	function submitPic() {
		if (!$("#picForm").form('validate')) {
			return false;
		}
		var f = $("#file").val();
		if (f == null || f == "") {
			$("#picTip").html("<span style='color:Red'>错误提示:上传文件不能为空,请重新选择文件</span>");
			return false;
		} else {
			var d1 = /\.[^\.]+$/.exec(f);
			if (d1 == ".xls") {
			} else {
				$("#picTip").html("<span style='color:Red'>错误提示:格式不正确,支持的格式为：xls！</span>");
				return false;
			}
		}
		ajaxFileUploadPic();
	}
	function ajaxFileUploadPic() {
		$.ajaxFileUpload({
			url : '../excel/upprove',// 用于文件上传的服务器端请求地址
			secureuri : false, // 一般设置为false
			fileElementId : 'file', // 文件上传空间的id属性 <input type="file" id="file"
			type : 'post',
			dataType : 'jsonp', // 返回值类型 一般设置为json
			success : function(data) {
				// 服务器成功响应处理函数
				grid.datagrid('reload');
				$('#uploadPicWindow').window('close');
			}
		});
		return false;
	}
	function excelMB() {
		window.location.href = "../excel/PROVE.xls";
	}
	/*编辑方法*/
	function editFun() {
		var selRow = grid.datagrid("getSelections");// 返回选中多行
		if (selRow.length != 1) {
			$.messager.alert({
				title : '提示',
				msg : '请选择一行数据。',
				showType : 'slide',
				top : 200,
				left : 400
			});
			return;
		}
		openeditWin(selRow[0]);
	}
	/*弹出编辑窗口*/
	function openeditWin(data) {
		SL.showWindow({
			title : '编辑证明',
			iconCls : 'pic_140',
			width : 420,
			height : 500,
			url : '../prove/toEdit',
			onLoad : function() {
				$("#p_department").combotree({
					url : '../dep/getDepTree',
					required : true,
					lines : true
				});

				$('#editform').form('load', data);
			},
			buttons : [ {
				text : '确定',
				iconCls : 'icon-add',
				handler : function() {
					if ($("#editform").form('enableValidation').form('validate')) {
						var param = {
							id : $("#proveid").val(),
							ustate : $('#p_ustate').combobox('getValue'),
							ustype : $("#p_ustype").val(),
							number : $("#p_number").val(),
							state : $('#p_state').combobox('getValue'),
							usriqi : $('#p_usriqi').val(),
							department : $('#p_department').combobox('getValue'),
							usman : $('#p_usman').val(),
							purpose : $('#p_purpose').combobox('getValue'),
							carNumber : $('#p_carNumber').val(),
							remark : $('#p_remark').val(),
							fees : $('#p_fees').combobox('getValue'),
							payee : $('#p_payee').val(),
						};
						$.ajax({
							cache : false,
							type : "POST",
							url : "../prove/editSave",
							data : param,
							dataType : "json",
							cache : false,
							success : function(data) {
								if (data) {
									SL.closeWindow();
									grid.datagrid('reload');
								}
							}
						});
					}
				}
			}, {
				text : '关闭',
				handler : function() {
					SL.closeWindow();
				}
			} ]
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
							<input id="txtNewPass" type="text" class="easyui-textbox" name="name" data-options="label:'证明编号：'" />
						</div>
						<div class="colRow">
							<input id="numbers" type="text" class="easyui-textbox" name="name" data-options="label:'证明类型'" />
						</div>
						<div class="colRow">
							<input type="text" id="selest-item" name="" class="easyui-combobox" name="language" label="所在部门" />
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
						编辑
					</a>
					<a href="javascript:;" class="del">
						<i class="fa fa-times-rectangle danger"></i>
						删除
					</a>
					<a href="javascript:;" class="count">
						<i class="fa fa-pie-chart purple"></i>
						统计
					</a>
					<a href="javascript:;" class="check">
						<i class="fa fa-check-circle yellow"></i>
						审核
					</a>
				</div>
				<table id="proveDataGrid" class="table table-fixed table-dotted table-hover "></table>
			</div>
		</div>
	</div>
</body>
</html>