<#include '/macro/crud-metro-page3-nowebpst.ftl' >

<#if id?exists>
	<#assign extPosition='修改班级信息'/>
<#else>
	<#assign extPosition='创建班级信息'/>
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
							<form id="save_inputForm" class="form-horizontal  form-bordered" action="${base}/ywmk/bjxx!save.action" method="post">
								<@s.token />
								<#if id?exists>
									<input type="hidden" name="id" value="${id}"/>
								</#if>
								<!-- 在这里编写输入的元素 -->
								<div class="control-group">
									<label class="control-label"><b>所属校区:</b><span class="required">*</span></label>
									<div class="controls">
										<select name="ssxq.id" class="m-wrap span10" id="ssxq" onchange="xzxq()">
											<option value="">==请选择==</option>
											<#if xxxqList?exists>
												<#list xxxqList as obj>
													<option value="${obj.id}"  <#if ssxq?exists && ssxq.id?exists && ssxq.id ==obj.id>selected</#if> >${obj.xqmc}</option>
												</#list>
											</#if>
										</select>
									</div>
								</div>
								<div class="control-group">
									<label class="control-label"><b>学段:</b></label>
									<div class="controls">
										<span class="text">
											&nbsp;&nbsp;&nbsp;&nbsp;
											<label class="radio">
												<input type="radio" name="xued"   value="1" <#if xued?exists & xued=='1'>checked</#if> >小学
											</label>
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											<label class="radio">
												<input type="radio" name="xued"  value="2" <#if xued?exists & xued=='2'>checked</#if>>初中
											</label>
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											<label class="radio">
												<input type="radio" name="xued"  value="3" <#if xued?exists & xued=='3'>checked</#if>>高中
											</label>
										</span>
									</div>
								</div>
								<div class="control-group">
									<label class="control-label"><b>班级代码:</b></label>
									<div class="controls">
										<input type="text" class="span10 m-wrap" id="bjdm" name="bjdm" value="${bjdm!}" maxlength="50" />
									</div>
								</div>
								<div class="control-group">
									<label class="control-label"><b>班级名称:</b><span class="required">*</span></label>
									<div class="controls">
										<input type="text" class="span10 m-wrap" id="bjmc" name="bjmc" value="${bjmc!}" maxlength="50" />
									</div>
								</div>
								<div class="control-group">
									<label class="control-label"><b>年级:</b><span class="required">*</span></label>
									<div class="controls">
										<input type="text" readonly class="span10 m-wrap" id="nianj" name="nianj" value="${nianj!}" maxlength="10"  onclick="WdatePicker({el:'nianj',dateFmt:'yyyy',readOnly:true})"    />
									</div>
								</div>
								<div class="control-group">
									<label class="control-label"><b>当前状态:</b><span class="required">*</span></label>
									<div class="controls" onchange="bynfcs()">
										<select name="dqzt.id" id="bynfcs" >
											<option value="">==请选择==</option>
											<#list dqztList as obj>
												<option value="${obj.id}"  <#if dqzt?exists && dqzt.id?exists && dqzt.id ==obj.id>selected</#if> >${obj.name}</option>
											</#list>
										</select>
									</div>
								</div>
								<div class="control-group" id="bynfid">
									<label class="control-label"><b>毕业年月:</b><span class="required">*</span></label>
									<div class="controls">
										<input type="text" class="span6 m-wrap" readonly id="byny" name="byny" value="${byny!}" maxlength="20" onclick="WdatePicker({el:'byny',dateFmt:'yyyy-MM',readOnly:true})" />
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
											nianj:{required:true},
											bjmc:{required:true},
											"dqzt.id":{required:true},
											"ssxq.id":{required:true},
										}
									});
									//毕业年份
									bynfcs()
								});
								function bynfcs(){
									var xzxj =  $('#bynfcs option:selected').val();
									if (xzxj == '8a80808886112a280186112ccc670003'){
										$("#bynfid").show();
									}else{
										$("#bynfid").hide();
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