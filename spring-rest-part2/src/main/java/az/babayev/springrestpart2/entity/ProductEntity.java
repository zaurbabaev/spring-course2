package az.babayev.springrestpart2.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "products")
@Entity
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Size(min = 3, max = 30, message = "Name should be min 3 max 30 characters")
    @NotEmpty(message = "Name should be not empty")
    String name;


    @NotNull(message = "Price should not be empty")
    @Min(value = 0, message = "min value of price is 0")
    BigDecimal price;

    @NotNull(message = "Quantity should not be empty")
    @Min(value = 1, message = "The minimum quantity is 1")
    Integer quantity;

    String description;
}
