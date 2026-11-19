package com.yunshu.oa.controller;

import com.yunshu.oa.util.Result;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 审批流条件表达式试算
 */
@RestController
public class RuleEngineController {

    private final ExpressionParser parser = new SpelExpressionParser();

    @PostMapping("/rule/flow/expr/eval")
    public Result eval(@RequestParam("expr") String expr) {
        StandardEvaluationContext context = new StandardEvaluationContext();
        context.setVariable("amount", 1000);
        context.setVariable("level", 3);
        Object value = parser.parseExpression(expr).getValue(context);
        return Result.ok(value == null ? "null" : value.toString());
    }
}
