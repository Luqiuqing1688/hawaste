package com.web.hawaste.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.web.hawaste.entity.SysUser;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author gec
 * @since 2022-05-26
 */
public interface SysUserMapper extends BaseMapper<SysUser> {

    /**
     * 根据角色Id查询已分配的人员
     *
     * @param rid
     * @return
     */
    @Select(" select  " +
            "  su.* " +
            " from " +
            "  sys_user su, " +
            "  sys_user_role sur " +
            " where " +
            "  su.del_flag=0 " +
            " and sur.del_flag=0 " +
            " and sur.role_id=#{rid} " +
            " and su.id = sur.user_id")
    List<SysUser> selectByRid(Long rid);

    /**
     * 根据机构Id和角色Id，查询未分配的人员
     *
     * @param officeId
     * @param rid
     * @return
     */
    @Select(" select " +
            "  * " +
            " from " +
            "  sys_user " +
            " where " +
            "  office_id=#{officeId} " +
            " and id not in( " +
            " select  " +
            "  su.id " +
            " from " +
            "  sys_user su, " +
            "  sys_user_role sur " +
            " where " +
            "  su.del_flag=0 " +
            " and sur.del_flag=0 " +
            " and su.id = sur.user_id " +
            " and sur.role_id = #{rid} " +
            " )")
    List<SysUser> selectOfficeUserNotRoleById(@Param("officeId") Long officeId, @Param("rid") Long rid);
}
