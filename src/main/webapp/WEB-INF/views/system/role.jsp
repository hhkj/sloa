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
		$('#newData').on('click', function() {
			layer.open({
				type : 1,
				skin : 'layui-layer-rim', //加上边框
				area : [ '490px', '250px' ], //宽高
				content : $('#newData-wrapper'),
				zIndex : 1000
			});
		});
		$('#editData').on('click', function() {
			editRole();
		});
		$('#delData').on('click', function() {
			delRole();
		});
		$('#checkRole').on('click', function() {
			loadModlue();
		});

	});
	function loadDataGrid() {
		$grid = $('#listGrid').datagrid({
			method : 'post',
			url : '../role/list',
			// 			height : 480,
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
	function clearForm() {//重置表单
		$('#vui_sample').form('clear');
	}
	function editRole() {
		var data = $grid.datagrid('getSelected');
		if (data == null) {
			layer.msg('请选择一条数据！', {
				time : 5000, //20s后自动关闭
			});
			return;
		}
		layer.open({
			type : 1,
			skin : 'layui-layer-rim', //加上边框
			area : [ '490px', '250px' ], //宽高
			content : $('#newData-wrapper'),
			zIndex : 1000
		});
		$("#vui_sample").form('load', data);
	}
	function submitForm() {
		if ($("#vui_sample").form('enableValidation').form('validate')) {
			var rid = $("#rid").val();
			var url = "../role/save";
			if (rid != "") {
				url = "../role/edit";
			}
			var data = $("#vui_sample").serialize();
			$.ajax({
				cache : false,
				type : "POST",
				url : url,
				data : data,
				async : false,
				success : function(data) {

					if (data) {
						clearForm();
						$grid.datagrid('reload');// 刷新datagrid
						layer.close(layer.index);
						layer.msg("保存成功", {
							time : 5000,
							icon : 6
						});
					} else {
						layer.msg("操作失败", {
							time : 5000,
							icon : 6
						});
					}

				}
			});
		}
	}
	/* 删除角色 */
	function delRole() {
		var data = $grid.datagrid('getSelected');
		if (data == null) {
			layer.msg('请选择一条数据！', {
				time : 5000, //20s后自动关闭
			});
			return;
		}

		layer.confirm('请检查该角色下用户,删除后拥有该角色用户将无法获取功能权限。', {
			btn : [ '确定', '关闭' ]
		//按钮
		}, function(index) {
			$.ajax({
				url : "../role/delete?id=" + data.id,
				success : function(data) {
					layer.close(index);
					if (data) {
						$grid.datagrid('reload');
					} else {
						layer.msg("操作失败", {
							time : 5000,
							icon : 6
						});
					}
				}
			});
		});
	}
	/* 加载角色功能权限列表 */
	function loadModlue() {
		var data = $grid.datagrid('getSelected');
		if (data == null) {
			layer.msg('请选择一条数据！', {
				time : 5000, //20s后自动关闭
			});
			return;
		}
		$("#roleid").val(data.id);
		layer.open({
			type : 1,
			skin : 'layui-layer-rim', //加上边框
			area : [ '400px', '450px' ], //宽高
			content : $('#treeData-wrapper'),
			zIndex : 1000
		});
		$('#reslist').tree({
			url : '../menus/role2Module?roleId=' + data.id,
			loadMsg : '数据加载中....',
			lines : true,
			checkbox : true
		});
	}
</script>
</head>
<body scroll="no" class="body-pd10">
	<div class="dataView-container">
		<div class="table-container">
			<div class="tabs-wrapper">
				<div class="btnbar-tools">
					<a href="javascript:;" class="add" id="newData">
						<i class="fa fa-plus-square success"></i>
						添加
					</a>
					<a href="javascript:;" class="edit" id="editData">
						<i class="fa fa-pencil-square info"></i>
						编辑
					</a>
					<a href="javascript:;" class="del" id="delData">
						<i class="fa fa-times-rectangle danger"></i>
						删除
					</a>
					<a href="javascript:;" class="check" id="checkRole">
						<i class="fa fa-check-circle yellow"></i>
						设置权限
					</a>
				</div>
				<table id="listGrid" class="table table-fixed table-dotted table-hover "></table>
			</div>
		</div>
	</div>

	<!-- 新增和编辑数据 -->
	<div class="dig-wrapper" id="newData-wrapper">
		<div class="form1-column">
			<form id="vui_sample" class="easyui-form" method="post" data-options="novalidate:true">
				<input id="rid" name="id" type="hidden">
				<div class="form-column1">
					<div class="form-column-left">
						<input class="easyui-textbox" name="name" style="width: 100%" data-options="label:'角色名称:',required:true">
					</div>
				</div>
				<div class="form-column1">
					<div class="form-column-left">
						<input class="easyui-textbox" name="dscript" style="width: 100%" data-options="label:'描述:',required:true">
					</div>
				</div>
				<div class="form-btnBar pl75">
					<input type="submit" name="" value="保存" class="easyui-linkbutton btnPrimary" onclick="submitForm()" style="width: 80px" />
					<input type="submit" name="" value="重置" class="easyui-linkbutton btnDefault" onclick="clearForm()" style="width: 80px" />
				</div>
			</form>
		</div>
	</div>
	<div class="dig-wrapper" id="treeData-wrapper">
	<a id="btn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add'" onclick="roleModule()">确定</a>
	<ul id="reslist" style="margin-top: 15px"></ul>
		<!-- 		<div class="form1-column"> -->
		<!-- 			<form id="vui_sample" class="easyui-form" method="post" data-options="novalidate:true"> -->
		<!-- 				<input id="roleid" name="id" type="hidden"> -->
		<!-- 				<div class="form-column1"> -->
		<!-- 					<div class="form-column-left"> -->
		<!-- 						<input class="easyui-textbox" name="name" style="width: 100%" data-options="label:'角色名称:',required:true"> -->
		<!-- 					</div> -->
		<!-- 				</div> -->
		<!-- 				<div class="form-column1"> -->
		<!-- 					<div class="form-column-left"> -->
		<!-- 						<input class="easyui-textbox" name="dscript" style="width: 100%" data-options="label:'描述:',required:true"> -->
		<!-- 					</div> -->
		<!-- 				</div> -->
		<!-- 				<div class="form-btnBar pl75"> -->
		<!-- 					<input type="submit" name="" value="保存" class="easyui-linkbutton btnPrimary" onclick="submitForm()" style="width: 80px" /> -->
		<!-- 					<input type="submit" name="" value="重置" class="easyui-linkbutton btnDefault" onclick="clearForm()" style="width: 80px" /> -->
		<!-- 				</div> -->
		<!-- 			</form> -->
		<!-- 		</div> -->
	</div>
</body>
</html>