package io.renren.modules.subject.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.subject.entity.SubjectResourceEntity;
import io.renren.modules.subject.form.UpdateResourceVersionForm;
import io.renren.modules.subject.form.UpdateScopeForm;
import io.renren.modules.sys.entity.SysUserEntity;

import java.util.List;
import java.util.Map;

/**
 * 教材
 *
 * @author lixiang
 * @email 395569664@qq.com
 * @date 2018-03-29 23:29:28
 */
public interface SubjectResourceService extends IService<SubjectResourceEntity> {

    PageUtils queryPage(Map<String, Object> params);

    PageUtils queryListPage(Map<String, Object> params);

    PageUtils queryListResourcePage(Map<String, Object> params);

    SubjectResourceEntity selectByForeignId(String foreignId);

    PageUtils queryZdResourceByMap(Map<String, Object> params);

    PageUtils queryAllResourceByMap(Map<String, Object> params);

    void deleteByByForeignId(String id);

    PageUtils queryOrgResourceByMap(Map<String, Object> params);

  //  void updateCurrent(SubjectResourceEntity subjectResource);
    void updateCurrent(SubjectResourceEntity ytsResource,SubjectResourceEntity newResource);
    void updateVersion(SubjectResourceEntity ytsResource,SubjectResourceEntity newResource);

    void updateVersion(UpdateResourceVersionForm updateResourceVersionForm);

    SubjectResourceEntity selectByCode(String ytscode);

    void deleteResource(List ids);


    PageUtils queryMajorCoursePage(Map<String, Object> params);


    PageUtils queryOrgResourceByMap1(Map<String, Object> params);

    List<String> listCatalog(Map<String, Object> params);

    boolean updateScope(UpdateScopeForm updateScopeForm);

    void updateResource(SubjectResourceEntity subjectResource);

    int countMap(String id, String orgCode);

    List<SubjectResourceEntity> listAll(Map<String, Object> params);

    List<SubjectResourceEntity> importResource(List<SubjectResourceEntity> list, SysUserEntity user);

}

