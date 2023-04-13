package io.renren.modules.zd.vo;

import io.renren.modules.subject.entity.SubjectResourceEntity;
import io.renren.modules.zd.entity.ZdSourceResourceEntity;

public class ZdSourceIncomeVO extends SubjectResourceEntity {
    private Integer totalSource;
    //已到货
    private Integer totalIncome;

    private Integer confirmTotalIncome;
    private Integer needTotalIncome;


    public Integer getNeedTotalIncome() {
        return needTotalIncome;
    }

    public void setNeedTotalIncome(Integer needTotalIncome) {
        this.needTotalIncome = needTotalIncome;
    }

    public Integer getConfirmTotalIncome() {
        return confirmTotalIncome;
    }

    public void setConfirmTotalIncome(Integer confirmTotalIncome) {
        this.confirmTotalIncome = confirmTotalIncome;
    }

    public Integer getTotalIncome() {
        return totalIncome;
    }

    public void setTotalIncome(Integer totalIncome) {
        this.totalIncome = totalIncome;
    }

    public Integer getTotalSource() {
        return totalSource;
    }

    public void setTotalSource(Integer totalSource) {
        this.totalSource = totalSource;
    }
}
