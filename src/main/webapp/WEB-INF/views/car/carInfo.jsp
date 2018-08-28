<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>车辆详细信息</title>
<script type="text/javascript">
	$(function() {
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
		//		alert(id);
		// 		$("#baseInfo").form( "load", "${pageContext.request.contextPath}/credit/company/findCredit.action?id=" + id);
		loadCarInfo(id);
	});

	function loadCarInfo(id) {
		$.ajax({
			type : "POST",
			url : '../vehicle/queryOne?id='+id,
			async : false,
			cache : false,
			success : function(data) {
				$("#carForm").form('load', data);
				$("input").attr("readonly", "readonly");
			}
		});
	}
</script>
</head>
<body>
	<form id="carForm">
		<table class="grid">
			

			<tr>
				<th width="150px">车牌号：</th>
				<td width="200px"><input id="search_box" name="carNumber"
					style="width: 100%; vertical-align: middle;"></input> 
				</td>
				<th width="150px">所属公司：</th>
				<td width="200px"><input id="companyName" name="companyName"
					class="easyui-textbox" style="width: 100%;" /></td>
				<th width="150px">车牌颜色：</th>
				<td width="200px"> <select class="easyui-combobox" name="plateColor" style="width: 100%;" editable="false" data-options="required:true">
						<option value="0">蓝色</option>
						<option value="1">黄色</option>
						<option value="2">黑色</option>
						<option value="3">白色</option>
				</select></td>
			</tr>
			<tr>
				<th>车主:</th>
				<td><input name="carOwner" class="easyui-textbox"
					data-options="required:true" style="width: 100%;" /></td>

				<th>联系人：</th>
				<td><input id="contacts" name="contacts" class="easyui-textbox"
					style="width: 100%;" data-options="required:true" /></td>
				<th>联系方式:</th>
				<td><input name="contactsTel" class="easyui-textbox"
					data-options="required:true" style="width: 100%;" /></td>
			</tr>
			<tr>
				<th>发证机关：</th>
				<td><input id="issuingUnit" name="issuingUnit"
					class="easyui-textbox" style="width: 100%;"></td>
				<th>登记时间：</th>
				<td><input id="retDate" name="retDate" class="easyui-datebox"
					style="width: 100%;"
					data-options="formatter:myformatter,parser:myparser"
					editable="false" /></td>

				<th>车辆类型：</th>
				<td><select name="carType" class="easyui-combobox"
					data-options="required:true" editable="false" style="width: 100%;">
						<option selected="selected" value="">请选择</option>
						<option value="普通客车">普通客车</option>
						<option value="双层客车">双层客车</option>
						<option value="重型厢式货车">重型厢式货车</option>
						<option value="普通货车">普通货车</option>
						<option value="罐车">罐车</option>
						<option value="牵引车">牵引车</option>
						<option value="集装箱车">集装箱车</option>
						<option value="半挂车">半挂车</option>
						<option value="自卸车">自卸车</option>
						<option value="大型货车">大型货车</option>
						<option value="厢式货车">厢式货车</option>
						<option value="低速载货汽车">低速载货汽车</option>
						<option value="专用运输车">专用运输车</option>
						<option value="仓栅式货车">仓栅式货车</option>
						<option value="平头柴油自卸货车">平头柴油自卸货车</option>
						<option value="重型罐式自卸货车">重型罐式自卸货车</option>
						<option value="重型集装箱半挂车">重型集装箱半挂车</option>
						<option value="平头柴油载货汽车">平头柴油载货汽车</option>
						<option value="洒布车">洒布车</option>
						<option value="粉粒物料运输车">粉粒物料运输车</option>
						<option value="重型普通货车">重型普通货车</option>
						<option value="中型半挂牵引车">中型半挂牵引车</option>
						<option value="中型罐式货车">中型罐式货车</option>
						<option value="重型普通半挂车">重型普通半挂车</option>
						<option value="重型厢式半挂车">重型厢式半挂车</option>
						<option value="中型普通货车">中型普通货车</option>
						<option value="重型平板半挂车">重型平板半挂车</option>
						<option value="重型集装箱车">重型集装箱车</option>
						<option value="中型仓栅式货车">中型仓栅式货车</option>
						<option value="重型非载货专项作业车">重型非载货专项作业车</option>
						<option value="中型专项作业车">中型专项作业车</option>
						<option value="中型厢式货车">中型厢式货车</option>
						<option value="重型罐式货车">重型罐式货车</option>
						<option value="重型货车">重型货车</option>
						<option value="爆破器材运输车">爆破器材运输车</option>
						<option value="中型自卸货车">中型自卸货车</option>
						<option value="重型专项作业车">重型专项作业车</option>
						<option value="重型特殊结构货车">重型特殊结构货车</option>
						<option value="大型专项作业车">大型专项作业车</option>
						<option value="重型半挂牵引车">重型半挂牵引车</option>
						<option value="封闭厢式">封闭厢式</option>
						<option value="非封闭厢式">非封闭厢式</option>
						<option value="非厢式">非厢式</option>
						<option value="载货汽车">载货汽车</option>
						<option value="农用车">农用车</option>
						<option value="整车">整车</option>
						<option value="挂车">挂车</option>
						<option value="重型平板货车">重型平板货车</option>
						<option value="仓栅式运输车">仓栅式运输车</option>
						<option value="重型载货专项作业车">重型载货专项作业车</option>
						<option value="轻型仓栅式货车">轻型仓栅式货车</option>
						<option value="重型仓栅式货车">重型仓栅式货车</option>
						<option value="重型仓栅式半挂车">重型仓栅式半挂车</option>
						<option value="大型载货专项作业车">大型载货专项作业车</option>
						<option value="重型自卸货车">重型自卸货车</option>
						<option value="重型封闭货车">重型封闭货车</option>
						<option value="混凝土搅拌运输车">混凝土搅拌运输车</option>
						<option value="中型特殊结构货车">中型特殊结构货车</option>
						<option value="中型非载货专项作业车">中型非载货专项作业车</option>
						<option value="重型平板自卸货车">重型平板自卸货车</option>
						<option value="大型非载货专项作业车">大型非载货专项作业车</option>
				</select></td>
			</tr>
			<tr>
				<th>车辆品牌：</th>
				<td><input id="carBrand" name="carBrand" class="easyui-textbox"
					data-options="required:true" style="width: 100%;" /></td>

				<th>车辆型号：</th>
				<td><input id="carModel" name="carModel" class="easyui-textbox"
					data-options="required:true" style="width: 100%;"></td>
				<th>车身颜色：</th>
				<td><input id="carColor" name="carColor" class="easyui-textbox"
					style="width: 100%;" /></td>
			</tr>
			<tr>
				<th>车辆识别代码/车架号：</th>
				<td><input id="carVin" name="carVin" class="easyui-textbox"
					data-options="required:true" style="width: 100%;"></td>
				<th>国产/进口：</th>
				<td><input id="made" name="made" class="easyui-textbox"
					style="width: 100%;" /></td>
				<th>发动机号：</th>
				<td><input id="carEngNum" name="carEngNum"
					class="easyui-textbox" style="width: 100%;"></td>
			</tr>
			<tr>
				<th>发动机型号：</th>
				<td><input id="carEngModel" name="carEngModel"
					class="easyui-textbox" style="width: 100%;" /></td>
				<th>燃料种类：</th>
				<td><input id="carFuelType" name="carFuelType"
					class="easyui-textbox" style="width: 100%;"></td>
				<th>排量/功率：</th>
				<td><input id="power" name="power" class="easyui-textbox"
					style="width: 100%;" /></td>
			</tr>
			<tr>
				<th>制造厂名称：</th>
				<td><input id="factory" name="factory" class="easyui-textbox"
					style="width: 100%;"></td>
				<th>转向方式：</th>
				<td><input id="steering" name="steering" class="easyui-textbox"
					style="width: 100%;" /></td>
				<th>轮距：</th>
				<td><input id="wheelTrack" name="wheelTrack"
					class="easyui-textbox" style="width: 100%;"></td>
			</tr>
			<tr>
				<th>轮胎数：</th>
				<td><input id="tyreNumber" name="tyreNumber"
					class="easyui-textbox" style="width: 100%;" /></td>

				<th>轮胎规格：</th>
				<td><input id="tyreModel" name="tyreModel"
					class="easyui-textbox" style="width: 100%;"></td>
				<th>钢板弹簧片数：</th>
				<td><input id="springNumber" name="springNumber"
					class="easyui-textbox" style="width: 100%;" /></td>
			</tr>
			<tr>
				<th>轴距：</th>
				<td><input id="wheelBase" name="wheelBase"
					class="easyui-textbox" style="width: 100%;"></td>
				<th>轴数：</th>
				<td><input id="axleNumber" name="axleNumber"
					class="easyui-textbox" style="width: 100%;"
					data-options="required:true" /></td>
				<th>外廓尺寸：</th>
				<td>长<input id="carOutLength" name="carOutLength"
					class="easyui-textbox" style="width: 60px;"
					data-options="required:true"> 宽<input id="carOutWidth"
					name="carOutWidth" class="easyui-textbox" style="width: 60px;"
					data-options="required:true"> 高<input id="carOutHeight"
					name="carOutHeight" class="easyui-textbox" style="width: 60px;"
					data-options="required:true"></td>
			</tr>
			<tr>
				<th>货箱内部尺寸：</th>
				<td>长<input id="carContLength" name="carContLength"
					class="easyui-textbox" style="width: 60px;"
					data-options="required:true"> 宽<input id="carContWidth"
					name="carContWidth" class="easyui-textbox" style="width: 60px;"
					data-options="required:true"> 高<input id="carContHeight"
					name="carContHeight" class="easyui-textbox" style="width: 60px;"
					data-options="required:true"></td>

				<th>总质量：</th>
				<td><input id="carTotalmass" name="carTotalmass"
					class="easyui-textbox" style="width: 100%;"
					data-options="required:true"></td>
				<th>核定载质量：</th>
				<td><input id="carApproved" name="carApproved"
					class="easyui-textbox" data-options="required:true"
					style="width: 100%;" /></td>
			</tr>
			<tr>
				<th>核定载客：</th>
				<td><input id="carApprGuest" name="carApprGuest"
					class="easyui-textbox" style="width: 100%;"></td>
				<th>准牵引总质量：</th>
				<td><input id="carTrac" name="carTrac" class="easyui-textbox"
					data-options="required:true" style="width: 100%;" /></td>
				<th>驾驶室载客：</th>
				<td><input id="cabGuest" name="cabGuest" class="easyui-textbox"
					style="width: 100%;"></td>
			</tr>
			<tr>
				<th>使用性质：</th>
				<td><input id="useCharacter" name="useCharacter"
					class="easyui-textbox" style="width: 100%;" /></td>

				<th>车辆获得方式：</th>
				<td>
					<!-- 					<input id="access" name="access" class="easyui-textbox" style="width: 100%;"> -->

					<label><input name="access" type="radio" checked="checked"
						value="购买" />购买 </label> <label><input name="access" type="radio"
						value="赠予" />赠予 </label>
				</td>
				<th>车辆出厂日期：</th>
				<td><input id="produce" name="produce" class="easyui-datebox"
					style="width: 100%;"
					data-options="formatter:myformatter,parser:myparser"
					editable="false" /></td>
			</tr>
			<tr>
				<th>设备号:</th>
				<td><input id="equitment1" name="equitment"
					class="easyui-textbox" style="width: 100%;" /></td>
				<th>SIM卡号:</th>
				<td><input id="simNumber1" name="simNumber"
					class="easyui-textbox" style="width: 100%;" /></td>
				<th>安装人:</th>
				<td><input name="installers" class="easyui-textbox"
					style="width: 100%;" /></td>
			</tr>
			<tr>
				<th>安装日期:</th>
				<td><input name="installtime" id="installtime"
					class="easyui-datebox" style="width: 100%;"
					data-options="formatter:myformatter,parser:myparser"
					editable="false" /></td>
			</tr>

		</table>
	</form>
</body>
</html>