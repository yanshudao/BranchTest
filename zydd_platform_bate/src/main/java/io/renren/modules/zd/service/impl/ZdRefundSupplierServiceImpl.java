package io.renren.modules.zd.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.common.exception.RRException;
import io.renren.common.utils.Constant;
import io.renren.common.utils.DecimalUtils;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;
import io.renren.modules.search.vo.StatisticsRefundSupplierResourceVO;
import io.renren.modules.zd.dao.ZdRefundSupplierDao;
import io.renren.modules.zd.dao.ZdRefundSupplierResourceDao;
import io.renren.modules.zd.entity.*;
import io.renren.modules.zd.form.ZdRefundForm;
import io.renren.modules.zd.form.ZdRefundResourceForm;
import io.renren.modules.zd.service.ZdRefundSupplierService;
import io.renren.modules.zd.vo.RefundSupplierResourceVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;


@Service("zdRefundSupplierService")
public class ZdRefundSupplierServiceImpl extends ServiceImpl<ZdRefundSupplierDao, ZdRefundSupplierEntity> implements ZdRefundSupplierService {

    @Autowired
    private ZdRefundSupplierResourceDao zdRefundSupplierResourceDao;
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<ZdRefundSupplierEntity> page = this.selectPage(
                new Query<ZdRefundSupplierEntity>(params).getPage(),
                new EntityWrapper<ZdRefundSupplierEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<ZdRefundSupplierResourceEntity> selectByRefundId(String id) {
        return zdRefundSupplierResourceDao.selectByRefundId(id);
    }

    @Override
    public PageUtils queryRefundOrderList(Map<String, Object> params) {
        Page<ZdRefundOrderListEntity> page = new Query<ZdRefundOrderListEntity>(params).getPage();
        List<ZdRefundOrderListEntity> resList = baseMapper.queryRefundOrderList(page ,params);
        page.setRecords(resList);
        return new PageUtils(page);
    }

    @Override
    public PageUtils getRefundResourceinfo(Map<String, Object> params) {
        Page<RefundSupplierResourceVO> page=new Query<RefundSupplierResourceVO>(params).getPage();
        page.setRecords(zdRefundSupplierResourceDao.queryRefundResourceInfoPage(page,params));
        return new PageUtils(page);
    }

    @Override
    @Transactional
    public int reportRefundOrder(String refundId) {
        ZdRefundSupplierEntity zdRefundSupplierEntity=new ZdRefundSupplierEntity();
        zdRefundSupplierEntity.setStatus("2");
        zdRefundSupplierEntity.setId(refundId);
        return baseMapper.updateById(zdRefundSupplierEntity);
    }

    @Override
    @Transactional
    public int deleteRefundResource(List<String> ids) {
        return zdRefundSupplierResourceDao.deleteBatchIds(ids);
    }

    @Override
    public PageUtils queryStatisticsByMap(Map<String, Object> params) {
        Page<StatisticsRefundSupplierResourceVO> page=new Query<StatisticsRefundSupplierResourceVO>(params).getPage();
        page.setRecords(baseMapper.selectStatisticsByMap(page,params));
        return new PageUtils(page);
    }

    @Override
    @Transactional
    public boolean deleteRefundSuppiler(List ids) {
        zdRefundSupplierResourceDao.delete(new EntityWrapper<ZdRefundSupplierResourceEntity>().in("refund_supplier_id",ids));
        return  deleteBatchIds(ids);
    }

    @Override
    public int auditRefund(ZdRefundForm zdRefundForm, String status) {
        ZdRefundSupplierEntity zdRefundEntity=baseMapper.selectById(zdRefundForm.getId());
        if(Constant.REFUND_STATUS.COMPLETE.equals(zdRefundEntity.getStatus())||Constant.REFUND_STATUS.FINISH.equals(zdRefundEntity.getStatus()))
        {
            throw new RRException("不能重复审核退货单");
        }
        if(Constant.REFUND_STATUS.COMPLETE.equals(status))
        {
            List<ZdRefundSupplierResourceEntity> refundResourceEntities=zdRefundSupplierResourceDao.selectByRefundId(zdRefundForm.getId());
            //修改退货单中教材资源具体数量
            for(ZdRefundSupplierResourceEntity zdRefundResourceEntity:refundResourceEntities)
            {
                zdRefundResourceEntity.setRealNum(zdRefundResourceEntity.getRefundNum());

                if(zdRefundForm.getResourceForm()!=null)
                {
                    for(ZdRefundResourceForm zdRefundResourceForm : zdRefundForm.getResourceForm() ) {
                        if(zdRefundResourceEntity.getId().equals(zdRefundResourceForm.getId()))
                        {
                            if(zdRefundResourceForm.getRealNum()<=0)
                            {
                                throw new RRException("审核数量不能小于等于0");
                            }else
                            {
                                zdRefundResourceEntity.setRealNum(zdRefundResourceForm.getRealNum());
                                break;
                            }

                        }
                    }
                }
                zdRefundResourceEntity.setMayang(DecimalUtils.mayang(zdRefundResourceEntity.getRefundPrice(),zdRefundResourceEntity.getRealNum()));
                zdRefundResourceEntity.setShiyang(DecimalUtils.shiyang(zdRefundResourceEntity.getRefundPrice(),zdRefundResourceEntity.getRealNum(),zdRefundResourceEntity.getNitemdiscountrate()));
                zdRefundSupplierResourceDao.updateById(zdRefundResourceEntity);
            }
            zdRefundEntity.setStatus(Constant.REFUND_STATUS.COMPLETE);
            baseMapper.updateById(zdRefundEntity);
        }
        if(Constant.REFUND_STATUS.AUDIT_FAIL.equals(status))
        {
            zdRefundEntity.setRemark(zdRefundForm.getRemark());
            zdRefundEntity.setStatus(Constant.REFUND_STATUS.AUDIT_FAIL);
            return baseMapper.updateById(zdRefundEntity);
        }
        return 1;
    }

    public List<RefundSupplierResourceVO> getRefundResourceinfoAll(Map<String, Object> params) {
        return  zdRefundSupplierResourceDao.queryRefundResourceInfoAll(params);
    }
}
