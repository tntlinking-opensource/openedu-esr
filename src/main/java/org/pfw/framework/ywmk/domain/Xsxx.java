package org.pfw.framework.ywmk.domain;

import org.pfw.framework.domain.IdEntity;
import org.pfw.framework.xtgl.domain.Dict;

import java.io.Serializable;

/**
 * @className(类名称): 学生信息
 * @author(创建人): wanjuntao
 * @createDate(创建时间): 2023130
 */
public class Xsxx extends IdEntity implements Serializable {
	//所属班级
	private Bjxx ssbj;
	//学号
	private String xh;
	//姓名
	private String xm;
	//性别
	private String xb;
	//出生年月
	private String csny;
	//入学年份
	private String rxnf;
	//民族
	private Dict mz;
	//政治面貌
	private Dict zzmm;
	//籍贯
	private Dict jig;
	//是否寄宿
	private String sfjs;
	//随迁子女类型
	private Dict sqznlx;
	//进城务工随迁子女类型
	private Dict jcwgsqlx;
	//是否农村留守儿童
	private String sfnclset;
	//招生中接受学前教育
	private String zszjsxqjy;
	//残疾类型
	private Dict cjlx;
	//是否送教上门
	private String sfsjsm;
	//是否随班就读
	private String sfsbjd;
	//当前状态
	private Dict dqzt;
	//备注
	private String bz;
	//数据年份
	private String sjnf;
	//状态变动原因: 将死亡原因、退学原因以文字保存
	private String ztbdyy;
	//死亡地点
	private String swdd;
	//死亡原因
	private String swyy;
	//入校类型
	private Dict rxlx;
	//入校类型
	private Dict swlx;
	//是否国际学生
	private String sfgjxs;
	//父母居住证积分是否达标
	private String fmjzzjfsfdb;
	//退学原因
	private Dict txyy;
	
	private Dict zszqtqk;
	private String ssdz;
	//学制类型
	private String xzlx;
	private String sfczbys;
	
	private String wnzgzzzd;

	private String sfhqzylzs;
	private String sfhqzyjnzs;
	
	
	
	
	public String getSfhqzylzs() {
		return sfhqzylzs;
	}

	public void setSfhqzylzs(String sfhqzylzs) {
		this.sfhqzylzs = sfhqzylzs;
	}

	public String getSfhqzyjnzs() {
		return sfhqzyjnzs;
	}

	public void setSfhqzyjnzs(String sfhqzyjnzs) {
		this.sfhqzyjnzs = sfhqzyjnzs;
	}

	public String getWnzgzzzd() {
		return wnzgzzzd;
	}

	public void setWnzgzzzd(String wnzgzzzd) {
		this.wnzgzzzd = wnzgzzzd;
	}

	public String getSfczbys() {
		return sfczbys;
	}

	public void setSfczbys(String sfczbys) {
		this.sfczbys = sfczbys;
	}

	public String getXzlx() {
		return xzlx;
	}

	public void setXzlx(String xzlx) {
		this.xzlx = xzlx;
	}

	public String getSsdz() {
		return ssdz;
	}

	public void setSsdz(String ssdz) {
		this.ssdz = ssdz;
	}

	public Dict getZszqtqk() {
		return zszqtqk;
	}

	public void setZszqtqk(Dict zszqtqk) {
		this.zszqtqk = zszqtqk;
	}

	public Dict getTxyy() {
		return txyy;
	}

	public void setTxyy(Dict txyy) {
		this.txyy = txyy;
	}

	public String getZszjsxqjy() {
		return zszjsxqjy;
	}

	public void setZszjsxqjy(String zszjsxqjy) {
		this.zszjsxqjy = zszjsxqjy;
	}

	public String getSfgjxs() {
		return sfgjxs;
	}

	public void setSfgjxs(String sfgjxs) {
		this.sfgjxs = sfgjxs;
	}

	public Dict getSwlx() {
		return swlx;
	}

	public void setSwlx(Dict swlx) {
		this.swlx = swlx;
	}

	public Dict getRxlx() {
		return rxlx;
	}

	public void setRxlx(Dict rxlx) {
		this.rxlx = rxlx;
	}

	public String getSwyy() {
		return swyy;
	}

	public void setSwyy(String swyy) {
		this.swyy = swyy;
	}

	public Bjxx getSsbj() {
		return ssbj;
	}

	public void setSsbj(Bjxx ssbj) {
		this.ssbj = ssbj;
	}

	public String getXh() {
		return xh;
	}

	public void setXh(String xh) {
		this.xh = xh;
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

	public String getRxnf() {
		return rxnf;
	}

	public void setRxnf(String rxnf) {
		this.rxnf = rxnf;
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

	public Dict getJig() {
		return jig;
	}

	public void setJig(Dict jig) {
		this.jig = jig;
	}

	public Dict getJcwgsqlx() {
		return jcwgsqlx;
	}

	public void setJcwgsqlx(Dict jcwgsqlx) {
		this.jcwgsqlx = jcwgsqlx;
	}



	public Dict getCjlx() {
		return cjlx;
	}

	public void setCjlx(Dict cjlx) {
		this.cjlx = cjlx;
	}

	public String getSfjs() {
		return sfjs;
	}

	public void setSfjs(String sfjs) {
		this.sfjs = sfjs;
	}

	public Dict getSqznlx() {
		return sqznlx;
	}

	public void setSqznlx(Dict sqznlx) {
		this.sqznlx = sqznlx;
	}

	public String getSfnclset() {
		return sfnclset;
	}

	public void setSfnclset(String sfnclset) {
		this.sfnclset = sfnclset;
	}

 

	public String getSfsjsm() {
		return sfsjsm;
	}

	public void setSfsjsm(String sfsjsm) {
		this.sfsjsm = sfsjsm;
	}

	public String getSfsbjd() {
		return sfsbjd;
	}

	public void setSfsbjd(String sfsbjd) {
		this.sfsbjd = sfsbjd;
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

	public String getSjnf() {
		return sjnf;
	}

	public void setSjnf(String sjnf) {
		this.sjnf = sjnf;
	}

	public String getZtbdyy() {
		return ztbdyy;
	}

	public void setZtbdyy(String ztbdyy) {
		this.ztbdyy = ztbdyy;
	}

	public String getSwdd() {
		return swdd;
	}

	public void setSwdd(String swdd) {
		this.swdd = swdd;
	}

	public String getFmjzzjfsfdb() {
		return fmjzzjfsfdb;
	}

	public void setFmjzzjfsfdb(String fmjzzjfsfdb) {
		this.fmjzzjfsfdb = fmjzzjfsfdb;
	}
}
