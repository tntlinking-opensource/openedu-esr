<#include '/macro/crud-metro-page3-nowebpst.ftl' >

<#if id?exists>
	<#assign extPosition='修改数据字典(三级)'/>
<#else>
	<#assign extPosition='创建数据字典(三级)'/>
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
							<form id="save_inputForm" class="form-horizontal" action="${base}/ywmk/dict_three!save.action" method="post">
								<@s.token />
								<#if id?exists>
									<input type="hidden" name="id" value="${id}"/>
								</#if>
								<!-- 在这里编写输入的元素 -->
								<div class="control-group">
									<label class="control-label"><b>所属字典:</b><span class="required">*</span></label>
									<div class="controls">
										<select name="sszd.id" class="m-wrap span10">
											<option value="">==请选择==</option>
											<#if twoList?exists>
												<#list twoList as obj>
													<option value="${obj.id}"  <#if sszd?exists && sszd.id?exists && sszd.id ==obj.id>selected</#if> >${obj.name}</option>
												</#list>
											</#if>
										</select>
									</div>
								</div>
								<div class="control-group">
									<label class="control-label"><b>代码:</b></label>
									<div class="controls">
										<input type="text" class="span10 m-wrap" id="dm" name="dm" value="${dm!}" maxlength="50" />
									</div>
								</div>
								<div class="control-group">
									<label class="control-label"><b>名称:</b></label>
									<div class="controls">
										<input type="text" class="span10 m-wrap" id="name" name="name" value="${name!}" maxlength="100" />
									</div>
								</div>
								<div class="control-group">
									<label class="control-label"><b>是否有效:</b></label>
									<div class="controls">
									<span class="text">
										<label class="radio">
												&nbsp;&nbsp;&nbsp;
												<input type="radio" name="sfyx" value="1"  <#if sfyx?exists && sfyx == '1'>checked<#else>checked</#if>>有效
										</label>
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<label class="radio">
											<input type="radio" name="sfyx" value="0"  <#if sfyx?exists && sfyx == '0'>checked</#if>>无效
										</label>
									</span>
									</div>
								</div>
								<div class="control-group">
									<label class="control-label"><b>备注:</b></label>
									<div class="controls">
										<input type="text" class="span10 m-wrap" id="remark" name="remark" value="${remark!}" maxlength="200" />
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
											"sszd.id":{required: true},
											name:{required: true},
										}
									});
								});

								$('select[id=ssxxstr]').select2({
									minimumInputLength:1,
									ajax: {
										url:  "${base}/ywmk/xxxx!getdictjson.action",
										dataType: 'json',
										delay: 250,
										data: function (params) {
											return {
												kword: params.term
												//page: params.page  //分页显示先不要，没有效果
											};
										},
										processResults: function (data, params) {
											var options = new Array();
											$(data).each(function(i, o) {
												options.push({　　　　　　　　　　//获取select2个必要的字段，id与text
													id : o.id,
													text : o.xxmc
												});
											});
											return {
												results: options        //返回数据
											};
										},
										cache: true
									}
								});

								<#if ssxx?exists>
								var option = new Option('${ssxx.xxmc!}','${ssxx.id!}', true, true);
								$("#ssxxstr").append(option).trigger("change");
								</#if>
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