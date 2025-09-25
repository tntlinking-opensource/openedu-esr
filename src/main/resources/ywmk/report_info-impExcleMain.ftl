<#include "/macro/crud-metro-page3-nowebpst.ftl" >

<@crudmetropage3>
	
	<div class="row-fluid">
		<div class="span12">
			<div class="row-fluid">

				<!--查询结果-->
				<div class="span12">
					<div class="portlet box green">
						<div class="portlet-title">
							<div class="caption"><i class="icon-edit"></i>
								${entity?if_exists.reportname!}导入
							</div>
							<div class="actions" id="pagemenubutton" ></div>
						</div>
						<div class="portlet-body form">
						
						<!--内容表单-->
						
						<form id="form_inputForm" name="form_inputForm" class="form-horizontal" action="${base}/ywmk/report_info!dotjimps.action" method="post" enctype="multipart/form-data">
							<input type="hidden" name="id" value="${id!}">
							<div class="row-fluid">
								<div class="span1"></div>
								<div class="span10">
									<div class="control-group">
										<label class="control-label">上传导入文件:<span class="required">*</span></label>
										<div class="controls">
											<@s.file name="drfj" id="drfj" value="" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		      								<button class="btn green" type="submit" id="commit" name="commit">
												<i class="icon-ok"></i> 执行导入
											</button>
		      								<br/><p/><p/>
		      								<a href="javascript:;" onclick="xzmb()" style="text-decoration:none;"><font color=red size=3>点击下载导入模板</font></a>
		      								
											
										</div>
									</div>

									
									
								</div>
								<div class="span1"></div>
							</div>
							
							<div class="row-fluid">
								<div class="span1"></div>
								<div class="span10">
									<#if Parameters['commit']?exists>
										<div class="alert alert-block alert-success fade in">
											<h3 class="alert-heading">已导入成功</h3>
										</div>
									</#if>
								</div>
								<div class="span1"></div>
							</div>
							
						</form>																	

						<script>
							$("#form_inputForm").validate({
								rules: {
									drfj:{required:true},
								}
							});
							function xzmb()
							{
								var nj='${id!}';
								
								if(nj!=null && nj!='')
								{
									var form1 = $("<form>");  
									form1.attr('style','display:none');  
									form1.attr('target','');
									form1.attr('method','post');  
									form1.attr('action','${base}/ywmk/report_info!downBbmb.action?id=${id!}&&rwid=${rwid!}'); 
									$('body').append(form1);
									form1.submit(); 
									form1.remove(); 
								}else{
									alert("请选择报表");
								}
							}
							
							<#if bystr1?exists>
								var reststr = '${bystr1}';
								console.log(reststr);		
								window.opener.impFill(reststr);
								window.close();
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
