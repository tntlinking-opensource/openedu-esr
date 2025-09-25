package org.pfw.framework.ywmk.domain;

import org.pfw.framework.domain.IdEntity;
import org.pfw.framework.xtgl.domain.Dict;

import java.io.Serializable;

/**
 * @className(类名称): 班级信息
 * @author(创建人): wanjuntao
 * @createDate(创建时间): 2023130
 */
public class Bjxx extends IdEntity implements Serializable {
	//所属校区
	private Xxxq ssxq;
	//班级代码
	private String bjdm;
	//班级名称
	private String bjmc;
	//年级
	private String nianj;
	//幼教-开班类型
	private Dict yjkblx;
	//小学-是否复式班
	private String xjsffsb;
	//特殊-残疾类型:视力残疾班,听力残疾班,言语残疾班,肢体残疾班,智力残疾班,精神残疾班,多重残疾班
	private Dict tjcjlx;
	//当前状态
	private Dict dqzt;
	//备注
	private String bz;
	//所属专业
	private Zyxx sszy;
	//毕业年月
	private String byny;
	//学段
	private String xued;
	//
	private String sfxzxtz;
	
	
	public String getSfxzxtz() {
		return sfxzxtz;
	}

	public void setSfxzxtz(String sfxzxtz) {
		this.sfxzxtz = sfxzxtz;
	}

	public Zyxx getSszy() {
		return sszy;
	}

	public void setSszy(Zyxx sszy) {
		this.sszy = sszy;
	}

	public String getXued() {
		return xued;
	}

	public void setXued(String xued) {
		this.xued = xued;
	}

	public String getByny() {
		return byny;
	}

	public void setByny(String byny) {
		this.byny = byny;
	}

	public Xxxq getSsxq() {
		return ssxq;
	}

	public void setSsxq(Xxxq ssxq) {
		this.ssxq = ssxq;
	}

	public String getBjdm() {
		return bjdm;
	}

	public void setBjdm(String bjdm) {
		this.bjdm = bjdm;
	}

	public String getBjmc() {
		return bjmc;
	}

	public void setBjmc(String bjmc) {
		this.bjmc = bjmc;
	}

	public String getNianj() {
		return nianj;
	}

	public void setNianj(String nianj) {
		this.nianj = nianj;
	}

	public Dict getYjkblx() {
		return yjkblx;
	}

	public void setYjkblx(Dict yjkblx) {
		this.yjkblx = yjkblx;
	}

	public String getXjsffsb() {
		return xjsffsb;
	}

	public void setXjsffsb(String xjsffsb) {
		this.xjsffsb = xjsffsb;
	}

	public Dict getTjcjlx() {
		return tjcjlx;
	}

	public void setTjcjlx(Dict tjcjlx) {
		this.tjcjlx = tjcjlx;
	}

	public Dict getDqzt() {
		return dqzt;
	}

	public void setDqzt(Dict dqzt) {
		this.dqzt = dqzt;
	}

	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}
 
}
