package com.web.hawaste.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.web.hawaste.entity.SysUserRole;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author gec
 * @since 2022-05-26
 */
public interface ISysUserRoleService extends IService<SysUserRole> {

    int deleteBatch(Long rid, Long[] ids);

    boolean insertBatch(Long rid, List<Long> ids);
}
