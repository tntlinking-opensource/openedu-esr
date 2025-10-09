<#include '/macro/crud-metro-page3-nowebpst.ftl' >

<#if id?exists>
	<#assign extPosition='修改教师授课分类情况'/>
<#else>
	<#assign extPosition='创建教师授课分类情况'/>
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
						<form id="save_inputForm" class="form-horizontal" action="${base}/ywmk/jsskflqk!save.action" method="post">
							<@s.token />
							<#if id?exists>
								<input type="hidden" name="id" value="${id}"/>
							</#if>
							<!-- 在这里编写输入的元素 -->
							<div class="control-group">
								<label class="control-label"><b>数据年份:</b><span class="required">*</span></label>
								<div class="controls">
									<input type="text" class="span10 m-wrap" readonly id="sjnf" name="sjnf" value="${sjnf!}" onclick="WdatePicker({el:'sjnf',dateFmt:'yyyy',readOnly:true})" maxlength="10" />
								</div>
							</div>
							<div class="control-group">
								<label class="control-label"><b>教工号:</b><span class="required">*</span></label>
								<div class="controls">
									<input type="text" class="span10 m-wrap" id="jgh" name="jgh" value="${jgh!}" maxlength="50" />
								</div>
							</div>
							<div class="control-group">
								<label class="control-label"><b>姓名:</b><span class="required">*</span></label>
								<div class="controls">
									<input type="text" class="span10 m-wrap" id="xm" name="xm" value="${xm!}" maxlength="50" />
								</div>
							</div>
							<div class="control-group">
								<label class="control-label"><b>是否授课:</b></label>
								<div class="controls">
									<span class="text">
										<label class="radio">
											&nbsp;&nbsp;&nbsp;
											<input type="radio" name="sfsk" value="1" id="sfskY" <#if sfsk?exists && sfsk == '1'>checked<#else>checked</#if>>是
										</label>
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<label class="radio">
											<input type="radio" name="sfsk" value="0" id="sfskN" <#if sfsk?exists && sfsk == '0'>checked</#if>>否
										</label>
									</span>
								</div>
							</div>
							<div class="control-group" id="sklbid">
								<label class="control-label"><b>授课类别:</b><span class="required">*</span></label>
								<div class="controls" onchange="szk()">
									<select name="sklb.id" id="sklx" >
										<option value="">==请选择==</option>
										<#if sklbList?exists>
											<#list sklbList as obj>
												<option  value="${obj.id}" <#if sklb?exists && sklb.id?exists && sklb.id == obj.id>selected</#if> >${obj.name!}</option>
											</#list>
										</#if>
									</select>
								</div>
							</div>
							<div class="control-group" id="sfszkid">
								<label class="control-label"><b>是否思政课:</b></label>
								<div class="controls">
									<span class="text">
										<label class="radio">
											&nbsp;&nbsp;&nbsp;
											<input type="radio" name="sfszk" value="1"   <#if sfszk?exists && sfszk == '1'>checked</#if>>是
										</label>
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<label class="radio">
											<input type="radio" name="sfszk" value="0"  <#if sfszk?exists && sfszk == '0'>checked</#if>>否
										</label>
									</span>
								</div>
							</div>
							<div class="control-group" id="bskyy">
								<label class="control-label"><b>不授课原因:</b><span class="required">*</span></label>
								<div class="controls">
									<span class="text">
										&nbsp;&nbsp;&nbsp;&nbsp;
										<label class="radio">
											<input type="radio" name="bskyy"   value="进修" <#if bskyy?exists & bskyy=='进修'>checked</#if> >进修
										</label>
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<label class="radio">
											<input type="radio" name="bskyy"  value="病休" <#if bskyy?exists & bskyy=='病休'>checked</#if>>病休
										</label>
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<label class="radio">
											<input type="radio" name="bskyy"  value="其他" <#if bskyy?exists & bskyy=='其他'>checked</#if>>其他
										</label>
									</span>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label"><b>备注:</b></label>
								<div class="controls">
									<input type="text" class="span10 m-wrap" id="bz" name="bz" value="${bz!}" maxlength="50" />
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
							var zt  = $("input[name=sfsk]:checked").val();
							$(document).ready(function() {
								$("#save_inputForm").validate({
									ignore: ":hidden",
									rules: {
										<!-- 在这里编写验证规则 -->
										<#if zt?exists && zt == '0'> bskyy:{required:true},</#if>
										jgh:{required:true},
										sfszk:{required:true},
										bskyy:{required:true},
										xm:{required:true},
										sjnf:{required:true},
										"sklb.id":{required:true},
									}
								});
								if (zt == '1'){
									<!--授课点击修改情况-->
									$("#bskyy").hide();
									$("#sfskY").click(function(){
										$("#sklbid").show();
										//$("#sfszkid").show();
										$("#bskyy").hide();
									});
									$("#sfskN").click(function (){
										$("#sklbid").hide();
										$("#sfszkid").hide();
										$("#bskyy").show();
									})
								}else{
									<!--不授课点击修改情况-->
									$("#sklbid").hide();
									$("#sfszkid").hide();
									$("#bskyy").show();

									$("#sfskY").click(function(){
										$("#sklbid").show();
										//$("#sfszkid").show();
										$("#bskyy").hide();
									});
									$("#sfskN").click(function (){
										$("#sklbid").hide();
										$("#sfszkid").hide();
										$("#bskyy").show();
									})
								}
								szk()
							});
							function szk(){
								var sklx =  $('#sklx option:selected').val();
								if (sklx =='8a808088860add6301860ae19e320002'){
									$("#sfszkid").show();
								}else{
									$("#sfszkid").hide();
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