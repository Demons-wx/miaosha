package online.wangxuan.miaosha.redis;

/**
 * @author wangxuan
 * @date 2018/5/30 下午2:06
 */

public class OrderKey extends BasePrefix {

    public OrderKey(String prefix) {
        super(prefix);
    }

    public static OrderKey getMiaoshaOrderByUidGid = new OrderKey("moug");
}
