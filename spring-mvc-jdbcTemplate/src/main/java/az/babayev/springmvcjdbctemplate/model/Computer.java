package az.babayev.springmvcjdbctemplate.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Computer {
    private Integer id;
    private String model;
    private String brand;
    private double price;

}
