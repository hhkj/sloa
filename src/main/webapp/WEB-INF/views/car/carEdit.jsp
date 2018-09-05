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
		// 		loadCompanyBox('#companyName');
		//解析url
		var param = window.location.href;
		//var reg = new RegExp("(^|&)" + param + "=([^&]*)(&|$)"); 
		var r = window.location.search.substr(1);
		var p = r.split("&");
		var ary = new Array();
		for (i = 0; i < p.length; i++) {
			var s = p[i];
			var t = s.split("=");
			ary.push(t[1]);
		}
		var id = ary[0];
		loadCarInfo(id);
		loadCompanyBox('#companyName');
	});
	function loadCarInfo(id) {
		$.ajax({
			type : "POST",
			url : '../vehicle/queryOne?id=' + id,
			async : false,
			cache : false,
			success : function(data) {
				$("#carForm").form('load', data);
			}
		});
	}
	function submitForm() {
		if ($("#carForm").form('enableValidation').form('validate')) {
			$.ajax({
				url : '../vehicle/edit',
				type : "POST",
				data : $('#carForm').serialize(),
				success : function(result) {
					if (result.success) {
						$('#carForm').form('clear');
						parent.carDataGrid.datagrid('load');
						var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
					    parent.layer.close(index); //再执行关闭
					}
					layer.msg(result.msg, {
						time : 5000,
						icon : 6
					});
				}
			});
		}
	}
	function clearForm() {
		$('#carForm').form('clear');
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
			},
			onSelect : function(index, row) {
				selectRow = row;
				//$('#deviceId').textbox('setValue', row.equitment);
				$("#companyId").val(row.id);
			}
		});
	}
</script>
</head>
<body>
	<div class="form2-column">
		<form id="carForm" class="easyui-form" method="post">
		<input id="id" name="id" type="hidden" />
			<div class="form-column2">
				<div class="form-column-left">
					<input class="easyui-textbox" name="carNumber" style="width: 100%" data-options="label:'车牌号:',required:true">
				</div>
				<div class="form-column-left fm-left">
					<select class="easyui-combobox" name="plateColor" data-options="label:'车牌颜色:',required:true" labelPosition="top" style="width: 100%;">
						<option value="1">黄色</option>
						<option value="0">蓝色</option>
						<option value="2">黑色</option>
						<option value="3">白色</option>
					</select>
				</div>
			</div>
			<div class="form-column2">
				<div class="form-column-left">
					<select class="easyui-combobox" name="carType" id="carType" labelPosition="top" style="width: 100%;" data-options="label:'车辆类型:',valueField:'value',textField:'value',required:true,
											url:'../data/VEHICLE_TYPE.json'"></select>
				</div>
				<div class="form-column-left fm-left">
					<input class="easyui-textbox" name="carBrand" style="width: 100%" data-options="label:'车辆品牌:'">
				</div>
			</div>
			<div class="form-column2">
				<div class="form-column-left">
					<input id="companyName" name="companyId" class="easyui-textbox" data-options="label:'所属企业:'" labelPosition="top" style="width: 100%;" />
				</div>
				<div class="form-column-left fm-left">
					<input class="easyui-textbox" name="carOwner" style="width: 100%" data-options="label:'车主:',required:true">
				</div>
			</div>
			<div class="form-column2">
				<div class="form-column-left">
					<input class="easyui-textbox" name="contacts" style="width: 100%" data-options="label:'联系人:',required:true">
				</div>
				<div class="form-column-left fm-left">
					<input class="easyui-textbox" name="contactsTel" style="width: 100%" data-options="label:'联系电话:',required:true">
				</div>
			</div>
			<div class="form-column2">
				<div class="form-column-left">
					<input class="easyui-textbox" name="carVin" style="width: 100%" data-options="label:'车辆识别代码/车架号:',required:true">
				</div>
				<div class="form-column-left fm-left">
					<input class="easyui-textbox" name="carColor" style="width: 100%" data-options="label:'车身颜色:',required:true">
				</div>
			</div>
			<div class="form-column2">
				<div class="form-column-left">
					<input class="easyui-textbox" name="carModel" style="width: 100%" data-options="label:'车辆型号:'">
				</div>
				<div class="form-column-left fm-left">
					<input class="easyui-textbox" name="factory" style="width: 100%" data-options="label:'制造厂名称:'">
				</div>
			</div>
			<div class="form-column2">
				<div class="form-column-left">
					<!-- 												<input class="easyui-textbox" name="vehicleModel" style="width: 60px" data-options="label:'外廓尺寸:mm'"> -->
					外廓尺寸:mm
					<input name="carOutLength" style="width: 60px;">
					X
					<input name="carOutWidth" style="width: 60px;">
					X
					<input name="carOutHeight" style="width: 60px;">
				</div>
				<div class="form-column-left fm-left">
					<!-- 						<input class="easyui-textbox" id="storage-time" name="hgzDataSource" style="width: 100%" data-options="label:'货箱内部尺寸:mm'"> -->
					货箱内部尺寸:mm
					<input name="carContLength" style="width: 60px;">
					X
					<input name="carContWidth" style="width: 60px;">
					X
					<input name="carContHeight" style="width: 60px;">
				</div>
			</div>
			<div class="form-column2">
				<div class="form-column-left">
					<input class="easyui-textbox" name="carTotalmass" style="width: 100%" data-options="label:'总质量:'">
				</div>
				<div class="form-column-left fm-left">
					<input class="easyui-textbox" name="carTrac" style="width: 100%" data-options="label:'准牵引总质量:'">
				</div>
			</div>
			<div class="form-column2">
				<div class="form-column-left">
					<input class="easyui-textbox" name="carApproved" style="width: 100%" data-options="label:'核定质量:'">
				</div>
				<div class="form-column-left fm-left">
					<input class="easyui-textbox" name="carApprGuest" style="width: 100%" data-options="label:'核定载客:'">
				</div>
			</div>
			<h3 class="form-title">入网信息</h3>
			<div class="form-column2">
				<div class="form-column-left">
					<input class="easyui-textbox" name="equitment" style="width: 100%" data-options="label:'设备编号:'">
				</div>
				<div class="form-column-left fm-left">
					<input class="easyui-textbox" name="simNumber" style="width: 100%" data-options="label:'SIM卡号:'">
				</div>
			</div>
			<div class="form-column2">
				<div class="form-column-left">
					<input class="easyui-textbox" name="installers" style="width: 100%" data-options="label:'安装人:'">
				</div>
				<div class="form-column-left fm-left">
					<input class="easyui-datebox" name="installtime" style="width: 100%" data-options="label:'安装时间:'">
				</div>
			</div>
			<div class="form-column1">
				<div class="form-column-left">
					<button class="layui-btn demoMore" lay-data="{url: '/a/', accept: 'images',size:1000}">上传车身45°照片</button>
					<button class="layui-btn demoMore" lay-data="{url: '/b/', accept: 'images',size:1000}">上传车辆登记照片</button>
					<button class="layui-btn demoMore" lay-data="{url: '/c/', accept: 'images',size:1000}">上传车辆登记照片2</button>
					<button class="layui-btn demoMore" lay-data="{url: '/c/', accept: 'images',size:1000}">上传车辆合格证/登记证</button>
				</div>
			</div>
			<div class="form-column1 pl75">
				<input type="submit" name="" value="保存" class="easyui-linkbutton btnPrimary" onclick="submitForm()" style="width: 80px" />
				<input type="reset" name="" value="重置" class="easyui-linkbutton btnDefault" onclick="clearForm()" style="width: 80px" />
			</div>
		</form>
	</div>
</body>
</html>