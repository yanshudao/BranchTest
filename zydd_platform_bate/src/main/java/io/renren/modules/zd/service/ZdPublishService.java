package io.renren.modules.zd.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.search.vo.StatisticsPublishResourceVO;
import io.renren.modules.zd.entity.ZdPublishEntity;
import io.renren.modules.zd.form.PublishConfirmForm;
import io.renren.modules.zd.form.ZdPublishForm;
import io.renren.modules.zd.vo.PublishResourceVO;
import io.renren.modules.zd.vo.ZdStockResourceVO;

import java.util.List;
import java.util.Map;

/**
 * 
 *
 * @author lixiang
 * @email 395569664@qq.com
 * @date 2018-03-31 00:14:32
 */
public interface ZdPublishService extends IService<ZdPublishEntity> {

    PageUtils queryPage(Map<String, Object> params);

	PageUtils queryByLimit(Map<String, Object> params);

	@Deprecated
	PageUtils queryOrg(Map<String, Object> params);

	PageUtils queryPublishList(Map<String, Object> params);

	List<PublishResourceVO> queryPublishListDetail(Map<String, Object> params);



    void savePublish(ZdPublishForm zdPublishForm);
    void transforPublish(ZdPublishForm zdPublishForm);

	PageUtils querySemester(Map<String, Object> params);


	PageUtils queryOrderResourcePage(Map<String, Object> params);


	PageUtils queryResourcePage(Map<String, Object> params);

    PageUtils queryStatisticsByMap(Map<String, Object> params);

    List<StatisticsPublishResourceVO> queryAllStatisticsByMap(Map<String, Object> params);

	List<ZdStockResourceVO> queryOrderResourceAll(Map<String, Object> params);

    void modifyPublishResource(ZdPublishForm zdPublishForm);

    void rejectPublish(ZdPublishForm zdPublishForm);

	void deletePublish(ZdPublishForm zdPublishForm);

    PageUtils queryResourcePage1(Map<String, Object> params);

	List<ZdStockResourceVO> queryOrderResourceAll1(Map<String, Object> params);

	void confirmPublish(PublishConfirmForm publishConfirmForm);

    void audit(List<String> ids);


    void reject(List<String> ids);

}

