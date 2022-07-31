package com.web.hawaste.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.web.hawaste.entity.ResultBean;
import com.web.hawaste.utils.PageInfo;
import com.web.hawaste.entity.AppVersion;
import com.web.hawaste.service.IAppVersionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author gec
 * @since 2022-05-26
 */
@RestController
@RequestMapping("/manager/app")
public class AppVersionController {

    @Autowired
    private IAppVersionService appVersionService;

    //查询app信息
    @GetMapping("/query")
    public ResultBean<Page> query(Integer current, Integer size) {

        PageInfo<AppVersion> pageInfo = appVersionService.page(new PageInfo<>(current, size));
        //设置分页对象的导航栏数据
        pageInfo.setNavigatePage();
        System.out.println(pageInfo.getRecords());
        return ResultBean.ok(pageInfo);
    }

    //增加或更新app信息
    @PostMapping("/saveOrUpdate")
    public ResultBean saveOrUpdate(@RequestBody AppVersion appVersion) {

        System.out.println(appVersion.getVersionNo());
        appVersionService.saveOrUpdate(appVersion);
        return ResultBean.ok();
    }

    //根据唯一标识查询表单记录
    @PostMapping("/selectOne")
    public ResultBean<AppVersion> selectOne(Long id) {
        AppVersion app = appVersionService.getById(id);
        System.out.println("编辑：" + app);
        return ResultBean.ok(app);
    }

    //删除app信息
    @GetMapping("/delete")
    public ResultBean delete(Long id) {
        appVersionService.removeById(id);
        return ResultBean.ok();
    }
}
