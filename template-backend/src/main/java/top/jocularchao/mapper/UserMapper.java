package top.jocularchao.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import top.jocularchao.entity.Account;

/**
 * @author jocularchao
 * @date 2023-12-13 17:28
 * @description 账户mapper接口
 */
@Mapper
public interface UserMapper {

    //根据用户名或密码找到用户
    @Select("select * from db_account where username = #{text} or email = #{text}")
    Account findAccountByNameOrEmail(String text);

}
