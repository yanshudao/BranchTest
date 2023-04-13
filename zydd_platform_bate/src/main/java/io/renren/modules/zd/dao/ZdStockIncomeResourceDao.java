package io.renren.modules.zd.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import io.renren.modules.search.vo.StatisticsStockIncomeResourceDetailVO;
import io.renren.modules.zd.entity.ZdStockIncomeResourceEntity;
import io.renren.modules.zd.vo.ZdStockIncomeResourceVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 入库教材
 * 
 * @author lixiang
 * @email 395569664@qq.com
 * @date 2018-03-31 00:14:32
 */
@Mapper
public interface ZdStockIncomeResourceDao extends BaseMapper<ZdStockIncomeResourceEntity> {

    List<ZdStockIncomeResourceEntity> selectByIncomeId(@Param("incomeId") String id);

    List<ZdStockIncomeResourceVO> selectVOByIncomeId(@Param("incomeId")String incomeId);

    int countByMap(@Param("params") Map map);

    ZdStockIncomeResourceEntity selectByForeignId(@Param("foreignId") String foreignId);

    List<StatisticsStockIncomeResourceDetailVO> selectStatisticsDetailByMap(@Param("params") Map<String, Object> params);
}
