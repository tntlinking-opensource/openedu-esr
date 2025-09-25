<#include '/macro/crud-metro-page3.ftl' >

<@crudmetropage3>
	<form id="query_form" action="${base}/ywmk/jzgxx.action" method="post" xmlns="http://www.w3.org/1999/html">
	
		<!--顶部查询条件，根据实际情况选是上下，还是左右布局-->
		<div class="row-fluid cpquery">
			  	<div class="span2">
					<label class="control-label">
						数据年份:
					</label>
					<br/>
					<input readonly id="filter_EQS_sjnf" name="filter_EQS_sjnf" class="span12 m-wrap" value="${Parameters['filter_EQS_sjnf']?if_exists}" type="text" onclick="WdatePicker({el:'filter_EQS_sjnf',dateFmt:'yyyy',readOnly:true})">
			  	</div>
			  	<div class="span2">
			  		<label class="control-label">
						姓名:
					</label>
					<input type="text" class="m-wrap span12" id="filter_LIKES_xm" name="filter_LIKES_xm"  value="${Parameters['filter_LIKES_xm']?if_exists}" maxlength="50"/>
			  	</div>
			  	<div class="span2">
			  		<label class="control-label">
						性别:
					</label>
					<select name="filter_EQS_xb" id="filter_EQS_xb" class="m-wrap span12">
						<option value="">==全部==</option>
						<option value="1" <#if Parameters['filter_EQS_xb']?exists && Parameters['filter_EQS_xb'] == '1'>selected</#if> >男</option>
						<option value="0" <#if Parameters['filter_EQS_xb']?exists && Parameters['filter_EQS_xb'] == '0'>selected</#if> >女</option>
					</select>
			  	</div>
			  	<div class="span2">
			  		<label class="control-label">
						民族:
					</label>
					<select name="filter_EQS_mz.id" id="filter_EQS_mz.id" class="m-wrap span12">
						<option value="">==全部==</option>
						<#if minzList?exists>
							<#list minzList as obj>
								<option value="${obj.id!}" <#if Parameters['filter_EQS_mz.id']?exists && Parameters['filter_EQS_mz.id'] == obj.id>selected</#if> >${obj.name!}</option>
							</#list>
						</#if>
					</select>
			  	</div>
			  	<div class="span2">
			  		<label class="control-label">
						政治面貌:
					</label>
					<select name="filter_EQS_zzmm.id" id="filter_EQS_zzmm.id" class="m-wrap span12">
						<option value="">==全部==</option>
						<#if zzmmList?exists>
							<#list zzmmList as obj>
								<option value="${obj.id!}" <#if Parameters['filter_EQS_zzmm.id']?exists && Parameters['filter_EQS_zzmm.id'] == obj.id>selected</#if> >${obj.name!}</option>
							</#list>
						</#if>
					</select>
			  	</div>
			  	<div class="span2">
			  		<label class="control-label">
						学历:
					</label>
					</br>
					<select name="filter_EQS_xuel.id" id="filter_EQS_xuel.id" class="m-wrap span10">
						<option value="">==全部==</option>
						<#if xuelList?exists>
							<#list xuelList as obj>
								<option value="${obj.id!}"  <#if Parameters['filter_EQS_xuel.id']?exists && Parameters['filter_EQS_xuel.id'] == obj.id>selected</#if> >${obj.name!}</option>
							</#list>
						</#if>
					</select>
			  	</div>
		    </div>
			<div class="row-fluid cpquery">
			  	<div class="span2">
			  		<label class="control-label">
						是否在编:
					</label>
					<select name="filter_EQS_sfzb" id="filter_EQS_sfzb" class="m-wrap span12">
						<option value="">==全部==</option>
						<option value="1" <#if Parameters['filter_EQS_sfzb']?exists && Parameters['filter_EQS_sfzb'] == '1'>selected</#if> >是</option>
						<option value="0" <#if Parameters['filter_EQS_sfzb']?exists && Parameters['filter_EQS_sfzb'] == '0'>selected</#if> >否</option>
					</select>
			  	</div>
				<div class="span2">
					<label class="control-label">
						教工号:
					</label>
					<input type="text" class="m-wrap span12" id="filter_LIKES_jgh" name="filter_LIKES_jgh"  value="${Parameters['filter_LIKES_jgh']?if_exists}" maxlength="50"/>
				</div>
			<div class="span2" style="text-align:right">
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
											${obj.sjnf!}
										</td>
										<td>
											${obj.ssxq?if_exists.xqmc!}
										</td>
									  	<td>
									  		${obj.jgh!}
									  	</td>
									  	<td>
									  		${obj.xm!}
									  	</td>
									  	<td>
											<#if obj.xb?exists>
												<#if obj.xb =='1'>
													男
												</#if>
												<#if obj.xb =='0'>
													女
												</#if>
											</#if>
									  	</td>
									  	<td>
									  		${obj.zzmm?if_exists.name!}
									  	</td>
										<td>
											<#if obj.sfzb?exists>
												<#if obj.sfzb =='1'>
													是
												</#if>
												<#if obj.sfzb =='0'>
													否
												</#if>
											</#if>
										</td>
										<td>
											${obj.rylb?if_exists.name!}
										</td>
									  	<td>
									  		${obj.zhij?if_exists.name!}
									  	</td>
										<td>
											${obj.xuel?if_exists.name!}
										</td>
										 <td>
											<center>
									 			<a class="oprt-a" href="javascript:;" onclick="window.open('${base}/ywmk/jzgxx!input.action?id=${obj.id}')"> 修改</a>

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
								
									function add()
									{
											window.open('${base}/ywmk/jzgxx!input.action');
									}
									
									function showReport_old(ywsjid,lcbh){
										if(ywsjid == "" || lcbh == "")
										{
											alert("错误：参数为空！");
											return;
										}
									    var url = "${base}/flow/t_flow_sjdqzt!shInfoUtil.action?id="+ywsjid+"&lcbh="+lcbh;
									    $("#pfwmodal").modal({backdrop: 'static', keyboard: false,remote:url});
									}
									function showReport(ywsjid,lcbh){
										if(ywsjid == "" || lcbh == "")
										{
											alert("错误：参数为空！");
											return;
										}
									    var url = "${base}/flow/t_flow_sjdqzt!shInfoUtil.action?id="+ywsjid+"&lcbh="+lcbh;
								     	//layerCommon(url);
									    //$("#pfwmodal").modal({backdrop: 'static', keyboard: false,remote:url});
									     parentlayerFull(url,' ');
									}
									function leadingExcel() {
										//var zxid =	$("#idstr").val();
										parent.layer.open({
											type: 2,
											title: ' ',
											content: '${base}/ywmk/jzgxx!tmimp.action',
											//closeBtn :0,//去掉右上角关闭按钮参数
											area: ['80%', '80%'],
											maxmin: true
										});
									}
									$('#query_form').pfwpage({
										page_button:[
											{b_name:'新增',b_function:add,bclass:'btn ',bicon:'icon-pencil'},
											{b_name:'导入',b_function:leadingExcel,bclass:'btn ',bicon:'icon-stackexchange'},
											{b_name:'删除',opttype:'delete',bclass:'btn redbtn',bicon:'icon-trash',b_url:'${base}/ywmk/jzgxx!delete.action',confirmStr:'是否确认删除？',paramName:'checks',selectNum:'^[0-9]*[1-9][0-9]*$'}
										],

										page_col:
										[
											 //在这里编写结果集显示栏目名称，例：{col_name:'xxxx'};最后一列注意不能有","号
												{col_name:'数据年份'},
												{col_name:'所属校区'},
												{col_name:'教工号',width:'10%'},
											  	{col_name:'姓名',width:'10%'},
											  	{col_name:'性别'},
											  	{col_name:'政治面貌'},
												{col_name:'是否在编'},
											  	{col_name:'人员类别'},
											  	{col_name:'职级'},
											  	{col_name:'学历'},
											 	{col_name:''}
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