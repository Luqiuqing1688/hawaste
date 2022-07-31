package com.web.hawaste.controller;


import com.web.hawaste.entity.ResultBean;
import com.web.hawaste.service.ISysUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author gec
 * @since 2022-05-26
 */
@RestController
@RequestMapping("/manager/role")
public class SysUserRoleController {

    @Autowired
    private ISysUserRoleService sysUserRoleService;

    /**
     * 批量删除角色已选用户
     *
     * @param rid
     * @param ids
     * @return
     */
    @GetMapping("/deleteBatch")
    public ResultBean deleteBatch(Long rid, Long[] ids) {
        return ResultBean.ok(sysUserRoleService.deleteBatch(rid, ids));
    }

    /**
     * 批量添加已选用户的角色
     *
     * @param rid
     * @param ids
     * @return
     */
    @GetMapping("/insertBatch")
    public ResultBean insertBatch(Long rid, Long[] ids) {
        return ResultBean.ok(sysUserRoleService.insertBatch(rid, Arrays.asList(ids)));
    }
}
