package online.wangxuan.miaosha.controller;

import lombok.extern.log4j.Log4j;
import online.wangxuan.miaosha.redis.RedisService;
import online.wangxuan.miaosha.result.Result;
import online.wangxuan.miaosha.service.MiaoshaUserService;
import online.wangxuan.miaosha.vo.LoginVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * @author wangxuan
 * @date 2018/5/30 下午3:06
 */

@Controller
@RequestMapping("/login")
@Log4j
public class LoginController {

    @Autowired
    MiaoshaUserService userService;

    @Autowired
    RedisService redisService;

    @RequestMapping("/to_login")
    public String toLogin() {
        return "login";
    }

    @RequestMapping("/do_login")
    @ResponseBody
    public Result<String> doLogin(HttpServletResponse response, @Valid LoginVo loginVo) {
        log.info(loginVo.toString());
        //登录
        String token = userService.login(response, loginVo);
        return Result.success(token);
    }

}
