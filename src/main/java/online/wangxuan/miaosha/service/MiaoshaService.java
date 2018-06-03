package online.wangxuan.miaosha.service;

import online.wangxuan.miaosha.dao.GoodsDao;
import online.wangxuan.miaosha.domain.Goods;
import online.wangxuan.miaosha.domain.MiaoshaOrder;
import online.wangxuan.miaosha.domain.MiaoshaUser;
import online.wangxuan.miaosha.domain.OrderInfo;
import online.wangxuan.miaosha.exception.GlobalException;
import online.wangxuan.miaosha.redis.GoodsKey;
import online.wangxuan.miaosha.redis.MiaoshaKey;
import online.wangxuan.miaosha.redis.RedisService;
import online.wangxuan.miaosha.result.CodeMsg;
import online.wangxuan.miaosha.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author wangxuan
 * @date 2018/5/31 上午9:38
 */
@Service
public class MiaoshaService {

    @Autowired
    GoodsService goodsService;
    @Autowired
    OrderService orderService;
    @Autowired
    RedisService redisService;

    @Transactional
    public OrderInfo miaosha(MiaoshaUser user, GoodsVo goods) {

        // 减库存
        boolean res = goodsService.reduceStock(goods);

        // 下订单
        if (res) {
            return orderService.createOrder(user, goods);
        } else {
            setGoodsOver(goods.getId());
            throw new GlobalException(CodeMsg.MIAO_SHA_OVER);
        }
    }

    public long getMiaoshaResult(Long id, long goodsId) {
        
        MiaoshaOrder order = orderService.getMiaoshaOrderByUserIdGoodsId(id, goodsId);
        if (order != null) {
            return order.getId();
        } else {
            boolean isOver = getGoodsOver(goodsId);
            if (isOver) {
                return -1;
            } else {
                return 0;
            }
        }
    }

    private boolean getGoodsOver(long goodsId) {
        return redisService.exists(MiaoshaKey.isGoodsOver, "" + goodsId);
    }

    public void setGoodsOver(Long goodsId) {
        redisService.set(MiaoshaKey.isGoodsOver, "" + goodsId, true);
    }

    public void reset(List<GoodsVo> goodsList) {
        goodsService.resetStock(goodsList);
        orderService.deleteOrders();
    }


}
