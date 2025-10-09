package org.pfw.framework.ywmk.domain;

import java.io.Serializable;

import org.pfw.framework.domain.IdEntity;
 
public class Sjrw extends IdEntity implements Serializable {
	
	private String rwmc;
	private String nianf;//数据年份
	
	
	
	public String getRwmc() {
		return rwmc;
	}
	public void setRwmc(String rwmc) {
		this.rwmc = rwmc;
	}
	public String getNianf() {
		return nianf;
	}
	public void setNianf(String nianf) {
		this.nianf = nianf;
	}
}
