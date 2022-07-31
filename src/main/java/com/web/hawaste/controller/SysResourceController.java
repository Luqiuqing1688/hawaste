package com.web.hawaste.controller;


import com.web.hawaste.domain.SysRoleDo;
import com.web.hawaste.entity.ResultBean;
import com.web.hawaste.entity.SysResource;
import com.web.hawaste.service.ISysResourceService;
import com.web.hawaste.service.ISysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author gec
 * @since 2022-05-26
 */
@RestController
@RequestMapping("/manager/menu")
public class SysResourceController {

    @Autowired
    private ISysResourceService service;
    @Autowired
    private ISysRoleService sysRoleService;

    @GetMapping("/list")
    public ResultBean<List<SysResource>> select() {
        return ResultBean.ok(service.list());
    }

    @PostMapping("saveOrUpdate")
    public ResultBean saveOrUpdate(@RequestBody SysRoleDo sysRole, HttpSession session) {
        //TODO 更新创建人   从状态对象中获取user名   后续完成登录功能后实现
        sysRole.setCreateBy("gec");
//        SysUser loginUser = (SysUser) session.getAttribute("loginUser");
//        if (sysRole.getId()==null){
//            sysRole.setCreateBy(loginUser.getName());
//        }
        //无异常直接返回，出异常由统一异常处理Controller处理返回
        //公共serviceImpl生成的添加或更新方法  有主键是更新，无主键值是添加
        sysRoleService.updateById(sysRole);
        return ResultBean.ok();
    }
}
