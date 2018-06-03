package online.wangxuan.miaosha.redis;

/**
 * @author wangxuan
 * @date 2018/5/30 下午2:00
 */

public interface KeyPrefix {

    public int expireSeconds();

    public String getPrefix();
}
