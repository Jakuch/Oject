package pl.sdacademy.sdafinalprojectrest.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl.sdacademy.sdafinalprojectrest.model.user.User;
import pl.sdacademy.sdafinalprojectrest.service.UserDetailsServiceImpl;


@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    public SecurityConfiguration(UserDetailsServiceImpl userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf()
                .disable()
                .httpBasic()
                .and()
                .authorizeRequests()
                .antMatchers("/client/**")
                .permitAll()
                .antMatchers(HttpMethod.POST, "/users")
                .permitAll()
                .antMatchers("/projects", "/client/home-page.html").hasAnyRole(User.Role.USER.name(), User.Role.ADMIN.name())
                .antMatchers(HttpMethod.GET,"/users")
                .hasRole(User.Role.ADMIN.name())
                .anyRequest()
                .authenticated();

    }
}
