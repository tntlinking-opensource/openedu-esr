<#include '/macro/crud-metro-page3-nowebpst.ftl' >
<#if id?exists>
	<#assign extPosition='修改学生信息'/>
<#else>
	<#assign extPosition='创建学生信息'/>
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
						<form id="save_inputForm" class="form-horizontal" action="${base}/ywmk/xsxx!save.action" method="post">
							<@s.token />
							<#if id?exists>
								<input type="hidden" name="id" value="${id}"/>
							</#if>
							<!-- 在这里编写输入的元素 -->
							<div class="row-fluid">
								<div class="span6">
									<div class="control-group">
										<label class="control-label"><b>数据年份:</b><span class="required">*</span></label>
										<div class="controls">
											<input type="text" class="span10 m-wrap" readonly id="sjnf" name="sjnf" value="${sjnf!}" onclick="WdatePicker({el:'sjnf',dateFmt:'yyyy',readOnly:true})" maxlength="10" />
										</div>
									</div>
								</div>
								<div class="span6">
									<div class="control-group">
										<label class="control-label"><b>所属班级:</b><span class="required">*</span></label>
										<div class="controls">
											<select name="ssbj.id" class="m-wrap span10" id="ssbj" onchange="cxxq()">
												<option value="">==请选择==</option>
												<#if BjxxList?exists>
													<#list BjxxList as obj>
														<option value="${obj.id}"  <#if ssbj?exists && ssbj.id?exists && ssbj.id ==obj.id>selected</#if> >${obj.bjmc}</option>
													</#list>
												</#if>
											</select>
										</div>
									</div>
							    </div>
							</div>
							<div class="row-fluid">
								<div class="span6">
									<div class="control-group">
										<label class="control-label"><b>学号:</b><span class="required">*</span></label>
										<div class="controls">
											<input type="text" class="span10 m-wrap" id="xh" name="xh" value="${xh!}" maxlength="50" />
										</div>
									</div>
								</div>
								<div class="span6">
									<div class="control-group">
										<label class="control-label"><b>姓名:</b><span class="required">*</span></label>
										<div class="controls">
											<input type="text" class="span10 m-wrap" id="xm" name="xm" value="${xm!}" maxlength="100" />
										</div>
									</div>
								</div>
							</div>
							<div class="row-fluid">
								<div class="span6">
									<div class="control-group">
										<label class="control-label"><b>出生年月:</b><span class="required">*</span></label>
										<div class="controls">
											<input type="text" class="span10 m-wrap" readonly id="csny" name="csny" value="${csny!}" maxlength="20" onclick="WdatePicker({el:'csny',dateFmt:'yyyy-MM-dd',readOnly:true})" />
										</div>
									</div>
								</div>
								<div class="span6">
									<div class="control-group">
										<label class="control-label"><b>性别:</b></label>
										<div class="controls">
											<span class="text">
												<label class="radio">
													&nbsp;&nbsp;&nbsp;
													<input type="radio" name="xb" value="1"  <#if xb?exists && xb == '1'>checked<#else>checked</#if>>男
												</label>
												&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
												<label class="radio">
													<input type="radio" name="xb" value="0"  <#if xb?exists && xb == '0'>checked</#if>>女
												</label>
											</span>
										</div>
									</div>
								</div>
							</div>
							<div class="row-fluid">
								<div class="span6">
									<div class="control-group">
										<label class="control-label"><b>入学年份:</b><span class="required">*</span></label>
										<div class="controls">
											<input type="text" class="span10 m-wrap" readonly id="rxnf" name="rxnf" value="${rxnf!}" maxlength="20" onclick="WdatePicker({el:'rxnf',dateFmt:'yyyy',readOnly:true})" />
										</div>
									</div>
								</div>
								<div class="span6">
									<div class="control-group">
										<label class="control-label"><b>入校类型:</b><span class="required">*</span></label>
										<div class="controls" id="dqztdiv">
											<select name="rxlx.id"  class="m-wrap span10" >
												<option value="">==请选择==</option>
												<#if rxlxList?exists>
													<#list rxlxList as obj>
														<option value="${obj.id}" <#if rxlx?exists && rxlx.id?exists && rxlx.id == obj.id>selected</#if> >${obj.name!}</option>
													</#list>
												</#if>
											</select>
										</div>
									</div>
								</div>
							</div>
							<div class="row-fluid">
								<div class="span6">
									<div class="control-group">
										<label class="control-label"><b>民族:</b><span class="required">*</span></label>
										<div class="controls" >
											<select name="mz.id" class="m-wrap span10">
												<option value="" >==请选择==</option>
												<#if minzList?exists>
													<#list minzList as obj>
														<option value="${obj.id}"  <#if mz?exists && mz.id?exists && mz.id ==obj.id>selected</#if> >${obj.name}</option>
													</#list>
												</#if>
											</select>
										</div>
									</div>
								</div>
								<div class="span6">
									<div class="control-group">
										<label class="control-label"><b>政治面貌:</b><span class="required">*</span></label>
										<div class="controls">
											<select name="zzmm.id" class="m-wrap span10">
												<option value="" >==请选择==</option>
												<#if zzmmList?exists>
													<#list zzmmList as obj>
														<option value="${obj.id}"  <#if zzmm?exists && zzmm.id?exists && zzmm.id ==obj.id>selected</#if> >${obj.name}</option>
													</#list>
												</#if>
											</select>
										</div>
									</div>
								</div>
							</div>
							<div class="row-fluid">
								<div class="span6">
									<div class="control-group">
										<label class="control-label"><b>籍贯:</b><span class="required">*</span></label>
										<div class="controls">
											<select name="jig.id" class="m-wrap span10">
												<#if jigList?exists>
													<option value="" >==请选择==</option>
													<#list jigList as obj>
														<option value="${obj.id}" <#if jig?exists && jig.id?exists && jig.id == obj.id>selected</#if>>
															${obj.name!}
														</option>
													</#list>
												</#if>
											</select>
										</div>
									</div>
								</div>
								<div class="span6">
									<div class="control-group">
										<label class="control-label"><b>残疾类型:</b></label>
										<div class="controls">
											<select name="cjlx.id"  class="m-wrap span10">
												<option value="">==请选择==</option>
												<#if canjlxList?exists>
													<#list canjlxList as obj>
														<option value="${obj.id}" <#if cjlx?exists && cjlx.id?exists && cjlx.id == obj.id>selected</#if> >${obj.name!}</option>
													</#list>
												</#if>
											</select>
										</div>
									</div>
								</div>
							</div>
							<div class="row-fluid">
								<div class="span6">
									<div class="control-group">
										<label class="control-label"><b>随迁子女类型:</b></label>
										<div class="controls">
											<select name="sqznlx.id"  class="m-wrap span10">
												<option value="">==请选择==</option>
												<#if sqznList?exists>
													<#list sqznList as obj>
														<option value="${obj.id}" <#if sqznlx?exists && sqznlx.id?exists && sqznlx.id == obj.id>selected</#if> >${obj.name!}</option>
													</#list>
												</#if>
											</select>
										</div>
									</div>
								</div>
								<div class="span6">
									<div class="control-group">
										<label class="control-label"><b>进城务工随迁子女类型:</b></label>
										<div class="controls">
											<select name="jcwgsqlx.id"  class="m-wrap span10">
												<option value="">==请选择==</option>
												<#if jcsqzntList?exists>
													<#list jcsqzntList as obj>
														<option value="${obj.id}" <#if jcwgsqlx?exists && jcwgsqlx.id?exists && jcwgsqlx.id == obj.id>selected</#if> >${obj.name!}</option>
													</#list>
												</#if>
											</select>
										</div>
									</div>
								</div>
							</div>
							<div class="row-fluid">
								<div class="span6">
									<div class="control-group">
										<label class="control-label"><b>是否国际学生:</b></label>
										<div class="controls">
											<span class="text">
												<label class="radio">
													&nbsp;&nbsp;&nbsp;
													<input type="radio" name="sfgjxs" value="0"  <#if sfgjxs?exists && sfgjxs == '0'>checked<#else>checked</#if>>否
												</label>
												&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
												<label class="radio">
													<input type="radio" name="sfgjxs" value="1"  <#if sfgjxs?exists && sfgjxs == '1'>checked</#if>>是
												</label>
										    </span>
										</div>
									</div>
								</div>

							</div>
							<div class="row-fluid">
								<div class="span6">
									<div class="control-group">
										<label class="control-label"><b>是否寄宿:</b></label>
										<div class="controls">
											<span class="text">
												<label class="radio">
													&nbsp;&nbsp;&nbsp;
													<input type="radio" name="sfjs" value="0"  <#if sfjs?exists && sfjs == '0'>checked<#else>checked</#if>>否
												</label>
												&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
												<label class="radio">
													<input type="radio" name="sfjs" value="1"  <#if sfjs?exists && sfjs == '1'>checked</#if>>是
												</label>
										    </span>
										</div>
									</div>
								</div>
							</div>
							<div class="row-fluid">
								<div class="span4">
									<div class="control-group">
										<label class="control-label"><b>当前状态:</b><span class="required">*</span></label>
										<div class="controls" id="dqztdiv">
											<select name="dqzt.id"  class="m-wrap span10" id="xzdqzt" onchange="ztbt()">
												<option value="">==请选择==</option>
												<#if dqztList?exists>
													<#list dqztList as obj>
														<option value="${obj.id}" <#if dqzt?exists && dqzt.id?exists && dqzt.id == obj.id>selected</#if> >${obj.name!}</option>
													</#list>
												</#if>
											</select>
										</div>
									</div>
								</div>
								
								<div class="span4" id="txyydiv">
									<div class="control-group">
										<label class="control-label"><b>退学原因:</b><span class="required"></span></label>
										<div class="controls">
											<select name="txyy.id"  class="m-wrap span10" id="txyy" >
												<option value="">==请选择==</option>
												<#if txyyList?exists>
													<#list txyyList as obj>
														<option value="${obj.id}" <#if txyy?exists && txyy.id?exists && txyy.id == obj.id>selected</#if> >${obj.name!}</option>
													</#list>
												</#if>
											</select>
										</div>
									</div>
								</div>
								<div class="span4">
									<div class="control-group" >
										<label class="control-label" id="swbt"><b>死亡原因:</b></label>
										<label class="control-label" id="bdbt"><b>状态变动原因:</b></label>
										<div class="controls">
											<input type="text" class="span10 m-wrap" id="ztbdyy" name="ztbdyy" value="${ztbdyy!}" maxlength="100" />
										</div>
										<div class="controls" id="swdxk" >
											<select name="swlx.id"  class="m-wrap span10" >
												<option value="">==请选择==</option>
												<#if swlxList?exists>
													<#list swlxList as obj>
														<option value="${obj.id}" <#if swlx?exists && swlx.id?exists && swlx.id == obj.id>selected</#if> >${obj.name!}</option>
													</#list>
												</#if>
											</select>
										</div>
									</div>
								</div>
								<div class="span4">
									<div class="control-group" id="swdddiv">
										<label class="control-label"><b>死亡地点:</b></label>
										<div class="controls">
											<span class="text">
												<label class="radio">
													&nbsp;&nbsp;&nbsp;
													<input type="radio" class="span10 m-wrap" name="swdd" value="1" <#if swdd?exists && swdd == '1'>checked</#if> maxlength="50" />校园内
												</label>
												&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
												<label class="radio">
													<input type="radio" class="span10 m-wrap" name="swdd" value="2" <#if swdd?exists && swdd == '2'>checked</#if> maxlength="50" />校园外
												</label>
											</span>
										</div>
									</div>
								</div>
							</div>
							<div class="row-fluid">
								<div class="span6">
									<div class="control-group">
										<label class="control-label"><b>备注:</b></label>
										<div class="controls">
											<input type="text" class="span10 m-wrap" id="bz" name="bz" value="${bz!}" maxlength="100" />
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
										xm : {required : true},
										xh : {required : true},
										rxnf : {required : true},
										csny : {required : true},
										sjnf : {required : true},
										xb : {required : true},
										"mz.id" : {required : true},
										"zzmm.id" : {required : true},
										"dqzt.id" : {required : true},
										"rxlx.id" : {required : true},
										"ssbj.id" : {required : true},
									}
								});
								$("#swbt").hide();
								$("#bdbt").hide();
								$("#ztbdyy").hide();
								$("#swdddiv").hide();
								ztbt();
							});
							function ztbt (){
								var xzdqzt =  $('#xzdqzt option:selected').val();
								if (xzdqzt == null || xzdqzt == ""){
									$("#swbt").hide();
									$("#swdxk").hide();
									$("#bdbt").hide();
									$("#ztbdyy").hide();
									$("#swdddiv").hide();
									$("#txyydiv").hide();
								}else{
									if (xzdqzt == '8a8080888625ddb6018625eda1e9000c'){
										$("#swbt").show();
										$("#swdxk").show();
										$("#bdbt").hide();
										$("#ztbdyy").hide();
										$("#swdddiv").show();
									}else {
										$("#swbt").hide();
										$("#swdxk").hide();
										$("#bdbt").show();
										$("#ztbdyy").show();
										$("#swdddiv").hide();
									}
									
									if(xzdqzt == '8a8080888625ddb6018625e85d4f0004'){
										$("#txyydiv").show();
									}else{
										$("#txyydiv").hide();
									}
									
								}

							}

							function cxxq(){
								var xzbjid =  $('#ssbj option:selected').val();
								$.ajax({
									url: "${base}/ywmk/xsxx!getxqlxBybjid.action",
									async: false,
									type: "post",
									data: {
										bjid: xzbjid,
									},
									success: function (data) {
										alert(data.xxlxmc)
									},
									cache: true
								});

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