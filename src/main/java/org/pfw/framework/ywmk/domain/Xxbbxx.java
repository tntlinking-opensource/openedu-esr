package org.pfw.framework.ywmk.domain;

import java.io.Serializable;

import org.pfw.framework.domain.IdEntity;
 
public class Xxbbxx extends IdEntity implements Serializable {
	private Sjrw sjrw;
	private String nianf;
	private String reportcode;
	private String reportname;
	private String rptTable;//报表html
	private String yxh;
	private String rptTableRes;
	private Integer columnNumber;
	
	public Integer getColumnNumber() {
		return columnNumber;
	}
	public void setColumnNumber(Integer columnNumber) {
		this.columnNumber = columnNumber;
	}
	public String getRptTableRes() {
		return rptTableRes;
	}
	public void setRptTableRes(String rptTableRes) {
		this.rptTableRes = rptTableRes;
	}
	public String getYxh() {
		return yxh;
	}
	public void setYxh(String yxh) {
		this.yxh = yxh;
	}
	public String getReportcode() {
		return reportcode;
	}
	public void setReportcode(String reportcode) {
		this.reportcode = reportcode;
	}
	public String getReportname() {
		return reportname;
	}
	public void setReportname(String reportname) {
		this.reportname = reportname;
	}
	public Sjrw getSjrw() {
		return sjrw;
	}
	public void setSjrw(Sjrw sjrw) {
		this.sjrw = sjrw;
	}
	public String getNianf() {
		return nianf;
	}
	public void setNianf(String nianf) {
		this.nianf = nianf;
	} 
	public String getRptTable() {
		return rptTable;
	}
	public void setRptTable(String rptTable) {
		this.rptTable = rptTable;
	}
}
