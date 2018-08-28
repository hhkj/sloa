<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>角色管理</title>
<link href="../assets/css/reset.css" rel="stylesheet" type="text/css" />
<link href="../assets/js/themes/default/easyui.css" rel="stylesheet" type="text/css" />
<link href="../assets/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
<link href="../assets/css/layout.css" rel="stylesheet" type="text/css" />
<script src="../assets/js/jquery2.1.1.js" type="text/javascript"></script>
<script src="../assets/js/jquery.easyui.min.js" type="text/javascript"></script>
<script src="../assets/js/easyui-lang-zh_CN.js" type="text/javascript"></script>
<script src="../assets/js/layer.js" type="text/javascript"></script>
<script type="text/javascript">
	var $grid;
	$(function() {
		loadDataGrid();
	});
	function loadDataGrid() {
		$grid = $('#listGrid').datagrid({
			method : 'post',
			url : '../role/list',
			height : 480,
			nowrap : true, // false:折行
			rownumbers : true, // 行号
			striped : true, // 隔行变色
			singleSelect : true,// 是否单选
			pagination : true,
			pageSize : 15,
			pageList : [ 10, 15, 20, 30, 50, 100 ],
			loadMsg : '数据加载中,请稍后……',
			columns : [ [ {
				title : '角色名称',
				field : 'name',
				width : 150
			}, {
				title : '资源描述',
				field : 'dscript',
				width : 220
			} ] ],
			onLoadSuccess : function() {
				$('#listGrid').datagrid('clearSelections');
			}
		});
		// 设置分页控件
		$grid.datagrid('getPager').pagination({
			beforePageText : '第',// 页数文本框前显示的汉字
			afterPageText : '页    共 {pages} 页',
			displayMsg : '当前显示 {from} - {to} 条记录   共 {total} 条记录'
		});
	}
	function addFun() {
		SL.showWindow({
			title : '角色信息',
			iconCls : 'icon-add',
			width : 500,
			height : 260,
			url : '../role/roleInfo',
			left : 100,
			top : 100,
			buttons : [ {
				text : '确定',
				iconCls : 'icon-add',
				handler : function() {
					// 保存数据
					fCallback("../role/save");
				}
			}, {
				text : '关闭',
				handler : function() {
					SL.closeWindow();
				}
			} ]
		});
	};
	/**
	 * 弹出编辑窗口
	 */
	function editFun(index) {
		var data = $grid.datagrid('getData').rows[index];
		SL.showWindow({
			title : '编辑角色信息',
			iconCls : 'icon-add',
			width : 500,
			height : 260,
			url : '../role/roleInfo',
			onLoad : function() {
				// 先加载数据
				$("#roleForm").form('load', data);
			},
			buttons : [ {
				text : '确定',
				iconCls : 'icon-add',
				handler : function() {
					// 保存数据
					fCallback("edit");
				}
			}, {
				text : '关闭',
				handler : function() {
					SL.closeWindow();
				}
			} ]
		});
	}
	/**
	 * ajax保存角色信息
	 */
	function fCallback(url) {
		if ($("#comForm").form('enableValidation').form('validate')) {
			var data = $("#roleForm").serialize();
			$.ajax({
				cache : false,
				type : "POST",
				url : url,
				data : data,
				async : false,
				success : function(data) {
					if (data) {
						$grid.datagrid('reload');// 刷新datagrid
						SL.closeWindow();
					} else {
						SL.sysSlideShow({
							title : 'Error',
							msg : "出现异常。"
						});
					}
				}
			});
		}
	}

	/* 加载角色功能权限列表 */
	function loadModlue(index) {
		var data = $grid.datagrid('getData').rows[index];
		$("#roleId").val(data.id);
		$('#right-panel').panel({
			title : "[" + data.name + "]:当前权限",
			href : '../role/allotRes',
			onLoad : function() {
				$('#reslist').tree({
					url : '../module/role2Module?roleId=' + data.id,
					loadMsg : '数据加载中....',
					lines : true,
					checkbox : true
				});
			}
		});
	}
	/* 删除角色 */
	function delRow(index) {
		var data = $grid.datagrid('getData').rows[index];
		$.messager.confirm('提示', '请检查该角色下用户,删除后拥有该角色用户将无法获取功能权限。', function(r) {
			if (r) {
				$.ajax({
					url : "../role/delete?id=" + data.id,
					success : function(data) {
						if (data) {
							$grid.datagrid('reload');
						} else {
							$.messager.show({
								title : 'Error',
								msg : '不好意思，出错了！'
							});
						}
					}
				});
			}
		});
	}
	/* 保存权限设置 */
	function roleModule() {
		var nodes = $('#reslist').tree('getChecked',
				[ 'checked', 'indeterminate' ]);
		var ids = [];
		for (var i = 0; i < nodes.length; i++) {
			ids.push(nodes[i].id);
		}
		if (ids.length > 0) {
			var param = {
				roleid : $("#roleId").val(),
				ids : ids
			};
			$.ajax({
				url : "../role/saveRoleRes",
				type : "POST",
				data : param,
				async : false,
				dataType : "json",
				cache : false,
				success : function(data) {
					if (data) {
						$.messager.show({
							msg : '设置成功！'
						});
						;
					} else {
						$.messager.show({
							title : 'Error',
							msg : '不好意思，出错了！'
						});
					}
				}
			});
		} else {
			alert("请选择分配资源!");
		}
	}
