package io.renren.config.mybatisplus;

import com.baomidou.mybatisplus.mapper.MetaObjectHandler;
import io.renren.modules.sys.entity.SysUserEntity;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.util.ThreadContext;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class MyMetaObjectHandler extends MetaObjectHandler {
    //新增填充
    @Override
    public void insertFill(MetaObject metaObject) {
        if(ThreadContext.getSecurityManager()!=null&&SecurityUtils.getSubject()!=null&&SecurityUtils.getSubject().getPrincipal()!=null)
        {
            SysUserEntity user = (SysUserEntity) SecurityUtils.getSubject().getPrincipal();
            if(user!=null)
            {
                setFieldValByName("createBy", String.valueOf(user.getUserId()), metaObject);
                setFieldValByName("updateBy", String.valueOf(user.getUserId()), metaObject);
                if(metaObject.hasGetter("orgCode")&&metaObject.getValue("orgCode")==null){
                    setFieldValByName("orgCode",user.getOrgCode(),metaObject);
                }

            }

            setFieldValByName("createTime",new Date(),metaObject);
            setFieldValByName("updateTime",new Date(),metaObject);

        }

    }

    //更新填充
    @Override
    public void updateFill(MetaObject metaObject) {
        if(ThreadContext.getSecurityManager()!=null&&SecurityUtils.getSubject()!=null&&SecurityUtils.getSubject().getPrincipal()!=null)
        {
            SysUserEntity user = (SysUserEntity) SecurityUtils.getSubject().getPrincipal();
            if(user!=null)
            {

                setFieldValByName("updateBy", String.valueOf(user.getUserId()), metaObject);

            }

            setFieldValByName("updateTime",new Date(),metaObject);


        }

    }
}
