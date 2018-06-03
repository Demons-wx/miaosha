package online.wangxuan.miaosha.rabbitmq;

import lombok.Data;
import online.wangxuan.miaosha.domain.MiaoshaUser;

/**
 * @author wangxuan
 * @date 2018/6/3 下午4:34
 */
@Data
public class MiaoshaMessage {

    private MiaoshaUser user;
    private long goodsId;

}
