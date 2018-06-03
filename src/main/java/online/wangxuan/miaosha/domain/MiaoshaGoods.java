package online.wangxuan.miaosha.domain;

import lombok.Data;

import java.util.Date;

/**
 * @author wangxuan
 * @date 2018/5/30 下午10:36
 */

@Data
public class MiaoshaGoods {

    private Long id;
    private Long goodsId;
    private Double miaoshaPrice;
    private Integer stockCount;
    private Date startDate;
    private Date endDate;
}
