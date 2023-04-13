package io.renren.modules.zd.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.search.vo.StatisticsSourceResourceDetailVO;
import io.renren.modules.search.vo.StatisticsSourceResourceVO;
import io.renren.modules.zd.entity.ZdSourceEntity;
import io.renren.modules.zd.form.SearchForm;
import io.renren.modules.zd.form.ZdOrgSourceForm;
import io.renren.modules.zd.form.ZdSourceForm;
import io.renren.modules.zd.form.ZdSourceResourceForm;
import io.renren.modules.zd.vo.ZdSourceDetailVO;

import java.util.List;
import java.util.Map;

/**
 * 采购主表
 *
 * @author lixiang
 * @email 395569664@qq.com
 * @date 2018-03-31 00:14:33
 */
public interface ZdSourceService extends IService<ZdSourceEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void saveSource(ZdSourceForm zdSource);

    PageUtils queryListPage(Map<String, Object> params);


    PageUtils queryResourceListPage(Map<String, Object> params);

    void completeOrder(List<String> ids);

    String syncOrder(String ids);

    void saveSourceResource(List<ZdSourceResourceForm> zdSourceResourceForms);

    void deleteSourceResource(List<String> ids);

    void deleteSource(List<String> ids);

    void saveSourceByOrg(ZdOrgSourceForm zdOrgSourceForm);

    ZdSourceEntity selectBySourceNo(String id);

    /**
     * 采购入库列表
     * @param params
     * @return
     */
    PageUtils queryResourceIncomePage(Map<String, Object> params);

    PageUtils queryStatisticsByMap(Map<String, Object> params);


    List<StatisticsSourceResourceVO> queryStatisticsAllByMap(Map<String, Object> params);

    int countByMap(Map map);

    List<StatisticsSourceResourceDetailVO> queryStatisticsDetailByMap(Map<String, Object> params);

    List<ZdSourceDetailVO> listResourceAll(SearchForm searchForm);

    void updateVersion(String id, String id1, List<String> orgList,String semesterCode,String zmcrId);

    int updateAddress(ZdSourceEntity zdSourceEntity);
}

