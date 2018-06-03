package online.wangxuan.miaosha.domain;

import lombok.Data;

/**
 * @author wangxuan
 * @date 2018/5/30 下午10:35
 */

@Data
public class Goods {

    private Long id;
    private String goodsName;
    private String goodsTitle;
    private String goodsImg;
    private String goodsDetail;
    private Double goodsPrice;
    private Integer goodsStock;
}
