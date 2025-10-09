<style>

	.showr-table td {

		font-size: 0.8vw;
		text-align: left;
	}
	.check{
		background-color: #ebf7ff;
	}
</style>
<script type="text/javascript" src="${base}/mainface/js/jquery-1.10.1.min.js"></script>
<script src="${base}\mainface\layer\layer.js"></script>
<link type="text/css" rel="stylesheet" href="${base}\mainface\css\filemtc.css">
<div style="text-align: center;  margin-top: 20px; ">
	<span >年份&nbsp;：&nbsp;&nbsp;&nbsp;${sjrw?if_exists.nianf!}</span>
	<span style="margin-left: 15%; ">任务名称&nbsp;:&nbsp;&nbsp;&nbsp;${sjrw?if_exists.rwmc!}</span>
</div>
<div  style="padding: 1vw;width: 100%;height: 60vw;background: #fff;border-radius: 4px;overflow: hidden;">
	<div class="show-l" style="width:19%; height: 100%; position:fixed; ">
		<div class="show-title" >
			<span>数据导入</span>
		</div>
		<div class="show-l-t  "   style="  height: 100%;" >
			<table class="showr-table" style="font-size: 0.8vw;   " id="lxdj">
				<tbody >
				<tr onclick="bjxxdr(this)">
					<td>班级信息导入</td>
				</tr>
				<tr onclick="xsxxdr(this,${sjrw?if_exists.nianf!})">
					<td>学生信息导入</td>
				</tr>
				<tr onclick="jzgxxdr(this,${sjrw?if_exists.nianf!})">
					<td>教职工信息导入</td>
				</tr>
				<tr onclick="zrjspxqkdr(this,${sjrw?if_exists.nianf!})">
					<td>专任教师培训信息导入</td>
				</tr>
				<tr onclick="jsskflqkdr(this,${sjrw?if_exists.nianf!})">
					<td>教师授课分类情况导入</td>
				</tr>
				<tr onclick="xiaoshexxdr(this,${sjrw?if_exists.nianf!})">
					<td>校舍信息导入</td>
				</tr>
				</tbody>
			</table>
		</div>

	</div>
	<div class="show-r showDiv"   style="width: 79%; height: 100%; ">

	</div>
</div>
<script>
	function init(){
		$(".check").each(function(){
			$(this).removeClass("check");
		})
	}
	function useifram(url){
		$(".showDiv").append("<IFRAME name='Main' id='Main' STYLE='WIDTH:100%;HEIGHT:100%' scrolling='yes'  frameborder='0' src='"+url+"' marginWidth='0' marginHeight='0' frameBorder='0' LEFTMARGIN='0' TOPMARGIN='0'></IFRAME>");
		var frm = document.getElementById('Main');   //  等iframe加载完毕

	}
	function bjxxdr(obj,nf) {
		var nfs = nf
		init();
		$(obj).addClass("check");
		$(".showDiv").empty();
		var url = '${base}/ywmk/bjxx!list.action?ljdz=1';
		useifram(url);
	}
	function xsxxdr(obj,nf) {
		var nfs = nf
		init();
		$(obj).addClass("check");
		$(".showDiv").empty();
		var url = '${base}/ywmk/xsxx!list.action?ljdz=1&nf='+nfs+'';
		useifram(url);
	}
	function jzgxxdr(obj,nf) {
		var nfs = nf
		init();
		$(obj).addClass("check");
		$(".showDiv").empty();
		var url = '${base}/ywmk/jzgxx!list.action?ljdz=1&nf='+nfs+'';
		useifram(url);
	}
	function zrjspxqkdr(obj,nf) {
		var nfs = nf
		init();
		$(obj).addClass("check");
		$(".showDiv").empty();
		var url = '${base}/ywmk/zrjspxqk!list.action?ljdz=1&nf='+nfs+'';
		useifram(url);
	}
	function jsskflqkdr(obj,nf) {
		var nfs = nf

		init();
		$(obj).addClass("check");
		$(".showDiv").empty();
		var url = '${base}/ywmk/jsskflqk!list.action?ljdz=1&nf='+nfs+'';
		useifram(url);
	}
	function xiaoshexxdr(obj,nf) {
		var nfs = nf
		init();
		$(obj).addClass("check");
		$(".showDiv").empty();
		var url = '${base}/ywmk/xiaoshexx!list.action?ljdz=1&nf='+nfs+'';
		useifram(url);
	}

</script>