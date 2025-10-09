package org.pfw.framework.ywmk.domain;

import org.pfw.framework.domain.IdEntity;
import org.pfw.framework.xtgl.domain.Dict;

import java.io.Serializable;

/**
 * @className(类名称): 数据字典三级
 * @author(创建人): wanjuntao
 * @createDate(创建时间): 20230207
 */
public class DictThree extends IdEntity implements Serializable {
	//所属字典
	private DictTwo sszd;
	//代码
	private String dm;
	//名称
	private String name;
	//是否有效
	private String sfyx;;
	//备注
	private String remark;



	public String getDm() {
		return dm;
	}

	public void setDm(String dm) {
		this.dm = dm;
	}

	public DictTwo getSszd() {
		return sszd;
	}

	public void setSszd(DictTwo sszd) {
		this.sszd = sszd;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSfyx() {
		return sfyx;
	}

	public void setSfyx(String sfyx) {
		this.sfyx = sfyx;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
}
