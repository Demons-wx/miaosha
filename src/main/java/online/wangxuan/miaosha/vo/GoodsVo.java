package online.wangxuan.miaosha.vo;

import lombok.Data;
import online.wangxuan.miaosha.domain.Goods;

import java.util.Date;

/**
 * @author wangxuan
 * @date 2018/5/30 下午10:41
 */
@Data
public class GoodsVo extends Goods {

    private Double miaoshaPrice;
    private Integer stockCount;
    private Date startDate;
    private Date endDate;
}
