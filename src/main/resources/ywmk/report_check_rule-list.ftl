<#include '/macro/crud-metro-page3.ftl' >

<@crudmetropage3>
	<form id="query_form" action="${base}/ywmk/report_check_rule.action?fenl=${Parameters['fenl']?if_exists}" method="post">
	
		<!--顶部查询条件，根据实际情况选是上下，还是左右布局-->
		<div class="row-fluid cpquery">
			<div class="span2">
		  		<label class="control-label">
					涉及报表代码:
				</label>
				<input type="text" class="m-wrap span12" id="" name="filter_LIKES_jwtabs"  value="${Parameters['filter_LIKES_jwtabs']?if_exists}" maxlength="10"/>
		  	</div>
		  	<div class="span2">
		  		<label class="control-label">
					校验类型:
				</label>
				<select class="m-wrap span12" id="filter_EQS_jylx" name="filter_EQS_jylx">
					<option value="">==全部==</option>
					<option value="1" <#if Parameters['filter_EQS_jylx']?exists && Parameters['filter_EQS_jylx'] == '1'>selected</#if> >逻辑校验</option>
					<option value="2" <#if Parameters['filter_EQS_jylx']?exists && Parameters['filter_EQS_jylx'] == '2'>selected</#if> >经验校验</option>
				</select>
		  	</div>
		  	<div class="span2">
		  		<label class="control-label">
					校验规则:
				</label>
				<input type="text" class="m-wrap span12" id="" name="filter_LIKES_jygz"  value="${Parameters['filter_LIKES_jygz']?if_exists}" maxlength="30"/>
		  	</div>
		  	<div class="span2">
		  		<label class="control-label">
					校验规则含义:
				</label>
				<input type="text" class="m-wrap span12" id="" name="filter_LIKES_jygzhy"  value="${Parameters['filter_LIKES_jygzhy']?if_exists}" maxlength="30"/>
		  	</div>
		  	<div class="span2">
		  		<label class="control-label">
					是否有效:
				</label>
				<select class="m-wrap span12" id="filter_EQS_sfyx" name="filter_EQS_sfyx">
					<option value="">==全部==</option>
					<option value="1" <#if Parameters['filter_EQS_sfyx']?exists && Parameters['filter_EQS_sfyx'] == '1'>selected</#if> >是</option>
					<option value="0" <#if Parameters['filter_EQS_sfyx']?exists && Parameters['filter_EQS_sfyx'] == '0'>selected</#if> >否</option>
				</select>
		  	</div>
		  	<div class="span2">
		  		<label class="control-label">
					源属:
				</label>
				<br/>
				<select class="m-wrap span10" id="filter_EQS_fenl" name="filter_EQS_fenl">
					<option value="">==全部==</option>
					<option value="1" <#if Parameters['filter_EQS_fenl']?exists && Parameters['filter_EQS_fenl'] == '1'>selected</#if> >教育部</option>
					<option value="2" <#if Parameters['filter_EQS_fenl']?exists && Parameters['filter_EQS_fenl'] == '2'>selected</#if> >市教委</option>
					<option value="2" <#if Parameters['filter_EQS_fenl']?exists && Parameters['filter_EQS_fenl'] == '3'>selected</#if> >区教育局</option>
					<option value="2" <#if Parameters['filter_EQS_fenl']?exists && Parameters['filter_EQS_fenl'] == '4'>selected</#if> >其他</option>
				</select>
		  	</div>
		  	
			  
		</div>
		
		<div class="row-fluid cpquery">
			<div class="span2" style="text-align:left">
				<label class="control-label">
					<br>
				</label>
				<button class="btn green" type="submit" >查询 <i class="m-icon-swapdown m-icon-white"></i></button>
				&nbsp;&nbsp;&nbsp;
				<button class="btn" type="button" id="reset">重置 </button>
			</div>
		</div>
		
		<div class="row-fluid cpmidrow">
			<center>
				&nbsp;&nbsp;
			</center>
		</div>
		<!--顶部查询条件，根据实际情况选是上下，还是左右布局-->
	
		<div class="row-fluid">
			<div class="span12 cpquery">
				<div class="row-fluid">
					
					<!--查询结果-->
					<div class="span12">
						<div class="portlet">
							<div class="portlet-title">
								<div class="caption">
									查询结果
								</div>
								<div class="actions" id="pagemenubutton" ></div>
							</div>
							<div class="portlet-body">
								<table id="result_page_table">
								<#list page.result as obj>
									<!-- 在这里编写结果列,使用新基础框架需要改成：<tr titl="${obj.id}"> -->
									<tr titl="${obj.id}">
									  	<td>
									  		<#if obj.jylx?exists>
									  			<#if obj.jylx == '1'>
									  				逻辑校验
									  			</#if>
									  			<#if obj.jylx == '2'>
									  				经验校验
									  			</#if>
									  		</#if>
									  	</td>
									  	<td>
									  		${obj.jwtabs!}
									  	</td>
									  	<td>
									  		<#if obj.fenl?exists>
									  			<#if obj.fenl == '1'>
									  				教育部
									  			</#if>
									  			<#if obj.fenl == '2'>
									  				市教委
									  			</#if>
									  			<#if obj.fenl == '3'>
									  				区教育局
									  			</#if>
									  			<#if obj.fenl == '4'>
									  				其他
									  			</#if>
									  		</#if>
									  	</td>
									  	<td title='${obj.jygz!}'>
									  		<#if obj.jygz?length gt 80>
									  			${obj.jygz?substring(0,80)?replace('<', '&lt')!}
									  		<#else>
									  			${obj.jygz?replace('<', '&lt')!}
									  		</#if>
									  	</td>
									  	<td title='${obj.jygzhy!}'>
									  		<#if obj.jygzhy?length gt 80>
									  			${obj.jygzhy?substring(0,80)?replace('<', '&lt')!}
									  		<#else>
									  			${obj.jygzhy?replace('<', '&lt')!}
									  		</#if>
									  	</td>
									  	<td>
									  		<#if obj.sfyx == '1' >
									  			是
									  		<#else>
									  			否
									  		</#if>
									  	</td>
										 <td>
											<center>
									 			<a class="oprt-a" href="javascript:;" onclick="window.open('${base}/ywmk/report_check_rule!input.action?id=${obj.id}')"> 修改</a>
											</center>
										 </td>
									</tr>
								</#list>
								</table>		
								
								<!--根据实际需要重新定义对话框的样式-->
								<style>
									#pfwmodal{
										width: 800px;
										margin: 0 0 0 -370px; 
									}
								</style>								
	
								<script type="text/javascript">
									
									function opengs(idstr){
										
										var url = '${base}/ywmk/report_check_rule!chakgs.action?id='+idstr;
										
										parent.layer.open({
										  type: 2,
										  title: ' ',
										  content: url,
										  //closeBtn :0,//去掉右上角关闭按钮参数
										  area: ['50%', '50%'],
										  maxmin: true
										});
									
									}
									
									function add()
									{
										window.open('${base}/ywmk/report_check_rule!input.action');
									}
									
									function impRule()
									{
										window.open('${base}/ywmk/report_check_rule!impRuleftl.action');
									}
									
									function showReport(ywsjid,lcbh){
										if(ywsjid == "" || lcbh == "")
										{
											alert("错误：参数为空！");
											return;
										}
									    var url = "${base}/flow/t_flow_sjdqzt!shInfoUtil.action?id="+ywsjid+"&lcbh="+lcbh;
									    $("#pfwmodal").modal({backdrop: 'static', keyboard: false,remote:url});
									}
									
									function plszwx(){
										
										var idstr = "";
										
										$(".page_tr_selected").each(function(){
											
											idstr += $(this).attr("titl")+",";
										
										})
										
										if(idstr == ""){
											layer.msg("至少选择一条数据!")
										}else{
											if(confirm("确定设置为无效吗？")){
												var url = "${base}/ywmk/report_check_rule!setsfyx.action"
												$.post(url,{idstr:idstr,sfyx:0},function(res){
												
													layer.msg(res);
													$("#query_form").submit();
												
												})
											}
											
										
										}
									}
									function plszyx(){
										
										var idstr = "";
										
										$(".page_tr_selected").each(function(){
											
											idstr += $(this).attr("titl")+",";
										
										})
										
										if(idstr == ""){
											layer.msg("至少选择一条数据!")
										}else{
											if(confirm("确定设置为有效吗？")){
												var url = "${base}/ywmk/report_check_rule!setsfyx.action"
												$.post(url,{idstr:idstr,sfyx:1},function(res){
												
													layer.msg(res);
													$("#query_form").submit();
												
												})
											}
											
										
										}
									}
									
									$('#query_form').pfwpage({
										page_button:[
											{b_name:'新增',b_function:add,bclass:'btn ',bicon:'icon-pencil'},
											{b_name:'批量设置无效',b_function:plszwx,bclass:'btn ',bicon:''},
											{b_name:'批量设置有效',b_function:plszyx,bclass:'btn ',bicon:''},
											{b_name:'规则导入',b_function:impRule,bclass:'btn ',bicon:''},
											{b_name:'删除',opttype:'delete',bclass:'btn redbtn',bicon:'icon-trash',b_url:'${base}/ywmk/report_check_rule!delete.action',confirmStr:'是否确认删除？',paramName:'checks',selectNum:'^[0-9]*[1-9][0-9]*$'}
										],
										page_col:
										[
											 //在这里编写结果集显示栏目名称，例：{col_name:'xxxx'};最后一列注意不能有","号
											  	{col_name:'校验类型',width:'5%'},
											  	{col_name:'涉及报表代码',width:'5%'},
											  	{col_name:'源属',width:'5%'},
											  	{col_name:'校验规则',width:'50%'},
											  	{col_name:'校验规则含义',width:'30%'},
											  	{col_name:'是否有效',width:'2%'},
											 	{col_name:'',width:'1%'}	
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
										
									$("#reset").click(function(){
										$("input[name^='filter_']").val("");
										$("select[name^='filter_']").val("");
									});
									
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