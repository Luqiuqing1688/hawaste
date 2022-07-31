package com.web.hawaste.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.web.hawaste.entity.SysUserRole;
import com.web.hawaste.mapper.SysUserRoleMapper;
import com.web.hawaste.service.ISysUserRoleService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author gec
 * @since 2022-05-26
 */
@Service
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleMapper, SysUserRole> implements ISysUserRoleService {

    /**
     * 根据角色Id和用户Id删除已选人员
     *
     * @param rid
     * @param ids
     * @return
     */
    @Override
    public int deleteBatch(Long rid, Long[] ids) {
        QueryWrapper<SysUserRole> query = new QueryWrapper<>();
        query.eq("role_id", rid)
                .in("user_id", ids);
        return baseMapper.delete(query);
    }

    /**
     * 根据角色id和用户Id添加已选人员
     *
     * @param rid
     * @param ids
     * @return
     */
    @Override
    public boolean insertBatch(Long rid, List<Long> ids) {

        ArrayList<SysUserRole> sysUserRoles = new ArrayList<>();
        ids.forEach(aLong -> {
            SysUserRole sysUserRole = new SysUserRole();
            sysUserRole.setRoleId(rid);
            sysUserRole.setUserId(aLong);
            sysUserRoles.add(sysUserRole);
        });

        return saveBatch(sysUserRoles);
    }
}
