package com.yunshu.oa.controller;

import com.yunshu.oa.entity.JobConfig;
import com.yunshu.oa.util.HttpFetcher;
import com.yunshu.oa.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 定时任务配置与触发
 */
@RestController
@RequestMapping("/job/cron")
public class JobCronController {

    /** 任务配置缓存 */
    private final Map<Long, JobConfig> jobStore = new ConcurrentHashMap<>();

    private final AtomicLong idGenerator = new AtomicLong(1);

    @Autowired
    private ApplicationContext applicationContext;

    /**
     * 保存任务配置
     */
    @PostMapping("/config/save")
    public Result save(@RequestBody JobConfig config) {
        long id = idGenerator.getAndIncrement();
        config.setId(id);
        jobStore.put(id, config);
        return Result.ok(id);
    }

    /**
     * 查询任务列表
     */
    @GetMapping("/config/list")
    public Result list() {
        return Result.ok(jobStore.values());
    }

    /**
     * 按配置触发一次任务
     */
    @PostMapping("/trigger/fire")
    public Result fire(@RequestParam Long id) {
        try {
            JobConfig config = jobStore.get(id);
            if (config == null) {
                return Result.fail("任务不存在");
            }
            // 调用目标格式: beanName.methodName(param)
            String target = config.getInvokeTarget();
            int left = target.indexOf('(');
            int right = target.lastIndexOf(')');
            String beanMethod = target.substring(0, left);
            String param = target.substring(left + 1, right);
            String[] parts = beanMethod.split("\\.");
            String beanName = parts[0];
            String methodName = parts[1];
            if ("httpJob".equals(beanName)) {
                return Result.ok(HttpFetcher.get(param));
            }
            Object bean = applicationContext.getBean(beanName);
            Method method = bean.getClass().getMethod(methodName, String.class);
            Object result = method.invoke(bean, param);
            return Result.ok(result == null ? null : result.toString());
        } catch (Exception e) {
            return Result.fail(e.getMessage());
        }
    }
}
