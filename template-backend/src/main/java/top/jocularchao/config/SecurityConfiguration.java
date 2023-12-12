package top.jocularchao.config;

import com.alibaba.fastjson2.JSONObject;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import top.jocularchao.entity.RestBean;

import java.io.IOException;

/**
 * @author jocularchao
 * @date 2023-12-12 16:34
 * @description spring security配置类
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                //先做权限校验，没有登录不能进入
                .authorizeHttpRequests()
                //目前没有分角色，没有其他的功能，直接所有请求全部需要验证
                .anyRequest().authenticated()
                .and()
                //配置登录接口
                .formLogin()
                .loginProcessingUrl("/api/auth/login")
                //登录成功后处理
                .successHandler(this::onAuthenticationSuccess)
                //登录失败的处理
                .failureHandler(this::onAuthenticationFailure)
                .and()
                //配置登出接口
                .logout()
                .logoutUrl("/api/auth/logout")
                .and()
                //暂时关闭csrf校验
                .csrf()
                .disable()
                //在未授权时会调用这个commence方法，输出重写的信息
                .exceptionHandling()
                .authenticationEntryPoint(this::onAuthenticationFailure)
                .and()
                .build();

    }

    //登录成功
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        //设置编码格式
        response.setCharacterEncoding("utf-8");
        response.getWriter().write(JSONObject.toJSONString(RestBean.success("登录成功")));
    }

    //登录失败
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        //避免乱码
        response.setCharacterEncoding("utf-8");
        response.getWriter().write(JSONObject.toJSONString(RestBean.failure(401,exception.getMessage())));
    }



}
