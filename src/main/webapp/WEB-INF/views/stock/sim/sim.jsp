<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.o../html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>SIM卡管理</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/jsjs/ajaxfileupload.js"></script>
<script type="text/javascript">
	$(function() {
		loadJSPButton();
		loadDataGrid();
		$("#sim_department").combotree({
			url : '../dep/getDepTree',
			lines : true
		});
		$("#rukuBtn").click(function() {
			rukuFun();
		});
		$("#chukuBtn").click(function() {
			chukuFun();
		});
		//编辑
		$("#editBtn").click(function() {
			editFun();
		});
		$("#revoke").click(function() {
			revoke();
		});
		//退回
		$("#backBtn").click(function() {
			backFun();
		});
		$("#daochuBtn").click(function() {
			outExcel();
		});
		$("#daoruBtn").click(function() {
			openExcel();
		});
		$("#search_btn").click(function() {
			$('#listGrid').datagrid({
				queryParams : {
					telnum : $('#telnum').val(),
					lyr : $('#lyr').val(),
					state : $('#state').combobox('getValue'),
					ustate : $('#ustate').combobox('getValue'),
					department : $("#sim_department").combotree("getValue")
				}
			});
			//	$('#gridForm').form('clear');
		});
	});

	function loadJSPButton() {
		var button = loadButton(29);
		var str = "";
		$.each(button.mButton, function(i, o) {
			str += '<a id="'+o.url+'" href="#" ' + 'class="easyui-linkbutton" data-options="iconCls:\'' + o.icon + '\',plain:\'true\'">' + o.name + '</a>';
		});
		var targetObj = $("#td_toolbar").append(str);
		$.parser.parse(targetObj);
	}

	function loadDataGrid() {
		grid = $('#listGrid').datagrid({
			method : 'post',
			url : '../sim/list',
			fit : true,
			nowrap : true, // false:折行
			rownumbers : true, // 行号
			striped : true, // 隔行变色
			pagination : true,
			pageSize : 20,
			pageList : [ 10, 15, 20, 30, 50, 100 ],
			loadMsg : '数据加载中,请稍后……',
			columns : [ [ {
				field : 'ck',
				checkbox : true
			}, {
				field : 'ustate',
				title : '出库状态',
				align : 'center',
				formatter : function(value, row) {
					var s = "";
					if (value == "0") {
						s = "<span  style='color:green;'>未出库</span>";
					} else if (value == "1") {
						s = "<span  style='color:blue;'>已出库</span>";
					} else if (value == "2") {
						s = "<span  style='color:read;'>已回收</span>";
					}
					return s;
				}
			}, {
				field : 'state',
				title : '使用状态',
				align : 'center',
				formatter : function(value, row) {
					var s = "";
					if (value == "0") {
						s = "<div  style='background-color:#999999;text-align:center;margin:0px;padding:0px;color:#FFFFFF;'>未用</div>";
					} else if (value == "1") {
						s = "<div  style='background-color:green;text-align:center;margin:0px;padding:0px;color:#FFFFFF;'>已用</div>";
					}
					return s;
				}
			}, {
				field : 'telnum',
				title : 'SIM号码'
			}, {
				field : 'lyr',
				title : '领用人'
			}, {
				field : 'departmentName',
				title : '领用部门'
			}, {
				field : 'outtime',
				title : '领用时间',
				sortable : true,
				order : 'desc',
			}, {
				field : 'lrr',
				title : '操作人'
			}, {
				field : 'remark',
				title : '备注'
			}, {
				field : 'opt',
				title : '出入库明细',
				align : 'center',
				formatter : function(value, row, index) {
					var str = "";
					str += "<a href=\"javascript:void(0)\"><span  onclick=\"javaScript:isRecdFun('" + row.telnum + "');\">明细 </span></a>";
					return str;
				}
			}
// 			, {
// 				field : 'opt1',
// 				title : '缴费记录',
// 				align : 'center',
// 				formatter : function(value, row, index) {
// 					var str = "";
// 					str += " <a href=\"javascript:void(0)\"><span onclick=\"javaScript:simRecord('" + index + "');\">记录</span></a>";
// 					return str;
// 				}
// 			}
			]],
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
			if (selRow[i].ustate != 1 && selRow[i].state != 1) {
				ids.push(selRow[i].id);
			}
		}
		if (ids.length == 0) {
			alert("请选择正确数据。");
			return;
		}
		if (ids.length != selRow.length) {
			alert("部分设备已出库或已使用，请选择正确数据");
			return;
		}
		openChukuWin(ids);

	}
	/*弹出入库窗口*/
	function rukuFun() {
		SL.showWindow({
			title : 'SIM卡入库信息',
			iconCls : 'icon-add',
			width : 380,
			height : 270,
			url : '../sim/toAddPage',
			buttons : [ {
				text : '确定',
				iconCls : 'icon-add',
				handler : function() {
					if ($("#devPostForm").form('enableValidation').form('validate')) {
						var data = $("#devPostForm").serialize();
						$.ajax({
							cache : false,
							type : "POST",
							url : '../sim/addSim',
							data : data,
							async : false,
							success : function(data) {
								if (data) {
									SL.closeWindow();
									grid.datagrid('reload');
								} else {
									alert("出错了");
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
	/*弹出出库窗口*/
	function openChukuWin(ids) {
		SL.showWindow({
			title : 'SIM卡出库信息',
			iconCls : 'icon-add',
			width : 420,
			height : 280,
			url : '../sim/toChukuPage',
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
							ustate : '1',
							department : $('#ck_department').combotree('getValue'),
							lyr : $('#ck_lyr').val(),
							outtime : $('#ck_lytime').val(),
							remark : $('#remark').val(),
							site : $('#site').val()
						};
						$.ajax({
							cache : false,
							type : "POST",
							url : "../sim/useSave",
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
	/*弹出出入库记录*/
	function isRecdFun(id) {
		SL.showWindow({
			title : '出入库明细',
			width : 720,
			height : 380,
			url : '../sim/toDetails', 
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
			url : '../sim/getListRecd',
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
	/*退回方法*/
	function backFun() {
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
			if (selRow[i].ustate == 1) {
				var id = selRow[i].id;
				ids.push(id);
			}
		}
		if (ids.length == 0) {
			$.messager.alert({
				title : '提示',
				msg : '该SIM卡未出库或者已退回，请选择正确数据。',
				showType : 'slide',
				top : 200,
				left : 400
			});
			return;
		} else if (ids.length != selRow.length) {
			$.messager.alert({
				title : '提示',
				msg : '该SIM卡未出库或者已退回，请选择正确数据。',
				showType : 'slide',
				top : 200,
				left : 400
			});
			return;
		} else {
			openBackWin(ids);
		} 
	}
	/*弹出退回窗口*/
	function openBackWin(ids) {
		SL.showWindow({
			title : '退回信息',
			iconCls : 'icon-add',
			width : 420,
			height : 280,
			url : '../sim/toBack',
			onLoad : function() { 
				$("#bk_department").combotree({ 
					url : '../dep/getDepTree',
					required : true,
					lines : true
				});
			},
			buttons : [ {
				text : '确定',
				iconCls : 'icon-add',
				handler : function() {
					if ($("#s_form").form('enableValidation').form('validate')) {
						var data = {
							ids : ids,
							ustate : '2',
							department : $('#bk_department').combotree('getValue'),
							remark : $('#remark').val(),
							lyr : $('#ck_lyr').val(),
							outtime : $('#ck_lytime').val(),
							site : $('#site').val()

						};
						$.ajax({
							cache : false,
							type : "POST",
							url : "../sim/useSave",
							data : data,
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
		window.location.href = encodeURI("../excel/exsim?" + param);
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
			url : '../excel/upsim',// 用于文件上传的服务器端请求地址
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
		window.location.href = "../excel/SIM.xls";
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
			title : '编辑SIM卡',
			iconCls : 'pic_140',
			width : 420,
			height : 380,
			url : '../sim/toEdit',
			onLoad : function() {
				$("#s_department").combotree({
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
							id :  $("#simid").val(), 
							ustate: $("#s_ustate").combobox('getValue'),
							state : $('#s_state').combobox('getValue'),
							telnum : $('#s_telnum').val(),
							lyr : $('#s_lyr').val(),
							department : $('#s_department').combobox('getValue'),
							outtime : $('#s_outtime').val(),
							lrr : $('#s_lrr').val(),
							remark : $('#s_remark').val(),
						};
						$.ajax({ 
							cache : false,
							type : "POST",
							url : "../sim/editSim",
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
<body class="easyui-layout">
	<div region="north" style="height: 80px; border-bottom: 1px #a7b1c2 dashed; padding: 5px;">
		<form id="gridForm">
			<div class="item">
				<div id="cot">
					<div class="shang">
						所在部门：<input id="sim_department" name="department" class="easyui-textbox" style="width: 150px"
							data-options="prompt:'SIM卡现存放部门'" /> 出库状态: <select id="ustate" name="ustate"
							class="easyui-combobox" style="width: 150px;" editable="false">
							<option value=""></option>
							<option value="1">已出库</option>
							<option value="0">未出库</option>
							<option value="2">已回收</option>
						</select> 使用状态: <select class="easyui-combobox" id="state" name="state" panelheight="auto"
							style="width: 150px">
							<option value=""></option>
							<option value="1">已用</option>
							<option value="0">未用</option>
						</select>
					</div>
					<div class="xia">
						SIM号码：<input id="telnum" name="telnum" style="width: 150px" /> 领用人 ：<input id="lyr"
							name="lyr" style="width: 150px" /> <a id="search_btn" href="#" class="easyui-linkbutton"
							data-options="iconCls:'icon-search'">查询</a>
					</div>
				</div>
			</div>
			<!--表格DIV结束-->
		</form>
	</div>
	<div region="center">
		<table id="listGrid"></table>
	</div>
	<div id="td_toolbar"></div>
	<!-- 导入Excel -->
	<div id="uploadPicWindow" class="easyui-window" title="设备入库导入" closed="true"
		style="width: 420px; height: 220px; padding: 20px; background: #fafafa;"
		data-options="iconCls:'icon-save',closable:true, collapsible:true,minimizable:true,maximizable:true">
		<form id="picForm" action="" method="post">
			<div id="picTip"></div>
			<div style="margin-bottom: 20px">
				选择文件: <input type="file" name="file" id="file" style="width: 80%" />
			</div>
			<a href="#" onclick="return excelMB();">导入模板下载</a>
			<div id="formWindowfooterPic1" style="padding: 5px; text-align: right;">
				<a href="#" onclick="submitPic();" class="easyui-linkbutton" data-options="iconCls:'icon-save'">提交</a>
			</div>
		</form>
	</div>
</body>
</html>