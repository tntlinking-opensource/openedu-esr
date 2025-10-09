<#include '/macro/crud-metro-page3.ftl' >

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
		.yxg
		{
			background-color: #a3ffbb;
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
		.select2-results{
		
			display:none;
		
		}
	</style>
	<h3 class="form-section">比对配置规则
		<span>请选择</span>
		<span style="background-color: #fe8282;">
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		</span>
		<span>
			内的单元格
		</span>
	</h3>
	
	<form id="query_form" action="${base}/ywmk/xxxx.action" method="post">
		<!--顶部查询条件，根据实际情况选是上下，还是左右布局-->
	
		<div class="row-fluid">
			<div class="span12 cpquery">
				<div class="row-fluid">
					
					<!--查询结果-->
					<div class="span12">
						<div class="portlet">
						 
							<div class="portlet-body">
								<div>
									
									<div class="row-fluid cpquery">
										
									  	<div class="span2">
									  		<label class="control-label">
												表号:
											</label>
											<br/>
											${entity.reportcode!}
									  	</div>
									  	
									  	<div class="span2">
									  		<label class="control-label">
												报表年份:
											</label>
											<br/>
											${entity.bbnf!}
									  	</div>
									  	<div class="span2">
									  		<label class="control-label">
												版本号:
											</label>
											<br/>
											${entity.versionh!}
									  	</div>
										<div class="span6">
									  		<label class="control-label">
												选择比对报表:
											</label>
											<br/>
											<select name="baob" id="baob" class="m-wrap span6" onchange="changTab()">
												<option value="">==请选择报表==</option>
												<#if reqlist?exists>
													<#list reqlist as obj>
														<option value="${obj.id!}"  >${obj.reportname!}-(${obj.versionh!})</option>
													</#list>
												</#if>
											</select>
									  	</div>
								  	</div> 
									<div id="tabhtml1" style="width:48%;    display: inline-block;">
										${entity.reptTable!}
									</div>		
									&nbsp;&nbsp;&nbsp;&nbsp;
									
									<!--顶部查询条件，根据实际情况选是上下，还是左右布局-->
									   
									<div id="tabhtml"  style="width:48%;    display: inline-block;">
									</div>  		
										
								</div>
								<br/>
								<div id="yxgzTab">
									
									<table id="result_page_table" class="table">
										<tbody></tbody>
									</table>
								
								</div>
								<div style="    text-align: center;">
									<a class="btn green big" href="javascript:;" onclick="baoc()" class="span12"><i class="icon-ok"> </i>保存</a>
								</div>		
								<script type="text/javascript">
									var bdgzpzMap = new Map();
									
									//Map 转 JSON，
									function   _strMapToObj(strMap){
								        let obj= Object.create(null);
								        for (let[k,v] of strMap) {
								          obj[k] = v;
								        }
								        return obj;
							      	}
							      	
							      	//JSON 转 Map
							      	function _objToStrMap(obj){
								      let strMap = new Map();
								      for (let k of Object.keys(obj)) {
								        strMap.set(k,obj[k]);
								      }
								      return strMap;
								    }
							      	
									
									function baoc(){
										
										var leftSize = $("#tabhtml").find(".tdclickon").length;
										var rightSize =  $("#tabhtml1").find(".tdclickon").length;
										
										if(rightSize == 0 || leftSize == 0 ){
											layer.msg("至少选中一个单元格！")
											return false;
										}
										
										if(leftSize === rightSize){
											var leftcheck = $("#tabhtml").find(".tdclickon");
											var rightcheck = $("#tabhtml1").find(".tdclickon");
											
											var xystr = "";
											
											for(var i=0;i<leftcheck.length;i++){
												var leftX = leftcheck.eq(i).attr("x");
												var leftY = leftcheck.eq(i).attr("y");
												
												var rightX = rightcheck.eq(i).attr("x");
												var rightY = rightcheck.eq(i).attr("y");
												
												xystr += leftX + "," + leftY + "-" + rightX + "," + rightY + "$";
											}
											 									
											xystr = xystr.substring(0,xystr.length - 1);
											var selSelectId = $("#baob").val();
											var val = bdgzpzMap.get(selSelectId);
											if(val != null && val != "" && typeof(val) != "undefined"){
												val +="#"+xystr;
												bdgzpzMap.set(selSelectId,val); 
											}else{
												bdgzpzMap.set(selSelectId,xystr); 
											}
											
											var jsonstr = _strMapToObj(bdgzpzMap);
											jsonstr = JSON.stringify(jsonstr);
											var url = '${base}/ywmk/report_info!insertgzpz.action';
											var bbid = "${entity.id!}";
											$.post(url,{checkstr:jsonstr,bbid:bbid},function(res){
												if(res != "" && res ){
													layer.msg("保存成功！")
													initTab();
												}else{
													layer.msg("保存失败！")
												}
											})
											
										}else{
											layer.msg("左右选中数量不一致！")
										}
									}
									
									function initTab(){
										
										$("td").removeClass("yxg");  
										$("td").removeClass("tdclickon");  
										$("td").css("background",""); 
									
										var bbid = $("#baob").val();
										
										var huixx = bdgzpzMap.get(bbid);	
										if(huixx != null && huixx != "" &&  typeof(huixx) != "undefined"){
										
											var tmpArr =  huixx.split("#");
											tmpArr.forEach((tmpval)=>{
												var xyarr =  tmpval.split("$");
												xyarr.forEach((ress)=>{
													var resArr =  ress.split("-"); 
													var leftxy =  resArr[0].split(","); 
													var rightxy =  resArr[1].split(","); 
											    	
													var leftiptid =  "td_"+	leftxy[0] + "_" + leftxy[1]										    	
											    	var rightiptid =  "td_"+	rightxy[0] + "_" + rightxy[1]
										    		$("#tabhtml").find("td[id="+leftiptid+"]").addClass("yxg");
										    		$("#tabhtml1").find("td[id="+rightiptid+"]").addClass("yxg");
											    })
											})
											
										}
										
										
										$("#result_page_table").find("tbody").html("");	
										if(bbid != null && bbid != ""){
																			
											if(huixx != null && huixx != ""){
												
												var xyarr =  huixx.split("#");
												var html ="";
												xyarr.forEach((ress)=>{
													html += '<tr titl='+ress+'>';
													html+='<td>'+ress+'</td>';
													html+='<td style="width:5%">';
													html+='	<a class="oprt-a" href="javascript:;" onclick="delgz(this)"> 删除</a>';
													html+='</td>';
													html+='</tr>';
											    })
											    $("#result_page_table").find("tbody").html(html);	
											}
										}
										
										
										$("#tabhtml").find("td").removeClass("tdclickon");  
										$("#tabhtml1").find("td").removeClass("tdclickon");  
									}
									//公式删除js
									function delgz(obj){
										
										if(confirm("确定删除吗？一旦删除不可恢复！")){
											var gs = $(obj).parent().parent().attr("titl");
											
											var bbid = $("#baob").val();
											
											var huixx = bdgzpzMap.get(bbid);	
											debugger
											huixx = huixx.replace(gs+"#","");
											huixx = huixx.replace("#"+gs,"");
											huixx = huixx.replace(gs,"");
											bdgzpzMap.set(bbid,huixx);
											
											var jsonstr = _strMapToObj(bdgzpzMap);
											jsonstr = JSON.stringify(jsonstr);
											var url = '${base}/ywmk/report_info!insertgzpz.action';
											var bbid = "${entity.id!}";
											$.post(url,{checkstr:jsonstr,bbid:bbid},function(res){
												if(res != "" && res ){
													layer.msg("删除成功！")
													initTab();
												}else{
													layer.msg("删除失败！")
												}
											})
										}
									}
									
									$("#result_page_table tbody").on('click', 'tr',function(){
										$("#tabhtml").find("td").css("background","");
										$("#tabhtml").find("td").removeClass("tdclickon");  
										$("#tabhtml1").find("td").css("background","");
										$("#tabhtml1").find("td").removeClass("tdclickon");  
										
									    if($(this).hasClass("page_tr_selected"))
										{
											$(this).removeClass("page_tr_selected");
										}else{
											$(this).addClass("page_tr_selected");
										}
										
										$(".page_tr_selected").each(function(){
											
											var gzstr = $(this).attr("titl");
											
											var xyarr =  gzstr.split("$");
												
											xyarr.forEach((ress)=>{
												var resArr =  ress.split("-"); 
												var leftxy =  resArr[0].split(","); 
												var rightxy =  resArr[1].split(","); 
										    	
												var leftiptid =  "td_"+	leftxy[0] + "_" + leftxy[1]										    	
										    	var rightiptid =  "td_"+	rightxy[0] + "_" + rightxy[1]
									    		$("#tabhtml").find("td[id="+leftiptid+"]").css("background","#e0f190");
									    		$("#tabhtml1").find("td[id="+rightiptid+"]").css("background","#e0f190");
										    })
										})
										
									});
									
									function changTab(){
										var baobid = $("#baob").val();
										var url = '${base}/ywmk/report_info!getTabByid.action';
										$.get(url,{rptid:baobid},function(res){
											if(res != ""){
												$("#tabhtml").html(res);
												$("#tabhtml").find("td[class!=noopttd]").each(function(){
													var typ = $(this).attr("typ");
													if(typ && typ != "" && typ != "unwirte"){
														$(this).html("");
													}
													$(this).on("click", function(e){ tdclick(this); });
												})
												oldinitcheckbox();
												initTab();
											}
										})
									}
									 
									
									$("td[id^=td_]").click(function(){
										if($(this).hasClass("yxg") == true){
											layer.msg("该单元格已经被占用！")
											return false;
										}
										var typ = $(this).attr("typ");
										
										if(typ && typ != "" && typ != "unwirte"){
											if($(this).hasClass("tdclickon"))
											{
												$(this).removeClass("tdclickon");
											}else{
												$(this).addClass("tdclickon");
											}
										}
									});
									function tdclick(obj){ 
										if($(obj).hasClass("yxg") == true){
											layer.msg("该单元格已经被占用！")
											return false;
										}
										var typ = $(obj).attr("typ");
										if(typ && typ != "" && typ != "unwirte" && $(obj).hasClass("yxg") == false){
											if($(obj).hasClass("tdclickon"))
											{
												$(obj).removeClass("tdclickon");
											}else{
												$(obj).addClass("tdclickon");
											}
										}
									};
									
								 	$(document).ready(function() {
								 		$("#tabhtml1").find("td[class!=noopttd]").each(function(){
											var typ = $(this).attr("typ");
											if(typ && typ != "" && typ != "unwirte"){
												$(this).html("");
											}
										})
										nowinitcheckbox()
										<#if entity.bdgzpz?exists && entity.bdgzpz != ''>
										
											var jsonstr = JSON.parse('${entity.bdgzpz!}');
											bdgzpzMap = _objToStrMap(jsonstr) 
										</#if>
								 	})   		
								 	
								 	
								 	
									function nowc_chk(colNum)
									{
										var ifchk = $("#tabhtml1").find("#c_chk_"+colNum).is(':checked');
										$("#tabhtml1").find("td[y="+colNum+"]").removeClass("tdclickon");
										
										if(ifchk)
										{
											$("#tabhtml1").find("td[y="+colNum+"]").each(function(){
												var typ = $(this).attr("typ");
												if(typ && typ != "" && typ != "unwirte" && $(this).hasClass("yxg") == false){
													$(this).addClass("tdclickon");
												}
											})
										}
									}
									
									function nowr_chk(rowNum)
									{
										var ifchk = $("#tabhtml1").find("#r_chk_"+rowNum).is(':checked');
										$("#tabhtml1").find("td[x="+rowNum+"]").removeClass("tdclickon");
										if(ifchk  )
										{
											$("#tabhtml1").find("td[x="+rowNum+"]").each(function(){
												var typ = $(this).attr("typ");
												if(typ && typ != "" && typ != "unwirte" && $(this).hasClass("yxg") == false){
													$(this).addClass("tdclickon");
												}
											})
										}
									}
									 
									function nowall_chk()
									{
										var ifchk = $("#tabhtml1").find("#all_chkbx").is(':checked');
										$("#tabhtml1").find("td[class!=noopttd]").removeClass("tdclickon");
										if(ifchk )
										{
											$("#tabhtml1").find("td[class!=noopttd]").each(function(){
												var typ = $(this).attr("typ");
												if(typ && typ != "" && typ != "unwirte" && $(this).hasClass("yxg") == false){
													$(this).addClass("tdclickon");
												}
											})
										}			
									}
									
								 	
								 	function nowinitcheckbox(){
								 		var test = $("#tabhtml1").find("input[type=checkbox]")
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
															newchkx = $($(tmpchkx).prop("outerHTML")).click(function(e){ nowall_chk(); });
														}
														
														if(dCheckboxid.indexOf("c_chk") >= 0)
														{
															newchkx = $($(tmpchkx).prop("outerHTML")).click(function(e){ 
																var eidarry = dCheckboxid.split("_"); 
																nowc_chk(eidarry[eidarry.length-1]); 
															});
														}
														if(dCheckboxid.indexOf("r_chk") >= 0)
														{
															newchkx = $($(tmpchkx).prop("outerHTML")).click(function(e){ 
																var eidarry = dCheckboxid.split("_"); 
																nowr_chk(eidarry[eidarry.length-1]); 
															});
														}
													}
											    	
											    	$(this).parents(".checker").parent().append(newchkx);
											        $(tmpchkxparent).remove();
											        
											    }
											});
										}
								 	}
								 	
								 	
								 	
								 	
								 	function oldinitcheckbox(){
								 		var test = $("#tabhtml").find("input[type=checkbox]")
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
															newchkx = $($(tmpchkx).prop("outerHTML")).click(function(e){ oldall_chk(); });
														}
														
														if(dCheckboxid.indexOf("c_chk") >= 0)
														{
															newchkx = $($(tmpchkx).prop("outerHTML")).click(function(e){ 
																var eidarry = dCheckboxid.split("_"); 
																oldc_chk(eidarry[eidarry.length-1]); 
															});
														}
														if(dCheckboxid.indexOf("r_chk") >= 0)
														{
															newchkx = $($(tmpchkx).prop("outerHTML")).click(function(e){ 
																var eidarry = dCheckboxid.split("_"); 
																oldr_chk(eidarry[eidarry.length-1]); 
															});
														}
													}
											    	
											    	$(this).parents(".checker").parent().append(newchkx);
											        $(tmpchkxparent).remove();
											        
											    }
											});
										}
								 	}
								 	
								 	function oldc_chk(colNum)
									{
										var ifchk = $("#tabhtml").find("#c_chk_"+colNum).is(':checked');
										$("#tabhtml").find("td[y="+colNum+"]").removeClass("tdclickon");
										
										if(ifchk)
										{
											$("#tabhtml").find("td[y="+colNum+"]").each(function(){
												var typ = $(this).attr("typ");
												if(typ && typ != "" && typ != "unwirte"  && $(this).hasClass("yxg") == false){
													$(this).addClass("tdclickon");
												}
											})
										}
									}
									
									function oldr_chk(rowNum)
									{
										var ifchk = $("#tabhtml").find("#r_chk_"+rowNum).is(':checked');
										$("#tabhtml").find("td[x="+rowNum+"]").removeClass("tdclickon");
										if(ifchk  )
										{
											$("#tabhtml").find("td[x="+rowNum+"]").each(function(){
												var typ = $(this).attr("typ");
												if(typ && typ != "" && typ != "unwirte"  && $(this).hasClass("yxg") == false){
													$(this).addClass("tdclickon");
												}
											})
										}
									}
									 
									function oldall_chk()
									{
										var ifchk = $("#tabhtml").find("#all_chkbx").is(':checked');
										$("#tabhtml").find("td[class!=noopttd]").removeClass("tdclickon");
										if(ifchk )
										{
											$("#tabhtml").find("td[class!=noopttd]").each(function(){
												var typ = $(this).attr("typ");
												if(typ && typ != "" && typ != "unwirte"  && $(this).hasClass("yxg") == false){
													$(this).addClass("tdclickon");
												}
											})
										}			
									}
								 	
								 	$('#query_form').pfwpage({
										page_button:[
										],
										page_col:
										[
											{col_name:'已配置规则'},
										  	{col_name:'操作'},
										],
										page_table:'#result_page_table',
										page_table_search:'#result_page_table',
										page_pageNo:${page?if_exists.pageNo!},
										page_pageSize:${page?if_exists.pageSize!},
										page_orderBy:'${page?if_exists.orderBy!}',
										page_order:'${page?if_exists.order!}',
										page_totalPages:${page?if_exists.totalPages!},
										page_totalCount:${page?if_exists.totalCount!}
									});
								 	
								 	$(document).ready(function() {
								 		$("#fyfoot").remove();
						 			})
								</script>								
															
							</div>
						</div>
					</div>
					<!--查询结果-->
				
				</div>
			</div>
		</div>
		
	</form>	
</@crudmetropage3>