package com.yunshu.oa.controller;

import com.yunshu.oa.dao.SysAccountMapper;
import com.yunshu.oa.entity.SysAccount;
import com.yunshu.oa.util.JwtBox;
import com.yunshu.oa.util.Result;
import com.yunshu.oa.util.SecretBox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 登录、口令找回与账号信息维护
 */
@RestController
public class AccountGateController {

    @Autowired
    private SysAccountMapper accountMapper;

    private final Map<String, String> resetTokens = new ConcurrentHashMap<>();

    @PostMapping("/entry/gate/passport/verify")
    public Result verify(@RequestBody Map<String, String> body, HttpServletResponse response) {
        String account = body.get("account");
        String pwd = body.get("pwd");
        SysAccount user = accountMapper.findByAccount(account);
        if (user == null) {
            return Result.fail("账号不存在");
        }
        String md5 = DigestUtils.md5DigestAsHex(pwd.getBytes(StandardCharsets.UTF_8));
        if (!md5.equals(user.getPwd())) {
            return Result.fail("密码错误");
        }
        String token = JwtBox.issue(user.getId(), user.getAccount());
        response.addHeader("Authorization", token);
        Map<String, Object> data = new HashMap<>();
        data.put("token", token);
        data.put("account", user.getAccount());
        data.put("nickname", user.getNickname());
        data.put("roleId", user.getRoleId());
        return Result.ok(data);
    }

    @PostMapping("/entry/gate/credential/reset")
    public Result reset(@RequestBody Map<String, String> body) {
        String account = body.get("account");
        SysAccount user = accountMapper.findByAccount(account);
        if (user == null) {
            return Result.fail("账号不存在");
        }
        String token = UUID.randomUUID().toString().replace("-", "").substring(0, 6);
        resetTokens.put(account, token);
        Map<String, Object> data = new HashMap<>();
        data.put("resetToken", token);
        data.put("tip", "重置凭证已生成，请在下一步提交");
        return Result.ok(data);
    }

    @PostMapping("/entry/gate/credential/confirm")
    public Result confirm(@RequestBody Map<String, String> body) {
        String account = body.get("account");
        String token = body.get("resetToken");
        String newPwd = body.get("newPwd");
        String cached = resetTokens.get(account);
        if (cached == null || !cached.equals(token)) {
            return Result.fail("重置凭证无效");
        }
        String md5 = DigestUtils.md5DigestAsHex(newPwd.getBytes(StandardCharsets.UTF_8));
        accountMapper.updatePwd(account, md5);
        resetTokens.remove(account);
        return Result.ok();
    }

    @PostMapping("/entry/gate/profile/save")
    public Result profileSave(@RequestBody Map<String, Object> body) {
        Long uid = Long.valueOf(body.get("uid").toString());
        String nickname = (String) body.get("nickname");
        String phone = (String) body.get("phone");
        Integer roleId = body.get("roleId") == null ? null : Integer.valueOf(body.get("roleId").toString());
        SysAccount update = new SysAccount();
        update.setId(uid);
        update.setNickname(nickname);
        update.setPhone(phone);
        update.setRoleId(roleId);
        accountMapper.updateProfile(update);
        return Result.ok();
    }

    @GetMapping("/entry/gate/memo/decode")
    public Result memoDecode(@RequestParam("data") String data) throws Exception {
        return Result.ok(SecretBox.decrypt(data));
    }
}
