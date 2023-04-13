package io.renren.modules.zd.dao;

import io.renren.modules.zd.entity.ZdStockRecordEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 库存变化明细
 * 
 * @author lixiang
 * @email 395569664@qq.com
 * @date 2018-03-31 00:14:32
 */
@Mapper
public interface ZdStockRecordDao extends BaseMapper<ZdStockRecordEntity> {
	
}
