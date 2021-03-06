package online.wangxuan.miaosha.service;

import online.wangxuan.miaosha.dao.UserDao;
import online.wangxuan.miaosha.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author wangxuan
 * @date 2018/5/30 上午10:39
 */

@Service
public class UserService {

    @Autowired(required = false)
    UserDao userDao;

    public User getById(int id) {
        return userDao.getById(id);
    }

 //   @Transactional(rollbackFor = Exception.class)
    public boolean tx() {
        User user1 = new User();
        user1.setId(2);
        user1.setName("222");
        userDao.insert(user1);

        User user2 = new User();
        user2.setId(1);
        user2.setName("111");
        userDao.insert(user2);

        return true;
    }

}
