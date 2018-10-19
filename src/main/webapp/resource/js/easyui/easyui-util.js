	
  var common = $.extend({}, common);/* 定义全局对象，类似于命名空间或包的作用 */
  /**
   * 定义全局唯一弹出窗口id，调用窗口关闭时，直接$('#'+globa_dialog_Id).dialog('close');即可
   */
  var globa_dialog_Id = 'GLOBA_DIALOG_ID';
  var globa_tab_id = 'index-center-tab';   //中间tabId
  
  var dialog_pool = new Array();		//弹出窗口池
  /**
	 * 
	 * 取消easyui默认开启的parser
	 * 
	 * 在页面加载之前，先开启一个进度条
	 * 
	 * 然后在页面所有easyui组件渲染完毕后，关闭进度条
	 */
  /**
	$.parser.auto = false;
	$(function() {
		$.messager.progress({
			text : '页面加载中....',
			interval : 100
		});
		$.parser.parse(window.document);
		window.setTimeout(function() {
			$.messager.progress('close');
			if (self != parent) {
				window.setTimeout(function() {
					try {
						parent.$.messager.progress('close');
					} catch (e) {
					}
				}, 500);
			}
		}, 1);
		$.parser.auto = true;
	});
	*/
  
  
  /**
   * 最后一个元素出栈
   */
  function winPop(){
	  if(self != top){
		  return top.dialog_pool.pop();
	  }else{
		  return dialog_pool.pop();
	  }
  }
  /**
   * 获取最后一个元素
   * @returns
   */
  function lastWin(){
	  if(self != top){
		  return top.dialog_pool[top.dialog_pool.length-1]
	  }else{
		  return dialog_pool[dialog_pool.length-1]
	  }
  }
  
  
  /**
   * 弹窗,默认ID 为定义的全局Id  globa_dialog_Id，modal：true
   * 直接调用只能覆盖本页面，parent.能实现全屏笼罩
   */
  openDialog = function(options){
	  
	  if(options.topWin == true){		//调用最顶级
		  options.nowTopWin = true;
		  options.topWin = false;
		  
		  if(self != top){
			  top.dialog_pool.push(self);		//记录当前点击的窗口
			  top.openDialog(options);
		  }else{
			  dialog_pool.push(self);	//记录当前点击的窗口
			  openDialog(options);
		  }
		  return;
	  }
	  
	  if(options.iframeWin == true){	//是否以iframe显示;暂不处理
		  var iframeWinId = options.iframeId;
		  if(iframeWinId == undefined || iframeWinId == ''){
			  iframeWinId = "Globa_iframeWin_commonId";
		  }
		  var content = '<iframe class="px-no-use-space" id="'+iframeWinId+'" name="'+iframeWinId+'" scrolling="auto" frameborder="0"  src="'
		        + options.href + '" style="width:100%;height:98%;margin:0;padding:0;"></iframe>';
		  
		  options.content = content;
		  options.href = undefined;
	  }
	  
	  //当最大高度大于当前浏览器
	  var clientDocumentHeight =  $(document).height();
	  var clientDocumentWidth = $(document).width();
	  
	  var lessFix = 0.1 ;
	  if(options.fullScreen){	//要求全屏
		  //options.width = clientDocumentWidth ;
		  //options.height = clientDocumentHeight ;
		  if(options.lessFix == false){
			options.width = clientDocumentWidth ;
			options.height = clientDocumentHeight ;
		  }else{
			  options.width = clientDocumentWidth-clientDocumentWidth*lessFix;
			  options.height = clientDocumentHeight-clientDocumentHeight*lessFix;
		  }
		  
	  }
	 
	  if(options.width > clientDocumentWidth){
		  options.width = clientDocumentWidth;
	  }
	  if(options.height > clientDocumentHeight){
		  options.height = clientDocumentHeight;
	  }
	  //if(options.height > 768){
	//	  options.height = 620;
	  //}
	  //if(options.width > 1366){
	//	  options.width = 1100;
	 // }
	    
	var winId = options.id ||globa_dialog_Id;   
	
	if($("#"+winId).length > 0){
		//$.messager.alert("提示","已存在同一Id的窗口","info");
		return;
	}  
  	var opts = $.extend({
  		id : winId,
  		modal : true,
  		onClose : function() {
  			if(options.nowTopWin == true){
  				
  				if(self != top){
  					top.winPop();
  				}else{
  					winPop();
  				}  				
  			}
  			$(this).dialog('destroy');
  		}
  	}, options);

  	
  	if(options.myButtons){  //自定义菜单
  		//var html = "<div id='bb' style=\"text-align:center\"><a href='javascript:void(0)' onclick='test();'>1111</a></div>";
  		var html = "";
  		var obj = options.myButtons;
  		if(obj.id == undefined){
  			alert("自定义按钮的id必须存在");
  			return;
  		}
  		
  		var div = "<div id="+obj.id.replace("#","")+" ";
  		if(obj.loc){ //位置
  			div += " style='text-align:"+obj.loc+";background:#EFF5FF' ";
  		}
  		div += " >";
  		div += "111</div>";
  		$('body').append(div);
  		
  		//button转换
  		$.each(obj.buttons,function(){
  			var handlerfunc = "";
  			if(this.handler.indexOf("close") != 0){	//非关闭操作
  			
	  			if(options.iframeWin == true){  //为iframe嵌入
	  				var iframeWinId = options.iframeId;
	  				if(iframeWinId == undefined || iframeWinId == ''){
	  					iframeWinId = "Globa_iframeWin_commonId";
	  				}
	  			  
	  				handlerfunc = '$("iframe[name='+iframeWinId+']")[0].contentWindow.'+this.handler+';';
	  			}else{
	  				handlerfunc = this.handler;
	  			}
  			}else{
  				handlerfunc = this.handler;
  			}
  			
  			html += "<a style='margin-right:2px;width:80px;' href='javascript:void(0)' id='"+this.id+"' onclick='"+handlerfunc+"'  class='push "+this.iconCls+"'   >"+this.text+"</a>";
  			//html += this.text+"</a>";
  		});
  		var btns = $(obj.id).html(html);
  		$.parser.parse(btns);
  		opts = $.extend(opts,{buttons:obj.id}); //将自定义的id取出添加到easy原dialog
  	}
  	return $('<div/>').dialog(opts);
  };
  
  /**
   * 弹窗,关闭打开的弹窗，modal：true,不带参数则默认关闭全局iD
   */
  closeDialog = function(dialogId){
	  if(dialogId){
		  $('#'+dialogId).dialog('close');
	  }else{
		  $('#'+globa_dialog_Id).dialog('close');
	  }
  };
  
  
  
  
  
  /**
   * 弹窗,默认ID 为定义的全局Id  globa_dialog_Id，modal：true
   * 直接调用只能覆盖本页面，parent.能实现全屏笼罩
   */
  openFullDialog = function(options){
	  options.id = "GLOBA_DIALOG_FULL";
	  options.lessFix = false;		//保证全屏
	  options.fullScreen = true;
	  options.border = false;
	  openDialog(options);
  };
  
  /**
   * 弹窗,关闭打开的弹窗，modal：true,不带参数则默认关闭全局iD
   */
  closeFullDialog = function(){
	  
	  $('#GLOBA_DIALOG_FULL').dialog('close');
	  
  };
  
  
  /**
   * 获取tab下的iframe
   * 
   */
   function getIFrame(id){
	   var tabId = id || globa_tab_id;
	   var panel =$('#'+tabId).tabs('getSelected');
	   var frame = panel.find('iframe :first');
	   if(frame.size() >= 1){
		   return frame[0];
	   }else{
		   return '';
	   }
   }
  
   
 
	
	
	
