package co.com.john.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private UserDetailsService userDetailsService;
    
    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    
    //@Autowired
    public void configurerGlobal(AuthenticationManagerBuilder build) throws Exception{
        build.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authz -> authz
                .requestMatchers("/editar/**", "/agregar/**", "/guardar/**", "/eliminar")
                .hasRole("ADMIN")
                .requestMatchers("/", "/login/**","/errores/403")
                .hasAnyRole("ADMIN", "USER"))
                
                .exceptionHandling((exceptionHandling)
                        -> exceptionHandling
                        .accessDeniedPage("/errores/403")
                );

//              .formLogin(withDefaults());
        http
                .formLogin(form -> form
                .loginPage("/login")
                .permitAll()
                )
                .logout((logout) -> logout
                .logoutSuccessUrl("/login")
                .permitAll()
                );

        return http.build();

    }
}
