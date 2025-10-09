package org.pfw.framework.ywmk.domain;

import org.pfw.framework.domain.IdEntity;
import org.pfw.framework.xtgl.domain.Dict;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * @className(类名称): 教职工信息
 * @author(创建人): wanjuntao
 * @createDate(创建时间): 2023130
 */

public class Jzgxx extends IdEntity implements Serializable {
	//所属校区
	private Xxxq ssxq;
	//教工号
	private String jgh;
	//姓名
	private String xm;
	//性别
	private String xb;
	//出生年月
	private String csny;
	//民族
	private Dict mz;
	//政治面貌
	private Dict zzmm;
	//从教年月
	private String cjny;
	//入校年月
	private String rxny;
	//人员类别
	private Dict rylb;
	//职级
	private Dict zhij;
	//是否校长/园长
	private String sfxzyz;
	//学历
	private Dict xuel;
	//学位
	private Dict xuew;
	//义务教育-任教课程
	private Dict ywjyrjkc;
	//高中教育-人教课程
	private Dict gzjyrjkc;
	//职业教育-任教大类
	private Dict zyjyrjdl;
	//是否在编
	private String sfzb;
	//是否接受过专业教育
	private String sfjsgzyjy;
	//是否心里健康教育教师
	private String sfxlzkjs;
	//是否双师
	private String sfss;
	//当前状态
	private Dict dqzt;
	//数据年份
	private String sjnf;
	//是否新进
	private String sfxj;
	//新进类型
	private Dict xjlx;
	//是否应届毕业生
	private String sfyjbys;
	//是否师范生
	private String sfsfs;
	//是否学段调整
	private String sfxdtz;
	//原学段
	private Dict yxd;
	//现学段
	private Dict xxd;
	//备注
	private String bz;
	//是否外校调入
	private String sfwxdr;
	//籍贯
	private Dict jig;
	//是否华侨,0f1s
	private String sfhq;
	//是否特教专任教师,0f1s
	private String sftjzrjs;
	//本学年是否授课(专任教师)
	private String bxnsfsk;
	//任课课程的详细科目(二级)
	private String xxkm;
	//任教课程的详细科目(多选)
	private String ywjyrjkcid;
	//任教课程(Set)
	private Set<Dict> rjkcSet = new HashSet<Dict>();
	//是否外语教师
	private String sfwyjs;
	//是否艺术教师
	private String sfysjs;
	//技术分类
	private String jsfl;
	
	//学段
	private String xued;
	
	private String sfsxzdkjs;
	
	
	
	
	public String getSfsxzdkjs() {
		return sfsxzdkjs;
	}

	public void setSfsxzdkjs(String sfsxzdkjs) {
		this.sfsxzdkjs = sfsxzdkjs;
	}

	public String getJsfl() {
		return jsfl;
	}

	public void setJsfl(String jsfl) {
		this.jsfl = jsfl;
	}

	public String getXued() {
		return xued;
	}

	public void setXued(String xued) {
		this.xued = xued;
	}

	public String getSfwyjs() {
		return sfwyjs;
	}

	public void setSfwyjs(String sfwyjs) {
		this.sfwyjs = sfwyjs;
	}

	public String getSfysjs() {
		return sfysjs;
	}

	public void setSfysjs(String sfysjs) {
		this.sfysjs = sfysjs;
	}

	public Set<Dict> getRjkcSet() {
		return rjkcSet;
	}

	public void setRjkcSet(Set<Dict> rjkcSet) {
		this.rjkcSet = rjkcSet;
	}

	public String getYwjyrjkcid() {
		return ywjyrjkcid;
	}

	public void setYwjyrjkcid(String ywjyrjkcid) {
		this.ywjyrjkcid = ywjyrjkcid;
	}

	public String getXxkm() {
		return xxkm;
	}

	public void setXxkm(String xxkm) {
		this.xxkm = xxkm;
	}

	public String getBxnsfsk() {
		return bxnsfsk;
	}

	public void setBxnsfsk(String bxnsfsk) {
		this.bxnsfsk = bxnsfsk;
	}

	public String getSftjzrjs() {
		return sftjzrjs;
	}

	public void setSftjzrjs(String sftjzrjs) {
		this.sftjzrjs = sftjzrjs;
	}

	public String getSfhq() {
		return sfhq;
	}

	public void setSfhq(String sfhq) {
		this.sfhq = sfhq;
	}

	public Dict getJig() {
		return jig;
	}

	public void setJig(Dict jig) {
		this.jig = jig;
	}

