package com.web.hawaste.controller;


import com.web.hawaste.domain.SysRoleDo;
import com.web.hawaste.entity.ResultBean;
import com.web.hawaste.utils.PageInfo;
import com.web.hawaste.service.ISysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 * 角色表 前端控制器
 * </p>
 *
 * @author gec
 * @since 2022-05-26
 */
@RestController
@RequestMapping("/manager/role")
public class SysRoleController {

    @Autowired
    private ISysRoleService sysRoleService;

    @GetMapping("/select/{current}/{size}")
    public ResultBean select(@PathVariable Integer current,
                             @PathVariable Integer size,
                             @RequestParam Map<String, Object> params) {
        PageInfo<SysRoleDo> pageInfo = (PageInfo<SysRoleDo>) sysRoleService.selectByCondition(new PageInfo<SysRoleDo>(current, size), params);
        pageInfo.setNavigatePage();
        return ResultBean.ok(pageInfo);
    }
}
