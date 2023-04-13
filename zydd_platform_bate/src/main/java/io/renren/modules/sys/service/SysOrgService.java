package io.renren.modules.sys.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;
import io.renren.modules.subject.entity.request.Customer;
import io.renren.modules.sys.entity.SysOrgEntity;
import io.renren.modules.sys.form.OrgCodeUpdateForm;
import io.renren.modules.sys.form.OrgForm;
import io.renren.modules.zd.vo.*;

import java.util.List;
import java.util.Map;

/**
 * 部门
 *
 * @author lixiang
 * @email 395569664@qq.com
 * @date 2018-03-29 23:15:51
 */
public interface SysOrgService extends IService<SysOrgEntity> {

    PageUtils queryPage(Map<String, Object> params);


    SysOrgEntity selectParentByOrgCode(String orgCode);
    SysOrgEntity selectByOrgCode(String orgCode);

    void saveOrg(OrgForm sysOrg);

    void updateOrg(OrgForm sysOrg);

    List selectChildren(Map map);

    List selectParent(Map<String, Object> queryMap);


    void saveOrUpdateCustomer(Customer customer);

    List<SysOrgEntity> selectByCustomCode(String customerCode);

    /**
     * 中央电大使用 获取结算单位列表
     * @param params
     * @return
     */
    List<RefundOrgCountVO> listAll(Map<String, Object> params);

    List<ZdOrderOrgVO> listOrderOrg(Map<String, Object> params);

    List<StatisticProvinceTOPVO> queryStatisticsTOPByMap(Map<String, Object> params);

    List<StatisticProvinceBalanceVO> queryStatisticsBalanceByMap(Map<String, Object> params);

    PageUtils queryStatisticsPage(Map<String, Object> params);

    List<StatisticProvinceTOPVO> queryStatisticsCountryTOPByMap(Map<String, Object> params);


    PageUtils queryStatisticsCountryPage(Map<String, Object> params);

    List<StatisticProvinceBalanceVO> queryStatisticsBalanceCountryByMap(Map<String, Object> params);

    List<OrderOrgCount> queryOrderList(Map<String, Object> params);

    List<OrderOrgCount> queryNotOrderList(Map<String, Object> params);

    List<StatisticProvinceTOPVO> queryAllStatisticsCountry(Map<String, Object> params);

    boolean deleteOrg(List ids);


    List selectByType(String orgType);


    List<SysOrgEntity> listProvince();


    int syncSemesterCourse(String semesterCode, String orgCode);

    R updateOrgCode(OrgCodeUpdateForm sysOrg);


    List treeList(String pid);

    SysOrgEntity selectChildrenById(String selectPid);

}

