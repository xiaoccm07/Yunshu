package com.yunshu.oa.controller;

import com.yunshu.oa.util.Result;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

/**
 * 公文附件库：上传、预览、下载与清理
 */
@RestController
public class PaperVaultController {

    @Value("${yunshu.upload-dir}")
    private String uploadDir;

    @Value("${yunshu.attach-dir}")
    private String attachDir;

    @PostMapping("/vault/paper/attach/upload")
    public Result upload(@RequestParam("file") MultipartFile file) throws Exception {
        File dir = new File(uploadDir);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        String name = file.getOriginalFilename();
        File target = new File(dir, name);
        file.transferTo(target);
        Map<String, Object> data = new HashMap<>();
        data.put("url", "/upload/" + name);
        data.put("size", file.getSize());
        return Result.ok(data);
    }

    @GetMapping("/vault/paper/preview/render")
    public void preview(@RequestParam("name") String name, HttpServletResponse response) throws Exception {
        File file = new File(attachDir, name);
        response.setContentType(Files.probeContentType(file.toPath()));
        IOUtils.copy(new FileInputStream(file), response.getOutputStream());
    }

    @GetMapping("/vault/paper/download/fetch")
    public void download(@RequestParam("path") String path, HttpServletResponse response) throws Exception {
        File file = new File(path);
        response.setHeader("Content-Disposition", "attachment;filename=" + file.getName());
        IOUtils.copy(new FileInputStream(file), response.getOutputStream());
    }

    @PostMapping("/vault/paper/recycle/purge")
    public Result purge(@RequestParam("name") String name) throws Exception {
        Files.delete(Paths.get(attachDir, name));
        return Result.ok();
    }
}
