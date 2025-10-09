<#include '/macro/crud-metro-page3.ftl' >

<@crudmetropage3>
	<form id="query_form" action="${base}/ywmk/xxbbxx!bbxxlist.action" method="post">
	
	 
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
								<table id="result_page_table" class="table">
									<thead style="background-color: #eeeeee">
										<tr style="height: 50px;color:#666">
											<th style="vertical-align: middle;font-size: 15px;">表号</th>
											<th style="vertical-align: middle;font-size: 15px;">报表名称</th>
											<th style="vertical-align: middle;font-size: 15px;">报表归属</th>
											<th style="vertical-align: middle;font-size: 15px;" width="350px"></th>
										</tr>
									</thead>
									<#if jsonstr?exists>
										<#assign jsondata=jsonstr?eval /> 
										<#if jsondata.code == '200' >
											<#assign datastr = jsondata.data?eval />
											<#list datastr as obj>
												<tr>
													<td>${obj.reportcode!}</td>
													<td>${obj.reportname!}</td>
													<td>
														<#if obj.bbgs?exists && obj.bbgs == '1'>国家</#if>
														<#if obj.bbgs?exists && obj.bbgs == '2'>教育局</#if>
													</td>
													<td>
														<a class="oprt-a" href="javascript:;" onclick="window.open('${base}/ywmk/xxbbxx!yulan.action?idstr=${obj.id!}')">预览</a>
													</td>
												</tr>
											</#list>
										<#else>
											<tr>
												<td colspan="3">${jsondata.msg!}</td>
											</tr>
										</#if>
									</#if> 
								</table>		
								 
								<!--根据实际需要重新定义对话框的样式-->
								<style>
									#pfwmodal{
										width: 800px;
										margin: 0 0 0 -370px; 
									}
								</style>								
	
								<script type="text/javascript">
								  
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