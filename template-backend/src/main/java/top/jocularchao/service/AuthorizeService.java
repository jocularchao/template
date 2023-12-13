package top.jocularchao.service;

import jakarta.annotation.Resource;
import lombok.val;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import top.jocularchao.entity.Account;
import top.jocularchao.mapper.UserMapper;

/**
 * @author jocularchao
 * @date 2023-12-12 19:34
 * @description 权限校验的服务
 */
@Service
public class AuthorizeService implements UserDetailsService {   //实现UserDetailsService接口从数据库中取东西

    //注入mapper
    @Resource
    UserMapper mapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //
        if (username == null){
            throw new UsernameNotFoundException("用户名不能为空");
        }
        Account account = mapper.findAccountByNameOrEmail(username);
        //获取的account用户对象，如果为空则说明，没有从数据库中取到该对象，说明用户名或密码错误
        if (account == null){
            throw new UsernameNotFoundException("用户名或密码错误");
        }
        return User
                .withUsername(account.getUsername())
                .password(account.getPassword())
                .roles("user")
                .build();
    }
}
