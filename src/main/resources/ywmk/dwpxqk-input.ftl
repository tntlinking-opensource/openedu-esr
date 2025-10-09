<#include '/macro/crud-metro-page3-nowebpst.ftl' >

<#if id?exists>
	<#assign extPosition='对外培训信息维护'/>
<#else>
	<#assign extPosition='对外培训信息维护'/>
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
						<form id="save_inputForm" class="form-horizontal  form-bordered" action="${base}/ywmk/dwpxqk!save.action" method="post">
							<@s.token />
							<#if id?exists>
								<input type="hidden" name="id" value="${id}"/>
							</#if>
							<!-- 在这里编写输入的元素 -->
							<div class="control-group">
								<label class="control-label"><b>数据年份:</b><span class="required">*</span></label>
								<div class="controls">
									<input readonly id="sjnf" name="sjnf" class="span5 m-wrap" value="${sjnf!}" type="text" onclick="WdatePicker({el:'sjnf',dateFmt:'yyyy',readOnly:true})">
								</div>
							</div>
							
							<div class="control-group">
								<label class="control-label"><b>培训项目名称:</b><span class="required">*</span></label>
								<div class="controls">
									<input type="text" class="span10 m-wrap" id="pxxmmc" name="pxxmmc" value="${pxxmmc!}" maxlength="100" />
								</div>
							</div>
							<div class="control-group">
								<label class="control-label"><b>主要资金来源类型:</b><span class="required"></span></label>
								<div class="controls">
									<span class="text">
										<label class="radio">
											&nbsp;&nbsp;&nbsp;
											<input type="radio" name="zyzjly" value="1"  <#if zyzjly?exists && zyzjly == '1'>checked<#else>checked</#if>>财政资金支付
										</label>
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<label class="radio">
											<input type="radio" name="zyzjly" value="2"  <#if zyzjly?exists && zyzjly == '2'>checked</#if>>非财政资金支付
										</label>
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<label class="radio">
											<input type="radio" name="zyzjly" value="3"  <#if zyzjly?exists && zyzjly == '3'>checked</#if>>免费公益项目
										</label>
									</span>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label"><b>到账经费（万元）:</b><span class="required"></span></label>
								<div class="controls">
									<input type="text" class="span10 m-wrap" id="dzjf" name="dzjf" value="${dzjf!}" maxlength="20" />
								</div>
							</div>
							<div class="control-group">
								<label class="control-label"><b>培训时间（学时）:</b><span class="required"></span></label>
								<div class="controls">
									<input type="text" class="span10 m-wrap" id="pxsj" name="pxsj" value="${pxsj!}" maxlength="20" />
								</div>
							</div>
							<div class="control-group">
								<label class="control-label"><b>培训形式:</b><span class="required"></span></label>
								<div class="controls">
									<input type="text" class="span10 m-wrap" id="pxxs" name="pxxs" value="${pxxs!}" maxlength="100" />
								</div>
							</div>
							<div class="control-group">
								<label class="control-label"><b>企业职工（人）:</b><span class="required"></span></label>
								<div class="controls">
									<input type="text" class="span10 m-wrap" id="qyzg" name="qyzg" value="${qyzg!}" maxlength="20" />
								</div>
							</div>
							<div class="control-group">
								<label class="control-label"><b>党政领导干部（人）:</b><span class="required"></span></label>
								<div class="controls">
									<input type="text" class="span10 m-wrap" id="dzldgb" name="dzldgb" value="${dzldgb!}" maxlength="20" />
								</div>
							</div>
							<div class="control-group">
								<label class="control-label"><b>教师（人）:</b><span class="required"></span></label>
								<div class="controls">
									<input type="text" class="span10 m-wrap" id="jiaos" name="jiaos" value="${jiaos!}" maxlength="20" />
								</div>
							</div>
							<div class="control-group">
								<label class="control-label"><b>农村劳动者（人）:</b><span class="required"></span></label>
								<div class="controls">
									<input type="text" class="span10 m-wrap" id="ncldz" name="ncldz" value="${ncldz!}" maxlength="20" />
								</div>
							</div>
							<div class="control-group">
								<label class="control-label"><b>在校学生（人）:</b><span class="required"></span></label>
								<div class="controls">
									<input type="text" class="span10 m-wrap" id="zxxs" name="zxxs" value="${zxxs!}" maxlength="20" />
								</div>
							</div>
							<div class="control-group">
								<label class="control-label"><b>老年人（人）:</b><span class="required"></span></label>
								<div class="controls">
									<input type="text" class="span10 m-wrap" id="lnr" name="lnr" value="${lnr!}" maxlength="20" />
								</div>
							</div>
							<div class="control-group">
								<label class="control-label"><b>其他（人）:</b><span class="required"></span></label>
								<div class="controls">
									<input type="text" class="span10 m-wrap" id="qita" name="qita" value="${qita!}" maxlength="20" />
								</div>
							</div>
							<div class="control-group">
								<label class="control-label"><b>退役军人（人）:</b><span class="required"></span></label>
								<div class="controls">
									<input type="text" class="span10 m-wrap" id="tyjr" name="tyjr" value="${tyjr!}" maxlength="20" />
								</div>
							</div>
							<div class="control-group">
								<label class="control-label"><b>残疾人（人）:</b><span class="required"></span></label>
								<div class="controls">
									<input type="text" class="span10 m-wrap" id="cjr" name="cjr" value="${cjr!}" maxlength="20" />
								</div>
							</div>
							<div class="control-group">
								<label class="control-label"><b>承担培训工作校内教师姓名:</b><span class="required"></span></label>
								<div class="controls">
									<input type="text" class="span10 m-wrap" id="xnjsxm" name="xnjsxm" value="${xnjsxm!}" maxlength="20" />
									<br/>
									多个使用逗号隔开例如："张三,李四"
								</div>
							</div>
							<div class="control-group">
								<label class="control-label"><b>承担培训工作外聘人员姓名:</b><span class="required"></span></label>
								<div class="controls">
									<input type="text" class="span10 m-wrap" id="wpry" name="wpry" value="${wpry!}" maxlength="20" />
									<br/>
									多个使用逗号隔开例如："张三,李四"
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
										pxxmmc:{required: true},
										sjnf:{required: true}
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