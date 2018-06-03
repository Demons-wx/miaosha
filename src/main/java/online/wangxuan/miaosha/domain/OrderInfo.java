package online.wangxuan.miaosha.domain;

import lombok.Data;

import java.util.Date;

/**
 * @author wangxuan
 * @date 2018/5/30 下午10:36
 */

@Data
public class OrderInfo {
    private Long id;
    private Long userId;
    private Long goodsId;
    private Long  deliveryAddrId;
    private String goodsName;
    private Integer goodsCount;
    private Double goodsPrice;
    private Integer orderChannel;
    private Integer status;
    private Date createDate;
    private Date payDate;
}
