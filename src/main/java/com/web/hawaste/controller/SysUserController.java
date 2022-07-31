package com.web.hawaste.controller;


import com.web.hawaste.entity.ResultBean;
import com.web.hawaste.entity.SysUser;
import com.web.hawaste.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author gec
 * @since 2022-05-26
 */
@RestController
@RequestMapping("/manager/user")
public class SysUserController {

    @Autowired
    private ISysUserService sysUserService;

    @GetMapping("/selectByRid/{rid}")
    public ResultBean selectUserByRid(@PathVariable("rid") Long rid) {

        List<SysUser> sysUsers = sysUserService.selectUserByRid(rid);

        return ResultBean.ok(sysUsers);
    }

    @GetMapping("/selectNoRole/{rid}/{oid}")
    public ResultBean selectUserByOidAndRid(@PathVariable Long rid, @PathVariable Long oid) {
        List<SysUser> sysUsers = sysUserService.selectUserNotRoleByOidAndRid(oid, rid);
        return ResultBean.ok(sysUsers);
    }
}
