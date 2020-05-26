package com.ccut.shixun.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller  //@Controller可以暂时理解为允许该页面接收前端的请求
public class IndexController {

    @GetMapping("/")
    public String index() {
        return "index";
    }

}
