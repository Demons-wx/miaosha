package online.wangxuan.miaosha.redis;

/**
 * @author wangxuan
 * @date 2018/6/3 下午4:57
 */

public class MiaoshaKey extends BasePrefix {

    public MiaoshaKey(String prefix) {
        super(prefix);
    }

    public static MiaoshaKey isGoodsOver = new MiaoshaKey("go");
}
