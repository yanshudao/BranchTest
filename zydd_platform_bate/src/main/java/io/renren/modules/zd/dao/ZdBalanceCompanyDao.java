package io.renren.modules.zd.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import io.renren.modules.zd.entity.ZdBalanceCompanyEntity;
import io.renren.modules.zd.vo.ZdBalanceCompanyVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author lixiang
 * @email 395569664@qq.com
 * @date 2018-06-10 09:18:28
 */
@Mapper
public interface ZdBalanceCompanyDao extends BaseMapper<ZdBalanceCompanyEntity> {

    List<ZdBalanceCompanyVO> selectListPage(Page page, @Param("params") Map<String, Object> params);

    List<ZdBalanceCompanyVO> listBalanceVO(@Param("semesterCode") String semesterCode,@Param("orgCode") String orgCode);
}
