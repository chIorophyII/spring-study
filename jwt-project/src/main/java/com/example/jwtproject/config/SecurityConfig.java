package com.example.jwtproject.config;

import com.example.jwtproject.filter.MyFilter1;
import com.example.jwtproject.filter.MyFilter3;
import lombok.RequiredArgsConstructor;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.context.SecurityContextPersistenceFilter;
import org.springframework.web.filter.CorsFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

//    private final CorsConfig corsConfig;
    private final CorsFilter corsFilter;
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .addFilterBefore(new MyFilter3(), SecurityContextPersistenceFilter.class);
                /* 시큐리티 필터만 등록되는데 MyFilter는 시큐리티 필터가 아니므로 등록이 안됨
                 .addFilter(new MyFilter1());
                 필터로 등록하고 싶으면 addFilterBefore나 addFilterAfter로 걸어라
                 BasicAuthFilter가 실행되기 전에 MyFilter 실행
                .addFilterBefore(new MyFilter1(), BasicAuthenticationFilter.class);
                근데 굳이 여기다 등록할 필요 없음 -> FilterConfig 만들자
                 */
        http
                .csrf().disable();
        http
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // 세션을 사용하지 않겠다
                .and()
                /* 시큐리티 인증이 필요할 때 필터에 등록은 이렇게
                 인증이 필요 없으면 컨트롤러에다 @CrossOrigin */
                .addFilter(corsFilter)
                .formLogin().disable()
                .httpBasic().disable()
                .authorizeRequests()
                .antMatchers("/api/vi/user/**")
                .access("hasRole('ROLE_USER') or hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
                .antMatchers("api/vi/manager/**")
                .access("hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
                .antMatchers("api/vi/admin")
                .access("hasRole('ROLE_ADMIN')")
                .anyRequest().permitAll();
    }
}
