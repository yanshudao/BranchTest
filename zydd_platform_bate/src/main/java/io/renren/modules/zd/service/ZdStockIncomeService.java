package io.renren.modules.zd.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.sys.entity.SysOrgEntity;
import io.renren.modules.sys.entity.SysUserEntity;
import io.renren.modules.zd.entity.ZdStockIncomeEntity;
import io.renren.modules.zd.form.ZdIncomeForm;
import io.renren.modules.zd.form.ZdSourceIncomeForm;
import io.renren.modules.zd.form.ZdSourceIncomeResourceForm;

import java.util.List;
import java.util.Map;

/**
 * 入库
 *
 * @author lixiang
 * @email 395569664@qq.com
 * @date 2018-03-31 00:14:33
 */
public interface ZdStockIncomeService extends IService<ZdStockIncomeEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void saveIncome(ZdSourceIncomeForm zdSourceIncomeForm, SysUserEntity sysUserEntity);

    PageUtils queryByOrg(Map<String, Object> params);

    void saveIncomeResource(ZdIncomeForm zdIncomeForm, SysUserEntity sysUserEntity);


    int countByMap(Map map);

    void saveIncomeByNC(ZdSourceIncomeForm zdSourceIncomeForm, SysOrgEntity sysOrgEntity);

    void delayIncome(ZdIncomeForm zdIncomeForm);

    void transforPublish(String id);
}

