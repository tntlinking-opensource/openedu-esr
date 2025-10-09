<#include '/macro/crud-metro-page3-nowebpst.ftl' >

<#if id?exists>
	<#assign extPosition='报表信息'/>
<#else>
	<#assign extPosition='报表信息'/>
</#if>

<@crudmetropage3>
	
		<div class="row-fluid">
			<div class="span12">
				<div class="row-fluid">
				<!--查询结果-->
				<div class="span12">
					<div class="portlet box green">
						<div class="portlet-title">
							<div class="caption"><i class="icon-edit"></i>
								${extPosition!}
							</div>
							<div class="actions" id="pagemenubutton" ></div>
						</div>
						<div class="portlet-body form">
						
						<!--内容表单-->
						<form id="save_inputForm" class="form-horizontal  form-bordered" action="${base}/ywmk/report_info!save.action" method="post">
							<@s.token />
							<#if id?exists>
								<input type="hidden" name="id" value="${id}"/>
							</#if>
							<!-- 在这里编写输入的元素 -->
							<div class="control-group">
								<label class="control-label"><b>表号:</b><span class="required">*</span></label>
								<div class="controls">
									<input type="text" class="span10 m-wrap" id="reportcode" name="reportcode" value="${reportcode!}" maxlength="25" />
								</div>
							</div>
							
							
							<div class="row-fluid">
								<div class="span6">
									<div class="control-group">
										<label class="control-label"><b>报表名称:</b><span class="required">*</span></label>
										<div class="controls">
											<input type="text" class="span10 m-wrap" id="reportname" name="reportname" value="${reportname!}" maxlength="50" />
										</div>
									</div>
								</div>
								<div class="span6">
									<div class="control-group">
										<label class="control-label"><b>报表代码:</b><span class="required">*</span></label>
										<div class="controls">
											<input type="text" class="span10 m-wrap" id="dm" name="dm" value="${dm!}" maxlength="50" />
										</div>
									</div>
								</div>
							</div>
							
							<div class="row-fluid">
								<div class="span6">
									<div class="control-group">
										<label class="control-label"><b>计量单位:</b><span class="required"></span></label>
										<div class="controls">
											<input type="text" class="span10 m-wrap" id="jldw" name="jldw" value="${jldw!}" maxlength="10" />
										</div>
									</div>
								</div>
								<div class="span6">
									<div class="control-group">
										<label class="control-label"><b>版本号:</b></label>
										<div class="controls">
											<input type="text" class="span10 m-wrap" id="versionh" name="versionh" value="${versionh!}" maxlength="25" />
										</div>
									</div>
								</div>
							</div>
							
							
							<div class="row-fluid">
								<div class="span6">
									<div class="control-group">
										<label class="control-label"><b>报表年份:</b><span class="required"></span></label>
										<div class="controls">
											<input readonly id="bbnf" name="bbnf" class="span10 m-wrap" value="${bbnf!}" type="text" onclick="WdatePicker({el:'bbnf',dateFmt:'yyyy',readOnly:true})" />
										</div>
									</div>
								</div>
							</div>
							 
							<div class="row-fluid">
								<div class="span6">
									<div class="control-group">
										<label class="control-label"><b>结果主表:</b><span class="required">*</span></label>
										<div class="controls">
											<input type="text" class="span10 m-wrap" id="jgbmc1" name="jgbmc1" value="${jgbmc1!}" maxlength="20" />
										</div>
									</div>
								</div>
								<div class="span6">
									<div class="control-group">
										<label class="control-label"><b>结果分表:</b><span class="required">*</span></label>
										<div class="controls">
											<input type="text" class="span10 m-wrap" id="jgbmc2" name="jgbmc2" value="${jgbmc2!}" maxlength="30" />
										</div>
									</div>
								</div>
								
							</div>
							<div class="row-fluid">
								<div class="span6">
									<div class="control-group">
										<label class="control-label"><b><span class="text">是否分校区填报: </span></b><span class="required">*</span></label>
										<div class="controls" style="margin-top: 5px;">
											&nbsp;&nbsp;&nbsp;&nbsp;
											<label class="radio">
												<input type="radio" name="sffxqtb"   value="0" <#if sffxqtb?exists & sffxqtb=='0'>checked<#else>checked</#if> >否
											</label>
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											<label class="radio">
												<input type="radio" name="sffxqtb"  value="1" <#if sffxqtb?exists & sffxqtb=='1'>checked</#if>>是
											</label>
										</div>
									</div>
								</div>
							</div>
							<!--
							<div class="control-group">
								<label class="control-label"><b>顺序号:</b></label>
								<div class="controls">
									<input type="number" min="1" max="999" step="1" id="sequenceNumber" name="sequenceNumber" value="${sequenceNumber!}" />
								</div>
							</div>
							-->
							
							<div class="row-fluid">
								<div class="span6">
							
									<div class="control-group">
										<label class="control-label"><b><span class="text">生成方式: </span></b><span class="required">*</span></label>
										<div class="controls" style="margin-top: 5px;">
											&nbsp;&nbsp;&nbsp;&nbsp;
											<label class="radio">
												<input type="radio" name="configytype" onclick="lxchg('1')" value="1" <#if entity.configytype?exists><#if entity.configytype=='1'>checked</#if><#else>checked</#if>>配置
											</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											<label class="radio">
												<input type="radio" name="configytype" onclick="lxchg('2')" value="2" <#if entity.configytype?exists && entity.configytype=='2'>checked</#if> >自定义url
											</label>
										</div>
									</div>
								</div>
							
							
								<div class="span6">
									<div class="control-group">
										<label class="control-label"><b><span class="text">报表归属: </span></b><span class="required">*</span></label>
										<div class="controls" style="margin-top: 5px;">
											&nbsp;&nbsp;&nbsp;&nbsp;
											<label class="radio">
												<input type="radio" name="bbgs"   value="1" <#if bbgs?exists & bbgs=='1'>checked<#else>checked</#if> >国家
											</label>
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											<label class="radio">
												<input type="radio" name="bbgs"  value="2" <#if bbgs?exists & bbgs=='2'>checked</#if>>教育局
											</label>
											
										</div>
									</div>
								</div>
							</div> 
							
							<div class="control-group" id="url_1">
								<label class="control-label"><b>url地址(报表填写页面):</b><span class="required">*</span></label>
								<div class="controls">
									<input type="text" class="span10 m-wrap" id="customURL" name="customURL" value="${customURL!}" maxlength="200" />
								</div>
							</div>
							<div class="control-group" id="url_2">
								<label class="control-label"><b>url地址(报表详情页面):</b><span class="required">*</span></label>
								<div class="controls">
									<input type="text" class="span10 m-wrap" id="customURLDetails" name="customURLDetails" value="${customURLDetails!}" maxlength="200" />
								</div>
							</div>
							<div class="control-group" id="url_3">
								<label class="control-label"><b>url地址(审核页面):</b><span class="required">*</span></label>
								<div class="controls">
									<input type="text" class="span10 m-wrap" id="customURLShenh" name="customURLShenh" value="${customURLShenh!}" maxlength="200" />
								</div>
							</div>
							<div class="control-group" id="url_4">
								<label class="control-label"><b>url地址(指导中心查询页面):</b><span class="required">*</span></label>
								<div class="controls">
									<input type="text" class="span10 m-wrap" id="customURLzdzxcx" name="customURLzdzxcx" value="${customURLzdzxcx!}" maxlength="200" />
								</div>
							</div>
							<div class="control-group" id="piz_1">
								<label class="control-label"><b>行数:</b><span class="required">*</span></label>
								<div class="controls">
									<input type="number" min="1" max="999" step="1" id="rowNumber" name="rowNumber" value="${rowNumber!}" />
								</div>
							</div>
							<div class="control-group" id="piz_2">
								<label class="control-label"><b>列数:</b><span class="required">*</span></label>
								<div class="controls">
									<input type="number" min="1" max="999" step="1" id="columnNumber" name="columnNumber" value="${columnNumber!}" />
								</div>
							</div>
							
							<div class="control-group">
								<label class="control-label"><b>是否复制填充:</b><span class="required">*</span></label>
								<div class="controls">	
									&nbsp;&nbsp;&nbsp;&nbsp;
									<label class="radio">
										<input type="radio" name="sffztc"   value="1" <#if sffztc?exists & sffztc=='1'>checked<#else>checked</#if> >是
									</label>
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<label class="radio">
										<input type="radio" name="sffztc"  value="0" <#if sffztc?exists & sffztc=='0'>checked</#if>>否
									</label>
								</div>
							</div>
							
							<div class="control-group">
								<label class="control-label"><b>是否比对:</b><span class="required">*</span></label>
								<div class="controls">
									&nbsp;&nbsp;&nbsp;&nbsp;
									<label class="radio">
										<input type="radio" name="sfbd"   value="1" <#if sfbd?exists & sfbd=='1'>checked<#else>checked</#if> >是
									</label>
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<label class="radio">
										<input type="radio" name="sfbd"  value="0" <#if sfbd?exists & sfbd=='0'>checked</#if>>否
									</label>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label"><b>指标解释:</b><span class="required">*</span></label>
								<div class="controls">
									<textarea   id="explains" name="explains" class="span10 m-wrap" rows="7" cols="40"  value="${entity.explains!}" >${entity.explains!}</textarea>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label"><b>指标说明:</b><span class="required">*</span></label>
								<div class="controls">
									<textarea   id="reportDescribe" name="reportDescribe" class="span10 m-wrap" rows="7" cols="40"  value="${entity.reportDescribe!}" >${entity.reportDescribe!}</textarea>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label"><b>校验关系:</b><span class="required">*</span></label>
								<div class="controls">
									<textarea   id="verifyRelations" name="verifyRelations" class="span10 m-wrap" rows="7" cols="40"  value="${entity.verifyRelations!}" >${entity.verifyRelations!}</textarea>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label"><b>适用学校:</b></label>
								<div class="controls" style="line-height: 40px;">
								<#list reqlist as obj>
									<label class="checkbox">
										<input type="checkbox" value="${obj.id}" name="checks"
										<#if syxxids?exists>
											<#list syxxids.split(",") as tmpid>
												<#if tmpid?exists && tmpid=obj.id>checked</#if>
											</#list>
										</#if> />${obj.name}&nbsp;&nbsp;&nbsp;&nbsp;
									</label>
								</#list>
								</div>
							</div>
							
							<div class="control-group">
								<label class="control-label"><b>备注:</b></label>
								<div class="controls">
									<input type="text" class="span10 m-wrap" id="bz" name="bz" value="${bz!}" maxlength="40" />
								</div>
							</div>
							
							<div class="row-fluid">
								<div class="span6">
									<div class="control-group">
										<label class="control-label"><b><span class="text">是否有效: </span></b><span class="required">*</span></label>
										<div class="controls" style="margin-top: 5px;">
											&nbsp;&nbsp;&nbsp;&nbsp;
											<label class="radio">
												<input type="radio" name="sfyx"   value="1" <#if sfyx?exists & sfyx=='1'>checked<#else>checked</#if> >是
											</label>
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											<label class="radio">
												<input type="radio" name="sfyx"  value="0" <#if sfyx?exists & sfyx=='0'>checked</#if>>否
											</label>
										</div>
									</div>
								</div>
							</div>
							
							<div class="row-fluid">
								<div class="span12">
									<div class="form-actions">
										<center>
											<button class="btn green big" type="submit" id="commit" name="commit" >
												<i class="icon-ok"></i> 提交
											</button>
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											<button class="btn big" type="button" onclick="javascript:window.close()" >
												<i class="icon-remove"></i> 关闭
											</button>
										</center>									
									</div>

								</div>
							</div>
						</form>																	

						<script type="text/javascript">	
							$(document).ready(function() {
								$("#save_inputForm").validate({
									rules: {
										<!-- 在这里编写验证规则 -->	
										reportcode : {required:true},
										reportname : {required:true},
										//jldw : {required:true},
										jgbmc1 : {required:true},
										dm : {required:true},
										bbgs : {required:true},
										jgbmc2 : {required:true}
													
									}
								});
								
								var sfpz = true;
								<#if entity.configytype?exists && entity.configytype == '2'>
									sfpz = false;					
								</#if>
								if(sfpz)
								{
									$("#url_1").hide();
									$("#url_2").hide();
									$("#url_3").hide();
									$("#url_4").hide();
									$("#piz_1").show();
								}else{
									$("#url_1").show();
									$("#url_2").show();
									$("#url_3").show();
									$("#url_4").show();
									$("#piz_1").hide();
								}
												
							});	
							
							function lxchg(val)
							{
								if(val && val == '1')
								{
									$("#url_1").hide();
									$("#url_2").hide();
									$("#url_3").hide();
									$("#url_4").hide();
									$("#piz_1").show();
									
								}else{
									$("#url_1").show();
									$("#url_2").show();
									$("#url_3").show();
									$("#url_4").show();
									$("#piz_1").hide();
								}
							}
						</script>
						<!--内容表单-->
						</div>
					</div>
				</div>
				<!--查询结果-->
				
				</div>
			</div>
		</div>
		
</@crudmetropage3>