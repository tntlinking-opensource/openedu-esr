<#include '/macro/crud-metro-page3-nowebpst.ftl' >

<#if id?exists>
	<#assign extPosition='校验规则配置'/>
<#else>
	<#assign extPosition='校验规则配置'/>
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
						<form id="save_inputForm" class="form-horizontal  form-bordered" action="${base}/ywmk/report_check_rule!save.action" method="post">
							<@s.token />
							<#if id?exists>
								<input type="hidden" name="id" value="${id}"/>
							</#if>
							<!-- 在这里编写输入的元素 -->
							<#--
							<div class="control-group">
								<label class="control-label"><b>所属报表:</b><span class="required">*</span></label>
								<div class="controls">
									<select id="ssbb" name="reportinfo.id" class="m-wrap span10">
									</select>
								</div>
							</div>
							-->
							
							<div class="control-group">
								<label class="control-label"><b>校验类型:</b><span class="required">*</span></label>
								<div class="controls">
									<select class="m-wrap span10" id="jylx" name="jylx" >
										<option value="1" <#if jylx?exists && jylx == '1'>selected</#if> >逻辑校验</option>
										<option value="2" <#if jylx?exists && jylx == '2'>selected</#if> >经验校验</option>
									</select>
								</div>
							</div>
							
							<div class="control-group">
								<label class="control-label"><b>源属:</b><span class="required">*</span></label>
								<div class="controls">
									<select class="m-wrap span10" id="fenl" name="fenl">
										<option value="">==全部==</option>
										<option value="1" <#if fenl?exists && fenl == '1'>selected</#if> >教育部</option>
										<option value="2" <#if fenl?exists && fenl == '2'>selected</#if> >市教委</option>
										<option value="3" <#if fenl?exists && fenl == '3'>selected</#if> >区教育局</option>
										<option value="4" <#if fenl?exists && fenl == '4'>selected</#if> >其他</option>
									</select>
								</div>
							</div>
							
							<div class="control-group" id="jyjy" >
								<label class="control-label"><b>校验规则:</b><span class="required">*</span></label>
								<div class="controls">
									<textarea type="text" class="span9 m-wrap" id="jygz1" rows="5" name="jygz"  maxlength="1000" >${jygz!}</textarea>
									&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" id="jygzsc" value="检验">
										<span class="help-block">
										校验规则格式例如：last.[表号1.行号,列号]+[表号2.行号,列号]== [表号3.行号,列号]-[表号4.行号,列号]  <font color="red">如果是判断相等(=),则必须用==号,绝对值使用(math.abs()函数)</font>
										 </span>
								</div>
							</div>
							
							<div class="control-group">
								<label class="control-label"><b>校验规则含义:</b><span class="required">*</span></label>
								<div class="controls">
									<input type="text" class="span10 m-wrap" id="jygzhy" name="jygzhy" value="${jygzhy!}" maxlength="1000" />
								</div>
							</div>
							
							
							<div class="control-group">
								<label class="control-label"><b>涉及报表（报表代码）:</b><span class="required">*</span></label>
								<div class="controls">
									<input type="text" class="span10 m-wrap" id="jwtabs" name="jwtabs" value="${jwtabs!}" maxlength="500"  />
								</div>
							</div>
							
							<div class="control-group">
								<label class="control-label"><b>校验规则数学公式:</b><span class="required"></span></label>
								<div class="controls">
									<input type="text" class="span10 m-wrap" id="jygzsxgs" name="jygzsxgs" value="${jygzsxgs!}" maxlength="1000" readonly />
								</div>
							</div>
							
							<div class="control-group">
								<label class="control-label"><b>校验规则数学公式对应关系:</b><span class="required"></span></label>
								<div class="controls">
									<input type="text" class="span10 m-wrap" id="jygzsxgsdygx" name="jygzsxgsdygx" value="${jygzsxgsdygx!}" maxlength="1000" readonly />
									<span class="help-block">格式参考：表号.行号,列号:变量名;表号.行号,列号:变量名2;.....</span>
								</div>
							</div>
							
							
							<div class="control-group">
								<label class="control-label"><b>是否有效:</b><span class="required"></span></label>
								<div class="controls">
									<label class="radio">
										<span class="text">		
											&nbsp;&nbsp;&nbsp;
											<input type="radio" name="sfyx" value="1"  <#if sfyx?exists && sfyx == '1'>checked<#else>checked</#if>>有效						
											</label>
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											<label class="radio">		
											<input type="radio" name="sfyx" value="0"  <#if sfyx?exists && sfyx == '0'>checked</#if>>无效
										</span>
									</label>
								</div>
							</div>
							
							
							<div class="control-group">
								<label class="control-label"><b>备注:</b></label>
								<div class="controls">
									<input type="text" class="span10 m-wrap" id="bz" name="bz" value="${bz!}" maxlength="500" />
								</div>
							</div>
							<div class="row-fluid">
								<div class="span12">
									<div class="form-actions">
										<center>
											<button class="btn green big" type="submit" onclick="sub()" >
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
										jylx:{required:true},					
										fenl:{required:true},					
										jygzhy:{required:true},
										jwtabs:{required:true},
										//jygzsxgs:{required:true},
										//jygzsxgsdygx:{required:true}	
									}
								});
								$('#gzsc').click(function(){
									var jygz = $("#jygz").val();
									if(jygz && typeof(jygz) != "undefined" && jygz != "")
									{
										//后台验证规则的合法性
										$.post
										(
											"${base}/ywmk/report_check_rule!valrule.action",
											{rulestr : jygz},
											function(data){
												if(data)
												{
													if(data != "" && data.indexOf("$") <= 0)
													{
														alert(data);
														$("#jygz").focus();
														return;
													}
													var dataArr = data.split("$");
													$("#jygzsxgs").val(dataArr[0]);
													$("#jygzsxgsdygx").val(dataArr[1]);
													$("#jygzhy").val(dataArr[2]);
												}
											}		
										);
									}else{
										alert("检验规则不能为空");
										$("#jygz").focus();
									}
								});
								
								$('#jygzsc').click(function(){
									var jygz = $("#jygz1").val();
									if(jygz && typeof(jygz) != "undefined" && jygz != "")
									{
										//后台验证规则的合法性
										$.post
										(
											"${base}/ywmk/report_check_rule!jyvalrule.action",
											{rulestr : jygz},
											function(data){
												if(data)
												{
													if(data != "" && data.indexOf("$") <= 0)
													{
														alert(data);
														$("#jygz").focus();
														return;
													}
													var dataArr = data.split("$");
													$("#jygzsxgs").val(dataArr[0]);
													$("#jygzsxgsdygx").val(dataArr[1]);
													$("#jygzhy").val(dataArr[2]);
													$("#jwtabs").val(dataArr[3]);
												}
											}		
										);
									}else{
										alert("检验规则不能为空");
										$("#jygz").focus();
									}
								});
								
								$('#ssbb').select2({  
									minimumInputLength:1,
						            ajax: {  
						                url:  "${base}/ywmk/report_check_rule!jsnbykeyword.action",  
						                dataType: 'json',  
						                delay: 250,  
						                data: function (params) {  
						                    return {  
						                        kword: params.term
						                        //zbid:  $(this).attr("zbid"),
						                        //page: params.page  //分页显示先不要，没有效果  
						                    };  
						                },  
						                processResults: function (data, params) {  
						                    var options = new Array();
							                $(data).each(function(i, o) {
							                    options.push({　　　　　　　　　　//获取select2个必要的字段，id与text
							                        id : o.id,         
							                        text :  o.versionh + "-" + o.reportname + "["+o.reportcode+"]"  + "-" + o.dm
							                    });
							                });
							                return {
							                    results: options        //返回数据
							                };
						                },
						                cache: true
						            }
						        }); 
							});	
							
							<#if reportinfo?exists>
								var option = new Option('${reportinfo.reportname}[${reportinfo.reportcode}]','${reportinfo.id}', true, true);
								$('#ssbb').append(option).trigger("change");
							</#if>
							
							
							function changjygz(){
								var flag = $("#jylx").val()
								if(flag == '1'){
									$("#ljyj").show()
									$("#jyjy").hide()
								}else{
									$("#ljyj").hide()
									$("#jyjy").show()
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