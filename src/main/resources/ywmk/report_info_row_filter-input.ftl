<#include '/macro/crud-metro-page3-nowebpst.ftl' >

<#if id?exists>
	<#assign extPosition='行过滤配置'/>
<#else>
	<#assign extPosition='行过滤配置'/>
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
						<form id="save_inputForm" class="form-horizontal  form-bordered" action="${base}/ywmk/report_info_row_filter!save.action?bbid=${bbid}" method="post">
							<@s.token />
							<#if id?exists>
								<input type="hidden" name="id" value="${id}"/>
							</#if>
							<!-- 在这里编写输入的元素 -->
							<div class="control-group">
								<label class="control-label"><b>办学类型:</b><span class="required">*</span></label>
								<div class="controls">
									<select name="ssbxlx.id" id="ssbxlx" class="span10 m-wrap">
										<#list reqlist as obj>
											<option value="${obj.id}" <#if ssbxlx?exists && ssbxlx.id == obj.id>selected</#if> >${obj.name!}</option>
										</#list>
									</select>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label"><b>行号:</b><span class="required">*</span></label>
								<div class="controls">
									<input type="text" class="span10 m-wrap" id="hanghs" name="hanghs" value="${hanghs!}" maxlength="200" />
									<span class="help-block" style="color:#b94a48">多行以,号分隔,如：01,02,03。指在报表中要显示的行号</span>
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
									}
								});						
							});	
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