/**
	*方法作用：加载datagrid页面列表
	*gridId,grid的id，
	*frmId需要传递到后台的form
	*arg1：调用方法，默认是load
	*/
	function loadgrid(gridId,frmId){
		var method = 'load';    
		$('#'+gridId).datagrid(method,serializeObject($('#'+frmId)));
	}
	function reloadgrid(gridId,frmId){
		var method = 'reload';    
		$('#'+gridId).datagrid(method,serializeObject($('#'+frmId)));
	}
	/**
	*方法作用：加载datagrid页面列表
	*gridId,grid的id，
	*frmId需要传递到后台的form
	*/
	function paloadgrid(gridId,frmId){
		var frame = getIFrame();
		if(frame){
			frame.contentWindow.loadgrid(gridId,frmId);
		}else{
			loadgrid(gridId,frmId);
		}
	}
	function pareloadgrid(gridId,frmId){
		var frame = getIFrame();
		if(frame){
			frame.contentWindow.reloadgrid(gridId,frmId);
		}else{
			reloadgrid(gridId,frmId);
		}
	}
	
	
	function paloadgrid(gridId,frmId,tabId){
		var frame = getIFrame(tabId);
		if(frame){
			frame.contentWindow.loadgrid(gridId,frmId);
		}else{
			loadgrid(gridId,frmId);
		}
	}
	
	/**
	*方法作用：重新加载datagrid页面列表
	*gridId,grid的id，
	*/
	function reloaddatagrid(gridId){
		$('#'+gridId).datagrid('clearSelections');
		$('#'+gridId).datagrid('reload');
	}
	
	/**
	*方法作用：重新加载treegrid页面列表
	*gridId,grid的id，
	*/
	function reloadtreegrid(gridId){
		$('#'+gridId).treegrid('clearChecked');
		$('#'+gridId).treegrid('reload');
	}	
	/**
	 * 从parent级访问iframe
	 * @param gridId
	 */
	function pareloadtreegrid(gridId){
		var frame = getIFrame();
		if(frame){
			frame.contentWindow.reloadtreegrid(gridId);
		}else{
			reloadtreegrid(gridId);
		}
		
	}
	
	/**
	*方法作用：重新加载treegrid页面列表
	*gridId,grid的id，
	*/
	function reloadtreegrid(gridId,frmId){
		$('#'+gridId).treegrid('clearChecked');
		$('#'+gridId).treegrid('reload',serializeObject($('#'+frmId)));
	}	
	/**
	 * 从parent级访问iframe
	 * @param gridId
	 */
	function pareloadtreegrid(gridId,frmId){
		var frame = getIFrame();
		if(frame){
			frame.contentWindow.reloadtreegrid(gridId,frmId);
		}else{
			reloadtreegrid(gridId,frmId);
		}
		
	}
	
	//获取选中行的条数
	function checkNum(rowId){
		return $("input[name='"+rowId+"']:checked").length;
	}
	//返回treegrid选中行的数据
	function checkData(rowId){
		var back="";
		var arr=$("input[name='"+rowId+"']:checked");
		$.each(arr,function(i){
			back+= $(this).attr("value");
			if(i!=arr.length-1) back+=",";
		});
		return back;
	}
	
	



	
