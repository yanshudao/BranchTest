package io.renren.modules.zd.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import io.renren.modules.sys.entity.SysSemesterEntity;
import io.renren.modules.zd.entity.ZdRefundSupplierResourceEntity;
import io.renren.modules.zd.vo.RefundSupplierResourceVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 退货详情
 * 
 * @author lixiang
 * @email 395569664@qq.com
 * @date 2018-11-26 18:46:17
 */
@Mapper
public interface ZdRefundSupplierResourceDao extends BaseMapper<ZdRefundSupplierResourceEntity> {

    List<ZdRefundSupplierResourceEntity> selectByRefundId(@Param("refundId") String id);

    List<RefundSupplierResourceVO> queryRefundResourceInfoPage(Page page, @Param("params") Map<String, Object> params);
    List<RefundSupplierResourceVO> queryRefundResourceInfoAll( @Param("params") Map<String, Object> params);

    ZdRefundSupplierResourceEntity selectByFRefundId(@Param("fRefundResourceId") String id);
    Integer selectRefundResourceLimit(@Param("orgCode") String orgCode,
                                  @Param("semesterList") List<SysSemesterEntity> semesterEntityList,
                                  @Param("resourceId") String resourceId);

    Integer selectRefundResourceLimit2(@Param("orgCode")String orgCode, @Param("semesterCode")String semesterCode, @Param("resourceId")String resourceId);

}
