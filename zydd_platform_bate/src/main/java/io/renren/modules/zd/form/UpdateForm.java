package io.renren.modules.zd.form;

import io.renren.common.validator.annotation.ListNotHasNull;

import java.util.List;

public class UpdateForm {
    @ListNotHasNull
    private List<String> ids;


    public List<String> getIds() {
        return ids;
    }

    public void setIds(List<String> ids) {
        this.ids = ids;
    }
}