function getRootPath(){
    //获取当前网址，如： http://localhost:90/base/system/userlIst.jsp
    var curWwwPath=window.document.location.href;
    //获取主机地址之后的目录，如： base/system/userList.jsp
    var pathName=window.document.location.pathname;
    var pos=curWwwPath.indexOf(pathName);
    //获取主机地址，如： http://localhost:90
    var localhostPaht=curWwwPath.substring(0,pos);
    //获取带"/"的项目名，如：/base
    var projectName=pathName.substring(0,pathName.substr(1).indexOf('/')+1);
    return projectName;
}


	
	
/**
*添加验证重复密码功能
*/

$.extend($.fn.validatebox.defaults.rules,{
	samePwd:{
		validator:function(value,param){
			return value==$(param[0]).val();
		},
		message:'密码不一致'
	}
});

//将form表单序列化成object
function serializeObject(form){
	var obj={};
	$.each(form.serializeArray(),function(i){
		if(obj[this['name']]){//如果对象不为空
			obj[this['name']]=obj[this['name']]+","+this['value'];
		}else{
			obj[this['name']]=this['value'];
		}
	});
	return obj;
};


/**
 * 
 * 
 * @requires jQuery,EasyUI
 * 
 * 防止panel/window/dialog组件超出浏览器边界
 * @param left
 * @param top
 */
