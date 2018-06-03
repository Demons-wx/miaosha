package online.wangxuan.miaosha.vo;

import lombok.Data;
import online.wangxuan.miaosha.domain.MiaoshaUser;

/**
 * @author wangxuan
 * @date 2018/6/1 下午10:19
 */
@Data
public class GoodsDetailVo {

    private int miaoshaStatus = 0;
    private int remainSeconds = 0;
    private GoodsVo goods;
    private MiaoshaUser user;
}
