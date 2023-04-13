package io.renren.modules.sys.dao;

import com.baomidou.mybatisplus.plugins.Page;
import io.renren.modules.sys.entity.SysSemesterEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 征订季节设定
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-03-29 22:09:37
 */
@Mapper
public interface SysSemesterDao extends BaseMapper<SysSemesterEntity> {

    void updateStatusNotInId(@Param("id") String id, @Param("status") String status);

    List<SysSemesterEntity> selectListPage(Page page, @Param("params") Map<String, Object> params);
    List<SysSemesterEntity> selectListPage(@Param("params") Map<String, Object> params);

    List querySemester(@Param("params") Map<String, Object> params);


    List<SysSemesterEntity> selectListZYDD(Page page,@Param("params") Map<String, Object> params);

    SysSemesterEntity selectCurrentSemester(@Param("orgCode") String orgCode,@Param("parentCodes")String parentCodes);
    SysSemesterEntity selectZyddSemester(@Param("orgCode") String orgCode);


    List<SysSemesterEntity> listAllByZydd(@Param("params") Map<String, Object> params);

    SysSemesterEntity selectByCode(@Param("semesterCode") String semesterCode);

}
