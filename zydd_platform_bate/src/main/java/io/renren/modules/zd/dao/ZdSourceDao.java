package io.renren.modules.zd.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import io.renren.modules.search.vo.StatisticsSourceResourceDetailVO;
import io.renren.modules.search.vo.StatisticsSourceResourceVO;
import io.renren.modules.zd.entity.ZdSourceEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 采购主表
 * 
 * @author lixiang
 * @email 395569664@qq.com
 * @date 2018-03-31 00:14:33
 */
@Mapper
public interface ZdSourceDao extends BaseMapper<ZdSourceEntity> {

    List<ZdSourceEntity> selectListPage(Page page,@Param("params") Map<String, Object> params);

    ZdSourceEntity selectBySourceNo(@Param("sourceNo") String sourceNo);

    List<StatisticsSourceResourceVO> selectStatisticsByMap(Page page,@Param("params") Map<String, Object> params);
    List<StatisticsSourceResourceVO> selectStatisticsByMap(@Param("params") Map<String, Object> params);

    int countByMap(@Param("params") Map map);

    List<StatisticsSourceResourceDetailVO> selectStatisticsDetailByMap(@Param("params") Map<String, Object> params);

    int updateAddress(ZdSourceEntity zdSourceEntity);
}
