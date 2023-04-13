package io.renren.modules.zd.dao;

import io.renren.modules.zd.entity.ZdSourceOrderResourceEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 
 * 
 * @author lixiang
 * @email 395569664@qq.com
 * @date 2019-02-25 16:21:35
 */
@Mapper
public interface ZdSourceOrderResourceDao extends BaseMapper<ZdSourceOrderResourceEntity> {

    void deleteBySourceIds(@Param("ids") List<String> ids);

}
