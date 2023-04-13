package io.renren.modules.zd.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.util.Date;

@TableName("zd_zmcr_source_log")
public class ZdZMCRSourceLog implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     *
     */
    @TableId(value = "id_", type = IdType.UUID)
    private String id;

    private String sourceId;
    private String sourceResourceId;
    private String oldZmcrId;
    private String newZmcrId;
    private String oldResourceId;
    private String newResourceId;
    private String createBy;
    private Date createTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSourceId() {
        return sourceId;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

    public String getSourceResourceId() {
        return sourceResourceId;
    }

    public void setSourceResourceId(String sourceResourceId) {
        this.sourceResourceId = sourceResourceId;
    }

    public String getOldResourceId() {
        return oldResourceId;
    }

    public void setOldResourceId(String oldResourceId) {
        this.oldResourceId = oldResourceId;
    }

    public String getNewResourceId() {
        return newResourceId;
    }

    public void setNewResourceId(String newResourceId) {
        this.newResourceId = newResourceId;
    }

    public String getOldZmcrId() {
        return oldZmcrId;
    }

    public void setOldZmcrId(String oldZmcrId) {
        this.oldZmcrId = oldZmcrId;
    }

    public String getNewZmcrId() {
        return newZmcrId;
    }

    public void setNewZmcrId(String newZmcrId) {
        this.newZmcrId = newZmcrId;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
