package com.web.hawaste.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.web.hawaste.entity.ResultBean;
import com.web.hawaste.utils.PageInfo;
import com.web.hawaste.domain.ExamineDo;
import com.web.hawaste.service.IExamineService;
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
@RequestMapping("/manager/examine")
public class ExamineController {

    @Autowired
    private IExamineService examineService;

    //查询订单扩展类
    @GetMapping("/select/{current}/{size}")
    public ResultBean<Page> select(@PathVariable int current, @PathVariable int size, @RequestParam Map<String, Object> params) {
        System.out.println("当前页数：" + current);
        System.out.println("每页记录数：" + size);

        PageInfo<ExamineDo> pageInfo = (PageInfo<ExamineDo>) examineService.selectByCondition(new PageInfo<>(current, size), params);
        pageInfo.setNavigatePage();

        return ResultBean.ok(pageInfo);
    }
}
