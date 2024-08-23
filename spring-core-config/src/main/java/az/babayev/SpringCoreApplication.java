package az.babayev;

import az.babayev.springcore.Computer;
import az.babayev.springcore.Employee;
import az.babayev.springcore.MyConfig;
import az.babayev.springcore.Person;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Arrays;

@SpringBootApplication
public class SpringCoreApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(SpringCoreApplication.class, args);
//        Person person = context.getBean(Person.class);
//        System.out.println(person.getName());
        Computer computer = context.getBean(Computer.class);
        System.out.println(computer.getCpu().getSpeed());

        MyConfig bean = context.getBean(MyConfig.class);
        System.out.println(bean.getLimit());

        Employee employee = context.getBean(Employee.class);
        System.out.println(employee);

        context.close();

    }

}
