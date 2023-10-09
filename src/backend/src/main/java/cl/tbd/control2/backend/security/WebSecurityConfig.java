package cl.tbd.control2.backend.security;


import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

@Configuration
@AllArgsConstructor
public class WebSecurityConfig {
    private final UserDetailsService userDetailsService;
    private final JWTAuthorizationFilter jwtAuthorizationFilter;

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http, AuthenticationManager authManager) throws Exception{

        JWTAuthenticationFilter jwtAuthenticationFilter = new JWTAuthenticationFilter();
        jwtAuthenticationFilter.setAuthenticationManager(authManager);
        jwtAuthenticationFilter.setFilterProcessesUrl("usuario/login");

        // Configuración CORS para permitir cualquier origen
        http.cors().configurationSource(request -> {
            CorsConfiguration corsConfig = new CorsConfiguration();
            corsConfig.addAllowedOrigin("*"); // Permite solicitudes desde cualquier origen
            corsConfig.addAllowedMethod("*");
            corsConfig.addAllowedHeader("*");
            return corsConfig;
        });


        return http
                .csrf().disable()
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers(HttpMethod.POST, "/usuario/register", "usuario/login").permitAll()
                        .requestMatchers(HttpMethod.GET,"/tareas/**").authenticated()
                        .requestMatchers(HttpMethod.PUT,"/tarea/**").authenticated()
                        .requestMatchers(HttpMethod.DELETE,"/tarea/**").authenticated()
                        .anyRequest().authenticated())
                .httpBasic()
                .authenticationEntryPoint(authenticationEntryPoint())
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilter(jwtAuthenticationFilter)
                .addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();




    }


    @Bean
    public AuthenticationEntryPoint authenticationEntryPoint() {
        return new CustomAuthenticationEntryPoint();
    }


    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    AuthenticationManager authManager(HttpSecurity http, PasswordEncoder passwordEncoder) throws Exception{
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder())
                .and()
                .build();
    }


    public static void main(String[] args){
        System.out.println("pass: " + new BCryptPasswordEncoder().encode("password"));
    }
}
