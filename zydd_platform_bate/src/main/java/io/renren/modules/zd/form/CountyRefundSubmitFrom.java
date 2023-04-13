package io.renren.modules.zd.form;

public class CountyRefundSubmitFrom  extends UpdateForm{
    /**
     * 物流方式
     */
    private String logisticType;
    /**
     * 物流单号
     */
    private String logisticNo;
    /**
     * 物流公司
     */
    private String logisticCompany;
    /**
     * 物流包个数
     */
    private String logisticBag;
    private String logisticAddress;
    private String logisticPerson;
    private String logisticTelphone;

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

    public String getLogisticBag() {
        return logisticBag;
    }

    public void setLogisticBag(String logisticBag) {
        this.logisticBag = logisticBag;
    }

    public String getLogisticAddress() {
        return logisticAddress;
    }

    public void setLogisticAddress(String logisticAddress) {
        this.logisticAddress = logisticAddress;
    }

    public String getLogisticPerson() {
        return logisticPerson;
    }

    public void setLogisticPerson(String logisticPerson) {
        this.logisticPerson = logisticPerson;
    }

    public String getLogisticTelphone() {
        return logisticTelphone;
    }

    public void setLogisticTelphone(String logisticTelphone) {
        this.logisticTelphone = logisticTelphone;
    }
}
