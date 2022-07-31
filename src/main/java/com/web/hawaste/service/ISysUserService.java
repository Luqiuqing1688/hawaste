package com.web.hawaste.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.web.hawaste.entity.SysUser;

import java.util.List;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author gec
 * @since 2022-05-26
 */
public interface ISysUserService extends IService<SysUser> {

    List<SysUser> selectUserByRid(Long id);

    List<SysUser> selectUserNotRoleByOidAndRid(Long oid, Long rid);

    SysUser findUserByUsername(String username);
}
