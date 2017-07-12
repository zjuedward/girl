package com.imooc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zxw on 2017/2/24.
 */
@Controller
public class PageController {

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String say() {
        return "index";
    }

    @RequestMapping(value = "/study", method = RequestMethod.GET)
    public String study(ModelMap map) {
        map.addAttribute("host", "http://blog.didispace.com");
        // return模板文件的名称，对应src/main/resources/templates/study.html
        return "study";
    }
}
