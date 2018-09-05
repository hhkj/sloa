<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%><c:set var="basePath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>神龙智能办公</title>
<link href="${basePath}/assets/css/reset.css" rel="stylesheet" type="text/css" />
<link href="${basePath}/assets/js/themes/default/easyui.css" rel="stylesheet" type="text/css" />
<link href="${basePath}/assets/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
<link href="${basePath}/assets/css/layout.css" rel="stylesheet" type="text/css" />
</head>
<body>
<body class="easyui-layout vui-easyui" scroll="no">
	<noscript>
		<div class="bowerPrompt" class="bowerPrompt">
			<img src="${basePath}/assets/images/noscript.gif" alt='抱歉，请开启脚本支持！' />
		</div>
	</noscript>
	<!-- 头部 -->
	<div data-options="region:'north',split:false,border:false,border:false" class="viewui-navheader">
		<!-- header start -->
		<div class="sys-logo">
			<a href="javascript:;" class="logoicon">logo图标</a> 
			<a href="javascript:;" class="logo_title">logo名称</a> 
			<a class="line"></a> <a href="javascript:;" class="e">logo副标题</a>
		</div>
		<!-- 菜单横栏 -->
		<ul class="viewui-navmenu"></ul>
		<div class="viewui-user">
			<div class="user-photo">
				<i class="fa fa-user-circle-o"></i>
			</div>
			<h4 class="user-name ellipsis">Admin</h4>
			<i class="fa fa-angle-down xiala"></i>

			<div class="viewui-userdrop-down">
				<ul class="user-opt">
					<li><a href="javascript:;"> <i class="fa fa-user"></i> <span class="opt-name">用户信息</span>
					</a></li>
					<li><a href="https://shop155629335.taobao.com/?spm=a230r.7195193.1997079397.2.diL9ud" target="_blank"> <i class="fa fa-cart-plus"></i> <span class="opt-name">商城购买</span>
					</a></li>
					<li class="modify-pwd"><a href="javascript:;" id="editpass"> <i class="fa fa-edit"></i> <span class="opt-name">修改密码</span>
					</a></li>
					<li class="logout"><a href="javascript:;" id="loginOut"> <i class="fa fa-power-off"></i> <span class="opt-name">退出</span>
					</a></li>
				</ul>
			</div>
		</div>
	</div>
	<!-- // 头部 -->

	<!-- 版权 -->
	<div data-options="region:'south',split:false,border:false" class="copyright">
		<div class="footer">
			<span class="pull-left"> &copy; Copyright ©2016 ~ 2019 slkj All Rights Reserved.
<!-- 			</span> <span class="pull-right"> <a href="javascript:;"><i class="fa fa-download"></i> 下载管理</a> <a href="javascript:;"><i class="fa fa-volume-up"></i> 消息</a> -->
			</span>
		</div>
	</div>
	<!-- // 版权 -->
	<!-- 左侧菜单 -->
	<div data-options="region:'west',hide:true,split:false,border:false" title="导航菜单" class="LeftMenu" id="west">
		<div id="nav" class="easyui-accordion" data-options="fit:true,border:false"></div>
	</div>
	<!-- // 左侧菜单 -->

	<!-- home -->
	<div data-options="region:'center'" id="mainPanle" class="home-panel">
		<div id="layout_center_plan" class="easyui-panel" data-options="fit:true,style:'{overflow:hidden}',closed:false,closable:true,
	tools:[{
				iconCls:'refresh-panel fa fa-refresh ',
				handler:function(){firstrefresh()}
			}]" style="overflow: hidden"></div>

	</div>
	<!-- // home -->

	<!--修改密码窗口-->
	<div data-options="collapsible:false,minimizable:false,maximizable:false" id="updatePwd" class="easyui-window updatePwd" title="修改密码">
		<div class="row">
			<label for="txtNewPass">新密码：</label> <input class="easyui-validatebox txt01" id="txtNewPass" type="Password" name="name" />
		</div>
		<div class="row">
			<label for="txtRePass">确认密码:</label> <input class="easyui-validatebox txt01" id="txtRePass" type="Password" name="Password" />
		</div>
		<div data-options="region:'south',border:false" class="pwdbtn">
			<a id="btnEp" class="easyui-linkbutton " href="javascript:;">确定</a> <a id="btnCancel" class="easyui-linkbutton btnDefault" href="javascript:;">取消</a>
		</div>
	</div>


	<script src="${basePath}/assets/js/jquery2.1.1.js" type="text/javascript"></script>
	<script src="${basePath}/assets/js/jquery.easyui.min.js" type="text/javascript"></script>
	<script src='${basePath}/assets/js/index2.js' type="text/javascript"></script>
	<script src='${basePath}/assets/js/system.menu2.js' type="text/javascript"></script>
	<script src="${basePath}/assets/js/easyui-lang-zh_CN.js" type="text/javascript"></script>
	
	<script type="text/javascript">
		//绑定 div 的鼠标事件
		$('.navmenu-item a').click(function() {
			$('.navmenu-item a').removeClass("active");//清空已经选择的元素
			$(this).addClass("active");
		});
	</script>
</body>
</html>