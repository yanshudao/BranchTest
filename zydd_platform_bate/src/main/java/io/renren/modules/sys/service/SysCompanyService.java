package io.renren.modules.sys.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.sys.entity.SysCompanyEntity;
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
public interface SysCompanyService extends IService<SysCompanyEntity> {


    PageUtils queryPage(Map<String, Object> params);

    List<SysCompanyEntity> queryAllByMap( Map<String, Object> params);

}

