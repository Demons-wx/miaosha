package online.wangxuan.miaosha.controller;

import online.wangxuan.miaosha.domain.User;
import online.wangxuan.miaosha.rabbitmq.MQSender;
import online.wangxuan.miaosha.redis.RedisService;
import online.wangxuan.miaosha.redis.UserKey;
import online.wangxuan.miaosha.result.Result;
import online.wangxuan.miaosha.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author wangxuan
 * @date 2018/5/30 上午10:07
 */
@Controller
@RequestMapping("/demo")
public class SampleController {

    @Autowired
    UserService userService;
    @Autowired
    RedisService redisService;
    @Autowired
    MQSender mqSender;

    @RequestMapping("/thymeleaf")
    public String  thymeleaf(Model model) {
        model.addAttribute("name", "Joshua");
        return "hello";
    }

    @RequestMapping("/db/get")
    @ResponseBody
    public Result<User> doGet() {
        User user = userService.getById(1);
        return Result.success(user);
    }

    @RequestMapping("/db/tx")
    @ResponseBody
    public Result<Boolean> doTx() {
        userService.tx();
        return Result.success(true);
    }

    @RequestMapping("/redis/get")
    @ResponseBody
    public Result<User> redisGet() {

        User user = redisService.get(UserKey.getById, "" + 1, User.class);
        return Result.success(user);
    }

    @RequestMapping("/redis/set")
    @ResponseBody
    public Result<Boolean> redisSet() {
        User user = new User(1, "wangxuan");
        boolean ret = redisService.set(UserKey.getById, "" + 1, user);
        return Result.success(ret);
    }

//    @RequestMapping("/mq")
//    @ResponseBody
//    public Result<String> mq() {
//        mqSender.send("hello, wx");
//        return Result.success("yes");
//    }
//
//    @RequestMapping("/mq/topic")
//    @ResponseBody
//    public Result<String> mqTopic() {
//        mqSender.sendTopic("hello, wx");
//        return Result.success("yes");
//    }
//
//    @RequestMapping("/mq/fanout")
//    @ResponseBody
//    public Result<String> mqFanout() {
//        mqSender.sendFanout("hello, wx");
//        return Result.success("yes");
//    }
//
//    @RequestMapping("/mq/headers")
//    @ResponseBody
//    public Result<String> mqHeaders() {
//        mqSender.sendHeaders("hello, wx");
//        return Result.success("yes");
//    }
}
