package online.wangxuan.miaosha.vo;

import lombok.Data;
import online.wangxuan.miaosha.domain.OrderInfo;

/**
 * @author wangxuan
 * @date 2018/6/2 下午3:29
 */
@Data
public class OrderDetailVo {

    private GoodsVo goods;
    private OrderInfo order;

}
