package org.pfw.framework.ywmk.domain;

import org.pfw.framework.domain.IdEntity;
import org.pfw.framework.xtgl.domain.Dict;

import java.io.Serializable;
/**
 * @className(类名称): 学校所属类型
 * @author(创建人): wanjuntao
 * @createDate(创建时间): 2023129
 */
public class Xxsslx extends IdEntity implements Serializable {
	//所属学校
	private Xxxx ssxx;
	//学校类型id
	private Dict xxlxid;

	public Xxxx getSsxx() {
		return ssxx;
	}

	public void setSsxx(Xxxx ssxx) {
		this.ssxx = ssxx;
	}

	public Dict getXxlxid() {
		return xxlxid;
	}

	public void setXxlxid(Dict xxlxid) {
		this.xxlxid = xxlxid;
	}
}
