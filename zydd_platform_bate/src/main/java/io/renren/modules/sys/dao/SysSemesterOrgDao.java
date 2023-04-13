package io.renren.modules.sys.dao;

import io.renren.modules.sys.entity.SysSemesterOrgEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 
 * 
 * @author lixiang
 * @email 395569664@qq.com
 * @date 2018-04-26 23:28:22
 */
@Mapper
public interface SysSemesterOrgDao extends BaseMapper<SysSemesterOrgEntity> {

    void updateStatus(@Param("orgCode") String orgCode, @Param("status")String status);

    SysSemesterOrgEntity selectByOrgAndSemester( @Param("orgCode")String orgCode, @Param("semesterCode") String semesterCode);

    void updateAllStatus(@Param("status")String status);

}
