package io.renren.modules.sys.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;
import io.renren.common.utils.ShiroUtils;
import io.renren.modules.sys.dao.SysOrgAreaDao;
import io.renren.modules.sys.dao.SysOrgRateDao;
import io.renren.modules.sys.entity.*;
import io.renren.modules.sys.service.SysOrgAreaService;
import io.renren.modules.sys.service.SysOrgRateService;
import io.renren.modules.sys.service.SysOrgService;
import io.renren.modules.sys.service.SysOrgSettingService;
import io.renren.modules.zd.service.ZdSemesterRegService;
import io.renren.modules.zd.vo.ZdSemesterRegVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SysOrgRateServiceImpl extends ServiceImpl<SysOrgRateDao, SysOrgRateEntity> implements SysOrgRateService {
    @Autowired
    private SysOrgSettingService sysOrgSettingService;
    @Autowired
    private SysOrgService sysOrgService;
    @Autowired
    private ZdSemesterRegService zdSemesterRegService;

    @Override
    public List<SysOrgRateEntity> queryPage(Map<String, Object> params) {
        return baseMapper.selectListPage(params);
    }

    @Override
    public int replaceRate(SysOrgRateEntity sysOrgRateEntity) {
        return baseMapper.replaceRate(sysOrgRateEntity);
    }

    @Override
    public ZdSemesterRegVO querySumPage(String orgCode,String semesterCode) {
        SysOrgSettingEntity sysOrgSettingEntity=sysOrgSettingService.selectByOrg(orgCode);
        SysOrgEntity sysOrgEntity=sysOrgService.selectByOrgCode(orgCode);
        Map<String,Object> params=new HashMap<>();
        params.put("semesterCode",semesterCode);
        params.put("orgCode",orgCode);
        params.put("parentOrgCodes",sysOrgEntity.getParentCodes());
        SysUserEntity sysUserEntity= ShiroUtils.getUserEntity();
            params.put("semesterCode", semesterCode);
            params.put("orgCode", sysOrgSettingEntity.getOrgCode());
            ZdSemesterRegVO zdSemesterRegVO = zdSemesterRegService.selectRateSum(params);
            if(zdSemesterRegVO!=null&&sysOrgSettingEntity.getIsConfigRate1()==1){
                if (zdSemesterRegVO.getSourceTotal() == 0 || zdSemesterRegVO.getStuTotal() == 0) {
                    zdSemesterRegVO.setRate1(new BigDecimal("0"));
                } else {
                    Integer total=zdSemesterRegVO.getSourceTotal();
                    zdSemesterRegVO.setRate1(new BigDecimal((float) total/ zdSemesterRegVO.getStuTotal())
                            .setScale(2, BigDecimal.ROUND_DOWN));
                    zdSemesterRegVO.setRate1(zdSemesterRegVO.getRate1().multiply(new BigDecimal("100")));
                }
            }
            if(zdSemesterRegVO!=null&&sysOrgSettingEntity.getIsConfigRate2()==1){
                if (zdSemesterRegVO.getStockIncomeTotal() == 0 || zdSemesterRegVO.getStuTotal() == 0) {
                    zdSemesterRegVO.setRate2(new BigDecimal("0"));
                } else {
                    Integer left=zdSemesterRegVO.getStockIncomeTotal()-zdSemesterRegVO.getRefundSupplierTotal();
                    if(left<0){
                        left=0;
                    }
                    zdSemesterRegVO.setRate2(new BigDecimal((float) left / zdSemesterRegVO.getStuTotal())
                            .setScale(2, BigDecimal.ROUND_DOWN));
                    zdSemesterRegVO.setRate2(zdSemesterRegVO.getRate2().multiply(new BigDecimal("100")));
                }
            }
            if(zdSemesterRegVO!=null&&sysOrgSettingEntity.getIsConfigRate3()==1){
                if (zdSemesterRegVO.getStuTotal() == 0) {
                    zdSemesterRegVO.setRate3(new BigDecimal("0"));
                } else {
                    zdSemesterRegVO.setRate3(new BigDecimal((float) zdSemesterRegVO.getStockIncomeTotal() / zdSemesterRegVO.getStuTotal())
                            .setScale(2, BigDecimal.ROUND_DOWN));
                    zdSemesterRegVO.setRate3(zdSemesterRegVO.getRate3().multiply(new BigDecimal("100")));
                }
            }
            return zdSemesterRegVO;
    }
}
