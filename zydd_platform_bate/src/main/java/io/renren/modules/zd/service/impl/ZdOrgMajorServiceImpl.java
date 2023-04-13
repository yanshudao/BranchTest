package io.renren.modules.zd.service.impl;

import io.renren.common.utils.ShiroUtils;
import io.renren.modules.sys.entity.SysOrgEntity;
import io.renren.modules.sys.entity.SysSemesterEntity;
import io.renren.modules.sys.entity.SysUserEntity;
import io.renren.modules.sys.service.SysOrgService;
import io.renren.modules.sys.service.SysSemesterService;
import io.renren.modules.zd.vo.OrgMajorVO;
import io.renren.modules.zd.vo.OrgZdMajorVO;
import io.renren.modules.zd.vo.OrgZdVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.*;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.zd.dao.ZdOrgMajorDao;
import io.renren.modules.zd.entity.ZdOrgMajorEntity;
import io.renren.modules.zd.service.ZdOrgMajorService;
import org.springframework.transaction.annotation.Transactional;


@Service("zdOrgMajorService")
public class ZdOrgMajorServiceImpl extends ServiceImpl<ZdOrgMajorDao, ZdOrgMajorEntity> implements ZdOrgMajorService {

    @Autowired
    private ZdOrgMajorDao zdOrgMajorDao;
    @Autowired
    private SysOrgService sysOrgService;
    @Autowired
    private SysSemesterService sysSemesterService;
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<OrgMajorVO> page=new Query<OrgMajorVO>(params).getPage();
        page.setRecords(zdOrgMajorDao.selectByList(page,params));
        return new PageUtils(page);
    }

    @Override
    public PageUtils queryByOrg(Map<String, Object> params) {
        Page<OrgMajorVO> page=new Query<OrgMajorVO>(params).getPage();
        page.setRecords(zdOrgMajorDao.selectByOrg(page,params));
        return new PageUtils(page);
    }

    @Override
    public PageUtils queryBySheng(Map<String, Object> params) {
        Page<OrgMajorVO> page=new Query<OrgMajorVO>(params).getPage();
        page.setRecords(zdOrgMajorDao.selectBySheng(page,params));
        return new PageUtils(page);
    }

    @Override
    public List queryPageGroup(Map<String, Object> params) {
        List<OrgZdVO> list=new ArrayList<>();
        List<OrgZdVO> orgTypeList=zdOrgMajorDao.selectPageGroup(params);
        for(OrgZdVO orgZdVO:orgTypeList)
        {

            params.put("majorType",orgZdVO.getMajorType());
            params.put("studentType",orgZdVO.getStudentType());
            List<OrgZdMajorVO> majorVOS=zdOrgMajorDao.selectMajorByMap(params);
//            orgZdVO.setMajorType(majorType);
            orgZdVO.setList(majorVOS);
            list.add(orgZdVO);
        }
        return list;
    }

    @Override
    @Transactional
    public boolean deleteBatchIds(Collection<? extends Serializable> idList) {
        SysUserEntity sysUserEntity=ShiroUtils.getUserEntity();
        SysSemesterEntity sysSemesterEntity=sysSemesterService.getCurrentSemester();
       /* Map<String,Object> queryMap=new HashMap<>();
        queryMap.put("orgCode", sysUserEntity.getOrgCode());
//        queryMap.put("parentId",parentId);
//        queryMap.put("orgType",orgType);
        List<SysOrgEntity> orgEntities = sysOrgService.selectChildren(queryMap);
        if(orgEntities.size()>0)
        {
            //清除下级单位开设
            List<ZdOrgMajorEntity> zdOrgMajorEntities=zdOrgMajorDao.selectBatchIds(idList);
            for(SysOrgEntity sysOrgEntity:orgEntities)
            {
                for(ZdOrgMajorEntity zdOrgMajorEntity:zdOrgMajorEntities)
                {
                    List<String> ids=zdOrgMajorDao.selectIdsByMap(sysSemesterEntity.getSemesterCode(),sysOrgEntity.getOrgCode(),zdOrgMajorEntity.getMajorId());

                    if(ids.size()>0)
                    {
                        super.deleteBatchIds(ids);
                    }
                }

            }

        }*/
       List<ZdOrgMajorEntity> zdOrgMajorEntities=zdOrgMajorDao.selectBatchIds(idList);
        zdOrgMajorDao.deleteChildrenBatchIds(zdOrgMajorEntities,sysUserEntity.getOrgCode(),sysSemesterEntity.getSemesterCode());
        return super.deleteBatchIds(idList);
    }
}
