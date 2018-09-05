var resource=[{"menuid":"0","menuname":"首页","icon":"fa-home"},{"menuid":"1","menuname":"成功案例","icon":"fa-trophy"},
{"menuid":"2","menuname":"特效组件","icon":"fa-inbox"},{"menuid":"3","menuname":"文档帮助","icon":"fa-suitcase"},{"menuid":"4","menuname":"系统管理","icon":"fa-dropbox"}];

var _menus1 =[{"menus":[{"menus":[{"menus":[],"menuid":9,"icon":"fa-delicious","menuname":"添加车辆","parentMenu":7},{"menus":[],"menuid":10,"icon":"fa-delicious","menuname":"查询车辆","parentMenu":7}],"menuid":7,"icon":"fa-trophy","menuname":"车辆管理","parentMenu":1},{"menus":[{"menus":[],"menuid":11,"menuname":"添加分组","parentMenu":8},{"menus":[],"menuid":12,"menuname":"查询分组","parentMenu":8}],"menuid":8,"icon":"fa-delicious","menuname":"分组管理","parentMenu":1}],"menuid":1,"icon":"fa-trophy","menuname":"车辆管理","parentMenu":0},{"menus":[{"menus":[{"menus":[],"menuid":20,"menuname":"查询设备","parentMenu":19}],"menuid":19,"menuname":"设备管理","parentMenu":2}],"menuid":2,"icon":"fa-dashboard","menuname":"仓储管理","parentMenu":0},{"menus":[{"menus":[{"menus":[],"menuid":15,"menuname":"工单登记","parentMenu":13},{"menus":[],"menuid":16,"menuname":"工单派检","parentMenu":13},{"menus":[],"menuid":17,"menuname":"工单查询","parentMenu":13}],"menuid":13,"menuname":"工单管理","parentMenu":3},{"menus":[{"menus":[],"menuid":18,"menuname":"到期查询","parentMenu":14}],"menuid":14,"menuname":"费用管理","parentMenu":3}],"menuid":3,"icon":"fa-trophy","menuname":"售后管理","parentMenu":0},{"menus":[],"menuid":4,"icon":"fa-trophy","menuname":"代理商","parentMenu":0},{"menus":[],"menuid":5,"icon":"fa-trophy","menuname":"财务管理","parentMenu":0},{"menus":[{"menus":[{"menus":[],"menuid":22,"icon":"fa-users","menuname":"用户管理","parentMenu":21,"url":"../user/toUserListPage"}],"menuid":21,"icon":"fa-dashboard","menuname":"系统管理","parentMenu":6}],"menuid":6,"icon":"fa-trophy","menuname":"系统管理","parentMenu":0}];
var _menus;
/*ajax获取菜单数据*/
function loadModuleData() {
    $.ajax({
    	url : "menus/list",
        async: false,
        dataType: "json",
        cache: false,
        success: function (data) {
            if (data.length > 0) {
                _menus =data;
            }
        }
    });
}
// 设置登录窗口
function openPwd() {
	$('#updatePwd').window({
		title : '修改密码',
		width : 300,
		modal : true,
		shadow : true,
		closed : true,
		height : 160,
		top : 149,
		resizable : false
	});
}
// 关闭登录窗口
function closePwd() {
	$('#updatePwd').window('close');
}

// 修改密码
function serverLogin() {
	var $newpass = $('#txtNewPass');
	var $rePass = $('#txtRePass');

	if ($newpass.val() == '') {
		msgShow('系统提示', '请输入密码！', 'admin');
		return false;
	}
	if ($rePass.val() == '') {
		msgShow('系统提示', '请在一次输入密码！', 'admin');
		return false;
	}

	if ($newpass.val() != $rePass.val()) {
		msgShow('系统提示', '两次密码不一至！请重新输入', 'admin');
		return false;
	}

	$.post('/ajax=' + $newpass.val(), function(msg) {
		msgShow('系统提示', '恭喜，密码修改成功！<br>您的新密码为：' + msg, 'info');
		$newpass.val('');
		$rePass.val('');
		close();
	})

}

$(function() {
	openPwd();

	$('#editpass').click(function() {
		$('#updatePwd').window('open');
	});

	$('#btnEp').click(function() {
		serverLogin();
	});

	$('#btnCancel').click(function() {
		closePwd();
	});

	$('#loginOut').click(function() {
		$.messager.confirm('系统提示', '您确定要退出本次登录吗?', function(r) {

			if (r) {
				location.href = 'login.html';
			}
		});
	})
});