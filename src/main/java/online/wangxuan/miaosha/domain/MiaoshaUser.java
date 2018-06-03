package online.wangxuan.miaosha.domain;

import lombok.Data;

import java.util.Date;

/**
 * @author wangxuan
 * @date 2018/5/30 下午3:31
 */

@Data
public class MiaoshaUser {

    private Long id;
    private String nickname;
    private String password;
    private String salt;
    private String head;
    private Date registerDate;
    private Date lastLoginDate;
    private Integer loginCount;

}
