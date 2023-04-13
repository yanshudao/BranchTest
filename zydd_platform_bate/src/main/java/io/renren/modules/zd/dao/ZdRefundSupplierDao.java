package io.renren.modules.zd.dao;

import com.baomidou.mybatisplus.plugins.Page;
import io.renren.modules.search.vo.StatisticsRefundSupplierResourceVO;
import io.renren.modules.zd.entity.ZdRefundOrderListEntity;
import io.renren.modules.zd.entity.ZdRefundSupplierEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import io.renren.modules.zd.vo.ZdBalancePriceVO;
import io.renren.modules.zd.vo.ZdBalanceRefundDetailVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 退货主单据
 * 
 * @author lixiang
 * @email 395569664@qq.com
 * @date 2018-11-26 18:46:17
 */
@Mapper
public interface ZdRefundSupplierDao extends BaseMapper<ZdRefundSupplierEntity> {

    List<ZdRefundOrderListEntity> queryRefundOrderList(Page page,@Param("params") Map<String, Object> params);

    ZdRefundSupplierEntity selectByRefundId(@Param("refundId") String id);

    List<StatisticsRefundSupplierResourceVO> selectStatisticsByMap(Page page,@Param("params") Map<String, Object> params);

    ZdBalancePriceVO getRefundPrice(List<ZdRefundSupplierEntity> refundList);

    List<ZdBalanceRefundDetailVO> queryBalanceRefundDetail(@Param("semesterCode") String semesterCode, @Param("orgCode") String orgCode,@Param("companyId") String companyId, @Param("balanceId") String balanceId);
}
