package io.renren.common.validator.annotation.impl;
import io.renren.common.validator.annotation.ListNotHasNull;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;
@Service
public class ListNotHasNullValidatorImpl implements ConstraintValidator<ListNotHasNull, List> {


    @Override
    public void initialize(ListNotHasNull constraintAnnotation) {
     
    }

    public boolean isValid(List list, ConstraintValidatorContext constraintValidatorContext) {
        if(list.size()<=0)
        {
            return false;
        }
        for (Object object : list) {
            if (object == null) {
                //如果List集合中含有Null元素，校验失败
                return false;
            }
        }
        return true;
    }

}