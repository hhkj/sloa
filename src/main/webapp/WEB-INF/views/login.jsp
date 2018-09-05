<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%><c:set var="basePath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>神龙智能办公</title>
<meta name="keywords" content="">
<meta name="searchtitle" content="">
<link type="text/css" rel="stylesheet" href="${basePath}/assets/default/login/css/login.css">
</head>
<body>
<body>
	<!--登录start-->
	<div class="ordinaryLogin">
		<div class="login_ad">
			<div class="bg_ordLogin">
				<img src="${basePath}/assets/default/login/images/icon-banner.png" />
			</div>
			<div class="loginCont_dk post05" style="right: -17px;">
				<div class="loginCont">
					<div class="login_th">
						<h4 class="lgCurr bd_r">用户登录</h4>
						<!--<a href="javascript:;" class="login_help">登录帮助&gt;</a> -->
					</div>
					<div class="login_text">
						<!-- error start -->
						<div class="login-error">
							<label id="EEE" class="login_error_tips" style="display: none;"></label>
						</div>
						<!-- end -->
						<div class="user_parent">
							<div class="login_input user_bg unm">
								<input name="username" id="username" tabindex="1" placeholder="账号" type="text" class="usernameSty" autocomplete="off" value="admin">
							</div>
						</div>

						<div class="user_paswd">
							<div class="login_input user_bg pwd" name="pwdParent" id="pwdParent">
								<div id="newPwd" class="keyboards-box">
									<input id="password" value="admin000" type="password" name="password" tabindex="3" pa_ui_name="keyboard" pa_ui_keyboard_position="place" pa_ui_key_type="advance" class="styTextinput w162px" maxlength="20">
								</div>
							</div>
						</div>

						<div style="height: 10px"></div>
						<div class="remeber_name">
							<b id="checked" class="normal" onclick="changeCheckRembername();"></b>
							<label id="login_save" style="float: left;">记住用户名</label>
						</div>
					</div>
					<div class="login_ck">
						<input name="submitBtn" onclick="severCheck()" type="submit" tabindex="5" class="login_btn" value="登录">
					</div>
					<div style="height: 24px"></div>
					<div class="login_ceLink"></div>
				</div>
			</div>
			<div class="filter box_shadow"></div>
		</div>
	</div>
	<!--登录end-->
	<script src="${basePath}/assets/js/jquery2.1.1.js" type="text/javascript"></script>
	<script src="${basePath}/assets/js/layer.js" type="text/javascript"></script>

	<script type="text/javascript">
		//处理记住用户名
		function changeCheckRembername() {
			var rembername = document.getElementById("rembername").value;
			if (rembername == 'true') {//选中，置为不选中
				document.getElementById("rembername").value = 'false';
				$('#checked').attr("class", "normal");
			} else {
				document.getElementById("rembername").value = 'true';
				$('#checked').attr("class", "checked");
			}
		}
		if (window.top !== window.self) {
			window.top.location = window.location;
		}
		document.onkeydown = function() {
			if (event.keyCode == 13) {
				severCheck();
			}
		}
		//服务器校验
		function severCheck() {
			var falt = check();
			if (falt) {
				var username = $("#username").val();
				var password = $("#password").val();
				$.ajax({
					type : "POST",
					url : '${basePath}/user/login_login',
					data : {
						username : username,
						password : password,
						tm : new Date().getTime()
					},
					dataType : 'json',
					cache : false,
					success : function(data) {
						if (data.success) {
							window.location.href = "toIndex";
						} else {
							alert("账号信息错误");
							$("#username").focus();
						}
					}
				});
			}
		}
		function check() {
			if ($("#username").val() == "") {
				alert("用户名不能为空");
				return false;
			}
			if ($("#password").val() == "") {
				alert("密码不能为空");
				return false;
			}
			return true;
		}
	</script>
</body>
</html>