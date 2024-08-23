package az.babayev.springcore;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyConfig {

    @Value(value = "${myConfig.limit}")
    private Integer limit;

    public Integer getLimit() {
        return limit;
    }

    @Bean
    public Person myPerson() {
        Person person = new Person();
        person.setName("Vusal");
        return person;
    }

    @Bean
//    @Primary
    public CPU myCpu(){
        CPU cpu = new CPU();
        cpu.setSpeed(2000);
        return cpu;
    }

}
