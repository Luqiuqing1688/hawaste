package com.web.hawaste.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.web.hawaste.entity.ResultBean;
import com.web.hawaste.utils.PageInfo;
import com.web.hawaste.entity.Statute;
import com.web.hawaste.service.IStatuteService;
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
@RequestMapping("/manager/statute")
public class StatuteController {

    @Autowired
    private IStatuteService statuteService;

    @GetMapping("/select/{current}/{size}")
    public ResultBean<Page> select(@PathVariable int current, @PathVariable int size, @RequestParam(required = false) Integer type) {
        PageInfo<Statute> page = (PageInfo<Statute>) statuteService.selectByConditions(new PageInfo<>(current, size), type);
        page.setNavigatePage();
        return ResultBean.ok(page);
    }

    /*
     * 查询某个statute信息
     * */
    @RequestMapping("selectOne")
    public ResultBean selectOne(Long id) {
        return ResultBean.ok(statuteService.getById(id));
    }

    /**
     * 更新或插入   无主键是插入数据，有主键是更新
     *
     * @param statute
     * @return
     */
    @PostMapping("saveOrUpdate")
    public ResultBean saveOrUpdate(@RequestBody Statute statute) {
        statuteService.saveOrUpdate(statute);
        return ResultBean.ok();
    }

    @RequestMapping("delete")
    public ResultBean delete(Long id) {
        statuteService.removeById(id);
        return ResultBean.ok();
    }
}
