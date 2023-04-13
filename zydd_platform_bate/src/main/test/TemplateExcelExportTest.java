
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jodd.bean.BeanUtil;
import org.apache.commons.collections.MapUtils;
import org.apache.poi.ss.usermodel.Workbook;


import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.TemplateExportParams;

import org.junit.Test;

import com.google.common.collect.Lists;
import org.springframework.beans.BeanUtils;

public class TemplateExcelExportTest {


    @Test
    public void fe_map() throws Exception {
        TemplateExportParams params = new TemplateExportParams(
                "WEB-INF/doc/专项支出用款申请书_map.xls");
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("date", "2014-12-25");
        map.put("money", 2000000.00);
        map.put("upperMoney", "贰佰万");
        map.put("company", "执笔潜行科技有限公司");
        map.put("bureau", "财政局");
        map.put("person", "JueYue");
        map.put("phone", "1879740****");
        List<Map<String, String>> listMap = new ArrayList<Map<String, String>>();
        for (int i = 0; i < 4; i++) {
            Map<String, String> lm = new HashMap<String, String>();
            lm.put("id", i + 1 + "");
            lm.put("zijin", i * 10000 + "");
            lm.put("bianma", "A001");
            lm.put("mingcheng", "设计");
            lm.put("xiangmumingcheng", "EasyPoi " + i + "期");
            lm.put("quancheng", "开源项目");
            lm.put("sqje", i * 10000 + "");
            lm.put("hdje", i * 10000 + "");

            listMap.add(lm);
        }
        map.put("maplist", listMap);

        Workbook workbook = ExcelExportUtil.exportExcel(params, map);
        File savefile = new File("D:/excel/");
        if (!savefile.exists()) {
            savefile.mkdirs();
        }
        FileOutputStream fos = new FileOutputStream("D:/excel/专项支出用款申请书_map.xls");
        workbook.write(fos);
        fos.close();
    }

    @Test
    public void test2() throws Exception {
        TemplateExportParams params = new TemplateExportParams(
                "doc/merge_test.xls");
        Map<String, Object> map = new HashMap<String, Object>();

        List<Map<String, String>> list = new ArrayList<Map<String, String>>();

        for (int i = 0; i < 8; i++) {
            Map<String, String> m = new HashMap<String, String>();
            m.put("id", "id" + "1");
            m.put("uname", "name" + "1");
            m.put("amount", i + "");
            list.add(m);
        }
        map.put("list", list);
        Workbook workbook = ExcelExportUtil.exportExcel(params, map);
        File savefile = new File("D:/excel/");
        if (!savefile.exists()) {
            savefile.mkdirs();
        }
        FileOutputStream fos = new FileOutputStream("D:/excel/merge_test.xls");
        workbook.write(fos);
        fos.close();
    }

}