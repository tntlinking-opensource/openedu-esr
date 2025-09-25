<#include '/macro/crud-metro-page3.ftl' >

<@crudmetropage3>
	<form id="query_form" action="${base}/ywmk/dwpxqk.action" method="post">
	
		<!--顶部查询条件，根据实际情况选是上下，还是左右布局-->
		<div class="row-fluid cpquery">
			  	<div class="span2">
			  		<label class="control-label">
						培训项目名称:
					</label>
					<input type="text" class="m-wrap span12" id="filter_LIKES_pxxmmc" name="filter_LIKES_pxxmmc"  value="${Parameters['filter_LIKES_pxxmmc']?if_exists}" maxlength="100"/>
			  	</div>
			  	<div class="span2">
			  		<label class="control-label">
						主要资金来源类型:
					</label>
					<select id="filter_EQS_zyzjly" name="filter_EQS_zyzjly" class="m-wrap span10">
						 <option value="">=全部=</option>
						 <option value="1"   <#if Parameters['filter_EQS_zyzjly']?exists && Parameters['filter_EQS_zyzjly']="1">selected</#if>>财政资金支付</option>
						 <option value="2"   <#if Parameters['filter_EQS_zyzjly']?exists && Parameters['filter_EQS_zyzjly']="2">selected</#if>>非财政资金支付</option>
						 <option value="3"   <#if Parameters['filter_EQS_zyzjly']?exists && Parameters['filter_EQS_zyzjly']="3">selected</#if>>免费公益项目</option>
					</select>
			  	</div>
			  	 
			  	<div class="span2">
			  		<label class="control-label">
						培训形式:
					</label>
					<input type="text" class="m-wrap span12" id="filter_LIKES_pxxs" name="filter_LIKES_pxxs"  value="${Parameters['filter_LIKES_pxxs']?if_exists}" maxlength="100"/>
			  	</div>
			  	<div class="span2">
			  		<label class="control-label">
						承担培训工作校内教师姓名:
					</label>
					<input type="text" class="m-wrap span12" id="filter_LIKES_xnjsxm" name="filter_LIKES_xnjsxm"  value="${Parameters['filter_LIKES_xnjsxm']?if_exists}" maxlength="20"/>
			  	</div>
			  	<div class="span2">
			  		<label class="control-label">
						承担培训工作外聘人员姓名:
					</label>
					<input type="text" class="m-wrap span12" id="filter_LIKES_wpry" name="filter_LIKES_wpry"  value="${Parameters['filter_LIKES_wpry']?if_exists}" maxlength="20"/>
			  	</div>
							 
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
									  		${obj.pxxmmc!}
									  	</td>
									  	<td>
									  		<#if obj.zyzjly?exists && obj.zyzjly == '1'>
									  			财政资金支付
									  		</#if>
									  		<#if obj.zyzjly?exists && obj.zyzjly == '2'>
									  			非财政资金支付
									  		</#if>
									  		<#if obj.zyzjly?exists && obj.zyzjly == '3'>
									  			免费公益项目
									  		</#if>
									  	<td>
									  		${obj.dzjf!}
									  	</td>
									  	<td>
									  		${obj.pxsj!}
									  	</td>
									  	<td>
									  		${obj.pxxs!}
									  	</td>
									  	 
									  	<td>
									  		${obj.xnjsxm!}
									  	</td>
									  	<td>
									  		${obj.wpry!}
									  	</td>
										 <td>
								 			<a class="oprt-a" href="javascript:;" onclick="window.open('${base}/ywmk/dwpxqk!input.action?id=${obj.id}')"> 修改</a>
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
											window.open('${base}/ywmk/dwpxqk!input.action');
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
									
									$('#query_form').pfwpage({
										page_button:[
											{b_name:'新增',b_function:add,bclass:'btn ',bicon:'icon-pencil'},
											{b_name:'导入',b_function:leadingExcel,bclass:'btn ',bicon:'icon-stackexchange'},
											{b_name:'删除',opttype:'delete',bclass:'btn redbtn',bicon:'icon-trash',b_url:'${base}/ywmk/dwpxqk!delete.action',confirmStr:'是否确认删除？',paramName:'checks',selectNum:'^[0-9]*[1-9][0-9]*$'}
										],
										page_col:
										[
											 //在这里编写结果集显示栏目名称，例：{col_name:'xxxx'};最后一列注意不能有","号
											  	{col_name:'培训项目名称'},
											  	{col_name:'主要资金来源类型'},
											  	{col_name:'到账经费（万元）'},
											  	{col_name:'培训时间（学时）'},
											  	{col_name:'培训形式'},
											  	
											  	{col_name:'承担培训工作校内教师姓名'},
											  	{col_name:'承担培训工作外聘人员姓名'},
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

										function leadingExcel() {
											parent.layer.open({
												type: 2,
												title: ' ',
												content: '${base}/ywmk/dwpxqk!tmimp.action',
												//closeBtn :0,//去掉右上角关闭按钮参数
												area: ['80%', '80%'],
												maxmin: true
											});
										}
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