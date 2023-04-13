package io.renren.modules.zd.dao;

import com.baomidou.mybatisplus.plugins.Page;
import io.renren.modules.zd.entity.ZdOrgMajorEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import io.renren.modules.zd.vo.OrgMajorVO;
import io.renren.modules.zd.vo.OrgZdMajorVO;
import io.renren.modules.zd.vo.OrgZdVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 单位开设专业
 * 
 * @author lixiang
 * @email 395569664@qq.com
 * @date 2018-04-01 15:58:41
 */
@Mapper
public interface ZdOrgMajorDao extends BaseMapper<ZdOrgMajorEntity> {

    List<OrgMajorVO> selectByList(Page page,@Param("params") Map<String, Object> params);

    List<OrgMajorVO> selectByOrg(Page page,@Param("params") Map<String, Object> params);

    List<OrgMajorVO> selectBySheng(Page page,@Param("params") Map<String, Object> params);

    List<OrgZdVO> selectPageGroup(@Param("params") Map<String, Object> params);

    List<OrgZdMajorVO> selectMajorByMap(@Param("params")Map<String, Object> params);

    List<String> selectIdsByMap(@Param("semesterCode")String semesterCode, @Param("orgCode")String orgCode,@Param("majorId") String majorId);

    void deleteChildrenBatchIds(@Param("zdOrgList") List<ZdOrgMajorEntity> zdOrgMajorEntities, @Param("parentOrgCode")String parentOrgCode,@Param("semesterCode")String semesterCode);

    void deleteByMajorIds(@Param("ids") Collection<? extends Serializable> idList);
}
