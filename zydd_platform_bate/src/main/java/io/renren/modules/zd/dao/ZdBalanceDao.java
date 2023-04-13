package io.renren.modules.zd.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import io.renren.modules.zd.entity.ZdBalanceEntity;
import io.renren.modules.zd.vo.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface ZdBalanceDao extends BaseMapper<ZdBalanceEntity> {
	
    List<ZdBalanceEntity> queryZdBalanceByHighLevelOrg(Page page, @Param("params") Map<String, Object> params);
    List<ZdBalanceEntity> queryZdBalanceByLowLevelOrg(Page page, @Param("params") Map<String, Object> params);
    List<ZdBalanceEntity> queryZdBalanceByHighLevelOrg(@Param("params") Map<String, Object> params);

	int updaeAuditStatus(@Param("params") Map<String, Object> params);

	int upBalanceStatus(@Param("params") Map<String, Object> params);

    List queryZdBalanceList(Page page,@Param("params") Map<String, Object> params);

    List<ZdBalancePublishVO> listPublishByBalanceId(@Param("balanceId") String balanceId);

    List<ZdBalanceRefundVO> listRefundByBalanceId(@Param("balanceId") String balanceId);

    List<BalanceOrgVO> listBalanceOrg(@Param("params") Map map);

    List<ZdBalanceVO> queryBalancePublish(@Param("semesterCode") String semesterCode,@Param("orgCode") String orgCode);

    List<BalanceExportVO> selectBalanceMergeList(@Param("params")Map<String, Object> params);
}
