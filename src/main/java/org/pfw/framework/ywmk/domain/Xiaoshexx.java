package org.pfw.framework.ywmk.domain;

import org.pfw.framework.domain.IdEntity;
import org.pfw.framework.xtgl.domain.Dict;

import java.io.Serializable;

/**
 * @className(类名称): 校舍信息
 * @author(创建人): wanjuntao
 * @createDate(创建时间): 2023130
 */
public class Xiaoshexx extends IdEntity implements Serializable {
	//数据年份
	private String sjnf;
	//校舍名称
	private String xsmc;
	//所属校区
	private Xxxq ssxq;
	//使用方向
	private Dict syfx;
	//使用方向2
	private DictTwo syfxtwo;
	//使用方向3
	private DictThree syfxthree;
	//校舍面积
	private String xsmj;
	//是否c#危房
	private String sfcjwf;
	//是否d#危房
	private String sfdjwf;
	//是否租用外单位
	private String sfzywdw;
	//是否被外单位租(借)用
	private String sfbwdwzjy;
	//职业教育使用--使用类型:独立使用,共同使用
	private String zjsylx;
	//当前状态
	private Dict dqzt;
	//备注
	private String bz;

	private String sfycq;
	
	private String sfzzsg;
	
	
	
	public String getSfycq() {
		return sfycq;
	}

	public void setSfycq(String sfycq) {
		this.sfycq = sfycq;
	}

	public String getSfzzsg() {
		return sfzzsg;
	}

	public void setSfzzsg(String sfzzsg) {
		this.sfzzsg = sfzzsg;
	}

	public String getSjnf() {
		return sjnf;
	}

	public void setSjnf(String sjnf) {
		this.sjnf = sjnf;
	}

	public String getXsmc() {
		return xsmc;
	}

	public void setXsmc(String xsmc) {
		this.xsmc = xsmc;
	}

	public Xxxq getSsxq() {
		return ssxq;
	}

	public void setSsxq(Xxxq ssxq) {
		this.ssxq = ssxq;
	}

	public String getXsmj() {
		return xsmj;
	}

	public void setXsmj(String xsmj) {
		this.xsmj = xsmj;
	}

	public String getSfcjwf() {
		return sfcjwf;
	}

	public void setSfcjwf(String sfcjwf) {
		this.sfcjwf = sfcjwf;
	}

	public String getSfdjwf() {
		return sfdjwf;
	}

	public void setSfdjwf(String sfdjwf) {
		this.sfdjwf = sfdjwf;
	}

	public String getSfzywdw() {
		return sfzywdw;
	}

	public void setSfzywdw(String sfzywdw) {
		this.sfzywdw = sfzywdw;
	}

	public String getSfbwdwzjy() {
		return sfbwdwzjy;
	}

	public void setSfbwdwzjy(String sfbwdwzjy) {
		this.sfbwdwzjy = sfbwdwzjy;
	}

	public String getZjsylx() {
		return zjsylx;
	}

	public void setZjsylx(String zjsylx) {
		this.zjsylx = zjsylx;
	}

	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}

	public Dict getSyfx() {
		return syfx;
	}

	public void setSyfx(Dict syfx) {
		this.syfx = syfx;
	}

	public Dict getDqzt() {
		return dqzt;
	}

	public void setDqzt(Dict dqzt) {
		this.dqzt = dqzt;
	}

	public DictTwo getSyfxtwo() {
		return syfxtwo;
	}

	public void setSyfxtwo(DictTwo syfxtwo) {
		this.syfxtwo = syfxtwo;
	}

	public DictThree getSyfxthree() {
		return syfxthree;
	}

	public void setSyfxthree(DictThree syfxthree) {
		this.syfxthree = syfxthree;
	}
}
