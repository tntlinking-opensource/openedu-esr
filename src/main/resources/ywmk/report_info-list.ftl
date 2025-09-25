<#include '/macro/crud-metro-page3.ftl' >

<@crudmetropage3>
	<form id="query_form" action="${base}/ywmk/report_info.action?bbgs=${Parameters['bbgs']?if_exists}" method="post">
	
		<!--顶部查询条件，根据实际情况选是上下，还是左右布局-->
		<div class="row-fluid cpquery">
		  	<div class="span2">
		  		<label class="control-label">
					版本号:
				</label>
				<input type="text" class="m-wrap span12" id="filter_LIKES_versionh" name="filter_LIKES_versionh"  value="${Parameters['filter_LIKES_versionh']?if_exists}" maxlength="20"/>
		  	</div>
		  	<div class="span2">
		  		<label class="control-label">
					表号:
				</label>
				<input type="text" class="m-wrap span12" id="filter_LIKES_reportcode" name="filter_LIKES_reportcode"  value="${Parameters['filter_LIKES_reportcode']?if_exists}" maxlength="50"/>
		  	</div>
		  	<div class="span2">
		  		<label class="control-label">
					报表名称/代码:
				</label>
				<input type="text" class="m-wrap span12" id="filter_LIKES_reportname_OR_dm" name="filter_LIKES_reportname_OR_dm"  value="${Parameters['filter_LIKES_reportname_OR_dm']?if_exists}" maxlength="30"/>
		  	</div>
		  	<#--
		  	<div class="span2">
		  		<label class="control-label">
					报表类型:
				</label>
				<select class="m-wrap span12" id="filter_EQS_reportlx" name="filter_EQS_reportlx">
					<option value="">==全部==</option>
					<option value="1" <#if Parameters['filter_EQS_reportlx']?exists && Parameters['filter_EQS_reportlx'] == '1'>selected</#if> >年报</option>
					<option value="2" <#if Parameters['filter_EQS_reportlx']?exists && Parameters['filter_EQS_reportlx'] == '2'>selected</#if> >季报</option>
				</select>
		  	</div>
		  	-->
		  	<div class="span2">
		  		<label class="control-label">
					适用学校:
				</label>
				<select class="m-wrap span12" id="filter_LIKES_syxxids" name="filter_LIKES_syxxids">
					<option value="">==全部==</option>
					<#if reqlist?exists>
						<#list reqlist as obj>
							<option value="${obj.id}" <#if Parameters['filter_LIKES_syxxids']?exists && Parameters['filter_LIKES_syxxids'] == obj.id>selected</#if> >${obj.name!}</option>
						</#list>
					</#if>
				</select>
		  	</div>
		  	
		  	<div class="span2">
		  		<label class="control-label">
					生成方式:
				</label>
				<select class="m-wrap span12" id="filter_EQS_configytype" name="filter_EQS_configytype">
					<option value="">==全部==</option>
					<option value="1" <#if Parameters['filter_EQS_configytype']?exists && Parameters['filter_EQS_configytype'] == '1'>selected</#if> >配置</option>
					<option value="2" <#if Parameters['filter_EQS_configytype']?exists && Parameters['filter_EQS_configytype'] == '2'>selected</#if> >自定义</option>
				</select>
		  	</div>
		  	<div class="span2">
		  		<label class="control-label">
					报表年份:
				</label>
				<input readonly id="filter_EQS_bbnf" name="filter_EQS_bbnf" class="span10 m-wrap" value="${Parameters['filter_EQS_bbnf']?if_exists}" type="text" onclick="WdatePicker({el:'filter_EQS_bbnf',dateFmt:'yyyy',readOnly:true})" />
		  	</div>	
		</div>
		<div class="row-fluid cpquery">
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
									  		${obj.reportcode!}
									  	</td>
									  	<td>
									  		${obj.reportname!}
									  	</td>
									  	<#--
									  	<td>
									  		<#if obj.reportlx?exists>
									  			<#if obj.reportlx == '1'>
									  				年报
									  			</#if>
									  			<#if obj.reportlx == '2'>
									  				季报
									  			</#if>
									  		</#if>
									  	</td>
									  	-->
									  	<td>
									  		${obj.versionh!}
									  	</td>
									  	<td>
									  		${obj.bbnf!}
									  	</td>
									  	<td>
									  		<#if obj.configytype?exists && obj.configytype == '2'>
									  			自定义url
									  		<#else>
									  			配置
									  		</#if>
									  	</td>
									  	
									  	<td title='${obj.syxxmcs!}'>
									  		<#if obj.syxxmcs?exists>
																				  			
												<#if obj.syxxmcs?length gt 50>
													${obj.syxxmcs?substring(0,50)!}...
												<#else>
													${obj.syxxmcs!} 
												</#if>
								  				
									  		</#if>
									  	</td>
									  	<td>
									  		<#if obj.sfyx?exists>
									  			<#if obj.sfyx == '1'>
									  				是
									  			</#if>
									  			<#if obj.sfyx == '0'>
									  				否
									  			</#if>
									  		</#if>
									  	</td>
										 <td>
											<center>
												<a class="oprt-a" href="javascript:;" onclick="window.open('${base}/ywmk/report_info!input.action?id=${obj.id}')"> 修改</a>
												<#if obj.configytype?exists && obj.configytype == '1'>
													&nbsp;&nbsp;&nbsp;
													<a class="oprt-a" href="javascript:;" onclick="window.open('${base}/ywmk/report_info!reptreview.action?id=${obj.id}')"> 预览</a>
													
													&nbsp;&nbsp;&nbsp;
													<a class="oprt-a" href="javascript:;" onclick="window.open('${base}/ywmk/report_info!inputtable.action?id=${obj.id}')"> 报表配置</a>
													&nbsp;&nbsp;&nbsp;
													<div class="btn-group">
											 			<a class="btn dropdown-toggle" data-toggle="dropdown" href="#">
															更多操作<i class="icon-angle-down"></i>
														</a>
														<ul class="dropdown-menu">
															<li><a class="oprt-a" href="javascript:;" onclick="gzpz('${obj.id!}')">比对规则配置</a></li>
															<li><a class="oprt-a" href="javascript:;" onclick="fzctgzpz('${obj.id!}')">复制填充规则配置</a></li>
															<li><a  href="javascript:;" onclick="window.open('${base}/ywmk/report_info_row_filter.action?bbid=${obj.id}')"> 行过滤</a></li>
														</ul>
													</div>
												</#if>
											</center>
										 </td>
									</tr>
								</#list>
								</table>		
								
								<div style="display: none;    width: 800px;    left: 37%;" data-focus-on="input:first"  class="modal hide fade in" id="pfwmodal2" style="display: block; margin-top: -155px;" aria-hidden="false">
									<div class="modal-body">
										<h3 class="form-section"><b>选择比对报表</b></h3>
										<table id="restab" class="table">
											<thead>
												<td>
													表名
												</td>
												<td>
													年份
												</td>
												<td>
													版本号
												</td>
												<td>
												</td>
											</thead>
											<tbody id="restabBody">
												
											</tbody>
										</table>
									</div>
									<div class="modal-footer">
										<a href="javascript:;" class="btn blue" onclick="modal2submit()">确定</a>
								        <a href="javascript:;" class="btn" onclick="modal2cancle()">取消</a>
								    </div>
								</div>
								
								
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
											window.open('${base}/ywmk/report_info!input.action');
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
									//报表复制功能
									function bbfz(){
										
										var idstr = "";
										$(".page_tr_selected").each(function(){
										
											idstr += $(this).attr("titl")+",";
										
										})
										idstr = idstr.substring(0,idstr.length - 1);
										if(idstr == ""){
											layer.msg("至少选择一张报表！");
											return false;
										}
										
										var url = '${base}/ywmk/report_info!openfzym.action?idstr='+idstr;
										parent.layer.open({
										  //closeBtn: 0,
										  type: 2,
										  title: '  ',
										  content: url,
										  area: ['80%', '60%'],
										  maxmin: false
										});
																			
									}
									
									$('#query_form').pfwpage({
										page_button:[
											{b_name:'新增',b_function:add,bclass:'btn ',bicon:'icon-pencil'},
											{b_name:'报表复制',b_function:bbfz,bclass:'btn '},
											{b_name:'删除',opttype:'delete',bclass:'btn redbtn',bicon:'icon-trash',b_url:'${base}/ywmk/report_info!delete.action',confirmStr:'是否确认删除？',paramName:'checks',selectNum:'^[0-9]*[1-9][0-9]*$'}
										],
										page_col:
										[
											 //在这里编写结果集显示栏目名称，例：{col_name:'xxxx'};最后一列注意不能有","号
											  	{col_name:'表号'},
											  	{col_name:'报表名称'},
											  	{col_name:'版本号'},
											  	{col_name:'报表年份'},
											  	{col_name:'生成方式'},
											  	{col_name:'适用学校'},
											  	{col_name:'是否有效'},
											 	{col_name:'',width: "350px"}	
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
									var bbidstr = "";
									
									function gzpz(bbid)
									{	
										window.open("${base}/ywmk/report_info!bdgzpz.action?bbid="+bbid);
										
										
										/**
						    			$("#restabBody").html("");
						    			
						    			bbidstr = bbid;
									    if(bbid != null && bbid !="" ){
									    	
									    	var url = '${base}/ywmk/report_info!getreptBycode.action';
									    	$.post(url,{bbid:bbid},function(res){
									    		
									    		if(res != "" && res != null){
									    				
									    			var result = JSON.parse(res);
									    			var html ="";
									    			for(var i=0;i<result.length;i++){
									    				html += "<tr>";
										    			html +='<td>'+result[i].reportname+'</td>';
										    			html +='<td>'+result[i].bbnf+'</td>';
										    			html +='<td>'+result[i].versionh+'</td>';
										    			html +='<td><a class="oprt-a" onclick="pz(\''+result[i].id+'\')">配置</a></td>';
										    			html +='</tr>';
									    			}
									    			
									    			$("#restabBody").append(html);
									    		}
									    		
									    	})
									    	
											$("#pfwmodal2").modal({backdrop: 'static', keyboard: false});
									    }else{
									     	layer.msg('参数错误！')
									    }
									    **/
									}	
									
									function fzctgzpz(bbid){
										window.open("${base}/ywmk/report_info!fzctgzpz.action?bbid="+bbid);
									}
									
									function pz(yxbbid){
										if(bbidstr != "" && yxbbid != ""){
											window.open("${base}/ywmk/report_info!bdgzpz.action")
										}else{
											layer.msg("参数错误！");
										
										}
									}
									
									function modal2cancle()
									{
										$('#pfwmodal2').modal('hide'); 
									}
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