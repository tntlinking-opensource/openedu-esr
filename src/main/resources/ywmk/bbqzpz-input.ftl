<#include '/macro/crud-metro-page3-nowebpst.ftl' >

<#if id?exists>
	<#assign extPosition='取值配置'/>
<#else>
	<#assign extPosition='取值配置'/>
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
						<form id="save_inputForm" class="form-horizontal  form-bordered" action="${base}/ywmk/bbqzpz!save.action" method="post">
							<@s.token />
							<#if id?exists>
								<input type="hidden" name="id" value="${id}"/>
							</#if>
							
							<div class="control-group">
								<label class="control-label"><b>办学类型:</b><span class="required">*</span></label>
								<div class="controls">
									<span class="text">
										<#if bxlxList?exists>
											<#list bxlxList as bxlxdict>
												<label class="radio">
													<input type="radio" name="bxlx.id" value="${bxlxdict.id!}" <#if bxlx?exists && bxlx.id == bxlxdict.id>checked</#if>  >${bxlxdict.name!}
												</label>
												&nbsp;&nbsp;&nbsp;&nbsp;
											</#list>
										</#if>
									</span>
								</div>
							</div>
							
							<div class="control-group">
								<label class="control-label"><b>所属报表:</b><span class="required">*</span></label>
								<div class="controls">
									<select name="reportInfo.id" id="reportInfo" class="m-wrap span10">
										<option value="">==请选择==</option>
										<#if bbList?exists>
											<#list bbList as obj>
												<option value="${obj.id!}" <#if reportInfo?exists && reportInfo.id?exists && reportInfo.id == obj.id>selected</#if> >${obj.reportcode!} - ${obj.reportname!}</option>
											</#list>
										</#if>
									</select>
								</div>
							</div> 
							
							<div class="control-group">
								<label class="control-label"><b>取值类型:</b><span class="required">*</span></label>
								<div class="controls">
									<span class="text">
										&nbsp;&nbsp;&nbsp;&nbsp;
										<label class="radio">
											<input type="radio" name="qzlx"   value="1" <#if qzlx?exists & qzlx=='1'>checked<#else>checked</#if> >sql取值
										</label>
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<label class="radio">
											<input type="radio" name="qzlx"  value="2" <#if qzlx?exists & qzlx=='2'>checked</#if>>回调服务
										</label>
									</span>
								</div>
							</div>
							
							<div class="control-group sql" >
								<label class="control-label"><b>sql:</b><span class="required">*</span></label>
								<div class="controls">
									<textarea type="text"  class="span10 m-wrap"  rows="5"  id="sqlpz" name="sqlpz"   maxlength="5000" />${sqlpz!}</textarea>
								</div>
							</div>
							<div class="control-group hdfw">
								<label class="control-label"><b>回调服务:</b><span class="required">*</span></label>
								<div class="controls">
									<input type="text" class="span10 m-wrap" id="hdfw" name="hdfw" value="${hdfw!}" maxlength="100" />
								</div>
							</div>
							<div class="control-group">
								<label class="control-label"><b><span class="text">是否有效: </span></b><span class="required">*</span></label>
								<div class="controls">
									<span class="text">
									<label class="radio">
										<input type="radio" name="sfyx"  value="1" <#if sfyx?exists & sfyx=='1'>checked<#else>checked</#if>>是
									</label>
									&nbsp;&nbsp;&nbsp;&nbsp;
									<label class="radio">
										<input type="radio" name="sfyx"   value="0" <#if sfyx?exists & sfyx=='0'>checked</#if> >否
									</label>
									</span>
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
								$("input[type='radio']").change(function(){
									if($(this).is(":checked")) {
										var selectedRadio = $(this).val();
										chagradio(selectedRadio);
									}
								});			
								var selectedValue = $('input[name="qzlx"]:checked').val();
								chagradio(selectedValue)
							});	
							
							function chagradio(val){
								if(val === "1"){
									$(".sql").show();
									$(".hdfw").hide();
								}
								if(val === "2"){
									$(".sql").hide();
									$(".hdfw").show();
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