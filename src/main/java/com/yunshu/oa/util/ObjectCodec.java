package com.yunshu.oa.util;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.util.Base64;

/**
 * 缓存对象编解码
 */
public class ObjectCodec {

    public static Object restore(String payload) throws Exception {
        byte[] bytes = Base64.getDecoder().decode(payload);
        ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(bytes));
        Object obj = ois.readObject();
        ois.close();
        return obj;
    }
}
