package az.babayev;

import az.babayev.springcore.Book;
import az.babayev.springcore.Car;
import az.babayev.springcore.Person;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Arrays;

@SpringBootApplication
public class SpringCoreApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(SpringCoreApplication.class, args);
        Car car = context.getBean(Car.class);
        System.out.println(car);


        String[] beans = context.getBeanDefinitionNames();
        Arrays.stream(beans)
                .forEach(System.out::println);


        System.out.println("-".repeat(30));

        Book bookBean = context.getBean(Book.class);

        System.out.println(bookBean.getId());
        System.out.println(bookBean.getName());
        System.out.println(bookBean.getPrice());
        System.out.println(bookBean.getPageCount());

        System.out.println("-".repeat(30));

        Person personBean = context.getBean(Person.class);

        System.out.println(personBean);




    }

}
