package io.renren.modules.zd.dao;

import com.baomidou.mybatisplus.plugins.Page;
import io.renren.modules.subject.entity.SubjectResourceEntity;
import io.renren.modules.zd.entity.ZdSourceResourceEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import io.renren.modules.zd.form.SearchForm;
import io.renren.modules.zd.vo.ZdSourceDetailVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 采购详情
 * 
 * @author lixiang
 * @email 395569664@qq.com
 * @date 2018-03-31 00:14:33
 */
@Mapper
public interface ZdSourceResourceDao extends BaseMapper<ZdSourceResourceEntity> {

    List<ZdSourceDetailVO> selectListPage(Page page, @Param("params") Map<String, Object> params);

    List<ZdSourceResourceEntity> selectBySourceId(@Param("sourceId") String id);


    void deleteBySourceIds(@Param("ids")List<String> ids);

    int updateVersion(@Param("oldResourceId") String id,@Param("newResourceId") String id1,
                      @Param("semesterCode")String semesterCode,  @Param("orgCode")String orgCode, @Param("sourceNo")String sourceNo);

    int countByResourceIds(@Param("ids")List ids);

    void updatePrice(@Param("subjectResource") SubjectResourceEntity newResource);

    List<ZdSourceDetailVO> selectResourceAll(@Param("searchForm") SearchForm searchForm);

    int updateResourceVersion(@Param("oldResourceCode")String id,
                              @Param("newResourceCode")String id1,
                              @Param("orgList") List<String> orgList,
                              @Param("semesterCode")String semesterCode,
                              @Param("zmcrId")String zmcrId);

    int selectLeftCount(@Param("sourceNo")String sourceId);

    void updateStatus(@Param("status") String status, @Param("sourceId") String id);
}
