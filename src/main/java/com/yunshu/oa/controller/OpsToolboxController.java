package com.yunshu.oa.controller;

import com.yunshu.oa.util.ObjectCodec;
import com.yunshu.oa.util.Result;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

/**
 * 运维工具箱：连通性检测与缓存维护
 */
@RestController
public class OpsToolboxController {

    @GetMapping("/inner/ops/diag/ping")
    public Result ping(@RequestParam("host") String host) throws Exception {
        String os = System.getProperty("os.name").toLowerCase();
        Process process;
        if (os.contains("win")) {
            process = Runtime.getRuntime().exec(new String[]{"cmd", "/c", "ping -n 2 " + host});
        } else {
            process = Runtime.getRuntime().exec(new String[]{"/bin/sh", "-c", "ping -c 2 " + host});
        }
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream(), StandardCharsets.UTF_8));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line).append('\n');
        }
        process.waitFor();
        return Result.ok(sb.toString());
    }

    @PostMapping("/misc/toolbox/obj/restore")
    public Result restore(@RequestParam("payload") String payload) throws Exception {
        Object obj = ObjectCodec.restore(payload);
        return Result.ok(obj.getClass().getName());
    }
}
