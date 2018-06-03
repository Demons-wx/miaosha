package online.wangxuan.miaosha.utils;

import java.util.UUID;

/**
 * @author wangxuan
 * @date 2018/5/30 下午3:45
 */

public class UUIDUtil {
    public static String uuid() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
