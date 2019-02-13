package br.zone.learnspring.learn.config;

import br.zone.learnspring.learn.security.JWTAuthenticationFilter;
import br.zone.learnspring.learn.security.JWTLoginFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                   .authorizeRequests()
                        .antMatchers("/login", "/user/register").permitAll()
                   .anyRequest().authenticated()
                   .and()

                     // filtra requisições de login
                    .addFilterBefore(new JWTLoginFilter("/login", authenticationManager()),
                        UsernamePasswordAuthenticationFilter.class)

                    // filtra outras requisições para verificar a presença do JWT no header
                    .addFilterBefore(new JWTAuthenticationFilter(),
                        UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
                .usersByUsernameQuery("SELECT username, password, status FROM user WHERE username = ?");
    }

}
