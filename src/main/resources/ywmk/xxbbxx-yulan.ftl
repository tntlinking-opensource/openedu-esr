<#include "/macro/crud-metro-page3.ftl" >

<@crudmetropage3>

	<style>
		.reptTable
		{
			width: 100%;
		}
		.reptTable tr td 
		{
			height: 30px;
			width : 40px;
			text-align:center;
		}
		.rept_td_Input
		{
			width: 91%;
			height: 30px !important;
			margin-bottom: 0px !important;
		}
		.tdclickon
		{
		    border: 1px solid #3943db;
			background: rgb(20 20 217 / 20%);
		}
		.noopttd
		{
			text-align:center;
			background-color: #f0f0f0;
		}
		.jzcellcss
		{
			background-color: #fe8282;
		}
		.inputNumcss
		{
			height: 30px !important;
		    width: 98%;
		    margin-bottom: 0px !important;
		    padding: 0px !important;
		}
		.inputNumcssFormula
		{
			height: 30px !important;
		    width: 98%;
		    margin-bottom: 0px !important;
		    padding: 0px !important;
		}
		input[readonly], select[readonly], textarea[readonly]
		{
			cursor: not-allowed;
    		background-color: #e5e5e5 !important;
		}
	</style>
	<div style="min-height:1200px">
		<table style="width:100%">
			<tr>
				<#assign jsondata=jsonstr?eval /> 
				<td colspan="2" style="text-align:center;font-size: 18px;font-weight: bold;">
					${jsondata.resultinfo[0].reportname!}
				</td>
			</tr>
			<tr><td colspan="2" style="text-align:right">表   号：${jsondata.resultinfo[0].reportcode!}</td></tr>
			<tr><td colspan="2" style="text-align:right">制定机关：教  育  部</td></tr>
			<tr><td colspan="2" style="text-align:right">批准机关：国 家 统 计 局</td></tr>
			<tr><td colspan="2" style="text-align:right">批准文号：国统制[202 ]  号</td></tr>
			<tr>
				<td style="text-align:left">学校（机构）名称：</td>
				<td style="text-align:right">批准文号：国统制[202 ]  号</td>
			</tr>
			<tr>
				<td style="text-align:left">学校（机构）标识码：</td>
				<td style="text-align:right">有效期至：202  年  月</td>
			</tr>
			<tr>
				<td style="text-align:left">统一社会信用代码：</td>
				<#if jsondata.resultinfo[0].jldw?exists>
					<td style="text-align:right">计量单位：${jsondata.resultinfo[0].jldw!}</td>
				</#if>
			</tr>
			<!--
			<tr><td colspan="2" style="text-align:center">（２０２１学年）</td></tr>
			-->
		<table>
		<p/>
			${jsondata.resultinfo[0].reptTable?replace("<br>","")!}
	</div>
					
	<script>
		//根据x，y得到业务y含义
		function getvalByxy(x,y){
			var rowx = $("td[ifjzcell=1]").attr('x');//得到基准单元格x
			var ywystr = "";
			for(var i=0;i<rowx-1 ; i++){
				dqjzdygid = "#td_"+(rowx-i-1)+"_"+y; 
				var ywyval = $(dqjzdygid).html();
				
				if($(dqjzdygid)){
					if(ywyval != '' && ywyval != null &&  typeof(ywyval) != 'undefined'){
						ywystr = ywyval +"|"+ ywystr;
					}else{
						
						for(var j=0;j<y;j++){
							dqjzdygid = "#td_"+(rowx-i-1)+"_"+(y-j); 
							var ywyval = $(dqjzdygid).html();
							if(ywyval != '' && ywyval != null &&  typeof(ywyval) != 'undefined'){
								if($(dqjzdygid).attr('colspan')>0 || $(dqjzdygid).attr('rowspan')>0  ){
									ywystr = ywyval +"|"+ ywystr;
									break
								}
							}
						}
					}
				}
				
			}
			
			
			if(rowx ==='1'){
				dqjzdygid = "#td_"+rowx+"_"+y; 
				var ywyval = $(dqjzdygid).html();
				if($(dqjzdygid)){
					if(ywyval != '' && ywyval != null &&  typeof(ywyval) != 'undefined'){
						ywystr = ywyval +"|"+ ywystr;
					}else{
						
						for(var j=0;j<y;j++){
							dqjzdygid = "#td_"+(rowx-i-1)+"_"+(y-j); 
							var ywyval = $(dqjzdygid).html();
							if(ywyval != '' && ywyval != null &&  typeof(ywyval) != 'undefined'){
								if($(dqjzdygid).attr('colspan')>0 || $(dqjzdygid).attr('rowspan')>0  ){
									ywystr = ywyval +"|"+ ywystr;
									break
								}
							}
						}
					}
				}
			}
			
			if(typeof(dqjzdygid) !="undefined" && typeof($(dqjzdygid)) !="undefined"   ){
				if(ywystr.charAt(ywystr.length-1)=='|'){
					ywystr = ywystr.substr(0,ywystr.length-1);
				}
			}
			return ywystr;
		}
		
		
		
		function yjb0(){
		
			$("input[type=text]").each(function(){
				var textval=$(this).val();
				if(textval == '' || textval == null || typeof(textval) == 'undefined'){
					$(this).attr("value",0);
				}
	         })
		}
		
		function expExcle()
		{
			window.open("${base}/ywmk/report_info!expExcle.action?id=${id!}");
		}
		
		function impExcle()
		{
			window.open("${base}/ywmk/report_info!impExcleMain.action?id=${id!}");
		}
		
		function del_html_tags(str)
		{
		    var words = '';
		    words = str.replace(/<[^>]+>/g,"");
		    return words;
		}
		
	
		function save(){
			
	        $("td[typ=formula]").find("input").each(function(){
				console.log($(this).val())
	         	$(this).attr("value",$(this).val());
	         })
			
			$("input[type=text][typ=numb]").each(function(){
	         	$(this).attr("value",$(this).val());
	         })
			var form=$("<form>");//定义一个form表单
				form.attr("style","display:none");
				form.attr("target","");
				form.attr("method","post");
				form.attr("action","${base}/sxglpt/report_info!baocUserReport.action");
				
				var tbleHtml = $("#reptTable").prop("outerHTML");
				$("<input type='hidden' value='"+tbleHtml+"' name='tabblestr'>").appendTo(form);  
				
				$("#reptTable").appendTo(form);
			   	$("<input type='hidden'   name='baobid'   value='${id!}'/>").appendTo(form);
			   
				$("body").append(form);//将表单放置在web中
				form.submit();//表单提交 
				
				form.remove();
		}
		
		var allformula = new Map();
		var fdqgsArr   = new Map();//非当前公式,因为也可能会有关联,也要最后计算一下
		
		
		
		$(document).ready(function() {
			 
				//把checkbox单元格去掉
				$(".noopttd").remove();
				$(".jzcellcss").removeClass("jzcellcss");
				
				$("td[typ=numb]").each(function(){
					var xstr = $(this).attr("x");
					var ystr = $(this).attr("y");
					var ywxstr = $(this).attr("ywx");
					var ywystr = $(this).attr("ywy");
					var idstr  = $(this).attr("id");
					
					
					var xy=idstr.replace("td_","");
					xy="td_"+xy.replace("_","$");
					
					var ywhyxstr = $(this).parent().find("td:first").html();
					ywhyxstr = del_html_tags(ywhyxstr.replace(/&nbsp;/g,''));
					
					ywhyystr = getvalByxy(xstr,ystr);
					
					var ywhyxy=ywhyxstr + "$" + ywhyystr;
					
					$(this).html("");
					var _inpt = $("<input typ='numb' type='text' id='"+idstr+"' name='ipt_"+xy+"_"+ywxstr+"$"+ywystr+"_"+ywhyxy+"' ywhyX='"+ywhyxstr+"' ywhyY='"+ywhyystr+"' class='inputNumcss' "
								+" x='"+xstr+"' y='"+ystr+"' ywx='"+ywxstr+"' ywy='"+ywystr+"' />");
					$(this).append(_inpt);
				});
				$("td[typ=textstr]").each(function(){
					var xstr = $(this).attr("x");
					var ystr = $(this).attr("y");
					var ywxstr = $(this).attr("ywx");
					var ywystr = $(this).attr("ywy");
					var idstr  = $(this).attr("id");
					
					
					var xy=idstr.replace("td_","");
					xy="td_"+xy.replace("_","$");
					
					var ywhyxstr = $(this).parent().find("td:first").html();
					ywhyxstr = del_html_tags(ywhyxstr.replace(/&nbsp;/g,''));
					
					ywhyystr = getvalByxy(xstr,ystr);
					
					var ywhyxy=ywhyxstr + "$" + ywhyystr;
					
					$(this).html("");
					var _inpt = $("<input typ='textstr' type='text' id='"+idstr+"' name='ipt_"+xy+"_"+ywxstr+"$"+ywystr+"_"+ywhyxy+"' ywhyX='"+ywhyxstr+"' ywhyY='"+ywhyystr+"' class='inputNumcss' "
								+" x='"+xstr+"' y='"+ystr+"' ywx='"+ywxstr+"' ywy='"+ywystr+"' />");
					$(this).append(_inpt);
				});
				$("td[typ=selectstr]").each(function(){
					var xstr = $(this).attr("x");
					var ystr = $(this).attr("y");
					var ywxstr = $(this).attr("ywx");
					var ywystr = $(this).attr("ywy");
					var idstr  = $(this).attr("id");
					var selectrules =  $(this).attr("selectrules");
					
					if(selectrules && selectrules != null && selectrules != ""){
					
						var xy=idstr.replace("td_","");
						xy="td_"+xy.replace("_","$");
						
						var ywhyxstr = $(this).parent().find("td:first").html();
						ywhyxstr = del_html_tags(ywhyxstr.replace(/&nbsp;/g,''));
						
						ywhyystr = getvalByxy(xstr,ystr);
						
						var ywhyxy=ywhyxstr + "$" + ywhyystr;
						
						$(this).html("");
						
						var html = "<select typ='selectstr' id='"+idstr+"' name='ipt_"+xy+"_"+ywxstr+"$"+ywystr+"_"+ywhyxy+"' ywhyX='"+ywhyxstr+"' ywhyY='"+ywhyystr+"' class='inputNumcss' x='"+xstr+"' y='"+ystr+"' ywx='"+ywxstr+"' ywy='"+ywystr+"' >";
						
						html += "<option >==请选择==</option>";
						
						var sltRuleArr = selectrules.split("$");
						for(var i =0;i<sltRuleArr.length;i++){
							
							var sltArr = sltRuleArr[i].split("#");
							
							html += "<option value='"+sltArr[0]+"'>"+sltArr[1]+"</option>";
							
						}
						
						html+=" </select>";
						$(this).append(html);
					
					}
					
				});
			$("td[typ=formula]").each(function(){
				var xstr = $(this).attr("x");
				var ystr = $(this).attr("y");
				var ywxstr = $(this).attr("ywx");
				var ywystr = $(this).attr("ywy");
				var idstr  = $(this).attr("id");
				
				var xy=idstr.replace("td_","");
				xy="td_"+xy.replace("_","$");
				
				var ywhyxstr = $(this).parent().find("td:first").html();
				ywhyxstr = del_html_tags(ywhyxstr.replace(/&nbsp;/g,''));
				
				ywhyystr = getvalByxy(xstr,ystr);
				
				var ywhyxy=ywhyxstr + "$" + ywhyystr;
				
				var inpval=$(this).find("input").val();
				
				$(this).html("");
				
				if(inpval != null && inpval != '' && typeof(inpval) != 'undefined'){
					var _inpt = $("<input typ='formula' type='text' id='"+idstr+"'   name='ipt_"+xy+"_"+ywxstr+"$"+ywystr+"_"+ywhyxy+"' ywhyX='"+ywhyxstr+"' ywhyY='"+ywhyystr+"'  class='inputNumcssFormula' "
							+" x='"+xstr+"' y='"+ystr+"' ywx='"+ywxstr+"' ywy='"+ywystr+"' readonly value='"+inpval+"' />");
					allformula.set(_inpt,$(this).attr('rules'));
				}else{
					var _inpt = $("<input typ='formula' type='text' id='"+idstr+"'   name='ipt_"+xy+"_"+ywxstr+"$"+ywystr+"_"+ywhyxy+"' ywhyX='"+ywhyxstr+"' ywhyY='"+ywhyystr+"'  class='inputNumcssFormula' "
							+" x='"+xstr+"' y='"+ystr+"' ywx='"+ywxstr+"' ywy='"+ywystr+"' readonly   />");
					allformula.set(_inpt,$(this).attr('rules'));
					
				}
				
				
				$(this).append(_inpt)
			});
			
			//给所有输入框绑定键盘事件
	 		$("input[type='text']").bind('keydown',function(event){
	             //下
	             if(event.keyCode == "40")    
	             {
	             	var xstr = parseInt($(this).attr("x")) + 1;
	             	var ystr = parseInt($(this).attr("y"));
	             	$("input[type=text][x="+xstr+"][y="+ystr+"]").focus();
	             }
	             //上
	             if(event.keyCode == "38")    
	             {
	             	var xstr = parseInt($(this).attr("x")) -1;
	             	var ystr = parseInt($(this).attr("y"));
	             	$("input[type=text][x="+xstr+"][y="+ystr+"]").focus();
	             }	 
	             //左
	             if(event.keyCode == "37")    
	             {
	             	var xstr = parseInt($(this).attr("x"));
	             	var ystr = parseInt($(this).attr("y")) - 1;
	             	$("input[type=text][x="+xstr+"][y="+ystr+"]").focus();
	             }
	             //右            
	             if(event.keyCode == "39")    
	             {
	             	var xstr = parseInt($(this).attr("x"));
	             	var ystr = parseInt($(this).attr("y")) + 1;
	             	$("input[type=text][x="+xstr+"][y="+ystr+"]").focus();
	             }
	         });
	         
	         
	         $("input[type=text][typ=numb]").blur(function(){
	         	var valtmp = $(this).val();
	         	if(valtmp && valtmp!= "" && !isNumber(valtmp))
	         	{
	         		layer.msg('所填项必是数字哦 :(');
	         		$(this).val("");
	         		return;
	         	}
	         	var xstr = $(this).attr("ywx");
	         	var ystr = $(this).attr("ywy");
	         	
	         	formulaStart(xstr,ystr);
	         });
	         
	         
		});
		
		function formulaStart(xstr,ystr)
		{
			//进行公式触发
         	if(allformula && allformula.size > 0)
         	{
	         	fdqgsArr = new Map();//非当前公式,因为也可能会有关联,也要最后计算一下
	         	
	         	if(xstr && ystr)
	         	{
	         		allformula.forEach(function(value,key){
		　　　　　　　　		//console.log(value,key);
						var gsval = value;
						if(value)
						{
							if(value.indexOf(xstr+","+ystr) >= 0)
							{
								var fval = calcFormula(value);
								$(key[0]).val(fval);
							}else{
								fdqgsArr.set(key,value);
							}
						}
		　　　　　　　　});
	         	}
	         	
	         	//进行非自身公式外的其他公式触发-开始
	         	if(fdqgsArr && fdqgsArr.size > 0)
	         	{
	         		fdqgsArr.forEach(function(value,key){
						var gsval = value;
						if(value)
						{
							var fval = calcFormula(value);
							$(key[0]).val(fval);
						}
		　　　　　　　　});
	         	}
	         	//进行非自身公式外的其他公式触发-结束
         	}
		}
		
		//根据公式进行值计算，返回计算值
		function calcFormula(value)
		{
			var gsval = value;
			var arr = value.match(/[0-9]*[,][0-9|\u4e00-\u9fa5]*/g);
			var retNum;
			if(arr && arr.length > 0)
			{
				var fltlen = 0;//小数点保留位数
				for(var i = 0;i < arr.length; i++)
				{
					var arrtmp = arr[i].split(",");
					var inptVal = $("input[ywx="+arrtmp[0]+"][ywy="+arrtmp[1]+"]").val();
					if(inptVal && isNumber(inptVal))
					{
						var tmpfltarr = inptVal.split(".");
						if(tmpfltarr && tmpfltarr.length > 1)
						{
							if(fltlen < tmpfltarr[1].length)
								fltlen = tmpfltarr[1].length;
						}
					}else{
						inptVal = "val";
					}
					gsval = gsval.replace((arrtmp[0]+","+arrtmp[1]),inptVal);
				}
				try{
					
					gsval = gsval.replaceAll("val+","");
					gsval = gsval.replaceAll("+val","");
					
					gsval = gsval.replaceAll("val-","");
					gsval = gsval.replaceAll("-val","");
					gsval = gsval.replaceAll("val","");
					
					
					if(gsval != ""){
						var retval = eval(gsval).toFixed(fltlen)/1;
							retNum = retval;
					}
					
				}catch(err)
				{
					retNum = "";
					layer.msg("第 "+$(key).attr('ywx')+" 行第 "+$(key).attr('ywy')+" 列公式计算出错，请进行核实!");
				}
			}
			return retNum;
		}
		//判断是否为数字函数
		function isNumber(value) {         //验证是否为数字
		    var patrn = /^(-)?\d+(\.\d+)?$/;
		    if (patrn.exec(value) == null || value == "") {
		        return false
		    } else {
		        return true
		    }
		}
		
		// toFixed兼容方法
		Number.prototype.toFixed = function(len){
		    if(len>20 || len<0){
		        throw new RangeError('toFixed() digits argument must be between 0 and 20');
		    }
		    // .123转为0.123
		    var number = Number(this);
		    if (isNaN(number) || number >= Math.pow(10, 21)) {
		        return number.toString();
		    }
		    if (typeof (len) == 'undefined' || len == 0) {
		        return (Math.round(number)).toString();
		    }
		    var result = number.toString(),
		        numberArr = result.split('.');
		    if(numberArr.length<2){
		        //整数的情况
		        return padNum(result);
		    }
		    var intNum = numberArr[0], //整数部分
		        deciNum = numberArr[1],//小数部分
		        lastNum = deciNum.substr(len, 1);//最后一个数字
		    
		    if(deciNum.length == len){
		        //需要截取的长度等于当前长度
		        return result;
		    }
		    if(deciNum.length < len){
		        //需要截取的长度大于当前长度 1.3.toFixed(2)
		        return padNum(result)
		    }
		    //需要截取的长度小于当前长度，需要判断最后一位数字
		    result = intNum + '.' + deciNum.substr(0, len);
		    if(parseInt(lastNum, 10)>=5){
		        //最后一位数字大于5，要进位
		        var times = Math.pow(10, len); //需要放大的倍数
		        var changedInt = Number(result.replace('.',''));//截取后转为整数
		        changedInt++;//整数进位
		        changedInt /= times;//整数转为小数，注：有可能还是整数
		        result = padNum(changedInt+'');
		    }
		    return result;
		    //对数字末尾加0
		    function padNum(num){
		        var dotPos = num.indexOf('.');
		        if(dotPos === -1){
		            //整数的情况
		            num += '.';
		            for(var i = 0;i<len;i++){
		                num += '0';
		            }
		            return num;
		        } else {
		            //小数的情况
		            var need = len - (num.length - dotPos - 1);
		            for(var j = 0;j<need;j++){
		                num += '0';
		            }
		            return num;
		        }
		    }
		}
		
		function impFill(reststr)
		{
			//所有输入单元格置空
			$("input[type=text]").each(function(){
	        	$(this).val("");
	        });
	         
			var restobj = JSON.parse(reststr);
			for (var key in restobj) 
			{
			    var keytmparr = key.split(",");
			    $("input[x="+keytmparr[0]+"][y="+keytmparr[1]+"]").val(restobj[key]);
			}
			
			$("input[typ=numb]").each(function(){
				var xstr = $(this).attr("ywx");
         		var ystr = $(this).attr("ywy");
         		formulaStart(xstr,ystr);
			});
		}
	</script>
		
</@crudmetropage3>