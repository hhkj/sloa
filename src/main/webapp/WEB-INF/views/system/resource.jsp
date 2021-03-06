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
<script src="../assets/js/easyui-lang-zh_CN.js" type="text/javascript"></script>
<script src="../assets/js/layer.js" type="text/javascript"></script>
<script src="../assets/json/resource.js" type="text/javascript"></script>
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
					<a href="javascript:;" class="edit" id="editRows">
						<i class="fa fa-pencil-square info"></i>
						编辑
					</a>
					<a href="javascript:;" class="del" id="deleteRows">
						<i class="fa fa-times-rectangle danger"></i>
						删除
					</a>
				</div>
				<table id="function_tb" class="easyui-treegrid" treeField="funcName"></table>
			</div>
		</div>
	</div>
	<div class="dig-wrapper" id="newData-wrapper">
		<div class="form1-column">
			<form id="vui_sample" class="easyui-form" method="post" data-options="novalidate:true">
				<input type="hidden" name="menuid" id="rid">
				<div class="form-column1">
					<div class="form-column-left">
						<input class="easyui-textbox" name="menuname" style="width: 100%" data-options="label:'资源名称:',required:true">
					</div>
				</div>
				<div class="form-column1">
					<div class="form-column-left">
						<input id="p_menus" name="parentMenu" labelPosition="top" style="width: 100%;"></input>
					</div>
				</div>
				<div class="form-column1">
					<div class="form-column-left">
						<input class="easyui-textbox" name="url" style="width: 100%" data-options="label:'资源路径:'">
					</div>
				</div>
				<div class="form-column1">
					<div class="form-column-left">
						<input class="easyui-numberbox" name="sort" style="width: 100%" data-options="label:'排序:'">
					</div>
				</div>
				<div class="form-column1">
					<div class="form-column-left">
						<input class="easyui-textbox" name="icon" style="width: 100%" data-options="label:'资源图标:',required:true">
					</div>
				</div>
				<div class="form-btnBar pl75">
					<input type="submit" name="" value="保存" class="easyui-linkbutton btnPrimary" onclick="submitForm()" style="width: 80px" />
					<input type="submit" name="" value="重置" class="easyui-linkbutton btnDefault" onclick="clearForm()" style="width: 80px" />
				</div>
			</form>
		</div>
	</div>
	<script type="text/javascript">
		var dataGrid;
		$(function() {
			laodGridData();
			//新增数据
			$('#newData').on('click', function() {
				layer.open({
					type : 1,
					skin : 'layui-layer-rim', //加上边框
					area : [ '490px', '355px' ], //宽高
					content : $('#newData-wrapper'),
					zIndex : 1000
				});
				$("#p_menus").combotree({
					url : '../menus/getCombotree',
					lines : true,
					label : '父级资源名称:',
					required : true
				});
				clearForm();
			});
			//新增数据
			$('#editRows').on('click', function() {
				editRowsFun();
			});
			$('#deleteRows').on('click', function() {
				deleteRowsFun();
			});
		});
		//获取列表数据
		function laodGridData() {
			dataGrid = $("#function_tb").treegrid({
				url : "../menus/listTree",// 加载的URL
				idField : "id",
				method : "GET",
				treeField : "name",
				pagination : false,// 显示分页
				fitColumns : true,
				singleSelect : true,
				iconCls : "icon-save",// 图标
				columns : [ [ // 每个列具体内容
				{
					field : 'id',
					title : '编号'
				}, {
					field : 'name',
					title : '资源名称',
					width : '25%'
				}, {
					field : 'url',
					title : '资源路径',
					width : '20%'
				}, {
					field : 'icon',
					title : '资源图标',
					width : '10%'
				}, {
					field : 'sort',
					title : '排序'
				} ] ],
				onLoadSuccess : function() {
					$('#function_tb').treegrid('collapseAll');
				}
			});
		}
		function submitForm() {
			//保存提交
			var url = "";
			if ($("#rid").val().length <= 0) {
				url = '../menus/addMenus';
			} else {
				url = '../menus/editModule';
			}
			if ($("#vui_sample").form('enableValidation').form('validate')) {
				$.ajax({
					cache : false,
					type : "POST",
					url : url,
					data : $("#vui_sample").serialize(),
					async : false,
					success : function(data) {
						layer.close(layer.index);
						dataGrid.treegrid('reload'); // 重新载入所有行
					}
				});
			}

		}
		function clearForm() {//重置表单
			$('#vui_sample').form('clear');
		}

		function editRowsFun() {
			var data = dataGrid.datagrid('getSelected');
			if (data == null) {
				layer.msg('请选择一条数据！', {
					time : 5000, //20s后自动关闭
				});
				return;
			}

			layer.open({
				type : 1,
				skin : 'layui-layer-rim', //加上边框
				area : [ '490px', '355px' ], //宽高
				content : $('#newData-wrapper'),
				zIndex : 1000
			});
			clearForm();
			$("#p_menus").combotree({
				url : '../menus/getCombotree',
				lines : true,
				label : '父级资源名称:',
				required : true
			});
			$.ajax({
				type : "POST",
				url : '../menus/queryOne/' + data.id,
				async : false,
				cache : false,
				success : function(re) {
					$("#vui_sample").form('load', re);
				}
			});
		}
		function deleteRowsFun() {
			layer.confirm('删除后车辆信息无法恢复！', {
				btn : [ '确定', '关闭' ]
			//按钮
			}, function(index) {
				var data = dataGrid.datagrid('getSelected');
				// 				$.ajax({
				// 					url : "../vehicle/delete?id=" + data.id,
				// 					success : function(data) {
				// 						if (data) {
				// 							carDataGrid.datagrid('load');
				// 							layer.close(index);
				// 						}
				// 					}
				// 				});
			});
		}
	</script>
</body>
</html>