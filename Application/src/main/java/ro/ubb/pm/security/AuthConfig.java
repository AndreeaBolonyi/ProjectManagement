package ro.ubb.pm.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import ro.ubb.pm.bll.users.UserBLL;

@Configuration
public class AuthConfig extends WebSecurityConfigurerAdapter {

    private static final String[] REQUESTS_NO_AUTH = {
            "/login"
    };

    private static final String[] REQUESTS_USER = {
            "/dashboard", "/project_management/get-all-by-project/{projectId}", "/project_management/sprints/get-current-sprint",
            "project_management/user-stories/get-all-by-sprint/{sprintId}", "project_management/user-stories/create",
            "/project_management/user-stories/update/{userStoryId}", "/project_management/user-stories/delete/{userStoryId}"
    };

    @Autowired
    private transient UserBLL userService;

    @Autowired
    private transient JwtRequestConfig jwtRequestConfig;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    protected void configureGlobal(final AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .cors().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, REQUESTS_NO_AUTH).permitAll()
                .and().authorizeRequests()
                .antMatchers(REQUESTS_USER).permitAll()//hasAuthority("Scrum Master")
                .and().authorizeRequests();
        http.addFilterBefore(jwtRequestConfig, UsernamePasswordAuthenticationFilter.class);
    }

}