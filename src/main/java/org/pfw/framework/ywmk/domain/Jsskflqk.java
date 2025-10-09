package org.pfw.framework.ywmk.domain;

import org.pfw.framework.domain.IdEntity;
import org.pfw.framework.xtgl.domain.Dict;

import java.io.Serializable;

/**
 * @className(类名称): 教师授课分类情况
 * @author(创建人): wanjuntao
 * @createDate(创建时间): 2023130
 */
public class Jsskflqk extends IdEntity implements Serializable {
	//教工号
	private String jgh;
	//姓名
	private String xm;
	//是否授课
	private String sfsk;
	//授课类别
	private Dict sklb;
	//是否思政课
	private String sfszk;
	//不授课原因
	private String bskyy;
	//备注
	private String bz;
	//数据年份
	private String sjnf;

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

	public String getSfsk() {
		return sfsk;
	}

	public void setSfsk(String sfsk) {
		this.sfsk = sfsk;
	}
	public Dict getSklb() {
		return sklb;
	}

	public void setSklb(Dict sklb) {
		this.sklb = sklb;
	}

	public String getSfszk() {
		return sfszk;
	}

	public void setSfszk(String sfszk) {
		this.sfszk = sfszk;
	}

	public String getBskyy() {
		return bskyy;
	}

	public void setBskyy(String bskyy) {
		this.bskyy = bskyy;
	}

	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}

	public String getSjnf() {
		return sjnf;
	}

	public void setSjnf(String sjnf) {
		this.sjnf = sjnf;
	}
}
