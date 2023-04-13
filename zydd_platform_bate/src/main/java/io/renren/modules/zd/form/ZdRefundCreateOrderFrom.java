package io.renren.modules.zd.form;

import io.renren.modules.zd.entity.ZdRefundEntity;
import io.renren.modules.zd.entity.ZdRefundResourceEntity;
import org.hibernate.validator.constraints.NotBlank;

import java.util.List;

public class ZdRefundCreateOrderFrom {
    private List<ZdRefundResourceEntity> resourceList;
    @NotBlank(message = "请选择代退单位")
    private String fromOrgCode;
    private String toOrgCode;
    private String logisticType;
    @NotBlank(message="物流单号不能为空")
    private String logisticNo;
    @NotBlank(message="物流公司不能为空")
    private String logisticCompany;
    @NotBlank(message="邮寄地址不能为空")
    private String logisticAddress;
    @NotBlank(message="物流件数不能为空")
    private String logisticBag;
    private String status;
    private String refundSemesterCode;

    public String getRefundSemesterCode() {
        return refundSemesterCode;
    }

    public void setRefundSemesterCode(String refundSemesterCode) {
        this.refundSemesterCode = refundSemesterCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<ZdRefundResourceEntity> getResourceList() {
        return resourceList;
    }

    public void setResourceList(List<ZdRefundResourceEntity> resourceList) {
        this.resourceList = resourceList;
    }

    public String getToOrgCode() {
        return toOrgCode;
    }

    public void setToOrgCode(String toOrgCode) {
        this.toOrgCode = toOrgCode;
    }

    public String getFromOrgCode() {
        return fromOrgCode;
    }

    public void setFromOrgCode(String fromOrgCode) {
        this.fromOrgCode = fromOrgCode;
    }

    public String getLogisticType() {
        return logisticType;
    }

    public void setLogisticType(String logisticType) {
        this.logisticType = logisticType;
    }

    public String getLogisticNo() {
        return logisticNo;
    }

    public void setLogisticNo(String logisticNo) {
        this.logisticNo = logisticNo;
    }

    public String getLogisticCompany() {
        return logisticCompany;
    }

    public void setLogisticCompany(String logisticCompany) {
        this.logisticCompany = logisticCompany;
    }

    public String getLogisticAddress() {
        return logisticAddress;
    }

    public void setLogisticAddress(String logisticAddress) {
        this.logisticAddress = logisticAddress;
    }

    public String getLogisticBag() {
        return logisticBag;
    }

    public void setLogisticBag(String logisticBag) {
        this.logisticBag = logisticBag;
    }
}
