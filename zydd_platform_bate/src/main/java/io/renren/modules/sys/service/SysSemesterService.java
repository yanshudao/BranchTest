package io.renren.modules.sys.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.sys.entity.SysSemesterEntity;

import java.util.List;
import java.util.Map;

/**
 * 征订季节设定
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-03-29 22:09:37
 */
public interface SysSemesterService extends IService<SysSemesterEntity> {

    PageUtils queryPage(Map<String, Object> params);

    SysSemesterEntity updateCurrentSemester(String orgCode,SysSemesterEntity sysSemesterEntity);

    SysSemesterEntity getCurrentSemester();
    SysSemesterEntity getCurrentSemester(String orgCode);
    SysSemesterEntity getCurrentSemester(String orgCode,String parentCodes);

    /**
     * 获得省级列表
     * @param params
     * @return
     */

    PageUtils queryListPage(Map<String, Object> params);

    List listAll(Map<String, Object> params);
    List listRefund();

    List<SysSemesterEntity> listAllByZydd(Map<String, Object> params);

    SysSemesterEntity getSemesterByCode(String semesterCode);

    SysSemesterEntity updateSemester(String orgCode, String semesterCode);

    void updateDefaultSemester(String semesterCode);


    void closeRate(String semesterCode);

}

