package com.benbird.bencenter.config;

import com.benbird.bencenter.handler.JwtAuthenticationTokenFilter;
import com.benbird.bencenter.handler.RestAuthenticationEntryPoint;
import com.benbird.bencenter.handler.RestfulAccessDeniedHandler;
import com.benbird.bencenter.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsUtils;

/**
 * 项目名: bencenter
 * 创建者: Admin
 * 创建时间: 2021/1/14
 * 描述: springSecurity 配置
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private RestfulAccessDeniedHandler restfulAccessDeniedHandler;

    @Autowired
    private RestAuthenticationEntryPoint restAuthenticationEntryPoint;

    @Autowired
    private JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;

    /**
     * 认证用户的来源
     * @param auth          认证
     * @throws Exception    Exception
     */
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        System.out.println("开始认证操作");
        auth.userDetailsService(userInfoService).passwordEncoder(new BCryptPasswordEncoder());
    }

    /**
     * 配置SpringSecurity相关信息
     * @param httpSecurity  httpSecurity
     * @throws Exception    Exception
     */
    @Override
    public void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                // 允许跨域访问
                .cors()
                .and()
                .authorizeRequests()
                .requestMatchers(CorsUtils::isPreFlightRequest).permitAll()
                // 不需要保护的资源路径允许访问
                .antMatchers("/user/login").permitAll()
                // 允许跨域请求的OPTIONS请求
                .antMatchers(HttpMethod.OPTIONS)
                .permitAll()
                .and()
                .authorizeRequests()
                .anyRequest()
                .authenticated()
                // 关闭跨站请求防护及不使用session
                .and()
                .csrf()
                .disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                // 自定义权限拒绝处理类
                .and()
                .exceptionHandling()
                // 权限不足
                .accessDeniedHandler(restfulAccessDeniedHandler)
                // 未登录或登录过期
                .authenticationEntryPoint(restAuthenticationEntryPoint)
                // 自定义权限拦截器JWT过滤器
                .and()
                .addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);
    }


}
