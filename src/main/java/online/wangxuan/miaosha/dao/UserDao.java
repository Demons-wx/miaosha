package online.wangxuan.miaosha.dao;

import online.wangxuan.miaosha.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @author wangxuan
 * @date 2018/5/30 上午10:36
 */

@Mapper
public interface UserDao {

    @Select("select * from user where id = #{id}")
    public User getById(@Param("id") int id);

    @Insert("insert into user (id, name) values (#{id}, #{name})")
    public int insert(User user);
}
