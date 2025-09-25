<#include "/macro/crud-metro-page3.ftl" >

<@crudmetropage3>

	<style>
		.reptTable
		{
			width: 100%;
		}
		.reptTable tr td 
		{
			height: 30px;
			width : 40px;
			text-align:center;
		}
		.rept_td_Input
		{
			width: 91%;
			height: 30px !important;
			margin-bottom: 0px !important;
		}
		.tdclickon
		{
		    border: 1px solid #3943db;
			background: rgb(20 20 217 / 20%);
		}
		.noopttd
		{
			text-align:center;
			background-color: #f0f0f0;
		}
		.jzcellcss
		{
			background-color: #fe8282;
		}
		.inputNumcss
		{
			height: 30px !important;
		    width: 98%;
		    margin-bottom: 0px !important;
		    padding: 0px !important;
		}
		.inputNumcssFormula
		{
			height: 30px !important;
		    width: 98%;
		    margin-bottom: 0px !important;
		    padding: 0px !important;
		}
		input[readonly], select[readonly], textarea[readonly]
		{
			cursor: not-allowed;
    		background-color: #e5e5e5 !important;
		}
	</style>
	<div style="min-height:1200px">
		<table style="width:100%">
			<tr>
				<td colspan="2" style="text-align:center;font-size: 18px;font-weight: bold;">
					${entity?if_exists.reportname!}
				</td>
			</tr>
			<tr><td colspan="2" style="text-align:right">表   号：${entity?if_exists.reportcode!}</td></tr>
			<tr><td colspan="2" style="text-align:right">制定机关：教  育  部</td></tr>
			<tr><td colspan="2" style="text-align:right">批准机关：国 家 统 计 局</td></tr>
			<tr><td colspan="2" style="text-align:right">批准文号：国统制[202 ]  号</td></tr>
			<tr>
				<td style="text-align:left">学校（机构）名称：</td>
				<td style="text-align:right">批准文号：国统制[202 ]  号</td>
			</tr>
			<tr>
				<td style="text-align:left">学校（机构）标识码：</td>
				<td style="text-align:right">有效期至：202  年  月</td>
			</tr>
			<tr>
				<td style="text-align:left">统一社会信用代码：</td>
				<#if entity.jldw?exists>
					<td style="text-align:right">计量单位：${entity.jldw!}</td>
				</#if>
			</tr>
			<!--
			<tr><td colspan="2" style="text-align:center">（２０２１学年）</td></tr>
			-->
		<table>
		${entity?if_exists.rptTableRes!}
	</div>
	<script>
	$(document).ready(function() { 
	     $("#reptTable").find("input").each(function(){
	     	$(this).parent().html($(this).val())
	     
	     })	
     })	
     </script>
</@crudmetropage3>