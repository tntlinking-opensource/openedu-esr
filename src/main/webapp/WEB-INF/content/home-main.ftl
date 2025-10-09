<html>
<head>

	<meta charset="utf-8">
	<title></title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta name="description" content="">
	<meta name="author" content="">
	
	<!-- BEGIN GLOBAL MANDATORY STYLES -->
	<link type="text/css" rel="stylesheet" href="${base}/mainface/css/bootstrap.min.css">
	<link type="text/css" rel="stylesheet" href="${base}/mainface/css/bootstrap-responsive.min.css">
	<link type="text/css" rel="stylesheet" href="${base}/mainface/css/font-awesome.min.css">
	<link type="text/css" rel="stylesheet" href="${base}/mainface/css/style-metro.css">
	<link type="text/css" rel="stylesheet" href="${base}/mainface/css/style.css">
	<link type="text/css" rel="stylesheet" href="${base}/mainface/css/style-responsive.css">
	<link id="style_color" type="text/css" rel="stylesheet" href="${base}/mainface/css/default.css">
	<link type="text/css" rel="stylesheet" href="${base}/mainface/css/uniform.default.css">
	
	<script type="text/javascript" src="${base}/mainface/js/jquery-1.10.1.min.js"></script>
	
	<link type="text/css" rel="stylesheet" href="${base}/scripts/pfw/pfw.page.metro.css"/>
	<script type="text/javascript" src="${base}/scripts/pfw/pfw.page.metro.js"></script>
	
	<link type="text/css" rel="stylesheet" href="${base}/scripts/jquery/jquery-validate/jquery.validate.css"/>
	<script type="text/javascript" src="${base}/scripts/jquery/jquery-validate/jquery.validate.pack.js"></script>
  	<script type="text/javascript" src="${base}/scripts/jquery/jquery-validate/localization/messages_cn.js"></script>
  	
	<link rel="stylesheet" type="text/css" href="${base}/mainface/icon/icon_skin/icon_skin.css">
	<link rel="stylesheet" type="text/css" href="${base}/mainface/icon/icon_skin/icon.css">
	
	<link rel="stylesheet" type="text/css" href="${base}/scripts/jquery/easyui/themes/default/easyui.css">
  	<link rel="stylesheet" type="text/css" href="${base}/scripts/jquery/easyui/themes/icon.css">
  	 
	<script type="text/javascript" src="${base}/mainface/js/jquery-migrate-1.2.1.min.js"></script>
	<!-- IMPORTANT! Load jquery-ui-1.10.1.custom.min.js before bootstrap.min.js to fix bootstrap tooltip conflict with jquery ui tooltip -->
	<script type="text/javascript" src="${base}/mainface/js/jquery-ui-1.10.1.custom.min.js"></script>      
	<script type="text/javascript" src="${base}/mainface/js/bootstrap.min.js"></script>
	<script type="text/javascript" async="" src="${base}/mainface/js/dc.js">

	<script type="text/javascript" src="${base}/mainface/js/jquery.slimscroll.min.js"></script>
	<script type="text/javascript" src="${base}/mainface/js/jquery.blockui.min.js"></script>  
	<script type="text/javascript" src="${base}/mainface/js/jquery.cookie.min.js"></script>
	<script type="text/javascript" src="${base}/mainface/js/jquery.uniform.min.js"></script>
	
	<script type="text/javascript" src="${base}/scripts/jquery/easyui/jquery.easyui.min.js"></script>
	
	<script type="text/javascript" src="${base}/scripts/js/select.js"></script>
	<script type="text/javascript" src="${base}/scripts/My97DatePicker/WdatePicker.js"></script>
	
	<link rel="shortcut icon" href="${base}/mainface/image/favicon.ico" />
	
	<script type="text/javascript" src="${base}/scripts/echarts.min.js"></script>
	
	<link rel="stylesheet" type="text/css" href="${base}/mainface/newlogin/css/workplat.css">
	<link rel="stylesheet" type="text/css" href="${base}/mainface/newlogin/css/index3.css"> 
	<link rel="stylesheet" type="text/css" href="${base}/mainface/newlogin/css/workbench.css"> 
	
	<!-- END GLOBAL MANDATORY STYLES -->

	<!--Form CSS-->

	
	<!--End Form CSS-->
	
	<!--解决jquery-1.10.1.min.js不支持$.browser问题-->
  	<script>
	(function(jQuery){ 
	
	if(jQuery.browser) return; 
	
	jQuery.browser = {}; 
	jQuery.browser.mozilla = false; 
	jQuery.browser.webkit = false; 
	jQuery.browser.opera = false; 
	jQuery.browser.msie = false; 
	
	var nAgt = navigator.userAgent; 
	jQuery.browser.name = navigator.appName; 
	jQuery.browser.fullVersion = ''+parseFloat(navigator.appVersion); 
	jQuery.browser.majorVersion = parseInt(navigator.appVersion,10); 
	var nameOffset,verOffset,ix; 
	
	// In Opera, the true version is after "Opera" or after "Version" 
	if ((verOffset=nAgt.indexOf("Opera"))!=-1) { 
	jQuery.browser.opera = true; 
	jQuery.browser.name = "Opera"; 
	jQuery.browser.fullVersion = nAgt.substring(verOffset+6); 
	if ((verOffset=nAgt.indexOf("Version"))!=-1) 
	jQuery.browser.fullVersion = nAgt.substring(verOffset+8); 
	} 
	// In MSIE, the true version is after "MSIE" in userAgent 
	else if ((verOffset=nAgt.indexOf("MSIE"))!=-1) { 
	jQuery.browser.msie = true; 
	jQuery.browser.name = "Microsoft Internet Explorer"; 
	jQuery.browser.fullVersion = nAgt.substring(verOffset+5); 
	} 
	// In Chrome, the true version is after "Chrome" 
	else if ((verOffset=nAgt.indexOf("Chrome"))!=-1) { 
	jQuery.browser.webkit = true; 
	jQuery.browser.name = "Chrome"; 
	jQuery.browser.fullVersion = nAgt.substring(verOffset+7); 
	} 
	// In Safari, the true version is after "Safari" or after "Version" 
	else if ((verOffset=nAgt.indexOf("Safari"))!=-1) { 
	jQuery.browser.webkit = true; 
	jQuery.browser.name = "Safari"; 
	jQuery.browser.fullVersion = nAgt.substring(verOffset+7); 
	if ((verOffset=nAgt.indexOf("Version"))!=-1) 
	jQuery.browser.fullVersion = nAgt.substring(verOffset+8); 
	} 
	// In Firefox, the true version is after "Firefox" 
	else if ((verOffset=nAgt.indexOf("Firefox"))!=-1) { 
	jQuery.browser.mozilla = true; 
	jQuery.browser.name = "Firefox"; 
	jQuery.browser.fullVersion = nAgt.substring(verOffset+8); 
	} 
	// In most other browsers, "name/version" is at the end of userAgent 
	else if ( (nameOffset=nAgt.lastIndexOf(' ')+1) < 
	(verOffset=nAgt.lastIndexOf('/')) ) 
	{ 
	jQuery.browser.name = nAgt.substring(nameOffset,verOffset); 
	jQuery.browser.fullVersion = nAgt.substring(verOffset+1); 
	if (jQuery.browser.name.toLowerCase()==jQuery.browser.name.toUpperCase()) { 
	jQuery.browser.name = navigator.appName; 
	} 
	} 
	// trim the fullVersion string at semicolon/space if present 
	if ((ix=jQuery.browser.fullVersion.indexOf(";"))!=-1) 
	jQuery.browser.fullVersion=jQuery.browser.fullVersion.substring(0,ix); 
	if ((ix=jQuery.browser.fullVersion.indexOf(" "))!=-1) 
	jQuery.browser.fullVersion=jQuery.browser.fullVersion.substring(0,ix); 
	
	jQuery.browser.majorVersion = parseInt(''+jQuery.browser.fullVersion,10); 
	if (isNaN(jQuery.browser.majorVersion)) { 
	jQuery.browser.fullVersion = ''+parseFloat(navigator.appVersion); 
	jQuery.browser.majorVersion = parseInt(navigator.appVersion,10); 
	} 
	jQuery.browser.version = jQuery.browser.majorVersion; 
	})(jQuery); 
  </script>
  <!--解决jquery-1.10.1.min.js不支持$.browser问题-->
	
	<!--让jquery validate的错误信息在输入域同处一行-->
	<style>
		label{
			display:inline;
		}
	</style>	

