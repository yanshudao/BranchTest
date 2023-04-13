package io.renren.modules.zd.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.sys.entity.SysUserEntity;
import io.renren.modules.zd.entity.ZdCartEntity;
import io.renren.modules.zd.form.SearchForm;
import io.renren.modules.zd.form.ZdCatFrom;
import io.renren.modules.zd.vo.CartResourceVO;
import io.renren.modules.zd.vo.ZdCatVO;
import io.renren.modules.zd.vo.ZdOrderCartVO;

import java.util.List;
import java.util.Map;

/**
 * 
 *
 * @author lixiang
 * @email 395569664@qq.com
 * @date 2018-10-23 09:42:20
 */
public interface ZdCartService extends IService<ZdCartEntity> {

    PageUtils queryPage(Map<String, Object> params);

    PageUtils queryListPage(Map<String, Object> params);



    void submitOrder(List ids, SysUserEntity user, String confirm,String note,String remark);

    void updateCatForm(ZdCatFrom zdCatFrom);

    void saveCatFormByRelation(ZdCatFrom zdCatFrom);

    List queryOrgList(Map<String, Object> params);

    List<ZdOrderCartVO> selectVOByIds(List ids);

    List<ZdCatVO> listAll(SearchForm searchForm);

    void updateVersion(String id, String id1, List<String> orgList,String semesterCode);

    List<CartResourceVO> importCart(List<CartResourceVO> list, SysUserEntity user, String semesterCode);
}

