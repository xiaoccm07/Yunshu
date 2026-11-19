package com.yunshu.oa.controller;

import com.yunshu.oa.dao.BizNoticeMapper;
import com.yunshu.oa.entity.BizNotice;
import com.yunshu.oa.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 公告发布与留言互动
 */
@RestController
public class NoticeBoardController {

    @Autowired
    private BizNoticeMapper noticeMapper;

    @PostMapping("/board/notice/publish/commit")
    public Result commit(@RequestBody BizNotice notice) {
        noticeMapper.insert(notice);
        return Result.ok(notice.getId());
    }

    @GetMapping("/board/notice/content/view")
    public Result view(@RequestParam("id") Long id) {
        return Result.ok(noticeMapper.findById(id));
    }

    @PostMapping("/board/remark/submit/save")
    public Result remark(@RequestParam("noticeId") Long noticeId,
                         @RequestParam("content") String content,
                         @RequestAttribute(value = "uid", required = false) Long uid) {
        noticeMapper.insertRemark(noticeId, uid == null ? 0L : uid, content);
        return Result.ok();
    }

    @GetMapping("/board/remark/thread/list")
    public Result thread(@RequestParam("noticeId") Long noticeId) {
        return Result.ok(noticeMapper.listRemarks(noticeId));
    }
}
