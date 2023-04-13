package io.renren.modules.zd.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import io.renren.modules.zd.entity.ZdSourceCartEntity;
import io.renren.modules.zd.vo.ZdSourceCartVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author lixiang
 * @email 395569664@qq.com
 * @date 2018-10-23 09:42:20
 */
@Mapper
public interface ZdSourceCartDao extends BaseMapper<ZdSourceCartEntity> {

    List<ZdSourceCartVO> selectAll(@Param("params") Map<String, Object> params);
}
