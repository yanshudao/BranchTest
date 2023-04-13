package io.renren.modules.zd.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.zd.dao.ZdBalanceCompanyIncomeDao;
import io.renren.modules.zd.entity.ZdBalanceCompanyIncomeEntity;
import io.renren.modules.zd.service.ZdBalanceCompanyIncomeService;


@Service("zdBalanceCompanyIncomeService")
public class ZdBalanceCompanyIncomeServiceImpl extends ServiceImpl<ZdBalanceCompanyIncomeDao, ZdBalanceCompanyIncomeEntity> implements ZdBalanceCompanyIncomeService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<ZdBalanceCompanyIncomeEntity> page = this.selectPage(
                new Query<ZdBalanceCompanyIncomeEntity>(params).getPage(),
                new EntityWrapper<ZdBalanceCompanyIncomeEntity>()
        );

        return new PageUtils(page);
    }

}
