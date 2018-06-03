package online.wangxuan.miaosha.vo;

import lombok.Data;
import lombok.NonNull;
import online.wangxuan.miaosha.validator.IsMobile;
import org.hibernate.validator.constraints.Length;

/**
 * @author wangxuan
 * @date 2018/5/30 下午3:15
 */

@Data
public class LoginVo {

    @NonNull
    @IsMobile
    private String mobile;
    @NonNull
    @Length(min=32)
    private String password;

    public LoginVo() {
    }
}
