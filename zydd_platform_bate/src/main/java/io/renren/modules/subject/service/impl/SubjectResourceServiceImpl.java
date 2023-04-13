package io.renren.modules.subject.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.common.exception.RRException;
import io.renren.common.utils.Constant;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;
import io.renren.common.utils.ShiroUtils;
import io.renren.modules.subject.dao.SubjectCourseResourceDao;
import io.renren.modules.subject.dao.SubjectResourceDao;
import io.renren.modules.subject.entity.SubjectResourceEntity;
import io.renren.modules.subject.entity.SubjectResourceScopeEntity;
import io.renren.modules.subject.form.UpdateResourceVersionForm;
import io.renren.modules.subject.form.UpdateScopeForm;
import io.renren.modules.subject.service.SubjectResourceScopeService;
import io.renren.modules.subject.service.SubjectResourceService;
import io.renren.modules.subject.vo.MajorCourseResourceVO;
import io.renren.modules.subject.vo.ZdOrgResourceVO;
import io.renren.modules.sys.entity.SysSemesterEntity;
import io.renren.modules.sys.entity.SysUserEntity;
import io.renren.modules.sys.service.SysSemesterService;
import io.renren.modules.zd.dao.ZdOrderResourceDao;
import io.renren.modules.zd.dao.ZdOrgCourseResourceDao;
import io.renren.modules.zd.dao.ZdPublishResourceDao;
import io.renren.modules.zd.dao.ZdSourceResourceDao;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service("subjectResourceService")
public class SubjectResourceServiceImpl extends ServiceImpl<SubjectResourceDao, SubjectResourceEntity> implements SubjectResourceService {

    @Autowired
    private SubjectResourceDao subjectResourceDao;

    @Autowired
    private SubjectCourseResourceDao subjectCourseResourceDao;
    @Autowired
    private ZdSourceResourceDao zdSourceResourceDao;
    @Autowired
    private ZdOrderResourceDao zdOrderResourceDao;
    @Autowired
    private ZdOrgCourseResourceDao zdOrgCourseResourceDao;
    @Autowired
    private ZdPublishResourceDao zdPublishResourceDao;
    @Autowired
    private SysSemesterService sysSemesterService;
    @Autowired
    private SubjectResourceScopeService subjectResourceScopeService;
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<SubjectResourceEntity> page = this.selectPage(
                new Query<SubjectResourceEntity>(params).getPage(),
                new EntityWrapper<SubjectResourceEntity>()
        );

        return new PageUtils(page);
    }

    /*@Override
    @Cacheable(value = Constant.CACHE_NAMESPACE + "RESOURCE",key = "'RESOURCE:'+#p0")
    public SubjectResourceEntity selectById(Serializable id) {
        return super.selectById(id);
    }*/

    @Override
//    @Cacheable(value = Constant.CACHE_NAMESPACE + "SUBJECT_RESOURCE")
    public PageUtils queryListPage(Map<String, Object> params) {
        Page<SubjectResourceEntity> page=new Query<SubjectResourceEntity>(params).getPage();
        page.setRecords(subjectResourceDao.selectListPage(page,params));
        return new PageUtils(page);
    }

    @Override
    public PageUtils queryListResourcePage(Map<String, Object> params) {
        Page<SubjectResourceEntity> page=new Query<SubjectResourceEntity>(params).getPage();
        page.setRecords(subjectResourceDao.selectListResourcePage(page,params));
        return new PageUtils(page);
    }

    @Override
    public SubjectResourceEntity selectByForeignId(String foreignId) {
        return subjectResourceDao.selectByForeignId(foreignId);
    }

    @Override
    public PageUtils queryZdResourceByMap(Map<String, Object> params) {
        Page<ZdOrgResourceVO> page=new Query<ZdOrgResourceVO>(params).getPage();
        page.setRecords(subjectResourceDao.selectZdResourceByMap(page,params));
        return new PageUtils(page);
    }

    @Override
