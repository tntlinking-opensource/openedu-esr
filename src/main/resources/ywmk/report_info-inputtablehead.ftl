<#include '/macro/crud-metro-page3-blank.ftl' >

<#if id?exists>
	<#assign extPosition='表头配置'/>
<#else>
	<#assign extPosition='表头配置'/>
</#if>

<@crudmetropage3>
	
	<#if entity?exists>
		<!--顶部查询条件，根据实际情况选是上下，还是左右布局-->
		<div class="row-fluid cpquery" >
		
			<div class="span3"></div>
		  	<div class="span2">
		  		<label class="control-label">
					<b>表号：</b> 
					<span style="color:#35b5f3bf">
						<b>${entity?if_exists.reportcode!}</b>
					</span>
				</label>
		  	</div>
		  	<div class="span3">
		  		<label class="control-label">
					<b>报表名称：</b> 
					<span style="color:#35b5f3bf">
						<b>${entity?if_exists.reportname!}</b>
					</span>
				</label>
		  	</div>
		  	<div class="span2">
		  		<label class="control-label">
					<b>版本号：</b>
					<span style="color:#35b5f3bf">
						<b>${entity?if_exists.versionh!}</b>
					</span>
				</label>
		  	</div>
		  	<div class="span2"></div>
		  	
		</div>
		
		<div class="row-fluid cpmidrow">
			<center>
				&nbsp;&nbsp;
			</center>
		</div>
		<!--顶部查询条件，根据实际情况选是上下，还是左右布局-->	
		
		<style>
			.reptTableHead
			{
				width: 100%;
			}
			.reptTableHead tr td 
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
		</style>
		
		<div class="row-fluid">
			<div class="span12" style="position: relative;">
				<div class="form-actions">
					<center>
						<button class="btn blue" type="button" onclick="setText()">设置文本</button>
						&nbsp;&nbsp;&nbsp;
						<button class="btn " type="button" onclick="mergeRow()">合并(行)</button>
						&nbsp;&nbsp;&nbsp;
						<button class="btn " type="button" onclick="mergeCol()">合并(列)</button>
						&nbsp;&nbsp;&nbsp;
						<button class="btn " type="button" onclick="aligLeft()">对齐(左)</button>
						&nbsp;&nbsp;&nbsp;
						<button class="btn " type="button" onclick="aligCenter()">对齐(中)</button>
						&nbsp;&nbsp;&nbsp;
						<button class="btn " type="button" onclick="aligRight()">对齐(右)</button>
						&nbsp;&nbsp;&nbsp;
						<button class="btn " type="button" onclick="createTable()">表格重置</button>
						&nbsp;&nbsp;&nbsp;
						<button class="btn " type="button" onclick="initCell()">单元格重置</button>
					</center>	
				</div>
	
			</div>
		</div>
		
		<div id="createDiv">
			
		</div>
		
		
	<#else>
		<center>
			<span style="font-size:20px">错误：不是有效报表</span>
		</center>
	</#if>
	
	<!--配置说明-->
	<div class="row-fluid">
		<div class="span12">
			&nbsp;
		</div>
	</div>
	
	<!--设置文本录入区-->
	<div style="display: none;" class="modal hide fade in" id="pfwmodal1" style="display: block; margin-top: -155px;" >
		<div class="modal-body">
		    <h3 class="form-section"><b>设置单元格文本</b></h3>
			<textarea rows="7" cols="40" id="shezwb" style="width:95%"></textarea>
			<div>
				<p><b>填充规则：</b>逗号,制表符 + 换行符</p>
			</div>
		</div>
		<div class="modal-footer">
	        <a href="javascript:;" class="btn" onclick="modal1cancle()">取消</a>
			<a href="javascript:;" class="btn blue" onclick="fillText()" style="margin-left:20px">确定</a>
	    </div>
	</div>
	
	<br/><br/><br/><br/>
	
	<div style="position: fixed;bottom: 40px;width: 97%;text-align: center;">
		<button class="btn big blue" type="button" onclick="save()">保存</button>
	</div>	
					
					
	<script type="text/javascript" src="${base}/mainface/layer/layer.js"></script>
	<script type="text/javascript" src="${base}/mainface/js/jquery.contextify.js"></script>
		
	<script type="text/javascript">	
		var allSelTd;
		var currentTd;
		var allRow = parseInt("7"); 
		var allCol = parseInt("2"); 
		
		var options = {items:[
		  {text: '用户填写', onclick: function() {  }},
		  {text: '不可填写', onclick: function() {  }},
		  {text: '设置公式', onclick: function() {  }},
		  {text: '取消选中', onclick: function() {  }},
		  {divider: true},
		  {text: '设置基准单元格', onclick: function() { }}
		]};
		function createTable(){
			$("#createDiv").html("");
			var _table = $('<table id="reptTableHead" class="reptTableHead" border="0" />');
			if(allRow > 0 && allCol > 0)
			{
				//第一行，用于选择/全选等
				var _tr = $("<tr />");
				for(var i=0;i<=allCol;i++)
				{
					var _td = $('<td  class="noopttd" id="noopttd_'+i+'">');
					var tmpstr = "&nbsp;&nbsp;";
					if(i != 0)
					{
						var _input = $('<input type="checkbox" name="c_chk_'+i+'" id="c_chk_'+i+'" />')
							.click(function(e){ 
								var eid = $(e.currentTarget).attr("id");
								var eidarry = eid.split("_"); 
								c_chk(eidarry[eidarry.length-1]); 
							});
							
						_td.prepend('c'+i+'&nbsp;&nbsp;')
						_td.append(_input);
					}else{
						$(_input).click(function(e){ all_chk(); });
						var _input = $('<input type="checkbox" name="all_chkbx" id="all_chkbx" />').click(function(e){all_chk(); });
						_td.append(_input);
					}
					_tr.append(_td);
				}
				_table.append(_tr);
				
				for(var i=1;i<=allRow;i++)
				{
					var _tr = $("<tr />");
					for(var j=0;j<=allCol;j++)
					{
						if(j != 0)
						{
							var _td = $('<td x="'+i+'" y="'+j+'" id="td_'+i+'_'+j+'" />').click(function(e){
								currentTd = $(e.currentTarget);
								if($(e.currentTarget).hasClass("tdclickon"))
								{
									$(e.currentTarget).removeClass("tdclickon");
								}else{
									$(e.currentTarget).addClass("tdclickon");
								}
							});
				
							_tr.append(_td);
						}else{
							var _td = $('<td class="noopttd" id="noopttd_'+j+'">r' +i+ '&nbsp;&nbsp;</td>').click(function(e){
								currentTd = $(e.currentTarget);
								if($(e.currentTarget).hasClass("tdclickon"))
								{
									$(e.currentTarget).removeClass("tdclickon");
								}else{
									$(e.currentTarget).addClass("tdclickon");
								}
							});
							var _input = $('<input type="checkbox" name="r_chk_'+i+'" id="r_chk_'+i+'" />').click(function(e){
								var eid = $(e.currentTarget).attr("id");
								var eidarry = eid.split("_"); 
								r_chk(eidarry[eidarry.length-1]); 
							});
							_td.append(_input);
							_tr.append(_td);
						}
					}	
					_table.append(_tr);			
				}
				
			}
			$("#createDiv").append(_table);
			var test = $("#createDiv").find("input[type=checkbox]");
			if (test.size() > 0) {
			    test.each(function () {
				    if ($(this).parents(".checker").size() == 0) {
				        $(this).show();
				        $(this).uniform();
				    }
				});
			}
			
		}
		
		$(document).ready(function() {
			<#if entity?exists && entity.reptTableHead?exists && entity.reptTableHead != "">
				var tmptabl = $('${entity.reptTableHead!}');
				$("#createDiv").html("");
				$("#createDiv").append(tmptabl);
				
				
				//每个单元格点击选中
				$("#createDiv").find("td[id^=td_]").click(function(){
					currentTd = $(this);
					if($(this).hasClass("tdclickon"))
					{
						$(this).removeClass("tdclickon");
					}else{
						$(this).addClass("tdclickon");
					}
				});
				
				//解决bootstrap会把checkbox进行init的问题，删除再动态添加一下，不然无法进行点击
				var test = $("#createDiv").find("input[type=checkbox]");
				if (test.size() > 0) {
				    test.each(function () {
					    if ($(this).parents(".checker").size() == 1) {
					    	var tmpchkx = $(this);
					    	var tmpchkxparent = $(this).parents(".checker");
					    	
					    	var dCheckboxid = $(tmpchkx).attr("id");
					    	var newchkx;
					    	if(dCheckboxid)
							{
								
								if(dCheckboxid == "all_chkbx")
								{
									newchkx = $($(tmpchkx).prop("outerHTML")).click(function(e){ all_chk(); });
								}
								
								if(dCheckboxid.indexOf("c_chk") >= 0)
								{
									newchkx = $($(tmpchkx).prop("outerHTML")).click(function(e){ 
										var eidarry = dCheckboxid.split("_"); 
										c_chk(eidarry[eidarry.length-1]); 
									});
								}
								if(dCheckboxid.indexOf("r_chk") >= 0)
								{
									newchkx = $($(tmpchkx).prop("outerHTML")).click(function(e){ 
										var eidarry = dCheckboxid.split("_"); 
										r_chk(eidarry[eidarry.length-1]); 
									});
								}
							}
					    	
					    	$(this).parents(".checker").parent().append(newchkx);
					        $(tmpchkxparent).remove();
					        
					    }
					});
				}
				
			<#else>
				createTable();
			</#if>
			
			
			$("#save_inputForm").validate({
				rules: {
					<!-- 在这里编写验证规则 -->						
				}
			});	
			/*
			//每个单元格点击选中
			$("td[id^=td_]").click(function(){
				currentTd = $(this);
				if($(this).hasClass("tdclickon"))
				{
					$(this).removeClass("tdclickon");
				}else{
					$(this).addClass("tdclickon");
				}
			});
			
			rightClickMouse("td[id^=td_]",function(t){
		        //$(t).removeClass("tdclickon");
		        currentTd = $(t);
		    });
		    
		    //每个单元格有一个右击菜单
		    //$("td[id^=td_]").contextify(options);
		    */
						
		});	
		
		//jquery鼠标右击事件函数
		function rightClickMouse (obj, callback) {
		　　//禁止浏览器默认事件
		　　$(document).delegate(obj,'contextmenu', function (e) {
		　　　　e.preventDefault();
		　　});
		　　//给选择器obj绑定右键事件
		　　$(document).delegate(obj,'mousedown', function (e) {
		　　　　var $t = $(this);
		　　　　if (e.which == 3) {
		　　　　　　if (typeof callback == 'function') {
		　　　　　　　　//右键执行回调函数
		　　　　　　　　callback($t);
		　　　　　　}
		　　　　}
		　　});
		}
		
		function c_chk(colNum)
		{
			var ifchk = $("#c_chk_"+colNum).is(':checked');
			$("td[y="+colNum+"]").removeClass("tdclickon");
			if(ifchk)
			{
				$("td[y="+colNum+"]").addClass("tdclickon");
			}
		}
		function r_chk(rowNum)
		{
			var ifchk = $("#r_chk_"+rowNum).is(':checked');
			$("td[x="+rowNum+"]").removeClass("tdclickon");
			if(ifchk)
			{
				$("td[x="+rowNum+"]").addClass("tdclickon");
			}
		}
		function all_chk()
		{
			var ifchk = $("#all_chkbx").is(':checked');
			$("td[class!=noopttd]").removeClass("tdclickon");
			if(ifchk)
			{
				$("td[class!=noopttd]").addClass("tdclickon");
			}			
		}
		
		function fillText()
		{
			var txt = $("#shezwb").val();
			if(txt && txt != "")
			{
				if(allSelTd && allSelTd.length > 0)
				{
					var txtArray = txt.split("\n");
					if(txtArray && txtArray.length > 0)
					{
						for (var i = 0; i < allSelTd.length; i++)
						{
							var trarr = allSelTd[i];
							var txtarr = txtArray[i];
							if(txtarr)
							{
								// 这里将每一行继续按逗号、制表符拆分：split(/[符号1、符号2]/)
								var pairs  = txtarr.split(/[,\t]/);
								for (var j = 0; j < trarr.length; j++)
								{
									if(pairs[j])
									{
										$(trarr[j]).html(pairs[j].trim());
									}
								}
							}
						}
					}
				}
				
				modal1cancle();
				
			}else
			{
				layer.msg('未输入文本信息');
			}
		}
		
		//设置为文本显示	
		function setText()
		{
			allSelTd = new Array();
			var trArry = $("#reptTableHead").find("tr");
			for(var i = 0;i < trArry.length; i++)
			{
				var tdSelArryTmp = $(trArry[i]).find(".tdclickon");
				if(tdSelArryTmp && tdSelArryTmp.length > 0)
				{
					allSelTd.push(tdSelArryTmp);
				}
			}
			if(allSelTd && allSelTd.length > 0)
			{
				batchText();
			}else{
				layer.msg('未选择单元格');
			}
		}
		
		function caclformulastr(oldformula,increaH,increaC)
		{
			var newformula = oldformula;
			var arr = newformula.match(/[0-9]*[,][0-9]*/g);
			if(arr && arr.length > 0)
			{
				for(var i = 0;i < arr.length; i++)
				{
					var arrtmp = arr[i].split(",");
					var _h = arrtmp[0];//业务坐标-行 以01,02,03出现
					var _l = arrtmp[1];//业务坐标-列 以1,2,3出现
					if(increaH)
					{
						var new_h = parseInt(_h)+1;
						if(String(new_h).length == 1)
							_h = '0'+String(new_h);
						else
							_h = String(new_h);
					}
					if(increaC)
					{
						var new_l = Number(_l)+1;
						_l = String(new_l);
					}
					newformula = newformula.replace(arr[i],_h+","+_l);
				}
			}
			return newformula;
		}
		function showformula(tdid){
			var gstdRules = $("#"+tdid).attr("rules");
			layer.alert(gstdRules, {icon: 6});
		}
		//单元格重置
		function initCell()
		{
			var selTd = $(".tdclickon");
			if(selTd && selTd.length > 0)
			{
				$(".tdclickon").each(function(e){
					var tmpobj = $(this);
					$(this).removeAttr("ywx");
					$(this).removeAttr("ywy");
					$(this).removeAttr("rules");
					$(this).removeAttr("typ");
					$(this).html("");
				});
			}else{
				layer.msg('未选择单元格');
			}
		}
		
		function batchText()
		{
			$('#shezwb').val("");
	   		$("#pfwmodal1").modal({backdrop: 'static', keyboard: false});
		}
		function modal1cancle()
		{
			$('#pfwmodal1').modal('hide'); 
		}
		function modal2cancle()
		{
			$('#pfwmodal2').modal('hide'); 
		}
		function init(){
			$("td[class!=noopttd]").removeClass("tdclickon"); //全部取消选择
			$("td[class!=noopttd]").html(""); //文本置为空
			$(".jzcellcss").removeAttr("ifjzcell");//清空基准单元格
	  		$(".jzcellcss").removeClass("jzcellcss");//清空基准单元格
	  		$("td[typ='numb']").html("");
	  		$("td[typ='numb']").remove
	  		currentTd = null;
		}
		
		function save()
		{
			var form1 = $("<form>");  
			form1.attr('style','display:none');  
			form1.attr('target','_self');
			form1.attr('method','post');  
			form1.attr('action',"${base}/ywmk/report_info!saveconfig2.action?id=${id!}"); 
			
			$("td[id^=td_]").removeClass("tdclickon");
			
			var tbleHtml = $("#reptTableHead").prop("outerHTML");
			var ck = ("<input type='hidden' value='"+tbleHtml+"' name='tabblestr'>");  
			form1.append(ck);
			$("body").append(form1);
			form1.submit(); 
			form1.remove(); 
			return true;
		}
		
		function mergeRow(){
			var selTd = $(".tdclickon");
			if(selTd && selTd.length > 1)
			{
				for(var i = 0; i < selTd.length; i++)
				{
					if(i == 0)
					{
						$(selTd[i]).attr("colspan",selTd.length);
					}else{
						$(selTd[i]).remove();
					}
				}
			}else{
				layer.msg('未选择单元格');
			}
		}
		
		function mergeCol(){
			var selTd = $(".tdclickon");
			if(selTd && selTd.length > 1)
			{
				for(var i = 0; i < selTd.length; i++)
				{
					if(i == 0)
					{
						$(selTd[i]).attr("rowspan",selTd.length);
					}else{
						$(selTd[i]).remove();
					}
				}
			}else{
				layer.msg('未选择单元格');
			}
		}
		
		function aligLeft()
		{
			var selTd = $(".tdclickon");
			if(selTd && selTd.length > 0)
			{
				for(var i = 0; i < selTd.length; i++)
				{
					$(selTd[i]).css("text-align","left");
				}
			}else{
				layer.msg('未选择单元格');
			}
		}
		
		function aligCenter()
		{
			var selTd = $(".tdclickon");
			if(selTd && selTd.length > 0)
			{
				for(var i = 0; i < selTd.length; i++)
				{
					$(selTd[i]).css("text-align","center");
				}
			}else{
				layer.msg('未选择单元格');
			}
		}
		
		function aligRight()
		{
			var selTd = $(".tdclickon");
			if(selTd && selTd.length > 0)
			{
				for(var i = 0; i < selTd.length; i++)
				{
					$(selTd[i]).css("text-align","right");
				}
			}else{
				layer.msg('未选择单元格');
			}
		}
		
	</script>
			
		
</@crudmetropage3>