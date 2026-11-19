package com.yunshu.oa.dao;

import com.yunshu.oa.entity.SysAccount;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysAccountMapper {

    SysAccount findByAccount(@Param("account") String account);

    SysAccount findById(@Param("id") Long id);

    List<SysAccount> search(@Param("keyword") String keyword, @Param("sort") String sort);

    int updatePwd(@Param("account") String account, @Param("pwd") String pwd);

    int updateProfile(SysAccount account);
}
