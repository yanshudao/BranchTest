package io.renren.modules.zd.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.search.vo.StatisticsStockIncomeResourceDetailVO;
import io.renren.modules.zd.entity.ZdStockIncomeResourceEntity;
import io.renren.modules.zd.form.ZdSourceIncomeResourceForm;
import io.renren.modules.zd.vo.ZdStockIncomeResourceVO;

import java.util.List;
import java.util.Map;

/**
 * 入库教材
 *
 * @author lixiang
 * @email 395569664@qq.com
 * @date 2018-03-31 00:14:32
 */
public interface ZdStockIncomeResourceService extends IService<ZdStockIncomeResourceEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<ZdStockIncomeResourceVO> selectByIncomeId(String incomeId);

    void saveIncomeResource(List<ZdSourceIncomeResourceForm> list);

    int countByMap(Map map);

    List<StatisticsStockIncomeResourceDetailVO> queryStatisticsDetailByMap(Map<String, Object> params);
}

