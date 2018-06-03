package online.wangxuan.miaosha.domain;

import lombok.Data;

/**
 * @author wangxuan
 * @date 2018/5/30 下午10:37
 */
@Data
public class MiaoshaOrder {

    private Long id;
    private Long userId;
    private Long  orderId;
    private Long goodsId;
}
