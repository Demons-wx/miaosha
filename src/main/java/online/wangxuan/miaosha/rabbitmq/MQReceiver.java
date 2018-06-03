package online.wangxuan.miaosha.rabbitmq;

import lombok.extern.log4j.Log4j;
import online.wangxuan.miaosha.domain.MiaoshaOrder;
import online.wangxuan.miaosha.domain.MiaoshaUser;
import online.wangxuan.miaosha.domain.OrderInfo;
import online.wangxuan.miaosha.redis.RedisService;
import online.wangxuan.miaosha.result.CodeMsg;
import online.wangxuan.miaosha.result.Result;
import online.wangxuan.miaosha.service.GoodsService;
import online.wangxuan.miaosha.service.MiaoshaService;
import online.wangxuan.miaosha.service.OrderService;
import online.wangxuan.miaosha.vo.GoodsVo;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author wangxuan
 * @date 2018/6/3 上午11:07
 */
@Service
@Log4j
public class MQReceiver {

    @Autowired
    GoodsService goodsService;
    @Autowired
    OrderService orderService;
    @Autowired
    MiaoshaService miaoshaService;
    @Autowired
    RedisService redisService;

//    @RabbitListener(queues = MQConfig.QUEUE)
//    public void receive(String message) {
//
//        log.info("receive message: " + message);
//    }
//
//    @RabbitListener(queues = MQConfig.TOPIC_QUEUE1)
//    public void receiveTopic1(String message) {
//
//        log.info("topic queue1 message: " + message);
//    }
//
//    @RabbitListener(queues = MQConfig.TOPIC_QUEUE2)
//    public void receiveTopic2(String message) {
//
//        log.info("topic queue2 message: " + message);
//    }
//
//    @RabbitListener(queues = MQConfig.HEADERS_QUEUE)
//    public void receiveHeaders(byte[] message) {
//
//        log.info("headers queue message: " + new String(message));
//    }

    @RabbitListener(queues = MQConfig.MIAOSHA_QUEUE)
    public void receive(String message) {
        log.info("headers queue message: " + message);
        MiaoshaMessage mm = RedisService.stringToBean(message, MiaoshaMessage.class);
        MiaoshaUser user = mm.getUser();
        long goodsId = mm.getGoodsId();

        //判断库存
        GoodsVo goods = goodsService.getGoodsVoByGoodsId(goodsId);
        int stock = goods.getStockCount();
        if(stock <= 0) {
            return;
        }

        //判断是否已经秒杀到了
        MiaoshaOrder order = orderService.getMiaoshaOrderByUserIdGoodsId(user.getId(), goodsId);
        if(order != null) {
            return;
        }

        //减库存 下订单 写入秒杀订单
        miaoshaService.miaosha(user, goods);
    }
}
