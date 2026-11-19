package com.yunshu.oa.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 门户统一跳转出口
 */
@RestController
public class PortalDispatchController {

    @GetMapping("/portal/gateway/dispatcher/jump")
    public String jump(@RequestParam("to") String to) {
        return "redirect:" + to;
    }
}
