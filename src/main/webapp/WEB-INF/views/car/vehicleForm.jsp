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
</head>

<body>
	<div class="form-wrapper" id="form-wrapper">
		<div class="form2-column">
			<h3 class="form-title">车辆信息</h3>
			<form id="vui_sample" class="easyui-form" method="post">
				<div class="form-column2">
					<div class="form-column-left">
						<input class="easyui-textbox" name="carPlateNo" style="width: 100%" data-options="label:'车牌号:',required:true">
					</div>
					<div class="form-column-left fm-left">
						<select class="easyui-combobox" name="carPlateCol" data-options="label:'车牌颜色:',required:true" labelPosition="top" style="width: 100%;">
							<option value="Y">黄色</option>
							<option value="U">蓝色</option>
							<option value="B">黑色</option>
							<option value="W">白色</option>
							<option value="O">其他</option>
						</select>
					</div>
				</div>
				<div class="form-column2">
					<div class="form-column-left">
						<select class="easyui-combobox" name="customerName" data-options="label:'所属企业:'" labelPosition="top" style="width: 100%;">
							<option value="燃油车">燃油车</option>
							<option value="双燃料">双燃料</option>
							<option value="新能源">新能源</option>
						</select>
					</div>
					<div class="form-column-left fm-left">
						<select class="easyui-combobox" name="carBrand" id="carBrand" labelPosition="top" style="width: 100%;" data-options="label:'车辆品牌:',valueField:'key',textField:'value',required:true,
											url:'../assets/json/VEHICLE_TYPE.json'"></select>
					</div>
				</div>
				<div class="form-column2">
					<div class="form-column-left">
						<input class="easyui-textbox" name="carOwner" style="width: 100%" data-options="label:'联系人:',required:true">
					</div>
					<div class="form-column-left fm-left">
						<input class="easyui-textbox" name="carOwnerTel" style="width: 100%" data-options="label:'联系电话:',required:true">
					</div>
				</div>
				<div class="form-column2">
					<div class="form-column-left">
						<input class="easyui-textbox" name="vin2" style="width: 100%" data-options="label:'车辆识别代码/车架号:',required:true">
					</div>
					<div class="form-column-left fm-left">
						<input class="easyui-textbox" name="carColor" style="width: 100%" data-options="label:'车身颜色:',required:true">
					</div>
				</div>
				<div class="form-column2">
					<div class="form-column-left">
						<input class="easyui-textbox" name="sqkf" style="width: 100%" data-options="label:'营运证号:'">
					</div>
					<div class="form-column-left fm-left">
						<input class="easyui-textbox" name="transportNo" style="width: 100%" data-options="label:'行驶证初次登记:'">
					</div>
				</div>
				<div class="form-column2">
					<div class="form-column-left">
						<input class="easyui-textbox" name="vehicleModel" style="width: 100%" data-options="label:'车辆型号:'">
					</div>
					<div class="form-column-left fm-left">
						<input class="easyui-textbox" id="storage-time" name="hgzDataSource" style="width: 100%" data-options="label:'汽车制造厂:'">
					</div>
				</div>
				<div class="form-btnBar pl75">
					<input type="submit" name="" value="保存" class="easyui-linkbutton btnPrimary" onclick="submitForm()" style="width: 80px" /> <input type="reset" name="" value="重置" class="easyui-linkbutton btnDefault" onclick="clearForm()" style="width: 80px" />
				</div>
			</form>
		</div>
	</div>
</body>
</html>