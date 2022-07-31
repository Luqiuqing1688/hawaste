package com.web.hawaste.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.web.hawaste.domain.SysRoleDo;
import com.web.hawaste.entity.SysRole;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 角色表 服务类
 * </p>
 *
 * @author gec
 * @since 2022-05-26
 */
public interface ISysRoleService extends IService<SysRole> {

    IPage<SysRoleDo> selectByCondition(IPage<SysRoleDo> page, Map<String, Object> params);

    List<SysRole> selectRoleByUserId(Long uid);

    SysRoleDo selectOne(Long rid);

    boolean updateById(SysRole role);
}