</script>
</head>
<body scroll="no" class="body-pd10">
	<div class="dataView-container">
		<div class="table-container">
			<div class="tabs-wrapper">
				<div class="btnbar-tools">
					<a href="javascript:;" class="add" id="newData"><i class="fa fa-plus-square success"></i>添加</a> <a href="javascript:;" class="edit"><i class="fa fa-pencil-square info"></i>编辑</a> <a href="javascript:;" class="del"><i class="fa fa-times-rectangle danger"></i>删除</a>
					<a href="javascript:;" class="check"><i class="fa fa-check-circle yellow"></i>设置权限</a>
				</div>
				<table id="listGrid" class="table table-fixed table-dotted table-hover "></table>
			</div>
		</div>
	</div>

	<!-- 新增和编辑数据 -->
	<div class="dig-wrapper" id="newData-wrapper">
		<div class="form1-column">
			<form id="vui_sample" class="easyui-form" method="post" data-options="novalidate:true">
				<div class="form-column1">
					<div class="form-column-left">
						<input class="easyui-textbox" name="username" style="width: 100%" data-options="label:'用户名称:',required:true">
					</div>
				</div>
				<div class="form-column1">
					<div class="form-column-left">
						<select class="easyui-combobox" name="roleId" data-options="label:'角色:',required:true" labelPosition="top" style="width: 100%;">
							<option value="AL">原材料</option>
							<option value="AK">辅料</option>
							<option value="AZ">产品</option>
							<option value="AR">深加工原料</option>
						</select>
					</div>
				</div>
				<div class="form-column1">
					<div class="form-column-left">
						<select class="easyui-combobox" name="departcode" data-options="label:'部门:',required:true" labelPosition="top" style="width: 100%;">
							<option value="AL">新增</option>
							<option value="AK">已提交</option>
							<option value="AZ">申请中</option>
							<option value="AR">已入库</option>
						</select>
					</div>
				</div>
				<div class="form-column1">
					<div class="form-column-left">
						<input class="easyui-textbox" name="realname" style="width: 100%" data-options="label:'姓名:',required:true">
					</div>
				</div>
				<div class="form-column1">
					<div class="form-column-left">
						<input class="easyui-textbox" name="phone" style="width: 100%" data-options="label:'联系电话:',required:true">
					</div>
				</div>
				<div class="form-btnBar pl75">
					<input type="submit" name="" value="保存" class="easyui-linkbutton btnPrimary" onclick="submitForm()" style="width: 80px" /> <input type="submit" name="" value="重置" class="easyui-linkbutton btnDefault" onclick="clearForm()" style="width: 80px" />
				</div>
			</form>
		</div>
	</div>
</body>
</html>