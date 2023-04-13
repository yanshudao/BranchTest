package io.renren.modules.sys.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.modules.sys.entity.SysOrgResourceTypeEntity;
import io.renren.modules.sys.form.SysOrgResourceTypeForm;
import io.renren.modules.sys.vo.SysOrgResourceTypeVO;

import java.util.List;
import java.util.Map;

/**
 * 
 *
 * @author lixiang
 * @email 395569664@qq.com
 * @date 2018-06-22 15:57:47
 */
public interface SysOrgResourceTypeService extends IService<SysOrgResourceTypeEntity> {


    List<SysOrgResourceTypeVO> selectAll(Map<String, Object> queryParams);

    void save(SysOrgResourceTypeForm resourceTypeForm);

}

