package com.yunshu.oa.dao;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 经营报表动态查询
 */
public interface StatQueryMapper {

    List<Map<String, Object>> dynamicQuery(@Param("table") String table,
                                           @Param("condition") String condition,
                                           @Param("columns") String columns);
}