var easyuiPanelOnMove = function(left, top) {
	var l = left;
	var t = top;
	if (l < 1) {
		l = 1;
	}
	if (t < 1) {
		t = 1;
	}
	var width = parseInt($(this).parent().css('width')) + 14;
	var height = parseInt($(this).parent().css('height')) + 14;
	//var width = parseInt($(this).css('width')) + 14;
	//var height = parseInt($(this).css('height')) + 14;
	var right = l + width;
	var buttom = t + height;
	var browserWidth = $(window).width();
	var browserHeight = $(window).height();
	if (right > browserWidth) {
		l = browserWidth - width;
	}
	if (buttom > browserHeight) {
		t = browserHeight - height;
	}
	$(this).parent().css({/* 修正面板位置 */
		left : l,
		top : t
	});
};
$.fn.dialog.defaults.onMove = easyuiPanelOnMove;
$.fn.window.defaults.onMove = easyuiPanelOnMove;
$.fn.panel.defaults.onMove = easyuiPanelOnMove;


	/**
	 * 
	 * 
	 * 
	 * 
	 * 避免验证tip屏幕跑偏
	 */
	var removeEasyuiTipFunction = function() {
		window.setTimeout(function() {
			$('div.validatebox-tip').remove();
		}, 0);
	};
	$.fn.panel.defaults.onClose = removeEasyuiTipFunction;
	$.fn.window.defaults.onClose = removeEasyuiTipFunction;
	$.fn.dialog.defaults.onClose = removeEasyuiTipFunction;
	
	
	
	/**
	 * 
	 * 
	 * @requires jQuery,EasyUI
	 * 
	 * 通用错误提示
	 * 
	 * 用于datagrid/treegrid/tree/combogrid/combobox/form加载数据出错时的操作
	 
	var easyuiErrorFunction = function(XMLHttpRequest) {
		$.messager.progress('close');
		$.messager.alert('错误', XMLHttpRequest.responseText);
	};
	$.fn.datagrid.defaults.onLoadError = easyuiErrorFunction;
	$.fn.treegrid.defaults.onLoadError = easyuiErrorFunction;
	$.fn.tree.defaults.onLoadError = easyuiErrorFunction;
	$.fn.combogrid.defaults.onLoadError = easyuiErrorFunction;
	$.fn.combobox.defaults.onLoadError = easyuiErrorFunction;
	$.fn.form.defaults.onLoadError = easyuiErrorFunction;
	
	*/
	
	
	$.fn.dialog.defaults.title = this.title || '';
	$.fn.dialog.defaults.border = this.border || false ;
	
	$.fn.panel.defaults.loadingMessage = '' ;
	$.fn.panel.defaults.cache = false;
	
	$.fn.panel.defaults.onMove = easyuiPanelOnMove;
	
	$.fn.dialog.defaults.loadingMessage = '' ;
	$.fn.dialog.defaults.cache = false;
	
/**
 * easyui tab使用iframe后，内存回收
 * 
*/

$.fn.panel.defaults.onBeforeDestroy = function() {
	var frame = $('iframe', this);
	try {
		if (frame.length > 0) {
			for ( var i = 0; i < frame.length; i++) {
				frame[i].contentWindow.document.write('');
				frame[i].contentWindow.close();
			}
			frame.remove();
			if ($.browser.msie) {
				CollectGarbage();
			}
		}
	} catch (e) {
	}
};
/**
 * 
 * 
 * 增加formatString功能
 * 
 * 使用方法：formatString('字符串{0}字符串{1}字符串','第一个变量','第二个变量');
 * 
 * @returns 格式化后的字符串
 */
formatString = function(str) {
	for ( var i = 0; i < arguments.length - 1; i++) {
		str = str.replace("{" + i + "}", arguments[i + 1]);
	}
	return str;
};
/**
 * 将字符串，变成数组
 */
str2Arr = function(str){
	if(str){
		var ss = str.split(",");
		var arr = [];
		for(var i=0;i<ss.length;i++){
			arr.push(ss[i]);
		}
		return arr;
	}else{
		return [];
	}
};

/**
 * 给combobox赋值
 */
