<#include '/macro/crud-metro-page3.ftl' >

<@crudmetropage3>
	<form id="query_form" action="${base}/ywmk/bbqzpz.action" method="post">
	
		<!--顶部查询条件，根据实际情况选是上下，还是左右布局-->
		<div class="row-fluid cpquery">
			  	<div class="span2">
			  		<label class="control-label">
						取值类型
					</label>
					<select id="filter_EQS_qzlx" name="filter_EQS_qzlx" class="m-wrap span10">
						 <option value="">=全部=</option>
						 <option value="1"   <#if Parameters['filter_EQS_qzlx']?exists && Parameters['filter_EQS_qzlx']="1">selected</#if>>sql取值</option>
						 <option value="2"   <#if Parameters['filter_EQS_qzlx']?exists && Parameters['filter_EQS_qzlx']="2">selected</#if>>回调服务</option>
					</select>
			  	</div>
			  	<div class="span2">
			  		<label class="control-label">
						sql:
					</label>
					<input type="text" class="m-wrap span12" id="filter_LIKES_sqlpz" name="filter_LIKES_sqlpz"  value="${Parameters['filter_LIKES_sqlpz']?if_exists}" maxlength="5000"/>
			  	</div>
			  	<div class="span2">
			  		<label class="control-label">
						回调服务:
					</label>
					<input type="text" class="m-wrap span12" id="filter_LIKES_hdfw" name="filter_LIKES_hdfw"  value="${Parameters['filter_LIKES_hdfw']?if_exists}" maxlength="100"/>
			  	</div>
				<div class="span2">
			  		<label class="control-label">
						是否有效
					</label>
					<select id="filter_EQS_sfyx" name="filter_EQS_sfyx" class="m-wrap span10">
						 <option value="">=全部=</option>
						 <option value="1"   <#if Parameters['filter_EQS_sfyx']?exists && Parameters['filter_EQS_sfyx']="1">selected</#if>>是</option>
						 <option value="0"   <#if Parameters['filter_EQS_sfyx']?exists && Parameters['filter_EQS_sfyx']="0">selected</#if>>否</option>
					</select>
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
									  		${obj.bxlx?if_exists.name!}
									  	</td>
									  	<td>
									  		${obj.reportInfo?if_exists.reportcode!} - ${obj.reportInfo?if_exists.reportname!}
									  	</td>
									  	
									  	<td>
									  		<#if obj.qzlx?exists && obj.qzlx == '1'>
									  			sql取值
									  		</#if>
									  		<#if obj.qzlx?exists && obj.qzlx == '2'>
									  			回调服务
									  		</#if>
									  	</td>  
									  	<td>
									  		<#if obj.sfyx?exists && obj.sfyx == '1'>
									  			是
									  		</#if>
									  		<#if obj.sfyx?exists && obj.sfyx == '0'>
									  			否
									  		</#if>
									  	</td>
										 <td>
											<center>
									 			<a class="oprt-a" href="javascript:;" onclick="window.open('${base}/ywmk/bbqzpz!input.action?id=${obj.id}')"> 修改</a>
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
											window.open('${base}/ywmk/bbqzpz!input.action');
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
											{b_name:'删除',opttype:'delete',bclass:'btn redbtn',bicon:'icon-trash',b_url:'${base}/ywmk/bbqzpz!delete.action',confirmStr:'是否确认删除？',paramName:'checks',selectNum:'^[0-9]*[1-9][0-9]*$'}
										],
										page_col:
										[
											 //在这里编写结果集显示栏目名称，例：{col_name:'xxxx'};最后一列注意不能有","号
											  	{col_name:'办学类型'},
											  	{col_name:'报表名称'},
											  	{col_name:'取值类型'},
											  	{col_name:'是否有效'},
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