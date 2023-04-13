package io.renren.modules.zd.form;

import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;

public class ZdCourseDetailForm implements Serializable{
    @NotBlank(message = "请先选择专业")
    private String majorId;
    @NotBlank(message ="请选择课程")
    private String courseId;
    private Integer num;

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getMajorId() {
        return majorId;
    }

    public void setMajorId(String majorId) {
        this.majorId = majorId;
    }
}
