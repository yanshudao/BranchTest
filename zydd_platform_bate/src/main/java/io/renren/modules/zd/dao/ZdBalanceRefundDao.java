package io.renren.modules.zd.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import io.renren.modules.zd.entity.ZdBalanceRefundEntity;
import io.renren.modules.zd.vo.ZdBalanceRefundDetailVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 
 * 
 * @author lixiang
 * @email 395569664@qq.com
 * @date 2018-06-03 00:39:29
 */
@Mapper
public interface ZdBalanceRefundDao extends BaseMapper<ZdBalanceRefundEntity> {

    List<ZdBalanceRefundDetailVO> queryBalanceRefundById(String balanceId);

    List<ZdBalanceRefundDetailVO> queryBalanceRefundDetail(@Param("semesterCode") String semesterCode,@Param("orgCode") String orgCode,@Param("balanceId")String balanceId);

}
