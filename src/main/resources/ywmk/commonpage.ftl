<#include '/macro/crud-metro-page3.ftl' >

<!--班级信息input页面-->

<#if ymlx?exists && ymlx == 'bjxx'>
	<#if xxxx.xxlxmc?contains("调整后中等职业学校") || xxxx.xxlxmc?contains("中等技术学校") || xxxx.xxlxmc?contains("职业高中学校") || xxxx.xxlxmc?contains("小学") ||  xxxx.xxlxmc?contains("幼儿园") || xxxx.xxlxmc?contains("十二年一贯制")  || xxxx.xxlxmc?contains("高级中学")>
		<#if xxxx.xxlxmc?contains("小学")>
			<#include "bjxx-xiaox-input.ftl">
		</#if>
		<#if xxxx.xxlxmc?contains("幼儿园")>
			<#include "bjxx-yey-input.ftl">
		</#if>
		<#if xxxx.xxlxmc?contains("十二年一贯制")>
			<#include "bjxx-senygz-input.ftl">
		</#if>
		<#if xxxx.xxlxmc?contains("完全中学") >
			<#include "bjxx-wqzx-input.ftl">
		</#if>
		<#if  xxxx.xxlxmc?contains("调整后中等职业学校") || xxxx.xxlxmc?contains("中等技术学校") || xxxx.xxlxmc?contains("职业高中学校")>
			<#include "bjxx-zyxx-input.ftl">
		</#if>
	<#else >
		<#include "bjxx-ptxx.ftl">
	</#if>
</#if>
<!--班级信息input页面-->
<!--学生信息input页面-->
<#if ymlx?exists && ymlx == 'xsxx'>
	<#if xxxx.xxlxmc?contains("初级中学") || xxxx.xxlxmc?contains("完全中学") || xxxx.xxlxmc?contains("高级中学") || xxxx.xxlxmc?contains("小学") ||  xxxx.xxlxmc?contains("九年一贯制学校") || xxxx.xxlxmc?contains("十二年一贯制")  || xxxx.xxlxmc?contains("调整后中等职业学校") || xxxx.xxlxmc?contains("中等技术学校") || xxxx.xxlxmc?contains("职业高中学校")>
		<#if xxxx.xxlxmc?contains("初级中学") || xxxx.xxlxmc?contains("完全中学")>
			<#include "xsxx-cjwqzx-input.ftl">
		</#if>
		<#if xxxx.xxlxmc?contains("高级中学")>
			<#include "xsxx-gjzx-input.ftl">
		</#if>
		<#if xxxx.xxlxmc?contains("小学") || xxxx.xxlxmc?contains("九年一贯制学校") || xxxx.xxlxmc?contains("十二年一贯制学")>
			<#include "xsxx-ygzxx-input.ftl">
		</#if>
		<#if  xxxx.xxlxmc?contains("调整后中等职业学校") || xxxx.xxlxmc?contains("中等技术学校") || xxxx.xxlxmc?contains("职业高中学校")>
			<#include "xsxx-zyxx-input.ftl">
		</#if>
	<#else >
		<#include "xsxx-input.ftl">
	</#if>
</#if>
<!--学生信息input页面-->
<!--教职工信息input页面-->
<#if ymlx?exists && ymlx == 'jzgxx'>
	<#if xxxx.xxlxmc?contains("幼儿园") || xxxx.xxlxmc?contains("完全中学") ||   xxxx.xxlxmc?contains("九年一贯制学校") || xxxx.xxlxmc?contains("十二年一贯制")  || xxxx.xxlxmc?contains("调整后中等职业学校") || xxxx.xxlxmc?contains("中等技术学校") || xxxx.xxlxmc?contains("职业高中学校")>
		<#if  xxxx.xxlxmc?contains("调整后中等职业学校") || xxxx.xxlxmc?contains("中等技术学校") || xxxx.xxlxmc?contains("职业高中学校")>
			<#include "jzgxx-zyxx-input.ftl">
		</#if>
		<#if  xxxx.xxlxmc?contains("九年一贯制学校") >
			<#include "jzgxx-jnygz-input.ftl">
		</#if>
		<#if  xxxx.xxlxmc?contains("十二年一贯制学")>
			<#include "jzgxx-senygz-input.ftl">
		</#if>
		<#if xxxx.xxlxmc?contains("完全中学")>
			<#include "jzgxx-wqzx-input.ftl">
		</#if>
		<#if xxxx.xxlxmc?contains("幼儿园")>
			<#include "jzgxx-yey-input.ftl">
		</#if>
	<#else >
		<!--普通教师-->
		<#include "jzgxx-input.ftl">
	</#if>
</#if>
<!--教职工信息input页面-->
<!--校舍信息input页面-->
<#if ymlx?exists && ymlx == 'xiaoshexx'>
	<#if xxxx.xxlxmc?contains("调整后中等职业学校") || xxxx.xxlxmc?contains("中等技术学校") || xxxx.xxlxmc?contains("职业高中学校") >
		<#include "xiaoshexx-zyxx.ftl">
	<#else >
		<!--普通教师-->
		<#include "xiaoshexx-ptxx.ftl">
	</#if>
</#if>
<!--校舍信息input页面-->



