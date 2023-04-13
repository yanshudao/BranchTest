package io.renren.modules.zd.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import io.renren.modules.zd.entity.ZdCartEntity;
import io.renren.modules.zd.entity.ZdSemesterRegEntity;
import io.renren.modules.zd.form.SearchForm;
import io.renren.modules.zd.vo.ZdCatVO;
import io.renren.modules.zd.vo.ZdOrderCartVO;
import io.renren.modules.zd.vo.ZdSemesterRegVO;
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
public interface ZdSemesterRegDao extends BaseMapper<ZdSemesterRegEntity> {

    List selectListPage(Page page, @Param("params") Map<String, Object> params);

    ZdSemesterRegVO selectRate(@Param("params") Map<String, Object> params);

    ZdSemesterRegVO selectRateSum(@Param("params") Map<String, Object> params);

    List<ZdSemesterRegEntity> selectAll(@Param("params")Map<String, Object> params);

    ZdSemesterRegEntity selectUnique(@Param("semesterCode") String semesterCode, @Param("studentType")String studentType,@Param("majorType") String majorType,@Param("majorCode") String majorCode, @Param("courseCode")String courseCode,@Param("custCode") String custCode);

}
