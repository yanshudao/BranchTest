package io.renren.modules.zd.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import io.renren.modules.zd.entity.ZdBalanceCompanyIncomeEntity;
import io.renren.modules.zd.vo.ZdBalanceCompanyIncomeVO;
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
public interface ZdBalanceCompanyIncomeDao extends BaseMapper<ZdBalanceCompanyIncomeEntity> {

    List<ZdBalanceCompanyIncomeVO> listIncomeListByBalanceId(@Param("balanceId") String balanceId);
    List<Map<String,Object>> listIncomeListByBalanceId(@Param("balanceId") String balanceId, String name);
}
