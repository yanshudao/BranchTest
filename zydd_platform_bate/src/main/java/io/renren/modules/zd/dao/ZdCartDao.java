package io.renren.modules.zd.dao;

import com.baomidou.mybatisplus.plugins.Page;
import io.renren.modules.zd.entity.ZdCartEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import io.renren.modules.zd.form.SearchForm;
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
 * @date 2018-10-23 09:42:20
 */
@Mapper
public interface ZdCartDao extends BaseMapper<ZdCartEntity> {

    List<ZdCatVO> selectListPage(Page page,@Param("params") Map<String, Object> params);

    ZdCartEntity selectByMCRId(@Param("zmcrId") String zmcrId,@Param("createBy")String createBy);

    List<ZdOrderCartVO> selectVOByIds(@Param("ids") List ids);

    List listOrg(@Param("params") Map<String, Object> params);

    List<ZdCatVO> listAll(@Param("searchForm") SearchForm searchForm);

    void updateVersion(@Param("oldZmcrId") String id, @Param("newZmcrId")String id1,@Param("orgList") List<String> orgList,@Param("semesterCode") String semesterCode);
}
