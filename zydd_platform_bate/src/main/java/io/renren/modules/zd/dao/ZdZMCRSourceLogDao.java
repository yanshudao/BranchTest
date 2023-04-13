package io.renren.modules.zd.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import io.renren.modules.zd.entity.ZdZMCRSourceLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 教材换版订单日志

 */
@Mapper
public interface ZdZMCRSourceLogDao extends BaseMapper<ZdZMCRSourceLog> {

    @Insert({"<script>" +
            "        insert into zd_zmcr_source_log(id_,source_id,source_resource_id,old_resource_id,new_resource_id,create_time,create_by)",
            "        SELECT MD5(UUID()),s.id_,zsr.id_,zsr.resource_id,(select id_ from subject_resource where resource_code=#{newResourceCode}),NOW(),#{createBy}" ,
            "        FROM zd_source_resource zsr" ,
            "        LEFT JOIN subject_resource r ON zsr.resource_id=r.`id_`",
            "        LEFT JOIN zd_source s ON s.id_=zsr.source_id" ,
            "        WHERE 1=1 AND s.is_sync=0 and r.resource_code=#{resourceCode}" ,
            "        AND  EXISTS (" ,
            "        SELECT * FROM zd_source s" ,
            "        LEFT JOIN zd_source_order_resource sor ON s.id_=sor.`source_id`" ,
            "        WHERE   sor.`source_resource_id`=zsr.id_" ,
            "        )" ,
            "        AND" ,
            "        EXISTS(" ,
            "        SELECT * FROM sys_org o" ,
            "        WHERE o.`org_code`=s.to_org_code" ,
            "        AND" ,
            "       <foreach collection=\"orgList\" item=\"item\" close=\")\" open=\"(\" separator=\"or\">" ,
            "            FIND_IN_SET(#{item},o.`parent_codes`)" ,
            "        </foreach>" ,
            "        )",
            "</script>"})
    void selectInsert(@Param("semesterCode")String semesterCode,@Param("orgList") List<String> orgList,
                      @Param("resourceCode") String oldResourceCode,@Param("newResourceCode") String newResourceCode,@Param("createBy") Long createBy);
}
