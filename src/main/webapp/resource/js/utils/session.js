

//获取路径
var pathName=window.document.location.pathname;
//截取，得到项目名称
var projectName=pathName.substring(0,pathName.substr(1).indexOf('/')+1);


$.ajaxSetup({
	type : 'POST',
	complete : function(xhr, status) {
		var sessionStatus = xhr.getResponseHeader('sessionstatus');
		if (sessionStatus == 'timeout') {
			var top = getTopWinow();
			var yes = confirm('由于您长时间没有操作, session已过期, 请重新登录.');
			if (yes) {
				top.location.href = projectName+'/pages/login.jsp';
			}
		}
	}
});

/**
 * 在页面中任何嵌套层次的窗口中获取顶层窗口
 * @return 当前页面的顶层窗口对象
 */
function getTopWinow() {
	var p = window;
	while (p != p.parent) {
		p = p.parent;
	}
	return p;
}