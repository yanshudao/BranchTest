package io.renren.modules.zd.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.zd.entity.ZdOrderResourceEntity;
import io.renren.modules.zd.form.SearchForm;
import io.renren.modules.zd.vo.OrderResourceVO;

import java.util.List;
import java.util.Map;

/**
 * 征订明细
 *
 * @author lixiang
 * @email 395569664@qq.com
 * @date 2018-03-31 00:14:32
 */
public interface ZdOrderResourceService extends IService<ZdOrderResourceEntity> {

    PageUtils queryPage(Map<String, Object> params);

    PageUtils queryListPage(Map<String, Object> params);

    List queryAllList(Map<String, Object> params);

    List<OrderResourceVO> queryAllOrderList(Map<String, Object> params);

    PageUtils queryListPage1(Map<String, Object> params);

    int countZMCRIds(List ids);


    int queryOrderSumByIds(String[] split);

    List<OrderResourceVO> listAll(SearchForm searchForm);

    void updateVersion(String id, String id1, List<String> orgList,String semesterCode);

}

