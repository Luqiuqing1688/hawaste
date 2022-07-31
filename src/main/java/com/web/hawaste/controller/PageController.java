package com.web.hawaste.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 页面跳转控制器
 */
@Controller
public class PageController {
    /**
     * 一级路径页面跳转
     *
     * @param url
     * @return
     */
    @GetMapping("{url}.html")
    public String module1(@PathVariable("url") String url) {

        return url;
    }

    /**
     * 二级路径页面跳转
     *
     * @param module
     * @param url
     * @return
     */
    @GetMapping("{module}/{url}.html")
    public String module2(@PathVariable("module") String module,
                          @PathVariable("url") String url) {
        return module + "/" + url;
    }

    /**
     * 三级路径页面跳转
     *
     * @param module
     * @param classify
     * @param url
     * @return
     */
    @GetMapping("{module}/{classify}/{url}.html")
    public String module3(@PathVariable("module") String module,
                          @PathVariable("classify") String classify,
                          @PathVariable("url") String url) {
        return module + "/" + classify + "/" + url;
    }

    /**
     * 四级页面跳转
     *
     * @param module
     * @param classify
     * @param cat
     * @param url
     * @return
     */
    @GetMapping("{module}/{classify}/{cat}/{url}.html")
    public String module4(@PathVariable("module") String module,
                          @PathVariable("classify") String classify,
                          @PathVariable("cat") String cat,
                          @PathVariable("url") String url) {
        return module + "/" + classify + "/" + cat + "/" + url;
    }

}
