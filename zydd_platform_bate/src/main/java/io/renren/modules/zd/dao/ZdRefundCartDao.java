package io.renren.modules.zd.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import io.renren.modules.sys.entity.SysCompanyEntity;
import io.renren.modules.zd.entity.ZdRefundCartEntity;
import io.renren.modules.zd.vo.ZdRefundCartVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author lixiang
 * @email 395569664@qq.com
 * @date 2018-11-20 15:56:23
 */
@Mapper
public interface ZdRefundCartDao extends BaseMapper<ZdRefundCartEntity> {

    List<ZdRefundCartVO> selectListPage(Page page, @Param("params") Map<String, Object> params);
    List<ZdRefundCartVO> selectListPage(@Param("params") Map<String, Object> params);

    ZdRefundCartEntity selectByOwnAndResourceID(@Param("refundSemesterCode") String refundSemesterCode,@Param("userId") Long userId,@Param("resourceId") String resourceId);

    List<ZdRefundCartEntity> selectByIdsAndOwn(@Param("supplierId")String supplierId,@Param("ids") List ids,@Param("userId") Long userId);

    void deleteByCreateBy(@Param("userId") Long userId);

    BigDecimal selectCartTotal(@Param("params")  Map<String, Object> map,@Param("semesterCode") String semesterCode);

    List<String> selectSuppliers(@Param("userId") Long userId);

    List<SysCompanyEntity> selectCompany(Long userId);

}