</head>
<body style="background-color:#f5f7f9;">
	<!--
		<img src="${base}/mainface/image/bg/loadingbg.png" style="width:100%">
	-->
	
	<div style="display:flex;margin:5px;">
		<div style="width:60%;padding:10px;">
			
			<div>
				<!--待办事项start-->
				<div class="portlet wkp-div" style="min-height:710px;padding: 10px;">
					<div class="portlet-title" style="padding-bottom:5px;position: relative;">
						<div class="caption" >
							<i class="icon-tasks" style="font-size: 18px;margin-top: 3px;color: rgb(41 142 236 / 85%);"></i>
							<span style="font-size:16px;">系统工作流程</span>
						</div>
					</div>
					<div class="portlet-body" style="position: relative;">
					
						<div>
							<img onclick="window.open('${base}/home!impmain.action')" style="width:45%;margin-left: 12%;cursor: pointer;" src="${base}/mainface/newlogin/images/work_pro_no1.png">
						</div>
						<div>
							<img style="width:35%;margin-left: 60%;" src="${base}/mainface/newlogin/images/work_pro_no2.png">
							<img style="width: 30%;position: absolute;left: 10%;top: 79%;" src="${base}/mainface/newlogin/images/work_pro_no3.png">
						</div>
						
						<img style="position: absolute;top: 40px;width: 32%;left: 40%;" src="${base}/mainface/newlogin/images/work_pro_no4.png">
						<img style="position: absolute;bottom: -5%;left: 32%;" src="${base}/mainface/newlogin/images/work_pro_no5.png">
						
					</div>
				</div>
				<!--待办事项end-->
			</div>
		</div>
		<div class="" style="padding:10px;width:40%;">
			<!--常用功能start-->
			<div class="portlet wkp-div" style="padding: 10px;min-height:330px;">
				<div class="portlet-title" style="padding-bottom:5px;">
					<div class="caption">
						<i class="icon-tags" style="font-size: 18px;margin-top: 3px;color: #298eecd9;"></i>
						<span style="font-size:16px;">学校基本情况</span>
					</div>
				</div>
				<div class="portlet-body" style="display:flex;padding-top: 50px;">
					
					<div style="width:20%;height: 120px;border-right: 1px dashed #eee;margin: 0 auto;">
						<div style="text-align: center;">
							<img style="width:82px" src="${base}/mainface/newlogin/images/work_icon_1.png">
						</div>
						<div style="text-align: center;font-size: 14px;padding: 10px 10px 10px 0px;">
							学生人数
						</div>
						<div style="text-align: center;font-size: 18px;color: #298eecd9;">
							1,600&nbsp;<span style="color: #4d5259;font-size: 14px;">人</span>
						</div>
					</div>
					<div style="width:20%;height: 120px;border-right: 1px dashed #eee;margin: 0 auto;">
						<div style="text-align: center;">
							<img style="width:70px" src="${base}/mainface/newlogin/images/work_icon_5.png">
						</div>
						<div style="text-align: center;font-size: 14px;padding: 10px 10px 10px 0px;">
							教职工人数
						</div>
						<div style="text-align: center;font-size: 18px;color: #298eecd9;">
							120&nbsp;<span style="color: #4d5259;font-size: 14px;">人</span>
						</div>
					</div>
					<div style="width:20%;height: 120px;border-right: 1px dashed #eee;margin: 0 auto;">
						<div style="text-align: center;">
							<img style="width:70px" src="${base}/mainface/newlogin/images/work_icon_7.png">
						</div>
						<div style="text-align: center;font-size: 14px;padding: 10px 10px 10px 0px;">
							设备资产
						</div>
						<div style="text-align: center;font-size: 18px;color: #298eecd9;">
							28,000&nbsp;<span style="color: #4d5259;font-size: 14px;">万元</span>
						</div>
					</div>
					<div style="width:20%;height: 120px;border-right: 0px dashed #eee;margin: 0 auto;">
						<div style="text-align: center;">
							<img style="width:70px" src="${base}/mainface/newlogin/images/work_icon_6.png">
						</div>
						<div style="text-align: center;font-size: 14px;padding: 10px 10px 10px 0px;">
							占地面积
						</div>
						<div style="text-align: center;font-size: 18px;color: #298eecd9;">
							34,000&nbsp;<span style="color: #4d5259;font-size: 14px;">人</span>
						</div>
					</div>
					
					
				</div>
			</div>
			<!--常用功能end-->
			<!--消息提醒start-->
			<div class="portlet wkp-div" style="padding: 10px;min-height:330px;">
				<div class="portlet-title" style="padding-bottom:5px;">
					<div class="caption">
						<i class="icon-reorder" style="font-size: 18px;margin-top: 3px;color: #298eecd9;"></i>
						<span style="font-size:16px;">历年报表数据</span>
					</div>
				</div>
				<div class="portlet-body">
					
					<div class="main">
	                    <div class="main-item">
	                        <div class="img-box"><!-- 偏移角度 -(180-180/100*48) -->
	                            <div class="active" style="transform: rotate(-93.6deg);"></div>
	                            <p class="num">48%</p>
	                            <div>基础数据</div>
	                        </div>
	                        <p style="border-bottom: 1px dashed #ddd">2023年</p>
	                        <div style="color:#f94e4ed9">基础数据不完整</div>
	                    </div>
	                    <div class="main-item" style="margin: 0px 15px;padding-left: 10px;">
	                        <div class="img-box">
	                            <div class="active" style="transform: rotate(-109.8deg);"></div>
	                            <p class="num">39%</p>
	                        </div>
	                        <p style="border-bottom: 1px dashed #ddd">2022年</p>
	                        <div style="color:#298eecd9">查询导出</div>
	                    </div>
	                    <div class="main-item" style="margin: 0px 15px;padding-left: 10px;">
	                        <div class="img-box">
	                            <div class="active" style="transform: rotate(0deg);"></div>
	                            <p class="num">100%</p>
	                        </div>
	                        <p style="border-bottom: 1px dashed #ddd">2021年</p>
	                        <div style="color:#298eecd9">查询导出</div>
	                    </div>
	                    <div class="main-item" style="padding-left: 10px;">
	                        <div class="img-box">
	                            <div class="active" style="transform: rotate(0deg);"></div>
	                            <p class="num">100%</p>
	                        </div>
	                        <p style="border-bottom: 1px dashed #ddd">2020年</p>
	                        <div style="color:#298eecd9">查询导出</div>
	                    </div>
	                </div>
					
					
				</div>
			</div>
			<!--消息提醒end-->
			
		</div>
	</div>
	
</body>
<script>
	//location.href="${base}/home!index3.action";
</script>
</html>
