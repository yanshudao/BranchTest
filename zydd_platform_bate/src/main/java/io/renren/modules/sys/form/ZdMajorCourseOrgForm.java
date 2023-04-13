package io.renren.modules.sys.form;

import io.renren.modules.zd.entity.ZdMajorCourseOrg;

import java.util.List;

public class ZdMajorCourseOrgForm {
    private List<ZdMajorCourseOrg> list;
    private String orgCode;


    public List<ZdMajorCourseOrg> getList() {
        return list;
    }

    public void setList(List<ZdMajorCourseOrg> list) {
        this.list = list;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }
}
