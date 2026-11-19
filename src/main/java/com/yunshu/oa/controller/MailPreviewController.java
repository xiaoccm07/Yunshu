package com.yunshu.oa.controller;

import freemarker.template.Configuration;
import freemarker.template.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * 邮件通知模板预览
 */
@RestController
public class MailPreviewController {

    @Autowired
    private Configuration freemarkerConfig;

    @PostMapping("/tpl/mail/preview/render")
    public String render(@RequestParam("tpl") String tpl,
                         @RequestParam(value = "name", defaultValue = "同事") String name) throws Exception {
        Template template = new Template("mailPreview", new StringReader(tpl), freemarkerConfig);
        Map<String, Object> model = new HashMap<>();
        model.put("name", name);
        model.put("system", "云枢管理系统");
        StringWriter writer = new StringWriter();
        template.process(model, writer);
        return writer.toString();
    }
}
