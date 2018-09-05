<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>分组</title>
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
	var grid;
	$(function() {
		loadDataGrid();
		$("#search_btn").click(function() {
			$('#listGrid').datagrid({
				queryParams : {
					compName : $('#compName').val()
				}
			});
			$('#gridForm').form('clear');
		});
		$('#editRows').on('click', function() {
			editRowsFun();
		});
		$('#deleteRows').on('click', function() {
			deleteRowsFun();
		});
		//新增数据
		$('#newData').on('click', function() {
			layer.open({
				type : 1,
				skin : 'layui-layer-rim', //加上边框
				area : [ '490px', '450px' ], //宽高
				content : $('#newData-wrapper'),
				zIndex : 1000
			});
		});
	});
	//查询数据列表
	function loadDataGrid() {
		grid = $('#listGrid').datagrid({
			method : 'post',
			url : '../company/list',
			loadMsg : '数据加载中....',
			// 				fit : true,
			nowrap : true, // false:折行
			rownumbers : true, // 行号
			striped : true, // 隔行变色
			singleSelect : true,// 是否单选
			pagination : true,
			pageSize : 20,
			pageList : [ 10, 20, 30, 50 ],
			columns : [ [ {
				field : 'compName',
				title : '公司名称',
				width : '25%'
			}, {
				field : 'contactMenber',
				title : '联系人',
				width : 100
			}, {
				field : 'contactWay',
				title : '联系电话',
				width : 100
			}, {
				field : 'email',
				title : '邮箱',
				width : 100
			}, {
				field : 'mobile',
				title : '电话',
				width : 100
			}, {
				field : 'qq',
				title : 'QQ',
				width : 100
			}, {
				field : 'compAddress',
				title : '地址',
				width : '15%'
			} ] ]
		});
		grid.datagrid('getPager').pagination({
			beforePageText : '第',// 页数文本框前显示的汉字
			afterPageText : '页    共 {pages} 页',
			displayMsg : '当前显示 {from} - {to} 条记录   共 {total} 条记录'
		});
	}
	function editRowsFun() {
		var data = grid.datagrid('getSelected');
		if (data == null) {
			layer.msg('请选择一条数据！', {
				time : 5000, //20s后自动关闭
			});
			return;
		}

		layer.open({
			type : 1,
			title : '分组信息',
			skin : 'layui-layer-rim', //加上边框
			area : [ '490px', '450px' ], //宽高
			content : $('#newData-wrapper'),
			zIndex : 1000
		});
		$("#comForm").form('load', data);
	}
	function deleteRowsFun() {
		layer.confirm('删除后信息无法恢复！', {
			btn : [ '确定', '关闭' ]
		//按钮
		}, function(index) {
			var data = grid.datagrid('getSelected');
			$.ajax({
				url : "../company/delete?id=" + id,
				success : function(data) {
					if (data) {
						grid.datagrid('load');
						layer.close(index);
					}
				}
			});
		});
	}
	function submitForm(index) {//保存提交
		var url = "";
		if ($("#comid").val().length <= 0) {
			url = '../company/save';
		} else {
			url = '../company/editCompany';
		}
		$.ajax({
			url : url,
			type : "POST",
			data : $('#comForm').serialize(),
			success : function(data) {
				if (data) {
					grid.datagrid('load');
					layer.close(layer.index); 
				}
			}
		});
	}
	function clearForm() {//重置表单
		$('#comForm').form('clear');
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
							<input id="txtNewPass" type="text" class="easyui-textbox" name="name" data-options="label:'公司名称'" />
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
				<table id="listGrid" class="table table-fixed table-dotted table-hover "></table>
			</div>
		</div>
	</div>

	<!-- 新增和编辑数据 -->
	<div class="dig-wrapper" id="newData-wrapper">
		<div class="form1-column" title="表单示例">
			<form id="comForm" class="easyui-form" method="post" data-options="novalidate:true">
				<input id="comid" name="id" type="hidden" />
				<div class="form-column1">
					<div class="form-column-left">
						<input class="easyui-textbox" name="compName" style="width: 100%" data-options="label:'公司名称:',required:true">
					</div>
				</div>
				<div class="form-column1">
					<div class="form-column-left">
						<input class="easyui-textbox" name="contactMenber" style="width: 100%" data-options="label:'联系人:'">
					</div>
				</div>
				<div class="form-column1">
					<div class="form-column-left">
						<input class="easyui-textbox" name="contactWay" style="width: 100%" data-options="label:'联系电话:'">
					</div>
				</div>
				<div class="form-column1">
					<div class="form-column-left">
						<input class="easyui-textbox" name="email" style="width: 100%" data-options="label:'Email:'">
					</div>
				</div>
				<div class="form-column1">
					<div class="form-column-left">
						<input class="easyui-textbox" name="qq" style="width: 100%" data-options="label:'QQ号码:'">
					</div>
				</div>
				<div class="form-column1">
					<div class="form-column-left">
						<input class="easyui-textbox" name="compAddress" style="width: 100%" data-options="label:'公司地址:',multiline:true">
					</div>
				</div>
				<div class="form-btnBar pl75">
					<input type="submit" name="" value="保存" class="easyui-linkbutton btnPrimary" onclick="submitForm()" style="width: 80px" />
					<input type="submit" name="" value="重置" class="easyui-linkbutton btnDefault" onclick="clearForm()" style="width: 80px" />
				</div>
			</form>
		</div>
	</div>
</body>
</html>