setComboboxData=function(comboboxId,idField,textField,dataArr){
	$('#'+comboboxId).combobox({
        url: null,
        valueField: idField,
        textField: textField,
        editable: false,
        data: dataArr
    });
}



/**
 *
 * 
 * @requires jQuery,EasyUI
 * 
 * 扩展tree，使其支持平滑数据格式
 */
$.fn.tree.defaults.loadFilter = function(data, parentId) {
	var opt = $(this).data().tree.options;
	var idField, textField, parentField,defaultTip,defaultValue,url;
	var isPre ;
	if (opt.parentField) {
		url = opt.url;
		idField = opt.idField || 'id';
		textField = opt.textField || 'text';
		parentField = opt.parentField;
		defaultTip = opt.defaultTip;
		defaultValue = opt.defaultValue;
		isPre = opt.isPre;
		var i, l, treeData = [], tmpMap = [];
		
		//前置即要求其他节点都在其下;要求其他节点是其之下
		if(url == undefined || url == null){
			if(defaultTip && isPre){ //如果有默认传参显示，则在树头添加
				var title = {};
				title[idField] = defaultValue || '';
				title[textField] = defaultTip;
				data.unshift(title);
			}
		}
		
		
		for (i = 0, l = data.length; i < l; i++) {
			tmpMap[data[i][idField]] = data[i];
		}
		for (i = 0, l = data.length; i < l; i++) {
			if (tmpMap[data[i][parentField]] && data[i][idField] != data[i][parentField]) {
				if (!tmpMap[data[i][parentField]]['children'])
					tmpMap[data[i][parentField]]['children'] = [];
				data[i]['text'] = data[i][textField];
				data[i]['id'] = data[i][idField];
				tmpMap[data[i][parentField]]['children'].push(data[i]);
			} else {
				data[i]['text'] = data[i][textField];
				data[i]['id'] = data[i][idField];
				treeData.push(data[i]);
			}
		}
		
		//后置即要求其他节点都在其下但不是其子节点
		if(url == undefined || url == null){
			if(defaultTip && !isPre){ //如果有默认传参显示，则在树头添加
				var title = {};
				title['id'] = defaultValue || '';
				title['text']=defaultTip;
				treeData.unshift(title);
			}
		}
		//console.info(treeData);
		return treeData;
	}
	
	return data;
};

/**
 * 
 * 
 * @requires jQuery,EasyUI
 * 
 * 扩展treegrid，使其支持平滑数据格式
 */
$.fn.treegrid.defaults.loadFilter = function(data, parentId) {
	
	var opt = $(this).data().treegrid.options;
	
	var idField, textField, parentField;
	if (opt.parentField) {
		idField = opt.idField || 'id';
		textField = opt.textField || 'name';
		parentField = opt.parentField;
		
		var i, l, treeData = [], tmpMap = [];
		//console.info()
		//alert(data.length);
		for (i = 0, l = data.length; i < l; i++) {
			tmpMap[data[i][idField]] = data[i];
		}
		for (i = 0, l = data.length; i < l; i++) {
			if (tmpMap[data[i][parentField]] && data[i][idField] != data[i][parentField]) {
				if (!tmpMap[data[i][parentField]]['children'])
					tmpMap[data[i][parentField]]['children'] = [];
				data[i]['name'] = data[i][textField];				
				tmpMap[data[i][parentField]]['children'].push(data[i]);
			} else {
				data[i]['name'] = data[i][textField];				
				treeData.push(data[i]);
			}
		}
		return treeData;
	}
	return data;
};

/**
 * 
 * 
 * @requires jQuery,EasyUI
 * 
 * 扩展combotree，使其支持平滑数据格式
 */
$.fn.combotree.defaults.loadFilter = $.fn.tree.defaults.loadFilter;


