package org.pfw.framework.ywmk.domain;

import org.pfw.framework.domain.IdEntity;

import java.io.Serializable;
/**
 * @className(类名称): 专业信息
 * @author(创建人): wanjuntao
 * @createDate(创建时间): 2023130
 */
public class Zyxx extends IdEntity implements Serializable {
	//专业代码
	private String zydm;
	//专业名称
	private String zymc;
	//自主专业名称
	private String zzzymc;
	//年制
	private String nzhi;
	//学制类型
	private String xzlx;
	//是否有效
	private String sfyx;
	//备注
	private String bz;

	public String getZydm() {
		return zydm;
	}

	public void setZydm(String zydm) {
		this.zydm = zydm;
	}

	public String getZymc() {
		return zymc;
	}

	public void setZymc(String zymc) {
		this.zymc = zymc;
	}

	public String getZzzymc() {
		return zzzymc;
	}

	public void setZzzymc(String zzzymc) {
		this.zzzymc = zzzymc;
	}

	public String getNzhi() {
		return nzhi;
	}

	public void setNzhi(String nzhi) {
		this.nzhi = nzhi;
	}

	public String getXzlx() {
		return xzlx;
	}

	public void setXzlx(String xzlx) {
		this.xzlx = xzlx;
	}

	public String getSfyx() {
		return sfyx;
	}

	public void setSfyx(String sfyx) {
		this.sfyx = sfyx;
	}


	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}
}
