package io.renren.modules.zd.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import io.renren.modules.zd.entity.ZdBalancePayEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

@Mapper
public interface ZdBalancePayDao extends BaseMapper<ZdBalancePayEntity> {
    


    List<ZdBalancePayEntity> selectPayDetailForAccount(@Param("balanceId")String balanceId);

    BigDecimal getActualToatal(@Param("balanceId") String balanceId);

    BigDecimal getsumAccount(@Param("balanceId") String balanceId);
}