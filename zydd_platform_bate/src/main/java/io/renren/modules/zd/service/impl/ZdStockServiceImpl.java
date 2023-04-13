package io.renren.modules.zd.service.impl;

import io.renren.common.exception.RRException;
import io.renren.common.utils.Constant;
import io.renren.modules.subject.dao.SubjectResourceDao;
import io.renren.modules.sys.entity.SysOrgEntity;
import io.renren.modules.sys.entity.SysUserEntity;
import io.renren.modules.zd.dao.ZdStockRecordDao;
import io.renren.modules.zd.entity.ZdStockRecordEntity;
import io.renren.modules.zd.vo.ZdRefundResourceVO;
import io.renren.modules.zd.vo.ZdStockResourceVO;
import io.renren.modules.zd.vo.ZdStockVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.zd.dao.ZdStockDao;
import io.renren.modules.zd.entity.ZdStockEntity;
import io.renren.modules.zd.service.ZdStockService;
import org.springframework.transaction.annotation.Transactional;


@Service("zdStockService")
public class ZdStockServiceImpl extends ServiceImpl<ZdStockDao, ZdStockEntity> implements ZdStockService {

    @Autowired
    private ZdStockDao zdStockDao;
    @Autowired
    private ZdStockRecordDao zdStockRecordDao;
    @Autowired
    private SubjectResourceDao subjectResourceDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<ZdStockVO> page=new Query<ZdStockVO>(params).getPage();
        page.setRecords(zdStockDao.selectListPage(page,params));
        return new PageUtils(page);

    }

    /**
     * 省级操作库存
     * @param saveEntity
     * @param orgCode
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveStock(ZdStockEntity saveEntity, String orgCode) {
        if(saveEntity.getTotalAmount()==null||saveEntity.getTotalAmount()==0)
        {
            throw new RRException("操作数量不能为0");
        }
       /* if(saveEntity.getTotalAmount()>0)
        {
            saveEntity.setRecordType(Constant.STOCK_RECORD_TYPE.ADD);
        }else
        {
            saveEntity.setRecordType(Constant.STOCK_RECORD_TYPE.SUB);
        }*/
        ZdStockEntity zdStockEntity=zdStockDao.selectResourceIdAndOrgCode(saveEntity.getResourceId(),orgCode);
        ZdStockRecordEntity zdStockRecordEntity=new ZdStockRecordEntity();
        zdStockRecordEntity.setAmount(saveEntity.getTotalAmount());//操作数量
        if(zdStockEntity==null)
        {
            zdStockEntity=new ZdStockEntity();
            zdStockEntity.setResourceId(saveEntity.getResourceId());
            zdStockEntity.setVersion(1);
            zdStockEntity.setTotalAmount(saveEntity.getTotalAmount());
            if(zdStockEntity.getTotalAmount()<0)
            {
                throw new RRException("库存不够！不支持此操作");
            }
            zdStockEntity.setOrgCode(orgCode);
            zdStockRecordEntity.setBeforeAmount(0);
           // zdStockRecordEntity.setResourceId(Constant.STOCK_RECORD_TYPE.ADD);
            zdStockDao.insert(zdStockEntity);
            zdStockRecordEntity.setAfterAmount(zdStockEntity.getTotalAmount());
        }else
        {
            zdStockRecordEntity.setBeforeAmount(zdStockEntity.getTotalAmount());
            zdStockEntity.setTotalAmount(zdStockEntity.getTotalAmount()+saveEntity.getTotalAmount());
            if(zdStockEntity.getTotalAmount()<0)
            {
                throw new RRException("库存不够！不支持此操作");
            }
           /* zdStockEntity.setTotalAmount(zdStockEntity.getTotalAmount()-saveEntity.getTotalAmount());
            if(Constant.STOCK_RECORD_TYPE.ADD.equals(saveEntity.getRecordType()))
            {

            }else
            {

            }*/
           //Integer version= zdStockEntity.getVersion();
            if(zdStockDao.updateById(zdStockEntity)<1)
            {
                throw new RRException("库存被其他人修改！");
            }
            zdStockRecordEntity.setAfterAmount(zdStockEntity.getTotalAmount());
        }

        zdStockRecordEntity.setStockId(zdStockEntity.getId());
        zdStockRecordEntity.setResourceId(saveEntity.getResourceId());
        zdStockRecordEntity.setRecordType(saveEntity.getRecordType());
        zdStockRecordDao.insert(zdStockRecordEntity);
    }



    @Override
    public PageUtils queryResourcePage(Map<String, Object> params) {
        Page<ZdRefundResourceVO> page=new Query<ZdRefundResourceVO>(params).getPage();
        page.setRecords(subjectResourceDao.selectRefundResourcePage(page,params));
        return new PageUtils(page);
    }

}
