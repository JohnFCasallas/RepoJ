package co.com.john.web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import static org.springframework.security.config.Customizer.withDefaults;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    /*    public InMemoryUserDetailsManager userDetailsService() {
        UserDetails user = User.withDefaultPasswordEncoder()
            .username("admin")
            .password("123")
            .roles("ADMIN","USER")
            .build();
        return new InMemoryUserDetailsManager(user);
    }
     */
    public UserDetailsService users() {
        UserDetails admin = User.builder()
                .username("admin")
                .password("{noop}456")
                .roles("ADMIN", "USER")
                .build();
        UserDetails user = User.builder()
                .username("user")
                .password("{noop}123")
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(user, admin);
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
