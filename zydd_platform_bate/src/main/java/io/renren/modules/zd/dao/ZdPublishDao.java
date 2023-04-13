package io.renren.modules.zd.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import io.renren.modules.search.vo.StatisticsPublishResourceVO;
import io.renren.modules.sys.entity.SysSemesterEntity;
import io.renren.modules.zd.entity.ZdPublishEntity;
import io.renren.modules.zd.vo.RefundLimitInfoVO;
import io.renren.modules.zd.vo.ZdBalancePriceVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author lixiang
 * @email 395569664@qq.com
 * @date 2018-03-31 00:14:32
 */
@Mapper
public interface ZdPublishDao extends BaseMapper<ZdPublishEntity> {
	  List<ZdPublishEntity> selectByLimit(@Param("params") Map<String, Object> params);

	 List queryPublishList(Page page,@Param("params") Map<String, Object> params);





    List<StatisticsPublishResourceVO> selectStatisticsByMap(Page page, @Param("params") Map<String, Object> params);
    List<StatisticsPublishResourceVO> selectStatisticsByMap( @Param("params") Map<String, Object> params);

    ZdBalancePriceVO getPublishPrice(@Param("list") List<ZdPublishEntity> publishEntityList);

    ZdPublishEntity selectBySource(@Param("sourceType") String sourceType, @Param("id")String id);

    RefundLimitInfoVO getRefundLimitInfo(@Param("orgCode") String orgCode,@Param("semesterList") List<SysSemesterEntity> semesterList);

    RefundLimitInfoVO getRefundLimitInfo2(@Param("orgCode") String orgCode,@Param("semesterCode")String semesterCode);

    List<ZdPublishEntity> selectByBalanceId(@Param("balanceId") String id);

}
