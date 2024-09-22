package az.babayev.springrest.config;

import az.babayev.springrest.SpringRestApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;



// bu ayar proyektin war file olaraq export olunması üçündür
public class ServletInitializer extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(SpringRestApplication.class);
    }
}
