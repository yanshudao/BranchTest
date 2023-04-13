package io.renren.modules.zd.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.modules.sys.entity.SysUserEntity;
import io.renren.modules.zd.entity.ZdSourceCartEntity;
import io.renren.modules.zd.form.ZdSourceCatFrom;
import io.renren.modules.zd.vo.ZdSourceCartVO;

import java.util.List;
import java.util.Map;

/**
 * 
 *
 * @author lixiang
 * @email 395569664@qq.com
 * @date 2018-10-23 09:42:20
 */
public interface ZdSourceCartService extends IService<ZdSourceCartEntity> {


    boolean saveCatForm(ZdSourceCatFrom zdCatFrom);

    boolean updateCatForm(ZdSourceCatFrom zdCatFrom);

    List<ZdSourceCartVO> queryList(Map<String, Object> params);

    List<ZdSourceCartVO> importCart(List<ZdSourceCartVO> list, SysUserEntity user, String semesterCode);

}

