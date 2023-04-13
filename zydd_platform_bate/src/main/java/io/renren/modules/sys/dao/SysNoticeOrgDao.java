package io.renren.modules.sys.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import io.renren.modules.sys.entity.SysNoticeOrgEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author lixiang
 * @email 395569664@qq.com
 * @date 2018-06-22 15:57:47
 */
@Mapper
public interface SysNoticeOrgDao extends BaseMapper<SysNoticeOrgEntity> {

    List<SysNoticeOrgEntity> selectListPage(Page page, @Param("params") Map<String, Object> params);
}
