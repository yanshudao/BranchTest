package io.renren.modules.zd.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.sys.entity.SysUserEntity;
import io.renren.modules.zd.entity.ZdOrderCartEntity;
import io.renren.modules.zd.form.ZdCatFrom;

import java.util.List;
import java.util.Map;

/**
 * 
 *
 * @author lixiang
 * @email 395569664@qq.com
 * @date 2018-09-07 16:30:50
 */
public interface ZdOrderCartService extends IService<ZdOrderCartEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void saveCatForm(ZdCatFrom zdCatFrom);

    PageUtils queryListPage(Map<String, Object> params);

    void submitOrder(List ids, SysUserEntity user,String status,String note,String remark);

    void updateCatForm(ZdCatFrom zdCatFrom);

}

