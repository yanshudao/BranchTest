package io.renren.modules.zd.vo;

import java.math.BigDecimal;

public class RefundLimitInfoVO {
    private BigDecimal auditRefundMayang;//总退货（包含退货车）
    private BigDecimal totalPublishMayang;//总发行
    private BigDecimal allowRefundTotal;//总可退
    private BigDecimal refundTotal;//购物车退货码洋
    private BigDecimal allowPublishMayang;//可退码洋

    public BigDecimal getAuditRefundMayang() {
        return auditRefundMayang;
    }

    public void setAuditRefundMayang(BigDecimal auditRefundMayang) {
        this.auditRefundMayang = auditRefundMayang;
    }

    public BigDecimal getTotalPublishMayang() {
        return totalPublishMayang;
    }

    public void setTotalPublishMayang(BigDecimal totalPublishMayang) {
        this.totalPublishMayang = totalPublishMayang;
    }

    public BigDecimal getAllowPublishMayang() {
        return allowPublishMayang;
    }

    public void setAllowPublishMayang(BigDecimal allowPublishMayang) {
        this.allowPublishMayang = allowPublishMayang;
    }

    public BigDecimal getAllowRefundTotal() {
        return allowRefundTotal;
    }

    public void setAllowRefundTotal(BigDecimal allowRefundTotal) {
        this.allowRefundTotal = allowRefundTotal;
    }

    public BigDecimal getRefundTotal() {
        return refundTotal;
    }

    public void setRefundTotal(BigDecimal refundTotal) {
        this.refundTotal = refundTotal;
    }
}
