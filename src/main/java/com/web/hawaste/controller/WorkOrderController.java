package com.web.hawaste.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.web.hawaste.domain.WorkOrderDetailDo;
import com.web.hawaste.entity.ResultBean;
import com.web.hawaste.utils.PageInfo;
import com.web.hawaste.domain.WorkOrderDo;
import com.web.hawaste.service.IWorkOrderService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author gec
 * @since 2022-05-26
 */
@RestController
@RequestMapping("/manager/work")
public class WorkOrderController {

    @Autowired
    private IWorkOrderService workOrderService;

    @GetMapping("/select/{current}/{size}")
    public ResultBean<Page> select(@PathVariable Integer current,
                                   @PathVariable Integer size,
                                   @RequestParam Map<String, Object> params) {

        PageInfo<WorkOrderDo> pageInfo = (PageInfo<WorkOrderDo>) workOrderService.selectByCondition(new PageInfo<WorkOrderDo>(current, size), params);
        //设置分页导航栏数据
        pageInfo.setNavigatePage();

        return ResultBean.ok(pageInfo);
    }

    @GetMapping("/selectOne/{oid}")
    @RequiresPermissions("center:select")  //权限限制
    public ResultBean selectOne(@PathVariable Long oid) {
        WorkOrderDetailDo detail = workOrderService.selectDetailById(oid);
        return ResultBean.ok(detail);
    }
}
