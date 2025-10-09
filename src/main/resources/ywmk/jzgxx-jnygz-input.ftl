<#include '/macro/crud-metro-page3-nowebpst.ftl' >

<#if id?exists>
	<#assign extPosition='修改教职工信息'/>
<#else>
	<#assign extPosition='创建教职工信息'/>
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
						<form id="save_inputForm" class="form-horizontal" action="${base}/ywmk/jzgxx!save.action" method="post">
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
											<input type="text" class="span10 m-wrap" id="sjnf" name="sjnf" value="${sjnf!}" readonly onclick="WdatePicker({el:'sjnf',dateFmt:'yyyy',readOnly:true})" maxlength="10" />
										</div>
									</div>
								</div>
								<input type="hidden" readonly class="span10 m-wrap" id="xxblxlx"  value="${xxlxdm!}"  />
								<div class="span6">
									<div class="control-group">
										<label class="control-label"><b>所属校区:</b><span class="required">*</span></label>
										<div class="controls">
											<select name="ssxq.id" class="m-wrap span10" id="ssxq" >
												<option value="">==请选择==</option>
												<#if xxxqList?exists>
													<#list xxxqList as obj>
														<option value="${obj.id}"  <#if ssxq?exists && ssxq.id?exists && ssxq.id ==obj.id>selected</#if> >${obj.xqmc}</option>
													</#list>
												</#if>
											</select>
										</div>
									</div>
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
									</span>
								</div>
							</div>
							<div class="row-fluid">
								<div class="span6">
									<div class="control-group">
										<label class="control-label"><b>教工号:</b><span class="required">*</span></label>
										<div class="controls">
											<input type="text" class="span10 m-wrap" id="jgh" name="jgh" value="${jgh!}" maxlength="50" />
										</div>
									</div>
								</div>
								<div class="span6">
									<div class="control-group">
										<label class="control-label"><b>姓名:</b><span class="required">*</span></label>
										<div class="controls">
											<input type="text" class="span10 m-wrap" id="xm" name="xm" value="${xm!}" maxlength="50" />
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
										<label class="control-label"><b>性别:</b><span class="required">*</span></label>
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
										<label class="control-label"><b>民族:</b><span class="required">*</span></label>
										<div class="controls" >
											<select name="mz.id" class="m-wrap span10">
												<option value="">==请选择==</option>
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
												<option value="">==请选择==</option>
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
										<label class="control-label"><b>从教年月:</b><span class="required">*</span></label>
										<div class="controls">
											<input type="text" readonly class="span10 m-wrap" id="cjny" name="cjny" value="${cjny!}" maxlength="20" onclick="WdatePicker({el:'cjny',dateFmt:'yyyy-MM',readOnly:true})" />
										</div>
									</div>
								</div>
								<div class="span6">
									<div class="control-group">
										<label class="control-label"><b>入校年月:</b><span class="required">*</span></label>
										<div class="controls">
											<input type="text" class="span10 m-wrap" id="rxny" name="rxny" value="${rxny!}" readonly onclick="WdatePicker({el:'rxny',dateFmt:'yyyy-MM',readOnly:true})" maxlength="20" />
										</div>
									</div>
								</div>
							</div>
							<div class="row-fluid">
								<div class="span6">
									<div class="control-group">
										<label class="control-label"><b>人员类别:</b><span class="required">*</span></label>
										<div class="controls">
											<select name="rylb.id" class="m-wrap span10" id="rylxid"  onchange="sftjzrjsa()">
												<option value="">==请选择==</option>
												<#if renylxList?exists>
													<#list renylxList as obj>
														<option value="${obj.id}"   <#if rylb?exists && rylb.id?exists && rylb.id ==obj.id>selected</#if> >${obj.name}</option>
													</#list>
												</#if>
											</select>
										</div>
									</div>
								</div>
								<div class="span6">
									<div class="control-group">
										<label class="control-label"><b>职级:</b><span class="required">*</span></label>
										<div class="controls">
											<select name="zhij.id" class="m-wrap span10">
												<option value="">==请选择==</option>
												<#if zhijList?exists>
													<#list zhijList as obj>
														<option value="${obj.id}"  <#if zhij?exists && zhij.id?exists && zhij.id ==obj.id>selected</#if> >${obj.name}</option>
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
										<label class="control-label"><b>是否华侨:</b><span class="required">*</span></label>
										<div class="controls">
											<span class="text">
												<label class="radio">
													&nbsp;&nbsp;&nbsp;
													<input type="radio" name="sfhq" value="0"  <#if sfhq?exists && sfhq == '0'>checked<#else>checked</#if>>否
												</label>
												&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
												<label class="radio">
													<input type="radio" name="sfhq" value="1"  <#if sfhq?exists && sfhq == '1'>checked</#if>>是
												</label>
											</span>
										</div>
									</div>
								</div>
								<div class="span6" id="bxnsfsk">
									<div class="control-group">
										<label class="control-label"><b>本学年是否授课:</b><span class="required">*</span></label>
										<div class="controls">
											<span class="text">
												<label class="radio">
													&nbsp;&nbsp;&nbsp;
													<input type="radio" name="bxnsfsk" value="0"  <#if bxnsfsk?exists && bxnsfsk == '0'>checked<#else>checked</#if>>否
												</label>
												&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
												<label class="radio">
													<input type="radio" name="bxnsfsk" value="1"  <#if bxnsfsk?exists && bxnsfsk == '1'>checked</#if>>是
												</label>
											</span>
										</div>
									</div>
								</div>
							</div>
							<div class="row-fluid">
								<div class="span6">
									<div class="control-group">
										<label class="control-label"><b>学历:</b><span class="required">*</span></label>
										<div class="controls">
											<select name="xuel.id" class="m-wrap span10">
												<option value="">==请选择==</option>
												<#if xuelList?exists>
													<#list xuelList as obj>
														<option value="${obj.id}"  <#if xuel?exists && xuel.id?exists && xuel.id ==obj.id>selected</#if> >${obj.name}</option>
													</#list>
												</#if>
											</select>
										</div>
									</div>
								</div>
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
							</div><div class="row-fluid">
								<div class="span6">
									<div class="control-group">
										<label class="control-label"><b>学位:</b><span class="required">*</span></label>
										<div class="controls">
											<select name="xuew.id" class="m-wrap span10">
												<option value="">==请选择==</option>
												<#if xuewList?exists>
													<#list xuewList as obj>
														<option value="${obj.id}"  <#if xuew?exists && xuew.id?exists && xuew.id ==obj.id>selected</#if> >${obj.name}</option>
													</#list>
												</#if>
											</select>
										</div>
									</div>
								</div>
								<div class="span6">
									<div class="control-group">
										<label class="control-label"><b>当前状态:</b><span class="required">*</span></label>
										<div class="controls">
											<select name="dqzt.id" class="m-wrap span10">
												<option value="">==请选择==</option>
												<#if dqztList?exists>
													<#list dqztList as obj>
														<option value="${obj.id}"  <#if dqzt?exists && dqzt.id?exists && dqzt.id ==obj.id>selected</#if> >${obj.name}</option>
													</#list>
												</#if>
											</select>
										</div>
									</div>
								</div>
							</div>
							<div class="row-fluid" >
								<div class="span6" >
									<div class="control-group" >
										<label class="control-label"><b>任教课程:</b></label>
										<div class="controls">
											<select name="ywjyrjkc.id" class="m-wrap span10" id="rjkc" onchange="xxkmeji()">
												<option value="">==请选择==</option>
												<#if ywjyList?exists>
													<#list ywjyList as obj>
														<option value="${obj.id}"  <#if ywjyrjkc?exists && ywjyrjkc.id?exists && ywjyrjkc.id ==obj.id>selected</#if> >${obj.name}</option>
													</#list>
												</#if>
											</select>
										</div>
									</div>
								</div>
								<div class="span6" id="xxkmdiv">
									<div class="control-group" >
										<label class="control-label"><b>课程类别:</b></label>
										<div class="controls">
											<select name="xxkm" class="m-wrap span10" id="xxkmid">

											</select>
										</div>
									</div>
								</div>
							</div>
							<div class="row-fluid">
								<div class="span6">
									<div class="control-group">
										<label class="control-label"><b>是否在编:</b><span class="required">*</span></label>
										<div class="controls">
											<span class="text">
												<label class="radio">
													&nbsp;&nbsp;&nbsp;
													<input type="radio" name="sfzb" value="1"  <#if sfzb?exists && sfzb == '1'>checked<#else>checked</#if>>是
												</label>
												&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
												<label class="radio">
													<input type="radio" name="sfzb" value="0"  <#if sfzb?exists && sfzb == '0'>checked</#if>>否
												</label>
											</span>
										</div>
									</div>
								</div>
								<div class="span6">
									<div class="control-group">
										<label class="control-label"><b>是否接受过专业教育:</b><span class="required">*</span></label>
										<div class="controls">
											<span class="text">
												<label class="radio">
													&nbsp;&nbsp;&nbsp;
													<input type="radio" name="sfjsgzyjy" value="0"  <#if sfjsgzyjy?exists && sfjsgzyjy == '0'>checked<#else>checked</#if>>否
												</label>
												&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
												<label class="radio">
													<input type="radio" name="sfjsgzyjy" value="1"  <#if sfjsgzyjy?exists && sfjsgzyjy == '1'>checked</#if>>是
												</label>
											</span>
										</div>
									</div>
								</div>
							</div>
							<div class="row-fluid">
								<div class="span6">
									<div class="control-group">
										<label class="control-label"><b>是否心里健康教育教师:</b><span class="required">*</span></label>
										<div class="controls">
											<span class="text">
												<label class="radio">
													&nbsp;&nbsp;&nbsp;
													<input type="radio" name="sfxlzkjs" value="0"  <#if sfxlzkjs?exists && sfxlzkjs == '0'>checked<#else>checked</#if>>否
												</label>
												&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
												<label class="radio">
													<input type="radio" name="sfxlzkjs" value="1"  <#if sfxlzkjs?exists && sfxlzkjs == '1'>checked</#if>>是
												</label>
											</span>
										</div>
									</div>
								</div>
								<div class="span6">
									<div class="control-group">
										<label class="control-label"><b>是否双师:</b><span class="required">*</span></label>
										<div class="controls">
											<span class="text">
												<label class="radio">
													&nbsp;&nbsp;&nbsp;
													<input type="radio" name="sfss" value="0"  <#if sfss?exists && sfss == '0'>checked<#else>checked</#if>>否

												</label>
												&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
												<label class="radio">
													<input type="radio" name="sfss" value="1"  <#if sfss?exists && sfss == '1'>checked</#if>>是
												</label>
											</span>
										</div>
									</div>
								</div>
							</div>
							<div class="row-fluid">
								<div class="span6">
									<div class="control-group">
										<label class="control-label"><b>是否新进:</b><span class="required">*</span></label>
										<div class="controls">
											<span class="text">
												<label class="radio">
													&nbsp;&nbsp;&nbsp;
													<input type="radio" name="sfxj" value="0" id="xjN" <#if sfxj?exists && sfxj == '0'>checked<#else>checked</#if>>否
												</label>
												&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
												<label class="radio">
													<input type="radio" name="sfxj" value="1" id="xjY"  <#if sfxj?exists && sfxj == '1'>checked</#if>>是
												</label>
											</span>
										</div>
									</div>
								</div>
								<div class="span6">
									<div class="control-group" id="xjlxxz">
										<label class="control-label"><b>新进类型:</b><span class="required">*</span></label>
										<div class="controls">
											<select name="xjlx.id" class="m-wrap span10"  id="blxzxjlx" onchange="chageblxzxjlx()">
												<option value="">==请选择==</option>
												<#if xjlxList?exists>
													<#list xjlxList as obj>
														<option value="${obj.id}"  <#if xjlx?exists && xjlx.id?exists && xjlx.id ==obj.id>selected</#if> >${obj.name}</option>
													</#list>
												</#if>
											</select>
										</div>
									</div>
								</div>
							</div>
							<div class="row-fluid">
								<div class="span6" id="sfyjbys">
									<div class="control-group" >
										<label class="control-label"><b>是否应届毕业生:</b><span class="required">*</span></label>
										<div class="controls">
											<span class="text">
												<label class="radio">
													&nbsp;&nbsp;&nbsp;
													<input type="radio" name="sfyjbys" value="0"  <#if sfyjbys?exists && sfyjbys == '0'>checked<#else>checked</#if>>否
												</label>
												&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
												<label class="radio">
													<input type="radio" name="sfyjbys" value="1"  <#if sfyjbys?exists && sfyjbys == '1'>checked</#if>>是
												</label>
											</span>
										</div>
									</div>
								</div>
								<div class="span6" id="sfsfs">
									<div class="control-group" >
										<label class="control-label"><b>是否师范生:</b><span class="required">*</span></label>
										<div class="controls">
											<span class="text">
												<label class="radio">
													&nbsp;&nbsp;&nbsp;
													<input type="radio" name="sfsfs" value="0"  <#if sfsfs?exists && sfsfs == '0'>checked<#else>checked</#if>>否
												</label>
												&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
												<label class="radio">
													<input type="radio" name="sfsfs" value="1"  <#if sfsfs?exists && sfsfs == '1'>checked</#if>>是
												</label>
											</span>
										</div>
									</div>
								</div>
							</div>
							<div class="row-fluid">
								<div class="span6" id="sfxdtz">
									<div class="control-group">
										<label class="control-label"><b>是否学段调整:</b><span class="required">*</span></label>
										<div class="controls">
											<span class="text">
												<label class="radio">
													&nbsp;&nbsp;&nbsp;
													<input type="radio" name="sfxdtz" value="0" id="xdN" <#if sfxdtz?exists && sfxdtz == '0'>checked<#else>checked</#if>>否
												</label>
												&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
												<label class="radio">
													<input type="radio" name="sfxdtz" value="1" id="xdY" <#if sfxdtz?exists && sfxdtz == '1'>checked</#if>>是
												</label>
											</span>
										</div>
									</div>
								</div>
								<div class="span6" id="yxd">
									<div class="control-group">
										<label class="control-label"><b>原学段:</b></label>
										<div class="controls">
											<select name="yxd.id" class="m-wrap span10">
												<option value="">==请选择==</option>
												<#if xuedList?exists>
													<#list xuedList as obj>
														<option value="${obj.id}"  <#if yxd?exists && yxd.id?exists && yxd.id ==obj.id>selected</#if> >${obj.name}</option>
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
										<label class="control-label"><b>备注:</b></label>
										<div class="controls">
											<input type="text" class="span10 m-wrap" id="bz" name="bz" value="${bz!}" maxlength="100" />
										</div>
									</div>
								</div>
								<div class="span6" id="sfwxdr">
									<div class="control-group" >
										<label class="control-label"><b>是否外校调入:</b></label>
										<div class="controls">
											<span class="text">
												<label class="radio">
													&nbsp;&nbsp;&nbsp;
													<input type="radio" name="sfwxdr" value="0"  <#if sfwxdr?exists && sfwxdr == '0'>checked<#else>checked</#if>>否
												</label>
												&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
												<label class="radio">
													<input type="radio" name="sfwxdr" value="1"  <#if sfwxdr?exists && sfwxdr == '1'>checked</#if>>是
												</label>
											</span>
										</div>
									</div>
								</div>
								<div class="span6" id="xxd">
									<div class="control-group" >
										<label class="control-label"><b>现学段:</b><span class="required">*</span></label>
										<div class="controls">
											<select name="xxd.id" class="m-wrap span10" id="xzxxd">
												<option value="">==请选择==</option>
												<#if xuedList?exists>
													<#list xuedList as obj>
														<option value="${obj.id}"  <#if xxd?exists && xxd.id?exists && xxd.id ==obj.id>selected</#if> >${obj.name}</option>
													</#list>
												</#if>
											</select>
										</div>
									</div>
								</div>
							</div>
							<div class="row-fluid">
								<div class="span12">
									<div class="form-actions">
										<center>
											<button class="btn green big" type="button" onclick="baoc()" id="commit" name="commit" >
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
							var zt  = $("input[name=sfsk]:checked").val();
							//页面加载完成立即加载这段代码
							$(document).ready(function() {
								//关闭所有触发显示框
								guanb()
								//回显
								chageblxzxjlx();
								//是否特教专任教师
								sftjzrjsa();
								//单选：任教课程,详细科目
								 $("#xxkmdiv").hide();
									xxkmeji();
							});
							function sftjzrjsa(){
								var xzry =  $('#rylxid option:selected').val();
								if (xzry != null && xzry !='' && xzry != 'undefined'){
									//人员类别的专任教师id
									if (xzry == '8a808088860bbfd501860bd0ffc20003'){
										$("#sftjzrjs").show();
										//本学年是否授课
										$("#bxnsfsk").show();
									}else{
										$("#sftjzrjs").hide();
										$("#bxnsfsk").hide();
									}
								}else{
									$("#sftjzrjs").hide();
									$("#bxnsfsk").hide();
								}
							}
							//单选：任教课程,二级标签
							function xxkmeji(){
								var tmpval = $('#rjkc option:selected').text();//选中的文本
								var xxkmSelect = document.getElementById("xxkmid");
									// 获取当前任教课程的值
									if (tmpval === "艺术" || tmpval === "外语" || tmpval === "技术"){
										$("#xxkmdiv").show();
										//外语
										if (tmpval == "外语") {
											xxkmSelect.innerHTML = "<option value=''>请选择外语科目</option><option  value='1' <#if xxkm?exists && xxkm== '1'>selected</#if>  >英语</option><option value='2'   <#if xxkm?exists && xxkm== '2'>selected</#if> >日语</option><option value='3' <#if xxkm?exists && xxkm== '3'>selected</#if> >俄语</option>";
										} else if (tmpval === "艺术") {
											xxkmSelect.innerHTML = "<option value=''>请选择艺术科目</option><option value='4' <#if xxkm?exists && xxkm== '4'>selected</#if> >音乐</option><option value='5' <#if xxkm?exists && xxkm== '5'>selected</#if> >美术</option>";
										}else if (tmpval === "技术") {
											xxkmSelect.innerHTML = "<option value=''>请选择技术科目</option><option value='5' <#if xxkm?exists && xxkm== '5'>selected</#if> >信息技术</option><option value='6' <#if xxkm?exists && xxkm== '6'>selected</#if> >通用技术</option>";
										}
									}else{
										xxkmSelect.innerHTML = "<option value='' selected></option>"
										$("#xxkmdiv").hide();
									}
							}
							function chageblxzxjlx(){
								//获取当前选中值的id
								var xzxj =  $('#blxzxjlx option:selected').val();
								if(xzxj != null && xzxj!= ""){
									//招聘的id
									if(xzxj.indexOf("8a808088860bbfd501860bfe49480016") != -1){
										//是否学段调整
										$("#sfyjbys").show();
										$("#sfsfs").show();
										$("#sfxdtz").hide();
										$("#yxd").hide();
										$("#xxd").hide();
										$("#sfwxdr").hide();
									}
									//校内调整id
									if(xzxj.indexOf("8a808088860bbfd501860bfe0cdd0015") != -1){
										$("#sfxdtz").show();
										$("#sfyjbys").hide();
										$("#sfsfs").hide();
										$("#sfwxdr").hide();
										var sfxdtz  = $("input[name=sfxdtz]:checked").val();
										if (sfxdtz == '1'){
											$("#xxd").show();
											$("#yxd").show();

										}
									}
									//调入的id
									var xzxj =  $('#blxzxjlx option:selected').val();
									if(xzxj != null && xzxj!= ""){
										//调入的id
										if(xzxj.indexOf("8a8080a4866d906001866d9224f60001") != -1){
											//是否学段调整
											$("#sfyjbys").hide();
											$("#sfsfs").hide();
											$("#sfxdtz").hide();
											$("#yxd").hide();
											$("#xxd").hide();
											$("#xxd").hide();
											$("#sfwxdr").show();

										}
									}
								}else{
									guanb()
								}
							}
							$(document).ready(function() {
								$("#save_inputForm").validate({
									rules: {
										<!-- 在这里编写验证规则 -->
										"ssxq.id":{required:true},
										       xm:{required:true},
										      jgh:{required:true},
										     rxny:{required:true},
										    "zzmm.id":{required:true},
										     "mz.id":{required:true},
										     cjny:{required:true},
											 csny:{required:true},
										     sjnf:{required:true},
											"rylb.id":{required:true},
											"zhij.id":{required:true},
											"xuel.id":{required:true},
											"xuew.id":{required:true},
											sfhq:{required:true},
											sfzb:{required:true},
											xued:{required:true},
											"jig.id":{required:true},
										    "dqzt.id":{required:true},
									}
								});
								<#if ssxq?exists>
								var option = new Option('${ssxq.xqmc!}','${ssxq.id!}', true, true);
								$("#ssxqstr").append(option).trigger("change");
								</#if>
								<!--新进类型开始-->
								var zt  = $("input[name=sfxj]:checked").val();
								if (zt == '0'){
									$("#xjlxxz").hide();
								}
								$("#xjN").click(function(){
									$("#xjlxxz").hide();
									$("#xxd").hide();
									$("#yxd").hide();
									$("#sfsfs").hide();
									$("#sfxdtz").hide();
									$("#sfyjbys").hide();
								});
								$("#xjY").click(function(){
									$("#xjlxxz").show();
								});
								<!--新进类型结束-->
								<!--学段开始-->
								var sfxdtz  = $("input[name=sfxdtz]:checked").val();
								if (sfxdtz == '0'){
									$("#xxd").hide();
								}
								$("#xdN").click(function(){
									$("#xxd").hide();
									$("#yxd").hide();
								});
								$("#xdY").click(function(){
									$("#xxd").show();
									$("#yxd").show();
								});
								<!--学段结束-->
								<!--学校办学类型开始-->
								  var  xxlxdm = $("#xxblxlx").val();
								if (xxlxdm != null || xxlxdm != "" || xxlxdm != undefined){
									//职高
									if (xxlxdm.indexOf("090zygzxx") >=0){
										$("#zyjyid").show();
										$("#gzjyid").hide();
										$("#ywjyid").hide();
									}
									//义务
									if (xxlxdm.indexOf("02xiaox") >=0  || xxlxdm.indexOf("030chujzx") >=0|| xxlxdm.indexOf("040jnygz") >=0){
										$("#zyjyid").hide();
										$("#gzjyid").hide();
										$("#ywjyid").show();
									}
									//高中
									if (xxlxdm.indexOf("051gaojzx") >=0){
										$("#zyjyid").hide();
										$("#gzjyid").show();
										$("#ywjyid").hide();
									}
								}else {
									$("#zyjyid").hide();
									$("#gzjyid").hide();
									$("#ywjyid").hide();
								}
								<!--学校办学类型结束-->
							});
							$('select[id=ssxqstr]').select2({
								minimumInputLength:1,
								ajax: {
									url:  "${base}/ywmk/xxxq!getdictjson.action",
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
												text : o.xqmc+"("+o.xqdm+")"
											});
										});
										return {
											results: options
										};
									},
									cache: true
								}
							});
							function baoc(){
								var xzxj =  $("input[name=sfxj]:checked").val();
								//是否学段调整
								var sfxdtz =  $("input[name=sfxdtz]:checked").val();
								if(xzxj.indexOf("1") != -1 || sfxdtz.indexOf("1")!=-1){
									if (xzxj != '0'){
										var xzxj =  $('#blxzxjlx option:selected').val();
										if(xzxj==null || xzxj=="" || typeof(xzxj)=='undefined'){
											layer.msg("请选择新进类型");
											$("#blxzxjlx").focus();
											return;
										}
									}
									if (sfxdtz != '0'){
										var xzxxd =  $('#xzxxd option:selected').val();
										if(xzxxd==null || xzxxd=="" || typeof(xzxxd)=='undefined'){
											layer.msg("请选择"+"现学段"+"");
											$("#xzxxd").focus();
											return;
										}
									}
									$("#save_inputForm").submit();
								}else{
									$("#save_inputForm").submit();
								}
							}
							function guanb(){
								//隐藏义务,高中,职教
								$("#zyjyid").hide();
								$("#gzjyid").hide();
								$("#ywjyid").hide();
								//招聘
								$("#sfxdtz").hide();
								$("#yxd").hide();
								$("#xxd").hide();
								//校内调整
								$("#sfyjbys").hide();
								$("#sfsfs").hide();
								//调入
								$("#sfwxdr").hide();
							}
							<#if rjkcSet?exists>
								<#list rjkcSet as rjkc>
								var option = new Option('${rjkc.name!}','${rjkc.id!}', true, true);
								$("#rjkc").append(option).trigger("change");
								</#list>
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