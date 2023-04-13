package io.renren.modules.subject.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.util.Date;

@TableName("subject_resource_scope")
public class SubjectResourceScopeEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @TableId(value = "id", type = IdType.UUID)
    private String id;
    private String resourceId;
    private String orgId;
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    /**
     *
     */
    @TableField(fill = FieldFill.INSERT)
    private String createBy;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }
}
