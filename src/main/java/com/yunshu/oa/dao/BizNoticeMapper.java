package com.yunshu.oa.dao;

import com.yunshu.oa.entity.BizNotice;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface BizNoticeMapper {

    int insert(BizNotice notice);

    BizNotice findById(@Param("id") Long id);

    int insertRemark(@Param("noticeId") Long noticeId, @Param("uid") Long uid, @Param("content") String content);

    List<Map<String, Object>> listRemarks(@Param("noticeId") Long noticeId);
}
