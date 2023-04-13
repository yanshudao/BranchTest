package io.renren.modules.zd.dao;

import com.baomidou.mybatisplus.plugins.Page;
import io.renren.modules.search.vo.StatisticsResourceDetailVO;
import io.renren.modules.search.vo.StatisticsResourceVO;
import io.renren.modules.zd.entity.ZdOrderEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import io.renren.modules.zd.vo.OrderResourceVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 征订主表
 * 
 * @author lixiang
 * @email 395569664@qq.com
 * @date 2018-03-31 00:14:33
 */
@Mapper
public interface ZdOrderDao extends BaseMapper<ZdOrderEntity> {

    List<ZdOrderEntity> selectListPage(Page page,@Param("params") Map<String, Object> params);

    List<StatisticsResourceVO> selectStatisticsByMap(Page page, @Param("params")Map<String, Object> params);
    List<StatisticsResourceVO> selectStatisticsByMap( @Param("params")Map<String, Object> params);

    int countByIdsAndStatus(@Param("ids") List<String> orderIds,@Param("status") String status);

    Integer selectCountByMap(@Param("params") Map<String, Object> params);


    List<StatisticsResourceDetailVO> selectStatisticsDetailByMap(@Param("params")Map<String, Object> params);
}
