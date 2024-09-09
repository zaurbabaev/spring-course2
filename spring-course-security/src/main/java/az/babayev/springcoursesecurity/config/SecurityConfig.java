package az.babayev.springcoursesecurity.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;


    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                // Yalnız likdə göstərilən GET sorğularına icazə verir
                .authorizeRequests().antMatchers(HttpMethod.GET, "/admins/create-admin-account").permitAll()
                .antMatchers(HttpMethod.POST, "/admins/add").permitAll()
                // Digər bütün sorğular autentifikasiya tələb edir
                .anyRequest().authenticated()
                .and()
                // Login səhifəsi üçün xüsusi URL təyin edilir
                .formLogin().loginPage("/users/open-our-login")
                // Login prosesi üçün icazə verilir
                .loginProcessingUrl("/authenticate-user").permitAll()
                .and()
                // Çıxış üçün xüsusi URL və icazə
                .logout().logoutUrl("users/logout").permitAll();
    }

//          Yeni versiya
//        @Bean
//        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//            http
//                    .authorizeHttpRequests(authorizeRequests ->
//                            authorizeRequests
//                                    .requestMatchers(HttpMethod.GET, "/admins/create-admin-account").permitAll()
//                                    .anyRequest().authenticated()
//                    )
//                    .formLogin(formLogin ->
//                            formLogin
//                                    .loginPage("/users/open-our-login")
//                                    .loginProcessingUrl("/authenticate-user")
//                                    .permitAll()
//                    )
//                    .logout(logout ->
//                            logout
//                                    .logoutUrl("/users/logout")
//                                    .permitAll()
//                    );
//
//            return http.build();
//        }


}
