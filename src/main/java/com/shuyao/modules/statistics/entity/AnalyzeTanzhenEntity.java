package com.shuyao.modules.statistics.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 统计分析
 * 
 * @author shuyao
 * @email shuyao@gmail.com
 * @date 2017-09-07 00:29:39
 */
public class AnalyzeTanzhenEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Long id;
	//经销商名
	private String dealerName;
	//公司名
	private String companyName;
	//4s店
	private String s4Name;
	//手机号
	private String mobile;
	//进入时间
	private Date incomingTime;
	//进入市场
	private Date keepTime;
	//状态  0：低   1：高
	private Integer status;
	//创建时间
	private Date createTime;

	/**
	 * 设置：
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 获取：
	 */
	public Long getId() {
		return id;
	}
	/**
	 * 设置：经销商名
	 */
	public void setDealerName(String dealerName) {
		this.dealerName = dealerName;
	}
	/**
	 * 获取：经销商名
	 */
	public String getDealerName() {
		return dealerName;
	}
	/**
	 * 设置：公司名
	 */
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	/**
	 * 获取：公司名
	 */
	public String getCompanyName() {
		return companyName;
	}
	/**
	 * 设置：4s店
	 */
	public void setS4Name(String s4Name) {
		this.s4Name = s4Name;
	}
	/**
	 * 获取：4s店
	 */
	public String getS4Name() {
		return s4Name;
	}
	/**
	 * 设置：手机号
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	/**
	 * 获取：手机号
	 */
	public String getMobile() {
		return mobile;
	}
	/**
	 * 设置：进入时间
	 */
	public void setIncomingTime(Date incomingTime) {
		this.incomingTime = incomingTime;
	}
	/**
	 * 获取：进入时间
	 */
	public Date getIncomingTime() {
		return incomingTime;
	}
	/**
	 * 设置：进入市场
	 */
	public void setKeepTime(Date keepTime) {
		this.keepTime = keepTime;
	}
	/**
	 * 获取：进入市场
	 */
	public Date getKeepTime() {
		return keepTime;
	}
	/**
	 * 设置：状态  0：低   1：高
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * 获取：状态  0：低   1：高
	 */
	public Integer getStatus() {
		return status;
	}
	/**
	 * 设置：创建时间
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * 获取：创建时间
	 */
	public Date getCreateTime() {
		return createTime;
	}
}
