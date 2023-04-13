package io.renren.modules.zd.form;

import io.renren.common.validator.annotation.ListNotHasNull;
import io.renren.modules.zd.entity.ZdOrgMajorCourseEntity;

import java.util.List;

public class BatchForm {
    @ListNotHasNull
    private List<String> ids;
    private List<ZdOrgMajorCourseEntity> list;
    private String orgCode;


    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public List<String> getIds() {
        return ids;
    }

    public void setIds(List<String> ids) {
        this.ids = ids;
    }

    public List<ZdOrgMajorCourseEntity> getList() {
        return list;
    }

    public void setList(List<ZdOrgMajorCourseEntity> list) {
        this.list = list;
    }
}
