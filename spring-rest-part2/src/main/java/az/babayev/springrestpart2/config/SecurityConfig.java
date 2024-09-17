package az.babayev.springrestpart2.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    private DataSource dataSource;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource);
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();

        http.authorizeRequests()
                .antMatchers("/h2-console/**").permitAll()
                .antMatchers(HttpMethod.POST, "/users/**").permitAll()
                .antMatchers(HttpMethod.POST, "/products/**").permitAll()
                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()

                .anyRequest().authenticated().and()
                .httpBasic().and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.headers().frameOptions().disable();
    }


//    @Bean
//    protected SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http.csrf(csrf ->
//                csrf.disable());
//
//        http.authorizeHttpRequests(request -> request
//                        .requestMatchers("/h2-console/**").permitAll()
//                        .requestMatchers(HttpMethod.POST, "/users/**").permitAll()
//                        .requestMatchers(HttpMethod.POST, "/products/**").permitAll()
//                        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
//
//                        .anyRequest().authenticated()
//
//                )
//                .sessionManagement(session -> session
//                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                )
//
//                .httpBasic(Customizer.withDefaults());
//
//        //H2 konsolu, <iframe> istifadə edərək işləyir. "X-Frame-Options" başlığının aktiv olması, təhlükəsizlik baxımından
//        // iframe içərisində səhifələrin yüklənməsini məhdudlaşdırır. Buna görə H2 konsoluna icazə vermək üçün bu
//        // başlıq deaktiv edilir.
//        http.headers(headers ->
//                headers.frameOptions(frame ->
//                        frame.disable()));
//
//        return http.build();
//    }


}
