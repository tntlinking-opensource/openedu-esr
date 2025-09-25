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
										<label class="control-label"><b>学制类型:</b></label>
										<div class="controls">
											<span class="text">
												<label class="radio">
													&nbsp;&nbsp;&nbsp;
													<input type="radio" name="xzlx" value="1"  <#if xzlx?exists && xzlx == '1'>checked<#else>checked</#if>>全日制
												</label>
												&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
												<label class="radio">
													<input type="radio" name="xzlx" value="0"  <#if xzlx?exists && xzlx == '0'>checked</#if>>非全日制
												</label>
											</span>
										</div>
									</div>
								</div>
								<div class="span6">
									<div class="control-group">
										<label class="control-label"><b>是否五年制高职中职段:</b></label>
										<div class="controls">
											<span class="text">
												<label class="radio">
													&nbsp;&nbsp;&nbsp;
													<input type="radio" name="wnzgzzzd" value="0"  <#if wnzgzzzd?exists && wnzgzzzd == '0'>checked<#else>checked</#if>>否
												</label>
												&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
												<label class="radio">
													<input type="radio" name="wnzgzzzd" value="1"  <#if wnzgzzzd?exists && wnzgzzzd == '1'>checked</#if>>是
												</label>
											</span>
										</div>
									</div>
								</div>
							</div>
							<div class="row-fluid">
								<div class="span6">
									<div class="control-group">
										<label class="control-label"><b>招生中其他情况:</b><span class="required"></span></label>
										<div class="controls" >
											 <select name="zszqtqk.id" id="zszqtqkid" class="m-wrap span10" onchange="changezszqtqk()">
												<option value="" >==请选择==</option>
												<#if zszqtqkList?exists>
													<#list zszqtqkList as obj>
														<option value="${obj.id}"  <#if zszqtqk?exists && zszqtqk.id?exists && zszqtqk.id ==obj.id>selected</#if> >${obj.name}</option>
													</#list>
												</#if>
											</select>
										</div>
									</div>
								</div>
								<div class="span6 xssfczby">
									<div class="control-group">
										<label class="control-label"><b>是否初中毕业生:</b><span class="required"></span></label>
										<div class="controls" >
											<span class="text">
												&nbsp;&nbsp;&nbsp;&nbsp;
												<label class="radio">
													<input type="radio" name="sfczbys"   value="0" <#if sfczbys?exists && sfczbys=='0'>checked<#else>checked</#if> >否
												</label>
												&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
												<label class="radio">
													<input type="radio" name="sfczbys"  value="1" <#if sfczbys?exists && sfczbys=='1'>checked</#if>>是
												</label>
											</span>
										</div>
									</div>
								</div>
							</div>

							<div class="row-fluid">
								<div class="span6">
									<div class="control-group">
										<label class="control-label"><b>是否获取职业类证书:</b><span class="required"></span></label>
										<div class="controls" >
											 <span class="text">
												&nbsp;&nbsp;&nbsp;&nbsp;
												<label class="radio">
													<input type="radio" name="sfhqzylzs"   value="0" <#if sfhqzylzs?exists && sfhqzylzs=='0'>checked<#else>checked</#if> >否
												</label>
												&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
												<label class="radio">
													<input type="radio" name="sfhqzylzs"  value="1" <#if sfhqzylzs?exists && sfhqzylzs=='1'>checked</#if>>是
												</label>
											</span>
										</div>
									</div>
								</div>
								<div class="span6">
									<div class="control-group">
										<label class="control-label"><b>是否获取职业技能等级证书:</b><span class="required"></span></label>
										<div class="controls" >
											<span class="text">
												&nbsp;&nbsp;&nbsp;&nbsp;
												<label class="radio">
													<input type="radio" name="sfhqzyjnzs"   value="0" <#if sfhqzyjnzs?exists && sfhqzyjnzs=='0'>checked<#else>checked</#if> >否
												</label>
												&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
												<label class="radio">
													<input type="radio" name="sfhqzyjnzs" onclick="sfhqzyjnzsclick()" value="1" <#if sfhqzyjnzs?exists && sfhqzyjnzs=='1'>checked</#if>>是
												</label>
											</span>
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
								<div class="span6">
									<div class="control-group">
										<label class="control-label"><b>所属大洲:</b></label>
										<div class="controls">
											<span class="text">
												<label class="radio">
													&nbsp;&nbsp;&nbsp;
													<input type="radio" name="ssdz" value="1"  <#if ssdz?exists && ssdz == '1'>checked<#else>checked</#if>>亚洲
												</label>
												&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
												<label class="radio">
													<input type="radio" name="ssdz" value="2"  <#if ssdz?exists && ssdz == '2'>checked</#if>>非洲
												</label>
												&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
												<label class="radio">
													<input type="radio" name="ssdz" value="3"  <#if ssdz?exists && ssdz == '3'>checked</#if>>欧洲
												</label>
												&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
												<label class="radio">
													<input type="radio" name="ssdz" value="4"  <#if ssdz?exists && ssdz == '4'>checked</#if>>北美洲
												</label>
												&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
												<label class="radio">
													<input type="radio" name="ssdz" value="5"  <#if ssdz?exists && ssdz == '5'>checked</#if>>南美洲
												</label>
												&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
												<label class="radio">
													<input type="radio" name="ssdz" value="6"  <#if ssdz?exists && ssdz == '6'>checked</#if>>大洋洲
												</label>
										    </span>
										</div>
									</div>
								</div>
							</div>
							<!-- ch cancle
							<div class="row-fluid">
								<div class="span6">
									<div class="control-group">
										<label class="control-label"><b>父母居住证积分是否达标:</b></label>
										<div class="controls">
											<span class="text">
												<label class="radio">
													&nbsp;&nbsp;&nbsp;
													<input type="radio" name="fmjzzjfsfdb" value="0"  <#if fmjzzjfsfdb?exists && fmjzzjfsfdb == '0'>checked<#else>checked</#if>>否
												</label>
												&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
												<label class="radio">
													<input type="radio" name="fmjzzjfsfdb" value="1"  <#if fmjzzjfsfdb?exists && fmjzzjfsfdb == '1'>checked</#if>>是
												</label>
										    </span>
										</div>
									</div>
								</div>
							</div>
							-->
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
								<div class="span6">
									<div class="control-group">
										<label class="control-label"><b>是否送教上门:</b></label>
										<div class="controls">
											<span class="text">
												<label class="radio">
													&nbsp;&nbsp;&nbsp;
													<input type="radio" name="sfsjsm" value="0"  <#if sfsjsm?exists && sfsjsm == '0'>checked<#else>checked</#if>>否
												</label>
												&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
												<label class="radio">
													<input type="radio" name="sfsjsm" value="1"  <#if sfsjsm?exists && sfsjsm == '1'>checked</#if>>是
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
								changezszqtqk()
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
							
							function changezszqtqk(){
								var val = $("#zszqtqkid").val();
								if(val != "" && val === "2c9080b088b7a5ff0188b7c56a210001"){
									$(".xssfczby").show();
								}else{
									$(".xssfczby").hide();
									$(".xssfczby").hide();
									
									$(':radio[name=sfczbys][value='+0+']').prop('checked',true).parent('span').addClass('checked');
									$(':radio[name=sfczbys][value='+1+']').prop('checked',false).parent('span').removeClass('checked');
								}
							}
							function sfhqzyjnzsclick(){
								$(':radio[name=sfhqzylzs][value='+0+']').prop('checked',false).parent('span').removeClass('checked');
								$(':radio[name=sfhqzylzs][value='+1+']').prop('checked',true).parent('span').addClass('checked');
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