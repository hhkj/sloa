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
<link rel="stylesheet" href="../assets/css/layui.css" media="all">
<script type="text/javascript">
	$(function() {
		//加载下拉框中的内容 
		loadCarNumBox();
		loadCompanyBox('#carOwnerText');
	});
	function loadCarNumBox() {
		$('#carNumberCombx').combogrid({
			required : true,
			panelWidth : 500,
			idField : 'carNumber',
			textField : 'carNumber',
			url : '../vehicle/list',
			delay : 2000,
			loadMsg : '数据加载中,请稍后……',
			onChange : function(newValue, oldValue) {
				artChanged = true;//记录是否有改变（当手动输入时发生)
			},
			columns : [ [ {
				field : 'carNumber',
				title : '车牌号',
				width : 50
			}, {
				field : 'carOwner',
				title : '所属企业',
				width : 50
			} ] ],
			fitColumns : true,
			pagination : true,
			pageSize : 20,
			keyHandler : {
				up : function() {
				},
				down : function() {
				},
				enter : function() {
				},
				query : function(q) {
					//动态搜索
					$(this).combogrid('grid').datagrid('reload', {
						'carNumber' : q
					});
					$(this).combogrid('setValue', q);
				}
			},
			onSelect : function(index, row) {
				selectRow = row;
				$('#contactsText').textbox('setValue', row.contacts);
				$('#contactsTelText').textbox('setValue', row.contactsTel);
				$('#carOwnerText').textbox('setValue', row.carOwner);
			}
		});
	}
	function loadCompanyBox(id) {
		$(id).combogrid({
			delay : 2000,
			required : true,
			panelWidth : 500,
			idField : 'id',
			textField : 'compName',
			url : '../company/list',
			loadMsg : '数据加载中,请稍后……',
			onChange : function(newValue, oldValue) {
				artChanged = true;//记录是否有改变（当手动输入时发生)
			},
			columns : [ [ {
				field : 'compName',
				title : '公司名称',
				width : 80
			}, {
				field : 'contactMenber',
				title : '联系人',
				width : 30
			}, {
				field : 'contactWay',
				title : '联系方式',
				width : 30
			} ] ],
			fitColumns : true,
			pagination : true,
			pageSize : 20,
			keyHandler : {
				up : function() {
				},
				down : function() {
				},
				enter : function() {
				},
				query : function(q) {
					//动态搜索
					$(this).combogrid('grid').datagrid('reload', {
						'compName' : q
					});
					$(this).combogrid('setValue', q);
				}
			}			
		});
	}
	function submitForm() {
		$.ajax({
			url : '../vehicle/save',
			type : "POST",
			data : $('#postForm').serialize(),
			success : function(result) {
				if (result.success) {
					$('#postForm').form('clear');
				}
				layer.msg(result.msg, {
					time : 5000,
					icon : 6
				});
			}
		});

	}
	function clearForm() {
		$('#vui_sample').form('clear');
	}
</script>
</head>
<body>
	<div class="form1-column" title="表单示例">
		<form id="vui_sample" class="easyui-form" method="post" data-options="novalidate:true">
			<div class="form-column1">
				<div class="form-column-left">
					<input class="easyui-textbox" id="carNumberCombx" name="name" style="width: 100%" data-options="label:'车牌号:',required:true">
				</div>
			</div>
			<div class="form-column1">
				<div class="form-column-left">
					<input class="easyui-textbox" id="carOwnerText" name="sqkf" style="width: 100%" data-options="label:'所属企业:',required:true">
				</div>
			</div>
			<div class="form-column1">
				<div class="form-column-left">
					<input class="easyui-textbox" id="contactsText" style="width: 100%" data-options="label:'联系人:',required:true">
				</div>
			</div>
			<div class="form-column1">
				<div class="form-column-left">
					<input class="easyui-textbox" id="contactsTelText" style="width: 100%" data-options="label:'联系电话:',required:true">
				</div>
			</div>
			<div class="form-column1">
				<div class="form-column-left">
					<input class="easyui-textbox" name="wznumber" style="width: 100%" data-options="label:'证明编号:',required:true">
				</div>
			</div>
			<div class="form-column1">
				<div class="form-column-left">
					<input class="easyui-textbox" name="message" style="width: 100%; height: 60px" data-options="label:'备注:',multiline:true">
				</div>
			</div>
			<div class="form-btnBar pl75">
				<input type="submit" name="" value="保存" class="easyui-linkbutton btnPrimary" onclick="submitForm()" style="width: 80px" />
				<input type="submit" name="" value="重置" class="easyui-linkbutton btnDefault" onclick="clearForm()" style="width: 80px" />
			</div>
		</form>
	</div>
</body>
</html>