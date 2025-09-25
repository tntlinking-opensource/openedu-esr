package org.pfw.framework.ywmk.domain;

import org.pfw.framework.domain.IdEntity;
import org.pfw.framework.xtgl.domain.Dict;

import java.io.Serializable;

/**
 * @className(类名称): 专任教师培训情况
 * @author(创建人): wanjuntao
 * @createDate(创建时间): 2023130
 */
public class Zrjspxqk extends IdEntity implements Serializable {
	//教工号
	private String jgh;
	//姓名
	private String xm;
	//培训名称
	private String pxmc;
	//学时
	private String xueshi;
	//培训层次-国家级等
	private Dict pxcc;
	//培训年月
	private String pxny;
	//数据年份
	private String sjnf;
	//备注
	private String bz;

	public String getJgh() {
		return jgh;
	}

	public void setJgh(String jgh) {
		this.jgh = jgh;
	}

	public String getXm() {
		return xm;
	}

	public void setXm(String xm) {
		this.xm = xm;
	}

	public String getPxmc() {
		return pxmc;
	}

	public void setPxmc(String pxmc) {
		this.pxmc = pxmc;
	}
	public String getXueshi() {
		return xueshi;
	}

	public void setXueshi(String xueshi) {
		this.xueshi = xueshi;
	}

	public Dict getPxcc() {
		return pxcc;
	}

	public void setPxcc(Dict pxcc) {
		this.pxcc = pxcc;
	}

	public String getSjnf() {
		return sjnf;
	}

	public void setSjnf(String sjnf) {
		this.sjnf = sjnf;
	}

	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}

	public String getPxny() {
		return pxny;
	}

	public void setPxny(String pxny) {
		this.pxny = pxny;
	}
}
