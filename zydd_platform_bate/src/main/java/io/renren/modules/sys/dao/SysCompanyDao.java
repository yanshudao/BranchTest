package io.renren.modules.sys.dao;

import com.baomidou.mybatisplus.plugins.Page;
import io.renren.modules.sys.entity.SysCompanyEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 供应商/出版社
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-03-29 22:09:37
 */
@Mapper
public interface SysCompanyDao extends BaseMapper<SysCompanyEntity> {

    List<SysCompanyEntity> selectListPage(Page page, @Param("params") Map<String, Object> params);

    List<SysCompanyEntity> selectAllByMap(@Param("params") Map<String, Object> params);

}
