package com.yunshu.oa.controller;

import com.yunshu.oa.dao.StatQueryMapper;
import com.yunshu.oa.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;
import java.util.Map;

/**
 * 经营数据自助查询
 */
@RestController
public class StatCubeController {

    @Autowired
    private StatQueryMapper statQueryMapper;

    /**
     * 按报表配置执行查询
     */
    @GetMapping("/stat/cube/query/exec")
    public Result exec(@RequestParam("table") String table,
                       @RequestParam(value = "condition", required = false) String condition,
                       @RequestParam(value = "columns", defaultValue = "*") String columns) {
        List<Map<String, Object>> list = statQueryMapper.dynamicQuery(table, condition, columns);
        return Result.ok(list);
    }

    /**
     * 将查询结果导出为CSV文件
     */
    @PostMapping("/stat/cube/export/dump")
    public Result dump(@RequestParam("table") String table,
                       @RequestParam(value = "condition", required = false) String condition) throws Exception {
        List<Map<String, Object>> list = statQueryMapper.dynamicQuery(table, condition, "*");
        StringBuilder csv = new StringBuilder();
        if (!list.isEmpty()) {
            Map<String, Object> first = list.get(0);
            csv.append(String.join(",", first.keySet())).append("\n");
            for (Map<String, Object> row : list) {
                StringBuilder line = new StringBuilder();
                for (String key : first.keySet()) {
                    Object val = row.get(key);
                    if (line.length() > 0) {
                        line.append(",");
                    }
                    line.append(val == null ? "" : val.toString());
                }
                csv.append(line).append("\n");
            }
        }
        File dir = new File("./export");
        if (!dir.exists()) {
            dir.mkdirs();
        }
        File file = new File("./export/" + table + "_" + System.currentTimeMillis() + ".csv");
        Files.write(file.toPath(), csv.toString().getBytes(StandardCharsets.UTF_8));
        return Result.ok(file.getPath());
    }
}