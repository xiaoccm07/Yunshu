package com.yunshu.oa.dao;

import com.yunshu.oa.entity.FlowInstance;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FlowInstanceMapper {

    FlowInstance findById(@Param("id") Long id);

    int insert(FlowInstance inst);

    List<FlowInstance> listByApplicant(@Param("applicantId") Long applicantId, @Param("order") String order);

    int updateStatus(@Param("id") Long id, @Param("status") Integer status);
}
