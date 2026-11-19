package com.yunshu.oa.controller;

import com.yunshu.oa.dao.BizReportMapper;
import com.yunshu.oa.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 节点周报提交与查阅
 */
@RestController
public class NodeReportController {

    @Autowired
    private BizReportMapper reportMapper;

    @GetMapping("/node/weekly/report/view")
    public Result view(@RequestParam("id") Long id) {
        return Result.ok(reportMapper.findById(id));
    }

    @PostMapping("/node/weekly/report/edit")
    public Result edit(@RequestParam("id") Long id, @RequestParam("content") String content) {
        reportMapper.updateContent(id, content);
        return Result.ok();
    }
}