	public String getSfwxdr() {
		return sfwxdr;
	}

	public void setSfwxdr(String sfwxdr) {
		this.sfwxdr = sfwxdr;
	}

	public Xxxq getSsxq() {
		return ssxq;
	}

	public void setSsxq(Xxxq ssxq) {
		this.ssxq = ssxq;
	}

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

	public String getXb() {
		return xb;
	}

	public void setXb(String xb) {
		this.xb = xb;
	}

	public String getCsny() {
		return csny;
	}

	public void setCsny(String csny) {
		this.csny = csny;
	}

	public Dict getMz() {
		return mz;
	}

	public void setMz(Dict mz) {
		this.mz = mz;
	}

	public Dict getZzmm() {
		return zzmm;
	}

	public void setZzmm(Dict zzmm) {
		this.zzmm = zzmm;
	}

	public String getCjny() {
		return cjny;
	}

	public void setCjny(String cjny) {
		this.cjny = cjny;
	}

	public String getRxny() {
		return rxny;
	}

	public void setRxny(String rxny) {
		this.rxny = rxny;
	}

	public Dict getRylb() {
		return rylb;
	}

	public void setRylb(Dict rylb) {
		this.rylb = rylb;
	}

	public Dict getZhij() {
		return zhij;
	}

	public void setZhij(Dict zhij) {
		this.zhij = zhij;
	}

	public String getSfxzyz() {
		return sfxzyz;
	}

	public void setSfxzyz(String sfxzyz) {
		this.sfxzyz = sfxzyz;
	}

	public Dict getXuel() {
		return xuel;
	}

	public void setXuel(Dict xuel) {
		this.xuel = xuel;
	}

	public Dict getXuew() {
		return xuew;
	}

	public void setXuew(Dict xuew) {
		this.xuew = xuew;
	}

	public Dict getYwjyrjkc() {
		return ywjyrjkc;
	}

	public void setYwjyrjkc(Dict ywjyrjkc) {
		this.ywjyrjkc = ywjyrjkc;
	}

	public Dict getGzjyrjkc() {
		return gzjyrjkc;
	}

	public void setGzjyrjkc(Dict gzjyrjkc) {
		this.gzjyrjkc = gzjyrjkc;
	}

	public Dict getZyjyrjdl() {
		return zyjyrjdl;
	}

	public void setZyjyrjdl(Dict zyjyrjdl) {
		this.zyjyrjdl = zyjyrjdl;
	}

	public String getSfzb() {
		return sfzb;
	}

	public void setSfzb(String sfzb) {
		this.sfzb = sfzb;
	}

	public String getSfjsgzyjy() {
		return sfjsgzyjy;
	}

	public void setSfjsgzyjy(String sfjsgzyjy) {
		this.sfjsgzyjy = sfjsgzyjy;
	}

	public String getSfxlzkjs() {
		return sfxlzkjs;
	}

	public void setSfxlzkjs(String sfxlzkjs) {
		this.sfxlzkjs = sfxlzkjs;
	}

	public String getSfss() {
		return sfss;
	}

	public void setSfss(String sfss) {
		this.sfss = sfss;
	}

	public Dict getDqzt() {
		return dqzt;
	}

	public void setDqzt(Dict dqzt) {
		this.dqzt = dqzt;
	}

	public String getSjnf() {
		return sjnf;
	}

	public void setSjnf(String sjnf) {
		this.sjnf = sjnf;
	}

	public String getSfxj() {
		return sfxj;
	}

	public void setSfxj(String sfxj) {
		this.sfxj = sfxj;
	}

	public Dict getXjlx() {
		return xjlx;
	}

	public void setXjlx(Dict xjlx) {
		this.xjlx = xjlx;
	}

	public String getSfyjbys() {
		return sfyjbys;
	}

	public void setSfyjbys(String sfyjbys) {
		this.sfyjbys = sfyjbys;
	}

	public String getSfsfs() {
		return sfsfs;
	}

	public void setSfsfs(String sfsfs) {
		this.sfsfs = sfsfs;
	}

	public String getSfxdtz() {
		return sfxdtz;
	}

	public void setSfxdtz(String sfxdtz) {
		this.sfxdtz = sfxdtz;
	}

	public Dict getYxd() {
		return yxd;
	}

	public void setYxd(Dict yxd) {
		this.yxd = yxd;
	}

	public Dict getXxd() {
		return xxd;
	}

	public void setXxd(Dict xxd) {
		this.xxd = xxd;
	}

	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}
}
