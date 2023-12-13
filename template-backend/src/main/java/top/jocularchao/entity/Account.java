package top.jocularchao.entity;

import lombok.Data;

/**
 * @author jocularchao
 * @date 2023-12-13 17:29
 * @description 账户实体类
 */
@Data
public class Account {
    int id;     //id
    String username;    //用户名
    String email;       //密码
    String password;    //邮箱
}
