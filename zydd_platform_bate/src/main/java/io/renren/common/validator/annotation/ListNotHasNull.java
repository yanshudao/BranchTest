package io.renren.common.validator.annotation;

import io.renren.common.validator.annotation.impl.ListNotHasNullValidatorImpl;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ElementType.ANNOTATION_TYPE, ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = ListNotHasNullValidatorImpl.class)////此处指定了注解的实现类为ListNotHasNullValidatorImpl
public @interface ListNotHasNull {

    String message() default "List不能为0或者含有null元素";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    /**
     * 定义List，为了让Bean的一个属性上可以添加多套规则
     */
    @Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER})
    @Retention(RUNTIME)
    @Documented
    @interface List {
        ListNotHasNull[] value();
    }
}