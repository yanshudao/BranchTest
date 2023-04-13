package io.renren.modules.zd.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import io.renren.modules.search.vo.StatisticsRefundResourceVO;
import io.renren.modules.sys.entity.SysSemesterEntity;
import io.renren.modules.zd.entity.*;
import io.renren.modules.zd.vo.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 退货主单据
 * 
 * @author lixiang
 * @email 395569664@qq.com
 * @date 2018-03-31 00:14:33
 */
@Mapper
public interface ZdRefundDao extends BaseMapper<ZdRefundEntity> {

    ZdRefundOrgInfoEntity queryRefundOrgInfo(String orgCode);

    List<ZdRefundableResourceEntity> queryRefundableResource(Page page, @Param("params") Map<String, Object> params);

    Integer queryTotalNumByResId(String resourceId);

    Integer queryRefundedNumByResId(String resId);

    Integer queryApprovingNumByResId(String resId);

    Integer querySemePublishedNumByResId(String resId, String semesterCode);

    Integer queryRefundablePublishedNumByResId(String resId);




    int reportRefundOrder(String refundCode);

    List<ZdRefundOrderListEntity> queryRefundOrderList(Page<ZdRefundOrderListEntity> page, @Param("params") Map<String,Object> params);

    List<ZdRefundResourceDetailEntity> queryRefundResourcesList(Page page,@Param("params") Map<String,Object> params);
    List<ZdRefundResourceDetailEntity> queryRefundResourcesList(@Param("params") Map<String,Object> params);

    long selectLastInsertPkId(String refundCode);

    List<String> getRefundOrgInfo();


    int updateStatus(String param);


    int countResourceKinds(String refundCode);

    Integer sumRefundNum(String refundCode);

    Integer sumRefundMaYang(String refundCode);

    Integer countPublishNumByResourceId(String resourceId);

    RefundCheckVo getResourceNumAndMayangByRefundid(String id);

    String getUserIdByOrgCode(String orgCode);

    int updateOrgCode(String orgCode, String id);

    int updateByRefundCode(String refundCode);

    int reportRefundOrderById(String refundId);

    ZdRefundEntity selectByRefundCode(String id);

    List<StatisticsRefundResourceVO> selectStatisticsByMap(Page page, @Param("params") Map<String, Object> params);
    List<StatisticsRefundResourceVO> selectStatisticsByMap( @Param("params") Map<String, Object> params);

    ZdBalancePriceVO getRefundPrice(@Param("list") List<ZdRefundEntity> refundList);


    List<RefundOrgCountVO> selectRefundCount(@Param("params") Map<String, Object> params);

    Integer selectCountByMap(@Param("params") Map<String, Object> params);

    Integer selectZyddCountByMap(@Param("params") Map<String, Object> params);

    List<ZdBalanceRefundDetailVO> queryBalanceRefundDetail(@Param("semesterCode") String semesterCode,
                                                           @Param("orgCode")String orgCode,@Param("companyId")String companyId,@Param("balanceId") String balanceId);

    int selectRefundResourceLimit(@Param("orgCode") String orgCode,
                                 @Param("semesterList") List<SysSemesterEntity> semesterEntityList,
                                  @Param("resourceId") String resourceId,@Param("semesterCode") String semesterCode,@Param("toOrgCode") String toOrgCode);

    List<ZdCountyRefundResourceVO> listRefundResource(Page page,@Param("params") Map<String, Object> params);

    List<ZdRefundEntity> selectByBalanceId(@Param("balanceId") String id);

    int selectRefundResourceLimit2(@Param("refundSemesterCode")String refundSemesterCode, @Param("orgCode")String orgCode, @Param("toOrgCode")String toOrgCode, @Param("resourceId") String resourceId);
}
