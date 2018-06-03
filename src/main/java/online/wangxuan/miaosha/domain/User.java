package online.wangxuan.miaosha.domain;

import lombok.Data;

/**
 * @author wangxuan
 * @date 2018/5/30 上午10:34
 */
@Data
public class User {

    private Integer id;
    private String name;

    public User() {
    }

    public User(Integer id, String name) {
        this.id = id;
        this.name = name;
    }
}
