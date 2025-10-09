<#include '/macro/crud-metro-page3-nowebpst.ftl' >

<#if id?exists>
	<#assign extPosition='修改校舍信息'/>
<#else>
	<#assign extPosition='创建校舍信息'/>
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
						<form id="save_inputForm" class="form-horizontal" action="${base}/ywmk/xiaoshexx!save.action" method="post">
							<@s.token />
							<#if id?exists>
								<input type="hidden" name="id" value="${id}"/>
							</#if>
							<!-- 在这里编写输入的元素 -->
							<div class="control-group">
								<label class="control-label"><b>数据年份:</b><span class="required">*</span></label>
								<div class="controls">
									<input type="text" class="span10 m-wrap" id="sjnf" name="sjnf" value="${sjnf!}" readonly onclick="WdatePicker({el:'sjnf',dateFmt:'yyyy',readOnly:true})" maxlength="10" />
								</div>
							</div>
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
							<div class="row-fluid" id="syfxdiv">
								<div class="span3">
									<div class="control-group" id="yjsffx">
										<label class="control-label"><b>使用方向:</b><span class="required">*</span></label>
										<div class="controls">
											<select name="syfx.id" class="m-wrap span12" id="syfx1" onchange="xzxqe()">
												<option value="" index="0">==请选择==</option>
											</select>
										</div>
									</div>
								</div>
								<div class="span3">
									<div class="control-group" id="yjsffx">
										<div class="controls">
											<select name="syfxtwo.id" class="m-wrap span12" id="syfx2" onchange="xzxqsan()">
												<option value=""  index="0">==请选择==</option>
											</select>
										</div>
									</div>
								</div>
								<div class="span3">
									<div class="control-group" id="yjsffx">
										<div class="controls">
											<select name="syfxthree.id" class="m-wrap span12"  id="syfx3" >
												<option value=""  index="0">==请选择==</option>
											</select>
										</div>
									</div>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label"><b>校舍名称:</b><span class="required">*</span></label>
								<div class="controls">
									<input type="text" class="span10 m-wrap" id="xsmc" name="xsmc" value="${xsmc!}" maxlength="50" />
								</div>
							</div>
							<div class="control-group">
								<label class="control-label"><b>校舍面积:</b></label>
								<div class="controls">
									<input type="text" class="span10 m-wrap" id="xsmj" name="xsmj" value="${xsmj!}" maxlength="10" />
								</div>
							</div>
							<div class="control-group">
								<label class="control-label"><b>是否c#危房:</b><span class="required">*</span></label>
								<div class="controls">
									<span class="text">
										<label class="radio">
											&nbsp;&nbsp;&nbsp;
											<input type="radio" name="sfcjwf" value="0"  <#if sfcjwf?exists && sfcjwf == '0'>checked<#else>checked</#if>>否
										</label>
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<label class="radio">
											<input type="radio" name="sfcjwf" value="1"  <#if sfcjwf?exists && sfcjwf == '1'>checked</#if>>是
										</label>
									</span>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label"><b>是否d#危房:</b><span class="required">*</span></label>
								<div class="controls">
									<span class="text">
										<label class="radio">
											&nbsp;&nbsp;&nbsp;
											<input type="radio" name="sfdjwf" value="0"  <#if sfdjwf?exists && sfdjwf == '0'>checked<#else>checked</#if>>否
										</label>
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<label class="radio">
											<input type="radio" name="sfdjwf" value="1"  <#if sfdjwf?exists && sfdjwf == '1'>checked</#if>>是
										</label>
									</span>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label"><b>是否租用外单位:</b><span class="required">*</span></label>
								<div class="controls">
									<span class="text">
										<label class="radio">
											&nbsp;&nbsp;&nbsp;
											<input type="radio" name="sfzywdw" value="0"  <#if sfzywdw?exists && sfzywdw == '0'>checked<#else>checked</#if>>否
										</label>
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<label class="radio">
											<input type="radio" name="sfzywdw" value="1"  <#if sfzywdw?exists && sfzywdw == '1'>checked</#if>>是
										</label>
									</span>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label"><b>是否被外单位租(借)用:</b><span class="required">*</span></label>
								<div class="controls">
									<span class="text">
										<label class="radio">
											&nbsp;&nbsp;&nbsp;
											<input type="radio" name="sfbwdwzjy" value="0"  <#if sfbwdwzjy?exists && sfbwdwzjy == '0'>checked<#else>checked</#if>>否
										</label>
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<label class="radio">
											<input type="radio" name="sfbwdwzjy" value="1"  <#if sfbwdwzjy?exists && sfbwdwzjy == '1'>checked</#if>>是
										</label>
									</span>
								</div>
							</div>
							<div class="control-group" id="sylxid">
								<label class="control-label"><b>使用类型:</b><span class="required">*</span></label>
								<div class="controls">
									<span class="text">
										<label class="radio">
											&nbsp;&nbsp;&nbsp;
											<input type="radio" name="zjsylx" value="1"  <#if zjsylx?exists && zjsylx == '1'>checked<#else>checked</#if>>独立使用
										</label>
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<label class="radio">
											<input type="radio" name="zjsylx" value="0"  <#if zjsylx?exists && zjsylx == '0'>checked</#if>>共同使用
										</label>
									</span>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label"><b>当前状态:</b></label>
								<div class="controls">
									<select name="dqzt.id">
										<option value="">==请选择==</option>
										<#list dqztList as obj>
											<option value="${obj.id}"  <#if dqzt?exists && dqzt.id?exists && dqzt.id ==obj.id>selected</#if> >${obj.name}</option>
										</#list>
									</select>
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
										sjnf :{required:true},
										"ssxq.id" :{required:true},
										xsmc :{required:true},
										xsmc :{required:true},
									}
								});
								$("#sylxid").hide();
								$("#syfxdiv").hide();
								xzxq();
								xzxqe();
								xzxqsan();
							});
							//一级
							function xzxq(){
								var xzxj =  $('#ssxq option:selected').val();
								$.ajax({
									url: "${base}/ywmk/xxxq!getxxlxjson.action",
									async: false,
									type: "post",
									data: {
										xxxqid: xzxj,
									},
									success: function (data) {
										if (data != null) {
											//特教
											if (data.indexOf("13qttjxx") >= 0 || data.indexOf("20pzxx") >= 0) {
												$("#syfxdiv").show();
												$.ajax({
													url: "${base}/ywmk/xiaoshexx!getdictjson.action",
													async: false,
													type: "post",
													data: {
														lxid: "8a80808886340564018634220eaa0010",
													},
													success: function (data) {
														if (data != null && data != "undefined" && data != "") {
															let ent = JSON.parse(data);
															for (let i = 0; i < ent.length; i++) {
																let syfxid = '${syfx?if_exists.id!}';
																if (syfxid != null) {
																	if (syfxid == ent[i].id) {
																		var opt = $("<option id=" + ent[i].id + " value=" + ent[i].id + "  selected='true'  >" + ent[i].name + "</option>");
																	} else {
																		var opt = $("<option id=" + ent[i].id + " value=" + ent[i].id + " >" + ent[i].name + "</option>");
																	}
																} else {
																	var opt = $("<option id=" + ent[i].id + " value=" + ent[i].id + " >" + ent[i].name + "</option>");
																}
																$("#syfx1").append(opt);
																$("#syfx2 option[index!='0']").find('option').remove();
															}
														}
														$("#sylxid").hide();
													},
													cache: true
												});

											}
											//中小学，高级中学
											if (data.indexOf("02xiaox") >= 0 || data.indexOf("030chujzx") >= 0 || data.indexOf("040jnygz") >= 0 || data.indexOf("050wqzx") >= 0 || data.indexOf("052shienygz") >= 0 || data.indexOf("051gaojzx") >= 0) {
												$("#syfxdiv").show();
												var lxid = "8a8080888633eded018633fae5fe0001";
												$("#sylxid").hide();
												$.ajax({
													url: "${base}/ywmk/xiaoshexx!getdictjson.action",
													async: false,
													type: "post",
													data: {
														lxid: lxid,
													},
													success: function (data) {
														let ent = JSON.parse(data);
														if (data != null && data != "undefined" && data != "") {
															for (let i = 0; i < ent.length; i++) {
																let syfxid = '${syfx?if_exists.id!}';
																if (syfxid != null) {
																	if (syfxid == ent[i].id) {
																		var opt = $("<option id=" + ent[i].id + " value=" + ent[i].id + "  selected='true'  >" + ent[i].name + "</option>");
																	} else {
																		var opt = $("<option id=" + ent[i].id + " value=" + ent[i].id + " >" + ent[i].name + "</option>");
																	}
																} else {
																	var opt = $("<option id=" + ent[i].id + " value=" + ent[i].id + " >" + ent[i].name + "</option>");
																}
																$("#syfx1").append(opt);
																$("#syfx2 option[index!='0']").find('option').remove();
															}
														}
													},
													cache: true
												});
											}
											//幼儿园
											if (data.indexOf("01yey") >= 0 || data.indexOf("02fsyey") >= 0) {
												$("#syfxdiv").show();
												var lxid = "8a8080a4866e61c101866e69193a0001";
												$("#sylxid").hide();
												$.ajax({
													url: "${base}/ywmk/xiaoshexx!getdictjson.action",
													async: false,
													type: "post",
													data: {
														lxid: lxid,
													},
													success: function (data) {
														if (data != null && data != undefined && data != "") {
															let ent = JSON.parse(data);
															for (let i = 0; i < ent.length; i++) {
																let syfxid = '${syfx?if_exists.id!}';
																if (syfxid != null && syfxid != '' && syfxid != 'undefined') {
																	if (syfxid == ent[i].id) {
																		var opt = $("<option id=" + ent[i].id + " value=" + ent[i].id + "  selected='true'  >" + ent[i].name + "</option>");
																	} else {
																		var opt = $("<option id=" + ent[i].id + " value=" + ent[i].id + " >" + ent[i].name + "</option>");
																	}
																} else {
																	var opt = $("<option id=" + ent[i].id + " value=" + ent[i].id + " >" + ent[i].name + "</option>");
																}
																$("#syfx1").append(opt);
																$("#syfx2 option[index!='0']").find('option').remove();
																$("#syfx3 option[index!='0']").find('option').remove();
															}
														}
													},
													cache: true
												});
											}
											//职教
											if (data.indexOf("07tzhzdzyxx") >= 0) {
												$("#syfxdiv").show();
												var lxid = "8a80808886340564018634374a940028";
												$("#sylxid").show();
												$.ajax({
													url: "${base}/ywmk/xiaoshexx!getdictjson.action?syfxs=3",
													async: false,
													type: "post",
													data: {
														lxid: lxid,
													},
													success: function (data) {
														if (data != null && data != "undefined" && data != "") {
															 var ent = JSON.parse(data);
															for (let i = 0; i < ent.length; i++) {
																let syfxid = '${syfx?if_exists.id!}';
																if (syfxid != null) {
																	if (syfxid == ent[i].id) {
																		var opt = $("<option id=" + ent[i].id + " value=" + ent[i].id + "  selected='true'  >" + ent[i].name + "</option>");
																	} else {
																		var opt = $("<option id=" + ent[i].id + " value=" + ent[i].id + " >" + ent[i].name + "</option>");
																	}
																}
																$("#syfx1").append(opt);
																$("#syfx2 option[index!='0']").find('option').remove();
																$("#syfx3 option[index!='0']").find('option').remove();
															}
														}
													},
													cache: true
												});
											} else if (data.indexOf("08zdjsxx") >= 0) {
												$("#syfxdiv").show();
												var lxid = "8a80808886340564018634374a940028";
												$("#sylxid").show();
												$.ajax({
													url: "${base}/ywmk/xiaoshexx!getdictjson.action?syfxs=3",
													async: false,
													type: "post",
													data: {
														lxid: lxid,
													},
													success: function (data) {
														if (data != null && data != "undefined" && data != "") {
															var ent =  JSON.parse(data);
															for (let i = 0; i < ent.length; i++) {
																let syfxid = '${syfx?if_exists.id!}';
																if (syfxid != null) {
																	if (syfxid == ent[i].id) {
																		var opt = $("<option id=" + ent[i].id + " value=" + ent[i].id + "  selected='true'  >" + ent[i].name + "</option>");
																	} else {
																		var opt = $("<option id=" + ent[i].id + " value=" + ent[i].id + " >" + ent[i].name + "</option>");
																	}
																}
																$("#syfx1").append(opt);
																$("#syfx2 option[index!='0']").find('option').remove();
																$("#syfx3 option[index!='0']").find('option').remove();
															}
														}
													},
													cache: true
												});
											} else if (data.indexOf("090zygzxx") >= 0) {
												$("#syfxdiv").show();
												var lxid = "8a80808886340564018634374a940028";
												$("#sylxid").show();
												$.ajax({
													url: "${base}/ywmk/xiaoshexx!getdictjson.action?syfxs=3",
													async: false,
													type: "post",
													data: {
														lxid: lxid,
													},
													success: function (data) {
														if (data != null && data != "undefined" && data != "") {
															 var ent = JSON.parse(data);
															for (let i = 0; i < ent.length; i++) {
																let syfxid = '${syfx?if_exists.id!}';
																if (syfxid != null) {
																	if (syfxid == ent[i].id){
																		var opt = $("<option id=" + ent[i].id + " value=" + ent[i].id + "  selected='true'  >" + ent[i].name + "</option>");
																	} else {
																		var opt = $("<option id=" + ent[i].id + " value=" + ent[i].id + " >" + ent[i].name + "</option>");
																	}
																}
																$("#syfx1").append(opt);
																$("#syfx2 option[index!='0']").find('option').remove();
																$("#syfx3 option[index!='0']").find('option').remove();
															}
														}
													},
													cache: true
												});
											}
										} else if (data != "") {
											//特教
											if (data.indexOf("13qttjxx") >= 0 || data.indexOf("20pzxx") >= 0) {
												$("#syfxdiv").show();
												var lxid = "8a80808886340564018634220eaa0010";
												$.ajax({
													url: "${base}/ywmk/xiaoshexx!getdictjson.action",
													async: false,
													type: "post",
													data: {
														lxid: lxid,
													},
													success: function (data) {
														if (data != null && data != "undefined" && data != "") {
															let ent = JSON.parse(data);
															for (let i = 0; i < ent[i].length; i++) {
																if (syfxid != null) {
																	if (syfxid == ent[i].id) {
																		var opt = $("<option id=" + ent[i].id + " value=" + ent[i].id + "  selected='true'  >" + ent[i].name + "</option>");
																	} else {
																		var opt = $("<option id=" + ent[i].id + " value=" + ent[i].id + " >" + ent[i].name + "</option>");
																	}
																} else {
																	var opt = $("<option id=" + ent[i].id + " value=" + ent[i].id + " >" + ent[i].name + "</option>");
																}
																$("#syfx1").append(opt);
																$("#syfx2 option[index!='0']").find('option').remove();
																$("#syfx3 option[index!='0']").find('option').remove();
															}
														}
														$("#sylxid").hide();
													},
													cache: true
												});

											}
											//中小学,高级中学
											if (data.indexOf("02xiaox") >= 0 || data.indexOf("030chujzx") >= 0 || data.indexOf("040jnygz") >= 0 || data.indexOf("050wqzx") >= 0 || data.indexOf("052shienygz") >= 0 || data.indexOf("051gaojzx") >= 0) {
												$("#syfxdiv").show();
												var lxid = "8a8080888633eded018633fae5fe0001";
												$("#sylxid").hide();
												$.ajax({
													url: "${base}/ywmk/xiaoshexx!getdictjson.action",
													async: false,
													type: "post",
													data: {
														lxid: lxid,
													},
													success: function (data) {
														if (data != null && data != "undefined" && data != "") {
															let ent = JSON.parse(data);
															for (let i = 0; i < ent.length; i++) {
																let syfxid = '${syfx?if_exists.id!}';
																if (syfxid != null) {
																	if (syfxid == ent[i].id) {
																		var opt = $("<option id=" + ent[i].id + " value=" + ent[i].id + "  selected='true'  >" + ent[i].name + "</option>");
																	} else {
																		var opt = $("<option id=" + ent[i].id + " value=" + ent[i].id + " >" + ent[i].name + "</option>");
																	}
																} else {
																	var opt = $("<option id=" + ent[i].id + " value=" + ent[i].id + " >" + ent[i].name + "</option>");
																}
																$("#syfx1").append(opt);
																$("#syfx2 option[index!='0']").find('option').remove();
															}
														}
													},
													cache: true
												});
											}
											//幼儿园
											if (data.indexOf("01yey") >= 0 || data.indexOf("02fsyey") >= 0) {
												$("#syfxdiv").show();
												var lxid = "8a8080a4866e61c101866e69193a0001";
												$("#sylxid").hide();
												$.ajax({
													url: "${base}/ywmk/xiaoshexx!getdictjson.action",
													async: false,
													type: "post",
													data: {
														lxid: lxid,
													},
													success: function (data) {
														if (data != null && data != undefined && data != "") {
															let ent = JSON.parse(data);
															for (let i = 0; i < ent.length; i++) {
																let syfxid = '${syfx?if_exists.id!}';
																if (syfxid != null && syfxid != '' && syfxid != 'undefined') {
																	if (syfxid == ent[i].id) {
																		var opt = $("<option id=" + ent[i].id + " value=" + ent[i].id + "  selected='true'  >" + ent[i].name + "</option>");
																	} else {
																		var opt = $("<option id=" + ent[i].id + " value=" + ent[i].id + " >" + ent[i].name + "</option>");
																	}
																} else {
																	var opt = $("<option id=" + ent[i].id + " value=" + ent[i].id + " >" + ent[i].name + "</option>");
																}
																$("#syfx1").append(opt);
																$("#syfx2 option[index!='0']").find('option').remove();
															}
														}
													},
													cache: true
												});
											}
											//职教
											if (data.indexOf("07tzhzdzyxx") >= 0) {
												$("#syfxdiv").show();
												var lxid = "8a80808886340564018634374a940028";
												$("#sylxid").show();
												$.ajax({
													url: "${base}/ywmk/xiaoshexx!getdictjson.action?syfxs=3",
													async: false,
													type: "post",
													data: {
														lxid: lxid,
													},
													success: function (data) {
														if (data != null && data != "undefined" && data != "") {
														     var  ent =	JSON.parse(data);
															for (let i = 0; i < data.length; i++) {
																let syfxid = '${syfx?if_exists.id!}';
																if (syfxid != null) {
																	if (syfxid == ent[i].id) {
																		var opt = $("<option id=" + ent[i].id + " value=" + ent[i].id + "  selected='true'  >" + ent[i].name + "</option>");
																	} else {
																		var opt = $("<option id=" + ent[i].id + " value=" + ent[i].id + " >" + ent[i].name + "</option>");
																	}
																}
																$("#syfx1").append(opt);
																$("#syfx2 option[index!='0']").find('option').remove();
															}
														}
													},
													cache: true
												});
											} else if (data.indexOf("08zdjsxx") >= 0) {
												$("#syfxdiv").show();
												var lxid = "8a80808886340564018634374a940028";
												$("#sylxid").show();
												$.ajax({
													url: "${base}/ywmk/xiaoshexx!getdictjson.action?syfxs=3",
													async: false,
													type: "post",
													data: {
														lxid: lxid,
													},
													success: function (data) {
														if (data != null && data != "undefined" && data != "") {
													        var ent =	JSON.parse(data);
															for (let i = 0; i < ent.length; i++) {
																let syfxid = '${syfx?if_exists.id!}';
																if (syfxid != null) {
																	if (syfxid == ent[i].id) {
																		var opt = $("<option id=" + ent[i].id + " value=" + ent[i].id + "  selected='true'  >" + ent[i].name + "</option>");
																	} else {
																		var opt = $("<option id=" + ent[i].id + " value=" + ent[i].id + " >" + ent[i].name + "</option>");
																	}
																}
																$("#syfx1").append(opt);
																$("#syfx2 option[index!='0']").find('option').remove();
															}
														}
													},
													cache: true
												});
											} else if (data.indexOf("090zygzxx") >= 0) {
												$("#syfxdiv").show();
												var lxid = "8a80808886340564018634374a940028";
												$("#sylxid").show();
												$.ajax({
													url: "${base}/ywmk/xiaoshexx!getdictjson.action?syfxs=3",
													async: false,
													type: "post",
													data: {
														lxid: lxid,
													},
													success: function (data) {
														if (data != null && data != "undefined" && data != "") {
															 var ent =   JSON.parse(data);
															for (let i = 0; i < ent.length; i++) {
																let syfxid = '${syfx?if_exists.id!}';
																if (syfxid != null) {
																	if (syfxid == ent[i].id) {
																		var opt = $("<option id=" + ent[i].id + " value=" + ent[i].id + "  selected='true'  >" + ent[i].name + "</option>");
																	} else {
																		var opt = $("<option id=" + ent[i].id + " value=" + ent[i].id + " >" + ent[i].name + "</option>");
																	}
																}
																$("#syfx1").append(opt);
																$("#syfx2 option[index!='0']").find('option').remove();
															}
														}
													},
													cache: true
												});
											}
										} else if (data != undefined) {
											//特教
											if (data.indexOf("13qttjxx") >= 0 || data.indexOf("20pzxx") >= 0) {
												$("#syfxdiv").show();
												var lxid = "8a80808886340564018634220eaa0010";
												$.ajax({
													url: "${base}/ywmk/xiaoshexx!getdictjson.action",
													async: false,
													type: "post",
													data: {
														lxid: lxid,
													},
													success: function (data) {
														if (data != null && data != "undefined" && data != "") {
															let ent = JSON.parse(data);
															for (let i = 0; i < ent.length; i++) {
																var opt = $("<option id=" + ent[i].id + " value=" + ent[i].id + " <#if sszd?exists && sszd.id?exists && sszd.id ==ent[0].id>selected</#if>  >" + ent[i].name + "</option>");
																$("#syfx1").append(opt);
															}
														}
														$("#sylxid").hide();
													},
													cache: true
												});

											}
											//中小学
											if (data.indexOf("02xiaox") >= 0 || data.indexOf("030chujzx") >= 0 || data.indexOf("040jnygz") >= 0 || data.indexOf("050wqzx") >= 0 || data.indexOf("052shienygz") >= 0 || data.indexOf("051gaojzx") >= 0) {
												$("#syfxdiv").show();
												var lxid = "8a8080888633eded018633fae5fe0001";
												$("#sylxid").hide();
												$.ajax({
													url: "${base}/ywmk/xiaoshexx!getdictjson.action",
													async: false,
													type: "post",
													data: {
														lxid: lxid,
													},
													success: function (data) {
														if (data != null && data != "undefined" && data != "") {
															let ent = JSON.parse(data);
															for (let i = 0; i < ent.length; i++) {
																let syfxid = '${syfx?if_exists.id!}';
																if (syfxid != null) {
																	if (syfxid == ent[i].id) {
																		var opt = $("<option id=" + ent[i].id + " value=" + ent[i].id + "  selected='true'  >" + ent[i].name + "</option>");
																	} else {
																		var opt = $("<option id=" + ent[i].id + " value=" + ent[i].id + " >" + ent[i].name + "</option>");
																	}
																} else {
																	var opt = $("<option id=" + ent[i].id + " value=" + ent[i].id + " >" + ent[i].name + "</option>");
																}
																$("#syfx1").append(opt);
																$("#syfx2 option[index!='0']").find('option').remove();
															}
														}

													},
													cache: true
												});
											}
											//幼儿园
											if (data.indexOf("01yey") >= 0 || data.indexOf("02fsyey") >= 0) {
												$("#syfxdiv").show();
												var lxid = "8a8080a4866e61c101866e69193a0001";
												$("#sylxid").hide();
												$.ajax({
													url: "${base}/ywmk/xiaoshexx!getdictjson.action",
													async: false,
													type: "post",
													data: {
														lxid: lxid,
													},
													success: function (data) {
														if (data != null && data != undefined && data != "") {
															let ent = JSON.parse(data);
															for (let i = 0; i < ent.length; i++) {
																var entid = ent[i].id;
																let syfxid = '${syfx?if_exists.id!}';
																if (syfxid != null && syfxid != '' && syfxid != 'undefined') {
																	if (syfxid == entid) {
																		var opt = $("<option id=" + ent[i].id + " value=" + ent[i].id + "  selected='true'  >" + ent[i].name + "</option>");
																	} else {
																		var opt = $("<option id=" + ent[i].id + " value=" + ent[i].id + " >" + ent[i].name + "</option>");
																	}
																} else {
																	var opt = $("<option id=" + ent[i].id + " value=" + ent[i].id + " >" + ent[i].name + "</option>");
																}
																$("#syfx1").append(opt);
																$("#syfx2 option[index!='0']").find('option').remove();
															}
														}
													},
													cache: true
												});
											}
											//职教
											if (data.indexOf("07tzhzdzyxx") >= 0) {
												$("#syfxdiv").show();
												var lxid = "8a80808886340564018634374a940028";
												$("#sylxid").show();
												$.ajax({
													url: "${base}/ywmk/xiaoshexx!getdictjson.action?syfxs=3",
													async: false,
													type: "post",
													data: {
														lxid: lxid,
													},
													success: function (data) {
														if (data != null && data != "undefined" && data != "") {
															 var ent =  JSON.parse(data);
															for (let i = 0; i < ent.length; i++) {
																let syfxid = '${syfx?if_exists.id!}';
																if (syfxid != null) {
																	if (syfxid == ent[i].id) {
																		var opt = $("<option id=" + ent[i].id + " value=" + ent[i].id + "  selected='true'  >" + ent[i].name + "</option>");
																	} else {
																		var opt = $("<option id=" + ent[i].id + " value=" + ent[i].id + " >" + ent[i].name + "</option>");
																	}
																}
																$("#syfx1").append(opt);
																$("#syfx2 option[index!='0']").find('option').remove();
															}
														}
													},
													cache: true
												});
											} else if (data.indexOf("08zdjsxx") >= 0) {
												$("#syfxdiv").show();
												var lxid = "8a80808886340564018634374a940028";
												$("#sylxid").show();
												$.ajax({
													url: "${base}/ywmk/xiaoshexx!getdictjson.action?syfxs=3",
													async: false,
													type: "post",
													data: {
														lxid: lxid,
													},
													success: function (data) {
														if (data != null && data != "undefined" && data != "") {
														   var ent =	JSON.parse(data);
															for (let i = 0; i < ent.length; i++) {
																let syfxid = '${syfx?if_exists.id!}';
																if (syfxid != null) {
																	if (syfxid == ent[i].id) {
																		var opt = $("<option id=" + ent[i].id + " value=" + ent[i].id + "  selected='true'  >" + ent[i].name + "</option>");
																	} else {
																		var opt = $("<option id=" + ent[i].id + " value=" + ent[i].id + " >" + ent[i].name + "</option>");
																	}
																}
																$("#syfx1").append(opt);
																$("#syfx2 option[index!='0']").find('option').remove();
															}
														}
													},
													cache: true
												});
											} else if (data.indexOf("090zygzxx") >= 0) {
												$("#syfxdiv").show();
												var lxid = "8a80808886340564018634374a940028";
												$("#sylxid").show();
												$.ajax({
													url: "${base}/ywmk/xiaoshexx!getdictjson.action?syfxs=3",
													async: false,
													type: "post",
													data: {
														lxid: lxid,
													},
													success: function (data) {
														if (data != null && data != "undefined" && data != "") {
															 var ent = JSON.parse(data);
															for (let i = 0; i < ent.length; i++) {
																let syfxid = '${syfx?if_exists.id!}';
																if (syfxid != null) {
																	if (syfxid == ent[i].id) {
																		var opt = $("<option id=" + ent[i].id + " value=" + ent[i].id + "  selected='true'  >" + ent[i].name + "</option>");
																	} else {
																		var opt = $("<option id=" + ent[i].id + " value=" + ent[i].id + " >" + ent[i].name + "</option>");
																	}
																}
																$("#syfx1").append(opt);
																$("#syfx2 option[index!='0']").find('option').remove();
															}
														}
													},
													cache: true
												});
											}
										}
									}
								});
							}
							//二级
							function xzxqe(){
								$("#syfx2 option[index!='0']").remove();
								$("#syfx3 option[index!='0']").remove();
								var syfx1 =  $('#syfx1 option:selected').val();
								$.ajax({
									url: "${base}/ywmk/xiaoshexx!getdictjsontwo.action",
									async: false,
									type: "post",
									data: {
										lxid: syfx1,
									},
									success: function (data) {
										if (data !=null && data != "undefined" && data !="" ){
											let ent = JSON.parse(data);
											for (let i = 0; i < ent.length; i++) {
												let syfxid   = '${syfxtwo?if_exists.id!}';
												if (syfxid != null && syfxid !='' && syfxid != 'undefined'){
													if (syfxid == ent[i].id){
														var opt =$("<option id="+ent[i].id+" value="+ent[i].id+"  selected='true'  >"+ent[i].name+"</option>");
													}else{
														var opt =$("<option id="+ent[i].id+" value="+ent[i].id+" >"+ent[i].name+"</option>");
													}
												}else{
													var opt =$("<option id="+ent[i].id+" value="+ent[i].id+" >"+ent[i].name+"</option>");
												}
												$("#syfx2").append(opt);
											}
											xzxqsan()
										}
									},
									cache: true
								});
							}
							//三级
							function xzxqsan(){
								$("#syfx3 option[index!='0']").remove();
								var syfx2 =  $('#syfx2 option:selected').val();
								$.ajax({
									url: "${base}/ywmk/xiaoshexx!getdictjsonthree.action",
									async: false,
									type: "post",
									data: {
										lxid: syfx2,
									},
									success: function (data) {
										if (data != null && data != "undefined" && data != ""){
											 var ent = JSON.parse(data);
											for (let i = 0; i < ent.length; i++) {
												let syfxid   = '${syfxthree?if_exists.id!}';
												if (syfxid != null && syfxid !='' && syfxid != 'undefined'){
													if (syfxid == ent[i].id){
														var opt =$("<option id="+ent[i].id+" value="+ent[i].id+"  selected='true'  >"+ent[i].name+"</option>");
													}else{
														var opt =$("<option id="+ent[i].id+" value="+ent[i].id+" >"+ent[i].name+"</option>");
													}
												}else{
													var opt =$("<option id="+ent[i].id+" value="+ent[i].id+" >"+ent[i].name+"</option>");
												}
												$("#syfx3").append(opt);
											}
										}
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