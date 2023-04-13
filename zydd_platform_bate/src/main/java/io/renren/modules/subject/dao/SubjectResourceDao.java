package io.renren.modules.subject.dao;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import io.renren.modules.subject.entity.SubjectResourceEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import io.renren.modules.subject.vo.MajorCourseResourceVO;
import io.renren.modules.subject.vo.ZdOrgResourceVO;
import io.renren.modules.zd.vo.ZdRefundResourceVO;
import io.renren.modules.zd.vo.ZdStockResourceVO;
import io.renren.modules.zd.vo.ZdSourceIncomeVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 教材
 * 
 * @author lixiang
 * @email 395569664@qq.com
 * @date 2018-03-29 23:29:28
 */
@Mapper
public interface SubjectResourceDao extends BaseMapper<SubjectResourceEntity> {

    List<SubjectResourceEntity> selectListPage(Pagination page, @Param("params") Map<String, Object> params);
    List<SubjectResourceEntity> selectListPage( @Param("params") Map<String, Object> params);

    List<SubjectResourceEntity> selectListResourcePage(Page page,@Param("params")  Map<String, Object> params);

    SubjectResourceEntity selectByForeignId(@Param("foreignId") String foreignId);

    List<ZdOrgResourceVO> selectZdResourceByMap(Page page,@Param("params") Map<String, Object> params);

    List<ZdOrgResourceVO> selectAllResourceByMap(Page page,@Param("params") Map<String, Object> params);

    void deleteByByForeignId(@Param("foreignId") String id);

    List<ZdOrgResourceVO> selectOrgResourceByMap(Page page,@Param("params")Map<String, Object> params);

    SubjectResourceEntity selectByCode(@Param("resourceCode") String resourceCode);

    List<ZdSourceIncomeVO> selectIncomePage(Page page, @Param("params") Map<String, Object> params);

    void updateByIds(@Param("deleteFlag") String delete,@Param("ids") List ids);



    List<ZdStockResourceVO> selectPublishResourcePage(Page page, @Param("params") Map<String, Object> params);

    List<ZdStockResourceVO> selectStockResourcePage(Page page, @Param("params")  Map<String, Object> params);

    List<ZdRefundResourceVO> selectRefundResourcePage(Page page,@Param("params") Map<String, Object> params);

    List<ZdStockResourceVO> selectPublishResourcePage(@Param("params")Map<String, Object> params);

    List<MajorCourseResourceVO> selectMajorCoursePage(Page page,@Param("params") Map<String, Object> params);

    List<ZdOrgResourceVO> selectOrgResourceByMap1(Page page, @Param("params")Map<String, Object> params);

    List<ZdStockResourceVO> selectStockResourcePage1(Page page, @Param("params")Map<String, Object> params);

    List<ZdStockResourceVO> selectPublishResourcePage1(@Param("params")Map<String, Object> params);

    List<String> listCatalog(Map<String, Object> params);

    int countMap(@Param("resourceId") String id,@Param("orgCode") String orgCode);

    int updateResourceType2(@Param("resourceType2") String resourceType2, @Param("list") List<SubjectResourceEntity> updateBatch1);

}
