<#include '/macro/crud-metro-page3.ftl' >

<@crudmetropage3>
	<form id="query_form" action="${base}/ywmk/bjxx.action" method="post">
	
		<!--顶部查询条件，根据实际情况选是上下，还是左右布局-->
		<div class="row-fluid cpquery">
			  	<div class="span2">
			  		<label class="control-label">
						班级代码:
					</label>
					<input type="text" class="m-wrap span12" id="filter_LIKES_bjdm" name="filter_LIKES_bjdm"  value="${Parameters['filter_LIKES_bjdm']?if_exists}" maxlength="50"/>
			  	</div>
			  	<div class="span2">
			  		<label class="control-label">
						班级名称:
					</label>
					<input type="text" class="m-wrap span12" id="filter_LIKES_bjmc" name="filter_LIKES_bjmc"  value="${Parameters['filter_LIKES_bjmc']?if_exists}" maxlength="50"/>
			  	</div>
			  	<div class="span2">
			  		<label class="control-label">
						年级:
					</label>
					<input type="text" class="m-wrap span12" id="filter_EQS_nianj" name="filter_EQS_nianj" readonly  value="${Parameters['filter_EQS_nianj']?if_exists}" onclick="WdatePicker({el:'filter_EQS_nianj',dateFmt:'yyyy',readOnly:true})" maxlength="10"/>
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
									  		${obj.bjdm!}
									  	</td>
									  	<td>
									  		${obj.bjmc!}
									  	</td>
									  	<td>
									  		${obj.nianj!}
									  	</td>
									  	<td>
									  		${obj.dqzt?if_exists.name!}
									  	</td>
										 <td>
											<center>
									 			<a class="oprt-a" href="javascript:;" onclick="window.open('${base}/ywmk/bjxx!input.action?id=${obj.id}')"> 修改</a>
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
											window.open('${base}/ywmk/bjxx!input.action');
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
										parent.layer.open({
											type: 2,
											title: ' ',
											content: '${base}/ywmk/bjxx!tmimp.action',
											//closeBtn :0,//去掉右上角关闭按钮参数
											area: ['80%', '80%'],
											maxmin: true
										});
									}
									$('#query_form').pfwpage({
									 
										page_button:[
											{b_name:'新增',b_function:add,bclass:'btn ',bicon:'icon-pencil'},
											{b_name:'导入',b_function:leadingExcel,bclass:'btn ',bicon:'icon-stackexchange'},
											{b_name:'删除',opttype:'delete',bclass:'btn redbtn',bicon:'icon-trash',b_url:'${base}/ywmk/bjxx!delete.action',confirmStr:'是否确认删除？',paramName:'checks',selectNum:'^[0-9]*[1-9][0-9]*$'}
										],

										page_col:
										[
											 //在这里编写结果集显示栏目名称，例：{col_name:'xxxx'};最后一列注意不能有","号
											  	{col_name:'班级代码'},
											  	{col_name:'班级名称'},
											  	{col_name:'年级'},
											  	{col_name:'当前状态'},
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