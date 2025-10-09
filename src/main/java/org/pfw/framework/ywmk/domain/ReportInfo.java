package org.pfw.framework.ywmk.domain;

import java.util.HashSet;
import java.util.Set;

import org.pfw.framework.domain.IdEntity;
import org.pfw.framework.domain.security.User;

public class ReportInfo extends IdEntity{

	private String versionh;//版本号
	private String dm;
	private String reportcode;//表号
	private String reportname;//报表名称
	private String reportlx;//报表类型
	private Integer sequenceNumber;//顺序号
	private String configytype;//配置类型: 1_配置,2_自定义url
	private String customURL;//url地址
	private String customURLDetails;//url地址
	private String customURLShenh;//url地址
	private String customURLzdzxcx;//url地址
	private Integer rowNumber;//行数
	private Integer columnNumber;//列数
	private String explains;//指标解释
	private String reportDescribe;//指标说明
	private String verifyRelations;//校验关系
	private String syxxids;//适用学校类型id字符串
	private String syxxmcs;//适用学校名称id字符串
	
	private String bz;//备注
	private String reptTable;//表格html存储
	private String reptTableHead;//表格头部html存储
	private String jldw;//计量单位
	private String jgbmc1;//保存结果表1
	private String jgbmc2;//保存结果表2
	private String sffxqtb;//是否分校区填报
	private String sffzxy;//是否复制下移
	private String sfjycw;//是否有校验错误
	
	
	private String sftx; //是否填写
	private String sfyyj; // 是否有审核意见(包括历史记录)
	private String sfybbyj; // 是否有审核意见(针对一个报表，和工作流审核id关联查询)
	private String bbyj;//报表意见记录
	private String zbid;//指导中心汇总主表id
	
	private String pzggsj;//配置更改时间
	private User pzgguser;//配置更改修改人
	private String dcmbwjm;//导出模版文件名
	private String dcmbscsj;//导出模版生成时间
	private User dcmbscuser;//导出模版生成操作人
	private Set<ReportInfoRowFilter> rowFilterSet = new HashSet<ReportInfoRowFilter>();//报表行过滤
	
	private String drmbwjm;//导入模版文件名
	private String drmbscsj;//导入模版生成时间
	private User drmbscuser;//导入模版生成操作人
	
	private String bbnf;
	
	private String bbgs;
	
	
	private String sffztc;
	private String sfbd;
	
	private String bdgzpz;
	private String fztcgzpz;
	
