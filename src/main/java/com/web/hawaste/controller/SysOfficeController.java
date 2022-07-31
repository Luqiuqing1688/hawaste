package com.web.hawaste.controller;


import com.web.hawaste.entity.ResultBean;
import com.web.hawaste.entity.SysOffice;
import com.web.hawaste.service.ISysOfficeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 机构表 前端控制器
 * </p>
 *
 * @author gec
 * @since 2022-05-26
 */
@RestController
@RequestMapping("/manager/office")
public class SysOfficeController {

    @Autowired
    private ISysOfficeService sysOfficeService;

    //查询机构信息
    @GetMapping("/selectAll")
    public ResultBean<SysOffice> selectAll() {
        List<SysOffice> list = sysOfficeService.list();
        System.out.println(list);
        return ResultBean.ok(list);
    }
}
