package org.pfw.framework.ywmk.domain;

import java.io.Serializable;

import org.pfw.framework.domain.IdEntity;
import org.pfw.framework.xtgl.domain.Dict;
/**
 * 报表取值配置
 * **/
public class Bbqzpz extends IdEntity implements Serializable {
	
	private String nianf;//数据年份
	private Dict bxlx;//办学类型
	private ReportInfo reportInfo;//所属报表 
	private String qzlx;//1:sql 2:回调方法取值
	private String sqlpz;//sql取值
	private String hdfw;//回调服务
	private String sfyx;
	
	
	public String getSfyx() {
		return sfyx;
	}
	public void setSfyx(String sfyx) {
		this.sfyx = sfyx;
	}
	public String getNianf() {
		return nianf;
	}
	public void setNianf(String nianf) {
		this.nianf = nianf;
	}
	public Dict getBxlx() {
		return bxlx;
	}
	public void setBxlx(Dict bxlx) {
		this.bxlx = bxlx;
	}
	public ReportInfo getReportInfo() {
		return reportInfo;
	}
	public void setReportInfo(ReportInfo reportInfo) {
		this.reportInfo = reportInfo;
	}
	public String getQzlx() {
		return qzlx;
	}
	public void setQzlx(String qzlx) {
		this.qzlx = qzlx;
	}
	public String getSqlpz() {
		return sqlpz;
	}
	public void setSqlpz(String sqlpz) {
		this.sqlpz = sqlpz;
	}
	public String getHdfw() {
		return hdfw;
	}
	public void setHdfw(String hdfw) {
		this.hdfw = hdfw;
	}
}
