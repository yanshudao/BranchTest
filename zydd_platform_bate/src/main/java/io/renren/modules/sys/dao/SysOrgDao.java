package io.renren.modules.sys.dao;

import com.baomidou.mybatisplus.plugins.Page;
import io.renren.modules.sys.entity.SysOrgEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import io.renren.modules.sys.vo.SysOrgVO;
import io.renren.modules.zd.vo.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 部门
 * 
 * @author lixiang
 * @email 395569664@qq.com
 * @date 2018-03-29 23:15:51
 */
@Mapper
public interface SysOrgDao extends BaseMapper<SysOrgEntity> {

    SysOrgEntity selectParentByOrgCode(@Param("orgCode") String orgCode);

    SysOrgEntity selectByOrgCode(String orgCode);

    List<String> selectIdByParentId(String id);
    List<String> selectCodeByParentId(String id);

    List selectChildren(@Param("params") Map map);

    SysOrgEntity selectByParentId(@Param("parentId") String parentId);


    List selectParent(@Param("params") Map<String, Object> queryMap);



    List queryOrgByType(@Param("params") Map<String, Object> params);

    List<String> getRefundOrgInfotWithoutFilter(String user_id);
    PublicshCourseOrderResultVO selectSettiingByOrgCode (@Param("orgCode") String orgCode);

    List<SysOrgEntity> getAllOrgInfo();


    List<SysOrgEntity> selectByCustomCode(@Param("custcode") String custcode);

    List<RefundOrgCountVO> listAll(@Param("params") Map<String, Object> params);

    List<ZdOrderOrgVO> listOrderOrg(@Param("params") Map<String, Object> params);

    List<StatisticProvinceTOPVO> selectStatisticsTOPByMap(@Param("params") Map<String, Object> params);
    List<StatisticProvinceTOPVO> selectStatisticsTOPByMap(Page page, @Param("params") Map<String, Object> params);

    List<StatisticProvinceBalanceVO> selectStatisticsBalanceByMap(@Param("params")Map<String, Object> params);

    List<StatisticProvinceTOPVO> selectStatisticsCountryTOPByMap(@Param("params") Map<String, Object> params);
    List<StatisticProvinceTOPVO> selectStatisticsCountryTOPByMap(Page page,@Param("params") Map<String, Object> params);

    List<StatisticProvinceBalanceVO> selectStatisticsBalanceCountryByMap(@Param("params")Map<String, Object> params);

    List<OrderOrgCount> selectOrderList(@Param("params") Map<String, Object> params);

    List<OrderOrgCount> selectNotOrderList(@Param("params") Map<String, Object> params);

    SysOrgVO selectVOByOrgCode(@Param("orgCode") String orgCode);

    List selectByType(@Param("orgType") String orgType);

    List selectChildrenByPId(@Param("pid")String pid);
    SysOrgEntity selectChildrenById(@Param("id")String id);
}
