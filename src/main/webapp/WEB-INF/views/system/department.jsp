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
					<a href="javascript:;" class="add" id="newData"><i class="fa fa-plus-square success"></i>添加</a> 
					<a href="javascript:;" class="edit"><i class="fa fa-pencil-square info"></i>编辑</a>
					 <a href="javascript:;" class="del"><i class="fa fa-times-rectangle danger"></i>删除</a> 
				</div>
				<table id="function_tb" class="easyui-treegrid" treeField="funcName"></table>
			</div>
		</div>
	</div>
	<div class="dig-wrapper" id="newData-wrapper">
		<div class="form1-column">
			<form id="vui_sample" class="easyui-form" method="post">
				<div class="form-column1">
					<div class="form-column-left">
						<input class="easyui-textbox" name="menuname" style="width: 100%" data-options="label:'部门名称:',required:true">
					</div>
				</div>
				<div class="form-column1">
					<div class="form-column-left">
						<select class="easyui-combobox" name="parentMenu" data-options="label:'父级资源名称:',required:true" labelPosition="top" style="width: 100%;">
							<option value="AL">新增</option>
							<option value="AK">已提交</option>
							<option value="AZ">申请中</option>
							<option value="AR">已入库</option>
						</select>
					</div>
				</div>
				<div class="form-column1">
					<div class="form-column-left">
						<input class="easyui-textbox" name="url" style="width: 100%" data-options="label:'负责人:'">
					</div>
				</div>
				<div class="form-column1">
					<div class="form-column-left">
						<input class="easyui-textbox" name="icon" style="width: 100%" data-options="label:'资联系电话:',required:true">
					</div>
				</div>
				<div class="form-column1">
					<div class="form-column-left">
						<input class="easyui-textbox" name="icon" style="width: 100%" data-options="label:'Email:',required:true">
					</div>
				</div>
				<div class="form-column1">
					<div class="form-column-left">
						<input class="easyui-textbox" name="icon" style="width: 100%" data-options="label:'地址:',required:true">
					</div>
				</div>
				<div class="form-btnBar pl75">
					<input type="submit" name="" value="保存" class="easyui-linkbutton btnPrimary" onclick="submitForm()" style="width: 80px" /> <input type="submit" name="" value="重置" class="easyui-linkbutton btnDefault" onclick="clearForm()" style="width: 80px" />
				</div>
			</form>
		</div>
	</div>
	<script type="text/javascript">
		var dataGrid;
		$(function() {
			dataGrid = $("#function_tb").treegrid({
				url : '../dep/list',
				loadMsg : '数据加载中....',
				nowrap : true, //false:折行
				striped : true, //隔行变色
				// 			singleSelect : true, //单选
				checkOnSelect : true,
				idField : 'id',
				treeField : 'd_name',
				lines : true,
				animate : true,
				height : 450,
				columns : [ [ {
					field : 'd_name',
					title : '部门名称',
					width : '30%'
				}, {
					field : 'd_fuzeren',
					title : '负责人',
					width : '10%'
				}, {
					field : 'd_tel',
					title : '联系电话',
					width : '10%'
				}, {
					field : 'd_fax',
					title : '传真',
					width : '10%'
				}, {
					field : 'd_email',
					title : 'Email',
					width : '10%'
				}, {
					field : 'd_add',
					title : '地址',
					width : '20%'
				} ] ]
			});
			//新增数据
			$('#newData').on('click', function() {
				layer.open({
					type : 1,
					skin : 'layui-layer-rim', //加上边框
					area : [ '490px', '455px' ], //宽高
					content : $('#newData-wrapper'),
					zIndex : 1000
				});
			});
		});
		function submitForm(){//保存提交
			if ($("#vui_sample").form('enableValidation').form('validate')) {
				$.ajax({
					cache : false,
					type : "POST",
					url : '../menus/addMenus',
					data : $("#vui_sample").serialize(),
					async : false,
					success : function(data) {
						layer.close(layer.index); 
						dataGrid.treegrid('reload'); // 重新载入所有行
					}
				});
			}
			
		}
		function clearForm(){//重置表单
			$('#vui_sample').form('clear');
		}
	</script>
</body>
</html>