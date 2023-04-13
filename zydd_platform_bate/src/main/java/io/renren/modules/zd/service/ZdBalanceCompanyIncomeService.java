package io.renren.modules.zd.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.zd.entity.ZdBalanceCompanyIncomeEntity;

import java.util.Map;

/**
 * 
 *
 * @author lixiang
 * @email 395569664@qq.com
 * @date 2018-06-10 09:18:28
 */
public interface ZdBalanceCompanyIncomeService extends IService<ZdBalanceCompanyIncomeEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

