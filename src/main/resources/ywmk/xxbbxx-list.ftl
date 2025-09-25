<#include '/macro/crud-metro-page3.ftl' >

<@crudmetropage3>
	<form id="query_form" action="${base}/ywmk/xxbbxx.action" method="post">
		<input type="hidden"   id="sjrwid" name="sjrwid"  value="${sjrwid!}" maxlength="50"/>
			  	
		<!--顶部查询条件，根据实际情况选是上下，还是左右布局-->
		<div class="row-fluid cpquery">
		  	<div class="span2">
		  		<label class="control-label">
					表号:
				</label>
				<input type="text" class="m-wrap span12" id="filter_LIKES_reportcode" name="filter_LIKES_reportcode"  value="${Parameters['filter_LIKES_reportcode']?if_exists}" maxlength="50"/>
		  	</div>
		  	<div class="span2">
		  		<label class="control-label">
					表名:
				</label>
				<input type="text" class="m-wrap span12" id="filter_LIKES_reportname" name="filter_LIKES_reportname"  value="${Parameters['filter_LIKES_reportname']?if_exists}" maxlength="50"/>
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
									  		${obj.reportcode!}
									  	</td>
										<td>
									  		${obj.reportname!}
									  	</td>
									  
										 <td>
											<a class="oprt-a" href="javascript:;" onclick="ckmb('${obj.id}')"><i class="icon-view"></i>查看模板</a>
											<#if obj.rptTableRes?exists && obj.rptTableRes != "">
												&nbsp;&nbsp;&nbsp;
												<a class="oprt-a" href="javascript:;" onclick="vietab('${obj.id}')"><i class="icon-view"></i>预览</a>
												<!--<#--
												&nbsp;&nbsp;&nbsp;
												<a class="oprt-a" href="javascript:;" onclick="sdtzbb('${obj.id}')"><i class="icon-view"></i>手动调整报表</a>
												-->-->
											</#if>
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
											window.open('${base}/ywmk/xxbbxx!input.action');
									}
									function ysjdr()
									{
										var sjrwid = "${sjrwid!}";
										window.open('${base}/ywmk/xxxx!shujdr.action?sjrwid='+sjrwid);
									}
									
									function yjsc()
									{
										if(confirm("已生成数据将被覆盖，确定重新生成吗？")){
											$("#modalsubmit").modal({backdrop: 'static', keyboard: false});
											var sjrwid = "${sjrwid!}"
											$.post("${base}/ywmk/xxbbxx!yjsc.action", {sjrwid:sjrwid}, function(res) {
											    alert(res);
											     $("#query_form").submit();
											});
										}	
									}
									function ckmb(idstr){
																			
										parent.layer.open({
										  type: 2,
										  title: ' ',
										  content: "${base}/ywmk/xxbbxx!ckmb.action?id="+idstr,
										  //closeBtn :0,//去掉右上角关闭按钮参数
										  area: ['80%', '80%'],
										  maxmin: true
										});
									}
									function sdtzbb(idstr){
																			
										parent.layer.open({
										  type: 2,
										  title: ' ',
										  content: "${base}/ywmk/xxbbxx!sdtzbb.action?id="+idstr,
										  //closeBtn :0,//去掉右上角关闭按钮参数
										  area: ['80%', '80%'],
										  maxmin: true
										});
									}
									function vietab(idstr){
																			
										parent.layer.open({
										  type: 2,
										  title: ' ',
										  content: "${base}/ywmk/xxbbxx!vietab.action?id="+idstr,
										  //closeBtn :0,//去掉右上角关闭按钮参数
										  area: ['80%', '80%'],
										  maxmin: true
										});
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
									
									function exportexcel(){
										layer.msg("导出中...",{time:3000})
										var sjrwid = "${sjrwid!}";
										location.href="${base}/ywmk/xxbbxx!exportexcel.action?sjrwid="+sjrwid;
									}
									 
									function mbsc(){
										if(confirm("已生成数据将被覆盖，确定重新生成吗？")){
											$("#modalsubmit").modal({backdrop: 'static', keyboard: false});
											var sjrwid = "${sjrwid!}";
											$.post("${base}/ywmk/xxbbxx!mbsc.action", {sjrwid:sjrwid}, function(res) {
											    alert(res);
											    $("#query_form").submit();
											});
										}
									}
									
									$('#query_form').pfwpage({
										page_button:[
											{b_name:'1.原数据导入',b_function:ysjdr,bclass:'btn '},
											{b_name:'2.模板生成',b_function:mbsc,bclass:'btn '},
											{b_name:'3.报表生成',b_function:yjsc,bclass:'btn '},
											{b_name:'4.报表导出',b_function:exportexcel,bclass:'btn '}
										],
										page_col:
										[
											 //在这里编写结果集显示栏目名称，例：{col_name:'xxxx'};最后一列注意不能有","号
											  	{col_name:'表号'},
											  	{col_name:'报表名称'},
											 	{col_name:'',width:'15%'}	
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