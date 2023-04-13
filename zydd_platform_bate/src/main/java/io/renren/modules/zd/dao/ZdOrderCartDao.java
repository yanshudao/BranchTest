package io.renren.modules.zd.dao;

import com.baomidou.mybatisplus.plugins.Page;
import io.renren.modules.zd.entity.ZdOrderCartEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import io.renren.modules.zd.vo.OrderResourceVO;
import io.renren.modules.zd.vo.ZdCatVO;
import io.renren.modules.zd.vo.ZdOrderCartVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author lixiang
 * @email 395569664@qq.com
 * @date 2018-09-07 16:30:50
 */
@Mapper
public interface ZdOrderCartDao extends BaseMapper<ZdOrderCartEntity> {

    List<ZdCatVO> selectListPage(Page page,@Param("params") Map<String, Object> params);

    ZdOrderCartEntity selectByMCRId(@Param("majorId") String majorId,@Param("courseId") String courseId,@Param("resourceId") String resourceId,@Param("createBy") String createBy);

    List<ZdOrderCartVO> selectVOByIds(@Param("ids") List ids);
}
