package az.babayev.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Computer {

    private Integer id;
    private String model;
    private String brand;
    private Double price;


}
