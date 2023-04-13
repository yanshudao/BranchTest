package io.renren.modules.zd.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import io.renren.modules.zd.entity.ZdBalanceEntity;
import io.renren.modules.zd.service.ZdBalanceService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ZdBalanceServiceImplTest {

    @Autowired
    ZdBalanceService zdBalanceService;
    @Test
    public void refreshBalanceOrder() throws Exception {
        List<ZdBalanceEntity> list=zdBalanceService.selectList(new EntityWrapper<ZdBalanceEntity>().eq("high_level_org","H00001").eq("semester_code","201809"));
       List<String> ids=new ArrayList<>();
       for(ZdBalanceEntity zdBalanceEntity:list)
       {
           ids.add(zdBalanceEntity.getId());
       }
        zdBalanceService.refreshBalanceOrder(ids);
    }

}