package com.yunshu.oa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 门户统一跳转出口
 */
@Controller
public class PortalDispatchController {

    @GetMapping("/portal/gateway/dispatcher/jump")
    public String jump(@RequestParam("to") String to) {
        return "redirect:" + to;
    }
}
