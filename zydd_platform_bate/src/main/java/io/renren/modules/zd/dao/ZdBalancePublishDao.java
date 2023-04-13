package io.renren.modules.zd.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import io.renren.modules.zd.entity.ZdBalancePublishEntity;
import io.renren.modules.zd.vo.ZdBalancePublishDetailVO;
import io.renren.modules.zd.vo.ZdBalancePublishVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 结算明细表
 * 
 * @author lixiang
 * @email 395569664@qq.com
 * @date 2018-06-03 00:39:29
 */
@Mapper
public interface ZdBalancePublishDao extends BaseMapper<ZdBalancePublishEntity> {

    List<ZdBalancePublishDetailVO> queryBalancePublishById(String balanceId);

    List<ZdBalancePublishVO> queryBalancePublish(@Param("semesterCode") String semesterCode,@Param("orgCode") String orgCode);

    List<ZdBalancePublishDetailVO> queryBalancePublishDetail(@Param("semesterCode") String semesterCode,
                                                             @Param("orgCode") String orgCode,@Param("balanceId")String balanceId);
}
