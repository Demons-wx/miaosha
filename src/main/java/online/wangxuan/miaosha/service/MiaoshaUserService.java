package online.wangxuan.miaosha.service;

import com.sun.xml.internal.rngom.digested.DDataPattern;
import online.wangxuan.miaosha.dao.MiaoShaUserDao;
import online.wangxuan.miaosha.domain.MiaoshaUser;
import online.wangxuan.miaosha.exception.GlobalException;
import online.wangxuan.miaosha.redis.MiaoShaUserKey;
import online.wangxuan.miaosha.redis.RedisService;
import online.wangxuan.miaosha.result.CodeMsg;
import online.wangxuan.miaosha.utils.MD5Util;
import online.wangxuan.miaosha.utils.UUIDUtil;
import online.wangxuan.miaosha.vo.LoginVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

/**
 * @author wangxuan
 * @date 2018/5/30 下午3:17
 */

@Service
public class MiaoshaUserService {

    public static final String COOKIE_NAME_TOKEN = "token";

    @Autowired(required = false)
    MiaoShaUserDao miaoShaUserDao;
    @Autowired
    RedisService redisService;

    public MiaoshaUser getById(long id) {

        // 取缓存
        MiaoshaUser user = redisService.get(MiaoShaUserKey.getById, "" + id, MiaoshaUser.class);
        if (user != null) {
            return user;
        }

        // 取数据库
        user = miaoShaUserDao.getById(id);
        if (user != null) {
            redisService.set(MiaoShaUserKey.getById, "" + id, user);
        }
        return user;

    }

    public boolean updatePassword(String token, long id, String formPass) {
        // 取user
        MiaoshaUser user = getById(id);
        if (user == null) {
            throw new GlobalException(CodeMsg.MOBILE_NOT_EXIST);
        }

        // 更新数据库
        MiaoshaUser toBeUpdate = new MiaoshaUser();
        toBeUpdate.setId(id);
        toBeUpdate.setPassword(MD5Util.formPassToDBPass(formPass, user.getSalt()));
        miaoShaUserDao.update(toBeUpdate);

        // 处理缓存
        redisService.delete(MiaoShaUserKey.getById, "" + id);
        user.setPassword(toBeUpdate.getPassword());
        redisService.set(MiaoShaUserKey.token, token, user);

        return true;
    }

    public String login(HttpServletResponse response, LoginVo loginVo) {
        if(loginVo == null) {
            throw new GlobalException(CodeMsg.SERVER_ERROR);
        }
        String mobile = loginVo.getMobile();
        String formPass = loginVo.getPassword();
        //判断手机号是否存在
        MiaoshaUser user = getById(Long.parseLong(mobile));
        if(user == null) {
            throw new GlobalException(CodeMsg.MOBILE_NOT_EXIST);
        }
        //验证密码
        String dbPass = user.getPassword();
        String saltDB = user.getSalt();
        String calcPass = MD5Util.formPassToDBPass(formPass, saltDB);
        if(!calcPass.equals(dbPass)) {
            throw new GlobalException(CodeMsg.PASSWORD_ERROR);
        }
        //生成cookie
        String token = UUIDUtil.uuid();
        addCookie(response, token, user);
        return token;
    }

    private void addCookie(HttpServletResponse response,
                           String token,
                           MiaoshaUser user) {
        redisService.set(MiaoShaUserKey.token, token, user);
        Cookie cookie = new Cookie(COOKIE_NAME_TOKEN, token);
        cookie.setMaxAge(MiaoShaUserKey.token.expireSeconds());
        cookie.setPath("/");

        response.addCookie(cookie);
    }

    public MiaoshaUser getByToken(HttpServletResponse response, String token) {
        if (StringUtils.isEmpty(token)) {
            return null;
        }
        MiaoshaUser user = redisService.get(MiaoShaUserKey.token, token, MiaoshaUser.class);
        if (user != null) {
            addCookie(response, token, user);
        }

        return user;
    }
}
