//package az.babayev.springrest.config;
//
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.Customizer;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//
//import javax.sql.DataSource;
//
//
//@Configuration
//@EnableWebSecurity
//@RequiredArgsConstructor
//public class SecurityConfig {
//
//    public final DataSource dataSource;
//
//    @Bean
//    public AuthenticationManager authManager(HttpSecurity http) throws Exception {
//        AuthenticationManagerBuilder authenticationManagerBuilder =
//                http.getSharedObject(AuthenticationManagerBuilder.class);
//        authenticationManagerBuilder
//                .jdbcAuthentication()
//                .dataSource(dataSource)
//                .passwordEncoder(passwordEncoder());
//        return authenticationManagerBuilder.build();
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
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
//                        .requestMatchers("/languages/**").permitAll()
//                        .requestMatchers("/students/**").permitAll()
//                        .requestMatchers("/swagger-ui/**", "/v2/api-docs", "/swagger-resources/**", "/webjars/**").permitAll()
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
//
//
//}
