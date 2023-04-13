package io.renren.modules.zd.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import java.io.Serializable;
import java.util.Date;

/**
 * 
 * 
 * @author lixiang
 * @email 395569664@qq.com
 * @date 2018-06-10 09:18:28
 */
@TableName("zd_balance_company_refund")
public class ZdBalanceCompanyRefundEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId(value = "id_", type = IdType.UUID)
	private String id;
	/**
	 * 
	 */
	private String refundId;
	/**
	 * 
	 */
	private String balanceCompanyId;

	/**
	 * 设置：
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * 获取：
	 */
	public String getId() {
		return id;
	}
	/**
	 * 设置：
	 */
	public void setRefundId(String refundId) {
		this.refundId = refundId;
	}
	/**
	 * 获取：
	 */
	public String getRefundId() {
		return refundId;
	}

	public String getBalanceCompanyId() {
		return balanceCompanyId;
	}

	public void setBalanceCompanyId(String balanceCompanyId) {
		this.balanceCompanyId = balanceCompanyId;
	}
}
