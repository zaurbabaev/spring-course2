package az.babayev.springcore;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
public class CPU {
    private Integer price;
    private Integer speed;

    public CPU() {
        speed = 2300;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getSpeed() {
        return speed;
    }

    public void setSpeed(Integer speed) {
        this.speed = speed;
    }


    @Override
    public String toString() {
        return "CPU{" +
                "price=" + price +
                ", speed=" + speed +
                '}';
    }
}
