package com.yunshu.oa.util;

import java.util.HashMap;
import java.util.Map;

/**
 * 统一响应结构
 */
public class Result extends HashMap<String, Object> {

    public static Result ok(Object data) {
        Result r = new Result();
        r.put("code", 0);
        r.put("msg", "success");
        r.put("data", data);
        return r;
    }

    public static Result ok() {
        return ok(null);
    }

    public static Result fail(String msg) {
        Result r = new Result();
        r.put("code", 500);
        r.put("msg", msg);
        return r;
    }

    public static Result of(int code, String msg) {
        Result r = new Result();
        r.put("code", code);
        r.put("msg", msg);
        return r;
    }

    public Result put(String key, Object value) {
        super.put(key, value);
        return this;
    }
}
