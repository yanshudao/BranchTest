package io.renren.modules.zd.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.util.Date;

@TableName("zd_zmcr_order_log")
public class ZdZMCROrderLog implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     *
     */
    @TableId(value = "id_", type = IdType.UUID)
    private String id;

    private String orderId;
    private String orderResourceId;
    private String oldZmcrId;
    private String newZmcrId;
    private String createBy;
    private Date createTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderResourceId() {
        return orderResourceId;
    }

    public void setOrderResourceId(String orderResourceId) {
        this.orderResourceId = orderResourceId;
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
