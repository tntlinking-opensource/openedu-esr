<#include '/macro/crud-metro-page3-nowebpst.ftl' >

<#if id?exists>
	<#assign extPosition='修改专业信息'/>
<#else>
	<#assign extPosition='创建专业信息'/>
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
						<form id="save_inputForm" class="form-horizontal" action="${base}/ywmk/zyxx!save.action" method="post">
							<@s.token />
							<#if id?exists>
								<input type="hidden" name="id" value="${id}"/>
							</#if>
							<!-- 在这里编写输入的元素 -->
							<div class="control-group">
								<label class="control-label"><b>专业代码:</b><span class="required">*</span></label>
								<div class="controls">
									<input type="text" class="span10 m-wrap" id="zydm" name="zydm" value="${zydm!}" maxlength="50" />
								</div>
							</div>
							<div class="control-group">
								<label class="control-label"><b>专业名称:</b><span class="required">*</span></label>
								<div class="controls">
									<input type="text" class="span10 m-wrap" id="zymc" name="zymc" value="${zymc!}" maxlength="100" />
								</div>
							</div>
							<div class="control-group">
								<label class="control-label"><b>自主专业名称:</b><span class="required">*</span></label>
								<div class="controls">
									<input type="text" class="span10 m-wrap" id="zzzymc" name="zzzymc" value="${zzzymc!}" maxlength="100" />
								</div>
							</div>
							<div class="control-group">
								<label class="control-label"><b>年制:</b><span class="required">*</span></label>
								<div class="controls">
									<input type="text" class="span10 m-wrap" id="nzhi" name="nzhi" value="${nzhi!}" maxlength="10" />
								</div>
							</div>
							 
							<div class="control-group">
								<label class="control-label"><b>是否有效:</b><span class="required">*</span></label>
								<div class="controls">
									<span class="text">
										<label class="radio">
											&nbsp;&nbsp;&nbsp;
											<input type="radio" name="sfyx" value="1"  <#if sfyx?exists && sfyx == '1'>checked<#else>checked</#if>>是
										</label>
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<label class="radio">
											<input type="radio" name="sfyx" value="0"  <#if sfyx?exists && sfyx == '0'>checked</#if>>否
										</label>
									</span>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label"><b>备注:</b></label>
								<div class="controls">
									<input type="text" class="span10 m-wrap" id="bz" name="bz" value="${bz!}" maxlength="100" />
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
										zydm :{required:true},
										zymc :{required:true},
										zzzymc:{required:true},
										nzhi :{required:true},
										xzlx :{required:true},
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