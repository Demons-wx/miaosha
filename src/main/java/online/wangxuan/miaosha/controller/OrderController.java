package online.wangxuan.miaosha.controller;

import online.wangxuan.miaosha.domain.MiaoshaUser;
import online.wangxuan.miaosha.domain.OrderInfo;
import online.wangxuan.miaosha.redis.MiaoShaUserKey;
import online.wangxuan.miaosha.result.CodeMsg;
import online.wangxuan.miaosha.result.Result;
import online.wangxuan.miaosha.service.GoodsService;
import online.wangxuan.miaosha.service.OrderService;
import online.wangxuan.miaosha.vo.GoodsVo;
import online.wangxuan.miaosha.vo.OrderDetailVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author wangxuan
 * @date 2018/6/2 下午3:29
 */
@Controller
@RequestMapping("order")
public class OrderController {

    @Autowired
    OrderService orderService;
    @Autowired
    GoodsService goodsService;

    @RequestMapping("/detail")
    @ResponseBody
    public Result<OrderDetailVo> detail(Model model,
                                        MiaoshaUser user,
                                        @RequestParam("orderId") long orderId) {

        if (user == null) {
            return Result.error(CodeMsg.SESSION_ERROR);
        }

        OrderInfo orderInfo = orderService.getOrderById(orderId);
        if (orderInfo == null) {
            return Result.error(CodeMsg.ORDER_NOT_EXIST);
        }

        long goodsId = orderInfo.getGoodsId();
        GoodsVo goods = goodsService.getGoodsVoByGoodsId(goodsId);

        OrderDetailVo vo = new OrderDetailVo();
        vo.setGoods(goods);
        vo.setOrder(orderInfo);

        return Result.success(vo);
    }

}
