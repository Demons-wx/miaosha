package online.wangxuan.miaosha.redis;

/**
 * @author wangxuan
 * @date 2018/5/30 下午4:52
 */

public class MiaoShaUserKey extends BasePrefix {

    public static final int TOKEN_EXPIRE = 3600 * 24 * 2;


    public MiaoShaUserKey(int expireSeconds, String prefix) {
        super(expireSeconds, prefix);
    }

    public static MiaoShaUserKey token = new MiaoShaUserKey(TOKEN_EXPIRE, "tk");
    public static MiaoShaUserKey getById = new MiaoShaUserKey(0, "id");

}
