package io.renren.modules.zd.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.common.exception.RRException;
import io.renren.common.utils.DecimalUtils;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;
import io.renren.modules.search.vo.StatisticsStockIncomeResourceDetailVO;
import io.renren.modules.zd.dao.ZdStockIncomeResourceDao;
import io.renren.modules.zd.entity.ZdStockIncomeResourceEntity;
import io.renren.modules.zd.form.ZdSourceIncomeResourceForm;
import io.renren.modules.zd.service.ZdStockIncomeResourceService;
import io.renren.modules.zd.vo.ZdStockIncomeResourceVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;


@Service("zdStockIncomeResourceService")
public class ZdStockIncomeResourceServiceImpl extends ServiceImpl<ZdStockIncomeResourceDao, ZdStockIncomeResourceEntity> implements ZdStockIncomeResourceService {

    @Autowired
    private ZdStockIncomeResourceDao zdStockIncomeResourceDao;
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<ZdStockIncomeResourceEntity> page = this.selectPage(
                new Query<ZdStockIncomeResourceEntity>(params).getPage(),
                new EntityWrapper<ZdStockIncomeResourceEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<ZdStockIncomeResourceVO> selectByIncomeId(String incomeId) {
        return zdStockIncomeResourceDao.selectVOByIncomeId(incomeId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveIncomeResource(List<ZdSourceIncomeResourceForm> list) {
        for(ZdSourceIncomeResourceForm zdSourceIncomeResourceForm:list)
        {
            ZdStockIncomeResourceEntity zdStockIncomeResourceEntity=zdStockIncomeResourceDao.selectById(zdSourceIncomeResourceForm.getId());
            if(zdStockIncomeResourceEntity==null||"1".equals(zdStockIncomeResourceEntity.getStatus()))
            {
                throw new RRException("无法找到详情单或者详情单状态不允许修改");
            }
            zdStockIncomeResourceEntity.setIncomeNum(zdSourceIncomeResourceForm.getNum());
            zdStockIncomeResourceEntity.setRealNum(zdSourceIncomeResourceForm.getNum());
            zdStockIncomeResourceEntity.setMayang(DecimalUtils.mayang(zdStockIncomeResourceEntity.getIncomePrice(),zdStockIncomeResourceEntity.getRealNum()));
            zdStockIncomeResourceEntity.setShiyang(DecimalUtils.shiyang(zdStockIncomeResourceEntity.getIncomePrice(),zdStockIncomeResourceEntity.getRealNum(),DecimalUtils.DEFAULT_DISCOUNT));

            zdStockIncomeResourceDao.updateById(zdStockIncomeResourceEntity);
        }
    }

    @Override
    public int countByMap(Map map) {
        return zdStockIncomeResourceDao.countByMap(map);
    }

    @Override
    public List<StatisticsStockIncomeResourceDetailVO> queryStatisticsDetailByMap(Map<String, Object> params) {
        return zdStockIncomeResourceDao.selectStatisticsDetailByMap(params);
    }

}
