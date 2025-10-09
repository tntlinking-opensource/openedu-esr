package org.pfw.framework.ywmk.domain;

import org.pfw.framework.domain.IdEntity;

import java.io.Serializable;
/**
 * @className(类名称): 学校信息
 * @author(创建人): wanjuntao
 * @createDate(创建时间): 2023129
 */
public class Xxxx extends IdEntity implements Serializable {
	//学校名称
	private String xxmc;
	//类型名称
	private String xxlxmc;
	//学校代码
	private String xxdm;
	//类型id
	private String bxlxid;
	//备注
	private String bz;

	public String getXxmc() {
		return xxmc;
	}

	public void setXxmc(String xxmc) {
		this.xxmc = xxmc;
	}

	public String getXxlxmc() {
		return xxlxmc;
	}

	public void setXxlxmc(String xxlxmc) {
		this.xxlxmc = xxlxmc;
	}
	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}

	public String getBxlxid() {
		return bxlxid;
	}

	public void setBxlxid(String bxlxid) {
		this.bxlxid = bxlxid;
	}

	public String getXxdm() {
		return xxdm;
	}

	public void setXxdm(String xxdm) {
		this.xxdm = xxdm;
	}
}
