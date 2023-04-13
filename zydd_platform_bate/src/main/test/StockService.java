import io.renren.PlatformApplication;
import io.renren.modules.subject.dao.SubjectResourceDao;
import io.renren.modules.subject.entity.SubjectResourceEntity;
import io.renren.modules.subject.entity.request.Book;
import io.renren.modules.subject.entity.request.Orderbill;
import io.renren.modules.subject.util.ArithUtil;
import io.renren.modules.sys.dao.SysOrgDao;
import io.renren.modules.sys.entity.SysOrgEntity;
import io.renren.modules.zd.dao.ZdSourceDao;
import io.renren.modules.zd.dao.ZdSourceResourceDao;
import io.renren.modules.zd.entity.ZdSourceEntity;
import io.renren.modules.zd.entity.ZdSourceResourceEntity;
import io.renren.modules.zd.service.ZdStockIncomeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = PlatformApplication.class)
public class StockService {
    @Autowired
    private ZdStockIncomeService zdStockService;
    @Autowired
    private ZdSourceDao zdSourceDao;
    @Autowired
    private SysOrgDao sysOrgDao;
    @Autowired
    private SubjectResourceDao subjectResourceDao;
    @Autowired
    private ZdSourceResourceDao zdSourceResourceDao;

    @Test
    public void findOne() throws Exception {
        ZdSourceEntity zdSourceEntity=zdSourceDao.selectById("da0f247b2aa048da994ae81b6559b051");
        List<ZdSourceResourceEntity> zdSourceResourceEntities = zdSourceResourceDao.selectBySourceId(zdSourceEntity.getId());
        SysOrgEntity toOrgEntity = sysOrgDao.selectByOrgCode(zdSourceEntity.getToOrgCode());
        Orderbill orderbill=new Orderbill();
        orderbill.setId(zdSourceEntity.getSourceNo());
        orderbill.setBookstoreid("123");
        orderbill.setCoperatorid("123");
        orderbill.setCorpcode("304");
        if(toOrgEntity!=null)
        {
            orderbill.setStaplerbasis(toOrgEntity.getOrgName()+"-"+toOrgEntity.getOrgCode());
        }
        int total=0;
        Double totalmayang=new Double(0);
        List<Book> books=new ArrayList<>();
        List<Orderbill> orderbills=new ArrayList<>();
        for(ZdSourceResourceEntity zdSourceResourceEntity:zdSourceResourceEntities)
        {

            SubjectResourceEntity subjectResourceEntity=subjectResourceDao.selectById(zdSourceResourceEntity.getResourceId());
            Book book=new Book();
            book.setBid(zdSourceResourceEntity.getId());
            book.setBookcode(subjectResourceEntity.getResourceCode());
            book.setBookname(subjectResourceEntity.getResourceName());
            book.setUnitprice(subjectResourceEntity.getPrice());
            book.setScrappedvol(zdSourceResourceEntity.getSourceNum());
            total+=zdSourceResourceEntity.getSourceNum();
            totalmayang= ArithUtil.add(totalmayang,(subjectResourceEntity.getPrice()*zdSourceResourceEntity.getSourceNum()));
            books.add(book);
        }
        System.out.println(totalmayang);
/*        ZdSourceIncomeForm zdSourceIncomeForm=new ZdSourceIncomeForm();
        SysUserEntity sysUserEntity=new SysUserEntity();
        sysUserEntity.setUserId(1L);
        sysUserEntity.setOrgCode("HNDD");
       // zdSourceIncomeForm.setSourceId("H000012ORDER201804171730");
        List<ZdSourceResourceIncomeForm> list=new ArrayList<>();
        ZdSourceResourceIncomeForm zdSourceResourceIncomeForm=new ZdSourceResourceIncomeForm();
        zdSourceResourceIncomeForm.setNum(1);
     //   zdSourceResourceIncomeForm.setSourceResourceId("9708c448423711e8a5630a002700000d");
        zdSourceResourceIncomeForm.setResourceId("2f175a5f350211e8bec9c85b76a65353");
        list.add(zdSourceResourceIncomeForm);
        zdSourceIncomeForm.setIncomeForms(list);
        zdSourceIncomeForm.setSemesterCode("201803");
        zdStockService.saveIncome(zdSourceIncomeForm,sysUserEntity);*/

      /*  List<ZdSourceIncomeResourceForm> zdSourceIncomeResourceForms=new ArrayList<ZdSourceIncomeResourceForm>();
        ZdSourceIncomeResourceForm zdSourceIncomeResourceForm=new ZdSourceIncomeResourceForm();
        zdSourceIncomeResourceForm.setId("007bb7fa1a9c4d1cbbe3f58a1975da91");
        zdSourceIncomeResourceForm.setNum(10);
        zdSourceIncomeResourceForms.add(zdSourceIncomeResourceForm);*/
        //zdStockService.saveIncomeResource(zdSourceIncomeResourceForms,new SysUserEntity());
    }


}