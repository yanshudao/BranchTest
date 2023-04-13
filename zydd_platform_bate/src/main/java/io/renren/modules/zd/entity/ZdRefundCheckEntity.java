package io.renren.modules.zd.entity;

import com.baomidou.mybatisplus.annotations.TableName;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Min;

/**
 * 退货审核详情
 * 
 * @author lixiang
 * @email 395569664@qq.com
 * @date 2018-03-31 00:14:33
 */
//@TableName("zd_refund_resource")
public class ZdRefundCheckEntity  {
	@NotBlank(message = "请选择要审核的教材")
	private String id;
	private String resourceId;
	@Min(value = 0,message = "审核数量不能小于0")
	private Integer realNum;

	private String refundCode;

	private String refundId;

	public String getRefundId() {
		return refundId;
	}

	public void setRefundId(String refundId) {
		this.refundId = refundId;
	}

	public String getResourceId() {
		return resourceId;
	}

	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}

	public Integer getRealNum() {
		return realNum;
	}

	public void setRealNum(Integer realNum) {
		this.realNum = realNum;
	}

	public String getRefundCode() {
		return refundCode;
	}

	public void setRefundCode(String refundCode) {
		this.refundCode = refundCode;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "ZdRefundCheckEntity{" +
				"resourceId='" + resourceId + '\'' +
				", realNum=" + realNum +
				", refundCode='" + refundCode + '\'' +
				'}';
	}
}