//    @CacheEvict(value = Constant.CACHE_NAMESPACE + "SUBJECT_RESOURCE",allEntries = true)
//    @CachePut(value = Constant.CACHE_NAMESPACE + "RESOURCE",key = "'RESOURCE:'+#entity.id")
    public boolean update(SubjectResourceEntity entity, Wrapper<SubjectResourceEntity> wrapper) {
        return super.update(entity, wrapper);
    }

    @Override
    public PageUtils queryAllResourceByMap(Map<String, Object> params) {
        Page<ZdOrgResourceVO> page=new Query<ZdOrgResourceVO>(params).getPage();
        page.setRecords(subjectResourceDao.selectAllResourceByMap(page,params));
        return new PageUtils(page);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
//    @CacheEvict(value = Constant.CACHE_NAMESPACE + "SUBJECT_RESOURCE",allEntries = true)
    public void deleteByByForeignId(String id) {
        subjectResourceDao.deleteByByForeignId(id);
    }

    @Override
    public PageUtils queryOrgResourceByMap(Map<String, Object> params) {
        Page<ZdOrgResourceVO> page=new Query<ZdOrgResourceVO>(params).getPage();
        page.setRecords(subjectResourceDao.selectOrgResourceByMap(page,params));
        return new PageUtils(page);
    }
    @Override
    public PageUtils queryOrgResourceByMap1(Map<String, Object> params) {
        Page<ZdOrgResourceVO> page=new Query<ZdOrgResourceVO>(params).getPage();
        page.setRecords(subjectResourceDao.selectOrgResourceByMap1(page,params));
        return new PageUtils(page);
    }

    @Override
    public List<String> listCatalog(Map<String, Object> params) {
        return subjectResourceDao.listCatalog(params);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
//    @CacheEvict(value = Constant.CACHE_NAMESPACE + "SUBJECT_RESOURCE",allEntries = true)
    public boolean updateScope(UpdateScopeForm updateScopeForm) {
        List<String> ids=updateScopeForm.getResourceList().stream().map(SubjectResourceEntity::getId).collect(Collectors.toList());
        if(updateScopeForm.getScopeList()==null||updateScopeForm.getScopeList().size()==0){
            SubjectResourceEntity subjectResourceEntity=new SubjectResourceEntity();
            subjectResourceEntity.setResourceScope(Constant.RESOURCE_SCOPE.ALL);
            update(subjectResourceEntity,new EntityWrapper<SubjectResourceEntity>().in("id_",ids));
            subjectResourceScopeService.delete(new EntityWrapper<SubjectResourceScopeEntity>().in("resource_id",ids));
        }else{

            updateScopeForm.getResourceList().stream().forEach(item->{
                List<SubjectResourceScopeEntity> list=new ArrayList<>();
                if(org.apache.commons.lang3.StringUtils.isNotBlank(item.getId())&&updateScopeForm.getScopeList()!=null&&updateScopeForm.getScopeList().size()>0){
                    subjectResourceScopeService.delete(new EntityWrapper<SubjectResourceScopeEntity>().eq("resource_id",item.getId()));
                    updateScopeForm.getScopeList().forEach(scope->{
                        SubjectResourceScopeEntity subjectResourceScopeEntity=new SubjectResourceScopeEntity();
                        subjectResourceScopeEntity.setResourceId(item.getId());
                        subjectResourceScopeEntity.setOrgId(scope.getOrgId());
                        list.add(subjectResourceScopeEntity);
                    });
                    subjectResourceScopeService.insertBatch(list);
                }else{
                    throw new RRException("未指定范围！");
                }


            });
            if(ids.size()>0){
                SubjectResourceEntity subjectResourceEntity=new SubjectResourceEntity();
                subjectResourceEntity.setResourceScope(Constant.RESOURCE_SCOPE.CUSTOM);
                update(subjectResourceEntity,new EntityWrapper<SubjectResourceEntity>().in("id_",ids));
            }
        }
        return true;
    }

    @Override
//    @CacheEvict(value = Constant.CACHE_NAMESPACE + "SUBJECT_RESOURCE",allEntries = true)
//    @CachePut(value = Constant.CACHE_NAMESPACE + "RESOURCE",key = "'RESOURCE:'+#subjectResource.id")
    public void updateResource(SubjectResourceEntity subjectResource) {
        if(subjectResource.getScopeList()==null||subjectResource.getScopeList().size()<=0){
            subjectResource.setResourceScope(Constant.RESOURCE_SCOPE.ALL);
        }else{
            subjectResource.setResourceScope(Constant.RESOURCE_SCOPE.CUSTOM);
        }
        SubjectResourceEntity update=baseMapper.selectById(subjectResource.getId());
        BeanUtils.copyProperties(subjectResource,update);
        insertOrUpdateAllColumn(update);
        subjectResourceScopeService.delete(new EntityWrapper<SubjectResourceScopeEntity>().eq("resource_id",subjectResource.getId()));
        if(subjectResource.getScopeList().size()>0){
            subjectResource.getScopeList().stream().forEach(item->{
                item.setResourceId(subjectResource.getId());
            });
            subjectResourceScopeService.insertBatch(subjectResource.getScopeList());
        }

    }

    @Override
    public int countMap(String id, String orgCode) {
        return baseMapper.countMap(id,orgCode);
    }

    @Override
    public List<SubjectResourceEntity> listAll(Map<String, Object> params) {
        return subjectResourceDao.selectListPage(params);
    }

    @Override
    @Transactional
    public List<SubjectResourceEntity> importResource(List<SubjectResourceEntity> list, SysUserEntity user) {
         List<SubjectResourceEntity> updateBatch1=new ArrayList<>();
         List<SubjectResourceEntity> updateBatch0=new ArrayList<>();
        for(SubjectResourceEntity subjectResourceEntity:list){
            if(StringUtils.isBlank(subjectResourceEntity.getResourceCode())){
                subjectResourceEntity.setResult("教材代码不能为空");
                continue;
            }
            subjectResourceEntity.setResult("成功");
//            SubjectResourceEntity entity=baseMapper.selectByCode(subjectResourceEntity.getResourceCode());
          /*  if(entity==null){
              entity=new SubjectResourceEntity();
            }*/
            if("1".equals(subjectResourceEntity.getResourceType2())){
                updateBatch1.add(subjectResourceEntity);
            }else{
                updateBatch0.add(subjectResourceEntity);
            }
           /* entity.setPrice(subjectResourceEntity.getPrice());
            entity.setResourceName(subjectResourceEntity.getResourceName());
            entity.setIsShow(subjectResourceEntity.getIsShow());
            entity.setIsbn(subjectResourceEntity.getIsbn());
            entity.setResourceType(subjectResourceEntity.getResourceType());
            entity.setAuthor(subjectResourceEntity.getAuthor());
            entity.setBcBs(subjectResourceEntity.getBcBs());
            entity.setPublishDate(subjectResourceEntity.getPublishDate());
            entity.setResourceType2(subjectResourceEntity.getResourceType2());*/
           /* if(StringUtils.isNotBlank(subjectResourceEntity.getResourceType2())){
                Constant.resourceType2Map.getKey(StringUtils.trim(subjectResourceEntity.getResourceType2())));
            }*/

          /*  if(StringUtils.isNotBlank(entity.getId())){
                updateBatch.add(entity);
            }else{
                saveBatch.add(entity);
            }*/

        }
        /*if(CollectionUtils.isNotEmpty(saveBatch)){
            insertBatch(saveBatch,1000);
        }
        if(CollectionUtils.isNotEmpty(updateBatch)){
            updateBatchById(updateBatch,1000);
        }*/
        if(CollectionUtils.isNotEmpty(updateBatch1)){
            baseMapper.updateResourceType2("1",updateBatch1);
        }
        if(CollectionUtils.isNotEmpty(updateBatch0)){
            baseMapper.updateResourceType2("0",updateBatch0);
        }
        return list;
    }

    /* @Override
     @Transactional(rollbackFor = Exception.class)
     @CacheEvict(value = Constant.CACHE_NAMESPACE + "SUBJECT_RESOURCE",allEntries = true)
     public void updateCurrent(SubjectResourceEntity subjectResource) {
         SubjectResourceEntity yts=subjectResourceDao.selectByForeignId(subjectResource.getForeignId());
         if(yts!=null)
         {
             yts.setResourceCode(subjectResource.getResourceCode());
             yts.setResourceName(subjectResource.getResourceName());
             yts.setPrice(subjectResource.getPrice());
             yts.setForeignId(subjectResource.getId());
             subjectResourceDao.updateById(yts);
         }
         else
         {
             yts=new SubjectResourceEntity();
             yts.setResourceCode(subjectResource.getResourceCode());
             yts.setResourceName(subjectResource.getResourceName());
             yts.setPrice(subjectResource.getPrice());
             yts.setForeignId(subjectResource.getId());
             subjectResourceDao.insert(yts);
         }
     }
 */
    @Override
//    @SysLog("教材准转现")
    @Transactional(rollbackFor = Exception.class)
//    @CacheEvict(value = Constant.CACHE_NAMESPACE + "SUBJECT_RESOURCE",allEntries = true)

    public void updateCurrent(SubjectResourceEntity ytsResource, SubjectResourceEntity newResource) {
        SubjectResourceEntity yts=subjectResourceDao.selectByCode(ytsResource.getResourceCode());
        if(yts!=null)
        {
            SubjectResourceEntity book=subjectResourceDao.selectByCode(newResource.getResourceCode());
            if(book==null)
            {
                //原图书不为空 新图书为空  直接替换原图书为新图书
                yts.setResourceCode(newResource.getResourceCode());
                yts.setResourceName(newResource.getResourceName());
                yts.setPrice(newResource.getPrice());
                yts.setForeignId(newResource.getId());
                subjectResourceDao.updateById(yts);
                //更新采购单价格
                zdSourceResourceDao.updatePrice(yts);


            }else
            {
                book.setPrice(newResource.getPrice());
                //原图书不为空 新图书不为空 替换原图书所有关系到新图书
//               int relaCount=subjectCourseResourceDao.updateVersion(yts.getId(),book.getId());
//               int orderCount=zdOrderResourceDao.updateVersion(yts.getId(),book.getId());
//               int sourceCount=zdSourceResourceDao.updateVersion(yts.getId(),book.getId());
               zdSourceResourceDao.updatePrice(book);
               yts.setDeleteFlag("1");
               subjectResourceDao.updateById(book);
               subjectResourceDao.updateById(yts);
            }
        }
        else
        {
            SubjectResourceEntity book=subjectResourceDao.selectByCode(newResource.getResourceCode());
            if(book==null)
            {
                book=new SubjectResourceEntity();
                book.setResourceScope(Constant.RESOURCE_SCOPE.ALL);
                book.setIsShow(Constant.RESOURCE_SHOW.SHOW);
            }
            book.setResourceCode(newResource.getResourceCode());
            book.setResourceName(newResource.getResourceName());
            book.setPrice(newResource.getPrice());
            book.setOrgCode(newResource.getOrgCode());
            book.setSupplierId(newResource.getSupplierId());
            book.setForeignId(newResource.getId());
            if(StringUtils.isBlank(book.getId()))
            {
                subjectResourceDao.insert(book);
            }else
            {
                subjectResourceDao.updateById(book);
            }

        }
    }

    @Override
//    @SysLog("教材换版")
    @Transactional(rollbackFor = Exception.class)
//    @CacheEvict(value = Constant.CACHE_NAMESPACE + "SUBJECT_RESOURCE",allEntries = true)
    public void updateVersion(SubjectResourceEntity ytsResource, SubjectResourceEntity newResource) {
        SubjectResourceEntity yts=subjectResourceDao.selectByCode(ytsResource.getResourceCode());
        if(yts==null)
        {
            throw new RRException("未找到原图书");

        }
        SubjectResourceEntity book=subjectResourceDao.selectByCode(newResource.getResourceCode());
        if(book==null)
        {
            if(newResource.getPrice()<=0)
            {
                throw new RRException("价格不能为0");
            }
            //原图书不为空 新图书为空  直接替换原图书为新图书
            yts.setResourceCode(newResource.getResourceCode());
            yts.setResourceName(newResource.getResourceName());
            yts.setPrice(newResource.getPrice());
            yts.setForeignId(newResource.getId());
            yts.setOldResourceId(yts.getId());
            subjectResourceDao.updateById(yts);
        }
        else
        {
            //原图书不为空 新图书不为空 替换原图书所有关系到新图书
            int relaCount=subjectCourseResourceDao.updateVersion(yts.getId(),book.getId());
            book.setOldResourceId(yts.getId());
            book.setResourceName(newResource.getResourceName());
            book.setResourceCode(newResource.getResourceCode());
            book.setPrice(newResource.getPrice());
            book.setForeignId(newResource.getForeignId());
            yts.setDeleteFlag("1");
            subjectResourceDao.updateById(yts);
            subjectResourceDao.updateById(book);
        }


    }



    @Override
    @Transactional(rollbackFor = Exception.class)
//    @CacheEvict(value = Constant.CACHE_NAMESPACE + "SUBJECT_RESOURCE",allEntries = true)
    public void updateVersion(UpdateResourceVersionForm updateResourceVersionForm) {
        SubjectResourceEntity ytsResource=baseMapper.selectByCode(updateResourceVersionForm.getOldResource());
        SubjectResourceEntity newResource=baseMapper.selectByCode(updateResourceVersionForm.getNewResource());
        if(ytsResource==null)
        {
            throw new RRException("旧书没找到！");
        }
        if(newResource==null)
        {
            throw new RRException("新书没找到！");
        }
        SysUserEntity sysUserEntity= ShiroUtils.getUserEntity();
        SysSemesterEntity sysSemesterEntity=sysSemesterService.getCurrentSemester();
        if(sysUserEntity!=null&&StringUtils.isNotBlank(sysUserEntity.getOrgCode()))
        {
            if("1".equals(updateResourceVersionForm.getOrderStatus()))
            {
                zdOrderResourceDao.updateVersion(ytsResource.getId(),newResource.getId(),
                        sysSemesterEntity.getSemesterCode(),sysUserEntity.getOrgCode(),updateResourceVersionForm.getMajorId(),updateResourceVersionForm.getCourseId());
            }
          /*  if("1".equals(updateResourceVersionForm.getSourceStatus()))
            {
                zdSourceResourceDao.updateVersion(ytsResource.getId(),newResource.getId(),
                        sysSemesterEntity.getSemesterCode(),sysUserEntity.getOrgCode(),updateResourceVersionForm.getMajorId(),updateResourceVersionForm.getCourseId());
            }*/
        }else
        {
            throw new RRException("未获取到登录人单位信息");
        }


    }

    @Override
    public SubjectResourceEntity selectByCode(String ytscode) {
        return subjectResourceDao.selectByCode(ytscode);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
//    @CacheEvict(value = Constant.CACHE_NAMESPACE + "SUBJECT_RESOURCE",allEntries = true)
    public void deleteResource(List ids) {
        int orderCount=zdOrderResourceDao.countByResourceIds(ids);
        if(orderCount>0)
        {
            throw new RRException("存在报订单据关联此教材，删除失败！");
        }
        int publishCount=zdPublishResourceDao.countByResourceIds(ids);
        if(publishCount>0)
        {
            throw new RRException("存在发行单据关联此教材，删除失败！");
        }
        int sourceCount=zdSourceResourceDao.countByResourceIds(ids);
        if(sourceCount>0)
        {
            throw new RRException("存在采购单据关联此教材，删除失败！");
        }
        subjectResourceDao.deleteBatchIds(ids);
        subjectCourseResourceDao.deleteByResourceIds(ids);
        zdOrgCourseResourceDao.deleteByResourceIds(ids);
    }



    @Override
//    @CacheEvict(value = Constant.CACHE_NAMESPACE + "SUBJECT_RESOURCE",allEntries = true)
    public boolean insert(SubjectResourceEntity entity) {
        return super.insert(entity);
    }

    @Override
//    @CacheEvict(value = Constant.CACHE_NAMESPACE + "SUBJECT_RESOURCE",allEntries = true)
    public boolean deleteBatchIds(Collection<? extends Serializable> idList) {
        return super.deleteBatchIds(idList);
    }

    @Override
//    @CacheEvict(value = Constant.CACHE_NAMESPACE + "SUBJECT_RESOURCE",allEntries = true)
    public boolean updateById(SubjectResourceEntity entity) {
        return super.updateById(entity);
    }

    @Override
    public PageUtils queryMajorCoursePage(Map<String, Object> params) {
        Page<MajorCourseResourceVO> page=new Query<MajorCourseResourceVO>(params).getPage();
        page.setRecords(subjectResourceDao.selectMajorCoursePage(page,params));
        return new PageUtils(page);
    }


}
