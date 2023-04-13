package io.renren.modules.zd.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;
import io.renren.modules.zd.dao.ZdOrderDao;
import io.renren.modules.zd.dao.ZdOrderResourceDao;
import io.renren.modules.zd.entity.ZdOrderResourceEntity;
import io.renren.modules.zd.form.SearchForm;
import io.renren.modules.zd.service.ZdOrderResourceService;
import io.renren.modules.zd.vo.OrderResourceVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;


@Service("zdOrderResourceService")
public class ZdOrderResourceServiceImpl extends ServiceImpl<ZdOrderResourceDao, ZdOrderResourceEntity> implements ZdOrderResourceService {

    @Autowired
    private ZdOrderResourceDao zdOrderResourceDao;
    private ZdOrderDao zdOrderDao;
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<ZdOrderResourceEntity> page = this.selectPage(
                new Query<ZdOrderResourceEntity>(params).getPage(),
                new EntityWrapper<ZdOrderResourceEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public PageUtils queryListPage(Map<String, Object> params) {
        Page<OrderResourceVO> page=new Query<OrderResourceVO>(params).getPage();
        page.setRecords(zdOrderResourceDao.selectListPage(page,params));
        return new PageUtils(page);

    }
    @Override
    public PageUtils queryListPage1(Map<String, Object> params) {
        Page<OrderResourceVO> page=new Query<OrderResourceVO>(params).getPage();
        page.setRecords(zdOrderResourceDao.selectListPage1(page,params));
        return new PageUtils(page);

    }

    @Override
    public int countZMCRIds(List ids) {
        return zdOrderResourceDao.countZMCRIds(ids);
    }

    @Override
    public int queryOrderSumByIds(String[] ids) {
        return zdOrderResourceDao.queryOrderSumByIds(ids);
    }



    @Override
    public List queryAllList(Map<String, Object> params) {
        return zdOrderResourceDao.selectListPage(params);
    }

    @Override
    public List<OrderResourceVO> queryAllOrderList(Map<String, Object> params) {
        return zdOrderResourceDao.selectAllOrderList(params);
    }

    @Override
    public List<OrderResourceVO> listAll(SearchForm searchForm) {
        return zdOrderResourceDao.selectAll(searchForm);
    }

    @Override
    @Transactional
    public void updateVersion(String id, String id1, List<String> orgList, String semesterCode) {
        zdOrderResourceDao.updateResourceVersion(id,id1,orgList,semesterCode);
    }


}
