<#include '/macro/crud-metro-page3.ftl' >

<@crudmetropage3>
	<form id="query_form" action="${base}/ywmk/jsskflqk.action" method="post">
	
		<!--顶部查询条件，根据实际情况选是上下，还是左右布局-->
		<div class="row-fluid cpquery">
				<div class="span1">
					<label class="control-label">
						数据年份:
					</label>
					<br/>
					<input readonly id="filter_EQS_sjnf" name="filter_EQS_sjnf" class="span12 m-wrap" value="${Parameters['filter_EQS_sjnf']?if_exists}" type="text" onclick="WdatePicker({el:'filter_EQS_sjnf',dateFmt:'yyyy',readOnly:true})">
				</div>
			  	<div class="span2">
			  		<label class="control-label">
						教工号:
					</label>
					<input type="text" class="m-wrap span12" id="filter_LIKES_jgh" name="filter_LIKES_jgh"  value="${Parameters['filter_LIKES_jgh']?if_exists}" maxlength="50"/>
			  	</div>
			  	<div class="span2">
			  		<label class="control-label">
						姓名:
					</label>
					<input type="text" class="m-wrap span12" id="filter_LIKES_xm" name="filter_LIKES_xm"  value="${Parameters['filter_LIKES_xm']?if_exists}" maxlength="50"/>
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
									  		${obj.jgh!}
									  	</td>
									  	<td>
									  		${obj.xm!}
									  	</td>
									  	<td>
											<#if obj.sfsk == '1'>
												是
											<#else>
												否
											</#if>
									  	</td>
									  	<td>
											${obj.sklb?if_exists.name!}
									  	</td>
								  		<td>
								  			<#if obj.sfszk?exists >
												<#if obj.sfszk == '1'>
													是
												</#if>
												<#if obj.sfszk == '0'>
													否
												</#if>
											</#if>
									  	</td>
										 <td>
											<center>
									 			<a class="oprt-a" href="javascript:;" onclick="window.open('${base}/ywmk/jsskflqk!input.action?id=${obj.id}')"> 修改</a>
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
											window.open('${base}/ywmk/jsskflqk!input.action');
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
											content: '${base}/ywmk/jsskflqk!tmimp.action',
											//closeBtn :0,//去掉右上角关闭按钮参数
											area: ['80%', '80%'],
											maxmin: true
										});
									}
									$('#query_form').pfwpage({
										page_button:[
											{b_name:'新增',b_function:add,bclass:'btn ',bicon:'icon-pencil'},
											{b_name:'导入',b_function:leadingExcel,bclass:'btn ',bicon:'icon-stackexchange'},
											{b_name:'删除',opttype:'delete',bclass:'btn redbtn',bicon:'icon-trash',b_url:'${base}/ywmk/jsskflqk!delete.action',confirmStr:'是否确认删除？',paramName:'checks',selectNum:'^[0-9]*[1-9][0-9]*$'}
										],

										page_col:
										[
											 //在这里编写结果集显示栏目名称，例：{col_name:'xxxx'};最后一列注意不能有","号
												{col_name:'数据年份'},
												{col_name:'教工号'},
											  	{col_name:'姓名'},
											  	{col_name:'是否授课'},
											  	{col_name:'授课类别'},
											  	{col_name:'是否思政课'},
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