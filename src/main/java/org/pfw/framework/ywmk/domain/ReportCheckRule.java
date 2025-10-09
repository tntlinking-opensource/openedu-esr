package org.pfw.framework.ywmk.domain;

import org.pfw.framework.domain.IdEntity;

public class ReportCheckRule extends IdEntity{
	
	private ReportInfo reportinfo;//所属报表
	private String fenl;//分类 1教育部 2市教委 3区教育局 4其他
	private String jylx;//校验类型 1逻辑校验 2经验校验
	private String jylxmc;//校验类型细化名称:表内关系校验、表间关系校验、年度对比校验、局内部门数据校验
	private String jygz;//校验规则
	private String jygzsxgs;//校验规则数学公式
	private String jygzhy;//校验规则含义
	private String jygzsxgsdygx;//校验规则数学公式对应关系 格式：表号.行号,列号:变量名;表号.行号,列号:变量名2;
	private String bz;//备注
	
	private String sfyx;//
	
	private String jwtabs;
	
	private String cjsj;
	
	
	public String getCjsj() {
		return cjsj;
	}
	public void setCjsj(String cjsj) {
		this.cjsj = cjsj;
	}
	public String getJwtabs() {
		return jwtabs;
	}
	public void setJwtabs(String jwtabs) {
		this.jwtabs = jwtabs;
	}
	public String getSfyx() {
		return sfyx;
	}
	public void setSfyx(String sfyx) {
		this.sfyx = sfyx;
	}
	public String getJygzsxgsdygx() {
		return jygzsxgsdygx;
	}
	public void setJygzsxgsdygx(String jygzsxgsdygx) {
		this.jygzsxgsdygx = jygzsxgsdygx;
	}
	public ReportInfo getReportinfo() {
		return reportinfo;
	}
	public void setReportinfo(ReportInfo reportinfo) {
		this.reportinfo = reportinfo;
	}
	public String getFenl() {
		return fenl;
	}
	public void setFenl(String fenl) {
		this.fenl = fenl;
	}
	public String getJylx() {
		return jylx;
	}
	public void setJylx(String jylx) {
		this.jylx = jylx;
	}
	public String getJylxmc() {
		return jylxmc;
	}
	public void setJylxmc(String jylxmc) {
		this.jylxmc = jylxmc;
	}
	public String getJygz() {
		return jygz;
	}
	public void setJygz(String jygz) {
		this.jygz = jygz;
	}
	public String getJygzhy() {
		return jygzhy;
	}
	public void setJygzhy(String jygzhy) {
		this.jygzhy = jygzhy;
	}
	public String getBz() {
		return bz;
	}
	public void setBz(String bz) {
		this.bz = bz;
	}
	public String getJygzsxgs() {
		return jygzsxgs;
	}
	public void setJygzsxgs(String jygzsxgs) {
		this.jygzsxgs = jygzsxgs;
	}
}
