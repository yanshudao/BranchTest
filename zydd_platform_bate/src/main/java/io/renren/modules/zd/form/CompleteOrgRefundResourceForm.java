package io.renren.modules.zd.form;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Min;

public class CompleteOrgRefundResourceForm {
    @NotBlank(message = "请选择要审核的教材")
    private String id;
    private String resourceId;
    @Min(value = 0,message = "审核数量不能小于0")
    private Integer realNum;

    private String refundCode;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
}
