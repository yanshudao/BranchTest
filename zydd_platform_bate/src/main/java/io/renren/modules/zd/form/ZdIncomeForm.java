package io.renren.modules.zd.form;

import java.util.List;

public class ZdIncomeForm {
    private String incomeId;
    private String status;
    private List<ZdSourceIncomeResourceForm> list;
    public String getIncomeId() {
        return incomeId;
    }

    public void setIncomeId(String incomeId) {
        this.incomeId = incomeId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<ZdSourceIncomeResourceForm> getList() {
        return list;
    }

    public void setList(List<ZdSourceIncomeResourceForm> list) {
        this.list = list;
    }
}
