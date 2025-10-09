<#include '/macro/crud-metro-page3-nowebpst.ftl' >

<#if id?exists>
	<#assign extPosition='修改学校校区'/>
<#else>
	<#assign extPosition='创建学校校区'/>
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
						<form id="save_inputForm" class="form-horizontal" action="${base}/ywmk/xxxq!save.action" method="post">
							<@s.token />
							<#if id?exists>
								<input type="hidden" name="id" value="${id}"/>
							</#if>
							<!-- 在这里编写输入的元素 -->
							<div class="control-group">
								<label class="control-label"><b>所属学校:</b><span class="required">*</span></label>
								<div class="controls">
									<select name="ssxx.id" class="m-wrap span10" id="ssxx">
										<#if xxxxList?exists>
											<#list xxxxList as obj>
												<option value="${obj.id}"  <#if ssxx?exists && ssxx.id?exists && ssxx.id ==obj.id>selected</#if> >${obj.xxmc}</option>
											</#list>
										</#if>
									</select>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label"><b>校区代码:</b></label>
								<div class="controls">
									<input type="text" class="span10 m-wrap" id="xqdm" name="xqdm" value="${xqdm!}" maxlength="50" />
								</div>
							</div>
							<div class="control-group">
								<label class="control-label"><b>校区名称:</b><span class="required">*</span></label>
								<div class="controls">
									<input type="text" class="span10 m-wrap" id="xqmc" name="xqmc" value="${xqmc!}" maxlength="100" />
								</div>
							</div>
							<div class="control-group">
								<label class="control-label"><b>校区地址:</b><span class="required">*</span></label>
								<div class="controls">
									<input type="text" class="span10 m-wrap" id="xqdz" name="xqdz" value="${xqdz!}" maxlength="200" />
								</div>
							</div>
							<div class="control-group">
								<label class="control-label"><b>占地面积(平方米):</b><span class="required"></span></label>
								<div class="controls">
									<input type="text" class="span10 m-wrap" id="zdmj" name="zdmj" value="${zdmj!}" maxlength="30" />
								</div>
							</div>
							<div class="control-group">
								<label class="control-label"><b>绿化用地面积(平方米):</b><span class="required"></span></label>
								<div class="controls">
									<input type="text" class="span10 m-wrap" id="lhydmj" name="lhydmj" value="${lhydmj!}" maxlength="30" />
								</div>
							</div>
							<div class="control-group">
								<label class="control-label"><b>运动场地面积(室外游戏场地平方米):</b><span class="required"></span></label>
								<div class="controls">
									<input type="text" class="span10 m-wrap" id="swyxcd" name="swyxcd" value="${swyxcd!}" maxlength="30" />
								</div>
							</div>
							<div class="control-group">
								<label class="control-label"><b>11人制足球场(个):</b><span class="required"></span></label>
								<div class="controls">
									<input type="text" class="span10 m-wrap" id="syrzqc" name="syrzqc" value="${syrzqc!}" maxlength="30" />
								</div>
							</div>
							<div class="control-group">
								<label class="control-label"><b>7人制足球场(个):</b><span class="required"></span></label>
								<div class="controls">
									<input type="text" class="span10 m-wrap" id="qirzqc" name="qirzqc" value="${qirzqc!}" maxlength="30" />
								</div>
							</div>
							<div class="control-group">
								<label class="control-label"><b>5人制足球场(个):</b><span class="required"></span></label>
								<div class="controls">
									<input type="text" class="span10 m-wrap" id="wurzqc" name="wurzqc" value="${wurzqc!}" maxlength="30" />
								</div>
							</div>
							
							<div class="control-group">
								<label class="control-label"><b>数字终端数(台):</b><span class="required"></span></label>
								<div class="controls">
									<input type="text" class="span10 m-wrap" id="szzds" name="szzds" value="${szzds!}" maxlength="30" />
								</div>
							</div>
							<div class="control-group">
								<label class="control-label"><b>教师终端数(台):</b><span class="required"></span></label>
								<div class="controls">
									<input type="text" class="span10 m-wrap" id="jszds" name="jszds" value="${jszds!}" maxlength="30" />
								</div>
							</div>
							<div class="control-group">
								<label class="control-label"><b>学生终端数(台):</b><span class="required"></span></label>
								<div class="controls">
									<input type="text" class="span10 m-wrap" id="xszds" name="xszds" value="${xszds!}" maxlength="30" />
								</div>
							</div>
							
							<div class="control-group">
								<label class="control-label"><b>教室(间):</b><span class="required"></span></label>
								<div class="controls">
									<input type="text" class="span10 m-wrap" id="jssl" name="jssl" value="${jssl!}" maxlength="30" />
								</div>
							</div>
							
							<div class="control-group">
								<label class="control-label"><b>网络多媒体教室(间):</b><span class="required"></span></label>
								<div class="controls">
									<input type="text" class="span10 m-wrap" id="wldmtjssl" name="wldmtjssl" value="${wldmtjssl!}" maxlength="30" />
								</div>
							</div>
							
							
							
							<div class="control-group">
								<label class="control-label"><b>图书(册):</b><span class="required"></span></label>
								<div class="controls">
									<input type="text" class="span10 m-wrap" id="tus" name="tus" value="${tus!}" maxlength="30" />
								</div>
							</div>
							<div class="control-group">
								<label class="control-label"><b>固定资产总值(万元):</b><span class="required"></span></label>
								<div class="controls">
									<input type="text" class="span10 m-wrap" id="gdzczz" name="gdzczz" value="${gdzczz!}" maxlength="30" />
								</div>
							</div>
							
							<div class="control-group">
								<label class="control-label"><b>教学仪器设备资产值(万元):</b><span class="required"></span></label>
								<div class="controls">
									<input type="text" class="span10 m-wrap" id="jxyqsbzcz" name="jxyqsbzcz" value="${jxyqsbzcz!}" maxlength="30" />
								</div>
							</div>
							
							
							
							<div class="control-group">
								<label class="control-label"><b>玩教具资产值(万元):</b><span class="required"></span></label>
								<div class="controls">
									<input type="text" class="span10 m-wrap" id="wjjzczz" name="wjjzczz" value="${wjjzczz!}" maxlength="30" />
								</div>
							</div>

							<div class="control-group">
								<label class="control-label"><b>是否有效:</b><span class="required"></span></label>
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
										"ssxx.id":{required: true},
										zdmj:{required: true},
										lhydmj:{required: true},
										swyxcd:{required: true},
										tus:{required: true},
										gdzczz:{required: true},
										wjjzczz:{required: true}
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