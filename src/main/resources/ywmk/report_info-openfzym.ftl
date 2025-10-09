<#include '/macro/crud-metro-page3-nowebpst.ftl' >

<#if id?exists>
	<#assign extPosition='报表复制'/>
<#else>
	<#assign extPosition='报表复制'/>
</#if>

<@crudmetropage3>
<style>
	.fjbut{
		 border: 1px solid;
   		 border-radius: 2px!important;
    
	}
</style>
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
						<form id="save_inputForm" class="form-horizontal  form-bordered" action="${base}/ywmk/report_info!bbfzgn.action" method="post"  >
							<!-- 在这里编写输入的元素 -->
								<input   type="hidden" class="span10 m-wrap" id="idstr" name="idstr" value="${idstr!}"   />
								<div class="control-group">
									<label class="control-label"><b>版本号:</b><span class="required">*</span></label>
									<div class="controls">
										<input   type="text" class="span10 m-wrap" id="bbh" name="bbh" value=""  maxlength="20" />
									</div>
								</div>
								<div class="control-group">
									<label class="control-label"><b>报表年份:</b><span class="required">*</span></label>
									<div class="controls">
										<input readonly type="text" class="span10 m-wrap" id="bbnf" name="bbnf"  onclick="WdatePicker({el:'bbnf',dateFmt:'yyyy',readOnly:true})" maxlength="20" />
									</div>
								</div>
							
							<div class="row-fluid">
								<div class="span12">
									<div class="form-actions">
										<center>
											<button class="btn green big" type="submit"  id="commit" name="commit" >
												<i class="icon-ok"></i> 确定
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
										bbh:{required:true},						
										bbnf:{required:true}						
										 
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