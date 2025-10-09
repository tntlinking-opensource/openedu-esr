<#include '/macro/crud-metro-page3-nowebpst.ftl' >

<#if id?exists>
	<#assign extPosition='学校信息'/>
<#else>
	<#assign extPosition='学校信息'/>
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
						<form id="save_inputForm" class="form-horizontal" action="${base}/ywmk/xxxx!baoc.action" method="post">
							<#if  entity?exists && entity.id?exists>
								<input type="hidden" name="id" value="${entity.id!}"/>
							</#if>
							<!-- 在这里编写输入的元素 -->
							<div class="control-group">
								<label class="control-label"><b>学校代码:</b><span class="required">*</span></label>
								<div class="controls">
									<input type="text" class="span10 m-wrap" id="xxdm" name="xxdm" value="${entity.xxdm!}" maxlength="50" />
								</div>
							</div>
							<div class="control-group">
								<label class="control-label"><b>学校名称:</b><span class="required">*</span></label>
								<div class="controls">
									<input type="text" class="span10 m-wrap" id="xxmc" name="xxmc" value="${entity.xxmc!}" maxlength="100" />
								</div>
							</div>
							<div class="row-fluid">
								<div class="span12">
									<div class="control-group">
										<label class="control-label"><b>办学类型:</b></label>
										<div class="controls" style="line-height: 40px;">
											<#list reqlist4 as obj>
												<label class="checkbox">
													<input type="radio" value="${obj.id}" name="bxlx"
													<#if entity?exists && entity.bxlxid?exists>
														<#list entity.bxlxid.split(",") as bxlxid>
															<#if bxlxid?exists && bxlxid=obj.id>checked</#if>
														</#list>
													</#if> />
													${obj.name}&nbsp;&nbsp;&nbsp;&nbsp;
												</label>
											</#list>
										</div>
									</div>
								</div>
							</div>
							<div class="row-fluid">
								<div class="span12">
									<div class="control-group">
										<label class="control-label"><b>附设类型:</b></label>
										<div class="controls" style="line-height: 40px;">
											<#list fsbxlxList as obj>
												<label class="checkbox">
													<input  type="radio" value="${obj.id}" name="fslxchecks"  onclick="sfxz(this)"
														<#if entity?exists && entity.bxlxid?exists>
															<#list entity.bxlxid.split(",") as bxlxid>
																<#if bxlxid?exists && bxlxid=obj.id>checked</#if>
															</#list>
														</#if> />
													${obj.name}&nbsp;&nbsp;&nbsp;&nbsp;
												</label>
											</#list>
										</div>
									</div>
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
												<i class="icon-ok"></i> 保存
											</button>
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
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
										xxmc : {required:true},
										xxdm : {required:true},
										"bxlx.id" : {required:true},
									}
								});						
							});
							function  sfxz(obj){
								var sfxz = $(obj).parent().attr('class');
								if(sfxz === 'checked'){
									$(obj).removeAttr("checked");
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