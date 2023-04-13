package io.renren.modules.subject.entity.response;

import com.thoughtworks.xstream.annotations.XStreamAlias;

public class ResponseBook  {
    @XStreamAlias("id")
    private String id;
    @XStreamAlias("result")
    private String result="成功";
    @XStreamAlias("reason")
    private String reason;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    @Override
    public String toString() {
        return "ResponseBook{" +
                "id='" + id + '\'' +
                ", result='" + result + '\'' +
                ", reason='" + reason + '\'' +
                '}';
    }
}
