package io.renren.modules.zd.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import io.renren.modules.zd.entity.ZdZMCROrderLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 教材换版订单日志

 */
@Mapper
public interface ZdZMCROrderLogDao extends BaseMapper<ZdZMCROrderLog> {

    @Insert({"<script> " ,
            "insert into zd_zmcr_order_log(id_,order_id,order_resource_id,old_zmcr_id,new_zmcr_id,create_time,create_by)" ,
            "select md5(uuid()),zor.order_id,zor.id_,zor.zmcr_id,#{newId},NOW(),#{createBy} from" ,
            "        zd_major_course_resource om" ,
            "        left join zd_order_resource zor on om.id_=zor.zmcr_id" ,
            "        left join subject_resource r on om.resource_code=r.resource_code" ,
            "        where 1=1" ,
            "        and zor.zmcr_id =#{oldId}" ,

            "        and" ,
            "        EXISTS(" ,
            "        SELECT * FROM sys_org o" ,
            "        WHERE o.`org_code`=zor.org_code" ,
            "        AND" ,
            "        <foreach collection=\"orgList\" item=\"item\" close=\")\" open=\"(\" separator=\"or\">" ,
            "            FIND_IN_SET(#{item},o.`parent_codes`)" ,
            "        </foreach>" ,
            "" ,
            "        )" ,
            "        AND not EXISTS (" ,
            "        SELECT * FROM zd_source s" ,
            "        LEFT JOIN zd_source_order_resource sor ON s.id_=sor.`source_id`" ,
            "        WHERE s.`is_sync`=1 AND sor.`order_resource_id`=zor.id_" ,
            "        )  </script>"})
    void selectInsert(@Param("semesterCode") String semesterCode,@Param("orgList") List<String> orgList,@Param("oldId") String oldId,
                      @Param("newId")String newId,@Param("createBy") Long createBy);
}
