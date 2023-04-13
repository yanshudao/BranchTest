package io.renren.modules.zd.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import io.renren.modules.zd.entity.ZdRefundResourceEntity;
import io.renren.modules.zd.vo.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 退货详情
 * 
 * @author lixiang
 * @email 395569664@qq.com
 * @date 2018-03-31 00:14:33
 */
@Mapper
public interface ZdRefundResourceDao extends BaseMapper<ZdRefundResourceEntity> {

    int updateRefundRealNum(@Param("params") Map<String, Object> params);

    List<RefundResourceVo> queryRefundResourceInfoPage(Page<RefundResourceVo> page, @Param("params") Map<String, Object> params);
    List<RefundResourceVo> queryRefundResourceInfoPage(@Param("params") Map<String, Object> params);

    List<RefundResourceInfoVo> getRefundResourceinfoForList(Page<RefundResourceInfoVo> page, @Param("params") Map<String, Object> params);

    List<ZdRefundResourceEntity> listRefundResourcesByRefundId(String refundId);


    List<ZdRefundResourceEntity> selectByRefundId(@Param("refundId") String id);

    List<ZdBalanceRefundDetailVO> queryBalanceRefundDetail(String balanceId);

    List<ZdRefundResourceGroup> selectGroupByRefundId(@Param("refundId") String refundId);

    int deleteByRefundId(@Param("refundId") String id);

    List<ZdCountyRefundResourceVO> listResourceByRefundId(@Param("refundId") String refundId);

}
