package io.renren.modules.zd.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;
import io.renren.modules.zd.dao.ZdPublishResourceDao;
import io.renren.modules.zd.entity.ZdPublishResourceEntity;
import io.renren.modules.zd.service.ZdPublishResourceService;
import io.renren.modules.zd.service.ZdStockService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service("zdPublishResourceService")
public class ZdPublishResourceServiceImpl extends ServiceImpl<ZdPublishResourceDao, ZdPublishResourceEntity> implements ZdPublishResourceService {

	Logger logger = LoggerFactory.getLogger(ZdPublishResourceServiceImpl.class);

	@Autowired
	private ZdStockService zdStockService;

	@Autowired
	private ZdPublishResourceDao zdPublishResourceDao;
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<ZdPublishResourceEntity> page = this.selectPage(
                new Query<ZdPublishResourceEntity>(params).getPage(),
                new EntityWrapper<ZdPublishResourceEntity>()
        );

        return new PageUtils(page);
    }

	/*@Override
	public boolean insertList(List<ZdPublishResourceEntity> list) {
		
		//更新库存表  resource_id   publish_num    zd_stock
		for(ZdPublishResourceEntity zdPublishResourceEntity :list){
//			System.out.println("--------------"+ org.json.JSONObject.valueToString(zdPublishResourceEntity));
			String resourceId = zdPublishResourceEntity.getResourceId();

			int num = 0 ;
			 if(zdPublishResourceEntity.getPublishNum() != null){
			 	num  =zdPublishResourceEntity.getPublishNum();
			}

			int tempNum = 0 ;
			 if(zdStockDao.getStockNum(resourceId)!= null){
				if( zdStockDao.getStockNum(resourceId).getTotalAmount()!=null){
					tempNum = zdStockDao.getStockNum(resourceId).getTotalAmount();
				}
			 }
			logger.info("sage resourece list :" +num +"----"+tempNum+"resourceId:"+resourceId);

			zdStockDao.save( tempNum-num,resourceId);
		}
//		for(ZdPublishResourceEntity zd:list){
//			this.insert(zd);
//		}
		
		return this.insertBatch(list);
	}*/

    
}
