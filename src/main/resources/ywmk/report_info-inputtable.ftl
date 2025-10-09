<#include '/macro/crud-metro-page3-blank.ftl' >

<#if id?exists>
	<#assign extPosition='报表配置'/>
<#else>
	<#assign extPosition='报表配置'/>
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
		</style>
		
		<div class="row-fluid">
			<div class="span12" style="position: relative;">
				<div class="form-actions">
					<center>
						<button class="btn blue" type="button" onclick="setText()">设置文本</button>
						&nbsp;&nbsp;&nbsp;
						<button class="btn blue" type="button" onclick="setNumb()">设置数字</button>
						&nbsp;&nbsp;&nbsp;
						<button class="btn blue" type="button" onclick="setTextstr()">设置填文本</button>
						&nbsp;&nbsp;&nbsp;
						<button class="btn blue" type="button" onclick="setSelect()">设置下拉</button>
						&nbsp;&nbsp;&nbsp;
						<button class="btn blue" type="button" onclick="setUnwrite()">设置不可填</button>
						&nbsp;&nbsp;&nbsp;
						<button class="btn blue" type="button" onclick="setFormula()">设置公式</button>
						&nbsp;&nbsp;&nbsp;
						<button class="btn blue" type="button" onclick="setBaseCell()">设置基准单元格</button>
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
						&nbsp;&nbsp;&nbsp;
						<button class="btn " type="button" onclick="setStyle()">填充空格</button>
						&nbsp;&nbsp;&nbsp;
						<button class="btn " type="button" onclick="setStyleno()">清空空格</button>
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
			<div class="form-actions">
				<p><b>设置文本按钮：</b>批量设置表格文本信息。<span style="color:blue">操作：</span>全选->从word中复制填报表格->点击“设置文本按钮”->确定</p>
				<p><b>设置基准单元格：</b>可批量将基准单元格坐标(x+1,y+1)后的单元格设置为“填/数字”。<span style="color:blue">操作：</span>选择一个单元格（一般为代码列的第2行），进行业务坐标（业务坐标为有意义的坐标，是后面进行数据检验的基础）设置</p>					
			</div>

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
	
	<!--设置样式录入区-->
	<div style="display: none;" class="modal hide fade in" id="pfwmodalys" style="display: block; margin-top: -155px;" >
		<div class="modal-body">
		    <h3 class="form-section"><b>填充空格数量</b></h3>
		    	<input type="number" min="1" max="999" step="1" id="shezys" name="shezys"  />
			<div>
				<p><b>填充规则：</b>1：即为1个&nbsp</p>
			</div>
		</div>
		<div class="modal-footer">
	        <a href="javascript:;" class="btn" onclick="modalyscancle()">取消</a>
			<a href="javascript:;" class="btn blue" onclick="fillStyle()" style="margin-left:20px">确定</a>
	    </div>
	</div>
	
	<!--设置公式录入区-->
	<div style="display: none;" class="modal hide fade in" id="pfwmodal2" style="display: block; margin-top: -155px;" >
		<div class="modal-body">
		    <h3 class="form-section"><b>设置汇总公式</b></h3>
			<textarea rows="7" cols="40" id="formulastr" style="width:95%"></textarea>
			<p>
				公式格式：业务坐标x,业务坐标y，如：03,2+03,3+03,4+03,5+03,6
			</p>
			<div id="ifMutTdDiv" style="display:none">
				<b>公式中递增：</b><input type="checkbox" name="increateH" id="increateH">行递增&nbsp;&nbsp;
						 <input type="checkbox" name="increateC" id="increateC">列递增
			</div>
		</div>
		
		<div class="modal-footer">
	        <a href="javascript:;" class="btn" onclick="modal2cancle()">取消</a>
			<a href="javascript:;" class="btn blue" onclick="formulaConfirm()" style="margin-left:20px">确定</a>
	    </div>
	</div>
	<!--设置下拉录入区-->
	<div style="display: none;" class="modal hide fade in" id="pfwmodal3" style="display: block; margin-top: -155px;" >
		<div class="modal-body">
		    <h3 class="form-section"><b>设置下拉数据</b></h3>
			<textarea rows="7" cols="40" id="setxlsj" style="width:95%"></textarea>
			<p>
				下拉数据格式：key1#val1$key2#val2
			</p>
		</div>
		<div class="modal-footer">
	        <a href="javascript:;" class="btn" onclick="modal3cancle()">取消</a>
			<a href="javascript:;" class="btn blue" onclick="selectConfirm()" style="margin-left:20px">确定</a>
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
		var allRow = parseInt("${entity.rowNumber!}"); 
		var allCol = parseInt("${entity.columnNumber!}"); 
		
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
			var _table = $('<table id="reptTable" class="reptTable" border="1" />');
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
			<#if entity?exists && entity.reptTable?exists && entity.reptTable != "">
				var tmptabl = $('${entity.reptTable}');
				$("#createDiv").html("");
				$("#createDiv").append(tmptabl);
				
				
				/**
				var lastRow = $(".reptTable tr td:last").attr('x');
				var lastCol = $(".reptTable tr td:last").attr('y');
				
				var xchs = Number(allRow) - Number(lastRow);
				var xcls = Number(allCol) - Number(lastCol);
				
				if(xchs > 0){//相差行数大于0 则在最后一行进行add
					
				}
				**/
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
		
		function del_html_tags(str)
		{
		    var words = '';
		    words = str.replace(/<[^>]+>/g,"");
		    return words;
		}
		
		function fillStyle(){
			
			var txt = $("#shezys").val();
			
			var nbspstr="";
			for(var i=0;i<txt;i++){
				nbspstr+="&nbsp;";
			
			}
		 
			if(txt && txt != "")
			{
				$(".tdclickon").each(function(e){
					var str = del_html_tags($(this).html().replace(/&nbsp;/g,''));
					$(this).html(nbspstr+str);
				});
				modalyscancle();
			}else
			{
				layer.msg('未输入数量');
			}
		}
		function setStyleno(){
			var curtTd = $(".tdclickon");
				if(curtTd && curtTd.length > 0)
				{
					$(".tdclickon").each(function(e){
						var str = del_html_tags($(this).html().replace(/&nbsp;/g,''));
						$(this).html(str);
					});
				}else{
				layer.msg('未选择单元格');
			}
		}
		
		//设置为文本显示	
		function setText()
		{
			allSelTd = new Array();
			var trArry = $("#reptTable").find("tr");
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
		
		//设置style	
		function setStyle()
		{
			var curtTd = $(".tdclickon");
			if(curtTd && curtTd.length > 0)
			{
				batchStyle();
			}else{
				layer.msg('未选择单元格');
			}
		}
		
		//设置为数字
		function setNumb()
		{
			var selTd = $(".tdclickon");
			if(selTd && selTd.length > 0)
			{
				var jzCell = $(".jzcellcss");
				var currX;
				var currY;
				if(jzCell)
				{
				}else{
					layer.msg('未指定基准单元格,默认以单元格的值为“乙”的单元格作为业务坐标');
					$("#reptTable").find("td").each(function(e){
						var tmpHtmlstr = $(this).html().trim();
						if(tmpHtmlstr == "乙")
						{
							jzCell = $(this);
						}
					});
				}
				
				initCell();
				if(jzCell)
				{
					currX = parseInt(jzCell.attr("x"));
			  		currY = parseInt(jzCell.attr("Y"));
				}else{
					currX = -1;
					currY = -1;
				}
			
				$(".tdclickon").each(function(e){
					
					var rowtmp = parseInt($(this).attr("x"));
					var coltmp = parseInt($(this).attr("y"));
					
					//默认为用户填数字(type为numb)
					$(this).attr("typ","numb");
					$(this).html("填/数字");
	  				
	  				//设置业务坐标
	  				var ywYstr = $("td[x='"+currX+"'][y='"+coltmp+"']").html().trim();
	  				var ywXstr = $("td[x='"+rowtmp+"'][y='"+currY+"']").html().trim();
	  				$(this).attr("ywX",ywXstr);
	  				$(this).attr("ywY",ywYstr);
	  				
				});
				
				
			}else{
				layer.msg('未选择单元格');
			}
			
			
		}
		//设置为文本域
		function setTextstr()
		{
			var selTd = $(".tdclickon");
			if(selTd && selTd.length > 0)
			{
				var jzCell = $(".jzcellcss");
				var currX;
				var currY;
				if(jzCell)
				{
				}else{
					layer.msg('未指定基准单元格,默认以单元格的值为“乙”的单元格作为业务坐标');
					$("#reptTable").find("td").each(function(e){
						var tmpHtmlstr = $(this).html().trim();
						if(tmpHtmlstr == "乙")
						{
							jzCell = $(this);
						}
					});
				}
				
				initCell();
				if(jzCell)
				{
					currX = parseInt(jzCell.attr("x"));
			  		currY = parseInt(jzCell.attr("Y"));
				}else{
					currX = -1;
					currY = -1;
				}
			
				$(".tdclickon").each(function(e){
					
					var rowtmp = parseInt($(this).attr("x"));
					var coltmp = parseInt($(this).attr("y"));
					
					//默认为用户填数字(type为numb)
					$(this).attr("typ","textstr");
					$(this).html("填/文本");
	  				
	  				//设置业务坐标
	  				var ywYstr = $("td[x='"+currX+"'][y='"+coltmp+"']").html().trim();
	  				var ywXstr = $("td[x='"+rowtmp+"'][y='"+currY+"']").html().trim();
	  				$(this).attr("ywX",ywXstr);
	  				$(this).attr("ywY",ywYstr);
	  				
				});
				
				
			}else{
				layer.msg('未选择单元格');
			}
			
			
		}
		//设置为不可填
		function setUnwrite()
		{
			initCell();
			$(".tdclickon").each(function(e){
				$(this).attr("typ","unwirte");
				$(this).html("/");
			})
		}
		//设置公式no.1	
		var ifMutTd = false;
		function setFormula()
		{
			ifMutTd = false;
			$("#ifMutTdDiv").hide();
			
			var curtTd = $(".tdclickon");
			if(curtTd && curtTd.length > 0)
			{
				if(curtTd.length > 1)
				{
					ifMutTd = true;
					$("#ifMutTdDiv").show();
				}
				var oldGs = $(curtTd[0]).attr("rules");
				if(oldGs)
					$('#formulastr').val(oldGs);
				else
					$('#formulastr').val("");
	   			$("#pfwmodal2").modal({backdrop: 'static', keyboard: false});
			}else
			{
				layer.msg('请选择一个单元格');
			}
		}
		//设置下拉no.1	
		function setSelect()
		{
			var curtTd = $(".tdclickon");
			if(curtTd && curtTd.length > 0)
			{
				var oldGs = $(curtTd[0]).attr("selectrules");
				if(oldGs)
					$('#setxlsj').val(oldGs);
				else
					$('#setxlsj').val("");
	   			$("#pfwmodal3").modal({backdrop: 'static', keyboard: false});
			}else
			{
				layer.msg('请选择一个单元格');
			}
		}
		//设置公式no.2
		function formulaConfirm()
		{
			var formulastr = $('#formulastr').val();
				
			var increaH = $("#increateH").is(':checked');
			var increaC = $("#increateC").is(':checked');
			
			if(formulastr && formulastr.trim() != "")
			{
				
				formulastr = formulastr.replace(/[\r\n]/g,"");
				
				var jzCell = $(".jzcellcss");
				var currX;
				var currY;
				if(jzCell)
				{
				}else{
					layer.msg('未指定基准单元格,默认以单元格的值为“乙”的单元格作为业务坐标');
					$("#reptTable").find("td").each(function(e){
						var tmpHtmlstr = $(this).html().trim();
						if(tmpHtmlstr == "乙")
						{
							jzCell = $(this);
						}
					});
				}
				
				if(jzCell)
				{
					currX = parseInt(jzCell.attr("x"));
			  		currY = parseInt(jzCell.attr("Y"));
				}else{
					currX = -1;
					currY = -1;
				}
				
				initCell();
				
				var flg = 0;
				$(".tdclickon").each(function(e){
					
					var rowtmp = parseInt($(this).attr("x"));
					var coltmp = parseInt($(this).attr("y"));
					
					//默认为用户填数字(type为numb)
					$(this).attr("typ","formula");
					if(flg == 0)
					{
						$(this).attr("rules",formulastr);
					}else{
						var newformulastr = caclformulastr(formulastr,increaH,increaC,flg);
						$(this).attr("rules",newformulastr);
					}
					
					$(this).html("<span style='color:blue'>汇总公式</span>");
	  				
	  				//设置业务坐标
	  				var ywYstr = $("td[x='"+currX+"'][y='"+coltmp+"']").html().trim();
	  				var ywXstr = $("td[x='"+rowtmp+"'][y='"+currY+"']").html().trim();
	  				$(this).attr("ywX",ywXstr);
	  				$(this).attr("ywY",ywYstr);
	  				
	  				flg = flg + 1;
				});
				
			}
			modal2cancle();
		}
		//设置下拉no.3
		function selectConfirm()
		{	
			var formulastr = $('#setxlsj').val();
			if(formulastr && formulastr.trim() != "")
			{
				formulastr = formulastr.replace(/[\r\n]/g,"");
				
				var jzCell = $(".jzcellcss");
				var currX;
				var currY;
				if(jzCell)
				{
				}else{
					layer.msg('未指定基准单元格,默认以单元格的值为“乙”的单元格作为业务坐标');
					$("#reptTable").find("td").each(function(e){
						var tmpHtmlstr = $(this).html().trim();
						if(tmpHtmlstr == "乙")
						{
							jzCell = $(this);
						}
					});
				}
				
				if(jzCell)
				{
					currX = parseInt(jzCell.attr("x"));
			  		currY = parseInt(jzCell.attr("Y"));
				}else{
					currX = -1;
					currY = -1;
				}
				
				initCell();
				$(".tdclickon").each(function(e){
					var rowtmp = parseInt($(this).attr("x"));
					var coltmp = parseInt($(this).attr("y"));
					
					$(this).html("下拉");
					//默认为用户填数字(type为numb)
					$(this).attr("typ","selectstr");
				 
					$(this).attr("selectRules",formulastr);
					$(this).html("下拉");
	  				//设置业务坐标
	  				var ywYstr = $("td[x='"+currX+"'][y='"+coltmp+"']").html().trim();
	  				var ywXstr = $("td[x='"+rowtmp+"'][y='"+currY+"']").html().trim();
	  				$(this).attr("ywX",ywXstr);
	  				$(this).attr("ywY",ywYstr);
	  				
				});
				
			}
			modal3cancle();
		}
		function caclformulastr(oldformula,increaH,increaC,flg)
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
						var new_h = parseInt(_h) + flg;
						if(String(new_h).length == 1)
							_h = '0'+String(new_h);
						else
							_h = String(new_h);
					}
					if(increaC)
					{
						var new_l = parseInt(_l) + flg;
						_l = String(new_l);
					}
					newformula = newformula.replace(arr[i], _h+","+_l);
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
					
					/*
					$.each(this.attributes, function(f) {
						console.log(this.name);
					    if(this.specified) {
					    	
					      	if(this.name == 'x' || this.name == 'y' || this.name == 'id')
						  	{
						  		
						  	}else{
						  		$(tmpobj).removeAttr(this.name);
						  	}
					    }
				    });
				    */
				    
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
		function batchStyle()
		{
			//$('#shezys').val("");
	   		$("#pfwmodalys").modal({backdrop: 'static', keyboard: false});
		}
		
		function modal1cancle()
		{
			$('#pfwmodal1').modal('hide'); 
		}
		
		function modal2cancle()
		{
			$('#pfwmodal2').modal('hide'); 
		}
		
		function modal3cancle()
		{
			$('#pfwmodal3').modal('hide'); 
		}
		
		function modalyscancle()
		{
			$('#pfwmodalys').modal('hide'); 
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
		function setBaseCell()
		{
			if(currentTd)
		  	{
		  		//只允许有一个基准单元格
		  		$(".jzcellcss").removeAttr("ifjzcell");
		  		$(".jzcellcss").removeClass("jzcellcss");
		  		$(currentTd).attr("ifjzcell","1");
		  		$(currentTd).removeClass("tdclickon");
		  		$(currentTd).addClass("jzcellcss");
		  		
		  		$("td[typ]").html("");
		  		$("td[typ]").removeAttr("typ");
		  		$("td[typ]").removeAttr("ywX"); //清除业务x
		  		$("td[typ]").removeAttr("ywY"); //清除业务y
		  		
		  		var currX = parseInt(currentTd.attr("x"));
		  		var currY = parseInt(currentTd.attr("Y"));
		  		
		  		for(var i = currX+1; i <= allRow; i++)
		  		{
		  			for(var j = currY+1; j <= allCol; j++)
		  			{
		  				//默认为用户填数字(type为numb)
		  				$("td[x='"+i+"'][y='"+j+"']").attr("typ","numb");
		  				$("td[x='"+i+"'][y='"+j+"']").html("填/数字");
		  				
		  				//设置业务坐标
		  				var ywXstr = $("td[x='"+i+"'][y='"+currY+"']").html().trim();
		  				var ywYstr = $("td[x='"+currX+"'][y='"+j+"']").html().trim();
		  				$("td[x='"+i+"'][y='"+j+"']").attr("ywX",ywXstr);
		  				$("td[x='"+i+"'][y='"+j+"']").attr("ywY",ywYstr);
		  				
		  			}
		  		}
		  		
		  	}else{
		  		layer.msg('未选择单元格');
		  	}
		}
		
		function save()
		{
		
			$("td[typ=numb]").each(function(){
				var xstr = $(this).attr("x");
				var ystr = $(this).attr("y");
				var ywxstr = $(this).attr("ywx");
				var ywystr = $(this).attr("ywy");
				var idstr  = $(this).attr("id");
				
				
				var xy=idstr.replace("td_","");
				xy="td_"+xy.replace("_","$");
				
				var ywhyxstr = $(this).parent().find("td").eq(1).html();
				ywhyxstr = del_html_tags(ywhyxstr.replace(/&nbsp;/g,''));
				
				ywhyystr = getvalByxy(xstr,ystr);
				
				var ywhyxy=ywhyxstr + "、" + ywhyystr;
				$(this).attr("ywhyxy",ywhyxy);
				
			});
			$("td[typ=formula]").each(function(){
				var xstr = $(this).attr("x");
				var ystr = $(this).attr("y");
				var ywxstr = $(this).attr("ywx");
				var ywystr = $(this).attr("ywy");
				var idstr  = $(this).attr("id");
				
				
				var xy=idstr.replace("td_","");
				xy="td_"+xy.replace("_","$");
				
				var ywhyxstr = $(this).parent().find("td").eq(1).html();
				ywhyxstr = del_html_tags(ywhyxstr.replace(/&nbsp;/g,''));
				
				ywhyystr = getvalByxy(xstr,ystr);
				
				var ywhyxy=ywhyxstr + "、" + ywhyystr;
				$(this).attr("ywhyxy",ywhyxy);
				
			});
			
			var form1 = $("<form>");  
			form1.attr('style','display:none');  
			form1.attr('target','_self');
			form1.attr('method','post');  
			form1.attr('action',"${base}/ywmk/report_info!saveconfig.action?id=${id!}"); 
			
			$("td[id^=td_]").removeClass("tdclickon");
			//$("td[id^=td_]").removeClass("jzcellcss");
			
			var tbleHtml = $("#reptTable").prop("outerHTML");
			var ck = ("<input type='hidden' value='"+tbleHtml+"' name='tabblestr'>");  
			form1.append(ck);
			$("body").append(form1);
			form1.submit(); 
			form1.remove(); 
			return true;
		}
		
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