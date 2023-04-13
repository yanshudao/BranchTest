package io.renren.modules.zd.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import io.renren.modules.sys.entity.SysSemesterEntity;
import io.renren.modules.zd.entity.ZdStockIncomeEntity;
import io.renren.modules.zd.vo.RefundLimitInfoVO;
import io.renren.modules.zd.vo.ZdBalanceIncomeDetailVO;
import io.renren.modules.zd.vo.ZdBalancePriceVO;
import io.renren.modules.zd.vo.ZdStockIncomeVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 入库
 * 
 * @author lixiang
 * @email 395569664@qq.com
 * @date 2018-03-31 00:14:33
 */
@Mapper
public interface ZdStockIncomeDao extends BaseMapper<ZdStockIncomeEntity> {

    List<ZdStockIncomeVO> selectListPage(Page page,@Param("params") Map<String, Object> params);

    int countByMap(@Param("params") Map map);

    ZdBalancePriceVO getIncomePrice(@Param("list") List<ZdStockIncomeEntity> incomeList);

    ZdStockIncomeEntity selectByForeignId(@Param("foreignId")String foreignId);

    List<ZdBalanceIncomeDetailVO> queryBalanceIncomeDetail(@Param("semesterCode") String semesterCode,@Param("orgCode")String orgCode,
                                                           @Param("companyId")String companyId,@Param("balanceId") String balanceId);

    RefundLimitInfoVO getRefundLimitInfo(@Param("orgCode") String orgCode, @Param("semesterList")List<SysSemesterEntity> semesterList);

    RefundLimitInfoVO getRefundLimitInfo2(@Param("semesterCode") String semesterCode,@Param("orgCode")  String orgCode,@Param("supplierId")  String supplierId);
}
