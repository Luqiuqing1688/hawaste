package com.web.hawaste.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.web.hawaste.domain.SysRoleDo;
import com.web.hawaste.entity.SysRole;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 角色表 Mapper 接口
 * </p>
 *
 * @author gec
 * @since 2022-05-26
 */
public interface SysRoleMapper extends BaseMapper<SysRole> {
    @Select("select   " +
            "  sr.*,  " +
            "  so.name office_name  " +
            "from   " +
            "  sys_role sr,  " +
            "  sys_office so" +
            " ${ew.customSqlSegment}")
    IPage<SysRoleDo> selectByCondition(IPage<SysRoleDo> page, Wrapper ew);

    @Select("  select  " +
            "    sr.*  " +
            "  from  " +
            "    sys_user su,  " +
            "    sys_user_role sur,  " +
            "    sys_role sr    " +
            "  where  " +
            "    su.del_flag=0  " +
            "  and sur.del_flag=0  " +
            "  and sr.del_flag =0  " +
            "  and su.id =sur.user_id  " +
            "  and sur.role_id =sr.id  " +
            "  and su.id=#{uid}")
    List<SysRole> selectByUid(Long uid);

    @Select(" select " +
            "  sr.*, " +
            "  so.name office_name " +
            " from " +
            "  sys_role sr, " +
            "  sys_office so " +
            " where " +
            "  sr.del_flag = 0 " +
            "  and so.del_flag = 0 " +
            "  and sr.office_id = so.id " +
            "  and sr.id =#{rid}")
    SysRoleDo selectRoleAndOfficeByRid(Long rid);
}
