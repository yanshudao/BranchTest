package io.renren.modules.zd.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import io.renren.modules.zd.entity.ZdOrderResourceEntity;
import io.renren.modules.zd.form.SearchForm;
import io.renren.modules.zd.vo.OrderResourceVO;
import io.renren.modules.zd.vo.RefundOrderVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 征订明细
 * 
 * @author lixiang
 * @email 395569664@qq.com
 * @date 2018-03-31 00:14:32
 */
@Mapper
public interface ZdOrderResourceDao extends BaseMapper<ZdOrderResourceEntity> {

    List<OrderResourceVO> selectListPage(Pagination pagination,@Param("params") Map<String, Object> params);
    List<OrderResourceVO> selectListPage(@Param("params") Map<String, Object> params);

    void deleteByOrderId(@Param("ids") List<String> ids);


    RefundOrderVO getRefundOrderInfo(@Param("params")Map<String, Object> refundMap);

    List<ZdOrderResourceEntity> getOrderResourceByOrgCode(@Param("fromOrgCode") String fromCode,@Param("toOrgCode") String toCode);


    @Deprecated
    int updateVersion(@Param("oldResourceId") String id,@Param("newResourceId") String id1,@Param("semesterCode")String semesterCode,
                      @Param("orgCode") String orgCodem,@Param("majorId")String majorId,@Param("courseId")String courseId);

    List<OrderResourceVO> selectAllOrderList(@Param("params")Map<String, Object> params);

    int countByResourceIds(@Param("ids") List ids);

    int countByCourseIds(@Param("ids")List ids);

    List<OrderResourceVO> selectListPage1(Page page,@Param("params") Map<String, Object> params);

    int countZMCRIds(@Param("zmcrIds")List ids);

    int queryOrderSumByIds(@Param("ids") String[] ids);

    List<OrderResourceVO> selectAll(@Param("searchForm") SearchForm searchForm);

    void updateResourceVersion(@Param("oldZmcrId") String id,
                              @Param("newZmcrId")String id1,
                              @Param("orgList") List<String> orgList, @Param("semesterCode")String semesterCode);
}
