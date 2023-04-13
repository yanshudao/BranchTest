package io.renren.modules.zd.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.search.vo.StatisticsResourceDetailVO;
import io.renren.modules.search.vo.StatisticsResourceVO;
import io.renren.modules.sys.entity.SysUserEntity;
import io.renren.modules.zd.entity.ZdOrderEntity;
import io.renren.modules.zd.form.ZdCourseForm;
import io.renren.modules.zd.form.ZdOrgResourceForm;
import io.renren.modules.zd.form.ZdResourceForm;
import io.renren.modules.zd.vo.ZdOrderCartVO;

import java.util.List;
import java.util.Map;

/**
 * 征订主表
 *
 * @author lixiang
 * @email 395569664@qq.com
 * @date 2018-03-31 00:14:33
 */
public interface ZdOrderService extends IService<ZdOrderEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void saveOrder(ZdCourseForm zdCourseForm);

    void confirmOder(List<String> orderIds);

    void completeOrder(List<String> orderIds);

    void saveOrderResource(ZdOrgResourceForm zdOrgResourceForm);

    void deleteOrderResource(List<String> id);

    void deleteOrder(List<String> ids);

    void saveOrderByResource(ZdResourceForm zdResourceForm);

    void rejectOrder(List<String> ids);

    PageUtils queryStatisticsByMap(Map<String, Object> params);

    void saveOrderFromProvince(ZdCourseForm zdCourseForm);

    void saveOrderByResourceFromProvince(ZdResourceForm zdResourceForm);

    List<StatisticsResourceVO> queryStatisticsAllByMap(Map<String, Object> params);

    Integer selectCountByMap(Map<String, Object> params);

    List<StatisticsResourceDetailVO> queryStatisticsDetailByMap(Map<String, Object> params);

    void submitCatOrder(List<ZdOrderCartVO> cartEntityList, SysUserEntity user,String status,String note,String remark);

}