//获取浏览器页面可见高度和宽度
var _PageHeight = document.documentElement.clientHeight,
_PageWidth = document.documentElement.clientWidth;
//计算loading框距离顶部和左部的距离（loading框的宽度为215px，高度为61px）
var _LoadingTop = _PageHeight > 61 ? (_PageHeight - 61) / 2 : 0,
_LoadingLeft = _PageWidth > 215 ? (_PageWidth - 215) / 2 : 0;
//加载gif地址
var Loadimagerul= getRootPath() + "/res/images/loading.gif";
//在页面未加载完毕之前显示的loading Html自定义内容
var _LoadingHtml = '<div id="loadingDiv" style="position:absolute;left:0;width:100%;height:' + _PageHeight + 'px;top:0;background:#f3f8ff;opacity:1;filter:alpha(opacity=80);z-index:10000;"><div style="position: absolute; cursor: wait; left: ' + _LoadingLeft + 'px; top:' + _LoadingTop + 'px; width:70px; height: 35px; line-height: 35px; padding-left: 38px; padding-right: 5px; background: #fff url('+Loadimagerul+') no-repeat scroll 14px 11px; border: 1px solid #83b6ff; color: #696969; font-family:\'Microsoft YaHei\';">加载中...</div></div>';
//呈现loading效果
//document.write(_LoadingHtml);
//监听加载状态改变
//document.onreadystatechange = completeLoading;
//加载状态为complete时移除loading效果
function completeLoading() {
	if (document.readyState == "complete") {
		var loadingMask = document.getElementById('loadingDiv');
		loadingMask.parentNode.removeChild(loadingMask);
	}
}

$.extend($.fn.validatebox.defaults.rules, {  
    /*必须和某个字段相等*/
    equalTo: {
        validator:function(value,param){
            return $(param[0]).val() == value;
        },
        message:'字段不匹配'
    }
           
});


function hasScrollbar() {
    return document.body.scrollHeight > (window.innerHeight || document.documentElement.clientHeight);
}

function getScrollbarWidth() {

    var scrollDiv = document.createElement("div");
    scrollDiv.style.cssText = 'width: 99px; height: 99px; overflow: scroll; position: absolute; top: -9999px;';
    document.body.appendChild(scrollDiv);
    var scrollbarWidth = scrollDiv.offsetWidth - scrollDiv.clientWidth;
    document.body.removeChild(scrollDiv);

    return scrollbarWidth;

}

window.onresize = function(){
	//alert(document.documentElement.clientWidth +"--11-"+document.documentElement.clientHeight);
	try{
		//获取滚动条宽度
		//$("#GLOBA_DIALOG_ID").dialog('maximize');
		if($("#GLOBA_DIALOG_FULL")){
			if($("#GLOBA_DIALOG_FULL").dialog('options').lessFix == false){
			
				$("#GLOBA_DIALOG_FULL").dialog('resize',{
					width:document.documentElement.clientWidth,
					height:document.documentElement.clientHeight
				});
			};
		}
	}catch(e){
		//alert(e);
	}
}


$.extend($.fn.validatebox.defaults.rules, {    
    equals: {    //密码相同验证   
        validator: function(value,param){    
            return value == $(param[0]).val();    
        },    
        message: '两次输入密码不匹配'   
    },
    ipvalidate: {    //ip地址验证 
        validator: function(value){    
        	var pattern = /^(?:(?:^|,)(?:[0-9]|[1-9]\d|1\d{2}|2[0-4]\d|25[0-5])(?:\.(?:[0-9]|[1-9]\d|1\d{2}|2[0-4]\d|25[0-5])){3})+$/;
            return pattern.test(value);    
        },    
        message: '请输入正确的IP地址，若有多个IP请用英文逗号区分！'   
    },
    mobilephone: { //验证手机号   
        validator: function(value, param){ 
         return /^1[3-8]+\d{9}$/.test(value);
        },    
        message: '请输入正确的手机号码。'   
    },
    pwdvalidate: { //验证密码  
        validator: function(value, param){ 
        var reg = /^(?![a-zA-z]+$)(?!\d+$)(?![!@#^&*]+$)[a-zA-Z\d!@#^&*]+$/;
         return reg.test(value);
        },    
        message: '密码必须包含数字、英文字母、特殊符号中的两种或两种以上'   
    }
});  

//获取树节点层级
$.extend($.fn.tree.methods, {
		    getLevel:function(jq,target){
		        var l = $(target).parentsUntil("ul.tree","ul");
		        return l.length+1;
		    }
});

