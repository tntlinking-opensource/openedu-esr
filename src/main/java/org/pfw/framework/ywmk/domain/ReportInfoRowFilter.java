package org.pfw.framework.ywmk.domain;

import org.pfw.framework.domain.IdEntity;
import org.pfw.framework.xtgl.domain.Dict;

public class ReportInfoRowFilter extends IdEntity
{
	
	private ReportInfo reportinfo;
	private Dict ssbxlx;
	private String hanghs;
	private String bz;
	
	public ReportInfo getReportinfo() {
		return reportinfo;
	}
	public void setReportinfo(ReportInfo reportinfo) {
		this.reportinfo = reportinfo;
	}
	public String getHanghs() {
		return hanghs;
	}
	public void setHanghs(String hanghs) {
		this.hanghs = hanghs;
	}
	public Dict getSsbxlx() {
		return ssbxlx;
	}
	public void setSsbxlx(Dict ssbxlx) {
		this.ssbxlx = ssbxlx;
	}
	public String getBz() {
		return bz;
	}
	public void setBz(String bz) {
		this.bz = bz;
	}
	
}
