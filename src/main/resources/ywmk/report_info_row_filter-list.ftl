<#include '/macro/crud-metro-page3.ftl' >

<@crudmetropage3>
	<form id="query_form" action="${base}/ywmk/report_info_row_filter.action?bbid=${bbid!}" method="post">
	
		<!--顶部查询条件，根据实际情况选是上下，还是左右布局-->
		<div class="row-fluid cpquery">
		  	<div class="span2">
		  		<label class="control-label">
					<b>表号:</b>&nbsp;${rptinfo?if_exists.reportcode!}
				</label>
		  	</div>
		  	<div class="span2">
		  		<label class="control-label">
					<b>报表名称:</b>&nbsp;${rptinfo?if_exists.reportname!}
				</label>
		  	</div>
		  	<div class="span2">
		  		<label class="control-label">
					<b>类型:</b>&nbsp;
					<#if rptinfo?exists && rptinfo.reportlx?exists>
			  			<#if rptinfo.reportlx == '1'>
			  				年报
			  			</#if>
			  			<#if rptinfo.reportlx == '2'>
			  				季报
			  			</#if>
			  		</#if>
				</label>
		  	</div>
		  	<div class="span6">
		  		<label class="control-label">
					<b>适用学校:</b>&nbsp;${rptinfo?if_exists.syxxmcs!}
				</label>
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
									
								</div>
								<div class="actions" id="pagemenubutton" ></div>
							</div>
							<div class="portlet-body">
								<table id="result_page_table">
								<#list page.result as obj>
									<!-- 在这里编写结果列,使用新基础框架需要改成：<tr titl="${obj.id}"> -->
									<tr titl="${obj.id}">
										<td>
									  		${obj.ssbxlx?if_exists.name!}
									  	</td>
									  	<td>
									  		${obj.hanghs!}
									  	</td>
										<td>
											<center>
									 			<a class="oprt-a" href="javascript:;" onclick="window.open('${base}/ywmk/report_info_row_filter!input.action?bbid=${bbid!}&id=${obj.id}')"> 修改</a>
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
											window.open('${base}/ywmk/report_info_row_filter!input.action?bbid=${bbid!}');
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
									
									$('#query_form').pfwpage({
										page_button:[
											{b_name:'新增',b_function:add,bclass:'btn ',bicon:'icon-pencil'},
											{b_name:'删除',opttype:'delete',bclass:'btn redbtn',bicon:'icon-trash',b_url:'${base}/ywmk/report_info_row_filter!delete.action',confirmStr:'是否确认删除？',paramName:'checks',selectNum:'^[0-9]*[1-9][0-9]*$'}
										],
										page_col:
										[
											 //在这里编写结果集显示栏目名称，例：{col_name:'xxxx'};最后一列注意不能有","号
											  	{col_name:'办学类型',width:'20%'},
											  	{col_name:'行号',width:'60%'},
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