	private String sfyx;
	 
	
	public String getSfyx() {
		return sfyx;
	}
	public void setSfyx(String sfyx) {
		this.sfyx = sfyx;
	}
	public String getFztcgzpz() {
		return fztcgzpz;
	}
	public void setFztcgzpz(String fztcgzpz) {
		this.fztcgzpz = fztcgzpz;
	}
	public String getBdgzpz() {
		return bdgzpz;
	}
	public void setBdgzpz(String bdgzpz) {
		this.bdgzpz = bdgzpz;
	}
	public String getSffztc() {
		return sffztc;
	}
	public void setSffztc(String sffztc) {
		this.sffztc = sffztc;
	}
	public String getSfbd() {
		return sfbd;
	}
	public void setSfbd(String sfbd) {
		this.sfbd = sfbd;
	}
	public String getBbnf() {
		return bbnf;
	}
	public void setBbnf(String bbnf) {
		this.bbnf = bbnf;
	}
	public String getDm() {
		return dm;
	}
	public void setDm(String dm) {
		this.dm = dm;
	}
	public String getBbgs() {
		return bbgs;
	}
	public void setBbgs(String bbgs) {
		this.bbgs = bbgs;
	}
	public String getCustomURLzdzxcx() {
		return customURLzdzxcx;
	}
	public void setCustomURLzdzxcx(String customURLzdzxcx) {
		this.customURLzdzxcx = customURLzdzxcx;
	}
	public String getCustomURLShenh() {
		return customURLShenh;
	}
	public void setCustomURLShenh(String customURLShenh) {
		this.customURLShenh = customURLShenh;
	}
	public String getCustomURLDetails() {
		return customURLDetails;
	}
	public void setCustomURLDetails(String customURLDetails) {
		this.customURLDetails = customURLDetails;
	}
	public String getZbid() {
		return zbid;
	}
	public void setZbid(String zbid) {
		this.zbid = zbid;
	}
	public String getSfjycw() {
		return sfjycw;
	}
	public void setSfjycw(String sfjycw) {
		this.sfjycw = sfjycw;
	}
	public String getDrmbwjm() {
		return drmbwjm;
	}
	public void setDrmbwjm(String drmbwjm) {
		this.drmbwjm = drmbwjm;
	}
	public String getDrmbscsj() {
		return drmbscsj;
	}
	public void setDrmbscsj(String drmbscsj) {
		this.drmbscsj = drmbscsj;
	}
	public User getDrmbscuser() {
		return drmbscuser;
	}
	public void setDrmbscuser(User drmbscuser) {
		this.drmbscuser = drmbscuser;
	}
	public String getSffzxy() {
		return sffzxy;
	}
	public void setSffzxy(String sffzxy) {
		this.sffzxy = sffzxy;
	}
	public String getSfybbyj() {
		return sfybbyj;
	}
	public void setSfybbyj(String sfybbyj) {
		this.sfybbyj = sfybbyj;
	}
	public String getBbyj() {
		return bbyj;
	}
	public void setBbyj(String bbyj) {
		this.bbyj = bbyj;
	}
	public String getSfyyj() {
		return sfyyj;
	}
	public void setSfyyj(String sfyyj) {
		this.sfyyj = sfyyj;
	}
	public String getSftx() {
		return sftx;
	}
	public void setSftx(String sftx) {
		this.sftx = sftx;
	}
	public String getSffxqtb() {
		return sffxqtb;
	}
	public void setSffxqtb(String sffxqtb) {
		this.sffxqtb = sffxqtb;
	}
	public String getJldw() {
		return jldw;
	}
	public void setJldw(String jldw) {
		this.jldw = jldw;
	}
	public String getJgbmc1() {
		return jgbmc1;
	}
	public void setJgbmc1(String jgbmc1) {
		this.jgbmc1 = jgbmc1;
	}
	public String getJgbmc2() {
		return jgbmc2;
	}
	public void setJgbmc2(String jgbmc2) {
		this.jgbmc2 = jgbmc2;
	}
	public String getReptTableHead() {
		return reptTableHead;
	}
	public void setReptTableHead(String reptTableHead) {
		this.reptTableHead = reptTableHead;
	}
	public String getReptTable() {
		return reptTable;
	}
	public void setReptTable(String reptTable) {
		this.reptTable = reptTable;
	}
	public String getVersionh() {
		return versionh;
	}
	public void setVersionh(String versionh) {
		this.versionh = versionh;
	}
	public String getSyxxids() {
		return syxxids;
	}
	public void setSyxxids(String syxxids) {
		this.syxxids = syxxids;
	}
	public String getSyxxmcs() {
		return syxxmcs;
	}
	public void setSyxxmcs(String syxxmcs) {
		this.syxxmcs = syxxmcs;
	}
	public String getReportcode() {
		return reportcode;
	}
	public void setReportcode(String reportcode) {
		this.reportcode = reportcode;
	}
	public String getReportname() {
		return reportname;
	}
	public void setReportname(String reportname) {
		this.reportname = reportname;
	}
	public String getReportlx() {
		return reportlx;
	}
	public void setReportlx(String reportlx) {
		this.reportlx = reportlx;
	}
	public Integer getSequenceNumber() {
		return sequenceNumber;
	}
	public void setSequenceNumber(Integer sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
	}
	public String getConfigytype() {
		return configytype;
	}
	public void setConfigytype(String configytype) {
		this.configytype = configytype;
	}
	public String getCustomURL() {
		return customURL;
	}
	public void setCustomURL(String customURL) {
		this.customURL = customURL;
	}
	public Integer getRowNumber() {
		return rowNumber;
	}
	public void setRowNumber(Integer rowNumber) {
		this.rowNumber = rowNumber;
	}
	public Integer getColumnNumber() {
		return columnNumber;
	}
	public void setColumnNumber(Integer columnNumber) {
		this.columnNumber = columnNumber;
	}
	public String getExplains() {
		return explains;
	}
	public void setExplains(String explains) {
		this.explains = explains;
	}
	public String getReportDescribe() {
		return reportDescribe;
	}
	public void setReportDescribe(String reportDescribe) {
		this.reportDescribe = reportDescribe;
	}
	public String getVerifyRelations() {
		return verifyRelations;
	}
	public void setVerifyRelations(String verifyRelations) {
		this.verifyRelations = verifyRelations;
	}
	public String getBz() {
		return bz;
	}
	public void setBz(String bz) {
		this.bz = bz;
	}
	public String getPzggsj() {
		return pzggsj;
	}
	public void setPzggsj(String pzggsj) {
		this.pzggsj = pzggsj;
	}
	public User getPzgguser() {
		return pzgguser;
	}
	public void setPzgguser(User pzgguser) {
		this.pzgguser = pzgguser;
	}
	public String getDcmbwjm() {
		return dcmbwjm;
	}
	public void setDcmbwjm(String dcmbwjm) {
		this.dcmbwjm = dcmbwjm;
	}
	public String getDcmbscsj() {
		return dcmbscsj;
	}
	public void setDcmbscsj(String dcmbscsj) {
		this.dcmbscsj = dcmbscsj;
	}
	public User getDcmbscuser() {
		return dcmbscuser;
	}
	public void setDcmbscuser(User dcmbscuser) {
		this.dcmbscuser = dcmbscuser;
	}
	public Set<ReportInfoRowFilter> getRowFilterSet() {
		return rowFilterSet;
	}
	public void setRowFilterSet(Set<ReportInfoRowFilter> rowFilterSet) {
		this.rowFilterSet = rowFilterSet;
	}
	
}
