package com.yunshu.oa.controller;

import com.yunshu.oa.dao.FlowInstanceMapper;
import com.yunshu.oa.entity.FlowInstance;
import com.yunshu.oa.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 审批单据提交与流转
 */
@RestController
public class FlowTicketController {

    @Autowired
    private FlowInstanceMapper flowInstanceMapper;

    // 发起审批
    @PostMapping("/flow/ticket/draft/submit")
    public Result submit(@RequestBody FlowInstance inst) {
        if (inst.getStatus() == null) {
            inst.setStatus(0);
        }
        flowInstanceMapper.insert(inst);
        return Result.ok(inst.getId());
    }

    // 我的审批列表，支持自定义排序
    @GetMapping("/flow/ticket/mine/list")
    public Result mineList(@RequestParam("applicantId") Long applicantId,
                           @RequestParam(value = "order", defaultValue = "id desc") String order) {
        return Result.ok(flowInstanceMapper.listByApplicant(applicantId, order));
    }

    // 审批单详情
    @GetMapping("/flow/ticket/detail/open")
    public Result detail(@RequestParam("id") Long id) {
        return Result.ok(flowInstanceMapper.findById(id));
    }

    // 审批状态流转
    @PostMapping("/flow/ticket/state/change")
    public Result stateChange(@RequestParam("id") Long id, @RequestParam("status") Integer status) {
        flowInstanceMapper.updateStatus(id, status);
        return Result.ok();
    }
}
