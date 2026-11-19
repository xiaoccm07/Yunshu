package com.yunshu.oa.controller;

import com.yunshu.oa.dao.BizNoticeMapper;
import com.yunshu.oa.entity.BizNotice;
import com.yunshu.oa.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;

/**
 * 集团简报 RSS 导入
 */
@RestController
public class FeedImportController {

    @Autowired
    private BizNoticeMapper noticeMapper;

    @PostMapping("/feed/bulletin/import/parse")
    public Result parse(@RequestBody String xml) throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(new ByteArrayInputStream(xml.getBytes(StandardCharsets.UTF_8)));
        NodeList items = doc.getElementsByTagName("item");
        int count = 0;
        for (int i = 0; i < items.getLength(); i++) {
            String title = items.item(i).getFirstChild().getTextContent();
            BizNotice notice = new BizNotice();
            notice.setTitle(title);
            notice.setContent("来自集团简报的同步内容");
            notice.setAuthorId(0L);
            noticeMapper.insert(notice);
            count++;
        }
        return Result.ok(count);
    }
}
