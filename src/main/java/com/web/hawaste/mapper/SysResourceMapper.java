package com.web.hawaste.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.web.hawaste.entity.SysResource;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author gec
 * @since 2022-05-26
 */
public interface SysResourceMapper extends BaseMapper<SysResource> {

    @Select("  select DISTINCT  " +
            "  sre.*  " +
            "  from  " +
            "  sys_user sus,  " +
            "  sys_user_role sur,  " +
            "  sys_role sro,  " +
            "  sys_role_resource srr,  " +
            "  sys_resource sre  " +
            "  where  " +
            "  sus.id = #{uid}  " +
            "  and sus.del_flag = 0  " +
            "  and sur.del_flag = 0  " +
            "  and sro.del_flag = 0  " +
            "  and srr.del_flag = 0  " +
            "  and sre.del_flag = 0  " +
            "  and sus.id =sur.user_id  " +
            "  and sur.role_id = sro.id  " +
            "  and sro.id = srr.role_id  " +
            "  and srr.resource_id = sre.id")
    List<SysResource> selectByUid(Long uid);

    @Select(" select " +
            "  sre.* " +
            " from " +
            "  sys_role sr, " +
            "  sys_role_resource srr, " +
            "  sys_resource sre " +
            " where " +
            "  srr.role_id = 24 " +
            " and sr.del_flag = 0 " +
            " and srr.del_flag = 0 " +
            " and sre.del_flag = 0 " +
            " and sr.id = srr.role_id " +
            " and srr.resource_id =sre.id")
    List<SysResource> selectByRid(Long rid);
}
