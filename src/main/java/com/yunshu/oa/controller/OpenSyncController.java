package com.yunshu.oa.controller;

import com.alibaba.fastjson.JSON;
import com.yunshu.oa.util.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 第三方系统数据同步入口
 */
@RestController
public class OpenSyncController {

    @PostMapping("/open/partner/sync/push")
    public Result push(@RequestBody String raw) {
        Object obj = JSON.parse(raw);
        return Result.ok(obj.getClass().getName());
    }
}
