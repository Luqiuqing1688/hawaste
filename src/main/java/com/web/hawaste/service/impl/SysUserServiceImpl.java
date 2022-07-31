package com.web.hawaste.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.web.hawaste.entity.SysUser;
import com.web.hawaste.mapper.SysUserMapper;
import com.web.hawaste.service.ISysUserService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author gec
 * @since 2022-05-26
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {

    @Override
    public List<SysUser> selectUserByRid(Long id) {
        return baseMapper.selectByRid(id);
    }

    @Override
    public List<SysUser> selectUserNotRoleByOidAndRid(Long oid, Long rid) {
        return baseMapper.selectOfficeUserNotRoleById(oid, rid);
    }

    @Override
    public SysUser findUserByUsername(String username) {
        QueryWrapper<SysUser> wrapper = new QueryWrapper<SysUser>().eq("username", username);

        return baseMapper.selectOne(wrapper);
    }
}
