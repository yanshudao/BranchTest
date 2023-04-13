package io.renren.modules.zd.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import io.renren.modules.sys.entity.SysUserEntity;
import io.renren.modules.zd.entity.ZdPublishEntity;
import io.renren.modules.zd.entity.ZdStockIncomeEntity;
import io.renren.modules.zd.service.ZdPublishService;
import io.renren.modules.zd.service.ZdStockIncomeService;
import org.apache.commons.lang.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class ZdStockIncomeServiceImplTest {
    @Autowired
    private ZdStockIncomeService zdStockIncomeService;
    @Autowired
    private ZdPublishService zdPublishService;
    @Test
    public void transforPublish() {
        List<ZdStockIncomeEntity> zdPublishEntityList=
                zdStockIncomeService.selectList(new EntityWrapper<ZdStockIncomeEntity>().eq("org_code","62000000").eq("semester_code","201809"));

        for(ZdStockIncomeEntity zdStockIncomeEntity:zdPublishEntityList)
        {
            zdStockIncomeService.transforPublish(zdStockIncomeEntity.getId());
        }

    }
}