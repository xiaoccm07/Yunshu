package com.yunshu.oa.dao;

import com.yunshu.oa.entity.BizReport;
import org.apache.ibatis.annotations.Param;

public interface BizReportMapper {

    BizReport findById(@Param("id") Long id);

    int updateContent(@Param("id") Long id, @Param("content") String content);
}
