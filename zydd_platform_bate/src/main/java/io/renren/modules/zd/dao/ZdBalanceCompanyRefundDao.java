package io.renren.modules.zd.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import io.renren.modules.zd.entity.ZdBalanceCompanyRefundEntity;
import io.renren.modules.zd.vo.ZdBalanceCompanyRefundVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author lixiang
 * @email 395569664@qq.com
 * @date 2018-06-10 09:18:28
 */
@Mapper
public interface ZdBalanceCompanyRefundDao extends BaseMapper<ZdBalanceCompanyRefundEntity> {

    List<ZdBalanceCompanyRefundVO> listRefundListByBalanceId(@Param("balanceId") String balanceId);
    List<Map<String,Object>> listRefundListByBalanceId(@Param("balanceId") String balanceId,String name);
}
