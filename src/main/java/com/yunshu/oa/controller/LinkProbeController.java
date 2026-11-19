package com.yunshu.oa.controller;

import com.yunshu.oa.util.HttpFetcher;
import com.yunshu.oa.util.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 友情链接可用性巡检
 */
@RestController
public class LinkProbeController {

    @GetMapping("/misc/linkcheck/probe/run")
    public Result run(@RequestParam("target") String target) throws Exception {
        String body = HttpFetcher.get(target);
        return Result.ok(body);
    }
}
