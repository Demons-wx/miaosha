package online.wangxuan.miaosha.redis;

/**
 * @author wangxuan
 * @date 2018/5/30 下午2:06
 */

public class UserKey extends BasePrefix {

    public UserKey(String prefix) {
        super(prefix);
    }

    public static UserKey getById = new UserKey("id");
    public static UserKey getByName = new UserKey("name");

}
