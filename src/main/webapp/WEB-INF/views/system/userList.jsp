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
<script type="text/javascript">
	var userGrid;
	$(document).ready(function() {
		userGrid = $('#dg').datagrid({
			method : 'post',
			url : '../user/list',
			loadMsg : '数据加载中....',
			fit : false,
			nowrap : true, // false:折行
			rownumbers : true, // 行号
			striped : true, // 隔行变色
			singleSelect : true,// 是否单选
			height : 400,
			pagination : true,
			pageSize : 10,
			pageList : [ 10, 20, 30, 50 ],
			columns : [ [ {
				field : 'ck',
				checkbox : true
			}, {
				field : 'username',
				title : '用户名',
				width : '15%'
			}, {
				field : 'status',
				title : '状态',
				width : '5%',
				align : 'center',
				formatter : function(value, rec) {
					return value == 'enabled' ? '启用'
							: '<span style="color:red">禁用</span>';
				}
			}, {
				field : 'realname',
				title : '姓名',
				width : '10%'
			}, {
				field : 'phone',
				title : '联系方式',
				width : '10%'
			}, {
				field : 'departName',
				title : '所属部门',
				width : '25%'
			} ] ]
		});
		userGrid.datagrid('getPager').pagination({
			beforePageText : '第',// 页数文本框前显示的汉字
			afterPageText : '页    共 {pages} 页',
			displayMsg : '当前显示 {from} - {to} 条记录   共 {total} 条记录'
		});
		//新增数据
		$('#newData').on('click', function() {
			layer.open({
				type : 1,
				skin : 'layui-layer-rim', //加上边框
				area : [ '490px', '405px' ], //宽高
				content : $('#newData-wrapper'),
				zIndex : 1000
			});
			$("#p_depid").combotree({
				url : '../dep/getDepTree',
				lines : true,
				label : '部门名称:',
				required : true
			});
			$('#roleId').combobox({
				url : '../role/queryAll',
				label : '角色名称:',
				valueField : 'id',
				textField : 'name',
				required : true
			});
		});
	});
	function submitForm() {//保存提交
		if ($("#vui_sample").form('enableValidation').form('validate')) {
			$.ajax({
				url : '../user/save',
				type : "POST",
				data : $('#vui_sample').serialize(),
				success: function(result){
					if (result.success){
						layer.close(layer.index); 
						userGrid.datagrid('reload');
					} else {
						layer.msg('操作失败');
					}
				}
		});
	}
	}
	function clearForm() {//重置表单
		$('#vui_sample').form('clear');
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
							<input id="txtNewPass" type="text" class="easyui-textbox" name="name" data-options="label:'账号'" />
						</div>
						<div class="colRow">
							<input id="numbers" type="text" class="easyui-textbox" name="name" data-options="label:'姓名'" />
						</div>
						<div class="colRow">
							<input type="text" id="selest-item2" name="" class="easyui-combobox" name="language" label="状态" />
						</div>
						<div class="colRow">
							<button class="easyui-linkbutton btnDefault">
								<i class="fa fa-search"></i>查询
							</button>
						</div>
					</div>
				</div>
				<div class="btnbar-tools">
					<a href="javascript:;" class="add" id="newData"><i class="fa fa-plus-square success"></i>添加</a> <a href="javascript:;" class="edit"><i class="fa fa-pencil-square info"></i>编辑</a> <a href="javascript:;" class="del"><i class="fa fa-times-rectangle danger"></i>删除</a>
				</div>
				<table id="dg" class="table table-fixed table-dotted table-hover "></table>
			</div>
		</div>
	</div>

	<!-- 新增和编辑数据 -->
	<div class="dig-wrapper" id="newData-wrapper">
		<div class="form1-column">
			<form id="vui_sample" class="easyui-form" method="post">
				<div class="form-column1">
					<div class="form-column-left">
						<input class="easyui-textbox" name="username" style="width: 100%" data-options="label:'用户名称:',required:true">
					</div>
				</div>
				<div class="form-column1">
					<div class="form-column-left">
						<input id="roleId" name="roleId" labelPosition="top" style="width: 100%;"></input>
					</div>
				</div>
				<div class="form-column1">
					<div class="form-column-left">
						<input id="p_depid" name="departcode" labelPosition="top" style="width: 100%;"></input>
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