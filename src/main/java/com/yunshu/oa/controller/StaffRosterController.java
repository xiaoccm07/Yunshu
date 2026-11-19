package com.yunshu.oa.controller;

import com.yunshu.oa.dao.SysAccountMapper;
import com.yunshu.oa.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 员工通讯录与人员检索
 */
@RestController
public class StaffRosterController {

    @Autowired
    private SysAccountMapper accountMapper;

    @GetMapping("/staff/roster/directory/query")
    public Result query(@RequestParam(value = "keyword", required = false) String keyword,
                        @RequestParam(value = "sort", defaultValue = "id asc") String sort) {
        return Result.ok(accountMapper.search(keyword, sort));
    }

    @GetMapping("/staff/roster/detail/fetch")
    public Result fetch(@RequestParam("uid") Long uid) {
        return Result.ok(accountMapper.findById(uid));
    }
}
