package io.renren.modules.zd.dao;

import com.baomidou.mybatisplus.plugins.Page;
import io.renren.modules.zd.entity.ZdStockEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import io.renren.modules.zd.vo.ZdStockVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 库存
 * 
 * @author lixiang
 * @email 395569664@qq.com
 * @date 2018-03-31 00:14:33
 */
@Mapper
public interface ZdStockDao extends BaseMapper<ZdStockEntity> {

    ZdStockEntity selectResourceIdAndOrgCode(@Param("resourceId")String resourceId, @Param("orgCode") String orgCode);


    //int updateResourceStock(@Param("params")Map<String, Object> refundMap);
   // public  boolean updateStockNum(@Param("num")int num,@Param("resourceId")String resourceId);

    public ZdStockEntity getStockNum(String resourceId);

    List<ZdStockVO> selectListPage(Page page,@Param("params") Map<String, Object> params);
}
